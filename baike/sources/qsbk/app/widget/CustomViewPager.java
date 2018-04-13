package qsbk.app.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private boolean mCanSwipe = true;

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomViewPager(Context context) {
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.mCanSwipe) {
            try {
                z = super.onInterceptTouchEvent(motionEvent);
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.mCanSwipe) {
            try {
                z = super.onTouchEvent(motionEvent);
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public boolean canSwipe() {
        return this.mCanSwipe;
    }

    public void setCanSwipe(boolean z) {
        this.mCanSwipe = z;
    }
}
