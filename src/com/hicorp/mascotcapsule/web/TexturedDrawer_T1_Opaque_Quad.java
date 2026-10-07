package com.hicorp.mascotcapsule.web;

public final class TexturedDrawer_T1_Opaque_Quad extends TexturedDrawer {
   private final Config rasterizer;

   public TexturedDrawer_T1_Opaque_Quad(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int stepDu = (super.duDxFixed > 0 ? super.duDxFixed : -super.duDxFixed) + (super.dvDxFixed > 0 ? super.dvDxFixed : -super.dvDxFixed) + 32768;
      int mipLevel = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(stepDu) - 17);
      int[] diffusePixels = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int mipOffset = Config.getDiffuseTexture(this.rasterizer).getMipOffset(mipLevel);
      int mipUMask = Config.getDiffuseTexture(this.rasterizer).getMipUMask(mipLevel);
      int mipVMask = Config.getDiffuseTexture(this.rasterizer).getMipVMask(mipLevel);
      int mipUShift = Config.getDiffuseTexture(this.rasterizer).getMipUShift(mipLevel);
      int mipVShift = Config.getDiffuseTexture(this.rasterizer).getMipVShift(mipLevel);
      int stepDu2 = super.duDxFixed >> mipLevel;
      int stepDv = super.dvDxFixed >> mipLevel;
      if (super.y < Config.getClipTop(this.rasterizer)) {
         int clipDeltaY;
         if (super.yEnd < Config.getClipTop(this.rasterizer)) {
            clipDeltaY = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            clipDeltaY = Config.getClipTop(this.rasterizer) - super.y;
            super.y = Config.getClipTop(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * clipDeltaY;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * clipDeltaY;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * clipDeltaY;
         super.uFixed = super.uFixed + super.duDyFixed * clipDeltaY;
         super.vFixed = super.vFixed + super.dvDyFixed * clipDeltaY;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.vFixed = super.vFixed + super.dvDyFixed
      ) {
         int xLeft = super.xLeftFixed >> 16;
         int xRight = super.xRightFixed >> 16;
         int curU = super.uFixed >> mipLevel;
         int curV = super.vFixed >> mipLevel;
         if (xLeft < Config.getClipLeft(this.rasterizer)) {
            int clipDeltaX = Config.getClipLeft(this.rasterizer) - xLeft;
            xLeft = Config.getClipLeft(this.rasterizer);
            curU += stepDu2 * clipDeltaX;
            curV += stepDv * clipDeltaX;
         }

         if (xRight > Config.getClipRight(this.rasterizer)) {
            xRight = Config.getClipRight(this.rasterizer);
         }

         int spanPixelIdx = super.scanlineOffset + xLeft;

         for (int spanEndIdx = super.scanlineOffset + xRight; spanPixelIdx < spanEndIdx; spanPixelIdx++) {
            int texelOffset = mipOffset + ((curV & mipVMask) >>> mipVShift) + ((curU & mipUMask) >>> mipUShift);
            int texelColor = diffusePixels[texelOffset];
            int dstColor = dstPixels[spanPixelIdx];
            int pixelVal = (texelColor & 16711422) + (dstColor & 16711422) >>> 1;
            dstPixels[spanPixelIdx] = pixelVal | 0xFF000000;
            curU += stepDu2;
            curV += stepDv;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDyFixed;
      }
   }
}
