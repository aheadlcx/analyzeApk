package com.budejie.www.activity.label.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.budejie.www.util.an;

public class SwipeLayout extends LinearLayout {
    private ViewDragHelper a;
    private View b;
    private View c;
    private int d;
    private int e;
    private boolean f;
    private float g;
    private float h;

    private class a extends Callback {
        final /* synthetic */ SwipeLayout a;

        private a(SwipeLayout swipeLayout) {
            this.a = swipeLayout;
        }

        public boolean tryCaptureView(View view, int i) {
            return view == this.a.b || view == this.a.c;
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            this.a.e = i;
            if (view == this.a.b) {
                this.a.c.offsetLeftAndRight(i3);
            } else {
                this.a.b.offsetLeftAndRight(i3);
            }
            if (this.a.c.getVisibility() == 8) {
                this.a.c.setVisibility(0);
            }
            this.a.invalidate();
            if (this.a.isPressed()) {
                this.a.setPressed(false);
            }
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (view == this.a.b) {
                return Math.min(Math.max((-this.a.getPaddingLeft()) - this.a.d, i), 0);
            }
            return Math.min(Math.max(i, (this.a.getPaddingLeft() + this.a.b.getMeasuredWidth()) - this.a.d), (this.a.getPaddingLeft() + this.a.b.getMeasuredWidth()) + this.a.getPaddingRight());
        }

        public int getViewHorizontalDragRange(View view) {
            return this.a.d;
        }

        public void onViewReleased(View view, float f, float f2) {
            int i = 1;
            if (this.a.f) {
                if (((double) f) > 800.0d) {
                    i = 0;
                } else if (((double) f) >= (-4650248090236747776) && this.a.e > (-this.a.d) / 2) {
                    i = this.a.e > (-this.a.d) / 2 ? 0 : 0;
                }
                if (i != 0) {
                    i = -this.a.d;
                } else {
                    i = 0;
                }
                this.a.a.smoothSlideViewTo(this.a.b, i, 0);
                ViewCompat.postInvalidateOnAnimation(this.a);
            }
        }
    }

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = true;
        this.a = ViewDragHelper.create(this, new a());
    }

    public void setNeedDrag(boolean z) {
        this.f = z;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = getChildAt(0);
        this.c = getChildAt(1);
        this.c.setVisibility(8);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.d = this.c.getMeasuredWidth();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return super.onTouchEvent(motionEvent);
        }
        this.a.processTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.g = motionEvent.getX();
                break;
            case 1:
            case 3:
                if (this.h > ((float) an.a(getContext(), 10))) {
                    this.h = 0.0f;
                    return true;
                }
                break;
            case 2:
                this.h = Math.abs(this.g - motionEvent.getX());
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void computeScroll() {
        if (this.f && this.a.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
