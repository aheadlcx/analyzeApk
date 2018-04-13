package android.support.v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.view.View;

public class LinearSnapHelper extends SnapHelper {
    @Nullable
    private OrientationHelper b;
    @Nullable
    private OrientationHelper c;

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

    public int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2) {
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            return -1;
        }
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View findSnapView = findSnapView(layoutManager);
        if (findSnapView == null) {
            return -1;
        }
        int position = layoutManager.getPosition(findSnapView);
        if (position == -1) {
            return -1;
        }
        PointF computeScrollVectorForPosition = ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1);
        if (computeScrollVectorForPosition == null) {
            return -1;
        }
        int a;
        int a2;
        if (layoutManager.canScrollHorizontally()) {
            a = a(layoutManager, d(layoutManager), i, 0);
            if (computeScrollVectorForPosition.x < 0.0f) {
                a = -a;
            }
        } else {
            a = 0;
        }
        if (layoutManager.canScrollVertically()) {
            a2 = a(layoutManager, c(layoutManager), 0, i2);
            if (computeScrollVectorForPosition.y < 0.0f) {
                a2 = -a2;
            }
        } else {
            a2 = 0;
        }
        if (!layoutManager.canScrollVertically()) {
            a2 = a;
        }
        if (a2 == 0) {
            return -1;
        }
        a = position + a2;
        if (a < 0) {
            a = 0;
        }
        if (a >= itemCount) {
            return itemCount - 1;
        }
        return a;
    }

    public View findSnapView(LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return a(layoutManager, c(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return a(layoutManager, d(layoutManager));
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

    private int a(LayoutManager layoutManager, OrientationHelper orientationHelper, int i, int i2) {
        int[] calculateScrollDistance = calculateScrollDistance(i, i2);
        float b = b(layoutManager, orientationHelper);
        if (b <= 0.0f) {
            return 0;
        }
        return Math.round(((float) (Math.abs(calculateScrollDistance[0]) > Math.abs(calculateScrollDistance[1]) ? calculateScrollDistance[0] : calculateScrollDistance[1])) / b);
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

    private float b(LayoutManager layoutManager, OrientationHelper orientationHelper) {
        View view = null;
        int i = Integer.MAX_VALUE;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        int i2 = 0;
        View view2 = null;
        int i3 = Integer.MIN_VALUE;
        while (i2 < childCount) {
            View view3;
            View childAt = layoutManager.getChildAt(i2);
            int position = layoutManager.getPosition(childAt);
            if (position == -1) {
                position = i;
                view3 = view;
                view = view2;
            } else {
                if (position < i) {
                    i = position;
                    view2 = childAt;
                }
                if (position > i3) {
                    i3 = position;
                    view = view2;
                    position = i;
                    view3 = childAt;
                } else {
                    position = i;
                    view3 = view;
                    view = view2;
                }
            }
            i2++;
            view2 = view;
            view = view3;
            i = position;
        }
        if (view2 == null || view == null) {
            return 1.0f;
        }
        position = Math.max(orientationHelper.getDecoratedEnd(view2), orientationHelper.getDecoratedEnd(view)) - Math.min(orientationHelper.getDecoratedStart(view2), orientationHelper.getDecoratedStart(view));
        if (position == 0) {
            return 1.0f;
        }
        return (((float) position) * 1.0f) / ((float) ((i3 - i) + 1));
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
