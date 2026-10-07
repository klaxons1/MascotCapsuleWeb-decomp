package com.hicorp.mascotcapsule.web;

public class Class_5a9 {
   public static final Class_5a9 NULL_NODE = null;
   protected Class_5a9 parent = null;
   protected boolean hasLocalTransform;
   protected final Transform3D localTransform = new Transform3D();
   protected boolean isWorldValid;
   protected final Transform3D worldTransform = new Transform3D();
   protected boolean isParentWorldValid;
   protected final Transform3D cachedTransform = new Transform3D();

   public Class_5a9() {
      this.hasLocalTransform = false;
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = NULL_NODE;
   }

   public Class_5a9(Class_5a9 parent) {
      this.hasLocalTransform = false;
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = parent;
   }

   public Class_5a9(Class_5a9 parent, Transform3D local) {
      this.hasLocalTransform = true;
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = parent;
      this.localTransform.set(local);
   }

   public final void setParent(Class_5a9 parent) {
      this.isWorldValid = false;
      this.isParentWorldValid = false;
      this.parent = parent;
   }

   public final Class_5a9 getParent() {
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
      Class_8ed.assertTrue(this.hasLocalTransform);
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
      Class_8ed.assertTrue(this.parent != NULL_NODE);
      Class_8ed.assertTrue(this.hasLocalTransform);
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
