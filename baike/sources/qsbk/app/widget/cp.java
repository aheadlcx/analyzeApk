package qsbk.app.widget;

class cp implements Runnable {
    final /* synthetic */ OverScrollView a;

    cp(OverScrollView overScrollView) {
        this.a = overScrollView;
    }

    public void run() {
        int paddingTop;
        if (this.a.b != null) {
            paddingTop = this.a.b.getPaddingTop();
        } else {
            paddingTop = 0;
        }
        this.a.scrollTo(0, paddingTop);
    }
}
