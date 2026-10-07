package com.hicorp.mascotcapsule.web;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

final class FrameBuffer implements ImageProducer {
   private final Canvas canvas;
   private Image image = null;
   private int[] pixelBuffer = null;
   private int width = 0;
   private int stride = 0;
   private int height = 0;
   private final Vector consumers = new Vector();
   private final ColorModel colorModel = ColorModel.getRGBdefault();
   private final Hashtable properties = new Hashtable();

   public int getWidth() {
      return this.width;
   }

   public int getStride() {
      return this.stride;
   }

   public int getHeight() {
      return this.height;
   }

   public FrameBuffer(Canvas canvas) {
      this.canvas = canvas;
   }

   public synchronized void setSize(int width, int height) {
      this.width = width;
      this.stride = width;
      this.height = height;
      this.pixelBuffer = new int[this.stride * height];
      this.image = this.canvas.createImage(this);
   }

   public int[] getPixels() {
      return this.pixelBuffer;
   }

   public synchronized void flush(BoundingBox dirtyRect) {
      if (dirtyRect.isValid()) {
         int minX = dirtyRect.minX;
         int minY = dirtyRect.minY;
         int w = dirtyRect.maxX - minX;
         int h = dirtyRect.maxY - minY;
         Enumeration e = this.consumers.elements();

         while (e.hasMoreElements()) {
            ImageConsumer consumer = (ImageConsumer)e.nextElement();
            if (this.isConsumer(consumer)) {
               consumer.setPixels(minX, minY, w, h, this.colorModel, this.pixelBuffer, this.stride * minY + minX, this.stride);
               consumer.imageComplete(2);
            }
         }
      }
   }

   public synchronized void paint(Graphics g, BoundingBox clip) {
      if (clip.isValid()) {
         g.clipRect(clip.minX, clip.minY, clip.maxX - clip.minX, clip.maxY - clip.minY);
      }

      g.drawImage(this.image, 0, 0, this.canvas);
   }

   public synchronized void paint(Graphics g) {
      g.drawImage(this.image, 0, 0, this.canvas);
   }

   public synchronized void requestTopDownLeftRightResend(ImageConsumer consumer) {
   }

   public synchronized boolean isConsumer(ImageConsumer consumer) {
      return this.consumers.contains(consumer);
   }

   public synchronized void removeConsumer(ImageConsumer consumer) {
      this.consumers.removeElement(consumer);
   }

   public synchronized void startProduction(ImageConsumer consumer) {
      this.addConsumer(consumer);
   }

   public synchronized void addConsumer(ImageConsumer consumer) {
      if (!this.consumers.contains(consumer)) {
         this.consumers.addElement(consumer);

         try {
            this.initConsumer(consumer);
            if (this.isConsumer(consumer)) {
               consumer.setPixels(0, 0, this.width, this.height, this.colorModel, this.pixelBuffer, 0, this.stride);
            }

            if (this.isConsumer(consumer)) {
               consumer.imageComplete(2);
            }
         } catch (Exception e) {
            if (this.isConsumer(consumer)) {
               consumer.imageComplete(1);
            }
         }
      }
   }

   private synchronized void initConsumer(ImageConsumer consumer) {
      if (this.isConsumer(consumer)) {
         consumer.setDimensions(this.width, this.height);
      }

      if (this.isConsumer(consumer)) {
         consumer.setProperties(this.properties);
      }

      if (this.isConsumer(consumer)) {
         consumer.setColorModel(this.colorModel);
      }

      if (this.isConsumer(consumer)) {
         consumer.setHints(1);
      }
   }
}
