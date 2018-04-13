package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
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
import cn.xiaochuankeji.tieba.R;
import com.izuiyou.a.a.b;
import java.util.ArrayList;
import java.util.Iterator;

public class StickyNavLayout extends LinearLayout {
    private View a;
    private View b;
    private ViewPager c;
    private int d;
    private ViewGroup e;
    private boolean f;
    private OverScroller g;
    private VelocityTracker h;
    private int i;
    private int j;
    private int k;
    private int l;
    private float m;
    private float n;
    private boolean o;
    private boolean p;
    private ArrayList<a> q;
    private boolean r;

    public interface a {
        void a_(int i);
    }

    public StickyNavLayout(Context context) {
        this(context, null);
    }

    public StickyNavLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StickyNavLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = false;
        this.n = -1.0f;
        this.p = false;
        this.q = new ArrayList();
        this.r = false;
        setOrientation(1);
        this.g = new OverScroller(context);
        this.i = ViewConfiguration.get(context).getScaledTouchSlop();
        this.j = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.k = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        setMotionEventSplittingEnabled(false);
    }

    public void setMoveOffset(int i) {
        this.l = i;
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
        LayoutParams layoutParams = this.c.getLayoutParams();
        int measuredHeight = (getMeasuredHeight() - this.b.getMeasuredHeight()) + this.l;
        if (layoutParams.height != measuredHeight) {
            layoutParams.height = measuredHeight;
            super.onMeasure(i, i2);
        }
        this.d = this.a.getMeasuredHeight() + this.l;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.r) {
            b.e("正在滑动,过滤掉点击");
            motionEvent.setAction(3);
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.m = y;
                break;
            case 2:
                y -= this.m;
                getCurrentScrollView();
                if ((this.e instanceof ListView) || (this.e instanceof RecyclerView)) {
                    View childAt;
                    boolean z;
                    if (this.e instanceof ListView) {
                        ListView listView = (ListView) this.e;
                        childAt = listView.getChildAt(listView.getFirstVisiblePosition());
                        z = childAt != null && childAt.getTop() == listView.getPaddingTop() && this.f && y > 0.0f;
                    } else if (this.e instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) this.e;
                        childAt = recyclerView.getChildAt(com.marshalchen.ultimaterecyclerview.b.a.a(recyclerView).a());
                        z = childAt != null && childAt.getTop() == recyclerView.getPaddingTop() && this.f && y > 0.0f;
                    } else {
                        z = false;
                    }
                    if (!this.p && r0) {
                        this.p = true;
                        motionEvent.setAction(3);
                        dispatchTouchEvent(motionEvent);
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
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
        float x = motionEvent.getX();
        switch (action) {
            case 0:
                this.m = y;
                this.n = x;
                break;
            case 1:
            case 3:
                this.o = false;
                b();
                break;
            case 2:
                float f = y - this.m;
                float f2 = x - this.n;
                getCurrentScrollView();
                if (this.o) {
                }
                if (Math.abs(f) > ((float) this.i) && Math.abs(f) > Math.abs(f2)) {
                    this.o = true;
                    if (this.e instanceof ScrollView) {
                        if (!this.f || (this.e.getScrollY() == 0 && this.f && f > 0.0f)) {
                            a();
                            this.h.addMovement(motionEvent);
                            this.m = y;
                            return true;
                        }
                    } else if (this.e instanceof ListView) {
                        ListView listView = (ListView) this.e;
                        r3 = listView.getChildAt(listView.getFirstVisiblePosition());
                        if (!this.f || ((r3 != null && r3.getTop() == listView.getPaddingTop() && f > 0.0f) || listView.getChildCount() == 0)) {
                            a();
                            this.h.addMovement(motionEvent);
                            this.m = y;
                            return true;
                        }
                    } else if (this.e instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) this.e;
                        r3 = recyclerView.getChildAt(com.marshalchen.ultimaterecyclerview.b.a.a(recyclerView).a());
                        if (!this.f || ((r3 != null && r3.getTop() == recyclerView.getPaddingTop() && f > 0.0f) || recyclerView.getChildCount() == 0)) {
                            a();
                            this.h.addMovement(motionEvent);
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
        this.h.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        motionEvent.getX();
        switch (action) {
            case 0:
                if (!this.g.isFinished()) {
                    this.g.abortAnimation();
                }
                this.m = y;
                return true;
            case 1:
                this.o = false;
                this.h.computeCurrentVelocity(1000, (float) this.j);
                int yVelocity = (int) this.h.getYVelocity();
                if (Math.abs(yVelocity) > this.k) {
                    a(-yVelocity);
                }
                b();
                break;
            case 2:
                float f = y - this.m;
                if (!this.o && Math.abs(f) > ((float) this.i)) {
                    this.o = true;
                }
                if (this.o) {
                    scrollBy(0, (int) (-f));
                    if (getScrollY() == this.d && f < 0.0f) {
                        motionEvent.setAction(0);
                        dispatchTouchEvent(motionEvent);
                        this.p = false;
                    }
                }
                this.m = y;
                break;
            case 3:
                this.o = false;
                b();
                if (!this.g.isFinished()) {
                    this.g.abortAnimation();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(int i) {
        this.g.fling(0, getScrollY(), 0, i, 0, 0, 0, this.d);
        invalidate();
    }

    public void scrollTo(int i, int i2) {
        boolean z = false;
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > this.d) {
            i2 = this.d;
        }
        if (i2 != getScrollY()) {
            super.scrollTo(i, i2);
        }
        int scrollY = getScrollY();
        if (this.q.size() > 0) {
            Iterator it = this.q.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a_(scrollY);
            }
        }
        if (getScrollY() == this.d) {
            z = true;
        }
        this.f = z;
    }

    public void computeScroll() {
        if (this.g.computeScrollOffset()) {
            this.r = true;
            scrollTo(0, this.g.getCurrY());
            invalidate();
            return;
        }
        this.r = false;
    }

    private void a() {
        if (this.h == null) {
            this.h = VelocityTracker.obtain();
        }
    }

    private void b() {
        if (this.h != null) {
            this.h.clear();
            this.h.recycle();
            this.h = null;
        }
    }

    public void a(a aVar) {
        this.q.add(aVar);
    }

    public void b(a aVar) {
        if (this.q != null) {
            this.q.remove(aVar);
        }
    }
}
