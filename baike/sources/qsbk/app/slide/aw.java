package qsbk.app.slide;

class aw implements Runnable {
    final /* synthetic */ SingleArticleFragment a;

    aw(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void run() {
        this.a.x();
        if (this.a.aw != null) {
            this.a.aw.getMainUIHandler().postDelayed(this, 60000);
        }
    }
}
