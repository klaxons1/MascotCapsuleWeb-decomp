package com.hicorp.mascotcapsule.web;

public final class Class_c6f extends Class_1279 {
   private final Config var_68;

   public Class_c6f(Config var1) {
      super(var1);
      this.var_68 = var1;
   }

   public void sub_1c() {
      int[] var1 = Config.sub_34c(this.var_68);
      int var2 = (super.var_491 > 0 ? super.var_491 : -super.var_491) + (super.var_49f > 0 ? super.var_49f : -super.var_49f) + 32768;
      int var3 = Config.sub_509(this.var_68).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Config.sub_509(this.var_68).sub_93();
      int var5 = Config.sub_509(this.var_68).sub_327(var3);
      int var6 = Config.sub_509(this.var_68).sub_3df(var3);
      int var7 = Config.sub_509(this.var_68).sub_418(var3);
      int var8 = Config.sub_509(this.var_68).sub_42b(var3);
      int var9 = Config.sub_509(this.var_68).sub_467(var3);
      int var10 = super.var_491 >> var3;
      int var11 = super.var_49f >> var3;
      if (super.var_139 < Config.sub_41a(this.var_68)) {
         int var12;
         if (super.var_21c < Config.sub_41a(this.var_68)) {
            var12 = super.var_21c - super.var_139;
            super.var_139 = super.var_21c;
         } else {
            var12 = Config.sub_41a(this.var_68) - super.var_139;
            super.var_139 = Config.sub_41a(this.var_68);
         }

         super.var_c4 = super.var_c4 + Config.sub_3dd(this.var_68) * var12;
         super.var_279 = super.var_279 + super.var_2fe * var12;
         super.var_2c3 = super.var_2c3 + super.var_326 * var12;
         super.var_368 = super.var_368 + super.var_40c * var12;
         super.var_3c2 = super.var_3c2 + super.var_438 * var12;
      }

      for (super.var_21c = super.var_21c < Config.sub_45d(this.var_68) ? super.var_21c : Config.sub_45d(this.var_68);
         super.var_139 < super.var_21c;
         super.var_3c2 = super.var_3c2 + super.var_438
      ) {
         int var22 = super.var_279 >> 16;
         int var13 = super.var_2c3 >> 16;
         int var14 = super.var_368 >> var3;
         int var15 = super.var_3c2 >> var3;
         if (var22 < Config.sub_467(this.var_68)) {
            int var16 = Config.sub_467(this.var_68) - var22;
            var22 = Config.sub_467(this.var_68);
            var14 += var10 * var16;
            var15 += var11 * var16;
         }

         if (var13 > Config.sub_48e(this.var_68)) {
            var13 = Config.sub_48e(this.var_68);
         }

         int var23 = super.var_c4 + var22;

         for (int var17 = super.var_c4 + var13; var23 < var17; var23++) {
            int var18 = var5 + ((var15 & var7) >>> var9) + ((var14 & var6) >>> var8);
            int var19 = var4[var18];
            if (var19 != -1) {
               int var20 = var1[var23];
               int var21 = (var19 & 16711422) + (var20 & 16711422) >>> 1;
               var1[var23] = var21 | 0xFF000000;
            }

            var14 += var10;
            var15 += var11;
         }

         super.var_139++;
         super.var_c4 = super.var_c4 + Config.sub_3dd(this.var_68);
         super.var_279 = super.var_279 + super.var_2fe;
         super.var_2c3 = super.var_2c3 + super.var_326;
         super.var_368 = super.var_368 + super.var_40c;
      }
   }
}
