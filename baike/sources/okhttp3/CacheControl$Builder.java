package okhttp3;

import java.util.concurrent.TimeUnit;

public final class CacheControl$Builder {
    boolean a;
    boolean b;
    int c = -1;
    int d = -1;
    int e = -1;
    boolean f;
    boolean g;
    boolean h;

    public CacheControl$Builder noCache() {
        this.a = true;
        return this;
    }

    public CacheControl$Builder noStore() {
        this.b = true;
        return this;
    }

    public CacheControl$Builder maxAge(int i, TimeUnit timeUnit) {
        if (i < 0) {
            throw new IllegalArgumentException("maxAge < 0: " + i);
        }
        int i2;
        long toSeconds = timeUnit.toSeconds((long) i);
        if (toSeconds > 2147483647L) {
            i2 = Integer.MAX_VALUE;
        } else {
            i2 = (int) toSeconds;
        }
        this.c = i2;
        return this;
    }

    public CacheControl$Builder maxStale(int i, TimeUnit timeUnit) {
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

    public CacheControl$Builder minFresh(int i, TimeUnit timeUnit) {
        if (i < 0) {
            throw new IllegalArgumentException("minFresh < 0: " + i);
        }
        int i2;
        long toSeconds = timeUnit.toSeconds((long) i);
        if (toSeconds > 2147483647L) {
            i2 = Integer.MAX_VALUE;
        } else {
            i2 = (int) toSeconds;
        }
        this.e = i2;
        return this;
    }

    public CacheControl$Builder onlyIfCached() {
        this.f = true;
        return this;
    }

    public CacheControl$Builder noTransform() {
        this.g = true;
        return this;
    }

    public CacheControl$Builder immutable() {
        this.h = true;
        return this;
    }

    public CacheControl build() {
        return new CacheControl(this);
    }
}
