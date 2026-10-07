package com.hicorp.mascotcapsule.web;

public abstract class SphereMapDrawer {
   protected int scanlineOffset;
   protected int y;
   protected int yEnd;
   protected int xLeftFixed;
   protected int xRightFixed;
   protected int dxLeftFixed;
   protected int dxRightFixed;
   protected int uFixed;
   protected int vFixed;
   protected int duDyFixed;
   protected int dvDyFixed;
   protected int duDxFixed;
   protected int dvDxFixed;
   protected int lightFixed;
   protected int dLightDyFixed;
   protected int dLightDxFixed;
   protected int sphereUFixed;
   protected int sphereVFixed;
   protected int dSphereUDyFixed;
   protected int dSphereVDyFixed;
   protected int dSphereUDxFixed;
   protected int dSphereVDxFixed;
   private final Config rasterizer;

   public SphereMapDrawer(Config rasterizer) {
      this.rasterizer = rasterizer;
   }

   public abstract void drawSpan();
}
