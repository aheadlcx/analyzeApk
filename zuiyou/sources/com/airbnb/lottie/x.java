package com.airbnb.lottie;

class x {
    private static float a(float f) {
        if (f <= 0.0031308f) {
            return 12.92f * f;
        }
        return (float) ((Math.pow((double) f, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    private static float b(float f) {
        return f <= 0.04045f ? f / 12.92f : (float) Math.pow((double) ((0.055f + f) / 1.055f), 2.4000000953674316d);
    }

    static int a(float f, int i, int i2) {
        float f2 = ((float) ((i >> 24) & 255)) / 255.0f;
        float f3 = ((float) ((i >> 8) & 255)) / 255.0f;
        float f4 = ((float) (i & 255)) / 255.0f;
        float f5 = ((float) ((i2 >> 24) & 255)) / 255.0f;
        float f6 = ((float) ((i2 >> 16) & 255)) / 255.0f;
        float f7 = ((float) ((i2 >> 8) & 255)) / 255.0f;
        float f8 = ((float) (i2 & 255)) / 255.0f;
        float b = b(((float) ((i >> 16) & 255)) / 255.0f);
        f3 = b(f3);
        f4 = b(f4);
        f6 = b(f6);
        return (((Math.round((f2 + ((f5 - f2) * f)) * 255.0f) << 24) | (Math.round(a(b + ((f6 - b) * f)) * 255.0f) << 16)) | (Math.round(a(f3 + ((b(f7) - f3) * f)) * 255.0f) << 8)) | Math.round(a(f4 + ((b(f8) - f4) * f)) * 255.0f);
    }
}
