package com.hicorp.mascotcapsule.web;

public final class LineDrawer_Alpha extends LineDrawer {
   private final Config rasterizer;

   public LineDrawer_Alpha(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int fillColor = Config.getFillColor(this.rasterizer) & 16711935;
      int fillColor2 = Config.getFillColor(this.rasterizer) & 0xFF00;
      int stepDz = super.dzDxFixed;
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
         super.zFixed = super.zFixed + super.dzDyFixed * clipDeltaY;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.zFixed = super.zFixed + super.dzDyFixed
      ) {
         int xLeft = super.xLeftFixed >> 16;
         int xRight = super.xRightFixed >> 16;
         int curZ = super.zFixed;
         if (xLeft < Config.getClipLeft(this.rasterizer)) {
            int clipDeltaX = Config.getClipLeft(this.rasterizer) - xLeft;
            xLeft = Config.getClipLeft(this.rasterizer);
            curZ += stepDz * clipDeltaX;
         }

         if (xRight > Config.getClipRight(this.rasterizer)) {
            xRight = Config.getClipRight(this.rasterizer);
         }

         int spanPixelIdx = super.scanlineOffset + xLeft;

         for (int spanEndIdx = super.scanlineOffset + xRight; spanPixelIdx < spanEndIdx; spanPixelIdx++) {
            int intensityVal = curZ >>> 16;
            int shadedColor = (fillColor * intensityVal & -16711936) + (fillColor2 * intensityVal & 0xFF0000) >>> 8;
            dstPixels[spanPixelIdx] = shadedColor | 0xFF000000;
            curZ += stepDz;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
      }
   }
}
