package com.hicorp.mascotcapsule.web;

final class Config {
   public static final int COLOR_TRANSPARENT = -1;
   private static final int COLOR_SHIFT_8 = 8;
   private static final int BLEND_OPAQUE = 0;
   private static final int FIXED_SHIFT = 16;
   private static final int FIXED_HALF = 32768;
   private static final int ALPHA_OPAQUE = -16777216;
   private static final int RB_MASK = 16711935;
   private static final int G_MASK = 65280;
   private static final int COLOR_MASK = 16711422;
   private static final int COLOR_MASK_ALT = 16711422;
   private static final int ALPHA_STEP = 16843008;
   private static final int BLEND_FACTOR = 8355711;
   private static final int GREEN_ALPHA_MASK = -16711936;
   private static final int RED_MASK = 16711680;
   private static final int RED_HIGH_BIT = 8388608;
   private static final int TABLE_MASK = 511;
   private static final int[] blendTable = new int[512];
   private int[] pixelBuffer;
   private int stride;
   private int pixelOffset;
   private int clipLeft;
   private int clipTop;
   private int clipRight;
   private int clipBottom;
   private int fillColor;
   private int blendAlpha;
   private Texture diffuseTexture;
   private Texture sphereMapTexture;
   public final FlatDrawer[][] flatDrawers = new FlatDrawer[2][];
   public final LineDrawer[] lineDrawers = new LineDrawer[2];
   public final TexturedDrawer[] litColorDrawers = new TexturedDrawer[2];
   public final TexturedDrawer[][][] texturedDrawers = new TexturedDrawer[4][][];
   public final UnlitDrawer[][][] unlitDrawers = new UnlitDrawer[4][][];
   public final LitDrawer[][][] litDrawers = new LitDrawer[4][][];
   public final SphereMapDrawer[][][] sphereMapDrawers = new SphereMapDrawer[4][][];

   public Config() {
      this.flatDrawers[0] = new FlatDrawer[2];
      this.flatDrawers[0][0] = new FlatDrawer_Opaque_Triangle(this);
      this.flatDrawers[0][1] = new FlatDrawer_Opaque_Quad(this);
      this.flatDrawers[1] = new FlatDrawer[2];
      this.flatDrawers[1][0] = new FlatDrawer_Alpha_Triangle(this);
      this.flatDrawers[1][1] = new FlatDrawer_Alpha_Quad(this);
      this.lineDrawers[0] = new LineDrawer_Opaque(this);
      this.lineDrawers[1] = new LineDrawer_Alpha(this);
      this.litColorDrawers[0] = new LitColorDrawer_Opaque(this);
      this.litColorDrawers[1] = new LitColorDrawer_Alpha(this);
      this.texturedDrawers[0] = new TexturedDrawer[2][];
      this.texturedDrawers[0][0] = new TexturedDrawer[2];
      this.texturedDrawers[0][0][0] = new TexturedDrawer_T0_Opaque_Triangle(this);
      this.texturedDrawers[0][0][1] = new TexturedDrawer_T0_Opaque_Quad(this);
      this.texturedDrawers[0][1] = new TexturedDrawer[2];
      this.texturedDrawers[0][1][0] = new TexturedDrawer_T0_Alpha_Triangle(this);
      this.texturedDrawers[0][1][1] = new TexturedDrawer_T0_Alpha_Quad(this);
      this.texturedDrawers[1] = new TexturedDrawer[2][];
      this.texturedDrawers[1][0] = new TexturedDrawer[2];
      this.texturedDrawers[1][0][0] = new TexturedDrawer_T1_Opaque_Triangle(this);
      this.texturedDrawers[1][0][1] = new TexturedDrawer_T1_Opaque_Quad(this);
      this.texturedDrawers[1][1] = new TexturedDrawer[2];
      this.texturedDrawers[1][1][0] = new TexturedDrawer_T1_Alpha_Triangle(this);
      this.texturedDrawers[1][1][1] = new TexturedDrawer_T1_Alpha_Quad(this);
      this.texturedDrawers[2] = new TexturedDrawer[2][];
      this.texturedDrawers[2][0] = new TexturedDrawer[2];
      this.texturedDrawers[2][0][0] = new TexturedDrawer_T2_Opaque_Triangle(this);
      this.texturedDrawers[2][0][1] = new TexturedDrawer_T2_Opaque_Quad(this);
      this.texturedDrawers[2][1] = new TexturedDrawer[2];
      this.texturedDrawers[2][1][0] = new TexturedDrawer_T2_Alpha_Triangle(this);
      this.texturedDrawers[2][1][1] = new TexturedDrawer_T2_Alpha_Quad(this);
      this.texturedDrawers[3] = new TexturedDrawer[2][];
      this.texturedDrawers[3][0] = new TexturedDrawer[2];
      this.texturedDrawers[3][0][0] = new TexturedDrawer_T3_Opaque_Triangle(this);
      this.texturedDrawers[3][0][1] = new TexturedDrawer_T3_Opaque_Quad(this);
      this.texturedDrawers[3][1] = new TexturedDrawer[2];
      this.texturedDrawers[3][1][0] = new TexturedDrawer_T3_Alpha_Triangle(this);
      this.texturedDrawers[3][1][1] = new TexturedDrawer_T3_Alpha_Quad(this);
      this.unlitDrawers[0] = new UnlitDrawer[2][];
      this.unlitDrawers[0][0] = new UnlitDrawer[2];
      this.unlitDrawers[0][0][0] = new UnlitDrawer_T0_Opaque_Triangle(this);
      this.unlitDrawers[0][0][1] = new UnlitDrawer_T0_Opaque_Quad(this);
      this.unlitDrawers[0][1] = new UnlitDrawer[2];
      this.unlitDrawers[0][1][0] = new UnlitDrawer_T0_Alpha_Triangle(this);
      this.unlitDrawers[0][1][1] = new UnlitDrawer_T0_Alpha_Quad(this);
      this.unlitDrawers[1] = new UnlitDrawer[2][];
      this.unlitDrawers[1][0] = new UnlitDrawer[2];
      this.unlitDrawers[1][0][0] = new UnlitDrawer_T1_Opaque_Triangle(this);
      this.unlitDrawers[1][0][1] = new UnlitDrawer_T1_Opaque_Quad(this);
      this.unlitDrawers[1][1] = new UnlitDrawer[2];
      this.unlitDrawers[1][1][0] = new UnlitDrawer_T1_Alpha_Triangle(this);
      this.unlitDrawers[1][1][1] = new UnlitDrawer_T1_Alpha_Quad(this);
      this.unlitDrawers[2] = new UnlitDrawer[2][];
      this.unlitDrawers[2][0] = new UnlitDrawer[2];
      this.unlitDrawers[2][0][0] = new UnlitDrawer_T2_Opaque_Triangle(this);
      this.unlitDrawers[2][0][1] = new UnlitDrawer_T2_Opaque_Quad(this);
      this.unlitDrawers[2][1] = new UnlitDrawer[2];
      this.unlitDrawers[2][1][0] = new UnlitDrawer_T2_Alpha_Triangle(this);
      this.unlitDrawers[2][1][1] = new UnlitDrawer_T2_Alpha_Quad(this);
      this.unlitDrawers[3] = new UnlitDrawer[2][];
      this.unlitDrawers[3][0] = new UnlitDrawer[2];
      this.unlitDrawers[3][0][0] = new UnlitDrawer_T3_Opaque_Triangle(this);
      this.unlitDrawers[3][0][1] = new UnlitDrawer_T3_Opaque_Quad(this);
      this.unlitDrawers[3][1] = new UnlitDrawer[2];
      this.unlitDrawers[3][1][0] = new UnlitDrawer_T3_Alpha_Triangle(this);
      this.unlitDrawers[3][1][1] = new UnlitDrawer_T3_Alpha_Quad(this);
      this.litDrawers[0] = new LitDrawer[2][];
      this.litDrawers[0][0] = new LitDrawer[2];
      this.litDrawers[0][0][0] = new LitDrawer_T0_Opaque_Triangle(this);
      this.litDrawers[0][0][1] = new LitDrawer_T0_Opaque_Quad(this);
      this.litDrawers[0][1] = new LitDrawer[2];
      this.litDrawers[0][1][0] = new LitDrawer_T0_Alpha_Triangle(this);
      this.litDrawers[0][1][1] = new LitDrawer_T0_Alpha_Quad(this);
      this.litDrawers[1] = new LitDrawer[2][];
      this.litDrawers[1][0] = new LitDrawer[2];
      this.litDrawers[1][0][0] = new LitDrawer_T1_Opaque_Triangle(this);
      this.litDrawers[1][0][1] = new LitDrawer_T1_Opaque_Quad(this);
      this.litDrawers[1][1] = new LitDrawer[2];
      this.litDrawers[1][1][0] = new LitDrawer_T1_Alpha_Triangle(this);
      this.litDrawers[1][1][1] = new LitDrawer_T1_Alpha_Quad(this);
      this.litDrawers[2] = new LitDrawer[2][];
      this.litDrawers[2][0] = new LitDrawer[2];
      this.litDrawers[2][0][0] = new LitDrawer_T2_Opaque_Triangle(this);
      this.litDrawers[2][0][1] = new LitDrawer_T2_Opaque_Quad(this);
      this.litDrawers[2][1] = new LitDrawer[2];
      this.litDrawers[2][1][0] = new LitDrawer_T2_Alpha_Triangle(this);
      this.litDrawers[2][1][1] = new LitDrawer_T2_Alpha_Quad(this);
      this.litDrawers[3] = new LitDrawer[2][];
      this.litDrawers[3][0] = new LitDrawer[2];
      this.litDrawers[3][0][0] = new LitDrawer_T3_Opaque_Triangle(this);
      this.litDrawers[3][0][1] = new LitDrawer_T3_Opaque_Quad(this);
      this.litDrawers[3][1] = new LitDrawer[2];
      this.litDrawers[3][1][0] = new LitDrawer_T3_Alpha_Triangle(this);
      this.litDrawers[3][1][1] = new LitDrawer_T3_Alpha_Quad(this);
      this.sphereMapDrawers[0] = new SphereMapDrawer[2][];
      this.sphereMapDrawers[0][0] = new SphereMapDrawer[2];
      this.sphereMapDrawers[0][0][0] = new SphereMapDrawer_T0_Opaque_Triangle(this);
      this.sphereMapDrawers[0][0][1] = new SphereMapDrawer_T0_Opaque_Quad(this);
      this.sphereMapDrawers[0][1] = new SphereMapDrawer[2];
      this.sphereMapDrawers[0][1][0] = new SphereMapDrawer_T0_Alpha_Triangle(this);
      this.sphereMapDrawers[0][1][1] = new SphereMapDrawer_T0_Alpha_Quad(this);
      this.sphereMapDrawers[1] = new SphereMapDrawer[2][];
      this.sphereMapDrawers[1][0] = new SphereMapDrawer[2];
      this.sphereMapDrawers[1][0][0] = new SphereMapDrawer_T1_Opaque_Triangle(this);
      this.sphereMapDrawers[1][0][1] = new SphereMapDrawer_T1_Opaque_Quad(this);
      this.sphereMapDrawers[1][1] = new SphereMapDrawer[2];
      this.sphereMapDrawers[1][1][0] = new SphereMapDrawer_T1_Alpha_Triangle(this);
      this.sphereMapDrawers[1][1][1] = new SphereMapDrawer_T1_Alpha_Quad(this);
      this.sphereMapDrawers[2] = new SphereMapDrawer[2][];
      this.sphereMapDrawers[2][0] = new SphereMapDrawer[2];
      this.sphereMapDrawers[2][0][0] = new SphereMapDrawer_T2_Opaque_Triangle(this);
      this.sphereMapDrawers[2][0][1] = new SphereMapDrawer_T2_Opaque_Quad(this);
      this.sphereMapDrawers[2][1] = new SphereMapDrawer[2];
      this.sphereMapDrawers[2][1][0] = new SphereMapDrawer_T2_Alpha_Triangle(this);
      this.sphereMapDrawers[2][1][1] = new SphereMapDrawer_T2_Alpha_Quad(this);
      this.sphereMapDrawers[3] = new SphereMapDrawer[2][];
      this.sphereMapDrawers[3][0] = new SphereMapDrawer[2];
      this.sphereMapDrawers[3][0][0] = new SphereMapDrawer_T3_Opaque_Triangle(this);
      this.sphereMapDrawers[3][0][1] = new SphereMapDrawer_T3_Opaque_Quad(this);
      this.sphereMapDrawers[3][1] = new SphereMapDrawer[2];
      this.sphereMapDrawers[3][1][0] = new SphereMapDrawer_T3_Alpha_Triangle(this);
      this.sphereMapDrawers[3][1][1] = new SphereMapDrawer_T3_Alpha_Quad(this);
   }

   public void setRenderTarget(int var1, int[] var2) {
      this.pixelBuffer = var2;
      this.stride = var1;
      this.pixelOffset = 0;
   }

   public void setClipRect(int minX, int minY, int maxX, int maxY) {
      this.clipLeft = minX;
      this.clipTop = minY;
      this.clipRight = maxX;
      this.clipBottom = maxY;
   }

   public void setClipRect(BoundingBox bounds) {
      this.clipLeft = bounds.minX;
      this.clipTop = bounds.minY;
      this.clipRight = bounds.maxX;
      this.clipBottom = bounds.maxY;
   }

   public void fillColor(int color) {
      color |= 0xFF000000;
      int offset = this.clipTop * this.stride + this.clipLeft + this.pixelOffset;

      for (int y = this.clipTop; y < this.clipBottom; y++) {
         int row = offset;

         for (int x = this.clipLeft; x < this.clipRight; x++) {
            this.pixelBuffer[row++] = color;
         }

         offset += this.stride;
      }
   }

   public void setDiffuseTexture(Texture var1) {
      this.diffuseTexture = var1;
   }

   public void setSphereMapTexture(Texture var1) {
      this.sphereMapTexture = var1;
   }

   public void setFillColor(int color) {
      this.fillColor = color | 0xFF000000;
   }

   public void setBlendAlpha(int alpha) {
      this.blendAlpha = alpha;
   }

   public void rasterizeFlatTriangle(FlatDrawer drawer, RasterVertex v0, RasterVertex v1, RasterVertex v2) {
      RasterVertex top;
      RasterVertex mid;
      RasterVertex bot;
      if (v0.y <= v2.y) {
         if (v0.y <= v1.y) {
            top = v0;
            if (v1.y <= v2.y) {
               mid = v1;
               bot = v2;
            } else {
               mid = v2;
               bot = v1;
            }
         } else {
            top = v1;
            mid = v0;
            bot = v2;
         }
      } else if (v2.y < v1.y) {
         top = v2;
         if (v0.y < v1.y) {
            mid = v0;
            bot = v1;
         } else {
            mid = v1;
            bot = v0;
         }
      } else {
         top = v1;
         mid = v2;
         bot = v0;
      }

      if (top.y != bot.y) {
         drawer.y = top.y;
         drawer.scanlineOffset = top.y * this.stride + this.pixelOffset;
         int var8 = bot.x - top.x;
         int var9 = fixedReciprocal(bot.y - top.y);
         int var12 = var8 * var9;
         int var10 = (top.x << 16) + 32768;
         var8 = mid.x - top.x;
         var9 = mid.y - top.y;
         int var16 = (var8 << 16) - var12 * var9;
         int var17 = var16 >> 16;
         if (var17 == 0) {
            var17 = var16 > 0 ? 1 : -1;
         }

         int var15 = fixedReciprocal(var17);
         drawer.xLeftFixed = var10;
         drawer.xRightFixed = var10;
         if (var9 > 0) {
            var9 = fixedReciprocal(var9);
            int var13 = var8 * var9;
            drawer.yEnd = mid.y;
            if (var15 > 0) {
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var13;
            } else {
               drawer.dxLeftFixed = var13;
               drawer.dxRightFixed = var12;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            var8 = bot.x - mid.x;
            var9 = fixedReciprocal(bot.y - mid.y);
            int var14 = var8 * var9;
            int var11 = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (var15 > 0) {
               drawer.xRightFixed = var11;
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var14;
            } else {
               drawer.xLeftFixed = var11;
               drawer.dxLeftFixed = var14;
               drawer.dxRightFixed = var12;
            }

            drawer.drawSpan();
         }
      }
   }

   public void rasterizeLitColorTriangle(LineDrawer drawer, RasterVertex v0, RasterVertex v1, RasterVertex v2) {
      RasterVertex top;
      RasterVertex mid;
      RasterVertex bot;
      if (v0.y <= v2.y) {
         if (v0.y <= v1.y) {
            top = v0;
            if (v1.y <= v2.y) {
               mid = v1;
               bot = v2;
            } else {
               mid = v2;
               bot = v1;
            }
         } else {
            top = v1;
            mid = v0;
            bot = v2;
         }
      } else if (v2.y < v1.y) {
         top = v2;
         if (v0.y < v1.y) {
            mid = v0;
            bot = v1;
         } else {
            mid = v1;
            bot = v0;
         }
      } else {
         top = v1;
         mid = v2;
         bot = v0;
      }

      if (top.y != bot.y) {
         drawer.y = top.y;
         drawer.scanlineOffset = top.y * this.stride + this.pixelOffset;
         int var8 = bot.x - top.x;
         int var9 = fixedReciprocal(bot.y - top.y);
         int var12 = var8 * var9;
         int var10 = (top.x << 16) + 32768;
         int var15 = bot.z - top.z;
         int var19 = var15 * var9;
         int var17 = (top.z << 16) + 32768;
         var8 = mid.x - top.x;
         var9 = mid.y - top.y;
         var15 = mid.z - top.z;
         int var22 = (var8 << 16) - var12 * var9;
         int var23 = var22 >> 16;
         if (var23 == 0) {
            var23 = var22 > 0 ? 1 : -1;
         }

         int var21 = fixedReciprocal(var23);
         drawer.dzDxFixed = (var15 - (var19 * var9 >> 16)) * var21;
         drawer.xLeftFixed = var10;
         drawer.xRightFixed = var10;
         drawer.zFixed = var17;
         if (var9 > 0) {
            var9 = fixedReciprocal(var9);
            int var13 = var8 * var9;
            drawer.yEnd = mid.y;
            if (var21 > 0) {
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var13;
               drawer.dzDyFixed = var19;
            } else {
               drawer.dxLeftFixed = var13;
               drawer.dxRightFixed = var12;
               drawer.dzDyFixed = var15 * var9;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            var8 = bot.x - mid.x;
            var9 = fixedReciprocal(bot.y - mid.y);
            int var14 = var8 * var9;
            int var11 = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (var21 > 0) {
               drawer.xRightFixed = var11;
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var14;
               drawer.dzDyFixed = var19;
            } else {
               drawer.xLeftFixed = var11;
               drawer.dxLeftFixed = var14;
               drawer.dxRightFixed = var12;
               drawer.zFixed = (mid.z << 16) + 32768;
               drawer.dzDyFixed = (bot.z - mid.z) * var9;
            }

            drawer.drawSpan();
         }
      }
   }

   public void rasterizeTexturedTriangle(TexturedDrawer drawer, RasterVertex v0, RasterVertex v1, RasterVertex v2) {
      RasterVertex top;
      RasterVertex mid;
      RasterVertex bot;
      if (v0.y <= v2.y) {
         if (v0.y <= v1.y) {
            top = v0;
            if (v1.y <= v2.y) {
               mid = v1;
               bot = v2;
            } else {
               mid = v2;
               bot = v1;
            }
         } else {
            top = v1;
            mid = v0;
            bot = v2;
         }
      } else if (v2.y < v1.y) {
         top = v2;
         if (v0.y < v1.y) {
            mid = v0;
            bot = v1;
         } else {
            mid = v1;
            bot = v0;
         }
      } else {
         top = v1;
         mid = v2;
         bot = v0;
      }

      if (top.y != bot.y) {
         drawer.y = top.y;
         drawer.scanlineOffset = top.y * this.stride + this.pixelOffset;
         int var8 = bot.x - top.x;
         int var9 = fixedReciprocal(bot.y - top.y);
         int var12 = var8 * var9;
         int var10 = (top.x << 16) + 32768;
         int var15 = bot.z - top.z;
         int var16 = bot.u - top.u;
         int var19 = var15 * var9;
         int var20 = var16 * var9;
         int var17 = (top.z << 16) + 32768;
         int var18 = (top.u << 16) + 32768;
         var8 = mid.x - top.x;
         var9 = mid.y - top.y;
         var15 = mid.z - top.z;
         var16 = mid.u - top.u;
         int var22 = (var8 << 16) - var12 * var9;
         int var23 = var22 >> 16;
         if (var23 == 0) {
            var23 = var22 > 0 ? 1 : -1;
         }

         int var21 = fixedReciprocal(var23);
         drawer.duDxFixed = (var15 - (var19 * var9 >> 16)) * var21;
         drawer.dvDxFixed = (var16 - (var20 * var9 >> 16)) * var21;
         drawer.xLeftFixed = var10;
         drawer.xRightFixed = var10;
         drawer.uFixed = var17;
         drawer.vFixed = var18;
         if (var9 > 0) {
            var9 = fixedReciprocal(var9);
            int var13 = var8 * var9;
            drawer.yEnd = mid.y;
            if (var21 > 0) {
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var13;
               drawer.duDyFixed = var19;
               drawer.dvDyFixed = var20;
            } else {
               drawer.dxLeftFixed = var13;
               drawer.dxRightFixed = var12;
               drawer.duDyFixed = var15 * var9;
               drawer.dvDyFixed = var16 * var9;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            var8 = bot.x - mid.x;
            var9 = fixedReciprocal(bot.y - mid.y);
            int var14 = var8 * var9;
            int var11 = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (var21 > 0) {
               drawer.xRightFixed = var11;
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var14;
               drawer.duDyFixed = var19;
               drawer.dvDyFixed = var20;
            } else {
               drawer.xLeftFixed = var11;
               drawer.dxLeftFixed = var14;
               drawer.dxRightFixed = var12;
               drawer.uFixed = (mid.z << 16) + 32768;
               drawer.vFixed = (mid.u << 16) + 32768;
               drawer.duDyFixed = (bot.z - mid.z) * var9;
               drawer.dvDyFixed = (bot.u - mid.u) * var9;
            }

            drawer.drawSpan();
         }
      }
   }

   public void rasterizeUnlitTriangle(UnlitDrawer drawer, RasterVertex v0, RasterVertex v1, RasterVertex v2) {
      RasterVertex top;
      RasterVertex mid;
      RasterVertex bot;
      if (v0.y <= v2.y) {
         if (v0.y <= v1.y) {
            top = v0;
            if (v1.y <= v2.y) {
               mid = v1;
               bot = v2;
            } else {
               mid = v2;
               bot = v1;
            }
         } else {
            top = v1;
            mid = v0;
            bot = v2;
         }
      } else if (v2.y < v1.y) {
         top = v2;
         if (v0.y < v1.y) {
            mid = v0;
            bot = v1;
         } else {
            mid = v1;
            bot = v0;
         }
      } else {
         top = v1;
         mid = v2;
         bot = v0;
      }

      if (top.y != bot.y) {
         drawer.y = top.y;
         drawer.scanlineOffset = top.y * this.stride + this.pixelOffset;
         int var8 = bot.x - top.x;
         int var9 = fixedReciprocal(bot.y - top.y);
         int var12 = var8 * var9;
         int var10 = (top.x << 16) + 32768;
         int var15 = bot.z - top.z;
         int var16 = bot.u - top.u;
         int var17 = bot.v - top.v;
         int var21 = var15 * var9;
         int var22 = var16 * var9;
         int var23 = var17 * var9;
         int var18 = (top.z << 16) + 32768;
         int var19 = (top.u << 16) + 32768;
         int var20 = (top.v << 16) + 32768;
         var8 = mid.x - top.x;
         var9 = mid.y - top.y;
         var15 = mid.z - top.z;
         var16 = mid.u - top.u;
         var17 = mid.v - top.v;
         int var25 = (var8 << 16) - var12 * var9;
         int var26 = var25 >> 16;
         if (var26 == 0) {
            var26 = var25 > 0 ? 1 : -1;
         }

         int var24 = fixedReciprocal(var26);
         drawer.lightFixed = (var15 - (var21 * var9 >> 16)) * var24;
         drawer.dLightDyFixed = (var16 - (var22 * var9 >> 16)) * var24;
         drawer.dLightDxFixed = (var17 - (var23 * var9 >> 16)) * var24;
         drawer.xLeftFixed = var10;
         drawer.xRightFixed = var10;
         drawer.uFixed = var18;
         drawer.vFixed = var19;
         drawer.duDyFixed = var20;
         if (var9 > 0) {
            var9 = fixedReciprocal(var9);
            int var13 = var8 * var9;
            drawer.yEnd = mid.y;
            if (var24 > 0) {
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var13;
               drawer.dvDyFixed = var21;
               drawer.duDxFixed = var22;
               drawer.dvDxFixed = var23;
            } else {
               drawer.dxLeftFixed = var13;
               drawer.dxRightFixed = var12;
               drawer.dvDyFixed = var15 * var9;
               drawer.duDxFixed = var16 * var9;
               drawer.dvDxFixed = var17 * var9;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            var8 = bot.x - mid.x;
            var9 = fixedReciprocal(bot.y - mid.y);
            int var14 = var8 * var9;
            int var11 = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (var24 > 0) {
               drawer.xRightFixed = var11;
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var14;
               drawer.dvDyFixed = var21;
               drawer.duDxFixed = var22;
               drawer.dvDxFixed = var23;
            } else {
               drawer.xLeftFixed = var11;
               drawer.dxLeftFixed = var14;
               drawer.dxRightFixed = var12;
               drawer.uFixed = (mid.z << 16) + 32768;
               drawer.vFixed = (mid.u << 16) + 32768;
               drawer.duDyFixed = (mid.v << 16) + 32768;
               drawer.dvDyFixed = (bot.z - mid.z) * var9;
               drawer.duDxFixed = (bot.u - mid.u) * var9;
               drawer.dvDxFixed = (bot.v - mid.v) * var9;
            }

            drawer.drawSpan();
         }
      }
   }

   public void rasterizeLitTriangle(LitDrawer drawer, RasterVertex v0, RasterVertex v1, RasterVertex v2) {
      RasterVertex top;
      RasterVertex mid;
      RasterVertex bot;
      if (v0.y <= v2.y) {
         if (v0.y <= v1.y) {
            top = v0;
            if (v1.y <= v2.y) {
               mid = v1;
               bot = v2;
            } else {
               mid = v2;
               bot = v1;
            }
         } else {
            top = v1;
            mid = v0;
            bot = v2;
         }
      } else if (v2.y < v1.y) {
         top = v2;
         if (v0.y < v1.y) {
            mid = v0;
            bot = v1;
         } else {
            mid = v1;
            bot = v0;
         }
      } else {
         top = v1;
         mid = v2;
         bot = v0;
      }

      if (top.y != bot.y) {
         drawer.y = top.y;
         drawer.scanlineOffset = top.y * this.stride + this.pixelOffset;
         int var8 = bot.x - top.x;
         int var9 = fixedReciprocal(bot.y - top.y);
         int var12 = var8 * var9;
         int var10 = (top.x << 16) + 32768;
         int var15 = bot.z - top.z;
         int var16 = bot.u - top.u;
         int var17 = bot.v - top.v;
         int var18 = bot.light - top.light;
         int var23 = var15 * var9;
         int var24 = var16 * var9;
         int var25 = var17 * var9;
         int var26 = var18 * var9;
         int var19 = (top.z << 16) + 32768;
         int var20 = (top.u << 16) + 32768;
         int var21 = (top.v << 16) + 32768;
         int var22 = (top.light << 16) + 32768;
         var8 = mid.x - top.x;
         var9 = mid.y - top.y;
         var15 = mid.z - top.z;
         var16 = mid.u - top.u;
         var17 = mid.v - top.v;
         var18 = mid.light - top.light;
         int var28 = (var8 << 16) - var12 * var9;
         int var29 = var28 >> 16;
         if (var29 == 0) {
            var29 = var28 > 0 ? 1 : -1;
         }

         int var27 = fixedReciprocal(var29);
         drawer.dLightDxFixed = (var15 - (var23 * var9 >> 16)) * var27;
         drawer.normalZFixed = (var16 - (var24 * var9 >> 16)) * var27;
         drawer.dNormalZDyFixed = (var17 - (var25 * var9 >> 16)) * var27;
         drawer.dNormalZDxFixed = (var18 - (var26 * var9 >> 16)) * var27;
         drawer.xLeftFixed = var10;
         drawer.xRightFixed = var10;
         drawer.uFixed = var19;
         drawer.vFixed = var20;
         drawer.duDyFixed = var21;
         drawer.dvDyFixed = var22;
         if (var9 > 0) {
            var9 = fixedReciprocal(var9);
            int var13 = var8 * var9;
            drawer.yEnd = mid.y;
            if (var27 > 0) {
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var13;
               drawer.duDxFixed = var23;
               drawer.dvDxFixed = var24;
               drawer.lightFixed = var25;
               drawer.dLightDyFixed = var26;
            } else {
               drawer.dxLeftFixed = var13;
               drawer.dxRightFixed = var12;
               drawer.duDxFixed = var15 * var9;
               drawer.dvDxFixed = var16 * var9;
               drawer.lightFixed = var17 * var9;
               drawer.dLightDyFixed = var18 * var9;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            var8 = bot.x - mid.x;
            var9 = fixedReciprocal(bot.y - mid.y);
            int var14 = var8 * var9;
            int var11 = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (var27 > 0) {
               drawer.xRightFixed = var11;
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var14;
               drawer.duDxFixed = var23;
               drawer.dvDxFixed = var24;
               drawer.lightFixed = var25;
               drawer.dLightDyFixed = var26;
            } else {
               drawer.xLeftFixed = var11;
               drawer.dxLeftFixed = var14;
               drawer.dxRightFixed = var12;
               drawer.uFixed = (mid.z << 16) + 32768;
               drawer.vFixed = (mid.u << 16) + 32768;
               drawer.duDyFixed = (mid.v << 16) + 32768;
               drawer.dvDyFixed = (mid.light << 16) + 32768;
               drawer.duDxFixed = (bot.z - mid.z) * var9;
               drawer.dvDxFixed = (bot.u - mid.u) * var9;
               drawer.lightFixed = (bot.v - mid.v) * var9;
               drawer.dLightDyFixed = (bot.light - mid.light) * var9;
            }

            drawer.drawSpan();
         }
      }
   }

   public void rasterizeSphereMapTriangle(SphereMapDrawer drawer, RasterVertex v0, RasterVertex v1, RasterVertex v2) {
      RasterVertex top;
      RasterVertex mid;
      RasterVertex bot;
      if (v0.y <= v2.y) {
         if (v0.y <= v1.y) {
            top = v0;
            if (v1.y <= v2.y) {
               mid = v1;
               bot = v2;
            } else {
               mid = v2;
               bot = v1;
            }
         } else {
            top = v1;
            mid = v0;
            bot = v2;
         }
      } else if (v2.y < v1.y) {
         top = v2;
         if (v0.y < v1.y) {
            mid = v0;
            bot = v1;
         } else {
            mid = v1;
            bot = v0;
         }
      } else {
         top = v1;
         mid = v2;
         bot = v0;
      }

      if (top.y != bot.y) {
         drawer.y = top.y;
         drawer.scanlineOffset = top.y * this.stride + this.pixelOffset;
         int var8 = bot.x - top.x;
         int var9 = fixedReciprocal(bot.y - top.y);
         int var12 = var8 * var9;
         int var10 = (top.x << 16) + 32768;
         int var15 = bot.z - top.z;
         int var16 = bot.u - top.u;
         int var17 = bot.v - top.v;
         int var18 = bot.light - top.light;
         int var19 = bot.normalZ - top.normalZ;
         int var25 = var15 * var9;
         int var26 = var16 * var9;
         int var27 = var17 * var9;
         int var28 = var18 * var9;
         int var29 = var19 * var9;
         int var20 = (top.z << 16) + 32768;
         int var21 = (top.u << 16) + 32768;
         int var22 = (top.v << 16) + 32768;
         int var23 = (top.light << 16) + 32768;
         int var24 = (top.normalZ << 16) + 32768;
         var8 = mid.x - top.x;
         var9 = mid.y - top.y;
         var15 = mid.z - top.z;
         var16 = mid.u - top.u;
         var17 = mid.v - top.v;
         var18 = mid.light - top.light;
         var19 = mid.normalZ - top.normalZ;
         int var31 = (var8 << 16) - var12 * var9;
         int var32 = var31 >> 16;
         if (var32 == 0) {
            var32 = var31 > 0 ? 1 : -1;
         }

         int var30 = fixedReciprocal(var32);
         drawer.sphereVFixed = (var15 - (var25 * var9 >> 16)) * var30;
         drawer.dSphereUDyFixed = (var16 - (var26 * var9 >> 16)) * var30;
         drawer.dSphereVDyFixed = (var17 - (var27 * var9 >> 16)) * var30;
         drawer.dSphereUDxFixed = (var18 - (var28 * var9 >> 16)) * var30;
         drawer.dSphereVDxFixed = (var19 - (var29 * var9 >> 16)) * var30;
         drawer.xLeftFixed = var10;
         drawer.xRightFixed = var10;
         drawer.uFixed = var20;
         drawer.vFixed = var21;
         drawer.duDyFixed = var22;
         drawer.dvDyFixed = var23;
         drawer.duDxFixed = var24;
         if (var9 > 0) {
            var9 = fixedReciprocal(var9);
            int var13 = var8 * var9;
            drawer.yEnd = mid.y;
            if (var30 > 0) {
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var13;
               drawer.dvDxFixed = var25;
               drawer.lightFixed = var26;
               drawer.dLightDyFixed = var27;
               drawer.dLightDxFixed = var28;
               drawer.sphereUFixed = var29;
            } else {
               drawer.dxLeftFixed = var13;
               drawer.dxRightFixed = var12;
               drawer.dvDxFixed = var15 * var9;
               drawer.lightFixed = var16 * var9;
               drawer.dLightDyFixed = var17 * var9;
               drawer.dLightDxFixed = var18 * var9;
               drawer.sphereUFixed = var19 * var9;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            var8 = bot.x - mid.x;
            var9 = fixedReciprocal(bot.y - mid.y);
            int var14 = var8 * var9;
            int var11 = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (var30 > 0) {
               drawer.xRightFixed = var11;
               drawer.dxLeftFixed = var12;
               drawer.dxRightFixed = var14;
               drawer.dvDxFixed = var25;
               drawer.lightFixed = var26;
               drawer.dLightDyFixed = var27;
               drawer.dLightDxFixed = var28;
               drawer.sphereUFixed = var29;
            } else {
               drawer.xLeftFixed = var11;
               drawer.dxLeftFixed = var14;
               drawer.dxRightFixed = var12;
               drawer.uFixed = (mid.z << 16) + 32768;
               drawer.vFixed = (mid.u << 16) + 32768;
               drawer.duDyFixed = (mid.v << 16) + 32768;
               drawer.dvDyFixed = (mid.light << 16) + 32768;
               drawer.duDxFixed = (mid.normalZ << 16) + 32768;
               drawer.dvDxFixed = (bot.z - mid.z) * var9;
               drawer.lightFixed = (bot.u - mid.u) * var9;
               drawer.dLightDyFixed = (bot.v - mid.v) * var9;
               drawer.dLightDxFixed = (bot.light - mid.light) * var9;
               drawer.sphereUFixed = (bot.normalZ - mid.normalZ) * var9;
            }

            drawer.drawSpan();
         }
      }
   }

   public int computeOutcode(RasterVertex vertex) {
      byte outcode = 0;
      if (vertex.x < this.clipLeft) {
         outcode |= 1;
      } else if (this.clipRight <= vertex.x) {
         outcode |= 2;
      }

      if (vertex.y < this.clipTop) {
         outcode |= 4;
      } else if (this.clipBottom <= vertex.y) {
         outcode |= 8;
      }

      return outcode;
   }

   private static int fixedReciprocal(int var0) {
      return 65536 / var0;
   }

   static int[] getPixelBuffer(Config rasterizer) {
      return rasterizer.pixelBuffer;
   }

   static int getFillColor(Config rasterizer) {
      return rasterizer.fillColor;
   }

   static int getStride(Config rasterizer) {
      return rasterizer.stride;
   }

   static int getClipTop(Config rasterizer) {
      return rasterizer.clipTop;
   }

   static int getClipBottom(Config rasterizer) {
      return rasterizer.clipBottom;
   }

   static int getClipLeft(Config rasterizer) {
      return rasterizer.clipLeft;
   }

   static int getClipRight(Config rasterizer) {
      return rasterizer.clipRight;
   }

   static int[] getColorTable() {
      return blendTable;
   }

   static Texture getDiffuseTexture(Config rasterizer) {
      return rasterizer.diffuseTexture;
   }

   static int getBlendAlpha(Config rasterizer) {
      return rasterizer.blendAlpha;
   }

   static Texture getSphereMapTexture(Config rasterizer) {
      return rasterizer.sphereMapTexture;
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
