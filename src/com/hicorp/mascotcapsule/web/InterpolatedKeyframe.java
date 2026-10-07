package com.hicorp.mascotcapsule.web;

final class InterpolatedKeyframe {
   int time;
   float duration;
   float value;
   float deltaValue;

   private InterpolatedKeyframe() {
   }

   InterpolatedKeyframe(InterpolatorToken unused) {
      this();
   }
}
