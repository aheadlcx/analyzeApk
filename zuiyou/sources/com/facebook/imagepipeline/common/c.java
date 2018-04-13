package com.facebook.imagepipeline.common;

import com.facebook.common.internal.g;
import com.facebook.common.util.a;
import java.util.Locale;

public class c {
    public final int a;
    public final int b;
    public final float c;
    public final float d;

    public c(int i, int i2) {
        this(i, i2, 2048.0f);
    }

    public c(int i, int i2, float f) {
        this(i, i2, f, 0.6666667f);
    }

    public c(int i, int i2, float f, float f2) {
        boolean z = true;
        g.a(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        g.a(z);
        this.a = i;
        this.b = i2;
        this.c = f;
        this.d = f2;
    }

    public int hashCode() {
        return a.a(this.a, this.b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a == cVar.a && this.b == cVar.b) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format((Locale) null, "%dx%d", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)});
    }
}
