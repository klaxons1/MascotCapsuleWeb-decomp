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

   public void setRenderTarget(int targetStride, int[] targetBuffer) {
      this.pixelBuffer = targetBuffer;
      this.stride = targetStride;
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

   public void setDiffuseTexture(Texture texture) {
      this.diffuseTexture = texture;
   }

   public void setSphereMapTexture(Texture texture) {
      this.sphereMapTexture = texture;
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
         int dx = bot.x - top.x;
         int invDy = fixedReciprocal(bot.y - top.y);
         int dxLeftStep = dx * invDy;
         int xTopFixed = (top.x << 16) + 32768;
         dx = mid.x - top.x;
         invDy = mid.y - top.y;
         int crossArea = (dx << 16) - dxLeftStep * invDy;
         int det = crossArea >> 16;
         if (det == 0) {
            det = crossArea > 0 ? 1 : -1;
         }

         int invDet = fixedReciprocal(det);
         drawer.xLeftFixed = xTopFixed;
         drawer.xRightFixed = xTopFixed;
         if (invDy > 0) {
            invDy = fixedReciprocal(invDy);
            int dxMidStep = dx * invDy;
            drawer.yEnd = mid.y;
            if (invDet > 0) {
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxMidStep;
            } else {
               drawer.dxLeftFixed = dxMidStep;
               drawer.dxRightFixed = dxLeftStep;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            dx = bot.x - mid.x;
            invDy = fixedReciprocal(bot.y - mid.y);
            int dxBotStep = dx * invDy;
            int xMidFixed = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (invDet > 0) {
               drawer.xRightFixed = xMidFixed;
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxBotStep;
            } else {
               drawer.xLeftFixed = xMidFixed;
               drawer.dxLeftFixed = dxBotStep;
               drawer.dxRightFixed = dxLeftStep;
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
         int dx = bot.x - top.x;
         int invDy = fixedReciprocal(bot.y - top.y);
         int dxLeftStep = dx * invDy;
         int xTopFixed = (top.x << 16) + 32768;
         int du = bot.u - top.u;
         int duLeftStep = du * invDy;
         int uTopFixed = (top.u << 16) + 32768;
         dx = mid.x - top.x;
         invDy = mid.y - top.y;
         du = mid.u - top.u;
         int crossArea = (dx << 16) - dxLeftStep * invDy;
         int det = crossArea >> 16;
         if (det == 0) {
            det = crossArea > 0 ? 1 : -1;
         }

         int invDet = fixedReciprocal(det);
         drawer.dzDxFixed = (du - (duLeftStep * invDy >> 16)) * invDet;
         drawer.xLeftFixed = xTopFixed;
         drawer.xRightFixed = xTopFixed;
         drawer.zFixed = uTopFixed;
         if (invDy > 0) {
            invDy = fixedReciprocal(invDy);
            int dxMidStep = dx * invDy;
            drawer.yEnd = mid.y;
            if (invDet > 0) {
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxMidStep;
               drawer.dzDyFixed = duLeftStep;
            } else {
               drawer.dxLeftFixed = dxMidStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.dzDyFixed = du * invDy;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            dx = bot.x - mid.x;
            invDy = fixedReciprocal(bot.y - mid.y);
            int dxBotStep = dx * invDy;
            int xMidFixed = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (invDet > 0) {
               drawer.xRightFixed = xMidFixed;
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxBotStep;
               drawer.dzDyFixed = duLeftStep;
            } else {
               drawer.xLeftFixed = xMidFixed;
               drawer.dxLeftFixed = dxBotStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.zFixed = (mid.u << 16) + 32768;
               drawer.dzDyFixed = (bot.u - mid.u) * invDy;
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
         int dx = bot.x - top.x;
         int invDy = fixedReciprocal(bot.y - top.y);
         int dxLeftStep = dx * invDy;
         int xTopFixed = (top.x << 16) + 32768;
         int du = bot.u - top.u;
         int dv = bot.v - top.v;
         int duLeftStep = du * invDy;
         int dvLeftStep = dv * invDy;
         int uTopFixed = (top.u << 16) + 32768;
         int vTopFixed = (top.v << 16) + 32768;
         dx = mid.x - top.x;
         invDy = mid.y - top.y;
         du = mid.u - top.u;
         dv = mid.v - top.v;
         int crossArea = (dx << 16) - dxLeftStep * invDy;
         int det = crossArea >> 16;
         if (det == 0) {
            det = crossArea > 0 ? 1 : -1;
         }

         int invDet = fixedReciprocal(det);
         drawer.duDxFixed = (du - (duLeftStep * invDy >> 16)) * invDet;
         drawer.dvDxFixed = (dv - (dvLeftStep * invDy >> 16)) * invDet;
         drawer.xLeftFixed = xTopFixed;
         drawer.xRightFixed = xTopFixed;
         drawer.uFixed = uTopFixed;
         drawer.vFixed = vTopFixed;
         if (invDy > 0) {
            invDy = fixedReciprocal(invDy);
            int dxMidStep = dx * invDy;
            drawer.yEnd = mid.y;
            if (invDet > 0) {
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxMidStep;
               drawer.duDyFixed = duLeftStep;
               drawer.dvDyFixed = dvLeftStep;
            } else {
               drawer.dxLeftFixed = dxMidStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.duDyFixed = du * invDy;
               drawer.dvDyFixed = dv * invDy;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            dx = bot.x - mid.x;
            invDy = fixedReciprocal(bot.y - mid.y);
            int dxBotStep = dx * invDy;
            int xMidFixed = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (invDet > 0) {
               drawer.xRightFixed = xMidFixed;
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxBotStep;
               drawer.duDyFixed = duLeftStep;
               drawer.dvDyFixed = dvLeftStep;
            } else {
               drawer.xLeftFixed = xMidFixed;
               drawer.dxLeftFixed = dxBotStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.uFixed = (mid.u << 16) + 32768;
               drawer.vFixed = (mid.v << 16) + 32768;
               drawer.duDyFixed = (bot.u - mid.u) * invDy;
               drawer.dvDyFixed = (bot.v - mid.v) * invDy;
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
         int dx = bot.x - top.x;
         int invDy = fixedReciprocal(bot.y - top.y);
         int dxLeftStep = dx * invDy;
         int xTopFixed = (top.x << 16) + 32768;
         int du = bot.u - top.u;
         int dv = bot.v - top.v;
         int dLight = bot.light - top.light;
         int duLeftStep = du * invDy;
         int dvLeftStep = dv * invDy;
         int dLightLeftStep = dLight * invDy;
         int uTopFixed = (top.u << 16) + 32768;
         int vTopFixed = (top.v << 16) + 32768;
         int lightTopFixed = (top.light << 16) + 32768;
         dx = mid.x - top.x;
         invDy = mid.y - top.y;
         du = mid.u - top.u;
         dv = mid.v - top.v;
         dLight = mid.light - top.light;
         int crossArea = (dx << 16) - dxLeftStep * invDy;
         int det = crossArea >> 16;
         if (det == 0) {
            det = crossArea > 0 ? 1 : -1;
         }

         int invDet = fixedReciprocal(det);
         drawer.lightFixed = (du - (duLeftStep * invDy >> 16)) * invDet;
         drawer.dLightDyFixed = (dv - (dvLeftStep * invDy >> 16)) * invDet;
         drawer.dLightDxFixed = (dLight - (dLightLeftStep * invDy >> 16)) * invDet;
         drawer.xLeftFixed = xTopFixed;
         drawer.xRightFixed = xTopFixed;
         drawer.uFixed = uTopFixed;
         drawer.vFixed = vTopFixed;
         drawer.duDyFixed = lightTopFixed;
         if (invDy > 0) {
            invDy = fixedReciprocal(invDy);
            int dxMidStep = dx * invDy;
            drawer.yEnd = mid.y;
            if (invDet > 0) {
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxMidStep;
               drawer.dvDyFixed = duLeftStep;
               drawer.duDxFixed = dvLeftStep;
               drawer.dvDxFixed = dLightLeftStep;
            } else {
               drawer.dxLeftFixed = dxMidStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.dvDyFixed = du * invDy;
               drawer.duDxFixed = dv * invDy;
               drawer.dvDxFixed = dLight * invDy;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            dx = bot.x - mid.x;
            invDy = fixedReciprocal(bot.y - mid.y);
            int dxBotStep = dx * invDy;
            int xMidFixed = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (invDet > 0) {
               drawer.xRightFixed = xMidFixed;
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxBotStep;
               drawer.dvDyFixed = duLeftStep;
               drawer.duDxFixed = dvLeftStep;
               drawer.dvDxFixed = dLightLeftStep;
            } else {
               drawer.xLeftFixed = xMidFixed;
               drawer.dxLeftFixed = dxBotStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.uFixed = (mid.u << 16) + 32768;
               drawer.vFixed = (mid.v << 16) + 32768;
               drawer.duDyFixed = (mid.light << 16) + 32768;
               drawer.dvDyFixed = (bot.u - mid.u) * invDy;
               drawer.duDxFixed = (bot.v - mid.v) * invDy;
               drawer.dvDxFixed = (bot.light - mid.light) * invDy;
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
         int dx = bot.x - top.x;
         int invDy = fixedReciprocal(bot.y - top.y);
         int dxLeftStep = dx * invDy;
         int xTopFixed = (top.x << 16) + 32768;
         int du = bot.u - top.u;
         int dv = bot.v - top.v;
         int dLight = bot.light - top.light;
         int dNormalZ = bot.normalZ - top.normalZ;
         int duLeftStep = du * invDy;
         int dvLeftStep = dv * invDy;
         int dLightLeftStep = dLight * invDy;
         int dNormalZLeftStep = dNormalZ * invDy;
         int uTopFixed = (top.u << 16) + 32768;
         int vTopFixed = (top.v << 16) + 32768;
         int lightTopFixed = (top.light << 16) + 32768;
         int normalZTopFixed = (top.normalZ << 16) + 32768;
         dx = mid.x - top.x;
         invDy = mid.y - top.y;
         du = mid.u - top.u;
         dv = mid.v - top.v;
         dLight = mid.light - top.light;
         dNormalZ = mid.normalZ - top.normalZ;
         int crossArea = (dx << 16) - dxLeftStep * invDy;
         int det = crossArea >> 16;
         if (det == 0) {
            det = crossArea > 0 ? 1 : -1;
         }

         int invDet = fixedReciprocal(det);
         drawer.dLightDxFixed = (du - (duLeftStep * invDy >> 16)) * invDet;
         drawer.normalZFixed = (dv - (dvLeftStep * invDy >> 16)) * invDet;
         drawer.dNormalZDyFixed = (dLight - (dLightLeftStep * invDy >> 16)) * invDet;
         drawer.dNormalZDxFixed = (dNormalZ - (dNormalZLeftStep * invDy >> 16)) * invDet;
         drawer.xLeftFixed = xTopFixed;
         drawer.xRightFixed = xTopFixed;
         drawer.uFixed = uTopFixed;
         drawer.vFixed = vTopFixed;
         drawer.duDyFixed = lightTopFixed;
         drawer.dvDyFixed = normalZTopFixed;
         if (invDy > 0) {
            invDy = fixedReciprocal(invDy);
            int dxMidStep = dx * invDy;
            drawer.yEnd = mid.y;
            if (invDet > 0) {
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxMidStep;
               drawer.duDxFixed = duLeftStep;
               drawer.dvDxFixed = dvLeftStep;
               drawer.lightFixed = dLightLeftStep;
               drawer.dLightDyFixed = dNormalZLeftStep;
            } else {
               drawer.dxLeftFixed = dxMidStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.duDxFixed = du * invDy;
               drawer.dvDxFixed = dv * invDy;
               drawer.lightFixed = dLight * invDy;
               drawer.dLightDyFixed = dNormalZ * invDy;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            dx = bot.x - mid.x;
            invDy = fixedReciprocal(bot.y - mid.y);
            int dxBotStep = dx * invDy;
            int xMidFixed = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (invDet > 0) {
               drawer.xRightFixed = xMidFixed;
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxBotStep;
               drawer.duDxFixed = duLeftStep;
               drawer.dvDxFixed = dvLeftStep;
               drawer.lightFixed = dLightLeftStep;
               drawer.dLightDyFixed = dNormalZLeftStep;
            } else {
               drawer.xLeftFixed = xMidFixed;
               drawer.dxLeftFixed = dxBotStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.uFixed = (mid.u << 16) + 32768;
               drawer.vFixed = (mid.v << 16) + 32768;
               drawer.duDyFixed = (mid.light << 16) + 32768;
               drawer.dvDyFixed = (mid.normalZ << 16) + 32768;
               drawer.duDxFixed = (bot.u - mid.u) * invDy;
               drawer.dvDxFixed = (bot.v - mid.v) * invDy;
               drawer.lightFixed = (bot.light - mid.light) * invDy;
               drawer.dLightDyFixed = (bot.normalZ - mid.normalZ) * invDy;
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
         int dx = bot.x - top.x;
         int invDy = fixedReciprocal(bot.y - top.y);
         int dxLeftStep = dx * invDy;
         int xTopFixed = (top.x << 16) + 32768;
         int du = bot.u - top.u;
         int dv = bot.v - top.v;
         int dLight = bot.light - top.light;
         int dNormalZ = bot.normalZ - top.normalZ;
         int dSphereV = bot.sphereV - top.sphereV;
         int duLeftStep = du * invDy;
         int dvLeftStep = dv * invDy;
         int dLightLeftStep = dLight * invDy;
         int dNormalZLeftStep = dNormalZ * invDy;
         int dSphereVLeftStep = dSphereV * invDy;
         int uTopFixed = (top.u << 16) + 32768;
         int vTopFixed = (top.v << 16) + 32768;
         int lightTopFixed = (top.light << 16) + 32768;
         int normalZTopFixed = (top.normalZ << 16) + 32768;
         int sphereVTopFixed = (top.sphereV << 16) + 32768;
         dx = mid.x - top.x;
         invDy = mid.y - top.y;
         du = mid.u - top.u;
         dv = mid.v - top.v;
         dLight = mid.light - top.light;
         dNormalZ = mid.normalZ - top.normalZ;
         dSphereV = mid.sphereV - top.sphereV;
         int crossArea = (dx << 16) - dxLeftStep * invDy;
         int det = crossArea >> 16;
         if (det == 0) {
            det = crossArea > 0 ? 1 : -1;
         }

         int invDet = fixedReciprocal(det);
         drawer.sphereVFixed = (du - (duLeftStep * invDy >> 16)) * invDet;
         drawer.dSphereUDyFixed = (dv - (dvLeftStep * invDy >> 16)) * invDet;
         drawer.dSphereVDyFixed = (dLight - (dLightLeftStep * invDy >> 16)) * invDet;
         drawer.dSphereUDxFixed = (dNormalZ - (dNormalZLeftStep * invDy >> 16)) * invDet;
         drawer.dSphereVDxFixed = (dSphereV - (dSphereVLeftStep * invDy >> 16)) * invDet;
         drawer.xLeftFixed = xTopFixed;
         drawer.xRightFixed = xTopFixed;
         drawer.uFixed = uTopFixed;
         drawer.vFixed = vTopFixed;
         drawer.duDyFixed = lightTopFixed;
         drawer.dvDyFixed = normalZTopFixed;
         drawer.duDxFixed = sphereVTopFixed;
         if (invDy > 0) {
            invDy = fixedReciprocal(invDy);
            int dxMidStep = dx * invDy;
            drawer.yEnd = mid.y;
            if (invDet > 0) {
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxMidStep;
               drawer.dvDxFixed = duLeftStep;
               drawer.lightFixed = dvLeftStep;
               drawer.dLightDyFixed = dLightLeftStep;
               drawer.dLightDxFixed = dNormalZLeftStep;
               drawer.sphereUFixed = dSphereVLeftStep;
            } else {
               drawer.dxLeftFixed = dxMidStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.dvDxFixed = du * invDy;
               drawer.lightFixed = dv * invDy;
               drawer.dLightDyFixed = dLight * invDy;
               drawer.dLightDxFixed = dNormalZ * invDy;
               drawer.sphereUFixed = dSphereV * invDy;
            }

            drawer.drawSpan();
         }

         if (mid.y != bot.y) {
            dx = bot.x - mid.x;
            invDy = fixedReciprocal(bot.y - mid.y);
            int dxBotStep = dx * invDy;
            int xMidFixed = (mid.x << 16) + 32768;
            drawer.yEnd = bot.y;
            if (invDet > 0) {
               drawer.xRightFixed = xMidFixed;
               drawer.dxLeftFixed = dxLeftStep;
               drawer.dxRightFixed = dxBotStep;
               drawer.dvDxFixed = duLeftStep;
               drawer.lightFixed = dvLeftStep;
               drawer.dLightDyFixed = dLightLeftStep;
               drawer.dLightDxFixed = dNormalZLeftStep;
               drawer.sphereUFixed = dSphereVLeftStep;
            } else {
               drawer.xLeftFixed = xMidFixed;
               drawer.dxLeftFixed = dxBotStep;
               drawer.dxRightFixed = dxLeftStep;
               drawer.uFixed = (mid.u << 16) + 32768;
               drawer.vFixed = (mid.v << 16) + 32768;
               drawer.duDyFixed = (mid.light << 16) + 32768;
               drawer.dvDyFixed = (mid.normalZ << 16) + 32768;
               drawer.duDxFixed = (mid.sphereV << 16) + 32768;
               drawer.dvDxFixed = (bot.u - mid.u) * invDy;
               drawer.lightFixed = (bot.v - mid.v) * invDy;
               drawer.dLightDyFixed = (bot.light - mid.light) * invDy;
               drawer.dLightDxFixed = (bot.normalZ - mid.normalZ) * invDy;
               drawer.sphereUFixed = (bot.sphereV - mid.sphereV) * invDy;
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

   private static int fixedReciprocal(int val) {
      return 65536 / val;
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
      int colorStep = 65793;

      for (int i = 0; i < 129; i++) {
         blendTable[i] = 0;
      }

      for (int i = 1; i < 255; i++) {
         blendTable[i + 128] = 65793 * i;
      }

      for (int i = -1; i < 128; i++) {
         blendTable[i + 384] = 16777215;
      }
   }
}
