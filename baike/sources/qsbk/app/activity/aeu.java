package qsbk.app.activity;

class aeu implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ VideoImmersionActivity b;

    aeu(VideoImmersionActivity videoImmersionActivity, int i) {
        this.b = videoImmersionActivity;
        this.a = i;
    }

    public void run() {
        this.b.n.smoothScrollToPositionFromTop(this.a, this.b.h, 200);
    }
}
