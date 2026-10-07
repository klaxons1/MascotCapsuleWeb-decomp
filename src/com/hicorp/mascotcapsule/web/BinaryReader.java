package com.hicorp.mascotcapsule.web;

import java.io.InputStream;

public class BinaryReader {
   private InputStream in;

   public BinaryReader(InputStream in) {
      this.in = in;
   }

   public final byte readByte() {
      return (byte)this.in.read();
   }

   public final short readShort() {
      int b0 = this.in.read();
      int b1 = this.in.read();
      return (short)((b1 << 8) + b0);
   }

   public final int readInt() {
      int b0 = this.in.read();
      int b1 = this.in.read();
      int b2 = this.in.read();
      int b3 = this.in.read();
      return (b3 << 24) + (b2 << 16) + (b1 << 8) + b0;
   }

   public final float readFloat() {
      int b0 = this.in.read();
      int b1 = this.in.read();
      int b2 = this.in.read();
      int b3 = this.in.read();
      return Float.intBitsToFloat((b3 << 24) + (b2 << 16) + (b1 << 8) + b0);
   }

   public final int skipBytes(int count) {
      return (int)this.in.skip(count);
   }

   public final int readUnsignedByte() {
      return this.in.read();
   }

   public final int readUnsignedShort() {
      int b0 = this.in.read();
      int b1 = this.in.read();
      return (b1 << 8) + b0;
   }

   public String readCString() {
      StringBuffer sb = new StringBuffer();
      int ch;
      while ((ch = this.in.read()) > 0) {
         sb.append((char)ch);
      }
      return sb.toString();
   }
}
