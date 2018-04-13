package qsbk.app.slide;

class ao implements Runnable {
    final /* synthetic */ SingleArticleFragment a;

    ao(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void run() {
        if (this.a.aw != null) {
            this.a.aw.showLoading();
        }
    }
}
