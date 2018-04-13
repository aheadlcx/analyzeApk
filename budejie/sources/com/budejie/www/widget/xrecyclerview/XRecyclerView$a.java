package com.budejie.www.widget.xrecyclerview;

import android.support.v7.widget.RecyclerView.AdapterDataObserver;

class XRecyclerView$a extends AdapterDataObserver {
    final /* synthetic */ XRecyclerView a;

    private XRecyclerView$a(XRecyclerView xRecyclerView) {
        this.a = xRecyclerView;
    }

    public void onChanged() {
        if (XRecyclerView.a(this.a) != null) {
            XRecyclerView.a(this.a).notifyDataSetChanged();
        }
        if (XRecyclerView.a(this.a) != null && XRecyclerView.b(this.a) != null) {
            int b = XRecyclerView.a(this.a).b() + 1;
            if (XRecyclerView.c(this.a)) {
                b++;
            }
            if (XRecyclerView.a(this.a).getItemCount() == b) {
                XRecyclerView.b(this.a).setVisibility(0);
                this.a.setVisibility(8);
                return;
            }
            XRecyclerView.b(this.a).setVisibility(8);
            this.a.setVisibility(0);
        }
    }

    public void onItemRangeInserted(int i, int i2) {
        XRecyclerView.a(this.a).notifyItemRangeInserted(i, i2);
    }

    public void onItemRangeChanged(int i, int i2) {
        XRecyclerView.a(this.a).notifyItemRangeChanged(i, i2);
    }

    public void onItemRangeChanged(int i, int i2, Object obj) {
        XRecyclerView.a(this.a).notifyItemRangeChanged(i, i2, obj);
    }

    public void onItemRangeRemoved(int i, int i2) {
        XRecyclerView.a(this.a).notifyItemRangeRemoved(i, i2);
    }

    public void onItemRangeMoved(int i, int i2, int i3) {
        XRecyclerView.a(this.a).notifyItemMoved(i, i2);
    }
}
