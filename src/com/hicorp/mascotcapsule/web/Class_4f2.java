package com.hicorp.mascotcapsule.web;

public final class Class_4f2 extends ModelLoader {
   private final Config var_56;

   public Class_4f2(Config var1) {
      super(var1);
      this.var_56 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_56);
      int var2 = (super.lightFixed > 0 ? super.lightFixed : -super.lightFixed) + (super.dLightDyFixed > 0 ? super.dLightDyFixed : -super.dLightDyFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.var_56).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.var_56).getPixels();
      int var5 = Config.getDiffuseTexture(this.var_56).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.var_56).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.var_56).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.var_56).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.var_56).getMipVShift(var3);
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
            int var20 = var1[var13];
            int var21 = var17 >>> 16;
            int var23 = ((var19 & 16711935) * var21 & -16711936) + ((var19 & 0xFF00) * var21 & 0xFF0000) >>> 8;
            int var22 = ((var23 & var20) << 1) + ((var23 ^ var20) & 16711422) & 16843008;
            var22 = (var22 >>> 8) + 8355711 ^ 8355711;
            var22 = var23 + var20 - var22 | var22;
            var1[var13] = var22 | 0xFF000000;
            var15 += var10;
            var16 += var11;
            var17 += var12;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_56);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDyFixed;
         super.vFixed = super.vFixed + super.duDxFixed;
      }
   }
}
