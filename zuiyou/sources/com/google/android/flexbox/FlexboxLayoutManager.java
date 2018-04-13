package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager.Properties;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayoutManager extends LayoutManager implements ScrollVectorProvider, a {
    static final /* synthetic */ boolean a = (!FlexboxLayoutManager.class.desiredAssertionStatus());
    private static final Rect b = new Rect();
    private a A;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;
    private List<c> i;
    private final d j;
    private Recycler k;
    private State l;
    private c m;
    private a n;
    private OrientationHelper o;
    private OrientationHelper p;
    private d q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private SparseArray<View> w;
    private final Context x;
    private View y;
    private int z;

    private class a {
        static final /* synthetic */ boolean a = (!FlexboxLayoutManager.class.desiredAssertionStatus());
        final /* synthetic */ FlexboxLayoutManager b;
        private int c;
        private int d;
        private int e;
        private int f;
        private boolean g;
        private boolean h;
        private boolean i;

        private a(FlexboxLayoutManager flexboxLayoutManager) {
            this.b = flexboxLayoutManager;
            this.f = 0;
        }

        private void a() {
            boolean z = true;
            this.c = -1;
            this.d = -1;
            this.e = Integer.MIN_VALUE;
            this.h = false;
            this.i = false;
            if (this.b.a()) {
                if (this.b.d == 0) {
                    if (this.b.c != 1) {
                        z = false;
                    }
                    this.g = z;
                    return;
                }
                if (this.b.d != 2) {
                    z = false;
                }
                this.g = z;
            } else if (this.b.d == 0) {
                if (this.b.c != 3) {
                    z = false;
                }
                this.g = z;
            } else {
                if (this.b.d != 2) {
                    z = false;
                }
                this.g = z;
            }
        }

        private void b() {
            int endAfterPadding;
            if (this.b.a() || !this.b.g) {
                if (this.g) {
                    endAfterPadding = this.b.o.getEndAfterPadding();
                } else {
                    endAfterPadding = this.b.o.getStartAfterPadding();
                }
                this.e = endAfterPadding;
                return;
            }
            if (this.g) {
                endAfterPadding = this.b.o.getEndAfterPadding();
            } else {
                endAfterPadding = this.b.getWidth() - this.b.o.getStartAfterPadding();
            }
            this.e = endAfterPadding;
        }

        private void a(View view) {
            if (this.b.a() || !this.b.g) {
                if (this.g) {
                    this.e = this.b.o.getDecoratedEnd(view) + this.b.o.getTotalSpaceChange();
                } else {
                    this.e = this.b.o.getDecoratedStart(view);
                }
            } else if (this.g) {
                this.e = this.b.o.getDecoratedStart(view) + this.b.o.getTotalSpaceChange();
            } else {
                this.e = this.b.o.getDecoratedEnd(view);
            }
            this.c = this.b.getPosition(view);
            this.i = false;
            if (a || this.b.j.a != null) {
                int i = this.b.j.a[this.c];
                if (i == -1) {
                    i = 0;
                }
                this.d = i;
                if (this.b.i.size() > this.d) {
                    this.c = ((c) this.b.i.get(this.d)).o;
                    return;
                }
                return;
            }
            throw new AssertionError();
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.c + ", mFlexLinePosition=" + this.d + ", mCoordinate=" + this.e + ", mPerpendicularCoordinate=" + this.f + ", mLayoutFromEnd=" + this.g + ", mValid=" + this.h + ", mAssignedFromSavedState=" + this.i + '}';
        }
    }

    public static class b extends LayoutParams implements b {
        public static final Creator<b> CREATOR = new Creator<b>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public b a(Parcel parcel) {
                return new b(parcel);
            }

            public b[] a(int i) {
                return new b[i];
            }
        };
        private float a = 0.0f;
        private float b = 1.0f;
        private int c = -1;
        private float d = -1.0f;
        private int e;
        private int f;
        private int g = ViewCompat.MEASURED_SIZE_MASK;
        private int h = ViewCompat.MEASURED_SIZE_MASK;
        private boolean i;

        public int a() {
            return this.width;
        }

        public int b() {
            return this.height;
        }

        public float d() {
            return this.a;
        }

        public float e() {
            return this.b;
        }

        public int f() {
            return this.c;
        }

        public int g() {
            return this.e;
        }

        public int h() {
            return this.f;
        }

        public int i() {
            return this.g;
        }

        public int j() {
            return this.h;
        }

        public boolean k() {
            return this.i;
        }

        public float l() {
            return this.d;
        }

        public int m() {
            return this.leftMargin;
        }

        public int n() {
            return this.topMargin;
        }

        public int o() {
            return this.rightMargin;
        }

        public int p() {
            return this.bottomMargin;
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public b(int i, int i2) {
            super(i, i2);
        }

        public int c() {
            return 1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.a);
            parcel.writeFloat(this.b);
            parcel.writeInt(this.c);
            parcel.writeFloat(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        protected b(Parcel parcel) {
            super(-2, -2);
            this.a = parcel.readFloat();
            this.b = parcel.readFloat();
            this.c = parcel.readInt();
            this.d = parcel.readFloat();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readByte() != (byte) 0;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    private static class c {
        private int a;
        private boolean b;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private boolean j;

        private c() {
            this.h = 1;
            this.i = 1;
        }

        private boolean a(State state, List<c> list) {
            return this.d >= 0 && this.d < state.getItemCount() && this.c >= 0 && this.c < list.size();
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.a + ", mFlexLinePosition=" + this.c + ", mPosition=" + this.d + ", mOffset=" + this.e + ", mScrollingOffset=" + this.f + ", mLastScrollDelta=" + this.g + ", mItemDirection=" + this.h + ", mLayoutDirection=" + this.i + '}';
        }
    }

    private static class d implements Parcelable {
        public static final Creator<d> CREATOR = new Creator<d>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public d a(Parcel parcel) {
                return new d(parcel);
            }

            public d[] a(int i) {
                return new d[i];
            }
        };
        private int a;
        private int b;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
        }

        d() {
        }

        private d(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
        }

        private d(d dVar) {
            this.a = dVar.a;
            this.b = dVar.b;
        }

        private void a() {
            this.a = -1;
        }

        private boolean a(int i) {
            return this.a >= 0 && this.a < i;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.a + ", mAnchorOffset=" + this.b + '}';
        }
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public FlexboxLayoutManager(Context context, int i) {
        this(context, i, 1);
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.i = new ArrayList();
        this.j = new d(this);
        this.n = new a();
        this.r = -1;
        this.s = Integer.MIN_VALUE;
        this.t = Integer.MIN_VALUE;
        this.u = Integer.MIN_VALUE;
        this.w = new SparseArray();
        this.z = -1;
        this.A = new a();
        c(i);
        d(i2);
        e(4);
        setAutoMeasureEnabled(true);
        this.x = context;
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.i = new ArrayList();
        this.j = new d(this);
        this.n = new a();
        this.r = -1;
        this.s = Integer.MIN_VALUE;
        this.t = Integer.MIN_VALUE;
        this.u = Integer.MIN_VALUE;
        this.w = new SparseArray();
        this.z = -1;
        this.A = new a();
        Properties properties = getProperties(context, attributeSet, i, i2);
        switch (properties.orientation) {
            case 0:
                if (!properties.reverseLayout) {
                    c(0);
                    break;
                } else {
                    c(1);
                    break;
                }
            case 1:
                if (!properties.reverseLayout) {
                    c(2);
                    break;
                } else {
                    c(3);
                    break;
                }
        }
        d(1);
        e(4);
        setAutoMeasureEnabled(true);
        this.x = context;
    }

    public int getFlexDirection() {
        return this.c;
    }

    public void c(int i) {
        if (this.c != i) {
            removeAllViews();
            this.c = i;
            this.o = null;
            this.p = null;
            i();
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.d;
    }

    public void d(int i) {
        if (i == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        } else if (this.d != i) {
            if (this.d == 0 || i == 0) {
                removeAllViews();
                i();
            }
            this.d = i;
            this.o = null;
            this.p = null;
            requestLayout();
        }
    }

    public int getAlignItems() {
        return this.f;
    }

    public void e(int i) {
        if (this.f != i) {
            if (this.f == 4 || i == 4) {
                removeAllViews();
                i();
            }
            this.f = i;
            requestLayout();
        }
    }

    public int getAlignContent() {
        return 5;
    }

    public int a(View view, int i, int i2) {
        if (a()) {
            return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
        }
        return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
    }

    public int a(View view) {
        if (a()) {
            return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        }
        return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
    }

    public void a(View view, int i, int i2, c cVar) {
        calculateItemDecorationsForChild(view, b);
        if (a()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            cVar.e += leftDecorationWidth;
            cVar.f = leftDecorationWidth + cVar.f;
            return;
        }
        leftDecorationWidth = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        cVar.e += leftDecorationWidth;
        cVar.f = leftDecorationWidth + cVar.f;
    }

    public int getFlexItemCount() {
        return this.l.getItemCount();
    }

    public View a(int i) {
        View view = (View) this.w.get(i);
        return view != null ? view : this.k.getViewForPosition(i);
    }

    public View b(int i) {
        return a(i);
    }

    public void a(c cVar) {
    }

    public int a(int i, int i2, int i3) {
        return getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    public int b(int i, int i2, int i3) {
        return getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    public int getLargestMainSize() {
        if (this.i.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.i.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, ((c) this.i.get(i2)).e);
        }
        return i;
    }

    public int getSumOfCrossSize() {
        int i = 0;
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            i += ((c) this.i.get(i2)).g;
        }
        return i;
    }

    public void setFlexLines(List<c> list) {
        this.i = list;
    }

    public List<c> getFlexLinesInternal() {
        return this.i;
    }

    public void a(int i, View view) {
        this.w.put(i, view);
    }

    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        if (a()) {
            return new PointF(0.0f, (float) i2);
        }
        return new PointF((float) i2, 0.0f);
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    public boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        removeAllViews();
    }

    public Parcelable onSaveInstanceState() {
        if (this.q != null) {
            return new d(this.q);
        }
        d dVar = new d();
        if (getChildCount() > 0) {
            View e = e();
            dVar.a = getPosition(e);
            dVar.b = this.o.getDecoratedStart(e) - this.o.getStartAfterPadding();
            return dVar;
        }
        dVar.a();
        return dVar;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof d) {
            this.q = (d) parcelable;
            requestLayout();
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        f(i);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        f(i);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        f(i);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        f(i);
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        f(Math.min(i, i2));
    }

    private void f(int i) {
        int b = b();
        int c = c();
        if (i < c) {
            int childCount = getChildCount();
            this.j.c(childCount);
            this.j.b(childCount);
            this.j.d(childCount);
            if (!a && this.j.a == null) {
                throw new AssertionError();
            } else if (i < this.j.a.length) {
                this.z = i;
                View e = e();
                if (e == null) {
                    return;
                }
                if (b > i || i > c) {
                    this.r = getPosition(e);
                    if (a() || !this.g) {
                        this.s = this.o.getDecoratedStart(e) - this.o.getStartAfterPadding();
                    } else {
                        this.s = this.o.getDecoratedEnd(e) + this.o.getEndPadding();
                    }
                }
            }
        }
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        this.k = recycler;
        this.l = state;
        int itemCount = state.getItemCount();
        if (itemCount != 0 || !state.isPreLayout()) {
            int a;
            d();
            g();
            h();
            this.j.c(itemCount);
            this.j.b(itemCount);
            this.j.d(itemCount);
            this.m.j = false;
            if (this.q != null && this.q.a(itemCount)) {
                this.r = this.q.a;
            }
            if (!(this.n.h && this.r == -1 && this.q == null)) {
                this.n.a();
                a(state, this.n);
                this.n.h = true;
            }
            detachAndScrapAttachedViews(recycler);
            if (this.n.g) {
                b(this.n, false, true);
            } else {
                a(this.n, false, true);
            }
            g(itemCount);
            if (this.n.g) {
                a(recycler, state, this.m);
                a = this.m.e;
                a(this.n, true, false);
                a(recycler, state, this.m);
                itemCount = this.m.e;
            } else {
                a(recycler, state, this.m);
                itemCount = this.m.e;
                b(this.n, true, false);
                a(recycler, state, this.m);
                a = this.m.e;
            }
            if (getChildCount() <= 0) {
                return;
            }
            if (this.n.g) {
                a(b(itemCount, recycler, state, true) + a, recycler, state, false);
            } else {
                b(itemCount + a(a, recycler, state, true), recycler, state, false);
            }
        }
    }

    private int a(int i, Recycler recycler, State state, boolean z) {
        int startAfterPadding;
        int i2;
        if (a() || !this.g) {
            startAfterPadding = i - this.o.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = -a(startAfterPadding, recycler, state);
        } else {
            startAfterPadding = this.o.getEndAfterPadding() - i;
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = a(-startAfterPadding, recycler, state);
        }
        startAfterPadding = i + i2;
        if (!z) {
            return i2;
        }
        startAfterPadding -= this.o.getStartAfterPadding();
        if (startAfterPadding <= 0) {
            return i2;
        }
        this.o.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    private int b(int i, Recycler recycler, State state, boolean z) {
        int a;
        int i2 = (a() || !this.g) ? 0 : 1;
        if (i2 != 0) {
            i2 = i - this.o.getStartAfterPadding();
            if (i2 <= 0) {
                return 0;
            }
            a = a(i2, recycler, state);
        } else {
            i2 = this.o.getEndAfterPadding() - i;
            if (i2 <= 0) {
                return 0;
            }
            a = -a(-i2, recycler, state);
        }
        i2 = i + a;
        if (!z) {
            return a;
        }
        i2 = this.o.getEndAfterPadding() - i2;
        if (i2 <= 0) {
            return a;
        }
        this.o.offsetChildren(i2);
        return a + i2;
    }

    private void g(int i) {
        int i2;
        int i3 = 1;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        int i4;
        if (a()) {
            if (this.t == Integer.MIN_VALUE || this.t == width) {
                i3 = 0;
            }
            if (this.m.b) {
                i4 = this.x.getResources().getDisplayMetrics().heightPixels;
            } else {
                i4 = this.m.a;
            }
            i2 = i4;
        } else {
            if (this.u == Integer.MIN_VALUE || this.u == height) {
                i3 = 0;
            }
            if (this.m.b) {
                i4 = this.x.getResources().getDisplayMetrics().widthPixels;
            } else {
                i4 = this.m.a;
            }
            i2 = i4;
        }
        this.t = width;
        this.u = height;
        if (this.z != -1 || (this.r == -1 && r0 == 0)) {
            if (this.z != -1) {
                width = Math.min(this.z, this.n.c);
            } else {
                width = this.n.c;
            }
            this.A.a();
            if (a()) {
                if (this.i.size() > 0) {
                    this.j.a(this.i, width);
                    this.j.a(this.A, makeMeasureSpec, makeMeasureSpec2, i2, width, this.n.c, this.i);
                } else {
                    this.j.d(i);
                    this.j.a(this.A, makeMeasureSpec, makeMeasureSpec2, i2, 0, this.i);
                }
            } else if (this.i.size() > 0) {
                this.j.a(this.i, width);
                this.j.a(this.A, makeMeasureSpec2, makeMeasureSpec, i2, width, this.n.c, this.i);
            } else {
                this.j.d(i);
                this.j.c(this.A, makeMeasureSpec, makeMeasureSpec2, i2, 0, this.i);
            }
            this.i = this.A.a;
            this.j.a(makeMeasureSpec, makeMeasureSpec2, width);
            this.j.a(width);
        } else if (!this.n.g) {
            this.i.clear();
            if (a || this.j.a != null) {
                this.A.a();
                if (a()) {
                    this.j.b(this.A, makeMeasureSpec, makeMeasureSpec2, i2, this.n.c, this.i);
                } else {
                    this.j.d(this.A, makeMeasureSpec, makeMeasureSpec2, i2, this.n.c, this.i);
                }
                this.i = this.A.a;
                this.j.a(makeMeasureSpec, makeMeasureSpec2);
                this.j.a();
                this.n.d = this.j.a[this.n.c];
                this.m.c = this.n.d;
                return;
            }
            throw new AssertionError();
        }
    }

    public void onLayoutCompleted(State state) {
        super.onLayoutCompleted(state);
        this.q = null;
        this.r = -1;
        this.s = Integer.MIN_VALUE;
        this.z = -1;
        this.n.a();
        this.w.clear();
    }

    private void d() {
        boolean z = false;
        boolean z2 = true;
        int layoutDirection = getLayoutDirection();
        boolean z3;
        switch (this.c) {
            case 0:
                if (layoutDirection == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.g = z3;
                if (this.d != 2) {
                    z2 = false;
                }
                this.h = z2;
                return;
            case 1:
                if (layoutDirection != 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.g = z3;
                if (this.d != 2) {
                    z2 = false;
                }
                this.h = z2;
                return;
            case 2:
                if (layoutDirection == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.g = z3;
                if (this.d == 2) {
                    if (this.g) {
                        z2 = false;
                    }
                    this.g = z2;
                }
                this.h = false;
                return;
            case 3:
                if (layoutDirection == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.g = z3;
                if (this.d == 2) {
                    if (!this.g) {
                        z = true;
                    }
                    this.g = z;
                }
                this.h = true;
                return;
            default:
                this.g = false;
                this.h = false;
                return;
        }
    }

    private void a(State state, a aVar) {
        if (!a(state, aVar, this.q) && !b(state, aVar)) {
            aVar.b();
            aVar.c = 0;
            aVar.d = 0;
        }
    }

    private boolean a(State state, a aVar, d dVar) {
        boolean z = false;
        if (!a && this.j.a == null) {
            throw new AssertionError();
        } else if (state.isPreLayout() || this.r == -1) {
            return false;
        } else {
            if (this.r < 0 || this.r >= state.getItemCount()) {
                this.r = -1;
                this.s = Integer.MIN_VALUE;
                return false;
            }
            aVar.c = this.r;
            aVar.d = this.j.a[aVar.c];
            if (this.q != null && this.q.a(state.getItemCount())) {
                aVar.e = this.o.getStartAfterPadding() + dVar.b;
                aVar.i = true;
                aVar.d = -1;
                return true;
            } else if (this.s == Integer.MIN_VALUE) {
                View findViewByPosition = findViewByPosition(this.r);
                if (findViewByPosition == null) {
                    if (getChildCount() > 0) {
                        if (this.r < getPosition(getChildAt(0))) {
                            z = true;
                        }
                        aVar.g = z;
                    }
                    aVar.b();
                    return true;
                } else if (this.o.getDecoratedMeasurement(findViewByPosition) > this.o.getTotalSpace()) {
                    aVar.b();
                    return true;
                } else if (this.o.getDecoratedStart(findViewByPosition) - this.o.getStartAfterPadding() < 0) {
                    aVar.e = this.o.getStartAfterPadding();
                    aVar.g = false;
                    return true;
                } else if (this.o.getEndAfterPadding() - this.o.getDecoratedEnd(findViewByPosition) < 0) {
                    aVar.e = this.o.getEndAfterPadding();
                    aVar.g = true;
                    return true;
                } else {
                    int decoratedEnd;
                    if (aVar.g) {
                        decoratedEnd = this.o.getDecoratedEnd(findViewByPosition) + this.o.getTotalSpaceChange();
                    } else {
                        decoratedEnd = this.o.getDecoratedStart(findViewByPosition);
                    }
                    aVar.e = decoratedEnd;
                    return true;
                }
            } else if (a() || !this.g) {
                aVar.e = this.o.getStartAfterPadding() + this.s;
                return true;
            } else {
                aVar.e = this.s - this.o.getEndPadding();
                return true;
            }
        }
    }

    private boolean b(State state, a aVar) {
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        View i;
        if (aVar.g) {
            i = i(state.getItemCount());
        } else {
            i = h(state.getItemCount());
        }
        if (i == null) {
            return false;
        }
        aVar.a(i);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
            if (this.o.getDecoratedStart(i) >= this.o.getEndAfterPadding() || this.o.getDecoratedEnd(i) < this.o.getStartAfterPadding()) {
                z = true;
            }
            if (z) {
                int endAfterPadding;
                if (aVar.g) {
                    endAfterPadding = this.o.getEndAfterPadding();
                } else {
                    endAfterPadding = this.o.getStartAfterPadding();
                }
                aVar.e = endAfterPadding;
            }
        }
        return true;
    }

    private View h(int i) {
        if (a || this.j.a != null) {
            View c = c(0, getChildCount(), i);
            if (c == null) {
                return null;
            }
            int i2 = this.j.a[getPosition(c)];
            if (i2 != -1) {
                return a(c, (c) this.i.get(i2));
            }
            return null;
        }
        throw new AssertionError();
    }

    private View i(int i) {
        if (a || this.j.a != null) {
            View c = c(getChildCount() - 1, -1, i);
            if (c == null) {
                return null;
            }
            return b(c, (c) this.i.get(this.j.a[getPosition(c)]));
        }
        throw new AssertionError();
    }

    private View c(int i, int i2, int i3) {
        View view = null;
        g();
        h();
        int startAfterPadding = this.o.getStartAfterPadding();
        int endAfterPadding = this.o.getEndAfterPadding();
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
                } else if (this.o.getDecoratedStart(childAt) >= startAfterPadding && this.o.getDecoratedEnd(childAt) <= endAfterPadding) {
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

    private View e() {
        return getChildAt(0);
    }

    private int a(Recycler recycler, State state, c cVar) {
        if (cVar.f != Integer.MIN_VALUE) {
            if (cVar.a < 0) {
                cVar.f = cVar.f + cVar.a;
            }
            a(recycler, cVar);
        }
        int c = cVar.a;
        int c2 = cVar.a;
        boolean a = a();
        int i = c2;
        c2 = 0;
        while (true) {
            if ((i > 0 || this.m.b) && cVar.a(state, this.i)) {
                c cVar2 = (c) this.i.get(cVar.c);
                cVar.d = cVar2.o;
                c2 += a(cVar2, cVar);
                if (a || !this.g) {
                    cVar.e = cVar.e + (cVar2.a() * cVar.i);
                } else {
                    cVar.e = cVar.e - (cVar2.a() * cVar.i);
                }
                i -= cVar2.a();
            }
        }
        cVar.a = cVar.a - c2;
        if (cVar.f != Integer.MIN_VALUE) {
            cVar.f = cVar.f + c2;
            if (cVar.a < 0) {
                cVar.f = cVar.f + cVar.a;
            }
            a(recycler, cVar);
        }
        return c - cVar.a;
    }

    private void a(Recycler recycler, c cVar) {
        if (!cVar.j) {
            return;
        }
        if (cVar.i == -1) {
            c(recycler, cVar);
        } else {
            b(recycler, cVar);
        }
    }

    private void b(Recycler recycler, c cVar) {
        if (cVar.f >= 0) {
            if (a || this.j.a != null) {
                int childCount = getChildCount();
                if (childCount != 0) {
                    int i = this.j.a[getPosition(getChildAt(0))];
                    if (i != -1) {
                        c cVar2 = (c) this.i.get(i);
                        int i2 = -1;
                        for (int i3 = 0; i3 < childCount; i3++) {
                            View childAt = getChildAt(i3);
                            if (!a(childAt, cVar.f)) {
                                break;
                            }
                            if (cVar2.p == getPosition(childAt)) {
                                if (i >= this.i.size() - 1) {
                                    i2 = i3;
                                    break;
                                }
                                int f = i + cVar.i;
                                i = f;
                                cVar2 = (c) this.i.get(f);
                                i2 = i3;
                            }
                        }
                        a(recycler, 0, i2);
                        return;
                    }
                    return;
                }
                return;
            }
            throw new AssertionError();
        }
    }

    private boolean a(View view, int i) {
        if (a() || !this.g) {
            if (this.o.getDecoratedEnd(view) > i) {
                return false;
            }
            return true;
        } else if (this.o.getEnd() - this.o.getDecoratedStart(view) <= i) {
            return true;
        } else {
            return false;
        }
    }

    private void c(Recycler recycler, c cVar) {
        if (cVar.f >= 0) {
            if (a || this.j.a != null) {
                int end = this.o.getEnd() - cVar.f;
                int childCount = getChildCount();
                if (childCount != 0) {
                    int i = this.j.a[getPosition(getChildAt(childCount - 1))];
                    if (i != -1) {
                        int i2 = childCount - 1;
                        c cVar2 = (c) this.i.get(i);
                        for (int i3 = childCount - 1; i3 >= 0; i3--) {
                            View childAt = getChildAt(i3);
                            if (!b(childAt, cVar.f)) {
                                break;
                            }
                            if (cVar2.o == getPosition(childAt)) {
                                if (i <= 0) {
                                    childCount = i3;
                                    break;
                                }
                                childCount = i + cVar.i;
                                cVar2 = (c) this.i.get(childCount);
                                i = childCount;
                                childCount = i3;
                            }
                        }
                        a(recycler, childCount, i2);
                        return;
                    }
                    return;
                }
                return;
            }
            throw new AssertionError();
        }
    }

    private boolean b(View view, int i) {
        if (a() || !this.g) {
            if (this.o.getDecoratedStart(view) < this.o.getEnd() - i) {
                return false;
            }
            return true;
        } else if (this.o.getDecoratedEnd(view) <= i) {
            return true;
        } else {
            return false;
        }
    }

    private void a(Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    private int a(c cVar, c cVar2) {
        if (a()) {
            return b(cVar, cVar2);
        }
        return c(cVar, cVar2);
    }

    private int b(c cVar, c cVar2) {
        if (a || this.j.b != null) {
            int i;
            float f;
            float f2;
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            int a = cVar2.e;
            if (cVar2.i == -1) {
                i = a - cVar.g;
            } else {
                i = a;
            }
            int h = cVar2.d;
            float f3 = 0.0f;
            switch (this.e) {
                case 0:
                    f = (float) paddingLeft;
                    f2 = (float) (width - paddingRight);
                    break;
                case 1:
                    f = (float) ((width - cVar.e) + paddingRight);
                    f2 = (float) (cVar.e - paddingLeft);
                    break;
                case 2:
                    f = (((float) (width - cVar.e)) / 2.0f) + ((float) paddingLeft);
                    f2 = ((float) (width - paddingRight)) - (((float) (width - cVar.e)) / 2.0f);
                    break;
                case 3:
                    f = (float) paddingLeft;
                    f3 = ((float) (width - cVar.e)) / (cVar.h != 1 ? (float) (cVar.h - 1) : 1.0f);
                    f2 = (float) (width - paddingRight);
                    break;
                case 4:
                    if (cVar.h != 0) {
                        f3 = ((float) (width - cVar.e)) / ((float) cVar.h);
                    }
                    f = (f3 / 2.0f) + ((float) paddingLeft);
                    f2 = ((float) (width - paddingRight)) - (f3 / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.e);
            }
            float g = f - ((float) this.n.f);
            f = f2 - ((float) this.n.f);
            float max = Math.max(f3, 0.0f);
            a = 0;
            int b = cVar.b();
            int i2 = h;
            while (i2 < h + b) {
                View a2 = a(i2);
                if (a2 == null) {
                    f2 = f;
                    f = g;
                } else {
                    int i3;
                    if (cVar2.i == 1) {
                        calculateItemDecorationsForChild(a2, b);
                        addView(a2);
                        i3 = a;
                    } else {
                        calculateItemDecorationsForChild(a2, b);
                        addView(a2, a);
                        i3 = a + 1;
                    }
                    long j = this.j.b[i2];
                    int a3 = this.j.a(j);
                    width = this.j.b(j);
                    LayoutParams layoutParams = (b) a2.getLayoutParams();
                    if (a(a2, a3, width, layoutParams)) {
                        a2.measure(a3, width);
                    }
                    float leftDecorationWidth = g + ((float) (layoutParams.leftMargin + getLeftDecorationWidth(a2)));
                    float rightDecorationWidth = f - ((float) (layoutParams.rightMargin + getRightDecorationWidth(a2)));
                    width = i + getTopDecorationHeight(a2);
                    if (this.g) {
                        this.j.a(a2, cVar, Math.round(rightDecorationWidth) - a2.getMeasuredWidth(), width, Math.round(rightDecorationWidth), width + a2.getMeasuredHeight());
                    } else {
                        this.j.a(a2, cVar, Math.round(leftDecorationWidth), width, a2.getMeasuredWidth() + Math.round(leftDecorationWidth), width + a2.getMeasuredHeight());
                    }
                    f = leftDecorationWidth + (((float) ((a2.getMeasuredWidth() + layoutParams.rightMargin) + getRightDecorationWidth(a2))) + max);
                    f2 = rightDecorationWidth - (((float) ((a2.getMeasuredWidth() + layoutParams.leftMargin) + getLeftDecorationWidth(a2))) + max);
                    a = i3;
                }
                i2++;
                g = f;
                f = f2;
            }
            cVar2.c = cVar2.c + this.m.i;
            return cVar.a();
        }
        throw new AssertionError();
    }

    private int c(c cVar, c cVar2) {
        if (a || this.j.b != null) {
            int i;
            int i2;
            float f;
            float f2;
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int height = getHeight();
            int a = cVar2.e;
            int a2 = cVar2.e;
            if (cVar2.i == -1) {
                i = a2 + cVar.g;
                i2 = a - cVar.g;
            } else {
                i = a2;
                i2 = a;
            }
            int h = cVar2.d;
            float f3 = 0.0f;
            switch (this.e) {
                case 0:
                    f = (float) paddingTop;
                    f2 = (float) (height - paddingBottom);
                    break;
                case 1:
                    f = (float) ((height - cVar.e) + paddingBottom);
                    f2 = (float) (cVar.e - paddingTop);
                    break;
                case 2:
                    f = (((float) (height - cVar.e)) / 2.0f) + ((float) paddingTop);
                    f2 = ((float) (height - paddingBottom)) - (((float) (height - cVar.e)) / 2.0f);
                    break;
                case 3:
                    f = (float) paddingTop;
                    f3 = ((float) (height - cVar.e)) / (cVar.h != 1 ? (float) (cVar.h - 1) : 1.0f);
                    f2 = (float) (height - paddingBottom);
                    break;
                case 4:
                    if (cVar.h != 0) {
                        f3 = ((float) (height - cVar.e)) / ((float) cVar.h);
                    }
                    f = (f3 / 2.0f) + ((float) paddingTop);
                    f2 = ((float) (height - paddingBottom)) - (f3 / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.e);
            }
            float g = f - ((float) this.n.f);
            float g2 = f2 - ((float) this.n.f);
            float max = Math.max(f3, 0.0f);
            int i3 = 0;
            int b = cVar.b();
            int i4 = h;
            while (i4 < h + b) {
                View a3 = a(i4);
                if (a3 == null) {
                    f3 = g2;
                    f2 = g;
                } else {
                    int i5;
                    long j = this.j.b[i4];
                    height = this.j.a(j);
                    int b2 = this.j.b(j);
                    LayoutParams layoutParams = (b) a3.getLayoutParams();
                    if (a(a3, height, b2, layoutParams)) {
                        a3.measure(height, b2);
                    }
                    float topDecorationHeight = g + ((float) (layoutParams.topMargin + getTopDecorationHeight(a3)));
                    float bottomDecorationHeight = g2 - ((float) (layoutParams.rightMargin + getBottomDecorationHeight(a3)));
                    if (cVar2.i == 1) {
                        calculateItemDecorationsForChild(a3, b);
                        addView(a3);
                        i5 = i3;
                    } else {
                        calculateItemDecorationsForChild(a3, b);
                        addView(a3, i3);
                        i5 = i3 + 1;
                    }
                    paddingBottom = i2 + getLeftDecorationWidth(a3);
                    b2 = i - getRightDecorationWidth(a3);
                    if (this.g) {
                        if (this.h) {
                            this.j.a(a3, cVar, this.g, b2 - a3.getMeasuredWidth(), Math.round(bottomDecorationHeight) - a3.getMeasuredHeight(), b2, Math.round(bottomDecorationHeight));
                        } else {
                            this.j.a(a3, cVar, this.g, b2 - a3.getMeasuredWidth(), Math.round(topDecorationHeight), b2, a3.getMeasuredHeight() + Math.round(topDecorationHeight));
                        }
                    } else if (this.h) {
                        this.j.a(a3, cVar, this.g, paddingBottom, Math.round(bottomDecorationHeight) - a3.getMeasuredHeight(), paddingBottom + a3.getMeasuredWidth(), Math.round(bottomDecorationHeight));
                    } else {
                        this.j.a(a3, cVar, this.g, paddingBottom, Math.round(topDecorationHeight), paddingBottom + a3.getMeasuredWidth(), a3.getMeasuredHeight() + Math.round(topDecorationHeight));
                    }
                    f3 = bottomDecorationHeight - (((float) ((a3.getMeasuredHeight() + layoutParams.bottomMargin) + getTopDecorationHeight(a3))) + max);
                    f2 = topDecorationHeight + (((float) ((a3.getMeasuredHeight() + layoutParams.topMargin) + getBottomDecorationHeight(a3))) + max);
                    i3 = i5;
                }
                i4++;
                g = f2;
                g2 = f3;
            }
            cVar2.c = cVar2.c + this.m.i;
            return cVar.a();
        }
        throw new AssertionError();
    }

    public boolean a() {
        return this.c == 0 || this.c == 1;
    }

    private void a(a aVar, boolean z, boolean z2) {
        if (z2) {
            f();
        } else {
            this.m.b = false;
        }
        if (a() || !this.g) {
            this.m.a = this.o.getEndAfterPadding() - aVar.e;
        } else {
            this.m.a = aVar.e - getPaddingRight();
        }
        this.m.d = aVar.c;
        this.m.h = 1;
        this.m.i = 1;
        this.m.e = aVar.e;
        this.m.f = Integer.MIN_VALUE;
        this.m.c = aVar.d;
        if (z && this.i.size() > 1 && aVar.d >= 0 && aVar.d < this.i.size() - 1) {
            c cVar = (c) this.i.get(aVar.d);
            this.m.c = this.m.c + 1;
            c cVar2 = this.m;
            cVar2.d = cVar.b() + cVar2.d;
        }
    }

    private void b(a aVar, boolean z, boolean z2) {
        if (z2) {
            f();
        } else {
            this.m.b = false;
        }
        if (a() || !this.g) {
            this.m.a = aVar.e - this.o.getStartAfterPadding();
        } else {
            this.m.a = (this.y.getWidth() - aVar.e) - this.o.getStartAfterPadding();
        }
        this.m.d = aVar.c;
        this.m.h = 1;
        this.m.i = -1;
        this.m.e = aVar.e;
        this.m.f = Integer.MIN_VALUE;
        this.m.c = aVar.d;
        if (z && aVar.d > 0 && this.i.size() > aVar.d) {
            c cVar = (c) this.i.get(aVar.d);
            this.m.c = this.m.c - 1;
            c cVar2 = this.m;
            cVar2.d = cVar2.d - cVar.b();
        }
    }

    private void f() {
        int heightMode;
        if (a()) {
            heightMode = getHeightMode();
        } else {
            heightMode = getWidthMode();
        }
        c cVar = this.m;
        boolean z = heightMode == 0 || heightMode == Integer.MIN_VALUE;
        cVar.b = z;
    }

    private void g() {
        if (this.o == null) {
            if (a()) {
                if (this.d == 0) {
                    this.o = OrientationHelper.createHorizontalHelper(this);
                    this.p = OrientationHelper.createVerticalHelper(this);
                    return;
                }
                this.o = OrientationHelper.createVerticalHelper(this);
                this.p = OrientationHelper.createHorizontalHelper(this);
            } else if (this.d == 0) {
                this.o = OrientationHelper.createVerticalHelper(this);
                this.p = OrientationHelper.createHorizontalHelper(this);
            } else {
                this.o = OrientationHelper.createHorizontalHelper(this);
                this.p = OrientationHelper.createVerticalHelper(this);
            }
        }
    }

    private void h() {
        if (this.m == null) {
            this.m = new c();
        }
    }

    public void scrollToPosition(int i) {
        this.r = i;
        this.s = Integer.MIN_VALUE;
        if (this.q != null) {
            this.q.a();
        }
        requestLayout();
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        SmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.y = (View) recyclerView.getParent();
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.v) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public boolean canScrollHorizontally() {
        return !a() || getWidth() > this.y.getWidth();
    }

    public boolean canScrollVertically() {
        return a() || getHeight() > this.y.getHeight();
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        if (a()) {
            int j = j(i);
            a aVar = this.n;
            aVar.f = aVar.f + j;
            this.p.offsetChildren(-j);
            return j;
        }
        j = a(i, recycler, state);
        this.w.clear();
        return j;
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        if (a()) {
            int a = a(i, recycler, state);
            this.w.clear();
            return a;
        }
        a = j(i);
        a aVar = this.n;
        aVar.f = aVar.f + a;
        this.p.offsetChildren(-a);
        return a;
    }

    private int a(int i, Recycler recycler, State state) {
        int i2 = 1;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        g();
        this.m.j = true;
        int i3 = (a() || !this.g) ? 0 : 1;
        if (i3 != 0) {
            if (i >= 0) {
                i2 = -1;
            }
        } else if (i <= 0) {
            i2 = -1;
        }
        int abs = Math.abs(i);
        a(i2, abs);
        int d = this.m.f + a(recycler, state, this.m);
        if (d < 0) {
            return 0;
        }
        if (i3 != 0) {
            if (abs > d) {
                i = (-i2) * d;
            }
        } else if (abs > d) {
            i = i2 * d;
        }
        this.o.offsetChildren(-i);
        this.m.g = i;
        return i;
    }

    private int j(int i) {
        Object obj = null;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        g();
        boolean a = a();
        int width = a ? this.y.getWidth() : this.y.getHeight();
        int width2 = a ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            obj = 1;
        }
        if (obj != null) {
            int abs = Math.abs(i);
            if (i < 0) {
                return -Math.min((width2 + this.n.f) - width, abs);
            }
            if (this.n.f + i > 0) {
                return -this.n.f;
            }
            return i;
        } else if (i > 0) {
            return Math.min((width2 - this.n.f) - width, i);
        } else {
            if (this.n.f + i < 0) {
                return -this.n.f;
            }
            return i;
        }
    }

    private void a(int i, int i2) {
        int i3 = 0;
        if (a || this.j.a != null) {
            this.m.i = i;
            boolean a = a();
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
            int i4 = (a || !this.g) ? 0 : 1;
            int d;
            if (i == 1) {
                View childAt = getChildAt(getChildCount() - 1);
                this.m.e = this.o.getDecoratedEnd(childAt);
                int position = getPosition(childAt);
                View b = b(childAt, (c) this.i.get(this.j.a[position]));
                this.m.h = 1;
                this.m.d = this.m.h + position;
                if (this.j.a.length <= this.m.d) {
                    this.m.c = -1;
                } else {
                    this.m.c = this.j.a[this.m.d];
                }
                if (i4 != 0) {
                    this.m.e = this.o.getDecoratedStart(b);
                    this.m.f = (-this.o.getDecoratedStart(b)) + this.o.getStartAfterPadding();
                    c cVar = this.m;
                    if (this.m.f >= 0) {
                        d = this.m.f;
                    } else {
                        d = 0;
                    }
                    cVar.f = d;
                } else {
                    this.m.e = this.o.getDecoratedEnd(b);
                    this.m.f = this.o.getDecoratedEnd(b) - this.o.getEndAfterPadding();
                }
                if ((this.m.c == -1 || this.m.c > this.i.size() - 1) && this.m.d <= getFlexItemCount()) {
                    i4 = i2 - this.m.f;
                    this.A.a();
                    if (i4 > 0) {
                        if (a) {
                            this.j.a(this.A, makeMeasureSpec, makeMeasureSpec2, i4, this.m.d, this.i);
                        } else {
                            this.j.c(this.A, makeMeasureSpec, makeMeasureSpec2, i4, this.m.d, this.i);
                        }
                        this.j.a(makeMeasureSpec, makeMeasureSpec2, this.m.d);
                        this.j.a(this.m.d);
                    }
                }
            } else {
                View childAt2 = getChildAt(0);
                this.m.e = this.o.getDecoratedStart(childAt2);
                makeMeasureSpec2 = getPosition(childAt2);
                View a2 = a(childAt2, (c) this.i.get(this.j.a[makeMeasureSpec2]));
                this.m.h = 1;
                d = this.j.a[makeMeasureSpec2];
                if (d == -1) {
                    makeMeasureSpec = 0;
                } else {
                    makeMeasureSpec = d;
                }
                if (makeMeasureSpec > 0) {
                    this.m.d = makeMeasureSpec2 - ((c) this.i.get(makeMeasureSpec - 1)).b();
                } else {
                    this.m.d = -1;
                }
                c cVar2 = this.m;
                if (makeMeasureSpec > 0) {
                    d = makeMeasureSpec - 1;
                } else {
                    d = 0;
                }
                cVar2.c = d;
                if (i4 != 0) {
                    this.m.e = this.o.getDecoratedEnd(a2);
                    this.m.f = this.o.getDecoratedEnd(a2) - this.o.getEndAfterPadding();
                    c cVar3 = this.m;
                    if (this.m.f >= 0) {
                        i3 = this.m.f;
                    }
                    cVar3.f = i3;
                } else {
                    this.m.e = this.o.getDecoratedStart(a2);
                    this.m.f = (-this.o.getDecoratedStart(a2)) + this.o.getStartAfterPadding();
                }
            }
            this.m.a = i2 - this.m.f;
            return;
        }
        throw new AssertionError();
    }

    private View a(View view, c cVar) {
        boolean a = a();
        int i = cVar.h;
        View view2 = view;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.g || a) {
                    if (this.o.getDecoratedStart(view2) > this.o.getDecoratedStart(childAt)) {
                        view2 = childAt;
                    }
                } else if (this.o.getDecoratedEnd(view2) < this.o.getDecoratedEnd(childAt)) {
                    view2 = childAt;
                }
            }
        }
        return view2;
    }

    private View b(View view, c cVar) {
        boolean a = a();
        int childCount = (getChildCount() - cVar.h) - 1;
        View view2 = view;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.g || a) {
                    if (this.o.getDecoratedEnd(view2) < this.o.getDecoratedEnd(childAt)) {
                        view2 = childAt;
                    }
                } else if (this.o.getDecoratedStart(view2) > this.o.getDecoratedStart(childAt)) {
                    view2 = childAt;
                }
            }
        }
        return view2;
    }

    public int computeHorizontalScrollExtent(State state) {
        return a(state);
    }

    public int computeVerticalScrollExtent(State state) {
        return a(state);
    }

    private int a(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        g();
        View h = h(itemCount);
        View i = i(itemCount);
        if (state.getItemCount() == 0 || h == null || i == null) {
            return 0;
        }
        return Math.min(this.o.getTotalSpace(), this.o.getDecoratedEnd(i) - this.o.getDecoratedStart(h));
    }

    public int computeHorizontalScrollOffset(State state) {
        b(state);
        return b(state);
    }

    public int computeVerticalScrollOffset(State state) {
        return b(state);
    }

    private int b(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View h = h(itemCount);
        View i = i(itemCount);
        if (state.getItemCount() == 0 || h == null || i == null) {
            return 0;
        }
        if (a || this.j.a != null) {
            int position = getPosition(h);
            int position2 = getPosition(i);
            itemCount = Math.abs(this.o.getDecoratedEnd(i) - this.o.getDecoratedStart(h));
            position = this.j.a[position];
            if (position == 0 || position == -1) {
                return 0;
            }
            return Math.round(((((float) itemCount) / ((float) ((this.j.a[position2] - position) + 1))) * ((float) position)) + ((float) (this.o.getStartAfterPadding() - this.o.getDecoratedStart(h))));
        }
        throw new AssertionError();
    }

    public int computeHorizontalScrollRange(State state) {
        return c(state);
    }

    public int computeVerticalScrollRange(State state) {
        return c(state);
    }

    private int c(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View h = h(itemCount);
        View i = i(itemCount);
        if (state.getItemCount() == 0 || h == null || i == null) {
            return 0;
        }
        if (a || this.j.a != null) {
            int b = b();
            return (int) ((((float) Math.abs(this.o.getDecoratedEnd(i) - this.o.getDecoratedStart(h))) / ((float) ((c() - b) + 1))) * ((float) state.getItemCount()));
        }
        throw new AssertionError();
    }

    private boolean a(View view, int i, int i2, LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && d(view.getWidth(), i, layoutParams.width) && d(view.getHeight(), i2, layoutParams.height)) ? false : true;
    }

    private static boolean d(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        switch (mode) {
            case Integer.MIN_VALUE:
                if (size < i) {
                    return false;
                }
                return true;
            case 0:
                return true;
            case 1073741824:
                if (size != i) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    private void i() {
        this.i.clear();
        this.n.a();
        this.n.f = 0;
    }

    private int b(View view) {
        return getDecoratedLeft(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
    }

    private int c(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + getDecoratedRight(view);
    }

    private int d(View view) {
        return getDecoratedTop(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
    }

    private int e(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + getDecoratedBottom(view);
    }

    private boolean a(View view, boolean z) {
        boolean z2;
        boolean z3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int b = b(view);
        int d = d(view);
        int c = c(view);
        int e = e(view);
        if (paddingLeft > b || width < c) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (b >= width || c >= paddingLeft) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z4;
        if (paddingTop > d || height < e) {
            z4 = false;
        } else {
            z4 = true;
        }
        boolean z5;
        if (d >= height || e >= paddingTop) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z) {
            if (z2 && r3) {
                return true;
            }
            return false;
        } else if (z3 && r2) {
            return true;
        } else {
            return false;
        }
    }

    public int b() {
        View a = a(0, getChildCount(), false);
        return a == null ? -1 : getPosition(a);
    }

    public int c() {
        View a = a(getChildCount() - 1, -1, false);
        if (a == null) {
            return -1;
        }
        return getPosition(a);
    }

    private View a(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (a(childAt, z)) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }
}
