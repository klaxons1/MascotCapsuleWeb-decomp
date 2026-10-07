package com.hicorp.mascotcapsule.web;

public final class Class_e88 extends MeshLoader {
   private final Config var_2f;

   public Class_e88(Config var1) {
      super(var1);
      this.var_2f = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_2f);
      int[] var2 = Config.getColorTable();
      int var3 = (super.dLightDxFixed > 0 ? super.dLightDxFixed : -super.dLightDxFixed) + (super.normalZFixed > 0 ? super.normalZFixed : -super.normalZFixed) + 32768;
      int var4 = Config.getDiffuseTexture(this.var_2f).selectMipLevel(MatrixUtils.ceilLog2(var3) - 17);
      int[] var5 = Config.getDiffuseTexture(this.var_2f).getPixels();
      int var6 = Config.getDiffuseTexture(this.var_2f).getMipOffset(var4);
      int var7 = Config.getDiffuseTexture(this.var_2f).getMipUMask(var4);
      int var8 = Config.getDiffuseTexture(this.var_2f).getMipVMask(var4);
      int var9 = Config.getDiffuseTexture(this.var_2f).getMipUShift(var4);
      int var10 = Config.getDiffuseTexture(this.var_2f).getMipVShift(var4);
      int var11 = super.dLightDxFixed >> var4;
      int var12 = super.normalZFixed >> var4;
      int var13 = super.dNormalZDyFixed;
      int var14 = super.dNormalZDxFixed;
      if (super.y < Config.getClipLeft(this.var_2f)) {
         int var15;
         if (super.yEnd < Config.getClipLeft(this.var_2f)) {
            var15 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var15 = Config.getClipLeft(this.var_2f) - super.y;
            super.y = Config.getClipLeft(this.var_2f);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_2f) * var15;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var15;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var15;
         super.uFixed = super.uFixed + super.duDxFixed * var15;
         super.vFixed = super.vFixed + super.dvDxFixed * var15;
         super.duDyFixed = super.duDyFixed + super.lightFixed * var15;
         super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed * var15;
      }

      super.yEnd = super.yEnd < Config.getClipBottom(this.var_2f) ? super.yEnd : Config.getClipBottom(this.var_2f);

      for (super.dvDyFixed += 8388608; super.y < super.yEnd; super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed) {
         int var31 = super.xLeftFixed >> 16;
         int var16 = super.xRightFixed >> 16;
         int var17 = super.uFixed >> var4;
         int var18 = super.vFixed >> var4;
         int var19 = super.duDyFixed;
         int var20 = super.dvDyFixed;
         if (var31 < Config.getBufferHeight(this.var_2f)) {
            int var21 = Config.getBufferHeight(this.var_2f) - var31;
            var31 = Config.getBufferHeight(this.var_2f);
            var17 += var11 * var21;
            var18 += var12 * var21;
            var19 += var13 * var21;
            var20 += var14 * var21;
         }

         if (var16 > Config.getClipRight(this.var_2f)) {
            var16 = Config.getClipRight(this.var_2f);
         }

         int var32 = super.scanlineOffset + var31;

         for (int var22 = super.scanlineOffset + var16; var32 < var22; var32++) {
            int var23 = var6 + ((var18 & var8) >>> var10) + ((var17 & var7) >>> var9);
            int var24 = var5[var23];
            int var25 = var1[var32];
            int var26 = var19 >>> 16;
            int var27 = var2[var20 >>> 16 & 511];
            int var30 = ((var24 & 16711935) * var26 & -16711936) + ((var24 & 0xFF00) * var26 & 0xFF0000) >>> 8;
            int var28 = ((var30 & var27) << 1) + ((var30 ^ var27) & 16711422) & 16843008;
            var28 = (var28 >>> 8) + 8355711 ^ 8355711;
            var28 = var30 + var27 - var28 | var28;
            int var29 = ((var28 & var25) << 1) + ((var28 ^ var25) & 16711422) & 16843008;
            var29 = (var29 >>> 8) + 8355711 ^ 8355711;
            var29 = var28 + var25 - var29 | var29;
            var1[var32] = var29 | 0xFF000000;
            var17 += var11;
            var18 += var12;
            var19 += var13;
            var20 += var14;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_2f);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDxFixed;
         super.vFixed = super.vFixed + super.dvDxFixed;
         super.duDyFixed = super.duDyFixed + super.lightFixed;
      }

      super.dvDyFixed -= 8388608;
   }
}
