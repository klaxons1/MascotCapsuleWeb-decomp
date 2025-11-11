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

final class Class_384 implements ImageProducer {
   private final Canvas var_39;
   private Image var_6a = null;
   private int[] var_ba = null;
   private int var_c9 = 0;
   private int var_ed = 0;
   private int var_130 = 0;
   private final Vector var_16c = new Vector();
   private final ColorModel var_1cb = ColorModel.getRGBdefault();
   private final Hashtable var_1e2 = new Hashtable();

   public int sub_6f() {
      return this.var_c9;
   }

   public int sub_89() {
      return this.var_ed;
   }

   public int sub_9b() {
      return this.var_130;
   }

   public Class_384(Canvas var1) {
      this.var_39 = var1;
   }

   public synchronized void sub_aa(int var1, int var2) {
      this.var_c9 = var1;
      this.var_ed = var1;
      this.var_130 = var2;
      this.var_ba = new int[this.var_ed * var2];
      this.var_6a = this.var_39.createImage(this);
   }

   public int[] sub_bc() {
      return this.var_ba;
   }

   public synchronized void sub_d6(Class_596 var1) {
      if (var1.sub_da()) {
         int var2 = var1.var_35;
         int var3 = var1.var_88;
         int var4 = var1.var_d4 - var2;
         int var5 = var1.var_f0 - var3;
         Enumeration var6 = this.var_16c.elements();

         while (var6.hasMoreElements()) {
            ImageConsumer var7 = (ImageConsumer)var6.nextElement();
            if (this.isConsumer(var7)) {
               var7.setPixels(var2, var3, var4, var5, this.var_1cb, this.var_ba, this.var_ed * var3 + var2, this.var_ed);
               var7.imageComplete(2);
            }
         }
      }
   }

   public synchronized void sub_11b(Graphics var1, Class_596 var2) {
      if (var2.sub_da()) {
         var1.clipRect(var2.var_35, var2.var_88, var2.var_d4 - var2.var_35, var2.var_f0 - var2.var_88);
      }

      var1.drawImage(this.var_6a, 0, 0, this.var_39);
   }

   public synchronized void sub_166(Graphics var1) {
      var1.drawImage(this.var_6a, 0, 0, this.var_39);
   }

   public synchronized void requestTopDownLeftRightResend(ImageConsumer var1) {
   }

   public synchronized boolean isConsumer(ImageConsumer var1) {
      return this.var_16c.contains(var1);
   }

   public synchronized void removeConsumer(ImageConsumer var1) {
      this.var_16c.removeElement(var1);
   }

   public synchronized void startProduction(ImageConsumer var1) {
      this.addConsumer(var1);
   }

   public synchronized void addConsumer(ImageConsumer var1) {
      if (!this.var_16c.contains(var1)) {
         this.var_16c.addElement(var1);

         try {
            this.sub_1c1(var1);
            if (this.isConsumer(var1)) {
               var1.setPixels(0, 0, this.var_c9, this.var_130, this.var_1cb, this.var_ba, 0, this.var_ed);
            }

            if (this.isConsumer(var1)) {
               var1.imageComplete(2);
            }
         } catch (Exception var3) {
            if (this.isConsumer(var1)) {
               var1.imageComplete(1);
            }
         }
      }
   }

   private synchronized void sub_1c1(ImageConsumer var1) {
      if (this.isConsumer(var1)) {
         var1.setDimensions(this.var_c9, this.var_130);
      }

      if (this.isConsumer(var1)) {
         var1.setProperties(this.var_1e2);
      }

      if (this.isConsumer(var1)) {
         var1.setColorModel(this.var_1cb);
      }

      if (this.isConsumer(var1)) {
         var1.setHints(1);
      }
   }
}
