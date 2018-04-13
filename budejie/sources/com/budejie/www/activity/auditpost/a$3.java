package com.budejie.www.activity.auditpost;

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
        if (a.b(this.a) != null && a.c(this.a).a() != null && a.b(this.a).getDataId().equals(a.c(this.a).a().getDataId())) {
            k.a(a.c(this.a)).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), a.d(this.a).getHeaderViewsCount());
        }
    }
}
