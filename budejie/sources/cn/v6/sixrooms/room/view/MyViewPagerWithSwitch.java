package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPagerWithSwitch extends ViewPager {
    private boolean a = true;

    public MyViewPagerWithSwitch(Context context) {
        super(context);
    }

    public MyViewPagerWithSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.a || super.onTouchEvent(motionEvent);
    }

    public boolean isScrollble() {
        return this.a;
    }

    public void setScrollble(boolean z) {
        this.a = z;
    }
}
