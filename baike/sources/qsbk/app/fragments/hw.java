package qsbk.app.fragments;

class hw implements Runnable {
    final /* synthetic */ QiushiListFragment a;

    hw(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void run() {
        try {
            this.a.a(false);
            if (this.a.f()) {
                this.a.g();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
