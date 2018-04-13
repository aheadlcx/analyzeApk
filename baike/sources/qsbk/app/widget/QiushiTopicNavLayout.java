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
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.ScrollView;
import qsbk.app.R;

public class QiushiTopicNavLayout extends LinearLayout {
    private OnScrollHeaderListener a;
    private View b;
    private View c;
    private ViewPager d;
    private int e;
    private int f = 0;
    private ViewGroup g;
    private boolean h = false;
    private boolean i = true;
    private OverScroller j;
    private VelocityTracker k;
    private int l;
    private int m;
    private int n;
    private float o;
    private boolean p;
    private boolean q = false;
    private int r;

    public interface OnScrollHeaderListener {
        void scrollHeader();

        void scrollHeaderChange();
    }

    public void setExtraHeight(int i) {
        this.r = i;
    }

    public QiushiTopicNavLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        this.j = new OverScroller(context);
        this.l = ViewConfiguration.get(context).getScaledTouchSlop();
        this.m = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.n = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    public OnScrollHeaderListener getOnScrollHeaderListener() {
        return this.a;
    }

    public void setOnScrollHeaderListener(OnScrollHeaderListener onScrollHeaderListener) {
        this.a = onScrollHeaderListener;
    }

    public int getmActionBarHeight() {
        return this.f;
    }

    public void setmActionBarHeight(int i) {
        this.f = i;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = findViewById(R.id.id_qiushi_topic_topview);
        this.c = findViewById(R.id.id_qiushi_topic_indicator);
        View findViewById = findViewById(R.id.id_qiushi_topic_viewpager);
        if (findViewById instanceof ViewPager) {
            this.d = (ViewPager) findViewById;
            return;
        }
        throw new RuntimeException("id_stickynavlayout_viewpager show used by ViewPager !");
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = getMeasuredHeight() - this.c.getMeasuredHeight();
        if (this.r != 0) {
            layoutParams.height += this.r;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.e = this.b.getMeasuredHeight() - this.f;
    }

    public void refreshTopViewHeight() {
        if (this.b != null && this.b.getMeasuredHeight() != 0) {
            this.e = this.b.getMeasuredHeight() - this.f;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.o = y;
                break;
            case 2:
                y -= this.o;
                getCurrentScrollView();
                MotionEvent obtain;
                if (this.g instanceof ScrollView) {
                    if (this.g.getScrollY() == 0 && this.h && y > 0.0f && !this.q) {
                        this.q = true;
                        motionEvent.setAction(3);
                        obtain = MotionEvent.obtain(motionEvent);
                        dispatchTouchEvent(motionEvent);
                        obtain.setAction(0);
                        return dispatchTouchEvent(obtain);
                    }
                } else if (this.g instanceof ListView) {
                    ListView listView = (ListView) this.g;
                    View childAt = listView.getChildAt(listView.getFirstVisiblePosition());
                    if (!this.q && childAt != null && childAt.getTop() == 0 && this.h && y > 0.0f) {
                        this.q = true;
                        motionEvent.setAction(3);
                        obtain = MotionEvent.obtain(motionEvent);
                        dispatchTouchEvent(motionEvent);
                        obtain.setAction(0);
                        return dispatchTouchEvent(obtain);
                    }
                } else if (this.g instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) this.g;
                    if (!this.q && ViewCompat.canScrollVertically(recyclerView, -1) && this.h && y > 0.0f) {
                        this.q = true;
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
                this.o = y;
                break;
            case 1:
            case 3:
                this.p = false;
                b();
                break;
            case 2:
                float f = y - this.o;
                getCurrentScrollView();
                if (Math.abs(f) > ((float) this.l)) {
                    this.p = true;
                    if (this.g instanceof ScrollView) {
                        if (!this.h || (this.g.getScrollY() == 0 && this.h && f > 0.0f)) {
                            a();
                            this.k.addMovement(motionEvent);
                            this.o = y;
                            return true;
                        }
                    } else if (this.g instanceof ListView) {
                        ListView listView = (ListView) this.g;
                        View childAt = listView.getChildAt(listView.getFirstVisiblePosition());
                        if (!this.h && this.i && f > 0.0f && (childAt == null || (childAt != null && childAt.getTop() != 0))) {
                            return false;
                        }
                        if (!this.h && this.i && f > 0.0f && childAt != null && childAt.getTop() == 0) {
                            return false;
                        }
                        if (!this.h || (childAt != null && childAt.getTop() == 0 && this.h && f > 0.0f)) {
                            a();
                            this.k.addMovement(motionEvent);
                            this.o = y;
                            return true;
                        }
                    } else if (this.g instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) this.g;
                        if (!this.h || (!ViewCompat.canScrollVertically(recyclerView, -1) && this.h && f > 0.0f)) {
                            a();
                            this.k.addMovement(motionEvent);
                            this.o = y;
                            return true;
                        }
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private void getCurrentScrollView() {
        int currentItem = this.d.getCurrentItem();
        PagerAdapter adapter = this.d.getAdapter();
        if (adapter instanceof FragmentPagerAdapter) {
            this.g = (ViewGroup) ((Fragment) ((FragmentPagerAdapter) adapter).instantiateItem(this.d, currentItem)).getView().findViewById(R.id.id_qiushi_topic_listview);
        } else if (adapter instanceof FragmentStatePagerAdapter) {
            this.g = (ViewGroup) ((Fragment) ((FragmentStatePagerAdapter) adapter).instantiateItem(this.d, currentItem)).getView().findViewById(R.id.id_qiushi_topic_listview);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a();
        this.k.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                if (!this.j.isFinished()) {
                    this.j.abortAnimation();
                }
                this.o = y;
                return true;
            case 1:
                this.p = false;
                this.k.computeCurrentVelocity(1000, (float) this.m);
                int yVelocity = (int) this.k.getYVelocity();
                if (Math.abs(yVelocity) > this.n) {
                    fling(-yVelocity);
                }
                b();
                break;
            case 2:
                float f = y - this.o;
                if (!this.p && Math.abs(f) > ((float) this.l)) {
                    this.p = true;
                }
                if (this.p) {
                    scrollBy(0, (int) (-f));
                    if (getScrollY() == this.e && f < 0.0f) {
                        motionEvent.setAction(0);
                        dispatchTouchEvent(motionEvent);
                        this.q = false;
                    }
                }
                this.o = y;
                break;
            case 3:
                this.p = false;
                b();
                if (!this.j.isFinished()) {
                    this.j.abortAnimation();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void fling(int i) {
        this.j.fling(0, getScrollY(), 0, i, 0, 0, 0, this.e);
        invalidate();
    }

    public void scrollTo(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > this.e) {
            i2 = this.e;
        }
        if (i2 != getScrollY()) {
            super.scrollTo(i, i2);
        }
        if (getScrollY() == this.e) {
            z = true;
        } else {
            z = false;
        }
        this.h = z;
        if (getScrollY() != 0) {
            z2 = false;
        }
        this.i = z2;
    }

    public void computeScroll() {
        if (this.j.computeScrollOffset()) {
            scrollTo(0, this.j.getCurrY());
            invalidate();
        }
    }

    private void a() {
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
        }
    }

    private void b() {
        if (this.k != null) {
            this.k.recycle();
            this.k = null;
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (i2 == this.e) {
            if (this.a != null) {
                this.a.scrollHeader();
            }
        } else if (this.a != null) {
            this.a.scrollHeaderChange();
        }
    }
}
