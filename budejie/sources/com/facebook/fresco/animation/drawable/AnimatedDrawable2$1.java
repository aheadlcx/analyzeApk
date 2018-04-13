package com.facebook.fresco.animation.drawable;

class AnimatedDrawable2$1 implements Runnable {
    final /* synthetic */ AnimatedDrawable2 this$0;

    AnimatedDrawable2$1(AnimatedDrawable2 animatedDrawable2) {
        this.this$0 = animatedDrawable2;
    }

    public void run() {
        this.this$0.unscheduleSelf(AnimatedDrawable2.access$000(this.this$0));
        this.this$0.invalidateSelf();
    }
}
