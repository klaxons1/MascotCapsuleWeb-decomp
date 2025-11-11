package com.hicorp.mascotcapsule.web;

public final class Class_da6 extends Class_2e8 {
   private final Class_6ed var_e1;

   public Class_da6(Class_6ed var1) {
      super(var1);
      this.var_e1 = var1;
   }

   public void sub_81() {
      int[] var1 = Class_6ed.sub_34c(this.var_e1);
      int[] var2 = Class_6ed.sub_4c1();
      int var3 = (super.var_3bf > 0 ? super.var_3bf : -super.var_3bf) + (super.var_3d8 > 0 ? super.var_3d8 : -super.var_3d8) + 32768;
      int var4 = Class_6ed.sub_509(this.var_e1).sub_2cf(Class_4ce.sub_30a(var3) - 17);
      int[] var5 = Class_6ed.sub_509(this.var_e1).sub_93();
      int var6 = Class_6ed.sub_509(this.var_e1).sub_327(var4);
      int var7 = Class_6ed.sub_509(this.var_e1).sub_3df(var4);
      int var8 = Class_6ed.sub_509(this.var_e1).sub_418(var4);
      int var9 = Class_6ed.sub_509(this.var_e1).sub_42b(var4);
      int var10 = Class_6ed.sub_509(this.var_e1).sub_467(var4);
      int var11 = super.var_3bf >> var4;
      int var12 = super.var_3d8 >> var4;
      int var13 = super.var_403;
      int var14 = super.var_45b;
      int var15 = Class_6ed.sub_52c(this.var_e1);
      int var16 = 255 - Class_6ed.sub_52c(this.var_e1);
      if (super.var_aa < Class_6ed.sub_41a(this.var_e1)) {
         int var17;
         if (super.var_bc < Class_6ed.sub_41a(this.var_e1)) {
            var17 = super.var_bc - super.var_aa;
            super.var_aa = super.var_bc;
         } else {
            var17 = Class_6ed.sub_41a(this.var_e1) - super.var_aa;
            super.var_aa = Class_6ed.sub_41a(this.var_e1);
         }

         super.var_5f = super.var_5f + Class_6ed.sub_3dd(this.var_e1) * var17;
         super.var_116 = super.var_116 + super.var_172 * var17;
         super.var_166 = super.var_166 + super.var_1ab * var17;
         super.var_204 = super.var_204 + super.var_2ea * var17;
         super.var_248 = super.var_248 + super.var_311 * var17;
         super.var_264 = super.var_264 + super.var_341 * var17;
         super.var_2c9 = super.var_2c9 + super.var_3a2 * var17;
      }

      super.var_bc = super.var_bc < Class_6ed.sub_45d(this.var_e1) ? super.var_bc : Class_6ed.sub_45d(this.var_e1);

      for (super.var_2c9 += 8388608; super.var_aa < super.var_bc; super.var_2c9 = super.var_2c9 + super.var_3a2) {
         int var33 = super.var_116 >> 16;
         int var18 = super.var_166 >> 16;
         int var19 = super.var_204 >> var4;
         int var20 = super.var_248 >> var4;
         int var21 = super.var_264;
         int var22 = super.var_2c9;
         if (var33 < Class_6ed.sub_467(this.var_e1)) {
            int var23 = Class_6ed.sub_467(this.var_e1) - var33;
            var33 = Class_6ed.sub_467(this.var_e1);
            var19 += var11 * var23;
            var20 += var12 * var23;
            var21 += var13 * var23;
            var22 += var14 * var23;
         }

         if (var18 > Class_6ed.sub_48e(this.var_e1)) {
            var18 = Class_6ed.sub_48e(this.var_e1);
         }

         int var34 = super.var_5f + var33;

         for (int var24 = super.var_5f + var18; var34 < var24; var34++) {
            int var25 = var6 + ((var20 & var8) >>> var10) + ((var19 & var7) >>> var9);
            int var26 = var5[var25];
            int var27 = var1[var34];
            int var28 = var21 >>> 16;
            int var29 = var2[var22 >>> 16 & 511];
            int var32 = ((var26 & 16711935) * var28 & -16711936) + ((var26 & 0xFF00) * var28 & 0xFF0000) >>> 8;
            int var30 = ((var32 & var29) << 1) + ((var32 ^ var29) & 16711422) & 16843008;
            var30 = (var30 >>> 8) + 8355711 ^ 8355711;
            var30 = var32 + var29 - var30 | var30;
            int var31 = ((var30 & 16711935) * var15 & -16711936)
                  + ((var30 & 0xFF00) * var15 & 0xFF0000)
                  + ((var27 & 16711935) * var16 & -16711936)
                  + ((var27 & 0xFF00) * var16 & 0xFF0000)
               >>> 8;
            var1[var34] = var31 | 0xFF000000;
            var19 += var11;
            var20 += var12;
            var21 += var13;
            var22 += var14;
         }

         super.var_aa++;
         super.var_5f = super.var_5f + Class_6ed.sub_3dd(this.var_e1);
         super.var_116 = super.var_116 + super.var_172;
         super.var_166 = super.var_166 + super.var_1ab;
         super.var_204 = super.var_204 + super.var_2ea;
         super.var_248 = super.var_248 + super.var_311;
         super.var_264 = super.var_264 + super.var_341;
      }

      super.var_2c9 -= 8388608;
   }
}
