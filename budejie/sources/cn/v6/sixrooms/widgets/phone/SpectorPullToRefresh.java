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
import cn.v6.sixrooms.widgets.phone.SpectatorPage.SwitchSpectatorListener;

public class SpectorPullToRefresh extends LinearLayout implements SwitchSpectatorListener {
    private Scroller A;
    private Context B;
    private final int C = 500;
    private SpectatorPage D;
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

    public interface OnFooterRefreshListener {
        void onFooterRefresh(SpectorPullToRefresh spectorPullToRefresh);
    }

    public interface OnHeaderRefreshListener {
        void onHeaderRefresh(SpectorPullToRefresh spectorPullToRefresh);
    }

    public SpectorPullToRefresh(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = context;
        a();
    }

    public SpectorPullToRefresh(Context context) {
        super(context);
        this.B = context;
        a();
    }

    public void initSwitchSpectatorListener(SpectatorPage spectatorPage) {
        this.D = spectatorPage;
        spectatorPage.setSwitchSpectatorListener(this);
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
        this.v = LayoutInflater.from(this.B);
        this.j = this.v.inflate(R.layout.refresh_layout_header_spectator, this, false);
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
        this.k = this.v.inflate(R.layout.refresh_layout_footer_spectator, this, false);
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
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof AdapterView) {
                this.l = (AdapterView) childAt;
            }
            if (childAt instanceof ScrollView) {
                this.m = (ScrollView) childAt;
            }
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

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case 0:
                this.i = rawY;
                return false;
            case 2:
                rawY -= this.i;
                if (rawY < 8 && rawY > -8) {
                    return false;
                }
                if (this.h && rawY < 0) {
                    return false;
                }
                if (this.a == 13 || this.b == 23) {
                    rawY = false;
                } else {
                    View childAt;
                    boolean z;
                    if (this.l != null) {
                        if (rawY > 0) {
                            childAt = this.l.getChildAt(0);
                            if (childAt == null) {
                                this.c = 1;
                                rawY = 1;
                            } else if (this.l.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
                                this.c = 1;
                                rawY = 1;
                            } else {
                                int top = childAt.getTop();
                                int paddingTop = this.l.getPaddingTop();
                                if (this.l.getFirstVisiblePosition() == 0 && Math.abs(top - paddingTop) <= 8) {
                                    this.c = 1;
                                    rawY = 1;
                                }
                            }
                        } else if (rawY < 0) {
                            childAt = this.l.getChildAt(this.l.getChildCount() - 1);
                            if (childAt == null) {
                                this.c = 0;
                                z = false;
                            } else if (childAt.getBottom() <= getHeight() && this.l.getLastVisiblePosition() == this.l.getCount() - 1) {
                                this.c = 0;
                                rawY = 1;
                            }
                        }
                    }
                    if (this.m != null) {
                        childAt = this.m.getChildAt(0);
                        if (rawY > 0 && this.m.getScrollY() == 0) {
                            this.c = 1;
                            rawY = 1;
                        } else if (rawY < 0 && childAt.getMeasuredHeight() <= getHeight() + this.m.getScrollY()) {
                            this.c = 0;
                            rawY = 1;
                        }
                    }
                    z = false;
                }
                if (rawY != 0) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        int scrollY;
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                scrollY = getScrollY();
                if (this.c == 1) {
                    if (Math.abs(scrollY) >= this.n) {
                        this.A.startScroll(0, scrollY, 0, -(this.n + scrollY), 500);
                        this.a = 13;
                        c();
                        if (this.z != null) {
                            this.z.onHeaderRefresh(this);
                        } else {
                            onHeaderRefreshComplete();
                        }
                    } else {
                        this.A.startScroll(0, scrollY, 0, -scrollY, 500);
                        this.a = 14;
                        c();
                    }
                } else if (this.c == 0) {
                    if (Math.abs(scrollY) >= this.o) {
                        this.A.startScroll(0, scrollY, 0, -(scrollY - this.o), 500);
                        this.b = 23;
                        d();
                        if (this.y != null) {
                            this.y.onFooterRefresh(this);
                        }
                    } else {
                        this.A.startScroll(0, scrollY, 0, -scrollY, 500);
                        this.b = 24;
                        d();
                    }
                }
                invalidate();
                break;
            case 2:
                scrollY = (int) (((float) (rawY - this.i)) * 0.5f);
                if (this.c == 1) {
                    scrollBy(0, -scrollY);
                    scrollY = getScrollY();
                    if (scrollY >= 0) {
                        scrollTo(0, 0);
                    }
                    if (Math.abs(scrollY) <= this.n && this.a == 14) {
                        this.a = 11;
                        c();
                    } else if (Math.abs(scrollY) > this.n && this.a == 11) {
                        this.a = 12;
                        c();
                    } else if (Math.abs(scrollY) <= this.n && this.a == 12) {
                        this.a = 11;
                        this.d = true;
                        c();
                    }
                } else if (this.c == 0) {
                    scrollBy(0, -scrollY);
                    scrollY = getScrollY();
                    if (scrollY <= 0) {
                        scrollTo(0, 0);
                    }
                    if (Math.abs(scrollY) <= this.o && this.b == 24) {
                        this.b = 21;
                        d();
                    } else if (Math.abs(scrollY) > this.o && this.b == 21) {
                        this.b = 22;
                        d();
                    } else if (Math.abs(scrollY) <= this.o && this.b == 22) {
                        this.e = true;
                        this.b = 21;
                        d();
                    }
                }
                this.i = rawY;
                break;
        }
        return super.onTouchEvent(motionEvent);
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
        this.A.startScroll(0, scrollY, 0, -scrollY, 500);
        this.a = 14;
        c();
    }

    public void onFooterRefreshComplete() {
        int scrollY = getScrollY();
        this.A.startScroll(0, scrollY, 0, -scrollY, 500);
        this.b = 24;
        d();
    }

    public void onHeaderRefreshComplete(CharSequence charSequence) {
        setLastUpdated(charSequence);
        onHeaderRefreshComplete();
    }

    public void setLastUpdated(CharSequence charSequence) {
    }

    public void isBanFooterRefresh(boolean z) {
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

    public void onSwitchSpectator() {
        if (this.a != 14) {
            onHeaderRefreshComplete();
        }
        if (this.b != 24) {
            onFooterRefreshComplete();
        }
    }
}
