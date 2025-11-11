package com.hicorp.mascotcapsule.web;

public final class Class_d0f extends Class_d00 {
   private final Config var_15;

   public Class_d0f(Config var1) {
      super(var1);
      this.var_15 = var1;
   }

   public void sub_21() {
      int[] var1 = Config.sub_34c(this.var_15);
      int var2 = Config.sub_397(this.var_15) & 16711935;
      int var3 = Config.sub_397(this.var_15) & 0xFF00;

      for (int var4 = super.var_324; super.var_cc < super.var_137; super.var_259 = super.var_259 + super.var_2a5) {
         int var5 = (super.var_15d >> 16) + super.var_7c;
         int var6 = (super.var_1c1 >> 16) + super.var_7c;

         for (int var7 = super.var_259; var5 < var6; var5++) {
            int var8 = var7 >>> 16;
            int var9 = (var2 * var8 & -16711936) + (var3 * var8 & 0xFF0000) >>> 8;
            var1[var5] = var9 | 0xFF000000;
            var7 += var4;
         }

         super.var_cc++;
         super.var_7c = super.var_7c + Config.sub_3dd(this.var_15);
         super.var_15d = super.var_15d + super.var_1ff;
         super.var_1c1 = super.var_1c1 + super.var_239;
      }
   }
}
