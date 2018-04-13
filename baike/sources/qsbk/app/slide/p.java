package qsbk.app.slide;

class p implements Runnable {
    final /* synthetic */ SingleArticleFragment a;

    p(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void run() {
        this.a.J.setText(this.a.l.getLoopString());
    }
}
