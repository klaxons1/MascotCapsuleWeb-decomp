package com.hicorp.mascotcapsule.web;

final class Class_c25 {
   protected int var_9b = 0;
   public VertexWeight[] var_ce = null;

   private Class_c25() {
   }

   protected void sub_e() {
      this.var_9b = 0;
      this.var_ce = null;
   }

   public int sub_41() {
      return this.var_9b;
   }

   public void sub_9a(int var1) {
      this.sub_e();
      if (var1 > 0) {
         this.var_9b = var1;
         this.var_ce = new VertexWeight[var1];

         for (int var2 = 0; var2 < var1; var2++) {
            this.var_ce[var2] = new VertexWeight();
            this.var_ce[var2].var_2e = -1;
            this.var_ce[var2].var_92 = 0.0F;
         }
      }
   }

   Class_c25(Class_15bc var1) {
      this();
   }
}
