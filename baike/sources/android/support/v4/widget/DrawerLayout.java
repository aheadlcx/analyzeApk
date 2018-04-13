package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    static final int[] a = new int[]{16842931};
    static final boolean b;
    private static final int[] c = new int[]{16843828};
    private static final boolean d;
    private float A;
    private Drawable B;
    private Drawable C;
    private Drawable D;
    private CharSequence E;
    private CharSequence F;
    private Object G;
    private boolean H;
    private Drawable I;
    private Drawable J;
    private Drawable K;
    private Drawable L;
    private final ArrayList<View> M;
    private final b e;
    private float f;
    private int g;
    private int h;
    private float i;
    private Paint j;
    private final ViewDragHelper k;
    private final ViewDragHelper l;
    private final c m;
    private final c n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    @Nullable
    private DrawerListener x;
    private List<DrawerListener> y;
    private float z;

    public interface DrawerListener {
        void onDrawerClosed(@NonNull View view);

        void onDrawerOpened(@NonNull View view);

        void onDrawerSlide(@NonNull View view, float f);

        void onDrawerStateChanged(int i);
    }

    public static class LayoutParams extends MarginLayoutParams {
        float a;
        boolean b;
        int c;
        public int gravity;

        public LayoutParams(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.a);
            this.gravity = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = 0;
        }

        public LayoutParams(int i, int i2, int i3) {
            this(i, i2);
            this.gravity = i3;
        }

        public LayoutParams(@NonNull LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(@NonNull android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }

        public LayoutParams(@NonNull MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = 0;
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new h();
        int a = 0;
        int b;
        int c;
        int d;
        int e;

        public SavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
        }

        public SavedState(@NonNull Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
        }
    }

    public static abstract class SimpleDrawerListener implements DrawerListener {
        public void onDrawerSlide(View view, float f) {
        }

        public void onDrawerOpened(View view) {
        }

        public void onDrawerClosed(View view) {
        }

        public void onDrawerStateChanged(int i) {
        }
    }

    class a extends AccessibilityDelegateCompat {
        final /* synthetic */ DrawerLayout a;
        private final Rect c = new Rect();

        a(DrawerLayout drawerLayout) {
            this.a = drawerLayout;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (DrawerLayout.b) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            } else {
                AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
                super.onInitializeAccessibilityNodeInfo(view, obtain);
                accessibilityNodeInfoCompat.setSource(view);
                ViewParent parentForAccessibility = ViewCompat.getParentForAccessibility(view);
                if (parentForAccessibility instanceof View) {
                    accessibilityNodeInfoCompat.setParent((View) parentForAccessibility);
                }
                a(accessibilityNodeInfoCompat, obtain);
                obtain.recycle();
                a(accessibilityNodeInfoCompat, (ViewGroup) view);
            }
            accessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
            accessibilityNodeInfoCompat.setFocusable(false);
            accessibilityNodeInfoCompat.setFocused(false);
            accessibilityNodeInfoCompat.removeAction(AccessibilityActionCompat.ACTION_FOCUS);
            accessibilityNodeInfoCompat.removeAction(AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            List text = accessibilityEvent.getText();
            View b = this.a.b();
            if (b != null) {
                CharSequence drawerTitle = this.a.getDrawerTitle(this.a.d(b));
                if (drawerTitle != null) {
                    text.add(drawerTitle);
                }
            }
            return true;
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.b || DrawerLayout.g(view)) {
                return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        private void a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.g(childAt)) {
                    accessibilityNodeInfoCompat.addChild(childAt);
                }
            }
        }

        private void a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.c;
            accessibilityNodeInfoCompat2.getBoundsInParent(rect);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
            accessibilityNodeInfoCompat2.getBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setVisibleToUser(accessibilityNodeInfoCompat2.isVisibleToUser());
            accessibilityNodeInfoCompat.setPackageName(accessibilityNodeInfoCompat2.getPackageName());
            accessibilityNodeInfoCompat.setClassName(accessibilityNodeInfoCompat2.getClassName());
            accessibilityNodeInfoCompat.setContentDescription(accessibilityNodeInfoCompat2.getContentDescription());
            accessibilityNodeInfoCompat.setEnabled(accessibilityNodeInfoCompat2.isEnabled());
            accessibilityNodeInfoCompat.setClickable(accessibilityNodeInfoCompat2.isClickable());
            accessibilityNodeInfoCompat.setFocusable(accessibilityNodeInfoCompat2.isFocusable());
            accessibilityNodeInfoCompat.setFocused(accessibilityNodeInfoCompat2.isFocused());
            accessibilityNodeInfoCompat.setAccessibilityFocused(accessibilityNodeInfoCompat2.isAccessibilityFocused());
            accessibilityNodeInfoCompat.setSelected(accessibilityNodeInfoCompat2.isSelected());
            accessibilityNodeInfoCompat.setLongClickable(accessibilityNodeInfoCompat2.isLongClickable());
            accessibilityNodeInfoCompat.addAction(accessibilityNodeInfoCompat2.getActions());
        }
    }

    static final class b extends AccessibilityDelegateCompat {
        b() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (!DrawerLayout.g(view)) {
                accessibilityNodeInfoCompat.setParent(null);
            }
        }
    }

    private class c extends Callback {
        final /* synthetic */ DrawerLayout a;
        private final int b;
        private ViewDragHelper c;
        private final Runnable d = new i(this);

        c(DrawerLayout drawerLayout, int i) {
            this.a = drawerLayout;
            this.b = i;
        }

        public void setDragger(ViewDragHelper viewDragHelper) {
            this.c = viewDragHelper;
        }

        public void removeCallbacks() {
            this.a.removeCallbacks(this.d);
        }

        public boolean tryCaptureView(View view, int i) {
            return this.a.f(view) && this.a.a(view, this.b) && this.a.getDrawerLockMode(view) == 0;
        }

        public void onViewDragStateChanged(int i) {
            this.a.a(this.b, i, this.c.getCapturedView());
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float f;
            int width = view.getWidth();
            if (this.a.a(view, 3)) {
                f = ((float) (width + i)) / ((float) width);
            } else {
                f = ((float) (this.a.getWidth() - i)) / ((float) width);
            }
            this.a.b(view, f);
            view.setVisibility(f == 0.0f ? 4 : 0);
            this.a.invalidate();
        }

        public void onViewCaptured(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).b = false;
            b();
        }

        private void b() {
            int i = 3;
            if (this.b == 3) {
                i = 5;
            }
            View a = this.a.a(i);
            if (a != null) {
                this.a.closeDrawer(a);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            float c = this.a.c(view);
            int width = view.getWidth();
            if (this.a.a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && c > 0.5f)) ? 0 : -width;
            } else {
                i = this.a.getWidth();
                if (f < 0.0f || (f == 0.0f && c > 0.5f)) {
                    i -= width;
                }
            }
            this.c.settleCapturedViewAt(i, view.getTop());
            this.a.invalidate();
        }

        public void onEdgeTouched(int i, int i2) {
            this.a.postDelayed(this.d, 160);
        }

        void a() {
            View view;
            int i;
            int i2 = 0;
            int edgeSize = this.c.getEdgeSize();
            boolean z = this.b == 3;
            if (z) {
                View a = this.a.a(3);
                if (a != null) {
                    i2 = -a.getWidth();
                }
                i2 += edgeSize;
                view = a;
                i = i2;
            } else {
                i2 = this.a.getWidth() - edgeSize;
                view = this.a.a(5);
                i = i2;
            }
            if (view == null) {
                return;
            }
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && this.a.getDrawerLockMode(view) == 0) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                this.c.smoothSlideViewTo(view, i, view.getTop());
                layoutParams.b = true;
                this.a.invalidate();
                b();
                this.a.c();
            }
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        public void onEdgeDragStarted(int i, int i2) {
            View a;
            if ((i & 1) == 1) {
                a = this.a.a(3);
            } else {
                a = this.a.a(5);
            }
            if (a != null && this.a.getDrawerLockMode(a) == 0) {
                this.c.captureChildView(a, i2);
            }
        }

        public int getViewHorizontalDragRange(View view) {
            return this.a.f(view) ? view.getWidth() : 0;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (this.a.a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = this.a.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }
    }

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 19) {
            z = true;
        } else {
            z = false;
        }
        b = z;
        if (VERSION.SDK_INT < 21) {
            z2 = false;
        }
        d = z2;
    }

    public DrawerLayout(@NonNull Context context) {
        this(context, null);
    }

    public DrawerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new b();
        this.h = SystemBarTintManager.DEFAULT_TINT_COLOR;
        this.j = new Paint();
        this.q = true;
        this.r = 3;
        this.s = 3;
        this.t = 3;
        this.u = 3;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.g = (int) ((64.0f * f) + 0.5f);
        float f2 = 400.0f * f;
        this.m = new c(this, 3);
        this.n = new c(this, 5);
        this.k = ViewDragHelper.create(this, 1.0f, this.m);
        this.k.setEdgeTrackingEnabled(1);
        this.k.setMinVelocity(f2);
        this.m.setDragger(this.k);
        this.l = ViewDragHelper.create(this, 1.0f, this.n);
        this.l.setEdgeTrackingEnabled(2);
        this.l.setMinVelocity(f2);
        this.n.setDragger(this.l);
        setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new a(this));
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        if (ViewCompat.getFitsSystemWindows(this)) {
            if (VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new g(this));
                setSystemUiVisibility(1280);
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(c);
                try {
                    this.B = obtainStyledAttributes.getDrawable(0);
                } finally {
                    obtainStyledAttributes.recycle();
                }
            } else {
                this.B = null;
            }
        }
        this.f = f * 10.0f;
        this.M = new ArrayList();
    }

    public void setDrawerElevation(float f) {
        this.f = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (f(childAt)) {
                ViewCompat.setElevation(childAt, this.f);
            }
        }
    }

    public float getDrawerElevation() {
        if (d) {
            return this.f;
        }
        return 0.0f;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setChildInsets(Object obj, boolean z) {
        this.G = obj;
        this.H = z;
        boolean z2 = !z && getBackground() == null;
        setWillNotDraw(z2);
        requestLayout();
    }

    public void setDrawerShadow(Drawable drawable, int i) {
        if (!d) {
            if ((i & GravityCompat.START) == GravityCompat.START) {
                this.I = drawable;
            } else if ((i & GravityCompat.END) == GravityCompat.END) {
                this.J = drawable;
            } else if ((i & 3) == 3) {
                this.K = drawable;
            } else if ((i & 5) == 5) {
                this.L = drawable;
            } else {
                return;
            }
            d();
            invalidate();
        }
    }

    public void setDrawerShadow(@DrawableRes int i, int i2) {
        setDrawerShadow(ContextCompat.getDrawable(getContext(), i), i2);
    }

    public void setScrimColor(@ColorInt int i) {
        this.h = i;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(DrawerListener drawerListener) {
        if (this.x != null) {
            removeDrawerListener(this.x);
        }
        if (drawerListener != null) {
            addDrawerListener(drawerListener);
        }
        this.x = drawerListener;
    }

    public void addDrawerListener(@NonNull DrawerListener drawerListener) {
        if (drawerListener != null) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.add(drawerListener);
        }
    }

    public void removeDrawerListener(@NonNull DrawerListener drawerListener) {
        if (drawerListener != null && this.y != null) {
            this.y.remove(drawerListener);
        }
    }

    public void setDrawerLockMode(int i) {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    public void setDrawerLockMode(int i, int i2) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this));
        switch (i2) {
            case 3:
                this.r = i;
                break;
            case 5:
                this.s = i;
                break;
            case GravityCompat.START /*8388611*/:
                this.t = i;
                break;
            case GravityCompat.END /*8388613*/:
                this.u = i;
                break;
        }
        if (i != 0) {
            (absoluteGravity == 3 ? this.k : this.l).cancel();
        }
        View a;
        switch (i) {
            case 1:
                a = a(absoluteGravity);
                if (a != null) {
                    closeDrawer(a);
                    return;
                }
                return;
            case 2:
                a = a(absoluteGravity);
                if (a != null) {
                    openDrawer(a);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setDrawerLockMode(int i, @NonNull View view) {
        if (f(view)) {
            setDrawerLockMode(i, ((LayoutParams) view.getLayoutParams()).gravity);
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a " + "drawer with appropriate layout_gravity");
    }

    public int getDrawerLockMode(int i) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        switch (i) {
            case 3:
                if (this.r != 3) {
                    return this.r;
                }
                layoutDirection = layoutDirection == 0 ? this.t : this.u;
                if (layoutDirection != 3) {
                    return layoutDirection;
                }
                break;
            case 5:
                if (this.s != 3) {
                    return this.s;
                }
                layoutDirection = layoutDirection == 0 ? this.u : this.t;
                if (layoutDirection != 3) {
                    return layoutDirection;
                }
                break;
            case GravityCompat.START /*8388611*/:
                if (this.t != 3) {
                    return this.t;
                }
                layoutDirection = layoutDirection == 0 ? this.r : this.s;
                if (layoutDirection != 3) {
                    return layoutDirection;
                }
                break;
            case GravityCompat.END /*8388613*/:
                if (this.u != 3) {
                    return this.u;
                }
                layoutDirection = layoutDirection == 0 ? this.s : this.r;
                if (layoutDirection != 3) {
                    return layoutDirection;
                }
                break;
        }
        return 0;
    }

    public int getDrawerLockMode(@NonNull View view) {
        if (f(view)) {
            return getDrawerLockMode(((LayoutParams) view.getLayoutParams()).gravity);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public void setDrawerTitle(int i, @Nullable CharSequence charSequence) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 3) {
            this.E = charSequence;
        } else if (absoluteGravity == 5) {
            this.F = charSequence;
        }
    }

    @Nullable
    public CharSequence getDrawerTitle(int i) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 3) {
            return this.E;
        }
        if (absoluteGravity == 5) {
            return this.F;
        }
        return null;
    }

    void a(int i, int i2, View view) {
        int viewDragState = this.k.getViewDragState();
        int viewDragState2 = this.l.getViewDragState();
        if (viewDragState == 1 || viewDragState2 == 1) {
            viewDragState = 1;
        } else if (viewDragState == 2 || viewDragState2 == 2) {
            viewDragState = 2;
        } else {
            viewDragState = 0;
        }
        if (view != null && i2 == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.a == 0.0f) {
                a(view);
            } else if (layoutParams.a == 1.0f) {
                b(view);
            }
        }
        if (viewDragState != this.o) {
            this.o = viewDragState;
            if (this.y != null) {
                for (int size = this.y.size() - 1; size >= 0; size--) {
                    ((DrawerListener) this.y.get(size)).onDrawerStateChanged(viewDragState);
                }
            }
        }
    }

    void a(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.c & 1) == 1) {
            layoutParams.c = 0;
            if (this.y != null) {
                for (int size = this.y.size() - 1; size >= 0; size--) {
                    ((DrawerListener) this.y.get(size)).onDrawerClosed(view);
                }
            }
            a(view, false);
            if (hasWindowFocus()) {
                View rootView = getRootView();
                if (rootView != null) {
                    rootView.sendAccessibilityEvent(32);
                }
            }
        }
    }

    void b(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.c & 1) == 0) {
            layoutParams.c = 1;
            if (this.y != null) {
                for (int size = this.y.size() - 1; size >= 0; size--) {
                    ((DrawerListener) this.y.get(size)).onDrawerOpened(view);
                }
            }
            a(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    private void a(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || f(childAt)) && !(z && childAt == view)) {
                ViewCompat.setImportantForAccessibility(childAt, 4);
            } else {
                ViewCompat.setImportantForAccessibility(childAt, 1);
            }
        }
    }

    void a(View view, float f) {
        if (this.y != null) {
            for (int size = this.y.size() - 1; size >= 0; size--) {
                ((DrawerListener) this.y.get(size)).onDrawerSlide(view, f);
            }
        }
    }

    void b(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f != layoutParams.a) {
            layoutParams.a = f;
            a(view, f);
        }
    }

    float c(View view) {
        return ((LayoutParams) view.getLayoutParams()).a;
    }

    int d(View view) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    boolean a(View view, int i) {
        return (d(view) & i) == i;
    }

    View a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((LayoutParams) childAt.getLayoutParams()).c & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    void c(View view, float f) {
        float c = c(view);
        int width = view.getWidth();
        int i = ((int) (((float) width) * f)) - ((int) (c * ((float) width)));
        if (!a(view, 3)) {
            i = -i;
        }
        view.offsetLeftAndRight(i);
        b(view, f);
    }

    View a(int i) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((d(childAt) & 7) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    static String b(int i) {
        if ((i & 3) == 3) {
            return "LEFT";
        }
        if ((i & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString(i);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
    }

    protected void onMeasure(int i, int i2) {
        Object obj;
        int layoutDirection;
        Object obj2;
        Object obj3;
        int childCount;
        int i3;
        View childAt;
        LayoutParams layoutParams;
        int absoluteGravity;
        WindowInsets windowInsets;
        Object obj4;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE && mode == 0) {
                    size = 300;
                }
                if (mode2 == Integer.MIN_VALUE) {
                    mode = size2;
                    mode2 = size;
                } else if (mode2 == 0) {
                    mode = 300;
                    mode2 = size;
                }
                setMeasuredDimension(mode2, mode);
                if (this.G == null && ViewCompat.getFitsSystemWindows(this)) {
                    obj = 1;
                } else {
                    obj = null;
                }
                layoutDirection = ViewCompat.getLayoutDirection(this);
                obj2 = null;
                obj3 = null;
                childCount = getChildCount();
                for (i3 = 0; i3 < childCount; i3++) {
                    childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 8) {
                        layoutParams = (LayoutParams) childAt.getLayoutParams();
                        if (obj != null) {
                            absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.gravity, layoutDirection);
                            if (ViewCompat.getFitsSystemWindows(childAt)) {
                                if (VERSION.SDK_INT >= 21) {
                                    windowInsets = (WindowInsets) this.G;
                                    if (absoluteGravity == 3) {
                                        windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
                                    } else if (absoluteGravity == 5) {
                                        windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                                    }
                                    layoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
                                    layoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
                                    layoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
                                    layoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
                                }
                            } else if (VERSION.SDK_INT >= 21) {
                                windowInsets = (WindowInsets) this.G;
                                if (absoluteGravity == 3) {
                                    windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
                                } else if (absoluteGravity == 5) {
                                    windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                                }
                                childAt.dispatchApplyWindowInsets(windowInsets);
                            }
                        }
                        if (e(childAt)) {
                            childAt.measure(MeasureSpec.makeMeasureSpec((mode2 - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec((mode - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                        } else if (f(childAt)) {
                            throw new IllegalStateException("Child " + childAt + " at index " + i3 + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                        } else {
                            if (d && ViewCompat.getElevation(childAt) != this.f) {
                                ViewCompat.setElevation(childAt, this.f);
                            }
                            absoluteGravity = d(childAt) & 7;
                            obj4 = absoluteGravity != 3 ? 1 : null;
                            if ((obj4 != null || obj2 == null) && (obj4 != null || obj3 == null)) {
                                if (obj4 == null) {
                                    Object obj5 = obj3;
                                    obj3 = 1;
                                    obj4 = obj5;
                                } else {
                                    obj4 = 1;
                                    obj3 = obj2;
                                }
                                childAt.measure(getChildMeasureSpec(i, (this.g + layoutParams.leftMargin) + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(i2, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                                obj2 = obj3;
                                obj3 = obj4;
                            } else {
                                throw new IllegalStateException("Child drawer has absolute gravity " + b(absoluteGravity) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
                            }
                        }
                    }
                }
            }
            throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
        }
        mode = size2;
        mode2 = size;
        setMeasuredDimension(mode2, mode);
        if (this.G == null) {
        }
        obj = null;
        layoutDirection = ViewCompat.getLayoutDirection(this);
        obj2 = null;
        obj3 = null;
        childCount = getChildCount();
        for (i3 = 0; i3 < childCount; i3++) {
            childAt = getChildAt(i3);
            if (childAt.getVisibility() == 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (obj != null) {
                    absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.gravity, layoutDirection);
                    if (ViewCompat.getFitsSystemWindows(childAt)) {
                        if (VERSION.SDK_INT >= 21) {
                            windowInsets = (WindowInsets) this.G;
                            if (absoluteGravity == 3) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
                            } else if (absoluteGravity == 5) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                            }
                            layoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
                            layoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
                            layoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
                            layoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
                        }
                    } else if (VERSION.SDK_INT >= 21) {
                        windowInsets = (WindowInsets) this.G;
                        if (absoluteGravity == 3) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
                        } else if (absoluteGravity == 5) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                        }
                        childAt.dispatchApplyWindowInsets(windowInsets);
                    }
                }
                if (e(childAt)) {
                    childAt.measure(MeasureSpec.makeMeasureSpec((mode2 - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec((mode - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                } else if (f(childAt)) {
                    throw new IllegalStateException("Child " + childAt + " at index " + i3 + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                } else {
                    ViewCompat.setElevation(childAt, this.f);
                    absoluteGravity = d(childAt) & 7;
                    if (absoluteGravity != 3) {
                    }
                    if (obj4 != null) {
                    }
                    if (obj4 == null) {
                        obj4 = 1;
                        obj3 = obj2;
                    } else {
                        Object obj52 = obj3;
                        obj3 = 1;
                        obj4 = obj52;
                    }
                    childAt.measure(getChildMeasureSpec(i, (this.g + layoutParams.leftMargin) + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(i2, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                    obj2 = obj3;
                    obj3 = obj4;
                }
            }
        }
    }

    private void d() {
        if (!d) {
            this.C = e();
            this.D = f();
        }
    }

    private Drawable e() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.I != null) {
                a(this.I, layoutDirection);
                return this.I;
            }
        } else if (this.J != null) {
            a(this.J, layoutDirection);
            return this.J;
        }
        return this.K;
    }

    private Drawable f() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.J != null) {
                a(this.J, layoutDirection);
                return this.J;
            }
        } else if (this.I != null) {
            a(this.I, layoutDirection);
            return this.I;
        }
        return this.L;
    }

    private boolean a(Drawable drawable, int i) {
        if (drawable == null || !DrawableCompat.isAutoMirrored(drawable)) {
            return false;
        }
        DrawableCompat.setLayoutDirection(drawable, i);
        return true;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.p = true;
        int i5 = i3 - i;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (e(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int i7;
                    float f;
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        i7 = ((int) (((float) measuredWidth) * layoutParams.a)) + (-measuredWidth);
                        f = ((float) (measuredWidth + i7)) / ((float) measuredWidth);
                    } else {
                        i7 = i5 - ((int) (((float) measuredWidth) * layoutParams.a));
                        f = ((float) (i5 - i7)) / ((float) measuredWidth);
                    }
                    Object obj = f != layoutParams.a ? 1 : null;
                    int i8;
                    switch (layoutParams.gravity & 112) {
                        case 16:
                            int i9 = i4 - i2;
                            i8 = (i9 - measuredHeight) / 2;
                            if (i8 < layoutParams.topMargin) {
                                i8 = layoutParams.topMargin;
                            } else if (i8 + measuredHeight > i9 - layoutParams.bottomMargin) {
                                i8 = (i9 - layoutParams.bottomMargin) - measuredHeight;
                            }
                            childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
                            break;
                        case 80:
                            i8 = i4 - i2;
                            childAt.layout(i7, (i8 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i8 - layoutParams.bottomMargin);
                            break;
                        default:
                            childAt.layout(i7, layoutParams.topMargin, measuredWidth + i7, measuredHeight + layoutParams.topMargin);
                            break;
                    }
                    if (obj != null) {
                        b(childAt, f);
                    }
                    int i10 = layoutParams.a > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i10) {
                        childAt.setVisibility(i10);
                    }
                }
            }
        }
        this.p = false;
        this.q = false;
    }

    public void requestLayout() {
        if (!this.p) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((LayoutParams) getChildAt(i).getLayoutParams()).a);
        }
        this.i = f;
        boolean continueSettling = this.k.continueSettling(true);
        boolean continueSettling2 = this.l.continueSettling(true);
        if (continueSettling || continueSettling2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private static boolean h(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return true;
    }

    public void setStatusBarBackground(@Nullable Drawable drawable) {
        this.B = drawable;
        invalidate();
    }

    @Nullable
    public Drawable getStatusBarBackgroundDrawable() {
        return this.B;
    }

    public void setStatusBarBackground(int i) {
        this.B = i != 0 ? ContextCompat.getDrawable(getContext(), i) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int i) {
        this.B = new ColorDrawable(i);
        invalidate();
    }

    public void onRtlPropertiesChanged(int i) {
        d();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.H && this.B != null) {
            int systemWindowInsetTop = VERSION.SDK_INT >= 21 ? this.G != null ? ((WindowInsets) this.G).getSystemWindowInsetTop() : 0 : 0;
            if (systemWindowInsetTop > 0) {
                this.B.setBounds(0, 0, getWidth(), systemWindowInsetTop);
                this.B.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        int height = getHeight();
        boolean e = e(view);
        int i2 = 0;
        int width = getWidth();
        int save = canvas.save();
        if (e) {
            int childCount = getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0 && h(childAt) && f(childAt)) {
                    if (childAt.getHeight() < height) {
                        i = width;
                    } else if (a(childAt, 3)) {
                        i = childAt.getRight();
                        if (i <= i2) {
                            i = i2;
                        }
                        i2 = i;
                        i = width;
                    } else {
                        i = childAt.getLeft();
                        if (i < width) {
                        }
                    }
                    i3++;
                    width = i;
                }
                i = width;
                i3++;
                width = i;
            }
            canvas.clipRect(i2, 0, width, getHeight());
        }
        i = width;
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        if (this.i > 0.0f && e) {
            this.j.setColor((((int) (((float) ((this.h & -16777216) >>> 24)) * this.i)) << 24) | (this.h & ViewCompat.MEASURED_SIZE_MASK));
            canvas.drawRect((float) i2, 0.0f, (float) i, (float) getHeight(), this.j);
        } else if (this.C != null && a(view, 3)) {
            i = this.C.getIntrinsicWidth();
            i2 = view.getRight();
            r2 = Math.max(0.0f, Math.min(((float) i2) / ((float) this.k.getEdgeSize()), 1.0f));
            this.C.setBounds(i2, view.getTop(), i + i2, view.getBottom());
            this.C.setAlpha((int) (255.0f * r2));
            this.C.draw(canvas);
        } else if (this.D != null && a(view, 5)) {
            i = this.D.getIntrinsicWidth();
            i2 = view.getLeft();
            r2 = Math.max(0.0f, Math.min(((float) (getWidth() - i2)) / ((float) this.l.getEdgeSize()), 1.0f));
            this.D.setBounds(i2 - i, view.getTop(), i2, view.getBottom());
            this.D.setAlpha((int) (255.0f * r2));
            this.D.draw(canvas);
        }
        return drawChild;
    }

    boolean e(View view) {
        return ((LayoutParams) view.getLayoutParams()).gravity == 0;
    }

    boolean f(View view) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view));
        if ((absoluteGravity & 3) != 0) {
            return true;
        }
        if ((absoluteGravity & 5) != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r1 = 1;
        r2 = 0;
        r0 = r8.getActionMasked();
        r3 = r7.k;
        r3 = r3.shouldInterceptTouchEvent(r8);
        r4 = r7.l;
        r4 = r4.shouldInterceptTouchEvent(r8);
        r3 = r3 | r4;
        switch(r0) {
            case 0: goto L_0x0027;
            case 1: goto L_0x0065;
            case 2: goto L_0x0050;
            case 3: goto L_0x0065;
            default: goto L_0x0016;
        };
    L_0x0016:
        r0 = r2;
    L_0x0017:
        if (r3 != 0) goto L_0x0025;
    L_0x0019:
        if (r0 != 0) goto L_0x0025;
    L_0x001b:
        r0 = r7.g();
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r0 = r7.w;
        if (r0 == 0) goto L_0x0026;
    L_0x0025:
        r2 = r1;
    L_0x0026:
        return r2;
    L_0x0027:
        r0 = r8.getX();
        r4 = r8.getY();
        r7.z = r0;
        r7.A = r4;
        r5 = r7.i;
        r6 = 0;
        r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x006d;
    L_0x003a:
        r5 = r7.k;
        r0 = (int) r0;
        r4 = (int) r4;
        r0 = r5.findTopChildUnder(r0, r4);
        if (r0 == 0) goto L_0x006d;
    L_0x0044:
        r0 = r7.e(r0);
        if (r0 == 0) goto L_0x006d;
    L_0x004a:
        r0 = r1;
    L_0x004b:
        r7.v = r2;
        r7.w = r2;
        goto L_0x0017;
    L_0x0050:
        r0 = r7.k;
        r4 = 3;
        r0 = r0.checkTouchSlop(r4);
        if (r0 == 0) goto L_0x0016;
    L_0x0059:
        r0 = r7.m;
        r0.removeCallbacks();
        r0 = r7.n;
        r0.removeCallbacks();
        r0 = r2;
        goto L_0x0017;
    L_0x0065:
        r7.a(r1);
        r7.v = r2;
        r7.w = r2;
        goto L_0x0016;
    L_0x006d:
        r0 = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.k.processTouchEvent(motionEvent);
        this.l.processTouchEvent(motionEvent);
        float x;
        float y;
        switch (motionEvent.getAction() & 255) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.z = x;
                this.A = y;
                this.v = false;
                this.w = false;
                break;
            case 1:
                boolean z;
                x = motionEvent.getX();
                y = motionEvent.getY();
                View findTopChildUnder = this.k.findTopChildUnder((int) x, (int) y);
                if (findTopChildUnder != null && e(findTopChildUnder)) {
                    x -= this.z;
                    y -= this.A;
                    int touchSlop = this.k.getTouchSlop();
                    if ((x * x) + (y * y) < ((float) (touchSlop * touchSlop))) {
                        View a = a();
                        if (a != null) {
                            z = getDrawerLockMode(a) == 2;
                            a(z);
                            this.v = false;
                            break;
                        }
                    }
                }
                z = true;
                a(z);
                this.v = false;
            case 3:
                a(true);
                this.v = false;
                this.w = false;
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.v = z;
        if (z) {
            a(true);
        }
    }

    public void closeDrawers() {
        a(false);
    }

    void a(boolean z) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (f(childAt) && (!z || layoutParams.b)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    i |= this.k.smoothSlideViewTo(childAt, -width, childAt.getTop());
                } else {
                    i |= this.l.smoothSlideViewTo(childAt, getWidth(), childAt.getTop());
                }
                layoutParams.b = false;
            }
        }
        this.m.removeCallbacks();
        this.n.removeCallbacks();
        if (i != 0) {
            invalidate();
        }
    }

    public void openDrawer(@NonNull View view) {
        openDrawer(view, true);
    }

    public void openDrawer(@NonNull View view, boolean z) {
        if (f(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.q) {
                layoutParams.a = 1.0f;
                layoutParams.c = 1;
                a(view, true);
            } else if (z) {
                layoutParams.c |= 2;
                if (a(view, 3)) {
                    this.k.smoothSlideViewTo(view, 0, view.getTop());
                } else {
                    this.l.smoothSlideViewTo(view, getWidth() - view.getWidth(), view.getTop());
                }
            } else {
                c(view, 1.0f);
                a(layoutParams.gravity, 0, view);
                view.setVisibility(0);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void openDrawer(int i) {
        openDrawer(i, true);
    }

    public void openDrawer(int i, boolean z) {
        View a = a(i);
        if (a == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + b(i));
        }
        openDrawer(a, z);
    }

    public void closeDrawer(@NonNull View view) {
        closeDrawer(view, true);
    }

    public void closeDrawer(@NonNull View view, boolean z) {
        if (f(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.q) {
                layoutParams.a = 0.0f;
                layoutParams.c = 0;
            } else if (z) {
                layoutParams.c |= 4;
                if (a(view, 3)) {
                    this.k.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
                } else {
                    this.l.smoothSlideViewTo(view, getWidth(), view.getTop());
                }
            } else {
                c(view, 0.0f);
                a(layoutParams.gravity, 0, view);
                view.setVisibility(4);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void closeDrawer(int i) {
        closeDrawer(i, true);
    }

    public void closeDrawer(int i, boolean z) {
        View a = a(i);
        if (a == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + b(i));
        }
        closeDrawer(a, z);
    }

    public boolean isDrawerOpen(@NonNull View view) {
        if (f(view)) {
            return (((LayoutParams) view.getLayoutParams()).c & 1) == 1;
        } else {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
    }

    public boolean isDrawerOpen(int i) {
        View a = a(i);
        if (a != null) {
            return isDrawerOpen(a);
        }
        return false;
    }

    public boolean isDrawerVisible(@NonNull View view) {
        if (f(view)) {
            return ((LayoutParams) view.getLayoutParams()).a > 0.0f;
        } else {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
    }

    public boolean isDrawerVisible(int i) {
        View a = a(i);
        if (a != null) {
            return isDrawerVisible(a);
        }
        return false;
    }

    private boolean g() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).b) {
                return true;
            }
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int i3 = 0;
        if (getDescendantFocusability() != 393216) {
            int i4;
            int childCount = getChildCount();
            int i5 = 0;
            for (i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (!f(childAt)) {
                    this.M.add(childAt);
                } else if (isDrawerOpen(childAt)) {
                    i5 = 1;
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
            if (i5 == 0) {
                i4 = this.M.size();
                while (i3 < i4) {
                    View view = (View) this.M.get(i3);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i, i2);
                    }
                    i3++;
                }
            }
            this.M.clear();
        }
    }

    private boolean h() {
        return b() != null;
    }

    View b() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (f(childAt) && isDrawerVisible(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    void c() {
        int i = 0;
        if (!this.w) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            while (i < childCount) {
                getChildAt(i).dispatchTouchEvent(obtain);
                i++;
            }
            obtain.recycle();
            this.w = true;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !h()) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View b = b();
        if (b != null && getDrawerLockMode(b) == 0) {
            closeDrawers();
        }
        return b != null;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (savedState.a != 0) {
                View a = a(savedState.a);
                if (a != null) {
                    openDrawer(a);
                }
            }
            if (savedState.b != 3) {
                setDrawerLockMode(savedState.b, 3);
            }
            if (savedState.c != 3) {
                setDrawerLockMode(savedState.c, 5);
            }
            if (savedState.d != 3) {
                setDrawerLockMode(savedState.d, (int) GravityCompat.START);
            }
            if (savedState.e != 3) {
                setDrawerLockMode(savedState.e, (int) GravityCompat.END);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            Object obj = layoutParams.c == 1 ? 1 : null;
            Object obj2;
            if (layoutParams.c == 2) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj != null || r4 != null) {
                savedState.a = layoutParams.gravity;
                break;
            }
        }
        savedState.b = this.r;
        savedState.c = this.s;
        savedState.d = this.t;
        savedState.e = this.u;
        return savedState;
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (a() != null || f(view)) {
            ViewCompat.setImportantForAccessibility(view, 4);
        } else {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
        if (!b) {
            ViewCompat.setAccessibilityDelegate(view, this.e);
        }
    }

    static boolean g(View view) {
        return (ViewCompat.getImportantForAccessibility(view) == 4 || ViewCompat.getImportantForAccessibility(view) == 2) ? false : true;
    }
}
