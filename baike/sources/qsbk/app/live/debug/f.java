package qsbk.app.live.debug;

class f implements Runnable {
    final /* synthetic */ LiveDebugListFragment a;

    f(LiveDebugListFragment liveDebugListFragment) {
        this.a = liveDebugListFragment;
    }

    public void run() {
        if (!this.a.e) {
            this.a.g.setRefreshing(true);
            this.a.d();
            this.a.g();
        }
    }
}
