package com.hicorp.mascotcapsule.web;

import java.io.InputStream;

public final class BmpDecoder implements ImageDecoder {
   private static final int BI_RGB = 0;
   private static final int BI_RLE8 = 1;
   private static final int BI_RLE4 = 2;
   private static final int BI_BITFIELDS = 3;
   private static final int OS2_HEADER_SIZE = 12;
   private static final int WIN_V3_HEADER_SIZE = 40;
   private static final int WIN_V4_HEADER_SIZE = 64;

   private int[] colorPalette = null;
   private BinaryReader streamReader = null;
   private Texture targetTexture = null;
   private int redMask;
   private int greenMask;
   private int blueMask;

   public boolean readImage(InputStream in, Texture texture) {
      if (in == null) {
         return false;
      } else {
         this.targetTexture = texture;
         this.streamReader = new BinaryReader(in);
         int magic0 = this.streamReader.readUnsignedByte();
         int magic1 = this.streamReader.readUnsignedByte();
         if (magic0 == 'B' && magic1 == 'M') {
            this.streamReader.skipBytes(8);
            int pixelOffset = this.streamReader.readInt();
            int headerSize = this.streamReader.readInt();
            int bmpWidth;
            int bmpHeight;
            short bitCount;
            int numColors;
            int paddingAfterPalette;

            if (headerSize != WIN_V3_HEADER_SIZE && headerSize != WIN_V4_HEADER_SIZE) {
               bmpWidth = this.streamReader.readShort();
               bmpHeight = this.streamReader.readShort();
               if (this.streamReader.readShort() != 1) {
                  return false;
               }
               bitCount = this.streamReader.readShort();
               numColors = 0;
               paddingAfterPalette = 0;
            } else {
               bmpWidth = this.streamReader.readInt();
               bmpHeight = this.streamReader.readInt();
               if (this.streamReader.readShort() != 1) {
                  return false;
               }
               bitCount = this.streamReader.readShort();
               int compression = this.streamReader.readInt();
               if (compression != BI_RGB && compression != BI_BITFIELDS) {
                  return false;
               }

               this.streamReader.skipBytes(12);
               numColors = this.streamReader.readInt();
               this.streamReader.skipBytes(4);

               switch (bitCount) {
                  case 16:
                     if (compression == BI_RGB) {
                        this.redMask = 0x7C00;
                        this.greenMask = 0x03E0;
                        this.blueMask = 0x001F;
                        this.streamReader.skipBytes(headerSize - WIN_V3_HEADER_SIZE);
                     } else {
                        this.redMask = this.streamReader.readInt();
                        this.greenMask = this.streamReader.readInt();
                        this.blueMask = this.streamReader.readInt();
                        this.streamReader.skipBytes(headerSize - WIN_V3_HEADER_SIZE);
                     }
                     break;
                  case 24:
                     if (compression != BI_RGB) {
                        return false;
                     }
                     this.streamReader.skipBytes(headerSize - WIN_V3_HEADER_SIZE);
                     break;
                  case 32:
                     if (compression != BI_BITFIELDS) {
                        return false;
                     }
                     this.redMask = this.streamReader.readInt();
                     this.greenMask = this.streamReader.readInt();
                     this.blueMask = this.streamReader.readInt();
                     this.streamReader.skipBytes(headerSize - WIN_V3_HEADER_SIZE);
                     break;
               }

               paddingAfterPalette = pixelOffset - (headerSize + 14);
            }

            if (bmpWidth >= 0 && bmpHeight > 0 && bmpWidth <= 8192 && bmpHeight <= 8192
                && (bitCount == 1 || bitCount == 4 || bitCount == 8 || bitCount == 16 || bitCount == 24)) {
               if (numColors >= 0 && numColors <= 1 << bitCount) {
                  if (numColors == 0) {
                     numColors = 1 << bitCount;
                  }

                  if (bitCount <= 8) {
                     int paletteEntries = 1 << bitCount;
                     this.colorPalette = new int[paletteEntries];
                     boolean isRgbQuad = headerSize != OS2_HEADER_SIZE;

                     for (int i = 0; i < numColors; i++) {
                        int b = this.streamReader.readUnsignedByte();
                        int g = this.streamReader.readUnsignedByte();
                        int r = this.streamReader.readUnsignedByte();
                        this.colorPalette[i] = 0xFF000000 | r << 16 | g << 8 | b;
                        if (isRgbQuad) {
                           this.streamReader.readUnsignedByte();
                        }
                     }

                     for (int i = numColors; i < paletteEntries; i++) {
                        this.colorPalette[i] = -1;
                     }

                     paddingAfterPalette -= (3 + (isRgbQuad ? 1 : 0)) * numColors;
                  }

                  if (headerSize != OS2_HEADER_SIZE && paddingAfterPalette > 0) {
                     this.streamReader.skipBytes(paddingAfterPalette);
                  }

                  texture.allocate(bmpWidth, bmpHeight);
                  switch (bitCount) {
                     case 4:
                        this.read4BitIndexed();
                        break;
                     case 8:
                        this.read8BitIndexed();
                        break;
                     case 16:
                        this.read16BitRgb();
                        break;
                     case 24:
                        this.read24BitRgb();
                        break;
                     default:
                        return false;
                  }

                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private void read4BitIndexed() {
      int width = this.targetTexture.getWidth();
      int height = this.targetTexture.getHeight();
      int stride = 1 << this.targetTexture.getWidthLog2();
      int byteWidth = (width + 1) / 2;
      int rowPadding = 4 - (byteWidth & 3) & 3;
      int[] pixels = this.targetTexture.getPixels();
      int rowOffset = stride * (height - 1);

      for (int y = height; y > 0; y--) {
         int pixelIndex = rowOffset;

         for (int xBytes = byteWidth; xBytes > 0; pixelIndex += 2) {
            int b = this.streamReader.readUnsignedByte();
            pixels[pixelIndex] = this.colorPalette[b >>> 4];
            pixels[pixelIndex + 1] = this.colorPalette[b & 15];
            xBytes--;
         }

         this.streamReader.skipBytes(rowPadding);
         rowOffset -= stride;
      }
   }

   private void read8BitIndexed() {
      int width = this.targetTexture.getWidth();
      int height = this.targetTexture.getHeight();
      int stride = 1 << this.targetTexture.getWidthLog2();
      int rowPadding = 4 - (width & 3) & 3;
      int[] pixels = this.targetTexture.getPixels();
      int rowOffset = stride * (height - 1);

      for (int y = height; y > 0; y--) {
         int pixelIndex = rowOffset;

         for (int x = width; x > 0; pixelIndex++) {
            int colorIndex = this.streamReader.readUnsignedByte();
            pixels[pixelIndex] = this.colorPalette[colorIndex];
            x--;
         }

         this.streamReader.skipBytes(rowPadding);
         rowOffset -= stride;
      }
   }

   private void read16BitRgb() {
      int width = this.targetTexture.getWidth();
      int height = this.targetTexture.getHeight();
      int stride = 1 << this.targetTexture.getWidthLog2();
      int byteWidth = this.targetTexture.getWidth() * 2;
      int rowPadding = 4 - (byteWidth & 3) & 3;
      int[] pixels = this.targetTexture.getPixels();
      int rowOffset = stride * (height - 1);

      for (int y = height; y > 0; y--) {
         int pixelIndex = rowOffset;

         for (int x = width; x > 0; pixelIndex++) {
            int pixelVal = this.streamReader.readUnsignedShort();
            int r = (pixelVal & this.redMask) * 255 / this.redMask;
            int g = (pixelVal & this.greenMask) * 255 / this.greenMask;
            int b = (pixelVal & this.blueMask) * 255 / this.blueMask;
            pixels[pixelIndex] = 0xFF000000 | r << 16 | g << 8 | b;
            x--;
         }

         this.streamReader.skipBytes(rowPadding);
         rowOffset -= stride;
      }
   }

   private void read24BitRgb() {
      int width = this.targetTexture.getWidth();
      int height = this.targetTexture.getHeight();
      int stride = 1 << this.targetTexture.getWidthLog2();
      int byteWidth = this.targetTexture.getWidth() * 3;
      int rowPadding = 4 - (byteWidth & 3) & 3;
      int[] pixels = this.targetTexture.getPixels();
      int rowOffset = stride * (height - 1);

      for (int y = height; y > 0; y--) {
         int pixelIndex = rowOffset;

         for (int x = width; x > 0; pixelIndex++) {
            int b = this.streamReader.readUnsignedByte();
            int g = this.streamReader.readUnsignedByte();
            int r = this.streamReader.readUnsignedByte();
            pixels[pixelIndex] = 0xFF000000 | r << 16 | g << 8 | b;
            x--;
         }

         this.streamReader.skipBytes(rowPadding);
         rowOffset -= stride;
      }
   }
}
