package com.hicorp.mascotcapsule.web;

public final class Class_1395 extends Class_1279 {
   private final Config var_52;

   public Class_1395(Config var1) {
      super(var1);
      this.var_52 = var1;
   }

   public void sub_1c() {
      int[] var1 = Config.sub_34c(this.var_52);
      int var2 = (super.var_491 > 0 ? super.var_491 : -super.var_491) + (super.var_49f > 0 ? super.var_49f : -super.var_49f) + 32768;
      int var3 = Config.sub_509(this.var_52).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Config.sub_509(this.var_52).sub_93();
      int var5 = Config.sub_509(this.var_52).sub_327(var3);
      int var6 = Config.sub_509(this.var_52).sub_3df(var3);
      int var7 = Config.sub_509(this.var_52).sub_418(var3);
      int var8 = Config.sub_509(this.var_52).sub_42b(var3);
      int var9 = Config.sub_509(this.var_52).sub_467(var3);
      int var10 = super.var_491 >> var3;
      int var11 = super.var_49f >> var3;
      int var12 = Config.sub_52c(this.var_52);

      for (int var13 = 255 - Config.sub_52c(this.var_52); super.var_139 < super.var_21c; super.var_3c2 = super.var_3c2 + super.var_438) {
         int var14 = (super.var_279 >> 16) + super.var_c4;
         int var15 = (super.var_2c3 >> 16) + super.var_c4;
         int var16 = super.var_368 >> var3;

         for (int var17 = super.var_3c2 >> var3; var14 < var15; var14++) {
            int var18 = var5 + ((var17 & var7) >>> var9) + ((var16 & var6) >>> var8);
            int var19 = var4[var18];
            if (var19 != -1) {
               int var20 = var1[var14];
               int var21 = ((var19 & 16711935) * var12 & -16711936)
                     + ((var19 & 0xFF00) * var12 & 0xFF0000)
                     + ((var20 & 16711935) * var13 & -16711936)
                     + ((var20 & 0xFF00) * var13 & 0xFF0000)
                  >>> 8;
               var1[var14] = var21 | 0xFF000000;
            }

            var16 += var10;
            var17 += var11;
         }

         super.var_139++;
         super.var_c4 = super.var_c4 + Config.sub_3dd(this.var_52);
         super.var_279 = super.var_279 + super.var_2fe;
         super.var_2c3 = super.var_2c3 + super.var_326;
         super.var_368 = super.var_368 + super.var_40c;
      }
   }
}
