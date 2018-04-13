package rx.internal.operators;

import rx.b.g;
import rx.d$a;
import rx.e.c;
import rx.exceptions.OnErrorThrowable;
import rx.f;
import rx.j;

public final class d<T, R> implements d$a<R> {
    final rx.d<T> a;
    final g<? super T, ? extends R> b;

    static final class a<T, R> extends j<T> {
        final j<? super R> a;
        final g<? super T, ? extends R> b;
        boolean c;

        public a(j<? super R> jVar, g<? super T, ? extends R> gVar) {
            this.a = jVar;
            this.b = gVar;
        }

        public void onNext(T t) {
            try {
                this.a.onNext(this.b.call(t));
            } catch (Throwable th) {
                rx.exceptions.a.b(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        public void onError(Throwable th) {
            if (this.c) {
                c.a(th);
                return;
            }
            this.c = true;
            this.a.onError(th);
        }

        public void onCompleted() {
            if (!this.c) {
                this.a.onCompleted();
            }
        }

        public void setProducer(f fVar) {
            this.a.setProducer(fVar);
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    public d(rx.d<T> dVar, g<? super T, ? extends R> gVar) {
        this.a = dVar;
        this.b = gVar;
    }

    public void a(j<? super R> jVar) {
        Object aVar = new a(jVar, this.b);
        jVar.add(aVar);
        this.a.a(aVar);
    }
}
