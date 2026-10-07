package com.hicorp.mascotcapsule.web;

public final class LitColorDrawer_Opaque extends TexturedDrawer {
   private final Config rasterizer;

   public LitColorDrawer_Opaque(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int[] colorTable = Config.getColorTable();
      int fillColor = Config.getFillColor(this.rasterizer) & 0x00FF00FF;
      int fillColor2 = Config.getFillColor(this.rasterizer) & 0xFF00;
      int stepDu = super.duDxFixed;
      int stepDv = super.dvDxFixed;

      for (super.vFixed += 0x800000; super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int xLeft = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int xRight = (super.xRightFixed >> 16) + super.scanlineOffset;
         int curU = super.uFixed;

         for (int curV = super.vFixed; xLeft < xRight; xLeft++) {
            int lightIntensity = curU >>> 16;
            int lightIntensity2 = colorTable[curV >> 16 & 511];
            int shadedColor = (fillColor * lightIntensity & 0xFF00FF00) + (fillColor2 * lightIntensity & 0xFF0000) >>> 8;
            int blendResult = ((shadedColor & lightIntensity2) << 1) + ((shadedColor ^ lightIntensity2) & 0x00FEFEFE) & 0x01010100;
            blendResult = (blendResult >>> 8) + 0x007F7F7F ^ 0x007F7F7F;
            blendResult = shadedColor + lightIntensity2 - blendResult | blendResult;
            dstPixels[xLeft] = blendResult | 0xFF000000;
            curU += stepDu;
            curV += stepDv;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDyFixed;
      }

      super.vFixed -= 0x800000;
   }
}
