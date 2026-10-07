package com.hicorp.mascotcapsule.web;

public final class LitDrawer_T0_Alpha_Quad extends LitDrawer {
   private final Config rasterizer;

   public LitDrawer_T0_Alpha_Quad(Config rasterizer) {
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
         super.uFixed = super.uFixed + super.duDxFixed * var15;
         super.vFixed = super.vFixed + super.dvDxFixed * var15;
         super.duDyFixed = super.duDyFixed + super.lightFixed * var15;
         super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed * var15;
      }

      super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);

      for (super.dvDyFixed += 8388608; super.y < super.yEnd; super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed) {
         int var29 = super.xLeftFixed >> 16;
         int var16 = super.xRightFixed >> 16;
         int var17 = super.uFixed >> var4;
         int var18 = super.vFixed >> var4;
         int var19 = super.duDyFixed;
         int var20 = super.dvDyFixed;
         if (var29 < Config.getClipLeft(this.rasterizer)) {
            int var21 = Config.getClipLeft(this.rasterizer) - var29;
            var29 = Config.getClipLeft(this.rasterizer);
            var17 += var11 * var21;
            var18 += var12 * var21;
            var19 += var13 * var21;
            var20 += var14 * var21;
         }

         if (var16 > Config.getClipRight(this.rasterizer)) {
            var16 = Config.getClipRight(this.rasterizer);
         }

         int var30 = super.scanlineOffset + var29;

         for (int var22 = super.scanlineOffset + var16; var30 < var22; var30++) {
            int var23 = var6 + ((var18 & var8) >>> var10) + ((var17 & var7) >>> var9);
            int var24 = var5[var23];
            if (var24 != -1) {
               int var25 = var19 >>> 16;
               int var26 = var2[var20 >>> 16 & 511];
               int var28 = ((var24 & 16711935) * var25 & -16711936) + ((var24 & 0xFF00) * var25 & 0xFF0000) >>> 8;
               int var27 = ((var28 & var26) << 1) + ((var28 ^ var26) & 16711422) & 16843008;
               var27 = (var27 >>> 8) + 8355711 ^ 8355711;
               var27 = var28 + var26 - var27 | var27;
               var1[var30] = var27 | 0xFF000000;
            }

            var17 += var11;
            var18 += var12;
            var19 += var13;
            var20 += var14;
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
