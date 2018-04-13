package rx.e;

import java.util.concurrent.ThreadFactory;
import rx.internal.schedulers.a;
import rx.internal.schedulers.b;
import rx.internal.schedulers.f;
import rx.internal.util.RxThreadFactory;

public class g {
    private static final g a = new g();

    public static rx.g a() {
        return a(new RxThreadFactory("RxComputationScheduler-"));
    }

    public static rx.g a(ThreadFactory threadFactory) {
        if (threadFactory != null) {
            return new b(threadFactory);
        }
        throw new NullPointerException("threadFactory == null");
    }

    public static rx.g b() {
        return b(new RxThreadFactory("RxIoScheduler-"));
    }

    public static rx.g b(ThreadFactory threadFactory) {
        if (threadFactory != null) {
            return new a(threadFactory);
        }
        throw new NullPointerException("threadFactory == null");
    }

    public static rx.g c() {
        return c(new RxThreadFactory("RxNewThreadScheduler-"));
    }

    public static rx.g c(ThreadFactory threadFactory) {
        if (threadFactory != null) {
            return new f(threadFactory);
        }
        throw new NullPointerException("threadFactory == null");
    }

    public rx.g d() {
        return null;
    }

    public rx.g e() {
        return null;
    }

    public rx.g f() {
        return null;
    }

    @Deprecated
    public rx.b.a a(rx.b.a aVar) {
        return aVar;
    }

    public static g g() {
        return a;
    }
}
