package cz.msebera.android.httpclient.impl.client;

import java.util.concurrent.atomic.AtomicLong;

public final class FutureRequestExecutionMetrics {
    private final AtomicLong a = new AtomicLong();
    private final AtomicLong b = new AtomicLong();
    private final a c = new a();
    private final a d = new a();
    private final a e = new a();
    private final a f = new a();

    static class a {
        private final AtomicLong a = new AtomicLong(0);
        private final AtomicLong b = new AtomicLong(0);

        a() {
        }

        public void increment(long j) {
            this.a.incrementAndGet();
            this.b.addAndGet(System.currentTimeMillis() - j);
        }

        public long count() {
            return this.a.get();
        }

        public long averageDuration() {
            long j = this.a.get();
            if (j > 0) {
                return this.b.get() / j;
            }
            return 0;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[count=").append(count()).append(", averageDuration=").append(averageDuration()).append("]");
            return stringBuilder.toString();
        }
    }

    FutureRequestExecutionMetrics() {
    }

    AtomicLong a() {
        return this.a;
    }

    AtomicLong b() {
        return this.b;
    }

    a c() {
        return this.c;
    }

    a d() {
        return this.d;
    }

    a e() {
        return this.e;
    }

    a f() {
        return this.f;
    }

    public long getActiveConnectionCount() {
        return this.a.get();
    }

    public long getScheduledConnectionCount() {
        return this.b.get();
    }

    public long getSuccessfulConnectionCount() {
        return this.c.count();
    }

    public long getSuccessfulConnectionAverageDuration() {
        return this.c.averageDuration();
    }

    public long getFailedConnectionCount() {
        return this.d.count();
    }

    public long getFailedConnectionAverageDuration() {
        return this.d.averageDuration();
    }

    public long getRequestCount() {
        return this.e.count();
    }

    public long getRequestAverageDuration() {
        return this.e.averageDuration();
    }

    public long getTaskCount() {
        return this.f.count();
    }

    public long getTaskAverageDuration() {
        return this.f.averageDuration();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[activeConnections=").append(this.a).append(", scheduledConnections=").append(this.b).append(", successfulConnections=").append(this.c).append(", failedConnections=").append(this.d).append(", requests=").append(this.e).append(", tasks=").append(this.f).append("]");
        return stringBuilder.toString();
    }
}
