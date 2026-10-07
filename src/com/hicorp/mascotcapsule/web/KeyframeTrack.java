package com.hicorp.mascotcapsule.web;

final class KeyframeTrack {
   protected int count = 0;
   public KeyframePoint[] keyframes = null;

   private KeyframeTrack() {
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
         this.keyframes = new KeyframePoint[count];

         for (int i = 0; i < count; i++) {
            this.keyframes[i] = new KeyframePoint();
            this.keyframes[i].time = -1;
            this.keyframes[i].value = 0.0F;
         }
      }
   }

   KeyframeTrack(InterpolatorToken unused) {
      this();
   }
}
