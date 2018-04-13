package qsbk.app.activity.base;

class q implements Runnable {
    final /* synthetic */ BaseArticleListViewFragment a;

    q(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public void run() {
        this.a.F.autoPlay();
    }
}
