package qsbk.app.live.ui;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearSmoothScroller;

class bz extends LinearSmoothScroller {
    final /* synthetic */ by f;

    bz(by byVar, Context context) {
        this.f = byVar;
        super(context);
    }

    protected int b(int i) {
        return 200;
    }

    public PointF computeScrollVectorForPosition(int i) {
        return this.f.a.N.computeScrollVectorForPosition(i);
    }
}
