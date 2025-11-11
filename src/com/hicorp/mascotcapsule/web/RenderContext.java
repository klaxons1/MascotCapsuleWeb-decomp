package com.hicorp.mascotcapsule.web;

final class RenderContext {
   public static final int var_63 = 1;
   public static final int var_84 = 2;
   public static final int var_e3 = 4096;
   public static final int var_110 = 8192;
   public static final int var_15f = 16384;
   public static final int var_183 = 32768;
   public static final int var_194 = 255;
   public static final int var_1eb = 16;
   public static final int var_240 = 255;
   public static final int var_2ef = 24;
   public static final int var_309 = 0;
   public static final int var_333 = 1;
   private static final boolean var_390 = true;
   private static final boolean var_3d2 = true;
   private static final boolean var_404 = false;
   protected int[] var_426;
   protected float[] var_477;
   protected static final int var_4b9 = 256;
   protected boolean var_4da = true;
   protected Class_1498[] var_53d;
   protected Class_1438 var_550 = null;
   protected float[] var_56b;
   protected static final int var_5a1 = 3;
   protected float var_5ef;
   protected float var_617;
   protected float var_64e;
   protected int var_67b;
   protected int var_6c6;
   protected final BoundingBox var_6fb = new BoundingBox();
   protected Class_6ed var_70d;
   protected Class_517 var_749 = null;
   protected Class_517 var_7a3 = null;
   protected boolean var_7e2;
   protected float var_80e;
   protected final Vector3f var_82c = new Vector3f();
   float var_852;
   private float var_8ad;
   private boolean var_8be;
   private float var_918;
   private static final float var_96e = 640.0F;
   private static final float var_992 = MatrixUtils.sub_1d7(60.0F);
   private float var_9da;
   private float var_a3a;
   private static final Vector3f var_a9e = new Vector3f(0.0F, 0.0F, -1.0F);

   private final float sub_1f() {
      return (float)(this.var_a3a / 2.0 / Math.tan(this.var_9da / 2.0));
   }

   public RenderContext(Class_6ed var1) {
      this.var_426 = new int[512];
      this.var_477 = new float[256];
      this.var_56b = new float[0];
      this.var_53d = null;
      this.var_67b = 0;
      this.var_6c6 = 0;
      this.var_6fb.sub_72();
      this.var_749 = null;
      this.var_70d = var1;
      this.var_7e2 = false;
      this.var_80e = 0.4F;
      this.var_82c.sub_7a(1.0F, -1.0F, 0.0F);
      this.var_82c.normalize();
      this.var_852 = 1.0F;
      this.var_9da = var_992;
      this.var_a3a = 640.0F;
      this.var_8ad = this.sub_1f();
      this.var_8be = false;
      this.var_918 = 1.0F;
   }

   public final void sub_73(float var1) {
      this.var_8be = true;
      this.var_918 = var1;
   }

   public final void sub_a2() {
      this.var_8be = false;
      this.var_918 = 1.0F;
   }

   protected Class_1438 sub_c5() {
      Class_1438 var1 = this.var_550;
      if (var1 != null) {
         this.var_550 = (Class_1438)var1.var_12a;
         return var1;
      } else {
         return new Class_1438(this);
      }
   }

   protected void sub_e3(Class_1438 var1) {
      var1.var_12a = this.var_550;
      this.var_550 = var1;
   }

   protected void sub_f7(Class_77c var1, boolean var2) {
      if (this.var_477.length < var1.sub_175()) {
         this.var_426 = new int[var1.sub_175() * 2];
         this.var_477 = new float[var1.sub_175()];
      }

      if (var2 && this.var_56b.length < var1.sub_175() * 3) {
         this.var_56b = new float[var1.sub_175() * 3];
      }
   }

   public void sub_106(int var1, float var2, float var3) {
      Class_8ed.sub_7d(this.var_4da);
      Class_8ed.sub_7d(var1 > 0);
      Class_8ed.sub_7d(var2 > 0.0F && var2 < var3);
      this.var_53d = new Class_1498[var1];
      this.var_5ef = var2;
      this.var_617 = var3;
      this.var_64e = (this.var_617 - this.var_5ef) / this.var_53d.length;
   }

   public void sub_154(float var1, float var2) {
      Class_8ed.sub_7d(var1 > 0.0F && var1 < var2);
      Class_8ed.sub_7d(this.var_53d.length > 0);
      this.var_5ef = var1;
      this.var_617 = var2;
      this.var_64e = (this.var_617 - this.var_5ef) / this.var_53d.length;
   }

   public void sub_19c(int var1, int var2) {
      this.var_67b = var1;
      this.var_6c6 = var2;
   }

   private final int sub_1f8(float var1) {
      return !(var1 < this.var_5ef) && !(var1 >= this.var_617) ? (int)((var1 - this.var_5ef) / this.var_64e) : -1;
   }

   public final void sub_21e() {
      this.var_6fb.sub_72();
   }

   public final void sub_23d(BoundingBox var1) {
      var1.sub_5d(this.var_6fb);
   }

   public void sub_269(Class_517 var1) {
      this.var_749 = var1;
   }

   public void sub_2b7(Class_517 var1) {
      this.var_7a3 = var1;
   }

   public void sub_2dd(Class_77c var1, Class_339 var2) {
      try {
         this.var_4da = false;
         boolean var3 = this.var_7e2 && var1.sub_1e6() != null;
         this.sub_f7(var1, var3);
         Transform3D var4 = new Transform3D();
         Transform3D var5 = new Transform3D();
         Transform3D var6 = new Transform3D();
         Transform3D var7 = new Transform3D();
         Transform3D var8 = new Transform3D();
         Transform3D var9 = new Transform3D();
         Transform3D var10 = new Transform3D();
         Vector3f var11 = new Vector3f();
         Vector3f var12 = new Vector3f();
         Vector3f var13 = new Vector3f();
         int[] var14 = this.var_426;
         float[] var15 = this.var_477;
         var2.sub_50(var1.sub_df(), var4);
         int var16 = var1.sub_85();
         int var17 = 0;
         new Vector3f();

         for (int var19 = 0; var19 < var16; var19++) {
            Class_13f var20 = var1.sub_126(var19);
            var20.sub_16b(var7);
            var5.sub_2b3(var4, var7);
            if (this.var_8be) {
               var5.sub_212(var1.sub_198(), var14, var15, var17, var20.sub_59(), this.var_918);
            } else {
               var5.sub_249(var1.sub_198(), var14, var15, var17, var20.sub_59(), this.var_8ad);
            }

            if (var3) {
               var1.sub_df().sub_12f(var6);
               var6.sub_311(var7);
               var8.sub_3d7(var6);
               var8.sub_447();
               var8.sub_289(this.var_82c, var11);
               var12.setScaled(var11, this.var_852);
               var9.sub_3d7(var5);
               var9.sub_447();
               var9.sub_289(var_a9e, var13);
               var10.sub_37(var5);
               var10.sub_447();
               if (this.var_7a3 != null) {
                  this.sub_477(var12, var10, var1.sub_1e6(), var17, var20.sub_59());
               } else {
                  this.sub_42e(var11, var12, var13, var1.sub_1e6(), var17, var20.sub_59());
               }
            }

            var17 += var20.sub_59();
         }

         int var48 = this.var_7a3 != null ? this.var_7a3.sub_10a() : 0;
         int var21 = this.var_7a3 != null ? this.var_7a3.sub_11a() : 0;
         Class_ae[] var22 = new Class_ae[4];
         Class_1438 var23 = this.sub_c5();
         var22[0] = var23.var_389;
         var22[1] = var23.var_3b9;
         var22[2] = var23.var_3ed;
         var22[3] = var23.var_46c;

         for (int var24 = 0; var24 < var1.sub_24a(); var24++) {
            Class_12f var25 = var1.sub_28f(var24);
            int var26 = var25.var_55;
            Class_8ed.sub_7d(var26 >= 3 && var26 <= 4);
            int var27 = var25.var_37;
            int var28 = var25.var_a0 * 2;
            int var29 = var25.var_15b * 2;
            int var30 = var25.var_1f4 * 2;
            if ((var27 & 1) == 0) {
               int var31 = (var14[var29 + 0] - var14[var28 + 0]) * (var14[var30 + 1] - var14[var29 + 1])
                  - (var14[var29 + 1] - var14[var28 + 1]) * (var14[var30 + 0] - var14[var29 + 0]);
               if (var26 == 3) {
                  if (var31 <= 0) {
                     continue;
                  }
               } else {
                  int var32 = var25.var_2ba * 2;
                  int var33 = (var14[var30 + 0] - var14[var29 + 0]) * (var14[var32 + 1] - var14[var30 + 1])
                     - (var14[var30 + 1] - var14[var29 + 1]) * (var14[var32 + 0] - var14[var30 + 0]);
                  if (var31 - var33 <= 0) {
                     continue;
                  }
               }
            }

            float var49 = Float.MAX_VALUE;
            float var50 = -Float.MAX_VALUE;
            var22[0].var_59 = var14[var28 + 0] + this.var_67b;
            var22[0].var_9f = var14[var28 + 1] + this.var_6c6;
            var22[1].var_59 = var14[var29 + 0] + this.var_67b;
            var22[1].var_9f = var14[var29 + 1] + this.var_6c6;
            var22[2].var_59 = var14[var30 + 0] + this.var_67b;
            var22[2].var_9f = var14[var30 + 1] + this.var_6c6;
            if (var15[var25.var_a0] < var49) {
               var49 = var15[var25.var_a0];
            }

            if (var15[var25.var_15b] < var49) {
               var49 = var15[var25.var_15b];
            }

            if (var15[var25.var_1f4] < var49) {
               var49 = var15[var25.var_1f4];
            }

            if (var15[var25.var_a0] > var50) {
               var50 = var15[var25.var_a0];
            }

            if (var15[var25.var_15b] > var50) {
               var50 = var15[var25.var_15b];
            }

            if (var15[var25.var_1f4] > var50) {
               var50 = var15[var25.var_1f4];
            }

            int var35 = this.var_70d.sub_2de(var22[0]);
            int var36 = this.var_70d.sub_2de(var22[1]);
            int var37 = this.var_70d.sub_2de(var22[2]);
            int var51 = var35 | var36 | var37;
            int var34 = var35 & var36 & var37;
            if (var26 == 4) {
               var35 = var25.var_2ba * 2;
               var22[3].var_59 = var14[var35 + 0] + this.var_67b;
               var22[3].var_9f = var14[var35 + 1] + this.var_6c6;
               if (var15[var25.var_2ba] < var49) {
                  var49 = var15[var25.var_2ba];
               }

               if (var15[var25.var_2ba] > var50) {
                  var50 = var15[var25.var_2ba];
               }

               var36 = this.var_70d.sub_2de(var22[3]);
               var51 |= var36;
               var34 &= var36;
            }

            var35 = this.sub_1f8((var49 + var50) / 2.0F);
            if (var34 == 0 && var35 >= 0) {
               boolean var55;
               if (!var3) {
                  var27 &= -32769;
                  var55 = false;
               } else {
                  var55 = (var27 & 32768) != 0;
               }

               if (!var55) {
                  var22[0].var_100 = var25.var_bf;
                  var22[0].var_114 = var25.var_103;
                  var22[1].var_100 = var25.var_1a3;
                  var22[1].var_114 = var25.var_1d0;
                  var22[2].var_100 = var25.var_23e;
                  var22[2].var_114 = var25.var_280;
                  if (var26 == 4) {
                     var22[3].var_100 = var25.var_30e;
                     var22[3].var_114 = var25.var_356;
                  }
               } else {
                  var37 = var27 >>> 16 & 0xFF;
                  int var38 = 255 - var37;
                  if (var37 <= 0) {
                     var23.var_2c5 = null;
                     var22[0].var_100 = var25.var_bf;
                     var22[0].var_114 = var25.var_103;
                     var22[1].var_100 = var25.var_1a3;
                     var22[1].var_114 = var25.var_1d0;
                     var22[2].var_100 = var25.var_23e;
                     var22[2].var_114 = var25.var_280;
                     var22[0].var_181 = var22[1].var_181 = var22[2].var_181 = 0;
                     int var39 = (int)(this.var_56b[var25.var_a0 * 3] * var38);
                     if (var39 > 255) {
                        var39 = 255;
                     }

                     var22[0].var_165 = var39;
                     var39 = (int)(this.var_56b[var25.var_15b * 3] * var38);
                     if (var39 > 255) {
                        var39 = 255;
                     }

                     var22[1].var_165 = var39;
                     var39 = (int)(this.var_56b[var25.var_1f4 * 3] * var38);
                     if (var39 > 255) {
                        var39 = 255;
                     }

                     var22[2].var_165 = var39;
                     if (var26 == 4) {
                        var22[3].var_100 = var25.var_30e;
                        var22[3].var_114 = var25.var_356;
                        var22[3].var_181 = 0;
                        var39 = (int)(this.var_56b[var25.var_2ba * 3] * var38);
                        if (var39 > 255) {
                           var39 = 255;
                        }

                        var22[3].var_165 = var39;
                     }
                  } else if (this.var_7a3 != null) {
                     var23.var_2c5 = this.var_7a3;
                     var22[0].var_100 = var25.var_bf;
                     var22[0].var_114 = var25.var_103;
                     var22[1].var_100 = var25.var_1a3;
                     var22[1].var_114 = var25.var_1d0;
                     var22[2].var_100 = var25.var_23e;
                     var22[2].var_114 = var25.var_280;
                     int var40 = var25.var_a0 * 3;
                     int var41 = var25.var_15b * 3;
                     int var42 = var25.var_1f4 * 3;
                     int var60 = (int)(this.var_56b[var40 + 0] * var38);
                     if (var60 > 255) {
                        var60 = 255;
                     }

                     var22[0].var_165 = var60;
                     var60 = (int)(this.var_56b[var41 + 0] * var38);
                     if (var60 > 255) {
                        var60 = 255;
                     }

                     var22[1].var_165 = var60;
                     var60 = (int)(this.var_56b[var42 + 0] * var38);
                     if (var60 > 255) {
                        var60 = 255;
                     }

                     var22[2].var_165 = var60;
                     var22[0].var_181 = (int)(this.var_56b[var40 + 1] * var48);
                     var22[0].var_1a4 = (int)(this.var_56b[var40 + 2] * var21);
                     var22[1].var_181 = (int)(this.var_56b[var41 + 1] * var48);
                     var22[1].var_1a4 = (int)(this.var_56b[var41 + 2] * var21);
                     var22[2].var_181 = (int)(this.var_56b[var42 + 1] * var48);
                     var22[2].var_1a4 = (int)(this.var_56b[var42 + 2] * var21);
                     if (var26 == 4) {
                        var22[3].var_100 = var25.var_30e;
                        var22[3].var_114 = var25.var_356;
                        int var43 = var25.var_2ba * 3;
                        var60 = (int)(this.var_56b[var43 + 0] * var38);
                        if (var60 > 255) {
                           var60 = 255;
                        }

                        var22[3].var_165 = var60;
                        var22[3].var_181 = (int)(this.var_56b[var43 + 1] * var48);
                        var22[3].var_1a4 = (int)(this.var_56b[var43 + 2] * var21);
                     }
                  } else {
                     var23.var_2c5 = null;
                     var22[0].var_100 = var25.var_bf;
                     var22[0].var_114 = var25.var_103;
                     var22[1].var_100 = var25.var_1a3;
                     var22[1].var_114 = var25.var_1d0;
                     var22[2].var_100 = var25.var_23e;
                     var22[2].var_114 = var25.var_280;
                     int var76 = var25.var_a0 * 3;
                     int var77 = var25.var_15b * 3;
                     int var44 = var25.var_1f4 * 3;
                     int var64 = (int)(this.var_56b[var76 + 0] * var38);
                     if (var64 > 255) {
                        var64 = 255;
                     }

                     var22[0].var_165 = var64;
                     var64 = (int)(this.var_56b[var77 + 0] * var38);
                     if (var64 > 255) {
                        var64 = 255;
                     }

                     var22[1].var_165 = var64;
                     var64 = (int)(this.var_56b[var44 + 0] * var38);
                     if (var64 > 255) {
                        var64 = 255;
                     }

                     var22[2].var_165 = var64;
                     float var72 = this.var_56b[var76 + 1];
                     int var68;
                     if (var72 > 0.0F) {
                        int var45 = var27 >>> 24 & 0xFF;
                        var68 = (int)((float)Math.pow(var72, 0.25 * var45) * this.var_852 * var37);
                        if (var68 > 255) {
                           var68 = 255;
                        }

                        if (var68 < 0) {
                           var68 = 0;
                        }
                     } else {
                        var68 = 0;
                     }

                     var22[0].var_181 = var68;
                     var72 = this.var_56b[var77 + 1];
                     if (var72 > 0.0F) {
                        int var78 = var27 >>> 24 & 0xFF;
                        var68 = (int)((float)Math.pow(var72, 0.25 * var78) * this.var_852 * var37);
                        if (var68 > 255) {
                           var68 = 255;
                        }

                        if (var68 < 0) {
                           var68 = 0;
                        }
                     } else {
                        var68 = 0;
                     }

                     var22[1].var_181 = var68;
                     var72 = this.var_56b[var44 + 1];
                     if (var72 > 0.0F) {
                        int var79 = var27 >>> 24 & 0xFF;
                        var68 = (int)((float)Math.pow(var72, 0.25 * var79) * this.var_852 * var37);
                        if (var68 > 255) {
                           var68 = 255;
                        }

                        if (var68 < 0) {
                           var68 = 0;
                        }
                     } else {
                        var68 = 0;
                     }

                     var22[2].var_181 = var68;
                     if (var26 == 4) {
                        var22[3].var_100 = var25.var_30e;
                        var22[3].var_114 = var25.var_356;
                        int var80 = var25.var_2ba * 3;
                        var64 = (int)(this.var_56b[var80 + 0] * var38);
                        if (var64 > 255) {
                           var64 = 255;
                        }

                        var22[3].var_165 = var64;
                        var72 = this.var_56b[var80 + 1];
                        if (var72 > 0.0F) {
                           int var46 = var27 >>> 24 & 0xFF;
                           var68 = (int)((float)Math.pow(var72, 0.25 * var46) * this.var_852 * var37);
                           if (var68 > 255) {
                              var68 = 255;
                           }

                           if (var68 < 0) {
                              var68 = 0;
                           }
                        } else {
                           var68 = 0;
                        }

                        var22[3].var_181 = var68;
                     }
                  }
               }

               var23.var_1c2 = var26;
               var23.var_1f3 = var51;
               var23.var_1db = var27;
               var23.var_2bb = this.var_749;
               var23.sub_ac(this.var_6fb);
               var23.var_12a = this.var_53d[var35];
               this.var_53d[var35] = var23;
               var23 = this.sub_c5();
               var22[0] = var23.var_389;
               var22[1] = var23.var_3b9;
               var22[2] = var23.var_3ed;
               var22[3] = var23.var_46c;
            }
         }

         this.sub_e3(var23);
      } catch (NullPointerException var47) {
      }
   }

   public void sub_2f2() {
      if (!this.var_4da) {
         for (int var1 = this.var_53d.length - 1; var1 >= 0; var1--) {
            Class_1498 var2 = this.var_53d[var1];

            while (var2 != null) {
               Class_1498 var3 = var2.var_12a;
               switch (var2.var_5c) {
                  case 1:
                     this.sub_364((Class_1438)var2);
                     this.sub_e3((Class_1438)var2);
                  default:
                     var2 = var3;
               }
            }

            this.var_53d[var1] = null;
         }

         this.var_4da = true;
      }
   }

   public void sub_34b() {
      if (!this.var_4da) {
         for (int var1 = this.var_53d.length - 1; var1 >= 0; var1--) {
            Class_1498 var2 = this.var_53d[var1];

            while (var2 != null) {
               Class_1498 var3 = var2.var_12a;
               switch (var2.var_5c) {
                  case 1:
                     this.sub_e3((Class_1438)var2);
                  default:
                     var2 = var3;
               }
            }

            this.var_53d[var1] = null;
         }

         this.var_4da = true;
      }
   }

   protected void sub_364(Class_1438 var1) {
      int var2 = var1.var_1db;
      this.var_70d.sub_99(var1.var_2bb);
      int var3 = (var2 & 2) != 0 ? 1 : 0;
      int var4 = var1.var_1f3 != 0 ? 1 : 0;
      int var5 = (var2 & 4080) >>> 4;
      int var6;
      if ((var2 & 4096) != 0 && var5 != 255) {
         var6 = var5 != 0 && var5 != 128 ? 2 : 1;
      } else if ((var2 & 8192) != 0) {
         var6 = 3;
      } else {
         var6 = 0;
      }

      this.var_70d.sub_10c(var5);

      try {
         if ((var2 & 32768) != 0) {
            if (var1.var_2c5 != null) {
               this.var_70d.sub_b1(var1.var_2c5);
               Class_15d5 var7 = this.var_70d.var_88e[var6][var3][var4];
               this.var_70d.sub_287(var7, var1.var_389, var1.var_3b9, var1.var_3ed);
               if (var1.var_1c2 == 4) {
                  this.var_70d.sub_287(var7, var1.var_3b9, var1.var_3ed, var1.var_46c);
               }
            } else {
               int var9 = var1.var_3b9.var_181 + var1.var_3ed.var_181;
               if (var1.var_389.var_181 + var9 == 0) {
                  this.var_70d.sub_235(this.var_70d.var_83b[var6][var3][var4], var1.var_389, var1.var_3b9, var1.var_3ed);
               } else {
                  this.var_70d.sub_253(this.var_70d.var_85a[var6][var3][var4], var1.var_389, var1.var_3b9, var1.var_3ed);
               }

               if (var1.var_1c2 == 4) {
                  if (var1.var_46c.var_181 + var9 == 0) {
                     this.var_70d.sub_235(this.var_70d.var_83b[var6][var3][var4], var1.var_3b9, var1.var_3ed, var1.var_46c);
                  } else {
                     this.var_70d.sub_253(this.var_70d.var_85a[var6][var3][var4], var1.var_3b9, var1.var_3ed, var1.var_46c);
                  }
               }
            }
         } else {
            Class_1279 var10 = this.var_70d.var_81c[var6][var3][var4];
            this.var_70d.sub_20e(var10, var1.var_389, var1.var_3b9, var1.var_3ed);
            if (var1.var_1c2 == 4) {
               this.var_70d.sub_20e(var10, var1.var_3b9, var1.var_3ed, var1.var_46c);
            }
         }
      } catch (ArrayIndexOutOfBoundsException var8) {
      }
   }

   public void sub_39e(boolean var1) {
      this.var_7e2 = var1;
   }

   public void sub_3ba(float var1) {
      this.var_80e = var1;
   }

   public void sub_3cc(Vector3f var1, float var2) {
      this.var_82c.sub_5e(var1);
      this.var_82c.normalize();
      this.var_82c.negate();
      this.var_852 = var2;
   }

   protected void sub_42e(Vector3f var1, Vector3f var2, Vector3f var3, Vector3f[] var4, int var5, int var6) {
      float var7 = var1.x;
      float var8 = var1.y;
      float var9 = var1.z;
      float var10 = var7 + var3.x;
      float var11 = var8 + var3.y;
      float var12 = var9 + var3.z;
      float var13 = 1.0F / (float)Math.sqrt(var10 * var10 + var11 * var11 + var12 * var12);
      var10 *= var13;
      var11 *= var13;
      var12 *= var13;
      int var14 = var5;
      int var15 = var5 * 3;

      for (int var16 = 0; var16 < var6; var16++) {
         Vector3f var17 = var4[var14];
         float var18 = this.var_80e;
         float var19 = var17.dot(var2);
         if (var19 > 0.0F) {
            var18 += var19;
         }

         this.var_56b[var15 + 0] = var18;
         float var20 = 0.0F;
         if (var19 > 0.0F) {
            float var21 = var17.x * var10 + var17.y * var11 + var17.z * var12;
            if (var21 > 0.0F) {
               var20 = var21;
            }
         }

         this.var_56b[var15 + 1] = var20;
         var14++;
         var15 += 3;
      }
   }

   protected void sub_477(Vector3f var1, Transform3D var2, Vector3f[] var3, int var4, int var5) {
      float var6 = var1.x;
      float var7 = var1.y;
      float var8 = var1.z;
      float var9 = var2.var_13 * 0.5F;
      float var10 = var2.var_ad * 0.5F;
      float var11 = var2.var_c2 * 0.5F;
      float var12 = var2.var_e7 * 0.5F;
      float var13 = var2.var_138 * 0.5F;
      float var14 = var2.var_191 * 0.5F;
      int var15 = var4;
      int var16 = var4 * 3;

      for (int var17 = 0; var17 < var5; var17++) {
         Vector3f var18 = var3[var15];
         float var19 = this.var_80e;
         float var20 = var6 * var18.x + var7 * var18.y + var8 * var18.z;
         if (var20 > 0.0F) {
            var19 += var20;
         }

         this.var_56b[var16 + 0] = var19;
         this.var_56b[var16 + 1] = var9 * var18.x + var10 * var18.y + var11 * var18.z + 0.5F;
         this.var_56b[var16 + 2] = var12 * var18.x + var13 * var18.y + var14 * var18.z + 0.5F;
         var15++;
         var16 += 3;
      }
   }
}
