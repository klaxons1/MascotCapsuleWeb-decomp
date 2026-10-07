package com.hicorp.mascotcapsule.web;

public final class UnlitDrawer_T2_Opaque_Quad extends UnlitDrawer {
   private final Config rasterizer;

   public UnlitDrawer_T2_Opaque_Quad(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int var2 = (super.lightFixed > 0 ? super.lightFixed : -super.lightFixed) + (super.dLightDyFixed > 0 ? super.dLightDyFixed : -super.dLightDyFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int var5 = Config.getDiffuseTexture(this.rasterizer).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.rasterizer).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.rasterizer).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.rasterizer).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.rasterizer).getMipVShift(var3);
      int var10 = super.lightFixed >> var3;
      int var11 = super.dLightDyFixed >> var3;
      int var12 = super.dLightDxFixed;
      int var13 = Config.getBlendAlpha(this.rasterizer);
      int var14 = 255 - Config.getBlendAlpha(this.rasterizer);
      if (super.y < Config.getClipTop(this.rasterizer)) {
         int var15;
         if (super.yEnd < Config.getClipTop(this.rasterizer)) {
            var15 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var15 = Config.getClipTop(this.rasterizer) - super.y;
            super.y = Config.getClipTop(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * var15;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var15;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var15;
         super.uFixed = super.uFixed + super.dvDyFixed * var15;
         super.vFixed = super.vFixed + super.duDxFixed * var15;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed * var15;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed
      ) {
         int var27 = super.xLeftFixed >> 16;
         int var16 = super.xRightFixed >> 16;
         int var17 = super.uFixed >> var3;
         int var18 = super.vFixed >> var3;
         int var19 = super.duDyFixed;
         if (var27 < Config.getClipLeft(this.rasterizer)) {
            int var20 = Config.getClipLeft(this.rasterizer) - var27;
            var27 = Config.getClipLeft(this.rasterizer);
            var17 += var10 * var20;
            var18 += var11 * var20;
            var19 += var12 * var20;
         }

         if (var16 > Config.getClipRight(this.rasterizer)) {
            var16 = Config.getClipRight(this.rasterizer);
         }

         int var28 = super.scanlineOffset + var27;

         for (int var21 = super.scanlineOffset + var16; var28 < var21; var28++) {
            int var22 = var5 + ((var18 & var7) >>> var9) + ((var17 & var6) >>> var8);
            int var23 = var4[var22];
            int var24 = var1[var28];
            int var25 = var19 * var13 >>> 24;
            int var26 = ((var23 & 16711935) * var25 & -16711936)
                  + ((var23 & 0xFF00) * var25 & 0xFF0000)
                  + ((var24 & 16711935) * var14 & -16711936)
                  + ((var24 & 0xFF00) * var14 & 0xFF0000)
               >>> 8;
            var1[var28] = var26 | 0xFF000000;
            var17 += var10;
            var18 += var11;
            var19 += var12;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDyFixed;
         super.vFixed = super.vFixed + super.duDxFixed;
      }
   }
}
