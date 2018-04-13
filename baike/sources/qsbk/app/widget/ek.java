package qsbk.app.widget;

class ek implements Runnable {
    final /* synthetic */ RefreshTipView a;

    ek(RefreshTipView refreshTipView) {
        this.a = refreshTipView;
    }

    public void run() {
        this.a.show();
    }
}
