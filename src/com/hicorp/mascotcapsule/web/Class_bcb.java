package com.hicorp.mascotcapsule.web;

public final class Class_bcb extends Class_1279 {
   private final Config var_2c;

   public Class_bcb(Config var1) {
      super(var1);
      this.var_2c = var1;
   }

   public void sub_1c() {
      int[] var1 = Config.sub_34c(this.var_2c);
      int var2 = (super.var_491 > 0 ? super.var_491 : -super.var_491) + (super.var_49f > 0 ? super.var_49f : -super.var_49f) + 32768;
      int var3 = Config.sub_509(this.var_2c).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Config.sub_509(this.var_2c).sub_93();
      int var5 = Config.sub_509(this.var_2c).sub_327(var3);
      int var6 = Config.sub_509(this.var_2c).sub_3df(var3);
      int var7 = Config.sub_509(this.var_2c).sub_418(var3);
      int var8 = Config.sub_509(this.var_2c).sub_42b(var3);
      int var9 = Config.sub_509(this.var_2c).sub_467(var3);
      int var10 = super.var_491 >> var3;

      for (int var11 = super.var_49f >> var3; super.var_139 < super.var_21c; super.var_3c2 = super.var_3c2 + super.var_438) {
         int var12 = (super.var_279 >> 16) + super.var_c4;
         int var13 = (super.var_2c3 >> 16) + super.var_c4;
         int var14 = super.var_368 >> var3;

         for (int var15 = super.var_3c2 >> var3; var12 < var13; var12++) {
            int var16 = var5 + ((var15 & var7) >>> var9) + ((var14 & var6) >>> var8);
            int var17 = var4[var16];
            if (var17 != -1) {
               int var18 = var1[var12];
               int var19 = ((var17 & var18) << 1) + ((var17 ^ var18) & 16711422) & 16843008;
               var19 = (var19 >>> 8) + 8355711 ^ 8355711;
               var19 = var17 + var18 - var19 | var19;
               var1[var12] = var19 | 0xFF000000;
            }

            var14 += var10;
            var15 += var11;
         }

         super.var_139++;
         super.var_c4 = super.var_c4 + Config.sub_3dd(this.var_2c);
         super.var_279 = super.var_279 + super.var_2fe;
         super.var_2c3 = super.var_2c3 + super.var_326;
         super.var_368 = super.var_368 + super.var_40c;
      }
   }
}
