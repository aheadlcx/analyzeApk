package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.List;

public class UserManagerView extends RelativeLayout implements OnGestureListener {
    private int a;
    private onScrollStateListener b;
    private Scroller c;
    private float d;
    private boolean e = false;
    private final int f = 20;
    private final int g = 50;
    private float h;
    private float i;
    private GestureDetector j;
    private boolean k;
    private List<View> l = new ArrayList();

    public interface onScrollStateListener {
        void move2ScreenRight();

        void up2ScreenLeftHalf();
    }

    public void setOnScrollStateListener(onScrollStateListener onscrollstatelistener) {
        this.b = onscrollstatelistener;
    }

    public UserManagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public UserManagerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public UserManagerView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.c = new Scroller(getContext());
        this.j = new GestureDetector(this);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.a = getWidth();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        r0 = r6.l;
        r3 = r0.iterator();
    L_0x0008:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x002c;
    L_0x000e:
        r0 = r3.next();
        r0 = (android.view.View) r0;
        r0 = r6.a(r0);
        r4 = r7.getX();
        r4 = (int) r4;
        r5 = r7.getY();
        r5 = (int) r5;
        r0 = r0.contains(r4, r5);
        if (r0 == 0) goto L_0x0008;
    L_0x0028:
        r0 = r2;
    L_0x0029:
        if (r0 == 0) goto L_0x002e;
    L_0x002b:
        return r1;
    L_0x002c:
        r0 = r1;
        goto L_0x0029;
    L_0x002e:
        r0 = r7.getRawX();
        r6.d = r0;
        r0 = r7.getAction();
        switch(r0) {
            case 0: goto L_0x003e;
            case 1: goto L_0x003b;
            case 2: goto L_0x004c;
            default: goto L_0x003b;
        };
    L_0x003b:
        r0 = r1;
    L_0x003c:
        r1 = r0;
        goto L_0x002b;
    L_0x003e:
        r0 = r7.getX();
        r6.h = r0;
        r0 = r7.getY();
        r6.i = r0;
        r0 = r1;
        goto L_0x003c;
    L_0x004c:
        r0 = r7.getX();
        r3 = r6.h;
        r0 = r0 - r3;
        r0 = (int) r0;
        r3 = r7.getY();
        r4 = r6.i;
        r3 = r3 - r4;
        r3 = (int) r3;
        r4 = java.lang.Math.abs(r0);
        r5 = java.lang.Math.abs(r3);
        if (r4 <= r5) goto L_0x003b;
    L_0x0066:
        r0 = r0 - r3;
        r0 = java.lang.Math.abs(r0);
        r3 = 10;
        if (r0 <= r3) goto L_0x003b;
    L_0x006f:
        r0 = r2;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.widgets.phone.UserManagerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.j.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.h = motionEvent.getX();
                this.i = motionEvent.getY();
                break;
            case 1:
                if (!this.e) {
                    LayoutParams layoutParams = (LayoutParams) getLayoutParams();
                    if (layoutParams.leftMargin < this.a / 2) {
                        this.c.startScroll(layoutParams.leftMargin, 0, -layoutParams.leftMargin, 0);
                    } else if (this.b != null) {
                        this.b.up2ScreenLeftHalf();
                    }
                    invalidate();
                    break;
                }
                break;
        }
        return true;
    }

    public void computeScroll() {
        if (this.c.computeScrollOffset()) {
            LayoutParams layoutParams = (LayoutParams) getLayoutParams();
            int currX = this.c.getCurrX();
            layoutParams.rightMargin = -currX;
            layoutParams.leftMargin = currX;
            setLayoutParams(layoutParams);
            if (currX >= this.a && this.b != null) {
                this.b.move2ScreenRight();
            }
            postInvalidate();
        }
        super.computeScroll();
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent2.getRawX() - this.d > 20.0f && f > 50.0f) {
            if (((LayoutParams) getLayoutParams()).leftMargin > 10 && this.k && this.b != null) {
                this.e = true;
                this.b.up2ScreenLeftHalf();
            }
            invalidate();
        }
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.e = false;
        int x = (int) (motionEvent2.getX() - this.h);
        this.k = f > 0.0f;
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.leftMargin += x;
        layoutParams.rightMargin -= x;
        if (layoutParams.rightMargin >= 0) {
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        } else if (layoutParams.leftMargin >= this.a && this.b != null) {
            this.b.move2ScreenRight();
        }
        setLayoutParams(layoutParams);
        return false;
    }

    public void finishView() {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        this.c.startScroll(layoutParams.leftMargin, 0, this.a - layoutParams.leftMargin, 0);
        invalidate();
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    private Rect a(View view) {
        Rect rect = new Rect();
        view.getHitRect(rect);
        View view2 = (View) view.getParent();
        if (view2 == null || (view2 instanceof UserManagerView)) {
            return rect;
        }
        Rect a = a(view2);
        rect.left += a.left;
        rect.top += a.top;
        rect.right += a.left;
        rect.bottom = a.top + rect.bottom;
        return rect;
    }

    public void addIgnoredView(View view) {
        if (!this.l.contains(view)) {
            this.l.add(view);
        }
    }

    public void removeIgnoredView(View view) {
        this.l.remove(view);
    }

    public void clearIgnoredViews() {
        this.l.clear();
    }
}
