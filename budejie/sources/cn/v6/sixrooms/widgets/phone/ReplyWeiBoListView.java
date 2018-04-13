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

public class ReplyWeiBoListView extends LinearLayout {
    private RotateAnimation A;
    private OnFooterRefreshListener B;
    private OnHeaderRefreshListener C;
    private Scroller D;
    private Context E;
    private ViewGroup F;
    private final int G = 250;
    private boolean H = false;
    private int a = 14;
    private int b = 24;
    private int c;
    private boolean d = false;
    private boolean e = false;
    private RotateAnimation f;
    private RotateAnimation g;
    private boolean h;
    private boolean i;
    private int j;
    private View k;
    private View l;
    private AdapterView<?> m;
    private ScrollView n;
    private int o;
    private int p;
    private ImageView q;
    private ImageView r;
    private ImageView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private ProgressBar w;
    private ProgressBar x;
    private LayoutInflater y;
    private RotateAnimation z;

    public interface OnHeaderRefreshListener {
        void onHeaderRefresh(ReplyWeiBoListView replyWeiBoListView);
    }

    public interface OnFooterRefreshListener {
        void onFooterRefresh(ReplyWeiBoListView replyWeiBoListView);
    }

    public ImageView getFoot_line() {
        return this.s;
    }

    public ReplyWeiBoListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = context;
        a();
    }

    public ReplyWeiBoListView(Context context) {
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
        this.f = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        this.f.setInterpolator(new LinearInterpolator());
        this.f.setDuration(250);
        this.f.setFillAfter(true);
        this.g = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.g.setInterpolator(new LinearInterpolator());
        this.g.setDuration(250);
        this.g.setFillAfter(true);
        this.y = LayoutInflater.from(getContext());
        this.k = this.y.inflate(R.layout.refresh_layout_header_weibo, this, false);
        this.q = (ImageView) this.k.findViewById(R.id.pull_to_refresh_image);
        this.t = (TextView) this.k.findViewById(R.id.pull_to_refresh_text);
        this.u = (TextView) this.k.findViewById(R.id.pull_to_refresh_time);
        this.w = (ProgressBar) this.k.findViewById(R.id.pull_to_refresh_progress);
        a(this.k);
        this.o = this.k.getMeasuredHeight();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.o);
        layoutParams.topMargin = -this.o;
        addView(this.k, layoutParams);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.l = this.y.inflate(R.layout.refresh_layout_footer_weibo, this, false);
        this.s = (ImageView) this.l.findViewById(R.id.foot_line);
        this.r = (ImageView) this.l.findViewById(R.id.pull_to_load_image);
        this.v = (TextView) this.l.findViewById(R.id.pull_to_load_text);
        this.x = (ProgressBar) this.l.findViewById(R.id.pull_to_load_progress);
        a(this.l);
        this.p = this.l.getMeasuredHeight();
        addView(this.l, new LinearLayout.LayoutParams(-1, this.p));
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
                this.m = (AdapterView) childAt;
            }
            if (childAt instanceof ScrollView) {
                this.n = (ScrollView) childAt;
            }
        }
        if (this.m != null) {
            this.F = this.m;
        } else {
            this.F = this.n;
        }
        if (this.m == null && this.n == null) {
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
        boolean z2;
        if (this.a == 13 || this.i) {
            z = false;
        } else {
            if (this.m != null) {
                View childAt = this.m.getChildAt(0);
                if (childAt == null) {
                    z = true;
                } else if (this.m.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
                    z = true;
                } else {
                    int top = childAt.getTop();
                    int paddingTop = this.m.getPaddingTop();
                    if (this.m.getFirstVisiblePosition() == 0 && Math.abs(top - paddingTop) <= 8) {
                        z = true;
                    }
                }
            }
            if (this.n == null || this.n.getScrollY() != 0) {
                z = false;
            } else {
                z = true;
            }
        }
        if (this.b == 23 || this.h) {
            z2 = false;
        } else {
            if (this.m != null) {
                View childAt2 = this.m.getChildAt(this.m.getChildCount() - 1);
                if (childAt2 != null) {
                    if (childAt2.getBottom() <= getHeight() && this.m.getLastVisiblePosition() == this.m.getCount() - 1) {
                        z2 = true;
                    }
                }
                z2 = false;
            }
            if (this.n != null && this.n.getChildAt(0).getMeasuredHeight() <= getHeight() + this.n.getScrollY()) {
                z2 = true;
            }
            z2 = false;
        }
        int rawY = (int) motionEvent.getRawY();
        int scrollY = getScrollY();
        switch (motionEvent.getAction()) {
            case 0:
                this.j = rawY;
                this.H = false;
                break;
            case 1:
            case 3:
                paddingTop = getScrollY();
                if (this.c == -2) {
                    if (Math.abs(paddingTop) >= this.o) {
                        this.D.startScroll(0, paddingTop, 0, -(this.o + paddingTop), 250);
                        this.a = 13;
                        e();
                        if (this.C != null) {
                            this.C.onHeaderRefresh(this);
                        }
                    } else {
                        this.D.startScroll(0, paddingTop, 0, -paddingTop, 250);
                        this.a = 14;
                        e();
                    }
                } else if (this.c == -1) {
                    if (Math.abs(paddingTop) >= this.p) {
                        this.D.startScroll(0, paddingTop, 0, -(paddingTop - this.p), 250);
                        this.b = 23;
                        f();
                        if (this.B != null) {
                            this.B.onFooterRefresh(this);
                        }
                    } else {
                        this.D.startScroll(0, paddingTop, 0, -paddingTop, 250);
                        this.b = 24;
                        f();
                    }
                }
                invalidate();
                this.c = 0;
                break;
            case 2:
                int i = rawY - this.j;
                if (z && i > 0 && this.c != -1) {
                    this.c = -2;
                    top = scrollY - (i / 2);
                    if (top < 0) {
                        this.H = true;
                        scrollTo(0, top);
                        c();
                    } else {
                        this.H = false;
                        scrollTo(0, 0);
                    }
                } else if (z && this.c == -2 && scrollY != 0 && i < 0) {
                    top = scrollY - (i / 2);
                    if (top < 0) {
                        this.H = true;
                        scrollTo(0, top);
                        c();
                    } else {
                        this.H = false;
                        scrollTo(0, 0);
                    }
                } else if (z2 && i < 0) {
                    this.c = -1;
                    top = scrollY - (i / 2);
                    if (top > 0) {
                        this.H = true;
                        scrollTo(0, top);
                        d();
                    } else {
                        this.H = false;
                        scrollTo(0, 0);
                    }
                } else if (z2 && this.c == -1 && scrollY != 0 && i > 0) {
                    top = scrollY - (i / 2);
                    if (top > 0) {
                        this.H = true;
                        scrollTo(0, top);
                        d();
                    } else {
                        this.H = false;
                        scrollTo(0, 0);
                    }
                }
                this.j = rawY;
                break;
        }
        if (!this.H) {
            this.F.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    private void c() {
        int scrollY = getScrollY();
        if (Math.abs(scrollY) <= this.o && this.a == 14) {
            this.a = 11;
            e();
        } else if (Math.abs(scrollY) > this.o && this.a == 11) {
            this.a = 12;
            e();
        } else if (Math.abs(scrollY) <= this.o && this.a == 12) {
            this.a = 11;
            this.d = true;
            e();
        }
    }

    private void d() {
        int scrollY = getScrollY();
        if (Math.abs(scrollY) <= this.p && this.b == 24) {
            this.b = 21;
            f();
        } else if (Math.abs(scrollY) > this.p && this.b == 21) {
            this.b = 22;
            f();
        } else if (Math.abs(scrollY) <= this.p && this.b == 22) {
            this.e = true;
            this.b = 21;
            f();
        }
    }

    private void e() {
        switch (this.a) {
            case 11:
                if (this.d) {
                    this.q.startAnimation(this.A);
                }
                this.t.setText("下拉可以刷新");
                return;
            case 12:
                this.q.startAnimation(this.z);
                this.t.setText("松开即可刷新");
                return;
            case 13:
                this.q.clearAnimation();
                this.q.setVisibility(4);
                this.t.setText("加载中...");
                this.w.setVisibility(0);
                return;
            case 14:
                this.d = false;
                this.q.setVisibility(0);
                this.t.setText("下拉可以刷新");
                this.w.setVisibility(4);
                return;
            default:
                return;
        }
    }

    private void f() {
        switch (this.b) {
            case 21:
                if (this.e) {
                    this.r.startAnimation(this.g);
                }
                this.v.setText("上拉加载全部");
                return;
            case 22:
                this.r.startAnimation(this.f);
                this.v.setText("松开即可加载");
                return;
            case 23:
                this.r.clearAnimation();
                this.r.setVisibility(4);
                this.v.setText("加载中...");
                this.x.setVisibility(0);
                return;
            case 24:
                this.e = false;
                this.r.setVisibility(0);
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
        this.a = 14;
        e();
    }

    public void onFooterRefreshComplete() {
        int scrollY = getScrollY();
        this.D.startScroll(0, scrollY, 0, -scrollY, 250);
        this.b = 24;
        f();
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
        if (this.i != z) {
            this.i = z;
            if (z) {
                onHeaderRefreshComplete();
                this.k.setVisibility(8);
                return;
            }
            this.k.setVisibility(0);
        }
    }

    public void isBanPullUpRefresh(boolean z) {
        if (this.h != z) {
            this.h = z;
            if (z) {
                onFooterRefreshComplete();
                this.l.setVisibility(8);
                return;
            }
            this.l.setVisibility(0);
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
}
