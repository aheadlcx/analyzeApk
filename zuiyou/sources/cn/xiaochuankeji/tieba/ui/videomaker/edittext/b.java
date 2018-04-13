package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.text.style.ReplacementSpan;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.a;

public class b extends ReplacementSpan {
    private static float a = 0.18518518f;
    private final int b;
    private final int c;
    private final boolean d;
    private a e;

    public b(int i, int i2, boolean z, a aVar) {
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = aVar;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText(charSequence.subSequence(i, i2).toString());
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        if (this.e != null) {
            Canvas canvas2;
            CharSequence charSequence2;
            int i6;
            int i7;
            float textSize = paint.getTextSize() * this.e.a;
            float textSize2 = paint.getTextSize() * this.e.b;
            if (this.d) {
                paint.setStyle(Style.STROKE);
                paint.setColor(this.e.c);
                paint.setStrokeWidth(paint.getTextSize() * a);
                paint.setStrokeJoin(Join.ROUND);
                canvas2 = canvas;
                charSequence2 = charSequence;
                i6 = i;
                i7 = i2;
                canvas2.drawText(charSequence2, i6, i7, f + textSize, ((float) i4) + textSize2, paint);
            }
            paint.setStyle(Style.FILL);
            paint.setColor(this.e.c);
            canvas2 = canvas;
            charSequence2 = charSequence;
            i6 = i;
            i7 = i2;
            canvas2.drawText(charSequence2, i6, i7, f + textSize, ((float) i4) + textSize2, paint);
        }
        if (this.d) {
            paint.setStyle(Style.STROKE);
            paint.setColor(this.c);
            paint.setStrokeWidth(paint.getTextSize() * a);
            paint.setStrokeJoin(Join.ROUND);
            canvas.drawText(charSequence, i, i2, f, (float) i4, paint);
        }
        paint.setStyle(Style.FILL);
        paint.setColor(this.b);
        canvas.drawText(charSequence, i, i2, f, (float) i4, paint);
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public a d() {
        return this.e;
    }
}
