package com.bdj.picture.edit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.bdj.picture.edit.a.d;
import java.util.Date;

public class XListView extends ListView implements OnScrollListener {
    private float a = -1.0f;
    private Scroller b;
    private OnScrollListener c;
    private XListView$a d;
    private XListViewHeader e;
    private RelativeLayout f;
    private TextView g;
    private int h;
    private boolean i = true;
    private boolean j = false;
    private XListViewFooter k;
    private boolean l;
    private boolean m;
    private boolean n = false;
    private boolean o = false;
    private int p;
    private int q;
    private long r = System.currentTimeMillis();
    private float s;
    private int t;
    private int u;
    private b v;

    public interface b {
        void a();

        void b();
    }

    public interface c extends OnScrollListener {
        void a(View view);
    }

    public XListView(Context context) {
        super(context);
        a(context);
    }

    public XListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public XListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.b = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.e = new XListViewHeader(context);
        this.f = (RelativeLayout) this.e.findViewById(d.mgc_xlistview_header_content);
        this.g = (TextView) this.e.findViewById(d.mgc_xlistview_header_time);
        addHeaderView(this.e);
        this.k = new XListViewFooter(context);
        this.e.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ XListView a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                this.a.h = this.a.f.getHeight();
                this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!this.n) {
            this.n = true;
            addFooterView(this.k);
        }
        super.setAdapter(listAdapter);
    }

    public void setHasNoMore(boolean z) {
        this.o = z;
        if (this.o) {
            this.k.setState(3);
        } else {
            this.k.setState(0);
        }
    }

    public void setPullRefreshEnable(boolean z) {
        this.i = z;
        if (this.i) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(4);
        }
    }

    public void setPullLoadEnable(boolean z) {
        boolean z2 = false;
        this.l = z;
        if (this.l) {
            this.m = false;
            this.k.b();
            this.k.setState(0);
            this.k.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ XListView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.f();
                }
            });
            return;
        }
        this.k.a();
        this.k.setOnClickListener(null);
        if (!this.l) {
            z2 = true;
        }
        setHasNoMore(z2);
    }

    public void a() {
        if (this.j) {
            this.j = false;
            d();
        }
    }

    public void a(boolean z) {
        if (this.m) {
            this.m = false;
            setHasNoMore(z);
        }
    }

    public void setRefreshTime(String str) {
        this.g.setText(str);
    }

    public void setLastRefreshTime(long j) {
        this.r = j;
    }

    private void c() {
        if (this.c instanceof c) {
            ((c) this.c).a(this);
        }
    }

    private void a(float f) {
        this.e.setVisiableHeight(((int) f) + this.e.getVisiableHeight());
        if (this.i && !this.j) {
            setRefreshTime(com.bdj.picture.edit.util.c.a(new Date(this.r)));
            if (this.e.getVisiableHeight() > this.h) {
                this.e.setState(1);
            } else {
                this.e.setState(0);
            }
        }
        setSelection(0);
    }

    private void d() {
        int visiableHeight = this.e.getVisiableHeight();
        if (visiableHeight != 0) {
            if (!this.j || visiableHeight > this.h) {
                int i;
                if (!this.j || visiableHeight <= this.h) {
                    i = 0;
                } else {
                    i = this.h;
                }
                this.q = 0;
                this.b.startScroll(0, visiableHeight, 0, i - visiableHeight, 400);
                invalidate();
            }
        }
    }

    private void b(float f) {
        int bottomMargin = this.k.getBottomMargin() + ((int) f);
        if (this.l && !this.m) {
            if (!this.o && bottomMargin > 50) {
                this.k.setState(1);
            } else if (this.o) {
                this.k.setState(3);
            } else {
                this.k.setState(0);
            }
        }
        this.k.setBottomMargin(bottomMargin);
    }

    private void e() {
        int bottomMargin = this.k.getBottomMargin();
        if (bottomMargin > 0) {
            this.q = 1;
            this.b.startScroll(0, bottomMargin, 0, -bottomMargin, 400);
            invalidate();
        }
    }

    private void f() {
        if (!this.o) {
            this.m = true;
            this.k.setState(2);
            if (this.d != null) {
                this.d.c();
            }
        }
    }

    public void b() {
        if (!(this.d == null || this.j)) {
            this.j = true;
            this.d.b();
        }
        this.e.setVisiableHeight(this.h);
        this.e.setState(2);
        setSelection(0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.a == -1.0f) {
            this.a = motionEvent.getRawY();
        }
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.a = motionEvent.getRawY();
                if (this.s == 0.0f) {
                    this.s = y;
                    break;
                }
                break;
            case 1:
            case 3:
                this.s = 0.0f;
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.a;
                this.a = motionEvent.getRawY();
                if (getFirstVisiblePosition() == 0 && (this.e.getVisiableHeight() > 0 || rawY > 0.0f)) {
                    if (this.v != null) {
                        this.v.b();
                    }
                    a(rawY / 1.8f);
                    c();
                } else if (getLastVisiblePosition() == this.p - 1 && (this.k.getBottomMargin() > 0 || rawY < 0.0f)) {
                    b((-rawY) / 1.8f);
                }
                if (this.s == 0.0f) {
                    this.s = y;
                }
                if (!(this.s == 0.0f || this.v == null)) {
                    if (this.s > y && this.s - y > ((float) this.t) && getFirstVisiblePosition() != 0) {
                        if (this.v != null) {
                            this.v.a();
                        }
                        this.s = y;
                    }
                    if (this.s < y && y - this.s > ((float) this.u)) {
                        this.s = y;
                        break;
                    }
                }
                break;
        }
        this.a = -1.0f;
        if (getFirstVisiblePosition() == 0) {
            if (this.i && this.e.getVisiableHeight() > this.h && !this.j) {
                this.j = true;
                this.e.setState(2);
                if (this.d != null) {
                    this.d.b();
                }
            }
            d();
        } else if (getLastVisiblePosition() == this.p - 1) {
            if (this.l && this.k.getBottomMargin() > 50) {
                f();
            }
            e();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void computeScroll() {
        if (this.b.computeScrollOffset()) {
            if (this.q == 0) {
                this.e.setVisiableHeight(this.b.getCurrY());
            } else {
                this.k.setBottomMargin(this.b.getCurrY());
            }
            postInvalidate();
            c();
        }
        super.computeScroll();
    }

    public int getHeaderVisiableHeight() {
        if (this.e != null) {
            return this.e.getVisiableHeight();
        }
        return 0;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.c = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.c != null) {
            this.c.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.p = i3;
        if (this.c != null) {
            this.c.onScroll(absListView, i, i2, i3);
        }
    }

    public void setXListViewListener(XListView$a xListView$a) {
        this.d = xListView$a;
    }

    public void setOnScrollDirectionListener(b bVar) {
        this.v = bVar;
    }
}
