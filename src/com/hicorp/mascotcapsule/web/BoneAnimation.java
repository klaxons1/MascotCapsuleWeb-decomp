package com.hicorp.mascotcapsule.web;

final class BoneAnimation {
   public boolean var_24 = false;
   public Transform3D var_ff;
   public Transform3D var_127;
   public Transform3D var_14a;
   public AnimationTrack[] var_1a0 = new AnimationTrack[10];

   public BoneAnimation() {
   }

   public void sub_56(float var1, float[] var2) {
      for (int var3 = 0; var3 < 10; var3++) {
         var2[var3] = this.var_1a0[var3].sub_d5(var1);
      }
   }
}
