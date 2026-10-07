package com.hicorp.mascotcapsule.web;

public final class LitDrawer_T2_Alpha_Triangle extends LitDrawer {
   private final Config rasterizer;

   public LitDrawer_T2_Alpha_Triangle(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int[] var2 = Config.getColorTable();
      int var3 = (super.dLightDxFixed > 0 ? super.dLightDxFixed : -super.dLightDxFixed) + (super.normalZFixed > 0 ? super.normalZFixed : -super.normalZFixed) + 32768;
      int var4 = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(var3) - 17);
      int[] var5 = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int var6 = Config.getDiffuseTexture(this.rasterizer).getMipOffset(var4);
      int var7 = Config.getDiffuseTexture(this.rasterizer).getMipUMask(var4);
      int var8 = Config.getDiffuseTexture(this.rasterizer).getMipVMask(var4);
      int var9 = Config.getDiffuseTexture(this.rasterizer).getMipUShift(var4);
      int var10 = Config.getDiffuseTexture(this.rasterizer).getMipVShift(var4);
      int var11 = super.dLightDxFixed >> var4;
      int var12 = super.normalZFixed >> var4;
      int var13 = super.dNormalZDyFixed;
      int var14 = super.dNormalZDxFixed;
      int var15 = Config.getBlendAlpha(this.rasterizer);
      int var16 = 255 - Config.getBlendAlpha(this.rasterizer);

      for (super.dvDyFixed += 8388608; super.y < super.yEnd; super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed) {
         int var17 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var18 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var19 = super.uFixed >> var4;
         int var20 = super.vFixed >> var4;
         int var21 = super.duDyFixed;

         for (int var22 = super.dvDyFixed; var17 < var18; var17++) {
            int var23 = var6 + ((var20 & var8) >>> var10) + ((var19 & var7) >>> var9);
            int var24 = var5[var23];
            if (var24 != -1) {
               int var25 = var1[var17];
               int var26 = var21 >>> 16;
               int var27 = var2[var22 >>> 16 & 511];
               int var30 = ((var24 & 16711935) * var26 & -16711936) + ((var24 & 0xFF00) * var26 & 0xFF0000) >>> 8;
               int var28 = ((var30 & var27) << 1) + ((var30 ^ var27) & 16711422) & 16843008;
               var28 = (var28 >>> 8) + 8355711 ^ 8355711;
               var28 = var30 + var27 - var28 | var28;
               int var29 = ((var28 & 16711935) * var15 & -16711936)
                     + ((var28 & 0xFF00) * var15 & 0xFF0000)
                     + ((var25 & 16711935) * var16 & -16711936)
                     + ((var25 & 0xFF00) * var16 & 0xFF0000)
                  >>> 8;
               var1[var17] = var29 | 0xFF000000;
            }

            var19 += var11;
            var20 += var12;
            var21 += var13;
            var22 += var14;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDxFixed;
         super.vFixed = super.vFixed + super.dvDxFixed;
         super.duDyFixed = super.duDyFixed + super.lightFixed;
      }

      super.dvDyFixed -= 8388608;
   }
}
