package com.budejie.www.activity.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.budejie.www.activity.video.a;

public class CameraCropBorderView extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private Paint f;
    private Paint g;
    private Rect h;
    private boolean i;
    private Context j;

    public CameraCropBorderView(Context context) {
        this(context, null);
    }

    public CameraCropBorderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CameraCropBorderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 640;
        this.b = 640;
        this.c = Color.parseColor("#000000");
        this.d = Color.parseColor("#000000");
        this.e = 1;
        this.h = new Rect();
        this.i = true;
        this.j = context;
        this.e = (int) TypedValue.applyDimension(1, (float) this.e, getResources().getDisplayMetrics());
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.f.setColor(this.c);
        this.f.setStrokeWidth((float) this.e);
        this.f.setStyle(Style.STROKE);
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.g.setColor(this.d);
        this.g.setStyle(Style.FILL);
    }

    public Rect getRect() {
        return this.h;
    }

    protected void onDraw(Canvas canvas) {
        if (this.i) {
            int width = getWidth();
            int height = getHeight();
            int i = width > height ? height : width;
            this.b = i;
            this.a = i;
            width = (width - this.a) / 2;
            i = (height - this.b) / 3;
            if (i < a.a(this.j, 52)) {
                i = a.a(this.j, 52);
            }
            this.h.set(width, i, this.a + width, this.b + i);
            this.i = false;
        }
        canvas.drawRect(this.h, this.f);
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) this.h.top, this.g);
        canvas.drawRect(0.0f, (float) this.h.bottom, (float) getWidth(), (float) getHeight(), this.g);
        canvas.drawRect(0.0f, (float) this.h.top, (float) this.h.left, (float) this.h.bottom, this.g);
        canvas.drawRect((float) this.h.right, (float) this.h.top, (float) getWidth(), (float) this.h.bottom, this.g);
    }
}
