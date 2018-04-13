package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnFlingListener;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

public abstract class SnapHelper extends OnFlingListener {
    RecyclerView a;
    private Scroller b;
    private final OnScrollListener c = new cl(this);

    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull LayoutManager layoutManager, @NonNull View view);

    @Nullable
    public abstract View findSnapView(LayoutManager layoutManager);

    public abstract int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2);

    public boolean onFling(int i, int i2) {
        LayoutManager layoutManager = this.a.getLayoutManager();
        if (layoutManager == null || this.a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.a.getMinFlingVelocity();
        if ((Math.abs(i2) > minFlingVelocity || Math.abs(i) > minFlingVelocity) && a(layoutManager, i, i2)) {
            return true;
        }
        return false;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        if (this.a != recyclerView) {
            if (this.a != null) {
                c();
            }
            this.a = recyclerView;
            if (this.a != null) {
                b();
                this.b = new Scroller(this.a.getContext(), new DecelerateInterpolator());
                a();
            }
        }
    }

    private void b() throws IllegalStateException {
        if (this.a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.a.addOnScrollListener(this.c);
        this.a.setOnFlingListener(this);
    }

    private void c() {
        this.a.removeOnScrollListener(this.c);
        this.a.setOnFlingListener(null);
    }

    public int[] calculateScrollDistance(int i, int i2) {
        r9 = new int[2];
        this.b.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        r9[0] = this.b.getFinalX();
        r9[1] = this.b.getFinalY();
        return r9;
    }

    private boolean a(@NonNull LayoutManager layoutManager, int i, int i2) {
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            return false;
        }
        SmoothScroller b = b(layoutManager);
        if (b == null) {
            return false;
        }
        int findTargetSnapPosition = findTargetSnapPosition(layoutManager, i, i2);
        if (findTargetSnapPosition == -1) {
            return false;
        }
        b.setTargetPosition(findTargetSnapPosition);
        layoutManager.startSmoothScroll(b);
        return true;
    }

    void a() {
        if (this.a != null) {
            LayoutManager layoutManager = this.a.getLayoutManager();
            if (layoutManager != null) {
                View findSnapView = findSnapView(layoutManager);
                if (findSnapView != null) {
                    int[] calculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, findSnapView);
                    if (calculateDistanceToFinalSnap[0] != 0 || calculateDistanceToFinalSnap[1] != 0) {
                        this.a.smoothScrollBy(calculateDistanceToFinalSnap[0], calculateDistanceToFinalSnap[1]);
                    }
                }
            }
        }
    }

    @Nullable
    protected SmoothScroller b(LayoutManager layoutManager) {
        return a(layoutManager);
    }

    @Nullable
    @Deprecated
    protected LinearSmoothScroller a(LayoutManager layoutManager) {
        if (layoutManager instanceof ScrollVectorProvider) {
            return new cm(this, this.a.getContext());
        }
        return null;
    }
}
