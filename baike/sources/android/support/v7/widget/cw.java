package android.support.v7.widget;

class cw implements Runnable {
    final /* synthetic */ Toolbar a;

    cw(Toolbar toolbar) {
        this.a = toolbar;
    }

    public void run() {
        this.a.showOverflowMenu();
    }
}
