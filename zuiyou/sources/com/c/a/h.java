package com.c.a;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import java.util.Arrays;

public class h {
    private static final Interpolator v = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private int a;
    private int b;
    private int c = -1;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private ScrollerCompat q;
    private final a r;
    private View s;
    private boolean t;
    private final ViewGroup u;
    private final Runnable w = new Runnable(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d(0);
        }
    };

    public static abstract class a {
        public abstract boolean a(View view, int i);

        public void a(int i) {
        }

        public void a(View view, int i, int i2, int i3, int i4) {
        }

        public void b(View view, int i) {
        }

        public void a(View view, float f, float f2) {
        }

        public void b(int i, int i2) {
        }

        public boolean b(int i) {
            return false;
        }

        public void a(int i, int i2) {
        }

        public int a(View view) {
            return 0;
        }

        public int b(View view) {
            return 0;
        }

        public int a(View view, int i, int i2) {
            return 0;
        }

        public int b(View view, int i, int i2) {
            return 0;
        }

        public boolean a() {
            return false;
        }
    }

    public static h a(ViewGroup viewGroup, a aVar) {
        return new h(viewGroup.getContext(), viewGroup, aVar);
    }

    private h(Context context, ViewGroup viewGroup, a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (aVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.u = viewGroup;
            this.r = aVar;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.b = viewConfiguration.getScaledTouchSlop();
            this.m = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.n = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.q = ScrollerCompat.create(context, v);
        }
    }

    public void a(Context context, float f) {
        this.b = (int) ((1.0f / Math.max(0.0f, Math.min(1.0f, f))) * ((float) ViewConfiguration.get(context).getScaledTouchSlop()));
    }

    public void a(float f) {
        this.n = f;
    }

    public void b(float f) {
        this.m = f;
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.p = i;
    }

    public void b(int i) {
        this.o = i;
    }

    public void a(View view, int i) {
        if (view.getParent() != this.u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
        }
        this.s = view;
        this.c = i;
        this.r.b(view, i);
    }

    public void b() {
        this.c = -1;
        c();
        if (this.l != null) {
            this.l.recycle();
            this.l = null;
        }
    }

    public boolean a(int i, int i2) {
        if (this.t) {
            return a(i, i2, (int) VelocityTrackerCompat.getXVelocity(this.l, this.c), (int) VelocityTrackerCompat.getYVelocity(this.l, this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean a(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.q.abortAnimation();
            d(0);
            return false;
        }
        this.q.startScroll(left, top, i5, i6, a(this.s, i5, i6, i3, i4));
        d(2);
        return true;
    }

    private int a(View view, int i, int i2, int i3, int i4) {
        int b = b(i3, (int) this.n, (int) this.m);
        int b2 = b(i4, (int) this.n, (int) this.m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        return (int) (((b2 != 0 ? ((float) abs4) / ((float) i5) : ((float) abs2) / ((float) i6)) * ((float) a(i2, b2, this.r.b(view)))) + ((b != 0 ? ((float) abs3) / ((float) i5) : ((float) abs) / ((float) i6)) * ((float) a(i, b, this.r.a(view)))));
    }

    private int a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        int i4 = width / 2;
        float c = (c(Math.min(1.0f, ((float) Math.abs(i)) / ((float) width))) * ((float) i4)) + ((float) i4);
        i4 = Math.abs(i2);
        if (i4 > 0) {
            width = Math.round(Math.abs(c / ((float) i4)) * 1000.0f) * 4;
        } else {
            width = (int) (((((float) Math.abs(i)) / ((float) i3)) + 1.0f) * 256.0f);
        }
        return Math.min(width, 600);
    }

    private int b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            return -i3;
        }
        return i3;
    }

    private float a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            return -f3;
        }
        return f3;
    }

    private float c(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public boolean a(boolean z) {
        if (this.a == 2) {
            boolean isFinished;
            boolean computeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.s.getLeft();
            int top = currY - this.s.getTop();
            if (left != 0) {
                this.s.offsetLeftAndRight(left);
            }
            if (top != 0) {
                this.s.offsetTopAndBottom(top);
            }
            if (!(left == 0 && top == 0)) {
                this.r.a(this.s, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                this.q.abortAnimation();
                isFinished = this.q.isFinished();
            } else {
                isFinished = computeScrollOffset;
            }
            if (!isFinished) {
                if (z) {
                    g.a(this.w);
                } else {
                    d(0);
                }
            }
        }
        return this.a == 2;
    }

    private void a(float f, float f2) {
        this.t = true;
        this.r.a(this.s, f, f2);
        this.t = false;
        if (this.a == 1) {
            d(0);
        }
    }

    private void c() {
        if (this.d != null) {
            Arrays.fill(this.d, 0.0f);
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            this.k = 0;
        }
    }

    private void e(int i) {
        if (this.d != null) {
            this.d[i] = 0.0f;
            this.e[i] = 0.0f;
            this.f[i] = 0.0f;
            this.g[i] = 0.0f;
            this.h[i] = 0;
            this.i[i] = 0;
            this.j[i] = 0;
            this.k &= (1 << i) ^ -1;
        }
    }

    private void f(int i) {
        if (this.d == null || this.d.length <= i) {
            Object obj = new float[(i + 1)];
            Object obj2 = new float[(i + 1)];
            Object obj3 = new float[(i + 1)];
            Object obj4 = new float[(i + 1)];
            Object obj5 = new int[(i + 1)];
            Object obj6 = new int[(i + 1)];
            Object obj7 = new int[(i + 1)];
            if (this.d != null) {
                System.arraycopy(this.d, 0, obj, 0, this.d.length);
                System.arraycopy(this.e, 0, obj2, 0, this.e.length);
                System.arraycopy(this.f, 0, obj3, 0, this.f.length);
                System.arraycopy(this.g, 0, obj4, 0, this.g.length);
                System.arraycopy(this.h, 0, obj5, 0, this.h.length);
                System.arraycopy(this.i, 0, obj6, 0, this.i.length);
                System.arraycopy(this.j, 0, obj7, 0, this.j.length);
            }
            this.d = obj;
            this.e = obj2;
            this.f = obj3;
            this.g = obj4;
            this.h = obj5;
            this.i = obj6;
            this.j = obj7;
        }
    }

    private void a(float f, float f2, int i) {
        f(i);
        float[] fArr = this.d;
        this.f[i] = f;
        fArr[i] = f;
        fArr = this.e;
        this.g[i] = f2;
        fArr[i] = f2;
        this.h[i] = d((int) f, (int) f2);
        this.k |= 1 << i;
    }

    private void c(MotionEvent motionEvent) {
        int pointerCount = MotionEventCompat.getPointerCount(motionEvent);
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = MotionEventCompat.getPointerId(motionEvent, i);
            float x = MotionEventCompat.getX(motionEvent, i);
            float y = MotionEventCompat.getY(motionEvent, i);
            this.f[pointerId] = x;
            this.g[pointerId] = y;
        }
    }

    public boolean c(int i) {
        return (this.k & (1 << i)) != 0;
    }

    void d(int i) {
        if (this.a != i) {
            this.a = i;
            this.r.a(i);
            if (i == 0) {
                this.s = null;
            }
        }
    }

    boolean b(View view, int i) {
        if (view == this.s && this.c == i) {
            return true;
        }
        if (view == null || !this.r.a(view, i)) {
            return false;
        }
        this.c = i;
        a(view, i);
        return true;
    }

    public boolean a(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            b();
        }
        float x;
        float y;
        switch (actionMasked) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                int pointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                a(x, y, pointerId);
                b(c((int) x, (int) y), pointerId);
                if (this.a != 2) {
                    if (this.a == 0) {
                        actionMasked = this.h[pointerId];
                        if ((this.p & actionMasked) != 0) {
                            this.r.b(actionMasked & this.p, pointerId);
                        }
                        d(3);
                        break;
                    }
                }
                d(1);
                break;
                break;
            case 1:
                d();
                b();
                break;
            case 2:
                if (this.a == 3) {
                    if (this.l == null) {
                        this.l = VelocityTracker.obtain();
                    }
                    if (this.a == 1) {
                        this.l.addMovement(motionEvent);
                    }
                    actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.c);
                    y = MotionEventCompat.getX(motionEvent, actionMasked);
                    x = MotionEventCompat.getY(motionEvent, actionMasked);
                    float f = y - this.d[this.c];
                    float f2 = x - this.e[this.c];
                    b(f, f2, this.c);
                    View c = c((int) y, (int) x);
                    actionIndex = a(c, f, f2);
                    if (actionIndex != -1) {
                        if (actionIndex > 0 && b(c, this.c)) {
                            break;
                        }
                    }
                    b();
                    c(motionEvent);
                    break;
                }
                break;
            case 3:
                a(0.0f, 0.0f);
                b();
                break;
            case 5:
                a(MotionEventCompat.getX(motionEvent, actionIndex), MotionEventCompat.getY(motionEvent, actionIndex), MotionEventCompat.getPointerId(motionEvent, actionIndex));
                break;
            case 6:
                e(MotionEventCompat.getPointerId(motionEvent, actionIndex));
                break;
        }
        if (this.a == 1) {
            return true;
        }
        return false;
    }

    public void b(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            b();
        }
        float x;
        float y;
        switch (actionMasked) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                int pointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                a(x, y, pointerId);
                b(c((int) x, (int) y), pointerId);
                if (this.a == 2) {
                    d(1);
                    return;
                } else if (this.a == 0) {
                    actionMasked = this.h[pointerId];
                    if ((this.p & actionMasked) != 0) {
                        this.r.b(actionMasked & this.p, pointerId);
                    }
                    d(3);
                    return;
                } else {
                    return;
                }
            case 1:
                d();
                b();
                return;
            case 2:
                if (this.a == 3) {
                    actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.c);
                    y = MotionEventCompat.getX(motionEvent, actionMasked);
                    x = MotionEventCompat.getY(motionEvent, actionMasked);
                    float f = y - this.d[this.c];
                    float f2 = x - this.e[this.c];
                    b(f, f2, this.c);
                    if (this.a != 1) {
                        View c = c((int) y, (int) x);
                        actionIndex = a(c, f, f2);
                        if (actionIndex == -1) {
                            b();
                        } else if (actionIndex > 0 && b(c, this.c)) {
                            return;
                        }
                        c(motionEvent);
                    } else {
                        return;
                    }
                }
                if (this.a == 1) {
                    if (this.l == null) {
                        this.l = VelocityTracker.obtain();
                    }
                    if (this.a == 1) {
                        this.l.addMovement(motionEvent);
                    }
                    actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.c);
                    if (actionMasked != -1) {
                        y = MotionEventCompat.getX(motionEvent, actionMasked);
                        actionIndex = (int) (y - this.f[this.c]);
                        actionMasked = (int) (MotionEventCompat.getY(motionEvent, actionMasked) - this.g[this.c]);
                        b(this.s.getLeft() + actionIndex, this.s.getTop() + actionMasked, actionIndex, actionMasked);
                        c(motionEvent);
                        return;
                    }
                    return;
                }
                return;
            case 3:
                a(0.0f, 0.0f);
                b();
                return;
            case 5:
                a(MotionEventCompat.getX(motionEvent, actionIndex), MotionEventCompat.getY(motionEvent, actionIndex), MotionEventCompat.getPointerId(motionEvent, actionIndex));
                return;
            case 6:
                e(MotionEventCompat.getPointerId(motionEvent, actionIndex));
                return;
            default:
                return;
        }
    }

    private void b(float f, float f2, int i) {
        int i2 = 1;
        if (!a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | i2;
            this.r.a(i2, i);
        }
    }

    private boolean a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        if (abs <= ((float) this.b) && abs2 <= ((float) this.b)) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.r.b(i2)) {
            int[] iArr = this.j;
            iArr[i] = iArr[i] | i2;
            return false;
        } else if ((this.i[i] & i2) != 0 || abs <= ((float) this.b)) {
            return false;
        } else {
            return true;
        }
    }

    private int a(View view, float f, float f2) {
        if (view == null) {
            return 0;
        }
        if (f <= ((float) this.b) && Math.abs(f2) <= ((float) this.b)) {
            return 0;
        }
        if (f > ((float) this.b) && Math.abs(f2) <= ((float) this.b)) {
            this.a = 1;
            return 1;
        } else if (f > ((float) this.b) || Math.abs(f2) <= ((float) this.b)) {
            return 2;
        } else {
            this.a = 0;
            b();
            return -1;
        }
    }

    public boolean b(int i, int i2) {
        return c(i2) && (this.h[i2] & i) != 0;
    }

    private void d() {
        this.l.computeCurrentVelocity(1000, this.m);
        float a = a(VelocityTrackerCompat.getXVelocity(this.l, this.c), this.n, this.m);
        float a2 = a(VelocityTrackerCompat.getYVelocity(this.l, this.c), this.n, this.m);
        if (a() == 1) {
            a(a, a2);
        }
    }

    private void b(int i, int i2, int i3, int i4) {
        int a;
        int b;
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i3 != 0) {
            a = this.r.a(this.s, i, i3);
            if (this.r.a()) {
                this.s.offsetLeftAndRight(a - left);
            }
        } else {
            a = i;
        }
        if (i4 != 0) {
            b = this.r.b(this.s, i2, i4);
            this.s.offsetTopAndBottom(b - top);
        } else {
            b = i2;
        }
        if (i3 != 0 || i4 != 0) {
            this.r.a(this.s, a, b, a - left, b - top);
        }
    }

    public View c(int i, int i2) {
        return this.u.getChildAt(0);
    }

    private int d(int i, int i2) {
        int i3 = 0;
        if (i < this.u.getLeft() + this.o) {
            i3 = 1;
        }
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        if (i2 > this.u.getBottom() - this.o) {
            return i3 | 8;
        }
        return i3;
    }
}
