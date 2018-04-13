package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
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
import android.widget.ListView;

public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    private static final int[] D = new int[]{16842766};
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;
    private static final String m = SwipeRefreshLayout.class.getSimpleName();
    private int A;
    private boolean B;
    private final DecelerateInterpolator C;
    private int E;
    private Animation F;
    private Animation G;
    private Animation H;
    private Animation I;
    private Animation J;
    private int K;
    private OnChildScrollUpCallback L;
    private AnimationListener M;
    private final Animation N;
    private final Animation O;
    OnRefreshListener a;
    boolean b;
    int c;
    boolean d;
    a e;
    protected int f;
    float g;
    protected int h;
    int i;
    CircularProgressDrawable j;
    boolean k;
    boolean l;
    private View n;
    private int o;
    private float p;
    private float q;
    private final NestedScrollingParentHelper r;
    private final NestedScrollingChildHelper s;
    private final int[] t;
    private final int[] u;
    private boolean v;
    private int w;
    private float x;
    private float y;
    private boolean z;

    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(@NonNull SwipeRefreshLayout swipeRefreshLayout, @Nullable View view);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    void a() {
        this.e.clearAnimation();
        this.j.stop();
        this.e.setVisibility(8);
        setColorViewAlpha(255);
        if (this.d) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.h - this.c);
        }
        this.c = this.e.getTop();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z) {
            a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    private void setColorViewAlpha(int i) {
        this.e.getBackground().setAlpha(i);
        this.j.setAlpha(i);
    }

    public void setProgressViewOffset(boolean z, int i, int i2) {
        this.d = z;
        this.h = i;
        this.i = i2;
        this.l = true;
        a();
        this.b = false;
    }

    public int getProgressViewStartOffset() {
        return this.h;
    }

    public int getProgressViewEndOffset() {
        return this.i;
    }

    public void setProgressViewEndTarget(boolean z, int i) {
        this.i = i;
        this.d = z;
        this.e.invalidate();
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                this.K = (int) (displayMetrics.density * 56.0f);
            } else {
                this.K = (int) (displayMetrics.density * 40.0f);
            }
            this.e.setImageDrawable(null);
            this.j.setStyle(i);
            this.e.setImageDrawable(this.j);
        }
    }

    public SwipeRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        this.p = -1.0f;
        this.t = new int[2];
        this.u = new int[2];
        this.A = -1;
        this.E = -1;
        this.M = new n(this);
        this.N = new s(this);
        this.O = new t(this);
        this.o = ViewConfiguration.get(context).getScaledTouchSlop();
        this.w = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.C = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.K = (int) (40.0f * displayMetrics.density);
        b();
        setChildrenDrawingOrderEnabled(true);
        this.i = (int) (displayMetrics.density * 64.0f);
        this.p = (float) this.i;
        this.r = new NestedScrollingParentHelper(this);
        this.s = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        int i = -this.K;
        this.c = i;
        this.h = i;
        a(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, D);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.E < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return this.E;
        }
        if (i2 >= this.E) {
            return i2 + 1;
        }
        return i2;
    }

    private void b() {
        this.e = new a(getContext(), -328966);
        this.j = new CircularProgressDrawable(getContext());
        this.j.setStyle(1);
        this.e.setImageDrawable(this.j);
        this.e.setVisibility(8);
        addView(this.e);
    }

    public void setOnRefreshListener(@Nullable OnRefreshListener onRefreshListener) {
        this.a = onRefreshListener;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.b == z) {
            a(z, false);
            return;
        }
        int i;
        this.b = z;
        if (this.l) {
            i = this.i;
        } else {
            i = this.i + this.h;
        }
        setTargetOffsetTopAndBottom(i - this.c);
        this.k = false;
        b(this.M);
    }

    private void b(AnimationListener animationListener) {
        this.e.setVisibility(0);
        if (VERSION.SDK_INT >= 11) {
            this.j.setAlpha(255);
        }
        this.F = new o(this);
        this.F.setDuration((long) this.w);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.F);
    }

    void setAnimationProgress(float f) {
        this.e.setScaleX(f);
        this.e.setScaleY(f);
    }

    private void a(boolean z, boolean z2) {
        if (this.b != z) {
            this.k = z2;
            e();
            this.b = z;
            if (this.b) {
                a(this.c, this.M);
            } else {
                a(this.M);
            }
        }
    }

    void a(AnimationListener animationListener) {
        this.G = new p(this);
        this.G.setDuration(150);
        this.e.setAnimationListener(animationListener);
        this.e.clearAnimation();
        this.e.startAnimation(this.G);
    }

    private void c() {
        this.H = a(this.j.getAlpha(), 76);
    }

    private void d() {
        this.I = a(this.j.getAlpha(), 255);
    }

    private Animation a(int i, int i2) {
        Animation qVar = new q(this, i, i2);
        qVar.setDuration(300);
        this.e.setAnimationListener(null);
        this.e.clearAnimation();
        this.e.startAnimation(qVar);
        return qVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int i) {
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), i));
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int i) {
        this.e.setBackgroundColor(i);
    }

    @Deprecated
    public void setColorScheme(@ColorRes int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeResources(@ColorRes int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = ContextCompat.getColor(context, iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setColorSchemeColors(@ColorInt int... iArr) {
        e();
        this.j.setColorSchemeColors(iArr);
    }

    public boolean isRefreshing() {
        return this.b;
    }

    private void e() {
        if (this.n == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.e)) {
                    i++;
                } else {
                    this.n = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.p = (float) i;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.n == null) {
                e();
            }
            if (this.n != null) {
                View view = this.n;
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                measuredHeight = this.e.getMeasuredWidth();
                this.e.layout((measuredWidth / 2) - (measuredHeight / 2), this.c, (measuredWidth / 2) + (measuredHeight / 2), this.c + this.e.getMeasuredHeight());
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.n == null) {
            e();
        }
        if (this.n != null) {
            this.n.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.e.measure(MeasureSpec.makeMeasureSpec(this.K, 1073741824), MeasureSpec.makeMeasureSpec(this.K, 1073741824));
            this.E = -1;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                if (getChildAt(i3) == this.e) {
                    this.E = i3;
                    return;
                }
            }
        }
    }

    public int getProgressCircleDiameter() {
        return this.K;
    }

    public boolean canChildScrollUp() {
        if (this.L != null) {
            return this.L.canChildScrollUp(this, this.n);
        }
        if (this.n instanceof ListView) {
            return ListViewCompat.canScrollList((ListView) this.n, -1);
        }
        return this.n.canScrollVertically(-1);
    }

    public void setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback onChildScrollUpCallback) {
        this.L = onChildScrollUpCallback;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        e();
        int actionMasked = motionEvent.getActionMasked();
        if (this.B && actionMasked == 0) {
            this.B = false;
        }
        if (!isEnabled() || this.B || canChildScrollUp() || this.b || this.v) {
            return false;
        }
        switch (actionMasked) {
            case 0:
                setTargetOffsetTopAndBottom(this.h - this.e.getTop());
                this.A = motionEvent.getPointerId(0);
                this.z = false;
                actionMasked = motionEvent.findPointerIndex(this.A);
                if (actionMasked >= 0) {
                    this.y = motionEvent.getY(actionMasked);
                    break;
                }
                return false;
            case 1:
            case 3:
                this.z = false;
                this.A = -1;
                break;
            case 2:
                if (this.A == -1) {
                    Log.e(m, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }
                actionMasked = motionEvent.findPointerIndex(this.A);
                if (actionMasked >= 0) {
                    d(motionEvent.getY(actionMasked));
                    break;
                }
                return false;
            case 6:
                a(motionEvent);
                break;
        }
        return this.z;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (VERSION.SDK_INT < 21 && (this.n instanceof AbsListView)) {
            return;
        }
        if (this.n == null || ViewCompat.isNestedScrollingEnabled(this.n)) {
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.B || this.b || (i & 2) == 0) ? false : true;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.r.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(i & 2);
        this.q = 0.0f;
        this.v = true;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0 && this.q > 0.0f) {
            if (((float) i2) > this.q) {
                iArr[1] = i2 - ((int) this.q);
                this.q = 0.0f;
            } else {
                this.q -= (float) i2;
                iArr[1] = i2;
            }
            b(this.q);
        }
        if (this.l && i2 > 0 && this.q == 0.0f && Math.abs(i2 - iArr[1]) > 0) {
            this.e.setVisibility(8);
        }
        int[] iArr2 = this.t;
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
        this.v = false;
        if (this.q > 0.0f) {
            c(this.q);
            this.q = 0.0f;
        }
        stopNestedScroll();
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(i, i2, i3, i4, this.u);
        int i5 = this.u[1] + i4;
        if (i5 < 0 && !canChildScrollUp()) {
            this.q = ((float) Math.abs(i5)) + this.q;
            b(this.q);
        }
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

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.s.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.s.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.s.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.s.dispatchNestedPreFling(f, f2);
    }

    private boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void b(float f) {
        float f2;
        this.j.setArrowEnabled(true);
        float min = Math.min(1.0f, Math.abs(f / this.p));
        float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f) - this.p;
        if (this.l) {
            f2 = (float) (this.i - this.h);
        } else {
            f2 = (float) this.i;
        }
        abs = Math.max(0.0f, Math.min(abs, f2 * 2.0f) / f2);
        abs = ((float) (((double) (abs / 4.0f)) - Math.pow((double) (abs / 4.0f), 2.0d))) * 2.0f;
        int i = ((int) ((f2 * min) + ((f2 * abs) * 2.0f))) + this.h;
        if (this.e.getVisibility() != 0) {
            this.e.setVisibility(0);
        }
        if (!this.d) {
            this.e.setScaleX(1.0f);
            this.e.setScaleY(1.0f);
        }
        if (this.d) {
            setAnimationProgress(Math.min(1.0f, f / this.p));
        }
        if (f < this.p) {
            if (this.j.getAlpha() > 76 && !a(this.H)) {
                c();
            }
        } else if (this.j.getAlpha() < 255 && !a(this.I)) {
            d();
        }
        this.j.setStartEndTrim(0.0f, Math.min(0.8f, max * 0.8f));
        this.j.setArrowScale(Math.min(1.0f, max));
        this.j.setProgressRotation(((-0.25f + (max * 0.4f)) + (abs * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i - this.c);
    }

    private void c(float f) {
        if (f > this.p) {
            a(true, true);
            return;
        }
        this.b = false;
        this.j.setStartEndTrim(0.0f, 0.0f);
        AnimationListener animationListener = null;
        if (!this.d) {
            animationListener = new r(this);
        }
        b(this.c, animationListener);
        this.j.setArrowEnabled(false);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.B && actionMasked == 0) {
            this.B = false;
        }
        if (!isEnabled() || this.B || canChildScrollUp() || this.b || this.v) {
            return false;
        }
        float y;
        switch (actionMasked) {
            case 0:
                this.A = motionEvent.getPointerId(0);
                this.z = false;
                break;
            case 1:
                actionMasked = motionEvent.findPointerIndex(this.A);
                if (actionMasked < 0) {
                    Log.e(m, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.z) {
                    y = (motionEvent.getY(actionMasked) - this.x) * 0.5f;
                    this.z = false;
                    c(y);
                }
                this.A = -1;
                return false;
            case 2:
                actionMasked = motionEvent.findPointerIndex(this.A);
                if (actionMasked < 0) {
                    Log.e(m, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                y = motionEvent.getY(actionMasked);
                d(y);
                if (this.z) {
                    y = (y - this.x) * 0.5f;
                    if (y > 0.0f) {
                        b(y);
                        break;
                    }
                    return false;
                }
                break;
            case 3:
                return false;
            case 5:
                actionMasked = motionEvent.getActionIndex();
                if (actionMasked >= 0) {
                    this.A = motionEvent.getPointerId(actionMasked);
                    break;
                }
                Log.e(m, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                return false;
            case 6:
                a(motionEvent);
                break;
        }
        return true;
    }

    private void d(float f) {
        if (f - this.y > ((float) this.o) && !this.z) {
            this.x = this.y + ((float) this.o);
            this.z = true;
            this.j.setAlpha(76);
        }
    }

    private void a(int i, AnimationListener animationListener) {
        this.f = i;
        this.N.reset();
        this.N.setDuration(200);
        this.N.setInterpolator(this.C);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.N);
    }

    private void b(int i, AnimationListener animationListener) {
        if (this.d) {
            c(i, animationListener);
            return;
        }
        this.f = i;
        this.O.reset();
        this.O.setDuration(200);
        this.O.setInterpolator(this.C);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.O);
    }

    void a(float f) {
        setTargetOffsetTopAndBottom((this.f + ((int) (((float) (this.h - this.f)) * f))) - this.e.getTop());
    }

    private void c(int i, AnimationListener animationListener) {
        this.f = i;
        this.g = this.e.getScaleX();
        this.J = new u(this);
        this.J.setDuration(150);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.J);
    }

    void setTargetOffsetTopAndBottom(int i) {
        this.e.bringToFront();
        ViewCompat.offsetTopAndBottom(this.e, i);
        this.c = this.e.getTop();
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.A) {
            this.A = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }
}
