package com.hicorp.mascotcapsule.web;

public final class Class_339 extends Class_5a9 {
   public Class_339() {
   }

   public Class_339(Class_5a9 parent) {
      super(parent);
   }

   public final void getViewTransform(Transform3D out) {
      this.getWorldTransform(out);
      out.invert();
   }

   public final void computeModelViewTransform(Class_5a9 node, Transform3D out) {
      node.getWorldTransform(out);
      Transform3D view = new Transform3D();
      this.getViewTransform(view);
      out.multiply(view, out);
   }
}
