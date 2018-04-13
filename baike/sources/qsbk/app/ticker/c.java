package qsbk.app.ticker;

import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import java.util.HashMap;
import java.util.Map;

class c {
    private final Paint a;
    private final Map<Character, Float> b = new HashMap(256);
    private float c;
    private float d;

    c(Paint paint) {
        this.a = paint;
        a();
    }

    void a() {
        this.b.clear();
        FontMetrics fontMetrics = this.a.getFontMetrics();
        this.c = fontMetrics.bottom - fontMetrics.top;
        this.d = -fontMetrics.top;
    }

    float a(char c) {
        if (c == '\u0000') {
            return 0.0f;
        }
        Float f = (Float) this.b.get(Character.valueOf(c));
        if (f != null) {
            return f.floatValue();
        }
        float measureText = this.a.measureText(Character.toString(c));
        this.b.put(Character.valueOf(c), Float.valueOf(measureText));
        return measureText;
    }

    float b() {
        return this.c;
    }

    float c() {
        return this.d;
    }
}
