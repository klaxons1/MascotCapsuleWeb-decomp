package com.hicorp.mascotcapsule.web;

final class BoneTrackEvaluator {
   public boolean isAnimated = false;
   public Transform3D rotationMatrix;
   public Transform3D scaleMatrix;
   public Transform3D transformMatrix;
   public AnimationTrack[] tracks = new AnimationTrack[10];

   public BoneTrackEvaluator() {
   }

   public void evaluate(float time, float[] outValues) {
      for (int i = 0; i < 10; i++) {
         outValues[i] = this.tracks[i].evaluate(time);
      }
   }
}
