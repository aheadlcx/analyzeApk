package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

final class q extends Timeout {
    q() {
    }

    public Timeout timeout(long j, TimeUnit timeUnit) {
        return this;
    }

    public Timeout deadlineNanoTime(long j) {
        return this;
    }

    public void throwIfReached() throws IOException {
    }
}
