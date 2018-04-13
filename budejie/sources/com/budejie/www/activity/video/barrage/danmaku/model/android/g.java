package com.budejie.www.activity.video.barrage.danmaku.model.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import com.budejie.www.activity.video.barrage.danmaku.model.c;
import java.util.HashMap;
import java.util.Map;

public class g extends b {
    private static final Map<Float, Float> a = new HashMap();

    protected Float a(c cVar, Paint paint) {
        Float valueOf = Float.valueOf(paint.getTextSize());
        Float f = (Float) a.get(valueOf);
        if (f != null) {
            return f;
        }
        FontMetrics fontMetrics = paint.getFontMetrics();
        f = Float.valueOf(fontMetrics.leading + (fontMetrics.descent - fontMetrics.ascent));
        a.put(valueOf, f);
        return f;
    }

    public void a(c cVar, TextPaint textPaint) {
        float f = 0.0f;
        Float valueOf = Float.valueOf(0.0f);
        if (cVar.d == null) {
            if (cVar.c != null) {
                f = textPaint.measureText(cVar.c.toString());
                valueOf = a(cVar, (Paint) textPaint);
            }
            cVar.n = f;
            cVar.o = valueOf.floatValue();
            return;
        }
        Float a = a(cVar, (Paint) textPaint);
        float f2 = 0.0f;
        for (String str : cVar.d) {
            if (str.length() > 0) {
                f2 = Math.max(textPaint.measureText(str), f2);
            }
        }
        cVar.n = f2;
        cVar.o = ((float) cVar.d.length) * a.floatValue();
    }

    public void a(c cVar, String str, Canvas canvas, float f, float f2, Paint paint) {
        if (str != null) {
            canvas.drawText(str, f, f2, paint);
        } else {
            canvas.drawText(cVar.c.toString(), f, f2, paint);
        }
    }

    public void b(c cVar, String str, Canvas canvas, float f, float f2, Paint paint) {
        if (str != null) {
            canvas.drawText(str, f, f2, paint);
        } else {
            canvas.drawText(cVar.c.toString(), f, f2, paint);
        }
    }

    public void a() {
        a.clear();
    }

    public void a(c cVar, Canvas canvas, float f, float f2) {
    }
}
