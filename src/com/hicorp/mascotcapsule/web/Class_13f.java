package com.hicorp.mascotcapsule.web;

public final class Class_13f extends Class_5a9 {
   protected boolean hasChild;
   protected boolean hasSibling;
   protected String name;
   protected final Transform3D restTransform = new Transform3D();
   protected int boneIndex;

   public Class_13f() {
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
