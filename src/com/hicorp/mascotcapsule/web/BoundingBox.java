package com.hicorp.mascotcapsule.web;

public final class BoundingBox {
   public int minX;
   public int minY;
   public int maxX;
   public int maxY;

   public BoundingBox() {
      this.resetEmpty();
   }

   public BoundingBox(int var1, int var2, int var3, int var4) {
      this.minX = var1;
      this.minY = var2;
      this.maxX = var3;
      this.maxY = var4;
   }

   public void setBounds(int var1, int var2, int var3, int var4) {
      this.minX = var1;
      this.minY = var2;
      this.maxX = var3;
      this.maxY = var4;
   }

   public void setBounds(BoundingBox var1) {
      this.minX = var1.minX;
      this.minY = var1.minY;
      this.maxX = var1.maxX;
      this.maxY = var1.maxY;
   }

   public void resetEmpty() {
      this.minX = this.minY = Integer.MAX_VALUE;
      this.maxX = this.maxY = Integer.MIN_VALUE;
   }

   public void resetInfinite() {
      this.minX = this.minY = Integer.MIN_VALUE;
      this.maxX = this.maxY = Integer.MAX_VALUE;
   }

   public boolean isValid() {
      return this.minX < this.maxX && this.minY < this.maxY;
   }

   public void intersect(int var1, int var2, int var3, int var4) {
      if (this.isValid()) {
         if (this.minX < var1) {
            this.minX = var1;
         }

         if (this.maxX > var3) {
            this.maxX = var3;
         }

         if (this.minY < var2) {
            this.minY = var2;
         }

         if (this.maxY > var4) {
            this.maxY = var4;
         }
      }
   }

   public void intersect(BoundingBox var1) {
      if (this.isValid()) {
         if (this.minX < var1.minX) {
            this.minX = var1.minX;
         }

         if (this.maxX > var1.maxX) {
            this.maxX = var1.maxX;
         }

         if (this.minY < var1.minY) {
            this.minY = var1.minY;
         }

         if (this.maxY > var1.maxY) {
            this.maxY = var1.maxY;
         }
      }
   }

   public void unionPoint(int var1, int var2) {
      if (var1 < this.minX) {
         this.minX = var1;
      }

      if (var1 > this.maxX) {
         this.maxX = var1;
      }

      if (var2 < this.minY) {
         this.minY = var2;
      }

      if (var2 > this.maxY) {
         this.maxY = var2;
      }
   }

   public void union(BoundingBox var1) {
      if (var1 != null) {
         if (var1.minX < this.minX) {
            this.minX = var1.minX;
         }

         if (var1.maxX > this.maxX) {
            this.maxX = var1.maxX;
         }

         if (var1.minY < this.minY) {
            this.minY = var1.minY;
         }

         if (var1.maxY > this.maxY) {
            this.maxY = var1.maxY;
         }
      }
   }
}
