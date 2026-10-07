package com.hicorp.mascotcapsule.web;

public final class FlatDrawer_Alpha_Triangle extends FlatDrawer {
   private final Config rasterizer;

   public FlatDrawer_Alpha_Triangle(Config rasterizer) {
      super(var1);
      this.rasterizer = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);

      for (int var2 = Config.getClipBottom(this.rasterizer); super.y < super.yEnd; super.xRightFixed = super.xRightFixed + super.dxRightFixed) {
         int var3 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var4 = (super.xRightFixed >> 16) + super.scanlineOffset;
         if ((var3 & 1 ^ super.y & 1) != 0) {
            var3++;
         }

         while (var3 < var4) {
            var1[var3] = var2;
            var3 += 2;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
