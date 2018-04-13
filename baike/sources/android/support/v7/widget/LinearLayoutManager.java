package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry;
import android.support.v7.widget.RecyclerView.LayoutManager.Properties;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.ViewDropHandler;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class LinearLayoutManager extends LayoutManager implements ScrollVectorProvider, ViewDropHandler {
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    private b a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private final LayoutChunkResult g;
    private int h;
    int i;
    OrientationHelper j;
    boolean k;
    int l;
    int m;
    SavedState n;
    final a o;

    protected static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        protected LayoutChunkResult() {
        }

        void a() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new bb();
        int a;
        int b;
        boolean c;

        SavedState(Parcel parcel) {
            boolean z = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.c = z;
        }

        public SavedState(SavedState savedState) {
            this.a = savedState.a;
            this.b = savedState.b;
            this.c = savedState.c;
        }

        boolean a() {
            return this.a >= 0;
        }

        void b() {
            this.a = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }
    }

    class a {
        int a;
        int b;
        boolean c;
        boolean d;
        final /* synthetic */ LinearLayoutManager e;

        a(LinearLayoutManager linearLayoutManager) {
            this.e = linearLayoutManager;
            a();
        }

        void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
        }

        void b() {
            int endAfterPadding;
            if (this.c) {
                endAfterPadding = this.e.j.getEndAfterPadding();
            } else {
                endAfterPadding = this.e.j.getStartAfterPadding();
            }
            this.b = endAfterPadding;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.a + ", mCoordinate=" + this.b + ", mLayoutFromEnd=" + this.c + ", mValid=" + this.d + '}';
        }

        boolean a(View view, State state) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
        }

        public void assignFromViewAndKeepVisibleRect(View view) {
            int totalSpaceChange = this.e.j.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                assignFromView(view);
                return;
            }
            this.a = this.e.getPosition(view);
            int decoratedMeasurement;
            if (this.c) {
                totalSpaceChange = (this.e.j.getEndAfterPadding() - totalSpaceChange) - this.e.j.getDecoratedEnd(view);
                this.b = this.e.j.getEndAfterPadding() - totalSpaceChange;
                if (totalSpaceChange > 0) {
                    decoratedMeasurement = this.b - this.e.j.getDecoratedMeasurement(view);
                    int startAfterPadding = this.e.j.getStartAfterPadding();
                    decoratedMeasurement -= startAfterPadding + Math.min(this.e.j.getDecoratedStart(view) - startAfterPadding, 0);
                    if (decoratedMeasurement < 0) {
                        this.b = Math.min(totalSpaceChange, -decoratedMeasurement) + this.b;
                        return;
                    }
                    return;
                }
                return;
            }
            decoratedMeasurement = this.e.j.getDecoratedStart(view);
            startAfterPadding = decoratedMeasurement - this.e.j.getStartAfterPadding();
            this.b = decoratedMeasurement;
            if (startAfterPadding > 0) {
                totalSpaceChange = (this.e.j.getEndAfterPadding() - Math.min(0, (this.e.j.getEndAfterPadding() - totalSpaceChange) - this.e.j.getDecoratedEnd(view))) - (decoratedMeasurement + this.e.j.getDecoratedMeasurement(view));
                if (totalSpaceChange < 0) {
                    this.b -= Math.min(startAfterPadding, -totalSpaceChange);
                }
            }
        }

        public void assignFromView(View view) {
            if (this.c) {
                this.b = this.e.j.getDecoratedEnd(view) + this.e.j.getTotalSpaceChange();
            } else {
                this.b = this.e.j.getDecoratedStart(view);
            }
            this.a = this.e.getPosition(view);
        }
    }

    static class b {
        boolean a = true;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h = 0;
        boolean i = false;
        int j;
        List<ViewHolder> k = null;
        boolean l;

        b() {
        }

        boolean a(State state) {
            return this.d >= 0 && this.d < state.getItemCount();
        }

        View a(Recycler recycler) {
            if (this.k != null) {
                return a();
            }
            View viewForPosition = recycler.getViewForPosition(this.d);
            this.d += this.e;
            return viewForPosition;
        }

        private View a() {
            int size = this.k.size();
            for (int i = 0; i < size; i++) {
                View view = ((ViewHolder) this.k.get(i)).itemView;
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if (!layoutParams.isItemRemoved() && this.d == layoutParams.getViewLayoutPosition()) {
                    assignPositionFromScrapList(view);
                    return view;
                }
            }
            return null;
        }

        public void assignPositionFromScrapList() {
            assignPositionFromScrapList(null);
        }

        public void assignPositionFromScrapList(View view) {
            View nextViewInLimitedList = nextViewInLimitedList(view);
            if (nextViewInLimitedList == null) {
                this.d = -1;
            } else {
                this.d = ((LayoutParams) nextViewInLimitedList.getLayoutParams()).getViewLayoutPosition();
            }
        }

        public View nextViewInLimitedList(View view) {
            int size = this.k.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < size) {
                int i3;
                View view3;
                View view4 = ((ViewHolder) this.k.get(i2)).itemView;
                LayoutParams layoutParams = (LayoutParams) view4.getLayoutParams();
                if (view4 != view) {
                    if (layoutParams.isItemRemoved()) {
                        i3 = i;
                        view3 = view2;
                    } else {
                        i3 = (layoutParams.getViewLayoutPosition() - this.d) * this.e;
                        if (i3 < 0) {
                            i3 = i;
                            view3 = view2;
                        } else if (i3 < i) {
                            if (i3 == 0) {
                                return view4;
                            }
                            view3 = view4;
                        }
                    }
                    i2++;
                    view2 = view3;
                    i = i3;
                }
                i3 = i;
                view3 = view2;
                i2++;
                view2 = view3;
                i = i3;
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.n = null;
        this.o = new a(this);
        this.g = new LayoutChunkResult();
        this.h = 2;
        setOrientation(i);
        setReverseLayout(z);
        setAutoMeasureEnabled(true);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.n = null;
        this.o = new a(this);
        this.g = new LayoutChunkResult();
        this.h = 2;
        Properties properties = LayoutManager.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
        setAutoMeasureEnabled(true);
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.f;
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.f = z;
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.f) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    public Parcelable onSaveInstanceState() {
        if (this.n != null) {
            return new SavedState(this.n);
        }
        Parcelable savedState = new SavedState();
        if (getChildCount() > 0) {
            b();
            boolean z = this.b ^ this.k;
            savedState.c = z;
            View j;
            if (z) {
                j = j();
                savedState.b = this.j.getEndAfterPadding() - this.j.getDecoratedEnd(j);
                savedState.a = getPosition(j);
                return savedState;
            }
            j = i();
            savedState.a = getPosition(j);
            savedState.b = this.j.getDecoratedStart(j) - this.j.getStartAfterPadding();
            return savedState;
        }
        savedState.b();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.n = (SavedState) parcelable;
            requestLayout();
        }
    }

    public boolean canScrollHorizontally() {
        return this.i == 0;
    }

    public boolean canScrollVertically() {
        return this.i == 1;
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.d != z) {
            this.d = z;
            requestLayout();
        }
    }

    public boolean getStackFromEnd() {
        return this.d;
    }

    public int getOrientation() {
        return this.i;
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll(null);
            if (i != this.i) {
                this.i = i;
                this.j = null;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + i);
    }

    private void h() {
        boolean z = true;
        if (this.i == 1 || !a()) {
            this.k = this.c;
            return;
        }
        if (this.c) {
            z = false;
        }
        this.k = z;
    }

    public boolean getReverseLayout() {
        return this.c;
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z != this.c) {
            this.c = z;
            requestLayout();
        }
    }

    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    protected int a(State state) {
        if (state.hasTargetScrollPosition()) {
            return this.j.getTotalSpace();
        }
        return 0;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        SmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public PointF computeScrollVectorForPosition(int i) {
        int i2 = 1;
        boolean z = false;
        if (getChildCount() == 0) {
            return null;
        }
        if (i < getPosition(getChildAt(0))) {
            z = true;
        }
        if (z != this.k) {
            i2 = -1;
        }
        if (this.i == 0) {
            return new PointF((float) i2, 0.0f);
        }
        return new PointF(0.0f, (float) i2);
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        int i = -1;
        if (!(this.n == null && this.l == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        int i2;
        int endAfterPadding;
        if (this.n != null && this.n.a()) {
            this.l = this.n.a;
        }
        b();
        this.a.a = false;
        h();
        View focusedChild = getFocusedChild();
        if (!this.o.d || this.l != -1 || this.n != null) {
            this.o.a();
            this.o.c = this.k ^ this.d;
            a(recycler, state, this.o);
            this.o.d = true;
        } else if (focusedChild != null && (this.j.getDecoratedStart(focusedChild) >= this.j.getEndAfterPadding() || this.j.getDecoratedEnd(focusedChild) <= this.j.getStartAfterPadding())) {
            this.o.assignFromViewAndKeepVisibleRect(focusedChild);
        }
        int a = a(state);
        if (this.a.j >= 0) {
            i2 = 0;
        } else {
            i2 = a;
            a = 0;
        }
        i2 += this.j.getStartAfterPadding();
        a += this.j.getEndPadding();
        if (!(!state.isPreLayout() || this.l == -1 || this.m == Integer.MIN_VALUE)) {
            View findViewByPosition = findViewByPosition(this.l);
            if (findViewByPosition != null) {
                if (this.k) {
                    endAfterPadding = (this.j.getEndAfterPadding() - this.j.getDecoratedEnd(findViewByPosition)) - this.m;
                } else {
                    endAfterPadding = this.m - (this.j.getDecoratedStart(findViewByPosition) - this.j.getStartAfterPadding());
                }
                if (endAfterPadding > 0) {
                    i2 += endAfterPadding;
                } else {
                    a -= endAfterPadding;
                }
            }
        }
        if (this.o.c) {
            if (this.k) {
                i = 1;
            }
        } else if (!this.k) {
            i = 1;
        }
        a(recycler, state, this.o, i);
        detachAndScrapAttachedViews(recycler);
        this.a.l = d();
        this.a.i = state.isPreLayout();
        if (this.o.c) {
            b(this.o);
            this.a.h = i2;
            a(recycler, this.a, state, false);
            i2 = this.a.b;
            endAfterPadding = this.a.d;
            if (this.a.c > 0) {
                a += this.a.c;
            }
            a(this.o);
            this.a.h = a;
            b bVar = this.a;
            bVar.d += this.a.e;
            a(recycler, this.a, state, false);
            i = this.a.b;
            if (this.a.c > 0) {
                a = this.a.c;
                e(endAfterPadding, i2);
                this.a.h = a;
                a(recycler, this.a, state, false);
                a = this.a.b;
            } else {
                a = i2;
            }
            i2 = a;
            a = i;
        } else {
            a(this.o);
            this.a.h = a;
            a(recycler, this.a, state, false);
            a = this.a.b;
            i = this.a.d;
            if (this.a.c > 0) {
                i2 += this.a.c;
            }
            b(this.o);
            this.a.h = i2;
            b bVar2 = this.a;
            bVar2.d += this.a.e;
            a(recycler, this.a, state, false);
            i2 = this.a.b;
            if (this.a.c > 0) {
                endAfterPadding = this.a.c;
                a(i, a);
                this.a.h = endAfterPadding;
                a(recycler, this.a, state, false);
                a = this.a.b;
            }
        }
        if (getChildCount() > 0) {
            int b;
            if ((this.k ^ this.d) != 0) {
                i = a(a, recycler, state, true);
                i2 += i;
                a += i;
                b = b(i2, recycler, state, false);
                i2 += b;
                a += b;
            } else {
                i = b(i2, recycler, state, true);
                i2 += i;
                a += i;
                b = a(a, recycler, state, false);
                i2 += b;
                a += b;
            }
        }
        a(recycler, state, i2, a);
        if (state.isPreLayout()) {
            this.o.a();
        } else {
            this.j.onLayoutComplete();
        }
        this.b = this.d;
    }

    public void onLayoutCompleted(State state) {
        super.onLayoutCompleted(state);
        this.n = null;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.o.a();
    }

    void a(Recycler recycler, State state, a aVar, int i) {
    }

    private void a(Recycler recycler, State state, int i, int i2) {
        if (state.willRunPredictiveAnimations() && getChildCount() != 0 && !state.isPreLayout() && supportsPredictiveItemAnimations()) {
            int i3 = 0;
            int i4 = 0;
            List scrapList = recycler.getScrapList();
            int size = scrapList.size();
            int position = getPosition(getChildAt(0));
            int i5 = 0;
            while (i5 < size) {
                int i6;
                int i7;
                ViewHolder viewHolder = (ViewHolder) scrapList.get(i5);
                if (viewHolder.m()) {
                    i6 = i4;
                    i7 = i3;
                } else {
                    if (((viewHolder.getLayoutPosition() < position) != this.k ? -1 : 1) == -1) {
                        i7 = this.j.getDecoratedMeasurement(viewHolder.itemView) + i3;
                        i6 = i4;
                    } else {
                        i6 = this.j.getDecoratedMeasurement(viewHolder.itemView) + i4;
                        i7 = i3;
                    }
                }
                i5++;
                i3 = i7;
                i4 = i6;
            }
            this.a.k = scrapList;
            if (i3 > 0) {
                e(getPosition(i()), i);
                this.a.h = i3;
                this.a.c = 0;
                this.a.assignPositionFromScrapList();
                a(recycler, this.a, state, false);
            }
            if (i4 > 0) {
                a(getPosition(j()), i2);
                this.a.h = i4;
                this.a.c = 0;
                this.a.assignPositionFromScrapList();
                a(recycler, this.a, state, false);
            }
            this.a.k = null;
        }
    }

    private void a(Recycler recycler, State state, a aVar) {
        if (!a(state, aVar) && !b(recycler, state, aVar)) {
            aVar.b();
            aVar.a = this.d ? state.getItemCount() - 1 : 0;
        }
    }

    private boolean b(Recycler recycler, State state, a aVar) {
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null && aVar.a(focusedChild, state)) {
            aVar.assignFromViewAndKeepVisibleRect(focusedChild);
            return true;
        } else if (this.b != this.d) {
            return false;
        } else {
            if (aVar.c) {
                focusedChild = a(recycler, state);
            } else {
                focusedChild = b(recycler, state);
            }
            if (focusedChild == null) {
                return false;
            }
            aVar.assignFromView(focusedChild);
            if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
                if (this.j.getDecoratedStart(focusedChild) >= this.j.getEndAfterPadding() || this.j.getDecoratedEnd(focusedChild) < this.j.getStartAfterPadding()) {
                    z = true;
                }
                if (z) {
                    int endAfterPadding;
                    if (aVar.c) {
                        endAfterPadding = this.j.getEndAfterPadding();
                    } else {
                        endAfterPadding = this.j.getStartAfterPadding();
                    }
                    aVar.b = endAfterPadding;
                }
            }
            return true;
        }
    }

    private boolean a(State state, a aVar) {
        boolean z = false;
        if (state.isPreLayout() || this.l == -1) {
            return false;
        }
        if (this.l < 0 || this.l >= state.getItemCount()) {
            this.l = -1;
            this.m = Integer.MIN_VALUE;
            return false;
        }
        aVar.a = this.l;
        if (this.n != null && this.n.a()) {
            aVar.c = this.n.c;
            if (aVar.c) {
                aVar.b = this.j.getEndAfterPadding() - this.n.b;
                return true;
            }
            aVar.b = this.j.getStartAfterPadding() + this.n.b;
            return true;
        } else if (this.m == Integer.MIN_VALUE) {
            View findViewByPosition = findViewByPosition(this.l);
            if (findViewByPosition == null) {
                if (getChildCount() > 0) {
                    boolean z2;
                    if (this.l < getPosition(getChildAt(0))) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2 == this.k) {
                        z = true;
                    }
                    aVar.c = z;
                }
                aVar.b();
                return true;
            } else if (this.j.getDecoratedMeasurement(findViewByPosition) > this.j.getTotalSpace()) {
                aVar.b();
                return true;
            } else if (this.j.getDecoratedStart(findViewByPosition) - this.j.getStartAfterPadding() < 0) {
                aVar.b = this.j.getStartAfterPadding();
                aVar.c = false;
                return true;
            } else if (this.j.getEndAfterPadding() - this.j.getDecoratedEnd(findViewByPosition) < 0) {
                aVar.b = this.j.getEndAfterPadding();
                aVar.c = true;
                return true;
            } else {
                int decoratedEnd;
                if (aVar.c) {
                    decoratedEnd = this.j.getDecoratedEnd(findViewByPosition) + this.j.getTotalSpaceChange();
                } else {
                    decoratedEnd = this.j.getDecoratedStart(findViewByPosition);
                }
                aVar.b = decoratedEnd;
                return true;
            }
        } else {
            aVar.c = this.k;
            if (this.k) {
                aVar.b = this.j.getEndAfterPadding() - this.m;
                return true;
            }
            aVar.b = this.j.getStartAfterPadding() + this.m;
            return true;
        }
    }

    private int a(int i, Recycler recycler, State state, boolean z) {
        int endAfterPadding = this.j.getEndAfterPadding() - i;
        if (endAfterPadding <= 0) {
            return 0;
        }
        endAfterPadding = -a(-endAfterPadding, recycler, state);
        int i2 = i + endAfterPadding;
        if (!z) {
            return endAfterPadding;
        }
        i2 = this.j.getEndAfterPadding() - i2;
        if (i2 <= 0) {
            return endAfterPadding;
        }
        this.j.offsetChildren(i2);
        return endAfterPadding + i2;
    }

    private int b(int i, Recycler recycler, State state, boolean z) {
        int startAfterPadding = i - this.j.getStartAfterPadding();
        if (startAfterPadding <= 0) {
            return 0;
        }
        startAfterPadding = -a(startAfterPadding, recycler, state);
        int i2 = i + startAfterPadding;
        if (!z) {
            return startAfterPadding;
        }
        i2 -= this.j.getStartAfterPadding();
        if (i2 <= 0) {
            return startAfterPadding;
        }
        this.j.offsetChildren(-i2);
        return startAfterPadding - i2;
    }

    private void a(a aVar) {
        a(aVar.a, aVar.b);
    }

    private void a(int i, int i2) {
        this.a.c = this.j.getEndAfterPadding() - i2;
        this.a.e = this.k ? -1 : 1;
        this.a.d = i;
        this.a.f = 1;
        this.a.b = i2;
        this.a.g = Integer.MIN_VALUE;
    }

    private void b(a aVar) {
        e(aVar.a, aVar.b);
    }

    private void e(int i, int i2) {
        this.a.c = i2 - this.j.getStartAfterPadding();
        this.a.d = i;
        this.a.e = this.k ? 1 : -1;
        this.a.f = -1;
        this.a.b = i2;
        this.a.g = Integer.MIN_VALUE;
    }

    protected boolean a() {
        return getLayoutDirection() == 1;
    }

    void b() {
        if (this.a == null) {
            this.a = c();
        }
        if (this.j == null) {
            this.j = OrientationHelper.createOrientationHelper(this, this.i);
        }
    }

    b c() {
        return new b();
    }

    public void scrollToPosition(int i) {
        this.l = i;
        this.m = Integer.MIN_VALUE;
        if (this.n != null) {
            this.n.b();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        this.l = i;
        this.m = i2;
        if (this.n != null) {
            this.n.b();
        }
        requestLayout();
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        if (this.i == 1) {
            return 0;
        }
        return a(i, recycler, state);
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        if (this.i == 0) {
            return 0;
        }
        return a(i, recycler, state);
    }

    public int computeHorizontalScrollOffset(State state) {
        return b(state);
    }

    public int computeVerticalScrollOffset(State state) {
        return b(state);
    }

    public int computeHorizontalScrollExtent(State state) {
        return c(state);
    }

    public int computeVerticalScrollExtent(State state) {
        return c(state);
    }

    public int computeHorizontalScrollRange(State state) {
        return d(state);
    }

    public int computeVerticalScrollRange(State state) {
        return d(state);
    }

    private int b(State state) {
        boolean z = false;
        if (getChildCount() == 0) {
            return 0;
        }
        b();
        OrientationHelper orientationHelper = this.j;
        View a = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return bx.a(state, orientationHelper, a, b(z, true), this, this.e, this.k);
    }

    private int c(State state) {
        boolean z = false;
        if (getChildCount() == 0) {
            return 0;
        }
        b();
        OrientationHelper orientationHelper = this.j;
        View a = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return bx.a(state, orientationHelper, a, b(z, true), this, this.e);
    }

    private int d(State state) {
        boolean z = false;
        if (getChildCount() == 0) {
            return 0;
        }
        b();
        OrientationHelper orientationHelper = this.j;
        View a = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return bx.b(state, orientationHelper, a, b(z, true), this, this.e);
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.e = z;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.e;
    }

    private void a(int i, int i2, boolean z, State state) {
        int i3 = -1;
        int i4 = 1;
        this.a.l = d();
        this.a.h = a(state);
        this.a.f = i;
        View j;
        b bVar;
        if (i == 1) {
            b bVar2 = this.a;
            bVar2.h += this.j.getEndPadding();
            j = j();
            bVar = this.a;
            if (!this.k) {
                i3 = 1;
            }
            bVar.e = i3;
            this.a.d = getPosition(j) + this.a.e;
            this.a.b = this.j.getDecoratedEnd(j);
            i3 = this.j.getDecoratedEnd(j) - this.j.getEndAfterPadding();
        } else {
            j = i();
            bVar = this.a;
            bVar.h += this.j.getStartAfterPadding();
            bVar = this.a;
            if (!this.k) {
                i4 = -1;
            }
            bVar.e = i4;
            this.a.d = getPosition(j) + this.a.e;
            this.a.b = this.j.getDecoratedStart(j);
            i3 = (-this.j.getDecoratedStart(j)) + this.j.getStartAfterPadding();
        }
        this.a.c = i2;
        if (z) {
            b bVar3 = this.a;
            bVar3.c -= i3;
        }
        this.a.g = i3;
    }

    boolean d() {
        return this.j.getMode() == 0 && this.j.getEnd() == 0;
    }

    void a(State state, b bVar, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i = bVar.d;
        if (i >= 0 && i < state.getItemCount()) {
            layoutPrefetchRegistry.addPosition(i, Math.max(0, bVar.g));
        }
    }

    public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2;
        boolean z;
        if (this.n == null || !this.n.a()) {
            h();
            boolean z2 = this.k;
            if (this.l == -1) {
                i2 = z2 ? i - 1 : 0;
                z = z2;
            } else {
                i2 = this.l;
                z = z2;
            }
        } else {
            z = this.n.c;
            i2 = this.n.a;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.h && i2 >= 0 && i2 < i; i4++) {
            layoutPrefetchRegistry.addPosition(i2, 0);
            i2 += i3;
        }
    }

    public void setInitialPrefetchItemCount(int i) {
        this.h = i;
    }

    public int getInitialPrefetchItemCount() {
        return this.h;
    }

    public void collectAdjacentPrefetchPositions(int i, int i2, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        if (this.i != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            b();
            a(i > 0 ? 1 : -1, Math.abs(i), true, state);
            a(state, this.a, layoutPrefetchRegistry);
        }
    }

    int a(int i, Recycler recycler, State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        this.a.a = true;
        b();
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        a(i2, abs, true, state);
        int a = this.a.g + a(recycler, this.a, state, false);
        if (a < 0) {
            return 0;
        }
        if (abs > a) {
            i = i2 * a;
        }
        this.j.offsetChildren(-i);
        this.a.j = i;
        return i;
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (this.n == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    private void a(Recycler recycler, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    removeAndRecycleViewAt(i3, recycler);
                }
                return;
            }
            while (i > i2) {
                removeAndRecycleViewAt(i, recycler);
                i--;
            }
        }
    }

    private void a(Recycler recycler, int i) {
        if (i >= 0) {
            int childCount = getChildCount();
            int i2;
            if (this.k) {
                for (i2 = childCount - 1; i2 >= 0; i2--) {
                    View childAt = getChildAt(i2);
                    if (this.j.getDecoratedEnd(childAt) > i || this.j.getTransformedEndWithDecoration(childAt) > i) {
                        a(recycler, childCount - 1, i2);
                        return;
                    }
                }
                return;
            }
            for (i2 = 0; i2 < childCount; i2++) {
                View childAt2 = getChildAt(i2);
                if (this.j.getDecoratedEnd(childAt2) > i || this.j.getTransformedEndWithDecoration(childAt2) > i) {
                    a(recycler, 0, i2);
                    return;
                }
            }
        }
    }

    private void b(Recycler recycler, int i) {
        int childCount = getChildCount();
        if (i >= 0) {
            int end = this.j.getEnd() - i;
            int i2;
            if (this.k) {
                for (i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    if (this.j.getDecoratedStart(childAt) < end || this.j.getTransformedStartWithDecoration(childAt) < end) {
                        a(recycler, 0, i2);
                        return;
                    }
                }
                return;
            }
            for (i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt2 = getChildAt(i2);
                if (this.j.getDecoratedStart(childAt2) < end || this.j.getTransformedStartWithDecoration(childAt2) < end) {
                    a(recycler, childCount - 1, i2);
                    return;
                }
            }
        }
    }

    private void a(Recycler recycler, b bVar) {
        if (bVar.a && !bVar.l) {
            if (bVar.f == -1) {
                b(recycler, bVar.g);
            } else {
                a(recycler, bVar.g);
            }
        }
    }

    int a(Recycler recycler, b bVar, State state, boolean z) {
        int i = bVar.c;
        if (bVar.g != Integer.MIN_VALUE) {
            if (bVar.c < 0) {
                bVar.g += bVar.c;
            }
            a(recycler, bVar);
        }
        int i2 = bVar.c + bVar.h;
        LayoutChunkResult layoutChunkResult = this.g;
        while (true) {
            if ((!bVar.l && i2 <= 0) || !bVar.a(state)) {
                break;
            }
            layoutChunkResult.a();
            a(recycler, state, bVar, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                bVar.b += layoutChunkResult.mConsumed * bVar.f;
                if (!(layoutChunkResult.mIgnoreConsumed && this.a.k == null && state.isPreLayout())) {
                    bVar.c -= layoutChunkResult.mConsumed;
                    i2 -= layoutChunkResult.mConsumed;
                }
                if (bVar.g != Integer.MIN_VALUE) {
                    bVar.g += layoutChunkResult.mConsumed;
                    if (bVar.c < 0) {
                        bVar.g += bVar.c;
                    }
                    a(recycler, bVar);
                }
                if (z && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - bVar.c;
    }

    void a(Recycler recycler, State state, b bVar, LayoutChunkResult layoutChunkResult) {
        View a = bVar.a(recycler);
        if (a == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        int decoratedMeasurementInOther;
        int i;
        int i2;
        int i3;
        LayoutParams layoutParams = (LayoutParams) a.getLayoutParams();
        if (bVar.k == null) {
            if (this.k == (bVar.f == -1)) {
                addView(a);
            } else {
                addView(a, 0);
            }
        } else {
            boolean z;
            boolean z2 = this.k;
            if (bVar.f == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z2 == z) {
                addDisappearingView(a);
            } else {
                addDisappearingView(a, 0);
            }
        }
        measureChildWithMargins(a, 0, 0);
        layoutChunkResult.mConsumed = this.j.getDecoratedMeasurement(a);
        if (this.i == 1) {
            int width;
            if (a()) {
                width = getWidth() - getPaddingRight();
                decoratedMeasurementInOther = width - this.j.getDecoratedMeasurementInOther(a);
            } else {
                decoratedMeasurementInOther = getPaddingLeft();
                width = this.j.getDecoratedMeasurementInOther(a) + decoratedMeasurementInOther;
            }
            if (bVar.f == -1) {
                i = bVar.b;
                i2 = bVar.b - layoutChunkResult.mConsumed;
                i3 = width;
            } else {
                i2 = bVar.b;
                i = layoutChunkResult.mConsumed + bVar.b;
                i3 = width;
            }
        } else {
            i2 = getPaddingTop();
            i = i2 + this.j.getDecoratedMeasurementInOther(a);
            if (bVar.f == -1) {
                decoratedMeasurementInOther = bVar.b - layoutChunkResult.mConsumed;
                i3 = bVar.b;
            } else {
                decoratedMeasurementInOther = bVar.b;
                i3 = bVar.b + layoutChunkResult.mConsumed;
            }
        }
        layoutDecoratedWithMargins(a, decoratedMeasurementInOther, i2, i3, i);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = a.hasFocusable();
    }

    boolean e() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !g()) ? false : true;
    }

    int a(int i) {
        int i2 = Integer.MIN_VALUE;
        int i3 = 1;
        switch (i) {
            case 1:
                if (this.i == 1 || !a()) {
                    return -1;
                }
                return 1;
            case 2:
                if (this.i == 1) {
                    return 1;
                }
                if (a()) {
                    return -1;
                }
                return 1;
            case 17:
                if (this.i != 0) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 33:
                if (this.i != 1) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 66:
                if (this.i != 0) {
                    i3 = Integer.MIN_VALUE;
                }
                return i3;
            case 130:
                if (this.i == 1) {
                    i2 = 1;
                }
                return i2;
            default:
                return Integer.MIN_VALUE;
        }
    }

    private View i() {
        return getChildAt(this.k ? getChildCount() - 1 : 0);
    }

    private View j() {
        return getChildAt(this.k ? 0 : getChildCount() - 1);
    }

    private View a(boolean z, boolean z2) {
        if (this.k) {
            return a(getChildCount() - 1, -1, z, z2);
        }
        return a(0, getChildCount(), z, z2);
    }

    private View b(boolean z, boolean z2) {
        if (this.k) {
            return a(0, getChildCount(), z, z2);
        }
        return a(getChildCount() - 1, -1, z, z2);
    }

    private View a(Recycler recycler, State state) {
        if (this.k) {
            return c(recycler, state);
        }
        return d(recycler, state);
    }

    private View b(Recycler recycler, State state) {
        if (this.k) {
            return d(recycler, state);
        }
        return c(recycler, state);
    }

    private View c(Recycler recycler, State state) {
        return a(recycler, state, 0, getChildCount(), state.getItemCount());
    }

    private View d(Recycler recycler, State state) {
        return a(recycler, state, getChildCount() - 1, -1, state.getItemCount());
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
                if (((LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view3 = view;
                        i += i4;
                        view = view3;
                        view2 = childAt;
                    }
                } else if (this.j.getDecoratedStart(childAt) < endAfterPadding && this.j.getDecoratedEnd(childAt) >= startAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view3 = childAt;
                        childAt = view2;
                        i += i4;
                        view = view3;
                        view2 = childAt;
                    }
                }
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

    private View e(Recycler recycler, State state) {
        if (this.k) {
            return g(recycler, state);
        }
        return h(recycler, state);
    }

    private View f(Recycler recycler, State state) {
        if (this.k) {
            return h(recycler, state);
        }
        return g(recycler, state);
    }

    private View g(Recycler recycler, State state) {
        return b(0, getChildCount());
    }

    private View h(Recycler recycler, State state) {
        return b(getChildCount() - 1, -1);
    }

    public int findFirstVisibleItemPosition() {
        View a = a(0, getChildCount(), false, true);
        return a == null ? -1 : getPosition(a);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View a = a(0, getChildCount(), true, false);
        return a == null ? -1 : getPosition(a);
    }

    public int findLastVisibleItemPosition() {
        View a = a(getChildCount() - 1, -1, false, true);
        if (a == null) {
            return -1;
        }
        return getPosition(a);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View a = a(getChildCount() - 1, -1, true, false);
        if (a == null) {
            return -1;
        }
        return getPosition(a);
    }

    View a(int i, int i2, boolean z, boolean z2) {
        int i3;
        int i4 = 320;
        b();
        if (z) {
            i3 = 24579;
        } else {
            i3 = 320;
        }
        if (!z2) {
            i4 = 0;
        }
        if (this.i == 0) {
            return this.r.a(i, i2, i3, i4);
        }
        return this.s.a(i, i2, i3, i4);
    }

    View b(int i, int i2) {
        b();
        Object obj = i2 > i ? 1 : i2 < i ? -1 : null;
        if (obj == null) {
            return getChildAt(i);
        }
        int i3;
        int i4;
        if (this.j.getDecoratedStart(getChildAt(i)) < this.j.getStartAfterPadding()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        if (this.i == 0) {
            return this.r.a(i, i2, i3, i4);
        }
        return this.s.a(i, i2, i3, i4);
    }

    public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
        h();
        if (getChildCount() == 0) {
            return null;
        }
        int a = a(i);
        if (a == Integer.MIN_VALUE) {
            return null;
        }
        View f;
        View i2;
        b();
        b();
        a(a, (int) (0.33333334f * ((float) this.j.getTotalSpace())), false, state);
        this.a.g = Integer.MIN_VALUE;
        this.a.a = false;
        a(recycler, this.a, state, true);
        if (a == -1) {
            f = f(recycler, state);
        } else {
            f = e(recycler, state);
        }
        if (a == -1) {
            i2 = i();
        } else {
            i2 = j();
        }
        if (!i2.hasFocusable()) {
            return f;
        }
        if (f == null) {
            return null;
        }
        return i2;
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.n == null && this.b == this.d;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void prepareForDrop(View view, View view2, int i, int i2) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        b();
        h();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        if (position < position2) {
            Object obj = 1;
        } else {
            position = -1;
        }
        if (this.k) {
            if (obj == 1) {
                scrollToPositionWithOffset(position2, this.j.getEndAfterPadding() - (this.j.getDecoratedStart(view2) + this.j.getDecoratedMeasurement(view)));
            } else {
                scrollToPositionWithOffset(position2, this.j.getEndAfterPadding() - this.j.getDecoratedEnd(view2));
            }
        } else if (obj == -1) {
            scrollToPositionWithOffset(position2, this.j.getDecoratedStart(view2));
        } else {
            scrollToPositionWithOffset(position2, this.j.getDecoratedEnd(view2) - this.j.getDecoratedMeasurement(view));
        }
    }
}
