package okhttp3.internal.http2;

import java.util.Arrays;

public final class l {
    private int a;
    private final int[] b = new int[10];

    void a() {
        this.a = 0;
        Arrays.fill(this.b, 0);
    }

    l a(int i, int i2) {
        if (i < this.b.length) {
            this.a = (1 << i) | this.a;
            this.b[i] = i2;
        }
        return this;
    }

    boolean a(int i) {
        if (((1 << i) & this.a) != 0) {
            return true;
        }
        return false;
    }

    int b(int i) {
        return this.b[i];
    }

    int b() {
        return Integer.bitCount(this.a);
    }

    int c() {
        return (2 & this.a) != 0 ? this.b[1] : -1;
    }

    int c(int i) {
        return (16 & this.a) != 0 ? this.b[4] : i;
    }

    int d(int i) {
        return (32 & this.a) != 0 ? this.b[5] : i;
    }

    int d() {
        return (128 & this.a) != 0 ? this.b[7] : 65535;
    }

    void a(l lVar) {
        for (int i = 0; i < 10; i++) {
            if (lVar.a(i)) {
                a(i, lVar.b(i));
            }
        }
    }
}
