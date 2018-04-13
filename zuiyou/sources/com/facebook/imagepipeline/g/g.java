package com.facebook.imagepipeline.g;

public class g implements h {
    public static final h a = a(Integer.MAX_VALUE, true, true);
    int b;
    boolean c;
    boolean d;

    private g(int i, boolean z, boolean z2) {
        this.b = i;
        this.c = z;
        this.d = z2;
    }

    public int a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public int hashCode() {
        int i = 0;
        int i2 = (this.c ? 4194304 : 0) ^ this.b;
        if (this.d) {
            i = 8388608;
        }
        return i2 ^ i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.b == gVar.b && this.c == gVar.c && this.d == gVar.d) {
            return true;
        }
        return false;
    }

    public static h a(int i, boolean z, boolean z2) {
        return new g(i, z, z2);
    }
}
