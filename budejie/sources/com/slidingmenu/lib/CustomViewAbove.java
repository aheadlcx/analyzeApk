package com.slidingmenu.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import cn.v6.sixrooms.widgets.phone.ExpressionKeyboard;
import com.slidingmenu.lib.SlidingMenu.f;
import java.util.ArrayList;
import java.util.List;

public class CustomViewAbove extends ViewGroup {
    private static final Interpolator f = new a();
    private List<View> A;
    private ExpressionKeyboard B;
    private boolean C;
    private float D;
    public Boolean a;
    protected int b;
    protected VelocityTracker c;
    protected int d;
    protected int e;
    private View g;
    private int h;
    private Scroller i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private float o;
    private float p;
    private float q;
    private int r;
    private int s;
    private CustomViewBehind t;
    private boolean u;
    private a v;
    private a w;
    private SlidingMenu$c x;
    private f y;
    private SlidingMenu$d z;

    public interface a {
        void a(int i);

        void a(int i, float f, int i2);
    }

    public static class b implements a {
        public void a(int i, float f, int i2) {
        }

        public void a(int i) {
        }
    }

    public CustomViewAbove(Context context) {
        this(context, null);
    }

    public CustomViewAbove(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = Boolean.valueOf(false);
        this.b = -1;
        this.u = true;
        this.A = new ArrayList();
        this.e = 0;
        this.C = false;
        this.D = 0.0f;
        a();
    }

    void a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.i = new Scroller(context, f);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.n = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.r = viewConfiguration.getScaledMinimumFlingVelocity();
        this.d = viewConfiguration.getScaledMaximumFlingVelocity();
        a(new b(this));
        this.s = (int) (context.getResources().getDisplayMetrics().density * 25.0f);
    }

    public void setCurrentItem(int i) {
        a(i, true, false);
    }

    public void a(int i, boolean z) {
        a(i, z, false);
    }

    public int getCurrentItem() {
        return this.h;
    }

    void a(int i, boolean z, boolean z2) {
        a(i, z, z2, 0);
    }

    void a(int i, boolean z, boolean z2, int i2) {
        if (z2 || this.h != i) {
            int a = this.t.a(i);
            boolean z3 = this.h != a;
            this.h = a;
            int a2 = a(this.h);
            if (z3 && this.v != null) {
                this.v.a(a);
            }
            if (z3 && this.w != null) {
                this.w.a(a);
            }
            if (z) {
                a(a2, 0, i2);
                return;
            }
            e();
            scrollTo(a2, 0);
            return;
        }
        setScrollingCacheEnabled(false);
    }

    public void setOnPageChangeListener(a aVar) {
        this.v = aVar;
    }

    public void setOnOpenedListener(f fVar) {
        this.y = fVar;
    }

    public void setOnClosedListener(SlidingMenu$c slidingMenu$c) {
        this.x = slidingMenu$c;
    }

    public void setOnMovedListener(SlidingMenu$d slidingMenu$d) {
        this.z = slidingMenu$d;
    }

    a a(a aVar) {
        a aVar2 = this.w;
        this.w = aVar;
        return aVar2;
    }

    float a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public int a(int i) {
        switch (i) {
            case 0:
            case 2:
                return this.t.a(this.g, i);
            case 1:
                return this.g.getLeft();
            default:
                return 0;
        }
    }

    private int getLeftBound() {
        return this.t.a(this.g);
    }

    private int getRightBound() {
        return this.t.b(this.g);
    }

    public int getContentLeft() {
        return this.g.getLeft() + this.g.getPaddingLeft();
    }

    public boolean b() {
        return this.h == 0 || this.h == 2;
    }

    private boolean a(MotionEvent motionEvent) {
        Rect rect = new Rect();
        for (View hitRect : this.A) {
            hitRect.getHitRect(rect);
            if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return true;
            }
        }
        return false;
    }

    public int getBehindWidth() {
        if (this.t == null) {
            return 0;
        }
        return this.t.getBehindWidth();
    }

    public void setSlidingEnabled(boolean z) {
        this.u = z;
    }

    void a(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            e();
            if (b()) {
                if (this.y != null) {
                    this.y.onOpened();
                    return;
                }
                return;
            } else if (this.x != null) {
                this.x.a();
                return;
            } else {
                return;
            }
        }
        setScrollingCacheEnabled(true);
        this.k = true;
        int behindWidth = getBehindWidth();
        int i6 = behindWidth / 2;
        float a = (a(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) behindWidth))) * ((float) i6)) + ((float) i6);
        i6 = Math.abs(i3);
        if (i6 > 0) {
            behindWidth = Math.round(Math.abs(a / ((float) i6)) * 1000.0f) * 4;
        } else {
            Math.abs(i4);
            behindWidth = 600;
        }
        this.i.startScroll(scrollX, scrollY, i4, i5, Math.min(behindWidth, 600));
        invalidate();
    }

    public void setContent(View view) {
        if (this.g != null) {
            removeView(this.g);
        }
        this.g = view;
        addView(this.g);
    }

    public View getContent() {
        return this.g;
    }

    public void setCustomViewBehind(CustomViewBehind customViewBehind) {
        this.t = customViewBehind;
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(0, i);
        int defaultSize2 = getDefaultSize(0, i2);
        setMeasuredDimension(defaultSize, defaultSize2);
        this.g.measure(getChildMeasureSpec(i, 0, defaultSize), getChildMeasureSpec(i2, 0, defaultSize2));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            e();
            scrollTo(a(this.h), getScrollY());
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.g.layout(0, 0, i3 - i, i4 - i2);
    }

    public void setAboveOffset(int i) {
        this.g.setPadding(i, this.g.getPaddingTop(), this.g.getPaddingRight(), this.g.getPaddingBottom());
    }

    public void computeScroll() {
        if (this.i.isFinished() || !this.i.computeScrollOffset()) {
            e();
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.i.getCurrX();
        int currY = this.i.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            c(currX);
        }
        invalidate();
    }

    private void c(int i) {
        int width = getWidth();
        int i2 = i % width;
        a(i / width, ((float) i2) / ((float) width), i2);
    }

    protected void a(int i, float f, int i2) {
        if (this.v != null) {
            this.v.a(i, f, i2);
        }
        if (this.w != null) {
            this.w.a(i, f, i2);
        }
    }

    private void e() {
        if (this.k) {
            setScrollingCacheEnabled(false);
            this.i.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.i.getCurrX();
            int currY = this.i.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
            if (b()) {
                if (this.y != null) {
                    this.y.onOpened();
                }
            } else if (this.x != null) {
                this.x.a();
            }
        }
        this.k = false;
    }

    public void setTouchMode(int i) {
        this.e = i;
    }

    public int getTouchMode() {
        return this.e;
    }

    public void setExpressionPage(ExpressionKeyboard expressionKeyboard) {
        this.B = expressionKeyboard;
    }

    private boolean b(MotionEvent motionEvent) {
        int x = (int) (motionEvent.getX() + this.D);
        if (b()) {
            return this.t.a(this.g, this.h, (float) x);
        }
        switch (this.e) {
            case 0:
                return this.t.b(this.g, x);
            case 1:
                if (a(motionEvent)) {
                    return false;
                }
                return true;
            case 2:
                return false;
            default:
                return false;
        }
    }

    private boolean b(float f) {
        if (b()) {
            return this.t.b(f);
        }
        return this.t.a(f);
    }

    private int a(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (findPointerIndex == -1) {
            this.b = -1;
        }
        return findPointerIndex;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.u) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1 || (action != 0 && this.m)) {
            g();
            return false;
        }
        boolean z;
        switch (action) {
            case 0:
                action = MotionEventCompat.getActionIndex(motionEvent);
                this.b = MotionEventCompat.getPointerId(motionEvent, action);
                if (this.b != -1) {
                    float x = MotionEventCompat.getX(motionEvent, action);
                    this.o = x;
                    this.p = x;
                    this.q = MotionEventCompat.getY(motionEvent, action);
                    if (!b(motionEvent)) {
                        this.m = true;
                        break;
                    }
                    this.l = false;
                    this.m = false;
                    if (b() && this.t.b(this.g, this.h, motionEvent.getX() + this.D)) {
                        this.C = true;
                        break;
                    }
                }
                break;
            case 2:
                c(motionEvent);
                break;
            case 6:
                d(motionEvent);
                break;
        }
        if (!this.l) {
            if (this.c == null) {
                this.c = VelocityTracker.obtain();
            }
            this.c.addMovement(motionEvent);
        }
        if (this.B == null || !this.B.interrupt) {
            z = true;
        } else {
            z = false;
        }
        if ((z && this.l) || this.C) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.u) {
            return false;
        }
        if (!this.l && !b(motionEvent)) {
            return false;
        }
        int action = motionEvent.getAction();
        if (this.c == null) {
            this.c = VelocityTracker.obtain();
        }
        this.c.addMovement(motionEvent);
        float x;
        int xVelocity;
        float scrollX;
        switch (action & 255) {
            case 0:
                e();
                this.b = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                x = motionEvent.getX();
                this.o = x;
                this.p = x;
                break;
            case 1:
                if (!this.l) {
                    if (this.C && this.t.b(this.g, this.h, motionEvent.getX() + this.D)) {
                        setCurrentItem(1);
                        g();
                        break;
                    }
                }
                VelocityTracker velocityTracker = this.c;
                velocityTracker.computeCurrentVelocity(1000, (float) this.d);
                xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.b);
                scrollX = ((float) (getScrollX() - a(this.h))) / ((float) getBehindWidth());
                int a = a(motionEvent, this.b);
                if (this.b != -1) {
                    a(a(scrollX, xVelocity, (int) (MotionEventCompat.getX(motionEvent, a) - this.o)), true, true, xVelocity);
                } else {
                    a(this.h, true, true, xVelocity);
                }
                this.b = -1;
                g();
                break;
            case 2:
                if (this.z != null) {
                    this.z.a();
                }
                if (!this.l) {
                    c(motionEvent);
                    if (this.m) {
                        return false;
                    }
                }
                if (this.l) {
                    xVelocity = a(motionEvent, this.b);
                    if (this.b != -1) {
                        x = MotionEventCompat.getX(motionEvent, xVelocity);
                        scrollX = this.p - x;
                        this.p = x;
                        float scrollX2 = ((float) getScrollX()) + scrollX;
                        x = (float) getLeftBound();
                        scrollX = (float) getRightBound();
                        if (scrollX2 >= x) {
                            if (scrollX2 > scrollX) {
                                x = scrollX;
                            } else {
                                x = scrollX2;
                            }
                        }
                        this.p += x - ((float) ((int) x));
                        scrollTo((int) x, getScrollY());
                        c((int) x);
                        break;
                    }
                }
                break;
            case 3:
                if (this.l) {
                    a(this.h, true, true);
                    this.b = -1;
                    g();
                    break;
                }
                break;
            case 5:
                xVelocity = MotionEventCompat.getActionIndex(motionEvent);
                this.p = MotionEventCompat.getX(motionEvent, xVelocity);
                this.b = MotionEventCompat.getPointerId(motionEvent, xVelocity);
                break;
            case 6:
                d(motionEvent);
                xVelocity = a(motionEvent, this.b);
                if (this.b != -1) {
                    this.p = MotionEventCompat.getX(motionEvent, xVelocity);
                    break;
                }
                break;
        }
        return true;
    }

    private void c(MotionEvent motionEvent) {
        int i = this.b;
        int a = a(motionEvent, i);
        if (i != -1) {
            float x = MotionEventCompat.getX(motionEvent, a);
            float f = x - this.p;
            float abs = Math.abs(f);
            float y = MotionEventCompat.getY(motionEvent, a);
            float abs2 = Math.abs(y - this.q);
            if (abs > ((float) (b() ? this.n / 2 : this.n)) && abs > abs2 && b(f)) {
                f();
                this.p = x;
                this.q = y;
                setScrollingCacheEnabled(true);
            } else if (abs > ((float) this.n)) {
                this.m = true;
            }
        }
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
        this.D = (float) i;
        if (this.u) {
            this.t.a(this.g, i, i2);
        }
        ((SlidingMenu) getParent()).a(getPercentOpen());
    }

    private int a(float f, int i, int i2) {
        int i3 = this.h;
        if (Math.abs(i2) <= this.s || Math.abs(i) <= this.r) {
            return Math.round(((float) this.h) + f);
        }
        if (i > 0 && i2 > 0) {
            return i3 - 1;
        }
        if (i >= 0 || i2 >= 0) {
            return i3;
        }
        return i3 + 1;
    }

    protected float getPercentOpen() {
        return Math.abs(this.D - ((float) this.g.getLeft())) / ((float) getBehindWidth());
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.t.a(this.g, canvas);
        this.t.a(this.g, canvas, getPercentOpen());
        this.t.b(this.g, canvas, getPercentOpen());
    }

    private void d(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.b) {
            actionIndex = actionIndex == 0 ? 1 : 0;
            this.p = MotionEventCompat.getX(motionEvent, actionIndex);
            this.b = MotionEventCompat.getPointerId(motionEvent, actionIndex);
            if (this.c != null) {
                this.c.clear();
            }
        }
    }

    private void f() {
        this.l = true;
        this.C = false;
    }

    private void g() {
        this.C = false;
        this.l = false;
        this.m = false;
        this.b = -1;
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.j != z) {
            this.j = z;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return b(17);
            case 22:
                return b(66);
            case 61:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (KeyEventCompat.hasNoModifiers(keyEvent)) {
                    return b(2);
                }
                if (KeyEventCompat.hasModifiers(keyEvent, 1)) {
                    return b(1);
                }
                return false;
            default:
                return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(int r6) {
        /*
        r5 = this;
        r4 = 66;
        r3 = 17;
        r0 = r5.findFocus();
        if (r0 != r5) goto L_0x000b;
    L_0x000a:
        r0 = 0;
    L_0x000b:
        r1 = 0;
        r2 = android.view.FocusFinder.getInstance();
        r2 = r2.findNextFocus(r5, r0, r6);
        if (r2 == 0) goto L_0x003b;
    L_0x0016:
        if (r2 == r0) goto L_0x003b;
    L_0x0018:
        if (r6 != r3) goto L_0x0028;
    L_0x001a:
        r0 = r2.requestFocus();
    L_0x001e:
        if (r0 == 0) goto L_0x0027;
    L_0x0020:
        r1 = android.view.SoundEffectConstants.getContantForFocusDirection(r6);
        r5.playSoundEffect(r1);
    L_0x0027:
        return r0;
    L_0x0028:
        if (r6 != r4) goto L_0x004f;
    L_0x002a:
        if (r0 == 0) goto L_0x0036;
    L_0x002c:
        r1 = r2.getLeft();
        r0 = r0.getLeft();
        if (r1 <= r0) goto L_0x004a;
    L_0x0036:
        r0 = r2.requestFocus();
        goto L_0x001e;
    L_0x003b:
        if (r6 == r3) goto L_0x0040;
    L_0x003d:
        r0 = 1;
        if (r6 != r0) goto L_0x0045;
    L_0x0040:
        r0 = r5.c();
        goto L_0x001e;
    L_0x0045:
        if (r6 == r4) goto L_0x004a;
    L_0x0047:
        r0 = 2;
        if (r6 != r0) goto L_0x004f;
    L_0x004a:
        r0 = r5.d();
        goto L_0x001e;
    L_0x004f:
        r0 = r1;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.slidingmenu.lib.CustomViewAbove.b(int):boolean");
    }

    boolean c() {
        if (this.h <= 0) {
            return false;
        }
        a(this.h - 1, true);
        return true;
    }

    boolean d() {
        if (this.h > 0) {
            return false;
        }
        a(this.h + 1, true);
        return true;
    }
}
