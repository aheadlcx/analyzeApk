package rx.internal.operators;

import rx.b.g;
import rx.d;
import rx.d$a;
import rx.e.c;
import rx.exceptions.OnErrorThrowable;
import rx.f;
import rx.j;

public final class b<T> implements d$a<T> {
    final d<T> a;
    final g<? super T, Boolean> b;

    static final class a<T> extends j<T> {
        final j<? super T> a;
        final g<? super T, Boolean> b;
        boolean c;

        public a(j<? super T> jVar, g<? super T, Boolean> gVar) {
            this.a = jVar;
            this.b = gVar;
            request(0);
        }

        public void onNext(T t) {
            try {
                if (((Boolean) this.b.call(t)).booleanValue()) {
                    this.a.onNext(t);
                } else {
                    request(1);
                }
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
            super.setProducer(fVar);
            this.a.setProducer(fVar);
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    public b(d<T> dVar, g<? super T, Boolean> gVar) {
        this.a = dVar;
        this.b = gVar;
    }

    public void a(j<? super T> jVar) {
        Object aVar = new a(jVar, this.b);
        jVar.add(aVar);
        this.a.a(aVar);
    }
}
