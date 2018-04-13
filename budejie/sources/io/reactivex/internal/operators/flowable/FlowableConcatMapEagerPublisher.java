package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.ErrorMode;
import org.a.b;
import org.a.c;

public final class FlowableConcatMapEagerPublisher<T, R> extends Flowable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends b<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;
    final b<T> source;

    public FlowableConcatMapEagerPublisher(b<T> bVar, Function<? super T, ? extends b<? extends R>> function, int i, int i2, ErrorMode errorMode) {
        this.source = bVar;
        this.mapper = function;
        this.maxConcurrency = i;
        this.prefetch = i2;
        this.errorMode = errorMode;
    }

    protected void subscribeActual(c<? super R> cVar) {
        this.source.subscribe(new ConcatMapEagerDelayErrorSubscriber(cVar, this.mapper, this.maxConcurrency, this.prefetch, this.errorMode));
    }
}
