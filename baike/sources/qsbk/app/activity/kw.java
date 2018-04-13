package qsbk.app.activity;

class kw implements Runnable {
    final /* synthetic */ FillUserDataActivity a;

    kw(FillUserDataActivity fillUserDataActivity) {
        this.a = fillUserDataActivity;
    }

    public void run() {
        this.a.g.scrollTo(0, 1000);
    }
}
