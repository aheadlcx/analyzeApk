package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ForwardingTimeout extends Timeout {
    private Timeout a;

    public ForwardingTimeout(Timeout timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = timeout;
    }

    public final Timeout delegate() {
        return this.a;
    }

    public final ForwardingTimeout setDelegate(Timeout timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = timeout;
        return this;
    }

    public Timeout timeout(long j, TimeUnit timeUnit) {
        return this.a.timeout(j, timeUnit);
    }

    public long timeoutNanos() {
        return this.a.timeoutNanos();
    }

    public boolean hasDeadline() {
        return this.a.hasDeadline();
    }

    public long deadlineNanoTime() {
        return this.a.deadlineNanoTime();
    }

    public Timeout deadlineNanoTime(long j) {
        return this.a.deadlineNanoTime(j);
    }

    public Timeout clearTimeout() {
        return this.a.clearTimeout();
    }

    public Timeout clearDeadline() {
        return this.a.clearDeadline();
    }

    public void throwIfReached() throws IOException {
        this.a.throwIfReached();
    }
}
