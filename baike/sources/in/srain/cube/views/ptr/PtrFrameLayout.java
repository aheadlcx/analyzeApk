package in.srain.cube.views.ptr;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ListViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.util.PtrCLog;
import qsbk.app.R;

public class PtrFrameLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    public static boolean DEBUG = false;
    public static final byte PTR_STATUS_COMPLETE = (byte) 4;
    public static final byte PTR_STATUS_INIT = (byte) 1;
    public static final byte PTR_STATUS_LOADING = (byte) 3;
    public static final byte PTR_STATUS_PREPARE = (byte) 2;
    private static int d = 1;
    private boolean A;
    private int B;
    private int[] C;
    private int[] D;
    private PointF E;
    private int F;
    private boolean G;
    protected final String a;
    protected View b;
    private byte c;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private View k;
    private d l;
    private PtrHandler m;
    public PtrIndicator mPtrIndicator;
    private PtrFrameLayout$a n;
    private int o;
    private int p;
    private int q;
    private final NestedScrollingParentHelper r;
    private final NestedScrollingChildHelper s;
    private boolean t;
    private MotionEvent u;
    private PtrUIHandlerHook v;
    private int w;
    private long x;
    private boolean y;
    private Runnable z;

    public PtrFrameLayout(Context context) {
        this(context, null);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = (byte) 1;
        StringBuilder append = new StringBuilder().append("ptr-frame-");
        int i2 = d + 1;
        d = i2;
        this.a = append.append(i2).toString();
        this.e = 0;
        this.f = 0;
        this.g = 200;
        this.h = 200;
        this.i = true;
        this.j = false;
        this.l = d.create();
        this.q = 0;
        this.t = false;
        this.w = 500;
        this.x = 0;
        this.y = false;
        this.z = new b(this);
        this.C = new int[2];
        this.D = new int[2];
        this.F = -1;
        this.G = false;
        this.mPtrIndicator = new PtrIndicator();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PtrFrameLayout, 0, 0);
        if (obtainStyledAttributes != null) {
            this.e = obtainStyledAttributes.getResourceId(0, this.e);
            this.f = obtainStyledAttributes.getResourceId(1, this.f);
            this.mPtrIndicator.setResistance(obtainStyledAttributes.getFloat(2, this.mPtrIndicator.getResistance()));
            this.g = obtainStyledAttributes.getInt(4, this.g);
            this.h = obtainStyledAttributes.getInt(5, this.h);
            this.mPtrIndicator.setRatioOfHeaderHeightToRefresh(obtainStyledAttributes.getFloat(3, this.mPtrIndicator.getRatioOfHeaderToHeightRefresh()));
            this.i = obtainStyledAttributes.getBoolean(7, this.i);
            this.j = obtainStyledAttributes.getBoolean(6, this.j);
            obtainStyledAttributes.recycle();
        }
        this.n = new PtrFrameLayout$a(this);
        this.o = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.r = new NestedScrollingParentHelper(this);
        this.s = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount > 2) {
            throw new IllegalStateException("PtrFrameLayout can only contains 2 children");
        }
        View childAt;
        if (childCount == 2) {
            if (this.e != 0 && this.k == null) {
                this.k = findViewById(this.e);
            }
            if (this.f != 0 && this.b == null) {
                this.b = findViewById(this.f);
            }
            if (this.b == null || this.k == null) {
                View childAt2 = getChildAt(0);
                childAt = getChildAt(1);
                if (childAt2 instanceof PtrUIHandler) {
                    this.k = childAt2;
                    this.b = childAt;
                } else if (childAt instanceof PtrUIHandler) {
                    this.k = childAt;
                    this.b = childAt2;
                } else if (this.b == null && this.k == null) {
                    this.k = childAt2;
                    this.b = childAt;
                } else if (this.k == null) {
                    if (this.b != childAt2) {
                        childAt = childAt2;
                    }
                    this.k = childAt;
                } else {
                    if (this.k != childAt2) {
                        childAt = childAt2;
                    }
                    this.b = childAt;
                }
            }
        } else if (childCount == 1) {
            this.b = getChildAt(0);
        } else {
            childAt = new TextView(getContext());
            childAt.setClickable(true);
            childAt.setTextColor(-39424);
            childAt.setGravity(17);
            childAt.setTextSize(20.0f);
            childAt.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
            this.b = childAt;
            addView(this.b);
        }
        if (this.k != null) {
            this.k.bringToFront();
        }
        super.onFinishInflate();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.n != null) {
            PtrFrameLayout$a.a(this.n);
        }
        if (this.z != null) {
            removeCallbacks(this.z);
        }
    }

    protected void onMeasure(int i, int i2) {
        MarginLayoutParams marginLayoutParams;
        super.onMeasure(i, i2);
        if (d()) {
            PtrCLog.d(this.a, "onMeasure frame: width: %s, height: %s, padding: %s %s %s %s", new Object[]{Integer.valueOf(getMeasuredHeight()), Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getPaddingLeft()), Integer.valueOf(getPaddingRight()), Integer.valueOf(getPaddingTop()), Integer.valueOf(getPaddingBottom())});
        }
        if (this.k != null) {
            int i3;
            measureChildWithMargins(this.k, i, 0, i2, 0);
            marginLayoutParams = (MarginLayoutParams) this.k.getLayoutParams();
            int measuredHeight = this.k.getMeasuredHeight();
            if (isHeaderLayoutMarginEnable()) {
                i3 = 0;
            } else {
                i3 = marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
            }
            this.p = i3 + measuredHeight;
            this.mPtrIndicator.setHeaderHeight(this.p);
        }
        if (this.b != null) {
            a(this.b, i, i2);
            if (d()) {
                marginLayoutParams = (MarginLayoutParams) this.b.getLayoutParams();
                PtrCLog.d(this.a, "onMeasure content, width: %s, height: %s, margin: %s %s %s %s", new Object[]{Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getMeasuredHeight()), Integer.valueOf(marginLayoutParams.leftMargin), Integer.valueOf(marginLayoutParams.topMargin), Integer.valueOf(marginLayoutParams.rightMargin), Integer.valueOf(marginLayoutParams.bottomMargin)});
                PtrCLog.d(this.a, "onMeasure, currentPos: %s, lastPos: %s, top: %s", new Object[]{Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Integer.valueOf(this.mPtrIndicator.getLastPosY()), Integer.valueOf(this.b.getTop())});
            }
        }
    }

    private void a(View view, int i, int i2) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin, marginLayoutParams.width), getChildMeasureSpec(i2, (getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin, marginLayoutParams.height));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        c();
    }

    private void c() {
        int i;
        int currentPosY = this.mPtrIndicator.getCurrentPosY();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (this.k != null) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.k.getLayoutParams();
            int i2 = marginLayoutParams.leftMargin + paddingLeft;
            i = -(((this.p - paddingTop) - marginLayoutParams.topMargin) - currentPosY);
            this.k.layout(i2, i, this.k.getMeasuredWidth() + i2, this.k.getMeasuredHeight() + i);
            if (d()) {
                PtrCLog.d(this.a, "onLayout header: %s %s %s %s", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(r6), Integer.valueOf(r7)});
            }
        }
        if (this.b != null) {
            if (isPinContent()) {
                currentPosY = 0;
            }
            marginLayoutParams = (MarginLayoutParams) this.b.getLayoutParams();
            paddingLeft += marginLayoutParams.leftMargin;
            i = (marginLayoutParams.topMargin + paddingTop) + currentPosY;
            currentPosY = this.b.getMeasuredWidth() + paddingLeft;
            paddingTop = this.b.getMeasuredHeight() + i;
            if (d()) {
                PtrCLog.d(this.a, "onLayout content: %s %s %s %s", new Object[]{Integer.valueOf(paddingLeft), Integer.valueOf(i), Integer.valueOf(currentPosY), Integer.valueOf(paddingTop)});
            }
            this.b.layout(paddingLeft, i, currentPosY, paddingTop);
        }
    }

    private boolean d() {
        return DEBUG;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.u = motionEvent;
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || this.b == null || this.k == null) {
            return false;
        }
        if (isRefreshing() && !this.mPtrIndicator.isInStartPosition()) {
            return true;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.F = motionEvent.getPointerId(0);
                int findPointerIndex = motionEvent.findPointerIndex(this.F);
                this.G = false;
                if (findPointerIndex >= 0) {
                    this.E = new PointF(motionEvent.getX(findPointerIndex), motionEvent.getY(findPointerIndex));
                    break;
                }
                return false;
            case 1:
            case 3:
                this.G = false;
                break;
            case 2:
                if (this.F == -1) {
                    if (!DEBUG) {
                        return false;
                    }
                    PtrCLog.e(this.a, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                } else if (motionEvent.findPointerIndex(this.F) >= 0) {
                    a(new PointF(motionEvent.getX(), motionEvent.getY()));
                    break;
                } else {
                    return false;
                }
        }
        PtrCLog.d(this.a, "is being dragged " + this.G);
        return this.G;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || canChildScrollUp()) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.y = false;
                this.n.abortIfWorking();
                if (canChildScrollUp()) {
                    return false;
                }
                if (DEBUG) {
                    PtrCLog.d(this.a, "handle action down touch event");
                }
                this.mPtrIndicator.onPressDown(motionEvent.getX(), motionEvent.getY());
                return true;
            case 1:
            case 3:
                this.mPtrIndicator.onRelease();
                this.G = false;
                if (this.mPtrIndicator.hasLeftStartPosition()) {
                    if (DEBUG) {
                        PtrCLog.d(this.a, "call onRelease when user release");
                    }
                    a(false);
                    if (this.mPtrIndicator.hasMovedAfterPressedDown()) {
                        return false;
                    }
                }
                return true;
            case 2:
                boolean z;
                boolean z2;
                if (this.mPtrIndicator.isUnderTouch()) {
                    this.mPtrIndicator.onMove(motionEvent.getX(), motionEvent.getY());
                } else {
                    this.mPtrIndicator.onPressDown(motionEvent.getX(), motionEvent.getY());
                }
                float offsetY = this.mPtrIndicator.getOffsetY();
                if (offsetY > 0.0f) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                boolean hasLeftStartPosition = this.mPtrIndicator.hasLeftStartPosition();
                if (DEBUG) {
                    boolean z3;
                    if (this.m == null || !this.m.checkCanDoRefresh(this, this.b, this.k)) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    PtrCLog.v(this.a, "ACTION_MOVE: offsetY:%s, currentPos: %s, moveUp: %s, canMoveUp: %s, moveDown: %s: canMoveDown: %s", new Object[]{Float.valueOf(offsetY), Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Boolean.valueOf(z2), Boolean.valueOf(hasLeftStartPosition), Boolean.valueOf(z), Boolean.valueOf(z3)});
                }
                if (this.mPtrIndicator.getCurrentPosY() != 0) {
                    a(offsetY);
                    return true;
                } else if (z && this.m != null && !this.m.checkCanDoRefresh(this, this.b, this.k)) {
                    return false;
                } else {
                    if ((z2 && hasLeftStartPosition) || z) {
                        a(offsetY);
                        return true;
                    }
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(float f) {
        int i = 0;
        if (f >= 0.0f || !this.mPtrIndicator.isInStartPosition()) {
            int currentPosY = this.mPtrIndicator.getCurrentPosY() + ((int) f);
            if (!this.mPtrIndicator.willOverTop(currentPosY)) {
                i = currentPosY;
            } else if (DEBUG) {
                PtrCLog.e(this.a, String.format("over top", new Object[0]));
            }
            this.mPtrIndicator.setCurrentPos(i);
            if (this.mPtrIndicator.isInStartPosition() && isRefreshing()) {
                l();
            }
            a(i - this.mPtrIndicator.getLastPosY());
        } else if (DEBUG) {
            PtrCLog.e(this.a, String.format("has reached the top", new Object[0]));
        }
    }

    private void a(int i) {
        if (i != 0) {
            boolean isUnderTouch = this.mPtrIndicator.isUnderTouch();
            if (isUnderTouch && !this.y && this.mPtrIndicator.hasMovedAfterPressedDown()) {
                this.y = true;
                o();
            }
            if ((this.mPtrIndicator.hasJustLeftStartPosition() && this.c == (byte) 1) || (this.mPtrIndicator.goDownCrossFinishPosition() && this.c == (byte) 4 && isEnabledNextPtrAtOnce())) {
                this.c = (byte) 2;
                this.l.onUIRefreshPrepare(this);
                if (DEBUG) {
                    PtrCLog.i(this.a, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", new Object[]{Integer.valueOf(this.q)});
                }
            }
            if (this.mPtrIndicator.hasJustBackToStartPosition()) {
                k();
                if (isUnderTouch) {
                    p();
                }
            }
            if (this.c == (byte) 2) {
                if (isUnderTouch && !isAutoRefresh() && this.j && this.mPtrIndicator.crossRefreshLineFromTopToBottom()) {
                    i();
                }
                if (n() && this.mPtrIndicator.hasJustReachedHeaderHeightFromTopToBottom()) {
                    i();
                }
            }
            if (DEBUG) {
                PtrCLog.v(this.a, "updatePos: change: %s, current: %s last: %s, top: %s, headerHeight: %s", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Integer.valueOf(this.mPtrIndicator.getLastPosY()), Integer.valueOf(this.b.getTop()), Integer.valueOf(this.p)});
            }
            this.k.offsetTopAndBottom(i);
            if (!isPinContent()) {
                this.b.offsetTopAndBottom(i);
            }
            invalidate();
            if (this.l.hasHandler()) {
                this.l.onUIPositionChange(this, isUnderTouch, this.c, this.mPtrIndicator);
            }
            a(isUnderTouch, this.c, this.mPtrIndicator);
        }
    }

    protected void a(boolean z, byte b, PtrIndicator ptrIndicator) {
    }

    public int getHeaderHeight() {
        return this.p;
    }

    private void a(boolean z) {
        i();
        if (this.c == (byte) 3) {
            if (!this.i) {
                f();
            } else if (this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading() && !z) {
                this.n.tryToScrollTo(this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading(), this.g);
            }
        } else if (this.c == (byte) 4) {
            b(false);
        } else {
            h();
        }
    }

    public void setRefreshCompleteHook(PtrUIHandlerHook ptrUIHandlerHook) {
        this.v = ptrUIHandlerHook;
        ptrUIHandlerHook.setResumeAction(new c(this));
    }

    public void tryResetScroll() {
        this.n.abortIfWorking();
        this.mPtrIndicator.setCurrentPos(0);
        a(0 - this.mPtrIndicator.getLastPosY());
    }

    private void e() {
        if (!this.mPtrIndicator.isUnderTouch()) {
            this.n.tryToScrollTo(0, this.h);
        }
    }

    private void f() {
        e();
    }

    private void g() {
        e();
    }

    private void h() {
        e();
    }

    private boolean i() {
        if (this.c == (byte) 2 && ((this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading() && isAutoRefresh()) || this.mPtrIndicator.isOverOffsetToRefresh())) {
            this.c = (byte) 3;
            j();
        }
        return false;
    }

    private void j() {
        this.x = System.currentTimeMillis();
        if (this.l.hasHandler()) {
            this.l.onUIRefreshBegin(this);
            if (DEBUG) {
                PtrCLog.i(this.a, "PtrUIHandler: onUIRefreshBegin");
            }
        }
        if (this.m != null) {
            this.m.onRefreshBegin(this);
        }
    }

    private boolean k() {
        if ((this.c != (byte) 4 && this.c != (byte) 2) || !this.mPtrIndicator.isInStartPosition()) {
            return false;
        }
        if (this.l.hasHandler()) {
            this.l.onUIReset(this);
            if (DEBUG) {
                PtrCLog.i(this.a, "PtrUIHandler: onUIReset");
            }
        }
        this.c = (byte) 1;
        m();
        return true;
    }

    protected void a() {
        if (this.mPtrIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            if (DEBUG) {
                PtrCLog.d(this.a, "call onRelease after scroll abort");
            }
            a(true);
        }
    }

    protected void b() {
        if (this.mPtrIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            if (DEBUG) {
                PtrCLog.d(this.a, "call onRelease after scroll finish");
            }
            a(true);
        }
    }

    public boolean isRefreshing() {
        return this.c == (byte) 3;
    }

    public final void refreshComplete() {
        if (DEBUG) {
            PtrCLog.i(this.a, "refreshComplete");
        }
        if (this.v != null) {
            this.v.reset();
        }
        int currentTimeMillis = (int) (((long) this.w) - (System.currentTimeMillis() - this.x));
        if (currentTimeMillis <= 0) {
            if (DEBUG) {
                PtrCLog.d(this.a, "performRefreshComplete at once");
            }
            l();
            return;
        }
        postDelayed(this.z, (long) currentTimeMillis);
        if (DEBUG) {
            PtrCLog.d(this.a, "performRefreshComplete after delay: %s", new Object[]{Integer.valueOf(currentTimeMillis)});
        }
    }

    private void l() {
        this.c = (byte) 4;
        if (!PtrFrameLayout$a.b(this.n) || !isAutoRefresh()) {
            b(false);
        } else if (DEBUG) {
            PtrCLog.d(this.a, "performRefreshComplete do nothing, scrolling: %s, auto refresh: %s", new Object[]{Boolean.valueOf(PtrFrameLayout$a.b(this.n)), Integer.valueOf(this.q)});
        }
    }

    private void b(boolean z) {
        if (!this.mPtrIndicator.hasLeftStartPosition() || z || this.v == null) {
            if (this.l.hasHandler()) {
                if (DEBUG) {
                    PtrCLog.i(this.a, "PtrUIHandler: onUIRefreshComplete");
                }
                this.l.onUIRefreshComplete(this);
            }
            this.mPtrIndicator.onUIRefreshComplete();
            g();
            k();
            return;
        }
        if (DEBUG) {
            PtrCLog.d(this.a, "notifyUIRefreshComplete mRefreshCompleteHook run.");
        }
        this.v.takeOver();
    }

    public void autoRefresh() {
        autoRefresh(true, this.h);
    }

    public void autoRefresh(boolean z) {
        autoRefresh(z, this.h);
    }

    private void m() {
        this.q &= -4;
    }

    public void autoRefresh(boolean z, int i) {
        if (this.c == (byte) 1) {
            this.q = (z ? 1 : 2) | this.q;
            this.c = (byte) 2;
            if (this.l.hasHandler()) {
                this.l.onUIRefreshPrepare(this);
                if (DEBUG) {
                    PtrCLog.i(this.a, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", new Object[]{Integer.valueOf(this.q)});
                }
            }
            this.n.tryToScrollTo(this.mPtrIndicator.getOffsetToRefresh(), i);
            if (z) {
                this.c = (byte) 3;
                j();
            }
        }
    }

    public boolean isAutoRefresh() {
        return (this.q & 3) > 0;
    }

    private boolean n() {
        return (this.q & 3) == 2;
    }

    public boolean isEnabledNextPtrAtOnce() {
        return (this.q & 4) > 0;
    }

    public void setEnabledNextPtrAtOnce(boolean z) {
        if (z) {
            this.q |= 4;
        } else {
            this.q &= -5;
        }
    }

    public boolean isPinContent() {
        return (this.q & 8) > 0;
    }

    public void setPinContent(boolean z) {
        if (z) {
            this.q |= 8;
        } else {
            this.q &= -9;
        }
    }

    public void setLoadingMinTime(int i) {
        this.w = i;
    }

    @Deprecated
    public void setInterceptEventWhileWorking(boolean z) {
    }

    public View getContentView() {
        return this.b;
    }

    public void setPtrHandler(PtrHandler ptrHandler) {
        this.m = ptrHandler;
    }

    public void addPtrUIHandler(PtrUIHandler ptrUIHandler) {
        d.addHandler(this.l, ptrUIHandler);
    }

    public void removePtrUIHandler(PtrUIHandler ptrUIHandler) {
        this.l = d.removeHandler(this.l, ptrUIHandler);
    }

    public void setPtrIndicator(PtrIndicator ptrIndicator) {
        if (!(this.mPtrIndicator == null || this.mPtrIndicator == ptrIndicator)) {
            ptrIndicator.convertFrom(this.mPtrIndicator);
        }
        this.mPtrIndicator = ptrIndicator;
    }

    public float getResistance() {
        return this.mPtrIndicator.getResistance();
    }

    public void setResistance(float f) {
        this.mPtrIndicator.setResistance(f);
    }

    public float getDurationToClose() {
        return (float) this.g;
    }

    public void setDurationToClose(int i) {
        this.g = i;
    }

    public long getDurationToCloseHeader() {
        return (long) this.h;
    }

    public void setDurationToCloseHeader(int i) {
        this.h = i;
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        this.mPtrIndicator.setRatioOfHeaderHeightToRefresh(f);
    }

    public int getOffsetToRefresh() {
        return this.mPtrIndicator.getOffsetToRefresh();
    }

    public void setOffsetToRefresh(int i) {
        this.mPtrIndicator.setOffsetToRefresh(i);
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.mPtrIndicator.getRatioOfHeaderToHeightRefresh();
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading();
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        this.mPtrIndicator.setOffsetToKeepHeaderWhileLoading(i);
    }

    public boolean isKeepHeaderWhenRefresh() {
        return this.i;
    }

    public void setKeepHeaderWhenRefresh(boolean z) {
        this.i = z;
    }

    public boolean isPullToRefresh() {
        return this.j;
    }

    public void setPullToRefresh(boolean z) {
        this.j = z;
    }

    public boolean isHeaderLayoutMarginEnable() {
        return this.t;
    }

    public void setHeaderLayoutMarginEnable(boolean z) {
        this.t = z;
    }

    public View getHeaderView() {
        return this.k;
    }

    public void setHeaderView(View view) {
        if (!(this.k == null || view == null || this.k == view)) {
            removeView(this.k);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new PtrFrameLayout$LayoutParams(-1, -2));
        }
        this.k = view;
        addView(view);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof PtrFrameLayout$LayoutParams);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new PtrFrameLayout$LayoutParams(-1, -1);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new PtrFrameLayout$LayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new PtrFrameLayout$LayoutParams(getContext(), attributeSet);
    }

    private void o() {
        if (DEBUG) {
            PtrCLog.d(this.a, "send cancel event");
        }
        if (this.u != null) {
            MotionEvent motionEvent = this.u;
            dispatchTouchEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime() + ((long) ViewConfiguration.getLongPressTimeout()), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
        }
    }

    private void p() {
        if (DEBUG) {
            PtrCLog.d(this.a, "send down event");
        }
        MotionEvent motionEvent = this.u;
        dispatchTouchEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.s.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.s.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return this.s.startNestedScroll(i);
    }

    public void stopNestedScroll() {
        this.s.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return this.s.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, @Nullable int[] iArr) {
        return this.s.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return this.s.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.s.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.s.dispatchNestedPreFling(f, f2);
    }

    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        return (!isEnabled() || isAutoRefresh() || isRefreshing() || (i & 2) == 0) ? false : true;
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.r.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(i & 2);
        this.B = 0;
        this.A = true;
    }

    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(i, i2, i3, i4, this.C);
        int i5 = this.C[1] + i4;
        if (i5 < 0 && !canChildScrollUp()) {
            this.B = Math.abs(i5) + this.B;
            p();
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        if (i2 > 0 && this.B > 0) {
            if (i2 > this.B) {
                iArr[1] = i2 - this.B;
                this.B = 0;
            } else {
                this.B -= i2;
                iArr[1] = i2;
            }
        }
        int[] iArr2 = this.D;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr2[1] + iArr[1];
        }
    }

    public int getNestedScrollAxes() {
        return this.r.getNestedScrollAxes();
    }

    public void onStopNestedScroll(View view) {
        this.r.onStopNestedScroll(view);
        this.A = false;
        if (this.B > 0) {
            this.B = 0;
        }
        stopNestedScroll();
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (VERSION.SDK_INT < 21 && (this.b instanceof AbsListView)) {
            return;
        }
        if (this.b == null || ViewCompat.isNestedScrollingEnabled(this.b)) {
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    public boolean canChildScrollUp() {
        boolean canScrollList;
        if (this.b instanceof ListView) {
            canScrollList = ListViewCompat.canScrollList((ListView) this.b, -1);
        } else {
            canScrollList = this.b.canScrollVertically(-1);
        }
        PtrCLog.d(this.a, "canChildScrollUp = " + canScrollList);
        return canScrollList;
    }

    private void a(PointF pointF) {
        float f = pointF.y - this.E.y;
        float abs = Math.abs(pointF.x - this.E.x);
        if (f > ((float) this.o) && f > abs && !this.G && !canChildScrollUp()) {
            this.G = true;
            PtrCLog.d(this.a, "ptr is beingDragged");
        }
    }
}
