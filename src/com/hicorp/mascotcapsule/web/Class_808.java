package com.hicorp.mascotcapsule.web;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class Class_808 extends Component implements ImageObserver, Material {
   public boolean sub_10(InputStream var1, Class_517 var2) {
      if (var1 == null) {
         return false;
      } else {
         if (var1.markSupported()) {
            var1.mark(2);
            Class_613 var3 = new Class_613(var1);
            int var4 = var3.sub_16f();
            int var5 = var3.sub_16f();
            Object var15 = null;
            var1.reset();
            if (var4 == 66 && var5 == 77) {
               Class_89e var23 = new Class_89e();
               return var23.sub_10(var1, var2);
            }
         }

         ByteArrayOutputStream var16 = new ByteArrayOutputStream();

         try {
            byte[] var17 = new byte[65536];

            int var20;
            while ((var20 = var1.read(var17)) != -1) {
               var16.write(var17, 0, var20);
            }
         } catch (IOException var14) {
            return false;
         }

         var16.close();
         Image var18 = Toolkit.getDefaultToolkit().createImage(var16.toByteArray());
         MediaTracker var21 = new MediaTracker(this);
         var21.addImage(var18, 0);

         try {
            var21.waitForID(0);
         } catch (InterruptedException var13) {
            return false;
         }

         if (var21.isErrorAny()) {
            return false;
         } else {
            int var6 = var18.getWidth(this);
            int var7 = var18.getHeight(this);
            var2.sub_38(var6, var7);
            int[] var8 = var2.sub_93();
            int var9 = 1 << var2.sub_143();
            var21.removeImage(var18);
            PixelGrabber var10 = new PixelGrabber(var18, 0, 0, var6, var7, var8, 0, var9);

            try {
               var10.grabPixels();
            } catch (InterruptedException var12) {
               return false;
            }

            Object var19 = null;
            Object var22 = null;
            return true;
         }
      }
   }

   public synchronized boolean imageUpdate(Image var1, int var2, int var3, int var4, int var5, int var6) {
      return false;
   }
}
