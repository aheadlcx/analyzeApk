package qsbk.app.activity;

class afn implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ VideoImmersionCircleActivity b;

    afn(VideoImmersionCircleActivity videoImmersionCircleActivity, int i) {
        this.b = videoImmersionCircleActivity;
        this.a = i;
    }

    public void run() {
        this.b.l.smoothScrollToPositionFromTop(this.a, this.b.d, 150);
    }
}
