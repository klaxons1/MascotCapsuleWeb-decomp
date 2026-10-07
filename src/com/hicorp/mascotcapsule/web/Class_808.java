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
   public boolean readImage(InputStream in, Class_517 texture) {
      if (in == null) {
         return false;
      } else {
         if (in.markSupported()) {
            in.mark(2);
            Class_613 reader = new Class_613(in);
            int magic0 = reader.readUnsignedByte();
            int magic1 = reader.readUnsignedByte();
            in.reset();
            if (magic0 == 'B' && magic1 == 'M') {
               Class_89e bmpDecoder = new Class_89e();
               return bmpDecoder.readImage(in, texture);
            }
         }

         ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

         try {
            byte[] buf = new byte[65536];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
               byteOut.write(buf, 0, bytesRead);
            }
         } catch (IOException e) {
            return false;
         }

         byteOut.close();
         Image img = Toolkit.getDefaultToolkit().createImage(byteOut.toByteArray());
         MediaTracker tracker = new MediaTracker(this);
         tracker.addImage(img, 0);

         try {
            tracker.waitForID(0);
         } catch (InterruptedException e) {
            return false;
         }

         if (tracker.isErrorAny()) {
            return false;
         } else {
            int imgWidth = img.getWidth(this);
            int imgHeight = img.getHeight(this);
            texture.allocate(imgWidth, imgHeight);
            int[] texturePixels = texture.getPixels();
            int stride = 1 << texture.getWidthLog2();
            tracker.removeImage(img);
            PixelGrabber grabber = new PixelGrabber(img, 0, 0, imgWidth, imgHeight, texturePixels, 0, stride);

            try {
               grabber.grabPixels();
            } catch (InterruptedException e) {
               return false;
            }

            return true;
         }
      }
   }

   public synchronized boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
      return false;
   }
}
