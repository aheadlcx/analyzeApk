package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import com.izuiyou.a.a.b;

public class DanmakuStickyFrameLayout extends FrameLayout {
    private ViewGroup a;
    private OverScroller b;
    private VelocityTracker c;
    private int d;
    private int e;
    private int f;
    private float g;
    private float h;
    private boolean i;
    private boolean j;
    private boolean k;
    private a l;
    private boolean m;
    private boolean n;

    public interface a {
        void a();
    }

    public DanmakuStickyFrameLayout(Context context) {
        this(context, null);
    }

    public DanmakuStickyFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DanmakuStickyFrameLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = -1.0f;
        this.j = false;
        this.k = false;
        this.m = false;
        this.n = false;
        this.b = new OverScroller(context);
        this.d = ViewConfiguration.get(context).getScaledTouchSlop();
        this.e = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.f = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        setMotionEventSplittingEnabled(false);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.m) {
            b.e("正在滑动,过滤掉点击");
            motionEvent.setAction(3);
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.g = y;
                break;
            case 2:
                y -= this.g;
                RecyclerView recyclerView = (RecyclerView) this.a;
                View childAt = recyclerView.getChildAt(com.marshalchen.ultimaterecyclerview.b.a.a(recyclerView).a());
                action = ((childAt == null || childAt.getTop() != recyclerView.getPaddingTop() || y <= 0.0f) && recyclerView.getChildCount() != 0) ? 0 : true;
                if (!(this.j || action == 0)) {
                    this.j = true;
                    motionEvent.setAction(3);
                    dispatchTouchEvent(motionEvent);
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction(0);
                    return dispatchTouchEvent(obtain);
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
                this.g = y;
                this.h = x;
                break;
            case 1:
            case 3:
                this.i = false;
                c();
                break;
            case 2:
                float f = y - this.g;
                float f2 = x - this.h;
                if (Math.abs(f) > ((float) this.d) && Math.abs(f) > Math.abs(f2)) {
                    this.i = true;
                    RecyclerView recyclerView = (RecyclerView) this.a;
                    View childAt = recyclerView.getChildAt(com.marshalchen.ultimaterecyclerview.b.a.a(recyclerView).a());
                    if ((childAt != null && childAt.getTop() == recyclerView.getPaddingTop() && f > 0.0f) || recyclerView.getChildCount() == 0) {
                        b();
                        this.c.addMovement(motionEvent);
                        this.g = y;
                        return true;
                    }
                }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        b();
        this.c.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        motionEvent.getX();
        switch (action) {
            case 0:
                if (!this.b.isFinished()) {
                    this.b.abortAnimation();
                }
                this.g = y;
                return true;
            case 1:
                this.i = false;
                this.c.computeCurrentVelocity(1000, (float) this.e);
                int yVelocity = (int) this.c.getYVelocity();
                if (Math.abs(yVelocity) > this.f) {
                    a(-yVelocity);
                } else {
                    yVelocity = getMaxScrollDistance();
                    if (((float) Math.abs(getScrollY())) >= ((float) yVelocity) / 2.0f) {
                        scrollTo(0, -yVelocity);
                    } else {
                        scrollTo(0, 0);
                    }
                }
                c();
                break;
            case 2:
                float f = y - this.g;
                if (!this.i && Math.abs(f) > ((float) this.d)) {
                    this.i = true;
                }
                if (this.i) {
                    scrollBy(0, (int) (-f));
                    if (getScrollY() == 0 && f < 0.0f) {
                        motionEvent.setAction(0);
                        dispatchTouchEvent(motionEvent);
                        this.j = false;
                    }
                }
                this.g = y;
                break;
            case 3:
                this.i = false;
                c();
                if (!this.b.isFinished()) {
                    this.b.abortAnimation();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private int getMaxScrollDistance() {
        return this.a.getMeasuredHeight();
    }

    private void a(int i) {
        int i2;
        this.n = true;
        int maxScrollDistance = getMaxScrollDistance();
        if (i < 0) {
            i2 = -maxScrollDistance;
        } else {
            i2 = 0;
        }
        this.b.fling(0, getScrollY(), 0, i, 0, 0, i2, i2);
        invalidate();
    }

    public void scrollTo(int i, int i2) {
        if (i2 > 0) {
            i2 = 0;
        }
        int maxScrollDistance = getMaxScrollDistance();
        if (i2 < (-maxScrollDistance)) {
            i2 = -maxScrollDistance;
        }
        if (i2 != getScrollY()) {
            super.scrollTo(i, i2);
        }
        if (this.k) {
            this.k = false;
        } else if (getScrollY() <= (-maxScrollDistance) && this.l != null) {
            this.l.a();
            a();
        }
    }

    private void a() {
        this.j = false;
        this.k = true;
        this.m = false;
        this.i = false;
        this.g = 0.0f;
        this.n = false;
    }

    public void computeScroll() {
        if (!this.j || !this.n) {
            return;
        }
        if (this.b.computeScrollOffset()) {
            this.m = true;
            scrollTo(0, this.b.getCurrY());
            invalidate();
            return;
        }
        this.m = false;
    }

    private void b() {
        if (this.c == null) {
            this.c = VelocityTracker.obtain();
        }
    }

    private void c() {
        if (this.c != null) {
            this.c.clear();
            this.c.recycle();
            this.c = null;
        }
    }

    public void a(a aVar) {
        this.l = aVar;
    }
}
