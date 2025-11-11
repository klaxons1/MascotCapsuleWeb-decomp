package pkg_4c.pkg_d.pkg_56.pkg_22;

import java.io.InputStream;

public final class Class_89e implements Class_41b {
   private static final int var_54 = 0;
   private static final int var_f5 = 1;
   private static final int var_159 = 2;
   private static final int var_174 = 3;
   private static final int var_1c6 = 12;
   private static final int var_1ec = 40;
   private static final int var_24b = 64;
   private int[] var_287 = null;
   private Class_613 var_2af = null;
   private Class_517 var_2c8 = null;
   private int var_2e4;
   private int var_317;
   private int var_342;

   public boolean sub_10(InputStream var1, Class_517 var2) {
      if (var1 == null) {
         return false;
      } else {
         this.var_2c8 = var2;
         this.var_2af = new Class_613(var1);
         int var3 = this.var_2af.sub_16f();
         int var4 = this.var_2af.sub_16f();
         if (var3 == 66 && var4 == 77) {
            this.var_2af.sub_11c(8);
            int var10 = this.var_2af.sub_77();
            int var11 = this.var_2af.sub_77();
            int var5;
            int var6;
            short var7;
            int var8;
            int var9;
            if (var11 != 40 && var11 != 64) {
               var5 = this.var_2af.sub_3f();
               var6 = this.var_2af.sub_3f();
               if (this.var_2af.sub_3f() != 1) {
                  return false;
               }

               var7 = this.var_2af.sub_3f();
               var8 = 0;
               var9 = 0;
            } else {
               var5 = this.var_2af.sub_77();
               var6 = this.var_2af.sub_77();
               if (this.var_2af.sub_3f() != 1) {
                  return false;
               }

               var7 = this.var_2af.sub_3f();
               int var12 = this.var_2af.sub_77();
               if (var12 != 0 && var12 != 3) {
                  return false;
               }

               this.var_2af.sub_11c(12);
               var8 = this.var_2af.sub_77();
               this.var_2af.sub_11c(4);
               switch (var7) {
                  case 16:
                     if (var12 == 0) {
                        this.var_2e4 = 31744;
                        this.var_317 = 992;
                        this.var_342 = 31;
                        this.var_2af.sub_11c(var11 - 40);
                     } else {
                        this.var_2e4 = this.var_2af.sub_77();
                        this.var_317 = this.var_2af.sub_77();
                        this.var_342 = this.var_2af.sub_77();
                        this.var_2af.sub_11c(var11 - 40);
                     }
                     break;
                  case 24:
                     if (var12 != 0) {
                        return false;
                     }

                     this.var_2af.sub_11c(var11 - 40);
                     break;
                  case 32:
                     if (var12 != 3) {
                        return false;
                     }

                     this.var_2e4 = this.var_2af.sub_77();
                     this.var_317 = this.var_2af.sub_77();
                     this.var_342 = this.var_2af.sub_77();
                     this.var_2af.sub_11c(var11 - 40);
               }

               var9 = var10 - (var11 + 14);
            }

            if (var5 >= 0 && var6 > 0 && var5 <= 8192 && var6 <= 8192 && (var7 == 1 || var7 == 4 || var7 == 8 || var7 == 16 || var7 == 24)) {
               if (var8 >= 0 && var8 <= 1 << var7) {
                  if (var8 == 0) {
                     var8 = 1 << var7;
                  }

                  if (var7 <= 8) {
                     int var18 = 1 << var7;
                     this.var_287 = new int[var18];
                     boolean var13 = var11 != 12;

                     for (int var14 = 0; var14 < var8; var14++) {
                        int var15 = this.var_2af.sub_16f();
                        int var16 = this.var_2af.sub_16f();
                        int var17 = this.var_2af.sub_16f();
                        this.var_287[var14] = 0xFF000000 | var17 << 16 | var16 << 8 | var15;
                        if (var13) {
                           this.var_2af.sub_16f();
                        }
                     }

                     for (int var19 = var8; var19 < var18; var19++) {
                        this.var_287[var19] = -1;
                     }

                     var9 -= (3 + (var13 ? 1 : 0)) * var8;
                  }

                  if (var11 != 12 && var9 > 0) {
                     this.var_2af.sub_11c(var9);
                  }

                  var2.sub_38(var5, var6);
                  switch (var7) {
                     case 4:
                        this.sub_42();
                        break;
                     case 8:
                        this.sub_92();
                        break;
                     case 16:
                        this.sub_b2();
                        break;
                     case 24:
                        this.sub_10c();
                        break;
                     default:
                        return false;
                  }

                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private void sub_42() {
      int var1 = this.var_2c8.sub_10a();
      int var2 = this.var_2c8.sub_11a();
      int var3 = 1 << this.var_2c8.sub_143();
      int var4 = (var1 + 1) / 2;
      int var5 = 4 - (var4 & 3) & 3;
      int[] var6 = this.var_2c8.sub_93();
      int var7 = var3 * (var2 - 1);

      for (int var8 = var2; var8 > 0; var8--) {
         int var9 = var7;

         for (int var10 = var4; var10 > 0; var9 += 2) {
            int var11 = this.var_2af.sub_16f();
            var6[var9 + 0] = this.var_287[var11 >>> 4];
            var6[var9 + 1] = this.var_287[var11 & 15];
            var10--;
         }

         this.var_2af.sub_11c(var5);
         var7 -= var3;
      }
   }

   private void sub_92() {
      int var1 = this.var_2c8.sub_10a();
      int var2 = this.var_2c8.sub_11a();
      int var3 = 1 << this.var_2c8.sub_143();
      int var4 = 4 - (var1 & 3) & 3;
      int[] var5 = this.var_2c8.sub_93();
      int var6 = var3 * (var2 - 1);

      for (int var7 = var2; var7 > 0; var7--) {
         int var8 = var6;

         for (int var9 = var1; var9 > 0; var8++) {
            int var10 = this.var_2af.sub_16f();
            var5[var8] = this.var_287[var10];
            var9--;
         }

         this.var_2af.sub_11c(var4);
         var6 -= var3;
      }
   }

   private void sub_b2() {
      int var1 = this.var_2c8.sub_10a();
      int var2 = this.var_2c8.sub_11a();
      int var3 = 1 << this.var_2c8.sub_143();
      int var4 = this.var_2c8.sub_10a() * 2;
      int var5 = 4 - (var4 & 3) & 3;
      int[] var6 = this.var_2c8.sub_93();
      int var7 = var3 * (var2 - 1);

      for (int var8 = var2; var8 > 0; var8--) {
         int var9 = var7;

         for (int var10 = var1; var10 > 0; var9++) {
            int var11 = this.var_2af.sub_192();
            int var12 = (var11 & this.var_2e4) * 255 / this.var_2e4;
            int var13 = (var11 & this.var_317) * 255 / this.var_317;
            int var14 = (var11 & this.var_342) * 255 / this.var_342;
            var6[var9] = 0xFF000000 | var12 << 16 | var13 << 8 | var14;
            var10--;
         }

         this.var_2af.sub_11c(var5);
         var7 -= var3;
      }
   }

   private void sub_10c() {
      int var1 = this.var_2c8.sub_10a();
      int var2 = this.var_2c8.sub_11a();
      int var3 = 1 << this.var_2c8.sub_143();
      int var4 = this.var_2c8.sub_10a() * 3;
      int var5 = 4 - (var4 & 3) & 3;
      int[] var6 = this.var_2c8.sub_93();
      int var7 = var3 * (var2 - 1);

      for (int var8 = var2; var8 > 0; var8--) {
         int var9 = var7;

         for (int var10 = var1; var10 > 0; var9++) {
            int var11 = this.var_2af.sub_16f();
            int var12 = this.var_2af.sub_16f();
            int var13 = this.var_2af.sub_16f();
            var6[var9] = 0xFF000000 | var13 << 16 | var12 << 8 | var11;
            var10--;
         }

         this.var_2af.sub_11c(var5);
         var7 -= var3;
      }
   }
}
