package qsbk.app.fragments;

class ih implements Runnable {
    final /* synthetic */ QiushiListFragment a;

    ih(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void run() {
        this.a.x.setSelectedTab(this.a.o);
    }
}
