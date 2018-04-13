package rx.internal.operators;

import rx.d;
import rx.d$a;
import rx.f;
import rx.g;
import rx.j;

public final class n<T> implements d$a<T> {
    final g a;
    final d<T> b;
    final boolean c;

    static final class a<T> extends j<T> implements rx.b.a {
        final j<? super T> a;
        final boolean b;
        final rx.g.a c;
        d<T> d;
        Thread e;

        a(j<? super T> jVar, boolean z, rx.g.a aVar, d<T> dVar) {
            this.a = jVar;
            this.b = z;
            this.c = aVar;
            this.d = dVar;
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            try {
                this.a.onError(th);
            } finally {
                this.c.unsubscribe();
            }
        }

        public void onCompleted() {
            try {
                this.a.onCompleted();
            } finally {
                this.c.unsubscribe();
            }
        }

        public void call() {
            d dVar = this.d;
            this.d = null;
            this.e = Thread.currentThread();
            dVar.a(this);
        }

        public void setProducer(final f fVar) {
            this.a.setProducer(new f(this) {
                final /* synthetic */ a b;

                public void request(final long j) {
                    if (this.b.e == Thread.currentThread() || !this.b.b) {
                        fVar.request(j);
                    } else {
                        this.b.c.a(new rx.b.a(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void call() {
                                fVar.request(j);
                            }
                        });
                    }
                }
            });
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    public n(d<T> dVar, g gVar, boolean z) {
        this.a = gVar;
        this.b = dVar;
        this.c = z;
    }

    public void a(j<? super T> jVar) {
        Object a = this.a.a();
        Object aVar = new a(jVar, this.c, a, this.b);
        jVar.add(aVar);
        jVar.add(a);
        a.a(aVar);
    }
}
