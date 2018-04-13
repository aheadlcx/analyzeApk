package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

public abstract class PullToRefreshAdapterViewBase<T extends AbsListView> extends PullToRefreshBase<T> implements OnScrollListener {
    private boolean b;
    private OnScrollListener c;
    private OnLastItemVisibleListener d;
    private View e;
    private boolean f;
    private boolean g = true;

    public PullToRefreshAdapterViewBase(Context context) {
        super(context);
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, Mode mode) {
        super(context, mode);
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    public boolean getShowIndicator() {
        return this.f;
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.d != null) {
            boolean z = i3 > 0 && i + i2 >= i3 - 1;
            this.b = z;
        }
        getShowIndicatorInternal();
        if (this.c != null) {
            this.c.onScroll(absListView, i, i2, i3);
        }
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.d != null && this.b) {
            this.d.onLastItemVisible();
        }
        if (this.c != null) {
            this.c.onScrollStateChanged(absListView, i);
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        ((AdapterView) this.a).setAdapter(listAdapter);
    }

    public final void setEmptyView(View view) {
        FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
        if (view != null) {
            view.setClickable(true);
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(view);
            }
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    layoutParams2.gravity = ((LinearLayout.LayoutParams) layoutParams).gravity;
                    layoutParams = layoutParams2;
                } else {
                    layoutParams2.gravity = 17;
                    layoutParams = layoutParams2;
                }
            } else {
                layoutParams = null;
            }
            if (layoutParams != null) {
                refreshableViewWrapper.addView(view, layoutParams);
            } else {
                refreshableViewWrapper.addView(view);
            }
        }
        if (this.a instanceof EmptyViewMethodAccessor) {
            ((EmptyViewMethodAccessor) this.a).setEmptyViewInternal(view);
        } else {
            ((AbsListView) this.a).setEmptyView(view);
        }
        this.e = view;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        ((AbsListView) this.a).setOnItemClickListener(onItemClickListener);
    }

    public final void setOnLastItemVisibleListener(OnLastItemVisibleListener onLastItemVisibleListener) {
        this.d = onLastItemVisibleListener;
    }

    public final void setOnScrollListener(OnScrollListener onScrollListener) {
        this.c = onScrollListener;
    }

    public final void setScrollEmptyView(boolean z) {
        this.g = z;
    }

    public void setShowIndicator(boolean z) {
        this.f = z;
        if (getShowIndicatorInternal()) {
            a();
        }
    }

    protected void onPullToRefresh() {
        super.onPullToRefresh();
    }

    protected void onRefreshing(boolean z) {
        super.onRefreshing(z);
        getShowIndicatorInternal();
    }

    protected void onReleaseToRefresh() {
        super.onReleaseToRefresh();
    }

    protected void onReset() {
        super.onReset();
        getShowIndicatorInternal();
    }

    protected void handleStyledAttributes(TypedArray typedArray) {
        this.f = typedArray.getBoolean(R.styleable.PullToRefresh_ptrShowIndicator, !isPullToRefreshOverScrollEnabled());
    }

    protected boolean isReadyForPullStart() {
        Adapter adapter = ((AbsListView) this.a).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            return true;
        }
        if (((AbsListView) this.a).getFirstVisiblePosition() <= 1) {
            View childAt = ((AbsListView) this.a).getChildAt(0);
            if (childAt != null) {
                return childAt.getTop() >= ((AbsListView) this.a).getTop();
            }
        }
        return false;
    }

    protected boolean isReadyForPullEnd() {
        Adapter adapter = ((AbsListView) this.a).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            return true;
        }
        int count = ((AbsListView) this.a).getCount() - 1;
        int lastVisiblePosition = ((AbsListView) this.a).getLastVisiblePosition();
        if (lastVisiblePosition >= count - 1) {
            View childAt = ((AbsListView) this.a).getChildAt(lastVisiblePosition - ((AbsListView) this.a).getFirstVisiblePosition());
            if (childAt != null) {
                return childAt.getBottom() <= ((AbsListView) this.a).getBottom();
            }
        }
        return false;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.e != null && !this.g) {
            this.e.scrollTo(-i, -i2);
        }
    }

    protected void updateUIForMode() {
        super.updateUIForMode();
        if (getShowIndicatorInternal()) {
            a();
        }
    }

    private void a() {
        getMode();
        getRefreshableViewWrapper();
    }

    private boolean getShowIndicatorInternal() {
        return this.f && isPullToRefreshEnabled();
    }
}
