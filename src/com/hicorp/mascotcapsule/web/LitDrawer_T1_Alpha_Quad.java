package com.hicorp.mascotcapsule.web;

public final class LitDrawer_T1_Alpha_Quad extends LitDrawer {
   private final Config rasterizer;

   public LitDrawer_T1_Alpha_Quad(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int[] colorTable = Config.getColorTable();
      int stepDLight = (super.dLightDxFixed > 0 ? super.dLightDxFixed : -super.dLightDxFixed) + (super.normalZFixed > 0 ? super.normalZFixed : -super.normalZFixed) + 32768;
      int mipLevel = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(stepDLight) - 17);
      int[] diffusePixels = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int mipOffset = Config.getDiffuseTexture(this.rasterizer).getMipOffset(mipLevel);
      int mipUMask = Config.getDiffuseTexture(this.rasterizer).getMipUMask(mipLevel);
      int mipVMask = Config.getDiffuseTexture(this.rasterizer).getMipVMask(mipLevel);
      int mipUShift = Config.getDiffuseTexture(this.rasterizer).getMipUShift(mipLevel);
      int mipVShift = Config.getDiffuseTexture(this.rasterizer).getMipVShift(mipLevel);
      int stepDLight2 = super.dLightDxFixed >> mipLevel;
      int curNormalZ = super.normalZFixed >> mipLevel;
      int stepDy = super.dNormalZDyFixed;
      int stepDNormalZ = super.dNormalZDxFixed;
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
         super.uFixed = super.uFixed + super.duDxFixed * clipDeltaY;
         super.vFixed = super.vFixed + super.dvDxFixed * clipDeltaY;
         super.duDyFixed = super.duDyFixed + super.lightFixed * clipDeltaY;
         super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed * clipDeltaY;
      }

      super.yEnd = Math.min(super.yEnd, Config.getClipBottom(this.rasterizer));

      for (super.dvDyFixed += 0x800000; super.y < super.yEnd; super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed) {
         int xLeft = super.xLeftFixed >> 16;
         int xRight = super.xRightFixed >> 16;
         int curU = super.uFixed >> mipLevel;
         int curV = super.vFixed >> mipLevel;
         int stepDy2 = super.duDyFixed;
         int stepDy3 = super.dvDyFixed;
         if (xLeft < Config.getClipLeft(this.rasterizer)) {
            int clipDeltaX = Config.getClipLeft(this.rasterizer) - xLeft;
            xLeft = Config.getClipLeft(this.rasterizer);
            curU += stepDLight2 * clipDeltaX;
            curV += curNormalZ * clipDeltaX;
            stepDy2 += stepDy * clipDeltaX;
            stepDy3 += stepDNormalZ * clipDeltaX;
         }

         if (xRight > Config.getClipRight(this.rasterizer)) {
            xRight = Config.getClipRight(this.rasterizer);
         }

         int spanPixelIdx = super.scanlineOffset + xLeft;

         for (int spanEndIdx = super.scanlineOffset + xRight; spanPixelIdx < spanEndIdx; spanPixelIdx++) {
            int texelOffset = mipOffset + ((curV & mipVMask) >>> mipVShift) + ((curU & mipUMask) >>> mipUShift);
            int texelColor = diffusePixels[texelOffset];
            if (texelColor != -1) {
               int dstColor = dstPixels[spanPixelIdx];
               int lightIntensity = stepDy2 >>> 16;
               int lightIntensity2 = colorTable[stepDy3 >>> 16 & 511];
               int shadedColor = ((texelColor & 0x00FF00FF) * lightIntensity & 0xFF00FF00) + ((texelColor & 0xFF00) * lightIntensity & 0xFF0000) >>> 8;
               int blendResult = ((shadedColor & lightIntensity2) << 1) + ((shadedColor ^ lightIntensity2) & 0x00FEFEFE) & 0x01010100;
               blendResult = (blendResult >>> 8) + 0x007F7F7F ^ 0x007F7F7F;
               blendResult = shadedColor + lightIntensity2 - blendResult | blendResult;
               blendResult = (blendResult & 0x00FEFEFE) + (dstColor & 0x00FEFEFE) >>> 1;
               dstPixels[spanPixelIdx] = blendResult | 0xFF000000;
            }

            curU += stepDLight2;
            curV += curNormalZ;
            stepDy2 += stepDy;
            stepDy3 += stepDNormalZ;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDxFixed;
         super.vFixed = super.vFixed + super.dvDxFixed;
         super.duDyFixed = super.duDyFixed + super.lightFixed;
      }

      super.dvDyFixed -= 0x800000;
   }
}
