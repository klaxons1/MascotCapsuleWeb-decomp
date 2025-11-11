package com.hicorp.mascotcapsule.web;

public class Class_5a9 {
   public static final Class_5a9 var_2d = null;
   protected Class_5a9 var_70 = null;
   protected boolean var_b4;
   protected final Transform3D var_124 = new Transform3D();
   protected boolean var_154;
   protected final Transform3D var_196 = new Transform3D();
   protected boolean var_1c0;
   protected final Transform3D var_1ea = new Transform3D();

   public Class_5a9() {
      this.var_b4 = false;
      this.var_154 = false;
      this.var_1c0 = false;
      this.var_70 = var_2d;
   }

   public Class_5a9(Class_5a9 var1) {
      this.var_b4 = false;
      this.var_154 = false;
      this.var_1c0 = false;
      this.var_70 = var1;
   }

   public Class_5a9(Class_5a9 var1, Transform3D var2) {
      this.var_b4 = true;
      this.var_154 = false;
      this.var_1c0 = false;
      this.var_70 = var1;
      this.var_124.sub_37(var2);
   }

   public final void sub_55(Class_5a9 var1) {
      this.var_154 = false;
      this.var_1c0 = false;
      this.var_70 = var1;
   }

   public final Class_5a9 sub_ab() {
      return this.var_70;
   }

   public final void sub_d7(Transform3D var1) {
      this.var_b4 = true;
      this.var_154 = false;
      this.var_1c0 = false;
      this.var_124.sub_37(var1);
   }

   public final void sub_12f(Transform3D var1) {
      this.sub_13b(var1, false);
   }

   protected final boolean sub_13b(Transform3D var1, boolean var2) {
      Class_8ed.sub_7d(this.var_b4);
      if (this.var_70 == var_2d) {
         var1.sub_37(this.var_124);
         return var2;
      } else {
         var2 = this.var_70.sub_13b(var1, var2);
         if (var2 || !this.var_154) {
            this.var_196.sub_2b3(var1, this.var_124);
            var2 = true;
            this.var_154 = true;
         }

         var1.sub_37(this.var_196);
         return var2;
      }
   }

   public final void sub_16b(Transform3D var1) {
      this.sub_1ab(var1, false);
   }

   protected final boolean sub_1ab(Transform3D var1, boolean var2) {
      Class_8ed.sub_7d(this.var_70 != var_2d);
      Class_8ed.sub_7d(this.var_b4);
      if (this.var_70.var_70 == var_2d) {
         var1.sub_37(this.var_124);
         return var2;
      } else {
         var2 = this.var_70.sub_1ab(var1, var2);
         if (var2 || !this.var_1c0) {
            this.var_1ea.sub_2b3(var1, this.var_124);
            var2 = true;
            this.var_1c0 = true;
         }

         var1.sub_37(this.var_1ea);
         return var2;
      }
   }
}
