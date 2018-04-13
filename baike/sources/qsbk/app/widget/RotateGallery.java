package qsbk.app.widget;

import android.content.Context;
import android.graphics.Camera;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.Gallery;

public class RotateGallery extends Gallery {
    private static final String a = RotateGallery.class.getSimpleName();
    private Camera b = new Camera();
    private int c = 0;
    private int d = 0;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private float j;
    private float k;
    private float l;

    public RotateGallery(Context context) {
        super(context);
        setStaticTransformationsEnabled(true);
        a();
    }

    public RotateGallery(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setStaticTransformationsEnabled(true);
        a();
    }

    public RotateGallery(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setStaticTransformationsEnabled(true);
        a();
    }

    private void a() {
        this.f = ViewConfiguration.getTouchSlop();
    }

    public int getMaxRotationAngle() {
        return this.c;
    }

    public void setMaxRotationAngle(int i) {
        this.c = i;
    }

    public int getMaxZoom() {
        return this.d;
    }

    public void setMaxZoom(int i) {
        this.d = i;
    }

    private int getCenterOfCoverflow() {
        return (((getWidth() - getPaddingLeft()) - getPaddingRight()) / 2) + getPaddingLeft();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.e = getCenterOfCoverflow();
        super.onSizeChanged(i, i2, i3, i4);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            if (this.g != 2) {
                onTouchEvent(motionEvent);
            }
            this.g = 0;
            this.h = -1;
            return false;
        }
        float x;
        switch (action) {
            case 0:
                x = motionEvent.getX();
                this.k = x;
                this.i = x;
                x = motionEvent.getY();
                this.l = x;
                this.j = x;
                this.h = MotionEventCompat.getPointerId(motionEvent, 0);
                this.g = 0;
                onTouchEvent(motionEvent);
                break;
            case 2:
                action = this.h;
                if (action != -1) {
                    action = MotionEventCompat.findPointerIndex(motionEvent, action);
                    float x2 = MotionEventCompat.getX(motionEvent, action) - this.i;
                    float abs = Math.abs(x2);
                    float y = MotionEventCompat.getY(motionEvent, action);
                    x = Math.abs(y - this.l);
                    if (abs > ((float) this.f) && abs * 0.5f > x) {
                        this.g = 1;
                        this.i = x2 > 0.0f ? this.k + ((float) this.f) : this.k - ((float) this.f);
                        this.j = y;
                    } else if (x > ((float) this.f)) {
                        this.g = 2;
                        motionEvent.setAction(3);
                        onTouchEvent(motionEvent);
                    }
                    if (this.g != 2) {
                        onTouchEvent(motionEvent);
                        break;
                    }
                }
                break;
        }
        if (this.g == 1) {
            return true;
        }
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int i;
        if (a(motionEvent, motionEvent2)) {
            i = 21;
        } else {
            i = 22;
        }
        onKeyDown(i, null);
        return false;
    }

    private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2) {
        return motionEvent2.getX() > motionEvent.getX();
    }
}
