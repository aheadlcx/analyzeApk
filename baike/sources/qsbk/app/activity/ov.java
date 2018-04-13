package qsbk.app.activity;

class ov implements Runnable {
    final /* synthetic */ ImageViewer a;

    ov(ImageViewer imageViewer) {
        this.a = imageViewer;
    }

    public void run() {
        this.a.finish();
        this.a.overridePendingTransition(0, 0);
    }
}
