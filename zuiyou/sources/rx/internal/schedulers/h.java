package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.internal.subscriptions.SequentialSubscription;
import rx.k;

public final class h {
    public static final long a = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    public interface a {
        long a();
    }

    public static k a(rx.g.a aVar, rx.b.a aVar2, long j, long j2, TimeUnit timeUnit, a aVar3) {
        final long toNanos = timeUnit.toNanos(j2);
        final long a = aVar3 != null ? aVar3.a() : TimeUnit.MILLISECONDS.toNanos(aVar.a());
        final long toNanos2 = a + timeUnit.toNanos(j);
        Object sequentialSubscription = new SequentialSubscription();
        final k sequentialSubscription2 = new SequentialSubscription(sequentialSubscription);
        final rx.b.a aVar4 = aVar2;
        final a aVar5 = aVar3;
        final rx.g.a aVar6 = aVar;
        sequentialSubscription.replace(aVar.a(new rx.b.a() {
            long a;
            long b = a;
            long c = toNanos2;

            public void call() {
                aVar4.call();
                if (!sequentialSubscription2.isUnsubscribed()) {
                    long a;
                    long j;
                    if (aVar5 != null) {
                        a = aVar5.a();
                    } else {
                        a = TimeUnit.MILLISECONDS.toNanos(aVar6.a());
                    }
                    long j2;
                    if (h.a + a < this.b || a >= (this.b + toNanos) + h.a) {
                        j = toNanos + a;
                        j2 = toNanos;
                        long j3 = this.a + 1;
                        this.a = j3;
                        this.c = j - (j2 * j3);
                    } else {
                        j = this.c;
                        j2 = this.a + 1;
                        this.a = j2;
                        j += j2 * toNanos;
                    }
                    this.b = a;
                    sequentialSubscription2.replace(aVar6.a(this, j - a, TimeUnit.NANOSECONDS));
                }
            }
        }, j, timeUnit));
        return sequentialSubscription2;
    }
}
