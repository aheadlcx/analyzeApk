package com.budejie.www.widget.curtain;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.budejie.www.activity.video.k;

public class CurtainVideoContainerLayout extends RelativeLayout {
    boolean a;
    private float b;
    private float c;
    private int d;
    private boolean e;
    private boolean f;
    private d g;

    public boolean getScrollSloped() {
        return this.f;
    }

    public boolean getTouched() {
        return this.e;
    }

    public void a() {
        this.e = false;
        this.a = false;
        this.f = false;
    }

    public CurtainVideoContainerLayout(Context context) {
        super(context);
        this.d = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public CurtainVideoContainerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.e && motionEvent.getAction() == 0) {
            onTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.e = true;
                this.f = false;
                this.b = motionEvent.getRawX();
                this.c = motionEvent.getRawY();
                if (!a((int) this.b, (int) this.c)) {
                    this.a = false;
                    break;
                }
                this.a = true;
                break;
            case 2:
                float abs = Math.abs(motionEvent.getRawX() - this.b);
                float abs2 = Math.abs(motionEvent.getRawY() - this.c);
                if ((abs > ((float) this.d) || abs2 > ((float) this.d)) && (!this.a || abs < abs2)) {
                    this.f = true;
                    if (this.g != null) {
                        this.g.b();
                        break;
                    }
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean a(int i, int i2) {
        SeekBar c = k.a(getContext()).c();
        if (c == null) {
            return false;
        }
        int[] iArr = new int[2];
        c.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + c.getWidth(), c.getHeight() + iArr[1]).contains(i, i2);
    }

    public void setOnInterceptTouchListener(d dVar) {
        this.g = dVar;
    }
}
