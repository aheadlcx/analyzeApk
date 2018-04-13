package qsbk.app.widget.qiushitopic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.text.style.ReplacementSpan;

public class PlaceHolderSpan extends ReplacementSpan {
    int a;
    boolean b;
    Paint c;
    int d;
    int e;
    int f;
    int g;
    float h;
    int i;

    public PlaceHolderSpan(int i) {
        this.a = 6;
        this.h = 1.0f;
        this.i = Math.round(((float) this.a) * this.h);
        this.e = i;
    }

    public PlaceHolderSpan(int i, int i2, int i3) {
        this.a = 6;
        this.h = 1.0f;
        this.i = Math.round(((float) this.a) * this.h);
        this.f = i;
        this.g = i2;
        this.e = i3;
        this.d = 0;
    }

    public PlaceHolderSpan(int i, int i2, int i3, Paint paint) {
        this(i, i2, i3, paint, 1.0f);
    }

    public PlaceHolderSpan(int i, int i2, int i3, Paint paint, float f) {
        this.a = 6;
        this.h = 1.0f;
        this.i = Math.round(((float) this.a) * this.h);
        this.f = i;
        this.g = i2;
        this.e = i3;
        this.c = paint;
        this.b = true;
        this.h = f;
        this.i = Math.round(((float) this.a) * f);
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null && ((fontMetricsInt.top == 0 || fontMetricsInt.bottom == 0) && this.c != null)) {
            FontMetricsInt fontMetricsInt2 = this.c.getFontMetricsInt();
            fontMetricsInt.top = this.b ? fontMetricsInt2.top - this.i : fontMetricsInt2.top;
            fontMetricsInt.ascent = fontMetricsInt2.ascent - this.i;
            fontMetricsInt.bottom = fontMetricsInt2.bottom + this.i;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.leading = fontMetricsInt2.leading;
        }
        return this.e;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
    }
}
