package com.budejie.www.activity.labelsubscription;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.labelsubscription.HorizontalListView.OnScrollStateChangedListener.ScrollState;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {
    private boolean A = false;
    private boolean B = false;
    private OnClickListener C;
    private float D;
    private float E;
    private DataSetObserver F = new HorizontalListView$2(this);
    private Runnable G = new HorizontalListView$3(this);
    protected Scroller a = new Scroller(getContext());
    protected ListAdapter b;
    protected int c;
    protected int d;
    private final HorizontalListView$a e = new HorizontalListView$a(this, null);
    private GestureDetector f;
    private int g;
    private List<Queue<View>> h = new ArrayList();
    private boolean i = false;
    private Rect j = new Rect();
    private View k = null;
    private int l = 0;
    private Drawable m = null;
    private Integer n = null;
    private int o = Integer.MAX_VALUE;
    private int p;
    private int q;
    private int r;
    private HorizontalListView$d s = null;
    private int t = 0;
    private boolean u = false;
    private HorizontalListView$OnScrollStateChangedListener v = null;
    private ScrollState w = ScrollState.SCROLL_STATE_IDLE;
    private EdgeEffectCompat x;
    private EdgeEffectCompat y;
    private int z;

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.x = new EdgeEffectCompat(context);
        this.y = new EdgeEffectCompat(context);
        this.f = new GestureDetector(context, this.e);
        a();
        b();
        a(context, attributeSet);
        setWillNotDraw(false);
        if (VERSION.SDK_INT >= 11) {
            HorizontalListView$b.a(this.a, 0.009f);
        }
    }

    private void a() {
        setOnTouchListener(new HorizontalListView$1(this));
    }

    private void a(Boolean bool) {
        if (this.B != bool.booleanValue()) {
            View view = this;
            while (view.getParent() instanceof View) {
                if ((view.getParent() instanceof ListView) || (view.getParent() instanceof ScrollView)) {
                    view.getParent().requestDisallowInterceptTouchEvent(bool.booleanValue());
                    this.B = bool.booleanValue();
                    return;
                }
                view = (View) view.getParent();
            }
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HorizontalListView);
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            if (drawable != null) {
                setDivider(drawable);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, 0);
            if (dimensionPixelSize != 0) {
                setDividerWidth(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("BUNDLE_ID_PARENT_STATE", super.onSaveInstanceState());
        bundle.putInt("BUNDLE_ID_CURRENT_X", this.c);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.n = Integer.valueOf(bundle.getInt("BUNDLE_ID_CURRENT_X"));
            super.onRestoreInstanceState(bundle.getParcelable("BUNDLE_ID_PARENT_STATE"));
        }
    }

    public void setDivider(Drawable drawable) {
        this.m = drawable;
        if (drawable != null) {
            setDividerWidth(drawable.getIntrinsicWidth());
        } else {
            setDividerWidth(0);
        }
    }

    public void setDividerWidth(int i) {
        this.l = i;
        requestLayout();
        invalidate();
    }

    private void b() {
        this.p = -1;
        this.q = -1;
        this.g = 0;
        this.c = 0;
        this.d = 0;
        this.o = Integer.MAX_VALUE;
        setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
    }

    private void c() {
        b();
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setSelection(int i) {
        this.r = i;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                getParent().requestDisallowInterceptTouchEvent(true);
                this.D = x;
                this.E = y;
                break;
            case 1:
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case 2:
                if (Math.abs(x - this.D) >= Math.abs(y - this.E)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return this.f.onTouchEvent(motionEvent);
    }

    public View getSelectedView() {
        return g(this.r);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.b != null) {
            this.b.unregisterDataSetObserver(this.F);
        }
        if (listAdapter != null) {
            this.u = false;
            this.b = listAdapter;
            this.b.registerDataSetObserver(this.F);
        }
        a(this.b.getViewTypeCount());
        c();
    }

    public ListAdapter getAdapter() {
        return this.b;
    }

    private void a(int i) {
        this.h.clear();
        for (int i2 = 0; i2 < i; i2++) {
            this.h.add(new LinkedList());
        }
    }

    private View b(int i) {
        int itemViewType = this.b.getItemViewType(i);
        if (c(itemViewType)) {
            return (View) ((Queue) this.h.get(itemViewType)).poll();
        }
        return null;
    }

    private void a(int i, View view) {
        int itemViewType = this.b.getItemViewType(i);
        if (c(itemViewType)) {
            ((Queue) this.h.get(itemViewType)).offer(view);
        }
    }

    private boolean c(int i) {
        return i < this.h.size();
    }

    private void a(View view, int i) {
        addViewInLayout(view, i, b(view), true);
        a(view);
    }

    private void a(View view) {
        int makeMeasureSpec;
        LayoutParams b = b(view);
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.z, getPaddingTop() + getPaddingBottom(), b.height);
        if (b.width > 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(b.width, 1073741824);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(makeMeasureSpec, childMeasureSpec);
    }

    private LayoutParams b(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            return new LayoutParams(-2, -1);
        }
        return layoutParams;
    }

    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.b != null) {
            int i5;
            invalidate();
            if (this.i) {
                i5 = this.c;
                b();
                removeAllViewsInLayout();
                this.d = i5;
                this.i = false;
            }
            if (this.n != null) {
                this.d = this.n.intValue();
                this.n = null;
            }
            if (this.a.computeScrollOffset()) {
                this.d = this.a.getCurrX();
            }
            if (this.d < 0) {
                this.d = 0;
                if (this.x.isFinished()) {
                    this.x.onAbsorb((int) d());
                }
                this.a.forceFinished(true);
                setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
            } else if (this.d > this.o) {
                this.d = this.o;
                if (this.y.isFinished()) {
                    this.y.onAbsorb((int) d());
                }
                this.a.forceFinished(true);
                setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
            }
            i5 = this.c - this.d;
            e(i5);
            d(i5);
            f(i5);
            this.c = this.d;
            if (e()) {
                onLayout(z, i, i2, i3, i4);
            } else if (!this.a.isFinished()) {
                ViewCompat.postOnAnimation(this, this.G);
            } else if (this.w == ScrollState.SCROLL_STATE_FLING) {
                setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
            }
        }
    }

    protected float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == 0) {
            return 0.0f;
        }
        if (this.c < horizontalFadingEdgeLength) {
            return ((float) this.c) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    protected float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == this.o) {
            return 0.0f;
        }
        if (this.o - this.c < horizontalFadingEdgeLength) {
            return ((float) (this.o - this.c)) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    private float d() {
        if (VERSION.SDK_INT >= 14) {
            return HorizontalListView$c.a(this.a);
        }
        return 30.0f;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.z = i2;
    }

    private boolean e() {
        if (!h(this.q)) {
            return false;
        }
        View rightmostChild = getRightmostChild();
        if (rightmostChild == null) {
            return false;
        }
        int i = this.o;
        this.o = ((rightmostChild.getRight() - getPaddingLeft()) + this.c) - getRenderWidth();
        if (this.o < 0) {
            this.o = 0;
        }
        if (this.o != i) {
            return true;
        }
        return false;
    }

    private void d(int i) {
        int right;
        int i2 = 0;
        View rightmostChild = getRightmostChild();
        if (rightmostChild != null) {
            right = rightmostChild.getRight();
        } else {
            right = 0;
        }
        a(right, i);
        rightmostChild = getLeftmostChild();
        if (rightmostChild != null) {
            i2 = rightmostChild.getLeft();
        }
        b(i2, i);
    }

    private void e(int i) {
        View leftmostChild = getLeftmostChild();
        while (leftmostChild != null && leftmostChild.getRight() + i <= 0) {
            int measuredWidth;
            int i2 = this.g;
            if (h(this.p)) {
                measuredWidth = leftmostChild.getMeasuredWidth();
            } else {
                measuredWidth = this.l + leftmostChild.getMeasuredWidth();
            }
            this.g = measuredWidth + i2;
            a(this.p, leftmostChild);
            removeViewInLayout(leftmostChild);
            this.p++;
            leftmostChild = getLeftmostChild();
        }
        View rightmostChild = getRightmostChild();
        while (rightmostChild != null && rightmostChild.getLeft() + i >= getWidth()) {
            a(this.q, rightmostChild);
            removeViewInLayout(rightmostChild);
            this.q--;
            rightmostChild = getRightmostChild();
        }
    }

    private void a(int i, int i2) {
        while ((i + i2) + this.l < getWidth() && this.q + 1 < this.b.getCount()) {
            this.q++;
            if (this.p < 0) {
                this.p = this.q;
            }
            View view = this.b.getView(this.q, b(this.q), this);
            a(view, -1);
            i += (this.q == 0 ? 0 : this.l) + view.getMeasuredWidth();
            h();
        }
    }

    private void b(int i, int i2) {
        while ((i + i2) - this.l > 0 && this.p >= 1) {
            this.p--;
            View view = this.b.getView(this.p, b(this.p), this);
            a(view, 0);
            i -= this.p == 0 ? view.getMeasuredWidth() : this.l + view.getMeasuredWidth();
            this.g -= i + i2 == 0 ? view.getMeasuredWidth() : this.l + view.getMeasuredWidth();
        }
    }

    private void f(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            this.g += i;
            int i2 = this.g;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                int paddingLeft = getPaddingLeft() + i2;
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                i2 += childAt.getMeasuredWidth() + this.l;
            }
        }
    }

    private View getLeftmostChild() {
        return getChildAt(0);
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() - 1);
    }

    private View g(int i) {
        if (i < this.p || i > this.q) {
            return null;
        }
        return getChildAt(i - this.p);
    }

    private int c(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).getHitRect(this.j);
            if (this.j.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    private boolean h(int i) {
        return i == this.b.getCount() + -1;
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public int getFirstVisiblePosition() {
        return this.p;
    }

    public int getLastVisiblePosition() {
        return this.q;
    }

    private void a(Canvas canvas) {
        int save;
        int height;
        if (this.x != null && !this.x.isFinished() && i()) {
            save = canvas.save();
            height = getHeight();
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            canvas.translate((float) ((-height) + getPaddingBottom()), 0.0f);
            this.x.setSize(getRenderHeight(), getRenderWidth());
            if (this.x.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        } else if (this.y != null && !this.y.isFinished() && i()) {
            save = canvas.save();
            height = getWidth();
            canvas.rotate(90.0f, 0.0f, 0.0f);
            canvas.translate((float) getPaddingTop(), (float) (-height));
            this.y.setSize(getRenderHeight(), getRenderWidth());
            if (this.y.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        }
    }

    private void b(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.j;
        this.j.top = getPaddingTop();
        this.j.bottom = this.j.top + getRenderHeight();
        for (int i = 0; i < childCount; i++) {
            if (i != childCount - 1 || !h(this.q)) {
                View childAt = getChildAt(i);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.l;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                a(canvas, rect);
                if (i == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    a(canvas, rect);
                }
            }
        }
    }

    private void a(Canvas canvas, Rect rect) {
        if (this.m != null) {
            this.m.setBounds(rect);
            this.m.draw(canvas);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b(canvas);
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        a(canvas);
    }

    protected void dispatchSetPressed(boolean z) {
    }

    protected boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.a.fling(this.d, 0, (int) (-f), 0, 0, this.o, 0, 0);
        setCurrentScrollState(ScrollState.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }

    protected boolean a(MotionEvent motionEvent) {
        this.A = !this.a.isFinished();
        this.a.forceFinished(true);
        setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
        f();
        if (!this.A) {
            int c = c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (c >= 0) {
                this.k = getChildAt(c);
                if (this.k != null) {
                    this.k.setPressed(true);
                    refreshDrawableState();
                }
            }
        }
        return true;
    }

    private void f() {
        if (this.k != null) {
            this.k.setPressed(false);
            refreshDrawableState();
            this.k = null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.a == null || this.a.isFinished()) {
                setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
            }
            a(Boolean.valueOf(false));
            g();
        } else if (motionEvent.getAction() == 3) {
            f();
            g();
            a(Boolean.valueOf(false));
        }
        return super.onTouchEvent(motionEvent);
    }

    private void g() {
        if (this.x != null) {
            this.x.onRelease();
        }
        if (this.y != null) {
            this.y.onRelease();
        }
    }

    private void h() {
        if (this.s != null && this.b != null && this.b.getCount() - (this.q + 1) < this.t && !this.u) {
            this.u = true;
            this.s.a();
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.C = onClickListener;
    }

    public void setOnScrollStateChangedListener(HorizontalListView$OnScrollStateChangedListener horizontalListView$OnScrollStateChangedListener) {
        this.v = horizontalListView$OnScrollStateChangedListener;
    }

    private void setCurrentScrollState(ScrollState scrollState) {
        if (!(this.w == scrollState || this.v == null)) {
            this.v.a(scrollState);
        }
        this.w = scrollState;
    }

    private void i(int i) {
        if (this.x != null && this.y != null) {
            int i2 = this.c + i;
            if (this.a != null && !this.a.isFinished()) {
                return;
            }
            if (i2 < 0) {
                this.x.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.y.isFinished()) {
                    this.y.onRelease();
                }
            } else if (i2 > this.o) {
                this.y.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.x.isFinished()) {
                    this.x.onRelease();
                }
            }
        }
    }

    private boolean i() {
        if (this.b == null || this.b.isEmpty() || this.o <= 0) {
            return false;
        }
        return true;
    }
}
