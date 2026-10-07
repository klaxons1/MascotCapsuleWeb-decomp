package com.hicorp.mascotcapsule.web;

public final class LitColorDrawer_Opaque extends TexturedDrawer {
   private final Config rasterizer;

   public LitColorDrawer_Opaque(Config rasterizer) {
      super(var1);
      this.rasterizer = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int[] var2 = Config.getColorTable();
      int var3 = Config.getClipBottom(this.rasterizer) & 16711935;
      int var4 = Config.getClipBottom(this.rasterizer) & 0xFF00;
      int var5 = super.duDxFixed;
      int var6 = super.dvDxFixed;

      for (super.vFixed += 8388608; super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int var7 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var8 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var9 = super.uFixed;

         for (int var10 = super.vFixed; var7 < var8; var7++) {
            int var11 = var9 >>> 16;
            int var12 = var2[var10 >> 16 & 511];
            int var14 = (var3 * var11 & -16711936) + (var4 * var11 & 0xFF0000) >>> 8;
            int var13 = ((var14 & var12) << 1) + ((var14 ^ var12) & 16711422) & 16843008;
            var13 = (var13 >>> 8) + 8355711 ^ 8355711;
            var13 = var14 + var12 - var13 | var13;
            var1[var7] = var13 | 0xFF000000;
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
