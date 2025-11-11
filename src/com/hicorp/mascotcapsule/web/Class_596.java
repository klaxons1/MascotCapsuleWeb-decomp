package com.hicorp.mascotcapsule.web;

public final class Class_596 {
   public int var_35;
   public int var_88;
   public int var_d4;
   public int var_f0;

   public Class_596() {
      this.sub_72();
   }

   public Class_596(int var1, int var2, int var3, int var4) {
      this.var_35 = var1;
      this.var_88 = var2;
      this.var_d4 = var3;
      this.var_f0 = var4;
   }

   public void sub_47(int var1, int var2, int var3, int var4) {
      this.var_35 = var1;
      this.var_88 = var2;
      this.var_d4 = var3;
      this.var_f0 = var4;
   }

   public void sub_5d(Class_596 var1) {
      this.var_35 = var1.var_35;
      this.var_88 = var1.var_88;
      this.var_d4 = var1.var_d4;
      this.var_f0 = var1.var_f0;
   }

   public void sub_72() {
      this.var_35 = this.var_88 = Integer.MAX_VALUE;
      this.var_d4 = this.var_f0 = Integer.MIN_VALUE;
   }

   public void sub_a9() {
      this.var_35 = this.var_88 = Integer.MIN_VALUE;
      this.var_d4 = this.var_f0 = Integer.MAX_VALUE;
   }

   public boolean sub_da() {
      return this.var_35 < this.var_d4 && this.var_88 < this.var_f0;
   }

   public void sub_133(int var1, int var2, int var3, int var4) {
      if (this.sub_da()) {
         if (this.var_35 < var1) {
            this.var_35 = var1;
         }

         if (this.var_d4 > var3) {
            this.var_d4 = var3;
         }

         if (this.var_88 < var2) {
            this.var_88 = var2;
         }

         if (this.var_f0 > var4) {
            this.var_f0 = var4;
         }
      }
   }

   public void sub_145(Class_596 var1) {
      if (this.sub_da()) {
         if (this.var_35 < var1.var_35) {
            this.var_35 = var1.var_35;
         }

         if (this.var_d4 > var1.var_d4) {
            this.var_d4 = var1.var_d4;
         }

         if (this.var_88 < var1.var_88) {
            this.var_88 = var1.var_88;
         }

         if (this.var_f0 > var1.var_f0) {
            this.var_f0 = var1.var_f0;
         }
      }
   }

   public void sub_159(int var1, int var2) {
      if (var1 < this.var_35) {
         this.var_35 = var1;
      }

      if (var1 > this.var_d4) {
         this.var_d4 = var1;
      }

      if (var2 < this.var_88) {
         this.var_88 = var2;
      }

      if (var2 > this.var_f0) {
         this.var_f0 = var2;
      }
   }

   public void sub_1bb(Class_596 var1) {
      if (var1 != null) {
         if (var1.var_35 < this.var_35) {
            this.var_35 = var1.var_35;
         }

         if (var1.var_d4 > this.var_d4) {
            this.var_d4 = var1.var_d4;
         }

         if (var1.var_88 < this.var_88) {
            this.var_88 = var1.var_88;
         }

         if (var1.var_f0 > this.var_f0) {
            this.var_f0 = var1.var_f0;
         }
      }
   }
}
