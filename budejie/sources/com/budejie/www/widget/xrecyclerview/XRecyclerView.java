package com.budejie.www.widget.xrecyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.constants.CommonInts;
import com.budejie.www.R;
import com.budejie.www.http.i;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.List;

public class XRecyclerView extends RecyclerView {
    private boolean a;
    private ArrayList<View> b;
    private XRecyclerView$e c;
    private float d;
    private XRecyclerView$c e;
    private ArrowRefreshHeader f;
    private boolean g;
    private boolean h;
    private long i;
    private List<Integer> j;
    private View k;
    private View l;
    private final AdapterDataObserver m;
    private float n;
    private int o;
    private int p;
    private XRecyclerView$d q;
    private boolean r;
    private float s;
    private long t;
    private boolean u;
    private boolean v;
    private boolean w;
    private XRecyclerView$b x;
    private boolean y;

    public XRecyclerView(Context context) {
        this(context, null);
    }

    public XRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        this.b = new ArrayList();
        this.d = -1.0f;
        this.g = true;
        this.h = true;
        this.i = System.currentTimeMillis();
        this.j = new ArrayList();
        this.m = new XRecyclerView$a(this, null);
        this.s = -1.0f;
        this.t = 0;
        this.v = false;
        this.w = false;
        this.x = XRecyclerView$b.c;
        a();
    }

    private void a() {
        if (this.g) {
            this.f = new ArrowRefreshHeader(getContext());
        }
        this.l = new LoadingMoreFooter(getContext());
        this.l.setVisibility(8);
    }

    public void a(View view) {
        this.j.add(Integer.valueOf(this.b.size() + CommonInts.GT_ERROR));
        this.b.add(view);
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }

    public int getHeaderViewCount() {
        if (this.f != null) {
            return this.b.size() + 1;
        }
        return this.b.size();
    }

    private View a(int i) {
        if (b(i)) {
            return (View) this.b.get(i - 10002);
        }
        return null;
    }

    private boolean b(int i) {
        return this.b.size() > 0 && this.j.contains(Integer.valueOf(i));
    }

    private boolean c(int i) {
        if (i == 20000 || i == 20001 || this.j.contains(Integer.valueOf(i))) {
            return true;
        }
        return false;
    }

    public void setFootView(View view) {
        this.l = view;
    }

    public void b() {
        this.w = false;
        if (this.l instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) this.l).setState(1);
        } else {
            this.l.setVisibility(8);
        }
        if (this.x == XRecyclerView$b.a) {
            this.x = XRecyclerView$b.c;
            e();
        }
    }

    public void setNoMore(boolean z) {
        this.a = z;
        if (this.l instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) this.l).setState(this.a ? 2 : 1);
        } else {
            this.l.setVisibility(8);
        }
    }

    public void c() {
        if (this.g && this.e != null && !this.v) {
            if (this.f != null) {
                this.f.setVisibleHeight(this.f.a);
                this.f.setState(2);
                this.f.setLastRefreshTime(this.i);
            }
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager != null) {
                layoutManager.scrollToPosition(0);
            }
            e();
        }
    }

    private void e() {
        if (this.w) {
            this.x = XRecyclerView$b.a;
        } else if (!this.v) {
            this.x = XRecyclerView$b.c;
            this.v = true;
            this.e.a();
        }
    }

    private void f() {
        if (this.v) {
            this.x = XRecyclerView$b.b;
            return;
        }
        this.x = XRecyclerView$b.c;
        this.w = true;
        this.e.b();
        g();
    }

    public void a(boolean z) {
        this.v = false;
        this.f.a();
        if (z) {
            this.i = System.currentTimeMillis();
        }
        if (this.x == XRecyclerView$b.b) {
            this.x = XRecyclerView$b.c;
            f();
        }
    }

    public void d() {
        a(true);
    }

    public void setLastRefreshTime(long j) {
        this.i = j;
    }

    public void setRefreshHeader(ArrowRefreshHeader arrowRefreshHeader) {
        this.f = arrowRefreshHeader;
    }

    public void setPullRefreshEnabled(boolean z) {
        this.g = z;
    }

    public void setLoadingMoreEnabled(boolean z) {
        this.h = z;
        if (!z && (this.l instanceof LoadingMoreFooter)) {
            ((LoadingMoreFooter) this.l).setState(1);
        }
    }

    public void setArrowImageView(int i) {
        if (this.f != null) {
            this.f.setArrowImageView(i);
        }
    }

    public void setEmptyView(View view) {
        this.k = view;
        this.m.onChanged();
    }

    public View getEmptyView() {
        return this.k;
    }

    public void setAdapter(Adapter adapter) {
        this.c = new XRecyclerView$e(this, adapter);
        super.setAdapter(this.c);
        adapter.registerAdapterDataObserver(this.m);
        this.m.onChanged();
    }

    public Adapter getAdapter() {
        if (this.c != null) {
            return this.c.a();
        }
        return null;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (this.c != null && (layoutManager instanceof GridLayoutManager)) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new XRecyclerView$1(this, gridLayoutManager));
        }
    }

    public void a(String str, OnClickListener onClickListener) {
        this.u = true;
        ((LoadingMoreFooter) this.l).setRandomState(str);
        ((LoadingMoreFooter) this.l).setState(2);
        this.l.setOnClickListener(onClickListener);
    }

    public void setNeedRefreshSpan(boolean z) {
        this.y = z;
    }

    public void onScrolled(int i, int i2) {
        if (!this.u) {
            if (this.y && getFirstVisibleMinPosition() <= this.b.size()) {
                this.y = false;
                LayoutManager layoutManager = getLayoutManager();
                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    ((StaggeredGridLayoutManager) layoutManager).invalidateSpanAssignments();
                }
                invalidateItemDecorations();
            }
            if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
                int[] findLastVisibleItemPositions = ((StaggeredGridLayoutManager) getLayoutManager()).findLastVisibleItemPositions(new int[((StaggeredGridLayoutManager) getLayoutManager()).getSpanCount()]);
                int itemCount = getAdapter().getItemCount();
                int b = b(findLastVisibleItemPositions) - getHeaderViewCount();
                if (this.e != null && !this.w && b >= itemCount - 6 && i2 > 0 && this.h) {
                    ((LoadingMoreFooter) this.l).setState(0);
                    f();
                }
            }
        }
    }

    public void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        if (i == 0 && getFirstVisibleMinPosition() <= this.b.size()) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                ((StaggeredGridLayoutManager) layoutManager).invalidateSpanAssignments();
            }
            invalidateItemDecorations();
        }
        if (i == 0 && this.e != null && !this.w && this.h) {
            LayoutManager layoutManager2 = getLayoutManager();
            int findLastVisibleItemPosition;
            if (layoutManager2 instanceof GridLayoutManager) {
                findLastVisibleItemPosition = ((GridLayoutManager) layoutManager2).findLastVisibleItemPosition();
            } else if (layoutManager2 instanceof StaggeredGridLayoutManager) {
                int[] iArr = new int[((StaggeredGridLayoutManager) layoutManager2).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager2).findLastVisibleItemPositions(iArr);
                findLastVisibleItemPosition = a(iArr);
            } else {
                findLastVisibleItemPosition = ((LinearLayoutManager) layoutManager2).findLastVisibleItemPosition();
            }
            if (layoutManager2.getChildCount() > 0 && r0 >= layoutManager2.getItemCount() - 1 && layoutManager2.getItemCount() > layoutManager2.getChildCount() && !this.a && this.f.getState() < 2) {
                if (!(this.l instanceof LoadingMoreFooter) || this.u) {
                    this.l.setVisibility(0);
                    return;
                }
                ((LoadingMoreFooter) this.l).setState(0);
                f();
            }
        }
    }

    private void g() {
        i.a(getContext().getString(R.string.track_event_load_more), getContext().getString(R.string.track_event_load_more));
    }

    public int getFirstVisibleMinPosition() {
        LayoutManager layoutManager = getLayoutManager();
        if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
            return 0;
        }
        int[] iArr = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
        ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(iArr);
        return a(iArr);
    }

    public int getFirstVisiblePosition() {
        LayoutManager layoutManager = getLayoutManager();
        if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
            return 0;
        }
        int[] iArr = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
        ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(iArr);
        ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(iArr);
        return b(iArr);
    }

    public void scrollToPosition(int i) {
        getLayoutManager().scrollToPosition(i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.d == -1.0f) {
            this.d = motionEvent.getRawY();
        }
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.s = motionEvent.getY();
                this.t = System.currentTimeMillis();
                this.d = motionEvent.getRawY();
                if (this.n == 0.0f) {
                    this.n = y;
                    break;
                }
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.d;
                this.d = motionEvent.getRawY();
                if (h() && this.g) {
                    this.f.a(rawY / 1.8f);
                    this.f.setLastRefreshTime(this.i);
                    if (this.f.getVisibleHeight() > 0 && this.f.getState() < 2) {
                        return false;
                    }
                }
                if (this.n == 0.0f) {
                    this.n = y;
                }
                if (!(this.n == 0.0f || this.q == null)) {
                    if (this.n > y && this.n - y > ((float) this.o) && !this.r) {
                        this.q.a();
                        this.n = y;
                        this.r = true;
                    }
                    if (this.n < y && y - this.n > ((float) this.p) && this.r) {
                        this.q.b();
                        this.n = y;
                        this.r = false;
                        break;
                    }
                }
            default:
                if (this.q != null) {
                    float abs = Math.abs(this.s - y) / ((float) (System.currentTimeMillis() - this.t));
                    if (((double) (Math.abs(this.s - y) / ((float) (System.currentTimeMillis() - this.t)))) > 2.0d) {
                        this.q.c();
                    }
                }
                this.n = 0.0f;
                this.s = -1.0f;
                this.d = -1.0f;
                if (h() && this.g && this.f.b() && this.e != null) {
                    e();
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    private int a(int[] iArr) {
        if (iArr == null || iArr.length <= 0) {
            return 0;
        }
        int i = iArr[0];
        for (int i2 : iArr) {
            if (i2 != -1 && i2 < i) {
                i = i2;
            }
        }
        return i == -1 ? 0 : i;
    }

    private int b(int[] iArr) {
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = iArr[0];
            int length = iArr.length;
            int i3 = 0;
            i = i2;
            while (i3 < length) {
                i2 = iArr[i3];
                if (i2 <= i) {
                    i2 = i;
                }
                i3++;
                i = i2;
            }
        }
        return i;
    }

    private boolean h() {
        return this.f.getParent() != null;
    }

    public void setLoadingListener(XRecyclerView$c xRecyclerView$c) {
        this.e = xRecyclerView$c;
    }

    public void setOnScrollDirectionListener(XRecyclerView$d xRecyclerView$d) {
        this.o = an.a(getContext(), 1);
        this.p = an.a(getContext(), 1);
        this.q = xRecyclerView$d;
    }
}
