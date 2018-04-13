package com.budejie.www.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.budejie.www.widget.XListView.a;

public class ExpertXListView extends ListView implements OnScrollListener {
    private final String a = ExpertXListView.class.getSimpleName();
    private float b = -1.0f;
    private Scroller c;
    private OnScrollListener d;
    private a e;
    private d f;
    private RelativeLayout g;
    private boolean h = true;
    private boolean i = false;
    private XListViewFooter j;
    private boolean k;
    private boolean l;
    private boolean m = false;
    private int n;
    private int o;
    private boolean p;

    public View getFooterView() {
        return this.j;
    }

    public d getHeaderView() {
        return this.f;
    }

    public RelativeLayout getmHeaderViewContent() {
        return this.g;
    }

    public ExpertXListView(Context context) {
        super(context);
        a(context);
    }

    public ExpertXListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ExpertXListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @SuppressLint({"NewApi"})
    private void a(Context context) {
        this.c = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.f = new d(context);
        this.g = this.f.getContainer();
        addHeaderView(this.f);
        this.j = new XListViewFooter(context);
        if (VERSION.SDK_INT >= 11) {
            setOverScrollMode(2);
        } else {
            setHorizontalFadingEdgeEnabled(false);
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
    }

    public void setPullRefreshEnable(boolean z) {
        this.h = z;
    }

    public void setPullLoadEnable(boolean z) {
        this.k = z;
        if (z) {
            d();
        } else {
            e();
        }
        if (this.k) {
            this.l = false;
            this.j.b();
            this.j.setState(0);
            this.j.setOnClickListener(new ExpertXListView$1(this));
            return;
        }
        this.j.a();
        this.j.setOnClickListener(null);
    }

    public void a() {
        this.j.getHintView().setVisibility(8);
        this.j.getProgressBar().setVisibility(8);
        this.j.getContentView().setVisibility(8);
    }

    private void d() {
        if (!this.m) {
            this.m = true;
            addFooterView(this.j);
        }
    }

    private void e() {
        if (this.m) {
            this.m = false;
            removeFooterView(this.j);
        }
    }

    public void b() {
        Log.d(this.a, "stopRefresh");
        if (this.i) {
            this.i = false;
            a(0.0f);
            g();
        }
    }

    public void c() {
        Log.d(this.a, "stopLoadMore");
        if (this.l) {
            this.l = false;
            this.j.setState(0);
        }
    }

    public void setRefreshTime(String str) {
    }

    private void f() {
        if (this.d instanceof ExpertXListView$a) {
            ((ExpertXListView$a) this.d).a(this);
        }
    }

    private void a(float f) {
        this.f.setVisiableHeight(((int) f) + this.f.getVisiableHeight());
        if (this.h && !this.i) {
            if (this.f.getVisiableHeight() > this.f.getTriggerHeight()) {
                this.f.setState(1);
            } else {
                this.f.setState(0);
            }
        }
        if (f < 0.0f) {
            setSelection(0);
        }
    }

    private void g() {
        int visiableHeight = this.f.getVisiableHeight();
        if (visiableHeight != this.f.getMinHeight()) {
            int i;
            int minHeight = this.f.getMinHeight();
            if (!this.i || visiableHeight <= this.f.getMinHeight()) {
                i = minHeight;
            } else {
                i = this.f.getMinHeight();
            }
            this.o = 0;
            this.c.startScroll(0, visiableHeight, 0, i - visiableHeight, 400);
            invalidate();
        }
    }

    private void b(float f) {
        int bottomMargin = this.j.getBottomMargin() + ((int) f);
        if (this.k && !this.l) {
            if (bottomMargin > 50) {
                this.j.setState(1);
            } else {
                this.j.setState(0);
            }
        }
        this.j.setBottomMargin(bottomMargin);
    }

    private void h() {
        int bottomMargin = this.j.getBottomMargin();
        if (bottomMargin > 0) {
            this.o = 1;
            this.c.startScroll(0, bottomMargin, 0, -bottomMargin, 400);
            invalidate();
        }
    }

    private void i() {
        int state = this.j.getState();
        if (this.e != null && state != 2) {
            this.l = true;
            this.j.setState(2);
            this.e.b();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b == -1.0f) {
            this.b = motionEvent.getRawY();
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.b = motionEvent.getRawY();
                this.p = false;
                break;
            case 1:
            case 3:
                this.p = false;
                this.b = -1.0f;
                if (getFirstVisiblePosition() == 0) {
                    if (this.h && this.f.getVisiableHeight() > this.f.getTriggerHeight()) {
                        int state = this.f.getState();
                        if (!(this.e == null || state == 2)) {
                            this.i = true;
                            this.f.setState(2);
                            this.e.a();
                        }
                    }
                } else if (getLastVisiblePosition() == this.n - 1 && this.k && this.j.getBottomMargin() > 50) {
                    i();
                }
                g();
                h();
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.b;
                this.b = motionEvent.getRawY();
                if (getFirstVisiblePosition() != 0) {
                    if (getLastVisiblePosition() == this.n - 1 && (this.j.getBottomMargin() > 0 || rawY < 0.0f)) {
                        b((-rawY) / 1.8f);
                        break;
                    }
                }
                if (!this.p && rawY > 0.0f && this.f.getTop() >= 0 && this.f.getParent() != null) {
                    this.p = true;
                }
                if (this.p) {
                    if (rawY > 0.0f) {
                        rawY /= 1.8f;
                    }
                    a(rawY);
                    f();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void computeScroll() {
        if (this.c.computeScrollOffset()) {
            if (this.o == 0) {
                this.f.setVisiableHeight(this.c.getCurrY());
            } else {
                this.j.setBottomMargin(this.c.getCurrY());
            }
            postInvalidate();
            f();
        }
        super.computeScroll();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.d = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.d != null) {
            this.d.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.n = i3;
        if (this.d != null) {
            this.d.onScroll(absListView, i, i2, i3);
        }
    }

    public void setXListViewListener(a aVar) {
        this.e = aVar;
    }
}
