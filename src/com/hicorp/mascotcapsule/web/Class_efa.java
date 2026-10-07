package com.hicorp.mascotcapsule.web;

public final class Class_efa extends Class_eda {
   private final Config var_29;

   public Class_efa(Config var1) {
      super(var1);
      this.var_29 = var1;
   }

   public void drawSpan() {
      int[] var1 = Config.getPixelBuffer(this.var_29);

      for (int var2 = Config.getClipBottom(this.var_29); super.y < super.yEnd; super.xRightFixed = super.xRightFixed + super.dxRightFixed) {
         int var3 = (super.xLeftFixed >> 16) + super.scanlineOffset;

         for (int var4 = (super.xRightFixed >> 16) + super.scanlineOffset; var3 < var4; var3++) {
            var1[var3] = var2;
         }

         super.y++;
         super.scanlineOffset = super.scanlineOffset + Config.getStride(this.var_29);
         super.xLeftFixed = super.xLeftFixed + super.dxLeftFixed;
      }
   }
}
