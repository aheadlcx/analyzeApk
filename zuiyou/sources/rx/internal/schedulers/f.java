package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import rx.g;
import rx.g.a;

public final class f extends g {
    private final ThreadFactory a;

    public f(ThreadFactory threadFactory) {
        this.a = threadFactory;
    }

    public a a() {
        return new g(this.a);
    }
}
