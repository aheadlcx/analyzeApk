package retrofit2.adapter.rxjava;

import retrofit2.l;
import rx.d$a;
import rx.e.f;
import rx.exceptions.CompositeException;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.j;

final class e<T> implements d$a<d<T>> {
    private final d$a<l<T>> a;

    private static class a<R> extends j<l<R>> {
        private final j<? super d<R>> a;

        public /* synthetic */ void onNext(Object obj) {
            a((l) obj);
        }

        a(j<? super d<R>> jVar) {
            super(jVar);
            this.a = jVar;
        }

        public void a(l<R> lVar) {
            this.a.onNext(d.a(lVar));
        }

        public void onError(Throwable th) {
            Throwable th2;
            try {
                this.a.onNext(d.a(th));
                this.a.onCompleted();
            } catch (Throwable th22) {
                try {
                    this.a.onError(th22);
                } catch (OnCompletedFailedException e) {
                    th22 = e;
                    f.a().b().a(th22);
                } catch (OnErrorFailedException e2) {
                    th22 = e2;
                    f.a().b().a(th22);
                } catch (OnErrorNotImplementedException e3) {
                    th22 = e3;
                    f.a().b().a(th22);
                } catch (Throwable th3) {
                    rx.exceptions.a.b(th3);
                    f.a().b().a(new CompositeException(new Throwable[]{th22, th3}));
                }
            }
        }

        public void onCompleted() {
            this.a.onCompleted();
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    e(d$a<l<T>> d_a) {
        this.a = d_a;
    }

    public void a(j<? super d<T>> jVar) {
        this.a.call(new a(jVar));
    }
}
