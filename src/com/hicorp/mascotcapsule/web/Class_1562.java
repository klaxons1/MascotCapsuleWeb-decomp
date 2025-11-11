package com.hicorp.mascotcapsule.web;

public final class Class_1562 extends Class_15c {
   private final Class_6ed var_61;

   public Class_1562(Class_6ed var1) {
      super(var1);
      this.var_61 = var1;
   }

   public void sub_26() {
      int[] var1 = Class_6ed.sub_34c(this.var_61);
      int var2 = (super.var_2d1 > 0 ? super.var_2d1 : -super.var_2d1) + (super.var_319 > 0 ? super.var_319 : -super.var_319) + 32768;
      int var3 = Class_6ed.sub_509(this.var_61).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_61).sub_93();
      int var5 = Class_6ed.sub_509(this.var_61).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_61).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_61).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_61).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_61).sub_467(var3);
      int var10 = super.var_2d1 >> var3;
      int var11 = super.var_319 >> var3;

      for (int var12 = super.var_339; super.var_6c < super.var_81; super.var_216 = super.var_216 + super.var_282) {
         int var13 = (super.var_db >> 16) + super.var_31;
         int var14 = (super.var_12b >> 16) + super.var_31;
         int var15 = super.var_1c8 >> var3;
         int var16 = super.var_205 >> var3;

         for (int var17 = super.var_216; var13 < var14; var13++) {
            int var18 = var5 + ((var16 & var7) >>> var9) + ((var15 & var6) >>> var8);
            int var19 = var4[var18];
            int var20 = var17 >>> 16;
            int var21 = ((var19 & 16711935) * var20 & -16711936) + ((var19 & 0xFF00) * var20 & 0xFF0000) >>> 8;
            var1[var13] = var21 | 0xFF000000;
            var15 += var10;
            var16 += var11;
            var17 += var12;
         }

         super.var_6c++;
         super.var_31 = super.var_31 + Class_6ed.sub_3dd(this.var_61);
         super.var_db = super.var_db + super.var_14b;
         super.var_12b = super.var_12b + super.var_18a;
         super.var_1c8 = super.var_1c8 + super.var_243;
         super.var_205 = super.var_205 + super.var_24d;
      }
   }
}
