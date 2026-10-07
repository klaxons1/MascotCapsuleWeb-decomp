package com.hicorp.mascotcapsule.web;

public final class UnlitDrawer_T1_Opaque_Triangle extends UnlitDrawer {
   private final Config rasterizer;

   public UnlitDrawer_T1_Opaque_Triangle(Config rasterizer) {
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

      for (int stepDLight = super.dLightDxFixed; super.y < super.yEnd; super.duDyFixed = super.duDyFixed + super.dvDxFixed) {
         int xLeft = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int xRight = (super.xRightFixed >> 16) + super.scanlineOffset;
         int curU = super.uFixed >> mipLevel;
         int curV = super.vFixed >> mipLevel;

         for (int stepDy2 = super.duDyFixed; xLeft < xRight; xLeft++) {
            int texelOffset = mipOffset + ((curV & mipVMask) >>> mipVShift) + ((curU & mipUMask) >>> mipUShift);
            int texelColor = diffusePixels[texelOffset];
            int dstColor = dstPixels[xLeft];
            int intensityVal = stepDy2 >>> 16;
            int shadedColor = ((texelColor & 0x00FF00FF) * intensityVal & 0xFF00FF00) + ((texelColor & 0xFF00) * intensityVal & 0xFF0000) >>> 8;
            shadedColor = (shadedColor & 0x00FEFEFE) + (dstColor & 0x00FEFEFE) >>> 1;
            dstPixels[xLeft] = shadedColor | 0xFF000000;
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
