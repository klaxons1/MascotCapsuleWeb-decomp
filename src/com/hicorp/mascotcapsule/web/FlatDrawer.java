package com.hicorp.mascotcapsule.web;

public abstract class FlatDrawer {
   protected int scanlineOffset;
   protected int y;
   protected int yEnd;
   protected int xLeftFixed;
   protected int xRightFixed;
   protected int dxLeftFixed;
   protected int dxRightFixed;
   private final Config rasterizer;

   public FlatDrawer(Config rasterizer) {
      this.rasterizer = rasterizer;
   }

   public abstract void drawSpan();
}
