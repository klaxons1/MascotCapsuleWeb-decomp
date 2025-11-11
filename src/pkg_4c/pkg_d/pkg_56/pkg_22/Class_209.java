package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_209 extends Class_15d5 {
   private final Class_6ed var_90;

   public Class_209(Class_6ed var1) {
      super(var1);
      this.var_90 = var1;
   }

   public void sub_22() {
      int[] var1 = Class_6ed.sub_34c(this.var_90);
      int var2 = (super.var_478 > 0 ? super.var_478 : -super.var_478) + (super.var_510 > 0 ? super.var_510 : -super.var_510) + 32768;
      int var3 = Class_6ed.sub_509(this.var_90).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_90).sub_93();
      int var5 = Class_6ed.sub_509(this.var_90).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_90).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_90).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_90).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_90).sub_467(var3);
      int var10 = super.var_478 >> var3;
      int var11 = super.var_510 >> var3;
      int var12 = super.var_52a;
      var2 = (super.var_54a > 0 ? super.var_54a : -super.var_54a) + (super.var_55b > 0 ? super.var_55b : -super.var_55b) + 32768;
      int var13 = Class_6ed.sub_55c(this.var_90).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var14 = Class_6ed.sub_55c(this.var_90).sub_93();
      int var15 = Class_6ed.sub_55c(this.var_90).sub_327(var13);
      int var16 = Class_6ed.sub_55c(this.var_90).sub_3df(var13);
      int var17 = Class_6ed.sub_55c(this.var_90).sub_418(var13);
      int var18 = Class_6ed.sub_55c(this.var_90).sub_42b(var13);
      int var19 = Class_6ed.sub_55c(this.var_90).sub_467(var13);
      int var20 = super.var_54a >> var13;
      int var21 = super.var_55b >> var13;
      if (super.var_8b < Class_6ed.sub_41a(this.var_90)) {
         int var22;
         if (super.var_e8 < Class_6ed.sub_41a(this.var_90)) {
            var22 = super.var_e8 - super.var_8b;
            super.var_8b = super.var_e8;
         } else {
            var22 = Class_6ed.sub_41a(this.var_90) - super.var_8b;
            super.var_8b = Class_6ed.sub_41a(this.var_90);
         }

         super.var_41 = super.var_41 + Class_6ed.sub_3dd(this.var_90) * var22;
         super.var_189 = super.var_189 + super.var_236 * var22;
         super.var_1d6 = super.var_1d6 + super.var_26a * var22;
         super.var_274 = super.var_274 + super.var_3b1 * var22;
         super.var_29a = super.var_29a + super.var_3ca * var22;
         super.var_2d0 = super.var_2d0 + super.var_3f6 * var22;
         super.var_328 = super.var_328 + super.var_44d * var22;
         super.var_383 = super.var_383 + super.var_460 * var22;
      }

      for (super.var_e8 = super.var_e8 < Class_6ed.sub_45d(this.var_90) ? super.var_e8 : Class_6ed.sub_45d(this.var_90);
         super.var_8b < super.var_e8;
         super.var_383 = super.var_383 + super.var_460
      ) {
         int var39 = super.var_189 >> 16;
         int var23 = super.var_1d6 >> 16;
         int var24 = super.var_274 >> var3;
         int var25 = super.var_29a >> var3;
         int var26 = super.var_2d0;
         int var27 = super.var_328 >> var13;
         int var28 = super.var_383 >> var13;
         if (var39 < Class_6ed.sub_467(this.var_90)) {
            int var29 = Class_6ed.sub_467(this.var_90) - var39;
            var39 = Class_6ed.sub_467(this.var_90);
            var24 += var10 * var29;
            var25 += var11 * var29;
            var26 += var12 * var29;
            var27 += var20 * var29;
            var28 += var21 * var29;
         }

         if (var23 > Class_6ed.sub_48e(this.var_90)) {
            var23 = Class_6ed.sub_48e(this.var_90);
         }

         int var40 = super.var_41 + var39;

         for (int var30 = super.var_41 + var23; var40 < var30; var40++) {
            int var31 = var5 + ((var25 & var7) >>> var9) + ((var24 & var6) >>> var8);
            int var32 = var4[var31];
            if (var32 != -1) {
               int var33 = var15 + ((var28 & var17) >>> var19) + ((var27 & var16) >>> var18);
               int var34 = var26 >>> 16;
               int var35 = var14[var33];
               int var37 = ((var32 & 16711935) * var34 & -16711936) + ((var32 & 0xFF00) * var34 & 0xFF0000) >>> 8;
               int var36 = ((var37 & var35) << 1) + ((var37 ^ var35) & 16711422) & 16843008;
               var36 = (var36 >>> 8) + 8355711 ^ 8355711;
               var36 = var37 + var35 - var36 | var36;
               var1[var40] = var36 | 0xFF000000;
            }

            var24 += var10;
            var25 += var11;
            var26 += var12;
            var27 += var20;
            var28 += var21;
         }

         super.var_8b++;
         super.var_41 = super.var_41 + Class_6ed.sub_3dd(this.var_90);
         super.var_189 = super.var_189 + super.var_236;
         super.var_1d6 = super.var_1d6 + super.var_26a;
         super.var_274 = super.var_274 + super.var_3b1;
         super.var_29a = super.var_29a + super.var_3ca;
         super.var_2d0 = super.var_2d0 + super.var_3f6;
         super.var_328 = super.var_328 + super.var_44d;
      }
   }
}
