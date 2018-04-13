package qsbk.app.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

public class VerticalImageSpan extends ImageSpan {
    private int a;
    private int b;

    public VerticalImageSpan(Drawable drawable) {
        super(drawable);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 0;
        }
        if (intrinsicHeight <= 0) {
            intrinsicHeight = 0;
        }
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
    }

    public VerticalImageSpan(Drawable drawable, int i, int i2) {
        super(drawable);
        drawable.setBounds(0, 0, i, i2);
    }

    public void setMargin(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        Drawable drawable = getDrawable();
        Rect bounds = drawable.getBounds();
        if (bounds.isEmpty()) {
            bounds.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        if (fontMetricsInt != null) {
            FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            int i3 = fontMetricsInt2.bottom - fontMetricsInt2.top;
            int i4 = bounds.bottom - bounds.top;
            int i5 = (i4 / 2) - (i3 / 4);
            i3 = (i3 / 4) + (i4 / 2);
            fontMetricsInt.ascent = -i3;
            fontMetricsInt.top = -i3;
            fontMetricsInt.bottom = i5;
            fontMetricsInt.descent = i5;
        }
        return (bounds.right + this.a) + this.b;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(((float) this.a) + f, (float) ((((i5 - i3) - drawable.getBounds().bottom) / 2) + i3));
        drawable.draw(canvas);
        canvas.restore();
    }
}
