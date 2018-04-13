package qsbk.app.activity;

class gs implements Runnable {
    final /* synthetic */ CircleArticleImageViewer a;

    gs(CircleArticleImageViewer circleArticleImageViewer) {
        this.a = circleArticleImageViewer;
    }

    public void run() {
        this.a.finish();
        this.a.overridePendingTransition(0, 0);
        this.a.b = null;
    }
}
