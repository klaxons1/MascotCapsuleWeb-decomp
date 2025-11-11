package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_10ea extends Class_15c {
   private final Class_6ed var_1e;

   public Class_10ea(Class_6ed var1) {
      super(var1);
      this.var_1e = var1;
   }

   public void sub_26() {
      int[] var1 = Class_6ed.sub_34c(this.var_1e);
      int var2 = (super.var_2d1 > 0 ? super.var_2d1 : -super.var_2d1) + (super.var_319 > 0 ? super.var_319 : -super.var_319) + 32768;
      int var3 = Class_6ed.sub_509(this.var_1e).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_1e).sub_93();
      int var5 = Class_6ed.sub_509(this.var_1e).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_1e).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_1e).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_1e).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_1e).sub_467(var3);
      int var10 = super.var_2d1 >> var3;
      int var11 = super.var_319 >> var3;
      int var12 = super.var_339;
      int var13 = Class_6ed.sub_52c(this.var_1e);
      int var14 = 255 - Class_6ed.sub_52c(this.var_1e);
      if (super.var_6c < Class_6ed.sub_41a(this.var_1e)) {
         int var15;
         if (super.var_81 < Class_6ed.sub_41a(this.var_1e)) {
            var15 = super.var_81 - super.var_6c;
            super.var_6c = super.var_81;
         } else {
            var15 = Class_6ed.sub_41a(this.var_1e) - super.var_6c;
            super.var_6c = Class_6ed.sub_41a(this.var_1e);
         }

         super.var_31 = super.var_31 + Class_6ed.sub_3dd(this.var_1e) * var15;
         super.var_db = super.var_db + super.var_14b * var15;
         super.var_12b = super.var_12b + super.var_18a * var15;
         super.var_1c8 = super.var_1c8 + super.var_243 * var15;
         super.var_205 = super.var_205 + super.var_24d * var15;
         super.var_216 = super.var_216 + super.var_282 * var15;
      }

      for (super.var_81 = super.var_81 < Class_6ed.sub_45d(this.var_1e) ? super.var_81 : Class_6ed.sub_45d(this.var_1e);
         super.var_6c < super.var_81;
         super.var_216 = super.var_216 + super.var_282
      ) {
         int var27 = super.var_db >> 16;
         int var16 = super.var_12b >> 16;
         int var17 = super.var_1c8 >> var3;
         int var18 = super.var_205 >> var3;
         int var19 = super.var_216;
         if (var27 < Class_6ed.sub_467(this.var_1e)) {
            int var20 = Class_6ed.sub_467(this.var_1e) - var27;
            var27 = Class_6ed.sub_467(this.var_1e);
            var17 += var10 * var20;
            var18 += var11 * var20;
            var19 += var12 * var20;
         }

         if (var16 > Class_6ed.sub_48e(this.var_1e)) {
            var16 = Class_6ed.sub_48e(this.var_1e);
         }

         int var28 = super.var_31 + var27;

         for (int var21 = super.var_31 + var16; var28 < var21; var28++) {
            int var22 = var5 + ((var18 & var7) >>> var9) + ((var17 & var6) >>> var8);
            int var23 = var4[var22];
            int var24 = var1[var28];
            int var25 = var19 * var13 >>> 24;
            int var26 = ((var23 & 16711935) * var25 & -16711936)
                  + ((var23 & 0xFF00) * var25 & 0xFF0000)
                  + ((var24 & 16711935) * var14 & -16711936)
                  + ((var24 & 0xFF00) * var14 & 0xFF0000)
               >>> 8;
            var1[var28] = var26 | 0xFF000000;
            var17 += var10;
            var18 += var11;
            var19 += var12;
         }

         super.var_6c++;
         super.var_31 = super.var_31 + Class_6ed.sub_3dd(this.var_1e);
         super.var_db = super.var_db + super.var_14b;
         super.var_12b = super.var_12b + super.var_18a;
         super.var_1c8 = super.var_1c8 + super.var_243;
         super.var_205 = super.var_205 + super.var_24d;
      }
   }
}
