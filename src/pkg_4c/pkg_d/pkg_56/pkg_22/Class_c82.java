package pkg_4c.pkg_d.pkg_56.pkg_22;

final class Class_c82 {
   protected int var_66;
   public Class_b42[] var_b5;
   private final Class_47d var_d1;

   private Class_c82(Class_47d var1) {
      this.var_d1 = var1;
      this.var_66 = 0;
      this.var_b5 = null;
   }

   public int sub_2c(int var1, int var2) {
      Class_8ed.sub_7d(var2 > 0);
      this.var_66 = var2;
      this.var_b5 = new Class_b42[this.var_66];

      for (int var3 = 0; var3 < this.var_66; var3++) {
         this.var_b5[var3] = this.var_d1.var_492[var1 + var3];
      }

      return var1 + var2;
   }

   final int sub_84() {
      return this.var_66;
   }

   float sub_d5(float var1) {
      Class_8ed.sub_7d(this.var_66 > 0);
      int var4;
      if (var1 < 0.0F) {
         var1 = 0.0F;
         var4 = 0;
      } else {
         var4 = (int)var1;
      }

      int var3 = 1;

      while (var3 < this.var_66 && var4 >= this.var_b5[var3].var_65) {
         var3++;
      }

      int var2 = var3 - 1;
      float var5;
      if (var3 < this.var_66) {
         float var6 = (var1 - this.var_b5[var2].var_65) / this.var_b5[var2].var_a3;
         var5 = this.var_b5[var2].var_eb + var6 * this.var_b5[var2].var_10a;
      } else {
         var5 = this.var_b5[var2].var_eb;
      }

      return var5;
   }

   Class_c82(Class_47d var1, Class_15bc var2) {
      this(var1);
   }
}
