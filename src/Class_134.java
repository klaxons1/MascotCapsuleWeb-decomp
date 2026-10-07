class Class_134 implements Runnable {
   private Thread thread;
   private final Class_aa canvas;

   private Class_134(Class_aa canvas) {
      this.canvas = canvas;
   }

   public synchronized void start() {
      if (this.canvas.isReadyToRender()) {
         this.thread = new Thread(this);
         this.thread.setName("MascotCapsule - Animation");
         this.thread.setPriority(1);
         this.thread.start();
      }
   }

   public synchronized void stop() {
      if (this.thread != null) {
         this.thread.interrupt();
         this.thread = null;
      }
   }

   public void run() {
      long lastTime = System.currentTimeMillis();

      try {
         while (true) {
            long now = System.currentTimeMillis();
            long dtMs = now - lastTime;
            Class_aa.advanceAnimationFrame(this.canvas, Class_aa.getFrameRate(this.canvas) * (float)dtMs / 1000.0F);
            float duration = Class_aa.getAnimation(this.canvas).getDuration();
            if (duration > 0.0F && Class_aa.getCurrentFrame(this.canvas) > duration) {
               Class_aa.wrapAnimationFrame(this.canvas, duration * (int)(Class_aa.getCurrentFrame(this.canvas) / duration));
            }

            if (!this.canvas.renderFrame() || Thread.interrupted()) {
               throw new InterruptedException();
            }

            Class_aa.advanceRotation(this.canvas, Class_aa.getRotationSpeed(this.canvas) * (float)dtMs / 1000.0F);
            lastTime = now;
            if (dtMs < Class_aa.getFrameIntervalMs(this.canvas)) {
               Thread.sleep(Class_aa.getFrameIntervalMs(this.canvas) - dtMs);
            }
         }
      } catch (InterruptedException e) {
      }
   }

   Class_134(Class_aa canvas, Class_105 unused) {
      this(canvas);
   }
}
