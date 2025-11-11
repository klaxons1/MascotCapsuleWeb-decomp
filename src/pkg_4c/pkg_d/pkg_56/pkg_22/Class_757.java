package pkg_4c.pkg_d.pkg_56.pkg_22;

public final class Class_757 {
   public float var_13;
   public float var_ad;
   public float var_c2;
   public float var_d7;
   public float var_e7;
   public float var_138;
   public float var_191;
   public float var_1ce;
   public float var_211;
   public float var_263;
   public float var_27d;
   public float var_2ce;

   public Class_757() {
   }

   public Class_757(Class_757 var1) {
      this.var_13 = var1.var_13;
      this.var_ad = var1.var_ad;
      this.var_c2 = var1.var_c2;
      this.var_d7 = var1.var_d7;
      this.var_e7 = var1.var_e7;
      this.var_138 = var1.var_138;
      this.var_191 = var1.var_191;
      this.var_1ce = var1.var_1ce;
      this.var_211 = var1.var_211;
      this.var_263 = var1.var_263;
      this.var_27d = var1.var_27d;
      this.var_2ce = var1.var_2ce;
   }

   public Class_757(
      float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10, float var11, float var12
   ) {
      this.var_13 = var1;
      this.var_ad = var2;
      this.var_c2 = var3;
      this.var_d7 = var4;
      this.var_e7 = var5;
      this.var_138 = var6;
      this.var_191 = var7;
      this.var_1ce = var8;
      this.var_211 = var9;
      this.var_263 = var10;
      this.var_27d = var11;
      this.var_2ce = var12;
   }

   public final void sub_37(Class_757 var1) {
      this.var_13 = var1.var_13;
      this.var_ad = var1.var_ad;
      this.var_c2 = var1.var_c2;
      this.var_d7 = var1.var_d7;
      this.var_e7 = var1.var_e7;
      this.var_138 = var1.var_138;
      this.var_191 = var1.var_191;
      this.var_1ce = var1.var_1ce;
      this.var_211 = var1.var_211;
      this.var_263 = var1.var_263;
      this.var_27d = var1.var_27d;
      this.var_2ce = var1.var_2ce;
   }

   public final void sub_92(
      float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10, float var11, float var12
   ) {
      this.var_13 = var1;
      this.var_ad = var2;
      this.var_c2 = var3;
      this.var_d7 = var4;
      this.var_e7 = var5;
      this.var_138 = var6;
      this.var_191 = var7;
      this.var_1ce = var8;
      this.var_211 = var9;
      this.var_263 = var10;
      this.var_27d = var11;
      this.var_2ce = var12;
   }

   public final void sub_d5(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      this.var_13 = var1;
      this.var_ad = var2;
      this.var_c2 = var3;
      this.var_e7 = var4;
      this.var_138 = var5;
      this.var_191 = var6;
      this.var_211 = var7;
      this.var_263 = var8;
      this.var_27d = var9;
   }

   public final void sub_139() {
      this.sub_92(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
   }

   public final void sub_160(Class_3d2 var1) {
      float var2 = var1.var_3f;
      float var3 = var1.var_93;
      float var4 = var1.var_d6;
      this.sub_d5(
         this.var_13 * var2,
         this.var_ad * var3,
         this.var_c2 * var4,
         this.var_e7 * var2,
         this.var_138 * var3,
         this.var_191 * var4,
         this.var_211 * var2,
         this.var_263 * var3,
         this.var_27d * var4
      );
   }

   public final void sub_199(Class_3d2 var1, Class_3d2 var2) {
      float var3 = this.var_13 * var1.var_3f + this.var_ad * var1.var_93 + this.var_c2 * var1.var_d6 + this.var_d7;
      float var4 = this.var_e7 * var1.var_3f + this.var_138 * var1.var_93 + this.var_191 * var1.var_d6 + this.var_1ce;
      float var5 = this.var_211 * var1.var_3f + this.var_263 * var1.var_93 + this.var_27d * var1.var_d6 + this.var_2ce;
      var2.var_3f = var3;
      var2.var_93 = var4;
      var2.var_d6 = var5;
   }

   public final void sub_1d7(Class_3d2[] var1, Class_3d2[] var2, int var3, int var4) {
      int var5 = var3 + var4;

      for (int var6 = var3; var6 < var5; var6++) {
         float var7 = var1[var6].var_3f;
         float var8 = var1[var6].var_93;
         float var9 = var1[var6].var_d6;
         float var10 = this.var_13 * var7 + this.var_ad * var8 + this.var_c2 * var9 + this.var_d7;
         float var11 = this.var_e7 * var7 + this.var_138 * var8 + this.var_191 * var9 + this.var_1ce;
         float var12 = this.var_211 * var7 + this.var_263 * var8 + this.var_27d * var9 + this.var_2ce;
         var2[var6].var_3f = var10;
         var2[var6].var_93 = var11;
         var2[var6].var_d6 = var12;
      }
   }

   public final void sub_212(Class_3d2[] var1, int[] var2, float[] var3, int var4, int var5, float var6) {
      int var9 = var4 + var5;
      float var10 = this.var_13 * var6;
      float var11 = this.var_ad * var6;
      float var12 = this.var_c2 * var6;
      float var13 = this.var_d7 * var6;
      float var14 = this.var_e7 * var6;
      float var15 = this.var_138 * var6;
      float var16 = this.var_191 * var6;
      float var17 = this.var_1ce * var6;
      int var7 = var4;

      for (int var8 = var4 * 2; var7 < var9; var8 += 2) {
         float var18 = var1[var7].var_3f;
         float var19 = var1[var7].var_93;
         float var20 = var1[var7].var_d6;
         var2[var8 + 0] = (int)(var10 * var18 + var11 * var19 + var12 * var20 + var13);
         var2[var8 + 1] = (int)(var14 * var18 + var15 * var19 + var16 * var20 + var17);
         var3[var7] = this.var_211 * var18 + this.var_263 * var19 + this.var_27d * var20 + this.var_2ce;
         var7++;
      }
   }

   public final void sub_249(Class_3d2[] var1, int[] var2, float[] var3, int var4, int var5, float var6) {
      int var9 = var4 + var5;
      int var7 = var4;

      for (int var8 = var4 * 2; var7 < var9; var8 += 2) {
         float var11 = var1[var7].var_3f;
         float var12 = var1[var7].var_93;
         float var13 = var1[var7].var_d6;
         var3[var7] = this.var_211 * var11 + this.var_263 * var12 + this.var_27d * var13 + this.var_2ce;
         float var10 = var6 / var3[var7];
         var2[var8 + 0] = (int)((this.var_13 * var11 + this.var_ad * var12 + this.var_c2 * var13 + this.var_d7) * var10);
         var2[var8 + 1] = (int)((this.var_e7 * var11 + this.var_138 * var12 + this.var_191 * var13 + this.var_1ce) * var10);
         var7++;
      }
   }

   public final void sub_289(Class_3d2 var1, Class_3d2 var2) {
      float var3 = this.var_13 * var1.var_3f + this.var_ad * var1.var_93 + this.var_c2 * var1.var_d6;
      float var4 = this.var_e7 * var1.var_3f + this.var_138 * var1.var_93 + this.var_191 * var1.var_d6;
      float var5 = this.var_211 * var1.var_3f + this.var_263 * var1.var_93 + this.var_27d * var1.var_d6;
      var2.var_3f = var3;
      var2.var_93 = var4;
      var2.var_d6 = var5;
   }

   public final void sub_2b3(Class_757 var1, Class_757 var2) {
      float var3 = var1.var_13 * var2.var_13 + var1.var_ad * var2.var_e7 + var1.var_c2 * var2.var_211;
      float var4 = var1.var_13 * var2.var_ad + var1.var_ad * var2.var_138 + var1.var_c2 * var2.var_263;
      float var5 = var1.var_13 * var2.var_c2 + var1.var_ad * var2.var_191 + var1.var_c2 * var2.var_27d;
      float var6 = var1.var_13 * var2.var_d7 + var1.var_ad * var2.var_1ce + var1.var_c2 * var2.var_2ce + var1.var_d7;
      float var7 = var1.var_e7 * var2.var_13 + var1.var_138 * var2.var_e7 + var1.var_191 * var2.var_211;
      float var8 = var1.var_e7 * var2.var_ad + var1.var_138 * var2.var_138 + var1.var_191 * var2.var_263;
      float var9 = var1.var_e7 * var2.var_c2 + var1.var_138 * var2.var_191 + var1.var_191 * var2.var_27d;
      float var10 = var1.var_e7 * var2.var_d7 + var1.var_138 * var2.var_1ce + var1.var_191 * var2.var_2ce + var1.var_1ce;
      float var11 = var1.var_211 * var2.var_13 + var1.var_263 * var2.var_e7 + var1.var_27d * var2.var_211;
      float var12 = var1.var_211 * var2.var_ad + var1.var_263 * var2.var_138 + var1.var_27d * var2.var_263;
      float var13 = var1.var_211 * var2.var_c2 + var1.var_263 * var2.var_191 + var1.var_27d * var2.var_27d;
      float var14 = var1.var_211 * var2.var_d7 + var1.var_263 * var2.var_1ce + var1.var_27d * var2.var_2ce + var1.var_2ce;
      this.var_13 = var3;
      this.var_ad = var4;
      this.var_c2 = var5;
      this.var_d7 = var6;
      this.var_e7 = var7;
      this.var_138 = var8;
      this.var_191 = var9;
      this.var_1ce = var10;
      this.var_211 = var11;
      this.var_263 = var12;
      this.var_27d = var13;
      this.var_2ce = var14;
   }

   public final void sub_311(Class_757 var1) {
      float var2 = this.var_13 * var1.var_13 + this.var_ad * var1.var_e7 + this.var_c2 * var1.var_211;
      float var3 = this.var_13 * var1.var_ad + this.var_ad * var1.var_138 + this.var_c2 * var1.var_263;
      float var4 = this.var_13 * var1.var_c2 + this.var_ad * var1.var_191 + this.var_c2 * var1.var_27d;
      float var5 = this.var_13 * var1.var_d7 + this.var_ad * var1.var_1ce + this.var_c2 * var1.var_2ce + this.var_d7;
      float var6 = this.var_e7 * var1.var_13 + this.var_138 * var1.var_e7 + this.var_191 * var1.var_211;
      float var7 = this.var_e7 * var1.var_ad + this.var_138 * var1.var_138 + this.var_191 * var1.var_263;
      float var8 = this.var_e7 * var1.var_c2 + this.var_138 * var1.var_191 + this.var_191 * var1.var_27d;
      float var9 = this.var_e7 * var1.var_d7 + this.var_138 * var1.var_1ce + this.var_191 * var1.var_2ce + this.var_1ce;
      float var10 = this.var_211 * var1.var_13 + this.var_263 * var1.var_e7 + this.var_27d * var1.var_211;
      float var11 = this.var_211 * var1.var_ad + this.var_263 * var1.var_138 + this.var_27d * var1.var_263;
      float var12 = this.var_211 * var1.var_c2 + this.var_263 * var1.var_191 + this.var_27d * var1.var_27d;
      float var13 = this.var_211 * var1.var_d7 + this.var_263 * var1.var_1ce + this.var_27d * var1.var_2ce + this.var_2ce;
      this.var_13 = var2;
      this.var_ad = var3;
      this.var_c2 = var4;
      this.var_d7 = var5;
      this.var_e7 = var6;
      this.var_138 = var7;
      this.var_191 = var8;
      this.var_1ce = var9;
      this.var_211 = var10;
      this.var_263 = var11;
      this.var_27d = var12;
      this.var_2ce = var13;
   }

   public final void sub_339(Class_757 var1, Class_757 var2) {
      float var3 = var1.var_13 * var2.var_13 + var1.var_ad * var2.var_e7 + var1.var_c2 * var2.var_211;
      float var4 = var1.var_13 * var2.var_ad + var1.var_ad * var2.var_138 + var1.var_c2 * var2.var_263;
      float var5 = var1.var_13 * var2.var_c2 + var1.var_ad * var2.var_191 + var1.var_c2 * var2.var_27d;
      float var6 = var1.var_e7 * var2.var_13 + var1.var_138 * var2.var_e7 + var1.var_191 * var2.var_211;
      float var7 = var1.var_e7 * var2.var_ad + var1.var_138 * var2.var_138 + var1.var_191 * var2.var_263;
      float var8 = var1.var_e7 * var2.var_c2 + var1.var_138 * var2.var_191 + var1.var_191 * var2.var_27d;
      float var9 = var1.var_211 * var2.var_13 + var1.var_263 * var2.var_e7 + var1.var_27d * var2.var_211;
      float var10 = var1.var_211 * var2.var_ad + var1.var_263 * var2.var_138 + var1.var_27d * var2.var_263;
      float var11 = var1.var_211 * var2.var_c2 + var1.var_263 * var2.var_191 + var1.var_27d * var2.var_27d;
      this.var_13 = var3;
      this.var_ad = var4;
      this.var_c2 = var5;
      this.var_e7 = var6;
      this.var_138 = var7;
      this.var_191 = var8;
      this.var_211 = var9;
      this.var_263 = var10;
      this.var_27d = var11;
   }

   public final void sub_37e(Class_757 var1) {
      float var2 = this.var_13 * var1.var_13 + this.var_ad * var1.var_e7 + this.var_c2 * var1.var_211;
      float var3 = this.var_13 * var1.var_ad + this.var_ad * var1.var_138 + this.var_c2 * var1.var_263;
      float var4 = this.var_13 * var1.var_c2 + this.var_ad * var1.var_191 + this.var_c2 * var1.var_27d;
      float var5 = this.var_e7 * var1.var_13 + this.var_138 * var1.var_e7 + this.var_191 * var1.var_211;
      float var6 = this.var_e7 * var1.var_ad + this.var_138 * var1.var_138 + this.var_191 * var1.var_263;
      float var7 = this.var_e7 * var1.var_c2 + this.var_138 * var1.var_191 + this.var_191 * var1.var_27d;
      float var8 = this.var_211 * var1.var_13 + this.var_263 * var1.var_e7 + this.var_27d * var1.var_211;
      float var9 = this.var_211 * var1.var_ad + this.var_263 * var1.var_138 + this.var_27d * var1.var_263;
      float var10 = this.var_211 * var1.var_c2 + this.var_263 * var1.var_191 + this.var_27d * var1.var_27d;
      this.var_13 = var2;
      this.var_ad = var3;
      this.var_c2 = var4;
      this.var_e7 = var5;
      this.var_138 = var6;
      this.var_191 = var7;
      this.var_211 = var8;
      this.var_263 = var9;
      this.var_27d = var10;
   }

   public final void sub_393() {
      float var1 = this.var_d7;
      float var2 = this.var_1ce;
      float var3 = this.var_2ce;
      this.sub_92(
         this.var_13,
         this.var_e7,
         this.var_211,
         -(this.var_13 * var1 + this.var_e7 * var2 + this.var_211 * var3),
         this.var_ad,
         this.var_138,
         this.var_263,
         -(this.var_ad * var1 + this.var_138 * var2 + this.var_263 * var3),
         this.var_c2,
         this.var_191,
         this.var_27d,
         -(this.var_c2 * var1 + this.var_191 * var2 + this.var_27d * var3)
      );
   }

   public final void sub_3d7(Class_757 var1) {
      float var2 = var1.var_d7;
      float var3 = var1.var_1ce;
      float var4 = var1.var_2ce;
      this.sub_92(
         var1.var_13,
         var1.var_e7,
         var1.var_211,
         -(var1.var_13 * var2 + var1.var_e7 * var3 + var1.var_211 * var4),
         var1.var_ad,
         var1.var_138,
         var1.var_263,
         -(var1.var_ad * var2 + var1.var_138 * var3 + var1.var_263 * var4),
         var1.var_c2,
         var1.var_191,
         var1.var_27d,
         -(var1.var_c2 * var2 + var1.var_191 * var3 + var1.var_27d * var4)
      );
   }

   public final void sub_41a() {
      this.sub_d5(this.var_13, this.var_e7, this.var_211, this.var_ad, this.var_138, this.var_263, this.var_c2, this.var_191, this.var_27d);
   }

   public final void sub_447() {
      float var2 = this.var_13;
      float var3 = this.var_e7;
      float var4 = this.var_211;
      float var1 = 1.0F / (float)Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4);
      var2 *= var1;
      var3 *= var1;
      var4 *= var1;
      this.var_13 = var2;
      this.var_e7 = var3;
      this.var_211 = var4;
      float var5 = var3 * this.var_263 - var4 * this.var_138;
      float var6 = var4 * this.var_ad - var2 * this.var_263;
      float var7 = var2 * this.var_138 - var3 * this.var_ad;
      var1 = 1.0F / (float)Math.sqrt(var5 * var5 + var6 * var6 + var7 * var7);
      var5 *= var1;
      var6 *= var1;
      var7 *= var1;
      this.var_c2 = var5;
      this.var_191 = var6;
      this.var_27d = var7;
      float var8 = var6 * var4 - var7 * var3;
      float var9 = var7 * var2 - var5 * var4;
      float var10 = var5 * var3 - var6 * var2;
      var1 = 1.0F / (float)Math.sqrt(var8 * var8 + var9 * var9 + var10 * var10);
      var8 *= var1;
      var9 *= var1;
      var10 *= var1;
      this.var_ad = var8;
      this.var_138 = var9;
      this.var_263 = var10;
   }
}
