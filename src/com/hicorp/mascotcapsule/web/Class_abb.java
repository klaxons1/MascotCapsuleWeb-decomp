package com.hicorp.mascotcapsule.web;

public final class Class_abb extends MeshLoader {
   private final Config var_83;

   public Class_abb(Config var1) {
      super(var1);
      this.var_83 = var1;
   }

   public void sub_81() {
      int[] var1 = Config.sub_34c(this.var_83);
      int[] var2 = Config.sub_4c1();
      int var3 = (super.var_3bf > 0 ? super.var_3bf : -super.var_3bf) + (super.var_3d8 > 0 ? super.var_3d8 : -super.var_3d8) + 32768;
      int var4 = Config.sub_509(this.var_83).sub_2cf(MatrixUtils.sub_30a(var3) - 17);
      int[] var5 = Config.sub_509(this.var_83).sub_93();
      int var6 = Config.sub_509(this.var_83).sub_327(var4);
      int var7 = Config.sub_509(this.var_83).sub_3df(var4);
      int var8 = Config.sub_509(this.var_83).sub_418(var4);
      int var9 = Config.sub_509(this.var_83).sub_42b(var4);
      int var10 = Config.sub_509(this.var_83).sub_467(var4);
      int var11 = super.var_3bf >> var4;
      int var12 = super.var_3d8 >> var4;
      int var13 = super.var_403;
      int var14 = super.var_45b;
      if (super.var_aa < Config.sub_41a(this.var_83)) {
         int var15;
         if (super.var_bc < Config.sub_41a(this.var_83)) {
            var15 = super.var_bc - super.var_aa;
            super.var_aa = super.var_bc;
         } else {
            var15 = Config.sub_41a(this.var_83) - super.var_aa;
            super.var_aa = Config.sub_41a(this.var_83);
         }

         super.var_5f = super.var_5f + Config.sub_3dd(this.var_83) * var15;
         super.var_116 = super.var_116 + super.var_172 * var15;
         super.var_166 = super.var_166 + super.var_1ab * var15;
         super.var_204 = super.var_204 + super.var_2ea * var15;
         super.var_248 = super.var_248 + super.var_311 * var15;
         super.var_264 = super.var_264 + super.var_341 * var15;
         super.var_2c9 = super.var_2c9 + super.var_3a2 * var15;
      }

      super.var_bc = super.var_bc < Config.sub_45d(this.var_83) ? super.var_bc : Config.sub_45d(this.var_83);

      for (super.var_2c9 += 8388608; super.var_aa < super.var_bc; super.var_2c9 = super.var_2c9 + super.var_3a2) {
         int var31 = super.var_116 >> 16;
         int var16 = super.var_166 >> 16;
         int var17 = super.var_204 >> var4;
         int var18 = super.var_248 >> var4;
         int var19 = super.var_264;
         int var20 = super.var_2c9;
         if (var31 < Config.sub_467(this.var_83)) {
            int var21 = Config.sub_467(this.var_83) - var31;
            var31 = Config.sub_467(this.var_83);
            var17 += var11 * var21;
            var18 += var12 * var21;
            var19 += var13 * var21;
            var20 += var14 * var21;
         }

         if (var16 > Config.sub_48e(this.var_83)) {
            var16 = Config.sub_48e(this.var_83);
         }

         int var32 = super.var_5f + var31;

         for (int var22 = super.var_5f + var16; var32 < var22; var32++) {
            int var23 = var6 + ((var18 & var8) >>> var10) + ((var17 & var7) >>> var9);
            int var24 = var5[var23];
            if (var24 != -1) {
               int var25 = var1[var32];
               int var26 = var19 >>> 16;
               int var27 = var2[var20 >>> 16 & 511];
               int var30 = ((var24 & 16711935) * var26 & -16711936) + ((var24 & 0xFF00) * var26 & 0xFF0000) >>> 8;
               int var28 = ((var30 & var27) << 1) + ((var30 ^ var27) & 16711422) & 16843008;
               var28 = (var28 >>> 8) + 8355711 ^ 8355711;
               var28 = var30 + var27 - var28 | var28;
               int var29 = ((var28 & var25) << 1) + ((var28 ^ var25) & 16711422) & 16843008;
               var29 = (var29 >>> 8) + 8355711 ^ 8355711;
               var29 = var28 + var25 - var29 | var29;
               var1[var32] = var29 | 0xFF000000;
            }

            var17 += var11;
            var18 += var12;
            var19 += var13;
            var20 += var14;
         }

         super.var_aa++;
         super.var_5f = super.var_5f + Config.sub_3dd(this.var_83);
         super.var_116 = super.var_116 + super.var_172;
         super.var_166 = super.var_166 + super.var_1ab;
         super.var_204 = super.var_204 + super.var_2ea;
         super.var_248 = super.var_248 + super.var_311;
         super.var_264 = super.var_264 + super.var_341;
      }

      super.var_2c9 -= 8388608;
   }
}
