package qsbk.app.activity;

class afk implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;
    final /* synthetic */ VideoImmersionActivity c;

    afk(VideoImmersionActivity videoImmersionActivity, boolean z, int i) {
        this.c = videoImmersionActivity;
        this.a = z;
        this.b = i;
    }

    public void run() {
        this.c.n.smoothScrollToPositionFromTop(this.a ? this.b + 1 : this.b, this.c.h, 150);
    }
}
