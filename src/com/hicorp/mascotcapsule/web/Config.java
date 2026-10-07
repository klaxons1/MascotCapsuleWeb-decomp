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
   private static final int[] blendTable = new int[512];
   private int[] pixelBuffer;
   private int stride;
   private int bufferWidth;
   private int bufferHeight;
   private int clipLeft;
   private int clipRight;
   private int clipTop;
   private int clipBottom;
   private int colorKey;
   private Class_517 diffuseTexture;
   private Class_517 sphereMapTexture;
   public final Class_eda[][] flatDrawers = new Class_eda[2][];
   public final Class_d00[] lineDrawers = new Class_d00[2];
   public final Class_1279[] litColorDrawers = new Class_1279[2];
   public final Class_1279[][][] texturedDrawers = new Class_1279[4][][];
   public final ModelLoader[][][] unlitDrawers = new ModelLoader[4][][];
   public final MeshLoader[][][] litDrawers = new MeshLoader[4][][];
   public final Class_15d5[][][] sphereMapDrawers = new Class_15d5[4][][];

   public Config() {
      this.flatDrawers[0] = new Class_eda[2];
      this.flatDrawers[0][0] = new Class_efa(this);
      this.flatDrawers[0][1] = new Class_4c3(this);
      this.flatDrawers[1] = new Class_eda[2];
      this.flatDrawers[1][0] = new Class_f8f(this);
      this.flatDrawers[1][1] = new Class_13b6(this);
      this.lineDrawers[0] = new Class_d0f(this);
      this.lineDrawers[1] = new Mesh(this);
      this.litColorDrawers[0] = new Light(this);
      this.litColorDrawers[1] = new Class_14bf(this);
      this.texturedDrawers[0] = new Class_1279[2][];
      this.texturedDrawers[0][0] = new Class_1279[2];
      this.texturedDrawers[0][0][0] = new Class_9ad(this);
      this.texturedDrawers[0][0][1] = new Class_38(this);
      this.texturedDrawers[0][1] = new Class_1279[2];
      this.texturedDrawers[0][1][0] = new Class_1146(this);
      this.texturedDrawers[0][1][1] = new Class_111(this);
      this.texturedDrawers[1] = new Class_1279[2][];
      this.texturedDrawers[1][0] = new Class_1279[2];
      this.texturedDrawers[1][0][0] = new Class_c3e(this);
      this.texturedDrawers[1][0][1] = new ResourceEntry(this);
      this.texturedDrawers[1][1] = new Class_1279[2];
      this.texturedDrawers[1][1][0] = new Class_1e8(this);
      this.texturedDrawers[1][1][1] = new Class_c6f(this);
      this.texturedDrawers[2] = new Class_1279[2][];
      this.texturedDrawers[2][0] = new Class_1279[2];
      this.texturedDrawers[2][0][0] = new Class_25b(this);
      this.texturedDrawers[2][0][1] = new Class_1541(this);
      this.texturedDrawers[2][1] = new Class_1279[2];
      this.texturedDrawers[2][1][0] = new Class_1395(this);
      this.texturedDrawers[2][1][1] = new Class_97c(this);
      this.texturedDrawers[3] = new Class_1279[2][];
      this.texturedDrawers[3][0] = new Class_1279[2];
      this.texturedDrawers[3][0][0] = new Class_1081(this);
      this.texturedDrawers[3][0][1] = new Class_13d8(this);
      this.texturedDrawers[3][1] = new Class_1279[2];
      this.texturedDrawers[3][1][0] = new Class_bcb(this);
      this.texturedDrawers[3][1][1] = new Class_1629(this);
      this.unlitDrawers[0] = new ModelLoader[2][];
      this.unlitDrawers[0][0] = new ModelLoader[2];
      this.unlitDrawers[0][0][0] = new Class_1562(this);
      this.unlitDrawers[0][0][1] = new Class_98(this);
      this.unlitDrawers[0][1] = new ModelLoader[2];
      this.unlitDrawers[0][1][0] = new Class_1002(this);
      this.unlitDrawers[0][1][1] = new Class_cc3(this);
      this.unlitDrawers[1] = new ModelLoader[2][];
      this.unlitDrawers[1][0] = new ModelLoader[2];
      this.unlitDrawers[1][0][0] = new Class_11bf(this);
      this.unlitDrawers[1][0][1] = new Class_dfd(this);
      this.unlitDrawers[1][1] = new ModelLoader[2];
      this.unlitDrawers[1][1][0] = new Class_b61(this);
      this.unlitDrawers[1][1][1] = new Class_3ba(this);
      this.unlitDrawers[2] = new ModelLoader[2][];
      this.unlitDrawers[2][0] = new ModelLoader[2];
      this.unlitDrawers[2][0][0] = new Class_7c1(this);
      this.unlitDrawers[2][0][1] = new Class_10ea(this);
      this.unlitDrawers[2][1] = new ModelLoader[2];
      this.unlitDrawers[2][1][0] = new Class_1059(this);
      this.unlitDrawers[2][1][1] = new Class_122c(this);
      this.unlitDrawers[3] = new ModelLoader[2][];
      this.unlitDrawers[3][0] = new ModelLoader[2];
      this.unlitDrawers[3][0][0] = new Class_4f2(this);
      this.unlitDrawers[3][0][1] = new Class_a39(this);
      this.unlitDrawers[3][1] = new ModelLoader[2];
      this.unlitDrawers[3][1][0] = new Face(this);
      this.unlitDrawers[3][1][1] = new Class_118e(this);
      this.litDrawers[0] = new MeshLoader[2][];
      this.litDrawers[0][0] = new MeshLoader[2];
      this.litDrawers[0][0][0] = new Class_5c8(this);
      this.litDrawers[0][0][1] = new Class_f30(this);
      this.litDrawers[0][1] = new MeshLoader[2];
      this.litDrawers[0][1][0] = new Class_11d7(this);
      this.litDrawers[0][1][1] = new Class_1359(this);
      this.litDrawers[1] = new MeshLoader[2][];
      this.litDrawers[1][0] = new MeshLoader[2];
      this.litDrawers[1][0][0] = new Class_14fc(this);
      this.litDrawers[1][0][1] = new TextureLoader(this);
      this.litDrawers[1][1] = new MeshLoader[2];
      this.litDrawers[1][1][0] = new Class_1091(this);
      this.litDrawers[1][1][1] = new Class_10ac(this);
      this.litDrawers[2] = new MeshLoader[2][];
      this.litDrawers[2][0] = new MeshLoader[2];
      this.litDrawers[2][0][0] = new Class_b0e(this);
      this.litDrawers[2][0][1] = new Class_da6(this);
      this.litDrawers[2][1] = new MeshLoader[2];
      this.litDrawers[2][1][0] = new Class_e4f(this);
      this.litDrawers[2][1][1] = new Class_159f(this);
      this.litDrawers[3] = new MeshLoader[2][];
      this.litDrawers[3][0] = new MeshLoader[2];
      this.litDrawers[3][0][0] = new Class_65f(this);
      this.litDrawers[3][0][1] = new Class_e88(this);
      this.litDrawers[3][1] = new MeshLoader[2];
      this.litDrawers[3][1][0] = new Class_916(this);
      this.litDrawers[3][1][1] = new Class_abb(this);
      this.sphereMapDrawers[0] = new Class_15d5[2][];
      this.sphereMapDrawers[0][0] = new Class_15d5[2];
      this.sphereMapDrawers[0][0][0] = new Class_12c7(this);
      this.sphereMapDrawers[0][0][1] = new Class_fa0(this);
      this.sphereMapDrawers[0][1] = new Class_15d5[2];
      this.sphereMapDrawers[0][1][0] = new Class_a8d(this);
      this.sphereMapDrawers[0][1][1] = new ColorRGBA(this);
      this.sphereMapDrawers[1] = new Class_15d5[2][];
      this.sphereMapDrawers[1][0] = new Class_15d5[2];
      this.sphereMapDrawers[1][0][0] = new Class_a10(this);
      this.sphereMapDrawers[1][0][1] = new Class_11ac(this);
      this.sphereMapDrawers[1][1] = new Class_15d5[2];
      this.sphereMapDrawers[1][1][0] = new Class_cce(this);
      this.sphereMapDrawers[1][1][1] = new Class_740(this);
      this.sphereMapDrawers[2] = new Class_15d5[2][];
      this.sphereMapDrawers[2][0] = new Class_15d5[2];
      this.sphereMapDrawers[2][0][0] = new AnimationSet(this);
      this.sphereMapDrawers[2][0][1] = new Class_6ba(this);
      this.sphereMapDrawers[2][1] = new Class_15d5[2];
      this.sphereMapDrawers[2][1][0] = new Class_a66(this);
      this.sphereMapDrawers[2][1][1] = new Class_d57(this);
      this.sphereMapDrawers[3] = new Class_15d5[2][];
      this.sphereMapDrawers[3][0] = new Class_15d5[2];
      this.sphereMapDrawers[3][0][0] = new Class_4a7(this);
      this.sphereMapDrawers[3][0][1] = new Class_81f(this);
      this.sphereMapDrawers[3][1] = new Class_15d5[2];
      this.sphereMapDrawers[3][1][0] = new Class_1340(this);
      this.sphereMapDrawers[3][1][1] = new Class_130f(this);
   }

   public void setRenderTarget(int var1, int[] var2) {
      this.pixelBuffer = var2;
      this.stride = var1;
      this.bufferWidth = 0;
   }

   public void sub_4d(int var1, int var2, int var3, int var4) {
      this.bufferHeight = var1;
      this.clipLeft = var2;
      this.clipRight = var3;
      this.clipTop = var4;
   }

   public void sub_58(BoundingBox var1) {
      this.bufferHeight = var1.minX;
      this.clipLeft = var1.minY;
      this.clipRight = var1.maxX;
      this.clipTop = var1.maxY;
   }

   public void sub_8b(int var1) {
      var1 |= -16777216;
      int var2 = this.clipLeft * this.stride + this.bufferHeight + this.bufferWidth;

      for (int var3 = this.clipLeft; var3 < this.clipTop; var3++) {
         int var4 = var2;

         for (int var5 = this.bufferHeight; var5 < this.clipRight; var5++) {
            this.pixelBuffer[var4++] = var1;
         }

         var2 += this.stride;
      }
   }

   public void setDiffuseTexture(Class_517 var1) {
      this.diffuseTexture = var1;
   }

   public void setSphereMapTexture(Class_517 var1) {
      this.sphereMapTexture = var1;
   }

   public void sub_f6(int var1) {
      this.clipBottom = var1 | 0xFF000000;
   }

   public void setAlpha(int var1) {
      this.colorKey = var1;
   }

   public void sub_16b(Class_eda var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.y <= var4.y) {
         if (var2.y <= var3.y) {
            var5 = var2;
            if (var3.y <= var4.y) {
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
      } else if (var4.y < var3.y) {
         var5 = var4;
         if (var2.y < var3.y) {
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

      if (var5.y != var7.y) {
         var1.y = var5.y;
         var1.scanlineOffset = var5.y * this.stride + this.bufferWidth;
         int var8 = var7.x - var5.x;
         int var9 = sub_31c(var7.y - var5.y);
         int var12 = var8 * var9;
         int var10 = (var5.x << 16) + 32768;
         var8 = var6.x - var5.x;
         var9 = var6.y - var5.y;
         int var16 = (var8 << 16) - var12 * var9;
         int var17 = var16 >> 16;
         if (var17 == 0) {
            var17 = var16 > 0 ? 1 : -1;
         }

         int var15 = sub_31c(var17);
         var1.xLeftFixed = var10;
         var1.xRightFixed = var10;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.yEnd = var6.y;
            if (var15 > 0) {
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var13;
            } else {
               var1.dxLeftFixed = var13;
               var1.dxRightFixed = var12;
            }

            var1.drawSpan();
         }

         if (var6.y != var7.y) {
            var8 = var7.x - var6.x;
            var9 = sub_31c(var7.y - var6.y);
            int var14 = var8 * var9;
            int var11 = (var6.x << 16) + 32768;
            var1.yEnd = var7.y;
            if (var15 > 0) {
               var1.xRightFixed = var11;
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var14;
            } else {
               var1.xLeftFixed = var11;
               var1.dxLeftFixed = var14;
               var1.dxRightFixed = var12;
            }

            var1.drawSpan();
         }
      }
   }

   public void sub_1ad(Class_d00 var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.y <= var4.y) {
         if (var2.y <= var3.y) {
            var5 = var2;
            if (var3.y <= var4.y) {
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
      } else if (var4.y < var3.y) {
         var5 = var4;
         if (var2.y < var3.y) {
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

      if (var5.y != var7.y) {
         var1.y = var5.y;
         var1.scanlineOffset = var5.y * this.stride + this.bufferWidth;
         int var8 = var7.x - var5.x;
         int var9 = sub_31c(var7.y - var5.y);
         int var12 = var8 * var9;
         int var10 = (var5.x << 16) + 32768;
         int var15 = var7.z - var5.z;
         int var19 = var15 * var9;
         int var17 = (var5.z << 16) + 32768;
         var8 = var6.x - var5.x;
         var9 = var6.y - var5.y;
         var15 = var6.z - var5.z;
         int var22 = (var8 << 16) - var12 * var9;
         int var23 = var22 >> 16;
         if (var23 == 0) {
            var23 = var22 > 0 ? 1 : -1;
         }

         int var21 = sub_31c(var23);
         var1.dzDxFixed = (var15 - (var19 * var9 >> 16)) * var21;
         var1.xLeftFixed = var10;
         var1.xRightFixed = var10;
         var1.zFixed = var17;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.yEnd = var6.y;
            if (var21 > 0) {
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var13;
               var1.dzDyFixed = var19;
            } else {
               var1.dxLeftFixed = var13;
               var1.dxRightFixed = var12;
               var1.dzDyFixed = var15 * var9;
            }

            var1.drawSpan();
         }

         if (var6.y != var7.y) {
            var8 = var7.x - var6.x;
            var9 = sub_31c(var7.y - var6.y);
            int var14 = var8 * var9;
            int var11 = (var6.x << 16) + 32768;
            var1.yEnd = var7.y;
            if (var21 > 0) {
               var1.xRightFixed = var11;
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var14;
               var1.dzDyFixed = var19;
            } else {
               var1.xLeftFixed = var11;
               var1.dxLeftFixed = var14;
               var1.dxRightFixed = var12;
               var1.zFixed = (var6.z << 16) + 32768;
               var1.dzDyFixed = (var7.z - var6.z) * var9;
            }

            var1.drawSpan();
         }
      }
   }

   public void rasterizeTexturedTriangle(Class_1279 var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.y <= var4.y) {
         if (var2.y <= var3.y) {
            var5 = var2;
            if (var3.y <= var4.y) {
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
      } else if (var4.y < var3.y) {
         var5 = var4;
         if (var2.y < var3.y) {
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

      if (var5.y != var7.y) {
         var1.y = var5.y;
         var1.scanlineOffset = var5.y * this.stride + this.bufferWidth;
         int var8 = var7.x - var5.x;
         int var9 = sub_31c(var7.y - var5.y);
         int var12 = var8 * var9;
         int var10 = (var5.x << 16) + 32768;
         int var15 = var7.z - var5.z;
         int var16 = var7.u - var5.u;
         int var19 = var15 * var9;
         int var20 = var16 * var9;
         int var17 = (var5.z << 16) + 32768;
         int var18 = (var5.u << 16) + 32768;
         var8 = var6.x - var5.x;
         var9 = var6.y - var5.y;
         var15 = var6.z - var5.z;
         var16 = var6.u - var5.u;
         int var22 = (var8 << 16) - var12 * var9;
         int var23 = var22 >> 16;
         if (var23 == 0) {
            var23 = var22 > 0 ? 1 : -1;
         }

         int var21 = sub_31c(var23);
         var1.duDxFixed = (var15 - (var19 * var9 >> 16)) * var21;
         var1.dvDxFixed = (var16 - (var20 * var9 >> 16)) * var21;
         var1.xLeftFixed = var10;
         var1.xRightFixed = var10;
         var1.uFixed = var17;
         var1.vFixed = var18;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.yEnd = var6.y;
            if (var21 > 0) {
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var13;
               var1.duDyFixed = var19;
               var1.dvDyFixed = var20;
            } else {
               var1.dxLeftFixed = var13;
               var1.dxRightFixed = var12;
               var1.duDyFixed = var15 * var9;
               var1.dvDyFixed = var16 * var9;
            }

            var1.drawSpan();
         }

         if (var6.y != var7.y) {
            var8 = var7.x - var6.x;
            var9 = sub_31c(var7.y - var6.y);
            int var14 = var8 * var9;
            int var11 = (var6.x << 16) + 32768;
            var1.yEnd = var7.y;
            if (var21 > 0) {
               var1.xRightFixed = var11;
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var14;
               var1.duDyFixed = var19;
               var1.dvDyFixed = var20;
            } else {
               var1.xLeftFixed = var11;
               var1.dxLeftFixed = var14;
               var1.dxRightFixed = var12;
               var1.uFixed = (var6.z << 16) + 32768;
               var1.vFixed = (var6.u << 16) + 32768;
               var1.duDyFixed = (var7.z - var6.z) * var9;
               var1.dvDyFixed = (var7.u - var6.u) * var9;
            }

            var1.drawSpan();
         }
      }
   }

   public void rasterizeUnlitTriangle(ModelLoader var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.y <= var4.y) {
         if (var2.y <= var3.y) {
            var5 = var2;
            if (var3.y <= var4.y) {
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
      } else if (var4.y < var3.y) {
         var5 = var4;
         if (var2.y < var3.y) {
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

      if (var5.y != var7.y) {
         var1.y = var5.y;
         var1.scanlineOffset = var5.y * this.stride + this.bufferWidth;
         int var8 = var7.x - var5.x;
         int var9 = sub_31c(var7.y - var5.y);
         int var12 = var8 * var9;
         int var10 = (var5.x << 16) + 32768;
         int var15 = var7.z - var5.z;
         int var16 = var7.u - var5.u;
         int var17 = var7.v - var5.v;
         int var21 = var15 * var9;
         int var22 = var16 * var9;
         int var23 = var17 * var9;
         int var18 = (var5.z << 16) + 32768;
         int var19 = (var5.u << 16) + 32768;
         int var20 = (var5.v << 16) + 32768;
         var8 = var6.x - var5.x;
         var9 = var6.y - var5.y;
         var15 = var6.z - var5.z;
         var16 = var6.u - var5.u;
         var17 = var6.v - var5.v;
         int var25 = (var8 << 16) - var12 * var9;
         int var26 = var25 >> 16;
         if (var26 == 0) {
            var26 = var25 > 0 ? 1 : -1;
         }

         int var24 = sub_31c(var26);
         var1.lightFixed = (var15 - (var21 * var9 >> 16)) * var24;
         var1.dLightDyFixed = (var16 - (var22 * var9 >> 16)) * var24;
         var1.dLightDxFixed = (var17 - (var23 * var9 >> 16)) * var24;
         var1.xLeftFixed = var10;
         var1.xRightFixed = var10;
         var1.uFixed = var18;
         var1.vFixed = var19;
         var1.duDyFixed = var20;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.yEnd = var6.y;
            if (var24 > 0) {
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var13;
               var1.dvDyFixed = var21;
               var1.duDxFixed = var22;
               var1.dvDxFixed = var23;
            } else {
               var1.dxLeftFixed = var13;
               var1.dxRightFixed = var12;
               var1.dvDyFixed = var15 * var9;
               var1.duDxFixed = var16 * var9;
               var1.dvDxFixed = var17 * var9;
            }

            var1.drawSpan();
         }

         if (var6.y != var7.y) {
            var8 = var7.x - var6.x;
            var9 = sub_31c(var7.y - var6.y);
            int var14 = var8 * var9;
            int var11 = (var6.x << 16) + 32768;
            var1.yEnd = var7.y;
            if (var24 > 0) {
               var1.xRightFixed = var11;
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var14;
               var1.dvDyFixed = var21;
               var1.duDxFixed = var22;
               var1.dvDxFixed = var23;
            } else {
               var1.xLeftFixed = var11;
               var1.dxLeftFixed = var14;
               var1.dxRightFixed = var12;
               var1.uFixed = (var6.z << 16) + 32768;
               var1.vFixed = (var6.u << 16) + 32768;
               var1.duDyFixed = (var6.v << 16) + 32768;
               var1.dvDyFixed = (var7.z - var6.z) * var9;
               var1.duDxFixed = (var7.u - var6.u) * var9;
               var1.dvDxFixed = (var7.v - var6.v) * var9;
            }

            var1.drawSpan();
         }
      }
   }

   public void rasterizeLitTriangle(MeshLoader var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.y <= var4.y) {
         if (var2.y <= var3.y) {
            var5 = var2;
            if (var3.y <= var4.y) {
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
      } else if (var4.y < var3.y) {
         var5 = var4;
         if (var2.y < var3.y) {
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

      if (var5.y != var7.y) {
         var1.y = var5.y;
         var1.scanlineOffset = var5.y * this.stride + this.bufferWidth;
         int var8 = var7.x - var5.x;
         int var9 = sub_31c(var7.y - var5.y);
         int var12 = var8 * var9;
         int var10 = (var5.x << 16) + 32768;
         int var15 = var7.z - var5.z;
         int var16 = var7.u - var5.u;
         int var17 = var7.v - var5.v;
         int var18 = var7.light - var5.light;
         int var23 = var15 * var9;
         int var24 = var16 * var9;
         int var25 = var17 * var9;
         int var26 = var18 * var9;
         int var19 = (var5.z << 16) + 32768;
         int var20 = (var5.u << 16) + 32768;
         int var21 = (var5.v << 16) + 32768;
         int var22 = (var5.light << 16) + 32768;
         var8 = var6.x - var5.x;
         var9 = var6.y - var5.y;
         var15 = var6.z - var5.z;
         var16 = var6.u - var5.u;
         var17 = var6.v - var5.v;
         var18 = var6.light - var5.light;
         int var28 = (var8 << 16) - var12 * var9;
         int var29 = var28 >> 16;
         if (var29 == 0) {
            var29 = var28 > 0 ? 1 : -1;
         }

         int var27 = sub_31c(var29);
         var1.dLightDxFixed = (var15 - (var23 * var9 >> 16)) * var27;
         var1.normalZFixed = (var16 - (var24 * var9 >> 16)) * var27;
         var1.dNormalZDyFixed = (var17 - (var25 * var9 >> 16)) * var27;
         var1.dNormalZDxFixed = (var18 - (var26 * var9 >> 16)) * var27;
         var1.xLeftFixed = var10;
         var1.xRightFixed = var10;
         var1.uFixed = var19;
         var1.vFixed = var20;
         var1.duDyFixed = var21;
         var1.dvDyFixed = var22;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.yEnd = var6.y;
            if (var27 > 0) {
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var13;
               var1.duDxFixed = var23;
               var1.dvDxFixed = var24;
               var1.lightFixed = var25;
               var1.dLightDyFixed = var26;
            } else {
               var1.dxLeftFixed = var13;
               var1.dxRightFixed = var12;
               var1.duDxFixed = var15 * var9;
               var1.dvDxFixed = var16 * var9;
               var1.lightFixed = var17 * var9;
               var1.dLightDyFixed = var18 * var9;
            }

            var1.drawSpan();
         }

         if (var6.y != var7.y) {
            var8 = var7.x - var6.x;
            var9 = sub_31c(var7.y - var6.y);
            int var14 = var8 * var9;
            int var11 = (var6.x << 16) + 32768;
            var1.yEnd = var7.y;
            if (var27 > 0) {
               var1.xRightFixed = var11;
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var14;
               var1.duDxFixed = var23;
               var1.dvDxFixed = var24;
               var1.lightFixed = var25;
               var1.dLightDyFixed = var26;
            } else {
               var1.xLeftFixed = var11;
               var1.dxLeftFixed = var14;
               var1.dxRightFixed = var12;
               var1.uFixed = (var6.z << 16) + 32768;
               var1.vFixed = (var6.u << 16) + 32768;
               var1.duDyFixed = (var6.v << 16) + 32768;
               var1.dvDyFixed = (var6.light << 16) + 32768;
               var1.duDxFixed = (var7.z - var6.z) * var9;
               var1.dvDxFixed = (var7.u - var6.u) * var9;
               var1.lightFixed = (var7.v - var6.v) * var9;
               var1.dLightDyFixed = (var7.light - var6.light) * var9;
            }

            var1.drawSpan();
         }
      }
   }

   public void rasterizeSphereMapTriangle(Class_15d5 var1, Class_ae var2, Class_ae var3, Class_ae var4) {
      Class_ae var5;
      Class_ae var6;
      Class_ae var7;
      if (var2.y <= var4.y) {
         if (var2.y <= var3.y) {
            var5 = var2;
            if (var3.y <= var4.y) {
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
      } else if (var4.y < var3.y) {
         var5 = var4;
         if (var2.y < var3.y) {
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

      if (var5.y != var7.y) {
         var1.y = var5.y;
         var1.scanlineOffset = var5.y * this.stride + this.bufferWidth;
         int var8 = var7.x - var5.x;
         int var9 = sub_31c(var7.y - var5.y);
         int var12 = var8 * var9;
         int var10 = (var5.x << 16) + 32768;
         int var15 = var7.z - var5.z;
         int var16 = var7.u - var5.u;
         int var17 = var7.v - var5.v;
         int var18 = var7.light - var5.light;
         int var19 = var7.normalZ - var5.normalZ;
         int var25 = var15 * var9;
         int var26 = var16 * var9;
         int var27 = var17 * var9;
         int var28 = var18 * var9;
         int var29 = var19 * var9;
         int var20 = (var5.z << 16) + 32768;
         int var21 = (var5.u << 16) + 32768;
         int var22 = (var5.v << 16) + 32768;
         int var23 = (var5.light << 16) + 32768;
         int var24 = (var5.normalZ << 16) + 32768;
         var8 = var6.x - var5.x;
         var9 = var6.y - var5.y;
         var15 = var6.z - var5.z;
         var16 = var6.u - var5.u;
         var17 = var6.v - var5.v;
         var18 = var6.light - var5.light;
         var19 = var6.normalZ - var5.normalZ;
         int var31 = (var8 << 16) - var12 * var9;
         int var32 = var31 >> 16;
         if (var32 == 0) {
            var32 = var31 > 0 ? 1 : -1;
         }

         int var30 = sub_31c(var32);
         var1.sphereVFixed = (var15 - (var25 * var9 >> 16)) * var30;
         var1.dSphereUDyFixed = (var16 - (var26 * var9 >> 16)) * var30;
         var1.dSphereVDyFixed = (var17 - (var27 * var9 >> 16)) * var30;
         var1.dSphereUDxFixed = (var18 - (var28 * var9 >> 16)) * var30;
         var1.dSphereVDxFixed = (var19 - (var29 * var9 >> 16)) * var30;
         var1.xLeftFixed = var10;
         var1.xRightFixed = var10;
         var1.uFixed = var20;
         var1.vFixed = var21;
         var1.duDyFixed = var22;
         var1.dvDyFixed = var23;
         var1.duDxFixed = var24;
         if (var9 > 0) {
            var9 = sub_31c(var9);
            int var13 = var8 * var9;
            var1.yEnd = var6.y;
            if (var30 > 0) {
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var13;
               var1.dvDxFixed = var25;
               var1.lightFixed = var26;
               var1.dLightDyFixed = var27;
               var1.dLightDxFixed = var28;
               var1.sphereUFixed = var29;
            } else {
               var1.dxLeftFixed = var13;
               var1.dxRightFixed = var12;
               var1.dvDxFixed = var15 * var9;
               var1.lightFixed = var16 * var9;
               var1.dLightDyFixed = var17 * var9;
               var1.dLightDxFixed = var18 * var9;
               var1.sphereUFixed = var19 * var9;
            }

            var1.drawSpan();
         }

         if (var6.y != var7.y) {
            var8 = var7.x - var6.x;
            var9 = sub_31c(var7.y - var6.y);
            int var14 = var8 * var9;
            int var11 = (var6.x << 16) + 32768;
            var1.yEnd = var7.y;
            if (var30 > 0) {
               var1.xRightFixed = var11;
               var1.dxLeftFixed = var12;
               var1.dxRightFixed = var14;
               var1.dvDxFixed = var25;
               var1.lightFixed = var26;
               var1.dLightDyFixed = var27;
               var1.dLightDxFixed = var28;
               var1.sphereUFixed = var29;
            } else {
               var1.xLeftFixed = var11;
               var1.dxLeftFixed = var14;
               var1.dxRightFixed = var12;
               var1.uFixed = (var6.z << 16) + 32768;
               var1.vFixed = (var6.u << 16) + 32768;
               var1.duDyFixed = (var6.v << 16) + 32768;
               var1.dvDyFixed = (var6.light << 16) + 32768;
               var1.duDxFixed = (var6.normalZ << 16) + 32768;
               var1.dvDxFixed = (var7.z - var6.z) * var9;
               var1.lightFixed = (var7.u - var6.u) * var9;
               var1.dLightDyFixed = (var7.v - var6.v) * var9;
               var1.dLightDxFixed = (var7.light - var6.light) * var9;
               var1.sphereUFixed = (var7.normalZ - var6.normalZ) * var9;
            }

            var1.drawSpan();
         }
      }
   }

   public int computeOutcode(Class_ae var1) {
      byte var2 = 0;
      if (var1.x < this.bufferHeight) {
         var2 |= 1;
      } else if (this.clipRight <= var1.x) {
         var2 |= 2;
      }

      if (var1.y < this.clipLeft) {
         var2 |= 4;
      } else if (this.clipTop <= var1.y) {
         var2 |= 8;
      }

      return var2;
   }

   private static int sub_31c(int var0) {
      return 65536 / var0;
   }

   static int[] getPixelBuffer(Config var0) {
      return var0.pixelBuffer;
   }

   static int getClipBottom(Config var0) {
      return var0.clipBottom;
   }

   static int getStride(Config var0) {
      return var0.stride;
   }

   static int getClipLeft(Config var0) {
      return var0.clipLeft;
   }

   static int getClipBottom(Config var0) {
      return var0.clipTop;
   }

   static int getBufferHeight(Config var0) {
      return var0.bufferHeight;
   }

   static int getClipRight(Config var0) {
      return var0.clipRight;
   }

   static int[] getColorTable() {
      return blendTable;
   }

   static Class_517 getDiffuseTexture(Config var0) {
      return var0.diffuseTexture;
   }

   static int getColorKey(Config var0) {
      return var0.colorKey;
   }

   static Class_517 getSphereMapTexture(Config var0) {
      return var0.sphereMapTexture;
   }

   static {
      int var0 = 65793;

      for (int var1 = 0; var1 < 129; var1++) {
         blendTable[var1] = 0;
      }

      for (int var2 = 1; var2 < 255; var2++) {
         blendTable[var2 + 128] = 65793 * var2;
      }

      for (int var3 = -1; var3 < 128; var3++) {
         blendTable[var3 + 384] = 16777215;
      }
   }
}
