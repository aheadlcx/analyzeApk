package qsbk.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import java.lang.ref.WeakReference;

class dm extends DynamicDrawableSpan {
    private final Context a;
    private final int b;
    private final int c;
    private final int d;
    private int e;
    private int f;
    private int g;
    private Drawable h;
    private WeakReference<Drawable> i;
    private int j = 0;
    private int k = 0;

    public dm(Context context, int i, int i2, int i3, int i4) {
        super(i3);
        this.a = context;
        this.b = i;
        this.c = i2;
        this.e = i2;
        this.f = i2;
        this.d = i4;
    }

    public dm(Context context, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i3);
        this.a = context;
        this.b = i;
        this.c = i2;
        this.e = i2;
        this.f = i2;
        this.d = i4;
        this.j = i5;
        this.k = i6;
    }

    public void setPadding(int i, int i2) {
        this.j = i;
        this.k = i2;
    }

    public Drawable getDrawable() {
        if (this.h == null) {
            try {
                this.h = this.a.getResources().getDrawable(this.b);
                this.e = this.c;
                this.f = (this.e * this.h.getIntrinsicWidth()) / this.h.getIntrinsicHeight();
                this.g = (this.d - this.e) / 2;
                this.h.setBounds(0, this.g, this.f, this.g + this.e);
            } catch (Exception e) {
            }
        }
        return this.h;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        Rect bounds = a().getBounds();
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = -bounds.bottom;
            fontMetricsInt.descent = 0;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = 0;
        }
        return (bounds.right + this.j) + this.k;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable a = a();
        canvas.save();
        int i6 = i5 - a.getBounds().bottom;
        if (this.mVerticalAlignment == 1) {
            i6 = ((((i5 - i3) / 2) + i3) - ((a.getBounds().bottom - a.getBounds().top) / 2)) - this.g;
        }
        canvas.translate(((float) this.j) + f, (float) i6);
        a.draw(canvas);
        canvas.restore();
    }

    private Drawable a() {
        if (this.i == null || this.i.get() == null) {
            this.i = new WeakReference(getDrawable());
        }
        return (Drawable) this.i.get();
    }
}
