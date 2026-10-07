class AnimationThread implements Runnable {
   private Thread thread;
   private final MascotCapsuleCanvas canvas;

   private AnimationThread(MascotCapsuleCanvas canvas) {
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
            MascotCapsuleCanvas.advanceAnimationFrame(this.canvas, MascotCapsuleCanvas.getFrameRate(this.canvas) * (float)dtMs / 1000.0F);
            float duration = MascotCapsuleCanvas.getAnimation(this.canvas).getDuration();
            if (duration > 0.0F && MascotCapsuleCanvas.getCurrentFrame(this.canvas) > duration) {
               MascotCapsuleCanvas.wrapAnimationFrame(this.canvas, duration * (int)(MascotCapsuleCanvas.getCurrentFrame(this.canvas) / duration));
            }

            if (!this.canvas.renderFrame() || Thread.interrupted()) {
               throw new InterruptedException();
            }

            MascotCapsuleCanvas.advanceRotation(this.canvas, MascotCapsuleCanvas.getRotationSpeed(this.canvas) * (float)dtMs / 1000.0F);
            lastTime = now;
            if (dtMs < MascotCapsuleCanvas.getFrameIntervalMs(this.canvas)) {
               Thread.sleep(MascotCapsuleCanvas.getFrameIntervalMs(this.canvas) - dtMs);
            }
         }
      } catch (InterruptedException e) {
      }
   }

   AnimationThread(MascotCapsuleCanvas canvas, AnimationThreadToken unused) {
      this(canvas);
   }
}
