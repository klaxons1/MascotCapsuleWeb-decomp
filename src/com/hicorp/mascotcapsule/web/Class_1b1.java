package com.hicorp.mascotcapsule.web;

public final class Class_1b1 extends Class_2e8 {
   private final Class_6ed var_48;

   public Class_1b1(Class_6ed var1) {
      super(var1);
      this.var_48 = var1;
   }

   public void sub_81() {
      int[] var1 = Class_6ed.sub_34c(this.var_48);
      int[] var2 = Class_6ed.sub_4c1();
      int var3 = (super.var_3bf > 0 ? super.var_3bf : -super.var_3bf) + (super.var_3d8 > 0 ? super.var_3d8 : -super.var_3d8) + 32768;
      int var4 = Class_6ed.sub_509(this.var_48).sub_2cf(MatrixUtils.sub_30a(var3) - 17);
      int[] var5 = Class_6ed.sub_509(this.var_48).sub_93();
      int var6 = Class_6ed.sub_509(this.var_48).sub_327(var4);
      int var7 = Class_6ed.sub_509(this.var_48).sub_3df(var4);
      int var8 = Class_6ed.sub_509(this.var_48).sub_418(var4);
      int var9 = Class_6ed.sub_509(this.var_48).sub_42b(var4);
      int var10 = Class_6ed.sub_509(this.var_48).sub_467(var4);
      int var11 = super.var_3bf >> var4;
      int var12 = super.var_3d8 >> var4;
      int var13 = super.var_403;
      int var14 = super.var_45b;
      if (super.var_aa < Class_6ed.sub_41a(this.var_48)) {
         int var15;
         if (super.var_bc < Class_6ed.sub_41a(this.var_48)) {
            var15 = super.var_bc - super.var_aa;
            super.var_aa = super.var_bc;
         } else {
            var15 = Class_6ed.sub_41a(this.var_48) - super.var_aa;
            super.var_aa = Class_6ed.sub_41a(this.var_48);
         }

         super.var_5f = super.var_5f + Class_6ed.sub_3dd(this.var_48) * var15;
         super.var_116 = super.var_116 + super.var_172 * var15;
         super.var_166 = super.var_166 + super.var_1ab * var15;
         super.var_204 = super.var_204 + super.var_2ea * var15;
         super.var_248 = super.var_248 + super.var_311 * var15;
         super.var_264 = super.var_264 + super.var_341 * var15;
         super.var_2c9 = super.var_2c9 + super.var_3a2 * var15;
      }

      super.var_bc = super.var_bc < Class_6ed.sub_45d(this.var_48) ? super.var_bc : Class_6ed.sub_45d(this.var_48);

      for (super.var_2c9 += 8388608; super.var_aa < super.var_bc; super.var_2c9 = super.var_2c9 + super.var_3a2) {
         int var30 = super.var_116 >> 16;
         int var16 = super.var_166 >> 16;
         int var17 = super.var_204 >> var4;
         int var18 = super.var_248 >> var4;
         int var19 = super.var_264;
         int var20 = super.var_2c9;
         if (var30 < Class_6ed.sub_467(this.var_48)) {
            int var21 = Class_6ed.sub_467(this.var_48) - var30;
            var30 = Class_6ed.sub_467(this.var_48);
            var17 += var11 * var21;
            var18 += var12 * var21;
            var19 += var13 * var21;
            var20 += var14 * var21;
         }

         if (var16 > Class_6ed.sub_48e(this.var_48)) {
            var16 = Class_6ed.sub_48e(this.var_48);
         }

         int var31 = super.var_5f + var30;

         for (int var22 = super.var_5f + var16; var31 < var22; var31++) {
            int var23 = var6 + ((var18 & var8) >>> var10) + ((var17 & var7) >>> var9);
            int var24 = var5[var23];
            int var25 = var1[var31];
            int var26 = var19 >>> 16;
            int var27 = var2[var20 >>> 16 & 511];
            int var29 = ((var24 & 16711935) * var26 & -16711936) + ((var24 & 0xFF00) * var26 & 0xFF0000) >>> 8;
            int var28 = ((var29 & var27) << 1) + ((var29 ^ var27) & 16711422) & 16843008;
            var28 = (var28 >>> 8) + 8355711 ^ 8355711;
            var28 = var29 + var27 - var28 | var28;
            var28 = (var28 & 16711422) + (var25 & 16711422) >>> 1;
            var1[var31] = var28 | 0xFF000000;
            var17 += var11;
            var18 += var12;
            var19 += var13;
            var20 += var14;
         }

         super.var_aa++;
         super.var_5f = super.var_5f + Class_6ed.sub_3dd(this.var_48);
         super.var_116 = super.var_116 + super.var_172;
         super.var_166 = super.var_166 + super.var_1ab;
         super.var_204 = super.var_204 + super.var_2ea;
         super.var_248 = super.var_248 + super.var_311;
         super.var_264 = super.var_264 + super.var_341;
      }

      super.var_2c9 -= 8388608;
   }
}
