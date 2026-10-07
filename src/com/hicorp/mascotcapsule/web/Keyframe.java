package com.hicorp.mascotcapsule.web;

final class Keyframe {
   int time;
   float duration;
   float value;
   float deltaValue;

   private Keyframe() {
   }

   Keyframe(Interpolator unused) {
      this();
   }
}
