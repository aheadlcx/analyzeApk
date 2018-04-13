package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.d$b;
import rx.e.c;
import rx.internal.producers.SingleProducer;
import rx.j;

public final class m<T> implements d$b<T, T> {
    private final boolean a;
    private final T b;

    static final class a {
        static final m<?> a = new m();
    }

    static final class b<T> extends j<T> {
        private final j<? super T> a;
        private final boolean b;
        private final T c;
        private T d;
        private boolean e;
        private boolean f;

        b(j<? super T> jVar, boolean z, T t) {
            this.a = jVar;
            this.b = z;
            this.c = t;
            request(2);
        }

        public void onNext(T t) {
            if (!this.f) {
                if (this.e) {
                    this.f = true;
                    this.a.onError(new IllegalArgumentException("Sequence contains too many elements"));
                    unsubscribe();
                    return;
                }
                this.d = t;
                this.e = true;
            }
        }

        public void onCompleted() {
            if (!this.f) {
                if (this.e) {
                    this.a.setProducer(new SingleProducer(this.a, this.d));
                } else if (this.b) {
                    this.a.setProducer(new SingleProducer(this.a, this.c));
                } else {
                    this.a.onError(new NoSuchElementException("Sequence contains no elements"));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.f) {
                c.a(th);
            } else {
                this.a.onError(th);
            }
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public static <T> m<T> a() {
        return a.a;
    }

    m() {
        this(false, null);
    }

    private m(boolean z, T t) {
        this.a = z;
        this.b = t;
    }

    public j<? super T> a(j<? super T> jVar) {
        Object bVar = new b(jVar, this.a, this.b);
        jVar.add(bVar);
        return bVar;
    }
}
