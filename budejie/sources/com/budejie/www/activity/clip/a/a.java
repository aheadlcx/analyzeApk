package com.budejie.www.activity.clip.a;

import android.content.Context;
import android.view.MotionEvent;

public abstract class a {
    protected final Context a;
    protected boolean b;
    protected MotionEvent c;
    protected MotionEvent d;
    protected float e;
    protected float f;
    protected long g;

    protected abstract void a(int i, MotionEvent motionEvent);

    protected abstract void b(int i, MotionEvent motionEvent);

    public a(Context context) {
        this.a = context;
    }

    public boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.b) {
            b(action, motionEvent);
        } else {
            a(action, motionEvent);
        }
        return true;
    }

    protected void b(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.c;
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
        this.d = MotionEvent.obtain(motionEvent);
        this.g = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.e = motionEvent.getPressure(motionEvent.getActionIndex());
        this.f = motionEvent2.getPressure(motionEvent2.getActionIndex());
    }

    protected void a() {
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
        this.b = false;
    }
}
