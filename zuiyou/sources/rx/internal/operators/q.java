package rx.internal.operators;

import rx.b.a;
import rx.d$b;
import rx.f;
import rx.g;
import rx.g.e;
import rx.j;

public class q<T> implements d$b<T, T> {
    final g a;

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public q(g gVar) {
        this.a = gVar;
    }

    public j<? super T> a(final j<? super T> jVar) {
        final j<? super T> anonymousClass1 = new j<T>(this) {
            final /* synthetic */ q b;

            public void onCompleted() {
                jVar.onCompleted();
            }

            public void onError(Throwable th) {
                jVar.onError(th);
            }

            public void onNext(T t) {
                jVar.onNext(t);
            }

            public void setProducer(f fVar) {
                jVar.setProducer(fVar);
            }
        };
        jVar.add(e.a(new a(this) {
            final /* synthetic */ q b;

            public void call() {
                final g.a a = this.b.a.a();
                a.a(new a(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void call() {
                        anonymousClass1.unsubscribe();
                        a.unsubscribe();
                    }
                });
            }
        }));
        return anonymousClass1;
    }
}
