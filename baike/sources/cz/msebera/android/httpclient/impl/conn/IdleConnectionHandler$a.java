package cz.msebera.android.httpclient.impl.conn;

import java.util.concurrent.TimeUnit;

class IdleConnectionHandler$a {
    private final long a;
    private final long b;

    IdleConnectionHandler$a(long j, long j2, TimeUnit timeUnit) {
        this.a = j;
        if (j2 > 0) {
            this.b = timeUnit.toMillis(j2) + j;
        } else {
            this.b = Long.MAX_VALUE;
        }
    }
}
