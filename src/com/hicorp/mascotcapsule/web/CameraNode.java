package com.hicorp.mascotcapsule.web;

public final class CameraNode extends SceneNode {
   public CameraNode() {
   }

   public CameraNode(SceneNode parent) {
      super(parent);
   }

   public final void getViewTransform(Transform3D out) {
      this.getWorldTransform(out);
      out.invert();
   }

   public final void computeModelViewTransform(SceneNode node, Transform3D out) {
      node.getWorldTransform(out);
      Transform3D view = new Transform3D();
      this.getViewTransform(view);
      out.multiply(view, out);
   }
}
