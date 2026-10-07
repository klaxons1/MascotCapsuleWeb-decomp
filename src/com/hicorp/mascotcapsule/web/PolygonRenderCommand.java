package com.hicorp.mascotcapsule.web;

final class PolygonRenderCommand extends RenderCommand {
   int vertexCount;
   int renderFlags;
   int clipped;
   int sortDepth;
   Texture diffuseTexture;
   Texture sphereMapTexture;
   final RasterVertex v0;
   final RasterVertex v1;
   final RasterVertex v2;
   final RasterVertex v3;
   private final RenderContext renderContext;

   PolygonRenderCommand(RenderContext renderContext) {
      super(renderContext, null);
      this.renderContext = renderContext;
      this.v0 = new RasterVertex();
      this.v1 = new RasterVertex();
      this.v2 = new RasterVertex();
      this.v3 = new RasterVertex();
      super.commandType = 1;
   }

   public void updateBounds(BoundingBox bounds) {
      int minXVal;
      int maxXVal;
      if (this.v1.x > this.v2.x) {
         if (this.v0.x > this.v1.x) {
            maxXVal = this.v0.x;
            minXVal = this.v2.x;
         } else {
            maxXVal = this.v1.x;
            minXVal = this.v0.x > this.v2.x ? this.v2.x : this.v0.x;
         }
      } else if (this.v1.x > this.v0.x) {
         maxXVal = this.v2.x;
         minXVal = this.v0.x;
      } else {
         maxXVal = this.v0.x > this.v2.x ? this.v2.x : this.v0.x;
         minXVal = this.v1.x;
      }

      int minYVal;
      int maxYVal;
      if (this.v1.y > this.v2.y) {
         if (this.v0.y > this.v1.y) {
            maxYVal = this.v0.y;
            minYVal = this.v2.y;
         } else {
            maxYVal = this.v1.y;
            minYVal = this.v0.y > this.v2.y ? this.v2.y : this.v0.y;
         }
      } else if (this.v1.y > this.v0.y) {
         maxYVal = this.v2.y;
         minYVal = this.v0.y;
      } else {
         maxYVal = this.v0.y > this.v2.y ? this.v2.y : this.v0.y;
         minYVal = this.v1.y;
      }

      if (this.vertexCount == 4) {
         if (minXVal > this.v3.x) {
            minXVal = this.v3.x;
         } else if (maxXVal < this.v3.x) {
            maxXVal = this.v3.x;
         }

         if (minYVal > this.v3.y) {
            minYVal = this.v3.y;
         } else if (maxYVal < this.v3.y) {
            maxYVal = this.v3.y;
         }
      }

      if (bounds.minX > minXVal) {
         bounds.minX = minXVal;
      }

      if (bounds.maxX < maxXVal) {
         bounds.maxX = maxXVal;
      }

      if (bounds.minY > minYVal) {
         bounds.minY = minYVal;
      }

      if (bounds.maxY < maxYVal) {
         bounds.maxY = maxYVal;
      }
   }
}
