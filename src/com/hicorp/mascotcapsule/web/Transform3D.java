package com.hicorp.mascotcapsule.web;

public final class Transform3D {
   public float m00;
   public float m01;
   public float m02;
   public float m03;
   public float m10;
   public float m11;
   public float m12;
   public float m13;
   public float m20;
   public float m21;
   public float m22;
   public float m23;

   public Transform3D() {
   }

   public Transform3D(Transform3D other) {
      this.m00 = other.m00;
      this.m01 = other.m01;
      this.m02 = other.m02;
      this.m03 = other.m03;
      this.m10 = other.m10;
      this.m11 = other.m11;
      this.m12 = other.m12;
      this.m13 = other.m13;
      this.m20 = other.m20;
      this.m21 = other.m21;
      this.m22 = other.m22;
      this.m23 = other.m23;
   }

   public Transform3D(
      float m00, float m01, float m02, float m03,
      float m10, float m11, float m12, float m13,
      float m20, float m21, float m22, float m23
   ) {
      this.m00 = m00;
      this.m01 = m01;
      this.m02 = m02;
      this.m03 = m03;
      this.m10 = m10;
      this.m11 = m11;
      this.m12 = m12;
      this.m13 = m13;
      this.m20 = m20;
      this.m21 = m21;
      this.m22 = m22;
      this.m23 = m23;
   }

   public final void set(Transform3D other) {
      this.m00 = other.m00;
      this.m01 = other.m01;
      this.m02 = other.m02;
      this.m03 = other.m03;
      this.m10 = other.m10;
      this.m11 = other.m11;
      this.m12 = other.m12;
      this.m13 = other.m13;
      this.m20 = other.m20;
      this.m21 = other.m21;
      this.m22 = other.m22;
      this.m23 = other.m23;
   }

   public final void set(
      float m00, float m01, float m02, float m03,
      float m10, float m11, float m12, float m13,
      float m20, float m21, float m22, float m23
   ) {
      this.m00 = m00;
      this.m01 = m01;
      this.m02 = m02;
      this.m03 = m03;
      this.m10 = m10;
      this.m11 = m11;
      this.m12 = m12;
      this.m13 = m13;
      this.m20 = m20;
      this.m21 = m21;
      this.m22 = m22;
      this.m23 = m23;
   }

   public final void setRotation(
      float m00, float m01, float m02,
      float m10, float m11, float m12,
      float m20, float m21, float m22
   ) {
      this.m00 = m00;
      this.m01 = m01;
      this.m02 = m02;
      this.m10 = m10;
      this.m11 = m11;
      this.m12 = m12;
      this.m20 = m20;
      this.m21 = m21;
      this.m22 = m22;
   }

   public final void setIdentity() {
      this.set(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
   }

   public final void transform(Vector3f v) {
      float vx = v.x;
      float vy = v.y;
      float vz = v.z;
      v.x = this.m00 * vx + this.m01 * vy + this.m02 * vz + this.m03;
      v.y = this.m10 * vx + this.m11 * vy + this.m12 * vz + this.m13;
      v.z = this.m20 * vx + this.m21 * vy + this.m22 * vz + this.m23;
   }

   public final void transform(Vector3f src, Vector3f dst) {
      float vx = this.m00 * src.x + this.m01 * src.y + this.m02 * src.z + this.m03;
      float vy = this.m10 * src.x + this.m11 * src.y + this.m12 * src.z + this.m13;
      float vz = this.m20 * src.x + this.m21 * src.y + this.m22 * src.z + this.m23;
      dst.x = vx;
      dst.y = vy;
      dst.z = vz;
   }

   public final void transformVertices(Vector3f[] src, Vector3f[] dst, int offset, int count) {
      int end = offset + count;
      for (int i = offset; i < end; i++) {
         float vx = src[i].x;
         float vy = src[i].y;
         float vz = src[i].z;
         dst[i].x = this.m00 * vx + this.m01 * vy + this.m02 * vz + this.m03;
         dst[i].y = this.m10 * vx + this.m11 * vy + this.m12 * vz + this.m13;
         dst[i].z = this.m20 * vx + this.m21 * vy + this.m22 * vz + this.m23;
      }
   }

   public final void transformAndProjectPerspective(Vector3f[] vertices, int[] screenCoords, float[] depths, int offset, int count, float focalLength) {
      int end = offset + count;
      float sx0 = this.m00 * focalLength;
      float sx1 = this.m01 * focalLength;
      float sx2 = this.m02 * focalLength;
      float tx = this.m03 * focalLength;
      float sy0 = this.m10 * focalLength;
      float sy1 = this.m11 * focalLength;
      float sy2 = this.m12 * focalLength;
      float ty = this.m13 * focalLength;

      int coordIdx = offset * 2;
      for (int i = offset; i < end; i++) {
         float vx = vertices[i].x;
         float vy = vertices[i].y;
         float vz = vertices[i].z;
         float z = this.m20 * vx + this.m21 * vy + this.m22 * vz + this.m23;
         depths[i] = z;
         float invZ = 1.0F / z;
         screenCoords[coordIdx++] = (int)((sx0 * vx + sx1 * vy + sx2 * vz + tx) * invZ);
         screenCoords[coordIdx++] = (int)((sy0 * vx + sy1 * vy + sy2 * vz + ty) * invZ);
      }
   }

   public final void transformAndProjectOrthographic(Vector3f[] vertices, int[] screenCoords, float[] depths, int offset, int count, float scale) {
      int end = offset + count;
      int coordIdx = offset * 2;
      for (int i = offset; i < end; i++) {
         float vx = vertices[i].x;
         float vy = vertices[i].y;
         float vz = vertices[i].z;
         depths[i] = this.m20 * vx + this.m21 * vy + this.m22 * vz + this.m23;
         screenCoords[coordIdx++] = (int)((this.m00 * vx + this.m01 * vy + this.m02 * vz + this.m03) * scale);
         screenCoords[coordIdx++] = (int)((this.m10 * vx + this.m11 * vy + this.m12 * vz + this.m13) * scale);
      }
   }

   public final void rotateVector(Vector3f src, Vector3f dst) {
      float vx = this.m00 * src.x + this.m01 * src.y + this.m02 * src.z;
      float vy = this.m10 * src.x + this.m11 * src.y + this.m12 * src.z;
      float vz = this.m20 * src.x + this.m21 * src.y + this.m22 * src.z;
      dst.x = vx;
      dst.y = vy;
      dst.z = vz;
   }

   public final void multiply(Transform3D a, Transform3D b) {
      float r00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20;
      float r01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21;
      float r02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22;
      float r03 = a.m00 * b.m03 + a.m01 * b.m13 + a.m02 * b.m23 + a.m03;

      float r10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20;
      float r11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21;
      float r12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22;
      float r13 = a.m10 * b.m03 + a.m11 * b.m13 + a.m12 * b.m23 + a.m13;

      float r20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20;
      float r21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21;
      float r22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22;
      float r23 = a.m20 * b.m03 + a.m21 * b.m13 + a.m22 * b.m23 + a.m23;

      this.m00 = r00;
      this.m01 = r01;
      this.m02 = r02;
      this.m03 = r03;
      this.m10 = r10;
      this.m11 = r11;
      this.m12 = r12;
      this.m13 = r13;
      this.m20 = r20;
      this.m21 = r21;
      this.m22 = r22;
      this.m23 = r23;
   }

   public final void multiply(Transform3D other) {
      float r00 = this.m00 * other.m00 + this.m01 * other.m10 + this.m02 * other.m20;
      float r01 = this.m00 * other.m01 + this.m01 * other.m11 + this.m02 * other.m21;
      float r02 = this.m00 * other.m02 + this.m01 * other.m12 + this.m02 * other.m22;
      float r03 = this.m00 * other.m03 + this.m01 * other.m13 + this.m02 * other.m23 + this.m03;

      float r10 = this.m10 * other.m00 + this.m11 * other.m10 + this.m12 * other.m20;
      float r11 = this.m10 * other.m01 + this.m11 * other.m11 + this.m12 * other.m21;
      float r12 = this.m10 * other.m02 + this.m11 * other.m12 + this.m12 * other.m22;
      float r13 = this.m10 * other.m03 + this.m11 * other.m13 + this.m12 * other.m23 + this.m13;

      float r20 = this.m20 * other.m00 + this.m21 * other.m10 + this.m22 * other.m20;
      float r21 = this.m20 * other.m01 + this.m21 * other.m11 + this.m22 * other.m21;
      float r22 = this.m20 * other.m02 + this.m21 * other.m12 + this.m22 * other.m22;
      float r23 = this.m20 * other.m03 + this.m21 * other.m13 + this.m22 * other.m23 + this.m23;

      this.m00 = r00;
      this.m01 = r01;
      this.m02 = r02;
      this.m03 = r03;
      this.m10 = r10;
      this.m11 = r11;
      this.m12 = r12;
      this.m13 = r13;
      this.m20 = r20;
      this.m21 = r21;
      this.m22 = r22;
      this.m23 = r23;
   }

   public final void multiplyRotation(Transform3D a, Transform3D b) {
      float r00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20;
      float r01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21;
      float r02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22;

      float r10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20;
      float r11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21;
      float r12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22;

      float r20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20;
      float r21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21;
      float r22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22;

      this.m00 = r00;
      this.m01 = r01;
      this.m02 = r02;
      this.m10 = r10;
      this.m11 = r11;
      this.m12 = r12;
      this.m20 = r20;
      this.m21 = r21;
      this.m22 = r22;
   }

   public final void multiplyRotation(Transform3D other) {
      float r00 = this.m00 * other.m00 + this.m01 * other.m10 + this.m02 * other.m20;
      float r01 = this.m00 * other.m01 + this.m01 * other.m11 + this.m02 * other.m21;
      float r02 = this.m00 * other.m02 + this.m01 * other.m12 + this.m02 * other.m22;

      float r10 = this.m10 * other.m00 + this.m11 * other.m10 + this.m12 * other.m20;
      float r11 = this.m10 * other.m01 + this.m11 * other.m11 + this.m12 * other.m21;
      float r12 = this.m10 * other.m02 + this.m11 * other.m12 + this.m12 * other.m22;

      float r20 = this.m20 * other.m00 + this.m21 * other.m10 + this.m22 * other.m20;
      float r21 = this.m20 * other.m01 + this.m21 * other.m11 + this.m22 * other.m21;
      float r22 = this.m20 * other.m02 + this.m21 * other.m12 + this.m22 * other.m22;

      this.m00 = r00;
      this.m01 = r01;
      this.m02 = r02;
      this.m10 = r10;
      this.m11 = r11;
      this.m12 = r12;
      this.m20 = r20;
      this.m21 = r21;
      this.m22 = r22;
   }

   public final void invert() {
      float tx = this.m03;
      float ty = this.m13;
      float tz = this.m23;
      this.transposeRotation();
      this.m03 = -(this.m00 * tx + this.m01 * ty + this.m02 * tz);
      this.m13 = -(this.m10 * tx + this.m11 * ty + this.m12 * tz);
      this.m23 = -(this.m20 * tx + this.m21 * ty + this.m22 * tz);
   }

   public final void invert(Transform3D src) {
      float tx = src.m03;
      float ty = src.m13;
      float tz = src.m23;
      this.setRotation(src.m00, src.m10, src.m20, src.m01, src.m11, src.m21, src.m02, src.m12, src.m22);
      this.m03 = -(this.m00 * tx + this.m01 * ty + this.m02 * tz);
      this.m13 = -(this.m10 * tx + this.m11 * ty + this.m12 * tz);
      this.m23 = -(this.m20 * tx + this.m21 * ty + this.m22 * tz);
   }

   public final void transposeRotation() {
      this.setRotation(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22);
   }

   public final void normalizeColumns() {
      float c0x = this.m00;
      float c0y = this.m10;
      float c0z = this.m20;
      float invLen0 = 1.0F / (float)Math.sqrt(c0x * c0x + c0y * c0y + c0z * c0z);
      this.m00 = c0x * invLen0;
      this.m10 = c0y * invLen0;
      this.m20 = c0z * invLen0;

      float c1x = this.m01;
      float c1y = this.m11;
      float c1z = this.m21;
      float invLen1 = 1.0F / (float)Math.sqrt(c1x * c1x + c1y * c1y + c1z * c1z);
      this.m01 = c1x * invLen1;
      this.m11 = c1y * invLen1;
      this.m21 = c1z * invLen1;

      float c2x = this.m02;
      float c2y = this.m12;
      float c2z = this.m22;
      float invLen2 = 1.0F / (float)Math.sqrt(c2x * c2x + c2y * c2y + c2z * c2z);
      this.m02 = c2x * invLen2;
      this.m12 = c2y * invLen2;
      this.m22 = c2z * invLen2;
   }
}
