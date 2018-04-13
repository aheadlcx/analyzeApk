package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final Handler a = new Handler(Looper.getMainLooper(), new e());
    private static final boolean d;
    final d b;
    final a c = new j(this);
    private final ViewGroup e;
    private final Context f;
    private final ContentViewCallback g;
    private int h;
    private List<BaseCallback<B>> i;
    private final AccessibilityManager j;

    public interface ContentViewCallback {
        void animateContentIn(int i, int i2);

        void animateContentOut(int i, int i2);
    }

    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public void onDismissed(B b, int i) {
        }

        public void onShown(B b) {
        }
    }

    @IntRange(from = 1)
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    final class a extends SwipeDismissBehavior<d> {
        final /* synthetic */ BaseTransientBottomBar a;

        a(BaseTransientBottomBar baseTransientBottomBar) {
            this.a = baseTransientBottomBar;
        }

        public boolean canSwipeDismissView(View view) {
            return view instanceof d;
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, d dVar, MotionEvent motionEvent) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    if (coordinatorLayout.isPointInChildBounds(dVar, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                        av.a().pauseTimeout(this.a.c);
                        break;
                    }
                    break;
                case 1:
                case 3:
                    av.a().restoreTimeoutIfPaused(this.a.c);
                    break;
            }
            return super.onInterceptTouchEvent(coordinatorLayout, dVar, motionEvent);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    interface b {
        void onViewAttachedToWindow(View view);

        void onViewDetachedFromWindow(View view);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    interface c {
        void onLayoutChange(View view, int i, int i2, int i3, int i4);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static class d extends FrameLayout {
        private c a;
        private b b;

        d(Context context) {
            this(context, null);
        }

        d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
            }
            obtainStyledAttributes.recycle();
            setClickable(true);
        }

        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.a != null) {
                this.a.onLayoutChange(this, i, i2, i3, i4);
            }
        }

        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.b != null) {
                this.b.onViewAttachedToWindow(this);
            }
            ViewCompat.requestApplyInsets(this);
        }

        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.b != null) {
                this.b.onViewDetachedFromWindow(this);
            }
        }

        void setOnLayoutChangeListener(c cVar) {
            this.a = cVar;
        }

        void setOnAttachStateChangeListener(b bVar) {
            this.b = bVar;
        }
    }

    static {
        boolean z = VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19;
        d = z;
    }

    protected BaseTransientBottomBar(@NonNull ViewGroup viewGroup, @NonNull View view, @NonNull ContentViewCallback contentViewCallback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        } else if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        } else if (contentViewCallback == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        } else {
            this.e = viewGroup;
            this.g = contentViewCallback;
            this.f = viewGroup.getContext();
            bj.a(this.f);
            this.b = (d) LayoutInflater.from(this.f).inflate(R.layout.design_layout_snackbar, this.e, false);
            this.b.addView(view);
            ViewCompat.setAccessibilityLiveRegion(this.b, 1);
            ViewCompat.setImportantForAccessibility(this.b, 1);
            ViewCompat.setFitsSystemWindows(this.b, true);
            ViewCompat.setOnApplyWindowInsetsListener(this.b, new i(this));
            this.j = (AccessibilityManager) this.f.getSystemService("accessibility");
        }
    }

    @NonNull
    public B setDuration(int i) {
        this.h = i;
        return this;
    }

    public int getDuration() {
        return this.h;
    }

    @NonNull
    public Context getContext() {
        return this.f;
    }

    @NonNull
    public View getView() {
        return this.b;
    }

    public void show() {
        av.a().show(this.h, this.c);
    }

    public void dismiss() {
        a(3);
    }

    void a(int i) {
        av.a().dismiss(this.c, i);
    }

    @NonNull
    public B addCallback(@NonNull BaseCallback<B> baseCallback) {
        if (baseCallback != null) {
            if (this.i == null) {
                this.i = new ArrayList();
            }
            this.i.add(baseCallback);
        }
        return this;
    }

    @NonNull
    public B removeCallback(@NonNull BaseCallback<B> baseCallback) {
        if (!(baseCallback == null || this.i == null)) {
            this.i.remove(baseCallback);
        }
        return this;
    }

    public boolean isShown() {
        return av.a().isCurrent(this.c);
    }

    public boolean isShownOrQueued() {
        return av.a().isCurrentOrNext(this.c);
    }

    final void a() {
        if (this.b.getParent() == null) {
            LayoutParams layoutParams = this.b.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
                Behavior aVar = new a(this);
                aVar.setStartAlphaSwipeDistance(0.1f);
                aVar.setEndAlphaSwipeDistance(0.6f);
                aVar.setSwipeDirection(0);
                aVar.setListener(new k(this));
                layoutParams2.setBehavior(aVar);
                layoutParams2.insetEdge = 80;
            }
            this.e.addView(this.b);
        }
        this.b.setOnAttachStateChangeListener(new l(this));
        if (!ViewCompat.isLaidOut(this.b)) {
            this.b.setOnLayoutChangeListener(new n(this));
        } else if (d()) {
            b();
        } else {
            c();
        }
    }

    void b() {
        if (VERSION.SDK_INT >= 12) {
            int height = this.b.getHeight();
            if (d) {
                ViewCompat.offsetTopAndBottom(this.b, height);
            } else {
                this.b.setTranslationY((float) height);
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(new int[]{height, 0});
            valueAnimator.setInterpolator(a.b);
            valueAnimator.setDuration(250);
            valueAnimator.addListener(new o(this));
            valueAnimator.addUpdateListener(new p(this, height));
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.b.getContext(), R.anim.design_snackbar_in);
        loadAnimation.setInterpolator(a.b);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new q(this));
        this.b.startAnimation(loadAnimation);
    }

    private void d(int i) {
        if (VERSION.SDK_INT >= 12) {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(new int[]{0, this.b.getHeight()});
            valueAnimator.setInterpolator(a.b);
            valueAnimator.setDuration(250);
            valueAnimator.addListener(new f(this, i));
            valueAnimator.addUpdateListener(new g(this));
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.b.getContext(), R.anim.design_snackbar_out);
        loadAnimation.setInterpolator(a.b);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new h(this, i));
        this.b.startAnimation(loadAnimation);
    }

    final void b(int i) {
        if (d() && this.b.getVisibility() == 0) {
            d(i);
        } else {
            c(i);
        }
    }

    void c() {
        av.a().onShown(this.c);
        if (this.i != null) {
            for (int size = this.i.size() - 1; size >= 0; size--) {
                ((BaseCallback) this.i.get(size)).onShown(this);
            }
        }
    }

    void c(int i) {
        av.a().onDismissed(this.c);
        if (this.i != null) {
            for (int size = this.i.size() - 1; size >= 0; size--) {
                ((BaseCallback) this.i.get(size)).onDismissed(this, i);
            }
        }
        if (VERSION.SDK_INT < 11) {
            this.b.setVisibility(8);
        }
        ViewParent parent = this.b.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.b);
        }
    }

    boolean d() {
        return !this.j.isEnabled();
    }
}
