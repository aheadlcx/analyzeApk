package android.support.v7.util;

import android.support.v7.util.DiffUtil.DiffResult;
import android.support.v7.widget.RecyclerView.Adapter;

class d implements ListUpdateCallback {
    final /* synthetic */ Adapter a;
    final /* synthetic */ DiffResult b;

    d(DiffResult diffResult, Adapter adapter) {
        this.b = diffResult;
        this.a = adapter;
    }

    public void onInserted(int i, int i2) {
        this.a.notifyItemRangeInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.a.notifyItemRangeRemoved(i, i2);
    }

    public void onMoved(int i, int i2) {
        this.a.notifyItemMoved(i, i2);
    }

    public void onChanged(int i, int i2, Object obj) {
        this.a.notifyItemRangeChanged(i, i2, obj);
    }
}
