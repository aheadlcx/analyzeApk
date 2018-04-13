package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.utils.LogUtils;

public class WeiBoListView extends LinearLayout {
    private static final String a = WeiBoListView.class.getSimpleName();
    private RotateAnimation A;
    private OnFooterRefreshListener B;
    private OnHeaderRefreshListener C;
    private Scroller D;
    private Context E;
    private ViewGroup F;
    private final int G = 250;
    private boolean H = false;
    private onMyClickListener I;
    private int b = 14;
    private int c = 24;
    private int d;
    private boolean e = false;
    private boolean f = false;
    private RotateAnimation g;
    private RotateAnimation h;
    private boolean i;
    private boolean j;
    private int k;
    private View l;
    private View m;
    private AdapterView<?> n;
    private ScrollView o;
    private int p;
    private int q;
    private ImageView r;
    private ImageView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private ProgressBar w;
    private ProgressBar x;
    private LayoutInflater y;
    private RotateAnimation z;

    public interface OnFooterRefreshListener {
        void onFooterRefresh(WeiBoListView weiBoListView);
    }

    public interface OnHeaderRefreshListener {
        void onHeaderRefresh(WeiBoListView weiBoListView);
    }

    public interface onMyClickListener {
        boolean isClick();
    }

    public WeiBoListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = context;
        a();
    }

    public WeiBoListView(Context context) {
        super(context);
        this.E = context;
        a();
    }

    private void a() {
        this.D = new Scroller(this.E);
        this.z = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.z.setInterpolator(new LinearInterpolator());
        this.z.setDuration(250);
        this.z.setFillAfter(true);
        this.A = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.A.setInterpolator(new LinearInterpolator());
        this.A.setDuration(250);
        this.A.setFillAfter(true);
        this.g = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        this.g.setInterpolator(new LinearInterpolator());
        this.g.setDuration(250);
        this.g.setFillAfter(true);
        this.h = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.h.setInterpolator(new LinearInterpolator());
        this.h.setDuration(250);
        this.h.setFillAfter(true);
        this.y = LayoutInflater.from(getContext());
        this.l = this.y.inflate(R.layout.refresh_layout_header_weibo, this, false);
        this.r = (ImageView) this.l.findViewById(R.id.pull_to_refresh_image);
        this.t = (TextView) this.l.findViewById(R.id.pull_to_refresh_text);
        this.u = (TextView) this.l.findViewById(R.id.pull_to_refresh_time);
        this.w = (ProgressBar) this.l.findViewById(R.id.pull_to_refresh_progress);
        a(this.l);
        this.p = this.l.getMeasuredHeight();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.p);
        layoutParams.topMargin = -this.p;
        addView(this.l, layoutParams);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.m = this.y.inflate(R.layout.refresh_layout_footer_weibo, this, false);
        this.s = (ImageView) this.m.findViewById(R.id.pull_to_load_image);
        this.v = (TextView) this.m.findViewById(R.id.pull_to_load_text);
        this.x = (ProgressBar) this.m.findViewById(R.id.pull_to_load_progress);
        a(this.m);
        this.q = this.m.getMeasuredHeight();
        addView(this.m, new LinearLayout.LayoutParams(-1, this.q));
        b();
    }

    private void b() {
        int childCount = getChildCount();
        if (childCount < 3) {
            throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof AdapterView) {
                this.n = (AdapterView) childAt;
            }
            if (childAt instanceof ScrollView) {
                this.o = (ScrollView) childAt;
            }
        }
        if (this.n != null) {
            this.F = this.n;
        } else {
            this.F = this.o;
        }
        if (this.n == null && this.o == null) {
            throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
        }
    }

    private static void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int paddingTop;
        boolean z2;
        if (this.b == 13 || this.j) {
            z = false;
        } else {
            if (this.n != null) {
                View childAt = this.n.getChildAt(0);
                if (childAt == null) {
                    z = true;
                } else if (this.n.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
                    z = true;
                } else {
                    int top = childAt.getTop();
                    paddingTop = this.n.getPaddingTop();
                    if (this.n.getFirstVisiblePosition() == 0 && Math.abs(top - paddingTop) <= 8) {
                        z = true;
                    }
                }
            }
            if (this.o == null || this.o.getScrollY() != 0) {
                z = false;
            } else {
                z = true;
            }
        }
        if (this.c == 23 || this.i) {
            z2 = false;
        } else {
            if (this.n != null) {
                View childAt2 = this.n.getChildAt(this.n.getChildCount() - 1);
                if (childAt2 != null) {
                    if (childAt2.getBottom() <= getHeight() && this.n.getLastVisiblePosition() == this.n.getCount() - 1) {
                        z2 = true;
                    }
                }
                z2 = false;
            }
            if (this.o != null && this.o.getChildAt(0).getMeasuredHeight() <= getHeight() + this.o.getScrollY()) {
                z2 = true;
            }
            z2 = false;
        }
        int rawY = (int) motionEvent.getRawY();
        int scrollY = getScrollY();
        switch (motionEvent.getAction()) {
            case 0:
                this.k = rawY;
                this.H = false;
                break;
            case 1:
                LogUtils.i(a, "MotionEvent.ACTION_UP");
                if (this.I != null) {
                    this.H = this.I.isClick();
                    break;
                }
                break;
            case 2:
                int i = rawY - this.k;
                if (z && Math.abs(i) > 0) {
                    this.d = 1;
                    top = scrollY - (i / 2);
                    if (top < 0) {
                        this.H = true;
                        scrollTo(0, top);
                        top = getScrollY();
                        if (Math.abs(top) <= this.p && this.b == 14) {
                            this.b = 11;
                            c();
                        } else if (Math.abs(top) > this.p && this.b == 11) {
                            this.b = 12;
                            c();
                        } else if (Math.abs(top) <= this.p && this.b == 12) {
                            this.b = 11;
                            this.e = true;
                            c();
                        }
                    } else {
                        this.H = false;
                        scrollTo(0, 0);
                    }
                } else if (z2 && Math.abs(i) > 0) {
                    this.d = 0;
                    top = scrollY - (i / 2);
                    if (top > 0) {
                        this.H = true;
                        scrollTo(0, top);
                        top = getScrollY();
                        if (Math.abs(top) <= this.q && this.c == 24) {
                            this.c = 21;
                            d();
                        } else if (Math.abs(top) > this.q && this.c == 21) {
                            this.c = 22;
                            d();
                        } else if (Math.abs(top) <= this.q && this.c == 22) {
                            this.f = true;
                            this.c = 21;
                            d();
                        }
                    } else {
                        this.H = false;
                        scrollTo(0, 0);
                    }
                }
                this.k = rawY;
                break;
            case 3:
                break;
        }
        paddingTop = getScrollY();
        if (this.d == 1) {
            if (Math.abs(paddingTop) >= this.p) {
                this.D.startScroll(0, paddingTop, 0, -(this.p + paddingTop), 250);
                this.b = 13;
                c();
                if (this.C != null) {
                    this.C.onHeaderRefresh(this);
                }
            } else {
                this.D.startScroll(0, paddingTop, 0, -paddingTop, 250);
                this.b = 14;
                c();
            }
        } else if (this.d == 0) {
            if (Math.abs(paddingTop) >= this.q) {
                this.D.startScroll(0, paddingTop, 0, -(paddingTop - this.q), 250);
                this.c = 23;
                d();
                if (this.B != null) {
                    this.B.onFooterRefresh(this);
                }
            } else {
                this.D.startScroll(0, paddingTop, 0, -paddingTop, 250);
                this.c = 24;
                d();
            }
        }
        invalidate();
        if (!this.H) {
            this.F.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    private void c() {
        switch (this.b) {
            case 11:
                if (this.e) {
                    this.r.startAnimation(this.A);
                }
                this.t.setText("下拉可以刷新");
                return;
            case 12:
                this.r.startAnimation(this.z);
                this.t.setText("松开即可刷新");
                return;
            case 13:
                this.r.clearAnimation();
                this.r.setVisibility(4);
                this.t.setText("加载中...");
                this.w.setVisibility(0);
                return;
            case 14:
                this.e = false;
                this.r.setVisibility(0);
                this.t.setText("下拉可以刷新");
                this.w.setVisibility(4);
                return;
            default:
                return;
        }
    }

    private void d() {
        switch (this.c) {
            case 21:
                if (this.f) {
                    this.s.startAnimation(this.h);
                }
                this.v.setText("上拉加载全部");
                return;
            case 22:
                this.s.startAnimation(this.g);
                this.v.setText("松开即可加载");
                return;
            case 23:
                this.s.clearAnimation();
                this.s.setVisibility(4);
                this.v.setText("加载中...");
                this.x.setVisibility(0);
                return;
            case 24:
                this.f = false;
                this.s.setVisibility(0);
                this.v.setText("上拉加载全部");
                this.x.setVisibility(4);
                return;
            default:
                return;
        }
    }

    public void onHeaderRefreshComplete() {
        int scrollY = getScrollY();
        this.D.startScroll(0, scrollY, 0, -scrollY, 250);
        this.b = 14;
        c();
    }

    public void onFooterRefreshComplete() {
        int scrollY = getScrollY();
        this.D.startScroll(0, scrollY, 0, -scrollY, 250);
        this.c = 24;
        d();
    }

    public void onHeaderRefreshComplete(CharSequence charSequence) {
        setLastUpdated(charSequence);
        onHeaderRefreshComplete();
    }

    public void setLastUpdated(CharSequence charSequence) {
        if (charSequence != null) {
            this.u.setVisibility(0);
            this.u.setText(String.format(this.E.getResources().getString(R.string.pull_to_refresh_time_text), new Object[]{charSequence}));
            return;
        }
        this.u.setVisibility(8);
    }

    public void isBanPullToRefresh(boolean z) {
        if (this.j != z) {
            this.j = z;
            if (z) {
                onHeaderRefreshComplete();
                this.l.setVisibility(8);
                return;
            }
            this.l.setVisibility(0);
        }
    }

    public void isBanPullUpRefresh(boolean z) {
        if (this.i != z) {
            this.i = z;
            if (z) {
                onFooterRefreshComplete();
                this.m.setVisibility(8);
                return;
            }
            this.m.setVisibility(0);
        }
    }

    public void computeScroll() {
        if (this.D.computeScrollOffset()) {
            scrollTo(0, this.D.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public void setOnHeaderRefreshListener(OnHeaderRefreshListener onHeaderRefreshListener) {
        this.C = onHeaderRefreshListener;
    }

    public void setOnFooterRefreshListener(OnFooterRefreshListener onFooterRefreshListener) {
        this.B = onFooterRefreshListener;
    }

    public void setOnMyClickListener(onMyClickListener onmyclicklistener) {
        this.I = onmyclicklistener;
    }
}
