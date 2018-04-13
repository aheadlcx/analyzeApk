package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.internal.fuseable.ScalarCallable;
import io.reactivex.internal.subscriptions.ScalarSubscription;
import org.a.c;

public final class FlowableJust<T> extends Flowable<T> implements ScalarCallable<T> {
    private final T value;

    public FlowableJust(T t) {
        this.value = t;
    }

    protected void subscribeActual(c<? super T> cVar) {
        cVar.onSubscribe(new ScalarSubscription(cVar, this.value));
    }

    public T call() {
        return this.value;
    }
}
