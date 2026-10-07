import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import com.hicorp.mascotcapsule.web.Vector3f;
import com.hicorp.mascotcapsule.web.MatrixUtils;

public class TestRunner {
   public static void main(String[] args) {
      try {
         System.out.println("Starting MascotCapsule Test Runner...");
         int width = 400;
         int height = 400;
         MascotCapsuleCanvas canvas = new MascotCapsuleCanvas(width, height, false);
         canvas.initPacketTable(10000, 100.0F, 2000.0F);
         canvas.setPerspective(true);
         canvas.setInitialScale(1.0F);
         canvas.setAmbientIntensity(0.3F);
         canvas.setDirectionalLight(new Vector3f(0.5F, -0.7F, -1.0F), 2.5F);
         canvas.setLightingMode(1);
         canvas.setCameraLookAt(MatrixUtils.createLookAt(new Vector3f(0.0F, 0.0F, 300.0F), new Vector3f(0.0F, 0.0F, 0.0F)));
         canvas.resetModelTransform();
         
         File zipFile = new File("docs/sample.zip");
         System.out.println("Loading archive: " + zipFile.getAbsolutePath());
         FileInputStream fis = new FileInputStream(zipFile);
         canvas.loadZipArchive(fis);
         fis.close();
         
         System.out.println("Stepping animation and rendering frame...");
         // Advance animation a few frames to get a dynamic 3D pose
         for (int i = 0; i < 15; i++) {
            canvas.renderFrame();
         }
         
         int[] pixels = canvas.getPixels();
         System.out.println("Framebuffer pixels length: " + (pixels != null ? pixels.length : "null"));
         
         BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
         img.setRGB(0, 0, width, height, pixels, 0, width);
         
         File outPng = new File("docs/screenshot.png");
         ImageIO.write(img, "png", outPng);
         System.out.println("Saved screenshot to: " + outPng.getAbsolutePath());
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
