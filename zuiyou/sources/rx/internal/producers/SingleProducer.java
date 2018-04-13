package rx.internal.producers;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.e;
import rx.exceptions.a;
import rx.f;
import rx.j;

public final class SingleProducer<T> extends AtomicBoolean implements f {
    private static final long serialVersionUID = -3353584923995471404L;
    final j<? super T> child;
    final T value;

    public SingleProducer(j<? super T> jVar, T t) {
        this.child = jVar;
        this.value = t;
    }

    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0 && compareAndSet(false, true)) {
            e eVar = this.child;
            if (!eVar.isUnsubscribed()) {
                Object obj = this.value;
                try {
                    eVar.onNext(obj);
                    if (!eVar.isUnsubscribed()) {
                        eVar.onCompleted();
                    }
                } catch (Throwable th) {
                    a.a(th, eVar, obj);
                }
            }
        }
    }
}
