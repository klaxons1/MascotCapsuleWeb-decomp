import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class Class_2a implements Runnable {
   private Thread var_4f;
   private final MascotCapsule var_97;

   private Class_2a(MascotCapsule var1) {
      this.var_97 = var1;
      this.var_4f = null;
   }

   public synchronized void sub_2d() {
      this.var_4f = new Thread(this);
      this.var_4f.setName("MascotCapsule - ModelLoader");
      this.var_4f.setPriority(5);
      this.var_4f.start();
   }

   public synchronized void sub_8c() {
      MascotCapsule.sub_13e(this.var_97).sub_a8();
      if (this.var_4f != null) {
         this.var_4f.interrupt();
         this.var_4f = null;
      }
   }

   public void run() {
      try {
         if (MascotCapsule.sub_15f(this.var_97)) {
            MascotCapsule.sub_13e(this.var_97).sub_1fc(true);
         } else {
            MascotCapsule.sub_13e(this.var_97).sub_168(" ");
         }

         long var1 = System.currentTimeMillis();

         try {
            String var3 = MascotCapsule.sub_171(this.var_97, "ZIPFILE", null);
            String var4 = MascotCapsule.sub_171(this.var_97, "BACFILE", null);
            String var5 = MascotCapsule.sub_171(this.var_97, "TRAFILE", null);
            String var6 = MascotCapsule.sub_171(this.var_97, "TEXTURE", null);
            String var7 = MascotCapsule.sub_171(this.var_97, "SPHERE", null);
            String var8 = MascotCapsule.sub_171(this.var_97, "BACKGROUND", null);
            URL var9 = this.var_97.getDocumentBase();
            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (var3 != null) {
               InputStream var10 = new URL(var9, var3).openStream();
               MascotCapsule.sub_13e(this.var_97).sub_312(var10);
               var10.close();
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (var4 != null || var5 != null || var6 != null) {
               InputStream var18 = var4 != null ? new URL(var9, var4).openStream() : null;
               InputStream var11 = var5 != null ? new URL(var9, var6).openStream() : null;
               InputStream var12 = var6 != null ? new URL(var9, var5).openStream() : null;
               MascotCapsule.sub_13e(this.var_97).sub_34e(var18, var11, var12);
               if (var18 != null) {
                  var18.close();
               }

               if (var11 != null) {
                  var11.close();
               }

               if (var12 != null) {
                  var12.close();
               }
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (var7 != null) {
               InputStream var19 = new URL(var9, var7).openStream();
               MascotCapsule.sub_13e(this.var_97).sub_399(var19);
               var19.close();
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (var8 != null) {
               InputStream var20 = new URL(var9, var8).openStream();
               MascotCapsule.sub_13e(this.var_97).sub_3da(var20);
               var20.close();
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }
         } catch (IOException var15) {
            var15.printStackTrace();
         }

         if (MascotCapsule.sub_15f(this.var_97)) {
            MascotCapsule.sub_13e(this.var_97).sub_259();
            MascotCapsule.sub_13e(this.var_97).repaint();
            long var17 = System.currentTimeMillis() - var1;
            if (var17 < 1000L) {
               Thread.sleep(1000L - var17);
            }
         }

         MascotCapsule.sub_13e(this.var_97).sub_168(MascotCapsule.sub_13e(this.var_97).sub_1aa() ? null : "Data Error.");
         if (MascotCapsule.sub_15f(this.var_97)) {
            MascotCapsule.sub_13e(this.var_97).sub_1fc(false);
         }

         if (!MascotCapsule.sub_13e(this.var_97).sub_1aa()) {
            MascotCapsule.sub_13e(this.var_97).repaint();
         }

         synchronized (this) {
            MascotCapsule.sub_13e(this.var_97).sub_a8();
            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            System.gc();
            MascotCapsule.sub_13e(this.var_97).sub_96();
         }
      } catch (InterruptedException var16) {
      }

      this.var_4f = null;
   }

   Class_2a(MascotCapsule var1, Class_60 var2) {
      this(var1);
   }
}
