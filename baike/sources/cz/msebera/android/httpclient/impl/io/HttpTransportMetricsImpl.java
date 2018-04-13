package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;

@NotThreadSafe
public class HttpTransportMetricsImpl implements HttpTransportMetrics {
    private long a = 0;

    public long getBytesTransferred() {
        return this.a;
    }

    public void setBytesTransferred(long j) {
        this.a = j;
    }

    public void incrementBytesTransferred(long j) {
        this.a += j;
    }

    public void reset() {
        this.a = 0;
    }
}
