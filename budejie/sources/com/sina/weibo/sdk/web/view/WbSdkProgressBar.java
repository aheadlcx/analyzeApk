package com.sina.weibo.sdk.web.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public class WbSdkProgressBar extends View {
    int a;
    private int b;
    private int c;
    private int d;
    private RectF e;
    private Paint f;
    private float g;
    private final int h;
    private final int i;
    private float j;
    private long k;
    private float l;
    private long m;
    private long n;
    private double o;
    private double p;
    private boolean q;
    private boolean r;
    private Handler s;

    public WbSdkProgressBar(Context context) {
        this(context, null);
    }

    public WbSdkProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WbSdkProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 20;
        this.i = 300;
        this.k = 0;
        this.l = 200.0f;
        this.m = 180;
        this.n = 0;
        this.o = 490.0d;
        this.q = false;
        this.r = true;
        this.a = 0;
        this.s = new WbSdkProgressBar$1(this);
        this.b = a(context, 50);
        this.c = a(context, 5);
        this.d = a(context, 3);
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.f.setColor(-48861);
        this.f.setStyle(Style.STROKE);
        this.f.setStrokeWidth((float) this.c);
        this.e = new RectF((float) this.d, (float) this.d, (float) (this.b - this.d), (float) (this.b - this.d));
    }

    private int a(Context context, int i) {
        return (int) (context.getResources().getDisplayMetrics().density * ((float) i));
    }

    protected synchronized void onDraw(Canvas canvas) {
        long abs = Math.abs(SystemClock.uptimeMillis() - this.k) % 360;
        float f = (this.l * ((float) abs)) / 1000.0f;
        a(abs);
        this.k = SystemClock.uptimeMillis();
        this.g += f;
        if (this.g >= 360.0f) {
            this.g -= 360.0f;
        }
        canvas.drawArc(this.e, this.g - 90.0f, this.j + 20.0f, false, this.f);
        if (this.r) {
            if (VERSION.SDK_INT >= 21) {
                postInvalidate();
            } else {
                invalidate();
            }
        }
    }

    public void setProgressColor(int i) {
        this.f.setColor(i);
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 8) {
            this.s.sendEmptyMessageDelayed(0, 1000);
        } else if (i == 0 && getVisibility() == 0) {
            this.s.removeMessages(0);
            this.r = true;
            invalidate();
        }
    }

    private void a(long j) {
        if (this.n >= this.m) {
            this.p += (double) j;
            if (this.p >= this.o) {
                this.p -= this.o;
                this.n = 0;
                this.q = !this.q;
            }
            float cos = (((float) Math.cos(((this.p / this.o) + 1.0d) * 3.141592653589793d)) / 2.0f) + 0.5f;
            if (this.q) {
                cos = (1.0f - cos) * ((float) 280);
                this.g += this.j - cos;
                this.j = cos;
                return;
            }
            this.j = cos * ((float) 280);
            return;
        }
        this.n += j;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(this.b, this.b);
    }
}
