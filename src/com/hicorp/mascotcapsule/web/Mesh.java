package com.hicorp.mascotcapsule.web;

public final class Mesh extends Class_d00 {
   private final Config var_ac;

   public Mesh(Config var1) {
      super(var1);
      this.var_ac = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_ac);
      int var2 = Config.getClipBottom(this.var_ac) & 16711935;
      int var3 = Config.getClipBottom(this.var_ac) & 0xFF00;
      int var4 = super.dzDxFixed;
      if (super.y < Config.getClipLeft(this.var_ac)) {
         int var5;
         if (super.yEnd < Config.getClipLeft(this.var_ac)) {
            var5 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var5 = Config.getClipLeft(this.var_ac) - super.y;
            super.y = Config.getClipLeft(this.var_ac);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_ac) * var5;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var5;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var5;
         super.zFixed = super.zFixed + super.dzDyFixed * var5;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.var_ac) ? super.yEnd : Config.getClipBottom(this.var_ac);
         super.y < super.yEnd;
         super.zFixed = super.zFixed + super.dzDyFixed
      ) {
         int var12 = super.xLeftFixed >> 16;
         int var6 = super.xRightFixed >> 16;
         int var7 = super.zFixed;
         if (var12 < Config.getBufferHeight(this.var_ac)) {
            int var8 = Config.getBufferHeight(this.var_ac) - var12;
            var12 = Config.getBufferHeight(this.var_ac);
            var7 += var4 * var8;
         }

         if (var6 > Config.getClipRight(this.var_ac)) {
            var6 = Config.getClipRight(this.var_ac);
         }

         int var13 = super.scanlineOffset + var12;

         for (int var9 = super.scanlineOffset + var6; var13 < var9; var13++) {
            int var10 = var7 >>> 16;
            int var11 = (var2 * var10 & -16711936) + (var3 * var10 & 0xFF0000) >>> 8;
            var1[var13] = var11 | 0xFF000000;
            var7 += var4;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_ac);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
      }
   }
}
