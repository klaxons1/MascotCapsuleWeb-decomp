package com.hicorp.mascotcapsule.web;

final class AnimationTrack {
   protected int keyframeCount;
   public InterpolatedKeyframe[] keyframes;
   private final TraAnimation animation;

   private AnimationTrack(TraAnimation animation) {
      this.animation = animation;
      this.keyframeCount = 0;
      this.keyframes = null;
   }

   public int bindKeyframes(int offset, int count) {
      Debug.assertTrue(count > 0);
      this.keyframeCount = count;
      this.keyframes = new InterpolatedKeyframe[this.keyframeCount];

      for (int i = 0; i < this.keyframeCount; i++) {
         this.keyframes[i] = this.animation.allKeyframes[offset + i];
      }

      return offset + count;
   }

   final int getKeyframeCount() {
      return this.keyframeCount;
   }

   float evaluate(float time) {
      Debug.assertTrue(this.keyframeCount > 0);
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

   AnimationTrack(TraAnimation animation, InterpolatorToken unused) {
      this(animation);
   }
}
