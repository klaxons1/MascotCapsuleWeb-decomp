package com.hicorp.mascotcapsule.web;

public final class Class_f8f extends Class_eda {
   private final Class_6ed var_33;

   public Class_f8f(Class_6ed var1) {
      super(var1);
      this.var_33 = var1;
   }

   public void sub_46() {
      int[] var1 = Class_6ed.sub_34c(this.var_33);

      for (int var2 = Class_6ed.sub_397(this.var_33); super.var_15e < super.var_170; super.var_1ac = super.var_1ac + super.var_24e) {
         int var3 = (super.var_1a2 >> 16) + super.var_f1;
         int var4 = (super.var_1ac >> 16) + super.var_f1;
         if ((var3 & 1 ^ super.var_15e & 1) != 0) {
            var3++;
         }

         while (var3 < var4) {
            var1[var3] = var2;
            var3 += 2;
         }

         super.var_15e++;
         super.var_f1 = super.var_f1 + Class_6ed.sub_3dd(this.var_33);
         super.var_1a2 = super.var_1a2 + super.var_201;
      }
   }
}
