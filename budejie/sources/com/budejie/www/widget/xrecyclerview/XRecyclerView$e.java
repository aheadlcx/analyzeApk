package com.budejie.www.widget.xrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.List;

class XRecyclerView$e extends Adapter<ViewHolder> {
    final /* synthetic */ XRecyclerView a;
    private Adapter b;

    private class a extends ViewHolder {
        final /* synthetic */ XRecyclerView$e a;

        public a(XRecyclerView$e xRecyclerView$e, View view) {
            this.a = xRecyclerView$e;
            super(view);
        }
    }

    public XRecyclerView$e(XRecyclerView xRecyclerView, Adapter adapter) {
        this.a = xRecyclerView;
        this.b = adapter;
    }

    public Adapter a() {
        return this.b;
    }

    public boolean a(int i) {
        return i >= 1 && i < XRecyclerView.d(this.a).size() + 1;
    }

    public boolean b(int i) {
        if (XRecyclerView.c(this.a) && i == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    public boolean c(int i) {
        return i == 0;
    }

    public int b() {
        return XRecyclerView.d(this.a).size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 20000) {
            return new a(this, XRecyclerView.e(this.a));
        }
        if (XRecyclerView.a(this.a, i)) {
            return new a(this, XRecyclerView.b(this.a, i));
        }
        if (i == 20001) {
            return new a(this, XRecyclerView.f(this.a));
        }
        return this.b.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (!a(i) && !c(i)) {
            int b = i - (b() + 1);
            if (this.b != null && b < this.b.getItemCount()) {
                this.b.onBindViewHolder(viewHolder, b);
            }
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        if (!a(i) && !c(i)) {
            int b = i - (b() + 1);
            if (this.b != null && b < this.b.getItemCount()) {
                if (list.isEmpty()) {
                    this.b.onBindViewHolder(viewHolder, b);
                } else {
                    this.b.onBindViewHolder(viewHolder, b, list);
                }
            }
        }
    }

    public int getItemCount() {
        if (this.b != null) {
            return (b() + this.b.getItemCount()) + 2;
        }
        return b() + 2;
    }

    public int getItemViewType(int i) {
        int b = i - (b() + 1);
        if (c(i)) {
            return 20000;
        }
        if (a(i)) {
            return ((Integer) XRecyclerView.g(this.a).get(i - 1)).intValue();
        } else if (b(i)) {
            return 20001;
        } else {
            if (this.b == null || b >= this.b.getItemCount()) {
                return 0;
            }
            b = this.b.getItemViewType(b);
            if (!XRecyclerView.c(this.a, b)) {
                return b;
            }
            throw new IllegalStateException("XRecyclerView require itemViewType in adapter should be less than 20000 ");
        }
    }

    public long getItemId(int i) {
        if (this.b != null && i >= b() + 1) {
            int b = i - (b() + 1);
            if (b < this.b.getItemCount()) {
                return this.b.getItemId(b);
            }
        }
        return -1;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup(this) {
                final /* synthetic */ XRecyclerView$e b;

                public int getSpanSize(int i) {
                    return (this.b.a(i) || this.b.b(i) || this.b.c(i)) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
        this.b.onAttachedToRecyclerView(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.b.onDetachedFromRecyclerView(recyclerView);
    }

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) && (a(viewHolder.getLayoutPosition()) || c(viewHolder.getLayoutPosition()) || b(viewHolder.getLayoutPosition()))) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
        this.b.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        this.b.onViewDetachedFromWindow(viewHolder);
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        this.b.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(ViewHolder viewHolder) {
        return this.b.onFailedToRecycleView(viewHolder);
    }

    public void unregisterAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
        this.b.unregisterAdapterDataObserver(adapterDataObserver);
    }

    public void registerAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
        this.b.registerAdapterDataObserver(adapterDataObserver);
    }
}
