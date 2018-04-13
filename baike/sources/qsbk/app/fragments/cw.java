package qsbk.app.fragments;

class cw implements Runnable {
    final /* synthetic */ HotCommentCircleFragment a;

    cw(HotCommentCircleFragment hotCommentCircleFragment) {
        this.a = hotCommentCircleFragment;
    }

    public void run() {
        this.a.h.autoPlay();
    }
}
