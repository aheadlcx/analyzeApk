package android.support.v7.widget.util;

import android.support.v7.util.SortedList.Callback;
import android.support.v7.widget.RecyclerView.Adapter;

public abstract class SortedListAdapterCallback<T2> extends Callback<T2> {
    final Adapter a;

    public SortedListAdapterCallback(Adapter adapter) {
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

    public void onChanged(int i, int i2) {
        this.a.notifyItemRangeChanged(i, i2);
    }
}
