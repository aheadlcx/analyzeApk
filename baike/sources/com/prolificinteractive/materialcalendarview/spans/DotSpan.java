package com.prolificinteractive.materialcalendarview.spans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

public class DotSpan implements LineBackgroundSpan {
    public static final float DEFAULT_RADIUS = 3.0f;
    private final float a;
    private final int b;
    private int c;

    public DotSpan() {
        this.a = 3.0f;
        this.b = 0;
    }

    public DotSpan(int i) {
        this.a = 3.0f;
        this.b = i;
    }

    public DotSpan(float f) {
        this.a = f;
        this.b = 0;
    }

    public DotSpan(float f, int i) {
        this.a = f;
        this.b = i;
    }

    public DotSpan(float f, int i, int i2) {
        this.a = f;
        this.b = i;
        this.c = i2;
    }

    public void drawBackground(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, int i8) {
        int color = paint.getColor();
        if (this.b != 0) {
            paint.setColor(this.b);
        }
        canvas.drawCircle((float) ((i + i2) / 2), ((float) (this.c + i5)) + this.a, this.a, paint);
        paint.setColor(color);
    }
}
