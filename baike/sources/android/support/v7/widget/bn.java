package android.support.v7.widget;

class bn implements Runnable {
    final /* synthetic */ RecyclerView a;

    bn(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public void run() {
        if (this.a.x != null) {
            this.a.x.runPendingAnimations();
        }
        this.a.E = false;
    }
}
