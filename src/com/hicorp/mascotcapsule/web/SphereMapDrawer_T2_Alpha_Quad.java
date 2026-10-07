package com.hicorp.mascotcapsule.web;

public final class SphereMapDrawer_T2_Alpha_Quad extends SphereMapDrawer {
   private final Config rasterizer;

   public SphereMapDrawer_T2_Alpha_Quad(Config rasterizer) {
      super(var1);
      this.rasterizer = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.rasterizer);
      int var2 = (super.sphereVFixed > 0 ? super.sphereVFixed : -super.sphereVFixed) + (super.dSphereUDyFixed > 0 ? super.dSphereUDyFixed : -super.dSphereUDyFixed) + 32768;
      int var3 = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var4 = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int var5 = Config.getDiffuseTexture(this.rasterizer).getMipOffset(var3);
      int var6 = Config.getDiffuseTexture(this.rasterizer).getMipUMask(var3);
      int var7 = Config.getDiffuseTexture(this.rasterizer).getMipVMask(var3);
      int var8 = Config.getDiffuseTexture(this.rasterizer).getMipUShift(var3);
      int var9 = Config.getDiffuseTexture(this.rasterizer).getMipVShift(var3);
      int var10 = super.sphereVFixed >> var3;
      int var11 = super.dSphereUDyFixed >> var3;
      int var12 = super.dSphereVDyFixed;
      var2 = (super.dSphereUDxFixed > 0 ? super.dSphereUDxFixed : -super.dSphereUDxFixed) + (super.dSphereVDxFixed > 0 ? super.dSphereVDxFixed : -super.dSphereVDxFixed) + 32768;
      int var13 = Config.getSphereMapTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(var2) - 17);
      int[] var14 = Config.getSphereMapTexture(this.rasterizer).getPixels();
      int var15 = Config.getSphereMapTexture(this.rasterizer).getMipOffset(var13);
      int var16 = Config.getSphereMapTexture(this.rasterizer).getMipUMask(var13);
      int var17 = Config.getSphereMapTexture(this.rasterizer).getMipVMask(var13);
      int var18 = Config.getSphereMapTexture(this.rasterizer).getMipUShift(var13);
      int var19 = Config.getSphereMapTexture(this.rasterizer).getMipVShift(var13);
      int var20 = super.dSphereUDxFixed >> var13;
      int var21 = super.dSphereVDxFixed >> var13;
      int var22 = Config.getColorKey(this.rasterizer);
      int var23 = 255 - Config.getColorKey(this.rasterizer);
      if (super.y < Config.getClipLeft(this.rasterizer)) {
         int var24;
         if (super.yEnd < Config.getClipLeft(this.rasterizer)) {
            var24 = super.yEnd - super.y;
            super.y = super.yEnd;
         } else {
            var24 = Config.getClipLeft(this.rasterizer) - super.y;
            super.y = Config.getClipLeft(this.rasterizer);
         }

         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer) * var24;
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed * var24;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed * var24;
         super.uFixed = super.uFixed + super.dvDxFixed * var24;
         super.vFixed = super.vFixed + super.lightFixed * var24;
         super.duDyFixed = super.duDyFixed + super.dLightDyFixed * var24;
         super.dvDyFixed = super.dvDyFixed + super.dLightDxFixed * var24;
         super.duDxFixed = super.duDxFixed + super.sphereUFixed * var24;
      }

      for (super.yEnd = super.yEnd < Config.getClipBottom(this.rasterizer) ? super.yEnd : Config.getClipBottom(this.rasterizer);
         super.y < super.yEnd;
         super.duDxFixed = super.duDxFixed + super.sphereUFixed
      ) {
         int var43 = super.xLeftFixed >> 16;
         int var25 = super.xRightFixed >> 16;
         int var26 = super.uFixed >> var3;
         int var27 = super.vFixed >> var3;
         int var28 = super.duDyFixed;
         int var29 = super.dvDyFixed >> var13;
         int var30 = super.duDxFixed >> var13;
         if (var43 < Config.getBufferHeight(this.rasterizer)) {
            int var31 = Config.getBufferHeight(this.rasterizer) - var43;
            var43 = Config.getBufferHeight(this.rasterizer);
            var26 += var10 * var31;
            var27 += var11 * var31;
            var28 += var12 * var31;
            var29 += var20 * var31;
            var30 += var21 * var31;
         }

         if (var25 > Config.getClipRight(this.rasterizer)) {
            var25 = Config.getClipRight(this.rasterizer);
         }

         int var44 = super.scanlineOffset + var43;

         for (int var32 = super.scanlineOffset + var25; var44 < var32; var44++) {
            int var33 = var5 + ((var27 & var7) >>> var9) + ((var26 & var6) >>> var8);
            int var34 = var4[var33];
            if (var34 != -1) {
               int var35 = var15 + ((var30 & var17) >>> var19) + ((var29 & var16) >>> var18);
               int var36 = var1[var44];
               int var37 = var28 >>> 16;
               int var38 = var14[var35];
               int var41 = ((var34 & 16711935) * var37 & -16711936) + ((var34 & 0xFF00) * var37 & 0xFF0000) >>> 8;
               int var39 = ((var41 & var38) << 1) + ((var41 ^ var38) & 16711422) & 16843008;
               var39 = (var39 >>> 8) + 8355711 ^ 8355711;
               var39 = var41 + var38 - var39 | var39;
               int var40 = ((var39 & 16711935) * var22 & -16711936)
                     + ((var39 & 0xFF00) * var22 & 0xFF0000)
                     + ((var36 & 16711935) * var23 & -16711936)
                     + ((var36 & 0xFF00) * var23 & 0xFF0000)
                  >>> 8;
               var1[var44] = var40 | 0xFF000000;
            }

            var26 += var10;
            var27 += var11;
            var28 += var12;
            var29 += var20;
            var30 += var21;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.dvDxFixed;
         super.vFixed = super.vFixed + super.lightFixed;
         super.duDyFixed = super.duDyFixed + super.dLightDyFixed;
         super.dvDyFixed = super.dvDyFixed + super.dLightDxFixed;
      }
   }
}
