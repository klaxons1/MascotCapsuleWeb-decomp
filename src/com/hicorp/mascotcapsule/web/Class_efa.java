package com.hicorp.mascotcapsule.web;

public final class Class_efa extends Class_eda {
   private final Config var_29;

   public Class_efa(Config var1) {
      super(var1);
      this.var_29 = var1;
   }

   public void sub_46() {
      int[] var1 = Config.sub_34c(this.var_29);

      for (int var2 = Config.sub_397(this.var_29); super.var_15e < super.var_170; super.var_1ac = super.var_1ac + super.var_24e) {
         int var3 = (super.var_1a2 >> 16) + super.var_f1;

         for (int var4 = (super.var_1ac >> 16) + super.var_f1; var3 < var4; var3++) {
            var1[var3] = var2;
         }

         super.var_15e++;
         super.var_f1 = super.var_f1 + Config.sub_3dd(this.var_29);
         super.var_1a2 = super.var_1a2 + super.var_201;
      }
   }
}
