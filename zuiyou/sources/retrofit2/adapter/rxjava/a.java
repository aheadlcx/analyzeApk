package retrofit2.adapter.rxjava;

import retrofit2.l;
import rx.d$a;
import rx.e.f;
import rx.exceptions.CompositeException;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.j;

final class a<T> implements d$a<T> {
    private final d$a<l<T>> a;

    private static class a<R> extends j<l<R>> {
        private final j<? super R> a;
        private boolean b;

        public /* synthetic */ void onNext(Object obj) {
            a((l) obj);
        }

        a(j<? super R> jVar) {
            super(jVar);
            this.a = jVar;
        }

        public void a(l<R> lVar) {
            Throwable e;
            if (lVar.d()) {
                this.a.onNext(lVar.e());
                return;
            }
            this.b = true;
            try {
                this.a.onError(new HttpException(lVar));
            } catch (OnCompletedFailedException e2) {
                e = e2;
                f.a().b().a(e);
            } catch (OnErrorFailedException e3) {
                e = e3;
                f.a().b().a(e);
            } catch (OnErrorNotImplementedException e4) {
                e = e4;
                f.a().b().a(e);
            } catch (Throwable e5) {
                rx.exceptions.a.b(e5);
                f.a().b().a(new CompositeException(new Throwable[]{r1, e5}));
            }
        }

        public void onError(Throwable th) {
            if (this.b) {
                Throwable assertionError = new AssertionError("This should never happen! Report as a Retrofit bug with the full stacktrace.");
                assertionError.initCause(th);
                f.a().b().a(assertionError);
                return;
            }
            this.a.onError(th);
        }

        public void onCompleted() {
            if (!this.b) {
                this.a.onCompleted();
            }
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    a(d$a<l<T>> d_a) {
        this.a = d_a;
    }

    public void a(j<? super T> jVar) {
        this.a.call(new a(jVar));
    }
}
