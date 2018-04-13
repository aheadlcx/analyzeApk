package cn.htjyb.ui.widget.headfooterlistview;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import cn.htjyb.ui.widget.headfooterlistview.header.State;
import cn.htjyb.ui.widget.headfooterlistview.header.b;
import cn.htjyb.ui.widget.headfooterlistview.header.d;

public class HeaderFooterListView extends ListView implements OnScrollListener, cn.htjyb.ui.a {
    protected d a;
    float b;
    float c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private b g;
    private b h;
    private View i;
    private cn.htjyb.ui.widget.headfooterlistview.header.a j;
    private boolean k;
    private int l;
    private int m = 0;
    private OnScrollListener n;
    private d o;
    private a p;
    private c q;

    public interface a {
        void a(int i, int i2, int i3);
    }

    public HeaderFooterListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public HeaderFooterListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public HeaderFooterListView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        setFocusable(false);
        setOnScrollListener(this);
    }

    public void setInScrollView(boolean z) {
        this.k = z;
    }

    public void setRefreshOnScrollListener(OnScrollListener onScrollListener) {
        this.n = onScrollListener;
    }

    public void setScrolledListener(a aVar) {
        this.p = aVar;
    }

    public void onMeasure(int i, int i2) {
        if (this.k) {
            i2 = MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }

    public void a(d dVar, b bVar) {
        this.g = bVar;
        this.a = dVar;
        this.j = dVar;
        addHeaderView(dVar, null, false);
        cn.htjyb.ui.b.a((View) dVar);
        this.l = dVar.getMeasuredHeight();
        setHeaderViewState(State.kStateHide);
    }

    public void a() {
        setHeaderViewState(State.kStateRefreshing);
        this.a.setPadding(0, 0, 0, 0);
    }

    public void b() {
        setHeaderViewState(State.kStateHide);
        if (this.h != null) {
            setHasMoreState(this.h.n());
        }
    }

    public d getViewHeader() {
        return this.a;
    }

    public void a(int i, int[] iArr, int i2, a aVar) {
        this.o = new d(this, i, iArr, i2, aVar);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if ((this.o == null || !this.o.a(motionEvent)) && (this.q == null || !this.q.a(motionEvent))) {
            return super.dispatchTouchEvent(motionEvent);
        }
        requestDisallowInterceptTouchEvent(true);
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionIndex() != 0) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                b(motionEvent);
                break;
            case 2:
                a(motionEvent);
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.o != null) {
            this.o.a();
        }
    }

    private void a(MotionEvent motionEvent) {
        if (this.a != null && this.g.j() && this.j.getState() != State.kStateRefreshing) {
            if (!this.f && this.d) {
                this.f = true;
                this.b = motionEvent.getY();
                setHeaderViewState(State.kStateDragToRefresh);
            } else if (this.f) {
                this.c = motionEvent.getY();
                if (this.j.getState() == State.kStateDragToRefresh) {
                    if ((this.c - this.b) / 3.0f >= ((float) this.l)) {
                        setHeaderViewState(State.kStateReleaseToRefresh);
                    }
                } else if (this.j.getState() == State.kStateReleaseToRefresh && (this.c - this.b) / 3.0f < ((float) this.l) && this.c - this.b > 0.0f) {
                    setHeaderViewState(State.kStateDragToRefresh);
                }
                this.a.setPadding(0, (int) (((this.c - this.b) / 3.0f) - ((float) this.l)), 0, 0);
            }
        }
    }

    private void b(MotionEvent motionEvent) {
        this.f = false;
        if (this.a != null) {
            if (motionEvent.getAction() == 3) {
                setHeaderViewState(State.kStateHide);
                return;
            }
            switch (this.j.getState()) {
                case kStateDragToRefresh:
                    setHeaderViewState(State.kStateHide);
                    return;
                case kStateReleaseToRefresh:
                    this.a.setPadding(0, 0, 0, 0);
                    setHeaderViewState(State.kStateRefreshing);
                    this.g.k();
                    return;
                default:
                    return;
            }
        }
    }

    private void setHeaderViewState(State state) {
        if (state == State.kStateHide) {
            this.a.setPadding(0, -this.l, 0, 0);
        }
        this.j.setState(state);
    }

    public void a(ViewLoadMoreFooter viewLoadMoreFooter, b bVar) {
        this.h = bVar;
        this.i = viewLoadMoreFooter.getChildAt(0);
        addFooterView(viewLoadMoreFooter, null, false);
        setHasMoreState(bVar.n());
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.n != null) {
            this.n.onScroll(absListView, i, i2, i3);
        }
        if (i == 0) {
            this.d = true;
        } else {
            this.d = false;
        }
        if (i + i2 == i3) {
            this.e = true;
        } else {
            this.e = false;
        }
        if (this.i != null && this.i.getVisibility() == 0) {
            e();
        }
        if (this.p != null) {
            this.p.a(i, i2, i3);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.m = i;
        if (this.n != null) {
            this.n.onScrollStateChanged(absListView, i);
        }
        e();
    }

    private void e() {
        if (!this.e || this.h == null || !this.h.n() || this.m != 0) {
            return;
        }
        if (this.j == null || this.j.getState() != State.kStateRefreshing) {
            this.h.o();
        }
    }

    public int getScrollState() {
        return this.m;
    }

    @TargetApi(11)
    public void a(boolean z, boolean z2) {
        setHasMoreState(z2);
        if (!z && z2 && getLastVisiblePosition() + 1 == getCount()) {
            setHasMoreState(false);
        }
    }

    public void d() {
        if (this.i != null && this.h != null) {
            this.i.setVisibility(this.h.n() ? 0 : 8);
        }
    }

    private void setHasMoreState(boolean z) {
        if (this.i != null) {
            if (z) {
                this.i.setVisibility(0);
            } else {
                this.i.setVisibility(8);
            }
        }
    }

    public void c() {
        this.g = null;
        this.h = null;
    }
}
