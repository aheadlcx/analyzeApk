package master.flame.danmaku.danmaku.model.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.model.android.a.a;
import master.flame.danmaku.danmaku.model.d;

public class i extends b {
    private static final Map<Float, Float> b = new HashMap();

    protected Float a(d dVar, Paint paint) {
        Float valueOf = Float.valueOf(paint.getTextSize());
        Float f = (Float) b.get(valueOf);
        if (f != null) {
            return f;
        }
        FontMetrics fontMetrics = paint.getFontMetrics();
        f = Float.valueOf(fontMetrics.leading + (fontMetrics.descent - fontMetrics.ascent));
        b.put(valueOf, f);
        return f;
    }

    public void a(d dVar, TextPaint textPaint, boolean z) {
        float f = 0.0f;
        Float valueOf = Float.valueOf(0.0f);
        if (dVar.d == null) {
            if (dVar.c != null) {
                f = textPaint.measureText(dVar.c.toString());
                valueOf = a(dVar, textPaint);
            }
            dVar.p = f;
            dVar.q = valueOf.floatValue();
            return;
        }
        Float a = a(dVar, textPaint);
        float f2 = 0.0f;
        for (String str : dVar.d) {
            if (str.length() > 0) {
                f2 = Math.max(textPaint.measureText(str), f2);
            }
        }
        dVar.p = f2;
        dVar.q = ((float) dVar.d.length) * a.floatValue();
    }

    protected void a(d dVar, String str, Canvas canvas, float f, float f2, Paint paint) {
        if (str != null) {
            canvas.drawText(str, f, f2, paint);
        } else {
            canvas.drawText(dVar.c.toString(), f, f2, paint);
        }
    }

    protected void a(d dVar, String str, Canvas canvas, float f, float f2, TextPaint textPaint, boolean z) {
        if (str != null) {
            canvas.drawText(str, f, f2, textPaint);
        } else {
            canvas.drawText(dVar.c.toString(), f, f2, textPaint);
        }
    }

    public void a() {
        b.clear();
    }

    protected void a(d dVar, Canvas canvas, float f, float f2) {
    }

    public void a(d dVar, Canvas canvas, float f, float f2, boolean z, a aVar) {
        float f3;
        float f4;
        float f5;
        float f6 = f + ((float) dVar.n);
        float f7 = ((float) dVar.n) + f2;
        if (dVar.m != 0) {
            f3 = f7 + 4.0f;
            f4 = f6 + 4.0f;
        } else {
            f3 = f7;
            f4 = f6;
        }
        aVar.a(z);
        Paint a = aVar.a(dVar, z);
        a(dVar, canvas, f, f2);
        float ascent;
        if (dVar.d != null) {
            String[] strArr = dVar.d;
            if (strArr.length == 1) {
                if (aVar.a(dVar)) {
                    aVar.a(dVar, a, true);
                    ascent = f3 - a.ascent();
                    if (aVar.i) {
                        f5 = f4 + aVar.d;
                        ascent += aVar.e;
                    } else {
                        f5 = f4;
                    }
                    a(dVar, strArr[0], canvas, f5, ascent, a);
                }
                aVar.a(dVar, a, false);
                a(dVar, strArr[0], canvas, f4, f3 - a.ascent(), a, z);
            } else {
                float length = (dVar.q - ((float) (dVar.n * 2))) / ((float) strArr.length);
                int i = 0;
                while (i < strArr.length) {
                    if (!(strArr[i] == null || strArr[i].length() == 0)) {
                        if (aVar.a(dVar)) {
                            aVar.a(dVar, a, true);
                            ascent = ((((float) i) * length) + f3) - a.ascent();
                            if (aVar.i) {
                                f5 = f4 + aVar.d;
                                ascent += aVar.e;
                            } else {
                                f5 = f4;
                            }
                            a(dVar, strArr[i], canvas, f5, ascent, a);
                        }
                        aVar.a(dVar, a, false);
                        a(dVar, strArr[i], canvas, f4, ((((float) i) * length) + f3) - a.ascent(), a, z);
                    }
                    i++;
                }
            }
        } else {
            if (aVar.a(dVar)) {
                aVar.a(dVar, a, true);
                ascent = f3 - a.ascent();
                if (aVar.i) {
                    f5 = f4 + aVar.d;
                    ascent += aVar.e;
                } else {
                    f5 = f4;
                }
                a(dVar, null, canvas, f5, ascent, a);
            }
            aVar.a(dVar, a, false);
            a(dVar, null, canvas, f4, f3 - a.ascent(), a, z);
        }
        if (dVar.k != 0) {
            float f8 = (dVar.q + f2) - ((float) aVar.c);
            float f9 = f + dVar.p;
            canvas.drawLine(f, f8, f9, f8, aVar.c(dVar));
        }
        if (dVar.m != 0) {
            f9 = f + dVar.p;
            f5 = f2 + dVar.q;
            canvas.drawRect(f, f2, f9, f5, aVar.b(dVar));
        }
    }
}
