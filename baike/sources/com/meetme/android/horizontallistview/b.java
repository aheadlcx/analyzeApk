package com.meetme.android.horizontallistview;

class b implements Runnable {
    final /* synthetic */ HorizontalListView a;

    b(HorizontalListView horizontalListView) {
        this.a = horizontalListView;
    }

    public void run() {
        this.a.requestLayout();
    }
}
