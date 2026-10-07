package com.hicorp.mascotcapsule.web;

public final class LineDrawer_Alpha extends LineDrawer {
   private final Config rasterizer;

   public LineDrawer_Alpha(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int var2 = Config.getFillColor(this.rasterizer) & 16711935;
      int var3 = Config.getFillColor(this.rasterizer) & 0xFF00;
      int var4 = super.dzDxFixed;
      if (super.y < Config.getClipTop(this.rasterizer)) {
         int var5;
         if (super.yEnd < Config.getClipTop(this.rasterizer)) {
            var5 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var5 = Config.getClipTop(this.rasterizer) - super.y;
            super.y = Config.getClipTop(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * var5;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var5;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var5;
         super.zFixed = super.zFixed + super.dzDyFixed * var5;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.zFixed = super.zFixed + super.dzDyFixed
      ) {
         int var12 = super.xLeftFixed >> 16;
         int var6 = super.xRightFixed >> 16;
         int var7 = super.zFixed;
         if (var12 < Config.getClipLeft(this.rasterizer)) {
            int var8 = Config.getClipLeft(this.rasterizer) - var12;
            var12 = Config.getClipLeft(this.rasterizer);
            var7 += var4 * var8;
         }

         if (var6 > Config.getClipRight(this.rasterizer)) {
            var6 = Config.getClipRight(this.rasterizer);
         }

         int var13 = super.scanlineOffset + var12;

         for (int var9 = super.scanlineOffset + var6; var13 < var9; var13++) {
            int var10 = var7 >>> 16;
            int var11 = (var2 * var10 & -16711936) + (var3 * var10 & 0xFF0000) >>> 8;
            var1[var13] = var11 | 0xFF000000;
            var7 += var4;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
      }
   }
}
