package qsbk.app.activity;

import android.widget.AbsListView;
import qsbk.app.widget.AutoLoadMoreListView.OnLoadMoreListener;

class gd implements OnLoadMoreListener {
    final /* synthetic */ CircleArticleActivity a;

    gd(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onLoadMore(AbsListView absListView, int i, int i2, int i3) {
        if (this.a.c.isNormalPage()) {
            this.a.c(this.a.o);
        } else {
            this.a.b(this.a.p);
        }
    }
}
