package in.srain.cube.views.ptr;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import in.srain.cube.views.ptr.a.a;

public class PtrFrameLayout extends ViewGroup {
    public static boolean a = false;
    private static int e = 1;
    private static byte f = (byte) 1;
    private static byte g = (byte) 2;
    private static byte h = (byte) 4;
    private static byte i = (byte) 8;
    private static byte j = (byte) 3;
    private d A;
    private int B;
    private long C;
    private a D;
    private boolean E;
    private Runnable F;
    protected final String b;
    protected View c;
    private byte d;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private View q;
    private c r;
    private a s;
    private PtrFrameLayout$b t;
    private int u;
    private int v;
    private boolean w;
    private int x;
    private boolean y;
    private MotionEvent z;

    public PtrFrameLayout(Context context) {
        this(context, null);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = (byte) 1;
        StringBuilder append = new StringBuilder().append("ptr-frame-");
        int i2 = e + 1;
        e = i2;
        this.b = append.append(i2).toString();
        this.k = 0;
        this.l = 0;
        this.m = 200;
        this.n = 1000;
        this.o = true;
        this.p = false;
        this.r = c.b();
        this.w = false;
        this.x = 0;
        this.y = false;
        this.B = 500;
        this.C = 0;
        this.E = false;
        this.F = new PtrFrameLayout$1(this);
        this.D = new a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.a.PtrFrameLayout, 0, 0);
        if (obtainStyledAttributes != null) {
            this.k = obtainStyledAttributes.getResourceId(e.a.PtrFrameLayout_ptr_header, this.k);
            this.l = obtainStyledAttributes.getResourceId(e.a.PtrFrameLayout_ptr_content, this.l);
            this.D.a(obtainStyledAttributes.getFloat(e.a.PtrFrameLayout_ptr_resistance, this.D.b()));
            this.m = obtainStyledAttributes.getInt(e.a.PtrFrameLayout_ptr_duration_to_close, this.m);
            this.n = obtainStyledAttributes.getInt(e.a.PtrFrameLayout_ptr_duration_to_close_header, this.n);
            this.D.b(obtainStyledAttributes.getFloat(e.a.PtrFrameLayout_ptr_ratio_of_header_height_to_refresh, this.D.f()));
            this.o = obtainStyledAttributes.getBoolean(e.a.PtrFrameLayout_ptr_keep_header_when_refresh, this.o);
            this.p = obtainStyledAttributes.getBoolean(e.a.PtrFrameLayout_ptr_pull_to_fresh, this.p);
            obtainStyledAttributes.recycle();
        }
        this.t = new PtrFrameLayout$b(this);
        this.u = ViewConfiguration.get(getContext()).getScaledTouchSlop() * 2;
    }

    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount > 2) {
            throw new IllegalStateException("PtrFrameLayout only can host 2 elements");
        }
        View childAt;
        if (childCount == 2) {
            if (this.k != 0 && this.q == null) {
                this.q = findViewById(this.k);
            }
            if (this.l != 0 && this.c == null) {
                this.c = findViewById(this.l);
            }
            if (this.c == null || this.q == null) {
                View childAt2 = getChildAt(0);
                childAt = getChildAt(1);
                if (childAt2 instanceof b) {
                    this.q = childAt2;
                    this.c = childAt;
                } else if (childAt instanceof b) {
                    this.q = childAt;
                    this.c = childAt2;
                } else if (this.c == null && this.q == null) {
                    this.q = childAt2;
                    this.c = childAt;
                } else if (this.q == null) {
                    if (this.c != childAt2) {
                        childAt = childAt2;
                    }
                    this.q = childAt;
                } else {
                    if (this.q != childAt2) {
                        childAt = childAt2;
                    }
                    this.c = childAt;
                }
            }
        } else if (childCount == 1) {
            this.c = getChildAt(0);
        } else {
            childAt = new TextView(getContext());
            childAt.setClickable(true);
            childAt.setTextColor(-39424);
            childAt.setGravity(17);
            childAt.setTextSize(20.0f);
            childAt.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
            this.c = childAt;
            addView(this.c);
        }
        if (this.q != null) {
            this.q.bringToFront();
        }
        super.onFinishInflate();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.t != null) {
            PtrFrameLayout$b.a(this.t);
        }
        if (this.F != null) {
            removeCallbacks(this.F);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (a) {
            in.srain.cube.views.ptr.b.a.b(this.b, "onMeasure frame: width: %s, height: %s, padding: %s %s %s %s", new Object[]{Integer.valueOf(getMeasuredHeight()), Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getPaddingLeft()), Integer.valueOf(getPaddingRight()), Integer.valueOf(getPaddingTop()), Integer.valueOf(getPaddingBottom())});
        }
        if (this.q != null) {
            measureChildWithMargins(this.q, i, 0, i2, 0);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.q.getLayoutParams();
            this.v = marginLayoutParams.bottomMargin + (this.q.getMeasuredHeight() + marginLayoutParams.topMargin);
            this.D.c(this.v);
        }
        if (this.c != null) {
            a(this.c, i, i2);
            if (a) {
                marginLayoutParams = (MarginLayoutParams) this.c.getLayoutParams();
                in.srain.cube.views.ptr.b.a.b(this.b, "onMeasure content, width: %s, height: %s, margin: %s %s %s %s", new Object[]{Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getMeasuredHeight()), Integer.valueOf(marginLayoutParams.leftMargin), Integer.valueOf(marginLayoutParams.topMargin), Integer.valueOf(marginLayoutParams.rightMargin), Integer.valueOf(marginLayoutParams.bottomMargin)});
                in.srain.cube.views.ptr.b.a.b(this.b, "onMeasure, currentPos: %s, lastPos: %s, top: %s", new Object[]{Integer.valueOf(this.D.k()), Integer.valueOf(this.D.j()), Integer.valueOf(this.c.getTop())});
            }
        }
    }

    private void a(View view, int i, int i2) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin, marginLayoutParams.width), getChildMeasureSpec(i2, (getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin, marginLayoutParams.height));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        h();
    }

    private void h() {
        int i;
        int k = this.D.k();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (this.q != null) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.q.getLayoutParams();
            int i2 = marginLayoutParams.leftMargin + paddingLeft;
            i = ((marginLayoutParams.topMargin + paddingTop) + k) - this.v;
            this.q.layout(i2, i, this.q.getMeasuredWidth() + i2, this.q.getMeasuredHeight() + i);
            if (a) {
                in.srain.cube.views.ptr.b.a.b(this.b, "onLayout header: %s %s %s %s", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(r6), Integer.valueOf(r7)});
            }
        }
        if (this.c != null) {
            if (g()) {
                k = 0;
            }
            marginLayoutParams = (MarginLayoutParams) this.c.getLayoutParams();
            paddingLeft += marginLayoutParams.leftMargin;
            i = (marginLayoutParams.topMargin + paddingTop) + k;
            k = this.c.getMeasuredWidth() + paddingLeft;
            paddingTop = this.c.getMeasuredHeight() + i;
            if (a) {
                in.srain.cube.views.ptr.b.a.b(this.b, "onLayout content: %s %s %s %s", new Object[]{Integer.valueOf(paddingLeft), Integer.valueOf(i), Integer.valueOf(k), Integer.valueOf(paddingTop)});
            }
            this.c.layout(paddingLeft, i, k, paddingTop);
        }
    }

    public boolean a(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || this.c == null || this.q == null) {
            return a(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.E = false;
                this.D.a(motionEvent.getX(), motionEvent.getY());
                this.t.a();
                this.y = false;
                a(motionEvent);
                return true;
            case 1:
            case 3:
                this.D.c();
                if (!this.D.m()) {
                    return a(motionEvent);
                }
                if (a) {
                    in.srain.cube.views.ptr.b.a.a(this.b, "call onRelease when user release");
                }
                b(false);
                if (!this.D.q()) {
                    return a(motionEvent);
                }
                s();
                return true;
            case 2:
                this.z = motionEvent;
                this.D.b(motionEvent.getX(), motionEvent.getY());
                float h = this.D.h();
                float i = this.D.i();
                if (this.w && !this.y && Math.abs(h) > ((float) this.u) && Math.abs(h) > Math.abs(i) && this.D.r()) {
                    this.y = true;
                }
                if (this.y) {
                    return a(motionEvent);
                }
                boolean z;
                boolean z2 = i > 0.0f;
                if (z2) {
                    z = false;
                } else {
                    z = true;
                }
                boolean m = this.D.m();
                if (a) {
                    boolean z3;
                    if (this.s == null || !this.s.a(this, this.c, this.q)) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    in.srain.cube.views.ptr.b.a.a(this.b, "ACTION_MOVE: offsetY:%s, currentPos: %s, moveUp: %s, canMoveUp: %s, moveDown: %s: canMoveDown: %s", new Object[]{Float.valueOf(i), Integer.valueOf(this.D.k()), Boolean.valueOf(z), Boolean.valueOf(m), Boolean.valueOf(z2), Boolean.valueOf(z3)});
                }
                if (z2 && this.s != null && !this.s.a(this, this.c, this.q)) {
                    return a(motionEvent);
                }
                if ((z && m) || z2) {
                    a(i);
                    return true;
                }
                break;
        }
        return a(motionEvent);
    }

    private void a(float f) {
        int i = 0;
        if (f >= 0.0f || !this.D.r()) {
            int k = this.D.k() + ((int) f);
            if (!this.D.f(k)) {
                i = k;
            } else if (a) {
                in.srain.cube.views.ptr.b.a.c(this.b, String.format("over top", new Object[0]));
            }
            this.D.b(i);
            a(i - this.D.j());
        } else if (a) {
            in.srain.cube.views.ptr.b.a.c(this.b, String.format("has reached the top", new Object[0]));
        }
    }

    private void a(int i) {
        if (i != 0) {
            boolean a = this.D.a();
            if (a && !this.E && this.D.q()) {
                this.E = true;
                s();
            }
            if ((this.D.n() && this.d == (byte) 1) || (this.D.e() && this.d == (byte) 4 && f())) {
                this.d = (byte) 2;
                this.r.b(this);
                if (a) {
                    in.srain.cube.views.ptr.b.a.c(this.b, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", new Object[]{Integer.valueOf(this.x)});
                }
            }
            if (this.D.o()) {
                o();
                if (a) {
                    t();
                }
            }
            if (this.d == (byte) 2) {
                if (a && !e() && this.p && this.D.s()) {
                    m();
                }
                if (r() && this.D.t()) {
                    m();
                }
            }
            if (a) {
                in.srain.cube.views.ptr.b.a.a(this.b, "updatePos: change: %s, current: %s last: %s, top: %s, headerHeight: %s", new Object[]{Integer.valueOf(i), Integer.valueOf(this.D.k()), Integer.valueOf(this.D.j()), Integer.valueOf(this.c.getTop()), Integer.valueOf(this.v)});
            }
            this.q.offsetTopAndBottom(i);
            if (!g()) {
                this.c.offsetTopAndBottom(i);
            }
            invalidate();
            if (this.r.a()) {
                this.r.a(this, a, this.d, this.D);
            }
            a(a, this.d, this.D);
        }
    }

    protected void a(boolean z, byte b, a aVar) {
    }

    public int getHeaderHeight() {
        return this.v;
    }

    private void b(boolean z) {
        m();
        if (this.d == (byte) 3) {
            if (!this.o) {
                j();
            } else if (this.D.u() && !z) {
                this.t.a(this.D.v(), this.m);
            }
        } else if (this.d == (byte) 4) {
            c(false);
        } else {
            l();
        }
    }

    public void setRefreshCompleteHook(d dVar) {
        this.A = dVar;
        dVar.b(new PtrFrameLayout$2(this));
    }

    private void i() {
        if (!this.D.a()) {
            this.t.a(0, this.n);
        }
    }

    private void j() {
        i();
    }

    private void k() {
        i();
    }

    private void l() {
        i();
    }

    private boolean m() {
        if (this.d == (byte) 2 && ((this.D.u() && e()) || this.D.p())) {
            this.d = (byte) 3;
            n();
        }
        return false;
    }

    private void n() {
        this.C = System.currentTimeMillis();
        if (this.r.a()) {
            this.r.c(this);
            if (a) {
                in.srain.cube.views.ptr.b.a.b(this.b, "PtrUIHandler: onUIRefreshBegin");
            }
        }
        if (this.s != null) {
            this.s.a(this);
        }
    }

    private boolean o() {
        if ((this.d != (byte) 4 && this.d != (byte) 2) || !this.D.r()) {
            return false;
        }
        if (this.r.a()) {
            this.r.a(this);
            if (a) {
                in.srain.cube.views.ptr.b.a.b(this.b, "PtrUIHandler: onUIReset");
            }
        }
        this.d = (byte) 1;
        q();
        return true;
    }

    protected void a() {
        if (this.D.m() && e()) {
            if (a) {
                in.srain.cube.views.ptr.b.a.a(this.b, "call onRelease after scroll abort");
            }
            b(true);
        }
    }

    protected void b() {
        if (this.D.m() && e()) {
            if (a) {
                in.srain.cube.views.ptr.b.a.a(this.b, "call onRelease after scroll finish");
            }
            b(true);
        }
    }

    public final void c() {
        if (a) {
            in.srain.cube.views.ptr.b.a.b(this.b, "refreshComplete");
        }
        if (this.A != null) {
            this.A.b();
        }
        int currentTimeMillis = (int) (((long) this.B) - (System.currentTimeMillis() - this.C));
        if (currentTimeMillis <= 0) {
            if (a) {
                in.srain.cube.views.ptr.b.a.a(this.b, "performRefreshComplete at once");
            }
            p();
            return;
        }
        postDelayed(this.F, (long) currentTimeMillis);
        if (a) {
            in.srain.cube.views.ptr.b.a.b(this.b, "performRefreshComplete after delay: %s", new Object[]{Integer.valueOf(currentTimeMillis)});
        }
    }

    private void p() {
        this.d = (byte) 4;
        if (!PtrFrameLayout$b.b(this.t) || !e()) {
            c(false);
        } else if (a) {
            in.srain.cube.views.ptr.b.a.b(this.b, "performRefreshComplete do nothing, scrolling: %s, auto refresh: %s", new Object[]{Boolean.valueOf(PtrFrameLayout$b.b(this.t)), Integer.valueOf(this.x)});
        }
    }

    private void c(boolean z) {
        if (!this.D.m() || z || this.A == null) {
            if (this.r.a()) {
                if (a) {
                    in.srain.cube.views.ptr.b.a.b(this.b, "PtrUIHandler: onUIRefreshComplete");
                }
                this.r.d(this);
            }
            this.D.d();
            k();
            o();
            return;
        }
        if (a) {
            in.srain.cube.views.ptr.b.a.a(this.b, "notifyUIRefreshComplete mRefreshCompleteHook run.");
        }
        this.A.a();
    }

    public void d() {
        a(true, this.n);
    }

    private void q() {
        this.x &= j ^ -1;
    }

    public void a(boolean z, int i) {
        if (this.d == (byte) 1) {
            this.x = (z ? f : g) | this.x;
            this.d = (byte) 2;
            if (this.r.a()) {
                this.r.b(this);
                if (a) {
                    in.srain.cube.views.ptr.b.a.c(this.b, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", new Object[]{Integer.valueOf(this.x)});
                }
            }
            this.t.a(this.D.g(), i);
            if (z) {
                this.d = (byte) 3;
                n();
            }
        }
    }

    public boolean e() {
        return (this.x & j) > 0;
    }

    private boolean r() {
        return (this.x & j) == g;
    }

    public boolean f() {
        return (this.x & h) > 0;
    }

    public void setEnabledNextPtrAtOnce(boolean z) {
        if (z) {
            this.x |= h;
        } else {
            this.x &= h ^ -1;
        }
    }

    public boolean g() {
        return (this.x & i) > 0;
    }

    public void setPinContent(boolean z) {
        if (z) {
            this.x |= i;
        } else {
            this.x &= i ^ -1;
        }
    }

    public void a(boolean z) {
        this.w = z;
    }

    public void setLoadingMinTime(int i) {
        this.B = i;
    }

    @Deprecated
    public void setInterceptEventWhileWorking(boolean z) {
    }

    public View getContentView() {
        return this.c;
    }

    public void setPtrHandler(a aVar) {
        this.s = aVar;
    }

    public void a(b bVar) {
        c.a(this.r, bVar);
    }

    public void setPtrIndicator(a aVar) {
        if (!(this.D == null || this.D == aVar)) {
            aVar.a(this.D);
        }
        this.D = aVar;
    }

    public float getResistance() {
        return this.D.b();
    }

    public void setResistance(float f) {
        this.D.a(f);
    }

    public float getDurationToClose() {
        return (float) this.m;
    }

    public void setDurationToClose(int i) {
        this.m = i;
    }

    public long getDurationToCloseHeader() {
        return (long) this.n;
    }

    public void setDurationToCloseHeader(int i) {
        this.n = i;
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        this.D.b(f);
    }

    public int getOffsetToRefresh() {
        return this.D.g();
    }

    public void setOffsetToRefresh(int i) {
        this.D.a(i);
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.D.f();
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return this.D.v();
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        this.D.d(i);
    }

    public void setKeepHeaderWhenRefresh(boolean z) {
        this.o = z;
    }

    public void setPullToRefresh(boolean z) {
        this.p = z;
    }

    public View getHeaderView() {
        return this.q;
    }

    public void setHeaderView(View view) {
        if (!(this.q == null || view == null || this.q == view)) {
            removeView(this.q);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new PtrFrameLayout$a(-1, -2));
        }
        this.q = view;
        addView(view);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof PtrFrameLayout$a;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new PtrFrameLayout$a(-1, -1);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new PtrFrameLayout$a(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new PtrFrameLayout$a(getContext(), attributeSet);
    }

    private void s() {
        if (a) {
            in.srain.cube.views.ptr.b.a.a(this.b, "send cancel event");
        }
        if (this.z != null) {
            MotionEvent motionEvent = this.z;
            a(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime() + ((long) ViewConfiguration.getLongPressTimeout()), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
        }
    }

    private void t() {
        if (a) {
            in.srain.cube.views.ptr.b.a.a(this.b, "send down event");
        }
        MotionEvent motionEvent = this.z;
        a(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }
}
