package com.hicorp.mascotcapsule.web;

public final class UnlitDrawer_T2_Opaque_Triangle extends UnlitDrawer {
   private final Config rasterizer;

   public UnlitDrawer_T2_Opaque_Triangle(Config rasterizer) {
      super(var1);
      this.rasterizer = var1;
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
      int var13 = Config.getColorKey(this.rasterizer);

      for (int var14 = 255 - Config.getColorKey(this.rasterizer); super.y < super.yEnd; super.duDyFixed = super.duDyFixed + super.dvDxFixed) {
         int var15 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var16 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var17 = super.uFixed >> var3;
         int var18 = super.vFixed >> var3;

         for (int var19 = super.duDyFixed; var15 < var16; var15++) {
            int var20 = var5 + ((var18 & var7) >>> var9) + ((var17 & var6) >>> var8);
            int var21 = var4[var20];
            int var22 = var1[var15];
            int var23 = var19 * var13 >>> 24;
            int var24 = ((var21 & 16711935) * var23 & -16711936)
                  + ((var21 & 0xFF00) * var23 & 0xFF0000)
                  + ((var22 & 16711935) * var14 & -16711936)
                  + ((var22 & 0xFF00) * var14 & 0xFF0000)
               >>> 8;
            var1[var15] = var24 | 0xFF000000;
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
