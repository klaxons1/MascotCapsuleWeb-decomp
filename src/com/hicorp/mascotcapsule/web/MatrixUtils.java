package com.hicorp.mascotcapsule.web;

public final class MatrixUtils {
   public static final double PI = Math.PI;
   public static final Vector3f UNIT_X = new Vector3f(1.0F, 0.0F, 0.0F);
   public static final Vector3f UNIT_Y = new Vector3f(0.0F, 1.0F, 0.0F);
   private static final int[] CLZ_NIBBLE_TABLE = new int[]{4, 3, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};

   public static void setScale(float scale, Transform3D out) {
      out.m00 = out.m11 = out.m22 = scale;
      out.m01 = out.m02 = out.m10 = out.m12 = out.m20 = out.m21 = 0.0F;
   }

   public static void setRotationX(float radians, Transform3D out) {
      float cos = (float)Math.cos(radians);
      float sin = (float)Math.sin(radians);
      out.m00 = 1.0F;
      out.m01 = 0.0F;
      out.m02 = 0.0F;
      out.m10 = 0.0F;
      out.m11 = cos;
      out.m12 = -sin;
      out.m20 = 0.0F;
      out.m21 = sin;
      out.m22 = cos;
   }

   public static void setRotationY(float radians, Transform3D out) {
      float cos = (float)Math.cos(radians);
      float sin = (float)Math.sin(radians);
      out.m00 = cos;
      out.m01 = 0.0F;
      out.m02 = sin;
      out.m10 = 0.0F;
      out.m11 = 1.0F;
      out.m12 = 0.0F;
      out.m20 = -sin;
      out.m21 = 0.0F;
      out.m22 = cos;
   }

   public static void setRotationZ(float radians, Transform3D out) {
      float cos = (float)Math.cos(radians);
      float sin = (float)Math.sin(radians);
      out.m00 = cos;
      out.m01 = -sin;
      out.m02 = 0.0F;
      out.m10 = sin;
      out.m11 = cos;
      out.m12 = 0.0F;
      out.m20 = 0.0F;
      out.m21 = 0.0F;
      out.m22 = 1.0F;
   }

   public static void setRotationAxis(Vector3f axis, float radians, Transform3D out) {
      float cos = (float)Math.cos(radians);
      float sin = (float)Math.sin(radians);
      float ax = axis.x;
      float ay = axis.y;
      float az = axis.z;
      float xx = ax * ax;
      float yy = ay * ay;
      float zz = az * az;
      float xy = ax * ay;
      float xz = ax * az;
      float xs = ax * sin;
      float yz = ay * az;
      float ys = ay * sin;
      float zs = az * sin;
      float oneMinusCos = 1.0F - cos;
      out.m00 = xx * oneMinusCos + cos;
      out.m01 = xy * oneMinusCos - zs;
      out.m02 = xz * oneMinusCos + ys;
      out.m10 = xy * oneMinusCos + zs;
      out.m11 = yy * oneMinusCos + cos;
      out.m12 = yz * oneMinusCos - xs;
      out.m20 = xz * oneMinusCos - ys;
      out.m21 = yz * oneMinusCos + xs;
      out.m22 = zz * oneMinusCos + cos;
   }

   public static final void setLookAt(Vector3f eye, Vector3f target, Transform3D out) {
      Vector3f rotAxis = new Vector3f();
      float angle = angleBetween(eye, target);
      if (angle < 0.001F) {
         out.setIdentity();
      } else {
         if (angle > 3.1405928F) {
            if (Math.abs(1.0 - Math.abs(eye.x)) < 0.001) {
               rotAxis.cross(eye, UNIT_Y);
            } else {
               rotAxis.cross(eye, UNIT_X);
            }
         } else {
            rotAxis.cross(eye, target);
         }

         rotAxis.normalize();
         setRotationAxis(rotAxis, angle, out);
      }
   }

   public static final Transform3D createLookAt(Vector3f eye, Vector3f target) {
      return createLookAt(eye, target, 0.0F);
   }

   public static final Transform3D createLookAt(Vector3f eye, Vector3f target, float roll) {
      Debug.assertTrue(eye != target);
      Vector3f forward = new Vector3f();
      forward.setDifference(target, eye);
      forward.normalize();
      float fx = forward.x;
      float fy = forward.y;
      float fz = forward.z;
      float xzLenSq = fx * fx + fz * fz;
      float r00;
      float r01;
      float r02;
      float r10;
      float r11;
      float r12;
      float r20;
      float r21;
      float r22;
      if (xzLenSq > 1.0E-5F) {
         float invXzLen = 1.0F / (float)Math.sqrt(xzLenSq);
         r00 = -invXzLen * fz;
         r01 = invXzLen * fx * fy;
         r02 = fx;
         r10 = 0.0F;
         r11 = -invXzLen * xzLenSq;
         r12 = fy;
         r20 = invXzLen * fx;
         r21 = invXzLen * fy * fz;
         r22 = fz;
      } else {
         r00 = -1.0F;
         r01 = 0.0F;
         r02 = fx;
         r10 = 0.0F;
         r11 = 0.0F;
         r12 = fy;
         r20 = 0.0F;
         r21 = fy < 0.0F ? -1.0F : 1.0F;
         r22 = fz;
      }

      Transform3D result = new Transform3D();
      result.setRotation(r00, r01, r02, r10, r11, r12, r20, r21, r22);
      if (roll != 0.0F) {
         Transform3D rollMat = new Transform3D();
         setRotationAxis(forward, roll, rollMat);
         result.multiply(rollMat, result);
      }

      result.m03 = eye.x;
      result.m13 = eye.y;
      result.m23 = eye.z;
      return result;
   }

   public static final float toRadians(float degrees) {
      return (float)(degrees * Math.PI / 180.0);
   }

   private static final float clampUnit(float val) {
      if (val > 1.0F) {
         return 1.0F;
      } else if (val < -1.0F) {
         return -1.0F;
      }
      return val;
   }

   private static final float angleBetween(Vector3f a, Vector3f b) {
      return (float)Math.acos(clampUnit(a.dot(b)));
   }

   public static int countLeadingZeros(int val) {
      if ((val & 0xFFFF0000) != 0) {
         if ((val & 0xFF000000) != 0) {
            return (val & 0xF0000000) != 0 ? CLZ_NIBBLE_TABLE[val >>> 28] : CLZ_NIBBLE_TABLE[val >>> 24] + 4;
         } else {
            return (val & 0x00F00000) != 0 ? CLZ_NIBBLE_TABLE[val >>> 20] + 8 : CLZ_NIBBLE_TABLE[val >>> 16] + 12;
         }
      } else if ((val & 0x0000FF00) != 0) {
         return (val & 0x0000F000) != 0 ? CLZ_NIBBLE_TABLE[val >>> 12] + 16 : CLZ_NIBBLE_TABLE[val >>> 8] + 20;
      } else {
         return (val & 0x000000F0) != 0 ? CLZ_NIBBLE_TABLE[val >>> 4] + 24 : CLZ_NIBBLE_TABLE[val] + 28;
      }
   }

   public static int ceilLog2(int val) {
      if (val != 0) {
         int highestBit = 31 - countLeadingZeros(val);
         return val == 1 << highestBit ? highestBit : highestBit + 1;
      } else {
         return -1;
      }
   }

   public static int countLeadingZerosLong(long val) {
      return (val & 0xFFFFFFFF00000000L) != 0L ? countLeadingZeros((int)(val >>> 32)) : countLeadingZeros((int)val) + 32;
   }

   public static int ceilLog2Long(long val) {
      if (val != 0L) {
         int highestBit = 63 - countLeadingZerosLong(val);
         return val == 1L << highestBit ? highestBit : highestBit + 1;
      } else {
         return -1;
      }
   }
}
