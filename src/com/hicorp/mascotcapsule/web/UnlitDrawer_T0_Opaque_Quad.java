package com.hicorp.mascotcapsule.web;

public final class UnlitDrawer_T0_Opaque_Quad extends UnlitDrawer {
   private final Config rasterizer;

   public UnlitDrawer_T0_Opaque_Quad(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int curLight = (super.lightFixed > 0 ? super.lightFixed : -super.lightFixed) + (super.dLightDyFixed > 0 ? super.dLightDyFixed : -super.dLightDyFixed) + 32768;
      int mipLevel = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(curLight) - 17);
      int[] diffusePixels = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int mipOffset = Config.getDiffuseTexture(this.rasterizer).getMipOffset(mipLevel);
      int mipUMask = Config.getDiffuseTexture(this.rasterizer).getMipUMask(mipLevel);
      int mipVMask = Config.getDiffuseTexture(this.rasterizer).getMipVMask(mipLevel);
      int mipUShift = Config.getDiffuseTexture(this.rasterizer).getMipUShift(mipLevel);
      int mipVShift = Config.getDiffuseTexture(this.rasterizer).getMipVShift(mipLevel);
      int curLight2 = super.lightFixed >> mipLevel;
      int stepDy = super.dLightDyFixed >> mipLevel;
      int stepDLight = super.dLightDxFixed;
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
         super.uFixed = super.uFixed + super.dvDyFixed * clipDeltaY;
         super.vFixed = super.vFixed + super.duDxFixed * clipDeltaY;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed * clipDeltaY;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed
      ) {
         int xLeft = super.xLeftFixed >> 16;
         int xRight = super.xRightFixed >> 16;
         int curU = super.uFixed >> mipLevel;
         int curV = super.vFixed >> mipLevel;
         int stepDy2 = super.duDyFixed;
         if (xLeft < Config.getClipLeft(this.rasterizer)) {
            int clipDeltaX = Config.getClipLeft(this.rasterizer) - xLeft;
            xLeft = Config.getClipLeft(this.rasterizer);
            curU += curLight2 * clipDeltaX;
            curV += stepDy * clipDeltaX;
            stepDy2 += stepDLight * clipDeltaX;
         }

         if (xRight > Config.getClipRight(this.rasterizer)) {
            xRight = Config.getClipRight(this.rasterizer);
         }

         int spanPixelIdx = super.scanlineOffset + xLeft;

         for (int spanEndIdx = super.scanlineOffset + xRight; spanPixelIdx < spanEndIdx; spanPixelIdx++) {
            int texelOffset = mipOffset + ((curV & mipVMask) >>> mipVShift) + ((curU & mipUMask) >>> mipUShift);
            int texelColor = diffusePixels[texelOffset];
            int intensityVal = stepDy2 >>> 16;
            int shadedColor = ((texelColor & 16711935) * intensityVal & -16711936) + ((texelColor & 0xFF00) * intensityVal & 0xFF0000) >>> 8;
            dstPixels[spanPixelIdx] = shadedColor | 0xFF000000;
            curU += curLight2;
            curV += stepDy;
            stepDy2 += stepDLight;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDyFixed;
         super.vFixed = super.vFixed + super.duDxFixed;
      }
   }
}
