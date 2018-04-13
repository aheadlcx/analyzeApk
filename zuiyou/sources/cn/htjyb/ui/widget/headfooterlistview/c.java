package cn.htjyb.ui.widget.headfooterlistview;

import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class c {
    private ListView a;
    @IdRes
    private int b;
    private int c;
    private View d;
    private long e;
    private float f;
    private float g;
    private boolean h;

    public boolean a(MotionEvent motionEvent) {
        if (this.a == null || this.a.getAdapter() == null || this.b <= 0) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                return b(motionEvent);
            case 1:
                boolean e = e(motionEvent);
                a();
                return e;
            case 2:
                return d(motionEvent);
            case 3:
                return c(motionEvent);
            default:
                if (this.d != null) {
                    return this.d.onTouchEvent(motionEvent);
                }
                return false;
        }
    }

    private boolean b(MotionEvent motionEvent) {
        View f = f(motionEvent);
        if (f == null) {
            return false;
        }
        int positionForView = this.a.getPositionForView(f) - this.a.getHeaderViewsCount();
        if (positionForView == this.c) {
            return false;
        }
        this.d = f;
        this.c = positionForView;
        this.e = System.currentTimeMillis();
        this.f = motionEvent.getX();
        this.g = motionEvent.getY();
        return this.d.dispatchTouchEvent(motionEvent);
    }

    private boolean c(MotionEvent motionEvent) {
        if (this.d == null) {
            return false;
        }
        return this.d.onTouchEvent(motionEvent);
    }

    private boolean d(MotionEvent motionEvent) {
        if (this.d == null) {
            return false;
        }
        float y = motionEvent.getY() - this.g;
        float abs = Math.abs(motionEvent.getX() - this.f);
        y = Math.abs(y);
        ((HeaderFooterListView) this.a).getScrollState();
        if (abs >= y || y < 30.0f) {
            return abs > 0.0f ? this.d.dispatchTouchEvent(motionEvent) : false;
        } else {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            this.d.onTouchEvent(obtain);
            obtain.recycle();
            if (!this.h) {
                obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(0);
                this.a.onTouchEvent(obtain);
                this.h = true;
            }
            this.a.onTouchEvent(motionEvent);
            return false;
        }
    }

    private boolean e(MotionEvent motionEvent) {
        if (this.d == null) {
            return false;
        }
        float x = motionEvent.getX() - this.f;
        return this.d.dispatchTouchEvent(motionEvent);
    }

    private void a() {
        this.e = 0;
        this.f = 0.0f;
        this.g = 0.0f;
        this.d = null;
        this.c = -1;
        this.h = false;
    }

    private View f(MotionEvent motionEvent) {
        Rect rect = new Rect();
        int childCount = this.a.getChildCount();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int i = 0;
        View view = null;
        while (i < childCount && view == null) {
            View childAt = this.a.getChildAt(i);
            if (childAt != null) {
                childAt.getGlobalVisibleRect(rect);
                if (rect.contains(rawX, rawY)) {
                    i++;
                    view = childAt;
                }
            }
            childAt = view;
            i++;
            view = childAt;
        }
        if (!(this.b == 0 || view == null)) {
            Rect rect2 = new Rect();
            childAt = view.findViewById(this.b);
            if (childAt != null && childAt.getVisibility() == 0) {
                childAt.getGlobalVisibleRect(rect2);
                if (rect2.contains(rawX, rawY)) {
                    return childAt;
                }
            }
        }
        return null;
    }
}
