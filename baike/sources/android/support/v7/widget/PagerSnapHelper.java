package android.support.v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.view.View;

public class PagerSnapHelper extends SnapHelper {
    @Nullable
    private OrientationHelper b;
    @Nullable
    private OrientationHelper c;

    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = a(layoutManager, view, d(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = a(layoutManager, view, c(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Nullable
    public View findSnapView(LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return a(layoutManager, c(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return a(layoutManager, d(layoutManager));
        }
        return null;
    }

    public int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2) {
        Object obj = null;
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View view = null;
        if (layoutManager.canScrollVertically()) {
            view = b(layoutManager, c(layoutManager));
        } else if (layoutManager.canScrollHorizontally()) {
            view = b(layoutManager, d(layoutManager));
        }
        if (view == null) {
            return -1;
        }
        int position = layoutManager.getPosition(view);
        if (position == -1) {
            return -1;
        }
        Object obj2;
        if (layoutManager.canScrollHorizontally()) {
            obj2 = i > 0 ? 1 : null;
        } else if (i2 > 0) {
            int i3 = 1;
        } else {
            obj2 = null;
        }
        if (layoutManager instanceof ScrollVectorProvider) {
            PointF computeScrollVectorForPosition = ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1);
            if (computeScrollVectorForPosition != null && (computeScrollVectorForPosition.x < 0.0f || computeScrollVectorForPosition.y < 0.0f)) {
                obj = 1;
            }
        }
        if (obj == null) {
            return obj2 != null ? position + 1 : position;
        } else {
            if (obj2 != null) {
                return position - 1;
            }
            return position;
        }
    }

    protected LinearSmoothScroller a(LayoutManager layoutManager) {
        if (layoutManager instanceof ScrollVectorProvider) {
            return new bi(this, this.a.getContext());
        }
        return null;
    }

    private int a(@NonNull LayoutManager layoutManager, @NonNull View view, OrientationHelper orientationHelper) {
        int startAfterPadding;
        int decoratedMeasurement = (orientationHelper.getDecoratedMeasurement(view) / 2) + orientationHelper.getDecoratedStart(view);
        if (layoutManager.getClipToPadding()) {
            startAfterPadding = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            startAfterPadding = orientationHelper.getEnd() / 2;
        }
        return decoratedMeasurement - startAfterPadding;
    }

    @Nullable
    private View a(LayoutManager layoutManager, OrientationHelper orientationHelper) {
        View view = null;
        int childCount = layoutManager.getChildCount();
        if (childCount != 0) {
            int startAfterPadding;
            if (layoutManager.getClipToPadding()) {
                startAfterPadding = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
            } else {
                startAfterPadding = orientationHelper.getEnd() / 2;
            }
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < childCount) {
                View view2;
                View childAt = layoutManager.getChildAt(i2);
                int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) / 2)) - startAfterPadding);
                if (abs < i) {
                    view2 = childAt;
                } else {
                    abs = i;
                    view2 = view;
                }
                i2++;
                view = view2;
                i = abs;
            }
        }
        return view;
    }

    @Nullable
    private View b(LayoutManager layoutManager, OrientationHelper orientationHelper) {
        View view = null;
        int childCount = layoutManager.getChildCount();
        if (childCount != 0) {
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < childCount) {
                View view2;
                View childAt = layoutManager.getChildAt(i2);
                int decoratedStart = orientationHelper.getDecoratedStart(childAt);
                if (decoratedStart < i) {
                    view2 = childAt;
                } else {
                    decoratedStart = i;
                    view2 = view;
                }
                i2++;
                view = view2;
                i = decoratedStart;
            }
        }
        return view;
    }

    @NonNull
    private OrientationHelper c(@NonNull LayoutManager layoutManager) {
        if (this.b == null || this.b.a != layoutManager) {
            this.b = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.b;
    }

    @NonNull
    private OrientationHelper d(@NonNull LayoutManager layoutManager) {
        if (this.c == null || this.c.a != layoutManager) {
            this.c = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.c;
    }
}
