package com.hicorp.mascotcapsule.web;

public final class Class_517 {
   private static final int var_3c = -1;
   private static final int var_5a = 12;
   private static final int var_cb = 16;
   protected int[] var_f9 = null;
   protected int var_148 = 0;
   protected int var_19e = 0;
   protected int var_1e8 = 0;
   protected int var_238 = 0;
   protected int var_26c = 0;
   protected int var_2f0 = 0;
   protected int var_34d = 0;
   protected int var_398 = 0;
   protected int[] var_3de = new int[12];
   protected int[] var_413 = new int[12];
   protected int[] var_4b2 = new int[12];
   protected int[] var_4d0 = new int[12];
   protected int[] var_529 = new int[12];
   protected int[] var_53a = new int[12];
   protected int[] var_586 = new int[12];
   protected int[] var_5cc = new int[12];

   public int sub_38(int var1, int var2) {
      this.var_1e8 = MatrixUtils.sub_30a(var1);
      this.var_238 = MatrixUtils.sub_30a(var2);
      this.var_26c = (1 << this.var_1e8) - 1 << 16;
      this.var_2f0 = (1 << this.var_238) - 1 << 16;
      this.var_34d = 16;
      this.var_398 = 16 - this.var_1e8;

      for (int var3 = 0; var3 < 12; var3++) {
         this.var_3de[var3] = 0;
      }

      this.var_413[0] = 0;
      this.var_4b2[0] = this.var_1e8;
      this.var_4d0[0] = this.var_238;
      this.var_529[0] = this.var_26c;
      this.var_53a[0] = this.var_2f0;
      this.var_586[0] = this.var_34d;
      this.var_5cc[0] = this.var_398;
      int var4 = 1 << this.var_1e8 + this.var_238;
      this.var_f9 = new int[var4];

      while (var4-- > 0) {
         this.var_f9[var4] = -1;
      }

      this.var_148 = var1;
      this.var_19e = var2;
      return 1;
   }

   public final int[] sub_93() {
      return this.var_f9;
   }

   public final int sub_10a() {
      return this.var_148;
   }

   public final int sub_11a() {
      return this.var_19e;
   }

   public final int sub_143() {
      return this.var_1e8;
   }

   public final int sub_15d() {
      return this.var_238;
   }

   public final int sub_185() {
      return this.var_26c;
   }

   public final int sub_1a8() {
      return this.var_2f0;
   }

   public final int sub_1eb() {
      return this.var_34d;
   }

   public final int sub_21d() {
      return this.var_398;
   }

   public final boolean sub_281() {
      if (this.var_f9 == null) {
         return false;
      } else {
         int var1 = 0;
         int var2 = this.var_1e8;

         for (int var3 = this.var_238; var2 >= 4 && var3 >= 4; var3--) {
            var1 += 1 << var2 + var3;
            var2--;
         }

         int[] var4 = new int[var1];
         int var5 = 1 << this.var_1e8 + this.var_238;
         System.arraycopy(this.var_f9, 0, var4, 0, var5);
         int var6 = 1;
         var2 = this.var_1e8 - 1;

         for (int var8 = this.var_238 - 1; var2 >= 4 && var8 >= 4; var8--) {
            this.var_3de[var6] = var6;
            this.var_413[var6] = var5;
            this.sub_2a1(var4, this.var_413[var6], this.var_413[var6 - 1], 1 << var2, 1 << var8);
            this.var_4b2[var6] = var2;
            this.var_4d0[var6] = var8;
            this.var_529[var6] = (1 << var2) - 1 << 16;
            this.var_53a[var6] = (1 << var8) - 1 << 16;
            this.var_586[var6] = 16;
            this.var_5cc[var6] = 16 - var2;
            var6++;
            var5 += 1 << var2 + var8;
            var2--;
         }

         while (var6 < 12) {
            this.var_3de[var6] = this.var_3de[var6 - 1];
            this.var_413[var6] = this.var_413[var6 - 1];
            this.var_4b2[var6] = this.var_4b2[var6 - 1];
            this.var_4d0[var6] = this.var_4d0[var6 - 1];
            this.var_529[var6] = this.var_529[var6 - 1];
            this.var_53a[var6] = this.var_53a[var6 - 1];
            this.var_586[var6] = this.var_586[var6 - 1];
            this.var_5cc[var6] = this.var_5cc[var6 - 1];
            var6++;
         }

         this.var_f9 = var4;
         return true;
      }
   }

   private void sub_2a1(int[] var1, int var2, int var3, int var4, int var5) {
      int var6 = var3;
      int var7 = var3 + var4 * 2;

      for (int var8 = 0; var8 < var5; var8++) {
         for (int var9 = 0; var9 < var4; var9++) {
            int var10 = 0;
            int var11 = 0;
            int var12 = 0;
            int var13 = 0;
            int var14 = var1[var6];
            if (var14 == -1) {
               var10++;
            } else {
               var11 += var14 & 0xFF;
               var12 += var14 & 0xFF00;
               var13 += var14 & 0xFF0000;
            }

            var14 = var1[var6 + 1];
            if (var14 == -1) {
               var10++;
            } else {
               var11 += var14 & 0xFF;
               var12 += var14 & 0xFF00;
               var13 += var14 & 0xFF0000;
            }

            var14 = var1[var7];
            if (var14 == -1) {
               var10++;
            } else {
               var11 += var14 & 0xFF;
               var12 += var14 & 0xFF00;
               var13 += var14 & 0xFF0000;
            }

            var14 = var1[var7 + 1];
            if (var14 == -1) {
               var10++;
            } else {
               var11 += var14 & 0xFF;
               var12 += var14 & 0xFF00;
               var13 += var14 & 0xFF0000;
            }

            if (var10 >= 2) {
               var1[var2] = -1;
            } else {
               if (var10 == 0) {
                  var11 >>= 2;
                  var12 >>= 2;
                  var13 >>= 2;
               } else {
                  var11 /= 3;
                  var12 /= 3;
                  var13 /= 3;
               }

               var1[var2] = var11 & 0xFF | var12 & 0xFF00 | var13 & 0xFF0000 | 0xFF000000;
            }

            var2++;
            var6 += 2;
            var7 += 2;
         }

         var6 += var4 * 2;
         var7 += var4 * 2;
      }
   }

   public final int sub_2cf(int var1) {
      if (var1 < 0) {
         var1 = 0;
      } else if (var1 >= 12) {
         var1 = 11;
      }

      return this.var_3de[var1];
   }

   public final int sub_327(int var1) {
      return this.var_413[var1];
   }

   public final int sub_36e(int var1) {
      return this.var_4b2[var1];
   }

   public final int sub_396(int var1) {
      return this.var_4d0[var1];
   }

   public final int sub_3df(int var1) {
      return this.var_529[var1];
   }

   public final int sub_418(int var1) {
      return this.var_53a[var1];
   }

   public final int sub_42b(int var1) {
      return this.var_586[var1];
   }

   public final int sub_467(int var1) {
      return this.var_5cc[var1];
   }
}
