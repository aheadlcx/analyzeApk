package com.handmark.pulltorefresh.library.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.c.a;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

public class SixRoomPullToRefreshRecyclerView extends PullToRefreshRecyclerView {
    private RecyclerView b;
    private LinearLayoutManager c;
    private a d;
    private int e;
    private boolean f;
    private com.c.c.a g;
    private OnFooterFuncListener h;

    public interface OnFooterFuncListener {
        void onAutoLoadMore();
    }

    public SixRoomPullToRefreshRecyclerView(Context context) {
        this(context, null);
    }

    public SixRoomPullToRefreshRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = (RecyclerView) getRefreshableView();
    }

    public LinearLayoutManager getLayoutManager() {
        return this.c;
    }

    public void setOnFooterFuncListener(OnFooterFuncListener onFooterFuncListener) {
        this.h = onFooterFuncListener;
    }

    public void setAutoLoadMoreEnabled(boolean z) {
        this.f = z;
        this.d = (a) this.b.getAdapter();
        this.c = (LinearLayoutManager) this.b.getLayoutManager();
        if (this.d == null || this.c == null) {
            throw new IllegalStateException("Need Adapter and LayoutManager!!!");
        }
        this.d.setAutoLoadMoreEnabled(z);
        if (this.f) {
            this.g = this.d.getFooterDelegate();
            if (this.g != null) {
                this.g.a(new b(this));
            }
            this.b.addOnScrollListener(new c(this));
        }
    }

    public boolean isAutoLoadMoreEnabled() {
        return this.f;
    }

    public void setEmptyViewAsLv(View view) {
        if (this.d == null) {
            throw new IllegalStateException("Need Adapter!!!");
        }
        this.d.setEmptyView(view);
    }

    public void setHeaderView(View view) {
        if (this.d == null) {
            throw new IllegalStateException("Need Adapter!!!");
        }
        this.d.setHeaderView(view);
    }

    public void addHeaderView(View view) {
        if (this.d == null) {
            throw new IllegalStateException("Need Adapter!!!");
        }
        this.d.addHeaderView(view);
    }

    public void addHeaderView(View view, int i) {
        if (this.d == null) {
            throw new IllegalStateException("Need Adapter!!!");
        }
        this.d.addHeaderView(view, i);
    }

    public void onLoadEnd() {
        onRefreshComplete();
        if (this.f) {
            this.g.a(3);
            this.d.notifyDataSetChanged();
        }
    }

    public void onLoadError() {
        onRefreshComplete();
        if (this.f) {
            this.g.a(4);
            this.d.notifyDataSetChanged();
        }
    }

    public void onLoadReset() {
        onRefreshComplete();
        if (this.f) {
            this.g.a(1);
        }
    }

    public void hideFooter() {
        onRefreshComplete();
        this.g.a(5);
        this.d.notifyDataSetChanged();
    }

    static /* synthetic */ void b(SixRoomPullToRefreshRecyclerView sixRoomPullToRefreshRecyclerView) {
        sixRoomPullToRefreshRecyclerView.onRefreshComplete();
        if (sixRoomPullToRefreshRecyclerView.f) {
            sixRoomPullToRefreshRecyclerView.g.a(2);
            sixRoomPullToRefreshRecyclerView.d.notifyDataSetChanged();
            if (sixRoomPullToRefreshRecyclerView.f && sixRoomPullToRefreshRecyclerView.h != null) {
                sixRoomPullToRefreshRecyclerView.h.onAutoLoadMore();
            }
        }
    }
}
