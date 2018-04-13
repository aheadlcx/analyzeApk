package qsbk.app.activity;

class st implements Runnable {
    final /* synthetic */ MainActivity a;

    st(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        if (!this.a.isFinishing()) {
            this.a.selecteTab(MainActivity.TAB_QIUSHI_ID);
        }
    }
}
