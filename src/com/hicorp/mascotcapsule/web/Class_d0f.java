package com.hicorp.mascotcapsule.web;

public final class Class_d0f extends Class_d00 {
   private final Config var_15;

   public Class_d0f(Config var1) {
      super(var1);
      this.var_15 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_15);
      int var2 = Config.getClipBottom(this.var_15) & 16711935;
      int var3 = Config.getClipBottom(this.var_15) & 0xFF00;

      for (int var4 = super.dzDxFixed; super.y < super.yEnd; super.zFixed = super.zFixed + super.dzDyFixed) {
         int var5 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var6 = (super.xRightFixed >> 16) + super.scanlineOffset;

         for (int var7 = super.zFixed; var5 < var6; var5++) {
            int var8 = var7 >>> 16;
            int var9 = (var2 * var8 & -16711936) + (var3 * var8 & 0xFF0000) >>> 8;
            var1[var5] = var9 | 0xFF000000;
            var7 += var4;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_15);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
      }
   }
}
