package com.yarolegovich.discretescrollview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.State;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.meizu.cloud.pushsdk.constants.PushConstants;

class a extends LayoutManager {
    private Point a = new Point();
    private Point b = new Point();
    private Point c = new Point();
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private a i;
    private int j;
    private int k;
    private int l = -1;
    private int m = -1;
    private Context n;
    private int o = 300;
    private int p;
    private SparseArray<View> q = new SparseArray();
    private boolean r;
    private boolean s;
    private int t = PushConstants.BROADCAST_MESSAGE_ARRIVE;
    private boolean u = false;
    @NonNull
    private final b v;
    private com.yarolegovich.discretescrollview.transform.a w;

    public interface b {
        void a();

        void a(float f);

        void a(boolean z);

        void b();

        void c();

        void d();
    }

    private class a extends LinearSmoothScroller {
        final /* synthetic */ a a;

        public a(a aVar, Context context) {
            this.a = aVar;
            super(context);
        }

        public int calculateDxToMakeVisible(View view, int i) {
            return this.a.i.a(-this.a.k);
        }

        public int calculateDyToMakeVisible(View view, int i) {
            return this.a.i.b(-this.a.k);
        }

        protected int calculateTimeForScrolling(int i) {
            return (int) (Math.max(0.01f, ((float) Math.min(Math.abs(i), this.a.g)) / ((float) this.a.g)) * ((float) this.a.o));
        }

        @Nullable
        public PointF computeScrollVectorForPosition(int i) {
            return new PointF((float) this.a.i.a(this.a.k), (float) this.a.i.b(this.a.k));
        }
    }

    public a(@NonNull Context context, @NonNull b bVar, @NonNull Orientation orientation) {
        this.n = context;
        this.v = bVar;
        this.i = orientation.createHelper();
        setAutoMeasureEnabled(true);
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        boolean z = false;
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            this.m = -1;
            this.l = -1;
            this.k = 0;
            this.j = 0;
            return;
        }
        if (this.l == -1) {
            this.l = 0;
        }
        if (!this.s) {
            if (getChildCount() == 0) {
                z = true;
            }
            this.s = z;
            if (this.s) {
                a(recycler);
            }
        }
        g();
        detachAndScrapAttachedViews(recycler);
        b(recycler);
        i();
    }

    public void onLayoutCompleted(State state) {
        if (this.s) {
            this.v.c();
            this.s = false;
        } else if (this.r) {
            this.v.d();
            this.r = false;
        }
    }

    private void a(Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(0);
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, 0, 0);
        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
        int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
        this.d = decoratedMeasuredWidth / 2;
        this.e = decoratedMeasuredHeight / 2;
        this.g = this.i.b(decoratedMeasuredWidth, decoratedMeasuredHeight);
        this.f = this.g * this.p;
        detachAndScrapView(viewForPosition, recycler);
    }

    private void g() {
        this.b.set(getWidth() / 2, getHeight() / 2);
    }

    private void b(Recycler recycler) {
        h();
        this.i.a(this.b, this.j, this.c);
        int a = this.i.a(getWidth(), getHeight());
        if (a(this.c, a)) {
            a(recycler, this.l, this.c);
        }
        a(recycler, Direction.START, a);
        a(recycler, Direction.END, a);
        c(recycler);
    }

    private void a(Recycler recycler, Direction direction, int i) {
        int applyTo = direction.applyTo(1);
        int i2 = (this.m == -1 || !direction.sameAs(this.m - this.l)) ? 1 : 0;
        this.a.set(this.c.x, this.c.y);
        int i3 = i2;
        for (i2 = this.l + applyTo; h(i2); i2 += applyTo) {
            if (i2 == this.m) {
                i3 = 1;
            }
            this.i.a(direction, this.g, this.a);
            if (a(this.a, i)) {
                a(recycler, i2, this.a);
            } else if (i3 != 0) {
                return;
            }
        }
    }

    private void a(Recycler recycler, int i, Point point) {
        if (i >= 0) {
            View view = (View) this.q.get(i);
            if (view == null) {
                View viewForPosition = recycler.getViewForPosition(i);
                addView(viewForPosition);
                measureChildWithMargins(viewForPosition, 0, 0);
                layoutDecoratedWithMargins(viewForPosition, point.x - this.d, point.y - this.e, this.d + point.x, this.e + point.y);
                return;
            }
            attachView(view);
            this.q.remove(i);
        }
    }

    private void h() {
        int i = 0;
        this.q.clear();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            this.q.put(getPosition(childAt), childAt);
        }
        while (i < this.q.size()) {
            detachView((View) this.q.valueAt(i));
            i++;
        }
    }

    private void c(Recycler recycler) {
        for (int i = 0; i < this.q.size(); i++) {
            recycler.recycleView((View) this.q.valueAt(i));
        }
        this.q.clear();
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        int i3 = this.l;
        if (this.l == -1) {
            i3 = 0;
        } else if (this.l >= i) {
            i3 = Math.min(this.l + i2, getItemCount() - 1);
        }
        d(i3);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        int i3 = -1;
        int i4 = this.l;
        if (getItemCount() != 0) {
            if (this.l >= i) {
                if (this.l < i + i2) {
                    this.l = -1;
                }
                i3 = Math.max(0, this.l - i2);
            } else {
                i3 = i4;
            }
        }
        d(i3);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.l = Math.min(Math.max(0, this.l), getItemCount() - 1);
        this.r = true;
    }

    private void d(int i) {
        if (this.l != i) {
            this.l = i;
            this.r = true;
        }
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        return a(i, recycler);
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        return a(i, recycler);
    }

    private int a(int i, Recycler recycler) {
        int i2 = 0;
        if (getChildCount() != 0) {
            Direction fromDelta = Direction.fromDelta(i);
            int a = a(fromDelta);
            if (a > 0) {
                i2 = fromDelta.applyTo(Math.min(a, Math.abs(i)));
                this.j += i2;
                if (this.k != 0) {
                    this.k -= i2;
                }
                this.i.a(-i2, (LayoutManager) this);
                if (this.i.a(this)) {
                    b(recycler);
                }
                n();
                i();
            }
        }
        return i2;
    }

    private void i() {
        if (this.w != null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                this.w.a(childAt, a(childAt));
            }
        }
    }

    public void scrollToPosition(int i) {
        if (this.l != i) {
            this.l = i;
            requestLayout();
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        if (this.l != i && this.m == -1) {
            e(i);
        }
    }

    public boolean canScrollHorizontally() {
        return this.i.b();
    }

    public boolean canScrollVertically() {
        return this.i.a();
    }

    public void onScrollStateChanged(int i) {
        if (this.h == 0 && this.h != i) {
            this.v.a();
        }
        if (i == 0) {
            if (j()) {
                this.v.b();
            } else {
                return;
            }
        } else if (i == 1) {
            k();
        }
        this.h = i;
    }

    private boolean j() {
        if (this.m != -1) {
            this.l = this.m;
            this.m = -1;
            this.j = 0;
        }
        Direction fromDelta = Direction.fromDelta(this.j);
        if (Math.abs(this.j) == this.g) {
            this.l = fromDelta.applyTo(1) + this.l;
            this.j = 0;
        }
        if (m()) {
            this.k = g(this.j);
        } else {
            this.k = -this.j;
        }
        if (this.k == 0) {
            return true;
        }
        l();
        return false;
    }

    private void k() {
        if ((Math.abs(this.j) > this.g ? 1 : 0) != 0) {
            int i = this.j / this.g;
            this.l += i;
            this.j -= i * this.g;
        }
        if (m()) {
            this.l = Direction.fromDelta(this.j).applyTo(1) + this.l;
            this.j = -g(this.j);
        }
        this.m = -1;
        this.k = 0;
    }

    public void a(int i, int i2) {
        Object obj;
        Object obj2 = 1;
        int c = this.i.c(i, i2);
        int f = f(Direction.fromDelta(c).applyTo(this.u ? Math.abs(c / this.t) : 1) + this.l);
        if (this.j * c >= 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null || !h(f)) {
            obj2 = null;
        }
        if (obj2 != null) {
            e(f);
        } else {
            a();
        }
    }

    public void a() {
        this.k = -this.j;
        if (this.k != 0) {
            l();
        }
    }

    private int a(Direction direction) {
        boolean z = false;
        if (this.k != 0) {
            return Math.abs(this.k);
        }
        int i;
        boolean z2 = direction.applyTo(this.j) > 0;
        boolean z3;
        if (direction == Direction.START && this.l == 0) {
            if (this.j == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                z = Math.abs(this.j);
            }
            z3 = z2;
            i = z;
            z = z3;
        } else if (direction == Direction.END && this.l == getItemCount() - 1) {
            if (this.j == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                z = Math.abs(this.j);
            }
            z3 = z2;
            z2 = z;
            z = z3;
        } else if (z2) {
            i = this.g - Math.abs(this.j);
        } else {
            i = this.g + Math.abs(this.j);
        }
        this.v.a(z);
        return i;
    }

    private void l() {
        SmoothScroller aVar = new a(this, this.n);
        aVar.setTargetPosition(this.l);
        startSmoothScroll(aVar);
    }

    private void e(int i) {
        if (this.l != i) {
            this.k = -this.j;
            Direction fromDelta = Direction.fromDelta(i - this.l);
            int abs = Math.abs(i - this.l) * this.g;
            this.k = fromDelta.applyTo(abs) + this.k;
            this.m = i;
            l();
        }
    }

    public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        this.m = -1;
        this.k = 0;
        this.j = 0;
        this.l = 0;
        removeAllViews();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        if (this.m != -1) {
            this.l = this.m;
        }
        bundle.putInt("extra_position", this.l);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        this.l = ((Bundle) parcelable).getInt("extra_position");
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public int b() {
        if (this.j == 0) {
            return this.l;
        }
        if (this.m != -1) {
            return this.m;
        }
        return this.l + Direction.fromDelta(this.j).applyTo(1);
    }

    public void a(com.yarolegovich.discretescrollview.transform.a aVar) {
        this.w = aVar;
    }

    public void a(int i) {
        this.o = i;
    }

    public void b(int i) {
        this.p = i;
        this.f = this.g * i;
        requestLayout();
    }

    public void a(Orientation orientation) {
        this.i = orientation.createHelper();
        removeAllViews();
        requestLayout();
    }

    public void a(boolean z) {
        this.u = z;
    }

    public void c(int i) {
        this.t = i;
    }

    public int c() {
        return this.l;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            asRecord.setFromIndex(getPosition(d()));
            asRecord.setToIndex(getPosition(e()));
        }
    }

    private float a(View view) {
        return Math.min(Math.max(-1.0f, this.i.a(this.b, getDecoratedLeft(view) + this.d, getDecoratedTop(view) + this.e) / ((float) this.g)), 1.0f);
    }

    private int f(int i) {
        if (this.l != 0 && i < 0) {
            return 0;
        }
        if (this.l == getItemCount() - 1 || i < getItemCount()) {
            return i;
        }
        return getItemCount() - 1;
    }

    private int g(int i) {
        return Direction.fromDelta(i).applyTo(this.g - Math.abs(this.j));
    }

    private boolean m() {
        return ((float) Math.abs(this.j)) >= ((float) this.g) * 0.6f;
    }

    public View d() {
        return getChildAt(0);
    }

    public View e() {
        return getChildAt(getChildCount() - 1);
    }

    public int f() {
        return this.f;
    }

    private void n() {
        this.v.a(-Math.min(Math.max(-1.0f, ((float) this.j) / (this.m != -1 ? (float) Math.abs(this.j + this.k) : (float) this.g)), 1.0f));
    }

    private boolean h(int i) {
        return i >= 0 && i < getItemCount();
    }

    private boolean a(Point point, int i) {
        return this.i.a(point, this.d, this.e, i, this.f);
    }
}
