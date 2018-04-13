package com.marshalchen.ultimaterecyclerview.swipe;

import android.graphics.Rect;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.View;

class SwipeLayout$1 extends Callback {
    boolean a = true;
    final /* synthetic */ SwipeLayout b;

    SwipeLayout$1(SwipeLayout swipeLayout) {
        this.b = swipeLayout;
    }

    public int clampViewPositionHorizontal(View view, int i, int i2) {
        if (view == this.b.getSurfaceView()) {
            switch (SwipeLayout$4.a[SwipeLayout.a(this.b).ordinal()]) {
                case 1:
                case 2:
                    return this.b.getPaddingLeft();
                case 3:
                    if (i < this.b.getPaddingLeft()) {
                        return this.b.getPaddingLeft();
                    }
                    if (i > this.b.getPaddingLeft() + SwipeLayout.b(this.b)) {
                        return this.b.getPaddingLeft() + SwipeLayout.b(this.b);
                    }
                    return i;
                case 4:
                    if (i > this.b.getPaddingLeft()) {
                        return this.b.getPaddingLeft();
                    }
                    if (i < this.b.getPaddingLeft() - SwipeLayout.b(this.b)) {
                        return this.b.getPaddingLeft() - SwipeLayout.b(this.b);
                    }
                    return i;
                default:
                    return i;
            }
        } else if (this.b.getCurrentBottomView() != view) {
            return i;
        } else {
            switch (SwipeLayout$4.a[SwipeLayout.a(this.b).ordinal()]) {
                case 1:
                case 2:
                    return this.b.getPaddingLeft();
                case 3:
                    if (SwipeLayout.c(this.b) != SwipeLayout$ShowMode.PullOut || i <= this.b.getPaddingLeft()) {
                        return i;
                    }
                    return this.b.getPaddingLeft();
                case 4:
                    if (SwipeLayout.c(this.b) != SwipeLayout$ShowMode.PullOut || i >= this.b.getMeasuredWidth() - SwipeLayout.b(this.b)) {
                        return i;
                    }
                    return this.b.getMeasuredWidth() - SwipeLayout.b(this.b);
                default:
                    return i;
            }
        }
    }

    public int clampViewPositionVertical(View view, int i, int i2) {
        if (view == this.b.getSurfaceView()) {
            switch (SwipeLayout$4.a[SwipeLayout.a(this.b).ordinal()]) {
                case 1:
                    if (i < this.b.getPaddingTop()) {
                        return this.b.getPaddingTop();
                    }
                    if (i > this.b.getPaddingTop() + SwipeLayout.b(this.b)) {
                        return this.b.getPaddingTop() + SwipeLayout.b(this.b);
                    }
                    return i;
                case 2:
                    if (i < this.b.getPaddingTop() - SwipeLayout.b(this.b)) {
                        return this.b.getPaddingTop() - SwipeLayout.b(this.b);
                    }
                    if (i > this.b.getPaddingTop()) {
                        return this.b.getPaddingTop();
                    }
                    return i;
                case 3:
                case 4:
                    return this.b.getPaddingTop();
                default:
                    return i;
            }
        }
        View surfaceView = this.b.getSurfaceView();
        int top = surfaceView == null ? 0 : surfaceView.getTop();
        switch (SwipeLayout$4.a[SwipeLayout.a(this.b).ordinal()]) {
            case 1:
                if (SwipeLayout.c(this.b) == SwipeLayout$ShowMode.PullOut) {
                    if (i > this.b.getPaddingTop()) {
                        return this.b.getPaddingTop();
                    }
                    return i;
                } else if (top + i2 < this.b.getPaddingTop()) {
                    return this.b.getPaddingTop();
                } else {
                    if (top + i2 > this.b.getPaddingTop() + SwipeLayout.b(this.b)) {
                        return this.b.getPaddingTop() + SwipeLayout.b(this.b);
                    }
                    return i;
                }
            case 2:
                if (SwipeLayout.c(this.b) == SwipeLayout$ShowMode.PullOut) {
                    if (i < this.b.getMeasuredHeight() - SwipeLayout.b(this.b)) {
                        return this.b.getMeasuredHeight() - SwipeLayout.b(this.b);
                    }
                    return i;
                } else if (top + i2 >= this.b.getPaddingTop()) {
                    return this.b.getPaddingTop();
                } else {
                    if (top + i2 <= this.b.getPaddingTop() - SwipeLayout.b(this.b)) {
                        return this.b.getPaddingTop() - SwipeLayout.b(this.b);
                    }
                    return i;
                }
            case 3:
            case 4:
                return this.b.getPaddingTop();
            default:
                return i;
        }
    }

    public boolean tryCaptureView(View view, int i) {
        boolean z;
        boolean z2 = true;
        if (view == this.b.getSurfaceView() || this.b.getBottomViews().contains(view)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.b.getOpenStatus() != SwipeLayout$Status.Close) {
                z2 = false;
            }
            this.a = z2;
        }
        return z;
    }

    public int getViewHorizontalDragRange(View view) {
        return SwipeLayout.b(this.b);
    }

    public int getViewVerticalDragRange(View view) {
        return SwipeLayout.b(this.b);
    }

    public void onViewReleased(View view, float f, float f2) {
        super.onViewReleased(view, f, f2);
        this.b.a(f, f2, this.a);
        for (SwipeLayout$f a : SwipeLayout.d(this.b)) {
            a.a(this.b, f, f2);
        }
        this.b.invalidate();
    }

    public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        View surfaceView = this.b.getSurfaceView();
        if (surfaceView != null) {
            View currentBottomView = this.b.getCurrentBottomView();
            int left = surfaceView.getLeft();
            int right = surfaceView.getRight();
            int top = surfaceView.getTop();
            int bottom = surfaceView.getBottom();
            if (view == surfaceView) {
                if (SwipeLayout.c(this.b) == SwipeLayout$ShowMode.PullOut && currentBottomView != null) {
                    if (SwipeLayout.a(this.b) == SwipeLayout$DragEdge.Left || SwipeLayout.a(this.b) == SwipeLayout$DragEdge.Right) {
                        currentBottomView.offsetLeftAndRight(i3);
                    } else {
                        currentBottomView.offsetTopAndBottom(i4);
                    }
                }
            } else if (this.b.getBottomViews().contains(view)) {
                if (SwipeLayout.c(this.b) == SwipeLayout$ShowMode.PullOut) {
                    surfaceView.offsetLeftAndRight(i3);
                    surfaceView.offsetTopAndBottom(i4);
                } else {
                    Rect a = SwipeLayout.a(this.b, SwipeLayout.a(this.b));
                    if (currentBottomView != null) {
                        currentBottomView.layout(a.left, a.top, a.right, a.bottom);
                    }
                    int left2 = surfaceView.getLeft() + i3;
                    int top2 = surfaceView.getTop() + i4;
                    if (SwipeLayout.a(this.b) == SwipeLayout$DragEdge.Left && left2 < this.b.getPaddingLeft()) {
                        left2 = this.b.getPaddingLeft();
                    } else if (SwipeLayout.a(this.b) == SwipeLayout$DragEdge.Right && left2 > this.b.getPaddingLeft()) {
                        left2 = this.b.getPaddingLeft();
                    } else if (SwipeLayout.a(this.b) == SwipeLayout$DragEdge.Top && top2 < this.b.getPaddingTop()) {
                        top2 = this.b.getPaddingTop();
                    } else if (SwipeLayout.a(this.b) == SwipeLayout$DragEdge.Bottom && top2 > this.b.getPaddingTop()) {
                        top2 = this.b.getPaddingTop();
                    }
                    surfaceView.layout(left2, top2, this.b.getMeasuredWidth() + left2, this.b.getMeasuredHeight() + top2);
                }
            }
            this.b.b(left, top, right, bottom);
            this.b.a(left, top, i3, i4);
            this.b.invalidate();
        }
    }
}
