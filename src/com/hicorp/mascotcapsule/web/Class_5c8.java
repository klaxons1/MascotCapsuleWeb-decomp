package com.hicorp.mascotcapsule.web;

public final class Class_5c8 extends Class_2e8 {
   private final Class_6ed var_a6;

   public Class_5c8(Class_6ed var1) {
      super(var1);
      this.var_a6 = var1;
   }

   public void sub_81() {
      int[] var1 = Class_6ed.sub_34c(this.var_a6);
      int[] var2 = Class_6ed.sub_4c1();
      int var3 = (super.var_3bf > 0 ? super.var_3bf : -super.var_3bf) + (super.var_3d8 > 0 ? super.var_3d8 : -super.var_3d8) + 32768;
      int var4 = Class_6ed.sub_509(this.var_a6).sub_2cf(Class_4ce.sub_30a(var3) - 17);
      int[] var5 = Class_6ed.sub_509(this.var_a6).sub_93();
      int var6 = Class_6ed.sub_509(this.var_a6).sub_327(var4);
      int var7 = Class_6ed.sub_509(this.var_a6).sub_3df(var4);
      int var8 = Class_6ed.sub_509(this.var_a6).sub_418(var4);
      int var9 = Class_6ed.sub_509(this.var_a6).sub_42b(var4);
      int var10 = Class_6ed.sub_509(this.var_a6).sub_467(var4);
      int var11 = super.var_3bf >> var4;
      int var12 = super.var_3d8 >> var4;
      int var13 = super.var_403;
      int var14 = super.var_45b;

      for (super.var_2c9 += 8388608; super.var_aa < super.var_bc; super.var_2c9 = super.var_2c9 + super.var_3a2) {
         int var15 = (super.var_116 >> 16) + super.var_5f;
         int var16 = (super.var_166 >> 16) + super.var_5f;
         int var17 = super.var_204 >> var4;
         int var18 = super.var_248 >> var4;
         int var19 = super.var_264;

         for (int var20 = super.var_2c9; var15 < var16; var15++) {
            int var21 = var6 + ((var18 & var8) >>> var10) + ((var17 & var7) >>> var9);
            int var22 = var5[var21];
            int var23 = var19 >>> 16;
            int var24 = var2[var20 >>> 16 & 511];
            int var26 = ((var22 & 16711935) * var23 & -16711936) + ((var22 & 0xFF00) * var23 & 0xFF0000) >>> 8;
            int var25 = ((var26 & var24) << 1) + ((var26 ^ var24) & 16711422) & 16843008;
            var25 = (var25 >>> 8) + 8355711 ^ 8355711;
            var25 = var26 + var24 - var25 | var25;
            var1[var15] = var25 | 0xFF000000;
            var17 += var11;
            var18 += var12;
            var19 += var13;
            var20 += var14;
         }

         super.var_aa++;
         super.var_5f = super.var_5f + Class_6ed.sub_3dd(this.var_a6);
         super.var_116 = super.var_116 + super.var_172;
         super.var_166 = super.var_166 + super.var_1ab;
         super.var_204 = super.var_204 + super.var_2ea;
         super.var_248 = super.var_248 + super.var_311;
         super.var_264 = super.var_264 + super.var_341;
      }

      super.var_2c9 -= 8388608;
   }
}
