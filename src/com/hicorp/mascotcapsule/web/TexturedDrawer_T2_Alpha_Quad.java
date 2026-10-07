package com.hicorp.mascotcapsule.web;

public final class TexturedDrawer_T2_Alpha_Quad extends TexturedDrawer {
   private final Config rasterizer;

   public TexturedDrawer_T2_Alpha_Quad(Config rasterizer) {
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
      int var13 = 255 - Config.getBlendAlpha(this.rasterizer);
      if (super.y < Config.getClipTop(this.rasterizer)) {
         int var14;
         if (super.yEnd < Config.getClipTop(this.rasterizer)) {
            var14 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var14 = Config.getClipTop(this.rasterizer) - super.y;
            super.y = Config.getClipTop(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * var14;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var14;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var14;
         super.uFixed = super.uFixed + super.duDyFixed * var14;
         super.vFixed = super.vFixed + super.dvDyFixed * var14;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.vFixed = super.vFixed + super.dvDyFixed
      ) {
         int var24 = super.xLeftFixed >> 16;
         int var15 = super.xRightFixed >> 16;
         int var16 = super.uFixed >> var3;
         int var17 = super.vFixed >> var3;
         if (var24 < Config.getClipLeft(this.rasterizer)) {
            int var18 = Config.getClipLeft(this.rasterizer) - var24;
            var24 = Config.getClipLeft(this.rasterizer);
            var16 += var10 * var18;
            var17 += var11 * var18;
         }

         if (var15 > Config.getClipRight(this.rasterizer)) {
            var15 = Config.getClipRight(this.rasterizer);
         }

         int var25 = super.scanlineOffset + var24;

         for (int var19 = super.scanlineOffset + var15; var25 < var19; var25++) {
            int var20 = var5 + ((var17 & var7) >>> var9) + ((var16 & var6) >>> var8);
            int var21 = var4[var20];
            if (var21 != -1) {
               int var22 = var1[var25];
               int var23 = ((var21 & 16711935) * var12 & -16711936)
                     + ((var21 & 0xFF00) * var12 & 0xFF0000)
                     + ((var22 & 16711935) * var13 & -16711936)
                     + ((var22 & 0xFF00) * var13 & 0xFF0000)
                  >>> 8;
               var1[var25] = var23 | 0xFF000000;
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
