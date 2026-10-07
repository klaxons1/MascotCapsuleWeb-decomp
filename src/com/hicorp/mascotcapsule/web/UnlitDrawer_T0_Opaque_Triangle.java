package com.hicorp.mascotcapsule.web;

public final class UnlitDrawer_T0_Opaque_Triangle extends UnlitDrawer {
   private final Config rasterizer;

   public UnlitDrawer_T0_Opaque_Triangle(Config rasterizer) {
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

      for (int var12 = super.dLightDxFixed; super.y < super.yEnd; super.duDyFixed = super.duDyFixed + super.dvDxFixed) {
         int var13 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var14 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var15 = super.uFixed >> var3;
         int var16 = super.vFixed >> var3;

         for (int var17 = super.duDyFixed; var13 < var14; var13++) {
            int var18 = var5 + ((var16 & var7) >>> var9) + ((var15 & var6) >>> var8);
            int var19 = var4[var18];
            int var20 = var17 >>> 16;
            int var21 = ((var19 & 16711935) * var20 & -16711936) + ((var19 & 0xFF00) * var20 & 0xFF0000) >>> 8;
            var1[var13] = var21 | 0xFF000000;
            var15 += var10;
            var16 += var11;
            var17 += var12;
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
