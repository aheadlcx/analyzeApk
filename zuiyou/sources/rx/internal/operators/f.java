package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.d$a;
import rx.g;
import rx.g.a;
import rx.j;

public final class f implements d$a<Long> {
    final long a;
    final long b;
    final TimeUnit c;
    final g d;

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    public f(long j, long j2, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = j2;
        this.c = timeUnit;
        this.d = gVar;
    }

    public void a(final j<? super Long> jVar) {
        final a a = this.d.a();
        jVar.add(a);
        a.a(new rx.b.a(this) {
            long a;
            final /* synthetic */ f d;

            public void call() {
                try {
                    j jVar = jVar;
                    long j = this.a;
                    this.a = 1 + j;
                    jVar.onNext(Long.valueOf(j));
                } catch (Throwable th) {
                    a.unsubscribe();
                } finally {
                    rx.exceptions.a.a(th, jVar);
                }
            }
        }, this.a, this.b, this.c);
    }
}
