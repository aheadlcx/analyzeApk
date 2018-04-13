package cn.v6.sixrooms.surfaceanim.util;

public class AnimEvaluatorUtils {
    public static float getEvaluator(float f, float f2, int i, int i2, int i3) {
        if (i3 < i) {
            return f;
        }
        if (i3 > i + i2) {
            return f2;
        }
        return f + (((f2 - f) / ((float) i2)) * ((float) (i3 - i)));
    }

    public static float getEvaluatorForAlpha(float f, float f2, int i, int i2, int i3) {
        if (i3 < i) {
            return f;
        }
        if (i3 > i + i2) {
            return f2;
        }
        return (((((f2 - f) / ((float) i2)) * ((float) (i3 - i))) + f) / 100.0f) * 255.0f;
    }

    public static float getOval(float f, int i, float f2) {
        return ((float) (((double) i) * Math.cos(0.017453292519943295d * ((double) f2)))) + f;
    }
}
