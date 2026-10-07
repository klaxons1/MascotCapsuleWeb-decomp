package com.hicorp.mascotcapsule.web;

public final class Class_fa0 extends Class_15d5 {
   private final Config var_58;

   public Class_fa0(Config var1) {
      super(var1);
      this.var_58 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_58);
      int var2 = (super.sphereVFixed > 0 ? super.sphereVFixed : -super.sphereVFixed) + (super.dSphereUDyFixed > 0 ? super.dSphereUDyFixed : -super.dSphereUDyFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.var_58).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.var_58).getPixels();
      int var5 = Config.getDiffuseTexture(this.var_58).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.var_58).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.var_58).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.var_58).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.var_58).getMipVShift(var3);
      int var10 = super.sphereVFixed >> var3;
      int var11 = super.dSphereUDyFixed >> var3;
      int var12 = super.dSphereVDyFixed;
      var2 = (super.dSphereUDxFixed > 0 ? super.dSphereUDxFixed : -super.dSphereUDxFixed) + (super.dSphereVDxFixed > 0 ? super.dSphereVDxFixed : -super.dSphereVDxFixed) + 32768;
      int var13 = Config.getSphereMapTexture(this.var_58).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var14 = Config.getSphereMapTexture(this.var_58).getPixels();
      int var15 = Config.getSphereMapTexture(this.var_58).getMipOffset(var13);
      int var16 = Config.getSphereMapTexture(this.var_58).getMipUMask(var13);
      int var17 = Config.getSphereMapTexture(this.var_58).getMipVMask(var13);
      int var18 = Config.getSphereMapTexture(this.var_58).getMipUShift(var13);
      int var19 = Config.getSphereMapTexture(this.var_58).getMipVShift(var13);
      int var20 = super.dSphereUDxFixed >> var13;
      int var21 = super.dSphereVDxFixed >> var13;
      if (super.y < Config.getClipLeft(this.var_58)) {
         int var22;
         if (super.yEnd < Config.getClipLeft(this.var_58)) {
            var22 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var22 = Config.getClipLeft(this.var_58) - super.y;
            super.y = Config.getClipLeft(this.var_58);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_58) * var22;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var22;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var22;
         super.uFixed = super.uFixed + super.dvDxFixed * var22;
         super.vFixed = super.vFixed + super.lightFixed * var22;
         super.duDyFixed = super.duDyFixed + super.dLightDyFixed * var22;
         super.dvDyFixed = super.dvDyFixed + super.dLightDxFixed * var22;
         super.duDxFixed = super.duDxFixed + super.sphereUFixed * var22;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.var_58) ? super.yEnd : Config.getClipBottom(this.var_58);
         super.y < super.yEnd;
         super.duDxFixed = super.duDxFixed + super.sphereUFixed
      ) {
         int var39 = super.xLeftFixed >> 16;
         int var23 = super.xRightFixed >> 16;
         int var24 = super.uFixed >> var3;
         int var25 = super.vFixed >> var3;
         int var26 = super.duDyFixed;
         int var27 = super.dvDyFixed >> var13;
         int var28 = super.duDxFixed >> var13;
         if (var39 < Config.getBufferHeight(this.var_58)) {
            int var29 = Config.getBufferHeight(this.var_58) - var39;
            var39 = Config.getBufferHeight(this.var_58);
            var24 += var10 * var29;
            var25 += var11 * var29;
            var26 += var12 * var29;
            var27 += var20 * var29;
            var28 += var21 * var29;
         }

         if (var23 > Config.getClipRight(this.var_58)) {
            var23 = Config.getClipRight(this.var_58);
         }

         int var40 = super.scanlineOffset + var39;

         for (int var30 = super.scanlineOffset + var23; var40 < var30; var40++) {
            int var31 = var5 + ((var25 & var7) >>> var9) + ((var24 & var6) >>> var8);
            int var32 = var15 + ((var28 & var17) >>> var19) + ((var27 & var16) >>> var18);
            int var33 = var4[var31];
            int var34 = var26 >>> 16;
            int var35 = var14[var32];
            int var37 = ((var33 & 16711935) * var34 & -16711936) + ((var33 & 0xFF00) * var34 & 0xFF0000) >>> 8;
            int var36 = ((var37 & var35) << 1) + ((var37 ^ var35) & 16711422) & 16843008;
            var36 = (var36 >>> 8) + 8355711 ^ 8355711;
            var36 = var37 + var35 - var36 | var36;
            var1[var40] = var36 | 0xFF000000;
            var24 += var10;
            var25 += var11;
            var26 += var12;
            var27 += var20;
            var28 += var21;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_58);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDxFixed;
         super.vFixed = super.vFixed + super.lightFixed;
         super.duDyFixed = super.duDyFixed + super.dLightDyFixed;
         super.dvDyFixed = super.dvDyFixed + super.dLightDxFixed;
      }
   }
}
