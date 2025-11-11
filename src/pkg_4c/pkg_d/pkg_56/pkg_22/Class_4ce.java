package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_4ce {
   private static final double var_44 = Math.PI;
   private static final Class_3d2 var_76 = new Class_3d2(1.0F, 0.0F, 0.0F);
   private static final Class_3d2 var_b7 = new Class_3d2(0.0F, 1.0F, 0.0F);
   private static final int[] var_d5 = new int[]{4, 3, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};

   public static void sub_2e(float var0, Class_757 var1) {
      var1.var_13 = var1.var_138 = var1.var_27d = var0;
      var1.var_ad = var1.var_c2 = var1.var_e7 = var1.var_191 = var1.var_211 = var1.var_263 = 0.0F;
   }

   public static void sub_3f(float var0, Class_757 var1) {
      float var2 = (float)Math.cos(var0);
      float var3 = (float)Math.sin(var0);
      var1.var_13 = 1.0F;
      var1.var_ad = 0.0F;
      var1.var_c2 = 0.0F;
      var1.var_e7 = 0.0F;
      var1.var_138 = var2;
      var1.var_191 = -var3;
      var1.var_211 = 0.0F;
      var1.var_263 = var3;
      var1.var_27d = var2;
   }

   public static void sub_92(float var0, Class_757 var1) {
      float var2 = (float)Math.cos(var0);
      float var3 = (float)Math.sin(var0);
      var1.var_13 = var2;
      var1.var_ad = 0.0F;
      var1.var_c2 = var3;
      var1.var_e7 = 0.0F;
      var1.var_138 = 1.0F;
      var1.var_191 = 0.0F;
      var1.var_211 = -var3;
      var1.var_263 = 0.0F;
      var1.var_27d = var2;
   }

   public static void sub_f2(float var0, Class_757 var1) {
      float var2 = (float)Math.cos(var0);
      float var3 = (float)Math.sin(var0);
      var1.var_13 = var2;
      var1.var_ad = -var3;
      var1.var_c2 = 0.0F;
      var1.var_e7 = var3;
      var1.var_138 = var2;
      var1.var_191 = 0.0F;
      var1.var_211 = 0.0F;
      var1.var_263 = 0.0F;
      var1.var_27d = 1.0F;
   }

   public static void sub_135(Class_3d2 var0, float var1, Class_757 var2) {
      float var3 = (float)Math.cos(var1);
      float var4 = (float)Math.sin(var1);
      float var5 = var0.var_3f;
      float var6 = var0.var_93;
      float var7 = var0.var_d6;
      float var8 = var5 * var5;
      float var9 = var6 * var6;
      float var10 = var7 * var7;
      float var11 = var5 * var6;
      float var12 = var5 * var7;
      float var13 = var5 * var4;
      float var14 = var6 * var7;
      float var15 = var6 * var4;
      float var16 = var7 * var4;
      float var17 = 1.0F - var3;
      var2.var_13 = var8 * var17 + var3;
      var2.var_ad = var11 * var17 - var16;
      var2.var_c2 = var12 * var17 + var15;
      var2.var_e7 = var11 * var17 + var16;
      var2.var_138 = var9 * var17 + var3;
      var2.var_191 = var14 * var17 - var13;
      var2.var_211 = var12 * var17 - var15;
      var2.var_263 = var14 * var17 + var13;
      var2.var_27d = var10 * var17 + var3;
   }

   public static final void sub_181(Class_3d2 var0, Class_3d2 var1, Class_757 var2) {
      Class_3d2 var4 = new Class_3d2();
      float var3 = sub_27d(var0, var1);
      if (var3 < 0.001F) {
         var2.sub_139();
      } else {
         if (var3 > 3.1405928F) {
            if (Math.abs(1.0 - Math.abs(var0.var_3f)) < 0.001) {
               var4.sub_399(var0, var_b7);
            } else {
               var4.sub_399(var0, var_76);
            }
         } else {
            var4.sub_399(var0, var1);
         }

         var4.sub_47c();
         sub_135(var4, var3, var2);
      }
   }

   public static final Class_757 sub_18c(Class_3d2 var0, Class_3d2 var1) {
      return sub_1aa(var0, var1, 0.0F);
   }

   public static final Class_757 sub_1aa(Class_3d2 var0, Class_3d2 var1, float var2) {
      Class_8ed.sub_7d(var0 != var1);
      Class_3d2 var3 = new Class_3d2();
      var3.sub_1e0(var1, var0);
      var3.sub_47c();
      float var13 = var3.var_3f;
      float var14 = var3.var_93;
      float var15 = var3.var_d6;
      float var17 = var13 * var13 + var15 * var15;
      float var4;
      float var5;
      float var6;
      float var7;
      float var8;
      float var9;
      float var10;
      float var11;
      float var12;
      if (var17 > 1.0E-5F) {
         float var16 = 1.0F / (float)Math.sqrt(var17);
         var4 = -var16 * var15;
         var5 = var16 * var13 * var14;
         var6 = var13;
         var7 = 0.0F;
         var8 = -var16 * var17;
         var9 = var14;
         var10 = var16 * var13;
         var11 = var16 * var14 * var15;
         var12 = var15;
      } else {
         var4 = -1.0F;
         var5 = 0.0F;
         var6 = var13;
         var7 = 0.0F;
         var8 = 0.0F;
         var9 = var14;
         var10 = 0.0F;
         var11 = var14 < 0.0F ? -1.0F : 1.0F;
         var12 = var15;
      }

      Class_757 var18 = new Class_757();
      var18.sub_d5(var4, var5, var6, var7, var8, var9, var10, var11, var12);
      if (var2 != 0.0F) {
         Class_757 var19 = new Class_757();
         sub_135(var3, var2, var19);
         var18.sub_2b3(var19, var18);
      }

      var18.var_d7 = var0.var_3f;
      var18.var_1ce = var0.var_93;
      var18.var_2ce = var0.var_d6;
      return var18;
   }

   public static final float sub_1d7(float var0) {
      return (float)(var0 * Math.PI / 180.0);
   }

   private static final float sub_227(float var0) {
      if (var0 > 1.0F) {
         var0 = 1.0F;
      } else if (var0 < -1.0F) {
         var0 = -1.0F;
      }

      return var0;
   }

   private static final float sub_27d(Class_3d2 var0, Class_3d2 var1) {
      return (float)Math.acos(sub_227(var0.sub_34f(var1)));
   }

   public static int sub_2ab(int var0) {
      if ((var0 & -65536) != 0) {
         if ((var0 & 0xFF000000) != 0) {
            return (var0 & -268435456) != 0 ? var_d5[var0 >>> 28] : var_d5[var0 >>> 24] + 4;
         } else {
            return (var0 & 15728640) != 0 ? var_d5[var0 >>> 20] + 8 : var_d5[var0 >>> 16] + 12;
         }
      } else if ((var0 & 0xFF00) != 0) {
         return (var0 & 61440) != 0 ? var_d5[var0 >>> 12] + 16 : var_d5[var0 >>> 8] + 20;
      } else {
         return (var0 & 240) != 0 ? var_d5[var0 >>> 4] + 24 : var_d5[var0] + 28;
      }
   }

   public static int sub_30a(int var0) {
      if (var0 != 0L) {
         int var1 = 31 - sub_2ab(var0);
         return var0 == 1 << var1 ? var1 : var1 + 1;
      } else {
         return -1;
      }
   }

   public static int sub_33c(long var0) {
      return (var0 & -4294967296L) != 0L ? sub_2ab((int)(var0 >>> 32)) : sub_2ab((int)var0) + 32;
   }

   public static int sub_3a0(long var0) {
      if (var0 != 0L) {
         int var2 = 63 - sub_33c(var0);
         return var0 == 1L << var2 ? var2 : var2 + 1;
      } else {
         return -1;
      }
   }
}
