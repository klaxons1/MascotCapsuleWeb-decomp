package com.hicorp.mascotcapsule.web;

public final class Class_13d8 extends Class_1279 {
   private final Config var_f3;

   public Class_13d8(Config var1) {
      super(var1);
      this.var_f3 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_f3);
      int var2 = (super.duDxFixed > 0 ? super.duDxFixed : -super.duDxFixed) + (super.dvDxFixed > 0 ? super.dvDxFixed : -super.dvDxFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.var_f3).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.var_f3).getPixels();
      int var5 = Config.getDiffuseTexture(this.var_f3).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.var_f3).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.var_f3).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.var_f3).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.var_f3).getMipVShift(var3);
      int var10 = super.duDxFixed >> var3;
      int var11 = super.dvDxFixed >> var3;
      if (super.y < Config.getClipLeft(this.var_f3)) {
         int var12;
         if (super.yEnd < Config.getClipLeft(this.var_f3)) {
            var12 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var12 = Config.getClipLeft(this.var_f3) - super.y;
            super.y = Config.getClipLeft(this.var_f3);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_f3) * var12;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var12;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var12;
         super.uFixed = super.uFixed + super.duDyFixed * var12;
         super.vFixed = super.vFixed + super.dvDyFixed * var12;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.var_f3) ? super.yEnd : Config.getClipBottom(this.var_f3);
         super.y < super.yEnd;
         super.vFixed = super.vFixed + super.dvDyFixed
      ) {
         int var22 = super.xLeftFixed >> 16;
         int var13 = super.xRightFixed >> 16;
         int var14 = super.uFixed >> var3;
         int var15 = super.vFixed >> var3;
         if (var22 < Config.getBufferHeight(this.var_f3)) {
            int var16 = Config.getBufferHeight(this.var_f3) - var22;
            var22 = Config.getBufferHeight(this.var_f3);
            var14 += var10 * var16;
            var15 += var11 * var16;
         }

         if (var13 > Config.getClipRight(this.var_f3)) {
            var13 = Config.getClipRight(this.var_f3);
         }

         int var23 = super.scanlineOffset + var22;

         for (int var17 = super.scanlineOffset + var13; var23 < var17; var23++) {
            int var18 = var5 + ((var15 & var7) >>> var9) + ((var14 & var6) >>> var8);
            int var19 = var4[var18];
            int var20 = var1[var23];
            int var21 = ((var19 & var20) << 1) + ((var19 ^ var20) & 16711422) & 16843008;
            var21 = (var21 >>> 8) + 8355711 ^ 8355711;
            var21 = var19 + var20 - var21 | var21;
            var1[var23] = var21 | 0xFF000000;
            var14 += var10;
            var15 += var11;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_f3);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDyFixed;
      }
   }
}
