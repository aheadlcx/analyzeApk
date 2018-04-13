package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class BottomSheetBehavior<V extends View> extends Behavior<V> {
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int a;
    int b;
    boolean c;
    int d = 4;
    ViewDragHelper e;
    int f;
    WeakReference<V> g;
    WeakReference<View> h;
    int i;
    boolean j;
    private float k;
    private int l;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    private int q;
    private boolean r;
    private BottomSheetCallback s;
    private VelocityTracker t;
    private int u;
    private final Callback v = new u(this);

    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);
    }

    protected static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new v();
        final int a;

        public SavedState(Parcel parcel) {
            this(parcel, null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.a = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    private class a implements Runnable {
        final /* synthetic */ BottomSheetBehavior a;
        private final View b;
        private final int c;

        a(BottomSheetBehavior bottomSheetBehavior, View view, int i) {
            this.a = bottomSheetBehavior;
            this.b = view;
            this.c = i;
        }

        public void run() {
            if (this.a.e == null || !this.a.e.continueSettling(true)) {
                this.a.a(this.c);
            } else {
                ViewCompat.postOnAnimation(this.b, this);
            }
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        TypedValue peekValue = obtainStyledAttributes.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue == null || peekValue.data != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            setPeekHeight(peekValue.data);
        }
        setHideable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        obtainStyledAttributes.recycle();
        this.k = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.d);
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.a == 1 || savedState.a == 2) {
            this.d = 4;
        } else {
            this.d = savedState.a;
        }
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        int max;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            ViewCompat.setFitsSystemWindows(v, true);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.f = coordinatorLayout.getHeight();
        if (this.m) {
            if (this.n == 0) {
                this.n = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            }
            max = Math.max(this.n, this.f - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            max = this.l;
        }
        this.a = Math.max(0, this.f - v.getHeight());
        this.b = Math.max(this.f - max, this.a);
        if (this.d == 3) {
            ViewCompat.offsetTopAndBottom(v, this.a);
        } else if (this.c && this.d == 5) {
            ViewCompat.offsetTopAndBottom(v, this.f);
        } else if (this.d == 4) {
            ViewCompat.offsetTopAndBottom(v, this.b);
        } else if (this.d == 1 || this.d == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        if (this.e == null) {
            this.e = ViewDragHelper.create(coordinatorLayout, this.v);
        }
        this.g = new WeakReference(v);
        this.h = new WeakReference(a((View) v));
        return true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = true;
        if (v.isShown()) {
            View view;
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                a();
            }
            if (this.t == null) {
                this.t = VelocityTracker.obtain();
            }
            this.t.addMovement(motionEvent);
            switch (actionMasked) {
                case 0:
                    int x = (int) motionEvent.getX();
                    this.u = (int) motionEvent.getY();
                    view = this.h != null ? (View) this.h.get() : null;
                    if (view != null && coordinatorLayout.isPointInChildBounds(view, x, this.u)) {
                        this.i = motionEvent.getPointerId(motionEvent.getActionIndex());
                        this.j = true;
                    }
                    boolean z2 = this.i == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.u);
                    this.p = z2;
                    break;
                case 1:
                case 3:
                    this.j = false;
                    this.i = -1;
                    if (this.p) {
                        this.p = false;
                        return false;
                    }
                    break;
            }
            if (!this.p && this.e.shouldInterceptTouchEvent(motionEvent)) {
                return true;
            }
            view = (View) this.h.get();
            if (actionMasked != 2 || view == null || this.p || this.d == 1 || coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY()) || Math.abs(((float) this.u) - motionEvent.getY()) <= ((float) this.e.getTouchSlop())) {
                z = false;
            }
            return z;
        }
        this.p = true;
        return false;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.d == 1 && actionMasked == 0) {
            return true;
        }
        if (this.e != null) {
            this.e.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            a();
        }
        if (this.t == null) {
            this.t = VelocityTracker.obtain();
        }
        this.t.addMovement(motionEvent);
        if (actionMasked == 2 && !this.p && Math.abs(((float) this.u) - motionEvent.getY()) > ((float) this.e.getTouchSlop())) {
            this.e.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        if (this.p) {
            return false;
        }
        return true;
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        this.q = 0;
        this.r = false;
        if ((i & 2) != 0) {
            return true;
        }
        return false;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        if (view == ((View) this.h.get())) {
            int top = v.getTop();
            int i3 = top - i2;
            if (i2 > 0) {
                if (i3 < this.a) {
                    iArr[1] = top - this.a;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    a(3);
                } else {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    a(1);
                }
            } else if (i2 < 0 && !view.canScrollVertically(-1)) {
                if (i3 <= this.b || this.c) {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    a(1);
                } else {
                    iArr[1] = top - this.b;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    a(4);
                }
            }
            b(v.getTop());
            this.q = i2;
            this.r = true;
        }
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        int i = 3;
        if (v.getTop() == this.a) {
            a(3);
        } else if (this.h != null && view == this.h.get() && this.r) {
            int i2;
            if (this.q > 0) {
                i2 = this.a;
            } else if (this.c && a((View) v, b())) {
                i2 = this.f;
                i = 5;
            } else if (this.q == 0) {
                int top = v.getTop();
                if (Math.abs(top - this.a) < Math.abs(top - this.b)) {
                    i2 = this.a;
                } else {
                    i2 = this.b;
                    i = 4;
                }
            } else {
                i2 = this.b;
                i = 4;
            }
            if (this.e.smoothSlideViewTo(v, v.getLeft(), i2)) {
                a(2);
                ViewCompat.postOnAnimation(v, new a(this, v, i));
            } else {
                a(i);
            }
            this.r = false;
        }
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.h.get() && (this.d != 3 || super.onNestedPreFling(coordinatorLayout, v, view, f, f2));
    }

    public final void setPeekHeight(int i) {
        boolean z = true;
        if (i == -1) {
            if (!this.m) {
                this.m = true;
            }
            z = false;
        } else {
            if (this.m || this.l != i) {
                this.m = false;
                this.l = Math.max(0, i);
                this.b = this.f - i;
            }
            z = false;
        }
        if (z && this.d == 4 && this.g != null) {
            View view = (View) this.g.get();
            if (view != null) {
                view.requestLayout();
            }
        }
    }

    public final int getPeekHeight() {
        return this.m ? -1 : this.l;
    }

    public void setHideable(boolean z) {
        this.c = z;
    }

    public boolean isHideable() {
        return this.c;
    }

    public void setSkipCollapsed(boolean z) {
        this.o = z;
    }

    public boolean getSkipCollapsed() {
        return this.o;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.s = bottomSheetCallback;
    }

    public final void setState(int i) {
        if (i != this.d) {
            if (this.g != null) {
                View view = (View) this.g.get();
                if (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(view)) {
                        view.post(new t(this, view, i));
                    } else {
                        a(view, i);
                    }
                }
            } else if (i == 4 || i == 3 || (this.c && i == 5)) {
                this.d = i;
            }
        }
    }

    public final int getState() {
        return this.d;
    }

    void a(int i) {
        if (this.d != i) {
            this.d = i;
            View view = (View) this.g.get();
            if (view != null && this.s != null) {
                this.s.onStateChanged(view, i);
            }
        }
    }

    private void a() {
        this.i = -1;
        if (this.t != null) {
            this.t.recycle();
            this.t = null;
        }
    }

    boolean a(View view, float f) {
        if (this.o) {
            return true;
        }
        if (view.getTop() < this.b) {
            return false;
        }
        if (Math.abs((((float) view.getTop()) + (0.1f * f)) - ((float) this.b)) / ((float) this.l) <= 0.5f) {
            return false;
        }
        return true;
    }

    @VisibleForTesting
    View a(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View a = a(viewGroup.getChildAt(i));
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }

    private float b() {
        this.t.computeCurrentVelocity(1000, this.k);
        return this.t.getYVelocity(this.i);
    }

    void a(View view, int i) {
        int i2;
        if (i == 4) {
            i2 = this.b;
        } else if (i == 3) {
            i2 = this.a;
        } else if (this.c && i == 5) {
            i2 = this.f;
        } else {
            throw new IllegalArgumentException("Illegal state argument: " + i);
        }
        if (this.e.smoothSlideViewTo(view, view.getLeft(), i2)) {
            a(2);
            ViewCompat.postOnAnimation(view, new a(this, view, i));
            return;
        }
        a(i);
    }

    void b(int i) {
        View view = (View) this.g.get();
        if (view != null && this.s != null) {
            if (i > this.b) {
                this.s.onSlide(view, ((float) (this.b - i)) / ((float) (this.f - this.b)));
            } else {
                this.s.onSlide(view, ((float) (this.b - i)) / ((float) (this.b - this.a)));
            }
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v) {
        LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
}
