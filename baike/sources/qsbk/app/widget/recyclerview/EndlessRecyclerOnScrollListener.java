package qsbk.app.widget.recyclerview;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;

public abstract class EndlessRecyclerOnScrollListener extends OnScrollListener {
    private static final String a = EndlessRecyclerOnScrollListener.class.getSimpleName();
    private boolean b = true;
    private int c = 0;
    private boolean d = true;
    private int e = -1;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private OrientationHelper j;
    private LayoutManager k;

    public abstract void onLoadMore();

    public EndlessRecyclerOnScrollListener(LayoutManager layoutManager) {
        this.k = layoutManager;
    }

    public EndlessRecyclerOnScrollListener(int i) {
        this.e = i;
    }

    public EndlessRecyclerOnScrollListener(LayoutManager layoutManager, int i) {
        this.k = layoutManager;
        this.e = i;
    }

    private int a(RecyclerView recyclerView) {
        View a = a(0, this.k.getChildCount(), false, true);
        return a == null ? -1 : recyclerView.getChildAdapterPosition(a);
    }

    private int b(RecyclerView recyclerView) {
        View a = a(recyclerView.getChildCount() - 1, -1, false, true);
        if (a == null) {
            return -1;
        }
        return recyclerView.getChildAdapterPosition(a);
    }

    private View a(int i, int i2, boolean z, boolean z2) {
        if (this.k.canScrollVertically() != this.i || this.j == null) {
            OrientationHelper createVerticalHelper;
            this.i = this.k.canScrollVertically();
            if (this.i) {
                createVerticalHelper = OrientationHelper.createVerticalHelper(this.k);
            } else {
                createVerticalHelper = OrientationHelper.createHorizontalHelper(this.k);
            }
            this.j = createVerticalHelper;
        }
        int startAfterPadding = this.j.getStartAfterPadding();
        int endAfterPadding = this.j.getEndAfterPadding();
        int i3 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View childAt = this.k.getChildAt(i);
            if (childAt != null) {
                int decoratedStart = this.j.getDecoratedStart(childAt);
                int decoratedEnd = this.j.getDecoratedEnd(childAt);
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
            }
            childAt = view;
            i += i3;
            view = childAt;
        }
        return view;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (this.b) {
            if (this.k == null) {
                this.k = recyclerView.getLayoutManager();
            }
            if (this.e == -1) {
                this.e = (b(recyclerView) - a(recyclerView)) - 0;
            }
            this.g = recyclerView.getChildCount() - 0;
            this.h = this.k.getItemCount() - 0;
            this.f = a(recyclerView);
            if (this.d && this.h > this.c) {
                this.d = false;
                this.c = this.h;
                Log.i(a, "not loading more");
            }
            if (!this.d && this.h - this.g <= this.f + this.e) {
                Log.i(a, "onLoadMore() total = " + this.h + "visibcount = " + this.g);
                onLoadMore();
                this.d = true;
            }
        }
    }

    public EndlessRecyclerOnScrollListener enable() {
        this.b = true;
        return this;
    }

    public EndlessRecyclerOnScrollListener disable() {
        this.b = false;
        return this;
    }

    public LayoutManager getLayoutManager() {
        return this.k;
    }

    public int getTotalItemCount() {
        return this.h;
    }

    public int getFirstVisibleItem() {
        return this.f;
    }

    public int getVisibleItemCount() {
        return this.g;
    }

    public void reset() {
        this.c = 0;
        this.d = false;
    }
}
