package com.marshalchen.ultimaterecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.marshalchen.ultimaterecyclerview.b.d;
import com.marshalchen.ultimaterecyclerview.b.e;
import com.marshalchen.ultimaterecyclerview.ui.VerticalSwipeRefreshLayout;

public class UltimateRecyclerView extends FrameLayout {
    public static int a = 0;
    private static boolean ad = false;
    public static int b = 1;
    public static int c = 2;
    public static int d = 3;
    public static int e = 0;
    public static int f = 1;
    private int A;
    private int B;
    private b C;
    private int D;
    private boolean E = false;
    private e F;
    private int G;
    private int H = -1;
    private int I;
    private int J;
    private int K;
    private SparseIntArray L = new SparseIntArray();
    private ObservableScrollState M;
    private a N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R = false;
    private MotionEvent S;
    private ViewGroup T;
    private View U;
    private com.marshalchen.ultimaterecyclerview.b.a V;
    private a W;
    private int aa;
    private final float ab = 0.5f;
    private c ac;
    private LayoutInflater ae;
    private boolean af = false;
    private int ag;
    private int ah = 0;
    private int ai = 0;
    private int aj = 0;
    private int ak;
    private int[] al;
    private float am = 0.5f;
    public RecyclerView g;
    protected com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.a h;
    protected OnScrollListener i;
    protected LAYOUT_MANAGER_TYPE j;
    protected int k;
    protected int l;
    protected int m;
    protected int n;
    protected int o;
    protected boolean p;
    protected ViewStub q;
    protected View r;
    protected int s;
    protected com.marshalchen.ultimaterecyclerview.ui.a.a t;
    protected ViewStub u;
    protected View v;
    protected int w;
    protected int[] x = null;
    public int y = 3;
    public VerticalSwipeRefreshLayout z;

    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID,
        PUZZLE
    }

    public static class a extends RelativeLayout {
        private int a;

        public a(Context context) {
            super(context);
        }

        protected void dispatchDraw(Canvas canvas) {
            if (UltimateRecyclerView.ad) {
                canvas.clipRect(new Rect(getLeft(), getTop(), getRight(), getBottom() + this.a));
            }
            super.dispatchDraw(canvas);
        }

        public void setClipY(int i) {
            this.a = i;
            invalidate();
        }
    }

    public interface b {
        void a(int i, int i2);
    }

    public interface c {
        void a(float f, float f2, View view);
    }

    public UltimateRecyclerView(Context context) {
        super(context);
        a();
    }

    public UltimateRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
        a();
    }

    public UltimateRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
        a();
    }

    public void setRecylerViewBackgroundColor(@ColorInt int i) {
        this.g.setBackgroundColor(i);
    }

    protected void a() {
        this.ae = (LayoutInflater) getContext().getSystemService("layout_inflater");
        View inflate = this.ae.inflate(d.ultimate_recycler_view_layout, this);
        this.g = (RecyclerView) inflate.findViewById(com.marshalchen.ultimaterecyclerview.b.c.ultimate_list);
        this.z = (VerticalSwipeRefreshLayout) inflate.findViewById(com.marshalchen.ultimaterecyclerview.b.c.swipe_refresh_layout);
        d();
        this.z.setEnabled(false);
        if (this.g != null) {
            this.g.setClipToPadding(this.p);
            if (((float) this.k) != -1.1f) {
                this.g.setPadding(this.k, this.k, this.k, this.k);
            } else {
                this.g.setPadding(this.n, this.l, this.o, this.m);
            }
        }
        this.h = (com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.a) inflate.findViewById(com.marshalchen.ultimaterecyclerview.b.c.defaultFloatingActionButton);
        e();
        this.q = (ViewStub) inflate.findViewById(com.marshalchen.ultimaterecyclerview.b.c.emptyview);
        if (this.s != 0) {
            this.q.setLayoutResource(this.s);
            this.r = this.q.inflate();
            this.q.setVisibility(8);
        }
        this.u = (ViewStub) inflate.findViewById(com.marshalchen.ultimaterecyclerview.b.c.floatingActionViewStub);
        this.u.setLayoutResource(this.w);
    }

    public View getEmptyView() {
        return this.r;
    }

    private void a(int i, int i2) {
        this.A = i;
        this.B = i2;
    }

    public void setInflater(LayoutInflater layoutInflater) {
        this.ae = layoutInflater;
    }

    private void setEmptyView(@LayoutRes int i) {
        if (this.r != null || i <= 0) {
            Log.d("View", "unabled to set empty view because the empty has been set");
            return;
        }
        this.s = i;
        this.q.setLayoutResource(i);
        if (VERSION.SDK_INT >= 16) {
            this.q.setLayoutInflater(this.ae);
        }
        this.r = this.q.inflate();
    }

    private void setEmptyView(@Nullable View view) {
        if (view != null) {
            this.r = view;
        }
    }

    public final void a(@LayoutRes int i, int i2, int i3) {
        setEmptyView(i);
        a(i2, i3);
    }

    public boolean b() {
        if (this.q == null || this.r == null || this.F == null) {
            Log.d("View", "it is unable to show empty view");
            return false;
        }
        if (this.F.g() == a || this.F.g() == c) {
            this.q.setVisibility(0);
            if (this.t != null) {
                this.t.a(this.r);
            }
        }
        return true;
    }

    public void c() {
        if (this.q == null || this.r == null) {
            Log.d("View", "there is no such empty view");
        } else {
            this.q.setVisibility(8);
        }
    }

    public void setLoadMoreView(View view) {
        if (this.U != null) {
            Log.d("View", "The loading more layout has already been initiated.");
        } else if (view == null) {
            this.U = LayoutInflater.from(getContext()).inflate(d.bottom_progressbar, null);
            Log.d("View", "Layout Resource view is null. This system will use the default loading view instead.");
        } else {
            this.U = view;
        }
    }

    public void setLoadMoreView(@LayoutRes int i) {
        if (i > 0) {
            this.U = LayoutInflater.from(getContext()).inflate(i, null);
        } else {
            Log.d("View", "Layout Resource Id is not found for load more view for ulitmaterecyclerview");
        }
    }

    protected void d() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        switch (this.ag) {
            case 1:
                this.z.removeView(this.g);
                this.g = (RecyclerView) layoutInflater.inflate(d.vertical_recycler_view, this.z, true).findViewById(com.marshalchen.ultimaterecyclerview.b.c.ultimate_list);
                return;
            case 2:
                this.z.removeView(this.g);
                this.g = (RecyclerView) layoutInflater.inflate(d.horizontal_recycler_view, this.z, true).findViewById(com.marshalchen.ultimaterecyclerview.b.c.ultimate_list);
                return;
            default:
                return;
        }
    }

    protected void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.UltimateRecyclerview);
        try {
            this.k = (int) obtainStyledAttributes.getDimension(e.UltimateRecyclerview_recyclerviewPadding, -1.1f);
            this.l = (int) obtainStyledAttributes.getDimension(e.UltimateRecyclerview_recyclerviewPaddingTop, 0.0f);
            this.m = (int) obtainStyledAttributes.getDimension(e.UltimateRecyclerview_recyclerviewPaddingBottom, 0.0f);
            this.n = (int) obtainStyledAttributes.getDimension(e.UltimateRecyclerview_recyclerviewPaddingLeft, 0.0f);
            this.o = (int) obtainStyledAttributes.getDimension(e.UltimateRecyclerview_recyclerviewPaddingRight, 0.0f);
            this.p = obtainStyledAttributes.getBoolean(e.UltimateRecyclerview_recyclerviewClipToPadding, false);
            this.s = obtainStyledAttributes.getResourceId(e.UltimateRecyclerview_recyclerviewEmptyView, 0);
            this.w = obtainStyledAttributes.getResourceId(e.UltimateRecyclerview_recyclerviewFloatingActionView, 0);
            this.ag = obtainStyledAttributes.getInt(e.UltimateRecyclerview_recyclerviewScrollbars, 0);
            int resourceId = obtainStyledAttributes.getResourceId(e.UltimateRecyclerview_recyclerviewDefaultSwipeColor, 0);
            if (resourceId != 0) {
                this.x = getResources().getIntArray(resourceId);
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    private void j() {
        this.g.removeOnScrollListener(this.i);
        this.i = new OnScrollListener(this) {
            final /* synthetic */ UltimateRecyclerView a;

            {
                this.a = r1;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.a.a(recyclerView);
            }
        };
        this.g.addOnScrollListener(this.i);
    }

    private void b(RecyclerView recyclerView) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (this.j == null) {
            if (layoutManager instanceof GridLayoutManager) {
                this.j = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                this.j = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else if (layoutManager instanceof LinearLayoutManager) {
                this.j = LAYOUT_MANAGER_TYPE.LINEAR;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        this.ai = layoutManager.getItemCount();
        this.ah = layoutManager.getChildCount();
        switch (this.j) {
            case LINEAR:
                this.ak = this.V.a();
                this.D = this.V.b();
                break;
            case GRID:
                if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    this.D = gridLayoutManager.findLastVisibleItemPosition();
                    this.ak = gridLayoutManager.findFirstVisibleItemPosition();
                    break;
                }
                break;
            case STAGGERED_GRID:
                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    if (this.al == null) {
                        this.al = new int[staggeredGridLayoutManager.getSpanCount()];
                    }
                    staggeredGridLayoutManager.findLastVisibleItemPositions(this.al);
                    this.D = a(this.al);
                    staggeredGridLayoutManager.findFirstVisibleItemPositions(this.al);
                    this.ak = b(this.al);
                    break;
                }
                break;
        }
        if (this.E && this.ai > this.aj) {
            this.E = false;
            this.aj = this.ai;
        }
        if (this.ai - this.ah <= this.ak) {
            if (this.R && !this.E) {
                this.C.a(this.g.getAdapter().getItemCount(), this.D);
                this.E = true;
            }
            this.F.f();
            this.aj = this.ai;
        }
    }

    protected void e() {
        this.g.removeOnScrollListener(this.i);
        this.i = new OnScrollListener(this) {
            final /* synthetic */ UltimateRecyclerView a;

            {
                this.a = r1;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (this.a.W != null) {
                    this.a.aa = this.a.aa + i2;
                    if (UltimateRecyclerView.ad) {
                        this.a.a((float) this.a.aa);
                    }
                }
                this.a.b(recyclerView);
                this.a.a(recyclerView);
            }
        };
        this.g.addOnScrollListener(this.i);
    }

    public void f() {
        if (!(this.F == null || this.U == null)) {
            this.F.b(true);
        }
        this.R = true;
    }

    public boolean g() {
        return this.R;
    }

    public void h() {
        this.R = false;
        if (this.F != null && this.U != null) {
            this.F.b(false);
        }
    }

    protected void a(RecyclerView recyclerView) {
        if (this.N != null && getChildCount() > 0) {
            int height;
            int childAdapterPosition = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0));
            int childAdapterPosition2 = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(recyclerView.getChildCount() - 1));
            int i = 0;
            int i2 = childAdapterPosition;
            while (i2 <= childAdapterPosition2) {
                try {
                    View childAt = recyclerView.getChildAt(i);
                    if ((this.L.indexOfKey(i2) < 0 || !(childAt == null || childAt.getHeight() == this.L.get(i2))) && childAt != null) {
                        height = childAt.getHeight();
                    } else {
                        height = 0;
                    }
                    this.L.put(i2, height);
                    i2++;
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                    c.a(e, "");
                }
            }
            View childAt2 = recyclerView.getChildAt(0);
            if (childAt2 != null) {
                if (this.G < childAdapterPosition) {
                    if (childAdapterPosition - this.G != 1) {
                        i = 0;
                        for (height = childAdapterPosition - 1; height > this.G; height--) {
                            if (this.L.indexOfKey(height) > 0) {
                                i += this.L.get(height);
                            } else {
                                i += childAt2.getHeight();
                            }
                        }
                    } else {
                        i = 0;
                    }
                    this.I += i + this.H;
                    this.H = childAt2.getHeight();
                } else if (childAdapterPosition < this.G) {
                    if (this.G - childAdapterPosition != 1) {
                        height = 0;
                        for (i = this.G - 1; i > childAdapterPosition; i--) {
                            if (this.L.indexOfKey(i) > 0) {
                                height += this.L.get(i);
                            } else {
                                height += childAt2.getHeight();
                            }
                        }
                    } else {
                        height = 0;
                    }
                    this.I -= height + childAt2.getHeight();
                    this.H = childAt2.getHeight();
                } else if (childAdapterPosition == 0) {
                    this.H = childAt2.getHeight();
                    this.I = 0;
                }
                if (this.H < 0) {
                    this.H = 0;
                }
                this.K = this.I - childAt2.getTop();
                this.G = childAdapterPosition;
                this.N.a(this.K, this.O, this.P);
                if (this.J < this.K) {
                    if (this.O) {
                        this.O = false;
                        this.M = ObservableScrollState.STOP;
                    }
                    this.M = ObservableScrollState.UP;
                } else if (this.K < this.J) {
                    this.M = ObservableScrollState.DOWN;
                } else {
                    this.M = ObservableScrollState.STOP;
                }
                if (this.O) {
                    this.O = false;
                }
                this.J = this.K;
            }
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.g.setOnScrollListener(onScrollListener);
    }

    public void a(OnScrollListener onScrollListener) {
        this.g.addOnScrollListener(onScrollListener);
    }

    public void setAdapter(e eVar) {
        this.g.setAdapter(eVar);
        setAdapterInternal(eVar);
    }

    public void a(ItemDecoration itemDecoration) {
        this.g.addItemDecoration(itemDecoration);
    }

    public void setItemAnimator(ItemAnimator itemAnimator) {
        this.g.setItemAnimator(itemAnimator);
    }

    public ItemAnimator getItemAnimator() {
        return this.g.getItemAnimator();
    }

    public void setDefaultOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.z.setEnabled(true);
        if (this.x == null || this.x.length <= 0) {
            this.z.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
        } else {
            this.z.setColorSchemeColors(this.x);
        }
        this.z.setOnRefreshListener(onRefreshListener);
    }

    public void setDefaultSwipeToRefreshColorScheme(int... iArr) {
        this.z.setColorSchemeColors(iArr);
    }

    public void setOnLoadMoreListener(b bVar) {
        this.C = bVar;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.g.setLayoutManager(layoutManager);
    }

    public Adapter getAdapter() {
        return this.g.getAdapter();
    }

    private void setAdapterInternal(e eVar) {
        this.F = eVar;
        if (this.z != null) {
            this.z.setRefreshing(false);
        }
        if (this.F != null) {
            this.F.registerAdapterDataObserver(new AdapterDataObserver(this) {
                final /* synthetic */ UltimateRecyclerView a;

                {
                    this.a = r1;
                }

                public void onItemRangeChanged(int i, int i2) {
                    super.onItemRangeChanged(i, i2);
                    this.a.k();
                }

                public void onItemRangeInserted(int i, int i2) {
                    super.onItemRangeInserted(i, i2);
                    this.a.k();
                }

                public void onItemRangeRemoved(int i, int i2) {
                    super.onItemRangeRemoved(i, i2);
                    this.a.k();
                }

                public void onItemRangeMoved(int i, int i2, int i3) {
                    super.onItemRangeMoved(i, i2, i3);
                    this.a.k();
                }

                public void onChanged() {
                    super.onChanged();
                    this.a.k();
                }
            });
        }
        this.V = com.marshalchen.ultimaterecyclerview.b.a.a(this.g);
        this.F.c(this.A);
        this.F.d(this.B);
        if (this.F.a() == 0 && this.B == e) {
            b();
        }
        if (this.B == f) {
            c();
        }
        if (this.F.d() == null && this.U != null) {
            this.F.h(this.U);
            this.F.b(true);
            this.F.notifyDataSetChanged();
            this.R = true;
        }
        if (this.W != null) {
            this.F.a(this.W);
        }
    }

    private void k() {
        int i = 0;
        this.E = false;
        if (this.z != null) {
            this.z.setRefreshing(false);
        }
        if (this.F != null) {
            if (this.af) {
                setRefreshing(false);
                l();
                return;
            }
            this.af = true;
            if (this.F.a() == 0) {
                ViewStub viewStub = this.q;
                if (this.r != null) {
                    i = 8;
                }
                viewStub.setVisibility(i);
            } else if (this.s != 0) {
                l();
                this.q.setVisibility(8);
            }
        }
    }

    private void l() {
        if (this.F.d() == null) {
            return;
        }
        if (this.F.e()) {
            this.F.d().setVisibility(0);
        } else {
            this.F.d().setVisibility(8);
        }
    }

    public void setHasFixedSize(boolean z) {
        this.g.setHasFixedSize(z);
    }

    public void setRefreshing(boolean z) {
        if (this.z != null) {
            this.z.setRefreshing(z);
        }
    }

    private int a(int[] iArr) {
        int i = Integer.MIN_VALUE;
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i3 <= i) {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    private int b(int[] iArr) {
        int i = Integer.MAX_VALUE;
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i3 == -1 || i3 >= i) {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    public void setParallaxHeader(@LayoutRes int i) {
        setParallaxHeader(LayoutInflater.from(getContext()).inflate(i, null));
    }

    public void setParallaxHeader(View view) {
        this.W = new a(view.getContext());
        this.W.setLayoutParams(new LayoutParams(-2, -2));
        this.W.addView(view, new RelativeLayout.LayoutParams(-1, -1));
        ad = true;
    }

    public void setNormalHeader(View view) {
        setParallaxHeader(view);
        ad = false;
    }

    public void setOnParallaxScroll(c cVar) {
        this.ac = cVar;
        this.ac.a(0.0f, 0.0f, this.W);
    }

    public void a(float f) {
        float f2 = 1.0f;
        float f3 = this.am * f;
        if (VERSION.SDK_INT >= 11 && f < ((float) this.W.getHeight())) {
            this.W.setTranslationY(f3);
        } else if (f < ((float) this.W.getHeight())) {
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, f3, f3);
            translateAnimation.setFillAfter(true);
            translateAnimation.setDuration(0);
            this.W.startAnimation(translateAnimation);
        }
        this.W.setClipY(Math.round(f3));
        if (this.ac != null) {
            if (this.g.findViewHolderForAdapterPosition(0) != null) {
                f2 = Math.min(1.0f, f3 / (((float) this.W.getHeight()) * this.am));
            }
            this.ac.a(f2, f, this.W);
        }
    }

    public void setScrollMultiplier(float f) {
        this.am = f;
    }

    public float getScrollMultiplier() {
        return this.am;
    }

    public void setScrollViewCallbacks(a aVar) {
        this.N = aVar;
    }

    public void setItemViewCacheSize(int i) {
        this.g.setItemViewCacheSize(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        com.marshalchen.ultimaterecyclerview.b.b bVar = (com.marshalchen.ultimaterecyclerview.b.b) parcelable;
        this.G = bVar.b;
        this.H = bVar.c;
        this.I = bVar.d;
        this.J = bVar.e;
        this.K = bVar.f;
        this.L = bVar.g;
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            int childCount = layoutManager.getChildCount();
            if (this.J != -1 && this.J < childCount) {
                layoutManager.scrollToPosition(this.J);
            }
        }
        super.onRestoreInstanceState(bVar.a());
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bVar = new com.marshalchen.ultimaterecyclerview.b.b(super.onSaveInstanceState());
        bVar.b = this.G;
        bVar.c = this.H;
        bVar.d = this.I;
        bVar.e = this.J;
        bVar.f = this.K;
        bVar.g = this.L;
        return bVar;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.N != null) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.P = true;
                    this.O = true;
                    this.N.a();
                    break;
                case 1:
                case 3:
                    this.Q = false;
                    this.P = false;
                    this.N.a(this.M);
                    break;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        this.T = viewGroup;
        j();
    }

    public void a(int i) {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !(layoutManager instanceof LinearLayoutManager)) {
            layoutManager.scrollToPosition(i);
        } else {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, 0);
        }
    }

    public int getCurrentScrollY() {
        return this.K;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f = 0.0f;
        c.a("ev---" + motionEvent);
        if (this.N != null) {
            switch (motionEvent.getActionMasked()) {
                case 1:
                case 3:
                    this.Q = false;
                    this.P = false;
                    this.N.a(this.M);
                    break;
                case 2:
                    if (this.S == null) {
                        this.S = motionEvent;
                    }
                    float y = motionEvent.getY() - this.S.getY();
                    this.S = MotionEvent.obtainNoHistory(motionEvent);
                    if (((float) getCurrentScrollY()) - y <= 0.0f) {
                        if (this.Q) {
                            return false;
                        }
                        View view;
                        if (this.T == null) {
                            view = (ViewGroup) getParent();
                        } else {
                            view = this.T;
                        }
                        View view2 = this;
                        float f2 = 0.0f;
                        while (view2 != null && view2 != view) {
                            f2 += (float) (view2.getLeft() - view2.getScrollX());
                            f += (float) (view2.getTop() - view2.getScrollY());
                            view2 = (View) view2.getParent();
                        }
                        final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                        obtainNoHistory.offsetLocation(f2, f);
                        if (!view.onInterceptTouchEvent(obtainNoHistory)) {
                            return super.onTouchEvent(motionEvent);
                        }
                        this.Q = true;
                        obtainNoHistory.setAction(0);
                        post(new Runnable(this) {
                            final /* synthetic */ UltimateRecyclerView c;

                            public void run() {
                                view.dispatchTouchEvent(obtainNoHistory);
                            }
                        });
                        return false;
                    }
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.a getDefaultFloatingActionButton() {
        return this.h;
    }

    public void setDefaultFloatingActionButton(com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.a aVar) {
        this.h = aVar;
    }

    public View getCustomFloatingActionView() {
        return this.v;
    }

    public LayoutManager getLayoutManager() {
        return this.g.getLayoutManager();
    }
}
