package com.hicorp.mascotcapsule.web;

final class AnimationTrack {
   protected int keyframeCount;
   public Keyframe[] keyframes;
   private final Model animation;

   private AnimationTrack(Model animation) {
      this.animation = animation;
      this.keyframeCount = 0;
      this.keyframes = null;
   }

   public int bindKeyframes(int offset, int count) {
      Class_8ed.assertTrue(count > 0);
      this.keyframeCount = count;
      this.keyframes = new Keyframe[this.keyframeCount];

      for (int i = 0; i < this.keyframeCount; i++) {
         this.keyframes[i] = this.animation.allKeyframes[offset + i];
      }

      return offset + count;
   }

   final int getKeyframeCount() {
      return this.keyframeCount;
   }

   float evaluate(float time) {
      Class_8ed.assertTrue(this.keyframeCount > 0);
      int frame;
      if (time < 0.0F) {
         time = 0.0F;
         frame = 0;
      } else {
         frame = (int)time;
      }

      int k = 1;
      while (k < this.keyframeCount && frame >= this.keyframes[k].time) {
         k++;
      }

      int prev = k - 1;
      float val;
      if (k < this.keyframeCount) {
         float alpha = (time - this.keyframes[prev].time) / this.keyframes[prev].duration;
         val = this.keyframes[prev].value + alpha * this.keyframes[prev].deltaValue;
      } else {
         val = this.keyframes[prev].value;
      }

      return val;
   }

   AnimationTrack(Model animation, Interpolator unused) {
      this(animation);
   }
}
