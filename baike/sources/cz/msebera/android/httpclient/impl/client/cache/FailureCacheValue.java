package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class FailureCacheValue {
    private final long a = System.nanoTime();
    private final String b;
    private final int c;

    public FailureCacheValue(String str, int i) {
        this.b = str;
        this.c = i;
    }

    public long getCreationTimeInNanos() {
        return this.a;
    }

    public String getKey() {
        return this.b;
    }

    public int getErrorCount() {
        return this.c;
    }

    public String toString() {
        return "[entry creationTimeInNanos=" + this.a + "; " + "key=" + this.b + "; errorCount=" + this.c + ']';
    }
}
