package qsbk.app.core.widget;

class o implements Runnable {
    final /* synthetic */ FrameAnimationView a;

    o(FrameAnimationView frameAnimationView) {
        this.a = frameAnimationView;
    }

    public void run() {
        if (this.a.j != null && !this.a.j.isRecycled()) {
            this.a.setImageBitmap(this.a.j);
        }
    }
}
