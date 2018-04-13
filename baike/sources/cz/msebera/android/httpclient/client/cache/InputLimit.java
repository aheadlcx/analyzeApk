package cz.msebera.android.httpclient.client.cache;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;

@NotThreadSafe
public class InputLimit {
    private final long a;
    private boolean b = false;

    public InputLimit(long j) {
        this.a = j;
    }

    public long getValue() {
        return this.a;
    }

    public void reached() {
        this.b = true;
    }

    public boolean isReached() {
        return this.b;
    }
}
