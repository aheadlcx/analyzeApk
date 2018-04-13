package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import android.support.v4.view.BetterViewPager;
import android.view.MotionEvent;

class c extends BetterViewPager {
    private boolean a = true;

    public c(Context context) {
        super(context);
    }

    public boolean isPagingEnabled() {
        return this.a;
    }

    public void setPagingEnabled(boolean z) {
        this.a = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.a && super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a && super.onTouchEvent(motionEvent);
    }

    public boolean canScrollVertically(int i) {
        return this.a && super.canScrollVertically(i);
    }

    public boolean canScrollHorizontally(int i) {
        return this.a && super.canScrollHorizontally(i);
    }
}
