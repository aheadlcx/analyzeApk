package com.budejie.www.widget.curtain;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;

public class CurtainBGLayout extends View {
    private float a;
    private float b;
    private int c;
    private boolean d;
    private boolean e;
    private d f;
    private boolean g;
    private OnTouchListener h = new OnTouchListener(this) {
        final /* synthetic */ CurtainBGLayout a;

        {
            this.a = r1;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.a.g;
        }
    };

    public boolean getScrollSloped() {
        return this.d;
    }

    public boolean getTouched() {
        return this.e;
    }

    public void a() {
        this.e = false;
        this.d = false;
    }

    public CurtainBGLayout(Context context) {
        super(context);
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public CurtainBGLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.e = true;
                this.d = false;
                this.a = motionEvent.getRawX();
                this.b = motionEvent.getRawY();
                break;
            case 1:
            case 3:
                if (!this.d && Math.abs(motionEvent.getRawX() - this.a) < ((float) this.c) && Math.abs(motionEvent.getRawY() - this.b) < ((float) this.c) && this.f != null) {
                    this.f.a();
                    break;
                }
            case 2:
                if (Math.abs(motionEvent.getRawX() - this.a) > ((float) this.c) || Math.abs(motionEvent.getRawY() - this.b) > ((float) this.c)) {
                    this.d = true;
                    if (this.f != null) {
                        this.f.b();
                        break;
                    }
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnInterceptTouchListener(d dVar) {
        this.f = dVar;
    }

    public void setFullScreenState(boolean z) {
    }

    void a(boolean z) {
        this.g = z;
        setOnTouchListener(this.h);
    }
}
