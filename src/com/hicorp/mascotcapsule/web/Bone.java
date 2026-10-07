package com.hicorp.mascotcapsule.web;

final class Bone {
   public String name = null;
   public final Vector3f translation = new Vector3f();
   public final Vector3f rotationAxis = new Vector3f();
   public final Vector3f rotationAngles = new Vector3f();
   public final Vector3f scale = new Vector3f();
   Class_c25[] tracks = new Class_c25[10];

   Bone() {
      for (int i = 0; i < 10; i++) {
         this.tracks[i] = new Class_c25(null);
      }
   }
}
