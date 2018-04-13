package com.facebook.drawee.generic;

import android.support.annotation.ColorInt;
import com.facebook.common.internal.g;
import java.util.Arrays;

public class RoundingParams {
    private RoundingMethod a = RoundingMethod.BITMAP_ONLY;
    private boolean b = false;
    private float[] c = null;
    private int d = 0;
    private float e = 0.0f;
    private int f = 0;
    private float g = 0.0f;

    public enum RoundingMethod {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    public RoundingParams a(boolean z) {
        this.b = z;
        return this;
    }

    public boolean a() {
        return this.b;
    }

    public RoundingParams a(float f) {
        Arrays.fill(h(), f);
        return this;
    }

    public RoundingParams a(float f, float f2, float f3, float f4) {
        float[] h = h();
        h[1] = f;
        h[0] = f;
        h[3] = f2;
        h[2] = f2;
        h[5] = f3;
        h[4] = f3;
        h[7] = f4;
        h[6] = f4;
        return this;
    }

    public float[] b() {
        return this.c;
    }

    public RoundingMethod c() {
        return this.a;
    }

    public RoundingParams a(@ColorInt int i) {
        this.d = i;
        this.a = RoundingMethod.OVERLAY_COLOR;
        return this;
    }

    public int d() {
        return this.d;
    }

    private float[] h() {
        if (this.c == null) {
            this.c = new float[8];
        }
        return this.c;
    }

    public RoundingParams b(float f) {
        g.a(f >= 0.0f, (Object) "the border width cannot be < 0");
        this.e = f;
        return this;
    }

    public float e() {
        return this.e;
    }

    public RoundingParams b(@ColorInt int i) {
        this.f = i;
        return this;
    }

    public int f() {
        return this.f;
    }

    public RoundingParams c(float f) {
        g.a(f >= 0.0f, (Object) "the padding cannot be < 0");
        this.g = f;
        return this;
    }

    public float g() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RoundingParams roundingParams = (RoundingParams) obj;
        if (this.b == roundingParams.b && this.d == roundingParams.d && Float.compare(roundingParams.e, this.e) == 0 && this.f == roundingParams.f && Float.compare(roundingParams.g, this.g) == 0 && this.a == roundingParams.a) {
            return Arrays.equals(this.c, roundingParams.c);
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = (this.a != null ? this.a.hashCode() : 0) * 31;
        if (this.b) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.c != null) {
            i = Arrays.hashCode(this.c);
        } else {
            i = 0;
        }
        hashCode = (((i + hashCode) * 31) + this.d) * 31;
        if (this.e != 0.0f) {
            i = Float.floatToIntBits(this.e);
        } else {
            i = 0;
        }
        i = (((i + hashCode) * 31) + this.f) * 31;
        if (this.g != 0.0f) {
            i2 = Float.floatToIntBits(this.g);
        }
        return i + i2;
    }
}
