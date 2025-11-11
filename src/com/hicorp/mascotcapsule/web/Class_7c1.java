package com.hicorp.mascotcapsule.web;

public final class Class_7c1 extends Class_15c {
   private final Class_6ed var_9e;

   public Class_7c1(Class_6ed var1) {
      super(var1);
      this.var_9e = var1;
   }

   public void sub_26() {
      int[] var1 = Class_6ed.sub_34c(this.var_9e);
      int var2 = (super.var_2d1 > 0 ? super.var_2d1 : -super.var_2d1) + (super.var_319 > 0 ? super.var_319 : -super.var_319) + 32768;
      int var3 = Class_6ed.sub_509(this.var_9e).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_9e).sub_93();
      int var5 = Class_6ed.sub_509(this.var_9e).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_9e).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_9e).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_9e).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_9e).sub_467(var3);
      int var10 = super.var_2d1 >> var3;
      int var11 = super.var_319 >> var3;
      int var12 = super.var_339;
      int var13 = Class_6ed.sub_52c(this.var_9e);

      for (int var14 = 255 - Class_6ed.sub_52c(this.var_9e); super.var_6c < super.var_81; super.var_216 = super.var_216 + super.var_282) {
         int var15 = (super.var_db >> 16) + super.var_31;
         int var16 = (super.var_12b >> 16) + super.var_31;
         int var17 = super.var_1c8 >> var3;
         int var18 = super.var_205 >> var3;

         for (int var19 = super.var_216; var15 < var16; var15++) {
            int var20 = var5 + ((var18 & var7) >>> var9) + ((var17 & var6) >>> var8);
            int var21 = var4[var20];
            int var22 = var1[var15];
            int var23 = var19 * var13 >>> 24;
            int var24 = ((var21 & 16711935) * var23 & -16711936)
                  + ((var21 & 0xFF00) * var23 & 0xFF0000)
                  + ((var22 & 16711935) * var14 & -16711936)
                  + ((var22 & 0xFF00) * var14 & 0xFF0000)
               >>> 8;
            var1[var15] = var24 | 0xFF000000;
            var17 += var10;
            var18 += var11;
            var19 += var12;
         }

         super.var_6c++;
         super.var_31 = super.var_31 + Class_6ed.sub_3dd(this.var_9e);
         super.var_db = super.var_db + super.var_14b;
         super.var_12b = super.var_12b + super.var_18a;
         super.var_1c8 = super.var_1c8 + super.var_243;
         super.var_205 = super.var_205 + super.var_24d;
      }
   }
}
