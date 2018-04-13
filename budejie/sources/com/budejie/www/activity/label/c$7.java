package com.budejie.www.activity.label;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.budejie.www.activity.video.k;

class c$7 implements OnScrollListener {
    final /* synthetic */ c a;

    c$7(c cVar) {
        this.a = cVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (k.a(c.b(this.a)).b() && this.a.getUserVisibleHint() && this.a.isResumed()) {
            k.a(c.b(this.a)).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), c.a(this.a).getHeaderViewsCount());
        }
    }
}
