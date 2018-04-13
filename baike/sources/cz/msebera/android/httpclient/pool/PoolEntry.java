package cz.msebera.android.httpclient.pool;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.TimeUnit;

@ThreadSafe
public abstract class PoolEntry<T, C> {
    private final String a;
    private final T b;
    private final C c;
    private final long d;
    private final long e;
    @GuardedBy("this")
    private long f;
    @GuardedBy("this")
    private long g;
    private volatile Object h;

    public abstract void close();

    public abstract boolean isClosed();

    public PoolEntry(String str, T t, C c, long j, TimeUnit timeUnit) {
        Args.notNull(t, "Route");
        Args.notNull(c, "Connection");
        Args.notNull(timeUnit, "Time unit");
        this.a = str;
        this.b = t;
        this.c = c;
        this.d = System.currentTimeMillis();
        if (j > 0) {
            this.e = this.d + timeUnit.toMillis(j);
        } else {
            this.e = Long.MAX_VALUE;
        }
        this.g = this.e;
    }

    public PoolEntry(String str, T t, C c) {
        this(str, t, c, 0, TimeUnit.MILLISECONDS);
    }

    public String getId() {
        return this.a;
    }

    public T getRoute() {
        return this.b;
    }

    public C getConnection() {
        return this.c;
    }

    public long getCreated() {
        return this.d;
    }

    public long getValidityDeadline() {
        return this.e;
    }

    @Deprecated
    public long getValidUnit() {
        return this.e;
    }

    public Object getState() {
        return this.h;
    }

    public void setState(Object obj) {
        this.h = obj;
    }

    public synchronized long getUpdated() {
        return this.f;
    }

    public synchronized long getExpiry() {
        return this.g;
    }

    public synchronized void updateExpiry(long j, TimeUnit timeUnit) {
        long toMillis;
        Args.notNull(timeUnit, "Time unit");
        this.f = System.currentTimeMillis();
        if (j > 0) {
            toMillis = this.f + timeUnit.toMillis(j);
        } else {
            toMillis = Long.MAX_VALUE;
        }
        this.g = Math.min(toMillis, this.e);
    }

    public synchronized boolean isExpired(long j) {
        return j >= this.g;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[id:");
        stringBuilder.append(this.a);
        stringBuilder.append("][route:");
        stringBuilder.append(this.b);
        stringBuilder.append("][state:");
        stringBuilder.append(this.h);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
