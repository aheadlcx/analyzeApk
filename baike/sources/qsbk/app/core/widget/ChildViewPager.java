package qsbk.app.core.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ChildViewPager extends ViewPager {
    private int a;
    private int b;

    public ChildViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChildViewPager(Context context) {
        super(context);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (getChildCount() > 1) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                this.a = (int) motionEvent.getRawX();
                this.b = (int) motionEvent.getRawY();
                break;
            case 2:
                int rawX = (int) motionEvent.getRawX();
                if (Math.abs(rawX - this.a) > Math.abs(((int) motionEvent.getRawY()) - this.b)) {
                    if (rawX <= this.a) {
                        if (getCurrentItem() == getAdapter().getCount() - 1) {
                            getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                        }
                    } else if (getCurrentItem() == 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
