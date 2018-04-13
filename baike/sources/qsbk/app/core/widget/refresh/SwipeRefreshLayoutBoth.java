package qsbk.app.core.widget.refresh;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;

public class SwipeRefreshLayoutBoth extends ViewGroup {
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;
    private static final String c = SwipeRefreshLayoutBoth.class.getSimpleName();
    private static final int[] s = new int[]{16842766};
    private Animation A;
    private Animation B;
    private float C;
    private boolean D;
    private int E;
    private int F;
    private boolean G;
    private AnimationListener H;
    private final Animation I;
    private final Animation J;
    protected int a;
    protected int b;
    private View d;
    private SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection e;
    private SwipeRefreshLayoutBoth$OnRefreshListener f;
    private boolean g;
    private int h;
    private float i;
    private int j;
    private int k;
    private boolean l;
    private float m;
    private boolean n;
    private int o;
    private boolean p;
    private boolean q;
    private final DecelerateInterpolator r;
    private a t;
    private int u;
    private float v;
    private MaterialProgressDrawable w;
    private Animation x;
    private Animation y;
    private Animation z;

    private void setColorViewAlpha(int i) {
        this.t.getBackground().setAlpha(i);
        this.w.setAlpha(i);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i2;
            if (i == 0) {
                i2 = (int) (displayMetrics.density * 56.0f);
                this.E = i2;
                this.F = i2;
            } else {
                i2 = (int) (displayMetrics.density * 40.0f);
                this.E = i2;
                this.F = i2;
            }
            this.t.setImageDrawable(null);
            this.w.updateSizes(i);
            this.t.setImageDrawable(this.w);
        }
    }

    public SwipeRefreshLayoutBoth(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayoutBoth(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
        this.i = -1.0f;
        this.l = false;
        this.o = -1;
        this.u = -1;
        this.H = new e(this);
        this.I = new j(this);
        this.J = new k(this);
        this.h = ViewConfiguration.get(context).getScaledTouchSlop();
        this.j = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.r = new DecelerateInterpolator(2.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, s);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        this.e = SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.E = (int) (displayMetrics.density * 40.0f);
        this.F = (int) (displayMetrics.density * 40.0f);
        a();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.C = displayMetrics.density * 64.0f;
        this.i = this.C;
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.u < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return this.u;
        }
        if (i2 >= this.u) {
            return i2 + 1;
        }
        return i2;
    }

    private void a() {
        this.t = new a(getContext(), -328966, 20.0f);
        this.w = new MaterialProgressDrawable(getContext(), this);
        this.w.setBackgroundColor(-328966);
        this.t.setImageDrawable(this.w);
        this.t.setVisibility(8);
        addView(this.t);
    }

    public void setOnRefreshListener(SwipeRefreshLayoutBoth$OnRefreshListener swipeRefreshLayoutBoth$OnRefreshListener) {
        this.f = swipeRefreshLayoutBoth$OnRefreshListener;
    }

    private boolean b() {
        return VERSION.SDK_INT < 11;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.g == z) {
            a(z, false);
            return;
        }
        int i;
        this.g = z;
        if (this.G) {
            i = (int) this.C;
        } else {
            i = (int) (this.C + ((float) this.b));
        }
        a(i - this.k, true);
        this.D = false;
        a(this.H);
    }

    private void a(AnimationListener animationListener) {
        this.t.setVisibility(0);
        if (VERSION.SDK_INT >= 11) {
            this.w.setAlpha(255);
        }
        this.x = new f(this);
        this.x.setDuration((long) this.j);
        if (animationListener != null) {
            this.t.setAnimationListener(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.x);
    }

    private void setAnimationProgress(float f) {
        if (b()) {
            setColorViewAlpha((int) (255.0f * f));
            return;
        }
        ViewCompat.setScaleX(this.t, f);
        ViewCompat.setScaleY(this.t, f);
    }

    private void a(boolean z, boolean z2) {
        if (this.g != z) {
            this.D = z2;
            e();
            this.g = z;
            if (this.g) {
                a(this.k, this.H);
            } else {
                b(this.H);
            }
        }
    }

    private void b(AnimationListener animationListener) {
        this.y = new g(this);
        this.y.setDuration(150);
        this.t.setAnimationListener(animationListener);
        this.t.clearAnimation();
        this.t.startAnimation(this.y);
    }

    private void c() {
        this.z = a(this.w.getAlpha(), 76);
    }

    private void d() {
        this.A = a(this.w.getAlpha(), 255);
    }

    private Animation a(int i, int i2) {
        if (this.p && b()) {
            return null;
        }
        Animation hVar = new h(this, i, i2);
        hVar.setDuration(300);
        this.t.setAnimationListener(null);
        this.t.clearAnimation();
        this.t.startAnimation(hVar);
        return hVar;
    }

    public void setProgressBackgroundColor(int i) {
        this.t.setBackgroundColor(i);
        this.w.setBackgroundColor(getResources().getColor(i));
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = resources.getColor(iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setColorSchemeColors(int... iArr) {
        e();
        this.w.setColorSchemeColors(iArr);
    }

    public boolean isRefreshing() {
        return this.g;
    }

    private void e() {
        if (this.d == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.t)) {
                    i++;
                } else {
                    this.d = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.i = (float) i;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.d == null) {
                e();
            }
            if (this.d != null) {
                View view = this.d;
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                measuredHeight = this.t.getMeasuredWidth();
                this.t.layout((measuredWidth / 2) - (measuredHeight / 2), this.k, (measuredWidth / 2) + (measuredHeight / 2), this.k + this.t.getMeasuredHeight());
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.d == null) {
            e();
        }
        if (this.d != null) {
            int measuredHeight;
            this.d.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.t.measure(MeasureSpec.makeMeasureSpec(this.E, 1073741824), MeasureSpec.makeMeasureSpec(this.F, 1073741824));
            if (!(this.G || this.l)) {
                this.l = true;
                switch (m.a[this.e.ordinal()]) {
                    case 1:
                        measuredHeight = getMeasuredHeight() - this.t.getMeasuredHeight();
                        this.b = measuredHeight;
                        this.k = measuredHeight;
                        break;
                    default:
                        measuredHeight = -this.t.getMeasuredHeight();
                        this.b = measuredHeight;
                        this.k = measuredHeight;
                        break;
                }
            }
            this.u = -1;
            for (measuredHeight = 0; measuredHeight < getChildCount(); measuredHeight++) {
                if (getChildAt(measuredHeight) == this.t) {
                    this.u = measuredHeight;
                    return;
                }
            }
        }
    }

    public boolean canChildScrollUp() {
        boolean z = true;
        if (VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(this.d, -1);
        }
        if (this.d instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.d;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        } else {
            if (this.d.getScrollY() <= 0) {
                z = false;
            }
            return z;
        }
    }

    public boolean canChildScrollDown() {
        if (VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(this.d, 1);
        }
        if (!(this.d instanceof AbsListView)) {
            return true;
        }
        AbsListView absListView = (AbsListView) this.d;
        try {
            if (absListView.getCount() <= 0 || absListView.getLastVisiblePosition() + 1 != absListView.getCount()) {
                return true;
            }
            boolean z;
            if (absListView.getChildAt(absListView.getLastVisiblePosition() - absListView.getFirstVisiblePosition()).getBottom() == absListView.getPaddingBottom()) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        e();
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.q && actionMasked == 0) {
            this.q = false;
        }
        if (!isEnabled() || this.q || this.g) {
            return false;
        }
        float a;
        switch (actionMasked) {
            case 0:
                a(this.b - this.t.getTop(), true);
                this.o = MotionEventCompat.getPointerId(motionEvent, 0);
                this.n = false;
                a = a(motionEvent, this.o);
                if (a != -1.0f) {
                    this.m = a;
                    break;
                }
                return false;
            case 1:
            case 3:
                this.n = false;
                this.o = -1;
                break;
            case 2:
                break;
            case 6:
                a(motionEvent);
                break;
        }
        if (this.o == -1) {
            return false;
        }
        a = a(motionEvent, this.o);
        if (a == -1.0f) {
            return false;
        }
        if (a > this.m) {
            setRawDirection(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP);
        } else if (a < this.m) {
            setRawDirection(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM);
        }
        if (this.e == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM && canChildScrollDown()) {
            return false;
        }
        if (this.e == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP && canChildScrollUp()) {
            return false;
        }
        float f;
        switch (m.a[this.e.ordinal()]) {
            case 1:
                f = this.m - a;
                break;
            default:
                f = a - this.m;
                break;
        }
        if (f > ((float) this.h) && !this.n) {
            this.n = true;
            this.w.setAlpha(76);
        }
        return this.n;
    }

    private float a(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (findPointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, findPointerIndex);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    private boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.q && actionMasked == 0) {
            this.q = false;
        }
        switch (m.a[this.e.ordinal()]) {
            case 1:
                if (!isEnabled() || this.q || this.g) {
                    return false;
                }
            default:
                if (!isEnabled() || this.q || canChildScrollUp() || this.g) {
                    return false;
                }
        }
        float y;
        switch (actionMasked) {
            case 0:
                this.o = MotionEventCompat.getPointerId(motionEvent, 0);
                this.n = false;
                break;
            case 1:
            case 3:
                if (this.o == -1) {
                    if (actionMasked == 1) {
                    }
                    return false;
                }
                actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.o);
                if (actionMasked < 0) {
                    return false;
                }
                y = MotionEventCompat.getY(motionEvent, actionMasked);
                switch (m.a[this.e.ordinal()]) {
                    case 1:
                        y = (this.m - y) * 0.5f;
                        break;
                    default:
                        y = (y - this.m) * 0.5f;
                        break;
                }
                this.n = false;
                if (y > this.i) {
                    a(true, true);
                } else {
                    this.g = false;
                    this.w.setStartEndTrim(0.0f, 0.0f);
                    AnimationListener animationListener = null;
                    if (!this.p) {
                        animationListener = new i(this);
                    }
                    b(this.k, animationListener);
                    this.w.showArrow(false);
                }
                this.o = -1;
                return false;
            case 2:
                actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.o);
                if (actionMasked >= 0) {
                    y = MotionEventCompat.getY(motionEvent, actionMasked);
                    switch (m.a[this.e.ordinal()]) {
                        case 1:
                            y = (this.m - y) * 0.5f;
                            break;
                        default:
                            y = (y - this.m) * 0.5f;
                            break;
                    }
                    if (this.n) {
                        this.w.showArrow(true);
                        float f = y / this.i;
                        if (f >= 0.0f) {
                            int i;
                            float min = Math.min(1.0f, Math.abs(f));
                            float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
                            float abs = Math.abs(y) - this.i;
                            f = this.G ? this.C - ((float) this.b) : this.C;
                            abs = Math.max(0.0f, Math.min(abs, 2.0f * f) / f);
                            abs = ((float) (((double) (abs / 4.0f)) - Math.pow((double) (abs / 4.0f), 2.0d))) * 2.0f;
                            float f2 = (f * abs) * 2.0f;
                            if (this.e == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
                                i = ((int) ((f * min) + f2)) + this.b;
                            } else {
                                i = this.b - ((int) ((f * min) + f2));
                            }
                            if (this.t.getVisibility() != 0) {
                                this.t.setVisibility(0);
                            }
                            if (!this.p) {
                                ViewCompat.setScaleX(this.t, 1.0f);
                                ViewCompat.setScaleY(this.t, 1.0f);
                            }
                            if (y < this.i) {
                                if (this.p) {
                                    setAnimationProgress(y / this.i);
                                }
                                if (this.w.getAlpha() > 76 && !a(this.z)) {
                                    c();
                                }
                                this.w.setStartEndTrim(0.0f, Math.min(0.8f, 0.8f * max));
                                this.w.setArrowScale(Math.min(1.0f, max));
                            } else if (this.w.getAlpha() < 255 && !a(this.A)) {
                                d();
                            }
                            this.w.setProgressRotation(((-0.25f + (0.4f * max)) + (2.0f * abs)) * 0.5f);
                            a(i - this.k, true);
                            break;
                        }
                        return false;
                    }
                }
                return false;
                break;
            case 5:
                this.o = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return true;
    }

    private void a(int i, AnimationListener animationListener) {
        this.a = i;
        this.I.reset();
        this.I.setDuration(200);
        this.I.setInterpolator(this.r);
        if (animationListener != null) {
            this.t.setAnimationListener(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.I);
    }

    private void b(int i, AnimationListener animationListener) {
        if (this.p) {
            c(i, animationListener);
            return;
        }
        this.a = i;
        this.J.reset();
        this.J.setDuration(200);
        this.J.setInterpolator(this.r);
        if (animationListener != null) {
            this.t.setAnimationListener(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.J);
    }

    private void a(float f) {
        a((this.a + ((int) (((float) (this.b - this.a)) * f))) - this.t.getTop(), false);
    }

    private void c(int i, AnimationListener animationListener) {
        this.a = i;
        if (b()) {
            this.v = (float) this.w.getAlpha();
        } else {
            this.v = ViewCompat.getScaleX(this.t);
        }
        this.B = new l(this);
        this.B.setDuration(150);
        if (animationListener != null) {
            this.t.setAnimationListener(animationListener);
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.B);
    }

    private void a(int i, boolean z) {
        this.t.bringToFront();
        this.t.offsetTopAndBottom(i);
        this.k = this.t.getTop();
        if (z && VERSION.SDK_INT < 11) {
            invalidate();
        }
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.o) {
            this.o = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
        }
    }

    public SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection getDirection() {
        return this.e;
    }

    private void setRawDirection(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (this.e != swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
            this.e = swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;
            int measuredHeight;
            switch (m.a[this.e.ordinal()]) {
                case 1:
                    measuredHeight = getMeasuredHeight() - this.t.getMeasuredHeight();
                    this.b = measuredHeight;
                    this.k = measuredHeight;
                    return;
                default:
                    measuredHeight = -this.t.getMeasuredHeight();
                    this.b = measuredHeight;
                    this.k = measuredHeight;
                    return;
            }
        }
    }
}
