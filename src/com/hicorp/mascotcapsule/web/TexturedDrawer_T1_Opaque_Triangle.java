package com.hicorp.mascotcapsule.web;

public final class TexturedDrawer_T1_Opaque_Triangle extends TexturedDrawer {
   private final Config rasterizer;

   public TexturedDrawer_T1_Opaque_Triangle(Config rasterizer) {
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

      for (int stepDv = super.dvDxFixed >> mipLevel; super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int xLeft = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int xRight = (super.xRightFixed >> 16) + super.scanlineOffset;
         int curU = super.uFixed >> mipLevel;

         for (int curV = super.vFixed >> mipLevel; xLeft < xRight; xLeft++) {
            int texelOffset = mipOffset + ((curV & mipVMask) >>> mipVShift) + ((curU & mipUMask) >>> mipUShift);
            int texelColor = diffusePixels[texelOffset];
            int dstColor = dstPixels[xLeft];
            int pixelVal = (texelColor & 0x00FEFEFE) + (dstColor & 0x00FEFEFE) >>> 1;
            dstPixels[xLeft] = pixelVal | 0xFF000000;
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
