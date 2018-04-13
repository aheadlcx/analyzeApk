package com.scwang.smartrefresh.layout.b.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

public class d extends View {
    private int a;
    private int b;
    private Path c;
    private Paint d;
    private int e;

    public d(Context context) {
        this(context, null, 0);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = -1;
        a();
    }

    private void a() {
        this.c = new Path();
        this.d = new Paint();
        this.d.setColor(-14736346);
        this.d.setAntiAlias(true);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(getSuggestedMinimumHeight(), i2));
    }

    public void setWaveColor(@ColorInt int i) {
        this.d.setColor(i);
    }

    public int getHeadHeight() {
        return this.b;
    }

    public void setHeadHeight(int i) {
        this.b = i;
    }

    public int getWaveHeight() {
        return this.a;
    }

    public void setWaveHeight(int i) {
        this.a = i;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        this.c.reset();
        this.c.lineTo(0.0f, (float) this.b);
        this.c.quadTo(this.e >= 0 ? (float) this.e : (float) (width / 2), (float) (this.b + this.a), (float) width, (float) this.b);
        this.c.lineTo((float) width, 0.0f);
        canvas.drawPath(this.c, this.d);
    }

    public void setWaveOffsetX(int i) {
        this.e = i;
    }
}
