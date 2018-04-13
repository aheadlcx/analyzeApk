package cn.v6.sixrooms.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

protected class VLPagerView$VLScrollableViewPager extends ViewPager {
    private static int a = 256;
    private boolean b = true;
    private int c;
    private int d;
    private int e;

    public void setType(int i) {
        this.e = i;
    }

    public void setManualResId() {
        int i = a;
        a = i + 1;
        setId(i);
    }

    public VLPagerView$VLScrollableViewPager(Context context) {
        super(context);
    }

    public VLPagerView$VLScrollableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.b) {
            return false;
        }
        if (this.e != 16) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            this.d = (int) motionEvent.getX();
            this.c = (int) motionEvent.getY();
        } else if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 2) && Math.abs(motionEvent.getX() - ((float) this.d)) > Math.abs(motionEvent.getY() - ((float) this.c)) && Math.abs(motionEvent.getX() - ((float) this.d)) > ((float) SixRoomsUtils.dip2px(8.0f))) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.b && super.onTouchEvent(motionEvent);
    }

    public boolean getScrollable() {
        return this.b;
    }

    public void setScrollable(boolean z) {
        this.b = z;
    }
}
