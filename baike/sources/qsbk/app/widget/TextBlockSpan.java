package qsbk.app.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.widget.TextView;

public class TextBlockSpan extends ReplacementSpan {
    private StaticLayout a;

    public TextBlockSpan(String str, TextView textView) {
        this.a = new StaticLayout(str, textView.getPaint(), 1048576, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    }

    public TextBlockSpan(String str, TextView textView, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.set(textView.getPaint());
        textPaint.setColor(i);
        this.a = new StaticLayout(str, textPaint, 1048576, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = (-this.a.getLineTop(this.a.getLineCount() - 1)) + this.a.getLineTop(this.a.getLineCount());
            fontMetricsInt.descent = 0;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = 0;
        }
        return Math.round(this.a.getLineRight(this.a.getLineCount() - 1));
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        canvas.save();
        canvas.translate(f, (float) (i5 - this.a.getHeight()));
        this.a.draw(canvas);
        canvas.restore();
    }
}
