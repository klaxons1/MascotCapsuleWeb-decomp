package com.hicorp.mascotcapsule.web;

public final class LitColorDrawer_Alpha extends TexturedDrawer {
   private final Config rasterizer;

   public LitColorDrawer_Alpha(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int[] var2 = Config.getColorTable();
      int var3 = Config.getFillColor(this.rasterizer) & 16711935;
      int var4 = Config.getFillColor(this.rasterizer) & 0xFF00;
      int var5 = super.duDxFixed;
      int var6 = super.dvDxFixed;
      if (super.y < Config.getClipTop(this.rasterizer)) {
         int var7;
         if (super.yEnd < Config.getClipTop(this.rasterizer)) {
            var7 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var7 = Config.getClipTop(this.rasterizer) - super.y;
            super.y = Config.getClipTop(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * var7;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var7;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var7;
         super.uFixed = super.uFixed + super.duDyFixed * var7;
         super.vFixed = super.vFixed + super.dvDyFixed * var7;
      }

      super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);

      for (super.vFixed += 8388608; super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int var17 = super.xLeftFixed >> 16;
         int var8 = super.xRightFixed >> 16;
         int var9 = super.uFixed;
         int var10 = super.vFixed;
         if (var17 < Config.getClipLeft(this.rasterizer)) {
            int var11 = Config.getClipLeft(this.rasterizer) - var17;
            var17 = Config.getClipLeft(this.rasterizer);
            var9 += var5 * var11;
            var10 += var6 * var11;
         }

         if (var8 > Config.getClipRight(this.rasterizer)) {
            var8 = Config.getClipRight(this.rasterizer);
         }

         int var18 = super.scanlineOffset + var17;

         for (int var12 = super.scanlineOffset + var8; var18 < var12; var18++) {
            int var13 = var9 >>> 16;
            int var14 = var2[var10 >> 16 & 511];
            int var16 = (var3 * var13 & -16711936) + (var4 * var13 & 0xFF0000) >>> 8;
            int var15 = ((var16 & var14) << 1) + ((var16 ^ var14) & 16711422) & 16843008;
            var15 = (var15 >>> 8) + 8355711 ^ 8355711;
            var15 = var16 + var14 - var15 | var15;
            var1[var18] = var15 | 0xFF000000;
            var9 += var5;
            var10 += var6;
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
