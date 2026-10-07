package com.hicorp.mascotcapsule.web;

public final class TextureLoader extends MeshLoader {
   private final Config var_48;

   public TextureLoader(Config var1) {
      super(var1);
      this.var_48 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_48);
      int[] var2 = Config.getColorTable();
      int var3 = (super.dLightDxFixed > 0 ? super.dLightDxFixed : -super.dLightDxFixed) + (super.normalZFixed > 0 ? super.normalZFixed : -super.normalZFixed) + 32768;
      int var4 = Config.getDiffuseTexture(this.var_48).selectMipLevel(MatrixUtils.ceilLog2(var3) - 17);
      int[] var5 = Config.getDiffuseTexture(this.var_48).getPixels();
      int var6 = Config.getDiffuseTexture(this.var_48).getMipOffset(var4);
      int var7 = Config.getDiffuseTexture(this.var_48).getMipUMask(var4);
      int var8 = Config.getDiffuseTexture(this.var_48).getMipVMask(var4);
      int var9 = Config.getDiffuseTexture(this.var_48).getMipUShift(var4);
      int var10 = Config.getDiffuseTexture(this.var_48).getMipVShift(var4);
      int var11 = super.dLightDxFixed >> var4;
      int var12 = super.normalZFixed >> var4;
      int var13 = super.dNormalZDyFixed;
      int var14 = super.dNormalZDxFixed;
      if (super.y < Config.getClipLeft(this.var_48)) {
         int var15;
         if (super.yEnd < Config.getClipLeft(this.var_48)) {
            var15 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var15 = Config.getClipLeft(this.var_48) - super.y;
            super.y = Config.getClipLeft(this.var_48);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_48) * var15;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var15;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var15;
         super.uFixed = super.uFixed + super.duDxFixed * var15;
         super.vFixed = super.vFixed + super.dvDxFixed * var15;
         super.duDyFixed = super.duDyFixed + super.lightFixed * var15;
         super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed * var15;
      }

      super.yEnd = super.yEnd < Config.getClipBottom(this.var_48) ? super.yEnd : Config.getClipBottom(this.var_48);

      for (super.dvDyFixed += 8388608; super.y < super.yEnd; super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed) {
         int var30 = super.xLeftFixed >> 16;
         int var16 = super.xRightFixed >> 16;
         int var17 = super.uFixed >> var4;
         int var18 = super.vFixed >> var4;
         int var19 = super.duDyFixed;
         int var20 = super.dvDyFixed;
         if (var30 < Config.getBufferHeight(this.var_48)) {
            int var21 = Config.getBufferHeight(this.var_48) - var30;
            var30 = Config.getBufferHeight(this.var_48);
            var17 += var11 * var21;
            var18 += var12 * var21;
            var19 += var13 * var21;
            var20 += var14 * var21;
         }

         if (var16 > Config.getClipRight(this.var_48)) {
            var16 = Config.getClipRight(this.var_48);
         }

         int var31 = super.scanlineOffset + var30;

         for (int var22 = super.scanlineOffset + var16; var31 < var22; var31++) {
            int var23 = var6 + ((var18 & var8) >>> var10) + ((var17 & var7) >>> var9);
            int var24 = var5[var23];
            int var25 = var1[var31];
            int var26 = var19 >>> 16;
            int var27 = var2[var20 >>> 16 & 511];
            int var29 = ((var24 & 16711935) * var26 & -16711936) + ((var24 & 0xFF00) * var26 & 0xFF0000) >>> 8;
            int var28 = ((var29 & var27) << 1) + ((var29 ^ var27) & 16711422) & 16843008;
            var28 = (var28 >>> 8) + 8355711 ^ 8355711;
            var28 = var29 + var27 - var28 | var28;
            var28 = (var28 & 16711422) + (var25 & 16711422) >>> 1;
            var1[var31] = var28 | 0xFF000000;
            var17 += var11;
            var18 += var12;
            var19 += var13;
            var20 += var14;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_48);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDxFixed;
         super.vFixed = super.vFixed + super.dvDxFixed;
         super.duDyFixed = super.duDyFixed + super.lightFixed;
      }

      super.dvDyFixed -= 8388608;
   }
}
