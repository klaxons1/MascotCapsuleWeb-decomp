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
import com.hicorp.mascotcapsule.web.CameraNode;
import com.hicorp.mascotcapsule.web.Vector3f;
import com.hicorp.mascotcapsule.web.ImageDecoder;
import com.hicorp.mascotcapsule.web.TraAnimation;
import com.hicorp.mascotcapsule.web.MatrixUtils;
import com.hicorp.mascotcapsule.web.Texture;
import com.hicorp.mascotcapsule.web.BoundingBox;
import com.hicorp.mascotcapsule.web.SceneNode;
import com.hicorp.mascotcapsule.web.Transform3D;
import com.hicorp.mascotcapsule.web.BacModel;
import com.hicorp.mascotcapsule.web.AwtImageDecoder;
import com.hicorp.mascotcapsule.web.MainCanvas;

public final class MascotCapsuleCanvas extends MainCanvas implements KeyListener, MouseListener, MouseMotionListener {
   private static final String VERSION_STRING = "1.00";
   private static final String LOGO_RESOURCE = "logo.gif";
   private Image logoImage = null;
   private boolean isPainted = false;
   private boolean showLoadingScreen = false;
   private int loadProgressSteps = 0;
   private final CameraNode camera = new CameraNode();
   private final Texture sphereTexture = new Texture();
   private Texture modelTexture = new Texture();
   private BacModel model = null;
   private TraAnimation animation = null;
   private boolean disablePerspective = false;
   private Texture backgroundTexture = new Texture();
   private int[] backgroundPixels = null;
   private int backgroundColor = -16777216;
   private boolean useBackgroundTexture = false;
   private float minScale = 0.005F;
   private float maxScale = 5.0F;
   private int initialCenterX;
   private int initialCenterY;
   private float initialAngleX = 0.0F;
   private float initialAngleY = 0.0F;
   private float initialScale = 2.0F;
   private String statusText = null;
   private final Transform3D modelRotation = new Transform3D();
   private boolean lightingEnabled = false;
   private float currentScale;
   private int currentCenterX;
   private int currentCenterY;
   private float currentFrame = 0.0F;
   private boolean mipmapEnabled = true;
   private float dragAngleX = 0.0F;
   private float dragAngleY = 0.0F;
   private float deltaAngleX = 0.0F;
   private float deltaAngleY = 0.0F;
   private int targetFrameIntervalMs = -1;
   private float frameRate = 0.1F;
   private float rotationSpeed = 0.0F;
   private int lastMouseX;
   private int lastMouseY;
   private boolean forceLighting = false;
   private static final Vector3f DEFAULT_CAMERA_POS = new Vector3f(0.0F, 0.0F, 1000.0F);
   private static final Vector3f DEFAULT_CAMERA_TARGET = new Vector3f(0.0F, 0.0F, 0.0F);
   Toolkit toolkit = Toolkit.getDefaultToolkit();
   final Transform3D rotTempX = new Transform3D();
   final Transform3D rotTempY = new Transform3D();
   final Transform3D modelTransform = new Transform3D();
   private AnimationThread animationThread;
   private int mouseDragMode = 0;

   public MascotCapsuleCanvas(int width, int height, boolean fixed) {
      super(width, height);
      this.loadLogoImage();
      this.updateBackgroundBuffer();
      this.clearBackground(null);
      this.initPacketTable(10000, 100.0F, 2000.0F);
      this.initialCenterX = width / 2;
      this.initialCenterY = height / 2;
      this.camera.setLocalTransform(MatrixUtils.createLookAt(DEFAULT_CAMERA_POS, DEFAULT_CAMERA_TARGET));
      this.resize(width, height);
      this.resetModelTransform();
      if (!fixed) {
         this.addKeyListener(this);
         this.addMouseListener(this);
         this.addMouseMotionListener(this);
      }
   }

   public void resetModelTransform() {
      Transform3D rotY = new Transform3D();
      MatrixUtils.setRotationX(this.initialAngleX, this.modelRotation);
      MatrixUtils.setRotationY(this.initialAngleY, rotY);
      this.modelRotation.multiplyRotation(rotY);
      this.modelRotation.normalizeColumns();
      this.currentFrame = 0.0F;
      this.currentCenterX = this.initialCenterX;
      this.currentCenterY = this.initialCenterY;
      this.currentScale = this.initialScale;
      this.deltaAngleX = 0.0F;
      this.deltaAngleY = 0.0F;
      this.dragAngleX = 0.0F;
      this.dragAngleY = 0.0F;
   }

   public synchronized boolean renderFrame() {
      this.setDiffuseTexture(this.modelTexture);
      this.setLightingEnabled(this.lightingEnabled);
      this.setViewportOffset(this.currentCenterX, this.currentCenterY);
      if (this.disablePerspective) {
         this.disablePerspective();
      } else {
         this.enablePerspective(1.0F);
      }

      MatrixUtils.setRotationX(this.deltaAngleX, this.rotTempX);
      MatrixUtils.setRotationY(this.deltaAngleY, this.rotTempY);
      this.deltaAngleX = 0.0F;
      this.deltaAngleY = 0.0F;
      this.rotTempX.multiplyRotation(this.rotTempY);
      this.modelRotation.multiplyRotation(this.rotTempX, this.modelRotation);
      this.modelRotation.normalizeColumns();
      this.modelTransform.m03 = this.modelTransform.m13 = this.modelTransform.m23 = 0.0F;
      MatrixUtils.setScale(this.currentScale, this.modelTransform);
      MatrixUtils.setRotationX(this.dragAngleX, this.rotTempX);
      this.modelTransform.multiplyRotation(this.rotTempX);
      MatrixUtils.setRotationY(this.dragAngleY, this.rotTempX);
      this.modelTransform.multiplyRotation(this.rotTempX);
      this.deltaAngleX = 0.0F;
      this.deltaAngleY = 0.0F;
      this.modelTransform.multiplyRotation(this.modelRotation);
      if (this.animation != null) {
         this.animation.applyPose(this.currentFrame);
      }

      SceneNode root = this.model.getRootNode();
      root.setLocalTransform(this.modelTransform);
      this.renderModel(this.model, this.camera);
      return this.renderAndFlush();
   }

   public synchronized void startAnimation() {
      this.stopAnimation();
      this.animationThread = new AnimationThread(this, null);
      this.animationThread.start();
   }

   public synchronized void stopAnimation() {
      if (this.animationThread != null) {
         this.animationThread.stop();
         this.animationThread = null;
      }
   }

   public void clearBackground(BoundingBox clip) {
      int[] pixels = this.getPixels();
      if (pixels != null && this.backgroundPixels != null) {
         if (clip == null) {
            System.arraycopy(this.backgroundPixels, 0, pixels, 0, pixels.length);
         } else if (clip.isValid()) {
            int width = this.getWidth();
            int spanWidth = clip.maxX - clip.minX;
            int offset = clip.minY * width + clip.minX;

            for (int y = clip.minY; y < clip.maxY; y++) {
               System.arraycopy(this.backgroundPixels, offset, pixels, offset, spanWidth);
               offset += width;
            }
         }
      }
   }

   public synchronized void setBounds(int x, int y, int width, int height) {
      int oldW = this.getWidth();
      int oldH = this.getHeight();
      super.setBounds(x, y, width, height);
      if (width != oldW || height != oldH) {
         this.updateBackgroundBuffer();
      }

      this.clearBackground(null);
   }

   public void paint(Graphics g) {
      if (this.statusText == null && !this.showLoadingScreen) {
         if (!this.isPainted) {
            this.markDirtyAll();
         }

         super.paint(g);
         this.isPainted = true;
      } else {
         if (!this.showLoadingScreen) {
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.white);
            g.drawString(this.statusText, 16, 32);
         } else {
            int left = (this.getWidth() - 104) / 2;
            int top = (this.getHeight() + 83) / 2 + 4;
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            int progress = this.getLoadingProgressPercentage();
            int remaining = 100 - progress;
            g.setColor(Color.gray);
            g.fillRect(left, top, 104, 16);
            g.setColor(Color.white);
            g.fillRect(left + 1, top + 1, 102, 14);
            int barX = left + 2;

            for (int i = 0; i < progress; i++) {
               int r = -124 * i / 99 + 220;
               int gr = -63 * i / 99 + 255;
               int b = 0 * i / 99 + 255;
               Color color = new Color(r, gr, b);
               g.setColor(color);
               g.drawLine(barX, top + 2, barX, top + 2 + 16 - 4 - 1);
               barX++;
            }

            g.setColor(Color.white);
            g.fillRect(left + 2 + 100 - remaining, top + 2, remaining, 12);
            if (this.logoImage != null) {
               g.drawImage(this.logoImage, (this.getWidth() - 287) / 2, (this.getHeight() - 99) / 2 - 20, this);
            }
         }

         this.isPainted = false;
      }
   }

   public void update(Graphics g) {
      if (this.statusText != null) {
         this.paint(g);
      }
   }

   private void loadLogoImage() {
      ByteArrayOutputStream byteOut = null;

      try {
         InputStream in = this.getClass().getResourceAsStream(LOGO_RESOURCE);
         byteOut = new ByteArrayOutputStream();
         byte[] buf = new byte[65536];

         int count;
         while ((count = in.read(buf)) != -1) {
            byteOut.write(buf, 0, count);
         }

         byteOut.close();
      } catch (IOException e) {
      }

      this.logoImage = Toolkit.getDefaultToolkit().createImage(byteOut.toByteArray());
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(this.logoImage, 0);

      try {
         tracker.waitForID(0);
      } catch (InterruptedException e) {
      }
   }

   public synchronized void setBackgroundColor(int rgb) {
      this.useBackgroundTexture = false;
      this.backgroundColor = rgb | 0xFF000000;
      this.updateBackgroundBuffer();
   }

   private void updateBackgroundBuffer() {
      int h = this.getHeight();
      int w = this.getWidth();
      int totalPixels = w * h;
      int[] buffer = new int[totalPixels];
      if (this.useBackgroundTexture) {
         int[] srcPixels = this.backgroundTexture.getPixels();
         int srcStride = 1 << this.backgroundTexture.getWidthLog2();
         int bgW = this.backgroundTexture.getWidth();
         int bgH = this.backgroundTexture.getHeight();

         for (int y = 0; y < h; y += bgH) {
            int blockH = y + bgH < h ? bgH : h - y;

            for (int dy = 0; dy < blockH; dy++) {
               int srcOffset = dy * srcStride;
               int dstOffset = (y + dy) * w;

               for (int x = 0; x < w; x += bgW) {
                  int blockW = x + bgW < w ? bgW : w - x;
                  System.arraycopy(srcPixels, srcOffset, buffer, dstOffset + x, blockW);
               }
            }
         }
      } else {
         for (int i = 0; i < totalPixels; i++) {
            buffer[i] = this.backgroundColor;
         }
      }

      this.backgroundPixels = buffer;
      this.markDirtyAll();
   }

   public void setStatusText(String status) {
      this.statusText = status;
   }

   public boolean isReadyToRender() {
      return this.modelTexture.getPixels() != null && this.model != null && this.animation != null;
   }

   public void setShowLoadingScreen(boolean show) {
      this.showLoadingScreen = show;
   }

   public void setLoadingProgressComplete() {
      this.loadProgressSteps = 5;
   }

   private int getLoadingProgressPercentage() {
      int percent = 20 * this.loadProgressSteps;
      if (percent < 0) {
         percent = 0;
      }

      if (percent > 100) {
         percent = 100;
      }

      return percent;
   }

   public void loadZipArchive(InputStream in) {
      ZipInputStream zipIn = new ZipInputStream(in);
      ImageDecoder reader = this.createTextureReader();
      Texture sphere = null;
      Texture wall = null;
      Texture tex = null;
      BacModel loadedModel = null;
      TraAnimation loadedAnim = null;

      try {
         ZipEntry entry;
         while ((entry = zipIn.getNextEntry()) != null) {
            String name = entry.getName();
            int len = name.length();
            if (name.regionMatches(true, len - 10, "sphere", 0, 6)) {
               sphere = new Texture();
               if (!reader.readImage(new BufferedInputStream(zipIn), sphere)) {
                  sphere = null;
               }

               if (this.sphereTexture == null && sphere != null) {
                  this.loadProgressSteps++;
                  this.repaint();
               }
            } else if (name.regionMatches(true, len - 8, "wall", 0, 4)) {
               wall = new Texture();
               if (!reader.readImage(new BufferedInputStream(zipIn), wall)) {
                  wall = null;
               }

               if (this.backgroundTexture == null && wall != null) {
                  this.loadProgressSteps++;
                  this.repaint();
               }
            } else if (name.regionMatches(true, len - 5, ".jbac", 0, 5)) {
               loadedModel = new BacModel();
               if (!loadedModel.load(new BufferedInputStream(zipIn))) {
                  loadedModel = null;
               } else if (this.forceLighting) {
                  loadedModel.generateNormals();
               }

               if (this.model == null && loadedModel != null) {
                  this.loadProgressSteps++;
                  this.repaint();
               }
            } else if (name.regionMatches(true, len - 5, ".jtra", 0, 5)) {
               loadedAnim = new TraAnimation();
               if (!loadedAnim.load(new BufferedInputStream(zipIn))) {
                  loadedAnim = null;
               }

               if (this.animation == null && loadedAnim != null) {
                  this.loadProgressSteps++;
                  this.repaint();
               }
            } else if (name.regionMatches(true, len - 4, ".bmp", 0, 4)
               || name.regionMatches(true, len - 4, ".jpg", 0, 4)
               || name.regionMatches(true, len - 4, ".png", 0, 4)
               || name.regionMatches(true, len - 4, ".gif", 0, 4)) {
               tex = new Texture();
               if (!reader.readImage(new BufferedInputStream(zipIn), tex)) {
                  tex = null;
               }

               if (this.modelTexture == null && tex != null) {
                  this.loadProgressSteps++;
                  this.repaint();
               }
            }
         }
      } catch (IOException e) {
      }

      synchronized (this) {
         if (sphere != null) {
            if (this.mipmapEnabled) {
               sphere.generateMipmaps();
            }

            this.setSphereMapTexture(sphere);
         }

         if (wall != null) {
            this.useBackgroundTexture = true;
            this.backgroundTexture = wall;
            this.updateBackgroundBuffer();
         }

         this.markDirtyAll();
         if (tex != null) {
            if (this.mipmapEnabled) {
               tex.generateMipmaps();
            }

            this.modelTexture = tex;
         }

         if (loadedModel != null) {
            this.model = loadedModel;
         }

         if (loadedAnim != null) {
            this.animation = loadedAnim;
         }

         if (loadedModel != null || loadedAnim != null && this.model != null) {
            this.animation.bindModel(this.model);
         }
      }

      try {
         zipIn.close();
      } catch (IOException e) {
      }
   }

   public void loadAssets(InputStream bacStream, InputStream traStream, InputStream texStream) {
      try {
         Texture tex = null;
         BacModel loadedModel = null;
         TraAnimation loadedAnim = null;
         if (texStream != null) {
            ImageDecoder reader = this.createTextureReader();
            tex = new Texture();
            if (!reader.readImage(new BufferedInputStream(texStream), tex)) {
               tex = null;
            }

            if (this.modelTexture == null && tex != null) {
               this.loadProgressSteps++;
               this.repaint();
            }
         }

         if (bacStream != null) {
            loadedModel = new BacModel();
            if (!loadedModel.load(new BufferedInputStream(bacStream))) {
               loadedModel = null;
            } else if (this.forceLighting) {
               loadedModel.generateNormals();
            }

            if (this.model == null && loadedModel != null) {
               this.loadProgressSteps++;
               this.repaint();
            }
         }

         if (traStream != null) {
            loadedAnim = new TraAnimation();
            if (!loadedAnim.load(new BufferedInputStream(traStream))) {
               loadedAnim = null;
            }

            if (this.animation == null && loadedAnim != null) {
               this.loadProgressSteps++;
               this.repaint();
            }
         }

         synchronized (this) {
            if (tex != null) {
               if (this.mipmapEnabled) {
                  tex.generateMipmaps();
               }

               this.modelTexture = tex;
            }

            if (loadedModel != null) {
               this.model = loadedModel;
            }

            if (loadedAnim != null) {
               this.animation = loadedAnim;
            }

            if (loadedModel != null || loadedAnim != null) {
               this.animation.bindModel(this.model);
            }
         }
      } catch (Exception e) {
      }
   }

   public synchronized void loadSphereTexture(InputStream in) {
      ImageDecoder reader = this.createTextureReader();
      if (reader.readImage(in, this.sphereTexture)) {
         if (this.mipmapEnabled) {
            this.sphereTexture.generateMipmaps();
         }

         this.setSphereMapTexture(this.sphereTexture);
         this.loadProgressSteps++;
         this.repaint();
      }
   }

   public synchronized void loadBackgroundTexture(InputStream in) {
      ImageDecoder reader = this.createTextureReader();
      if (reader.readImage(in, this.backgroundTexture)) {
         this.useBackgroundTexture = true;
         this.updateBackgroundBuffer();
         this.loadProgressSteps++;
         this.repaint();
      }
   }

   public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
         case 27:
            this.resetModelTransform();
            break;
         case 37:
            this.moveCenter(-16, 0);
            break;
         case 38:
            this.moveCenter(0, -16);
            break;
         case 39:
            this.moveCenter(16, 0);
            break;
         case 40:
            this.moveCenter(0, 16);
            break;
         case 50:
            this.deltaAngleX -= 0.1F;
            break;
         case 52:
            this.deltaAngleY -= 0.1F;
            break;
         case 54:
            this.deltaAngleY += 0.1F;
            break;
         case 56:
            this.deltaAngleX += 0.1F;
            break;
         case 88:
            this.adjustScale(-0.1F);
            break;
         case 90:
            this.adjustScale(0.1F);
      }
   }

   public void keyReleased(KeyEvent e) {
   }

   public void keyTyped(KeyEvent e) {
   }

   public void mousePressed(MouseEvent e) {
      if (e.isAltDown()) {
         this.mouseDragMode = 3;
      } else if (e.isMetaDown()) {
         this.mouseDragMode = 2;
      } else {
         this.mouseDragMode = 1;
      }

      this.setCursor(new Cursor(12));
   }

   public void mouseReleased(MouseEvent e) {
      this.mouseDragMode = 0;
      this.setCursor(Cursor.getDefaultCursor());
      if (this.dragAngleX != 0.0F || this.dragAngleY != 0.0F) {
         this.deltaAngleX = this.dragAngleX;
         this.deltaAngleY = this.dragAngleY;
         this.dragAngleX = 0.0F;
         this.dragAngleY = 0.0F;
      }
   }

   public void mouseClicked(MouseEvent e) {
   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
      this.mouseReleased(e);
   }

   public void mouseDragged(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      switch (this.mouseDragMode) {
         case 1:
            this.dragAngleY += (x - this.lastMouseX) * 0.01F;
            this.dragAngleX += (y - this.lastMouseY) * 0.01F;
            break;
         case 2:
            this.currentCenterX += (x - this.lastMouseX);
            this.currentCenterY += (y - this.lastMouseY);
            break;
         case 3:
            this.currentScale += (x - this.lastMouseX) * 0.005F;
            this.currentScale += (y - this.lastMouseY) * 0.005F;
            if (this.currentScale < this.minScale) {
               this.currentScale = this.minScale;
            } else if (this.currentScale > this.maxScale) {
               this.currentScale = this.maxScale;
            }
      }

      this.lastMouseX = x;
      this.lastMouseY = y;
   }

   public void mouseMoved(MouseEvent e) {
      this.lastMouseX = e.getX();
      this.lastMouseY = e.getY();
   }

   public ImageDecoder createTextureReader() {
      return new AwtImageDecoder();
   }

   public void addViewOffset(int dx, int dy) {
      this.initialCenterX += dx;
      this.initialCenterY += dy;
   }

   public void setViewOffset(int x, int y) {
      this.initialCenterX = x;
      this.initialCenterY = y;
   }

   public void setInitialScale(float scale) {
      if (scale > this.maxScale) {
         scale = this.maxScale;
      } else if (scale < this.minScale) {
         scale = this.minScale;
      }

      this.initialScale = scale;
   }

   public void setInitialAngleX(float angle) {
      this.initialAngleX = angle;
   }

   public void setInitialAngleY(float angle) {
      this.initialAngleY = angle;
   }

   public void setCameraLookAt(Transform3D lookAt) {
      this.camera.setLocalTransform(lookAt);
   }

   public void setForceLighting(boolean force) {
      this.forceLighting = force;
   }

   public void setRotationSpeed(float speed) {
      this.rotationSpeed = speed;
   }

   public void adjustScale(float delta) {
      this.currentScale += delta;
      if (this.currentScale > this.maxScale) {
         this.currentScale = this.maxScale;
      } else if (this.currentScale < this.minScale) {
         this.currentScale = this.minScale;
      }
   }

   public void moveCenter(int dx, int dy) {
      this.currentCenterX += dx;
      this.currentCenterY += dy;
   }

   public void rotateModel(float rotX, float rotY) {
      this.deltaAngleX += rotX;
      this.deltaAngleY += rotY;
   }

   public void setFrameRate(float fps) {
      this.frameRate = fps;
   }

   public void adjustFrameRate(float delta) {
      float newRate = this.frameRate + delta;
      if (newRate < 0.0F) {
         newRate = 0.0F;
      }

      this.frameRate = newRate;
   }

   public void setLightingMode(int mode) {
      this.lightingEnabled = mode != 0;
   }

   public void setMaxFps(float maxFps) {
      if (maxFps == 0.0F) {
         this.targetFrameIntervalMs = -1;
      } else {
         this.targetFrameIntervalMs = (int)(1000.0F / maxFps);
      }
   }

   public void setPerspective(boolean persp) {
      this.disablePerspective = !persp;
   }

   public void setMipmapEnabled(boolean mipmap) {
      this.mipmapEnabled = mipmap;
   }

   static float advanceAnimationFrame(MascotCapsuleCanvas canvas, float delta) {
      return canvas.currentFrame += delta;
   }

   static float getFrameRate(MascotCapsuleCanvas canvas) {
      return canvas.frameRate;
   }

   static TraAnimation getAnimation(MascotCapsuleCanvas canvas) {
      return canvas.animation;
   }

   static float getCurrentFrame(MascotCapsuleCanvas canvas) {
      return canvas.currentFrame;
   }

   static float wrapAnimationFrame(MascotCapsuleCanvas canvas, float delta) {
      return canvas.currentFrame -= delta;
   }

   static float advanceRotation(MascotCapsuleCanvas canvas, float delta) {
      return canvas.deltaAngleY += delta;
   }

   static float getRotationSpeed(MascotCapsuleCanvas canvas) {
      return canvas.rotationSpeed;
   }

   static int getFrameIntervalMs(MascotCapsuleCanvas canvas) {
      return canvas.targetFrameIntervalMs;
   }
}
