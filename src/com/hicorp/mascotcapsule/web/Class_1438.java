package com.hicorp.mascotcapsule.web;

final class Class_1438 extends Class_1498 {
   int var_1c2;
   int var_1db;
   int var_1f3;
   int var_272;
   Class_517 var_2bb;
   Class_517 var_2c5;
   final Class_ae var_389;
   final Class_ae var_3b9;
   final Class_ae var_3ed;
   final Class_ae var_46c;
   private final Class_539 var_4f3;

   Class_1438(Class_539 var1) {
      super(var1, null);
      this.var_4f3 = var1;
      this.var_389 = new Class_ae();
      this.var_3b9 = new Class_ae();
      this.var_3ed = new Class_ae();
      this.var_46c = new Class_ae();
      super.var_5c = 1;
   }

   public void sub_ac(Class_596 var1) {
      int var2;
      int var3;
      if (this.var_3b9.var_59 > this.var_3ed.var_59) {
         if (this.var_389.var_59 > this.var_3b9.var_59) {
            var3 = this.var_389.var_59;
            var2 = this.var_3ed.var_59;
         } else {
            var3 = this.var_3b9.var_59;
            var2 = this.var_389.var_59 > this.var_3ed.var_59 ? this.var_3ed.var_59 : this.var_389.var_59;
         }
      } else if (this.var_3b9.var_59 > this.var_389.var_59) {
         var3 = this.var_3ed.var_59;
         var2 = this.var_389.var_59;
      } else {
         var3 = this.var_389.var_59 > this.var_3ed.var_59 ? this.var_389.var_59 : this.var_3ed.var_59;
         var2 = this.var_3b9.var_59;
      }

      int var4;
      int var5;
      if (this.var_3b9.var_9f > this.var_3ed.var_9f) {
         if (this.var_389.var_9f > this.var_3b9.var_9f) {
            var5 = this.var_389.var_9f;
            var4 = this.var_3ed.var_9f;
         } else {
            var5 = this.var_3b9.var_9f;
            var4 = this.var_389.var_9f > this.var_3ed.var_9f ? this.var_3ed.var_9f : this.var_389.var_9f;
         }
      } else if (this.var_3b9.var_9f > this.var_389.var_9f) {
         var5 = this.var_3ed.var_9f;
         var4 = this.var_389.var_9f;
      } else {
         var5 = this.var_389.var_9f > this.var_3ed.var_9f ? this.var_389.var_9f : this.var_3ed.var_9f;
         var4 = this.var_3b9.var_9f;
      }

      if (this.var_1c2 == 4) {
         if (var2 > this.var_46c.var_59) {
            var2 = this.var_46c.var_59;
         } else if (var3 < this.var_46c.var_59) {
            var3 = this.var_46c.var_59;
         }

         if (var4 > this.var_46c.var_9f) {
            var4 = this.var_46c.var_9f;
         } else if (var5 < this.var_46c.var_9f) {
            var5 = this.var_46c.var_9f;
         }
      }

      if (var1.var_35 > var2) {
         var1.var_35 = var2;
      }

      if (var1.var_d4 < var3) {
         var1.var_d4 = var3;
      }

      if (var1.var_88 > var4) {
         var1.var_88 = var4;
      }

      if (var1.var_f0 < var5) {
         var1.var_f0 = var5;
      }
   }
}
