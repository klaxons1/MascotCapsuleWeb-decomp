package com.hicorp.mascotcapsule.web;

public final class FlatDrawer_Opaque_Quad extends FlatDrawer {
   private final Config rasterizer;

   public FlatDrawer_Opaque_Quad(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int fillColor = Config.getFillColor(this.rasterizer);
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
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed
      ) {
         int xLeft = super.xLeftFixed >> 16;
         int xRight = super.xRightFixed >> 16;
         if (xLeft < Config.getClipLeft(this.rasterizer)) {
            xLeft = Config.getClipLeft(this.rasterizer);
         }

         if (xRight > Config.getClipRight(this.rasterizer)) {
            xRight = Config.getClipRight(this.rasterizer);
         }

         int spanPixelIdx = super.scanlineOffset + xLeft;

         for (int spanEndIdx = super.scanlineOffset + xRight; spanPixelIdx < spanEndIdx; spanPixelIdx++) {
            dstPixels[spanPixelIdx] = fillColor;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
