package com.spriteapp.booklibrary.widget.xrecyclerview;

import android.support.v7.widget.RecyclerView.AdapterDataObserver;

class XRecyclerView$DataObserver extends AdapterDataObserver {
    final /* synthetic */ XRecyclerView this$0;

    private XRecyclerView$DataObserver(XRecyclerView xRecyclerView) {
        this.this$0 = xRecyclerView;
    }

    public void onChanged() {
        if (XRecyclerView.access$100(this.this$0) != null) {
            XRecyclerView.access$100(this.this$0).notifyDataSetChanged();
        }
        if (XRecyclerView.access$100(this.this$0) != null && XRecyclerView.access$200(this.this$0) != null) {
            int headersCount = XRecyclerView.access$100(this.this$0).getHeadersCount() + 1;
            if (XRecyclerView.access$300(this.this$0)) {
                headersCount++;
            }
            if (XRecyclerView.access$100(this.this$0).getItemCount() == headersCount) {
                XRecyclerView.access$200(this.this$0).setVisibility(0);
                this.this$0.setVisibility(8);
                return;
            }
            XRecyclerView.access$200(this.this$0).setVisibility(8);
            this.this$0.setVisibility(0);
        }
    }

    public void onItemRangeInserted(int i, int i2) {
        XRecyclerView.access$100(this.this$0).notifyItemRangeInserted(i, i2);
    }

    public void onItemRangeChanged(int i, int i2) {
        XRecyclerView.access$100(this.this$0).notifyItemRangeChanged(i, i2);
    }

    public void onItemRangeChanged(int i, int i2, Object obj) {
        XRecyclerView.access$100(this.this$0).notifyItemRangeChanged(i, i2, obj);
    }

    public void onItemRangeRemoved(int i, int i2) {
        XRecyclerView.access$100(this.this$0).notifyItemRangeRemoved(i, i2);
    }

    public void onItemRangeMoved(int i, int i2, int i3) {
        XRecyclerView.access$100(this.this$0).notifyItemMoved(i, i2);
    }
}
