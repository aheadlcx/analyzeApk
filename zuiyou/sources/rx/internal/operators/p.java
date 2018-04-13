package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.d$b;
import rx.g;
import rx.j;

public final class p<T> implements d$b<T, T> {
    final long a;
    final g b;

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public p(long j, TimeUnit timeUnit, g gVar) {
        this.a = timeUnit.toMillis(j);
        this.b = gVar;
    }

    public j<? super T> a(final j<? super T> jVar) {
        return new j<T>(this, jVar) {
            final /* synthetic */ p b;
            private long c = -1;

            public void onStart() {
                request(Long.MAX_VALUE);
            }

            public void onNext(T t) {
                long b = this.b.b.b();
                if (this.c == -1 || b < this.c || b - this.c >= this.b.a) {
                    this.c = b;
                    jVar.onNext(t);
                }
            }

            public void onCompleted() {
                jVar.onCompleted();
            }

            public void onError(Throwable th) {
                jVar.onError(th);
            }
        };
    }
}
