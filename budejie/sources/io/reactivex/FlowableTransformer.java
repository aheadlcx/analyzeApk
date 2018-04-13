package io.reactivex;

import io.reactivex.annotations.NonNull;
import org.a.b;

public interface FlowableTransformer<Upstream, Downstream> {
    @NonNull
    b<Downstream> apply(@NonNull Flowable<Upstream> flowable);
}
