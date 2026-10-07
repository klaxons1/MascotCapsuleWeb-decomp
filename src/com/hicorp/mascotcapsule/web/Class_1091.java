package com.hicorp.mascotcapsule.web;

public final class Class_1091 extends MeshLoader {
   private final Config var_36;

   public Class_1091(Config var1) {
      super(var1);
      this.var_36 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_36);
      int[] var2 = Config.getColorTable();
      int var3 = (super.dLightDxFixed > 0 ? super.dLightDxFixed : -super.dLightDxFixed) + (super.normalZFixed > 0 ? super.normalZFixed : -super.normalZFixed) + 32768;
      int var4 = Config.getDiffuseTexture(this.var_36).selectMipLevel(MatrixUtils.ceilLog2(var3) - 17);
      int[] var5 = Config.getDiffuseTexture(this.var_36).getPixels();
      int var6 = Config.getDiffuseTexture(this.var_36).getMipOffset(var4);
      int var7 = Config.getDiffuseTexture(this.var_36).getMipUMask(var4);
      int var8 = Config.getDiffuseTexture(this.var_36).getMipVMask(var4);
      int var9 = Config.getDiffuseTexture(this.var_36).getMipUShift(var4);
      int var10 = Config.getDiffuseTexture(this.var_36).getMipVShift(var4);
      int var11 = super.dLightDxFixed >> var4;
      int var12 = super.normalZFixed >> var4;
      int var13 = super.dNormalZDyFixed;
      int var14 = super.dNormalZDxFixed;

      for (super.dvDyFixed += 8388608; super.y < super.yEnd; super.dvDyFixed = super.dvDyFixed + super.dLightDyFixed) {
         int var15 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var16 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var17 = super.uFixed >> var4;
         int var18 = super.vFixed >> var4;
         int var19 = super.duDyFixed;

         for (int var20 = super.dvDyFixed; var15 < var16; var15++) {
            int var21 = var6 + ((var18 & var8) >>> var10) + ((var17 & var7) >>> var9);
            int var22 = var5[var21];
            if (var22 != -1) {
               int var23 = var1[var15];
               int var24 = var19 >>> 16;
               int var25 = var2[var20 >>> 16 & 511];
               int var27 = ((var22 & 16711935) * var24 & -16711936) + ((var22 & 0xFF00) * var24 & 0xFF0000) >>> 8;
               int var26 = ((var27 & var25) << 1) + ((var27 ^ var25) & 16711422) & 16843008;
               var26 = (var26 >>> 8) + 8355711 ^ 8355711;
               var26 = var27 + var25 - var26 | var26;
               var26 = (var26 & 16711422) + (var23 & 16711422) >>> 1;
               var1[var15] = var26 | 0xFF000000;
            }

            var17 += var11;
            var18 += var12;
            var19 += var13;
            var20 += var14;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_36);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDxFixed;
         super.vFixed = super.vFixed + super.dvDxFixed;
         super.duDyFixed = super.duDyFixed + super.lightFixed;
      }

      super.dvDyFixed -= 8388608;
   }
}
