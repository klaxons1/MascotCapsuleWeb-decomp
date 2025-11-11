package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_111 extends Class_1279 {
   private final Class_6ed var_5b;

   public Class_111(Class_6ed var1) {
      super(var1);
      this.var_5b = var1;
   }

   public void sub_1c() {
      int[] var1 = Class_6ed.sub_34c(this.var_5b);
      int var2 = (super.var_491 > 0 ? super.var_491 : -super.var_491) + (super.var_49f > 0 ? super.var_49f : -super.var_49f) + 32768;
      int var3 = Class_6ed.sub_509(this.var_5b).sub_2cf(Class_4ce.sub_30a(var2) - 17);
      int[] var4 = Class_6ed.sub_509(this.var_5b).sub_93();
      int var5 = Class_6ed.sub_509(this.var_5b).sub_327(var3);
      int var6 = Class_6ed.sub_509(this.var_5b).sub_3df(var3);
      int var7 = Class_6ed.sub_509(this.var_5b).sub_418(var3);
      int var8 = Class_6ed.sub_509(this.var_5b).sub_42b(var3);
      int var9 = Class_6ed.sub_509(this.var_5b).sub_467(var3);
      int var10 = super.var_491 >> var3;
      int var11 = super.var_49f >> var3;
      if (super.var_139 < Class_6ed.sub_41a(this.var_5b)) {
         int var12;
         if (super.var_21c < Class_6ed.sub_41a(this.var_5b)) {
            var12 = super.var_21c - super.var_139;
            super.var_139 = super.var_21c;
         } else {
            var12 = Class_6ed.sub_41a(this.var_5b) - super.var_139;
            super.var_139 = Class_6ed.sub_41a(this.var_5b);
         }

         super.var_c4 = super.var_c4 + Class_6ed.sub_3dd(this.var_5b) * var12;
         super.var_279 = super.var_279 + super.var_2fe * var12;
         super.var_2c3 = super.var_2c3 + super.var_326 * var12;
         super.var_368 = super.var_368 + super.var_40c * var12;
         super.var_3c2 = super.var_3c2 + super.var_438 * var12;
      }

      for (super.var_21c = super.var_21c < Class_6ed.sub_45d(this.var_5b) ? super.var_21c : Class_6ed.sub_45d(this.var_5b);
         super.var_139 < super.var_21c;
         super.var_3c2 = super.var_3c2 + super.var_438
      ) {
         int var20 = super.var_279 >> 16;
         int var13 = super.var_2c3 >> 16;
         int var14 = super.var_368 >> var3;
         int var15 = super.var_3c2 >> var3;
         if (var20 < Class_6ed.sub_467(this.var_5b)) {
            int var16 = Class_6ed.sub_467(this.var_5b) - var20;
            var20 = Class_6ed.sub_467(this.var_5b);
            var14 += var10 * var16;
            var15 += var11 * var16;
         }

         if (var13 > Class_6ed.sub_48e(this.var_5b)) {
            var13 = Class_6ed.sub_48e(this.var_5b);
         }

         int var21 = super.var_c4 + var20;

         for (int var17 = super.var_c4 + var13; var21 < var17; var21++) {
            int var18 = var5 + ((var15 & var7) >>> var9) + ((var14 & var6) >>> var8);
            int var19 = var4[var18];
            if (var19 != -1) {
               var1[var21] = var19;
            }

            var14 += var10;
            var15 += var11;
         }

         super.var_139++;
         super.var_c4 = super.var_c4 + Class_6ed.sub_3dd(this.var_5b);
         super.var_279 = super.var_279 + super.var_2fe;
         super.var_2c3 = super.var_2c3 + super.var_326;
         super.var_368 = super.var_368 + super.var_40c;
      }
   }
}
