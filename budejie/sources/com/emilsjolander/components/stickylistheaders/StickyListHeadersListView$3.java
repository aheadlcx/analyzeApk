package com.emilsjolander.components.stickylistheaders;

import android.os.Build.VERSION;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class StickyListHeadersListView$3 implements OnScrollListener {
    final /* synthetic */ StickyListHeadersListView this$0;

    StickyListHeadersListView$3(StickyListHeadersListView stickyListHeadersListView) {
        this.this$0 = stickyListHeadersListView;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (StickyListHeadersListView.access$2(this.this$0) != null) {
            StickyListHeadersListView.access$2(this.this$0).onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (StickyListHeadersListView.access$2(this.this$0) != null) {
            StickyListHeadersListView.access$2(this.this$0).onScroll(absListView, i, i2, i3);
        }
        if (VERSION.SDK_INT >= 8) {
            StickyListHeadersListView.access$3(this.this$0, i);
        }
    }
}
