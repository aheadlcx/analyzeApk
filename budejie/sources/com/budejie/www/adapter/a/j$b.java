package com.budejie.www.adapter.a;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class j$b implements OnScrollListener {
    final /* synthetic */ j a;

    private j$b(j jVar) {
        this.a = jVar;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        j.a(this.a, i);
        j.b(this.a, i2);
        if (j.a(this.a) && i2 > 0) {
            j.a(this.a, i, i2);
            j.a(this.a, false);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0) {
            j.a(this.a, j.b(this.a), j.c(this.a));
        } else {
            this.a.a();
        }
    }
}
