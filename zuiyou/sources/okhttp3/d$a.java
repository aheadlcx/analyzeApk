package okhttp3;

import java.util.concurrent.TimeUnit;

public final class d$a {
    boolean a;
    boolean b;
    int c = -1;
    int d = -1;
    int e = -1;
    boolean f;
    boolean g;
    boolean h;

    public d$a a() {
        this.a = true;
        return this;
    }

    public d$a b() {
        this.b = true;
        return this;
    }

    public d$a a(int i, TimeUnit timeUnit) {
        if (i < 0) {
            throw new IllegalArgumentException("maxStale < 0: " + i);
        }
        int i2;
        long toSeconds = timeUnit.toSeconds((long) i);
        if (toSeconds > 2147483647L) {
            i2 = Integer.MAX_VALUE;
        } else {
            i2 = (int) toSeconds;
        }
        this.d = i2;
        return this;
    }

    public d$a c() {
        this.f = true;
        return this;
    }

    public d d() {
        return new d(this);
    }
}
