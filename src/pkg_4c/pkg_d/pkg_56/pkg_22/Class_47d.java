package pkg_4c.pkg_d.pkg_56.pkg_22;

import java.io.InputStream;

public final class Class_47d {
   private static final Class_3d2 var_62 = new Class_3d2(1.0F, 1.0F, 1.0F);
   private static final int var_bb = 0;
   private static final int var_105 = 1;
   private static final int var_164 = 2;
   private static final int var_1c3 = 3;
   private static final int var_217 = 4;
   private static final int var_262 = 5;
   private static final int var_26d = 6;
   private static final int var_2a6 = 7;
   private static final int var_2b1 = 8;
   private static final int var_2e5 = 9;
   private static final int var_33e = 10;
   protected int var_360 = 0;
   protected int var_395 = 0;
   protected Class_ae3[] var_3c7 = null;
   protected int var_3e4 = 0;
   protected Class_b78[] var_409 = null;
   protected int var_450 = 0;
   protected Class_b42[] var_492 = null;
   public Class_77c var_4ab = null;

   protected void sub_43() {
      this.var_360 = 0;
      this.var_395 = 0;
      this.var_3c7 = null;
      this.var_3e4 = 0;
      this.var_409 = null;
      this.var_450 = 0;
      this.var_492 = null;
      this.var_4ab = null;
   }

   protected void sub_a5() {
      this.var_450 = 0;
      this.var_492 = null;

      for (int var1 = 0; var1 < this.var_3e4; var1++) {
         int var2 = 0;

         while (var2 < this.var_395 && !this.var_3c7[var2].var_51.equalsIgnoreCase(this.var_4ab.sub_126(var1).sub_9e())) {
            var2++;
         }

         if (var2 != this.var_395) {
            for (int var3 = 0; var3 <= 9; var3++) {
               this.var_450 = this.var_450 + this.var_3c7[var2].var_2b0[var3].sub_41();
            }
         } else {
            this.var_450 += 10;
         }
      }

      this.var_492 = new Class_b42[this.var_450];

      for (int var4 = 0; var4 < this.var_450; var4++) {
         this.var_492[var4] = new Class_b42(null);
      }
   }

   public boolean sub_f8(InputStream var1) {
      this.sub_43();
      if (var1 == null) {
         return true;
      } else {
         Class_613 var2 = new Class_613(var1);
         byte var11 = var2.sub_13();
         byte var12 = var2.sub_13();
         byte var13 = var2.sub_13();
         byte var14 = var2.sub_13();
         if (var11 == 72 && var12 == 73 && var13 == 74 && var14 == 84) {
            int var9 = var2.sub_77();
            if (var9 != 1) {
               return false;
            } else {
               var2.sub_11c(12);
               byte[] var15 = new byte[64];

               for (int var3 = 0; var3 < 64; var3++) {
                  var15[var3] = var2.sub_13();
               }

               short var10 = var2.sub_3f();
               this.var_395 = var2.sub_3f();
               this.var_360 = var10 < 1 ? 0 : var10 - 1;
               this.var_3c7 = new Class_ae3[this.var_395];

               for (int var16 = 0; var16 < this.var_395; var16++) {
                  this.var_3c7[var16] = new Class_ae3();
                  this.var_3c7[var16].var_51 = var2.sub_1a5();
                  this.var_3c7[var16].var_109.var_3f = var2.sub_de();
                  this.var_3c7[var16].var_109.var_93 = var2.sub_de();
                  this.var_3c7[var16].var_109.var_d6 = var2.sub_de();
                  this.var_3c7[var16].var_209.var_3f = var2.sub_de();
                  this.var_3c7[var16].var_209.var_93 = var2.sub_de();
                  this.var_3c7[var16].var_209.var_d6 = var2.sub_de();
                  this.var_3c7[var16].var_241.var_3f = var2.sub_de();
                  this.var_3c7[var16].var_241.var_93 = var2.sub_de();
                  this.var_3c7[var16].var_241.var_d6 = var2.sub_de();
                  this.var_3c7[var16].var_268.var_3f = var2.sub_de();
                  this.var_3c7[var16].var_268.var_93 = var2.sub_de();
                  this.var_3c7[var16].var_268.var_d6 = var2.sub_de();

                  for (int var4 = 0; var4 < 10; var4++) {
                     short var6 = var2.sub_3f();
                     this.var_3c7[var16].var_2b0[var4].sub_9a(var6);

                     for (int var5 = 0; var5 < var6; var5++) {
                        short var7 = var2.sub_3f();
                        this.var_3c7[var16].var_2b0[var4].var_ce[var5].var_2e = var7;
                        float var8 = var2.sub_de();
                        this.var_3c7[var16].var_2b0[var4].var_ce[var5].var_92 = var8;
                     }
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      }
   }

   public int sub_122(Class_77c var1) {
      Class_8ed.sub_7d(var1 != null);
      Class_8ed.sub_7d(var1.sub_2ef());
      if (var1 == null) {
         return 1;
      } else if (!var1.sub_2ef()) {
         return 1;
      } else {
         this.var_4ab = var1;
         this.var_3e4 = var1.sub_85();
         this.var_409 = new Class_b78[this.var_3e4];

         for (int var2 = 0; var2 < this.var_3e4; var2++) {
            this.var_409[var2] = new Class_b78();

            for (int var3 = 0; var3 < 10; var3++) {
               this.var_409[var2].var_1a0[var3] = new Class_c82(this, null);
            }
         }

         this.sub_a5();
         int var13 = 0;

         for (int var19 = 0; var19 < this.var_3e4; var19++) {
            Class_b78 var14 = this.var_409[var19];
            int var5 = 0;

            while (var5 < this.var_395 && !this.var_3c7[var5].var_51.equalsIgnoreCase(var1.sub_126(var19).sub_9e())) {
               var5++;
            }

            var14.var_ff = new Class_757();
            if (var5 == this.var_395) {
               for (int var24 = 0; var24 <= 9; var24++) {
                  Class_c82 var27 = var14.var_1a0[var24];
                  var13 = var27.sub_2c(var13, 1);
                  var27.var_b5[0].var_65 = 0;
                  var27.var_b5[0].var_a3 = 0.0F;
                  var27.var_b5[0].var_eb = 0.0F;
                  var27.var_b5[0].var_10a = 0.0F;
               }

               var14.var_1a0[3].var_b5[0].var_eb = 1.0F;
               var14.var_1a0[4].var_b5[0].var_eb = 1.0F;
               var14.var_1a0[5].var_b5[0].var_eb = 1.0F;
               var14.var_1a0[8].var_b5[0].var_eb = 1.0F;
               var14.var_24 = true;
               var14.var_ff.sub_139();
            } else {
               Class_ae3 var15 = this.var_3c7[var5];
               var14.var_127 = var1.sub_126(var19).sub_d3();
               var14.var_14a = sub_2c1(var1, var1.sub_126(var19), var15);
               boolean var16 = false;

               for (int var4 = 0; var4 <= 9; var4++) {
                  float var17 = Float.MAX_VALUE;
                  float var18 = -Float.MAX_VALUE;
                  Class_c25 var11 = var15.var_2b0[var4];
                  Class_c82 var12 = var14.var_1a0[var4];
                  int var6 = var11.sub_41();
                  var13 = var12.sub_2c(var13, var6);

                  for (int var20 = 0; var20 < var6; var20++) {
                     int var7 = var11.var_ce[var20].var_2e;
                     float var8 = var20 == var6 - 1 ? 0.0F : var11.var_ce[var20 + 1].var_2e - var7;
                     float var9 = var11.var_ce[var20].var_92;
                     float var10 = var20 == var6 - 1 ? 0.0F : var11.var_ce[var20 + 1].var_92 - var9;
                     var12.var_b5[var20].var_65 = var7;
                     var12.var_b5[var20].var_a3 = var8;
                     var12.var_b5[var20].var_eb = var9;
                     var12.var_b5[var20].var_10a = var10;
                     var17 = var17 > var9 ? var9 : var17;
                     var18 = var18 < var9 ? var9 : var18;
                  }

                  if (var18 - var17 > 1.0E-5F) {
                     var16 = true;
                  }
               }

               for (int var23 = 3; var23 <= 5; var23++) {
                  Class_c82 var25 = var14.var_1a0[var23];

                  for (int var21 = 0; var21 < var25.sub_84(); var21++) {
                     var25.var_b5[var21].var_eb /= 100.0F;
                     var25.var_b5[var21].var_10a /= 100.0F;
                  }
               }

               Class_c82 var26 = var14.var_1a0[9];

               for (int var22 = 0; var22 < var26.sub_84(); var22++) {
                  var26.var_b5[var22].var_eb = Class_4ce.sub_1d7(var26.var_b5[var22].var_eb);
                  var26.var_b5[var22].var_10a = Class_4ce.sub_1d7(var26.var_b5[var22].var_10a);
               }

               if (!var16) {
                  this.sub_1bc(var19, 0.0F, var14.var_ff);
                  var14.var_24 = true;
               }
            }
         }

         this.var_395 = 0;
         this.var_3c7 = null;
         Class_8ed.sub_7d(var13 == this.var_450);
         return 0;
      }
   }

   public void sub_161(Class_47d var1, float var2, Class_47d var3, float var4, int var5) {
      Class_8ed.sub_7d(var5 >= 2);
      Class_8ed.sub_7d(this != var1 && this != var3);
      Class_8ed.sub_7d(var1.var_3e4 == var3.var_3e4);
      float[] var8 = new float[10];
      float[] var9 = new float[10];
      Class_3d2 var10 = new Class_3d2();
      this.sub_43();
      this.var_360 = var5 - 1;
      this.var_3e4 = var1.var_3e4;
      this.var_409 = new Class_b78[this.var_3e4];
      this.var_450 = 20 * this.var_3e4;
      this.var_492 = new Class_b42[this.var_450];
      int var12 = 0;

      for (int var6 = 0; var6 < this.var_450; var6++) {
         this.var_492[var6] = new Class_b42(null);
      }

      for (int var16 = 0; var16 < this.var_3e4; var16++) {
         this.var_409[var16] = new Class_b78();
         Class_b78 var13 = this.var_409[var16];
         Class_b78 var14 = var1.var_409[var16];
         Class_b78 var15 = var3.var_409[var16];
         var14.sub_56(var2, var8);
         var15.sub_56(var4, var9);
         var10.sub_7a(var8[6], var8[7], var8[8]);
         var10.sub_47c();
         var8[6] = var10.var_3f;
         var8[7] = var10.var_93;
         var8[8] = var10.var_d6;
         var10.sub_7a(var9[6], var9[7], var9[8]);
         var10.sub_47c();
         var9[6] = var10.var_3f;
         var9[7] = var10.var_93;
         var9[8] = var10.var_d6;

         for (int var7 = 0; var7 < 10; var7++) {
            Class_c82 var11 = var13.var_1a0[var7];
            var12 = var11.sub_2c(var12, 2);
            var11.var_b5[0].var_65 = 0;
            var11.var_b5[0].var_a3 = var5 - 1;
            var11.var_b5[0].var_eb = var8[var7];
            var11.var_b5[0].var_10a = var9[var7] - var8[var7];
            var11.var_b5[1].var_65 = var5 - 1;
            var11.var_b5[1].var_a3 = 0.0F;
            var11.var_b5[1].var_eb = var9[var7];
            var11.var_b5[1].var_10a = 0.0F;
         }
      }

      Class_8ed.sub_7d(var12 == this.var_450);
   }

   public void sub_1bc(int var1, float var2, Class_757 var3) {
      Class_8ed.sub_7d(var1 < this.var_3e4);
      Class_b78 var10 = this.var_409[var1];
      if (var10.var_24) {
         var3.sub_37(var10.var_ff);
      } else {
         float[] var12 = new float[10];
         int var8;
         if (var2 < 0.0F) {
            var2 = 0.0F;
            var8 = 0;
         } else {
            var8 = (int)var2;
         }

         for (int var4 = 0; var4 < 10; var4++) {
            int var7 = var10.var_1a0[var4].sub_84();
            Class_b42[] var11 = var10.var_1a0[var4].var_b5;
            int var6 = 1;

            while (var6 < var7 && var8 >= var11[var6].var_65) {
               var6++;
            }

            int var5 = var6 - 1;
            if (var6 < var7) {
               float var9 = (var2 - var11[var5].var_65) / var11[var5].var_a3;
               var12[var4] = var11[var5].var_eb + var9 * var11[var5].var_10a;
            } else {
               var12[var4] = var11[var5].var_eb;
            }
         }

         sub_307(var12, var3);
         var3.sub_2b3(var10.var_14a, var3);
         var3.sub_311(var10.var_127);
      }
   }

   public void sub_208(float var1) {
      Class_757 var2 = new Class_757();

      for (int var3 = 0; var3 < this.var_3e4; var3++) {
         this.sub_1bc(var3, var1, var2);
         this.var_4ab.sub_126(var3).sub_d7(var2);
      }
   }

   public final int sub_23d() {
      return this.var_360;
   }

   public final int sub_267() {
      return this.var_3e4;
   }

   private static final Class_757 sub_2c1(Class_77c var0, Class_13f var1, Class_ae3 var2) {
      Class_757 var3 = new Class_757(var1.sub_d3());
      var3.sub_393();
      return var3;
   }

   private static final void sub_307(float[] var0, Class_757 var1) {
      float var2 = var0[6];
      float var3 = var0[7];
      float var4 = var0[8];
      float var5 = 1.0F / (float)Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4);
      var2 *= var5;
      var3 *= var5;
      var4 *= var5;
      float var6 = var2 * var2;
      float var7 = var3 * var3;
      if (var6 == 0.0F && var7 == 0.0F) {
         var1.sub_d5(1.0F, 0.0F, 0.0F, 0.0F, var4, 0.0F, 0.0F, 0.0F, var4);
      } else {
         float var8 = (1.0F - var4) / (var6 + var7);
         float var9 = -var2 * var3 * var8;
         var1.sub_d5(var7 * var8 + var4, var9, var2, var9, var6 * var8 + var4, var3, -var2, -var3, var4);
      }

      if (var0[9] != 0.0F) {
         Class_757 var14 = new Class_757();
         Class_4ce.sub_f2(var0[9], var14);
         var1.sub_37e(var14);
      }

      float var15 = var0[3];
      float var16 = var0[4];
      float var10 = var0[5];
      if (var15 != 1.0F || var16 != 1.0F || var10 != 1.0F) {
         var1.sub_d5(
            var1.var_13 * var15,
            var1.var_ad * var16,
            var1.var_c2 * var10,
            var1.var_e7 * var15,
            var1.var_138 * var16,
            var1.var_191 * var10,
            var1.var_211 * var15,
            var1.var_263 * var16,
            var1.var_27d * var10
         );
      }

      var1.var_d7 = var0[0];
      var1.var_1ce = var0[1];
      var1.var_2ce = var0[2];
   }
}
