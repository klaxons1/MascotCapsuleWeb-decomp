package com.hicorp.mascotcapsule.web;

public final class Class_dfd extends Class_15c {
   private final Class_6ed var_10f;

   public Class_dfd(Class_6ed var1) {
      super(var1);
      this.var_10f = var1;
   }

   public void sub_26() {
      int[] var1 = Class_6ed.sub_34c(this.var_10f);
      int var2 = (super.var_2d1 > 0 ? super.var_2d1 : -super.var_2d1) + (super.var_319 > 0 ? super.var_319 : -super.var_319) + 32768;
      int var3 = Class_6ed.sub_509(this.var_10f).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_10f).sub_93();
      int var5 = Class_6ed.sub_509(this.var_10f).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_10f).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_10f).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_10f).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_10f).sub_467(var3);
      int var10 = super.var_2d1 >> var3;
      int var11 = super.var_319 >> var3;
      int var12 = super.var_339;
      if (super.var_6c < Class_6ed.sub_41a(this.var_10f)) {
         int var13;
         if (super.var_81 < Class_6ed.sub_41a(this.var_10f)) {
            var13 = super.var_81 - super.var_6c;
            super.var_6c = super.var_81;
         } else {
            var13 = Class_6ed.sub_41a(this.var_10f) - super.var_6c;
            super.var_6c = Class_6ed.sub_41a(this.var_10f);
         }

         super.var_31 = super.var_31 + Class_6ed.sub_3dd(this.var_10f) * var13;
         super.var_db = super.var_db + super.var_14b * var13;
         super.var_12b = super.var_12b + super.var_18a * var13;
         super.var_1c8 = super.var_1c8 + super.var_243 * var13;
         super.var_205 = super.var_205 + super.var_24d * var13;
         super.var_216 = super.var_216 + super.var_282 * var13;
      }

      for (super.var_81 = super.var_81 < Class_6ed.sub_45d(this.var_10f) ? super.var_81 : Class_6ed.sub_45d(this.var_10f);
         super.var_6c < super.var_81;
         super.var_216 = super.var_216 + super.var_282
      ) {
         int var25 = super.var_db >> 16;
         int var14 = super.var_12b >> 16;
         int var15 = super.var_1c8 >> var3;
         int var16 = super.var_205 >> var3;
         int var17 = super.var_216;
         if (var25 < Class_6ed.sub_467(this.var_10f)) {
            int var18 = Class_6ed.sub_467(this.var_10f) - var25;
            var25 = Class_6ed.sub_467(this.var_10f);
            var15 += var10 * var18;
            var16 += var11 * var18;
            var17 += var12 * var18;
         }

         if (var14 > Class_6ed.sub_48e(this.var_10f)) {
            var14 = Class_6ed.sub_48e(this.var_10f);
         }

         int var26 = super.var_31 + var25;

         for (int var19 = super.var_31 + var14; var26 < var19; var26++) {
            int var20 = var5 + ((var16 & var7) >>> var9) + ((var15 & var6) >>> var8);
            int var21 = var4[var20];
            int var22 = var1[var26];
            int var23 = var17 >>> 16;
            int var24 = ((var21 & 16711935) * var23 & -16711936) + ((var21 & 0xFF00) * var23 & 0xFF0000) >>> 8;
            var24 = (var24 & 16711422) + (var22 & 16711422) >>> 1;
            var1[var26] = var24 | 0xFF000000;
            var15 += var10;
            var16 += var11;
            var17 += var12;
         }

         super.var_6c++;
         super.var_31 = super.var_31 + Class_6ed.sub_3dd(this.var_10f);
         super.var_db = super.var_db + super.var_14b;
         super.var_12b = super.var_12b + super.var_18a;
         super.var_1c8 = super.var_1c8 + super.var_243;
         super.var_205 = super.var_205 + super.var_24d;
      }
   }
}
