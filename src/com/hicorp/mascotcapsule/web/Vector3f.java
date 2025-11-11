package com.hicorp.mascotcapsule.web;

public final class Vector3f {
   public float x;
   public float y;
   public float z;

   public Vector3f() {
   }

   public Vector3f(Vector3f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public Vector3f(float var1, float var2, float var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   public final void sub_5e(Vector3f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public final void sub_7a(float var1, float var2, float var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   public final boolean equals(Vector3f var1) {
      return this.x == var1.x && this.y == var1.y && this.z == var1.z;
   }

   public final boolean sub_ef(Vector3f var1) {
      return this.x != var1.y || this.y != var1.y || this.z != var1.z;
   }

   public final void negate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
   }

   public final void sub_184(Vector3f var1) {
      this.x = -var1.x;
      this.y = -var1.y;
      this.z = -var1.z;
   }

   public final void sub_1a2(Vector3f var1, Vector3f var2) {
      this.x = var1.x + var2.x;
      this.y = var1.y + var2.y;
      this.z = var1.z + var2.z;
   }

   public final void sub_1b7(Vector3f var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
      this.z = this.z + var1.z;
   }

   public final void setDifference(Vector3f var1, Vector3f var2) {
      this.x = var1.x - var2.x;
      this.y = var1.y - var2.y;
      this.z = var1.z - var2.z;
   }

   public final void substract(Vector3f var1) {
      this.x = this.x - var1.x;
      this.y = this.y - var1.y;
      this.z = this.z - var1.z;
   }

   public final void setScaled(Vector3f var1, float var2) {
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
   }

   public final void scale(float var1) {
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
   }

   public static final float dot(Vector3f var0, Vector3f var1) {
      return var0.x * var1.x + var0.y * var1.y + var0.z * var1.z;
   }

   public final float dot(Vector3f var1) {
      return this.x * var1.x + this.y * var1.y + this.z * var1.z;
   }

   public final void sub_399(Vector3f var1, Vector3f var2) {
      float var3 = var1.y * var2.z - var1.z * var2.y;
      float var4 = var1.z * var2.x - var1.x * var2.z;
      float var5 = var1.x * var2.y - var1.y * var2.x;
      this.x = var3;
      this.y = var4;
      this.z = var5;
   }

   public final void sub_3b7(Vector3f var1) {
      float var2 = this.y * var1.z - this.z * var1.y;
      float var3 = this.z * var1.x - this.x * var1.z;
      float var4 = this.x * var1.y - this.y * var1.x;
      this.x = var2;
      this.y = var3;
      this.z = var4;
   }

   public final float lenght() {
      return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
   }

   public final void normalize() {
      float var1 = 1.0F / this.lenght();
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
   }
}
