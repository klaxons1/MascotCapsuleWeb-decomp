package com.hicorp.mascotcapsule.web;

import java.io.InputStream;

public final class Class_77c {
   public static final int var_27 = 4;
   private long var_a8;
   private static final Vector3f var_107 = new Vector3f(0.0F, 0.0F, 1.0F);
   protected final Class_5a9 var_11b = new Class_5a9();
   protected int var_12f = 0;
   protected Class_13f[] var_17c = null;
   protected int var_1d4 = 0;
   protected Vector3f[] var_22e = null;
   protected Vector3f[] var_244 = null;
   protected int var_255 = 0;
   protected Class_12f[] var_2b7 = null;

   protected int sub_2b(int var1, int var2, Class_613 var3) {
      boolean var5;
      do {
         Class_13f var9 = new Class_13f();
         this.var_17c[var1] = var9;
         boolean var4;
         var9.var_2a = var4 = var3.sub_13() != 0;
         var9.var_8c = var5 = var3.sub_13() != 0;
         var9.var_a2 = var3.sub_1a5();
         var9.var_f4.var_13 = var3.sub_de();
         var9.var_f4.var_ad = var3.sub_de();
         var9.var_f4.var_c2 = var3.sub_de();
         var9.var_f4.var_e7 = var3.sub_de();
         var9.var_f4.var_138 = var3.sub_de();
         var9.var_f4.var_191 = var3.sub_de();
         var9.var_f4.var_211 = var3.sub_de();
         var9.var_f4.var_263 = var3.sub_de();
         var9.var_f4.var_27d = var3.sub_de();
         var9.var_f4.var_d7 = var3.sub_de();
         var9.var_f4.var_1ce = var3.sub_de();
         var9.var_f4.var_2ce = var3.sub_de();
         var9.var_118 = var3.sub_3f();
         var9.sub_55((Class_5a9)(var1 == 0 ? this.var_11b : this.var_17c[var2]));
         var1++;
         if (var4) {
            var1 = this.sub_2b(var1, var1 - 1, var3);
         }
      } while (var5);

      return var1;
   }

   public boolean sub_35(InputStream var1) {
      Class_613 var2 = new Class_613(var1);
      byte var4 = var2.sub_13();
      byte var5 = var2.sub_13();
      byte var6 = var2.sub_13();
      byte var7 = var2.sub_13();
      if (var4 == 72 && var5 == 73 && var6 == 74 && var7 == 66) {
         this.var_a8 = var2.sub_77();
         if (this.var_a8 != 1L) {
            return false;
         } else {
            var2.sub_11c(12);
            byte[] var8 = new byte[64];
            byte var9 = 0;

            for (int var3 = 0; var3 < 64; var3++) {
               var8[var3] = var2.sub_13();
               var9 += var8[var3];
            }

            this.var_1d4 = var2.sub_77();
            this.var_22e = new Vector3f[this.var_1d4];

            for (int var13 = 0; var13 < this.var_1d4; var13++) {
               this.var_22e[var13] = new Vector3f();
               this.var_22e[var13].x = var2.sub_de();
               this.var_22e[var13].y = var2.sub_de();
               this.var_22e[var13].z = var2.sub_de();
            }

            int var10 = var2.sub_77();
            if (var10 > 0) {
               if (var10 != this.var_1d4) {
                  return false;
               }

               this.var_244 = new Vector3f[var10];

               for (int var14 = 0; var14 < this.var_1d4; var14++) {
                  this.var_244[var14] = new Vector3f();
                  this.var_244[var14].x = var2.sub_de();
                  this.var_244[var14].y = var2.sub_de();
                  this.var_244[var14].z = var2.sub_de();
               }
            } else {
               this.var_244 = null;
            }

            this.var_255 = var2.sub_77();
            if (var9 == 0 && this.var_255 >= 500) {
               return false;
            } else {
               this.var_2b7 = new Class_12f[this.var_255];
               int var11 = var2.sub_77();

               for (int var15 = 0; var15 < var11; var15++) {
                  this.var_2b7[var15] = new Class_12f();
                  this.var_2b7[var15].var_37 = var2.sub_77();
                  this.var_2b7[var15].var_55 = 3;
                  this.var_2b7[var15].var_a0 = var2.sub_3f();
                  this.var_2b7[var15].var_bf = var2.sub_3f();
                  this.var_2b7[var15].var_103 = var2.sub_3f();
                  this.var_2b7[var15].var_15b = var2.sub_3f();
                  this.var_2b7[var15].var_1a3 = var2.sub_3f();
                  this.var_2b7[var15].var_1d0 = var2.sub_3f();
                  this.var_2b7[var15].var_1f4 = var2.sub_3f();
                  this.var_2b7[var15].var_23e = var2.sub_3f();
                  this.var_2b7[var15].var_280 = var2.sub_3f();
               }

               int var12 = var2.sub_77();

               for (int var16 = var11; var16 < this.var_255; var16++) {
                  this.var_2b7[var16] = new Class_12f();
                  this.var_2b7[var16].var_37 = var2.sub_77();
                  this.var_2b7[var16].var_55 = 4;
                  this.var_2b7[var16].var_a0 = var2.sub_3f();
                  this.var_2b7[var16].var_bf = var2.sub_3f();
                  this.var_2b7[var16].var_103 = var2.sub_3f();
                  this.var_2b7[var16].var_15b = var2.sub_3f();
                  this.var_2b7[var16].var_1a3 = var2.sub_3f();
                  this.var_2b7[var16].var_1d0 = var2.sub_3f();
                  this.var_2b7[var16].var_1f4 = var2.sub_3f();
                  this.var_2b7[var16].var_23e = var2.sub_3f();
                  this.var_2b7[var16].var_280 = var2.sub_3f();
                  this.var_2b7[var16].var_2ba = var2.sub_3f();
                  this.var_2b7[var16].var_30e = var2.sub_3f();
                  this.var_2b7[var16].var_356 = var2.sub_3f();
               }

               this.var_12f = var2.sub_77();
               this.var_17c = new Class_13f[this.var_12f];
               return this.var_12f == this.sub_2b(0, 0, var2);
            }
         }
      } else {
         return false;
      }
   }

   public final int sub_85() {
      return this.var_12f;
   }

   public final Class_5a9 sub_df() {
      return this.var_11b;
   }

   public final Class_13f sub_126(int var1) {
      return this.var_17c[var1];
   }

   public final int sub_175() {
      return this.var_1d4;
   }

   public final Vector3f[] sub_198() {
      return this.var_22e;
   }

   public final Vector3f[] sub_1e6() {
      return this.var_244;
   }

   public final int sub_24a() {
      return this.var_255;
   }

   public final Class_12f sub_28f(int var1) {
      return this.var_2b7[var1];
   }

   public final Class_12f sub_2e1() {
      return this.var_2b7[0];
   }

   public final boolean sub_2ef() {
      return this.var_17c != null;
   }

   public final void sub_31b() {
      if (this.var_244 == null) {
         this.var_244 = new Vector3f[this.var_1d4];
         Vector3f var1 = new Vector3f();
         Vector3f var2 = new Vector3f();

         for (int var3 = this.var_1d4 - 1; var3 >= 0; var3--) {
            this.var_244[var3] = new Vector3f(0.0F, 0.0F, 0.0F);
         }

         for (int var4 = 0; var4 < this.var_255; var4++) {
            Class_12f var5 = this.var_2b7[var4];
            var1.setDifference(this.var_22e[var5.var_15b], this.var_22e[var5.var_a0]);
            var2.setDifference(this.var_22e[var5.var_1f4], this.var_22e[var5.var_a0]);
            var2.sub_3b7(var1);
            this.var_244[var5.var_a0].sub_1b7(var2);
            this.var_244[var5.var_15b].sub_1b7(var2);
            this.var_244[var5.var_1f4].sub_1b7(var2);
            if (var5.var_55 == 4) {
               this.var_244[var5.var_2ba].sub_1b7(var2);
            }

            var5.var_37 |= 32768;
            var5.var_37 &= 65535;
            var5.var_37 |= 1077936128;
         }

         for (int var7 = this.var_1d4 - 1; var7 >= 0; var7--) {
            float var6 = this.var_244[var7].lenght();
            if (var6 != 0.0F) {
               this.var_244[var7].scale(1.0F / var6);
            } else {
               this.var_244[var7].sub_7a(1.0F, 0.0F, 0.0F);
            }
         }
      }
   }
}
