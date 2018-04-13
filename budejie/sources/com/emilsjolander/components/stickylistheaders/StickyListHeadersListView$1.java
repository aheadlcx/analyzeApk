package com.emilsjolander.components.stickylistheaders;

import android.view.View;
import com.emilsjolander.components.stickylistheaders.AdapterWrapper.OnHeaderClickListener;

class StickyListHeadersListView$1 implements OnHeaderClickListener {
    final /* synthetic */ StickyListHeadersListView this$0;

    StickyListHeadersListView$1(StickyListHeadersListView stickyListHeadersListView) {
        this.this$0 = stickyListHeadersListView;
    }

    public void onHeaderClick(View view, int i, long j) {
        if (StickyListHeadersListView.access$0(this.this$0) != null) {
            StickyListHeadersListView.access$0(this.this$0).onHeaderClick(this.this$0, view, i, j, false);
        }
    }
}
