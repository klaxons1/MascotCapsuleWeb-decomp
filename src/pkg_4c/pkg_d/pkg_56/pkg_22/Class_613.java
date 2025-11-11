package pkg_4c.pkg_d.pkg_56.pkg_22;

import java.io.InputStream;

public class Class_613 {
   private InputStream var_38;

   public Class_613(InputStream var1) {
      this.var_38 = var1;
   }

   public final byte sub_13() {
      return (byte)this.var_38.read();
   }

   public final short sub_3f() {
      int var1 = this.var_38.read();
      int var2 = this.var_38.read();
      return (short)((var2 << 8) + var1);
   }

   public final int sub_77() {
      int var1 = this.var_38.read();
      int var2 = this.var_38.read();
      int var3 = this.var_38.read();
      int var4 = this.var_38.read();
      return (var4 << 24) + (var3 << 16) + (var2 << 8) + var1;
   }

   public final float sub_de() {
      int var1 = this.var_38.read();
      int var2 = this.var_38.read();
      int var3 = this.var_38.read();
      int var4 = this.var_38.read();
      return Float.intBitsToFloat((var4 << 24) + (var3 << 16) + (var2 << 8) + var1);
   }

   public final int sub_11c(int var1) {
      return (int)this.var_38.skip(var1);
   }

   public final int sub_16f() {
      return this.var_38.read();
   }

   public final int sub_192() {
      int var1 = this.var_38.read();
      int var2 = this.var_38.read();
      return (var2 << 8) + var1;
   }

   public String sub_1a5() {
      StringBuffer var1 = new StringBuffer();

      int var2;
      while ((var2 = this.var_38.read()) > 0) {
         var1.append((char)var2);
      }

      return var1.toString();
   }
}
