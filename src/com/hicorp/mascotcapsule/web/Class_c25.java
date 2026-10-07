package com.hicorp.mascotcapsule.web;

final class Class_c25 {
   protected int count = 0;
   public VertexWeight[] keyframes = null;

   private Class_c25() {
   }

   protected void clear() {
      this.count = 0;
      this.keyframes = null;
   }

   public int getCount() {
      return this.count;
   }

   public void allocate(int count) {
      this.clear();
      if (count > 0) {
         this.count = count;
         this.keyframes = new VertexWeight[count];

         for (int i = 0; i < count; i++) {
            this.keyframes[i] = new VertexWeight();
            this.keyframes[i].time = -1;
            this.keyframes[i].value = 0.0F;
         }
      }
   }

   Class_c25(Interpolator unused) {
      this();
   }
}
