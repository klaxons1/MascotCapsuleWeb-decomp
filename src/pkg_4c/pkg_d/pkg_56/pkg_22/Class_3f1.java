package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_3f1 extends Class_d00 {
   private final Class_6ed var_ac;

   public Class_3f1(Class_6ed var1) {
      super(var1);
      this.var_ac = var1;
   }

   public void sub_21() {
      int[] var1 = Class_6ed.sub_34c(this.var_ac);
      int var2 = Class_6ed.sub_397(this.var_ac) & 16711935;
      int var3 = Class_6ed.sub_397(this.var_ac) & 0xFF00;
      int var4 = super.var_324;
      if (super.var_cc < Class_6ed.sub_41a(this.var_ac)) {
         int var5;
         if (super.var_137 < Class_6ed.sub_41a(this.var_ac)) {
            var5 = super.var_137 - super.var_cc;
            super.var_cc = super.var_137;
         } else {
            var5 = Class_6ed.sub_41a(this.var_ac) - super.var_cc;
            super.var_cc = Class_6ed.sub_41a(this.var_ac);
         }

         super.var_7c = super.var_7c + Class_6ed.sub_3dd(this.var_ac) * var5;
         super.var_15d = super.var_15d + super.var_1ff * var5;
         super.var_1c1 = super.var_1c1 + super.var_239 * var5;
         super.var_259 = super.var_259 + super.var_2a5 * var5;
      }

      for (super.var_137 = super.var_137 < Class_6ed.sub_45d(this.var_ac) ? super.var_137 : Class_6ed.sub_45d(this.var_ac);
         super.var_cc < super.var_137;
         super.var_259 = super.var_259 + super.var_2a5
      ) {
         int var12 = super.var_15d >> 16;
         int var6 = super.var_1c1 >> 16;
         int var7 = super.var_259;
         if (var12 < Class_6ed.sub_467(this.var_ac)) {
            int var8 = Class_6ed.sub_467(this.var_ac) - var12;
            var12 = Class_6ed.sub_467(this.var_ac);
            var7 += var4 * var8;
         }

         if (var6 > Class_6ed.sub_48e(this.var_ac)) {
            var6 = Class_6ed.sub_48e(this.var_ac);
         }

         int var13 = super.var_7c + var12;

         for (int var9 = super.var_7c + var6; var13 < var9; var13++) {
            int var10 = var7 >>> 16;
            int var11 = (var2 * var10 & -16711936) + (var3 * var10 & 0xFF0000) >>> 8;
            var1[var13] = var11 | 0xFF000000;
            var7 += var4;
         }

         super.var_cc++;
         super.var_7c = super.var_7c + Class_6ed.sub_3dd(this.var_ac);
         super.var_15d = super.var_15d + super.var_1ff;
         super.var_1c1 = super.var_1c1 + super.var_239;
      }
   }
}
