package com.hicorp.mascotcapsule.web;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Class_87a extends Canvas {
   private final Toolkit var_e = Toolkit.getDefaultToolkit();
   private final FrameBuffer var_1c = new FrameBuffer(this);
   private final Class_6ed var_4a = new Class_6ed();
   private final Class_539 var_94 = new Class_539(this.var_4a);
   private final Class_596 var_ee = new Class_596();
   private final Class_596 var_1bb = new Class_596();
   private final Class_596 var_1cd = new Class_596();
   private boolean var_1fe = false;
   private StringBuffer var_21f = new StringBuffer("");
   private boolean var_252 = false;
   private int var_25e = 0;
   private long var_2aa = System.currentTimeMillis();
   private long var_2fa = this.var_2aa;
   private int var_337 = 0;
   private int var_375 = 0;
   private int var_3ae = 0;

   public final int getWidth() {
      return this.var_1c.sub_6f();
   }

   public final int getHeight() {
      return this.var_1c.sub_9b();
   }

   public final int[] sub_3d() {
      return this.var_1c.sub_bc();
   }

   public Class_87a(int var1, int var2) {
      this.setSize(var1, var2);
   }

   public synchronized void setSize(int var1, int var2) {
      this.var_1c.sub_aa(var1, var2);
      this.var_1cd.sub_47(0, 0, var1, var2);
      this.var_4a.sub_58(this.var_1cd);
      this.var_4a.sub_38(this.var_1c.sub_89(), this.var_1c.sub_bc());
      this.sub_c9(null);
   }

   public synchronized void sub_4b() {
      this.var_1bb.sub_a9();
   }

   public synchronized boolean sub_88() {
      FrameBuffer var1 = this.var_1c;
      synchronized (var1) {
         if (this.var_1bb.sub_da()) {
            this.var_1bb.sub_145(this.var_1cd);
            this.sub_c9(this.var_1bb);
         }

         this.var_ee.sub_5d(this.var_1bb);
         this.var_94.sub_23d(this.var_1bb);
         this.var_94.sub_21e();
         this.var_ee.sub_1bb(this.var_1bb);
         this.var_ee.sub_145(this.var_1cd);
         this.var_94.sub_2f2();
      }

      Graphics var2 = null;

      try {
         var2 = this.getGraphics();
         if (var2 == null) {
            return true;
         }
      } catch (NullPointerException var6) {
         return false;
      }

      if (this.var_252 && this.var_ee.var_88 < 15) {
         this.var_ee.var_88 = 15;
      }

      if (this.var_ee.sub_da()) {
         this.var_1c.sub_d6(this.var_ee);
         this.var_1c.sub_11b(var2, this.var_ee);
      }

      if (this.var_252) {
         var2.setClip(null);
         var2.setColor(Color.black);
         var2.fillRect(0, 0, this.getWidth(), 15);
         var2.setColor(Color.white);
         var2.drawString(this.var_21f.toString(), 8, 12);
      }

      var2.dispose();
      var2 = null;
      if (this.var_252) {
         this.var_25e++;
         long var3 = System.currentTimeMillis();
         int var5 = this.var_25e - this.var_337;
         if (var5 > 50) {
            this.var_21f = new StringBuffer(256);
            this.var_21f.append((int)(var5 * 1000.0F / (float)(var3 - this.var_2fa)));
            this.var_21f.append(" fps, ");
            this.var_21f.append(this.var_375 / var5);
            this.var_21f.append(" poly, ");
            this.var_21f.append(this.var_3ae / var5);
            this.var_21f.append(" vert");
            this.var_2fa = var3;
            this.var_337 = this.var_25e;
            this.var_375 = 0;
            this.var_3ae = 0;
         }
      }

      return true;
   }

   public void sub_c9(Class_596 var1) {
   }

   public void paint(Graphics var1) {
      this.var_1c.sub_166(var1);
      var1.dispose();
   }

   public void update(Graphics var1) {
   }

   public synchronized void setBounds(int var1, int var2, int var3, int var4) {
      if (this.getWidth() != var3 || this.getHeight() != var4) {
         this.setSize(var3, var4);
      }

      super.setBounds(var1, var2, var3, var4);
      this.var_e.sync();
      this.sub_4b();
   }

   public synchronized void sub_e6(float var1) {
      this.var_94.sub_73(var1);
   }

   public synchronized void sub_144() {
      this.var_94.sub_a2();
   }

   public synchronized void sub_1a2(int var1, float var2, float var3) {
      this.var_94.sub_106(var1, var2, var3);
   }

   public synchronized void sub_1f9(Class_517 var1) {
      this.var_94.sub_269(var1);
   }

   public synchronized void sub_22b(Class_517 var1) {
      this.var_94.sub_2b7(var1);
   }

   public synchronized void sub_242(Class_77c var1, Class_339 var2) {
      this.var_94.sub_2dd(var1, var2);
      this.var_375 = this.var_375 + var1.var_255;
      this.var_3ae = this.var_3ae + var1.var_1d4;
   }

   public synchronized void sub_263(int var1, int var2) {
      this.var_94.sub_19c(var1, var2);
   }

   public synchronized void sub_283(boolean var1) {
      this.var_94.sub_39e(var1);
   }

   public synchronized void sub_2e4(Vector3f var1, float var2) {
      this.var_94.sub_3cc(var1, var2);
   }

   public synchronized void sub_331(float var1) {
      this.var_94.sub_3ba(var1);
   }

   public synchronized void sub_33e(boolean var1) {
      this.var_252 = var1;
      if (!var1) {
         this.sub_4b();
      } else {
         this.var_2fa = System.currentTimeMillis();
         this.var_337 = this.var_25e;
         this.var_375 = 0;
         this.var_3ae = 0;
      }
   }
}
