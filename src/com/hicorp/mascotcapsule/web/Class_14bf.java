package com.hicorp.mascotcapsule.web;

public final class Class_14bf extends Class_1279 {
   private final Config var_5e;

   public Class_14bf(Config var1) {
      super(var1);
      this.var_5e = var1;
   }

   public void sub_1c() {
      int[] var1 = Config.sub_34c(this.var_5e);
      int[] var2 = Config.sub_4c1();
      int var3 = Config.sub_397(this.var_5e) & 16711935;
      int var4 = Config.sub_397(this.var_5e) & 0xFF00;
      int var5 = super.var_491;
      int var6 = super.var_49f;
      if (super.var_139 < Config.sub_41a(this.var_5e)) {
         int var7;
         if (super.var_21c < Config.sub_41a(this.var_5e)) {
            var7 = super.var_21c - super.var_139;
            super.var_139 = super.var_21c;
         } else {
            var7 = Config.sub_41a(this.var_5e) - super.var_139;
            super.var_139 = Config.sub_41a(this.var_5e);
         }

         super.var_c4 = super.var_c4 + Config.sub_3dd(this.var_5e) * var7;
         super.var_279 = super.var_279 + super.var_2fe * var7;
         super.var_2c3 = super.var_2c3 + super.var_326 * var7;
         super.var_368 = super.var_368 + super.var_40c * var7;
         super.var_3c2 = super.var_3c2 + super.var_438 * var7;
      }

      super.var_21c = super.var_21c < Config.sub_45d(this.var_5e) ? super.var_21c : Config.sub_45d(this.var_5e);

      for (super.var_3c2 += 8388608; super.var_139 < super.var_21c; super.var_3c2 = super.var_3c2 + super.var_438) {
         int var17 = super.var_279 >> 16;
         int var8 = super.var_2c3 >> 16;
         int var9 = super.var_368;
         int var10 = super.var_3c2;
         if (var17 < Config.sub_467(this.var_5e)) {
            int var11 = Config.sub_467(this.var_5e) - var17;
            var17 = Config.sub_467(this.var_5e);
            var9 += var5 * var11;
            var10 += var6 * var11;
         }

         if (var8 > Config.sub_48e(this.var_5e)) {
            var8 = Config.sub_48e(this.var_5e);
         }

         int var18 = super.var_c4 + var17;

         for (int var12 = super.var_c4 + var8; var18 < var12; var18++) {
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

         super.var_139++;
         super.var_c4 = super.var_c4 + Config.sub_3dd(this.var_5e);
         super.var_279 = super.var_279 + super.var_2fe;
         super.var_2c3 = super.var_2c3 + super.var_326;
         super.var_368 = super.var_368 + super.var_40c;
      }

      super.var_3c2 -= 8388608;
   }
}
