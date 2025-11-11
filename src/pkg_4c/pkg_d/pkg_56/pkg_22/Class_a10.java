package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_a10 extends Class_15d5 {
   private final Class_6ed var_3e;

   public Class_a10(Class_6ed var1) {
      super(var1);
      this.var_3e = var1;
   }

   public void sub_22() {
      int[] var1 = Class_6ed.sub_34c(this.var_3e);
      int var2 = (super.var_478 > 0 ? super.var_478 : -super.var_478) + (super.var_510 > 0 ? super.var_510 : -super.var_510) + 32768;
      int var3 = Class_6ed.sub_509(this.var_3e).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_3e).sub_93();
      int var5 = Class_6ed.sub_509(this.var_3e).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_3e).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_3e).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_3e).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_3e).sub_467(var3);
      int var10 = super.var_478 >> var3;
      int var11 = super.var_510 >> var3;
      int var12 = super.var_52a;
      var2 = (super.var_54a > 0 ? super.var_54a : -super.var_54a) + (super.var_55b > 0 ? super.var_55b : -super.var_55b) + 32768;
      int var13 = Class_6ed.sub_55c(this.var_3e).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var14 = Class_6ed.sub_55c(this.var_3e).sub_93();
      int var15 = Class_6ed.sub_55c(this.var_3e).sub_327(var13);
      int var16 = Class_6ed.sub_55c(this.var_3e).sub_3df(var13);
      int var17 = Class_6ed.sub_55c(this.var_3e).sub_418(var13);
      int var18 = Class_6ed.sub_55c(this.var_3e).sub_42b(var13);
      int var19 = Class_6ed.sub_55c(this.var_3e).sub_467(var13);
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
            int var30 = var15 + ((var28 & var17) >>> var19) + ((var27 & var16) >>> var18);
            int var31 = var4[var29];
            int var32 = var1[var22];
            int var33 = var26 >>> 16;
            int var34 = var14[var30];
            int var36 = ((var31 & 16711935) * var33 & -16711936) + ((var31 & 0xFF00) * var33 & 0xFF0000) >>> 8;
            int var35 = ((var36 & var34) << 1) + ((var36 ^ var34) & 16711422) & 16843008;
            var35 = (var35 >>> 8) + 8355711 ^ 8355711;
            var35 = var36 + var34 - var35 | var35;
            var35 = (var35 & 16711422) + (var32 & 16711422) >>> 1;
            var1[var22] = var35 | 0xFF000000;
            var24 += var10;
            var25 += var11;
            var26 += var12;
            var27 += var20;
            var28 += var21;
         }

         super.var_8b++;
         super.var_41 = super.var_41 + Class_6ed.sub_3dd(this.var_3e);
         super.var_189 = super.var_189 + super.var_236;
         super.var_1d6 = super.var_1d6 + super.var_26a;
         super.var_274 = super.var_274 + super.var_3b1;
         super.var_29a = super.var_29a + super.var_3ca;
         super.var_2d0 = super.var_2d0 + super.var_3f6;
         super.var_328 = super.var_328 + super.var_44d;
      }
   }
}
