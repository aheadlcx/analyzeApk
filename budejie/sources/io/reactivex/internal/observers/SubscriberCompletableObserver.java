package io.reactivex.internal.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import org.a.c;
import org.a.d;

public final class SubscriberCompletableObserver<T> implements CompletableObserver, d {
    Disposable d;
    final c<? super T> subscriber;

    public SubscriberCompletableObserver(c<? super T> cVar) {
        this.subscriber = cVar;
    }

    public void onComplete() {
        this.subscriber.onComplete();
    }

    public void onError(Throwable th) {
        this.subscriber.onError(th);
    }

    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.d, disposable)) {
            this.d = disposable;
            this.subscriber.onSubscribe(this);
        }
    }

    public void request(long j) {
    }

    public void cancel() {
        this.d.dispose();
    }
}
