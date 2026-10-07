package com.hicorp.mascotcapsule.web;

public final class Class_1e8 extends Class_1279 {
   private final Config var_4c;

   public Class_1e8(Config var1) {
      super(var1);
      this.var_4c = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_4c);
      int var2 = (super.duDxFixed > 0 ? super.duDxFixed : -super.duDxFixed) + (super.dvDxFixed > 0 ? super.dvDxFixed : -super.dvDxFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.var_4c).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.var_4c).getPixels();
      int var5 = Config.getDiffuseTexture(this.var_4c).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.var_4c).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.var_4c).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.var_4c).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.var_4c).getMipVShift(var3);
      int var10 = super.duDxFixed >> var3;

      for (int var11 = super.dvDxFixed >> var3; super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int var12 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var13 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var14 = super.uFixed >> var3;

         for (int var15 = super.vFixed >> var3; var12 < var13; var12++) {
            int var16 = var5 + ((var15 & var7) >>> var9) + ((var14 & var6) >>> var8);
            int var17 = var4[var16];
            if (var17 != -1) {
               int var18 = var1[var12];
               int var19 = (var17 & 16711422) + (var18 & 16711422) >>> 1;
               var1[var12] = var19 | 0xFF000000;
            }

            var14 += var10;
            var15 += var11;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_4c);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDyFixed;
      }
   }
}
