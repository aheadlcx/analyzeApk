package qsbk.app.widget;

class cr implements Runnable {
    final /* synthetic */ OverScrollView a;

    cr(OverScrollView overScrollView) {
        this.a = overScrollView;
    }

    public void run() {
        this.a.scrollTo(0, this.a.b.getPaddingTop());
    }
}
