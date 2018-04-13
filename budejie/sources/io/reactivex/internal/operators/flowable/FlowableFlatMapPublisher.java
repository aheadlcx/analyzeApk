package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.a.b;
import org.a.c;

public final class FlowableFlatMapPublisher<T, U> extends Flowable<U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends b<? extends U>> mapper;
    final int maxConcurrency;
    final b<T> source;

    public FlowableFlatMapPublisher(b<T> bVar, Function<? super T, ? extends b<? extends U>> function, boolean z, int i, int i2) {
        this.source = bVar;
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i;
        this.bufferSize = i2;
    }

    protected void subscribeActual(c<? super U> cVar) {
        if (!FlowableScalarXMap.tryScalarXMapSubscribe(this.source, cVar, this.mapper)) {
            this.source.subscribe(FlowableFlatMap.subscribe(cVar, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
        }
    }
}
