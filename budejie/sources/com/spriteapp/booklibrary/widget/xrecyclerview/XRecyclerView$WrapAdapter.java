package com.spriteapp.booklibrary.widget.xrecyclerview;

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

class XRecyclerView$WrapAdapter extends Adapter<ViewHolder> {
    private Adapter adapter;
    final /* synthetic */ XRecyclerView this$0;

    private class SimpleViewHolder extends ViewHolder {
        public SimpleViewHolder(View view) {
            super(view);
        }
    }

    public XRecyclerView$WrapAdapter(XRecyclerView xRecyclerView, Adapter adapter) {
        this.this$0 = xRecyclerView;
        this.adapter = adapter;
    }

    public Adapter getOriginalAdapter() {
        return this.adapter;
    }

    public boolean isHeader(int i) {
        return i >= 1 && i < XRecyclerView.access$400(this.this$0).size() + 1;
    }

    public boolean isFooter(int i) {
        if (XRecyclerView.access$300(this.this$0) && i == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    public boolean isRefreshHeader(int i) {
        return i == 0;
    }

    public int getHeadersCount() {
        return XRecyclerView.access$400(this.this$0).size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 10000) {
            return new SimpleViewHolder(XRecyclerView.access$500(this.this$0));
        }
        if (XRecyclerView.access$600(this.this$0, i)) {
            return new SimpleViewHolder(XRecyclerView.access$700(this.this$0, i));
        }
        if (i == 10001) {
            return new SimpleViewHolder(XRecyclerView.access$800(this.this$0));
        }
        return this.adapter.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (!isHeader(i) && !isRefreshHeader(i)) {
            int headersCount = i - (getHeadersCount() + 1);
            if (this.adapter != null && headersCount < this.adapter.getItemCount()) {
                this.adapter.onBindViewHolder(viewHolder, headersCount);
            }
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        if (!isHeader(i) && !isRefreshHeader(i)) {
            int headersCount = i - (getHeadersCount() + 1);
            if (this.adapter != null && headersCount < this.adapter.getItemCount()) {
                if (list.isEmpty()) {
                    this.adapter.onBindViewHolder(viewHolder, headersCount);
                } else {
                    this.adapter.onBindViewHolder(viewHolder, headersCount, list);
                }
            }
        }
    }

    public int getItemCount() {
        if (XRecyclerView.access$300(this.this$0)) {
            if (this.adapter != null) {
                return (getHeadersCount() + this.adapter.getItemCount()) + 2;
            }
            return getHeadersCount() + 2;
        } else if (this.adapter != null) {
            return (getHeadersCount() + this.adapter.getItemCount()) + 1;
        } else {
            return getHeadersCount() + 1;
        }
    }

    public int getItemViewType(int i) {
        int headersCount = i - (getHeadersCount() + 1);
        if (isRefreshHeader(i)) {
            return 10000;
        }
        if (isHeader(i)) {
            return ((Integer) XRecyclerView.access$900().get(i - 1)).intValue();
        } else if (isFooter(i)) {
            return 10001;
        } else {
            if (this.adapter == null || headersCount >= this.adapter.getItemCount()) {
                return 0;
            }
            headersCount = this.adapter.getItemViewType(headersCount);
            if (!XRecyclerView.access$1000(this.this$0, headersCount)) {
                return headersCount;
            }
            throw new IllegalStateException("XRecyclerView require itemViewType in adapter should be less than 10000 ");
        }
    }

    public long getItemId(int i) {
        if (this.adapter != null && i >= getHeadersCount() + 1) {
            int headersCount = i - (getHeadersCount() + 1);
            if (headersCount < this.adapter.getItemCount()) {
                return this.adapter.getItemId(headersCount);
            }
        }
        return -1;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
                public int getSpanSize(int i) {
                    return (XRecyclerView$WrapAdapter.this.isHeader(i) || XRecyclerView$WrapAdapter.this.isFooter(i) || XRecyclerView$WrapAdapter.this.isRefreshHeader(i)) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
        this.adapter.onAttachedToRecyclerView(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.adapter.onDetachedFromRecyclerView(recyclerView);
    }

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) && (isHeader(viewHolder.getLayoutPosition()) || isRefreshHeader(viewHolder.getLayoutPosition()) || isFooter(viewHolder.getLayoutPosition()))) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
        this.adapter.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        this.adapter.onViewDetachedFromWindow(viewHolder);
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        this.adapter.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(ViewHolder viewHolder) {
        return this.adapter.onFailedToRecycleView(viewHolder);
    }

    public void unregisterAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
        this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
    }

    public void registerAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
        this.adapter.registerAdapterDataObserver(adapterDataObserver);
    }
}
