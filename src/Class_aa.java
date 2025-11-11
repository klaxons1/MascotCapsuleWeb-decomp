import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_339;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_3d2;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_41b;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_47d;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_4ce;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_517;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_596;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_5a9;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_757;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_77c;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_808;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_87a;

public final class Class_aa extends Class_87a implements KeyListener, MouseListener, MouseMotionListener {
   private static final String var_40 = "1.00";
   private static final String var_6d = "logo.gif";
   private Image var_85 = null;
   private boolean var_a1 = false;
   private boolean var_d0 = false;
   private int var_10c = 0;
   private final Class_339 var_15a = new Class_339();
   private final Class_517 var_17d = new Class_517();
   private Class_517 var_1a7 = new Class_517();
   private Class_77c var_1c9 = null;
   private Class_47d var_1e4 = null;
   private boolean var_235 = false;
   private Class_517 var_247 = new Class_517();
   private int[] var_27f = null;
   private int var_2a8 = -16777216;
   private boolean var_2cc = false;
   private float var_2de = 0.005F;
   private float var_2fb = 5.0F;
   private int var_34f;
   private int var_3ab;
   private float var_3e2 = 0.0F;
   private float var_431 = 0.0F;
   private float var_454 = 2.0F;
   private String var_473 = null;
   private final Class_757 var_4a8 = new Class_757();
   private boolean var_4d7 = false;
   private float var_53b;
   private int var_570;
   private int var_5d4;
   private float var_61a = 0.0F;
   private boolean var_642 = true;
   private float var_698 = 0.0F;
   private float var_6a3 = 0.0F;
   private float var_6bf = 0.0F;
   private float var_71c = 0.0F;
   private int var_77f = -1;
   private float var_78f = 0.1F;
   private float var_7d3 = 0.0F;
   private int var_7f0;
   private int var_833;
   private boolean var_865 = false;
   private static final Class_3d2 var_880 = new Class_3d2(0.0F, 0.0F, 1000.0F);
   private static final Class_3d2 var_898 = new Class_3d2(0.0F, 0.0F, 0.0F);
   Toolkit var_8cd = Toolkit.getDefaultToolkit();
   final Class_757 var_92a = new Class_757();
   final Class_757 var_976 = new Class_757();
   final Class_757 var_9b2 = new Class_757();
   private Class_134 var_9de;
   private int var_a18 = 0;

   public Class_aa(int var1, int var2, boolean var3) {
      super(var1, var2);
      this.sub_ea();
      this.sub_147();
      this.sub_c9(null);
      this.sub_1a2(10000, 100.0F, 2000.0F);
      this.var_34f = var1 / 2;
      this.var_3ab = var2 / 2;
      this.var_15a.sub_d7(Class_4ce.sub_18c(var_880, var_898));
      this.resize(var1, var2);
      this.sub_1a();
      if (!var3) {
         this.addKeyListener(this);
         this.addMouseListener(this);
         this.addMouseMotionListener(this);
      }
   }

   public void sub_1a() {
      Class_757 var1 = new Class_757();
      Class_4ce.sub_3f(this.var_3e2, this.var_4a8);
      Class_4ce.sub_92(this.var_431, var1);
      this.var_4a8.sub_37e(var1);
      this.var_4a8.sub_447();
      this.var_61a = 0.0F;
      this.var_570 = this.var_34f;
      this.var_5d4 = this.var_3ab;
      this.var_53b = this.var_454;
      this.var_6bf = 0.0F;
      this.var_71c = 0.0F;
      this.var_698 = 0.0F;
      this.var_6a3 = 0.0F;
   }

   public synchronized boolean sub_5f() {
      this.sub_1f9(this.var_1a7);
      this.sub_283(this.var_4d7);
      this.sub_263(this.var_570, this.var_5d4);
      if (this.var_235) {
         this.sub_144();
      } else {
         this.sub_e6(1.0F);
      }

      Class_4ce.sub_3f(this.var_6bf, this.var_92a);
      Class_4ce.sub_92(this.var_71c, this.var_976);
      this.var_6bf = 0.0F;
      this.var_71c = 0.0F;
      this.var_92a.sub_37e(this.var_976);
      this.var_4a8.sub_339(this.var_92a, this.var_4a8);
      this.var_4a8.sub_447();
      this.var_9b2.var_d7 = this.var_9b2.var_1ce = this.var_9b2.var_2ce = 0.0F;
      Class_4ce.sub_2e(this.var_53b, this.var_9b2);
      Class_4ce.sub_3f(this.var_698, this.var_92a);
      this.var_9b2.sub_37e(this.var_92a);
      Class_4ce.sub_92(this.var_6a3, this.var_92a);
      this.var_9b2.sub_37e(this.var_92a);
      this.var_6bf = 0.0F;
      this.var_71c = 0.0F;
      this.var_9b2.sub_37e(this.var_4a8);
      if (this.var_1e4 != null) {
         this.var_1e4.sub_208(this.var_61a);
      }

      Class_5a9 var1 = this.var_1c9.sub_df();
      var1.sub_d7(this.var_9b2);
      this.sub_242(this.var_1c9, this.var_15a);
      return this.sub_88();
   }

   public synchronized void sub_96() {
      this.sub_a8();
      this.var_9de = new Class_134(this, null);
      this.var_9de.sub_10();
   }

   public synchronized void sub_a8() {
      if (this.var_9de != null) {
         this.var_9de.sub_5a();
         this.var_9de = null;
      }
   }

   public void sub_c9(Class_596 var1) {
      int[] var2 = this.sub_3d();
      if (var2 != null && this.var_27f != null) {
         if (var1 == null) {
            System.arraycopy(this.var_27f, 0, var2, 0, var2.length);
         } else if (var1.sub_da()) {
            int var3 = this.getWidth();
            int var4 = var1.var_d4 - var1.var_35;
            int var5 = var1.var_88 * var3 + var1.var_35;

            for (int var6 = var1.var_88; var6 < var1.var_f0; var6++) {
               System.arraycopy(this.var_27f, var5, var2, var5, var4);
               var5 += var3;
            }
         }
      }
   }

   public synchronized void setBounds(int var1, int var2, int var3, int var4) {
      int var5 = this.getWidth();
      int var6 = this.getHeight();
      super.setBounds(var1, var2, var3, var4);
      if (var3 != var5 || var4 != var6) {
         this.sub_147();
      }

      this.sub_c9(null);
   }

   public void paint(Graphics var1) {
      if (this.var_473 == null && !this.var_d0) {
         if (!this.var_a1) {
            this.sub_4b();
         }

         super.paint(var1);
         this.var_a1 = true;
      } else {
         if (!this.var_d0) {
            var1.setColor(Color.black);
            var1.fillRect(0, 0, this.getWidth(), this.getHeight());
            var1.setColor(Color.white);
            var1.drawString(this.var_473, 16, 32);
         } else {
            short var2 = 287;
            byte var3 = 83;
            byte var4 = 99;
            int var5 = (this.getWidth() - 104) / 2;
            int var6 = (this.getHeight() + 83) / 2 + 4;
            var1.setColor(Color.white);
            var1.fillRect(0, 0, this.getWidth(), this.getHeight());
            int var7 = this.sub_2b9();
            int var8 = 100 - var7;
            var1.setColor(Color.gray);
            var1.fillRect(var5, var6, 104, 16);
            var1.setColor(Color.white);
            var1.fillRect(var5 + 1, var6 + 1, 102, 14);
            int var10 = var5 + 2;
            short var11 = 220;
            short var12 = 255;
            short var13 = 255;
            byte var14 = 96;
            short var15 = 192;
            short var16 = 255;

            for (int var17 = 0; var17 < var7; var17++) {
               int var18 = -124 * var17 / 99 + 220;
               int var19 = -63 * var17 / 99 + 255;
               int var20 = 0 * var17 / 99 + 255;
               Color var9 = new Color(var18, var19, var20);
               var1.setColor(var9);
               var1.drawLine(var10, var6 + 2, var10, var6 + 2 + 16 - 4 - 1);
               var10++;
            }

            var1.setColor(Color.white);
            var1.fillRect(var5 + 2 + 100 - var8, var6 + 2, var8, 12);
            if (this.var_85 != null) {
               var1.drawImage(this.var_85, (this.getWidth() - 287) / 2, (this.getHeight() - 99) / 2 - 20, this);
            }
         }

         this.var_a1 = false;
      }
   }

   public void update(Graphics var1) {
      if (this.var_473 != null) {
         this.paint(var1);
      }
   }

   private void sub_ea() {
      ByteArrayOutputStream var1 = null;

      try {
         InputStream var2 = this.getClass().getResourceAsStream("logo.gif");
         var1 = new ByteArrayOutputStream();
         byte[] var3 = new byte[65536];

         int var4;
         while ((var4 = var2.read(var3)) != -1) {
            var1.write(var3, 0, var4);
         }

         var1.close();
      } catch (IOException var6) {
      }

      this.var_85 = Toolkit.getDefaultToolkit().createImage(var1.toByteArray());
      MediaTracker var7 = new MediaTracker(this);
      var7.addImage(this.var_85, 0);

      try {
         var7.waitForID(0);
      } catch (InterruptedException var5) {
      }
   }

   public synchronized void sub_123(int var1) {
      this.var_2cc = false;
      this.var_2a8 = var1 | 0xFF000000;
      this.sub_147();
   }

   private void sub_147() {
      int var1 = this.getHeight();
      int var2 = this.getWidth();
      int var3 = var2 * var1;
      int[] var4 = new int[var3];
      if (this.var_2cc) {
         int[] var5 = this.var_247.sub_93();
         int var6 = 1 << this.var_247.sub_143();
         int var7 = this.var_247.sub_10a();
         int var8 = this.var_247.sub_11a();

         for (int var9 = 0; var9 < var1; var9 += var8) {
            int var10 = var9 + var8 < var1 ? var8 : var1 - var9;

            for (int var11 = 0; var11 < var10; var11++) {
               int var12 = var11 * var6;
               int var13 = (var9 + var11) * var2;

               for (int var14 = 0; var14 < var2; var14 += var7) {
                  int var15 = var14 + var7 < var2 ? var7 : var2 - var14;
                  System.arraycopy(var5, var12, var4, var13 + var14, var15);
               }
            }
         }
      } else {
         for (int var16 = 0; var16 < var3; var16++) {
            var4[var16] = this.var_2a8;
         }
      }

      this.var_27f = var4;
      this.sub_4b();
   }

   public void sub_168(String var1) {
      this.var_473 = var1;
   }

   public boolean sub_1aa() {
      return this.var_1a7.sub_93() != null && this.var_1c9 != null && this.var_1e4 != null;
   }

   public void sub_1fc(boolean var1) {
      this.var_d0 = var1;
   }

   public void sub_259() {
      this.var_10c = 5;
   }

   private int sub_2b9() {
      int var1 = 20 * this.var_10c;
      if (var1 < 0) {
         var1 = 0;
      }

      if (var1 > 100) {
         var1 = 100;
      }

      return var1;
   }

   public void sub_312(InputStream var1) {
      ZipInputStream var2 = new ZipInputStream(var1);
      Class_41b var3 = this.sub_43a();
      Class_517 var4 = null;
      Class_517 var5 = null;
      Class_517 var6 = null;
      Class_77c var7 = null;
      Class_47d var8 = null;

      ZipEntry var9;
      try {
         while ((var9 = var2.getNextEntry()) != null) {
            String var10 = var9.getName();
            int var11 = var10.length();
            if (var10.regionMatches(true, var11 - 10, "sphere", 0, 6)) {
               var4 = new Class_517();
               if (!var3.sub_10(new BufferedInputStream(var2), var4)) {
                  var4 = null;
               }

               if (this.var_17d == null && var4 != null) {
                  this.var_10c++;
                  this.repaint();
               }
            } else if (var10.regionMatches(true, var11 - 8, "wall", 0, 4)) {
               var5 = new Class_517();
               if (!var3.sub_10(new BufferedInputStream(var2), var5)) {
                  var5 = null;
               }

               if (this.var_247 == null && var5 != null) {
                  this.var_10c++;
                  this.repaint();
               }
            } else if (var10.regionMatches(true, var11 - 5, ".jbac", 0, 5)) {
               var7 = new Class_77c();
               if (!var7.sub_35(new BufferedInputStream(var2))) {
                  var7 = null;
               } else if (this.var_865) {
                  var7.sub_31b();
               }

               if (this.var_1c9 == null && var7 != null) {
                  this.var_10c++;
                  this.repaint();
               }
            } else if (var10.regionMatches(true, var11 - 5, ".jtra", 0, 5)) {
               var8 = new Class_47d();
               if (!var8.sub_f8(new BufferedInputStream(var2))) {
                  var8 = null;
               }

               if (this.var_1e4 == null && var8 != null) {
                  this.var_10c++;
                  this.repaint();
               }
            } else if (var10.regionMatches(true, var11 - 4, ".bmp", 0, 4)
               || var10.regionMatches(true, var11 - 4, ".jpg", 0, 4)
               || var10.regionMatches(true, var11 - 4, ".png", 0, 4)
               || var10.regionMatches(true, var11 - 4, ".gif", 0, 4)) {
               var6 = new Class_517();
               if (!var3.sub_10(new BufferedInputStream(var2), var6)) {
                  var6 = null;
               }

               if (this.var_1a7 == null && var6 != null) {
                  this.var_10c++;
                  this.repaint();
               }
            }
         }
      } catch (ZipException var14) {
      }

      synchronized (this) {
         if (var4 != null) {
            if (this.var_642) {
               var4.sub_281();
            }

            this.sub_22b(var4);
         }

         if (var5 != null) {
            this.var_2cc = true;
            this.var_247 = var5;
            this.sub_147();
         }

         this.sub_4b();
         if (var6 != null) {
            if (this.var_642) {
               var6.sub_281();
            }

            this.var_1a7 = var6;
         }

         if (var7 != null) {
            this.var_1c9 = var7;
         }

         if (var8 != null) {
            this.var_1e4 = var8;
         }

         if (var7 != null || var8 != null && this.var_1c9 != null) {
            this.var_1e4.sub_122(this.var_1c9);
         }
      }

      var2.close();
   }

   public void sub_34e(InputStream var1, InputStream var2, InputStream var3) {
      try {
         Class_517 var4 = null;
         Class_77c var5 = null;
         Class_47d var6 = null;
         if (var3 != null) {
            Class_41b var7 = this.sub_43a();
            var4 = new Class_517();
            if (!var7.sub_10(new BufferedInputStream(var3), var4)) {
               var4 = null;
            }

            if (this.var_1a7 == null && var4 != null) {
               this.var_10c++;
               this.repaint();
            }
         }

         if (var1 != null) {
            var5 = new Class_77c();
            if (!var5.sub_35(new BufferedInputStream(var1))) {
               var5 = null;
            } else if (this.var_865) {
               var5.sub_31b();
            }

            if (this.var_1c9 == null && var5 != null) {
               this.var_10c++;
               this.repaint();
            }
         }

         if (var2 != null) {
            var6 = new Class_47d();
            if (!var6.sub_f8(new BufferedInputStream(var2))) {
               var6 = null;
            }

            if (this.var_1e4 == null && var6 != null) {
               this.var_10c++;
               this.repaint();
            }
         }

         synchronized (this) {
            if (var4 != null) {
               if (this.var_642) {
                  var4.sub_281();
               }

               this.var_1a7 = var4;
            }

            if (var5 != null) {
               this.var_1c9 = var5;
            }

            if (var6 != null) {
               this.var_1e4 = var6;
            }

            if (var5 != null || var6 != null) {
               this.var_1e4.sub_122(this.var_1c9);
            }
         }
      } catch (IOException var10) {
      }
   }

   public synchronized void sub_399(InputStream var1) {
      Class_41b var2 = this.sub_43a();
      if (var2.sub_10(var1, this.var_17d)) {
         if (this.var_642) {
            this.var_17d.sub_281();
         }

         this.sub_22b(this.var_17d);
         this.var_10c++;
         this.repaint();
      }
   }

   public synchronized void sub_3da(InputStream var1) {
      Class_41b var2 = this.sub_43a();
      if (var2.sub_10(var1, this.var_247)) {
         this.var_2cc = true;
         this.sub_147();
         this.var_10c++;
         this.repaint();
      }
   }

   public void keyPressed(KeyEvent var1) {
      switch (var1.getKeyCode()) {
         case 27:
            this.sub_1a();
            break;
         case 37:
            this.sub_6ba(-16, 0);
            break;
         case 38:
            this.sub_6ba(0, -16);
            break;
         case 39:
            this.sub_6ba(16, 0);
            break;
         case 40:
            this.sub_6ba(0, 16);
            break;
         case 50:
            this.var_6bf -= 0.1F;
            break;
         case 52:
            this.var_71c -= 0.1F;
            break;
         case 54:
            this.var_71c += 0.1F;
            break;
         case 56:
            this.var_6bf += 0.1F;
            break;
         case 88:
            this.sub_68c(-0.1F);
            break;
         case 90:
            this.sub_68c(0.1F);
      }
   }

   public void keyReleased(KeyEvent var1) {
   }

   public void keyTyped(KeyEvent var1) {
   }

   public void mousePressed(MouseEvent var1) {
      if (var1.isAltDown()) {
         this.var_a18 = 3;
      } else if (var1.isMetaDown()) {
         this.var_a18 = 2;
      } else {
         this.var_a18 = 1;
      }

      this.setCursor(new Cursor(12));
   }

   public void mouseReleased(MouseEvent var1) {
      this.var_a18 = 0;
      this.setCursor(Cursor.getDefaultCursor());
      if (this.var_698 != 0.0F || this.var_6a3 != 0.0F) {
         this.var_6bf = this.var_698;
         this.var_71c = this.var_6a3;
         this.var_698 = 0.0F;
         this.var_6a3 = 0.0F;
      }
   }

   public void mouseClicked(MouseEvent var1) {
   }

   public void mouseEntered(MouseEvent var1) {
   }

   public void mouseExited(MouseEvent var1) {
      this.mouseReleased(var1);
   }

   public void mouseDragged(MouseEvent var1) {
      int var2 = var1.getX();
      int var3 = var1.getY();
      switch (this.var_a18) {
         case 1:
            this.var_6a3 = this.var_6a3 + (var2 - this.var_7f0) * 0.01F;
            this.var_698 = this.var_698 + (var3 - this.var_833) * 0.01F;
            break;
         case 2:
            this.var_570 = this.var_570 + (var2 - this.var_7f0);
            this.var_5d4 = this.var_5d4 + (var3 - this.var_833);
            break;
         case 3:
            this.var_53b = this.var_53b + (var2 - this.var_7f0) * 0.005F;
            this.var_53b = this.var_53b + (var3 - this.var_833) * 0.005F;
            if (this.var_53b < this.var_2de) {
               this.var_53b = this.var_2de;
            } else if (this.var_53b > this.var_2fb) {
               this.var_53b = this.var_2fb;
            }
      }

      this.var_7f0 = var2;
      this.var_833 = var3;
   }

   public void mouseMoved(MouseEvent var1) {
      this.var_7f0 = var1.getX();
      this.var_833 = var1.getY();
   }

   public Class_41b sub_43a() {
      return new Class_808();
   }

   public void sub_498(int var1, int var2) {
      this.var_34f += var1;
      this.var_3ab += var2;
   }

   public void sub_4ec(int var1, int var2) {
      this.var_34f = var1;
      this.var_3ab = var2;
   }

   public void sub_501(float var1) {
      if (var1 > this.var_2fb) {
         var1 = this.var_2fb;
      } else if (var1 < this.var_2de) {
         var1 = this.var_2de;
      }

      this.var_454 = var1;
   }

   public void sub_55f(float var1) {
      this.var_3e2 = var1;
   }

   public void sub_5b5(float var1) {
      this.var_431 = var1;
   }

   public void sub_5ca(Class_757 var1) {
      this.var_15a.sub_d7(var1);
   }

   public void sub_5fa(boolean var1) {
      this.var_865 = var1;
   }

   public void sub_64f(float var1) {
      this.var_7d3 = var1;
   }

   public void sub_68c(float var1) {
      this.var_53b += var1;
      if (this.var_53b > this.var_2fb) {
         this.var_53b = this.var_2fb;
      } else if (this.var_53b < this.var_2de) {
         this.var_53b = this.var_2de;
      }
   }

   public void sub_6ba(int var1, int var2) {
      this.var_570 += var1;
      this.var_5d4 += var2;
   }

   public void sub_6c8(float var1, float var2) {
      this.var_6bf += var1;
      this.var_71c += var2;
   }

   public void sub_6f5(float var1) {
      this.var_78f = var1;
   }

   public void sub_74b(float var1) {
      float var2 = this.var_78f + var1;
      if (var2 < 0.0F) {
         var2 = 0.0F;
      }

      this.var_78f = var2;
   }

   public void sub_761(int var1) {
      this.var_4d7 = var1 != 0;
   }

   public void sub_7a4(float var1) {
      if (var1 == 0.0F) {
         this.var_77f = -1;
      } else {
         this.var_77f = (int)(1000.0F / var1);
      }
   }

   public void sub_7d9(boolean var1) {
      this.var_235 = var1;
   }

   public void sub_806(boolean var1) {
      this.var_642 = var1;
   }

   static float sub_81d(Class_aa var0, float var1) {
      return var0.var_61a += var1;
   }

   static float sub_880(Class_aa var0) {
      return var0.var_78f;
   }

   static Class_47d sub_8e3(Class_aa var0) {
      return var0.var_1e4;
   }

   static float sub_917(Class_aa var0) {
      return var0.var_61a;
   }

   static float sub_945(Class_aa var0, float var1) {
      return var0.var_61a -= var1;
   }

   static float sub_985(Class_aa var0, float var1) {
      return var0.var_71c += var1;
   }

   static float sub_9de(Class_aa var0) {
      return var0.var_7d3;
   }

   static int sub_a14(Class_aa var0) {
      return var0.var_77f;
   }
}
