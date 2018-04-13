package cn.xiaochuankeji.tieba.ui.danmaku;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class f extends ReplacementSpan {
    private final int a;
    private final int b;

    public f(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
        return (int) (((float) (fontMetricsInt2.descent * 4)) + paint.measureText(charSequence.subSequence(i, i2).toString()));
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        float size = (float) getSize(paint, charSequence, i, i2, paint.getFontMetricsInt());
        FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        RectF rectF = new RectF(((float) fontMetricsInt.descent) + f, (float) (fontMetricsInt.ascent + i4), size + f, (float) (fontMetricsInt.descent + i4));
        int color = paint.getColor();
        paint.setColor(this.b);
        canvas.drawRoundRect(rectF, rectF.height(), rectF.height(), paint);
        paint.setColor(color);
        size = ((rectF.width() - paint.measureText(charSequence.subSequence(i, i2).toString())) / 2.0f) + ((float) fontMetricsInt.descent);
        float f2 = (((rectF.top + rectF.bottom) - ((float) fontMetricsInt.bottom)) - ((float) fontMetricsInt.top)) / 2.0f;
        paint.setColor(this.a);
        canvas.drawText(charSequence, i, i2, f + size, f2, paint);
    }
}
