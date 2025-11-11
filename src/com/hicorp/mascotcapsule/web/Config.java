package com.hicorp.mascotcapsule.web;

final class Config {
   public static final int var_69 = -1;
   private static final int var_78 = 8;
   private static final int var_131 = 0;
   private static final int var_15c = 16;
   private static final int var_1b6 = 32768;
   private static final int var_1e9 = -16777216;
   private static final int var_23f = 16711935;
   private static final int var_28e = 65280;
   private static final int var_2b3 = 16711422;
   private static final int var_2f3 = 16711422;
   private static final int var_353 = 16843008;
   private static final int var_3a0 = 8355711;
   private static final int var_3cf = -16711936;
   private static final int var_432 = 16711680;
   private static final int var_48d = 8388608;
   private static final int var_4cf = 511;
   private static final int[] var_50c = new int[512];
   private int[] var_54d;
   private int var_56c;
   private int var_587;
   private int var_5c5;
   private int var_5e7;
   private int var_643;
   private int var_674;
   private int var_6aa;
   private int var_6ca;
   private Class_517 var_70b;
   private Class_517 var_742;
   public final Class_eda[][] var_74c = new Class_eda[2][];
   public final Class_d00[] var_783 = new Class_d00[2];
   public final Class_1279[] var_7d8 = new Class_1279[2];
   public final Class_1279[][][] var_81c = new Class_1279[4][][];
   public final ModelLoader[][][] var_83b = new ModelLoader[4][][];
   public final MeshLoader[][][] var_85a = new MeshLoader[4][][];
   public final Class_15d5[][][] var_88e = new Class_15d5[4][][];

   public Config() {
      this.var_74c[0] = new Class_eda[2];
      this.var_74c[0][0] = new Class_efa(this);
      this.var_74c[0][1] = new Class_4c3(this);
      this.var_74c[1] = new Class_eda[2];
      this.var_74c[1][0] = new Class_f8f(this);
      this.var_74c[1][1] = new Class_13b6(this);
      this.var_783[0] = new Class_d0f(this);
      this.var_783[1] = new Mesh(this);
      this.var_7d8[0] = new Light(this);
      this.var_7d8[1] = new Class_14bf(this);
      this.var_81c[0] = new Class_1279[2][];
      this.var_81c[0][0] = new Class_1279[2];
      this.var_81c[0][0][0] = new Class_9ad(this);
      this.var_81c[0][0][1] = new Class_38(this);
      this.var_81c[0][1] = new Class_1279[2];
      this.var_81c[0][1][0] = new Class_1146(this);
      this.var_81c[0][1][1] = new Class_111(this);
      this.var_81c[1] = new Class_1279[2][];
      this.var_81c[1][0] = new Class_1279[2];
      this.var_81c[1][0][0] = new Class_c3e(this);
      this.var_81c[1][0][1] = new ResourceEntry(this);
      this.var_81c[1][1] = new Class_1279[2];
      this.var_81c[1][1][0] = new Class_1e8(this);
      this.var_81c[1][1][1] = new Class_c6f(this);
      this.var_81c[2] = new Class_1279[2][];
      this.var_81c[2][0] = new Class_1279[2];
      this.var_81c[2][0][0] = new Class_25b(this);
      this.var_81c[2][0][1] = new Class_1541(this);
      this.var_81c[2][1] = new Class_1279[2];
      this.var_81c[2][1][0] = new Class_1395(this);
      this.var_81c[2][1][1] = new Class_97c(this);
      this.var_81c[3] = new Class_1279[2][];
      this.var_81c[3][0] = new Class_1279[2];
      this.var_81c[3][0][0] = new Class_1081(this);
      this.var_81c[3][0][1] = new Class_13d8(this);
      this.var_81c[3][1] = new Class_1279[2];
      this.var_81c[3][1][0] = new Class_bcb(this);
      this.var_81c[3][1][1] = new Class_1629(this);
      this.var_83b[0] = new ModelLoader[2][];
      this.var_83b[0][0] = new ModelLoader[2];
      this.var_83b[0][0][0] = new Class_1562(this);
      this.var_83b[0][0][1] = new Class_98(this);
      this.var_83b[0][1] = new ModelLoader[2];
      this.var_83b[0][1][0] = new Class_1002(this);
      this.var_83b[0][1][1] = new Class_cc3(this);
      this.var_83b[1] = new ModelLoader[2][];
      this.var_83b[1][0] = new ModelLoader[2];
      this.var_83b[1][0][0] = new Class_11bf(this);
      this.var_83b[1][0][1] = new Class_dfd(this);
      this.var_83b[1][1] = new ModelLoader[2];
      this.var_83b[1][1][0] = new Class_b61(this);
      this.var_83b[1][1][1] = new Class_3ba(this);
      this.var_83b[2] = new ModelLoader[2][];
      this.var_83b[2][0] = new ModelLoader[2];
      this.var_83b[2][0][0] = new Class_7c1(this);
      this.var_83b[2][0][1] = new Class_10ea(this);
      this.var_83b[2][1] = new ModelLoader[2];
      this.var_83b[2][1][0] = new Class_1059(this);
      this.var_83b[2][1][1] = new Class_122c(this);
      this.var_83b[3] = new ModelLoader[2][];
      this.var_83b[3][0] = new ModelLoader[2];
      this.var_83b[3][0][0] = new Class_4f2(this);
      this.var_83b[3][0][1] = new Class_a39(this);
      this.var_83b[3][1] = new ModelLoader[2];
      this.var_83b[3][1][0] = new Face(this);
      this.var_83b[3][1][1] = new Class_118e(this);
      this.var_85a[0] = new MeshLoader[2][];
      this.var_85a[0][0] = new MeshLoader[2];
      this.var_85a[0][0][0] = new Class_5c8(this);
      this.var_85a[0][0][1] = new Class_f30(this);
      this.var_85a[0][1] = new MeshLoader[2];
      this.var_85a[0][1][0] = new Class_11d7(this);
      this.var_85a[0][1][1] = new Class_1359(this);
      this.var_85a[1] = new MeshLoader[2][];
      this.var_85a[1][0] = new MeshLoader[2];
      this.var_85a[1][0][0] = new Class_14fc(this);
      this.var_85a[1][0][1] = new TextureLoader(this);
      this.var_85a[1][1] = new MeshLoader[2];
      this.var_85a[1][1][0] = new Class_1091(this);
      this.var_85a[1][1][1] = new Class_10ac(this);
      this.var_85a[2] = new MeshLoader[2][];
      this.var_85a[2][0] = new MeshLoader[2];
      this.var_85a[2][0][0] = new Class_b0e(this);
      this.var_85a[2][0][1] = new Class_da6(this);
      this.var_85a[2][1] = new MeshLoader[2];
      this.var_85a[2][1][0] = new Class_e4f(this);
      this.var_85a[2][1][1] = new Class_159f(this);
      this.var_85a[3] = new MeshLoader[2][];
      this.var_85a[3][0] = new MeshLoader[2];
      this.var_85a[3][0][0] = new Class_65f(this);
      this.var_85a[3][0][1] = new Class_e88(this);
      this.var_85a[3][1] = new MeshLoader[2];
      this.var_85a[3][1][0] = new Class_916(this);
      this.var_85a[3][1][1] = new Class_abb(this);
      this.var_88e[0] = new Class_15d5[2][];
      this.var_88e[0][0] = new Class_15d5[2];
      this.var_88e[0][0][0] = new Class_12c7(this);
      this.var_88e[0][0][1] = new Class_fa0(this);
      this.var_88e[0][1] = new Class_15d5[2];
      this.var_88e[0][1][0] = new Class_a8d(this);
      this.var_88e[0][1][1] = new ColorRGBA(this);
      this.var_88e[1] = new Class_15d5[2][];
      this.var_88e[1][0] = new Class_15d5[2];
      this.var_88e[1][0][0] = new Class_a10(this);
      this.var_88e[1][0][1] = new Class_11ac(this);
      this.var_88e[1][1] = new Class_15d5[2];
      this.var_88e[1][1][0] = new Class_cce(this);
      this.var_88e[1][1][1] = new Class_740(this);
      this.var_88e[2] = new Class_15d5[2][];
      this.var_88e[2][0] = new Class_15d5[2];
      this.var_88e[2][0][0] = new AnimationSet(this);
      this.var_88e[2][0][1] = new Class_6ba(this);
      this.var_88e[2][1] = new Class_15d5[2];
      this.var_88e[2][1][0] = new Class_a66(this);
      this.var_88e[2][1][1] = new Class_d57(this);
      this.var_88e[3] = new Class_15d5[2][];
      this.var_88e[3][0] = new Class_15d5[2];
      this.var_88e[3][0][0] = new Class_4a7(this);
      this.var_88e[3][0][1] = new Class_81f(this);
      this.var_88e[3][1] = new Class_15d5[2];
      this.var_88e[3][1][0] = new Class_1340(this);
      this.var_88e[3][1][1] = new Class_130f(this);
   }

   public void sub_38(int var1, int[] var2) {
      this.var_54d = var2;
      this.var_56c = var1;
      this.var_587 = 0;
   }

   public void sub_4d(int var1, int var2, int var3, int var4) {
      this.var_5c5 = var1;
      this.var_5e7 = var2;
      this.var_643 = var3;
      this.var_674 = var4;
   }

   public void sub_58(BoundingBox var1) {
      this.var_5c5 = var1.var_35;
      this.var_5e7 = var1.var_88;
      this.var_643 = var1.var_d4;
      this.var_674 = var1.var_f0;
   }

   public void sub_8b(int var1) {
      var1 |= -16777216;
      int var2 = this.var_5e7 * this.var_56c + this.var_5c5 + this.var_587;

      for (int var3 = this.var_5e7; var3 < this.var_674; var3++) {
         int var4 = var2;

         for (int var5 = this.var_5c5; var5 < this.var_643; var5++) {
            this.var_54d[var4++] = var1;
         }

         var2 += this.var_56c;
      }
   }

   public void sub_99(Class_517 var1) {
      this.var_70b = var1;
   }

   public void sub_b1(Class_517 var1) {
      this.var_742 = var1;
   }

   public void sub_f6(int var1) {
      this.var_6aa = var1 | 0xFF000000;
   }

   public void sub_10c(int var1) {
      this.var_6ca = var1;
   }

   public void sub_16b(Class_eda var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.var_9f <= var4.var_9f) {
         if (var2.var_9f <= var3.var_9f) {
            var5 = var2;
            if (var3.var_9f <= var4.var_9f) {
               var6 = var3;
               var7 = var4;
            } else {
               var6 = var4;
               var7 = var3;
            }
         } else {
            var5 = var3;
            var6 = var2;
            var7 = var4;
         }
      } else if (var4.var_9f < var3.var_9f) {
         var5 = var4;
         if (var2.var_9f < var3.var_9f) {
            var6 = var2;
            var7 = var3;
         } else {
            var6 = var3;
            var7 = var2;
         }
      } else {
         var5 = var3;
         var6 = var4;
         var7 = var2;
      }

      if (var5.var_9f != var7.var_9f) {
         var1.var_15e = var5.var_9f;
         var1.var_f1 = var5.var_9f * this.var_56c + this.var_587;
         int var8 = var7.var_59 - var5.var_59;
         int var9 = sub_31c(var7.var_9f - var5.var_9f);
         int var12 = var8 * var9;
         int var10 = (var5.var_59 << 16) + 32768;
         var8 = var6.var_59 - var5.var_59;
         var9 = var6.var_9f - var5.var_9f;
         int var16 = (var8 << 16) - var12 * var9;
         int var17 = var16 >> 16;
         if (var17 == 0) {
            var17 = var16 > 0 ? 1 : -1;
         }

         int var15 = sub_31c(var17);
         var1.var_1a2 = var10;
         var1.var_1ac = var10;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.var_170 = var6.var_9f;
            if (var15 > 0) {
               var1.var_201 = var12;
               var1.var_24e = var13;
            } else {
               var1.var_201 = var13;
               var1.var_24e = var12;
            }

            var1.sub_46();
         }

         if (var6.var_9f != var7.var_9f) {
            var8 = var7.var_59 - var6.var_59;
            var9 = sub_31c(var7.var_9f - var6.var_9f);
            int var14 = var8 * var9;
            int var11 = (var6.var_59 << 16) + 32768;
            var1.var_170 = var7.var_9f;
            if (var15 > 0) {
               var1.var_1ac = var11;
               var1.var_201 = var12;
               var1.var_24e = var14;
            } else {
               var1.var_1a2 = var11;
               var1.var_201 = var14;
               var1.var_24e = var12;
            }

            var1.sub_46();
         }
      }
   }

   public void sub_1ad(Class_d00 var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.var_9f <= var4.var_9f) {
         if (var2.var_9f <= var3.var_9f) {
            var5 = var2;
            if (var3.var_9f <= var4.var_9f) {
               var6 = var3;
               var7 = var4;
            } else {
               var6 = var4;
               var7 = var3;
            }
         } else {
            var5 = var3;
            var6 = var2;
            var7 = var4;
         }
      } else if (var4.var_9f < var3.var_9f) {
         var5 = var4;
         if (var2.var_9f < var3.var_9f) {
            var6 = var2;
            var7 = var3;
         } else {
            var6 = var3;
            var7 = var2;
         }
      } else {
         var5 = var3;
         var6 = var4;
         var7 = var2;
      }

      if (var5.var_9f != var7.var_9f) {
         var1.var_cc = var5.var_9f;
         var1.var_7c = var5.var_9f * this.var_56c + this.var_587;
         int var8 = var7.var_59 - var5.var_59;
         int var9 = sub_31c(var7.var_9f - var5.var_9f);
         int var12 = var8 * var9;
         int var10 = (var5.var_59 << 16) + 32768;
         int var15 = var7.var_100 - var5.var_100;
         int var19 = var15 * var9;
         int var17 = (var5.var_100 << 16) + 32768;
         var8 = var6.var_59 - var5.var_59;
         var9 = var6.var_9f - var5.var_9f;
         var15 = var6.var_100 - var5.var_100;
         int var22 = (var8 << 16) - var12 * var9;
         int var23 = var22 >> 16;
         if (var23 == 0) {
            var23 = var22 > 0 ? 1 : -1;
         }

         int var21 = sub_31c(var23);
         var1.var_324 = (var15 - (var19 * var9 >> 16)) * var21;
         var1.var_15d = var10;
         var1.var_1c1 = var10;
         var1.var_259 = var17;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.var_137 = var6.var_9f;
            if (var21 > 0) {
               var1.var_1ff = var12;
               var1.var_239 = var13;
               var1.var_2a5 = var19;
            } else {
               var1.var_1ff = var13;
               var1.var_239 = var12;
               var1.var_2a5 = var15 * var9;
            }

            var1.sub_21();
         }

         if (var6.var_9f != var7.var_9f) {
            var8 = var7.var_59 - var6.var_59;
            var9 = sub_31c(var7.var_9f - var6.var_9f);
            int var14 = var8 * var9;
            int var11 = (var6.var_59 << 16) + 32768;
            var1.var_137 = var7.var_9f;
            if (var21 > 0) {
               var1.var_1c1 = var11;
               var1.var_1ff = var12;
               var1.var_239 = var14;
               var1.var_2a5 = var19;
            } else {
               var1.var_15d = var11;
               var1.var_1ff = var14;
               var1.var_239 = var12;
               var1.var_259 = (var6.var_100 << 16) + 32768;
               var1.var_2a5 = (var7.var_100 - var6.var_100) * var9;
            }

            var1.sub_21();
         }
      }
   }

   public void sub_20e(Class_1279 var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.var_9f <= var4.var_9f) {
         if (var2.var_9f <= var3.var_9f) {
            var5 = var2;
            if (var3.var_9f <= var4.var_9f) {
               var6 = var3;
               var7 = var4;
            } else {
               var6 = var4;
               var7 = var3;
            }
         } else {
            var5 = var3;
            var6 = var2;
            var7 = var4;
         }
      } else if (var4.var_9f < var3.var_9f) {
         var5 = var4;
         if (var2.var_9f < var3.var_9f) {
            var6 = var2;
            var7 = var3;
         } else {
            var6 = var3;
            var7 = var2;
         }
      } else {
         var5 = var3;
         var6 = var4;
         var7 = var2;
      }

      if (var5.var_9f != var7.var_9f) {
         var1.var_139 = var5.var_9f;
         var1.var_c4 = var5.var_9f * this.var_56c + this.var_587;
         int var8 = var7.var_59 - var5.var_59;
         int var9 = sub_31c(var7.var_9f - var5.var_9f);
         int var12 = var8 * var9;
         int var10 = (var5.var_59 << 16) + 32768;
         int var15 = var7.var_100 - var5.var_100;
         int var16 = var7.var_114 - var5.var_114;
         int var19 = var15 * var9;
         int var20 = var16 * var9;
         int var17 = (var5.var_100 << 16) + 32768;
         int var18 = (var5.var_114 << 16) + 32768;
         var8 = var6.var_59 - var5.var_59;
         var9 = var6.var_9f - var5.var_9f;
         var15 = var6.var_100 - var5.var_100;
         var16 = var6.var_114 - var5.var_114;
         int var22 = (var8 << 16) - var12 * var9;
         int var23 = var22 >> 16;
         if (var23 == 0) {
            var23 = var22 > 0 ? 1 : -1;
         }

         int var21 = sub_31c(var23);
         var1.var_491 = (var15 - (var19 * var9 >> 16)) * var21;
         var1.var_49f = (var16 - (var20 * var9 >> 16)) * var21;
         var1.var_279 = var10;
         var1.var_2c3 = var10;
         var1.var_368 = var17;
         var1.var_3c2 = var18;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.var_21c = var6.var_9f;
            if (var21 > 0) {
               var1.var_2fe = var12;
               var1.var_326 = var13;
               var1.var_40c = var19;
               var1.var_438 = var20;
            } else {
               var1.var_2fe = var13;
               var1.var_326 = var12;
               var1.var_40c = var15 * var9;
               var1.var_438 = var16 * var9;
            }

            var1.sub_1c();
         }

         if (var6.var_9f != var7.var_9f) {
            var8 = var7.var_59 - var6.var_59;
            var9 = sub_31c(var7.var_9f - var6.var_9f);
            int var14 = var8 * var9;
            int var11 = (var6.var_59 << 16) + 32768;
            var1.var_21c = var7.var_9f;
            if (var21 > 0) {
               var1.var_2c3 = var11;
               var1.var_2fe = var12;
               var1.var_326 = var14;
               var1.var_40c = var19;
               var1.var_438 = var20;
            } else {
               var1.var_279 = var11;
               var1.var_2fe = var14;
               var1.var_326 = var12;
               var1.var_368 = (var6.var_100 << 16) + 32768;
               var1.var_3c2 = (var6.var_114 << 16) + 32768;
               var1.var_40c = (var7.var_100 - var6.var_100) * var9;
               var1.var_438 = (var7.var_114 - var6.var_114) * var9;
            }

            var1.sub_1c();
         }
      }
   }

   public void sub_235(ModelLoader var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.var_9f <= var4.var_9f) {
         if (var2.var_9f <= var3.var_9f) {
            var5 = var2;
            if (var3.var_9f <= var4.var_9f) {
               var6 = var3;
               var7 = var4;
            } else {
               var6 = var4;
               var7 = var3;
            }
         } else {
            var5 = var3;
            var6 = var2;
            var7 = var4;
         }
      } else if (var4.var_9f < var3.var_9f) {
         var5 = var4;
         if (var2.var_9f < var3.var_9f) {
            var6 = var2;
            var7 = var3;
         } else {
            var6 = var3;
            var7 = var2;
         }
      } else {
         var5 = var3;
         var6 = var4;
         var7 = var2;
      }

      if (var5.var_9f != var7.var_9f) {
         var1.var_6c = var5.var_9f;
         var1.var_31 = var5.var_9f * this.var_56c + this.var_587;
         int var8 = var7.var_59 - var5.var_59;
         int var9 = sub_31c(var7.var_9f - var5.var_9f);
         int var12 = var8 * var9;
         int var10 = (var5.var_59 << 16) + 32768;
         int var15 = var7.var_100 - var5.var_100;
         int var16 = var7.var_114 - var5.var_114;
         int var17 = var7.var_165 - var5.var_165;
         int var21 = var15 * var9;
         int var22 = var16 * var9;
         int var23 = var17 * var9;
         int var18 = (var5.var_100 << 16) + 32768;
         int var19 = (var5.var_114 << 16) + 32768;
         int var20 = (var5.var_165 << 16) + 32768;
         var8 = var6.var_59 - var5.var_59;
         var9 = var6.var_9f - var5.var_9f;
         var15 = var6.var_100 - var5.var_100;
         var16 = var6.var_114 - var5.var_114;
         var17 = var6.var_165 - var5.var_165;
         int var25 = (var8 << 16) - var12 * var9;
         int var26 = var25 >> 16;
         if (var26 == 0) {
            var26 = var25 > 0 ? 1 : -1;
         }

         int var24 = sub_31c(var26);
         var1.var_2d1 = (var15 - (var21 * var9 >> 16)) * var24;
         var1.var_319 = (var16 - (var22 * var9 >> 16)) * var24;
         var1.var_339 = (var17 - (var23 * var9 >> 16)) * var24;
         var1.var_db = var10;
         var1.var_12b = var10;
         var1.var_1c8 = var18;
         var1.var_205 = var19;
         var1.var_216 = var20;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.var_81 = var6.var_9f;
            if (var24 > 0) {
               var1.var_14b = var12;
               var1.var_18a = var13;
               var1.var_243 = var21;
               var1.var_24d = var22;
               var1.var_282 = var23;
            } else {
               var1.var_14b = var13;
               var1.var_18a = var12;
               var1.var_243 = var15 * var9;
               var1.var_24d = var16 * var9;
               var1.var_282 = var17 * var9;
            }

            var1.sub_26();
         }

         if (var6.var_9f != var7.var_9f) {
            var8 = var7.var_59 - var6.var_59;
            var9 = sub_31c(var7.var_9f - var6.var_9f);
            int var14 = var8 * var9;
            int var11 = (var6.var_59 << 16) + 32768;
            var1.var_81 = var7.var_9f;
            if (var24 > 0) {
               var1.var_12b = var11;
               var1.var_14b = var12;
               var1.var_18a = var14;
               var1.var_243 = var21;
               var1.var_24d = var22;
               var1.var_282 = var23;
            } else {
               var1.var_db = var11;
               var1.var_14b = var14;
               var1.var_18a = var12;
               var1.var_1c8 = (var6.var_100 << 16) + 32768;
               var1.var_205 = (var6.var_114 << 16) + 32768;
               var1.var_216 = (var6.var_165 << 16) + 32768;
               var1.var_243 = (var7.var_100 - var6.var_100) * var9;
               var1.var_24d = (var7.var_114 - var6.var_114) * var9;
               var1.var_282 = (var7.var_165 - var6.var_165) * var9;
            }

            var1.sub_26();
         }
      }
   }

   public void sub_253(MeshLoader var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.var_9f <= var4.var_9f) {
         if (var2.var_9f <= var3.var_9f) {
            var5 = var2;
            if (var3.var_9f <= var4.var_9f) {
               var6 = var3;
               var7 = var4;
            } else {
               var6 = var4;
               var7 = var3;
            }
         } else {
            var5 = var3;
            var6 = var2;
            var7 = var4;
         }
      } else if (var4.var_9f < var3.var_9f) {
         var5 = var4;
         if (var2.var_9f < var3.var_9f) {
            var6 = var2;
            var7 = var3;
         } else {
            var6 = var3;
            var7 = var2;
         }
      } else {
         var5 = var3;
         var6 = var4;
         var7 = var2;
      }

      if (var5.var_9f != var7.var_9f) {
         var1.var_aa = var5.var_9f;
         var1.var_5f = var5.var_9f * this.var_56c + this.var_587;
         int var8 = var7.var_59 - var5.var_59;
         int var9 = sub_31c(var7.var_9f - var5.var_9f);
         int var12 = var8 * var9;
         int var10 = (var5.var_59 << 16) + 32768;
         int var15 = var7.var_100 - var5.var_100;
         int var16 = var7.var_114 - var5.var_114;
         int var17 = var7.var_165 - var5.var_165;
         int var18 = var7.var_181 - var5.var_181;
         int var23 = var15 * var9;
         int var24 = var16 * var9;
         int var25 = var17 * var9;
         int var26 = var18 * var9;
         int var19 = (var5.var_100 << 16) + 32768;
         int var20 = (var5.var_114 << 16) + 32768;
         int var21 = (var5.var_165 << 16) + 32768;
         int var22 = (var5.var_181 << 16) + 32768;
         var8 = var6.var_59 - var5.var_59;
         var9 = var6.var_9f - var5.var_9f;
         var15 = var6.var_100 - var5.var_100;
         var16 = var6.var_114 - var5.var_114;
         var17 = var6.var_165 - var5.var_165;
         var18 = var6.var_181 - var5.var_181;
         int var28 = (var8 << 16) - var12 * var9;
         int var29 = var28 >> 16;
         if (var29 == 0) {
            var29 = var28 > 0 ? 1 : -1;
         }

         int var27 = sub_31c(var29);
         var1.var_3bf = (var15 - (var23 * var9 >> 16)) * var27;
         var1.var_3d8 = (var16 - (var24 * var9 >> 16)) * var27;
         var1.var_403 = (var17 - (var25 * var9 >> 16)) * var27;
         var1.var_45b = (var18 - (var26 * var9 >> 16)) * var27;
         var1.var_116 = var10;
         var1.var_166 = var10;
         var1.var_204 = var19;
         var1.var_248 = var20;
         var1.var_264 = var21;
         var1.var_2c9 = var22;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.var_bc = var6.var_9f;
            if (var27 > 0) {
               var1.var_172 = var12;
               var1.var_1ab = var13;
               var1.var_2ea = var23;
               var1.var_311 = var24;
               var1.var_341 = var25;
               var1.var_3a2 = var26;
            } else {
               var1.var_172 = var13;
               var1.var_1ab = var12;
               var1.var_2ea = var15 * var9;
               var1.var_311 = var16 * var9;
               var1.var_341 = var17 * var9;
               var1.var_3a2 = var18 * var9;
            }

            var1.sub_81();
         }

         if (var6.var_9f != var7.var_9f) {
            var8 = var7.var_59 - var6.var_59;
            var9 = sub_31c(var7.var_9f - var6.var_9f);
            int var14 = var8 * var9;
            int var11 = (var6.var_59 << 16) + 32768;
            var1.var_bc = var7.var_9f;
            if (var27 > 0) {
               var1.var_166 = var11;
               var1.var_172 = var12;
               var1.var_1ab = var14;
               var1.var_2ea = var23;
               var1.var_311 = var24;
               var1.var_341 = var25;
               var1.var_3a2 = var26;
            } else {
               var1.var_116 = var11;
               var1.var_172 = var14;
               var1.var_1ab = var12;
               var1.var_204 = (var6.var_100 << 16) + 32768;
               var1.var_248 = (var6.var_114 << 16) + 32768;
               var1.var_264 = (var6.var_165 << 16) + 32768;
               var1.var_2c9 = (var6.var_181 << 16) + 32768;
               var1.var_2ea = (var7.var_100 - var6.var_100) * var9;
               var1.var_311 = (var7.var_114 - var6.var_114) * var9;
               var1.var_341 = (var7.var_165 - var6.var_165) * var9;
               var1.var_3a2 = (var7.var_181 - var6.var_181) * var9;
            }

            var1.sub_81();
         }
      }
   }

   public void sub_287(Class_15d5 var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.var_9f <= var4.var_9f) {
         if (var2.var_9f <= var3.var_9f) {
            var5 = var2;
            if (var3.var_9f <= var4.var_9f) {
               var6 = var3;
               var7 = var4;
            } else {
               var6 = var4;
               var7 = var3;
            }
         } else {
            var5 = var3;
            var6 = var2;
            var7 = var4;
         }
      } else if (var4.var_9f < var3.var_9f) {
         var5 = var4;
         if (var2.var_9f < var3.var_9f) {
            var6 = var2;
            var7 = var3;
         } else {
            var6 = var3;
            var7 = var2;
         }
      } else {
         var5 = var3;
         var6 = var4;
         var7 = var2;
      }

      if (var5.var_9f != var7.var_9f) {
         var1.var_8b = var5.var_9f;
         var1.var_41 = var5.var_9f * this.var_56c + this.var_587;
         int var8 = var7.var_59 - var5.var_59;
         int var9 = sub_31c(var7.var_9f - var5.var_9f);
         int var12 = var8 * var9;
         int var10 = (var5.var_59 << 16) + 32768;
         int var15 = var7.var_100 - var5.var_100;
         int var16 = var7.var_114 - var5.var_114;
         int var17 = var7.var_165 - var5.var_165;
         int var18 = var7.var_181 - var5.var_181;
         int var19 = var7.var_1a4 - var5.var_1a4;
         int var25 = var15 * var9;
         int var26 = var16 * var9;
         int var27 = var17 * var9;
         int var28 = var18 * var9;
         int var29 = var19 * var9;
         int var20 = (var5.var_100 << 16) + 32768;
         int var21 = (var5.var_114 << 16) + 32768;
         int var22 = (var5.var_165 << 16) + 32768;
         int var23 = (var5.var_181 << 16) + 32768;
         int var24 = (var5.var_1a4 << 16) + 32768;
         var8 = var6.var_59 - var5.var_59;
         var9 = var6.var_9f - var5.var_9f;
         var15 = var6.var_100 - var5.var_100;
         var16 = var6.var_114 - var5.var_114;
         var17 = var6.var_165 - var5.var_165;
         var18 = var6.var_181 - var5.var_181;
         var19 = var6.var_1a4 - var5.var_1a4;
         int var31 = (var8 << 16) - var12 * var9;
         int var32 = var31 >> 16;
         if (var32 == 0) {
            var32 = var31 > 0 ? 1 : -1;
         }

         int var30 = sub_31c(var32);
         var1.var_478 = (var15 - (var25 * var9 >> 16)) * var30;
         var1.var_510 = (var16 - (var26 * var9 >> 16)) * var30;
         var1.var_52a = (var17 - (var27 * var9 >> 16)) * var30;
         var1.var_54a = (var18 - (var28 * var9 >> 16)) * var30;
         var1.var_55b = (var19 - (var29 * var9 >> 16)) * var30;
         var1.var_189 = var10;
         var1.var_1d6 = var10;
         var1.var_274 = var20;
         var1.var_29a = var21;
         var1.var_2d0 = var22;
         var1.var_328 = var23;
         var1.var_383 = var24;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.var_e8 = var6.var_9f;
            if (var30 > 0) {
               var1.var_236 = var12;
               var1.var_26a = var13;
               var1.var_3b1 = var25;
               var1.var_3ca = var26;
               var1.var_3f6 = var27;
               var1.var_44d = var28;
               var1.var_460 = var29;
            } else {
               var1.var_236 = var13;
               var1.var_26a = var12;
               var1.var_3b1 = var15 * var9;
               var1.var_3ca = var16 * var9;
               var1.var_3f6 = var17 * var9;
               var1.var_44d = var18 * var9;
               var1.var_460 = var19 * var9;
            }

            var1.sub_22();
         }

         if (var6.var_9f != var7.var_9f) {
            var8 = var7.var_59 - var6.var_59;
            var9 = sub_31c(var7.var_9f - var6.var_9f);
            int var14 = var8 * var9;
            int var11 = (var6.var_59 << 16) + 32768;
            var1.var_e8 = var7.var_9f;
            if (var30 > 0) {
               var1.var_1d6 = var11;
               var1.var_236 = var12;
               var1.var_26a = var14;
               var1.var_3b1 = var25;
               var1.var_3ca = var26;
               var1.var_3f6 = var27;
               var1.var_44d = var28;
               var1.var_460 = var29;
            } else {
               var1.var_189 = var11;
               var1.var_236 = var14;
               var1.var_26a = var12;
               var1.var_274 = (var6.var_100 << 16) + 32768;
               var1.var_29a = (var6.var_114 << 16) + 32768;
               var1.var_2d0 = (var6.var_165 << 16) + 32768;
               var1.var_328 = (var6.var_181 << 16) + 32768;
               var1.var_383 = (var6.var_1a4 << 16) + 32768;
               var1.var_3b1 = (var7.var_100 - var6.var_100) * var9;
               var1.var_3ca = (var7.var_114 - var6.var_114) * var9;
               var1.var_3f6 = (var7.var_165 - var6.var_165) * var9;
               var1.var_44d = (var7.var_181 - var6.var_181) * var9;
               var1.var_460 = (var7.var_1a4 - var6.var_1a4) * var9;
            }

            var1.sub_22();
         }
      }
   }

   public int sub_2de(Class_ae var1) {
      byte var2 = 0;
      if (var1.var_59 < this.var_5c5) {
         var2 |= 1;
      } else if (this.var_643 <= var1.var_59) {
         var2 |= 2;
      }

      if (var1.var_9f < this.var_5e7) {
         var2 |= 4;
      } else if (this.var_674 <= var1.var_9f) {
         var2 |= 8;
      }

      return var2;
   }

   private static int sub_31c(int var0) {
      return 65536 / var0;
   }

   static int[] sub_34c(Config var0) {
      return var0.var_54d;
   }

   static int sub_397(Config var0) {
      return var0.var_6aa;
   }

   static int sub_3dd(Config var0) {
      return var0.var_56c;
   }

   static int sub_41a(Config var0) {
      return var0.var_5e7;
   }

   static int sub_45d(Config var0) {
      return var0.var_674;
   }

   static int sub_467(Config var0) {
      return var0.var_5c5;
   }

   static int sub_48e(Config var0) {
      return var0.var_643;
   }

   static int[] sub_4c1() {
      return var_50c;
   }

   static Class_517 sub_509(Config var0) {
      return var0.var_70b;
   }

   static int sub_52c(Config var0) {
      return var0.var_6ca;
   }

   static Class_517 sub_55c(Config var0) {
      return var0.var_742;
   }

   static {
      int var0 = 65793;

      for (int var1 = 0; var1 < 129; var1++) {
         var_50c[var1] = 0;
      }

      for (int var2 = 1; var2 < 255; var2++) {
         var_50c[var2 + 128] = 65793 * var2;
      }

      for (int var3 = -1; var3 < 128; var3++) {
         var_50c[var3 + 384] = 16777215;
      }
   }
}
