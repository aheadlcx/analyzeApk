package qsbk.app.core.widget.refresh;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;

public class SwipeRefreshLayoutBottom extends ViewGroup {
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;
    private static final String c = SwipeRefreshLayoutBottom.class.getSimpleName();
    private static final int[] r = new int[]{16842766};
    private Animation A;
    private float B;
    private boolean C;
    private int D;
    private int E;
    private boolean F;
    private AnimationListener G;
    private final Animation H;
    private final Animation I;
    protected int a;
    protected int b;
    private View d;
    private SwipeRefreshLayoutBottom$OnRefreshListener e;
    private boolean f;
    private int g;
    private float h;
    private int i;
    private int j;
    private boolean k;
    private float l;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    private final DecelerateInterpolator q;
    private a s;
    private int t;
    private float u;
    private MaterialProgressDrawable v;
    private Animation w;
    private Animation x;
    private Animation y;
    private Animation z;

    private void setColorViewAlpha(int i) {
        this.s.getBackground().setAlpha(i);
        this.v.setAlpha(i);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i2;
            if (i == 0) {
                i2 = (int) (displayMetrics.density * 56.0f);
                this.D = i2;
                this.E = i2;
            } else {
                i2 = (int) (displayMetrics.density * 40.0f);
                this.D = i2;
                this.E = i2;
            }
            this.s.setImageDrawable(null);
            this.v.updateSizes(i);
            this.s.setImageDrawable(this.v);
        }
    }

    public SwipeRefreshLayoutBottom(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayoutBottom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = false;
        this.h = -1.0f;
        this.k = false;
        this.n = -1;
        this.t = -1;
        this.G = new n(this);
        this.H = new s(this);
        this.I = new t(this);
        this.g = ViewConfiguration.get(context).getScaledTouchSlop();
        this.i = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.q = new DecelerateInterpolator(2.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, r);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.D = (int) (displayMetrics.density * 40.0f);
        this.E = (int) (displayMetrics.density * 40.0f);
        a();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.B = displayMetrics.density * 100.0f;
        this.h = this.B;
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.t < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return this.t;
        }
        if (i2 >= this.t) {
            return i2 + 1;
        }
        return i2;
    }

    private void a() {
        this.s = new a(getContext(), -328966, 20.0f);
        this.v = new MaterialProgressDrawable(getContext(), this);
        this.v.setBackgroundColor(-328966);
        this.s.setImageDrawable(this.v);
        this.s.setVisibility(8);
        addView(this.s);
    }

    public void setOnRefreshListener(SwipeRefreshLayoutBottom$OnRefreshListener swipeRefreshLayoutBottom$OnRefreshListener) {
        this.e = swipeRefreshLayoutBottom$OnRefreshListener;
    }

    private boolean b() {
        return VERSION.SDK_INT < 11;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.f == z) {
            a(z, false);
            return;
        }
        int i;
        this.f = z;
        if (this.F) {
            i = (int) this.B;
        } else {
            i = (int) (this.B + ((float) this.b));
        }
        a(i - this.j, true);
        this.C = false;
        a(this.G);
    }

    private void a(AnimationListener animationListener) {
        this.s.setVisibility(0);
        if (VERSION.SDK_INT >= 11) {
            this.v.setAlpha(255);
        }
        this.w = new o(this);
        this.w.setDuration((long) this.i);
        if (animationListener != null) {
            this.s.setAnimationListener(animationListener);
        }
        this.s.clearAnimation();
        this.s.startAnimation(this.w);
    }

    private void setAnimationProgress(float f) {
        if (b()) {
            setColorViewAlpha((int) (255.0f * f));
            return;
        }
        ViewCompat.setScaleX(this.s, f);
        ViewCompat.setScaleY(this.s, f);
    }

    private void a(boolean z, boolean z2) {
        if (this.f != z) {
            this.C = z2;
            e();
            this.f = z;
            if (this.f) {
                a(this.j, this.G);
            } else {
                b(this.G);
            }
        }
    }

    private void b(AnimationListener animationListener) {
        this.x = new p(this);
        this.x.setDuration(150);
        this.s.setAnimationListener(animationListener);
        this.s.clearAnimation();
        this.s.startAnimation(this.x);
    }

    private void c() {
        this.y = a(this.v.getAlpha(), 76);
    }

    private void d() {
        this.z = a(this.v.getAlpha(), 255);
    }

    private Animation a(int i, int i2) {
        if (this.o && b()) {
            return null;
        }
        Animation qVar = new q(this, i, i2);
        qVar.setDuration(300);
        this.s.setAnimationListener(null);
        this.s.clearAnimation();
        this.s.startAnimation(qVar);
        return qVar;
    }

    public void setProgressBackgroundColor(int i) {
        this.s.setBackgroundColor(i);
        this.v.setBackgroundColor(getResources().getColor(i));
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
        this.v.setColorSchemeColors(iArr);
    }

    public boolean isRefreshing() {
        return this.f;
    }

    private void e() {
        if (this.d == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.s)) {
                    i++;
                } else {
                    this.d = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.h = (float) i;
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
                measuredHeight = this.s.getMeasuredWidth();
                this.s.layout((measuredWidth / 2) - (measuredHeight / 2), this.j, (measuredWidth / 2) + (measuredHeight / 2), this.j + this.s.getMeasuredHeight());
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
            this.s.measure(MeasureSpec.makeMeasureSpec(this.D, 1073741824), MeasureSpec.makeMeasureSpec(this.E, 1073741824));
            if (!(this.F || this.k)) {
                this.k = true;
                measuredHeight = getMeasuredHeight() - this.s.getMeasuredHeight();
                this.b = measuredHeight;
                this.j = measuredHeight;
            }
            this.t = -1;
            for (measuredHeight = 0; measuredHeight < getChildCount(); measuredHeight++) {
                if (getChildAt(measuredHeight) == this.s) {
                    this.t = measuredHeight;
                    return;
                }
            }
        }
    }

    public boolean canChildScrollUp() {
        boolean z = true;
        Log.w("ANDREY", "canChildScrollUp()");
        if (VERSION.SDK_INT >= 14) {
            Log.e("ANDREY", "return 333 " + ViewCompat.canScrollVertically(this.d, 1));
            return ViewCompat.canScrollVertically(this.d, 1);
        } else if (this.d instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.d;
            Log.e("ANDREY", " absListView.getFirstVisiblePosition() " + absListView.getFirstVisiblePosition());
            Log.e("ANDREY", " absListView.getLastVisiblePosition() " + absListView.getLastVisiblePosition());
            Log.e("ANDREY", " absListView.getCount() " + absListView.getCount());
            if (absListView.getLastVisiblePosition() + 1 != absListView.getCount()) {
                return true;
            }
            int lastVisiblePosition = absListView.getLastVisiblePosition() - absListView.getFirstVisiblePosition();
            Log.e("ANDREY", " lastIndex  " + lastVisiblePosition);
            if (absListView.getChildAt(lastVisiblePosition).getBottom() != absListView.getPaddingBottom()) {
                z = false;
            }
            if (z) {
                return z;
            }
            Log.e("ANDREY", "return " + z);
            return z;
        } else {
            boolean z2;
            String str = "ANDREY";
            StringBuilder append = new StringBuilder().append("return 222 ");
            if (this.d.getScrollY() > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            Log.e(str, append.append(z2).toString());
            if (this.d.getScrollY() <= 0) {
                return false;
            }
            return true;
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
        if (this.p && actionMasked == 0) {
            this.p = false;
        }
        if (!isEnabled() || this.p || canChildScrollDown() || this.f) {
            return false;
        }
        float a;
        switch (actionMasked) {
            case 0:
                a(this.b - this.s.getTop(), true);
                this.n = MotionEventCompat.getPointerId(motionEvent, 0);
                this.m = false;
                a = a(motionEvent, this.n);
                if (a != -1.0f) {
                    this.l = a;
                    break;
                }
                return false;
            case 1:
            case 3:
                this.m = false;
                this.n = -1;
                break;
            case 2:
                break;
            case 6:
                a(motionEvent);
                break;
        }
        if (this.n == -1) {
            Log.e(c, "Got ACTION_MOVE event but don't have an active pointer id.");
            return false;
        }
        a = a(motionEvent, this.n);
        if (a == -1.0f) {
            return false;
        }
        if (this.l - a > ((float) this.g) && !this.m) {
            this.m = true;
            this.v.setAlpha(76);
        }
        return this.m;
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
        if (this.p && actionMasked == 0) {
            this.p = false;
        }
        if (!isEnabled() || this.p) {
            return false;
        }
        float y;
        switch (actionMasked) {
            case 0:
                this.n = MotionEventCompat.getPointerId(motionEvent, 0);
                this.m = false;
                break;
            case 1:
            case 3:
                if (this.n == -1) {
                    if (actionMasked == 1) {
                        Log.e(c, "Got ACTION_UP event but don't have an active pointer id.");
                    }
                    return false;
                }
                y = (this.l - MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.n))) * 0.5f;
                this.m = false;
                if (y > this.h) {
                    a(true, true);
                } else {
                    this.f = false;
                    this.v.setStartEndTrim(0.0f, 0.0f);
                    AnimationListener animationListener = null;
                    if (!this.o) {
                        animationListener = new r(this);
                    }
                    b(this.j, animationListener);
                    this.v.showArrow(false);
                }
                this.n = -1;
                return false;
            case 2:
                actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.n);
                if (actionMasked >= 0) {
                    float y2 = 0.5f * (this.l - MotionEventCompat.getY(motionEvent, actionMasked));
                    if (this.m) {
                        this.v.showArrow(true);
                        y = y2 / this.h;
                        if (y >= 0.0f) {
                            float min = Math.min(1.0f, Math.abs(y));
                            float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
                            float abs = Math.abs(y2) - this.h;
                            y = this.F ? this.B - ((float) this.b) : this.B;
                            abs = Math.max(0.0f, Math.min(abs, 2.0f * y) / y);
                            abs = ((float) (((double) (abs / 4.0f)) - Math.pow((double) (abs / 4.0f), 2.0d))) * 2.0f;
                            actionMasked = this.b - ((int) ((y * min) + ((y * abs) * 2.0f)));
                            if (this.s.getVisibility() != 0) {
                                this.s.setVisibility(0);
                            }
                            if (!this.o) {
                                ViewCompat.setScaleX(this.s, 1.0f);
                                ViewCompat.setScaleY(this.s, 1.0f);
                            }
                            if (y2 < this.h) {
                                if (this.o) {
                                    setAnimationProgress(y2 / this.h);
                                }
                                if (this.v.getAlpha() > 76 && !a(this.y)) {
                                    c();
                                }
                                this.v.setStartEndTrim(0.0f, Math.min(0.8f, 0.8f * max));
                                this.v.setArrowScale(Math.min(1.0f, max));
                            } else if (this.v.getAlpha() < 255 && !a(this.z)) {
                                d();
                            }
                            this.v.setProgressRotation(((-0.25f + (0.4f * max)) + (2.0f * abs)) * 0.5f);
                            a(actionMasked - this.j, true);
                            break;
                        }
                        return false;
                    }
                }
                Log.e(c, "Got ACTION_MOVE event but have an invalid active pointer id.");
                return false;
                break;
            case 5:
                this.n = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return true;
    }

    private void a(int i, AnimationListener animationListener) {
        this.a = i;
        this.H.reset();
        this.H.setDuration(200);
        this.H.setInterpolator(this.q);
        if (animationListener != null) {
            this.s.setAnimationListener(animationListener);
        }
        this.s.clearAnimation();
        this.s.startAnimation(this.H);
    }

    private void b(int i, AnimationListener animationListener) {
        if (this.o) {
            c(i, animationListener);
            return;
        }
        this.a = i;
        this.I.reset();
        this.I.setDuration(200);
        this.I.setInterpolator(this.q);
        if (animationListener != null) {
            this.s.setAnimationListener(animationListener);
        }
        this.s.clearAnimation();
        this.s.startAnimation(this.I);
    }

    private void a(float f) {
        a((this.a + ((int) (((float) (this.b - this.a)) * f))) - this.s.getTop(), false);
    }

    private void c(int i, AnimationListener animationListener) {
        this.a = i;
        if (b()) {
            this.u = (float) this.v.getAlpha();
        } else {
            this.u = ViewCompat.getScaleX(this.s);
        }
        this.A = new u(this);
        this.A.setDuration(150);
        if (animationListener != null) {
            this.s.setAnimationListener(animationListener);
        }
        this.s.clearAnimation();
        this.s.startAnimation(this.A);
    }

    private void a(int i, boolean z) {
        this.s.bringToFront();
        this.s.offsetTopAndBottom(i);
        this.j = this.s.getTop();
        if (z && VERSION.SDK_INT < 11) {
            invalidate();
        }
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.n) {
            this.n = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
        }
    }
}
