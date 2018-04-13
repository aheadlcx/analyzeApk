package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class s {
    public static final s b = new s() {
        public s a(long j, TimeUnit timeUnit) {
            return this;
        }

        public s a(long j) {
            return this;
        }

        public void g() throws IOException {
        }
    };
    private boolean a;
    private long c;
    private long d;

    public s a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            this.d = timeUnit.toNanos(j);
            return this;
        }
    }

    public long e_() {
        return this.d;
    }

    public boolean f_() {
        return this.a;
    }

    public long d() {
        if (this.a) {
            return this.c;
        }
        throw new IllegalStateException("No deadline");
    }

    public s a(long j) {
        this.a = true;
        this.c = j;
        return this;
    }

    public s g_() {
        this.d = 0;
        return this;
    }

    public s h_() {
        this.a = false;
        return this;
    }

    public void g() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.a && this.c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
