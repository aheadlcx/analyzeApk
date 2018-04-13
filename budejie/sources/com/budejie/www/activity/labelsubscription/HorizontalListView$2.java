package com.budejie.www.activity.labelsubscription;

import android.database.DataSetObserver;

class HorizontalListView$2 extends DataSetObserver {
    final /* synthetic */ HorizontalListView a;

    HorizontalListView$2(HorizontalListView horizontalListView) {
        this.a = horizontalListView;
    }

    public void onChanged() {
        HorizontalListView.a(this.a, true);
        HorizontalListView.b(this.a, false);
        HorizontalListView.b(this.a);
        this.a.invalidate();
        this.a.requestLayout();
    }

    public void onInvalidated() {
        HorizontalListView.b(this.a, false);
        HorizontalListView.b(this.a);
        HorizontalListView.c(this.a);
        this.a.invalidate();
        this.a.requestLayout();
    }
}
