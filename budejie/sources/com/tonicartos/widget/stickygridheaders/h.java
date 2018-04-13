package com.tonicartos.widget.stickygridheaders;

import android.view.View;

final class h implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ StickyGridHeadersGridView b;

    h(StickyGridHeadersGridView stickyGridHeadersGridView, View view) {
        this.b = stickyGridHeadersGridView;
        this.a = view;
    }

    public final void run() {
        this.b.invalidate(0, this.a.getTop(), this.b.getWidth(), this.a.getTop() + this.a.getHeight());
    }
}
