package com.hicorp.mascotcapsule.web;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public class MainCanvas extends Canvas {
   private final Toolkit toolkit = Toolkit.getDefaultToolkit();
   private final FrameBuffer frameBuffer = new FrameBuffer(this);
   private final Config rasterizer = new Config();
   private final RenderContext renderContext = new RenderContext(this.rasterizer);
   private final BoundingBox damageBounds = new BoundingBox();
   private final BoundingBox accumulatedBounds = new BoundingBox();
   private final BoundingBox screenBounds = new BoundingBox();
   private boolean unusedFlag = false;
   private StringBuffer statsText = new StringBuffer("");
   private boolean showStats = false;
   private int frameCount = 0;
   private long startTimeMs = System.currentTimeMillis();
   private long lastFpsTimeMs = this.startTimeMs;
   private int lastFpsFrameCount = 0;
   private int polyCount = 0;
   private int vertexCount = 0;

   public final int getWidth() {
      return this.frameBuffer.getWidth();
   }

   public final int getHeight() {
      return this.frameBuffer.getHeight();
   }

   public final int[] getPixels() {
      return this.frameBuffer.getPixels();
   }

   public MainCanvas(int width, int height) {
      this.setSize(width, height);
   }

   public synchronized void setSize(int width, int height) {
      this.frameBuffer.setSize(width, height);
      this.screenBounds.setBounds(0, 0, width, height);
      this.rasterizer.setClipRect(this.screenBounds);
      this.rasterizer.setRenderTarget(this.frameBuffer.getStride(), this.frameBuffer.getPixels());
      this.clearBackground(null);
   }

   public synchronized void markDirtyAll() {
      this.accumulatedBounds.resetInfinite();
   }

   public synchronized boolean renderAndFlush() {
      FrameBuffer fb = this.frameBuffer;
      synchronized (fb) {
         if (this.accumulatedBounds.isValid()) {
            this.accumulatedBounds.intersect(this.screenBounds);
            this.clearBackground(this.accumulatedBounds);
         }

         this.damageBounds.setBounds(this.accumulatedBounds);
         this.renderContext.flushToDirtyRect(this.accumulatedBounds);
         this.renderContext.resetRenderBounds();
         this.damageBounds.union(this.accumulatedBounds);
         this.damageBounds.intersect(this.screenBounds);
         this.renderContext.clearPacketTable();
      }

      Graphics g = null;

      try {
         g = this.getGraphics();
         if (g == null) {
            return true;
         }
      } catch (NullPointerException e) {
         return false;
      }

      if (this.showStats && this.damageBounds.minY < 15) {
         this.damageBounds.minY = 15;
      }

      if (this.damageBounds.isValid()) {
         this.frameBuffer.flush(this.damageBounds);
         this.frameBuffer.paint(g, this.damageBounds);
      }

      if (this.showStats) {
         g.setClip(null);
         g.setColor(Color.black);
         g.fillRect(0, 0, this.getWidth(), 15);
         g.setColor(Color.white);
         g.drawString(this.statsText.toString(), 8, 12);
      }

      g.dispose();
      g = null;
      if (this.showStats) {
         this.frameCount++;
         long now = System.currentTimeMillis();
         int frames = this.frameCount - this.lastFpsFrameCount;
         if (frames > 50) {
            this.statsText = new StringBuffer(256);
            this.statsText.append((int)(frames * 1000.0F / (float)(now - this.lastFpsTimeMs)));
            this.statsText.append(" fps, ");
            this.statsText.append(this.polyCount / frames);
            this.statsText.append(" poly, ");
            this.statsText.append(this.vertexCount / frames);
            this.statsText.append(" vert");
            this.lastFpsTimeMs = now;
            this.lastFpsFrameCount = this.frameCount;
            this.polyCount = 0;
            this.vertexCount = 0;
         }
      }

      return true;
   }

   public void clearBackground(BoundingBox bounds) {
   }

   public void paint(Graphics g) {
      this.frameBuffer.paint(g);
      g.dispose();
   }

   public void update(Graphics g) {
   }

   public synchronized void setBounds(int x, int y, int width, int height) {
      if (this.getWidth() != width || this.getHeight() != height) {
         this.setSize(width, height);
      }

      super.setBounds(x, y, width, height);
      this.toolkit.sync();
      this.markDirtyAll();
   }

   public synchronized void enablePerspective(float fov) {
      this.renderContext.enablePerspective(fov);
   }

   public synchronized void disablePerspective() {
      this.renderContext.disablePerspective();
   }

   public synchronized void initPacketTable(int capacity, float nearZ, float farZ) {
      this.renderContext.initPacketTable(capacity, nearZ, farZ);
   }

   public synchronized void setDiffuseTexture(Texture texture) {
      this.renderContext.setDiffuseTexture(texture);
   }

   public synchronized void setSphereMapTexture(Texture texture) {
      this.renderContext.setSphereMapTexture(texture);
   }

   public synchronized void renderModel(BacModel model, CameraNode camera) {
      this.renderContext.setProjection(model, camera);
      this.polyCount += model.getPolygonCount();
      this.vertexCount += model.getVertexCount();
   }

   public synchronized void setViewportOffset(int x, int y) {
      this.renderContext.setViewportOffset(x, y);
   }

   public synchronized void setLightingEnabled(boolean enabled) {
      this.renderContext.setLightingEnabled(enabled);
   }

   public synchronized void setDirectionalLight(Vector3f dir, float intensity) {
      this.renderContext.setDirectionalLight(dir, intensity);
   }

   public synchronized void setAmbientIntensity(float intensity) {
      this.renderContext.setAmbientIntensity(intensity);
   }

   public synchronized void setShowStats(boolean show) {
      this.showStats = show;
      if (!show) {
         this.markDirtyAll();
      } else {
         this.lastFpsTimeMs = System.currentTimeMillis();
         this.lastFpsFrameCount = this.frameCount;
         this.polyCount = 0;
         this.vertexCount = 0;
      }
   }
}
