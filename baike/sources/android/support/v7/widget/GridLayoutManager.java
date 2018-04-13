package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
    public static final int DEFAULT_SPAN_COUNT = -1;
    boolean a = false;
    int b = -1;
    int[] c;
    View[] d;
    final SparseIntArray e = new SparseIntArray();
    final SparseIntArray f = new SparseIntArray();
    SpanSizeLookup g = new DefaultSpanSizeLookup();
    final Rect h = new Rect();

    public static abstract class SpanSizeLookup {
        final SparseIntArray a = new SparseIntArray();
        private boolean b = false;

        public abstract int getSpanSize(int i);

        public void setSpanIndexCacheEnabled(boolean z) {
            this.b = z;
        }

        public void invalidateSpanIndexCache() {
            this.a.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.b;
        }

        int a(int i, int i2) {
            if (!this.b) {
                return getSpanIndex(i, i2);
            }
            int i3 = this.a.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            i3 = getSpanIndex(i, i2);
            this.a.put(i, i3);
            return i3;
        }

        public int getSpanIndex(int i, int i2) {
            int spanSize = getSpanSize(i);
            if (spanSize == i2) {
                return 0;
            }
            int a;
            int spanSize2;
            int i3;
            if (this.b && this.a.size() > 0) {
                a = a(i);
                if (a >= 0) {
                    spanSize2 = this.a.get(a) + getSpanSize(a);
                    a++;
                    i3 = a;
                    while (i3 < i) {
                        a = getSpanSize(i3);
                        spanSize2 += a;
                        if (spanSize2 == i2) {
                            a = 0;
                        } else if (spanSize2 <= i2) {
                            a = spanSize2;
                        }
                        i3++;
                        spanSize2 = a;
                    }
                    if (spanSize2 + spanSize > i2) {
                        return spanSize2;
                    }
                    return 0;
                }
            }
            a = 0;
            spanSize2 = 0;
            i3 = a;
            while (i3 < i) {
                a = getSpanSize(i3);
                spanSize2 += a;
                if (spanSize2 == i2) {
                    a = 0;
                } else if (spanSize2 <= i2) {
                    a = spanSize2;
                }
                i3++;
                spanSize2 = a;
            }
            if (spanSize2 + spanSize > i2) {
                return 0;
            }
            return spanSize2;
        }

        int a(int i) {
            int i2 = 0;
            int size = this.a.size() - 1;
            while (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                if (this.a.keyAt(i3) < i) {
                    i2 = i3 + 1;
                } else {
                    size = i3 - 1;
                }
            }
            size = i2 - 1;
            if (size < 0 || size >= this.a.size()) {
                return -1;
            }
            return this.a.keyAt(size);
        }

        public int getSpanGroupIndex(int i, int i2) {
            int spanSize = getSpanSize(i);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i3 < i) {
                int spanSize2 = getSpanSize(i3);
                i5 += spanSize2;
                if (i5 == i2) {
                    i4++;
                    spanSize2 = 0;
                } else if (i5 > i2) {
                    i4++;
                } else {
                    spanSize2 = i5;
                }
                i3++;
                i5 = spanSize2;
            }
            if (i5 + spanSize > i2) {
                return i4 + 1;
            }
            return i4;
        }
    }

    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        public int getSpanSize(int i) {
            return 1;
        }

        public int getSpanIndex(int i, int i2) {
            return i % i2;
        }
    }

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        int a = -1;
        int b = 0;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public int getSpanIndex() {
            return this.a;
        }

        public int getSpanSize() {
            return this.b;
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setSpanCount(LayoutManager.getProperties(context, attributeSet, i, i2).spanCount);
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        setSpanCount(i);
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i2, z);
        setSpanCount(i);
    }

    public void setStackFromEnd(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    public int getRowCountForAccessibility(Recycler recycler, State state) {
        if (this.i == 0) {
            return this.b;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return a(recycler, state, state.getItemCount() - 1) + 1;
    }

    public int getColumnCountForAccessibility(Recycler recycler, State state) {
        if (this.i == 1) {
            return this.b;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return a(recycler, state, state.getItemCount() - 1) + 1;
    }

    public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            int a = a(recycler, state, layoutParams2.getViewLayoutPosition());
            if (this.i == 0) {
                int spanIndex = layoutParams2.getSpanIndex();
                int spanSize = layoutParams2.getSpanSize();
                boolean z = this.b > 1 && layoutParams2.getSpanSize() == this.b;
                accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(spanIndex, spanSize, a, 1, z, false));
                return;
            }
            int spanIndex2 = layoutParams2.getSpanIndex();
            int spanSize2 = layoutParams2.getSpanSize();
            boolean z2 = this.b > 1 && layoutParams2.getSpanSize() == this.b;
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(a, 1, spanIndex2, spanSize2, z2, false));
            return;
        }
        super.a(view, accessibilityNodeInfoCompat);
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        if (state.isPreLayout()) {
            i();
        }
        super.onLayoutChildren(recycler, state);
        h();
    }

    public void onLayoutCompleted(State state) {
        super.onLayoutCompleted(state);
        this.a = false;
    }

    private void h() {
        this.e.clear();
        this.f.clear();
    }

    private void i() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            this.e.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.f.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        this.g.invalidateSpanIndexCache();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.g.invalidateSpanIndexCache();
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        this.g.invalidateSpanIndexCache();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.g.invalidateSpanIndexCache();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        this.g.invalidateSpanIndexCache();
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.i == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public boolean checkLayoutParams(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.g = spanSizeLookup;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.g;
    }

    private void j() {
        int width;
        if (getOrientation() == 1) {
            width = (getWidth() - getPaddingRight()) - getPaddingLeft();
        } else {
            width = (getHeight() - getPaddingBottom()) - getPaddingTop();
        }
        b(width);
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        if (this.c == null) {
            super.setMeasuredDimension(rect, i, i2);
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.i == 1) {
            paddingTop = LayoutManager.chooseSize(i2, paddingTop + rect.height(), getMinimumHeight());
            paddingRight = LayoutManager.chooseSize(i, paddingRight + this.c[this.c.length - 1], getMinimumWidth());
        } else {
            paddingRight = LayoutManager.chooseSize(i, paddingRight + rect.width(), getMinimumWidth());
            paddingTop = LayoutManager.chooseSize(i2, paddingTop + this.c[this.c.length - 1], getMinimumHeight());
        }
        setMeasuredDimension(paddingRight, paddingTop);
    }

    private void b(int i) {
        this.c = a(this.c, this.b, i);
    }

    static int[] a(int[] iArr, int i, int i2) {
        int i3 = 0;
        if (!(iArr != null && iArr.length == i + 1 && iArr[iArr.length - 1] == i2)) {
            iArr = new int[(i + 1)];
        }
        iArr[0] = 0;
        int i4 = i2 / i;
        int i5 = i2 % i;
        int i6 = 0;
        for (int i7 = 1; i7 <= i; i7++) {
            int i8;
            i3 += i5;
            if (i3 <= 0 || i - i3 >= i5) {
                i8 = i4;
            } else {
                i8 = i4 + 1;
                i3 -= i;
            }
            i6 += i8;
            iArr[i7] = i6;
        }
        return iArr;
    }

    int a(int i, int i2) {
        if (this.i == 1 && a()) {
            return this.c[this.b - i] - this.c[(this.b - i) - i2];
        }
        return this.c[i + i2] - this.c[i];
    }

    void a(Recycler recycler, State state, a aVar, int i) {
        super.a(recycler, state, aVar, i);
        j();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            b(recycler, state, aVar, i);
        }
        k();
    }

    private void k() {
        if (this.d == null || this.d.length != this.b) {
            this.d = new View[this.b];
        }
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        j();
        k();
        return super.scrollHorizontallyBy(i, recycler, state);
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        j();
        k();
        return super.scrollVerticallyBy(i, recycler, state);
    }

    private void b(Recycler recycler, State state, a aVar, int i) {
        Object obj = 1;
        if (i != 1) {
            obj = null;
        }
        int b = b(recycler, state, aVar.a);
        if (obj != null) {
            while (b > 0 && aVar.a > 0) {
                aVar.a--;
                b = b(recycler, state, aVar.a);
            }
            return;
        }
        int itemCount = state.getItemCount() - 1;
        int i2 = aVar.a;
        int i3 = b;
        while (i2 < itemCount) {
            b = b(recycler, state, i2 + 1);
            if (b <= i3) {
                break;
            }
            i2++;
            i3 = b;
        }
        aVar.a = i2;
    }

    View a(Recycler recycler, State state, int i, int i2, int i3) {
        View view = null;
        b();
        int startAfterPadding = this.j.getStartAfterPadding();
        int endAfterPadding = this.j.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view2 = null;
        while (i != i2) {
            View view3;
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (b(recycler, state, position) != 0) {
                    view3 = view;
                    childAt = view2;
                } else if (((android.support.v7.widget.RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view3 = view;
                    }
                } else if (this.j.getDecoratedStart(childAt) < endAfterPadding && this.j.getDecoratedEnd(childAt) >= startAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view3 = childAt;
                        childAt = view2;
                    }
                }
                i += i4;
                view = view3;
                view2 = childAt;
            }
            view3 = view;
            childAt = view2;
            i += i4;
            view = view3;
            view2 = childAt;
        }
        if (view == null) {
            view = view2;
        }
        return view;
    }

    private int a(Recycler recycler, State state, int i) {
        if (!state.isPreLayout()) {
            return this.g.getSpanGroupIndex(i, this.b);
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.g.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.b);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    private int b(Recycler recycler, State state, int i) {
        if (!state.isPreLayout()) {
            return this.g.a(i, this.b);
        }
        int i2 = this.f.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        i2 = recycler.convertPreLayoutPositionToPostLayout(i);
        if (i2 != -1) {
            return this.g.a(i2, this.b);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    private int c(Recycler recycler, State state, int i) {
        if (!state.isPreLayout()) {
            return this.g.getSpanSize(i);
        }
        int i2 = this.e.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        i2 = recycler.convertPreLayoutPositionToPostLayout(i);
        if (i2 != -1) {
            return this.g.getSpanSize(i2);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    void a(State state, b bVar, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i = this.b;
        for (int i2 = 0; i2 < this.b && bVar.a(state) && i > 0; i2++) {
            int i3 = bVar.d;
            layoutPrefetchRegistry.addPosition(i3, Math.max(0, bVar.g));
            i -= this.g.getSpanSize(i3);
            bVar.d += bVar.e;
        }
    }

    void a(Recycler recycler, State state, b bVar, LayoutChunkResult layoutChunkResult) {
        int modeInOther = this.j.getModeInOther();
        Object obj = modeInOther != 1073741824 ? 1 : null;
        int i = getChildCount() > 0 ? this.c[this.b] : 0;
        if (obj != null) {
            j();
        }
        boolean z = bVar.e == 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = this.b;
        if (!z) {
            i4 = b(recycler, state, bVar.d) + c(recycler, state, bVar.d);
        }
        while (i2 < this.b && bVar.a(state) && i4 > 0) {
            int i5 = bVar.d;
            int c = c(recycler, state, i5);
            if (c <= this.b) {
                i4 -= c;
                if (i4 >= 0) {
                    View a = bVar.a(recycler);
                    if (a == null) {
                        break;
                    }
                    i3 += c;
                    this.d[i2] = a;
                    i2++;
                } else {
                    break;
                }
            }
            throw new IllegalArgumentException("Item at position " + i5 + " requires " + c + " spans but GridLayoutManager has only " + this.b + " spans.");
        }
        if (i2 == 0) {
            layoutChunkResult.mFinished = true;
            return;
        }
        LayoutParams layoutParams;
        int i6;
        int a2;
        a(recycler, state, i2, i3, z);
        i3 = 0;
        float f = 0.0f;
        i5 = 0;
        while (i3 < i2) {
            View view = this.d[i3];
            if (bVar.k == null) {
                if (z) {
                    addView(view);
                } else {
                    addView(view, 0);
                }
            } else if (z) {
                addDisappearingView(view);
            } else {
                addDisappearingView(view, 0);
            }
            calculateItemDecorationsForChild(view, this.h);
            a(view, modeInOther, false);
            i4 = this.j.getDecoratedMeasurement(view);
            if (i4 > i5) {
                i5 = i4;
            }
            float decoratedMeasurementInOther = (((float) this.j.getDecoratedMeasurementInOther(view)) * 1.0f) / ((float) ((LayoutParams) view.getLayoutParams()).b);
            if (decoratedMeasurementInOther <= f) {
                decoratedMeasurementInOther = f;
            }
            i3++;
            f = decoratedMeasurementInOther;
        }
        if (obj != null) {
            a(f, i);
            i5 = 0;
            c = 0;
            while (c < i2) {
                View view2 = this.d[c];
                a(view2, 1073741824, true);
                i4 = this.j.getDecoratedMeasurement(view2);
                if (i4 <= i5) {
                    i4 = i5;
                }
                c++;
                i5 = i4;
            }
        }
        for (i3 = 0; i3 < i2; i3++) {
            View view3 = this.d[i3];
            if (this.j.getDecoratedMeasurement(view3) != i5) {
                layoutParams = (LayoutParams) view3.getLayoutParams();
                Rect rect = layoutParams.d;
                i6 = ((rect.top + rect.bottom) + layoutParams.topMargin) + layoutParams.bottomMargin;
                c = ((rect.right + rect.left) + layoutParams.leftMargin) + layoutParams.rightMargin;
                a2 = a(layoutParams.a, layoutParams.b);
                if (this.i == 1) {
                    c = LayoutManager.getChildMeasureSpec(a2, 1073741824, c, layoutParams.width, false);
                    i4 = MeasureSpec.makeMeasureSpec(i5 - i6, 1073741824);
                } else {
                    c = MeasureSpec.makeMeasureSpec(i5 - c, 1073741824);
                    i4 = LayoutManager.getChildMeasureSpec(a2, 1073741824, i6, layoutParams.height, false);
                }
                a(view3, c, i4, true);
            }
        }
        layoutChunkResult.mConsumed = i5;
        i4 = 0;
        if (this.i == 1) {
            if (bVar.f == -1) {
                i4 = bVar.b;
                i5 = i4 - i5;
                c = 0;
                i3 = 0;
            } else {
                c = bVar.b;
                i4 = c + i5;
                i5 = c;
                c = 0;
                i3 = 0;
            }
        } else if (bVar.f == -1) {
            i3 = bVar.b;
            c = i3;
            i3 -= i5;
            i5 = 0;
        } else {
            i3 = bVar.b;
            c = i5 + i3;
            i5 = 0;
        }
        i = i4;
        a2 = i5;
        int i7 = c;
        i6 = i3;
        for (i5 = 0; i5 < i2; i5++) {
            view3 = this.d[i5];
            layoutParams = (LayoutParams) view3.getLayoutParams();
            if (this.i != 1) {
                a2 = getPaddingTop() + this.c[layoutParams.a];
                i = a2 + this.j.getDecoratedMeasurementInOther(view3);
            } else if (a()) {
                i7 = getPaddingLeft() + this.c[this.b - layoutParams.a];
                i6 = i7 - this.j.getDecoratedMeasurementInOther(view3);
            } else {
                i6 = getPaddingLeft() + this.c[layoutParams.a];
                i7 = i6 + this.j.getDecoratedMeasurementInOther(view3);
            }
            layoutDecoratedWithMargins(view3, i6, a2, i7, i);
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                layoutChunkResult.mIgnoreConsumed = true;
            }
            layoutChunkResult.mFocusable |= view3.hasFocusable();
        }
        Arrays.fill(this.d, null);
    }

    private void a(View view, int i, boolean z) {
        int childMeasureSpec;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.d;
        int i2 = ((rect.top + rect.bottom) + layoutParams.topMargin) + layoutParams.bottomMargin;
        int i3 = layoutParams.rightMargin + ((rect.right + rect.left) + layoutParams.leftMargin);
        int a = a(layoutParams.a, layoutParams.b);
        if (this.i == 1) {
            a = LayoutManager.getChildMeasureSpec(a, i, i3, layoutParams.width, false);
            childMeasureSpec = LayoutManager.getChildMeasureSpec(this.j.getTotalSpace(), getHeightMode(), i2, layoutParams.height, true);
        } else {
            int childMeasureSpec2 = LayoutManager.getChildMeasureSpec(a, i, i2, layoutParams.height, false);
            a = LayoutManager.getChildMeasureSpec(this.j.getTotalSpace(), getWidthMode(), i3, layoutParams.width, true);
            childMeasureSpec = childMeasureSpec2;
        }
        a(view, a, childMeasureSpec, z);
    }

    private void a(float f, int i) {
        b(Math.max(Math.round(((float) this.b) * f), i));
    }

    private void a(View view, int i, int i2, boolean z) {
        boolean a;
        android.support.v7.widget.RecyclerView.LayoutParams layoutParams = (android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams();
        if (z) {
            a = a(view, i, i2, layoutParams);
        } else {
            a = b(view, i, i2, layoutParams);
        }
        if (a) {
            view.measure(i, i2);
        }
    }

    private void a(Recycler recycler, State state, int i, int i2, boolean z) {
        int i3;
        int i4;
        if (z) {
            i3 = 1;
            i4 = 0;
        } else {
            i3 = i - 1;
            i = -1;
            i4 = i3;
            i3 = -1;
        }
        int i5 = 0;
        for (int i6 = i4; i6 != i; i6 += i3) {
            View view = this.d[i6];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.b = c(recycler, state, getPosition(view));
            layoutParams.a = i5;
            i5 += layoutParams.b;
        }
    }

    public int getSpanCount() {
        return this.b;
    }

    public void setSpanCount(int i) {
        if (i != this.b) {
            this.a = true;
            if (i < 1) {
                throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
            }
            this.b = i;
            this.g.invalidateSpanIndexCache();
            requestLayout();
        }
    }

    public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        int i2 = layoutParams.a;
        int i3 = layoutParams.a + layoutParams.b;
        if (super.onFocusSearchFailed(view, i, recycler, state) == null) {
            return null;
        }
        int childCount;
        int i4;
        int i5;
        if (((a(i) == 1) != this.k ? 1 : null) != null) {
            childCount = getChildCount() - 1;
            i4 = -1;
            i5 = -1;
        } else {
            childCount = 0;
            i4 = 1;
            i5 = getChildCount();
        }
        Object obj = (this.i == 1 && a()) ? 1 : null;
        View view2 = null;
        int i6 = -1;
        int i7 = 0;
        View view3 = null;
        int i8 = -1;
        int i9 = 0;
        int a = a(recycler, state, childCount);
        int i10 = childCount;
        while (i10 != i5) {
            childCount = a(recycler, state, i10);
            View childAt = getChildAt(i10);
            if (childAt == findContainingItemView) {
                break;
            }
            View view4;
            int min;
            View view5;
            int i11;
            if (childAt.hasFocusable() && childCount != a) {
                if (view2 != null) {
                    break;
                }
            }
            layoutParams = (LayoutParams) childAt.getLayoutParams();
            int i12 = layoutParams.a;
            int i13 = layoutParams.a + layoutParams.b;
            if (childAt.hasFocusable() && i12 == i2 && i13 == i3) {
                return childAt;
            }
            Object obj2 = null;
            if (!(childAt.hasFocusable() && view2 == null) && (childAt.hasFocusable() || view3 != null)) {
                int min2 = Math.min(i13, i3) - Math.max(i12, i2);
                if (childAt.hasFocusable()) {
                    if (min2 > i7) {
                        obj2 = 1;
                    } else if (min2 == i7) {
                        if (obj == (i12 > i6 ? 1 : null)) {
                            obj2 = 1;
                        }
                    }
                } else if (view2 == null && isViewPartiallyVisible(childAt, false, true)) {
                    if (min2 > i9) {
                        obj2 = 1;
                    } else if (min2 == i9) {
                        if (obj == (i12 > i8 ? 1 : null)) {
                            obj2 = 1;
                        }
                    }
                }
            } else {
                obj2 = 1;
            }
            if (obj2 != null) {
                if (childAt.hasFocusable()) {
                    i7 = layoutParams.a;
                    int i14 = i9;
                    i9 = i8;
                    view4 = view3;
                    min = Math.min(i13, i3) - Math.max(i12, i2);
                    childCount = i14;
                    int i15 = i7;
                    view5 = childAt;
                    i11 = i15;
                } else {
                    i9 = layoutParams.a;
                    childCount = Math.min(i13, i3) - Math.max(i12, i2);
                    view4 = childAt;
                    min = i7;
                    i11 = i6;
                    view5 = view2;
                }
                i10 += i4;
                view2 = view5;
                i7 = min;
                i6 = i11;
                view3 = view4;
                i8 = i9;
                i9 = childCount;
            }
            childCount = i9;
            i11 = i6;
            i9 = i8;
            view4 = view3;
            min = i7;
            view5 = view2;
            i10 += i4;
            view2 = view5;
            i7 = min;
            i6 = i11;
            view3 = view4;
            i8 = i9;
            i9 = childCount;
        }
        if (view2 == null) {
            view2 = view3;
        }
        return view2;
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.n == null && !this.a;
    }
}
