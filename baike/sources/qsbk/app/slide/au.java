package qsbk.app.slide;

class au implements Runnable {
    final /* synthetic */ SingleArticleFragment a;

    au(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void run() {
        if (this.a.aw != null) {
            this.a.aw.hideLoading();
        }
    }
}
