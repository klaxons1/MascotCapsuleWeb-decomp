package com.hicorp.mascotcapsule.web;

public final class Class_118e extends ModelLoader {
   private final Config var_42;

   public Class_118e(Config var1) {
      super(var1);
      this.var_42 = var1;
   }

   public void sub_26() {
      int[] var1 = Config.sub_34c(this.var_42);
      int var2 = (super.var_2d1 > 0 ? super.var_2d1 : -super.var_2d1) + (super.var_319 > 0 ? super.var_319 : -super.var_319) + 32768;
      int var3 = Config.sub_509(this.var_42).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Config.sub_509(this.var_42).sub_93();
      int var5 = Config.sub_509(this.var_42).sub_327(var3);
      int var6 = Config.sub_509(this.var_42).sub_3df(var3);
      int var7 = Config.sub_509(this.var_42).sub_418(var3);
      int var8 = Config.sub_509(this.var_42).sub_42b(var3);
      int var9 = Config.sub_509(this.var_42).sub_467(var3);
      int var10 = super.var_2d1 >> var3;
      int var11 = super.var_319 >> var3;
      int var12 = super.var_339;
      if (super.var_6c < Config.sub_41a(this.var_42)) {
         int var13;
         if (super.var_81 < Config.sub_41a(this.var_42)) {
            var13 = super.var_81 - super.var_6c;
            super.var_6c = super.var_81;
         } else {
            var13 = Config.sub_41a(this.var_42) - super.var_6c;
            super.var_6c = Config.sub_41a(this.var_42);
         }

         super.var_31 = super.var_31 + Config.sub_3dd(this.var_42) * var13;
         super.var_db = super.var_db + super.var_14b * var13;
         super.var_12b = super.var_12b + super.var_18a * var13;
         super.var_1c8 = super.var_1c8 + super.var_243 * var13;
         super.var_205 = super.var_205 + super.var_24d * var13;
         super.var_216 = super.var_216 + super.var_282 * var13;
      }

      for (super.var_81 = super.var_81 < Config.sub_45d(this.var_42) ? super.var_81 : Config.sub_45d(this.var_42);
         super.var_6c < super.var_81;
         super.var_216 = super.var_216 + super.var_282
      ) {
         int var26 = super.var_db >> 16;
         int var14 = super.var_12b >> 16;
         int var15 = super.var_1c8 >> var3;
         int var16 = super.var_205 >> var3;
         int var17 = super.var_216;
         if (var26 < Config.sub_467(this.var_42)) {
            int var18 = Config.sub_467(this.var_42) - var26;
            var26 = Config.sub_467(this.var_42);
            var15 += var10 * var18;
            var16 += var11 * var18;
            var17 += var12 * var18;
         }

         if (var14 > Config.sub_48e(this.var_42)) {
            var14 = Config.sub_48e(this.var_42);
         }

         int var27 = super.var_31 + var26;

         for (int var19 = super.var_31 + var14; var27 < var19; var27++) {
            int var20 = var5 + ((var16 & var7) >>> var9) + ((var15 & var6) >>> var8);
            int var21 = var4[var20];
            if (var21 != -1) {
               int var22 = var1[var27];
               int var23 = var17 >>> 16;
               int var25 = ((var21 & 16711935) * var23 & -16711936) + ((var21 & 0xFF00) * var23 & 0xFF0000) >>> 8;
               int var24 = ((var25 & var22) << 1) + ((var25 ^ var22) & 16711422) & 16843008;
               var24 = (var24 >>> 8) + 8355711 ^ 8355711;
               var24 = var25 + var22 - var24 | var24;
               var1[var27] = var24 | 0xFF000000;
            }

            var15 += var10;
            var16 += var11;
            var17 += var12;
         }

         super.var_6c++;
         super.var_31 = super.var_31 + Config.sub_3dd(this.var_42);
         super.var_db = super.var_db + super.var_14b;
         super.var_12b = super.var_12b + super.var_18a;
         super.var_1c8 = super.var_1c8 + super.var_243;
         super.var_205 = super.var_205 + super.var_24d;
      }
   }
}
