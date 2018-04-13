package qsbk.app.slide;

import android.widget.AbsListView;
import qsbk.app.widget.AutoLoadMoreListView.OnLoadMoreListener;

class i implements OnLoadMoreListener {
    final /* synthetic */ SingleArticleFragment a;

    i(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onLoadMore(AbsListView absListView, int i, int i2, int i3) {
        if (this.a.s <= 0) {
            return;
        }
        if (this.a.m.isNormalPage()) {
            this.a.n.load();
        } else {
            this.a.p.load();
        }
    }
}
