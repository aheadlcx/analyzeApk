package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import com.handmark.pulltorefresh.library.internal.SixroomLayout;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.handmark.pulltorefresh.library.internal.ViewCompat;

public abstract class PullToRefreshBase<T extends View> extends LinearLayout implements IPullToRefresh<T> {
    public static final int SMOOTH_SCROLL_DURATION_MS = 200;
    public static final int SMOOTH_SCROLL_LONG_DURATION_MS = 325;
    T a;
    private int b;
    private float c;
    private float d;
    private float e;
    private float f;
    private boolean g = false;
    private State h = State.RESET;
    private Mode i = Mode.a();
    private Mode j;
    private FrameLayout k;
    private boolean l = true;
    private boolean m = false;
    private boolean n = true;
    private boolean o = true;
    private boolean p = true;
    private Interpolator q;
    private AnimationStyle r = AnimationStyle.a();
    private LoadingLayout s;
    private LoadingLayout t;
    private PullToRefreshBase$OnRefreshListener<T> u;
    private PullToRefreshBase$OnRefreshListener2<T> v;
    private OnPullEventListener<T> w;
    private b x;

    public enum AnimationStyle {
        ROTATE,
        FLIP;

        static AnimationStyle a() {
            return ROTATE;
        }

        static AnimationStyle a(int i) {
            switch (i) {
                case 1:
                    return FLIP;
                default:
                    return ROTATE;
            }
        }

        static LoadingLayout a(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
            return new SixroomLayout(context, mode, orientation, typedArray);
        }
    }

    public enum Mode {
        DISABLED(0),
        PULL_FROM_START(1),
        PULL_FROM_END(2),
        BOTH(3),
        MANUAL_REFRESH_ONLY(4);
        
        public static Mode PULL_DOWN_TO_REFRESH;
        public static Mode PULL_UP_TO_REFRESH;
        private int a;

        static {
            PULL_DOWN_TO_REFRESH = PULL_FROM_START;
            PULL_UP_TO_REFRESH = PULL_FROM_END;
        }

        static Mode a(int i) {
            for (Mode mode : values()) {
                if (i == mode.a) {
                    return mode;
                }
            }
            return PULL_FROM_START;
        }

        static Mode a() {
            return PULL_FROM_START;
        }

        private Mode(int i) {
            this.a = i;
        }

        final boolean b() {
            return (this == DISABLED || this == MANUAL_REFRESH_ONLY) ? false : true;
        }

        public final boolean showHeaderLoadingLayout() {
            return this == PULL_FROM_START || this == BOTH;
        }

        public final boolean showFooterLoadingLayout() {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }

        final int c() {
            return this.a;
        }
    }

    public interface OnLastItemVisibleListener {
        void onLastItemVisible();
    }

    public interface OnPullEventListener<V extends View> {
        void onPullEvent(PullToRefreshBase<V> pullToRefreshBase, State state, Mode mode);
    }

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public enum State {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9),
        OVERSCROLLING(16);
        
        private int a;

        static State a(int i) {
            for (State state : values()) {
                if (i == state.a) {
                    return state;
                }
            }
            return RESET;
        }

        private State(int i) {
            this.a = i;
        }

        final int a() {
            return this.a;
        }
    }

    interface a {
        void a();
    }

    final class b implements Runnable {
        final /* synthetic */ PullToRefreshBase a;
        private final Interpolator b;
        private final int c;
        private final int d;
        private final long e;
        private a f;
        private boolean g = true;
        private long h = -1;
        private int i = -1;

        public b(PullToRefreshBase pullToRefreshBase, int i, int i2, long j, a aVar) {
            this.a = pullToRefreshBase;
            this.d = i;
            this.c = i2;
            this.b = pullToRefreshBase.q;
            this.e = j;
            this.f = aVar;
        }

        public final void run() {
            if (this.h == -1) {
                this.h = System.currentTimeMillis();
            } else {
                float f = (float) (this.d - this.c);
                this.i = this.d - Math.round(this.b.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.h) * 1000) / this.e, 1000), 0)) / 1000.0f) * f);
                this.a.setHeaderScroll(this.i);
            }
            if (this.g && this.c != this.i) {
                ViewCompat.postOnAnimation(this.a, this);
            } else if (this.f != null) {
                this.f.a();
            }
        }

        public final void a() {
            this.g = false;
            this.a.removeCallbacks(this);
        }
    }

    protected abstract T createRefreshableView(Context context, AttributeSet attributeSet);

    public abstract Orientation getPullToRefreshScrollDirection();

    protected abstract boolean isReadyForPullEnd();

    protected abstract boolean isReadyForPullStart();

    public PullToRefreshBase(Context context) {
        super(context);
        a(context, null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public PullToRefreshBase(Context context, Mode mode) {
        super(context);
        this.i = mode;
        a(context, null);
    }

    public PullToRefreshBase(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context);
        this.i = mode;
        this.r = animationStyle;
        a(context, null);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        View refreshableView = getRefreshableView();
        if (refreshableView instanceof ViewGroup) {
            ((ViewGroup) refreshableView).addView(view, i, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
    }

    public final boolean demo() {
        if (this.i.showHeaderLoadingLayout() && isReadyForPullStart()) {
            a((-getHeaderSize()) * 2);
            return true;
        } else if (!this.i.showFooterLoadingLayout() || !isReadyForPullEnd()) {
            return false;
        } else {
            a(getFooterSize() * 2);
            return true;
        }
    }

    public final Mode getCurrentMode() {
        return this.j;
    }

    public final boolean getFilterTouchEvents() {
        return this.n;
    }

    public final ILoadingLayout getLoadingLayoutProxy() {
        return getLoadingLayoutProxy(true, true);
    }

    public final ILoadingLayout getLoadingLayoutProxy(boolean z, boolean z2) {
        return createLoadingLayoutProxy(z, z2);
    }

    public final Mode getMode() {
        return this.i;
    }

    public final T getRefreshableView() {
        return this.a;
    }

    public final boolean getShowViewWhileRefreshing() {
        return this.l;
    }

    public final State getState() {
        return this.h;
    }

    public final boolean isDisableScrollingWhileRefreshing() {
        return !isScrollingWhileRefreshingEnabled();
    }

    public final boolean isPullToRefreshEnabled() {
        return this.i.b();
    }

    public final boolean isPullToRefreshOverScrollEnabled() {
        return VERSION.SDK_INT >= 9 && this.o && OverscrollHelper.a(this.a);
    }

    public final boolean isRefreshing() {
        return this.h == State.REFRESHING || this.h == State.MANUAL_REFRESHING;
    }

    public final boolean isScrollingWhileRefreshingEnabled() {
        return this.m;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!isPullToRefreshEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.g = false;
            return false;
        } else if (action != 0 && this.g) {
            return true;
        } else {
            float y;
            switch (action) {
                case 0:
                    if (b()) {
                        y = motionEvent.getY();
                        this.f = y;
                        this.d = y;
                        y = motionEvent.getX();
                        this.e = y;
                        this.c = y;
                        this.g = false;
                        break;
                    }
                    break;
                case 2:
                    if (this.m || !isRefreshing()) {
                        if (b()) {
                            float y2 = motionEvent.getY();
                            float x = motionEvent.getX();
                            float f;
                            switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
                                case 1:
                                    y = x - this.c;
                                    f = y2 - this.d;
                                    break;
                                default:
                                    y = y2 - this.d;
                                    f = x - this.c;
                                    break;
                            }
                            float abs = Math.abs(y);
                            if (abs > ((float) this.b) && (!this.n || abs > Math.abs(r0))) {
                                if (!this.i.showHeaderLoadingLayout() || y < 1.0f || !isReadyForPullStart()) {
                                    if (this.i.showFooterLoadingLayout() && y <= -1.0f && isReadyForPullEnd()) {
                                        this.d = y2;
                                        this.c = x;
                                        this.g = true;
                                        if (this.i == Mode.BOTH) {
                                            this.j = Mode.PULL_FROM_END;
                                            break;
                                        }
                                    }
                                }
                                this.d = y2;
                                this.c = x;
                                this.g = true;
                                if (this.i == Mode.BOTH) {
                                    this.j = Mode.PULL_FROM_START;
                                    break;
                                }
                            }
                        }
                    }
                    return true;
                    break;
            }
            return this.g;
        }
    }

    public final void onRefreshComplete() {
        if (isRefreshing()) {
            a(State.RESET, new boolean[0]);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isPullToRefreshEnabled()) {
            return false;
        }
        if (!this.m && isRefreshing()) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        float y;
        switch (motionEvent.getAction()) {
            case 0:
                if (b()) {
                    y = motionEvent.getY();
                    this.f = y;
                    this.d = y;
                    y = motionEvent.getX();
                    this.e = y;
                    this.c = y;
                    return true;
                }
                break;
            case 1:
            case 3:
                if (this.g) {
                    this.g = false;
                    if (this.h == State.RELEASE_TO_REFRESH && (this.u != null || this.v != null)) {
                        a(State.REFRESHING, true);
                        return true;
                    } else if (isRefreshing()) {
                        smoothScrollTo(0);
                        return true;
                    } else {
                        a(State.RESET, new boolean[0]);
                        return true;
                    }
                }
                break;
            case 2:
                if (this.g) {
                    float f;
                    int round;
                    int footerSize;
                    this.d = motionEvent.getY();
                    this.c = motionEvent.getX();
                    switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
                        case 1:
                            f = this.e;
                            y = this.c;
                            break;
                        default:
                            f = this.f;
                            y = this.d;
                            break;
                    }
                    switch (e.c[this.j.ordinal()]) {
                        case 1:
                            round = Math.round(Math.max(f - y, 0.0f) / 2.0f);
                            footerSize = getFooterSize();
                            break;
                        default:
                            round = Math.round(Math.min(f - y, 0.0f) / 2.0f);
                            footerSize = getHeaderSize();
                            break;
                    }
                    setHeaderScroll(round);
                    if (!(round == 0 || isRefreshing())) {
                        float abs = ((float) Math.abs(round)) / ((float) footerSize);
                        switch (e.c[this.j.ordinal()]) {
                            case 1:
                                this.t.onPull(abs);
                                break;
                            default:
                                this.s.onPull(abs);
                                break;
                        }
                        if (this.h != State.PULL_TO_REFRESH && footerSize >= Math.abs(round)) {
                            a(State.PULL_TO_REFRESH, new boolean[0]);
                        } else if (this.h == State.PULL_TO_REFRESH && footerSize < Math.abs(round)) {
                            a(State.RELEASE_TO_REFRESH, new boolean[0]);
                        }
                    }
                    return true;
                }
                break;
        }
        return false;
    }

    public final void setScrollingWhileRefreshingEnabled(boolean z) {
        this.m = z;
    }

    public void setDisableScrollingWhileRefreshing(boolean z) {
        setScrollingWhileRefreshingEnabled(!z);
    }

    public final void setFilterTouchEvents(boolean z) {
        this.n = z;
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setLastUpdatedLabel(charSequence);
    }

    public void setLoadingDrawable(Drawable drawable) {
        getLoadingLayoutProxy().setLoadingDrawable(drawable);
    }

    public void setLoadingDrawable(Drawable drawable, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setLoadingDrawable(drawable);
    }

    public void setLongClickable(boolean z) {
        getRefreshableView().setLongClickable(z);
    }

    public final void setMode(Mode mode) {
        if (mode != this.i) {
            this.i = mode;
            updateUIForMode();
        }
    }

    public void setOnPullEventListener(OnPullEventListener<T> onPullEventListener) {
        this.w = onPullEventListener;
    }

    public final void setOnRefreshListener(PullToRefreshBase$OnRefreshListener<T> pullToRefreshBase$OnRefreshListener) {
        this.u = pullToRefreshBase$OnRefreshListener;
        this.v = null;
    }

    public final void setOnRefreshListener(PullToRefreshBase$OnRefreshListener2<T> pullToRefreshBase$OnRefreshListener2) {
        this.v = pullToRefreshBase$OnRefreshListener2;
        this.u = null;
    }

    public void setPullLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setPullLabel(charSequence);
    }

    public void setPullLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setPullLabel(charSequence);
    }

    public final void setPullToRefreshEnabled(boolean z) {
        setMode(z ? Mode.a() : Mode.DISABLED);
    }

    public final void setPullToRefreshOverScrollEnabled(boolean z) {
        this.o = z;
    }

    public final void setRefreshing() {
        setRefreshing(true);
    }

    public void setRefresh() {
        this.h = State.RESET;
        setRefreshing(true);
    }

    public final void setRefreshing(boolean z) {
        if (!isRefreshing()) {
            a(State.MANUAL_REFRESHING, z);
        }
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setRefreshingLabel(charSequence);
    }

    public void setRefreshingLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setRefreshingLabel(charSequence);
    }

    public void setReleaseLabel(CharSequence charSequence) {
        setReleaseLabel(charSequence, Mode.BOTH);
    }

    public void setReleaseLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setReleaseLabel(charSequence);
    }

    public void setScrollAnimationInterpolator(Interpolator interpolator) {
        this.q = interpolator;
    }

    public final void setShowViewWhileRefreshing(boolean z) {
        this.l = z;
    }

    final void a(State state, boolean... zArr) {
        this.h = state;
        switch (e.b[this.h.ordinal()]) {
            case 1:
                onReset();
                break;
            case 2:
                onPullToRefresh();
                break;
            case 3:
                onReleaseToRefresh();
                break;
            case 4:
            case 5:
                onRefreshing(zArr[0]);
                break;
        }
        if (this.w != null) {
            this.w.onPullEvent(this, this.h, this.j);
        }
    }

    protected final void addViewInternal(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    protected final void addViewInternal(View view, LayoutParams layoutParams) {
        super.addView(view, -1, layoutParams);
    }

    protected LoadingLayout createLoadingLayout(Context context, Mode mode, TypedArray typedArray) {
        LoadingLayout a = AnimationStyle.a(context, mode, getPullToRefreshScrollDirection(), typedArray);
        a.setVisibility(4);
        return a;
    }

    protected LoadingLayoutProxy createLoadingLayoutProxy(boolean z, boolean z2) {
        LoadingLayoutProxy loadingLayoutProxy = new LoadingLayoutProxy();
        if (z && this.i.showHeaderLoadingLayout()) {
            loadingLayoutProxy.addLayout(this.s);
        }
        if (z2 && this.i.showFooterLoadingLayout()) {
            loadingLayoutProxy.addLayout(this.t);
        }
        return loadingLayoutProxy;
    }

    protected final void disableLoadingLayoutVisibilityChanges() {
        this.p = false;
    }

    protected final LoadingLayout getFooterLayout() {
        return this.t;
    }

    protected final int getFooterSize() {
        return this.t.getContentSize();
    }

    protected final LoadingLayout getHeaderLayout() {
        return this.s;
    }

    protected final int getHeaderSize() {
        return this.s.getContentSize();
    }

    protected int getPullToRefreshScrollDuration() {
        return 200;
    }

    protected int getPullToRefreshScrollDurationLonger() {
        return 325;
    }

    protected FrameLayout getRefreshableViewWrapper() {
        return this.k;
    }

    protected void handleStyledAttributes(TypedArray typedArray) {
    }

    protected void onPtrRestoreInstanceState(Bundle bundle) {
    }

    protected void onPtrSaveInstanceState(Bundle bundle) {
    }

    protected void onPullToRefresh() {
        switch (e.c[this.j.ordinal()]) {
            case 1:
                this.t.pullToRefresh();
                return;
            case 2:
                this.s.pullToRefresh();
                return;
            default:
                return;
        }
    }

    protected void onRefreshing(boolean z) {
        if (this.i.showHeaderLoadingLayout()) {
            this.s.refreshing();
        }
        if (this.i.showFooterLoadingLayout()) {
            this.t.refreshing();
        }
        if (!z) {
            a();
        } else if (this.l) {
            a bVar = new b(this);
            switch (e.c[this.j.ordinal()]) {
                case 1:
                case 3:
                    smoothScrollTo(getFooterSize(), bVar);
                    return;
                default:
                    smoothScrollTo(-getHeaderSize(), bVar);
                    return;
            }
        } else {
            smoothScrollTo(0);
        }
    }

    protected void onReleaseToRefresh() {
        switch (e.c[this.j.ordinal()]) {
            case 1:
                this.t.releaseToRefresh();
                return;
            case 2:
                this.s.releaseToRefresh();
                return;
            default:
                return;
        }
    }

    protected void onReset() {
        this.g = false;
        this.p = true;
        this.s.reset();
        this.t.reset();
        smoothScrollTo(0);
    }

    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            setMode(Mode.a(bundle.getInt("ptr_mode", 0)));
            this.j = Mode.a(bundle.getInt("ptr_current_mode", 0));
            this.m = bundle.getBoolean("ptr_disable_scrolling", false);
            this.l = bundle.getBoolean("ptr_show_refreshing_view", true);
            super.onRestoreInstanceState(bundle.getParcelable("ptr_super"));
            State a = State.a(bundle.getInt("ptr_state", 0));
            if (a == State.REFRESHING || a == State.MANUAL_REFRESHING) {
                a(a, true);
            }
            onPtrRestoreInstanceState(bundle);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected final Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        onPtrSaveInstanceState(bundle);
        bundle.putInt("ptr_state", this.h.a());
        bundle.putInt("ptr_mode", this.i.c());
        bundle.putInt("ptr_current_mode", this.j.c());
        bundle.putBoolean("ptr_disable_scrolling", this.m);
        bundle.putBoolean("ptr_show_refreshing_view", this.l);
        bundle.putParcelable("ptr_super", super.onSaveInstanceState());
        return bundle;
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        refreshLoadingViewsSize();
        refreshRefreshableViewSize(i, i2);
        post(new c(this));
    }

    protected final void refreshLoadingViewsSize() {
        int i;
        int i2 = 0;
        int maximumPullScroll = (int) (((float) getMaximumPullScroll()) * 1.2f);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
            case 1:
                if (this.i.showHeaderLoadingLayout()) {
                    this.s.setWidth(maximumPullScroll);
                    i = -maximumPullScroll;
                } else {
                    i = 0;
                }
                if (!this.i.showFooterLoadingLayout()) {
                    paddingRight = i;
                    i = 0;
                    i2 = paddingBottom;
                    paddingBottom = paddingTop;
                    break;
                }
                this.t.setWidth(maximumPullScroll);
                paddingRight = i;
                i = -maximumPullScroll;
                i2 = paddingBottom;
                paddingBottom = paddingTop;
                break;
            case 2:
                if (this.i.showHeaderLoadingLayout()) {
                    this.s.setHeight(maximumPullScroll);
                    i = -maximumPullScroll;
                } else {
                    i = 0;
                }
                if (!this.i.showFooterLoadingLayout()) {
                    paddingBottom = i;
                    i = paddingRight;
                    paddingRight = paddingLeft;
                    break;
                }
                this.t.setHeight(maximumPullScroll);
                i2 = -maximumPullScroll;
                paddingBottom = i;
                i = paddingRight;
                paddingRight = paddingLeft;
                break;
            default:
                i2 = paddingBottom;
                i = paddingRight;
                paddingBottom = paddingTop;
                paddingRight = paddingLeft;
                break;
        }
        setPadding(paddingRight, paddingBottom, i, i2);
    }

    protected final void refreshRefreshableViewSize(int i, int i2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.k.getLayoutParams();
        switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
            case 1:
                if (layoutParams.width != i) {
                    layoutParams.width = i;
                    this.k.requestLayout();
                    return;
                }
                return;
            case 2:
                if (layoutParams.height != i2) {
                    layoutParams.height = i2;
                    this.k.requestLayout();
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected final void setHeaderScroll(int i) {
        int maximumPullScroll = getMaximumPullScroll();
        maximumPullScroll = Math.min(maximumPullScroll, Math.max(-maximumPullScroll, i));
        if (this.p) {
            if (maximumPullScroll < 0) {
                this.s.setVisibility(0);
            } else if (maximumPullScroll > 0) {
                this.t.setVisibility(0);
            } else {
                this.s.setVisibility(4);
                this.t.setVisibility(4);
            }
        }
        switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
            case 1:
                scrollTo(maximumPullScroll, 0);
                return;
            case 2:
                scrollTo(0, maximumPullScroll);
                return;
            default:
                return;
        }
    }

    protected final void smoothScrollTo(int i) {
        a(i, (long) getPullToRefreshScrollDuration());
    }

    protected final void smoothScrollTo(int i, a aVar) {
        a(i, (long) getPullToRefreshScrollDuration(), 0, aVar);
    }

    protected final void smoothScrollToLonger(int i) {
        a(i, (long) getPullToRefreshScrollDurationLonger());
    }

    protected void updateUIForMode() {
        LayoutParams loadingLayoutLayoutParams = getLoadingLayoutLayoutParams();
        if (this == this.s.getParent()) {
            removeView(this.s);
        }
        if (this.i.showHeaderLoadingLayout()) {
            addViewInternal(this.s, 0, loadingLayoutLayoutParams);
        }
        if (this == this.t.getParent()) {
            removeView(this.t);
        }
        if (this.i.showFooterLoadingLayout()) {
            addViewInternal(this.t, loadingLayoutLayoutParams);
        }
        refreshLoadingViewsSize();
        this.j = this.i != Mode.BOTH ? this.i : Mode.PULL_FROM_START;
    }

    private void a() {
        if (this.u != null) {
            this.u.onRefresh(this);
        } else if (this.v == null) {
        } else {
            if (this.j == Mode.PULL_FROM_START) {
                this.v.onPullDownToRefresh(this);
            } else if (this.j == Mode.PULL_FROM_END) {
                this.v.onPullUpToRefresh(this);
            }
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
            case 1:
                setOrientation(0);
                break;
            default:
                setOrientation(1);
                break;
        }
        setGravity(17);
        this.b = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PullToRefresh);
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrMode)) {
            this.i = Mode.a(obtainStyledAttributes.getInteger(R.styleable.PullToRefresh_ptrMode, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrAnimationStyle)) {
            this.r = AnimationStyle.a(obtainStyledAttributes.getInteger(R.styleable.PullToRefresh_ptrAnimationStyle, 0));
        }
        this.a = createRefreshableView(context, attributeSet);
        View view = this.a;
        this.k = new FrameLayout(context);
        this.k.addView(view, -1, -1);
        addViewInternal(this.k, new LinearLayout.LayoutParams(-1, -1));
        this.s = createLoadingLayout(context, Mode.PULL_FROM_START, obtainStyledAttributes);
        this.t = createLoadingLayout(context, Mode.PULL_FROM_END, obtainStyledAttributes);
        Drawable drawable;
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrRefreshableViewBackground)) {
            drawable = obtainStyledAttributes.getDrawable(R.styleable.PullToRefresh_ptrRefreshableViewBackground);
            if (drawable != null) {
                this.a.setBackgroundDrawable(drawable);
            }
        } else if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrAdapterViewBackground)) {
            Utils.warnDeprecation("ptrAdapterViewBackground", "ptrRefreshableViewBackground");
            drawable = obtainStyledAttributes.getDrawable(R.styleable.PullToRefresh_ptrAdapterViewBackground);
            if (drawable != null) {
                this.a.setBackgroundDrawable(drawable);
            }
        }
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrOverScroll)) {
            this.o = obtainStyledAttributes.getBoolean(R.styleable.PullToRefresh_ptrOverScroll, true);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
            this.m = obtainStyledAttributes.getBoolean(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled, false);
        }
        handleStyledAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        updateUIForMode();
    }

    private boolean b() {
        switch (e.c[this.i.ordinal()]) {
            case 1:
                return isReadyForPullEnd();
            case 2:
                return isReadyForPullStart();
            case 4:
                return isReadyForPullEnd() || isReadyForPullStart();
            default:
                return false;
        }
    }

    private LinearLayout.LayoutParams getLoadingLayoutLayoutParams() {
        switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
            case 1:
                return new LinearLayout.LayoutParams(-2, -1);
            default:
                return new LinearLayout.LayoutParams(-1, -2);
        }
    }

    private int getMaximumPullScroll() {
        switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
            case 1:
                return Math.round(((float) getWidth()) / 2.0f);
            default:
                return Math.round(((float) getHeight()) / 2.0f);
        }
    }

    private final void a(int i, long j) {
        a(i, j, 0, null);
    }

    private final void a(int i, long j, long j2, a aVar) {
        int scrollX;
        if (this.x != null) {
            this.x.a();
        }
        switch (e.a[getPullToRefreshScrollDirection().ordinal()]) {
            case 1:
                scrollX = getScrollX();
                break;
            default:
                scrollX = getScrollY();
                break;
        }
        if (scrollX != i) {
            if (this.q == null) {
                this.q = new DecelerateInterpolator();
            }
            this.x = new b(this, scrollX, i, j, aVar);
            if (j2 > 0) {
                postDelayed(this.x, j2);
            } else {
                post(this.x);
            }
        }
    }

    private final void a(int i) {
        a(i, 200, 0, new d(this));
    }
}
