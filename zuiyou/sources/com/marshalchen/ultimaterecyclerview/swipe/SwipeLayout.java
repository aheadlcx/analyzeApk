package com.marshalchen.ultimaterecyclerview.swipe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import com.marshalchen.ultimaterecyclerview.b.e;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SwipeLayout extends FrameLayout {
    private static final SwipeLayout$DragEdge c = SwipeLayout$DragEdge.Right;
    OnClickListener a;
    OnLongClickListener b;
    private int d;
    private SwipeLayout$DragEdge e;
    private ViewDragHelper f;
    private int g;
    private LinkedHashMap<SwipeLayout$DragEdge, View> h;
    private SwipeLayout$ShowMode i;
    private float[] j;
    private List<SwipeLayout$f> k;
    private List<SwipeLayout$d> l;
    private Map<View, ArrayList<SwipeLayout$c>> m;
    private Map<View, Boolean> n;
    private SwipeLayout$a o;
    private boolean p;
    private boolean[] q;
    private boolean r;
    private Callback s;
    private int t;
    private List<SwipeLayout$b> u;
    private boolean v;
    private float w;
    private float x;
    private Rect y;
    private GestureDetector z;

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = c;
        this.g = 0;
        this.h = new LinkedHashMap();
        this.j = new float[4];
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.m = new HashMap();
        this.n = new HashMap();
        this.p = true;
        this.q = new boolean[]{true, true, true, true};
        this.r = false;
        this.s = new SwipeLayout$1(this);
        this.t = 0;
        this.w = -1.0f;
        this.x = -1.0f;
        this.z = new GestureDetector(getContext(), new SwipeLayout$e(this));
        this.f = ViewDragHelper.create(this, this.s);
        this.d = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.SwipeLayout);
        int i2 = obtainStyledAttributes.getInt(e.SwipeLayout_drag_edge, 2);
        this.j[SwipeLayout$DragEdge.Left.ordinal()] = obtainStyledAttributes.getDimension(e.SwipeLayout_leftEdgeSwipeOffset, 0.0f);
        this.j[SwipeLayout$DragEdge.Right.ordinal()] = obtainStyledAttributes.getDimension(e.SwipeLayout_rightEdgeSwipeOffset, 0.0f);
        this.j[SwipeLayout$DragEdge.Top.ordinal()] = obtainStyledAttributes.getDimension(e.SwipeLayout_topEdgeSwipeOffset, 0.0f);
        this.j[SwipeLayout$DragEdge.Bottom.ordinal()] = obtainStyledAttributes.getDimension(e.SwipeLayout_bottomEdgeSwipeOffset, 0.0f);
        setClickToClose(obtainStyledAttributes.getBoolean(e.SwipeLayout_clickToClose, this.r));
        if ((i2 & 1) == 1) {
            this.h.put(SwipeLayout$DragEdge.Left, null);
        }
        if ((i2 & 4) == 4) {
            this.h.put(SwipeLayout$DragEdge.Top, null);
        }
        if ((i2 & 2) == 2) {
            this.h.put(SwipeLayout$DragEdge.Right, null);
        }
        if ((i2 & 8) == 8) {
            this.h.put(SwipeLayout$DragEdge.Bottom, null);
        }
        this.i = SwipeLayout$ShowMode.values()[obtainStyledAttributes.getInt(e.SwipeLayout_show_mode, SwipeLayout$ShowMode.PullOut.ordinal())];
        obtainStyledAttributes.recycle();
    }

    protected boolean a(View view, Rect rect, SwipeLayout$DragEdge swipeLayout$DragEdge, int i, int i2, int i3, int i4) {
        if (((Boolean) this.n.get(view)).booleanValue()) {
            return false;
        }
        boolean z;
        int i5 = rect.left;
        int i6 = rect.right;
        int i7 = rect.top;
        int i8 = rect.bottom;
        if (getShowMode() == SwipeLayout$ShowMode.LayDown) {
            if ((swipeLayout$DragEdge == SwipeLayout$DragEdge.Right && i3 <= i5) || ((swipeLayout$DragEdge == SwipeLayout$DragEdge.Left && i >= i6) || ((swipeLayout$DragEdge == SwipeLayout$DragEdge.Top && i2 >= i8) || (swipeLayout$DragEdge == SwipeLayout$DragEdge.Bottom && i4 <= i7)))) {
                z = true;
            }
            z = false;
        } else {
            if (getShowMode() == SwipeLayout$ShowMode.PullOut && ((swipeLayout$DragEdge == SwipeLayout$DragEdge.Right && i6 <= getWidth()) || ((swipeLayout$DragEdge == SwipeLayout$DragEdge.Left && i5 >= getPaddingLeft()) || ((swipeLayout$DragEdge == SwipeLayout$DragEdge.Top && i7 >= getPaddingTop()) || (swipeLayout$DragEdge == SwipeLayout$DragEdge.Bottom && i8 <= getHeight()))))) {
                z = true;
            }
            z = false;
        }
        return z;
    }

    protected boolean b(View view, Rect rect, SwipeLayout$DragEdge swipeLayout$DragEdge, int i, int i2, int i3, int i4) {
        int i5 = rect.left;
        int i6 = rect.right;
        int i7 = rect.top;
        int i8 = rect.bottom;
        if (getShowMode() != SwipeLayout$ShowMode.LayDown) {
            if (getShowMode() == SwipeLayout$ShowMode.PullOut) {
                switch (SwipeLayout$4.a[swipeLayout$DragEdge.ordinal()]) {
                    case 1:
                        if (i7 < getPaddingTop() && i8 >= getPaddingTop()) {
                            return true;
                        }
                    case 2:
                        if (i7 < getHeight() && i7 >= getPaddingTop()) {
                            return true;
                        }
                    case 3:
                        if (i6 >= getPaddingLeft() && i5 < getPaddingLeft()) {
                            return true;
                        }
                    case 4:
                        if (i5 <= getWidth() && i6 > getWidth()) {
                            return true;
                        }
                    default:
                        break;
                }
            }
        }
        switch (SwipeLayout$4.a[swipeLayout$DragEdge.ordinal()]) {
            case 1:
                if (i2 >= i7 && i2 < i8) {
                    return true;
                }
            case 2:
                if (i4 > i7 && i4 <= i8) {
                    return true;
                }
            case 3:
                if (i < i6 && i >= i5) {
                    return true;
                }
            case 4:
                if (i3 > i5 && i3 <= i6) {
                    return true;
                }
        }
        return false;
    }

    protected Rect a(View view) {
        Rect rect = new Rect(view.getLeft(), view.getTop(), 0, 0);
        View view2 = view;
        while (view2.getParent() != null && view2 != getRootView()) {
            view2 = (View) view2.getParent();
            if (view2 == this) {
                break;
            }
            rect.left += view2.getLeft();
            rect.top += view2.getTop();
        }
        rect.right = rect.left + view.getMeasuredWidth();
        rect.bottom = rect.top + view.getMeasuredHeight();
        return rect;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(int r5, int r6, int r7, int r8) {
        /*
        r4 = this;
        r0 = 0;
        r2 = r4.getDragEdge();
        r1 = 1;
        r3 = com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout$DragEdge.Left;
        if (r2 != r3) goto L_0x0010;
    L_0x000a:
        if (r7 >= 0) goto L_0x0016;
    L_0x000c:
        r4.a(r5, r6, r0);
        return;
    L_0x0010:
        r3 = com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout$DragEdge.Right;
        if (r2 != r3) goto L_0x0018;
    L_0x0014:
        if (r7 > 0) goto L_0x000c;
    L_0x0016:
        r0 = r1;
        goto L_0x000c;
    L_0x0018:
        r3 = com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout$DragEdge.Top;
        if (r2 != r3) goto L_0x001f;
    L_0x001c:
        if (r8 >= 0) goto L_0x0016;
    L_0x001e:
        goto L_0x000c;
    L_0x001f:
        r3 = com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout$DragEdge.Bottom;
        if (r2 != r3) goto L_0x0016;
    L_0x0023:
        if (r8 <= 0) goto L_0x0016;
    L_0x0025:
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout.a(int, int, int, int):void");
    }

    protected void a(int i, int i2, boolean z) {
        k();
        SwipeLayout$Status openStatus = getOpenStatus();
        if (!this.k.isEmpty()) {
            this.t++;
            for (SwipeLayout$f swipeLayout$f : this.k) {
                if (this.t == 1) {
                    if (z) {
                        swipeLayout$f.a(this);
                    } else {
                        swipeLayout$f.c(this);
                    }
                }
                swipeLayout$f.a(this, i - getPaddingLeft(), i2 - getPaddingTop());
            }
            if (openStatus == SwipeLayout$Status.Close) {
                for (SwipeLayout$f swipeLayout$f2 : this.k) {
                    swipeLayout$f2.d(this);
                }
                this.t = 0;
            }
            if (openStatus == SwipeLayout$Status.Open) {
                View currentBottomView = getCurrentBottomView();
                if (currentBottomView != null) {
                    currentBottomView.setEnabled(true);
                }
                for (SwipeLayout$f swipeLayout$f22 : this.k) {
                    swipeLayout$f22.b(this);
                }
                this.t = 0;
            }
        }
    }

    private void k() {
        SwipeLayout$Status openStatus = getOpenStatus();
        List<View> bottomViews = getBottomViews();
        if (openStatus == SwipeLayout$Status.Close) {
            for (View view : bottomViews) {
                View view2;
                if (!(view2 == null || view2.getVisibility() == 4)) {
                    view2.setVisibility(4);
                }
            }
            return;
        }
        view2 = getCurrentBottomView();
        if (view2 != null && view2.getVisibility() != 0) {
            view2.setVisibility(0);
        }
    }

    protected void b(int i, int i2, int i3, int i4) {
        if (!this.m.isEmpty()) {
            for (Entry entry : this.m.entrySet()) {
                View view = (View) entry.getKey();
                Rect a = a(view);
                if (b(view, a, this.e, i, i2, i3, i4)) {
                    int i5;
                    float f;
                    this.n.put(view, Boolean.valueOf(false));
                    int i6 = 0;
                    float f2 = 0.0f;
                    if (getShowMode() == SwipeLayout$ShowMode.LayDown) {
                        switch (SwipeLayout$4.a[this.e.ordinal()]) {
                            case 1:
                                i6 = a.top - i2;
                                f2 = ((float) i6) / ((float) view.getHeight());
                                break;
                            case 2:
                                i6 = a.bottom - i4;
                                f2 = ((float) i6) / ((float) view.getHeight());
                                break;
                            case 3:
                                i6 = a.left - i;
                                f2 = ((float) i6) / ((float) view.getWidth());
                                break;
                            case 4:
                                i6 = a.right - i3;
                                f2 = ((float) i6) / ((float) view.getWidth());
                                break;
                        }
                        i5 = i6;
                        f = f2;
                    } else {
                        if (getShowMode() == SwipeLayout$ShowMode.PullOut) {
                            switch (SwipeLayout$4.a[this.e.ordinal()]) {
                                case 1:
                                    i6 = a.bottom - getPaddingTop();
                                    i5 = i6;
                                    f = ((float) i6) / ((float) view.getHeight());
                                    break;
                                case 2:
                                    i6 = a.top - getHeight();
                                    i5 = i6;
                                    f = ((float) i6) / ((float) view.getHeight());
                                    break;
                                case 3:
                                    i6 = a.right - getPaddingLeft();
                                    i5 = i6;
                                    f = ((float) i6) / ((float) view.getWidth());
                                    break;
                                case 4:
                                    i6 = a.left - getWidth();
                                    i5 = i6;
                                    f = ((float) i6) / ((float) view.getWidth());
                                    break;
                            }
                        }
                        i5 = 0;
                        f = 0.0f;
                    }
                    Iterator it = ((ArrayList) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        ((SwipeLayout$c) it.next()).a(view, this.e, Math.abs(f), i5);
                        if (Math.abs(f) == 1.0f) {
                            this.n.put(view, Boolean.valueOf(true));
                        }
                    }
                }
                if (a(view, a, this.e, i, i2, i3, i4)) {
                    this.n.put(view, Boolean.valueOf(true));
                    Iterator it2 = ((ArrayList) entry.getValue()).iterator();
                    while (it2.hasNext()) {
                        SwipeLayout$c swipeLayout$c = (SwipeLayout$c) it2.next();
                        if (this.e == SwipeLayout$DragEdge.Left || this.e == SwipeLayout$DragEdge.Right) {
                            swipeLayout$c.a(view, this.e, 1.0f, view.getWidth());
                        } else {
                            swipeLayout$c.a(view, this.e, 1.0f, view.getHeight());
                        }
                    }
                }
            }
        }
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.f.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void a() {
        this.h.clear();
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (view != null) {
            int intValue;
            try {
                intValue = ((Integer) layoutParams.getClass().getField("gravity").get(layoutParams)).intValue();
            } catch (Exception e) {
                e.printStackTrace();
                intValue = 0;
            }
            if (intValue <= 0) {
                for (Entry entry : this.h.entrySet()) {
                    if (entry.getValue() == null) {
                        this.h.put(entry.getKey(), view);
                        break;
                    }
                }
            }
            intValue = GravityCompat.getAbsoluteGravity(intValue, ViewCompat.getLayoutDirection(this));
            if ((intValue & 3) == 3) {
                this.h.put(SwipeLayout$DragEdge.Left, view);
            }
            if ((intValue & 5) == 5) {
                this.h.put(SwipeLayout$DragEdge.Right, view);
            }
            if ((intValue & 48) == 48) {
                this.h.put(SwipeLayout$DragEdge.Top, view);
            }
            if ((intValue & 80) == 80) {
                this.h.put(SwipeLayout$DragEdge.Bottom, view);
            }
            if (view.getParent() != this) {
                super.addView(view, i, layoutParams);
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        o();
        if (this.u != null) {
            for (int i5 = 0; i5 < this.u.size(); i5++) {
                ((SwipeLayout$b) this.u.get(i5)).a(this);
            }
        }
    }

    void b() {
        Rect a = a(false);
        View surfaceView = getSurfaceView();
        if (surfaceView != null) {
            surfaceView.layout(a.left, a.top, a.right, a.bottom);
            bringChildToFront(surfaceView);
        }
        a = a(SwipeLayout$ShowMode.PullOut, a);
        surfaceView = getCurrentBottomView();
        if (surfaceView != null) {
            surfaceView.layout(a.left, a.top, a.right, a.bottom);
        }
    }

    void c() {
        Rect a = a(false);
        View surfaceView = getSurfaceView();
        if (surfaceView != null) {
            surfaceView.layout(a.left, a.top, a.right, a.bottom);
            bringChildToFront(surfaceView);
        }
        a = a(SwipeLayout$ShowMode.LayDown, a);
        surfaceView = getCurrentBottomView();
        if (surfaceView != null) {
            surfaceView.layout(a.left, a.top, a.right, a.bottom);
        }
    }

    private void a(MotionEvent motionEvent) {
        boolean z = true;
        if (!this.v) {
            if (getOpenStatus() == SwipeLayout$Status.Middle) {
                this.v = true;
                return;
            }
            boolean z2;
            boolean z3;
            SwipeLayout$Status openStatus = getOpenStatus();
            float rawX = motionEvent.getRawX() - this.w;
            float rawY = motionEvent.getRawY() - this.x;
            float toDegrees = (float) Math.toDegrees(Math.atan((double) Math.abs(rawY / rawX)));
            if (getOpenStatus() == SwipeLayout$Status.Close) {
                SwipeLayout$DragEdge swipeLayout$DragEdge;
                if (toDegrees < 45.0f) {
                    if (rawX > 0.0f && e()) {
                        swipeLayout$DragEdge = SwipeLayout$DragEdge.Left;
                    } else if (rawX < 0.0f && f()) {
                        swipeLayout$DragEdge = SwipeLayout$DragEdge.Right;
                    } else {
                        return;
                    }
                } else if (rawY > 0.0f && g()) {
                    swipeLayout$DragEdge = SwipeLayout$DragEdge.Top;
                } else if (rawY < 0.0f && h()) {
                    swipeLayout$DragEdge = SwipeLayout$DragEdge.Bottom;
                } else {
                    return;
                }
                setCurrentDragEdge(swipeLayout$DragEdge);
            }
            if (this.e == SwipeLayout$DragEdge.Right) {
                if ((openStatus != SwipeLayout$Status.Open || rawX <= ((float) this.d)) && (openStatus != SwipeLayout$Status.Close || rawX >= ((float) (-this.d)))) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (z2 || openStatus == SwipeLayout$Status.Middle) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (toDegrees > 30.0f || !r0) {
                    z2 = true;
                    if (this.e == SwipeLayout$DragEdge.Left) {
                        if ((openStatus == SwipeLayout$Status.Open || rawX >= ((float) (-this.d))) && (openStatus != SwipeLayout$Status.Close || rawX <= ((float) this.d))) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                        if (!z3 || openStatus == SwipeLayout$Status.Middle) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (toDegrees > 30.0f || !r3) {
                            z2 = true;
                        }
                    }
                    if (this.e == SwipeLayout$DragEdge.Top) {
                        if ((openStatus == SwipeLayout$Status.Open || rawY >= ((float) (-this.d))) && (openStatus != SwipeLayout$Status.Close || rawY <= ((float) this.d))) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                        if (!z3 || openStatus == SwipeLayout$Status.Middle) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (toDegrees < 60.0f || !r3) {
                            z2 = true;
                        }
                    }
                    if (this.e == SwipeLayout$DragEdge.Bottom) {
                        if ((openStatus == SwipeLayout$Status.Open || rawY <= ((float) this.d)) && (openStatus != SwipeLayout$Status.Close || rawY >= ((float) (-this.d)))) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                        if (!z3 || openStatus == SwipeLayout$Status.Middle) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (toDegrees < 60.0f || !r3) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        z = false;
                    }
                    this.v = z;
                }
            }
            z2 = false;
            if (this.e == SwipeLayout$DragEdge.Left) {
                if (openStatus == SwipeLayout$Status.Open) {
                }
                z3 = false;
                if (z3) {
                }
                z3 = true;
                z2 = true;
            }
            if (this.e == SwipeLayout$DragEdge.Top) {
                if (openStatus == SwipeLayout$Status.Open) {
                }
                z3 = false;
                if (z3) {
                }
                z3 = true;
                z2 = true;
            }
            if (this.e == SwipeLayout$DragEdge.Bottom) {
                if (openStatus == SwipeLayout$Status.Open) {
                }
                z3 = false;
                if (z3) {
                }
                z3 = true;
                z2 = true;
            }
            if (z2) {
                z = false;
            }
            this.v = z;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!d()) {
            return false;
        }
        if (this.r && getOpenStatus() == SwipeLayout$Status.Open && b(motionEvent)) {
            return true;
        }
        for (SwipeLayout$d swipeLayout$d : this.l) {
            if (swipeLayout$d != null && swipeLayout$d.a(motionEvent)) {
                return false;
            }
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.f.processTouchEvent(motionEvent);
                this.v = false;
                this.w = motionEvent.getRawX();
                this.x = motionEvent.getRawY();
                if (getOpenStatus() == SwipeLayout$Status.Middle) {
                    this.v = true;
                    break;
                }
                break;
            case 1:
            case 3:
                this.v = false;
                this.f.processTouchEvent(motionEvent);
                break;
            case 2:
                boolean z = this.v;
                a(motionEvent);
                if (this.v) {
                    ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (!z && this.v) {
                    return false;
                }
            default:
                this.f.processTouchEvent(motionEvent);
                break;
        }
        return this.v;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!d()) {
            return super.onTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        this.z.onTouchEvent(motionEvent);
        switch (actionMasked) {
            case 0:
                this.f.processTouchEvent(motionEvent);
                this.w = motionEvent.getRawX();
                this.x = motionEvent.getRawY();
                break;
            case 1:
            case 3:
                this.v = false;
                this.f.processTouchEvent(motionEvent);
                break;
            case 2:
                break;
            default:
                this.f.processTouchEvent(motionEvent);
                break;
        }
        a(motionEvent);
        if (this.v) {
            getParent().requestDisallowInterceptTouchEvent(true);
            this.f.processTouchEvent(motionEvent);
        }
        if (super.onTouchEvent(motionEvent) || this.v || actionMasked == 0) {
            return true;
        }
        return false;
    }

    public void setClickToClose(boolean z) {
        this.r = z;
    }

    public void setSwipeEnabled(boolean z) {
        this.p = z;
    }

    public boolean d() {
        return this.p;
    }

    public boolean e() {
        View view = (View) this.h.get(SwipeLayout$DragEdge.Left);
        return view != null && view.getParent() == this && view != getSurfaceView() && this.q[SwipeLayout$DragEdge.Left.ordinal()];
    }

    public void setLeftSwipeEnabled(boolean z) {
        this.q[SwipeLayout$DragEdge.Left.ordinal()] = z;
    }

    public boolean f() {
        View view = (View) this.h.get(SwipeLayout$DragEdge.Right);
        return view != null && view.getParent() == this && view != getSurfaceView() && this.q[SwipeLayout$DragEdge.Right.ordinal()];
    }

    public void setRightSwipeEnabled(boolean z) {
        this.q[SwipeLayout$DragEdge.Right.ordinal()] = z;
    }

    public boolean g() {
        View view = (View) this.h.get(SwipeLayout$DragEdge.Top);
        return view != null && view.getParent() == this && view != getSurfaceView() && this.q[SwipeLayout$DragEdge.Top.ordinal()];
    }

    public void setTopSwipeEnabled(boolean z) {
        this.q[SwipeLayout$DragEdge.Top.ordinal()] = z;
    }

    public boolean h() {
        View view = (View) this.h.get(SwipeLayout$DragEdge.Bottom);
        return view != null && view.getParent() == this && view != getSurfaceView() && this.q[SwipeLayout$DragEdge.Bottom.ordinal()];
    }

    public void setBottomSwipeEnabled(boolean z) {
        this.q[SwipeLayout$DragEdge.Bottom.ordinal()] = z;
    }

    private boolean l() {
        return getAdapterView() != null;
    }

    private AdapterView getAdapterView() {
        ViewParent parent = getParent();
        if (parent instanceof AdapterView) {
            return (AdapterView) parent;
        }
        return null;
    }

    private void m() {
        if (getOpenStatus() == SwipeLayout$Status.Close) {
            ViewParent parent = getParent();
            if (parent instanceof AdapterView) {
                AdapterView adapterView = (AdapterView) parent;
                int positionForView = adapterView.getPositionForView(this);
                if (positionForView != -1) {
                    adapterView.performItemClick(adapterView.getChildAt(positionForView - adapterView.getFirstVisiblePosition()), positionForView, adapterView.getAdapter().getItemId(positionForView));
                }
            }
        }
    }

    private boolean n() {
        boolean onItemLongClick;
        if (getOpenStatus() != SwipeLayout$Status.Close) {
            return false;
        }
        ViewParent parent = getParent();
        if (!(parent instanceof AdapterView)) {
            return false;
        }
        AdapterView adapterView = (AdapterView) parent;
        int positionForView = adapterView.getPositionForView(this);
        if (positionForView == -1) {
            return false;
        }
        long itemIdAtPosition = adapterView.getItemIdAtPosition(positionForView);
        try {
            Method declaredMethod = AbsListView.class.getDeclaredMethod("performLongPress", new Class[]{View.class, Integer.TYPE, Long.TYPE});
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(adapterView, new Object[]{this, Integer.valueOf(positionForView), Long.valueOf(itemIdAtPosition)})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            if (adapterView.getOnItemLongClickListener() != null) {
                onItemLongClick = adapterView.getOnItemLongClickListener().onItemLongClick(adapterView, this, positionForView, itemIdAtPosition);
            } else {
                onItemLongClick = false;
            }
            if (!onItemLongClick) {
                return onItemLongClick;
            }
            adapterView.performHapticFeedback(0);
            return onItemLongClick;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (l()) {
            if (this.a == null) {
                setOnClickListener(new SwipeLayout$2(this));
            }
            if (this.b == null) {
                setOnLongClickListener(new SwipeLayout$3(this));
            }
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.a = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
        this.b = onLongClickListener;
    }

    private boolean b(MotionEvent motionEvent) {
        View surfaceView = getSurfaceView();
        if (surfaceView == null) {
            return false;
        }
        if (this.y == null) {
            this.y = new Rect();
        }
        surfaceView.getHitRect(this.y);
        return this.y.contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    public void setDragDistance(int i) {
        if (i < 0) {
            i = 0;
        }
        this.g = a((float) i);
        requestLayout();
    }

    public void setShowMode(SwipeLayout$ShowMode swipeLayout$ShowMode) {
        this.i = swipeLayout$ShowMode;
        requestLayout();
    }

    public SwipeLayout$DragEdge getDragEdge() {
        return this.e;
    }

    public int getDragDistance() {
        return this.g;
    }

    public SwipeLayout$ShowMode getShowMode() {
        return this.i;
    }

    public View getSurfaceView() {
        if (getChildCount() == 0) {
            return null;
        }
        return getChildAt(getChildCount() - 1);
    }

    @Nullable
    public View getCurrentBottomView() {
        List bottomViews = getBottomViews();
        if (this.e.ordinal() < bottomViews.size()) {
            return (View) bottomViews.get(this.e.ordinal());
        }
        return null;
    }

    public List<View> getBottomViews() {
        List arrayList = new ArrayList();
        for (Object obj : SwipeLayout$DragEdge.values()) {
            arrayList.add(this.h.get(obj));
        }
        return arrayList;
    }

    public SwipeLayout$Status getOpenStatus() {
        View surfaceView = getSurfaceView();
        if (surfaceView == null) {
            return SwipeLayout$Status.Close;
        }
        int left = surfaceView.getLeft();
        int top = surfaceView.getTop();
        if (left == getPaddingLeft() && top == getPaddingTop()) {
            return SwipeLayout$Status.Close;
        }
        if (left == getPaddingLeft() - this.g || left == getPaddingLeft() + this.g || top == getPaddingTop() - this.g || top == getPaddingTop() + this.g) {
            return SwipeLayout$Status.Open;
        }
        return SwipeLayout$Status.Middle;
    }

    protected void a(float f, float f2, boolean z) {
        float minVelocity = this.f.getMinVelocity();
        View surfaceView = getSurfaceView();
        SwipeLayout$DragEdge swipeLayout$DragEdge = this.e;
        if (swipeLayout$DragEdge != null && surfaceView != null) {
            float f3 = z ? 0.25f : 0.75f;
            if (swipeLayout$DragEdge == SwipeLayout$DragEdge.Left) {
                if (f > minVelocity) {
                    i();
                } else if (f < (-minVelocity)) {
                    j();
                } else if ((((float) getSurfaceView().getLeft()) * 1.0f) / ((float) this.g) > f3) {
                    i();
                } else {
                    j();
                }
            } else if (swipeLayout$DragEdge == SwipeLayout$DragEdge.Right) {
                if (f > minVelocity) {
                    j();
                } else if (f < (-minVelocity)) {
                    i();
                } else if ((((float) (-getSurfaceView().getLeft())) * 1.0f) / ((float) this.g) > f3) {
                    i();
                } else {
                    j();
                }
            } else if (swipeLayout$DragEdge == SwipeLayout$DragEdge.Top) {
                if (f2 > minVelocity) {
                    i();
                } else if (f2 < (-minVelocity)) {
                    j();
                } else if ((((float) getSurfaceView().getTop()) * 1.0f) / ((float) this.g) > f3) {
                    i();
                } else {
                    j();
                }
            } else if (swipeLayout$DragEdge != SwipeLayout$DragEdge.Bottom) {
            } else {
                if (f2 > minVelocity) {
                    j();
                } else if (f2 < (-minVelocity)) {
                    i();
                } else if ((((float) (-getSurfaceView().getTop())) * 1.0f) / ((float) this.g) > f3) {
                    i();
                } else {
                    j();
                }
            }
        }
    }

    public void i() {
        a(true, true);
    }

    public void a(boolean z, boolean z2) {
        View surfaceView = getSurfaceView();
        View currentBottomView = getCurrentBottomView();
        if (surfaceView != null) {
            Rect a = a(true);
            if (z) {
                this.f.smoothSlideViewTo(surfaceView, a.left, a.top);
            } else {
                int left = a.left - surfaceView.getLeft();
                int top = a.top - surfaceView.getTop();
                surfaceView.layout(a.left, a.top, a.right, a.bottom);
                if (getShowMode() == SwipeLayout$ShowMode.PullOut) {
                    Rect a2 = a(SwipeLayout$ShowMode.PullOut, a);
                    if (currentBottomView != null) {
                        currentBottomView.layout(a2.left, a2.top, a2.right, a2.bottom);
                    }
                }
                if (z2) {
                    b(a.left, a.top, a.right, a.bottom);
                    a(a.left, a.top, left, top);
                } else {
                    k();
                }
            }
            invalidate();
        }
    }

    public void j() {
        b(true, true);
    }

    public void b(boolean z, boolean z2) {
        View surfaceView = getSurfaceView();
        if (surfaceView != null) {
            if (z) {
                this.f.smoothSlideViewTo(getSurfaceView(), getPaddingLeft(), getPaddingTop());
            } else {
                Rect a = a(false);
                int left = a.left - surfaceView.getLeft();
                int top = a.top - surfaceView.getTop();
                surfaceView.layout(a.left, a.top, a.right, a.bottom);
                if (z2) {
                    b(a.left, a.top, a.right, a.bottom);
                    a(a.left, a.top, left, top);
                } else {
                    k();
                }
            }
            invalidate();
        }
    }

    private Rect a(boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (z) {
            if (this.e == SwipeLayout$DragEdge.Left) {
                paddingLeft = getPaddingLeft() + this.g;
            } else if (this.e == SwipeLayout$DragEdge.Right) {
                paddingLeft = getPaddingLeft() - this.g;
            } else {
                paddingTop = this.e == SwipeLayout$DragEdge.Top ? getPaddingTop() + this.g : getPaddingTop() - this.g;
            }
        }
        return new Rect(paddingLeft, paddingTop, getMeasuredWidth() + paddingLeft, getMeasuredHeight() + paddingTop);
    }

    private Rect a(SwipeLayout$ShowMode swipeLayout$ShowMode, Rect rect) {
        int i = 0;
        View currentBottomView = getCurrentBottomView();
        int i2 = rect.left;
        int i3 = rect.top;
        int i4 = rect.right;
        int i5 = rect.bottom;
        int i6;
        if (swipeLayout$ShowMode == SwipeLayout$ShowMode.PullOut) {
            if (this.e == SwipeLayout$DragEdge.Left) {
                i2 = rect.left - this.g;
            } else if (this.e == SwipeLayout$DragEdge.Right) {
                i2 = rect.right;
            } else {
                i3 = this.e == SwipeLayout$DragEdge.Top ? rect.top - this.g : rect.bottom;
            }
            if (this.e == SwipeLayout$DragEdge.Left || this.e == SwipeLayout$DragEdge.Right) {
                i5 = rect.bottom;
                if (currentBottomView != null) {
                    i = currentBottomView.getMeasuredWidth();
                }
                i6 = i5;
                i5 = i2;
                i2 = i + i2;
                i = i3;
                i3 = i6;
            } else {
                if (currentBottomView != null) {
                    i = currentBottomView.getMeasuredHeight();
                }
                i6 = i + i3;
                i = i3;
                i3 = i6;
                i5 = i2;
                i2 = rect.right;
            }
        } else if (swipeLayout$ShowMode != SwipeLayout$ShowMode.LayDown) {
            i = i3;
            i3 = i5;
            i5 = i2;
            i2 = i4;
        } else if (this.e == SwipeLayout$DragEdge.Left) {
            i6 = i5;
            i5 = i2;
            i2 = this.g + i2;
            i = i3;
            i3 = i6;
        } else if (this.e == SwipeLayout$DragEdge.Right) {
            i = i3;
            i3 = i5;
            i5 = i4 - this.g;
            i2 = i4;
        } else if (this.e == SwipeLayout$DragEdge.Top) {
            i5 = i2;
            i2 = i4;
            i6 = i3;
            i3 = this.g + i3;
            i = i6;
        } else {
            i = i5 - this.g;
            i3 = i5;
            i5 = i2;
            i2 = i4;
        }
        return new Rect(i5, i, i2, i3);
    }

    private Rect a(SwipeLayout$DragEdge swipeLayout$DragEdge) {
        int i;
        int measuredHeight;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (swipeLayout$DragEdge == SwipeLayout$DragEdge.Right) {
            paddingLeft = getMeasuredWidth() - this.g;
        } else if (swipeLayout$DragEdge == SwipeLayout$DragEdge.Bottom) {
            paddingTop = getMeasuredHeight() - this.g;
        }
        if (swipeLayout$DragEdge == SwipeLayout$DragEdge.Left || swipeLayout$DragEdge == SwipeLayout$DragEdge.Right) {
            i = paddingLeft + this.g;
            measuredHeight = getMeasuredHeight() + paddingTop;
        } else {
            i = paddingLeft + getMeasuredWidth();
            measuredHeight = this.g + paddingTop;
        }
        return new Rect(paddingLeft, paddingTop, i, measuredHeight);
    }

    public void setOnDoubleClickListener(SwipeLayout$a swipeLayout$a) {
        this.o = swipeLayout$a;
    }

    private int a(float f) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    @Deprecated
    public void setDragEdge(SwipeLayout$DragEdge swipeLayout$DragEdge) {
        a();
        if (getChildCount() >= 2) {
            this.h.put(swipeLayout$DragEdge, getChildAt(getChildCount() - 2));
        }
        setCurrentDragEdge(swipeLayout$DragEdge);
    }

    public void onViewRemoved(View view) {
        for (Entry entry : new HashMap(this.h).entrySet()) {
            if (entry.getValue() == view) {
                this.h.remove(entry.getKey());
            }
        }
    }

    public Map<SwipeLayout$DragEdge, View> getDragEdgeMap() {
        return this.h;
    }

    @Deprecated
    public List<SwipeLayout$DragEdge> getDragEdges() {
        return new ArrayList(this.h.keySet());
    }

    @Deprecated
    public void setDragEdges(List<SwipeLayout$DragEdge> list) {
        a();
        int min = Math.min(list.size(), getChildCount() - 1);
        for (int i = 0; i < min; i++) {
            this.h.put((SwipeLayout$DragEdge) list.get(i), getChildAt(i));
        }
        if (list.size() == 0 || list.contains(c)) {
            setCurrentDragEdge(c);
        } else {
            setCurrentDragEdge((SwipeLayout$DragEdge) list.get(0));
        }
    }

    @Deprecated
    public void setDragEdges(SwipeLayout$DragEdge... swipeLayout$DragEdgeArr) {
        a();
        setDragEdges(Arrays.asList(swipeLayout$DragEdgeArr));
    }

    private float getCurrentOffset() {
        if (this.e == null) {
            return 0.0f;
        }
        return this.j[this.e.ordinal()];
    }

    private void setCurrentDragEdge(SwipeLayout$DragEdge swipeLayout$DragEdge) {
        this.e = swipeLayout$DragEdge;
        o();
    }

    private void o() {
        View currentBottomView = getCurrentBottomView();
        if (currentBottomView != null) {
            if (this.e == SwipeLayout$DragEdge.Left || this.e == SwipeLayout$DragEdge.Right) {
                this.g = currentBottomView.getMeasuredWidth() - a(getCurrentOffset());
            } else {
                this.g = currentBottomView.getMeasuredHeight() - a(getCurrentOffset());
            }
        }
        if (this.i == SwipeLayout$ShowMode.PullOut) {
            b();
        } else if (this.i == SwipeLayout$ShowMode.LayDown) {
            c();
        }
        k();
    }
}
