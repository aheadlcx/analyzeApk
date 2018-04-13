package com.budejie.www.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.budejie.www.R;
import com.umeng.analytics.MobclickAgent;

public class CustomListViewForBigPicture extends ListView implements OnScrollListener {
    private long A;
    public int a;
    public OnScrollListener b;
    int c = 0;
    private boolean d = true;
    private LayoutInflater e;
    private LinearLayout f;
    private TextView g;
    private TextView h;
    private ImageView i;
    private ProgressBar j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private c q;
    private b r;
    private a s;
    private boolean t;
    private View u;
    private Context v;
    private com.budejie.www.g.a w;
    private int x;
    private float y;
    private boolean z = false;

    public interface a {
        void a(int i);
    }

    public interface b {
        void a();
    }

    public interface c {
        void a();
    }

    public void setmEnablePullLoad(boolean z) {
        this.d = z;
    }

    public CustomListViewForBigPicture(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = context;
        a(context);
    }

    private void a(Context context) {
        this.x = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        setCacheColorHint(context.getResources().getColor(R.color.transparent));
        this.e = LayoutInflater.from(context);
        this.f = (LinearLayout) this.e.inflate(R.layout.pull_to_refresh_header, null);
        this.i = (ImageView) this.f.findViewById(R.id.pull_to_refresh_image);
        this.i.setMinimumWidth(70);
        this.i.setMinimumHeight(50);
        this.j = (ProgressBar) this.f.findViewById(R.id.pull_to_refresh_progress);
        this.g = (TextView) this.f.findViewById(R.id.pull_to_refresh_text);
        this.h = (TextView) this.f.findViewById(R.id.pull_to_refresh_updated_at);
        a(this.f);
        this.m = this.f.getMeasuredHeight();
        this.l = this.f.getMeasuredWidth();
        this.f.setPadding(this.f.getPaddingLeft(), this.l * -1, this.f.getPaddingRight(), this.f.getPaddingBottom());
        this.f.invalidate();
        addHeaderView(this.f, null, false);
        setOnScrollListener(this);
        this.a = 3;
        this.t = false;
        this.u = LayoutInflater.from(context).inflate(R.layout.message_foot_more, null);
        setOverScrollMode(2);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.o = i;
        if (this.b != null) {
            this.b.onScroll(absListView, i, i2, i3);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (absListView.getAdapter() != null) {
            if (this.s != null) {
                this.s.a(absListView.getFirstVisiblePosition());
            }
            if (absListView.getLastVisiblePosition() == ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
                a();
            }
        }
        if (this.b != null) {
            this.b.onScrollStateChanged(absListView, i);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        super.setOnScrollListener(this);
        if (onScrollListener != this) {
            this.b = onScrollListener;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.t) {
            switch (motionEvent.getAction()) {
                case 0:
                    if (this.o == 0 && !this.k) {
                        this.k = true;
                        this.n = (int) motionEvent.getY();
                    }
                    this.z = false;
                    this.y = (float) ((int) motionEvent.getY());
                    break;
                case 1:
                    if (!(this.a == 2 || this.a == 4)) {
                        if (this.a == 3) {
                        }
                        if (this.a == 1) {
                            this.a = 3;
                            b();
                        }
                        if (this.a == 0) {
                            this.a = 2;
                            b();
                            c();
                            MobclickAgent.onEvent(this.v, "refreshLable", "底部最新按钮 ");
                        }
                    }
                    this.k = false;
                    this.p = false;
                    if (this.w != null) {
                        this.w.a(0, null, this.z);
                        break;
                    }
                    break;
                case 2:
                    int y = (int) motionEvent.getY();
                    if (((int) Math.abs(this.y - ((float) y))) > this.x) {
                        this.z = true;
                    }
                    if (!this.k && this.o == 0) {
                        this.k = true;
                        this.n = y;
                    }
                    if (!(this.a == 2 || !this.k || this.a == 4)) {
                        if (this.a == 0) {
                            setSelection(0);
                            if ((y - this.n) / 3 < this.m && y - this.n > 0) {
                                this.a = 1;
                                b();
                            } else if (y - this.n <= 0) {
                                this.a = 3;
                                b();
                            }
                        }
                        if (this.a == 1) {
                            setSelection(0);
                            if ((y - this.n) / 3 >= this.m) {
                                this.a = 0;
                                this.p = true;
                                b();
                            } else if (y - this.n <= 0) {
                                this.a = 3;
                                b();
                            }
                        }
                        if (this.a == 3 && y - this.n > 0 && this.o == 0) {
                            this.a = 1;
                            b();
                        }
                        if (this.a == 1) {
                            this.f.setPadding(0, (this.m * -1) + ((y - this.n) / 3), 0, this.f.getPaddingBottom());
                        }
                        if (this.a == 0) {
                            this.f.setPadding(0, ((y - this.n) / 3) - this.m, 0, this.f.getPaddingBottom());
                            break;
                        }
                    }
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void b() {
        switch (this.a) {
            case 0:
                this.i.setVisibility(0);
                this.g.setVisibility(0);
                this.h.setVisibility(0);
                this.g.setText(R.string.release_to_refresh_label);
                this.i.setImageDrawable(getResources().getDrawable(R.drawable.list_view_release));
                return;
            case 1:
                this.g.setVisibility(0);
                this.h.setVisibility(0);
                this.i.setVisibility(0);
                this.h.setText(this.v.getString(R.string.refresh_title) + com.budejie.www.widget.a.a.a(this.A));
                if (this.p) {
                    this.p = false;
                    this.g.setText(R.string.pull_to_refresh_label);
                    this.i.setImageDrawable(getResources().getDrawable(R.drawable.list_view_pull));
                    return;
                }
                this.g.setText(R.string.pull_to_refresh_label);
                this.i.setImageDrawable(getResources().getDrawable(R.drawable.list_view_pull));
                return;
            case 2:
                this.f.setPadding(0, 10, 0, this.f.getPaddingBottom());
                this.g.setText(R.string.refreshing_label);
                this.i.setImageDrawable(getResources().getDrawable(R.drawable.list_view_refreshing));
                this.h.setVisibility(0);
                return;
            case 3:
                this.f.setPadding(0, this.m * -1, 0, this.f.getPaddingBottom());
                this.g.setText(R.string.pull_to_refresh_label);
                this.i.setImageDrawable(getResources().getDrawable(R.drawable.list_view_pull));
                this.h.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void setOnRefreshListener(c cVar) {
        this.q = cVar;
        this.t = true;
    }

    public void setonLoadListener(b bVar) {
        this.r = bVar;
    }

    public void setonFlingListener(a aVar) {
        this.s = aVar;
    }

    public void a() {
        if (getFooterViewsCount() < 1) {
            addFooterView(this.u);
        }
        if (this.r != null && this.d) {
            this.r.a();
        }
    }

    private void c() {
        if (this.q != null) {
            this.q.a();
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

    public void setAdapter(BaseAdapter baseAdapter) {
        super.setAdapter(baseAdapter);
        setSelection(1);
        this.A = System.currentTimeMillis();
        this.h.setText(this.v.getString(R.string.refresh_title) + com.budejie.www.widget.a.a.a(this.A));
    }

    public View getFootView() {
        return this.u;
    }

    public View getHeaderView() {
        return this.f;
    }

    protected void layoutChildren() {
        try {
            super.layoutChildren();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        int size = MeasureSpec.getSize(i);
        if (this.c != 0) {
            setMeasuredDimension(size, getMeasuredHeight() + this.c);
        }
    }

    public void setAdHeight(int i) {
        this.c = i;
    }
}
