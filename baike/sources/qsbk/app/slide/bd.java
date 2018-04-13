package qsbk.app.slide;

class bd implements Runnable {
    final /* synthetic */ OnLoadListener a;

    bd(OnLoadListener onLoadListener) {
        this.a = onLoadListener;
    }

    public void run() {
        this.a.a.d.q.setSelection(this.a.a.d.q.getHeaderViewsCount());
    }
}
