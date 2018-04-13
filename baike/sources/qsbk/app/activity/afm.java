package qsbk.app.activity;

class afm implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;
    final /* synthetic */ VideoImmersionCircleActivity c;

    afm(VideoImmersionCircleActivity videoImmersionCircleActivity, boolean z, int i) {
        this.c = videoImmersionCircleActivity;
        this.a = z;
        this.b = i;
    }

    public void run() {
        this.c.l.smoothScrollToPositionFromTop(this.a ? this.b + 1 : this.b, this.c.d, 150);
    }
}
