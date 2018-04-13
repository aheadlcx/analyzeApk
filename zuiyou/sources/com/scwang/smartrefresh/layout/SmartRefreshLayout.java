package com.scwang.smartrefresh.layout;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import com.scwang.smartrefresh.layout.a.d;
import com.scwang.smartrefresh.layout.a.e;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.a.i;
import com.scwang.smartrefresh.layout.constant.DimensionStatus;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.e.c;
import java.util.ArrayList;
import java.util.List;

public class SmartRefreshLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent, h {
    protected static boolean aF = false;
    protected static com.scwang.smartrefresh.layout.a.a aG = new com.scwang.smartrefresh.layout.a.a() {
        @NonNull
        public d a(Context context, h hVar) {
            return new com.scwang.smartrefresh.layout.footer.a(context);
        }
    };
    protected static com.scwang.smartrefresh.layout.a.b aH = new com.scwang.smartrefresh.layout.a.b() {
        @NonNull
        public e a(Context context, h hVar) {
            return new com.scwang.smartrefresh.layout.b.a(context);
        }
    };
    protected boolean A;
    protected boolean B;
    protected boolean C;
    protected boolean D;
    protected boolean E;
    protected boolean F;
    protected boolean G;
    protected boolean H;
    protected boolean I;
    protected boolean J;
    protected boolean K;
    protected boolean L;
    protected boolean M;
    protected boolean N;
    protected boolean O;
    protected boolean P;
    protected boolean Q;
    protected c R;
    protected com.scwang.smartrefresh.layout.e.a S;
    protected com.scwang.smartrefresh.layout.e.b T;
    protected i U;
    protected int[] V;
    protected int[] W;
    protected int a;
    protected int aA;
    protected int aB;
    protected boolean aC;
    protected boolean aD;
    protected boolean aE;
    MotionEvent aI;
    protected ValueAnimator aJ;
    protected AnimatorListener aK;
    protected AnimatorUpdateListener aL;
    protected int aa;
    protected boolean ab;
    protected NestedScrollingChildHelper ac;
    protected NestedScrollingParentHelper ad;
    protected int ae;
    protected DimensionStatus af;
    protected int ag;
    protected DimensionStatus ah;
    protected int ai;
    protected int aj;
    protected float ak;
    protected float al;
    protected float am;
    protected float an;
    protected e ao;
    protected d ap;
    protected com.scwang.smartrefresh.layout.a.c aq;
    protected Paint ar;
    protected Handler as;
    protected g at;
    protected List<com.scwang.smartrefresh.layout.f.a> au;
    protected RefreshState av;
    protected RefreshState aw;
    protected boolean ax;
    protected long ay;
    protected long az;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected float h;
    protected float i;
    protected float j;
    protected float k;
    protected float l;
    protected boolean m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    protected Interpolator q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected Scroller v;
    protected VelocityTracker w;
    protected int[] x;
    protected boolean y;
    protected boolean z;

    public static class a extends MarginLayoutParams {
        public int a = 0;
        public SpinnerStyle b = null;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_Layout);
            this.a = obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_Layout_layout_srlBackgroundColor, this.a);
            if (obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_Layout_layout_srlSpinnerStyle)) {
                this.b = SpinnerStyle.values()[obtainStyledAttributes.getInt(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_Layout_layout_srlSpinnerStyle, SpinnerStyle.Translate.ordinal())];
            }
            obtainStyledAttributes.recycle();
        }

        public a(int i, int i2) {
            super(i, i2);
        }

        public a(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public static class b implements g {
        SmartRefreshLayout a;

        public b(SmartRefreshLayout smartRefreshLayout) {
            this.a = smartRefreshLayout;
        }

        @NonNull
        public h a() {
            return this.a;
        }

        public void b() {
            if (this.a.av == RefreshState.TwoLevel) {
                this.a.a(RefreshState.TwoLevelFinish);
                if (this.a.b == 0) {
                    a(0, true);
                    this.a.a(RefreshState.None);
                    return;
                }
                this.a.b(0).setDuration((long) this.a.e);
            }
        }

        public g a(int i, boolean z) {
            this.a.a(i, z);
            return this;
        }

        public g a(int i) {
            if (this.a.ar == null && i != 0) {
                this.a.ar = new Paint();
            }
            this.a.aA = i;
            return this;
        }

        public g b(int i) {
            if (this.a.ar == null && i != 0) {
                this.a.ar = new Paint();
            }
            this.a.aB = i;
            return this;
        }
    }

    public /* synthetic */ h b(com.scwang.smartrefresh.layout.e.a aVar) {
        return a(aVar);
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return l();
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    protected /* synthetic */ LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public /* synthetic */ h h(int i) {
        return e(i);
    }

    public /* synthetic */ h h(boolean z) {
        return d(z);
    }

    public /* synthetic */ h i(int i) {
        return d(i);
    }

    public /* synthetic */ h i(boolean z) {
        return c(z);
    }

    public /* synthetic */ h j(boolean z) {
        return b(z);
    }

    public /* synthetic */ h k(boolean z) {
        return a(z);
    }

    public /* synthetic */ h t() {
        return o();
    }

    public /* synthetic */ h u() {
        return n();
    }

    public SmartRefreshLayout(Context context) {
        super(context);
        this.e = 250;
        this.f = 250;
        this.l = 0.5f;
        this.y = true;
        this.z = false;
        this.A = true;
        this.B = true;
        this.C = false;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = false;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.V = new int[2];
        this.W = new int[2];
        this.af = DimensionStatus.DefaultUnNotify;
        this.ah = DimensionStatus.DefaultUnNotify;
        this.ak = 2.5f;
        this.al = 2.5f;
        this.am = 1.0f;
        this.an = 1.0f;
        this.av = RefreshState.None;
        this.aw = RefreshState.None;
        this.ax = false;
        this.ay = 0;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.aE = false;
        this.aI = null;
        this.aK = new AnimatorListenerAdapter(this) {
            final /* synthetic */ SmartRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                this.a.aJ = null;
                if (this.a.b == 0) {
                    if (this.a.av != RefreshState.None && !this.a.av.opening) {
                        this.a.a(RefreshState.None);
                    }
                } else if (this.a.av != this.a.aw) {
                    this.a.setViceState(this.a.av);
                }
            }
        };
        this.aL = new AnimatorUpdateListener(this) {
            final /* synthetic */ SmartRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
            }
        };
        a(context, null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 250;
        this.f = 250;
        this.l = 0.5f;
        this.y = true;
        this.z = false;
        this.A = true;
        this.B = true;
        this.C = false;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = false;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.V = new int[2];
        this.W = new int[2];
        this.af = DimensionStatus.DefaultUnNotify;
        this.ah = DimensionStatus.DefaultUnNotify;
        this.ak = 2.5f;
        this.al = 2.5f;
        this.am = 1.0f;
        this.an = 1.0f;
        this.av = RefreshState.None;
        this.aw = RefreshState.None;
        this.ax = false;
        this.ay = 0;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.aE = false;
        this.aI = null;
        this.aK = /* anonymous class already generated */;
        this.aL = /* anonymous class already generated */;
        a(context, attributeSet);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 250;
        this.f = 250;
        this.l = 0.5f;
        this.y = true;
        this.z = false;
        this.A = true;
        this.B = true;
        this.C = false;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = false;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.V = new int[2];
        this.W = new int[2];
        this.af = DimensionStatus.DefaultUnNotify;
        this.ah = DimensionStatus.DefaultUnNotify;
        this.ak = 2.5f;
        this.al = 2.5f;
        this.am = 1.0f;
        this.an = 1.0f;
        this.av = RefreshState.None;
        this.aw = RefreshState.None;
        this.ax = false;
        this.ay = 0;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.aE = false;
        this.aI = null;
        this.aK = /* anonymous class already generated */;
        this.aL = /* anonymous class already generated */;
        a(context, attributeSet);
    }

    @RequiresApi(21)
    public SmartRefreshLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = 250;
        this.f = 250;
        this.l = 0.5f;
        this.y = true;
        this.z = false;
        this.A = true;
        this.B = true;
        this.C = false;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = false;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.V = new int[2];
        this.W = new int[2];
        this.af = DimensionStatus.DefaultUnNotify;
        this.ah = DimensionStatus.DefaultUnNotify;
        this.ak = 2.5f;
        this.al = 2.5f;
        this.am = 1.0f;
        this.an = 1.0f;
        this.av = RefreshState.None;
        this.aw = RefreshState.None;
        this.ax = false;
        this.ay = 0;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.aE = false;
        this.aI = null;
        this.aK = /* anonymous class already generated */;
        this.aL = /* anonymous class already generated */;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        setClipToPadding(false);
        com.scwang.smartrefresh.layout.f.b bVar = new com.scwang.smartrefresh.layout.f.b();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.v = new Scroller(context);
        this.at = new b(this);
        this.w = VelocityTracker.obtain();
        this.g = context.getResources().getDisplayMetrics().heightPixels;
        this.q = new com.scwang.smartrefresh.layout.f.d();
        this.a = viewConfiguration.getScaledTouchSlop();
        this.t = viewConfiguration.getScaledMinimumFlingVelocity();
        this.u = viewConfiguration.getScaledMaximumFlingVelocity();
        this.ad = new NestedScrollingParentHelper(this);
        this.ac = new NestedScrollingChildHelper(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout);
        ViewCompat.setNestedScrollingEnabled(this, obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableNestedScrolling, false));
        this.l = obtainStyledAttributes.getFloat(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlDragRate, this.l);
        this.ak = obtainStyledAttributes.getFloat(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlHeaderMaxDragRate, this.ak);
        this.al = obtainStyledAttributes.getFloat(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlFooterMaxDragRate, this.al);
        this.am = obtainStyledAttributes.getFloat(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlHeaderTriggerRate, this.am);
        this.an = obtainStyledAttributes.getFloat(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlFooterTriggerRate, this.an);
        this.y = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableRefresh, this.y);
        this.f = obtainStyledAttributes.getInt(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlReboundDuration, this.f);
        this.z = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableLoadmore, this.z);
        this.ae = obtainStyledAttributes.getDimensionPixelOffset(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlHeaderHeight, bVar.c(100.0f));
        this.ag = obtainStyledAttributes.getDimensionPixelOffset(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlFooterHeight, bVar.c(60.0f));
        this.L = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlDisableContentWhenRefresh, this.L);
        this.M = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlDisableContentWhenLoading, this.M);
        this.A = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableHeaderTranslationContent, this.A);
        this.B = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableFooterTranslationContent, this.B);
        this.D = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnablePreviewInEditMode, this.D);
        this.G = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableAutoLoadmore, this.G);
        this.E = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableOverScrollBounce, this.E);
        this.H = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnablePureScrollMode, this.H);
        this.I = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableScrollContentWhenLoaded, this.I);
        this.J = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableScrollContentWhenRefreshed, this.J);
        this.K = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableLoadmoreWhenContentNotFull, this.K);
        this.C = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableFooterFollowWhenLoadFinished, this.C);
        this.F = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableOverScrollDrag, this.F);
        this.r = obtainStyledAttributes.getResourceId(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlFixedHeaderViewId, -1);
        this.s = obtainStyledAttributes.getResourceId(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlFixedFooterViewId, -1);
        this.O = obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableLoadmore);
        this.P = obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableNestedScrolling);
        this.Q = obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlEnableHeaderTranslationContent);
        this.af = obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlHeaderHeight) ? DimensionStatus.XmlLayoutUnNotify : this.af;
        this.ah = obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlFooterHeight) ? DimensionStatus.XmlLayoutUnNotify : this.ah;
        this.ai = (int) Math.max(((float) this.ae) * (this.ak - 1.0f), 0.0f);
        this.aj = (int) Math.max(((float) this.ag) * (this.al - 1.0f), 0.0f);
        int color = obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlAccentColor, 0);
        if (obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.SmartRefreshLayout_srlPrimaryColor, 0) != 0) {
            if (color != 0) {
                this.x = new int[]{r2, color};
            } else {
                this.x = new int[]{r2};
            }
        } else if (color != 0) {
            this.x = new int[]{0, color};
        }
        obtainStyledAttributes.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 3) {
            throw new RuntimeException("最多只支持3个子View，Most only support three sub view");
        }
        boolean[] zArr = new boolean[childCount];
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((childAt instanceof e) && this.ao == null) {
                this.ao = (e) childAt;
            } else if ((childAt instanceof d) && this.ap == null) {
                boolean z;
                if (this.z || !this.O) {
                    z = true;
                } else {
                    z = false;
                }
                this.z = z;
                this.ap = (d) childAt;
            } else if (this.aq == null && ((childAt instanceof AbsListView) || (childAt instanceof WebView) || (childAt instanceof ScrollView) || (childAt instanceof ScrollingView) || (childAt instanceof NestedScrollingChild) || (childAt instanceof NestedScrollingParent) || (childAt instanceof ViewPager))) {
                this.aq = new com.scwang.smartrefresh.layout.c.a(childAt);
            } else {
                zArr[i] = true;
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            if (zArr[i2]) {
                View childAt2 = getChildAt(i2);
                if (childCount == 1 && this.aq == null) {
                    this.aq = new com.scwang.smartrefresh.layout.c.a(childAt2);
                } else if (i2 == 0 && this.ao == null) {
                    this.ao = new com.scwang.smartrefresh.layout.c.c(childAt2);
                } else if (childCount == 2 && this.aq == null) {
                    this.aq = new com.scwang.smartrefresh.layout.c.a(childAt2);
                } else if (i2 == 2 && this.ap == null) {
                    if (this.z || !this.O) {
                        r0 = true;
                    } else {
                        r0 = false;
                    }
                    this.z = r0;
                    this.ap = new com.scwang.smartrefresh.layout.c.b(childAt2);
                } else if (this.aq == null) {
                    this.aq = new com.scwang.smartrefresh.layout.c.a(childAt2);
                } else if (i2 == 1 && childCount == 2 && this.ap == null) {
                    if (this.z || !this.O) {
                        r0 = true;
                    } else {
                        r0 = false;
                    }
                    this.z = r0;
                    this.ap = new com.scwang.smartrefresh.layout.c.b(childAt2);
                }
            }
        }
        if (isInEditMode()) {
            if (this.x != null) {
                if (this.ao != null) {
                    this.ao.setPrimaryColors(this.x);
                }
                if (this.ap != null) {
                    this.ap.setPrimaryColors(this.x);
                }
            }
            if (this.aq != null) {
                bringChildToFront(this.aq.e());
            }
            if (!(this.ao == null || this.ao.getSpinnerStyle() == SpinnerStyle.FixedBehind)) {
                bringChildToFront(this.ao.getView());
            }
            if (this.ap != null && this.ap.getSpinnerStyle() != SpinnerStyle.FixedBehind) {
                bringChildToFront(this.ap.getView());
            }
        }
    }

    protected void onAttachedToWindow() {
        View view = null;
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (this.as == null) {
                this.as = new Handler();
            }
            if (this.au != null) {
                for (com.scwang.smartrefresh.layout.f.a aVar : this.au) {
                    this.as.postDelayed(aVar, aVar.a);
                }
                this.au.clear();
                this.au = null;
            }
            if (this.ao == null) {
                this.ao = aH.a(getContext(), this);
                if (!(this.ao.getView().getLayoutParams() instanceof MarginLayoutParams)) {
                    if (this.ao.getSpinnerStyle() == SpinnerStyle.Scale) {
                        addView(this.ao.getView(), -1, -1);
                    } else {
                        addView(this.ao.getView(), -1, -2);
                    }
                }
            }
            if (this.ap == null) {
                boolean z;
                this.ap = aG.a(getContext(), this);
                if (this.z || (!this.O && aF)) {
                    z = true;
                } else {
                    z = false;
                }
                this.z = z;
                if (!(this.ap.getView().getLayoutParams() instanceof MarginLayoutParams)) {
                    if (this.ap.getSpinnerStyle() == SpinnerStyle.Scale) {
                        addView(this.ap.getView(), -1, -1);
                    } else {
                        addView(this.ap.getView(), -1, -2);
                    }
                }
            }
            int childCount = getChildCount();
            int i = 0;
            while (this.aq == null && i < childCount) {
                View childAt = getChildAt(i);
                if ((this.ao == null || childAt != this.ao.getView()) && (this.ap == null || childAt != this.ap.getView())) {
                    this.aq = new com.scwang.smartrefresh.layout.c.a(childAt);
                }
                i++;
            }
            if (this.aq == null) {
                this.aq = new com.scwang.smartrefresh.layout.c.a(getContext());
            }
            View findViewById = this.r > 0 ? findViewById(this.r) : null;
            if (this.s > 0) {
                view = findViewById(this.s);
            }
            this.aq.a(this.U);
            this.aq.a(this.K);
            this.aq.a(this.at, findViewById, view);
            if (this.b != 0) {
                a(RefreshState.None);
                com.scwang.smartrefresh.layout.a.c cVar = this.aq;
                this.b = 0;
                cVar.a(0);
            }
            bringChildToFront(this.aq.e());
            if (this.ao.getSpinnerStyle() != SpinnerStyle.FixedBehind) {
                bringChildToFront(this.ao.getView());
            }
            if (this.ap.getSpinnerStyle() != SpinnerStyle.FixedBehind) {
                bringChildToFront(this.ap.getView());
            }
            if (this.R == null) {
                this.R = new c(this) {
                    final /* synthetic */ SmartRefreshLayout a;

                    {
                        this.a = r1;
                    }

                    public void a_(h hVar) {
                        hVar.i(3000);
                    }
                };
            }
            if (this.S == null) {
                this.S = new com.scwang.smartrefresh.layout.e.a(this) {
                    final /* synthetic */ SmartRefreshLayout a;

                    {
                        this.a = r1;
                    }

                    public void a(h hVar) {
                        hVar.h(2000);
                    }
                };
            }
            if (this.x != null) {
                this.ao.setPrimaryColors(this.x);
                this.ap.setPrimaryColors(this.x);
            }
            if (!this.P && !isNestedScrollingEnabled()) {
                for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                    if (parent instanceof NestedScrollingParent) {
                        setNestedScrollingEnabled(true);
                        this.P = false;
                        return;
                    }
                }
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        Object obj;
        int i3 = 0;
        if (isInEditMode() && this.D) {
            obj = 1;
        } else {
            obj = null;
        }
        int childCount = getChildCount();
        int i4 = 0;
        while (i4 < childCount) {
            View view;
            a aVar;
            int childMeasureSpec;
            int i5;
            int childMeasureSpec2;
            int paddingTop;
            View childAt = getChildAt(i4);
            if (this.ao != null && this.ao.getView() == childAt) {
                view = this.ao.getView();
                aVar = (a) view.getLayoutParams();
                childMeasureSpec = getChildMeasureSpec(i, aVar.leftMargin + aVar.rightMargin, aVar.width);
                if (this.af.gteReplaceWith(DimensionStatus.XmlLayoutUnNotify)) {
                    view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ae - aVar.bottomMargin, 0), 1073741824));
                } else if (this.ao.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                    i5 = 0;
                    if (!this.af.notifyed) {
                        measureChild(view, childMeasureSpec, i2);
                        i5 = view.getMeasuredHeight();
                    }
                    view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), 1073741824));
                    if (i5 > 0 && i5 != view.getMeasuredHeight()) {
                        this.ae = i5 + aVar.bottomMargin;
                    }
                } else if (aVar.height > 0) {
                    if (this.af.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                        this.ae = aVar.height + aVar.bottomMargin;
                        this.af = DimensionStatus.XmlExactUnNotify;
                    }
                    view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(aVar.height, 1073741824));
                } else if (aVar.height == -2) {
                    view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(MeasureSpec.getSize(i2) - aVar.bottomMargin, 0), Integer.MIN_VALUE));
                    i5 = view.getMeasuredHeight();
                    if (i5 > 0 && this.af.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                        this.af = DimensionStatus.XmlWrapUnNotify;
                        this.ae = view.getMeasuredHeight() + aVar.bottomMargin;
                    } else if (i5 <= 0) {
                        view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ae - aVar.bottomMargin, 0), 1073741824));
                    }
                } else if (aVar.height == -1) {
                    view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ae - aVar.bottomMargin, 0), 1073741824));
                } else {
                    view.measure(childMeasureSpec, i2);
                }
                if (this.ao.getSpinnerStyle() == SpinnerStyle.Scale && obj == null) {
                    view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(Math.max(0, s() ? this.b : 0) - aVar.bottomMargin, 0), 1073741824));
                }
                if (!this.af.notifyed) {
                    this.af = this.af.notifyed();
                    this.ai = (int) Math.max(((float) this.ae) * (this.ak - 1.0f), 0.0f);
                    this.ao.a(this.at, this.ae, this.ai);
                }
                if (obj != null && s()) {
                    i5 = view.getMeasuredHeight() + i3;
                    if (this.ap != null && this.ap.getView() == childAt) {
                        view = this.ap.getView();
                        aVar = (a) view.getLayoutParams();
                        childMeasureSpec = getChildMeasureSpec(i, aVar.leftMargin + aVar.rightMargin, aVar.width);
                        if (this.ah.gteReplaceWith(DimensionStatus.XmlLayoutUnNotify)) {
                            view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ag - aVar.topMargin, 0), 1073741824));
                        } else if (this.ap.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                            i3 = 0;
                            if (!this.ah.notifyed) {
                                measureChild(view, childMeasureSpec, i2);
                                i3 = view.getMeasuredHeight();
                            }
                            view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), 1073741824));
                            if (i3 > 0 && i3 != view.getMeasuredHeight()) {
                                this.ae = i3 + aVar.bottomMargin;
                            }
                        } else if (aVar.height > 0) {
                            if (this.ah.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.ag = aVar.height + aVar.topMargin;
                                this.ah = DimensionStatus.XmlExactUnNotify;
                            }
                            view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(aVar.height, 1073741824));
                        } else if (aVar.height == -2) {
                            view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(MeasureSpec.getSize(i2) - aVar.topMargin, 0), Integer.MIN_VALUE));
                            i3 = view.getMeasuredHeight();
                            if (i3 <= 0 && this.ah.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                this.ah = DimensionStatus.XmlWrapUnNotify;
                                this.ag = view.getMeasuredHeight() + aVar.topMargin;
                            } else if (i3 <= 0) {
                                view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ag - aVar.topMargin, 0), 1073741824));
                            }
                        } else if (aVar.height != -1) {
                            view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ag - aVar.topMargin, 0), 1073741824));
                        } else {
                            view.measure(childMeasureSpec, i2);
                        }
                        if (this.ap.getSpinnerStyle() == SpinnerStyle.Scale && obj == null) {
                            view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(Math.max(0, this.z ? -this.b : 0) - aVar.topMargin, 0), 1073741824));
                        }
                        if (!this.ah.notifyed) {
                            this.ah = this.ah.notifyed();
                            this.aj = (int) Math.max(((float) this.ag) * (this.al - 1.0f), 0.0f);
                            this.ap.a(this.at, this.ag, this.aj);
                        }
                        if (obj != null && this.z) {
                            i5 += view.getMeasuredHeight();
                        }
                    }
                    if (this.aq != null && this.aq.e() == childAt) {
                        aVar = (a) this.aq.g();
                        childMeasureSpec2 = getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + aVar.leftMargin) + aVar.rightMargin, aVar.width);
                        paddingTop = aVar.bottomMargin + ((getPaddingTop() + getPaddingBottom()) + aVar.topMargin);
                        i3 = (obj == null && s() && (this.A || this.ao.getSpinnerStyle() == SpinnerStyle.FixedBehind)) ? this.ae : 0;
                        paddingTop += i3;
                        i3 = (obj == null && r() && (this.B || this.ap.getSpinnerStyle() == SpinnerStyle.FixedBehind)) ? this.ag : 0;
                        this.aq.a(childMeasureSpec2, getChildMeasureSpec(i2, i3 + paddingTop, aVar.height));
                        this.aq.b(this.ae, this.ag);
                        i5 += this.aq.d();
                    }
                    i4++;
                    i3 = i5;
                }
            }
            i5 = i3;
            view = this.ap.getView();
            aVar = (a) view.getLayoutParams();
            childMeasureSpec = getChildMeasureSpec(i, aVar.leftMargin + aVar.rightMargin, aVar.width);
            if (this.ah.gteReplaceWith(DimensionStatus.XmlLayoutUnNotify)) {
                view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ag - aVar.topMargin, 0), 1073741824));
            } else if (this.ap.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                i3 = 0;
                if (this.ah.notifyed) {
                    measureChild(view, childMeasureSpec, i2);
                    i3 = view.getMeasuredHeight();
                }
                view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), 1073741824));
                this.ae = i3 + aVar.bottomMargin;
            } else if (aVar.height > 0) {
                if (this.ah.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                    this.ag = aVar.height + aVar.topMargin;
                    this.ah = DimensionStatus.XmlExactUnNotify;
                }
                view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(aVar.height, 1073741824));
            } else if (aVar.height == -2) {
                view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(MeasureSpec.getSize(i2) - aVar.topMargin, 0), Integer.MIN_VALUE));
                i3 = view.getMeasuredHeight();
                if (i3 <= 0) {
                }
                if (i3 <= 0) {
                    view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ag - aVar.topMargin, 0), 1073741824));
                }
            } else if (aVar.height != -1) {
                view.measure(childMeasureSpec, i2);
            } else {
                view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(this.ag - aVar.topMargin, 0), 1073741824));
            }
            if (this.z) {
            }
            view.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(Math.max(0, this.z ? -this.b : 0) - aVar.topMargin, 0), 1073741824));
            if (this.ah.notifyed) {
                this.ah = this.ah.notifyed();
                this.aj = (int) Math.max(((float) this.ag) * (this.al - 1.0f), 0.0f);
                this.ap.a(this.at, this.ag, this.aj);
            }
            i5 += view.getMeasuredHeight();
            aVar = (a) this.aq.g();
            childMeasureSpec2 = getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + aVar.leftMargin) + aVar.rightMargin, aVar.width);
            paddingTop = aVar.bottomMargin + ((getPaddingTop() + getPaddingBottom()) + aVar.topMargin);
            if (obj == null) {
            }
            paddingTop += i3;
            if (obj == null) {
            }
            this.aq.a(childMeasureSpec2, getChildMeasureSpec(i2, i3 + paddingTop, aVar.height));
            this.aq.b(this.ae, this.ag);
            i5 += this.aq.d();
            i4++;
            i3 = i5;
        }
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(i3, i2));
        this.j = (float) (getMeasuredWidth() / 2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            Object obj;
            int i6;
            int d;
            int i7;
            View childAt = getChildAt(i5);
            if (this.aq != null && this.aq.e() == childAt) {
                if (isInEditMode() && this.D) {
                    obj = 1;
                } else {
                    obj = null;
                }
                a aVar = (a) this.aq.g();
                int i8 = paddingLeft + aVar.leftMargin;
                i6 = paddingTop + aVar.topMargin;
                int c = i8 + this.aq.c();
                d = this.aq.d() + i6;
                if (obj != null && s() && (this.A || this.ao.getSpinnerStyle() == SpinnerStyle.FixedBehind)) {
                    i7 = this.ae + i6;
                    d += this.ae;
                } else {
                    i7 = i6;
                }
                this.aq.a(i8, i7, c, d);
            }
            if (this.ao != null && this.ao.getView() == childAt) {
                if (isInEditMode() && this.D && s()) {
                    obj = 1;
                } else {
                    obj = null;
                }
                View view = this.ao.getView();
                aVar = (a) view.getLayoutParams();
                int i9 = aVar.leftMargin;
                i8 = aVar.topMargin;
                int measuredWidth = i9 + view.getMeasuredWidth();
                i6 = view.getMeasuredHeight() + i8;
                if (obj == null) {
                    if (this.ao.getSpinnerStyle() == SpinnerStyle.Translate) {
                        i7 = i8 - this.ae;
                        d = view.getMeasuredHeight() + i7;
                    } else if (this.ao.getSpinnerStyle() == SpinnerStyle.Scale) {
                        d = Math.max(Math.max(0, s() ? this.b : 0) - aVar.bottomMargin, 0) + i8;
                        i7 = i8;
                    }
                    view.layout(i9, i7, measuredWidth, d);
                }
                d = i6;
                i7 = i8;
                view.layout(i9, i7, measuredWidth, d);
            }
            if (this.ap != null && this.ap.getView() == childAt) {
                if (isInEditMode() && this.D && r()) {
                    obj = 1;
                } else {
                    obj = null;
                }
                View view2 = this.ap.getView();
                aVar = (a) view2.getLayoutParams();
                SpinnerStyle spinnerStyle = this.ap.getSpinnerStyle();
                c = aVar.leftMargin;
                i6 = (aVar.topMargin + getMeasuredHeight()) - aVar.bottomMargin;
                if (obj != null || spinnerStyle == SpinnerStyle.FixedFront || spinnerStyle == SpinnerStyle.FixedBehind) {
                    d = i6 - this.ag;
                } else if (spinnerStyle == SpinnerStyle.Scale) {
                    d = i6 - Math.max(Math.max(r() ? -this.b : 0, 0) - aVar.topMargin, 0);
                } else {
                    d = i6;
                }
                view2.layout(c, d, view2.getMeasuredWidth() + c, view2.getMeasuredHeight() + d);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a(0, false);
        a(RefreshState.None);
        this.as.removeCallbacksAndMessages(null);
        this.as = null;
        this.O = true;
        this.P = true;
    }

    protected void dispatchDraw(Canvas canvas) {
        Object obj = (this.D && isInEditMode()) ? 1 : null;
        if (s() && this.aA != 0 && (this.b > 0 || obj != null)) {
            this.ar.setColor(this.aA);
            canvas.drawRect(0.0f, 0.0f, (float) getWidth(), obj != null ? (float) this.ae : (float) this.b, this.ar);
        } else if (r() && this.aB != 0 && (this.b < 0 || obj != null)) {
            int height = getHeight();
            this.ar.setColor(this.aB);
            canvas.drawRect(0.0f, (float) (height - (obj != null ? this.ag : -this.b)), (float) getWidth(), (float) height, this.ar);
        }
        super.dispatchDraw(canvas);
    }

    public void computeScroll() {
        int currY = this.v.getCurrY();
        if (this.v.computeScrollOffset()) {
            int finalY = this.v.getFinalY();
            if ((finalY <= 0 || !this.aq.b()) && (finalY >= 0 || !this.aq.a())) {
                this.ax = true;
                invalidate();
                return;
            }
            if (this.ax) {
                int currVelocity;
                if (VERSION.SDK_INT >= 14) {
                    currVelocity = (int) this.v.getCurrVelocity();
                } else {
                    currVelocity = (finalY - this.v.getCurrY()) / (this.v.getDuration() - this.v.timePassed());
                }
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - ((long) ((Math.abs(this.v.getCurrY() - currY) * 1000) / currVelocity));
                if (finalY > 0) {
                    if (r() || this.F) {
                        if (this.G && r() && !this.N) {
                            c(-((int) (Math.pow((((double) currVelocity) * 1.0d) / ((double) this.u), 0.5d) * ((double) this.ag))));
                            if (!(this.av.opening || this.av == RefreshState.Loading || this.av == RefreshState.LoadFinish)) {
                                g();
                            }
                        } else if (this.E) {
                            c(-((int) (Math.pow((((double) currVelocity) * 1.0d) / ((double) this.u), 0.5d) * ((double) this.ag))));
                        }
                    }
                } else if ((s() || this.F) && this.E) {
                    c((int) (Math.pow((((double) currVelocity) * 1.0d) / ((double) this.u), 0.5d) * ((double) this.ae)));
                }
                this.ax = false;
            }
            this.v.forceFinished(true);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        Object obj = actionMasked == 6 ? 1 : null;
        int actionIndex = obj != null ? motionEvent.getActionIndex() : -1;
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            if (actionIndex != i) {
                f2 += motionEvent.getX(i);
                f += motionEvent.getY(i);
            }
        }
        actionIndex = obj != null ? pointerCount - 1 : pointerCount;
        f2 /= (float) actionIndex;
        float f3 = f / ((float) actionIndex);
        if ((actionMasked == 6 || actionMasked == 5) && this.m) {
            this.i += f3 - this.k;
        }
        this.j = f2;
        this.k = f3;
        if (this.aq != null) {
            switch (actionMasked) {
                case 0:
                    this.w.clear();
                    this.w.addMovement(motionEvent);
                    this.aq.a(motionEvent);
                    this.v.forceFinished(true);
                    break;
                case 1:
                    this.w.computeCurrentVelocity(1000, (float) this.u);
                    break;
                case 2:
                    if ((this.G && this.z) || (this.E && (this.z || this.y))) {
                        this.w.addMovement(motionEvent);
                        break;
                    }
                case 3:
                    break;
            }
            this.aq.h();
        }
        if ((this.aJ != null && !a(actionMasked)) || this.av.finishing || ((this.av == RefreshState.Loading && this.M) || (this.av == RefreshState.Refreshing && this.L))) {
            return false;
        }
        if (this.ab) {
            int i2 = this.aa;
            boolean a = a(motionEvent);
            if (actionMasked != 2 || i2 != this.aa) {
                return a;
            }
            i2 = (int) this.j;
            int width = getWidth();
            float f4 = this.j / ((float) width);
            if (s() && this.b > 0 && this.ao != null && this.ao.a()) {
                this.ao.a(f4, i2, width);
                return a;
            } else if (!r() || this.b >= 0 || this.ap == null || !this.ap.a()) {
                return a;
            } else {
                this.ap.a(f4, i2, width);
                return a;
            }
        } else if (!isEnabled() || ((!s() && !r() && !this.F) || ((this.aC && (this.av == RefreshState.Refreshing || this.av == RefreshState.RefreshFinish)) || (this.aD && (this.av == RefreshState.Loading || this.av == RefreshState.LoadFinish))))) {
            return a(motionEvent);
        } else {
            long eventTime;
            float f5;
            switch (actionMasked) {
                case 0:
                    this.h = f2;
                    this.i = f3;
                    this.c = 0;
                    this.d = this.b;
                    this.m = false;
                    this.p = a(motionEvent);
                    if (this.av != RefreshState.TwoLevel || this.i >= ((float) ((getMeasuredHeight() * 5) / 6))) {
                        return true;
                    }
                    this.o = true;
                    return this.p;
                case 1:
                case 3:
                    this.n = false;
                    this.o = false;
                    if (this.aI != null) {
                        this.aI = null;
                        eventTime = motionEvent.getEventTime();
                        a(MotionEvent.obtain(eventTime, eventTime, actionMasked, this.h, f3, 0));
                    }
                    if (!k()) {
                        if (!(this.av == this.aw || this.b == 0 || this.av == RefreshState.TwoLevel)) {
                            f5 = -this.w.getYVelocity();
                            if (Math.abs(f5) > ((float) this.t) && ((float) this.b) * f5 > 0.0f) {
                                b(0);
                                if (this.aq != null) {
                                    this.aq.b((int) f5);
                                    if (this.aE && f5 < 0.0f) {
                                        this.aE = false;
                                    }
                                }
                            }
                        }
                        this.m = false;
                        break;
                    }
                    this.m = false;
                    return true;
                case 2:
                    float f6;
                    int i3;
                    MotionEvent obtain;
                    float f7 = f2 - this.h;
                    f5 = f3 - this.i;
                    if (!(this.m || this.o)) {
                        if (this.n || (Math.abs(f5) >= ((float) this.a) && Math.abs(f7) < Math.abs(f5))) {
                            this.n = true;
                            if (f5 > 0.0f && (this.b < 0 || ((s() || this.F) && this.aq.a()))) {
                                this.m = true;
                                this.i = f3 - ((float) this.a);
                            } else if (f5 < 0.0f && (this.b > 0 || ((r() || this.F) && ((this.av == RefreshState.Loading && this.aE) || this.aq.b())))) {
                                this.m = true;
                                this.i = ((float) this.a) + f3;
                            }
                            if (this.m) {
                                f5 = f3 - this.i;
                                if (this.p) {
                                    motionEvent.setAction(3);
                                    a(motionEvent);
                                }
                                if (this.b > 0 || (this.b == 0 && f5 > 0.0f)) {
                                    f();
                                } else {
                                    a();
                                }
                                getParent().requestDisallowInterceptTouchEvent(true);
                                f6 = f5;
                                if (this.m) {
                                    if (this.aE && f6 > ((float) this.a) && this.b < 0) {
                                        this.aE = false;
                                        break;
                                    }
                                }
                                i3 = ((int) f6) + this.d;
                                if ((getViceState().isHeader() && (i3 < 0 || this.c < 0)) || (getViceState().isFooter() && (i3 > 0 || this.c > 0))) {
                                    this.c = i3;
                                    eventTime = motionEvent.getEventTime();
                                    if (this.aI == null) {
                                        this.aI = MotionEvent.obtain(eventTime, eventTime, 0, this.h + f7, this.i, 0);
                                        a(this.aI);
                                    }
                                    obtain = MotionEvent.obtain(eventTime, eventTime, 2, this.h + f7, this.i + ((float) i3), 0);
                                    if (this.aI != null) {
                                        a(obtain);
                                        if (this.aE && f6 > ((float) this.a) && this.b < 0) {
                                            this.aE = false;
                                        }
                                    }
                                    if (i3 <= 0 && ((s() || this.F) && this.aq.a())) {
                                        this.k = f3;
                                        this.i = f3;
                                        actionIndex = 0;
                                        this.d = 0;
                                        f();
                                    } else if (i3 < 0 || !((r() || this.F) && this.aq.b())) {
                                        actionIndex = i3;
                                    } else {
                                        this.k = f3;
                                        this.i = f3;
                                        actionIndex = 0;
                                        this.d = 0;
                                        a();
                                    }
                                    if ((getViceState().isHeader() || actionIndex >= 0) && (!getViceState().isFooter() || actionIndex <= 0)) {
                                        if (this.aI != null) {
                                            this.aI = null;
                                            obtain.setAction(3);
                                            a(obtain);
                                        }
                                        i3 = actionIndex;
                                    } else {
                                        if (this.b != 0) {
                                            a(0.0f);
                                        }
                                        return true;
                                    }
                                }
                                a((float) i3);
                                return true;
                            }
                        } else if (Math.abs(f7) >= ((float) this.a) && Math.abs(f7) > Math.abs(f5) && !this.n) {
                            this.o = true;
                        }
                    }
                    f6 = f5;
                    if (this.m) {
                        this.aE = false;
                        break;
                    }
                    i3 = ((int) f6) + this.d;
                    this.c = i3;
                    eventTime = motionEvent.getEventTime();
                    if (this.aI == null) {
                        this.aI = MotionEvent.obtain(eventTime, eventTime, 0, this.h + f7, this.i, 0);
                        a(this.aI);
                    }
                    obtain = MotionEvent.obtain(eventTime, eventTime, 2, this.h + f7, this.i + ((float) i3), 0);
                    if (this.aI != null) {
                        a(obtain);
                        this.aE = false;
                        break;
                    }
                    if (i3 <= 0) {
                        break;
                    }
                    if (i3 < 0) {
                        break;
                    }
                    actionIndex = i3;
                    if (getViceState().isHeader()) {
                    }
                    if (this.aI != null) {
                        this.aI = null;
                        obtain.setAction(3);
                        a(obtain);
                    }
                    i3 = actionIndex;
                    a((float) i3);
                    return true;
                    break;
            }
            return a(motionEvent);
        }
    }

    protected boolean a(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 1) {
            float f = -this.w.getYVelocity();
            if (Math.abs(f) > ((float) this.t) && this.b == 0 && this.d == 0) {
                this.ax = false;
                this.v.fling(0, getScrollY(), 0, (int) f, 0, 0, -2147483647, Integer.MAX_VALUE);
                this.v.computeScrollOffset();
                invalidate();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    protected boolean a(int i) {
        if (this.aJ == null || i != 0 || this.av == RefreshState.LoadFinish || this.av == RefreshState.RefreshFinish) {
            return false;
        }
        if (this.av == RefreshState.PullDownCanceled) {
            f();
        } else if (this.av == RefreshState.PullUpCanceled) {
            a();
        }
        this.aJ.cancel();
        this.aJ = null;
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        View f = this.aq.f();
        if (VERSION.SDK_INT < 21 && (f instanceof AbsListView)) {
            return;
        }
        if (f == null || ViewCompat.isNestedScrollingEnabled(f)) {
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    protected void a(RefreshState refreshState) {
        RefreshState refreshState2 = this.av;
        if (refreshState2 != refreshState) {
            this.av = refreshState;
            this.aw = refreshState;
            if (this.ap != null) {
                this.ap.a(this, refreshState2, refreshState);
            }
            if (this.ao != null) {
                this.ao.a(this, refreshState2, refreshState);
            }
            if (this.T != null) {
                this.T.a(this, refreshState2, refreshState);
            }
        }
    }

    protected void a() {
        if (!r() || this.N || this.av.opening || this.av.finishing) {
            setViceState(RefreshState.PullToUpLoad);
        } else {
            a(RefreshState.PullToUpLoad);
        }
    }

    protected void b() {
        if (!r() || this.N || this.av.opening || this.av.finishing) {
            setViceState(RefreshState.ReleaseToLoad);
        } else {
            a(RefreshState.ReleaseToLoad);
        }
    }

    protected void c() {
        if (!r() || this.N || this.av.opening) {
            setViceState(RefreshState.PullUpCanceled);
            return;
        }
        a(RefreshState.PullUpCanceled);
        j();
    }

    protected void d() {
        if (this.av.opening || !s()) {
            setViceState(RefreshState.PullDownCanceled);
            return;
        }
        a(RefreshState.PullDownCanceled);
        j();
    }

    protected void e() {
        if (this.av.opening || !s()) {
            setViceState(RefreshState.ReleaseToRefresh);
        } else {
            a(RefreshState.ReleaseToRefresh);
        }
    }

    protected void f() {
        if (this.av.opening || !s()) {
            setViceState(RefreshState.PullDownToRefresh);
        } else {
            a(RefreshState.PullDownToRefresh);
        }
    }

    protected void g() {
        if (this.av != RefreshState.Loading) {
            this.ay = System.currentTimeMillis();
            if (this.av != RefreshState.LoadReleased) {
                if (this.av != RefreshState.ReleaseToLoad) {
                    if (this.av != RefreshState.PullToUpLoad) {
                        a();
                    }
                    b();
                }
                a(RefreshState.LoadReleased);
                if (this.ap != null) {
                    this.ap.a(this, this.ag, this.aj);
                }
            }
            a(RefreshState.Loading);
            this.aE = true;
            if (this.ap != null) {
                this.ap.b(this, this.ag, this.aj);
            }
            if (this.S != null) {
                this.S.a(this);
            }
            if (this.T != null) {
                this.T.a(this);
                this.T.b(this.ap, this.ag, this.aj);
            }
        }
    }

    protected void h() {
        Object anonymousClass9 = new AnimatorListenerAdapter(this) {
            final /* synthetic */ SmartRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                this.a.g();
            }
        };
        a(RefreshState.LoadReleased);
        ValueAnimator b = b(-this.ag);
        if (b != null) {
            b.addListener(anonymousClass9);
        }
        if (this.ap != null) {
            this.ap.a(this, this.ag, this.aj);
        }
        if (this.T != null) {
            this.T.a(this.ap, this.ag, this.aj);
        }
        if (b == null) {
            anonymousClass9.onAnimationEnd(null);
        }
    }

    protected void i() {
        Object anonymousClass10 = new AnimatorListenerAdapter(this) {
            final /* synthetic */ SmartRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                this.a.az = System.currentTimeMillis();
                this.a.a(RefreshState.Refreshing);
                if (this.a.R != null) {
                    this.a.R.a_(this.a);
                }
                if (this.a.ao != null) {
                    this.a.ao.b(this.a, this.a.ae, this.a.ai);
                }
                if (this.a.T != null) {
                    this.a.T.a_(this.a);
                    this.a.T.b(this.a.ao, this.a.ae, this.a.ai);
                }
            }
        };
        a(RefreshState.RefreshReleased);
        ValueAnimator b = b(this.ae);
        if (b != null) {
            b.addListener(anonymousClass10);
        }
        if (this.ao != null) {
            this.ao.a(this, this.ae, this.ai);
        }
        if (this.T != null) {
            this.T.a(this.ao, this.ae, this.ai);
        }
        if (b == null) {
            anonymousClass10.onAnimationEnd(null);
        }
    }

    protected void j() {
        if (this.av != RefreshState.None && this.b == 0) {
            a(RefreshState.None);
        }
        if (this.b != 0) {
            b(0);
        }
    }

    protected RefreshState getViceState() {
        return this.aw;
    }

    protected void setViceState(RefreshState refreshState) {
        if (this.av.draging && this.av.isHeader() != refreshState.isHeader()) {
            a(RefreshState.None);
        }
        if (this.aw != refreshState) {
            this.aw = refreshState;
        }
    }

    protected ValueAnimator b(int i) {
        return a(i, 0);
    }

    protected ValueAnimator a(int i, int i2) {
        return a(i, i2, this.q);
    }

    protected ValueAnimator a(int i, int i2, Interpolator interpolator) {
        if (this.b == i) {
            return null;
        }
        if (this.aJ != null) {
            this.aJ.cancel();
        }
        this.aJ = ValueAnimator.ofInt(new int[]{this.b, i});
        this.aJ.setDuration((long) this.f);
        this.aJ.setInterpolator(interpolator);
        this.aJ.addUpdateListener(this.aL);
        this.aJ.addListener(this.aK);
        this.aJ.setStartDelay((long) i2);
        this.aJ.start();
        return this.aJ;
    }

    protected ValueAnimator c(int i) {
        if (this.aJ == null) {
            int i2 = (this.f * 2) / 3;
            this.j = (float) (getMeasuredWidth() / 2);
            if ((this.av == RefreshState.Refreshing || this.av == RefreshState.TwoLevel) && i > 0) {
                this.aJ = ValueAnimator.ofInt(new int[]{this.b, Math.min(i * 2, this.ae)});
                this.aJ.addListener(this.aK);
            } else if (i < 0 && (this.av == RefreshState.Loading || ((this.C && this.N) || (this.G && r() && !this.N && this.av != RefreshState.Refreshing)))) {
                this.aJ = ValueAnimator.ofInt(new int[]{this.b, Math.max((i * 7) / 2, -this.ag)});
                this.aJ.addListener(this.aK);
            } else if (this.b == 0 && this.E) {
                if (i > 0) {
                    if (this.av != RefreshState.Loading) {
                        f();
                    }
                    i2 = Math.max(150, (i * 250) / this.ae);
                    this.aJ = ValueAnimator.ofInt(new int[]{0, Math.min(i, this.ae)});
                } else {
                    if (this.av != RefreshState.Refreshing) {
                        a();
                    }
                    i2 = Math.max(150, ((-i) * 250) / this.ag);
                    this.aJ = ValueAnimator.ofInt(new int[]{0, Math.max(i, -this.ag)});
                }
                this.aJ.addListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ SmartRefreshLayout b;

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        this.b.aJ = ValueAnimator.ofInt(new int[]{this.b.b, 0});
                        this.b.aJ.setDuration((long) i2);
                        this.b.aJ.setInterpolator(new DecelerateInterpolator());
                        this.b.aJ.addUpdateListener(this.b.aL);
                        this.b.aJ.addListener(this.b.aK);
                        this.b.aJ.start();
                    }
                });
            }
            if (this.aJ != null) {
                this.aJ.setDuration((long) i2);
                this.aJ.setInterpolator(new DecelerateInterpolator());
                this.aJ.addUpdateListener(this.aL);
                this.aJ.start();
            }
        }
        return this.aJ;
    }

    protected boolean k() {
        if (this.av == RefreshState.TwoLevel) {
            if (this.w.getYVelocity() > -1000.0f && this.b > getMeasuredHeight() / 2) {
                ValueAnimator b = b(getMeasuredHeight());
                if (b != null) {
                    b.setDuration((long) this.e);
                }
            } else if (this.m) {
                this.at.b();
            }
            return this.m;
        }
        if (this.av == RefreshState.Loading || ((this.G && r() && !this.N && this.b < 0 && this.av != RefreshState.Refreshing) || (this.C && this.N && this.b < 0))) {
            if (this.b < (-this.ag)) {
                this.aa = -this.ag;
                b(-this.ag);
            } else if (this.b <= 0) {
                return false;
            } else {
                this.aa = 0;
                b(0);
            }
        } else if (this.av == RefreshState.Refreshing) {
            if (this.b > this.ae) {
                this.aa = this.ae;
                b(this.ae);
            } else if (this.b >= 0) {
                return false;
            } else {
                this.aa = 0;
                b(0);
            }
        } else if (this.av == RefreshState.PullDownToRefresh) {
            d();
        } else if (this.av == RefreshState.PullToUpLoad) {
            c();
        } else if (this.av == RefreshState.ReleaseToRefresh) {
            i();
        } else if (this.av == RefreshState.ReleaseToLoad) {
            h();
        } else if (this.av == RefreshState.ReleaseToTwoLevel) {
            a(RefreshState.TwoLevelReleased);
        } else if (this.b == 0) {
            return false;
        } else {
            b(0);
        }
        return true;
    }

    protected void a(float f) {
        if (this.av == RefreshState.TwoLevel && f > 0.0f) {
            a(Math.min((int) f, getMeasuredHeight()), false);
        } else if (this.av != RefreshState.Refreshing || f < 0.0f) {
            if (f >= 0.0f || !(this.av == RefreshState.Loading || ((this.C && this.N) || (this.G && r() && !this.N)))) {
                if (f >= 0.0f) {
                    r4 = (double) Math.max(0.0f, this.l * f);
                    a((int) Math.min(((double) (this.ai + this.ae)) * (1.0d - Math.pow(100.0d, (-r4) / ((double) Math.max(this.g / 2, getHeight())))), r4), false);
                } else {
                    r4 = (double) (-Math.min(0.0f, this.l * f));
                    a((int) (-Math.min(((double) (this.aj + this.ag)) * (1.0d - Math.pow(100.0d, (-r4) / ((double) Math.max(this.g / 2, getHeight())))), r4)), false);
                }
            } else if (f > ((float) (-this.ag))) {
                a((int) f, false);
            } else {
                r4 = (double) (-Math.min(0.0f, (((float) this.ag) + f) * this.l));
                a(((int) (-Math.min(((double) this.aj) * (1.0d - Math.pow(100.0d, (-r4) / ((double) (Math.max((this.g * 4) / 3, getHeight()) - this.ag)))), r4))) - this.ag, false);
            }
        } else if (f < ((float) this.ae)) {
            a((int) f, false);
        } else {
            r4 = (double) Math.max(0.0f, (f - ((float) this.ae)) * this.l);
            a(((int) Math.min(((double) this.ai) * (1.0d - Math.pow(100.0d, (-r4) / ((double) (Math.max((this.g * 4) / 3, getHeight()) - this.ae)))), r4)) + this.ae, false);
        }
        if (this.G && r() && f < 0.0f && this.av != RefreshState.Refreshing && this.av != RefreshState.Loading && this.av != RefreshState.LoadFinish && !this.N) {
            g();
        }
    }

    protected void a(int i, boolean z) {
        if (this.b != i || ((this.ao != null && this.ao.a()) || (this.ap != null && this.ap.a()))) {
            int max;
            int i2;
            int i3;
            float f;
            int i4;
            int width;
            int i5 = this.b;
            this.b = i;
            if (!z && getViceState().draging) {
                if (((float) this.b) > ((float) this.ae) * this.am) {
                    if (this.av != RefreshState.ReleaseToTwoLevel) {
                        e();
                    }
                } else if (((float) (-this.b)) > ((float) this.ag) * this.an && !this.N) {
                    b();
                } else if (this.b < 0 && !this.N) {
                    a();
                } else if (this.b > 0) {
                    f();
                }
            }
            if (this.aq != null) {
                Integer num = null;
                if (i >= 0) {
                    if (this.A || this.ao == null || this.ao.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                        num = Integer.valueOf(i);
                    } else if (i5 < 0) {
                        num = Integer.valueOf(0);
                    }
                }
                if (i <= 0) {
                    if (this.B || this.ap == null || this.ap.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                        num = Integer.valueOf(i);
                    } else if (i5 > 0) {
                        num = Integer.valueOf(0);
                    }
                }
                if (num != null) {
                    this.aq.a(num.intValue());
                    if ((this.aA != 0 && (num.intValue() >= 0 || i5 > 0)) || (this.aB != 0 && (num.intValue() <= 0 || i5 < 0))) {
                        invalidate();
                    }
                }
            }
            if ((i >= 0 || i5 > 0) && this.ao != null) {
                max = Math.max(i, 0);
                i2 = this.ae;
                i3 = this.ai;
                f = (((float) max) * 1.0f) / ((float) this.ae);
                if (s() || (this.av == RefreshState.RefreshFinish && z)) {
                    if (i5 != this.b) {
                        if (this.ao.getSpinnerStyle() == SpinnerStyle.Translate) {
                            this.ao.getView().setTranslationY((float) this.b);
                        } else if (this.ao.getSpinnerStyle() == SpinnerStyle.Scale) {
                            this.ao.getView().requestLayout();
                        }
                        if (z) {
                            this.ao.b(f, max, i2, i3);
                        }
                    }
                    if (!z) {
                        if (this.ao.a()) {
                            i4 = (int) this.j;
                            width = getWidth();
                            this.ao.a(this.j / ((float) width), i4, width);
                            this.ao.a(f, max, i2, i3);
                        } else if (i5 != this.b) {
                            this.ao.a(f, max, i2, i3);
                        }
                    }
                }
                if (!(i5 == this.b || this.T == null)) {
                    if (z) {
                        this.T.b(this.ao, f, max, i2, i3);
                    } else {
                        this.T.a(this.ao, f, max, i2, i3);
                    }
                }
            }
            if ((i <= 0 || i5 < 0) && this.ap != null) {
                max = -Math.min(i, 0);
                i2 = this.ag;
                i3 = this.aj;
                f = (((float) max) * 1.0f) / ((float) this.ag);
                if (r() || (this.av == RefreshState.LoadFinish && z)) {
                    if (i5 != this.b) {
                        if (this.ap.getSpinnerStyle() == SpinnerStyle.Translate) {
                            this.ap.getView().setTranslationY((float) this.b);
                        } else if (this.ap.getSpinnerStyle() == SpinnerStyle.Scale) {
                            this.ap.getView().requestLayout();
                        }
                        if (z) {
                            this.ap.b(f, max, i2, i3);
                        }
                    }
                    if (!z) {
                        if (this.ap.a()) {
                            i4 = (int) this.j;
                            width = getWidth();
                            this.ap.a(this.j / ((float) width), i4, width);
                            this.ap.a(f, max, i2, i3);
                        } else if (i5 != this.b) {
                            this.ap.a(f, max, i2, i3);
                        }
                    }
                }
                if (i5 != this.b && this.T != null) {
                    if (z) {
                        this.T.b(this.ap, f, max, i2, i3);
                    } else {
                        this.T.a(this.ap, f, max, i2, i3);
                    }
                }
            }
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    protected a l() {
        return new a(-1, -1);
    }

    protected a a(LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    public a a(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        boolean z;
        if (isEnabled() && isNestedScrollingEnabled() && (i & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        return z && (s() || r());
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.ad.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(i & 2);
        this.aa = 0;
        this.d = this.b;
        this.ab = true;
    }

    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        int[] iArr2;
        if (this.av.opening) {
            iArr2 = this.V;
            if (dispatchNestedPreScroll(i, i2, iArr2, null)) {
                i2 -= iArr2[1];
            }
            int i3;
            if ((this.av == RefreshState.Refreshing || this.av == RefreshState.TwoLevel) && (this.aa * i2 > 0 || this.d > 0)) {
                iArr[1] = 0;
                if (Math.abs(i2) > Math.abs(this.aa)) {
                    iArr[1] = iArr[1] + this.aa;
                    this.aa = 0;
                    i3 = i2 - this.aa;
                    if (this.d <= 0) {
                        a(0.0f);
                    }
                } else {
                    this.aa -= i2;
                    iArr[1] = iArr[1] + i2;
                    a((float) (this.aa + this.d));
                    i3 = 0;
                }
                if (i3 > 0 && this.d > 0) {
                    if (i3 > this.d) {
                        iArr[1] = iArr[1] + this.d;
                        this.d = 0;
                    } else {
                        this.d -= i3;
                        iArr[1] = i3 + iArr[1];
                    }
                    a((float) this.d);
                    return;
                }
                return;
            } else if (this.av != RefreshState.Loading) {
                return;
            } else {
                if (this.aa * i2 > 0 || this.d < 0) {
                    iArr[1] = 0;
                    if (Math.abs(i2) > Math.abs(this.aa)) {
                        iArr[1] = iArr[1] + this.aa;
                        this.aa = 0;
                        i3 = i2 - this.aa;
                        if (this.d >= 0) {
                            a(0.0f);
                        }
                    } else {
                        this.aa -= i2;
                        iArr[1] = iArr[1] + i2;
                        a((float) (this.aa + this.d));
                        i3 = 0;
                    }
                    if (i3 < 0 && this.d < 0) {
                        if (i3 < this.d) {
                            iArr[1] = iArr[1] + this.d;
                            this.d = 0;
                        } else {
                            this.d -= i3;
                            iArr[1] = i3 + iArr[1];
                        }
                        a((float) this.d);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        if (s() && i2 > 0 && this.aa > 0) {
            if (i2 > this.aa) {
                iArr[1] = i2 - this.aa;
                this.aa = 0;
            } else {
                this.aa -= i2;
                iArr[1] = i2;
            }
            a((float) this.aa);
        } else if (r() && i2 < 0 && this.aa < 0) {
            if (i2 < this.aa) {
                iArr[1] = i2 - this.aa;
                this.aa = 0;
            } else {
                this.aa -= i2;
                iArr[1] = i2;
            }
            a((float) this.aa);
        }
        iArr2 = this.V;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr2[1] + iArr[1];
        }
    }

    public int getNestedScrollAxes() {
        return this.ad.getNestedScrollAxes();
    }

    public void onStopNestedScroll(@NonNull View view) {
        this.ad.onStopNestedScroll(view);
        this.ab = false;
        this.aa = 0;
        k();
        stopNestedScroll();
    }

    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(i, i2, i3, i4, this.W);
        int i5 = this.W[1] + i4;
        if (this.av.opening) {
            if (s() && i5 < 0 && (this.aq == null || this.aq.a())) {
                this.aa = Math.abs(i5) + this.aa;
                a((float) (this.aa + this.d));
            } else if (r() && i5 > 0) {
                if (this.aq == null || this.aq.b()) {
                    this.aa -= Math.abs(i5);
                    a((float) (this.aa + this.d));
                }
            }
        } else if (s() && i5 < 0 && (this.aq == null || this.aq.a())) {
            if (this.av == RefreshState.None) {
                f();
            }
            this.aa = Math.abs(i5) + this.aa;
            a((float) this.aa);
        } else if (r() && i5 > 0) {
            if (this.aq == null || this.aq.b()) {
                if (this.av == RefreshState.None && !this.N) {
                    a();
                }
                this.aa -= Math.abs(i5);
                a((float) this.aa);
            }
        }
    }

    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        if (this.b != 0 && this.av.opening) {
            b(0);
        }
        if (this.aJ != null || this.av == RefreshState.ReleaseToRefresh || this.av == RefreshState.ReleaseToLoad || ((this.av == RefreshState.PullDownToRefresh && this.b > 0) || ((this.av == RefreshState.PullToUpLoad && this.b > 0) || dispatchNestedPreFling(f, f2)))) {
            return true;
        }
        return false;
    }

    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.P = true;
        this.ac.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.ac.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return this.ac.startNestedScroll(i);
    }

    public void stopNestedScroll() {
        this.ac.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return this.ac.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.ac.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.ac.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.ac.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.ac.dispatchNestedPreFling(f, f2);
    }

    public SmartRefreshLayout b(float f) {
        this.al = f;
        this.aj = (int) Math.max(((float) this.ag) * (this.al - 1.0f), 0.0f);
        if (this.ap == null || this.as == null) {
            this.ah = this.ah.unNotify();
        } else {
            this.ap.a(this.at, this.ag, this.aj);
        }
        return this;
    }

    public SmartRefreshLayout a(boolean z) {
        this.O = true;
        this.z = z;
        return this;
    }

    public SmartRefreshLayout b(boolean z) {
        this.y = z;
        return this;
    }

    public SmartRefreshLayout c(boolean z) {
        this.A = z;
        this.Q = true;
        return this;
    }

    public SmartRefreshLayout d(boolean z) {
        this.B = z;
        return this;
    }

    public SmartRefreshLayout e(boolean z) {
        this.F = z;
        return this;
    }

    public h f(boolean z) {
        setNestedScrollingEnabled(z);
        return this;
    }

    @Nullable
    public d getRefreshFooter() {
        return this.ap;
    }

    @Nullable
    public e getRefreshHeader() {
        return this.ao;
    }

    public RefreshState getState() {
        return this.av;
    }

    public SmartRefreshLayout getLayout() {
        return this;
    }

    public SmartRefreshLayout a(c cVar) {
        this.R = cVar;
        return this;
    }

    public SmartRefreshLayout a(com.scwang.smartrefresh.layout.e.a aVar) {
        this.S = aVar;
        boolean z = this.z || !(this.O || aVar == null);
        this.z = z;
        return this;
    }

    public SmartRefreshLayout g(boolean z) {
        this.N = z;
        if (this.ap != null) {
            this.ap.a(z);
        }
        return this;
    }

    public SmartRefreshLayout m() {
        return d(Math.max(0, 1000 - ((int) (System.currentTimeMillis() - this.az))));
    }

    public SmartRefreshLayout n() {
        return e(Math.max(0, 1000 - ((int) (System.currentTimeMillis() - this.ay))));
    }

    public SmartRefreshLayout d(int i) {
        return b(i, true);
    }

    public SmartRefreshLayout b(int i, final boolean z) {
        long j;
        Runnable anonymousClass2 = new Runnable(this) {
            final /* synthetic */ SmartRefreshLayout b;

            public void run() {
                if (this.b.av != RefreshState.Refreshing) {
                    return;
                }
                if (this.b.ao == null || this.b.aq == null) {
                    this.b.j();
                    return;
                }
                int a = this.b.ao.a(this.b, z);
                if (a < Integer.MAX_VALUE) {
                    if (this.b.m) {
                        this.b.d = 0;
                        this.b.i = this.b.k;
                        this.b.m = false;
                        long currentTimeMillis = System.currentTimeMillis();
                        this.b.a(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, this.b.j, this.b.i + ((float) this.b.b), 0));
                    }
                    this.b.a(RefreshState.RefreshFinish);
                }
                if (this.b.T != null) {
                    this.b.T.a(this.b.ao, z);
                }
                if (a >= Integer.MAX_VALUE) {
                    return;
                }
                if (this.b.b > 0) {
                    AnimatorUpdateListener animatorUpdateListener = null;
                    ValueAnimator a2 = this.b.a(0, a);
                    if (this.b.J) {
                        animatorUpdateListener = this.b.aq.c(this.b.b);
                    }
                    if (a2 != null && animatorUpdateListener != null) {
                        a2.addUpdateListener(animatorUpdateListener);
                        return;
                    }
                    return;
                }
                this.b.a(0, true);
                this.b.j();
            }
        };
        if (i <= 0) {
            j = 1;
        } else {
            j = (long) i;
        }
        postDelayed(anonymousClass2, j);
        return this;
    }

    public SmartRefreshLayout e(int i) {
        return c(i, true);
    }

    public SmartRefreshLayout c(int i, boolean z) {
        return a(i, z, false);
    }

    public SmartRefreshLayout a(int i, final boolean z, final boolean z2) {
        long j;
        Runnable anonymousClass3 = new Runnable(this) {
            final /* synthetic */ SmartRefreshLayout c;

            public void run() {
                if (this.c.av == RefreshState.Loading) {
                    if (this.c.ap == null || this.c.aq == null) {
                        this.c.j();
                        return;
                    }
                    long currentTimeMillis;
                    final int a = this.c.ap.a(this.c, z);
                    if (a < Integer.MAX_VALUE) {
                        if (this.c.m) {
                            this.c.d = 0;
                            this.c.i = this.c.k;
                            this.c.m = false;
                            currentTimeMillis = System.currentTimeMillis();
                            this.c.a(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, this.c.j, this.c.i + ((float) this.c.b), 0));
                        }
                        this.c.a(RefreshState.LoadFinish);
                    }
                    if (this.c.T != null) {
                        this.c.T.a(this.c.ap, z);
                    }
                    if (a < Integer.MAX_VALUE) {
                        SmartRefreshLayout smartRefreshLayout = this.c;
                        Runnable anonymousClass1 = new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 b;

                            public void run() {
                                AnimatorUpdateListener animatorUpdateListener = null;
                                if (this.b.c.I && this.b.c.b < 0) {
                                    animatorUpdateListener = this.b.c.aq.c(this.b.c.b);
                                }
                                if (animatorUpdateListener != null) {
                                    animatorUpdateListener.onAnimationUpdate(ValueAnimator.ofInt(new int[]{0, 0}));
                                }
                                if (animatorUpdateListener != null || this.b.c.b >= 0) {
                                    this.b.c.a(0, true);
                                    this.b.c.j();
                                    if (z2) {
                                        this.b.c.g(true);
                                        return;
                                    }
                                    return;
                                }
                                ValueAnimator a = this.b.c.a(0, a);
                                if (a != null && z2) {
                                    a.addListener(new AnimatorListenerAdapter(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onAnimationEnd(Animator animator) {
                                            this.a.b.c.g(true);
                                        }
                                    });
                                }
                            }
                        };
                        if (this.c.b < 0) {
                            currentTimeMillis = (long) a;
                        } else {
                            currentTimeMillis = 0;
                        }
                        smartRefreshLayout.postDelayed(anonymousClass1, currentTimeMillis);
                    }
                } else if (z2) {
                    this.c.g(true);
                }
            }
        };
        if (i <= 0) {
            j = 1;
        } else {
            j = (long) i;
        }
        postDelayed(anonymousClass3, j);
        return this;
    }

    public SmartRefreshLayout o() {
        return a(Math.max(0, 1000 - ((int) (System.currentTimeMillis() - this.ay))), true, true);
    }

    public SmartRefreshLayout p() {
        g(false);
        return this;
    }

    public boolean q() {
        return f(this.as == null ? 400 : 0);
    }

    public boolean f(int i) {
        return a(i, this.f, (1.0f * ((float) (this.ae + (this.ai / 2)))) / ((float) this.ae));
    }

    public boolean a(int i, final int i2, final float f) {
        if (this.av != RefreshState.None || !s()) {
            return false;
        }
        if (this.aJ != null) {
            this.aJ.cancel();
        }
        Runnable anonymousClass4 = new Runnable(this) {
            final /* synthetic */ SmartRefreshLayout c;

            public void run() {
                this.c.aJ = ValueAnimator.ofInt(new int[]{this.c.b, (int) (((float) this.c.ae) * f)});
                this.c.aJ.setDuration((long) i2);
                this.c.aJ.setInterpolator(new DecelerateInterpolator());
                this.c.aJ.addUpdateListener(new AnimatorUpdateListener(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.a.c.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), false);
                    }
                });
                this.c.aJ.addListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationStart(Animator animator) {
                        this.a.c.j = (float) (this.a.c.getMeasuredWidth() / 2);
                        this.a.c.f();
                    }

                    public void onAnimationEnd(Animator animator) {
                        this.a.c.aJ = null;
                        if (this.a.c.av != RefreshState.ReleaseToRefresh) {
                            this.a.c.e();
                        }
                        this.a.c.k();
                    }
                });
                this.c.aJ.start();
            }
        };
        if (i > 0) {
            this.aJ = new ValueAnimator();
            postDelayed(anonymousClass4, (long) i);
        } else {
            anonymousClass4.run();
        }
        return true;
    }

    public boolean g(int i) {
        return b(i, this.f, (1.0f * ((float) (this.ag + (this.aj / 2)))) / ((float) this.ag));
    }

    public boolean b(int i, final int i2, final float f) {
        if (this.av != RefreshState.None || !r() || this.N) {
            return false;
        }
        if (this.aJ != null) {
            this.aJ.cancel();
        }
        Runnable anonymousClass5 = new Runnable(this) {
            final /* synthetic */ SmartRefreshLayout c;

            public void run() {
                this.c.aJ = ValueAnimator.ofInt(new int[]{this.c.b, -((int) (((float) this.c.ag) * f))});
                this.c.aJ.setDuration((long) i2);
                this.c.aJ.setInterpolator(new DecelerateInterpolator());
                this.c.aJ.addUpdateListener(new AnimatorUpdateListener(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.a.c.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), false);
                    }
                });
                this.c.aJ.addListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationStart(Animator animator) {
                        this.a.c.j = (float) (this.a.c.getMeasuredWidth() / 2);
                        this.a.c.a();
                    }

                    public void onAnimationEnd(Animator animator) {
                        this.a.c.aJ = null;
                        if (this.a.c.av != RefreshState.ReleaseToLoad) {
                            this.a.c.b();
                        }
                        if (this.a.c.G) {
                            this.a.c.G = false;
                            this.a.c.k();
                            this.a.c.G = true;
                            return;
                        }
                        this.a.c.k();
                    }
                });
                this.c.aJ.start();
            }
        };
        if (i > 0) {
            this.aJ = new ValueAnimator();
            postDelayed(anonymousClass5, (long) i);
        } else {
            anonymousClass5.run();
        }
        return true;
    }

    public boolean r() {
        return this.z && !this.H;
    }

    public boolean s() {
        return this.y && !this.H;
    }

    public static void setDefaultRefreshHeaderCreater(@NonNull com.scwang.smartrefresh.layout.a.b bVar) {
        aH = bVar;
    }

    public static void setDefaultRefreshFooterCreater(@NonNull com.scwang.smartrefresh.layout.a.a aVar) {
        aG = aVar;
        aF = true;
    }

    public boolean post(Runnable runnable) {
        if (this.as != null) {
            return this.as.post(new com.scwang.smartrefresh.layout.f.a(runnable));
        }
        this.au = this.au == null ? new ArrayList() : this.au;
        this.au.add(new com.scwang.smartrefresh.layout.f.a(runnable));
        return false;
    }

    public boolean postDelayed(Runnable runnable, long j) {
        if (j == 0) {
            new com.scwang.smartrefresh.layout.f.a(runnable).run();
            return true;
        } else if (this.as != null) {
            return this.as.postDelayed(new com.scwang.smartrefresh.layout.f.a(runnable), j);
        } else {
            this.au = this.au == null ? new ArrayList() : this.au;
            this.au.add(new com.scwang.smartrefresh.layout.f.a(runnable, j));
            return false;
        }
    }
}
