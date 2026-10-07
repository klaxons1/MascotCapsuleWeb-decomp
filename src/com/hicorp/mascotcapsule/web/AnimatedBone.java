package com.hicorp.mascotcapsule.web;

final class AnimatedBone {
   public String name = null;
   public final Vector3f translation = new Vector3f();
   public final Vector3f rotationAxis = new Vector3f();
   public final Vector3f rotationAngles = new Vector3f();
   public final Vector3f scale = new Vector3f();
   KeyframeTrack[] tracks = new KeyframeTrack[10];

   AnimatedBone() {
      for (int i = 0; i < 10; i++) {
         this.tracks[i] = new KeyframeTrack(null);
      }
   }
}
