package com.hicorp.mascotcapsule.web;

public final class TexturedDrawer_T2_Alpha_Triangle extends TexturedDrawer {
   private final Config rasterizer;

   public TexturedDrawer_T2_Alpha_Triangle(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int var2 = (super.duDxFixed > 0 ? super.duDxFixed : -super.duDxFixed) + (super.dvDxFixed > 0 ? super.dvDxFixed : -super.dvDxFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int var5 = Config.getDiffuseTexture(this.rasterizer).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.rasterizer).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.rasterizer).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.rasterizer).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.rasterizer).getMipVShift(var3);
      int var10 = super.duDxFixed >> var3;
      int var11 = super.dvDxFixed >> var3;
      int var12 = Config.getBlendAlpha(this.rasterizer);

      for (int var13 = 255 - Config.getBlendAlpha(this.rasterizer); super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int var14 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var15 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var16 = super.uFixed >> var3;

         for (int var17 = super.vFixed >> var3; var14 < var15; var14++) {
            int var18 = var5 + ((var17 & var7) >>> var9) + ((var16 & var6) >>> var8);
            int var19 = var4[var18];
            if (var19 != -1) {
               int var20 = var1[var14];
               int var21 = ((var19 & 16711935) * var12 & -16711936)
                     + ((var19 & 0xFF00) * var12 & 0xFF0000)
                     + ((var20 & 16711935) * var13 & -16711936)
                     + ((var20 & 0xFF00) * var13 & 0xFF0000)
                  >>> 8;
               var1[var14] = var21 | 0xFF000000;
            }

            var16 += var10;
            var17 += var11;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDyFixed;
      }
   }
}
