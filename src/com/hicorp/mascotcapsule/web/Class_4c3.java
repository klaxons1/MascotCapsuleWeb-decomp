package com.hicorp.mascotcapsule.web;

public final class Class_4c3 extends Class_eda {
   private final Config var_5d;

   public Class_4c3(Config var1) {
      super(var1);
      this.var_5d = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_5d);
      int var2 = Config.getClipBottom(this.var_5d);
      if (super.y < Config.getClipLeft(this.var_5d)) {
         int var3;
         if (super.yEnd < Config.getClipLeft(this.var_5d)) {
            var3 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var3 = Config.getClipLeft(this.var_5d) - super.y;
            super.y = Config.getClipLeft(this.var_5d);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_5d) * var3;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var3;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var3;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.var_5d) ? super.yEnd : Config.getClipBottom(this.var_5d);
         super.y < super.yEnd;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed
      ) {
         int var7 = super.xLeftFixed >> 16;
         int var4 = super.xRightFixed >> 16;
         if (var7 < Config.getBufferHeight(this.var_5d)) {
            var7 = Config.getBufferHeight(this.var_5d);
         }

         if (var4 > Config.getClipRight(this.var_5d)) {
            var4 = Config.getClipRight(this.var_5d);
         }

         int var5 = super.scanlineOffset + var7;

         for (int var6 = super.scanlineOffset + var4; var5 < var6; var5++) {
            var1[var5] = var2;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_5d);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
