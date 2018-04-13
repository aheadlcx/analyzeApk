package qsbk.app.share.message;

class g implements Runnable {
    final /* synthetic */ RecentContactsFragment a;

    g(RecentContactsFragment recentContactsFragment) {
        this.a = recentContactsFragment;
    }

    public void run() {
        this.a.f = this.a.a(this.a.h);
        this.a.g.post(new h(this));
    }
}
