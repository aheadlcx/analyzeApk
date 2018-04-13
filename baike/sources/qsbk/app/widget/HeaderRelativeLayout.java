package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import qsbk.app.R;

public class HeaderRelativeLayout extends RelativeLayout {
    private final Rect a;
    private ScrollChangedListener b;
    private boolean c;
    private int d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private VelocityTracker n;
    private View o;
    private int p;

    public interface ScrollChangedListener {
        void onScrollChanged(int i, int i2);
    }

    public HeaderRelativeLayout(Context context) {
        this(context, null);
    }

    public HeaderRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HeaderRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Rect();
        this.p = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HeaderRelativelayout, i, 0);
        this.d = obtainStyledAttributes.getResourceId(0, -1);
        this.e = obtainStyledAttributes.getBoolean(1, true);
        this.f = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        obtainStyledAttributes.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.g = viewConfiguration.getScaledTouchSlop();
        this.i = viewConfiguration.getScaledMinimumFlingVelocity();
        this.h = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.d == -1) {
            throw new IllegalStateException("No Head View");
        } else if (getChildCount() < 2) {
            throw new IllegalStateException("HeaderRelativeLayout must contain at least 2 child.");
        } else {
            this.o = findViewById(this.d);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.e) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        a();
        this.n.addMovement(motionEvent);
        int y;
        switch (actionMasked) {
            case 0:
                this.m = motionEvent.getPointerId(0);
                actionMasked = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                this.j = actionMasked;
                this.k = y;
                this.l = y;
                if (a(actionMasked, y)) {
                    this.c = true;
                    break;
                }
                break;
            case 1:
            case 3:
                this.n.computeCurrentVelocity(1000, (float) this.h);
                actionMasked = (int) VelocityTrackerCompat.getYVelocity(this.n, this.m);
                this.m = -1;
                b();
                if (this.c || Math.abs(actionMasked) >= this.i) {
                }
                if ((this.o.getHeight() / 2) + this.o.getTop() <= 0) {
                    this.p = -1;
                    setHeadViewOffsetTopAndBottom(this.f - this.o.getBottom());
                } else {
                    this.p = 1;
                    setHeadViewOffsetTopAndBottom(-this.o.getTop());
                }
                this.c = false;
                break;
            case 2:
                actionMasked = motionEvent.findPointerIndex(this.m);
                if (actionMasked == -1) {
                    this.m = motionEvent.getPointerId(0);
                    actionMasked = 0;
                }
                int y2 = (int) motionEvent.getY(actionMasked);
                int x = (int) motionEvent.getX(actionMasked);
                actionMasked = y2 - this.k;
                int i = x - this.j;
                this.k = y2;
                this.j = x;
                if (Math.abs(this.l - y2) >= this.g && !this.c && Math.abs(i) <= Math.abs(actionMasked)) {
                    boolean z;
                    if (actionMasked <= 0 || this.o.getTop() >= 0) {
                        y = actionMasked;
                        z = false;
                    } else {
                        y = Math.min(-this.o.getTop(), actionMasked);
                        z = true;
                    }
                    if (y < 0 && this.o.getBottom() > this.f) {
                        y = Math.max(y, this.f - this.o.getBottom());
                        z = true;
                    }
                    if (z) {
                        if (y == (-this.o.getTop())) {
                            this.p = 1;
                        } else if (y == this.f - this.o.getBottom()) {
                            this.p = -1;
                        }
                        setHeadViewOffsetTopAndBottom(y);
                        break;
                    }
                }
                break;
            case 5:
                actionMasked = motionEvent.getActionIndex();
                y = motionEvent.getPointerId(actionMasked);
                int x2 = (int) motionEvent.getX(actionMasked);
                actionMasked = (int) motionEvent.getY(actionMasked);
                this.m = y;
                this.j = x2;
                this.k = actionMasked;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            super.onLayout(z, i, i2, i3, i4);
        } catch (Exception e) {
        }
        if (this.o == null) {
            return;
        }
        if (this.p == -1) {
            setHeadViewOffsetTopAndBottom(this.f - this.o.getMeasuredHeight());
        } else if (this.p == 1) {
            setHeadViewOffsetTopAndBottom(-this.o.getTop());
        }
    }

    public void setHeadViewState(boolean z) {
        if (z) {
            this.p = 1;
            setHeadViewOffsetTopAndBottom(-this.o.getTop());
            return;
        }
        this.p = -1;
        setHeadViewOffsetTopAndBottom(this.f - this.o.getMeasuredHeight());
    }

    private void setHeadViewOffsetTopAndBottom(int i) {
        this.o.offsetTopAndBottom(i);
        b(0, i);
    }

    public boolean isHeadViewOpened() {
        return this.p == 1;
    }

    private boolean a(int i, int i2) {
        if (this.o == null) {
            return false;
        }
        this.o.getHitRect(this.a);
        return this.a.contains(i, i2);
    }

    private void a() {
        if (this.n == null) {
            this.n = VelocityTracker.obtain();
        }
    }

    private void b() {
        if (this.n != null) {
            this.n.recycle();
            this.n = null;
        }
    }

    private void b(int i, int i2) {
        if (this.b != null) {
            this.b.onScrollChanged(i, i2);
        }
    }

    public void setScrollChangedListener(ScrollChangedListener scrollChangedListener) {
        this.b = scrollChangedListener;
    }
}
