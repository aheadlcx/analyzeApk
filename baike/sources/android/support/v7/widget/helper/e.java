package android.support.v7.widget.helper;

import android.support.v7.widget.RecyclerView.ChildDrawingOrderCallback;

class e implements ChildDrawingOrderCallback {
    final /* synthetic */ ItemTouchHelper a;

    e(ItemTouchHelper itemTouchHelper) {
        this.a = itemTouchHelper;
    }

    public int onGetChildDrawingOrder(int i, int i2) {
        if (this.a.s == null) {
            return i2;
        }
        int i3 = this.a.t;
        if (i3 == -1) {
            i3 = this.a.p.indexOfChild(this.a.s);
            this.a.t = i3;
        }
        if (i2 == i - 1) {
            return i3;
        }
        return i2 >= i3 ? i2 + 1 : i2;
    }
}
