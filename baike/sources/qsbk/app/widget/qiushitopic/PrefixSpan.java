package qsbk.app.widget.qiushitopic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.style.ImageSpan;

public class PrefixSpan extends ImageSpan {
    Rect a = new Rect();
    TextPaint b;
    private Drawable c;

    public PrefixSpan(Drawable drawable, TextPaint textPaint) {
        super(drawable);
        this.b = textPaint;
        drawable.setBounds(0, 0, (int) textPaint.getTextSize(), (int) textPaint.getTextSize());
        this.c = drawable;
    }

    public void setMargin(int i, int i2, int i3, int i4) {
        this.a.set(i, i2, i3, i4);
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        Rect bounds = getDrawable().getBounds();
        if (bounds.isEmpty() && paint != null) {
            bounds.set(0, 0, (int) paint.getTextSize(), ((int) paint.getTextSize()) + this.a.height());
        }
        if (fontMetricsInt != null) {
            int i3 = fontMetricsInt.bottom - fontMetricsInt.top;
            int i4 = bounds.bottom - bounds.top;
            int i5 = (i4 / 2) - (i3 / 4);
            i3 = (i3 / 4) + (i4 / 2);
            fontMetricsInt.ascent = -i3;
            fontMetricsInt.top = -i3;
            fontMetricsInt.bottom = i5;
            fontMetricsInt.descent = i5;
        }
        return (bounds.right + bounds.left) + this.a.width();
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(((float) this.a.left) + f, (float) (((((i5 - i3) - drawable.getBounds().bottom) / 2) + i3) + this.a.top));
        drawable.draw(canvas);
        canvas.restore();
    }
}
