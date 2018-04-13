package qsbk.app.activity;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class afj implements OnScrollListener {
    final /* synthetic */ VideoImmersionActivity a;

    afj(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.a.p.onScrollStateChanged(absListView, i);
        this.a.q.onScrollStateChanged(absListView, i);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.a.p.onScroll(absListView, i, i2, i3);
        this.a.q.onScroll(absListView, i, i2, i3);
    }
}
