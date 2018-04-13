package com.scwang.smartrefresh.layout.b.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.view.View;

public class b extends View {
    private int a = 7;
    private Paint b = new Paint();
    private float c;
    private float d;

    public b(Context context) {
        super(context);
        this.b.setAntiAlias(true);
        this.b.setColor(-1);
        this.c = (float) com.scwang.smartrefresh.layout.f.b.a(7.0f);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(getSuggestedMinimumHeight(), i2));
    }

    public void setDotColor(@ColorInt int i) {
        this.b.setColor(i);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        float f = (this.d * ((float) (width / this.a))) - (this.d > 1.0f ? ((this.d - 1.0f) * ((float) (width / this.a))) / this.d : 0.0f);
        float f2 = ((float) height) - (this.d > 1.0f ? (((this.d - 1.0f) * ((float) height)) / 2.0f) / this.d : 0.0f);
        for (int i = 0; i < this.a; i++) {
            float f3 = (1.0f + ((float) i)) - ((1.0f + ((float) this.a)) / 2.0f);
            float abs = 255.0f * (1.0f - (2.0f * (Math.abs(f3) / ((float) this.a))));
            float b = com.scwang.smartrefresh.layout.f.b.b((float) height);
            this.b.setAlpha((int) (((double) abs) * (1.0d - (1.0d / Math.pow((((double) b) / 800.0d) + 1.0d, 15.0d)))));
            abs = this.c * (1.0f - (1.0f / ((b / 10.0f) + 1.0f)));
            canvas.drawCircle((f3 * f) + (((float) (width / 2)) - (abs / 2.0f)), f2 / 2.0f, abs, this.b);
        }
    }

    public void setFraction(float f) {
        this.d = f;
    }
}
