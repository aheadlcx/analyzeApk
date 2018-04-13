package qsbk.app.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.ScrollView;
import qsbk.app.R;

public class StickyNavLayout extends LinearLayout {
    private View a;
    private View b;
    private ViewPager c;
    private int d;
    private ViewGroup e;
    private boolean f = false;
    private boolean g = true;
    private OverScroller h;
    private VelocityTracker i;
    private int j;
    private int k;
    private int l;
    private float m;
    private boolean n;
    private boolean o = false;
    private int p = 0;
    private qsbk.app.widget.QiushiTopicNavLayout.OnScrollHeaderListener q;

    public interface OnScrollHeaderListener {
        void scrollHeader();

        void scrollHeaderChange();
    }

    public StickyNavLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        this.h = new OverScroller(context);
        this.j = ViewConfiguration.get(context).getScaledTouchSlop();
        this.k = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.l = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = findViewById(R.id.id_stickynavlayout_topview);
        this.b = findViewById(R.id.id_stickynavlayout_indicator);
        View findViewById = findViewById(R.id.id_stickynavlayout_viewpager);
        if (findViewById instanceof ViewPager) {
            this.c = (ViewPager) findViewById;
            return;
        }
        throw new RuntimeException("id_stickynavlayout_viewpager show used by ViewPager !");
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.c.getLayoutParams().height = getMeasuredHeight() - this.b.getMeasuredHeight();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.d = this.a.getMeasuredHeight() + this.p;
    }

    public void setExtraScrollHeight(int i) {
        this.p = i;
        if (this.a != null) {
            this.d = this.a.getMeasuredHeight() + this.p;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.m = y;
                break;
            case 2:
                y -= this.m;
                getCurrentScrollView();
                MotionEvent obtain;
                if (this.e instanceof ScrollView) {
                    if (this.e.getScrollY() == 0 && this.f && y > 0.0f && !this.o) {
                        this.o = true;
                        motionEvent.setAction(3);
                        obtain = MotionEvent.obtain(motionEvent);
                        dispatchTouchEvent(motionEvent);
                        obtain.setAction(0);
                        return dispatchTouchEvent(obtain);
                    }
                } else if (this.e instanceof ListView) {
                    ListView listView = (ListView) this.e;
                    View childAt = listView.getChildAt(listView.getFirstVisiblePosition());
                    if (!this.o && childAt != null && childAt.getTop() == 0 && this.f && y > 0.0f) {
                        this.o = true;
                        motionEvent.setAction(3);
                        obtain = MotionEvent.obtain(motionEvent);
                        dispatchTouchEvent(motionEvent);
                        obtain.setAction(0);
                        return dispatchTouchEvent(obtain);
                    }
                } else if (this.e instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) this.e;
                    if (!this.o && ViewCompat.canScrollVertically(recyclerView, -1) && this.f && y > 0.0f) {
                        this.o = true;
                        motionEvent.setAction(3);
                        obtain = MotionEvent.obtain(motionEvent);
                        dispatchTouchEvent(motionEvent);
                        obtain.setAction(0);
                        return dispatchTouchEvent(obtain);
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.m = y;
                break;
            case 1:
            case 3:
                this.n = false;
                b();
                break;
            case 2:
                float f = y - this.m;
                getCurrentScrollView();
                if (Math.abs(f) > ((float) this.j)) {
                    this.n = true;
                    if (this.e instanceof ScrollView) {
                        if (!this.f || (this.e.getScrollY() == 0 && this.f && f > 0.0f)) {
                            a();
                            this.i.addMovement(motionEvent);
                            this.m = y;
                            return true;
                        }
                    } else if (this.e instanceof ListView) {
                        ListView listView = (ListView) this.e;
                        View childAt = listView.getChildAt(listView.getFirstVisiblePosition());
                        if (!this.f && this.g && f > 0.0f && (childAt == null || (childAt != null && childAt.getTop() != 0))) {
                            return false;
                        }
                        if (!this.f && this.g && f > 0.0f && childAt != null && childAt.getTop() == 0) {
                            return false;
                        }
                        if (!this.f || (childAt != null && childAt.getTop() == 0 && this.f && f > 0.0f)) {
                            a();
                            this.i.addMovement(motionEvent);
                            this.m = y;
                            return true;
                        }
                    } else if (this.e instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) this.e;
                        if (!this.f || (!ViewCompat.canScrollVertically(recyclerView, -1) && this.f && f > 0.0f)) {
                            a();
                            this.i.addMovement(motionEvent);
                            this.m = y;
                            return true;
                        }
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private void getCurrentScrollView() {
        int currentItem = this.c.getCurrentItem();
        PagerAdapter adapter = this.c.getAdapter();
        if (adapter instanceof FragmentPagerAdapter) {
            this.e = (ViewGroup) ((Fragment) ((FragmentPagerAdapter) adapter).instantiateItem(this.c, currentItem)).getView().findViewById(R.id.id_stickynavlayout_innerscrollview);
        } else if (adapter instanceof FragmentStatePagerAdapter) {
            this.e = (ViewGroup) ((Fragment) ((FragmentStatePagerAdapter) adapter).instantiateItem(this.c, currentItem)).getView().findViewById(R.id.id_stickynavlayout_innerscrollview);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a();
        this.i.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                if (!this.h.isFinished()) {
                    this.h.abortAnimation();
                }
                this.m = y;
                return true;
            case 1:
                this.n = false;
                this.i.computeCurrentVelocity(1000, (float) this.k);
                int yVelocity = (int) this.i.getYVelocity();
                if (Math.abs(yVelocity) > this.l) {
                    fling(-yVelocity);
                }
                b();
                break;
            case 2:
                float f = y - this.m;
                if (!this.n && Math.abs(f) > ((float) this.j)) {
                    this.n = true;
                }
                if (this.n) {
                    scrollBy(0, (int) (-f));
                    if (getScrollY() == this.d && f < 0.0f) {
                        motionEvent.setAction(0);
                        dispatchTouchEvent(motionEvent);
                        this.o = false;
                    }
                }
                this.m = y;
                break;
            case 3:
                this.n = false;
                b();
                if (!this.h.isFinished()) {
                    this.h.abortAnimation();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void fling(int i) {
        this.h.fling(0, getScrollY(), 0, i, 0, 0, 0, this.d);
        invalidate();
    }

    public void scrollTo(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > this.d) {
            i2 = this.d;
        }
        if (i2 != getScrollY()) {
            super.scrollTo(i, i2);
        }
        if (getScrollY() == this.d) {
            z = true;
        } else {
            z = false;
        }
        this.f = z;
        if (getScrollY() != 0) {
            z2 = false;
        }
        this.g = z2;
    }

    public void computeScroll() {
        if (this.h.computeScrollOffset()) {
            scrollTo(0, this.h.getCurrY());
            invalidate();
        }
    }

    private void a() {
        if (this.i == null) {
            this.i = VelocityTracker.obtain();
        }
    }

    private void b() {
        if (this.i != null) {
            this.i.recycle();
            this.i = null;
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (i2 >= this.d) {
            if (this.q != null) {
                this.q.scrollHeader();
            }
        } else if (this.q != null) {
            this.q.scrollHeaderChange();
        }
    }

    public void setOnScrollHeaderListener(qsbk.app.widget.QiushiTopicNavLayout.OnScrollHeaderListener onScrollHeaderListener) {
        this.q = onScrollHeaderListener;
    }
}
