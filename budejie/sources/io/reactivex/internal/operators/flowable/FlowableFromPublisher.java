package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import org.a.b;
import org.a.c;

public final class FlowableFromPublisher<T> extends Flowable<T> {
    final b<? extends T> publisher;

    public FlowableFromPublisher(b<? extends T> bVar) {
        this.publisher = bVar;
    }

    protected void subscribeActual(c<? super T> cVar) {
        this.publisher.subscribe(cVar);
    }
}
