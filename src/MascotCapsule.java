import java.applet.Applet;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_3d2;
import pkg_4c.pkg_d.pkg_56.pkg_22.Class_4ce;

public final class MascotCapsule extends Applet {
   private Class_aa var_75 = null;
   private boolean var_13c = true;
   private static final Class_3d2 var_153 = new Class_3d2(0.0F, 0.0F, 1000.0F);
   private static final Class_3d2 var_1b7 = new Class_3d2(0.0F, 0.0F, 0.0F);
   private Class_2a var_253 = null;

   public synchronized void init() {
      Thread.currentThread().setName("MascotCapsule");
      Thread.currentThread().setPriority(5);
      this.var_75 = new Class_aa(this.sub_a5("WIDTH", 640), this.sub_a5("HEIGHT", 480), this.sub_a5("FIXED", 0) != 0);
      this.var_75.sub_168(" ");
      this.var_75.sub_33e(this.sub_a5("SHOW_STATUS", 0) != 0);
      this.var_13c = false;
      this.var_75.sub_6f5(this.sub_fd("FRAME_PER_SEC", 20.0F));
      this.var_75.sub_55f(Class_4ce.sub_1d7(this.sub_fd("ANGLE_X", 0.0F)));
      this.var_75.sub_5b5(Class_4ce.sub_1d7(this.sub_fd("ANGLE_Y", 0.0F)));
      this.var_75.sub_64f(Class_4ce.sub_1d7(this.sub_fd("ANGLE_PER_SEC", 0.0F)));
      this.var_75.sub_7d9(this.sub_a5("PERS", 0) != 0);
      this.var_75.sub_501(this.sub_fd("SCALE", 0.5F));
      this.var_75.sub_331(this.sub_fd("AMBIENT_LIGHT_INTENSITY", 0.2F));
      this.var_75.sub_7a4(this.sub_a5("MAX_FPS", -1));
      this.var_75.sub_806(this.sub_a5("MIPMAP", 1) != 0);
      Class_3d2 var1 = new Class_3d2(-this.sub_fd("DIRECTION_LIGHT_X", 0.0F), this.sub_fd("DIRECTION_LIGHT_Y", 0.0F), -this.sub_fd("DIRECTION_LIGHT_Z", -1.0F));
      this.var_75.sub_2e4(var1, this.sub_fd("DIRECTION_LIGHT_INTENSITY", 2.0F));
      this.var_75.sub_5ca(Class_4ce.sub_18c(var_153, var_1b7));
      this.var_75.sub_498(this.sub_a5("OFFSET_X", 0), this.sub_a5("OFFSET_Y", 0));
      this.var_75.sub_1a2(this.sub_a5("PKTTBL_NUM", 10000), this.sub_fd("PKTTBL_X", 100.0F), this.sub_fd("PKTTBL_Y", 2000.0F));
      this.var_75.sub_5fa(this.sub_a5("FORCE_LIGHTING", 0) != 0);
      this.var_75.sub_761(this.sub_a5("ENABLE_LIGHTING", 1));
      this.var_75.sub_1a();

      try {
         FlowLayout var2 = (FlowLayout)this.getLayout();
         var2.setHgap(0);
         var2.setVgap(0);
      } catch (Exception var3) {
      }

      this.add(this.var_75);
      this.var_75.repaint();
      this.var_253 = new Class_2a(this, null);
      this.var_253.sub_2d();
   }

   public void start() {
   }

   public void stop() {
   }

   public synchronized void destroy() {
      if (this.var_253 != null) {
         this.var_253.sub_8c();
         this.var_253 = null;
      }

      this.var_75.sub_a8();
   }

   private String sub_62(String var1, String var2) {
      String var3 = null;

      try {
         var3 = this.getParameter(var1);
      } catch (NullPointerException var5) {
      }

      return var3 == null ? var2 : var3;
   }

   private int sub_a5(String var1, int var2) {
      String var3 = null;

      try {
         var3 = this.getParameter(var1);
      } catch (NullPointerException var6) {
      }

      try {
         return var3 != null ? Integer.parseInt(var3) : var2;
      } catch (NumberFormatException var5) {
         var5.printStackTrace();
         throw new RuntimeException();
      }
   }

   private float sub_fd(String var1, float var2) {
      String var3 = null;

      try {
         var3 = this.getParameter(var1);
      } catch (NullPointerException var6) {
      }

      try {
         return var3 != null ? new Float(var3) : var2;
      } catch (NumberFormatException var5) {
         var5.printStackTrace();
         throw new RuntimeException();
      }
   }

   public void bacScale(float var1) {
      this.var_75.sub_68c(var1);
   }

   public void bacMove(int var1, int var2) {
      this.var_75.sub_6ba(var1, var2);
   }

   public void bacRotate(float var1, float var2) {
      this.var_75.sub_6c8(var1, var2);
   }

   public void traSpeed(float var1) {
      this.var_75.sub_74b(var1);
   }

   public void showDrawStatus(int var1) {
      this.var_75.sub_33e(var1 != 0);
   }

   public void enableLighting(int var1) {
      this.var_75.sub_761(var1);
   }

   public void setSphere(String var1) {
      try {
         if (var1 == null) {
            this.var_75.sub_22b(null);
         } else {
            InputStream var2 = new URL(this.getDocumentBase(), var1).openStream();
            this.var_75.sub_399(var2);
            var2.close();
         }
      } catch (IOException var3) {
      }
   }

   public void setModel(String var1, String var2, String var3) {
      try {
         URL var4 = this.getDocumentBase();
         InputStream var5 = new URL(var4, var1).openStream();
         InputStream var6 = new URL(var4, var2).openStream();
         InputStream var7 = new URL(var4, var3).openStream();
         this.var_75.sub_34e(var5, var6, var7);
         var7.close();
         var6.close();
         var5.close();
      } catch (IOException var8) {
      }
   }

   public void setBG(String var1) {
      try {
         URL var2 = this.getDocumentBase();
         InputStream var3 = new URL(var2, var1).openStream();
         this.var_75.sub_3da(var3);
         var3.close();
      } catch (IOException var4) {
      }
   }

   public void setModel(String var1) {
      try {
         URL var2 = this.getDocumentBase();
         InputStream var3 = new URL(var2, var1).openStream();
         this.var_75.sub_312(var3);
         var3.close();
      } catch (IOException var4) {
      }
   }

   static Class_aa sub_13e(MascotCapsule var0) {
      return var0.var_75;
   }

   static boolean sub_15f(MascotCapsule var0) {
      return var0.var_13c;
   }

   static String sub_171(MascotCapsule var0, String var1, String var2) {
      return var0.sub_62(var1, var2);
   }
}
