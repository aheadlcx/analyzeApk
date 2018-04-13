package okhttp3.internal.http2;

import android.support.v4.internal.view.SupportMenu;
import java.util.Arrays;

public final class Settings {
    private int a;
    private final int[] b = new int[10];

    void a() {
        this.a = 0;
        Arrays.fill(this.b, 0);
    }

    Settings a(int i, int i2) {
        if (i >= 0 && i < this.b.length) {
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
        return (128 & this.a) != 0 ? this.b[7] : SupportMenu.USER_MASK;
    }

    void a(Settings settings) {
        for (int i = 0; i < 10; i++) {
            if (settings.a(i)) {
                a(i, settings.b(i));
            }
        }
    }
}
