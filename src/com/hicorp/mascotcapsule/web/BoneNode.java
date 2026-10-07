package com.hicorp.mascotcapsule.web;

public final class BoneNode extends SceneNode {
   protected boolean hasChild;
   protected boolean hasSibling;
   protected String name;
   protected final Transform3D restTransform = new Transform3D();
   protected int boneIndex;

   public BoneNode() {
      this.name = "";
      this.boneIndex = 0;
   }

   final int getIndex() {
      return this.boneIndex;
   }

   final String getName() {
      return this.name;
   }

   final Transform3D getRestTransform() {
      return this.restTransform;
   }
}
