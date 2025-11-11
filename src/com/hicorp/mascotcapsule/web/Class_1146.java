package com.hicorp.mascotcapsule.web;

public final class Class_1146 extends Class_1279 {
   private final Class_6ed var_b0;

   public Class_1146(Class_6ed var1) {
      super(var1);
      this.var_b0 = var1;
   }

   public void sub_1c() {
      int[] var1 = Class_6ed.sub_34c(this.var_b0);
      int var2 = (super.var_491 > 0 ? super.var_491 : -super.var_491) + (super.var_49f > 0 ? super.var_49f : -super.var_49f) + 32768;
      int var3 = Class_6ed.sub_509(this.var_b0).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_b0).sub_93();
      int var5 = Class_6ed.sub_509(this.var_b0).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_b0).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_b0).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_b0).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_b0).sub_467(var3);
      int var10 = super.var_491 >> var3;

      for (int var11 = super.var_49f >> var3; super.var_139 < super.var_21c; super.var_3c2 = super.var_3c2 + super.var_438) {
         int var12 = (super.var_279 >> 16) + super.var_c4;
         int var13 = (super.var_2c3 >> 16) + super.var_c4;
         int var14 = super.var_368 >> var3;

         for (int var15 = super.var_3c2 >> var3; var12 < var13; var12++) {
            int var16 = var5 + ((var15 & var7) >>> var9) + ((var14 & var6) >>> var8);
            int var17 = var4[var16];
            if (var17 != -1) {
               var1[var12] = var17;
            }

            var14 += var10;
            var15 += var11;
         }

         super.var_139++;
         super.var_c4 = super.var_c4 + Class_6ed.sub_3dd(this.var_b0);
         super.var_279 = super.var_279 + super.var_2fe;
         super.var_2c3 = super.var_2c3 + super.var_326;
         super.var_368 = super.var_368 + super.var_40c;
      }
   }
}
