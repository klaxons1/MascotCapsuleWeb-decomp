package com.hicorp.mascotcapsule.web;

public abstract class LineDrawer {
   protected int scanlineOffset;
   protected int y;
   protected int yEnd;
   protected int xLeftFixed;
   protected int xRightFixed;
   protected int dxLeftFixed;
   protected int dxRightFixed;
   protected int zFixed;
   protected int dzDyFixed;
   protected int dzDxFixed;
   private final Config rasterizer;

   public LineDrawer(Config rasterizer) {
      this.rasterizer = rasterizer;
   }

   public abstract void drawSpan();
}
