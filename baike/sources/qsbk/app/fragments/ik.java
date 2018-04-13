package qsbk.app.fragments;

class ik implements Runnable {
    final /* synthetic */ QiushiListFragment a;

    ik(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void run() {
        this.a.F.post(new il(this));
    }
}
