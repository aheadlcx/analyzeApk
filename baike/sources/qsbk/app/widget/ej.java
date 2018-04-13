package qsbk.app.widget;

class ej implements Runnable {
    final /* synthetic */ RefreshTipView a;

    ej(RefreshTipView refreshTipView) {
        this.a = refreshTipView;
    }

    public void run() {
        this.a.hide();
    }
}
