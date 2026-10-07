package com.hicorp.mascotcapsule.web;

public final class Vector3f {
   public float x;
   public float y;
   public float z;

   public Vector3f() {
   }

   public Vector3f(Vector3f other) {
      this.x = other.x;
      this.y = other.y;
      this.z = other.z;
   }

   public Vector3f(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public final void set(Vector3f other) {
      this.x = other.x;
      this.y = other.y;
      this.z = other.z;
   }

   public final void set(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public final boolean equals(Vector3f other) {
      return this.x == other.x && this.y == other.y && this.z == other.z;
   }

   public final boolean notEquals(Vector3f other) {
      return this.x != other.x || this.y != other.y || this.z != other.z;
   }

   public final void negate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
   }

   public final void setNegative(Vector3f other) {
      this.x = -other.x;
      this.y = -other.y;
      this.z = -other.z;
   }

   public final void add(Vector3f v1, Vector3f v2) {
      this.x = v1.x + v2.x;
      this.y = v1.y + v2.y;
      this.z = v1.z + v2.z;
   }

   public final void add(Vector3f other) {
      this.x = this.x + other.x;
      this.y = this.y + other.y;
      this.z = this.z + other.z;
   }

   public final void setDifference(Vector3f v1, Vector3f v2) {
      this.x = v1.x - v2.x;
      this.y = v1.y - v2.y;
      this.z = v1.z - v2.z;
   }

   public final void subtract(Vector3f other) {
      this.x = this.x - other.x;
      this.y = this.y - other.y;
      this.z = this.z - other.z;
   }

   public final void setScaled(Vector3f other, float scale) {
      this.x = other.x * scale;
      this.y = other.y * scale;
      this.z = other.z * scale;
   }

   public final void scale(float scale) {
      this.x *= scale;
      this.y *= scale;
      this.z *= scale;
   }

   public static final float dot(Vector3f v1, Vector3f v2) {
      return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
   }

   public final float dot(Vector3f other) {
      return this.x * other.x + this.y * other.y + this.z * other.z;
   }

   public final void cross(Vector3f v1, Vector3f v2) {
      float cx = v1.y * v2.z - v1.z * v2.y;
      float cy = v1.z * v2.x - v1.x * v2.z;
      float cz = v1.x * v2.y - v1.y * v2.x;
      this.x = cx;
      this.y = cy;
      this.z = cz;
   }

   public final void cross(Vector3f other) {
      float cx = this.y * other.z - this.z * other.y;
      float cy = this.z * other.x - this.x * other.z;
      float cz = this.x * other.y - this.y * other.x;
      this.x = cx;
      this.y = cy;
      this.z = cz;
   }

   public final float length() {
      return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
   }

   public final void normalize() {
      float invLength = 1.0F / this.length();
      this.x *= invLength;
      this.y *= invLength;
      this.z *= invLength;
   }
}
