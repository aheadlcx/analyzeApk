package com.bdj.picture.edit.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.bdj.picture.edit.a.j;

public class RoundProgressBar extends View {
    private Paint a;
    private int b;
    private int c;
    private int d;
    private float e;
    private float f;
    private int g;
    private int h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private RectF n;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = 0;
        this.l = getWidth() / 2;
        this.m = (int) (((float) this.l) - (this.f / 2.0f));
        this.a = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.RoundProgressBar);
        this.b = obtainStyledAttributes.getColor(j.RoundProgressBar_pciEditroundColor, 0);
        this.c = obtainStyledAttributes.getColor(j.RoundProgressBar_pciEditroundProgressColor, -16711936);
        this.d = obtainStyledAttributes.getColor(j.RoundProgressBar_pciEditTextColor, -16711936);
        this.e = obtainStyledAttributes.getDimension(j.RoundProgressBar_pciEditTextSize, 15.0f);
        this.f = obtainStyledAttributes.getDimension(j.RoundProgressBar_pciEditroundWidth, 5.0f);
        this.g = obtainStyledAttributes.getInteger(j.RoundProgressBar_pciEditmax, 100);
        this.i = obtainStyledAttributes.getBoolean(j.RoundProgressBar_pciEdittextIsDisplayable, true);
        this.j = obtainStyledAttributes.getInt(j.RoundProgressBar_pciEditstyle, 0);
        obtainStyledAttributes.recycle();
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.k == 0) {
            this.l = getWidth() / 2;
            this.m = (int) (((float) this.l) - (this.f / 2.0f));
            this.a.setColor(this.b);
            this.a.setStyle(Style.STROKE);
            this.a.setStrokeWidth(this.f);
            this.a.setAntiAlias(true);
            canvas.drawCircle((float) this.l, (float) this.l, (float) this.m, this.a);
            this.a.setStrokeWidth(0.0f);
            this.a.setColor(this.d);
            this.a.setTextSize(this.e);
            this.a.setTypeface(Typeface.DEFAULT_BOLD);
            int i = (int) ((((float) this.h) / ((float) this.g)) * 100.0f);
            float measureText = this.a.measureText(i + "%");
            if (this.i && i != 0 && this.j == 0) {
                canvas.drawText(i + "%", ((float) this.l) - (measureText / 2.0f), ((float) this.l) + (this.e / 2.0f), this.a);
            }
            this.a.setStrokeWidth(this.f);
            this.a.setColor(this.c);
            this.n = new RectF((float) (this.l - this.m), (float) (this.l - this.m), (float) (this.l + this.m), (float) (this.l + this.m));
            switch (this.j) {
                case 0:
                    this.a.setStyle(Style.STROKE);
                    if (this.h != 0) {
                        canvas.drawArc(this.n, 0.0f, (float) ((this.h * 360) / this.g), false, this.a);
                        return;
                    }
                    return;
                case 1:
                    this.a.setStyle(Style.FILL_AND_STROKE);
                    if (this.h != 0) {
                        canvas.drawArc(this.n, 0.0f, 360.0f, true, this.a);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (this.k == 1) {
            switch (this.j) {
                case 0:
                    return;
                case 1:
                    this.a.setStyle(Style.FILL_AND_STROKE);
                    if (this.h != 0) {
                        canvas.drawArc(this.n, (float) ((this.h * 360) / this.g), (float) ((int) (360.0d * (1.0d - ((((double) this.h) * 1.0d) / ((double) this.g))))), true, this.a);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public synchronized int getMax() {
        return this.g;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.g = i;
    }

    public synchronized int getProgress() {
        return this.h;
    }

    public synchronized void setProgress(int i) {
        this.k = 1;
        if (i < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (i > this.g) {
            i = this.g;
        }
        if (i <= this.g) {
            this.h = i;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return this.b;
    }

    public void setCricleColor(int i) {
        this.b = i;
    }

    public int getCricleProgressColor() {
        return this.c;
    }

    public void setCricleProgressColor(int i) {
        this.c = i;
    }

    public int getTextColor() {
        return this.d;
    }

    public void setTextColor(int i) {
        this.d = i;
    }

    public float getTextSize() {
        return this.e;
    }

    public void setTextSize(float f) {
        this.e = f;
    }

    public float getRoundWidth() {
        return this.f;
    }

    public void setRoundWidth(float f) {
        this.f = f;
    }
}
