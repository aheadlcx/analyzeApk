package cn.v6.sixrooms.room.view;

import android.support.v4.view.ViewPager;
import java.lang.reflect.Field;

public class ViewPageHelper {
    ViewPager a;
    ViewPagerScrollerUtil b = new ViewPagerScrollerUtil(this.a.getContext());

    public ViewPageHelper(ViewPager viewPager) {
        this.a = viewPager;
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this.a, this.b);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    public ViewPagerScrollerUtil getScroller() {
        return this.b;
    }

    public void setCurrentItem(int i, boolean z) {
        if (Math.abs(this.a.getCurrentItem() - i) > 1) {
            this.b.setNoDuration(true);
            this.a.setCurrentItem(i, z);
            this.b.setNoDuration(false);
            return;
        }
        this.b.setNoDuration(false);
        this.a.setCurrentItem(i, z);
    }
}
