package qsbk.app.widget;

class cq implements Runnable {
    final /* synthetic */ OverScrollView a;

    cq(OverScrollView overScrollView) {
        this.a = overScrollView;
    }

    public void run() {
        OverScrollView.a(this.a).computeScrollOffset();
        this.a.scrollTo(0, OverScrollView.a(this.a).getCurrY());
        if (OverScrollView.a(this.a).isFinished()) {
            if (OverScrollView.b(this.a) != null) {
                OverScrollView.b(this.a).onScrollChanged(this.a, 0, 0, 0, 0);
            }
            if (Math.abs(OverScrollView.c(this.a)) >= OverScrollView.d(this.a) && OverScrollView.e(this.a) != null) {
                OverScrollView.e(this.a).onLoadMore();
                return;
            }
            return;
        }
        if (OverScrollView.b(this.a) != null) {
            OverScrollView.b(this.a).onScroll(this.a, (int) ((((double) OverScrollView.a(this.a).timePassed()) * ((double) OverScrollView.c(this.a))) / 250.0d), this.a.isTopOverScrolled(), this.a.isBottomOverScrolled());
        }
        this.a.post(this);
    }
}
