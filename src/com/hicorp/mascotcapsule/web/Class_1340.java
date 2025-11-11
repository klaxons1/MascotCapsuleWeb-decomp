package com.hicorp.mascotcapsule.web;

public final class Class_1340 extends Class_15d5 {
   private final Config var_30;

   public Class_1340(Config var1) {
      super(var1);
      this.var_30 = var1;
   }

   public void sub_22() {
      int[] var1 = Config.sub_34c(this.var_30);
      int var2 = (super.var_478 > 0 ? super.var_478 : -super.var_478) + (super.var_510 > 0 ? super.var_510 : -super.var_510) + 32768;
      int var3 = Config.sub_509(this.var_30).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var4 = Config.sub_509(this.var_30).sub_93();
      int var5 = Config.sub_509(this.var_30).sub_327(var3);
      int var6 = Config.sub_509(this.var_30).sub_3df(var3);
      int var7 = Config.sub_509(this.var_30).sub_418(var3);
      int var8 = Config.sub_509(this.var_30).sub_42b(var3);
      int var9 = Config.sub_509(this.var_30).sub_467(var3);
      int var10 = super.var_478 >> var3;
      int var11 = super.var_510 >> var3;
      int var12 = super.var_52a;
      var2 = (super.var_54a > 0 ? super.var_54a : -super.var_54a) + (super.var_55b > 0 ? super.var_55b : -super.var_55b) + 32768;
      int var13 = Config.sub_55c(this.var_30).sub_2cf(MatrixUtils.sub_30a(var2) - 17);
      int[] var14 = Config.sub_55c(this.var_30).sub_93();
      int var15 = Config.sub_55c(this.var_30).sub_327(var13);
      int var16 = Config.sub_55c(this.var_30).sub_3df(var13);
      int var17 = Config.sub_55c(this.var_30).sub_418(var13);
      int var18 = Config.sub_55c(this.var_30).sub_42b(var13);
      int var19 = Config.sub_55c(this.var_30).sub_467(var13);
      int var20 = super.var_54a >> var13;

      for (int var21 = super.var_55b >> var13; super.var_8b < super.var_e8; super.var_383 = super.var_383 + super.var_460) {
         int var22 = (super.var_189 >> 16) + super.var_41;
         int var23 = (super.var_1d6 >> 16) + super.var_41;
         int var24 = super.var_274 >> var3;
         int var25 = super.var_29a >> var3;
         int var26 = super.var_2d0;
         int var27 = super.var_328 >> var13;

         for (int var28 = super.var_383 >> var13; var22 < var23; var22++) {
            int var29 = var5 + ((var25 & var7) >>> var9) + ((var24 & var6) >>> var8);
            int var30 = var4[var29];
            if (var30 != -1) {
               int var31 = var15 + ((var28 & var17) >>> var19) + ((var27 & var16) >>> var18);
               int var32 = var1[var22];
               int var33 = var26 >>> 16;
               int var34 = var14[var31];
               int var37 = ((var30 & 16711935) * var33 & -16711936) + ((var30 & 0xFF00) * var33 & 0xFF0000) >>> 8;
               int var35 = ((var37 & var34) << 1) + ((var37 ^ var34) & 16711422) & 16843008;
               var35 = (var35 >>> 8) + 8355711 ^ 8355711;
               var35 = var37 + var34 - var35 | var35;
               int var36 = ((var35 & var32) << 1) + ((var35 ^ var32) & 16711422) & 16843008;
               var36 = (var36 >>> 8) + 8355711 ^ 8355711;
               var36 = var35 + var32 - var36 | var36;
               var1[var22] = var36 | 0xFF000000;
            }

            var24 += var10;
            var25 += var11;
            var26 += var12;
            var27 += var20;
            var28 += var21;
         }

         super.var_8b++;
         super.var_41 = super.var_41 + Config.sub_3dd(this.var_30);
         super.var_189 = super.var_189 + super.var_236;
         super.var_1d6 = super.var_1d6 + super.var_26a;
         super.var_274 = super.var_274 + super.var_3b1;
         super.var_29a = super.var_29a + super.var_3ca;
         super.var_2d0 = super.var_2d0 + super.var_3f6;
         super.var_328 = super.var_328 + super.var_44d;
      }
   }
}
