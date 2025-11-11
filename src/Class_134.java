class Class_134 implements Runnable {
   private Thread var_9c;
   private final Class_aa var_155;

   private Class_134(Class_aa var1) {
      this.var_155 = var1;
   }

   public synchronized void sub_10() {
      if (this.var_155.sub_1aa()) {
         this.var_9c = new Thread(this);
         this.var_9c.setName("MascotCapsule - Animation");
         this.var_9c.setPriority(1);
         this.var_9c.start();
      }
   }

   public synchronized void sub_5a() {
      if (this.var_9c != null) {
         this.var_9c.interrupt();
         this.var_9c = null;
      }
   }

   public void run() {
      long var1 = System.currentTimeMillis();

      try {
         while (true) {
            long var3 = System.currentTimeMillis();
            long var5 = var3 - var1;
            Class_aa.sub_81d(this.var_155, Class_aa.sub_880(this.var_155) * (float)var5 / 1000.0F);
            float var7 = Class_aa.sub_8e3(this.var_155).sub_23d();
            if (var7 > 0.0F && Class_aa.sub_917(this.var_155) > var7) {
               Class_aa.sub_945(this.var_155, var7 * (int)(Class_aa.sub_917(this.var_155) / var7));
            }

            if (!this.var_155.sub_5f() || Thread.interrupted()) {
               throw new InterruptedException();
            }

            Class_aa.sub_985(this.var_155, Class_aa.sub_9de(this.var_155) * (float)var5 / 1000.0F);
            var1 = var3;
            if (var5 < Class_aa.sub_a14(this.var_155)) {
               Thread.sleep(Class_aa.sub_a14(this.var_155) - var5);
            }
         }
      } catch (InterruptedException var8) {
      }
   }

   Class_134(Class_aa var1, Class_105 var2) {
      this(var1);
   }
}
