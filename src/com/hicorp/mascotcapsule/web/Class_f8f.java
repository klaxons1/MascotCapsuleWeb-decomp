package com.hicorp.mascotcapsule.web;

public final class Class_f8f extends Class_eda {
   private final Config var_33;

   public Class_f8f(Config var1) {
      super(var1);
      this.var_33 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_33);

      for (int var2 = Config.getClipBottom(this.var_33); super.y < super.yEnd; super.xRightFixed = super.xRightFixed + super.dxRightFixed) {
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
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_33);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
