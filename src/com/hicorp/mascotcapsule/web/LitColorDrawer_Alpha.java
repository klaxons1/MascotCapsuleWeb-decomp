package com.hicorp.mascotcapsule.web;

public final class LitColorDrawer_Alpha extends TexturedDrawer {
   private final Config rasterizer;

   public LitColorDrawer_Alpha(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int[] colorTable = Config.getColorTable();
      int fillColor = Config.getFillColor(this.rasterizer) & 16711935;
      int fillColor2 = Config.getFillColor(this.rasterizer) & 0xFF00;
      int stepDu = super.duDxFixed;
      int stepDv = super.dvDxFixed;
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

      super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);

      for (super.vFixed += 8388608; super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int xLeft = super.xLeftFixed >> 16;
         int xRight = super.xRightFixed >> 16;
         int curU = super.uFixed;
         int curV = super.vFixed;
         if (xLeft < Config.getClipLeft(this.rasterizer)) {
            int clipDeltaX = Config.getClipLeft(this.rasterizer) - xLeft;
            xLeft = Config.getClipLeft(this.rasterizer);
            curU += stepDu * clipDeltaX;
            curV += stepDv * clipDeltaX;
         }

         if (xRight > Config.getClipRight(this.rasterizer)) {
            xRight = Config.getClipRight(this.rasterizer);
         }

         int spanPixelIdx = super.scanlineOffset + xLeft;

         for (int spanEndIdx = super.scanlineOffset + xRight; spanPixelIdx < spanEndIdx; spanPixelIdx++) {
            int lightIntensity = curU >>> 16;
            int lightIntensity2 = colorTable[curV >> 16 & 511];
            int shadedColor = (fillColor * lightIntensity & -16711936) + (fillColor2 * lightIntensity & 0xFF0000) >>> 8;
            int blendResult = ((shadedColor & lightIntensity2) << 1) + ((shadedColor ^ lightIntensity2) & 16711422) & 16843008;
            blendResult = (blendResult >>> 8) + 8355711 ^ 8355711;
            blendResult = shadedColor + lightIntensity2 - blendResult | blendResult;
            dstPixels[spanPixelIdx] = blendResult | 0xFF000000;
            curU += stepDu;
            curV += stepDv;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDyFixed;
      }

      super.vFixed -= 8388608;
   }
}
