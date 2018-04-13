package qsbk.app.core.widget;

class n implements Runnable {
    final /* synthetic */ FrameAnimationView a;

    n(FrameAnimationView frameAnimationView) {
        this.a = frameAnimationView;
    }

    public void run() {
        if (!this.a.m) {
            this.a.setImageBitmap(null);
            this.a.a(this.a.j);
        }
        if (this.a.h != null) {
            this.a.h.onEnd();
        }
    }
}
