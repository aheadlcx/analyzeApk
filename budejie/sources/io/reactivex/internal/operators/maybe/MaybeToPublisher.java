package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;
import org.a.b;

public enum MaybeToPublisher implements Function<MaybeSource<Object>, b<Object>> {
    INSTANCE;

    public static <T> Function<MaybeSource<T>, b<T>> instance() {
        return INSTANCE;
    }

    public b<Object> apply(MaybeSource<Object> maybeSource) throws Exception {
        return new MaybeToFlowable(maybeSource);
    }
}
