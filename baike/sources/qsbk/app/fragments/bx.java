package qsbk.app.fragments;

class bx implements Runnable {
    final /* synthetic */ CircleVideoFragment a;

    bx(CircleVideoFragment circleVideoFragment) {
        this.a = circleVideoFragment;
    }

    public void run() {
        this.a.h.autoPlay();
    }
}
