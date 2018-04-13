package android.support.v7.widget;

class bm implements Runnable {
    final /* synthetic */ RecyclerView a;

    bm(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public void run() {
        if (this.a.s && !this.a.isLayoutRequested()) {
            if (!this.a.p) {
                this.a.requestLayout();
            } else if (this.a.u) {
                this.a.t = true;
            } else {
                this.a.d();
            }
        }
    }
}
