package io.reactivex;

import io.reactivex.annotations.NonNull;
import org.a.c;

public interface FlowableOperator<Downstream, Upstream> {
    @NonNull
    c<? super Upstream> apply(@NonNull c<? super Downstream> cVar) throws Exception;
}
