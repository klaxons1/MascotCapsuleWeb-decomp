package com.hicorp.mascotcapsule.web;

public final class Light extends Class_1279 {
   private final Config var_26;

   public Light(Config var1) {
      super(var1);
      this.var_26 = var1;
   }

   public void sub_1c() {
      int[] var1 = Config.sub_34c(this.var_26);
      int[] var2 = Config.sub_4c1();
      int var3 = Config.sub_397(this.var_26) & 16711935;
      int var4 = Config.sub_397(this.var_26) & 0xFF00;
      int var5 = super.var_491;
      int var6 = super.var_49f;

      for (super.var_3c2 += 8388608; super.var_139 < super.var_21c; super.var_3c2 = super.var_3c2 + super.var_438) {
         int var7 = (super.var_279 >> 16) + super.var_c4;
         int var8 = (super.var_2c3 >> 16) + super.var_c4;
         int var9 = super.var_368;

         for (int var10 = super.var_3c2; var7 < var8; var7++) {
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

         super.var_139++;
         super.var_c4 = super.var_c4 + Config.sub_3dd(this.var_26);
         super.var_279 = super.var_279 + super.var_2fe;
         super.var_2c3 = super.var_2c3 + super.var_326;
         super.var_368 = super.var_368 + super.var_40c;
      }

      super.var_3c2 -= 8388608;
   }
}
