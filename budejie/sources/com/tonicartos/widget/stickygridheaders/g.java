package com.tonicartos.widget.stickygridheaders;

import android.database.DataSetObserver;

final class g extends DataSetObserver {
    final /* synthetic */ StickyGridHeadersGridView a;

    g(StickyGridHeadersGridView stickyGridHeadersGridView) {
        this.a = stickyGridHeadersGridView;
    }

    public final void onChanged() {
        this.a.c();
    }

    public final void onInvalidated() {
        this.a.c();
    }
}
