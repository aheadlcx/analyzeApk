package com.budejie.www.activity.mycomment;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.budejie.www.activity.video.k;

class a$3 implements OnScrollListener {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        k.a(this.a.getActivity()).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), a.a(this.a).getHeaderViewsCount());
    }
}
