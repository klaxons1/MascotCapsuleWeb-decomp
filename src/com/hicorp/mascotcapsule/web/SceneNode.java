package com.hicorp.mascotcapsule.web;

public class SceneNode {
   public static final SceneNode NULL_NODE = null;
   protected SceneNode parent = null;
   protected boolean hasLocalTransform;
   protected final Transform3D localTransform = new Transform3D();
   protected boolean isWorldValid;
   protected final Transform3D worldTransform = new Transform3D();
   protected boolean isParentWorldValid;
   protected final Transform3D cachedTransform = new Transform3D();

   public SceneNode() {
      this.hasLocalTransform = false;
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = NULL_NODE;
   }

   public SceneNode(SceneNode parent) {
      this.hasLocalTransform = false;
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = parent;
   }

   public SceneNode(SceneNode parent, Transform3D local) {
      this.hasLocalTransform = true;
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = parent;
      this.localTransform.set(local);
   }

   public final void setParent(SceneNode parent) {
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = parent;
   }

   public final SceneNode getParent() {
      return this.parent;
   }

   public final void setLocalTransform(Transform3D local) {
      this.hasLocalTransform = true;
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.localTransform.set(local);
   }

   public final void getWorldTransform(Transform3D out) {
      this.computeWorldTransform(out, false);
   }

   protected final boolean computeWorldTransform(Transform3D out, boolean isDirty) {
      Debug.assertTrue(this.hasLocalTransform);
      if (this.parent == NULL_NODE) {
         out.set(this.localTransform);
         return isDirty;
      } else {
         isDirty = this.parent.computeWorldTransform(out, isDirty);
         if (isDirty || !this.isWorldValid) {
            this.worldTransform.multiply(out, this.localTransform);
            isDirty = true;
            this.isWorldValid = true;
         }

         out.set(this.worldTransform);
         return isDirty;
      }
   }

   public final void getTransformRelativeToRoot(Transform3D out) {
      this.computeTransformRelativeToRoot(out, false);
   }

   protected final boolean computeTransformRelativeToRoot(Transform3D out, boolean isDirty) {
      Debug.assertTrue(this.parent != NULL_NODE);
      Debug.assertTrue(this.hasLocalTransform);
      if (this.parent.parent == NULL_NODE) {
         out.set(this.localTransform);
         return isDirty;
      } else {
         isDirty = this.parent.computeTransformRelativeToRoot(out, isDirty);
         if (isDirty || !this.isParentWorldValid) {
            this.cachedTransform.multiply(out, this.localTransform);
            isDirty = true;
            this.isParentWorldValid = true;
         }

         out.set(this.cachedTransform);
         return isDirty;
      }
   }
}
