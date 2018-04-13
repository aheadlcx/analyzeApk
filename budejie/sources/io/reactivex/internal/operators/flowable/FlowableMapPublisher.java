package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.a.b;
import org.a.c;

public final class FlowableMapPublisher<T, U> extends Flowable<U> {
    final Function<? super T, ? extends U> mapper;
    final b<T> source;

    public FlowableMapPublisher(b<T> bVar, Function<? super T, ? extends U> function) {
        this.source = bVar;
        this.mapper = function;
    }

    protected void subscribeActual(c<? super U> cVar) {
        this.source.subscribe(new MapSubscriber(cVar, this.mapper));
    }
}
