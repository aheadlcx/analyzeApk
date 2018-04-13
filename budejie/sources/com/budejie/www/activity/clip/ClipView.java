package com.budejie.www.activity.clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ClipView extends View {
    private Paint a;
    private Paint b;
    private Paint c;
    private int d;
    private int e;
    private int f;
    private int g;

    public ClipView(Context context) {
        super(context);
        a();
    }

    public ClipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ClipView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.c = new Paint();
        this.c.setColor(-1442840576);
        this.a = new Paint(1);
        this.a.setColor(-1);
        this.a.setStyle(Style.STROKE);
        this.b = new Paint(1);
        this.b.setColor(-16777216);
        this.b.setStrokeWidth(3.0f);
        this.b.setStyle(Style.STROKE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawRect(0.0f, 0.0f, (float) width, (float) this.f, this.c);
        canvas.drawRect(0.0f, (float) this.f, (float) this.d, (float) this.g, this.c);
        canvas.drawRect((float) this.e, (float) this.f, (float) width, (float) this.g, this.c);
        canvas.drawRect(0.0f, (float) this.g, (float) width, (float) height, this.c);
        canvas.drawRect((float) this.d, (float) this.f, (float) this.e, (float) this.g, this.b);
        canvas.drawRect((float) this.d, (float) this.f, (float) this.e, (float) this.g, this.a);
    }

    public Rect getClipRect() {
        return new Rect(this.d, this.f, this.e, this.g);
    }

    public void setClipRect(Rect rect) {
        this.d = rect.left;
        this.f = rect.top;
        this.e = rect.right;
        this.g = rect.bottom;
    }
}
