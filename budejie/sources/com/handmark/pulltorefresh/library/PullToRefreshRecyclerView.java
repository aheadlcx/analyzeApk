package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;

public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    private SparseArray<View> b;
    private int c = 0;

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected RecyclerView createRefreshableView(Context context, AttributeSet attributeSet) {
        this.b = new SparseArray();
        RecyclerView recyclerView = new RecyclerView(context, attributeSet);
        recyclerView.setId(R.id.recyclerview);
        return recyclerView;
    }

    protected boolean isReadyForPullStart() {
        if (((RecyclerView) this.a).getChildCount() <= 0) {
            return true;
        }
        return ((RecyclerView) this.a).getChildLayoutPosition(((RecyclerView) this.a).getChildAt(0)) == 0 && ((RecyclerView) this.a).getChildAt(0).getTop() == ((RecyclerView) this.a).getPaddingTop() + this.c;
    }

    protected boolean isReadyForPullEnd() {
        return ((RecyclerView) this.a).getChildLayoutPosition(((RecyclerView) this.a).getChildAt(((RecyclerView) this.a).getChildCount() + -1)) >= ((RecyclerView) this.a).getAdapter().getItemCount() + -1 && ((RecyclerView) this.a).getChildAt(((RecyclerView) this.a).getChildCount() - 1) != null && ((RecyclerView) this.a).getChildAt(((RecyclerView) this.a).getChildCount() - 1).getBottom() <= ((RecyclerView) this.a).getBottom();
    }

    public final View setEmptyView(@LayoutRes int i) {
        View inflate;
        View view = (View) this.b.get(i);
        if (view == null) {
            inflate = LayoutInflater.from(getContext()).inflate(i, getRefreshableViewWrapper(), false);
        } else {
            inflate = view;
        }
        this.b.append(i, inflate);
        FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
        ViewParent parent = inflate.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(inflate);
        }
        LayoutParams layoutParams = inflate.getLayoutParams();
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
            refreshableViewWrapper.addView(inflate, layoutParams);
        } else {
            refreshableViewWrapper.addView(inflate, new FrameLayout.LayoutParams(-1, -1, 17));
        }
        return inflate;
    }

    public final void hideView(@LayoutRes int i) {
        View view = (View) this.b.get(i);
        if (view != null) {
            this.b.remove(i);
            getRefreshableViewWrapper().removeView(view);
        }
    }

    public int getOffset() {
        return this.c;
    }

    public void setOffset(int i) {
        this.c = i;
    }
}
