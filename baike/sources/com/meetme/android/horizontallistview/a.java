package com.meetme.android.horizontallistview;

import android.database.DataSetObserver;

class a extends DataSetObserver {
    final /* synthetic */ HorizontalListView a;

    a(HorizontalListView horizontalListView) {
        this.a = horizontalListView;
    }

    public void onChanged() {
        this.a.i = true;
        this.a.u = false;
        this.a.f();
        this.a.invalidate();
        this.a.requestLayout();
    }

    public void onInvalidated() {
        this.a.u = false;
        this.a.f();
        this.a.c();
        this.a.invalidate();
        this.a.requestLayout();
    }
}
