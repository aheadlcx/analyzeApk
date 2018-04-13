package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;

public class cw extends View {
    Path a;
    private Drawable b;
    private Drawable c;
    private PaintFlagsDrawFilter d = new PaintFlagsDrawFilter(1, 2);

    public cw(Context context) {
        super(context);
        try {
            this.b = cs.a(getContext(), "voice_empty");
            this.c = cs.a(getContext(), "voice_full");
            this.b.setBounds(new Rect((-this.b.getIntrinsicWidth()) / 2, (-this.b.getIntrinsicHeight()) / 2, this.b.getIntrinsicWidth() / 2, this.b.getIntrinsicHeight() / 2));
            this.c.setBounds(new Rect((-this.c.getIntrinsicWidth()) / 2, (-this.c.getIntrinsicHeight()) / 2, this.c.getIntrinsicWidth() / 2, this.c.getIntrinsicHeight() / 2));
            this.a = new Path();
            setVolume(0);
        } catch (Throwable e) {
            cb.a(e);
        }
    }

    public void finalize() throws Throwable {
        this.b = null;
        this.c = null;
        super.finalize();
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        canvas.setDrawFilter(this.d);
        canvas.translate((float) (getWidth() / 2), (float) (getHeight() / 2));
        this.b.draw(canvas);
        canvas.clipPath(this.a);
        this.c.draw(canvas);
        canvas.restore();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        Drawable background = getBackground();
        if (background != null) {
            size = background.getMinimumWidth();
            size2 = background.getMinimumHeight();
        }
        setMeasuredDimension(resolveSize(size, i), resolveSize(size2, i2));
    }

    public void setVolume(int i) {
        this.a.reset();
        this.a.addCircle(0.0f, 0.0f, (float) ((this.b.getIntrinsicWidth() * i) / 12), Direction.CCW);
    }
}
