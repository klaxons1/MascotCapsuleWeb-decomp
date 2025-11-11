package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_3e4 extends Class_15c {
   private final Class_6ed var_3d;

   public Class_3e4(Class_6ed var1) {
      super(var1);
      this.var_3d = var1;
   }

   public void sub_26() {
      int[] var1 = Class_6ed.sub_34c(this.var_3d);
      int var2 = (super.var_2d1 > 0 ? super.var_2d1 : -super.var_2d1) + (super.var_319 > 0 ? super.var_319 : -super.var_319) + 32768;
      int var3 = Class_6ed.sub_509(this.var_3d).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_3d).sub_93();
      int var5 = Class_6ed.sub_509(this.var_3d).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_3d).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_3d).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_3d).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_3d).sub_467(var3);
      int var10 = super.var_2d1 >> var3;
      int var11 = super.var_319 >> var3;

      for (int var12 = super.var_339; super.var_6c < super.var_81; super.var_216 = super.var_216 + super.var_282) {
         int var13 = (super.var_db >> 16) + super.var_31;
         int var14 = (super.var_12b >> 16) + super.var_31;
         int var15 = super.var_1c8 >> var3;
         int var16 = super.var_205 >> var3;

         for (int var17 = super.var_216; var13 < var14; var13++) {
            int var18 = var5 + ((var16 & var7) >>> var9) + ((var15 & var6) >>> var8);
            int var19 = var4[var18];
            if (var19 != -1) {
               int var20 = var1[var13];
               int var21 = var17 >>> 16;
               int var23 = ((var19 & 16711935) * var21 & -16711936) + ((var19 & 0xFF00) * var21 & 0xFF0000) >>> 8;
               int var22 = ((var23 & var20) << 1) + ((var23 ^ var20) & 16711422) & 16843008;
               var22 = (var22 >>> 8) + 8355711 ^ 8355711;
               var22 = var23 + var20 - var22 | var22;
               var1[var13] = var22 | 0xFF000000;
            }

            var15 += var10;
            var16 += var11;
            var17 += var12;
         }

         super.var_6c++;
         super.var_31 = super.var_31 + Class_6ed.sub_3dd(this.var_3d);
         super.var_db = super.var_db + super.var_14b;
         super.var_12b = super.var_12b + super.var_18a;
         super.var_1c8 = super.var_1c8 + super.var_243;
         super.var_205 = super.var_205 + super.var_24d;
      }
   }
}
