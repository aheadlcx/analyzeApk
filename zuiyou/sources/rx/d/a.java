package rx.d;

import java.util.Arrays;
import rx.e;
import rx.e.c;
import rx.e.f;
import rx.exceptions.CompositeException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.exceptions.UnsubscribeFailedException;
import rx.j;

public class a<T> extends j<T> {
    boolean a;
    private final j<? super T> b;

    public a(j<? super T> jVar) {
        super(jVar);
        this.b = jVar;
    }

    public void onCompleted() {
        UnsubscribeFailedException unsubscribeFailedException;
        if (!this.a) {
            this.a = true;
            try {
                this.b.onCompleted();
                try {
                    unsubscribe();
                } catch (Throwable th) {
                    c.a(th);
                    unsubscribeFailedException = new UnsubscribeFailedException(th.getMessage(), th);
                }
            } catch (Throwable th2) {
                try {
                    unsubscribe();
                } catch (Throwable th3) {
                    c.a(th3);
                    unsubscribeFailedException = new UnsubscribeFailedException(th3.getMessage(), th3);
                }
            }
        }
    }

    public void onError(Throwable th) {
        rx.exceptions.a.b(th);
        if (!this.a) {
            this.a = true;
            a(th);
        }
    }

    public void onNext(T t) {
        try {
            if (!this.a) {
                this.b.onNext(t);
            }
        } catch (Throwable th) {
            rx.exceptions.a.a(th, (e) this);
        }
    }

    protected void a(Throwable th) {
        OnErrorFailedException onErrorFailedException;
        f.a().b().a(th);
        try {
            this.b.onError(th);
            try {
                unsubscribe();
            } catch (Throwable th2) {
                c.a(th2);
                onErrorFailedException = new OnErrorFailedException(th2);
            }
        } catch (OnErrorNotImplementedException e) {
            unsubscribe();
            throw e;
        } catch (Throwable th3) {
            c.a(th3);
            OnErrorFailedException onErrorFailedException2 = new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(new Throwable[]{th, th2, th3})));
        }
    }
}
