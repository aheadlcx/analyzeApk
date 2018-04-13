package android.support.v7.widget;

import android.support.v7.widget.GridLayout.Alignment;
import android.view.View;

class ax extends d {
    final /* synthetic */ aw a;
    private int b;

    ax(aw awVar) {
        this.a = awVar;
    }

    protected void a() {
        super.a();
        this.b = Integer.MIN_VALUE;
    }

    protected void a(int i, int i2) {
        super.a(i, i2);
        this.b = Math.max(this.b, i + i2);
    }

    protected int a(boolean z) {
        return Math.max(super.a(z), this.b);
    }

    protected int a(GridLayout gridLayout, View view, Alignment alignment, int i, boolean z) {
        return Math.max(0, super.a(gridLayout, view, alignment, i, z));
    }
}
