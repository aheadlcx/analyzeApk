package com.budejie.www.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.view.TabWidget;
import com.budejie.www.http.i;
import com.budejie.www.util.an;
import com.nostra13.universalimageloader.core.d;

@SuppressLint({"NewApi"})
public class XListView extends ListView implements OnScrollListener {
    public static boolean a = true;
    private float A;
    private int B;
    private int C;
    private b D;
    private LinearLayout b;
    private float c = -1.0f;
    private float d = -1.0f;
    private long e = 0;
    private Scroller f;
    private Context g;
    private OnScrollListener h;
    private a i;
    private XListViewHeader j;
    private RelativeLayout k;
    private XListViewBlankHeader l;
    private TextView m;
    private int n;
    private boolean o = true;
    private boolean p = false;
    private XListViewFooter q;
    private boolean r;
    private boolean s;
    private boolean t = false;
    private int u;
    private int v;
    private long w = System.currentTimeMillis();
    private boolean x = false;
    private TabWidget y;
    private Handler z = new Handler(this) {
        final /* synthetic */ XListView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.y.setPadding(0, 0, 0, 0);
                    this.a.y.setVisibility(0);
                    return;
                default:
                    return;
            }
        }
    };

    public interface a {
        void a();

        void b();
    }

    public interface b {
        void a();

        void b();

        void c();
    }

    public interface c extends OnScrollListener {
        void a(View view);
    }

    public XListView(Context context) {
        super(context);
        this.g = context;
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
        this.f = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.g = context;
        this.j = new XListViewHeader(context);
        this.k = (RelativeLayout) this.j.findViewById(R.id.xlistview_header_content);
        this.m = (TextView) this.j.findViewById(R.id.xlistview_header_time);
        this.b = (LinearLayout) this.j.findViewById(R.id.time_layout);
        this.l = new XListViewBlankHeader(context);
        addHeaderView(this.l);
        addHeaderView(this.j);
        this.q = new XListViewFooter(context);
        this.j.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ XListView a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                this.a.n = this.a.k.getHeight();
                this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        if (VERSION.SDK_INT >= 11) {
            setOverScrollMode(2);
        } else {
            setHorizontalFadingEdgeEnabled(false);
        }
    }

    public void a() {
        if (this.l != null) {
            this.l.a();
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!this.t) {
            this.t = true;
            addFooterView(this.q);
        }
        super.setAdapter(listAdapter);
    }

    public void setPullRefreshEnable(boolean z) {
        this.o = z;
        if (this.o) {
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(4);
        }
    }

    public void setPullLoadEnable(boolean z) {
        this.r = z;
        if (this.r) {
            this.s = false;
            this.q.b();
            this.q.setState(0);
            this.q.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ XListView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.h();
                }
            });
            return;
        }
        this.q.a();
        this.q.setOnClickListener(null);
    }

    public void a(String str, OnClickListener onClickListener) {
        this.s = false;
        this.q.b();
        this.q.getHintView().setVisibility(0);
        this.q.getProgressBar().setVisibility(4);
        this.q.getHintView().setText(str);
        this.q.getProgressBar().setVisibility(8);
        this.q.setOnClickListener(onClickListener);
    }

    public void b(String str, OnClickListener onClickListener) {
        this.s = false;
        this.q.b();
        this.q.getContentChildView().setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.list_item_bg));
        this.q.getHintView().setVisibility(0);
        this.q.getHintView().setTextColor(com.budejie.www.h.c.a().c(R.attr.theme_red_color));
        this.q.getHintView().setText(str);
        this.q.getProgressBar().setVisibility(8);
        this.q.setOnClickListener(onClickListener);
    }

    public void setFooterViewState(int i) {
        this.q.b();
        this.q.setState(i);
    }

    public void b() {
        if (this.p) {
            this.p = false;
            f();
        }
    }

    public void a(boolean z) {
        b();
        if (z) {
            this.w = System.currentTimeMillis();
        }
    }

    public void c() {
        if (this.s) {
            this.s = false;
            this.q.setState(0);
        }
    }

    public void setRefreshTime(String str) {
        this.m.setText(str);
    }

    public void setLastRefreshTime(long j) {
        this.w = j;
    }

    private void e() {
        if (this.h instanceof c) {
            ((c) this.h).a(this);
        }
    }

    private void a(float f) {
        this.j.setVisiableHeight(((int) f) + this.j.getVisiableHeight());
        if (this.o && !this.p) {
            setRefreshTime(com.budejie.www.widget.a.a.a(this.w));
            if (this.j.getVisiableHeight() > this.n) {
                this.j.setState(1);
            } else {
                this.j.setState(0);
            }
        }
        setSelection(0);
    }

    private void f() {
        int visiableHeight = this.j.getVisiableHeight();
        if (visiableHeight != 0) {
            if (!this.p || visiableHeight > this.n) {
                int i;
                if (!this.p || visiableHeight < this.n) {
                    i = 0;
                } else {
                    i = this.n;
                }
                this.v = 0;
                this.f.startScroll(0, visiableHeight, 0, i - visiableHeight, 400);
                invalidate();
            }
        }
    }

    private void b(float f) {
        int bottomMargin = this.q.getBottomMargin() + ((int) f);
        if (this.r && !this.s) {
            if (bottomMargin > 50) {
                this.q.setState(1);
            } else {
                this.q.setState(0);
            }
            this.q.setBottomMargin(bottomMargin);
        }
    }

    private void g() {
        int bottomMargin = this.q.getBottomMargin();
        if (bottomMargin > 0) {
            this.v = 1;
            this.f.startScroll(0, bottomMargin, 0, -bottomMargin, 400);
            invalidate();
        }
    }

    private void h() {
        this.s = true;
        this.q.setState(2);
        if (this.i != null) {
            this.i.b();
            i.a(this.g.getString(R.string.track_event_load_more), this.g.getString(R.string.track_event_load_more));
        }
    }

    public void a(TabWidget tabWidget) {
        int dimension;
        this.y = tabWidget;
        if (this.g != null) {
            dimension = (int) this.g.getResources().getDimension(R.dimen.tabwidgt_height);
        } else {
            dimension = 0;
        }
        this.y.setPadding(0, dimension * -1, 0, 0);
        this.y.setVisibility(8);
        addHeaderView(this.y);
        if (dimension > 0) {
            this.z.sendMessageDelayed(this.z.obtainMessage(0), 1500);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            this.d = motionEvent.getY();
            this.e = System.currentTimeMillis();
        }
        return onInterceptTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.c == -1.0f) {
            this.c = motionEvent.getRawY();
        }
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.c = motionEvent.getRawY();
                if (this.A == 0.0f) {
                    this.A = y;
                    break;
                }
                break;
            case 1:
            case 3:
                if (this.D != null && ((double) (Math.abs(this.d - y) / ((float) (System.currentTimeMillis() - this.e)))) > 2.0d) {
                    this.D.c();
                }
                this.A = 0.0f;
                this.d = -1.0f;
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.c;
                this.c = motionEvent.getRawY();
                if (getFirstVisiblePosition() == 0 && (this.j.getVisiableHeight() > 0 || rawY > 0.0f)) {
                    a(rawY / 1.8f);
                    e();
                } else if (getLastVisiblePosition() == this.u - 1 && (this.q.getBottomMargin() > 0 || rawY < 0.0f)) {
                    b((-rawY) / 1.8f);
                }
                if (this.A == 0.0f) {
                    this.A = y;
                }
                if (!(this.A == 0.0f || this.D == null)) {
                    if (this.A > y && this.A - y > ((float) this.B) && !this.x && getFirstVisiblePosition() != 0) {
                        this.D.a();
                        this.A = y;
                        this.x = true;
                    }
                    if (this.A < y && y - this.A > ((float) this.C) && this.x) {
                        this.D.b();
                        this.A = y;
                        this.x = false;
                        break;
                    }
                }
                break;
        }
        this.c = -1.0f;
        if (getFirstVisiblePosition() == 0) {
            if (this.o && this.j.getVisiableHeight() > this.n && !this.p) {
                this.p = true;
                this.j.setState(2);
                if (this.i != null) {
                    this.i.a();
                }
            }
            f();
        } else if (getLastVisiblePosition() == this.u - 1) {
            if (this.r && this.q.getBottomMargin() > 50) {
                h();
            }
            g();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void d() {
        if (!(this.i == null || this.p)) {
            this.p = true;
            this.i.a();
        }
        setRefreshTime(com.budejie.www.widget.a.a.a(this.w));
        this.j.setVisiableHeight(this.n);
        this.j.setState(2);
        setSelection(0);
        d.a().h();
    }

    public void computeScroll() {
        if (this.f.computeScrollOffset()) {
            if (this.v == 0) {
                this.j.setVisiableHeight(this.f.getCurrY());
            } else {
                this.q.setBottomMargin(this.f.getCurrY());
            }
            postInvalidate();
            e();
        }
        super.computeScroll();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.h = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.h != null) {
            this.h.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.u = i3;
        if (this.h != null) {
            this.h.onScroll(absListView, i, i2, i3);
        }
        if (a && absListView.getAdapter() != null && this.r && !this.s && absListView.getLastVisiblePosition() <= ((ListAdapter) absListView.getAdapter()).getCount() - 1 && ((ListAdapter) absListView.getAdapter()).getCount() >= 10 && absListView.getLastVisiblePosition() >= ((ListAdapter) absListView.getAdapter()).getCount() - 3) {
            h();
        }
    }

    public void setXListViewListener(a aVar) {
        this.i = aVar;
    }

    public void setHeaderTimeEnable(boolean z) {
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(4);
        }
    }

    public void setOnScrollDirectionListener(b bVar) {
        this.B = an.a(getContext(), 1);
        this.C = an.a(getContext(), 1);
        this.D = bVar;
    }
}
