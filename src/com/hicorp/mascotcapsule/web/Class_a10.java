package com.hicorp.mascotcapsule.web;

public final class Class_a10 extends Class_15d5 {
   private final Config var_3e;

   public Class_a10(Config var1) {
      super(var1);
      this.var_3e = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_3e);
      int var2 = (super.sphereVFixed > 0 ? super.sphereVFixed : -super.sphereVFixed) + (super.dSphereUDyFixed > 0 ? super.dSphereUDyFixed : -super.dSphereUDyFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.var_3e).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.var_3e).getPixels();
      int var5 = Config.getDiffuseTexture(this.var_3e).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.var_3e).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.var_3e).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.var_3e).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.var_3e).getMipVShift(var3);
      int var10 = super.sphereVFixed >> var3;
      int var11 = super.dSphereUDyFixed >> var3;
      int var12 = super.dSphereVDyFixed;
      var2 = (super.dSphereUDxFixed > 0 ? super.dSphereUDxFixed : -super.dSphereUDxFixed) + (super.dSphereVDxFixed > 0 ? super.dSphereVDxFixed : -super.dSphereVDxFixed) + 32768;
      int var13 = Config.getSphereMapTexture(this.var_3e).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var14 = Config.getSphereMapTexture(this.var_3e).getPixels();
      int var15 = Config.getSphereMapTexture(this.var_3e).getMipOffset(var13);
      int var16 = Config.getSphereMapTexture(this.var_3e).getMipUMask(var13);
      int var17 = Config.getSphereMapTexture(this.var_3e).getMipVMask(var13);
      int var18 = Config.getSphereMapTexture(this.var_3e).getMipUShift(var13);
      int var19 = Config.getSphereMapTexture(this.var_3e).getMipVShift(var13);
      int var20 = super.dSphereUDxFixed >> var13;

      for (int var21 = super.dSphereVDxFixed >> var13; super.y < super.yEnd; super.duDxFixed = super.duDxFixed + super.sphereUFixed) {
         int var22 = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int var23 = (super.xRightFixed >> 16) + super.scanlineOffset;
         int var24 = super.uFixed >> var3;
         int var25 = super.vFixed >> var3;
         int var26 = super.duDyFixed;
         int var27 = super.dvDyFixed >> var13;

         for (int var28 = super.duDxFixed >> var13; var22 < var23; var22++) {
            int var29 = var5 + ((var25 & var7) >>> var9) + ((var24 & var6) >>> var8);
            int var30 = var15 + ((var28 & var17) >>> var19) + ((var27 & var16) >>> var18);
            int var31 = var4[var29];
            int var32 = var1[var22];
            int var33 = var26 >>> 16;
            int var34 = var14[var30];
            int var36 = ((var31 & 16711935) * var33 & -16711936) + ((var31 & 0xFF00) * var33 & 0xFF0000) >>> 8;
            int var35 = ((var36 & var34) << 1) + ((var36 ^ var34) & 16711422) & 16843008;
            var35 = (var35 >>> 8) + 8355711 ^ 8355711;
            var35 = var36 + var34 - var35 | var35;
            var35 = (var35 & 16711422) + (var32 & 16711422) >>> 1;
            var1[var22] = var35 | 0xFF000000;
            var24 += var10;
            var25 += var11;
            var26 += var12;
            var27 += var20;
            var28 += var21;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_3e);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDxFixed;
         super.vFixed = super.vFixed + super.lightFixed;
         super.duDyFixed = super.duDyFixed + super.dLightDyFixed;
         super.dvDyFixed = super.dvDyFixed + super.dLightDxFixed;
      }
   }
}
