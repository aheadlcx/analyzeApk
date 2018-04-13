package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import java.util.concurrent.Callable;
import org.a.b;
import org.a.c;

public final class FlowableDefer<T> extends Flowable<T> {
    final Callable<? extends b<? extends T>> supplier;

    public FlowableDefer(Callable<? extends b<? extends T>> callable) {
        this.supplier = callable;
    }

    public void subscribeActual(c<? super T> cVar) {
        try {
            ((b) ObjectHelper.requireNonNull(this.supplier.call(), "The publisher supplied is null")).subscribe(cVar);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
