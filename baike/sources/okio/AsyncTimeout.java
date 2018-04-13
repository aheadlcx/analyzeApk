package okio;

import com.alipay.sdk.data.a;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class AsyncTimeout extends Timeout {
    private static final long a = TimeUnit.SECONDS.toMillis(60);
    @Nullable
    static AsyncTimeout b;
    private static final long c = TimeUnit.MILLISECONDS.toNanos(a);
    private boolean d;
    @Nullable
    private AsyncTimeout e;
    private long f;

    public final void enter() {
        if (this.d) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            this.d = true;
            a(this, timeoutNanos, hasDeadline);
        }
    }

    private static synchronized void a(AsyncTimeout asyncTimeout, long j, boolean z) {
        synchronized (AsyncTimeout.class) {
            if (b == null) {
                b = new AsyncTimeout();
                new AsyncTimeout$a().start();
            }
            long nanoTime = System.nanoTime();
            if (j != 0 && z) {
                asyncTimeout.f = Math.min(j, asyncTimeout.deadlineNanoTime() - nanoTime) + nanoTime;
            } else if (j != 0) {
                asyncTimeout.f = nanoTime + j;
            } else if (z) {
                asyncTimeout.f = asyncTimeout.deadlineNanoTime();
            } else {
                throw new AssertionError();
            }
            long a = asyncTimeout.a(nanoTime);
            AsyncTimeout asyncTimeout2 = b;
            while (asyncTimeout2.e != null && a >= asyncTimeout2.e.a(nanoTime)) {
                asyncTimeout2 = asyncTimeout2.e;
            }
            asyncTimeout.e = asyncTimeout2.e;
            asyncTimeout2.e = asyncTimeout;
            if (asyncTimeout2 == b) {
                AsyncTimeout.class.notify();
            }
        }
    }

    public final boolean exit() {
        if (!this.d) {
            return false;
        }
        this.d = false;
        return a(this);
    }

    private static synchronized boolean a(AsyncTimeout asyncTimeout) {
        boolean z;
        synchronized (AsyncTimeout.class) {
            for (AsyncTimeout asyncTimeout2 = b; asyncTimeout2 != null; asyncTimeout2 = asyncTimeout2.e) {
                if (asyncTimeout2.e == asyncTimeout) {
                    asyncTimeout2.e = asyncTimeout.e;
                    asyncTimeout.e = null;
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    private long a(long j) {
        return this.f - j;
    }

    protected void a() {
    }

    public final Sink sink(Sink sink) {
        return new a(this, sink);
    }

    public final Source source(Source source) {
        return new b(this, source);
    }

    final void a(boolean z) throws IOException {
        if (exit() && z) {
            throw a(null);
        }
    }

    final IOException b(IOException iOException) throws IOException {
        return !exit() ? iOException : a(iOException);
    }

    protected IOException a(@Nullable IOException iOException) {
        IOException interruptedIOException = new InterruptedIOException(a.f);
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    @Nullable
    static AsyncTimeout b() throws InterruptedException {
        AsyncTimeout asyncTimeout = b.e;
        if (asyncTimeout == null) {
            long nanoTime = System.nanoTime();
            AsyncTimeout.class.wait(a);
            if (b.e != null || System.nanoTime() - nanoTime < c) {
                return null;
            }
            return b;
        }
        nanoTime = asyncTimeout.a(System.nanoTime());
        if (nanoTime > 0) {
            long j = nanoTime / 1000000;
            AsyncTimeout.class.wait(j, (int) (nanoTime - (1000000 * j)));
            return null;
        }
        b.e = asyncTimeout.e;
        asyncTimeout.e = null;
        return asyncTimeout;
    }
}
