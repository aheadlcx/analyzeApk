package com.budejie.www.activity.labelsubscription;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class c$8 implements OnScrollListener {
    final /* synthetic */ c a;

    c$8(c cVar) {
        this.a = cVar;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        int i2 = 0;
        if (i == 0) {
            c.a(this.a, absListView.getFirstVisiblePosition());
        }
        if (c.i(this.a) != null) {
            View childAt = absListView.getChildAt(0);
            c cVar = this.a;
            if (childAt != null) {
                i2 = childAt.getTop();
            }
            c.b(cVar, i2);
        }
    }
}
