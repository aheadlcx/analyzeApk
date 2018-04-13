package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class CustomViewPager extends ViewPager {
    private ViewPageHelper a;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ViewPageHelper(this);
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    public void setCurrentItem(int i, boolean z) {
        ViewPagerScrollerUtil scroller = this.a.getScroller();
        if (Math.abs(getCurrentItem() - i) > 1) {
            scroller.setNoDuration(true);
            super.setCurrentItem(i, z);
            scroller.setNoDuration(false);
            return;
        }
        scroller.setNoDuration(false);
        super.setCurrentItem(i, z);
    }
}
