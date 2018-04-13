package retrofit2.adapter.rxjava;

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.b;
import retrofit2.l;
import rx.exceptions.CompositeException;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.exceptions.a;
import rx.f;
import rx.j;
import rx.k;

final class CallArbiter<T> extends AtomicInteger implements f, k {
    private final b<T> call;
    private volatile l<T> response;
    private final j<? super l<T>> subscriber;

    CallArbiter(b<T> bVar, j<? super l<T>> jVar) {
        super(0);
        this.call = bVar;
        this.subscriber = jVar;
    }

    public void unsubscribe() {
        this.call.b();
    }

    public boolean isUnsubscribed() {
        return this.call.c();
    }

    public void request(long j) {
        if (j != 0) {
            while (true) {
                int i = get();
                switch (i) {
                    case 0:
                        if (!compareAndSet(0, 1)) {
                            break;
                        }
                        return;
                    case 1:
                    case 3:
                        return;
                    case 2:
                        if (!compareAndSet(2, 3)) {
                            break;
                        }
                        a(this.response);
                        return;
                    default:
                        throw new IllegalStateException("Unknown state: " + i);
                }
            }
        }
    }

    void emitResponse(l<T> lVar) {
        while (true) {
            int i = get();
            switch (i) {
                case 0:
                    this.response = lVar;
                    if (!compareAndSet(0, 2)) {
                        break;
                    }
                    return;
                case 1:
                    if (!compareAndSet(1, 3)) {
                        break;
                    }
                    a(lVar);
                    return;
                case 2:
                case 3:
                    throw new AssertionError();
                default:
                    throw new IllegalStateException("Unknown state: " + i);
            }
        }
    }

    private void a(l<T> lVar) {
        Throwable e;
        try {
            if (!isUnsubscribed()) {
                this.subscriber.onNext(lVar);
            }
            try {
                if (!isUnsubscribed()) {
                    this.subscriber.onCompleted();
                }
            } catch (OnCompletedFailedException e2) {
                e = e2;
                rx.e.f.a().b().a(e);
            } catch (OnErrorFailedException e3) {
                e = e3;
                rx.e.f.a().b().a(e);
            } catch (OnErrorNotImplementedException e4) {
                e = e4;
                rx.e.f.a().b().a(e);
            } catch (Throwable e5) {
                a.b(e5);
                rx.e.f.a().b().a(e5);
            }
        } catch (OnCompletedFailedException e6) {
            e5 = e6;
            rx.e.f.a().b().a(e5);
        } catch (OnErrorFailedException e7) {
            e5 = e7;
            rx.e.f.a().b().a(e5);
        } catch (OnErrorNotImplementedException e8) {
            e5 = e8;
            rx.e.f.a().b().a(e5);
        } catch (Throwable e52) {
            a.b(e52);
            try {
                this.subscriber.onError(e52);
            } catch (OnCompletedFailedException e9) {
                e52 = e9;
                rx.e.f.a().b().a(e52);
            } catch (OnErrorFailedException e10) {
                e52 = e10;
                rx.e.f.a().b().a(e52);
            } catch (OnErrorNotImplementedException e11) {
                e52 = e11;
                rx.e.f.a().b().a(e52);
            } catch (Throwable th) {
                a.b(th);
                rx.e.f.a().b().a(new CompositeException(new Throwable[]{e52, th}));
            }
        }
    }

    void emitError(Throwable th) {
        Throwable e;
        set(3);
        if (!isUnsubscribed()) {
            try {
                this.subscriber.onError(th);
                return;
            } catch (OnCompletedFailedException e2) {
                e = e2;
            } catch (OnErrorFailedException e3) {
                e = e3;
            } catch (OnErrorNotImplementedException e4) {
                e = e4;
            } catch (Throwable e5) {
                a.b(e5);
                rx.e.f.a().b().a(new CompositeException(new Throwable[]{th, e5}));
                return;
            }
        }
        return;
        rx.e.f.a().b().a(e5);
    }
}
