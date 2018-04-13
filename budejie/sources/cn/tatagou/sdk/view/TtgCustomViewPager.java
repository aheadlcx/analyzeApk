package cn.tatagou.sdk.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TtgCustomViewPager extends ViewPager {
    private boolean a = false;

    public TtgCustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TtgCustomViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean z) {
        this.a = z;
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            if (!this.a) {
                return super.onTouchEvent(motionEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (!this.a) {
                return super.onInterceptTouchEvent(motionEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(i, false);
    }

    public void setCurrentItem(int i) {
        super.setCurrentItem(i, false);
    }
}
