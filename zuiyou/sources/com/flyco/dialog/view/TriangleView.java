package com.flyco.dialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TriangleView extends View {
    private Paint a = new Paint(1);
    private Path b = new Path();
    private int c;
    private int d;

    public TriangleView(Context context) {
        super(context);
    }

    public TriangleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setColor(int i) {
        this.c = i;
        invalidate();
    }

    public void setGravity(int i) {
        this.d = i;
        invalidate();
    }

    public int getColor() {
        return this.c;
    }

    public int getGravity() {
        return this.d;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        this.a.setColor(this.c);
        this.b.reset();
        if (this.d == 48) {
            this.b.moveTo((float) (width / 2), 0.0f);
            this.b.lineTo(0.0f, (float) height);
            this.b.lineTo((float) width, (float) height);
            this.b.close();
        } else if (this.d == 80) {
            this.b.moveTo(0.0f, 0.0f);
            this.b.lineTo((float) width, 0.0f);
            this.b.lineTo((float) (width / 2), (float) height);
            this.b.close();
        }
        canvas.drawPath(this.b, this.a);
    }
}
