package qsbk.app.activity.base;

class x implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ BaseArticleListViewFragment b;

    x(BaseArticleListViewFragment baseArticleListViewFragment, int i) {
        this.b = baseArticleListViewFragment;
        this.a = i;
    }

    public void run() {
        this.b.m.requestFocus();
        this.b.m.setSelection(this.a);
    }
}
