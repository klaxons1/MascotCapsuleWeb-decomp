import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class ModelLoaderThread implements Runnable {
   private Thread thread;
   private final MascotCapsule applet;

   private ModelLoaderThread(MascotCapsule applet) {
      this.applet = applet;
      this.thread = null;
   }

   public synchronized void start() {
      this.thread = new Thread(this);
      this.thread.setName("MascotCapsule - UnlitDrawer");
      this.thread.setPriority(5);
      this.thread.start();
   }

   public synchronized void stop() {
      this.applet.getCanvas().stopAnimation();
      if (this.thread != null) {
         this.thread.interrupt();
         this.thread = null;
      }
   }

   public void run() {
      try {
         if (this.applet.isAutoLoad()) {
            this.applet.getCanvas().setShowLoadingScreen(true);
         } else {
            this.applet.getCanvas().setStatusText(" ");
         }

         long startTime = System.currentTimeMillis();

         try {
            String zipFile = this.applet.getStringParam("ZIPFILE", null);
            String bacFile = this.applet.getStringParam("BACFILE", null);
            String traFile = this.applet.getStringParam("TRAFILE", null);
            String textureFile = this.applet.getStringParam("TEXTURE", null);
            String sphereFile = this.applet.getStringParam("SPHERE", null);
            String bgFile = this.applet.getStringParam("BACKGROUND", null);
            URL docBase = this.applet.getDocumentBase();
            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (zipFile != null) {
               InputStream zipStream = new URL(docBase, zipFile).openStream();
               this.applet.getCanvas().loadZipArchive(zipStream);
               zipStream.close();
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (bacFile != null || traFile != null || textureFile != null) {
               InputStream bacStream = bacFile != null ? new URL(docBase, bacFile).openStream() : null;
               InputStream traStream = traFile != null ? new URL(docBase, traFile).openStream() : null;
               InputStream texStream = textureFile != null ? new URL(docBase, textureFile).openStream() : null;
               this.applet.getCanvas().loadAssets(bacStream, traStream, texStream);
               if (bacStream != null) {
                  bacStream.close();
               }

               if (traStream != null) {
                  traStream.close();
               }

               if (texStream != null) {
                  texStream.close();
               }
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (sphereFile != null) {
               InputStream sphereStream = new URL(docBase, sphereFile).openStream();
               this.applet.getCanvas().loadSphereTexture(sphereStream);
               sphereStream.close();
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            if (bgFile != null) {
               InputStream bgStream = new URL(docBase, bgFile).openStream();
               this.applet.getCanvas().loadBackgroundTexture(bgStream);
               bgStream.close();
            }

            if (Thread.interrupted()) {
               throw new InterruptedException();
            }
         } catch (IOException e) {
            e.printStackTrace();
         }

         if (this.applet.isAutoLoad()) {
            this.applet.getCanvas().setLoadingProgressComplete();
            this.applet.getCanvas().repaint();
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed < 1000L) {
               Thread.sleep(1000L - elapsed);
            }
         }

         this.applet.getCanvas().setStatusText(this.applet.getCanvas().isReadyToRender() ? null : "Data Error.");
         if (this.applet.isAutoLoad()) {
            this.applet.getCanvas().setShowLoadingScreen(false);
         }

         if (!this.applet.getCanvas().isReadyToRender()) {
            this.applet.getCanvas().repaint();
         }

         synchronized (this) {
            this.applet.getCanvas().stopAnimation();
            if (Thread.interrupted()) {
               throw new InterruptedException();
            }

            System.gc();
            this.applet.getCanvas().startAnimation();
         }
      } catch (InterruptedException e) {
      }

      this.thread = null;
   }

   ModelLoaderThread(MascotCapsule applet, ModelLoaderThreadToken unused) {
      this(applet);
   }
}
