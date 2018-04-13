package io.reactivex.processors;

import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.plugins.RxJavaPlugins;
import org.a.c;

final class AsyncProcessor$AsyncSubscription<T> extends DeferredScalarSubscription<T> {
    private static final long serialVersionUID = 5629876084736248016L;
    final AsyncProcessor<T> parent;

    AsyncProcessor$AsyncSubscription(c<? super T> cVar, AsyncProcessor<T> asyncProcessor) {
        super(cVar);
        this.parent = asyncProcessor;
    }

    public void cancel() {
        if (super.tryCancel()) {
            this.parent.remove(this);
        }
    }

    void onComplete() {
        if (!isCancelled()) {
            this.actual.onComplete();
        }
    }

    void onError(Throwable th) {
        if (isCancelled()) {
            RxJavaPlugins.onError(th);
        } else {
            this.actual.onError(th);
        }
    }
}
