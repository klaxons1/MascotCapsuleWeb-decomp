package com.hicorp.mascotcapsule.web;

public final class BoundingBox {
   public int minX;
   public int minY;
   public int maxX;
   public int maxY;

   public BoundingBox() {
      this.resetEmpty();
   }

   public BoundingBox(int minX, int minY, int maxX, int maxY) {
      this.minX = minX;
      this.minY = minY;
      this.maxX = maxX;
      this.maxY = maxY;
   }

   public void setBounds(int minX, int minY, int maxX, int maxY) {
      this.minX = minX;
      this.minY = minY;
      this.maxX = maxX;
      this.maxY = maxY;
   }

   public void setBounds(BoundingBox other) {
      this.minX = other.minX;
      this.minY = other.minY;
      this.maxX = other.maxX;
      this.maxY = other.maxY;
   }

   public void resetEmpty() {
      this.minX = Integer.MAX_VALUE;
      this.minY = Integer.MAX_VALUE;
      this.maxX = Integer.MIN_VALUE;
      this.maxY = Integer.MIN_VALUE;
   }

   public void resetInfinite() {
      this.minX = Integer.MIN_VALUE;
      this.minY = Integer.MIN_VALUE;
      this.maxX = Integer.MAX_VALUE;
      this.maxY = Integer.MAX_VALUE;
   }

   public boolean isValid() {
      return this.minX < this.maxX && this.minY < this.maxY;
   }

   public void intersect(int left, int top, int right, int bottom) {
      if (this.isValid()) {
         if (this.minX < left) {
            this.minX = left;
         }

         if (this.maxX > right) {
            this.maxX = right;
         }

         if (this.minY < top) {
            this.minY = top;
         }

         if (this.maxY > bottom) {
            this.maxY = bottom;
         }
      }
   }

   public void intersect(BoundingBox other) {
      if (this.isValid()) {
         if (this.minX < other.minX) {
            this.minX = other.minX;
         }

         if (this.maxX > other.maxX) {
            this.maxX = other.maxX;
         }

         if (this.minY < other.minY) {
            this.minY = other.minY;
         }

         if (this.maxY > other.maxY) {
            this.maxY = other.maxY;
         }
      }
   }

   public void unionPoint(int x, int y) {
      if (x < this.minX) {
         this.minX = x;
      }

      if (x > this.maxX) {
         this.maxX = x;
      }

      if (y < this.minY) {
         this.minY = y;
      }

      if (y > this.maxY) {
         this.maxY = y;
      }
   }

   public void union(BoundingBox other) {
      if (other != null) {
         if (other.minX < this.minX) {
            this.minX = other.minX;
         }

         if (other.maxX > this.maxX) {
            this.maxX = other.maxX;
         }

         if (other.minY < this.minY) {
            this.minY = other.minY;
         }

         if (other.maxY > this.maxY) {
            this.maxY = other.maxY;
         }
      }
   }
}
