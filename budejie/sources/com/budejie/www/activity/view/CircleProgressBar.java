package com.budejie.www.activity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.budejie.www.R$styleable;

public class CircleProgressBar extends View {
    private float a = 4.0f;
    private float b = 0.0f;
    private int c = 0;
    private int d = 100;
    private int e = -90;
    private int f = -12303292;
    private int g = -12303292;
    private RectF h;
    private Paint i;
    private Paint j;

    public float getStrokeWidth() {
        return this.a;
    }

    public void setStrokeWidth(float f) {
        this.a = f;
        this.i.setStrokeWidth(f);
        this.j.setStrokeWidth(f);
        invalidate();
        requestLayout();
    }

    public float getProgress() {
        return this.b;
    }

    public void setProgress(float f) {
        this.b = f;
        invalidate();
    }

    public int getMin() {
        return this.c;
    }

    public void setMin(int i) {
        this.c = i;
        invalidate();
    }

    public int getMax() {
        return this.d;
    }

    public void setMax(int i) {
        this.d = i;
        invalidate();
    }

    public int getColor() {
        return this.f;
    }

    public void setColor(int i) {
        this.f = i;
        this.i.setColor(a(i, 0.3f));
        this.j.setColor(i);
        invalidate();
        requestLayout();
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.h = new RectF();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.CircleProgressBar, 0, 0);
        try {
            this.a = obtainStyledAttributes.getDimension(5, this.a);
            this.b = obtainStyledAttributes.getFloat(2, this.b);
            this.f = obtainStyledAttributes.getInt(3, this.f);
            this.g = obtainStyledAttributes.getInt(4, this.f);
            this.c = obtainStyledAttributes.getInt(0, this.c);
            this.d = obtainStyledAttributes.getInt(1, this.d);
            this.i = new Paint(1);
            this.i.setColor(this.g);
            this.i.setStyle(Style.STROKE);
            this.i.setStrokeWidth(this.a);
            this.j = new Paint(1);
            this.j.setColor(this.f);
            this.j.setStyle(Style.STROKE);
            this.j.setStrokeWidth(this.a);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(this.h, this.i);
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.h, (float) this.e, (360.0f * this.b) / ((float) this.d), false, this.j);
    }

    protected void onMeasure(int i, int i2) {
        int min = Math.min(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
        setMeasuredDimension(min, min);
        this.h.set((this.a / 2.0f) + 0.0f, (this.a / 2.0f) + 0.0f, ((float) min) - (this.a / 2.0f), ((float) min) - (this.a / 2.0f));
    }

    public int a(int i, float f) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * f), Color.red(i), Color.green(i), Color.blue(i));
    }
}
