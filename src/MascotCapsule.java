import java.applet.Applet;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import com.hicorp.mascotcapsule.web.Vector3f;
import com.hicorp.mascotcapsule.web.MatrixUtils;

public final class MascotCapsule extends Applet {
   private MascotCapsuleCanvas canvas = null;
   private boolean autoLoad = true;
   private static final Vector3f DEFAULT_CAMERA_POS = new Vector3f(0.0F, 0.0F, 1000.0F);
   private static final Vector3f DEFAULT_CAMERA_TARGET = new Vector3f(0.0F, 0.0F, 0.0F);
   private ModelLoaderThread modelLoaderThread = null;

   public synchronized void init() {
      Thread.currentThread().setName("MascotCapsule");
      Thread.currentThread().setPriority(5);
      this.canvas = new MascotCapsuleCanvas(this.getIntParam("WIDTH", 640), this.getIntParam("HEIGHT", 480), this.getIntParam("FIXED", 0) != 0);
      this.canvas.setStatusText(" ");
      this.canvas.setShowStats(this.getIntParam("SHOW_STATUS", 0) != 0);
      this.autoLoad = false;
      this.canvas.setFrameRate(this.getFloatParam("FRAME_PER_SEC", 20.0F));
      this.canvas.setInitialAngleX(MatrixUtils.toRadians(this.getFloatParam("ANGLE_X", 0.0F)));
      this.canvas.setInitialAngleY(MatrixUtils.toRadians(this.getFloatParam("ANGLE_Y", 0.0F)));
      this.canvas.setRotationSpeed(MatrixUtils.toRadians(this.getFloatParam("ANGLE_PER_SEC", 0.0F)));
      this.canvas.setPerspective(this.getIntParam("PERS", 0) != 0);
      this.canvas.setInitialScale(this.getFloatParam("SCALE", 0.5F));
      this.canvas.setAmbientIntensity(this.getFloatParam("AMBIENT_LIGHT_INTENSITY", 0.2F));
      this.canvas.setMaxFps(this.getIntParam("MAX_FPS", -1));
      this.canvas.setMipmapEnabled(this.getIntParam("MIPMAP", 1) != 0);
      Vector3f dirLight = new Vector3f(
         -this.getFloatParam("DIRECTION_LIGHT_X", 0.0F),
         this.getFloatParam("DIRECTION_LIGHT_Y", 0.0F),
         -this.getFloatParam("DIRECTION_LIGHT_Z", -1.0F)
      );
      this.canvas.setDirectionalLight(dirLight, this.getFloatParam("DIRECTION_LIGHT_INTENSITY", 2.0F));
      this.canvas.setCameraLookAt(MatrixUtils.createLookAt(DEFAULT_CAMERA_POS, DEFAULT_CAMERA_TARGET));
      this.canvas.addViewOffset(this.getIntParam("OFFSET_X", 0), this.getIntParam("OFFSET_Y", 0));
      this.canvas.initPacketTable(this.getIntParam("PKTTBL_NUM", 10000), this.getFloatParam("PKTTBL_X", 100.0F), this.getFloatParam("PKTTBL_Y", 2000.0F));
      this.canvas.setForceLighting(this.getIntParam("FORCE_LIGHTING", 0) != 0);
      this.canvas.setLightingMode(this.getIntParam("ENABLE_LIGHTING", 1));
      this.canvas.resetModelTransform();

      try {
         FlowLayout layout = (FlowLayout)this.getLayout();
         layout.setHgap(0);
         layout.setVgap(0);
      } catch (Exception e) {
      }

      this.add(this.canvas);
      this.canvas.repaint();
      this.modelLoaderThread = new ModelLoaderThread(this, null);
      this.modelLoaderThread.start();
   }

   public void start() {
   }

   public void stop() {
   }

   public synchronized void destroy() {
      if (this.modelLoaderThread != null) {
         this.modelLoaderThread.stop();
         this.modelLoaderThread = null;
      }

      this.canvas.stopAnimation();
   }

   String getStringParam(String name, String defaultValue) {
      String val = null;

      try {
         val = this.getParameter(name);
      } catch (NullPointerException e) {
      }

      return val == null ? defaultValue : val;
   }

   int getIntParam(String name, int defaultValue) {
      String val = null;

      try {
         val = this.getParameter(name);
      } catch (NullPointerException e) {
      }

      try {
         return val != null ? Integer.parseInt(val) : defaultValue;
      } catch (NumberFormatException e) {
         e.printStackTrace();
         throw new RuntimeException();
      }
   }

   float getFloatParam(String name, float defaultValue) {
      String val = null;

      try {
         val = this.getParameter(name);
      } catch (NullPointerException e) {
      }

      try {
         return val != null ? new Float(val) : defaultValue;
      } catch (NumberFormatException e) {
         e.printStackTrace();
         throw new RuntimeException();
      }
   }

   public void bacScale(float scale) {
      this.canvas.adjustScale(scale);
   }

   public void bacMove(int dx, int dy) {
      this.canvas.moveCenter(dx, dy);
   }

   public void bacRotate(float rotX, float rotY) {
      this.canvas.rotateModel(rotX, rotY);
   }

   public void traSpeed(float speed) {
      this.canvas.adjustFrameRate(speed);
   }

   public void showDrawStatus(int show) {
      this.canvas.setShowStats(show != 0);
   }

   public void enableLighting(int mode) {
      this.canvas.setLightingMode(mode);
   }

   public void setSphere(String url) {
      try {
         if (url == null) {
            this.canvas.setSphereMapTexture(null);
         } else {
            InputStream in = new URL(this.getDocumentBase(), url).openStream();
            this.canvas.loadSphereTexture(in);
            in.close();
         }
      } catch (IOException e) {
      }
   }

   public void setModel(String bacFile, String traFile, String texFile) {
      try {
         URL docBase = this.getDocumentBase();
         InputStream bacIn = new URL(docBase, bacFile).openStream();
         InputStream traIn = new URL(docBase, traFile).openStream();
         InputStream texIn = new URL(docBase, texFile).openStream();
         this.canvas.loadAssets(bacIn, traIn, texIn);
         texIn.close();
         traIn.close();
         bacIn.close();
      } catch (IOException e) {
      }
   }

   public void setBG(String bgUrl) {
      try {
         URL docBase = this.getDocumentBase();
         InputStream in = new URL(docBase, bgUrl).openStream();
         this.canvas.loadBackgroundTexture(in);
         in.close();
      } catch (IOException e) {
      }
   }

   public void setModel(String zipUrl) {
      try {
         URL docBase = this.getDocumentBase();
         InputStream in = new URL(docBase, zipUrl).openStream();
         this.canvas.loadZipArchive(in);
         in.close();
      } catch (IOException e) {
      }
   }

   MascotCapsuleCanvas getCanvas() {
      return this.canvas;
   }

   boolean isAutoLoad() {
      return this.autoLoad;
   }
}
