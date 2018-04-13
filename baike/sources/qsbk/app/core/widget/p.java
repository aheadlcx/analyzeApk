package qsbk.app.core.widget;

class p implements Runnable {
    final /* synthetic */ FrameAnimationView a;

    p(FrameAnimationView frameAnimationView) {
        this.a = frameAnimationView;
    }

    public void run() {
        if (!this.a.k) {
            this.a.setImageBitmap(null);
            this.a.a(this.a.j);
        }
    }
}
