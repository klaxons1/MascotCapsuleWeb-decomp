package com.hicorp.mascotcapsule.web;

public final class LineDrawer_Opaque extends LineDrawer {
   private final Config rasterizer;

   public LineDrawer_Opaque(Config rasterizer) {
      super(rasterizer);
      this.rasterizer = rasterizer;
   }

   public void drawSpan() {
      int[] dstPixels = Config.getPixelBuffer(this.rasterizer);
      int fillColor = Config.getFillColor(this.rasterizer) & 16711935;
      int fillColor2 = Config.getFillColor(this.rasterizer) & 0xFF00;

      for (int stepDz = super.dzDxFixed; super.y < super.yEnd; super.zFixed = super.zFixed + super.dzDyFixed) {
         int xLeft = (super.xLeftFixed >> 16) + super.scanlineOffset;
         int xRight = (super.xRightFixed >> 16) + super.scanlineOffset;

         for (int curZ = super.zFixed; xLeft < xRight; xLeft++) {
            int intensityVal = curZ >>> 16;
            int shadedColor = (fillColor * intensityVal & -16711936) + (fillColor2 * intensityVal & 0xFF0000) >>> 8;
            dstPixels[xLeft] = shadedColor | 0xFF000000;
            curZ += stepDz;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.rasterizer);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
         super.xRightFixed = super.xRightFixed + super.dxRightFixed;
      }
   }
}
