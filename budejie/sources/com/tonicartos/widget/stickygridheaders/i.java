package com.tonicartos.widget.stickygridheaders;

import android.view.View;

final class i implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ e b;
    final /* synthetic */ StickyGridHeadersGridView c;

    i(StickyGridHeadersGridView stickyGridHeadersGridView, View view, e eVar) {
        this.c = stickyGridHeadersGridView;
        this.a = view;
        this.b = eVar;
    }

    public final void run() {
        this.c.f = -1;
        this.c.H = null;
        this.c.g = -1;
        this.a.setPressed(false);
        this.c.setPressed(false);
        this.a.invalidate();
        this.c.invalidate(0, this.a.getTop(), this.c.getWidth(), this.a.getHeight());
        if (!this.c.e) {
            this.b.run();
        }
    }
}
