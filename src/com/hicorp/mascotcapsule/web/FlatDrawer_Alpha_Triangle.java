package com.hicorp.mascotcapsule.web;

public final class FlatDrawer_Alpha_Triangle extends FlatDrawer {
   private final Config rasterizer;

   public FlatDrawer_Alpha_Triangle(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);

      for (int fillColor = Config.getFillColor(this.rasterizer); super.y < super.yEnd; super.xRightFixed = super.xRightFixed + super.dxRightFixed) {
         int xLeft = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int xRight = (super.xRightFixed >> 16) + super.scanlineOffset;
         if ((xLeft & 1 ^ super.y & 1) != 0) {
            xLeft++;
         }

         while (xLeft < xRight) {
            dstPixels[xLeft] = fillColor;
            xLeft += 2;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
