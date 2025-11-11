package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_4c3 extends Class_eda {
   private final Class_6ed var_5d;

   public Class_4c3(Class_6ed var1) {
      super(var1);
      this.var_5d = var1;
   }

   public void sub_46() {
      int[] var1 = Class_6ed.sub_34c(this.var_5d);
      int var2 = Class_6ed.sub_397(this.var_5d);
      if (super.var_15e < Class_6ed.sub_41a(this.var_5d)) {
         int var3;
         if (super.var_170 < Class_6ed.sub_41a(this.var_5d)) {
            var3 = super.var_170 - super.var_15e;
            super.var_15e = super.var_170;
         } else {
            var3 = Class_6ed.sub_41a(this.var_5d) - super.var_15e;
            super.var_15e = Class_6ed.sub_41a(this.var_5d);
         }

         super.var_f1 = super.var_f1 + Class_6ed.sub_3dd(this.var_5d) * var3;
         super.var_1a2 = super.var_1a2 + super.var_201 * var3;
         super.var_1ac = super.var_1ac + super.var_24e * var3;
      }

      for (super.var_170 = super.var_170 < Class_6ed.sub_45d(this.var_5d) ? super.var_170 : Class_6ed.sub_45d(this.var_5d);
         super.var_15e < super.var_170;
         super.var_1ac = super.var_1ac + super.var_24e
      ) {
         int var7 = super.var_1a2 >> 16;
         int var4 = super.var_1ac >> 16;
         if (var7 < Class_6ed.sub_467(this.var_5d)) {
            var7 = Class_6ed.sub_467(this.var_5d);
         }

         if (var4 > Class_6ed.sub_48e(this.var_5d)) {
            var4 = Class_6ed.sub_48e(this.var_5d);
         }

         int var5 = super.var_f1 + var7;

         for (int var6 = super.var_f1 + var4; var5 < var6; var5++) {
            var1[var5] = var2;
         }

         super.var_15e++;
         super.var_f1 = super.var_f1 + Class_6ed.sub_3dd(this.var_5d);
         super.var_1a2 = super.var_1a2 + super.var_201;
      }
   }
}
