package qsbk.app.core.widget;

class m implements Runnable {
    final /* synthetic */ FrameAnimationView a;

    m(FrameAnimationView frameAnimationView) {
        this.a = frameAnimationView;
    }

    public void run() {
        this.a.setImageBitmap(null);
        this.a.a(this.a.j);
        if (this.a.h != null) {
            this.a.h.onEnd();
        }
    }
}
