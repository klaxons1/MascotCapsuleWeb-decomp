package com.hicorp.mascotcapsule.web;

public final class Class_da6 extends MeshLoader {
   private final Config var_e1;

   public Class_da6(Config var1) {
      super(var1);
      this.var_e1 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_e1);
      int[] var2 = Config.getColorTable();
      int var3 = (super.dLightDxFixed > 0 ? super.dLightDxFixed : -super.dLightDxFixed) + (super.normalZFixed > 0 ? super.normalZFixed : -super.normalZFixed) + 32768;
      int var4 = Config.getDiffuseTexture(this.var_e1).selectMipLevel(MatrixUtils.ceilLog2(var3) - 17);
      int[] var5 = Config.getDiffuseTexture(this.var_e1).getPixels();
      int var6 = Config.getDiffuseTexture(this.var_e1).getMipOffset(var4);
      int var7 = Config.getDiffuseTexture(this.var_e1).getMipUMask(var4);
      int var8 = Config.getDiffuseTexture(this.var_e1).getMipVMask(var4);
      int var9 = Config.getDiffuseTexture(this.var_e1).getMipUShift(var4);
      int var10 = Config.getDiffuseTexture(this.var_e1).getMipVShift(var4);
      int var11 = super.dLightDxFixed >> var4;
      int var12 = super.normalZFixed >> var4;
      int var13 = super.dNormalZDyFixed;
      int var14 = super.dNormalZDxFixed;
      int var15 = Config.getColorKey(this.var_e1);
      int var16 = 255 - Config.getColorKey(this.var_e1);
      if (super.y < Config.getClipLeft(this.var_e1)) {
         int var17;
         if (super.yEnd < Config.getClipLeft(this.var_e1)) {
            var17 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var17 = Config.getClipLeft(this.var_e1) - super.y;
            super.y = Config.getClipLeft(this.var_e1);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_e1) * var17;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var17;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var17;
         super.uFixed = super.uFixed + super.duDxFixed * var17;
         super.vFixed = super.vFixed + super.dvDxFixed * var17;
         super.duDyFixed = super.duDyFixed + super.lightFixed * var17;
         super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed * var17;
      }

      super.yEnd = super.yEnd < Config.getClipBottom(this.var_e1) ? super.yEnd : Config.getClipBottom(this.var_e1);

      for (super.dvDyFixed += 8388608; super.y < super.yEnd; super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed) {
         int var33 = super.xLeftFixed >> 16;
         int var18 = super.xRightFixed >> 16;
         int var19 = super.uFixed >> var4;
         int var20 = super.vFixed >> var4;
         int var21 = super.duDyFixed;
         int var22 = super.dvDyFixed;
         if (var33 < Config.getBufferHeight(this.var_e1)) {
            int var23 = Config.getBufferHeight(this.var_e1) - var33;
            var33 = Config.getBufferHeight(this.var_e1);
            var19 += var11 * var23;
            var20 += var12 * var23;
            var21 += var13 * var23;
            var22 += var14 * var23;
         }

         if (var18 > Config.getClipRight(this.var_e1)) {
            var18 = Config.getClipRight(this.var_e1);
         }

         int var34 = super.scanlineOffset + var33;

         for (int var24 = super.scanlineOffset + var18; var34 < var24; var34++) {
            int var25 = var6 + ((var20 & var8) >>> var10) + ((var19 & var7) >>> var9);
            int var26 = var5[var25];
            int var27 = var1[var34];
            int var28 = var21 >>> 16;
            int var29 = var2[var22 >>> 16 & 511];
            int var32 = ((var26 & 16711935) * var28 & -16711936) + ((var26 & 0xFF00) * var28 & 0xFF0000) >>> 8;
            int var30 = ((var32 & var29) << 1) + ((var32 ^ var29) & 16711422) & 16843008;
            var30 = (var30 >>> 8) + 8355711 ^ 8355711;
            var30 = var32 + var29 - var30 | var30;
            int var31 = ((var30 & 16711935) * var15 & -16711936)
                  + ((var30 & 0xFF00) * var15 & 0xFF0000)
                  + ((var27 & 16711935) * var16 & -16711936)
                  + ((var27 & 0xFF00) * var16 & 0xFF0000)
               >>> 8;
            var1[var34] = var31 | 0xFF000000;
            var19 += var11;
            var20 += var12;
            var21 += var13;
            var22 += var14;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_e1);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDxFixed;
         super.vFixed = super.vFixed + super.dvDxFixed;
         super.duDyFixed = super.duDyFixed + super.lightFixed;
      }

      super.dvDyFixed -= 8388608;
   }
}
