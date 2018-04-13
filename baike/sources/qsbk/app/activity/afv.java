package qsbk.app.activity;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class afv implements OnScrollListener {
    final /* synthetic */ VideoImmersionCircleActivity a;

    afv(VideoImmersionCircleActivity videoImmersionCircleActivity) {
        this.a = videoImmersionCircleActivity;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.a.n.onScrollStateChanged(absListView, i);
        this.a.o.onScrollStateChanged(absListView, i);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.a.n.onScroll(absListView, i, i2, i3);
        this.a.o.onScroll(absListView, i, i2, i3);
    }
}
