package com.hicorp.mascotcapsule.web;

public final class Texture {
   private static final int COLOR_TRANSPARENT = -1;
   private static final int MAX_MIP_LEVELS = 12;
   private static final int FIXED_SHIFT = 16;
   protected int[] pixels = null;
   protected int width = 0;
   protected int height = 0;
   protected int widthLog2 = 0;
   protected int heightLog2 = 0;
   protected int uMaskFixed = 0;
   protected int vMaskFixed = 0;
   protected int uShift = 0;
   protected int vShift = 0;
   protected int[] mipLevels = new int[MAX_MIP_LEVELS];
   protected int[] mipOffsets = new int[MAX_MIP_LEVELS];
   protected int[] mipWidthLog2 = new int[MAX_MIP_LEVELS];
   protected int[] mipHeightLog2 = new int[MAX_MIP_LEVELS];
   protected int[] mipUMaskFixed = new int[MAX_MIP_LEVELS];
   protected int[] mipVMaskFixed = new int[MAX_MIP_LEVELS];
   protected int[] mipUShift = new int[MAX_MIP_LEVELS];
   protected int[] mipVShift = new int[MAX_MIP_LEVELS];

   public int allocate(int width, int height) {
      this.widthLog2 = MatrixUtils.ceilLog2(width);
      this.heightLog2 = MatrixUtils.ceilLog2(height);
      this.uMaskFixed = (1 << this.widthLog2) - 1 << FIXED_SHIFT;
      this.vMaskFixed = (1 << this.heightLog2) - 1 << FIXED_SHIFT;
      this.uShift = FIXED_SHIFT;
      this.vShift = FIXED_SHIFT - this.widthLog2;

      for (int i = 0; i < MAX_MIP_LEVELS; i++) {
         this.mipLevels[i] = 0;
      }

      this.mipOffsets[0] = 0;
      this.mipWidthLog2[0] = this.widthLog2;
      this.mipHeightLog2[0] = this.heightLog2;
      this.mipUMaskFixed[0] = this.uMaskFixed;
      this.mipVMaskFixed[0] = this.vMaskFixed;
      this.mipUShift[0] = this.uShift;
      this.mipVShift[0] = this.vShift;
      int totalPixels = 1 << this.widthLog2 + this.heightLog2;
      this.pixels = new int[totalPixels];

      while (totalPixels-- > 0) {
         this.pixels[totalPixels] = COLOR_TRANSPARENT;
      }

      this.width = width;
      this.height = height;
      return 1;
   }

   public final int[] getPixels() {
      return this.pixels;
   }

   public final int getWidth() {
      return this.width;
   }

   public final int getHeight() {
      return this.height;
   }

   public final int getWidthLog2() {
      return this.widthLog2;
   }

   public final int getHeightLog2() {
      return this.heightLog2;
   }

   public final int getUMaskFixed() {
      return this.uMaskFixed;
   }

   public final int getVMaskFixed() {
      return this.vMaskFixed;
   }

   public final int getUShift() {
      return this.uShift;
   }

   public final int getVShift() {
      return this.vShift;
   }

   public final boolean generateMipmaps() {
      if (this.pixels == null) {
         return false;
      } else {
         int totalMipPixels = 0;
         int wLog = this.widthLog2;

         for (int hLog = this.heightLog2; wLog >= 4 && hLog >= 4; hLog--) {
            totalMipPixels += 1 << wLog + hLog;
            wLog--;
         }

         int[] mipBuffer = new int[totalMipPixels];
         int basePixels = 1 << this.widthLog2 + this.heightLog2;
         System.arraycopy(this.pixels, 0, mipBuffer, 0, basePixels);
         int level = 1;
         wLog = this.widthLog2 - 1;

         for (int hLog = this.heightLog2 - 1; wLog >= 4 && hLog >= 4; hLog--) {
            this.mipLevels[level] = level;
            this.mipOffsets[level] = basePixels;
            this.downsample2x2(mipBuffer, this.mipOffsets[level], this.mipOffsets[level - 1], 1 << wLog, 1 << hLog);
            this.mipWidthLog2[level] = wLog;
            this.mipHeightLog2[level] = hLog;
            this.mipUMaskFixed[level] = (1 << wLog) - 1 << FIXED_SHIFT;
            this.mipVMaskFixed[level] = (1 << hLog) - 1 << FIXED_SHIFT;
            this.mipUShift[level] = FIXED_SHIFT;
            this.mipVShift[level] = FIXED_SHIFT - wLog;
            level++;
            basePixels += 1 << wLog + hLog;
            wLog--;
         }

         while (level < MAX_MIP_LEVELS) {
            this.mipLevels[level] = this.mipLevels[level - 1];
            this.mipOffsets[level] = this.mipOffsets[level - 1];
            this.mipWidthLog2[level] = this.mipWidthLog2[level - 1];
            this.mipHeightLog2[level] = this.mipHeightLog2[level - 1];
            this.mipUMaskFixed[level] = this.mipUMaskFixed[level - 1];
            this.mipVMaskFixed[level] = this.mipVMaskFixed[level - 1];
            this.mipUShift[level] = this.mipUShift[level - 1];
            this.mipVShift[level] = this.mipVShift[level - 1];
            level++;
         }

         this.pixels = mipBuffer;
         return true;
      }
   }

   private void downsample2x2(int[] buffer, int dstOffset, int srcOffset, int dstWidth, int dstHeight) {
      int row0 = srcOffset;
      int row1 = srcOffset + dstWidth * 2;

      for (int y = 0; y < dstHeight; y++) {
         for (int x = 0; x < dstWidth; x++) {
            int transparentCount = 0;
            int rSum = 0;
            int gSum = 0;
            int bSum = 0;

            int c = buffer[row0];
            if (c == COLOR_TRANSPARENT) {
               transparentCount++;
            } else {
               bSum += c & 0xFF;
               gSum += c & 0xFF00;
               rSum += c & 0xFF0000;
            }

            c = buffer[row0 + 1];
            if (c == COLOR_TRANSPARENT) {
               transparentCount++;
            } else {
               bSum += c & 0xFF;
               gSum += c & 0xFF00;
               rSum += c & 0xFF0000;
            }

            c = buffer[row1];
            if (c == COLOR_TRANSPARENT) {
               transparentCount++;
            } else {
               bSum += c & 0xFF;
               gSum += c & 0xFF00;
               rSum += c & 0xFF0000;
            }

            c = buffer[row1 + 1];
            if (c == COLOR_TRANSPARENT) {
               transparentCount++;
            } else {
               bSum += c & 0xFF;
               gSum += c & 0xFF00;
               rSum += c & 0xFF0000;
            }

            if (transparentCount >= 2) {
               buffer[dstOffset] = COLOR_TRANSPARENT;
            } else {
               if (transparentCount == 0) {
                  bSum >>= 2;
                  gSum >>= 2;
                  rSum >>= 2;
               } else {
                  bSum /= 3;
                  gSum /= 3;
                  rSum /= 3;
               }

               buffer[dstOffset] = bSum & 0xFF | gSum & 0xFF00 | rSum & 0xFF0000 | 0xFF000000;
            }

            dstOffset++;
            row0 += 2;
            row1 += 2;
         }

         row0 += dstWidth * 2;
         row1 += dstWidth * 2;
      }
   }

   public final int selectMipLevel(int delta) {
      if (delta < 0) {
         delta = 0;
      } else if (delta >= MAX_MIP_LEVELS) {
         delta = MAX_MIP_LEVELS - 1;
      }

      return this.mipLevels[delta];
   }

   public final int getMipOffset(int level) {
      return this.mipOffsets[level];
   }

   public final int getMipWidthLog2(int level) {
      return this.mipWidthLog2[level];
   }

   public final int getMipHeightLog2(int level) {
      return this.mipHeightLog2[level];
   }

   public final int getMipUMask(int level) {
      return this.mipUMaskFixed[level];
   }

   public final int getMipVMask(int level) {
      return this.mipVMaskFixed[level];
   }

   public final int getMipUShift(int level) {
      return this.mipUShift[level];
   }

   public final int getMipVShift(int level) {
      return this.mipVShift[level];
   }
}
