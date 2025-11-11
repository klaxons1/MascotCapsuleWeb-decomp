package com.hicorp.mascotcapsule.web;

public final class Class_3d2 {
   public float var_3f;
   public float var_93;
   public float var_d6;

   public Class_3d2() {
   }

   public Class_3d2(Class_3d2 var1) {
      this.var_3f = var1.var_3f;
      this.var_93 = var1.var_93;
      this.var_d6 = var1.var_d6;
   }

   public Class_3d2(float var1, float var2, float var3) {
      this.var_3f = var1;
      this.var_93 = var2;
      this.var_d6 = var3;
   }

   public final void sub_5e(Class_3d2 var1) {
      this.var_3f = var1.var_3f;
      this.var_93 = var1.var_93;
      this.var_d6 = var1.var_d6;
   }

   public final void sub_7a(float var1, float var2, float var3) {
      this.var_3f = var1;
      this.var_93 = var2;
      this.var_d6 = var3;
   }

   public final boolean sub_96(Class_3d2 var1) {
      return this.var_3f == var1.var_3f && this.var_93 == var1.var_93 && this.var_d6 == var1.var_d6;
   }

   public final boolean sub_ef(Class_3d2 var1) {
      return this.var_3f != var1.var_93 || this.var_93 != var1.var_93 || this.var_d6 != var1.var_d6;
   }

   public final void sub_133() {
      this.var_3f = -this.var_3f;
      this.var_93 = -this.var_93;
      this.var_d6 = -this.var_d6;
   }

   public final void sub_184(Class_3d2 var1) {
      this.var_3f = -var1.var_3f;
      this.var_93 = -var1.var_93;
      this.var_d6 = -var1.var_d6;
   }

   public final void sub_1a2(Class_3d2 var1, Class_3d2 var2) {
      this.var_3f = var1.var_3f + var2.var_3f;
      this.var_93 = var1.var_93 + var2.var_93;
      this.var_d6 = var1.var_d6 + var2.var_d6;
   }

   public final void sub_1b7(Class_3d2 var1) {
      this.var_3f = this.var_3f + var1.var_3f;
      this.var_93 = this.var_93 + var1.var_93;
      this.var_d6 = this.var_d6 + var1.var_d6;
   }

   public final void sub_1e0(Class_3d2 var1, Class_3d2 var2) {
      this.var_3f = var1.var_3f - var2.var_3f;
      this.var_93 = var1.var_93 - var2.var_93;
      this.var_d6 = var1.var_d6 - var2.var_d6;
   }

   public final void sub_239(Class_3d2 var1) {
      this.var_3f = this.var_3f - var1.var_3f;
      this.var_93 = this.var_93 - var1.var_93;
      this.var_d6 = this.var_d6 - var1.var_d6;
   }

   public final void sub_249(Class_3d2 var1, float var2) {
      this.var_3f = var1.var_3f * var2;
      this.var_93 = var1.var_93 * var2;
      this.var_d6 = var1.var_d6 * var2;
   }

   public final void sub_2a8(float var1) {
      this.var_3f *= var1;
      this.var_93 *= var1;
      this.var_d6 *= var1;
   }

   public static final float sub_2ed(Class_3d2 var0, Class_3d2 var1) {
      return var0.var_3f * var1.var_3f + var0.var_93 * var1.var_93 + var0.var_d6 * var1.var_d6;
   }

   public final float sub_34f(Class_3d2 var1) {
      return this.var_3f * var1.var_3f + this.var_93 * var1.var_93 + this.var_d6 * var1.var_d6;
   }

   public final void sub_399(Class_3d2 var1, Class_3d2 var2) {
      float var3 = var1.var_93 * var2.var_d6 - var1.var_d6 * var2.var_93;
      float var4 = var1.var_d6 * var2.var_3f - var1.var_3f * var2.var_d6;
      float var5 = var1.var_3f * var2.var_93 - var1.var_93 * var2.var_3f;
      this.var_3f = var3;
      this.var_93 = var4;
      this.var_d6 = var5;
   }

   public final void sub_3b7(Class_3d2 var1) {
      float var2 = this.var_93 * var1.var_d6 - this.var_d6 * var1.var_93;
      float var3 = this.var_d6 * var1.var_3f - this.var_3f * var1.var_d6;
      float var4 = this.var_3f * var1.var_93 - this.var_93 * var1.var_3f;
      this.var_3f = var2;
      this.var_93 = var3;
      this.var_d6 = var4;
   }

   public final float sub_419() {
      return (float)Math.sqrt(this.var_3f * this.var_3f + this.var_93 * this.var_93 + this.var_d6 * this.var_d6);
   }

   public final void sub_47c() {
      float var1 = 1.0F / this.sub_419();
      this.var_3f *= var1;
      this.var_93 *= var1;
      this.var_d6 *= var1;
   }
}
