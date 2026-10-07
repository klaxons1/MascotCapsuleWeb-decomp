package com.hicorp.mascotcapsule.web;

public final class FlatDrawer_Opaque_Quad extends FlatDrawer {
   private final Config rasterizer;

   public FlatDrawer_Opaque_Quad(Config rasterizer) {
      super(var1);
      this.rasterizer = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int var2 = Config.getClipBottom(this.rasterizer);
      if (super.y < Config.getClipLeft(this.rasterizer)) {
         int var3;
         if (super.yEnd < Config.getClipLeft(this.rasterizer)) {
            var3 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var3 = Config.getClipLeft(this.rasterizer) - super.y;
            super.y = Config.getClipLeft(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * var3;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var3;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var3;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed
      ) {
         int var7 = super.xLeftFixed >> 16;
         int var4 = super.xRightFixed >> 16;
         if (var7 < Config.getBufferHeight(this.rasterizer)) {
            var7 = Config.getBufferHeight(this.rasterizer);
         }

         if (var4 > Config.getClipRight(this.rasterizer)) {
            var4 = Config.getClipRight(this.rasterizer);
         }

         int var5 = super.scanlineOffset + var7;

         for (int var6 = super.scanlineOffset + var4; var5 < var6; var5++) {
            var1[var5] = var2;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
