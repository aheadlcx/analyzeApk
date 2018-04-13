package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.ErrorMode;
import org.a.b;
import org.a.c;

public final class FlowableConcatMapPublisher<T, R> extends Flowable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends b<? extends R>> mapper;
    final int prefetch;
    final b<T> source;

    public FlowableConcatMapPublisher(b<T> bVar, Function<? super T, ? extends b<? extends R>> function, int i, ErrorMode errorMode) {
        this.source = bVar;
        this.mapper = function;
        this.prefetch = i;
        this.errorMode = errorMode;
    }

    protected void subscribeActual(c<? super R> cVar) {
        if (!FlowableScalarXMap.tryScalarXMapSubscribe(this.source, cVar, this.mapper)) {
            this.source.subscribe(FlowableConcatMap.subscribe(cVar, this.mapper, this.prefetch, this.errorMode));
        }
    }
}
