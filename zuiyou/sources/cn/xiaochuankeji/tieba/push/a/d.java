package cn.xiaochuankeji.tieba.push.a;

import android.support.v7.widget.RecyclerView.AdapterDataObserver;

public class d extends AdapterDataObserver {
    public void onChanged() {
        super.onChanged();
        a();
    }

    public void onItemRangeChanged(int i, int i2) {
        super.onItemRangeChanged(i, i2);
        a();
    }

    public void onItemRangeChanged(int i, int i2, Object obj) {
        super.onItemRangeChanged(i, i2, obj);
        a();
    }

    public void onItemRangeInserted(int i, int i2) {
        super.onItemRangeInserted(i, i2);
        a();
    }

    public void onItemRangeRemoved(int i, int i2) {
        super.onItemRangeRemoved(i, i2);
        a();
    }

    public void onItemRangeMoved(int i, int i2, int i3) {
        super.onItemRangeMoved(i, i2, i3);
        a();
    }

    public void a() {
    }
}
