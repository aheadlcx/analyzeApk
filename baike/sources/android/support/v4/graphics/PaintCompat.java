package android.support.v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

public final class PaintCompat {
    private static final ThreadLocal<Pair<Rect, Rect>> a = new ThreadLocal();

    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        if (VERSION.SDK_INT >= 23) {
            return paint.hasGlyph(str);
        }
        int length = str.length();
        if (length == 1 && Character.isWhitespace(str.charAt(0))) {
            return true;
        }
        float measureText = paint.measureText("󟿽");
        float measureText2 = paint.measureText("m");
        float measureText3 = paint.measureText(str);
        if (measureText3 == 0.0f) {
            return false;
        }
        if (str.codePointCount(0, str.length()) > 1) {
            if (measureText3 > measureText2 * 2.0f) {
                return false;
            }
            measureText2 = 0.0f;
            int i = 0;
            while (i < length) {
                int charCount = Character.charCount(str.codePointAt(i));
                measureText2 += paint.measureText(str, i, i + charCount);
                i += charCount;
            }
            if (measureText3 >= measureText2) {
                return false;
            }
        }
        if (measureText3 != measureText) {
            return true;
        }
        Pair a = a();
        paint.getTextBounds("󟿽", 0, "󟿽".length(), (Rect) a.first);
        paint.getTextBounds(str, 0, length, (Rect) a.second);
        return !((Rect) a.first).equals(a.second);
    }

    private static Pair<Rect, Rect> a() {
        Pair<Rect, Rect> pair = (Pair) a.get();
        if (pair == null) {
            pair = new Pair(new Rect(), new Rect());
            a.set(pair);
            return pair;
        }
        ((Rect) pair.first).setEmpty();
        ((Rect) pair.second).setEmpty();
        return pair;
    }

    private PaintCompat() {
    }
}
