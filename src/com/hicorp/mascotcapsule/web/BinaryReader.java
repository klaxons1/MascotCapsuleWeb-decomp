package com.hicorp.mascotcapsule.web;

import java.io.IOException;
import java.io.InputStream;

public class BinaryReader {
   private InputStream in;

   public BinaryReader(InputStream in) {
      this.in = in;
   }

   public byte readByte() {
      try {
         return (byte)this.in.read();
      } catch (IOException e) {
         return -1;
      }
   }

   public byte readByteSigned() {
      return this.readByte();
   }

   public short readShort() {
      int b0 = this.readUnsignedByte();
      int b1 = this.readUnsignedByte();
      return (short)((b1 << 8) + b0);
   }

   public short readShortLE() {
      return this.readShort();
   }

   public int readInt() {
      int b0 = this.readUnsignedByte();
      int b1 = this.readUnsignedByte();
      int b2 = this.readUnsignedByte();
      int b3 = this.readUnsignedByte();
      return (b3 << 24) + (b2 << 16) + (b1 << 8) + b0;
   }

   public int readIntLE() {
      return this.readInt();
   }

   public float readFloat() {
      int bits = this.readInt();
      return Float.intBitsToFloat(bits);
   }

   public float readFloatLE() {
      return this.readFloat();
   }

   public int skip(int count) {
      try {
         return (int)this.in.skip(count);
      } catch (IOException e) {
         return 0;
      }
   }

   public int skipBytes(int count) {
      return this.skip(count);
   }

   public int readUnsignedByte() {
      try {
         return this.in.read();
      } catch (IOException e) {
         return -1;
      }
   }

   public int readUnsignedShort() {
      return this.readUnsignedShortLE();
   }

   public int readUnsignedShortLE() {
      int b0 = this.readUnsignedByte();
      int b1 = this.readUnsignedByte();
      return (b1 << 8) + b0;
   }

   public String readCString() {
      StringBuffer sb = new StringBuffer();

      int ch;
      while ((ch = this.readUnsignedByte()) > 0) {
         sb.append((char)ch);
      }

      return sb.toString();
   }

   public String readNullTerminatedString() {
      return this.readCString();
   }
}
