package cn.xiaochuankeji.tieba.ui.videomaker.music;

import android.content.Context;
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
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.izuiyou.a.a.b;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectMusicStickyLinearLayout extends LinearLayout {
    private ViewPager a;
    private int b;
    private ViewGroup c;
    private boolean d = false;
    private OverScroller e;
    private VelocityTracker f;
    private int g;
    private int h;
    private int i;
    private float j;
    private float k = -1.0f;
    private boolean l;
    private boolean m = false;
    private ArrayList<a> n = new ArrayList();
    private boolean o = false;

    public interface a {
        void a(int i);
    }

    public SelectMusicStickyLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        this.e = new OverScroller(context);
        this.g = ViewConfiguration.get(context).getScaledTouchSlop();
        this.h = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.i = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        setMotionEventSplittingEnabled(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        View findViewById = findViewById(R.id.id_stickynavlayout_viewpager);
        if (findViewById instanceof ViewPager) {
            this.a = (ViewPager) findViewById;
            return;
        }
        throw new RuntimeException("id_stickynavlayout_viewpager show used by ViewPager !");
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        LayoutParams layoutParams = this.a.getLayoutParams();
        int measuredHeight = (getMeasuredHeight() - e.a(89.0f)) + e.a(46.0f);
        if (layoutParams.height != measuredHeight) {
            layoutParams.height = measuredHeight;
            super.onMeasure(i, i2);
        }
        this.b = e.a(46.0f);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.o) {
            b.e("正在滑动,过滤掉点击");
            motionEvent.setAction(3);
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.j = y;
                break;
            case 2:
                y -= this.j;
                getCurrentScrollView();
                if ((this.c instanceof ListView) || (this.c instanceof RecyclerView)) {
                    View childAt;
                    boolean z;
                    if (this.c instanceof ListView) {
                        ListView listView = (ListView) this.c;
                        childAt = listView.getChildAt(listView.getFirstVisiblePosition());
                        z = childAt != null && childAt.getTop() == listView.getPaddingTop() && this.d && y > 0.0f;
                    } else if (this.c instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) this.c;
                        childAt = recyclerView.getChildAt(com.marshalchen.ultimaterecyclerview.b.a.a(recyclerView).a());
                        z = childAt != null && childAt.getTop() == recyclerView.getPaddingTop() && this.d && y > 0.0f;
                    } else {
                        z = false;
                    }
                    if (!this.m && r0) {
                        this.m = true;
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
                this.j = y;
                this.k = x;
                break;
            case 1:
            case 3:
                this.l = false;
                b();
                break;
            case 2:
                float f = y - this.j;
                float f2 = x - this.k;
                getCurrentScrollView();
                if (Math.abs(f) > ((float) this.g)) {
                    this.l = true;
                    if (this.c instanceof ScrollView) {
                        if (!this.d || (this.c.getScrollY() == 0 && this.d && f > 0.0f)) {
                            a();
                            this.f.addMovement(motionEvent);
                            this.j = y;
                            return true;
                        }
                    } else if (this.c instanceof ListView) {
                        ListView listView = (ListView) this.c;
                        r3 = listView.getChildAt(listView.getFirstVisiblePosition());
                        if (!this.d || ((r3 != null && r3.getTop() == listView.getPaddingTop() && f > 0.0f) || listView.getChildCount() == 0)) {
                            a();
                            this.f.addMovement(motionEvent);
                            this.j = y;
                            return true;
                        }
                    } else if (this.c instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) this.c;
                        r3 = recyclerView.getChildAt(com.marshalchen.ultimaterecyclerview.b.a.a(recyclerView).a());
                        if (!this.d || ((r3 != null && r3.getTop() == recyclerView.getPaddingTop() && f > 0.0f) || recyclerView.getChildCount() == 0)) {
                            a();
                            this.f.addMovement(motionEvent);
                            this.j = y;
                            return true;
                        }
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private void getCurrentScrollView() {
        int currentItem = this.a.getCurrentItem();
        PagerAdapter adapter = this.a.getAdapter();
        if (adapter instanceof FragmentPagerAdapter) {
            this.c = (ViewGroup) ((Fragment) ((FragmentPagerAdapter) adapter).instantiateItem(this.a, currentItem)).getView().findViewById(R.id.id_stickynavlayout_innerscrollview);
        } else if (adapter instanceof FragmentStatePagerAdapter) {
            this.c = (ViewGroup) ((Fragment) ((FragmentStatePagerAdapter) adapter).instantiateItem(this.a, currentItem)).getView().findViewById(R.id.id_stickynavlayout_innerscrollview);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a();
        this.f.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        motionEvent.getX();
        switch (action) {
            case 0:
                if (!this.e.isFinished()) {
                    this.e.abortAnimation();
                }
                this.j = y;
                return true;
            case 1:
                this.l = false;
                this.f.computeCurrentVelocity(1000, (float) this.h);
                int yVelocity = (int) this.f.getYVelocity();
                if (Math.abs(yVelocity) > this.i) {
                    a(-yVelocity);
                }
                b();
                break;
            case 2:
                float f = y - this.j;
                if (!this.l && Math.abs(f) > ((float) this.g)) {
                    this.l = true;
                }
                if (this.l) {
                    scrollBy(0, (int) (-f));
                    if (getScrollY() == this.b && f < 0.0f) {
                        motionEvent.setAction(0);
                        dispatchTouchEvent(motionEvent);
                        this.m = false;
                    }
                }
                this.j = y;
                break;
            case 3:
                this.l = false;
                b();
                if (!this.e.isFinished()) {
                    this.e.abortAnimation();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(int i) {
        this.e.fling(0, getScrollY(), 0, i, 0, 0, 0, this.b);
        invalidate();
    }

    public void scrollTo(int i, int i2) {
        boolean z = false;
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > this.b) {
            i2 = this.b;
        }
        if (i2 != getScrollY()) {
            super.scrollTo(i, i2);
        }
        int scrollY = getScrollY();
        if (this.n.size() > 0) {
            Iterator it = this.n.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(scrollY);
            }
        }
        if (getScrollY() == this.b) {
            z = true;
        }
        this.d = z;
    }

    public void computeScroll() {
        if (this.e.computeScrollOffset()) {
            this.o = true;
            scrollTo(0, this.e.getCurrY());
            invalidate();
            return;
        }
        this.o = false;
    }

    private void a() {
        if (this.f == null) {
            this.f = VelocityTracker.obtain();
        }
    }

    private void b() {
        if (this.f != null) {
            this.f.clear();
            this.f.recycle();
            this.f = null;
        }
    }
}
