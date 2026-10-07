package com.hicorp.mascotcapsule.web;

public final class Class_10ea extends ModelLoader {
   private final Config var_1e;

   public Class_10ea(Config var1) {
      super(var1);
      this.var_1e = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_1e);
      int var2 = (super.lightFixed > 0 ? super.lightFixed : -super.lightFixed) + (super.dLightDyFixed > 0 ? super.dLightDyFixed : -super.dLightDyFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.var_1e).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.var_1e).getPixels();
      int var5 = Config.getDiffuseTexture(this.var_1e).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.var_1e).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.var_1e).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.var_1e).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.var_1e).getMipVShift(var3);
      int var10 = super.lightFixed >> var3;
      int var11 = super.dLightDyFixed >> var3;
      int var12 = super.dLightDxFixed;
      int var13 = Config.getColorKey(this.var_1e);
      int var14 = 255 - Config.getColorKey(this.var_1e);
      if (super.y < Config.getClipLeft(this.var_1e)) {
         int var15;
         if (super.yEnd < Config.getClipLeft(this.var_1e)) {
            var15 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var15 = Config.getClipLeft(this.var_1e) - super.y;
            super.y = Config.getClipLeft(this.var_1e);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_1e) * var15;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var15;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var15;
         super.uFixed = super.uFixed + super.dvDyFixed * var15;
         super.vFixed = super.vFixed + super.duDxFixed * var15;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed * var15;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.var_1e) ? super.yEnd : Config.getClipBottom(this.var_1e);
         super.y < super.yEnd;
         super.duDyFixed = super.duDyFixed + super.dvDxFixed
      ) {
         int var27 = super.xLeftFixed >> 16;
         int var16 = super.xRightFixed >> 16;
         int var17 = super.uFixed >> var3;
         int var18 = super.vFixed >> var3;
         int var19 = super.duDyFixed;
         if (var27 < Config.getBufferHeight(this.var_1e)) {
            int var20 = Config.getBufferHeight(this.var_1e) - var27;
            var27 = Config.getBufferHeight(this.var_1e);
            var17 += var10 * var20;
            var18 += var11 * var20;
            var19 += var12 * var20;
         }

         if (var16 > Config.getClipRight(this.var_1e)) {
            var16 = Config.getClipRight(this.var_1e);
         }

         int var28 = super.scanlineOffset + var27;

         for (int var21 = super.scanlineOffset + var16; var28 < var21; var28++) {
            int var22 = var5 + ((var18 & var7) >>> var9) + ((var17 & var6) >>> var8);
            int var23 = var4[var22];
            int var24 = var1[var28];
            int var25 = var19 * var13 >>> 24;
            int var26 = ((var23 & 16711935) * var25 & -16711936)
                  + ((var23 & 0xFF00) * var25 & 0xFF0000)
                  + ((var24 & 16711935) * var14 & -16711936)
                  + ((var24 & 0xFF00) * var14 & 0xFF0000)
               >>> 8;
            var1[var28] = var26 | 0xFF000000;
            var17 += var10;
            var18 += var11;
            var19 += var12;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_1e);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDyFixed;
         super.vFixed = super.vFixed + super.duDxFixed;
      }
   }
}
