package bolts;

import java.util.Locale;
import java.util.concurrent.CancellationException;

public class CancellationToken {
    private final CancellationTokenSource a;

    CancellationToken(CancellationTokenSource cancellationTokenSource) {
        this.a = cancellationTokenSource;
    }

    public boolean isCancellationRequested() {
        return this.a.isCancellationRequested();
    }

    public CancellationTokenRegistration register(Runnable runnable) {
        return this.a.a(runnable);
    }

    public void throwIfCancellationRequested() throws CancellationException {
        this.a.a();
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(this.a.isCancellationRequested())});
    }
}
