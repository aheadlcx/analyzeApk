package rx;

import java.util.concurrent.TimeUnit;
import rx.internal.schedulers.h;

public abstract class g {

    public static abstract class a implements k {
        public abstract k a(rx.b.a aVar);

        public abstract k a(rx.b.a aVar, long j, TimeUnit timeUnit);

        public k a(rx.b.a aVar, long j, long j2, TimeUnit timeUnit) {
            return h.a(this, aVar, j, j2, timeUnit, null);
        }

        public long a() {
            return System.currentTimeMillis();
        }
    }

    public abstract a a();

    public long b() {
        return System.currentTimeMillis();
    }
}
