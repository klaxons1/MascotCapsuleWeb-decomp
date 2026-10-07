package com.hicorp.mascotcapsule.web;

public final class TexturedDrawer_T2_Alpha_Triangle extends TexturedDrawer {
   private final Config rasterizer;

   public TexturedDrawer_T2_Alpha_Triangle(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int stepDu = (super.duDxFixed > 0 ? super.duDxFixed : -super.duDxFixed) + (super.dvDxFixed > 0 ? super.dvDxFixed : -super.dvDxFixed) + 32768;
      int mipLevel = Config.getDiffuseTexture(this.rasterizer).selectMipLevel(MatrixUtils.ceilLog2(stepDu) - 17);
      int[] diffusePixels = Config.getDiffuseTexture(this.rasterizer).getPixels();
      int mipOffset = Config.getDiffuseTexture(this.rasterizer).getMipOffset(mipLevel);
      int mipUMask = Config.getDiffuseTexture(this.rasterizer).getMipUMask(mipLevel);
      int mipVMask = Config.getDiffuseTexture(this.rasterizer).getMipVMask(mipLevel);
      int mipUShift = Config.getDiffuseTexture(this.rasterizer).getMipUShift(mipLevel);
      int mipVShift = Config.getDiffuseTexture(this.rasterizer).getMipVShift(mipLevel);
      int stepDu2 = super.duDxFixed >> mipLevel;
      int stepDv = super.dvDxFixed >> mipLevel;
      int blendAlpha = Config.getBlendAlpha(this.rasterizer);

      for (int invBlendAlpha = 255 - Config.getBlendAlpha(this.rasterizer); super.y < super.yEnd; super.vFixed = super.vFixed + super.dvDyFixed) {
         int xLeft = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int xRight = (super.xRightFixed >> 16) + super.scanlineOffset;
         int curU = super.uFixed >> mipLevel;

         for (int curV = super.vFixed >> mipLevel; xLeft < xRight; xLeft++) {
            int texelOffset = mipOffset + ((curV & mipVMask) >>> mipVShift) + ((curU & mipUMask) >>> mipUShift);
            int texelColor = diffusePixels[texelOffset];
            if (texelColor != -1) {
               int dstColor = dstPixels[xLeft];
               int shadedColor = ((texelColor & 0x00FF00FF) * blendAlpha & 0xFF00FF00)
                     + ((texelColor & 0xFF00) * blendAlpha & 0xFF0000)
                     + ((dstColor & 0x00FF00FF) * invBlendAlpha & 0xFF00FF00)
                     + ((dstColor & 0xFF00) * invBlendAlpha & 0xFF0000)
                  >>> 8;
               dstPixels[xLeft] = shadedColor | 0xFF000000;
            }

            curU += stepDu2;
            curV += stepDv;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
         super.uFixed = super.uFixed + super.duDyFixed;
      }
   }
}
