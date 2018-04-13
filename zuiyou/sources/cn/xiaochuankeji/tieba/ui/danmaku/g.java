package cn.xiaochuankeji.tieba.ui.danmaku;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.text.style.ReplacementSpan;

public class g extends ReplacementSpan {
    private final int a;
    private final int b;

    public g(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText(charSequence.subSequence(i, i2).toString());
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        paint.setStyle(Style.STROKE);
        paint.setColor(this.b);
        canvas.drawText(charSequence, i, i2, f, (float) i4, paint);
        paint.setStyle(Style.FILL);
        paint.setColor(this.a);
        canvas.drawText(charSequence, i, i2, f, (float) i4, paint);
    }
}
