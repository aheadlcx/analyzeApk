package com.marshalchen.ultimaterecyclerview.b;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;

public class a {
    final RecyclerView a;
    final LayoutManager b;

    a(RecyclerView recyclerView) {
        this.a = recyclerView;
        this.b = recyclerView.getLayoutManager();
    }

    public static a a(RecyclerView recyclerView) {
        if (recyclerView != null) {
            return new a(recyclerView);
        }
        throw new NullPointerException("Recycler View is null");
    }

    public int a() {
        View a = a(0, this.b.getChildCount(), false, true);
        return a == null ? -1 : this.a.getChildAdapterPosition(a);
    }

    public int b() {
        View a = a(this.b.getChildCount() - 1, -1, false, true);
        if (a == null) {
            return -1;
        }
        return this.a.getChildAdapterPosition(a);
    }

    private View a(int i, int i2, boolean z, boolean z2) {
        OrientationHelper createVerticalHelper;
        if (this.b.canScrollVertically()) {
            createVerticalHelper = OrientationHelper.createVerticalHelper(this.b);
        } else {
            createVerticalHelper = OrientationHelper.createHorizontalHelper(this.b);
        }
        int startAfterPadding = createVerticalHelper.getStartAfterPadding();
        int endAfterPadding = createVerticalHelper.getEndAfterPadding();
        int i3 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View childAt = this.b.getChildAt(i);
            int decoratedStart = createVerticalHelper.getDecoratedStart(childAt);
            int decoratedEnd = createVerticalHelper.getDecoratedEnd(childAt);
            if (decoratedStart < endAfterPadding && decoratedEnd > startAfterPadding) {
                if (!z) {
                    return childAt;
                }
                if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                    return childAt;
                }
                if (z2 && view == null) {
                    i += i3;
                    view = childAt;
                }
            }
            childAt = view;
            i += i3;
            view = childAt;
        }
        return view;
    }
}
