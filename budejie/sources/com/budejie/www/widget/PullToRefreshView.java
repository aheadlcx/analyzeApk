package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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
import android.widget.TextView;
import com.budejie.www.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PullToRefreshView extends LinearLayout {
    private int a;
    private boolean b;
    private View c;
    private View d;
    private AdapterView<?> e;
    private ScrollView f;
    private int g;
    private int h;
    private ImageView i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private ProgressBar n;
    private ProgressBar o;
    private LayoutInflater p;
    private int q;
    private int r;
    private int s;
    private RotateAnimation t;
    private RotateAnimation u;
    private a v;
    private b w;

    public interface b {
        void a(PullToRefreshView pullToRefreshView);
    }

    public interface a {
        void a(PullToRefreshView pullToRefreshView);
    }

    public PullToRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public PullToRefreshView(Context context) {
        super(context);
        d();
    }

    private void d() {
        this.t = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.t.setInterpolator(new LinearInterpolator());
        this.t.setDuration(250);
        this.t.setFillAfter(true);
        this.u = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.u.setInterpolator(new LinearInterpolator());
        this.u.setDuration(250);
        this.u.setFillAfter(true);
        this.p = LayoutInflater.from(getContext());
        e();
    }

    private void e() {
        this.c = this.p.inflate(R.layout.gridview_refresh_header, this, false);
        this.i = (ImageView) this.c.findViewById(R.id.pull_to_refresh_image);
        this.k = (TextView) this.c.findViewById(R.id.pull_to_refresh_text);
        this.m = (TextView) this.c.findViewById(R.id.pull_to_refresh_updated_at);
        this.n = (ProgressBar) this.c.findViewById(R.id.pull_to_refresh_progress);
        a(this.c);
        this.g = this.c.getMeasuredHeight();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.g);
        layoutParams.topMargin = -this.g;
        addView(this.c, layoutParams);
    }

    private void f() {
        this.d = this.p.inflate(R.layout.gridview_refresh_footer, this, false);
        this.j = (ImageView) this.d.findViewById(R.id.pull_to_load_image);
        this.l = (TextView) this.d.findViewById(R.id.pull_to_load_text);
        this.o = (ProgressBar) this.d.findViewById(R.id.pull_to_load_progress);
        a(this.d);
        this.h = this.d.getMeasuredHeight();
        addView(this.d, new LinearLayout.LayoutParams(-1, this.h));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        f();
        g();
    }

    private void g() {
        int childCount = getChildCount();
        if (childCount < 3) {
            throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
        }
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof AdapterView) {
                this.e = (AdapterView) childAt;
            }
            if (childAt instanceof ScrollView) {
                this.f = (ScrollView) childAt;
            }
        }
        if (this.e == null && this.f == null) {
            throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
        }
    }

    private void a(View view) {
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
                this.a = rawY;
                break;
            case 2:
                if (a(rawY - this.a)) {
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b) {
            return true;
        }
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                rawY = getHeaderTopMargin();
                if (this.s != 1) {
                    if (this.s == 0) {
                        h();
                        break;
                    }
                } else if (rawY < 0) {
                    setHeaderTopMargin(-this.g);
                    break;
                } else {
                    a();
                    break;
                }
                break;
            case 2:
                int i = rawY - this.a;
                if (this.s == 1) {
                    Log.i("PullToRefreshView", " pull down!parent view move!");
                    b(i);
                } else if (this.s == 0) {
                    Log.i("PullToRefreshView", "pull up!parent view move!");
                    c(i);
                }
                this.a = rawY;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean a(int i) {
        if (this.q == 4 || this.r == 4) {
            return false;
        }
        View childAt;
        if (this.e != null) {
            if (i > 0) {
                childAt = this.e.getChildAt(0);
                if (childAt == null) {
                    return false;
                }
                if (this.e.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
                    this.s = 1;
                    return true;
                }
                int top = childAt.getTop();
                int paddingTop = this.e.getPaddingTop();
                if (this.e.getFirstVisiblePosition() == 0 && Math.abs(top - paddingTop) <= 8) {
                    this.s = 1;
                    return true;
                }
            } else if (i < 0) {
                childAt = this.e.getChildAt(this.e.getChildCount() - 1);
                if (childAt == null) {
                    return false;
                }
                if (childAt.getBottom() <= getHeight() && this.e.getLastVisiblePosition() == this.e.getCount() - 1) {
                    this.s = 0;
                    return true;
                }
            }
        }
        if (this.f == null) {
            return false;
        }
        childAt = this.f.getChildAt(0);
        if (i > 0 && this.f.getScrollY() == 0) {
            this.s = 1;
            return true;
        } else if (i >= 0 || childAt.getMeasuredHeight() > getHeight() + this.f.getScrollY()) {
            return false;
        } else {
            this.s = 0;
            return true;
        }
    }

    private void b(int i) {
        int d = d(i);
        if (d >= 0 && this.q != 3) {
            this.k.setText(R.string.xlistview_header_hint_ready);
            this.m.setVisibility(0);
            this.i.clearAnimation();
            this.i.startAnimation(this.t);
            this.q = 3;
        } else if (d < 0 && d > (-this.g)) {
            this.i.clearAnimation();
            this.i.startAnimation(this.t);
            this.k.setText(R.string.xlistview_header_hint_normal);
            this.q = 2;
        }
    }

    private void c(int i) {
        int d = d(i);
        if (Math.abs(d) >= this.g + this.h && this.r != 3) {
            this.l.setText(R.string.xlistview_footer_hint_normal);
            this.j.clearAnimation();
            this.j.startAnimation(this.t);
            this.r = 3;
        } else if (Math.abs(d) < this.g + this.h) {
            this.j.clearAnimation();
            this.j.startAnimation(this.t);
            this.l.setText(R.string.xlistview_footer_hint_normal);
            this.r = 2;
        }
    }

    private int d(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.c.getLayoutParams();
        float f = ((float) layoutParams.topMargin) + (((float) i) * 0.3f);
        if (i > 0 && this.s == 0 && Math.abs(layoutParams.topMargin) <= this.g) {
            return layoutParams.topMargin;
        }
        if (i < 0 && this.s == 1 && Math.abs(layoutParams.topMargin) >= this.g) {
            return layoutParams.topMargin;
        }
        layoutParams.topMargin = (int) f;
        this.c.setLayoutParams(layoutParams);
        invalidate();
        return layoutParams.topMargin;
    }

    public void a() {
        this.q = 4;
        setHeaderTopMargin(0);
        this.i.setVisibility(8);
        this.i.clearAnimation();
        this.i.setImageDrawable(null);
        this.n.setVisibility(0);
        this.k.setText(R.string.xlistview_header_hint_loading);
        if (this.w != null) {
            this.w.a(this);
        }
    }

    private void h() {
        this.r = 4;
        setHeaderTopMargin(-(this.g + this.h));
        this.j.setVisibility(8);
        this.j.clearAnimation();
        this.j.setImageDrawable(null);
        this.o.setVisibility(0);
        this.l.setText(R.string.xlistview_footer_hint_normal);
        if (this.v != null) {
            this.v.a(this);
        }
    }

    private void setHeaderTopMargin(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.c.getLayoutParams();
        layoutParams.topMargin = i;
        this.c.setLayoutParams(layoutParams);
        invalidate();
    }

    public void b() {
        setHeaderTopMargin(-this.g);
        this.i.setVisibility(0);
        this.i.setImageResource(R.drawable.xlistview_arrow);
        this.k.setText(R.string.xlistview_header_hint_normal);
        this.n.setVisibility(8);
        this.m.setText("更新于：" + a(new Date()));
        this.q = 2;
    }

    public void c() {
        setHeaderTopMargin(-this.g);
        this.j.setVisibility(0);
        this.j.setImageResource(R.drawable.xlistview_arrow);
        this.l.setText(R.string.xlistview_footer_hint_normal);
        this.o.setVisibility(8);
        this.m.setText("更新于：" + a(new Date()));
        this.r = 2;
    }

    private String a(Date date) {
        return new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }

    public void setLastUpdated(Long l) {
        if (l.longValue() != 0) {
            this.m.setVisibility(0);
            this.m.setText("上次更新时间：" + com.budejie.www.widget.a.a.a(l.longValue()));
            return;
        }
        this.m.setVisibility(8);
    }

    private int getHeaderTopMargin() {
        return ((LinearLayout.LayoutParams) this.c.getLayoutParams()).topMargin;
    }

    public void setOnHeaderRefreshListener(b bVar) {
        this.w = bVar;
    }

    public void setOnFooterRefreshListener(a aVar) {
        this.v = aVar;
    }
}
