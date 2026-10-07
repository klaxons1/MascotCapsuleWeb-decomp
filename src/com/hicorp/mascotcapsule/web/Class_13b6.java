package com.hicorp.mascotcapsule.web;

public final class Class_13b6 extends Class_eda {
   private final Config var_49;

   public Class_13b6(Config var1) {
      super(var1);
      this.var_49 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_49);
      int var2 = Config.getClipBottom(this.var_49);
      if (super.y < Config.getClipLeft(this.var_49)) {
         int var3;
         if (super.yEnd < Config.getClipLeft(this.var_49)) {
            var3 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var3 = Config.getClipLeft(this.var_49) - super.y;
            super.y = Config.getClipLeft(this.var_49);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_49) * var3;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var3;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var3;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.var_49) ? super.yEnd : Config.getClipBottom(this.var_49);
         super.y < super.yEnd;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed
      ) {
         int var7 = super.xLeftFixed >> 16;
         int var4 = super.xRightFixed >> 16;
         if (var7 < Config.getBufferHeight(this.var_49)) {
            var7 = Config.getBufferHeight(this.var_49);
         }

         if (var4 > Config.getClipRight(this.var_49)) {
            var4 = Config.getClipRight(this.var_49);
         }

         int var5 = super.scanlineOffset + var7;
         int var6 = super.scanlineOffset + var4;
         if ((var5 & 1 ^ super.y & 1) != 0) {
            var5++;
         }

         while (var5 < var6) {
            var1[var5] = var2;
            var5 += 2;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_49);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
