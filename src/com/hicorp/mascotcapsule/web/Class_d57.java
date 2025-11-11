package com.hicorp.mascotcapsule.web;

public final class Class_d57 extends Class_15d5 {
   private final Class_6ed var_53;

   public Class_d57(Class_6ed var1) {
      super(var1);
      this.var_53 = var1;
   }

   public void sub_22() {
      int[] var1 = Class_6ed.sub_34c(this.var_53);
      int var2 = (super.var_478 > 0 ? super.var_478 : -super.var_478) + (super.var_510 > 0 ? super.var_510 : -super.var_510) + 32768;
      int var3 = Class_6ed.sub_509(this.var_53).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_53).sub_93();
      int var5 = Class_6ed.sub_509(this.var_53).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_53).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_53).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_53).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_53).sub_467(var3);
      int var10 = super.var_478 >> var3;
      int var11 = super.var_510 >> var3;
      int var12 = super.var_52a;
      var2 = (super.var_54a > 0 ? super.var_54a : -super.var_54a) + (super.var_55b > 0 ? super.var_55b : -super.var_55b) + 32768;
      int var13 = Class_6ed.sub_55c(this.var_53).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var14 = Class_6ed.sub_55c(this.var_53).sub_93();
      int var15 = Class_6ed.sub_55c(this.var_53).sub_327(var13);
      int var16 = Class_6ed.sub_55c(this.var_53).sub_3df(var13);
      int var17 = Class_6ed.sub_55c(this.var_53).sub_418(var13);
      int var18 = Class_6ed.sub_55c(this.var_53).sub_42b(var13);
      int var19 = Class_6ed.sub_55c(this.var_53).sub_467(var13);
      int var20 = super.var_54a >> var13;
      int var21 = super.var_55b >> var13;
      int var22 = Class_6ed.sub_52c(this.var_53);
      int var23 = 255 - Class_6ed.sub_52c(this.var_53);
      if (super.var_8b < Class_6ed.sub_41a(this.var_53)) {
         int var24;
         if (super.var_e8 < Class_6ed.sub_41a(this.var_53)) {
            var24 = super.var_e8 - super.var_8b;
            super.var_8b = super.var_e8;
         } else {
            var24 = Class_6ed.sub_41a(this.var_53) - super.var_8b;
            super.var_8b = Class_6ed.sub_41a(this.var_53);
         }

         super.var_41 = super.var_41 + Class_6ed.sub_3dd(this.var_53) * var24;
         super.var_189 = super.var_189 + super.var_236 * var24;
         super.var_1d6 = super.var_1d6 + super.var_26a * var24;
         super.var_274 = super.var_274 + super.var_3b1 * var24;
         super.var_29a = super.var_29a + super.var_3ca * var24;
         super.var_2d0 = super.var_2d0 + super.var_3f6 * var24;
         super.var_328 = super.var_328 + super.var_44d * var24;
         super.var_383 = super.var_383 + super.var_460 * var24;
      }

      for (super.var_e8 = super.var_e8 < Class_6ed.sub_45d(this.var_53) ? super.var_e8 : Class_6ed.sub_45d(this.var_53);
         super.var_8b < super.var_e8;
         super.var_383 = super.var_383 + super.var_460
      ) {
         int var43 = super.var_189 >> 16;
         int var25 = super.var_1d6 >> 16;
         int var26 = super.var_274 >> var3;
         int var27 = super.var_29a >> var3;
         int var28 = super.var_2d0;
         int var29 = super.var_328 >> var13;
         int var30 = super.var_383 >> var13;
         if (var43 < Class_6ed.sub_467(this.var_53)) {
            int var31 = Class_6ed.sub_467(this.var_53) - var43;
            var43 = Class_6ed.sub_467(this.var_53);
            var26 += var10 * var31;
            var27 += var11 * var31;
            var28 += var12 * var31;
            var29 += var20 * var31;
            var30 += var21 * var31;
         }

         if (var25 > Class_6ed.sub_48e(this.var_53)) {
            var25 = Class_6ed.sub_48e(this.var_53);
         }

         int var44 = super.var_41 + var43;

         for (int var32 = super.var_41 + var25; var44 < var32; var44++) {
            int var33 = var5 + ((var27 & var7) >>> var9) + ((var26 & var6) >>> var8);
            int var34 = var4[var33];
            if (var34 != -1) {
               int var35 = var15 + ((var30 & var17) >>> var19) + ((var29 & var16) >>> var18);
               int var36 = var1[var44];
               int var37 = var28 >>> 16;
               int var38 = var14[var35];
               int var41 = ((var34 & 16711935) * var37 & -16711936) + ((var34 & 0xFF00) * var37 & 0xFF0000) >>> 8;
               int var39 = ((var41 & var38) << 1) + ((var41 ^ var38) & 16711422) & 16843008;
               var39 = (var39 >>> 8) + 8355711 ^ 8355711;
               var39 = var41 + var38 - var39 | var39;
               int var40 = ((var39 & 16711935) * var22 & -16711936)
                     + ((var39 & 0xFF00) * var22 & 0xFF0000)
                     + ((var36 & 16711935) * var23 & -16711936)
                     + ((var36 & 0xFF00) * var23 & 0xFF0000)
                  >>> 8;
               var1[var44] = var40 | 0xFF000000;
            }

            var26 += var10;
            var27 += var11;
            var28 += var12;
            var29 += var20;
            var30 += var21;
         }

         super.var_8b++;
         super.var_41 = super.var_41 + Class_6ed.sub_3dd(this.var_53);
         super.var_189 = super.var_189 + super.var_236;
         super.var_1d6 = super.var_1d6 + super.var_26a;
         super.var_274 = super.var_274 + super.var_3b1;
         super.var_29a = super.var_29a + super.var_3ca;
         super.var_2d0 = super.var_2d0 + super.var_3f6;
         super.var_328 = super.var_328 + super.var_44d;
      }
   }
}
