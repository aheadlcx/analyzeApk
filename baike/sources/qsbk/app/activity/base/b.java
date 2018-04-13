package qsbk.app.activity.base;

class b implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ BaseArticleListViewFragment b;

    b(BaseArticleListViewFragment baseArticleListViewFragment, boolean z) {
        this.b = baseArticleListViewFragment;
        this.a = z;
    }

    public void run() {
        this.b.a(this.a);
    }
}
