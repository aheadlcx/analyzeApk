package rx.internal.producers;

import java.util.concurrent.atomic.AtomicInteger;
import rx.exceptions.a;
import rx.f;
import rx.j;

public final class SingleDelayedProducer<T> extends AtomicInteger implements f {
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 2;
    static final int NO_REQUEST_HAS_VALUE = 1;
    static final int NO_REQUEST_NO_VALUE = 0;
    private static final long serialVersionUID = -2873467947112093874L;
    final j<? super T> child;
    T value;

    public SingleDelayedProducer(j<? super T> jVar) {
        this.child = jVar;
    }

    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0) {
            int i;
            while (true) {
                i = get();
                if (i != 0) {
                    break;
                } else if (compareAndSet(0, 2)) {
                    return;
                }
            }
            if (i == 1 && compareAndSet(1, 3)) {
                a(this.child, this.value);
            }
        }
    }

    public void setValue(T t) {
        do {
            int i = get();
            if (i == 0) {
                this.value = t;
            } else if (i == 2 && compareAndSet(2, 3)) {
                a(this.child, t);
                return;
            } else {
                return;
            }
        } while (!compareAndSet(0, 1));
    }

    private static <T> void a(j<? super T> jVar, T t) {
        if (!jVar.isUnsubscribed()) {
            try {
                jVar.onNext(t);
                if (!jVar.isUnsubscribed()) {
                    jVar.onCompleted();
                }
            } catch (Throwable th) {
                a.a(th, jVar, t);
            }
        }
    }
}
