package com.budejie.www.activity.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.budejie.www.util.an;

public class MyViewPager extends ViewPager {
    PointF a = new PointF();
    PointF b = new PointF();
    a c;
    PointF d = new PointF();

    public interface a {
        void a(int i);
    }

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyViewPager(Context context) {
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.b.x = motionEvent.getX();
        this.b.y = motionEvent.getY();
        if (motionEvent.getAction() == 0) {
            this.a.x = motionEvent.getX();
            this.a.y = motionEvent.getY();
            if (getChildCount() > 1) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        if (motionEvent.getAction() == 2 && getChildCount() > 1) {
            this.d.x = motionEvent.getX();
            this.d.y = motionEvent.getY();
            float abs = Math.abs(this.d.x - this.a.x);
            if (Math.abs(this.d.y - this.a.y) <= ((float) an.a(getContext(), 10)) || abs >= ((float) an.a(getContext(), 10))) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
                return super.onTouchEvent(motionEvent);
            }
        }
        if (motionEvent.getAction() != 1 || PointF.length(this.b.x - this.a.x, this.b.y - this.a.y) >= 10.0f) {
            return super.onTouchEvent(motionEvent);
        }
        a(getCurrentItem());
        return true;
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public void setOnSingleTouchListener(a aVar) {
        this.c = aVar;
    }
}
