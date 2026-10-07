package com.hicorp.mascotcapsule.web;

public final class SphereMapDrawer_T3_Alpha_Triangle extends SphereMapDrawer {
   private final Config rasterizer;

   public SphereMapDrawer_T3_Alpha_Triangle(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int curSphereV = (super.sphereVFixed > 0 ? super.sphereVFixed : -super.sphereVFixed) + (super.dSphereUDyFixed > 0 ? super.dSphereUDyFixed : -super.dSphereUDyFixed) + 32768;
      int mipLevel = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(curSphereV) - 17);
      int[] diffusePixels = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int mipOffset = Config.getDiffuseTexture(this.rasterizer).getMipOffset(mipLevel);
      int mipUMask = Config.getDiffuseTexture(this.rasterizer).getMipUMask(mipLevel);
      int mipVMask = Config.getDiffuseTexture(this.rasterizer).getMipVMask(mipLevel);
      int mipUShift = Config.getDiffuseTexture(this.rasterizer).getMipUShift(mipLevel);
      int mipVShift = Config.getDiffuseTexture(this.rasterizer).getMipVShift(mipLevel);
      int curSphereV2 = super.sphereVFixed >> mipLevel;
      int stepDy = super.dSphereUDyFixed >> mipLevel;
      int stepDy2 = super.dSphereVDyFixed;
      curSphereV = (super.dSphereUDxFixed > 0 ? super.dSphereUDxFixed : -super.dSphereUDxFixed) + (super.dSphereVDxFixed > 0 ? super.dSphereVDxFixed : -super.dSphereVDxFixed) + 32768;
      int sphereMipLevel = Config.getSphereMapTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(curSphereV) - 17);
      int[] spherePixels = Config.getSphereMapTexture(this.rasterizer).getPixels();
      int sphereMipOffset = Config.getSphereMapTexture(this.rasterizer).getMipOffset(sphereMipLevel);
      int sphereMipUMask = Config.getSphereMapTexture(this.rasterizer).getMipUMask(sphereMipLevel);
      int sphereMipVMask = Config.getSphereMapTexture(this.rasterizer).getMipVMask(sphereMipLevel);
      int sphereMipUShift = Config.getSphereMapTexture(this.rasterizer).getMipUShift(sphereMipLevel);
      int sphereMipVShift = Config.getSphereMapTexture(this.rasterizer).getMipVShift(sphereMipLevel);
      int stepDx = super.dSphereUDxFixed >> sphereMipLevel;

      for (int stepDSphereV = super.dSphereVDxFixed >> sphereMipLevel; super.y < super.yEnd; super.duDxFixed = super.duDxFixed + super.sphereUFixed) {
         int xLeft = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int xRight = (super.xRightFixed >> 16) + super.scanlineOffset;
         int curU = super.uFixed >> mipLevel;
         int curV = super.vFixed >> mipLevel;
         int stepDy3 = super.duDyFixed;
         int stepDy4 = super.dvDyFixed >> sphereMipLevel;

         for (int stepDu = super.duDxFixed >> sphereMipLevel; xLeft < xRight; xLeft++) {
            int texelOffset = mipOffset + ((curV & mipVMask) >>> mipVShift) + ((curU & mipUMask) >>> mipUShift);
            int texelColor = diffusePixels[texelOffset];
            if (texelColor != -1) {
               int pixelVal = sphereMipOffset + ((stepDu & sphereMipVMask) >>> sphereMipVShift) + ((stepDy4 & sphereMipUMask) >>> sphereMipUShift);
               int dstColor = dstPixels[xLeft];
               int intensityVal = stepDy3 >>> 16;
               int pixelVal2 = spherePixels[pixelVal];
               int shadedColor = ((texelColor & 16711935) * intensityVal & -16711936) + ((texelColor & 0xFF00) * intensityVal & 0xFF0000) >>> 8;
               int blendResult = ((shadedColor & pixelVal2) << 1) + ((shadedColor ^ pixelVal2) & 16711422) & 16843008;
               blendResult = (blendResult >>> 8) + 8355711 ^ 8355711;
               blendResult = shadedColor + pixelVal2 - blendResult | blendResult;
               int blendResult2 = ((blendResult & dstColor) << 1) + ((blendResult ^ dstColor) & 16711422) & 16843008;
               blendResult2 = (blendResult2 >>> 8) + 8355711 ^ 8355711;
               blendResult2 = blendResult + dstColor - blendResult2 | blendResult2;
               dstPixels[xLeft] = blendResult2 | 0xFF000000;
            }

            curU += curSphereV2;
            curV += stepDy;
            stepDy3 += stepDy2;
            stepDy4 += stepDx;
            stepDu += stepDSphereV;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDxFixed;
         super.vFixed = super.vFixed + super.lightFixed;
         super.duDyFixed = super.duDyFixed + super.dLightDyFixed;
         super.dvDyFixed = super.dvDyFixed + super.dLightDxFixed;
      }
   }
}
