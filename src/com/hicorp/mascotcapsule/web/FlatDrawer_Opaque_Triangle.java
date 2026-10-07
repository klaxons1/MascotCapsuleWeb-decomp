package com.hicorp.mascotcapsule.web;

public final class FlatDrawer_Opaque_Triangle extends FlatDrawer {
   private final Config rasterizer;

   public FlatDrawer_Opaque_Triangle(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);

      for (int var2 = Config.getFillColor(this.rasterizer); super.y < super.yEnd; super.xRightFixed = super.xRightFixed + super.dxRightFixed) {
         int var3 = (super.xLeftFixed >> 16) + super.scanlineOffset;

         for (int var4 = (super.xRightFixed >> 16) + super.scanlineOffset; var3 < var4; var3++) {
            var1[var3] = var2;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
