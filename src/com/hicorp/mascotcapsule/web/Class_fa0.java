package com.hicorp.mascotcapsule.web;

public final class Class_fa0 extends Class_15d5 {
   private final Config var_58;

   public Class_fa0(Config var1) {
      super(var1);
      this.var_58 = var1;
   }

   public void sub_22() {
      int[] var1 = Config.sub_34c(this.var_58);
      int var2 = (super.var_478 > 0 ? super.var_478 : -super.var_478) + (super.var_510 > 0 ? super.var_510 : -super.var_510) + 32768;
      int var3 = Config.sub_509(this.var_58).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Config.sub_509(this.var_58).sub_93();
      int var5 = Config.sub_509(this.var_58).sub_327(var3);
      int var6 = Config.sub_509(this.var_58).sub_3df(var3);
      int var7 = Config.sub_509(this.var_58).sub_418(var3);
      int var8 = Config.sub_509(this.var_58).sub_42b(var3);
      int var9 = Config.sub_509(this.var_58).sub_467(var3);
      int var10 = super.var_478 >> var3;
      int var11 = super.var_510 >> var3;
      int var12 = super.var_52a;
      var2 = (super.var_54a > 0 ? super.var_54a : -super.var_54a) + (super.var_55b > 0 ? super.var_55b : -super.var_55b) + 32768;
      int var13 = Config.sub_55c(this.var_58).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var14 = Config.sub_55c(this.var_58).sub_93();
      int var15 = Config.sub_55c(this.var_58).sub_327(var13);
      int var16 = Config.sub_55c(this.var_58).sub_3df(var13);
      int var17 = Config.sub_55c(this.var_58).sub_418(var13);
      int var18 = Config.sub_55c(this.var_58).sub_42b(var13);
      int var19 = Config.sub_55c(this.var_58).sub_467(var13);
      int var20 = super.var_54a >> var13;
      int var21 = super.var_55b >> var13;
      if (super.var_8b < Config.sub_41a(this.var_58)) {
         int var22;
         if (super.var_e8 < Config.sub_41a(this.var_58)) {
            var22 = super.var_e8 - super.var_8b;
            super.var_8b = super.var_e8;
         } else {
            var22 = Config.sub_41a(this.var_58) - super.var_8b;
            super.var_8b = Config.sub_41a(this.var_58);
         }

         super.var_41 = super.var_41 + Config.sub_3dd(this.var_58) * var22;
         super.var_189 = super.var_189 + super.var_236 * var22;
         super.var_1d6 = super.var_1d6 + super.var_26a * var22;
         super.var_274 = super.var_274 + super.var_3b1 * var22;
         super.var_29a = super.var_29a + super.var_3ca * var22;
         super.var_2d0 = super.var_2d0 + super.var_3f6 * var22;
         super.var_328 = super.var_328 + super.var_44d * var22;
         super.var_383 = super.var_383 + super.var_460 * var22;
      }

      for (super.var_e8 = super.var_e8 < Config.sub_45d(this.var_58) ? super.var_e8 : Config.sub_45d(this.var_58);
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
         if (var39 < Config.sub_467(this.var_58)) {
            int var29 = Config.sub_467(this.var_58) - var39;
            var39 = Config.sub_467(this.var_58);
            var24 += var10 * var29;
            var25 += var11 * var29;
            var26 += var12 * var29;
            var27 += var20 * var29;
            var28 += var21 * var29;
         }

         if (var23 > Config.sub_48e(this.var_58)) {
            var23 = Config.sub_48e(this.var_58);
         }

         int var40 = super.var_41 + var39;

         for (int var30 = super.var_41 + var23; var40 < var30; var40++) {
            int var31 = var5 + ((var25 & var7) >>> var9) + ((var24 & var6) >>> var8);
            int var32 = var15 + ((var28 & var17) >>> var19) + ((var27 & var16) >>> var18);
            int var33 = var4[var31];
            int var34 = var26 >>> 16;
            int var35 = var14[var32];
            int var37 = ((var33 & 16711935) * var34 & -16711936) + ((var33 & 0xFF00) * var34 & 0xFF0000) >>> 8;
            int var36 = ((var37 & var35) << 1) + ((var37 ^ var35) & 16711422) & 16843008;
            var36 = (var36 >>> 8) + 8355711 ^ 8355711;
            var36 = var37 + var35 - var36 | var36;
            var1[var40] = var36 | 0xFF000000;
            var24 += var10;
            var25 += var11;
            var26 += var12;
            var27 += var20;
            var28 += var21;
         }

         super.var_8b++;
         super.var_41 = super.var_41 + Config.sub_3dd(this.var_58);
         super.var_189 = super.var_189 + super.var_236;
         super.var_1d6 = super.var_1d6 + super.var_26a;
         super.var_274 = super.var_274 + super.var_3b1;
         super.var_29a = super.var_29a + super.var_3ca;
         super.var_2d0 = super.var_2d0 + super.var_3f6;
         super.var_328 = super.var_328 + super.var_44d;
      }
   }
}
