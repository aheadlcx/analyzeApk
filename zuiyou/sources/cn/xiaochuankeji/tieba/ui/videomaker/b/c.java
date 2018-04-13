package cn.xiaochuankeji.tieba.ui.videomaker.b;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public abstract class c extends a {
    protected float h;
    protected float i;
    protected float j;
    protected float k;
    private final float l;
    private float m;
    private float n;

    protected abstract void a(int i, MotionEvent motionEvent);

    protected abstract void b(int i, MotionEvent motionEvent);

    public c(Context context) {
        super(context);
        this.l = (float) ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    protected void b(MotionEvent motionEvent) {
        super.b(motionEvent);
        try {
            MotionEvent motionEvent2 = this.c;
            this.m = -1.0f;
            this.n = -1.0f;
            float x = motionEvent2.getX(0);
            float y = motionEvent2.getY(0);
            float x2 = motionEvent2.getX(1);
            float y2 = motionEvent2.getY(1) - y;
            this.h = x2 - x;
            this.i = y2;
            y2 = motionEvent.getX(0);
            x = motionEvent.getY(0);
            x = motionEvent.getY(1) - x;
            this.j = motionEvent.getX(1) - y2;
            this.k = x;
        } catch (IllegalArgumentException e) {
        }
    }

    protected boolean c(MotionEvent motionEvent) {
        return false;
    }
}
