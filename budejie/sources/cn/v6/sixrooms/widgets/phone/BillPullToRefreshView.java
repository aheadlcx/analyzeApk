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

public class BillPullToRefreshView extends LinearLayout {
    private Scroller A;
    private Context B;
    private ViewGroup C;
    private final int D = 250;
    private boolean E = false;
    private int a = 14;
    private int b = 24;
    private int c;
    private boolean d = false;
    private boolean e = false;
    private RotateAnimation f;
    private RotateAnimation g;
    private boolean h;
    private int i;
    private View j;
    private View k;
    private AdapterView<?> l;
    private ScrollView m;
    private int n;
    private int o;
    private ImageView p;
    private ImageView q;
    private TextView r;
    private TextView s;
    private ProgressBar t;
    private ProgressBar u;
    private LayoutInflater v;
    private RotateAnimation w;
    private RotateAnimation x;
    private OnFooterRefreshListener y;
    private OnHeaderRefreshListener z;

    public interface OnHeaderRefreshListener {
        void onHeaderRefresh(BillPullToRefreshView billPullToRefreshView);
    }

    public interface OnFooterRefreshListener {
        void onFooterRefresh(BillPullToRefreshView billPullToRefreshView);
    }

    public BillPullToRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = context;
        a();
    }

    public BillPullToRefreshView(Context context) {
        super(context);
        this.B = context;
        a();
    }

    private void a() {
        this.A = new Scroller(this.B);
        this.w = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.w.setInterpolator(new LinearInterpolator());
        this.w.setDuration(250);
        this.w.setFillAfter(true);
        this.x = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.x.setInterpolator(new LinearInterpolator());
        this.x.setDuration(250);
        this.x.setFillAfter(true);
        this.f = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        this.f.setInterpolator(new LinearInterpolator());
        this.f.setDuration(250);
        this.f.setFillAfter(true);
        this.g = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.g.setInterpolator(new LinearInterpolator());
        this.g.setDuration(250);
        this.g.setFillAfter(true);
        this.v = LayoutInflater.from(getContext());
        this.j = this.v.inflate(R.layout.bill_refresh_layout_header, this, false);
        this.p = (ImageView) this.j.findViewById(R.id.pull_to_refresh_image);
        this.r = (TextView) this.j.findViewById(R.id.pull_to_refresh_text);
        this.t = (ProgressBar) this.j.findViewById(R.id.pull_to_refresh_progress);
        a(this.j);
        this.n = this.j.getMeasuredHeight();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.n);
        layoutParams.topMargin = -this.n;
        addView(this.j, layoutParams);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.k = this.v.inflate(R.layout.bill_refresh_layout_footer, this, false);
        this.q = (ImageView) this.k.findViewById(R.id.pull_to_load_image);
        this.s = (TextView) this.k.findViewById(R.id.pull_to_load_text);
        this.u = (ProgressBar) this.k.findViewById(R.id.pull_to_load_progress);
        a(this.k);
        this.o = this.k.getMeasuredHeight();
        addView(this.k, new LinearLayout.LayoutParams(-1, this.o));
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
                this.l = (AdapterView) childAt;
            }
            if (childAt instanceof ScrollView) {
                this.m = (ScrollView) childAt;
            }
        }
        if (this.l != null) {
            this.C = this.l;
        } else {
            this.C = this.m;
        }
        if (this.l == null && this.m == null) {
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
        int rawY;
        int scrollY;
        int scrollY2;
        int i;
        int i2;
        if (this.a != 13) {
            if (this.l != null) {
                View childAt = this.l.getChildAt(0);
                if (childAt != null) {
                    if (this.l.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
                        z = true;
                        if (this.b != 23) {
                        }
                        z2 = false;
                        rawY = (int) motionEvent.getRawY();
                        scrollY = getScrollY();
                        switch (motionEvent.getAction()) {
                            case 0:
                                this.i = rawY;
                                this.E = false;
                                break;
                            case 1:
                            case 3:
                                scrollY2 = getScrollY();
                                if (this.c != 1) {
                                    if (this.c == 0) {
                                        if (Math.abs(scrollY2) < this.o) {
                                            this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                                            this.b = 24;
                                            d();
                                        } else {
                                            this.A.startScroll(0, scrollY2, 0, -(scrollY2 - this.o), 250);
                                            this.b = 23;
                                            d();
                                            if (this.y != null) {
                                                this.y.onFooterRefresh(this);
                                            }
                                        }
                                    }
                                } else if (Math.abs(scrollY2) < this.n) {
                                    this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                                    this.a = 14;
                                    c();
                                } else {
                                    this.A.startScroll(0, scrollY2, 0, -(this.n + scrollY2), 250);
                                    this.a = 13;
                                    c();
                                    if (this.z != null) {
                                        this.z.onHeaderRefresh(this);
                                    }
                                }
                                invalidate();
                                break;
                            case 2:
                                i = rawY - this.i;
                                if (!z) {
                                    break;
                                }
                                this.c = 0;
                                i2 = scrollY - (i / 2);
                                if (i2 <= 0) {
                                    this.E = true;
                                    scrollTo(0, i2);
                                    i2 = getScrollY();
                                    if (Math.abs(i2) > this.o) {
                                        break;
                                    }
                                    if (Math.abs(i2) <= this.o) {
                                        break;
                                    }
                                    this.e = true;
                                    this.b = 21;
                                    d();
                                    break;
                                }
                                this.E = false;
                                scrollTo(0, 0);
                                this.i = rawY;
                                break;
                        }
                        if (!this.E) {
                            this.C.dispatchTouchEvent(motionEvent);
                        }
                        return true;
                    }
                    i2 = childAt.getTop();
                    scrollY2 = this.l.getPaddingTop();
                    if (this.l.getFirstVisiblePosition() == 0 && Math.abs(i2 - scrollY2) <= 8) {
                        z = true;
                        if (this.b != 23 || this.h) {
                            z2 = false;
                        } else {
                            if (this.l != null) {
                                View childAt2 = this.l.getChildAt(this.l.getChildCount() - 1);
                                if (childAt2 != null) {
                                    if (childAt2.getBottom() <= getHeight() && this.l.getLastVisiblePosition() == this.l.getCount() - 1) {
                                        z2 = true;
                                    }
                                }
                                z2 = false;
                            }
                            if (this.m != null && this.m.getChildAt(0).getMeasuredHeight() <= getHeight() + this.m.getScrollY()) {
                                z2 = true;
                            }
                            z2 = false;
                        }
                        rawY = (int) motionEvent.getRawY();
                        scrollY = getScrollY();
                        switch (motionEvent.getAction()) {
                            case 0:
                                this.i = rawY;
                                this.E = false;
                                break;
                            case 1:
                            case 3:
                                scrollY2 = getScrollY();
                                if (this.c != 1) {
                                    if (Math.abs(scrollY2) < this.n) {
                                        this.A.startScroll(0, scrollY2, 0, -(this.n + scrollY2), 250);
                                        this.a = 13;
                                        c();
                                        if (this.z != null) {
                                            this.z.onHeaderRefresh(this);
                                        }
                                    } else {
                                        this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                                        this.a = 14;
                                        c();
                                    }
                                } else if (this.c == 0) {
                                    if (Math.abs(scrollY2) < this.o) {
                                        this.A.startScroll(0, scrollY2, 0, -(scrollY2 - this.o), 250);
                                        this.b = 23;
                                        d();
                                        if (this.y != null) {
                                            this.y.onFooterRefresh(this);
                                        }
                                    } else {
                                        this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                                        this.b = 24;
                                        d();
                                    }
                                }
                                invalidate();
                                break;
                            case 2:
                                i = rawY - this.i;
                                if (!z && Math.abs(i) > 0) {
                                    this.c = 1;
                                    i2 = scrollY - (i / 2);
                                    if (i2 < 0) {
                                        this.E = true;
                                        scrollTo(0, i2);
                                        i2 = getScrollY();
                                        if (Math.abs(i2) <= this.n && this.a == 14) {
                                            this.a = 11;
                                            c();
                                        } else if (Math.abs(i2) > this.n && this.a == 11) {
                                            this.a = 12;
                                            c();
                                        } else if (Math.abs(i2) <= this.n && this.a == 12) {
                                            this.a = 11;
                                            this.d = true;
                                            c();
                                        }
                                    } else {
                                        this.E = false;
                                        scrollTo(0, 0);
                                    }
                                } else if (z2 && Math.abs(i) > 0) {
                                    this.c = 0;
                                    i2 = scrollY - (i / 2);
                                    if (i2 <= 0) {
                                        this.E = true;
                                        scrollTo(0, i2);
                                        i2 = getScrollY();
                                        if (Math.abs(i2) > this.o && this.b == 24) {
                                            this.b = 21;
                                            d();
                                        } else if (Math.abs(i2) <= this.o && this.b == 21) {
                                            this.b = 22;
                                            d();
                                        } else if (Math.abs(i2) <= this.o && this.b == 22) {
                                            this.e = true;
                                            this.b = 21;
                                            d();
                                        }
                                    } else {
                                        this.E = false;
                                        scrollTo(0, 0);
                                    }
                                }
                                this.i = rawY;
                                break;
                        }
                        if (this.E) {
                            this.C.dispatchTouchEvent(motionEvent);
                        }
                        return true;
                    }
                }
            }
            if (this.m != null && this.m.getScrollY() == 0) {
                z = true;
                if (this.b != 23) {
                }
                z2 = false;
                rawY = (int) motionEvent.getRawY();
                scrollY = getScrollY();
                switch (motionEvent.getAction()) {
                    case 0:
                        this.i = rawY;
                        this.E = false;
                        break;
                    case 1:
                    case 3:
                        scrollY2 = getScrollY();
                        if (this.c != 1) {
                            if (Math.abs(scrollY2) < this.n) {
                                this.A.startScroll(0, scrollY2, 0, -(this.n + scrollY2), 250);
                                this.a = 13;
                                c();
                                if (this.z != null) {
                                    this.z.onHeaderRefresh(this);
                                }
                            } else {
                                this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                                this.a = 14;
                                c();
                            }
                        } else if (this.c == 0) {
                            if (Math.abs(scrollY2) < this.o) {
                                this.A.startScroll(0, scrollY2, 0, -(scrollY2 - this.o), 250);
                                this.b = 23;
                                d();
                                if (this.y != null) {
                                    this.y.onFooterRefresh(this);
                                }
                            } else {
                                this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                                this.b = 24;
                                d();
                            }
                        }
                        invalidate();
                        break;
                    case 2:
                        i = rawY - this.i;
                        if (!z) {
                            break;
                        }
                        this.c = 0;
                        i2 = scrollY - (i / 2);
                        if (i2 <= 0) {
                            this.E = true;
                            scrollTo(0, i2);
                            i2 = getScrollY();
                            if (Math.abs(i2) > this.o) {
                                break;
                            }
                            if (Math.abs(i2) <= this.o) {
                                break;
                            }
                            this.e = true;
                            this.b = 21;
                            d();
                            break;
                        }
                        this.E = false;
                        scrollTo(0, 0);
                        this.i = rawY;
                        break;
                }
                if (this.E) {
                    this.C.dispatchTouchEvent(motionEvent);
                }
                return true;
            }
        }
        z = false;
        if (this.b != 23) {
        }
        z2 = false;
        rawY = (int) motionEvent.getRawY();
        scrollY = getScrollY();
        switch (motionEvent.getAction()) {
            case 0:
                this.i = rawY;
                this.E = false;
                break;
            case 1:
            case 3:
                scrollY2 = getScrollY();
                if (this.c != 1) {
                    if (this.c == 0) {
                        if (Math.abs(scrollY2) < this.o) {
                            this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                            this.b = 24;
                            d();
                        } else {
                            this.A.startScroll(0, scrollY2, 0, -(scrollY2 - this.o), 250);
                            this.b = 23;
                            d();
                            if (this.y != null) {
                                this.y.onFooterRefresh(this);
                            }
                        }
                    }
                } else if (Math.abs(scrollY2) < this.n) {
                    this.A.startScroll(0, scrollY2, 0, -scrollY2, 250);
                    this.a = 14;
                    c();
                } else {
                    this.A.startScroll(0, scrollY2, 0, -(this.n + scrollY2), 250);
                    this.a = 13;
                    c();
                    if (this.z != null) {
                        this.z.onHeaderRefresh(this);
                    }
                }
                invalidate();
                break;
            case 2:
                i = rawY - this.i;
                if (!z) {
                    break;
                }
                this.c = 0;
                i2 = scrollY - (i / 2);
                if (i2 <= 0) {
                    this.E = true;
                    scrollTo(0, i2);
                    i2 = getScrollY();
                    if (Math.abs(i2) > this.o) {
                        break;
                    }
                    if (Math.abs(i2) <= this.o) {
                        break;
                    }
                    this.e = true;
                    this.b = 21;
                    d();
                    break;
                }
                this.E = false;
                scrollTo(0, 0);
                this.i = rawY;
                break;
        }
        if (this.E) {
            this.C.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    private void c() {
        switch (this.a) {
            case 11:
                if (this.d) {
                    this.p.startAnimation(this.x);
                }
                this.r.setText("下拉可以刷新");
                return;
            case 12:
                this.p.startAnimation(this.w);
                this.r.setText("松开即可刷新");
                return;
            case 13:
                this.p.clearAnimation();
                this.p.setVisibility(4);
                this.r.setText("加载中...");
                this.t.setVisibility(0);
                return;
            case 14:
                this.d = false;
                this.p.setVisibility(0);
                this.r.setText("下拉可以刷新");
                this.t.setVisibility(4);
                return;
            default:
                return;
        }
    }

    private void d() {
        switch (this.b) {
            case 21:
                if (this.e) {
                    this.q.startAnimation(this.g);
                }
                this.s.setText("上拉加载全部");
                return;
            case 22:
                this.q.startAnimation(this.f);
                this.s.setText("松开即可加载");
                return;
            case 23:
                this.q.clearAnimation();
                this.q.setVisibility(4);
                this.s.setText("加载中...");
                this.u.setVisibility(0);
                return;
            case 24:
                this.e = false;
                this.q.setVisibility(0);
                this.s.setText("上拉加载全部");
                this.u.setVisibility(4);
                return;
            default:
                return;
        }
    }

    public void onHeaderRefreshComplete() {
        int scrollY = getScrollY();
        this.A.startScroll(0, scrollY, 0, -scrollY, 250);
        this.a = 14;
        c();
    }

    public void onComplete() {
        if (this.a != 14) {
            onHeaderRefreshComplete();
            LogUtils.i("BillPullToRefreshView", "onHeaderRefreshComplete");
        }
        if (this.b != 24) {
            onFooterRefreshComplete();
            LogUtils.i("BillPullToRefreshView", "onFooterRefreshComplete");
        }
    }

    public void onFooterRefreshComplete() {
        int scrollY = getScrollY();
        this.A.startScroll(0, scrollY, 0, -scrollY, 250);
        this.b = 24;
        d();
    }

    public void onHeaderRefreshComplete(CharSequence charSequence) {
        setLastUpdated(charSequence);
        onHeaderRefreshComplete();
    }

    public void setLastUpdated(CharSequence charSequence) {
    }

    public void isBanPullUpRefresh(boolean z) {
        if (this.h != z) {
            this.h = z;
            if (z) {
                onFooterRefreshComplete();
                this.k.setVisibility(8);
                return;
            }
            this.k.setVisibility(0);
        }
    }

    public void computeScroll() {
        if (this.A.computeScrollOffset()) {
            scrollTo(0, this.A.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public void setOnHeaderRefreshListener(OnHeaderRefreshListener onHeaderRefreshListener) {
        this.z = onHeaderRefreshListener;
    }

    public void setOnFooterRefreshListener(OnFooterRefreshListener onFooterRefreshListener) {
        this.y = onFooterRefreshListener;
    }
}
