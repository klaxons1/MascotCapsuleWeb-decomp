package com.hicorp.mascotcapsule.web;

public final class UnlitDrawer_T1_Alpha_Quad extends UnlitDrawer {
   private final Config rasterizer;

   public UnlitDrawer_T1_Alpha_Quad(Config rasterizer) {
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
      if (super.y < Config.getClipLeft(this.rasterizer)) {
         int var13;
         if (super.yEnd < Config.getClipLeft(this.rasterizer)) {
            var13 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var13 = Config.getClipLeft(this.rasterizer) - super.y;
            super.y = Config.getClipLeft(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * var13;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var13;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var13;
         super.uFixed = super.uFixed + super.dvDyFixed * var13;
         super.vFixed = super.vFixed + super.duDxFixed * var13;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed * var13;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed
      ) {
         int var25 = super.xLeftFixed >> 16;
         int var14 = super.xRightFixed >> 16;
         int var15 = super.uFixed >> var3;
         int var16 = super.vFixed >> var3;
         int var17 = super.duDyFixed;
         if (var25 < Config.getBufferHeight(this.rasterizer)) {
            int var18 = Config.getBufferHeight(this.rasterizer) - var25;
            var25 = Config.getBufferHeight(this.rasterizer);
            var15 += var10 * var18;
            var16 += var11 * var18;
            var17 += var12 * var18;
         }

         if (var14 > Config.getClipRight(this.rasterizer)) {
            var14 = Config.getClipRight(this.rasterizer);
         }

         int var26 = super.scanlineOffset + var25;

         for (int var19 = super.scanlineOffset + var14; var26 < var19; var26++) {
            int var20 = var5 + ((var16 & var7) >>> var9) + ((var15 & var6) >>> var8);
            int var21 = var4[var20];
            if (var21 != -1) {
               int var22 = var1[var26];
               int var23 = var17 >>> 16;
               int var24 = ((var21 & 16711935) * var23 & -16711936) + ((var21 & 0xFF00) * var23 & 0xFF0000) >>> 8;
               var24 = (var24 & 16711422) + (var22 & 16711422) >>> 1;
               var1[var26] = var24 | 0xFF000000;
            }

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
