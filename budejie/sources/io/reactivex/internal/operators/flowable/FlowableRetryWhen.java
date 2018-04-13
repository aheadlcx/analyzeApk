package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.flowable.FlowableRepeatWhen.WhenReceiver;
import io.reactivex.internal.operators.flowable.FlowableRepeatWhen.WhenSourceSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.SerializedSubscriber;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableRetryWhen<T> extends AbstractFlowableWithUpstream<T, T> {
    final Function<? super Flowable<Throwable>, ? extends b<?>> handler;

    static final class RetryWhenSubscriber<T> extends WhenSourceSubscriber<T, Throwable> {
        private static final long serialVersionUID = -2680129890138081029L;

        RetryWhenSubscriber(c<? super T> cVar, FlowableProcessor<Throwable> flowableProcessor, d dVar) {
            super(cVar, flowableProcessor, dVar);
        }

        public void onError(Throwable th) {
            again(th);
        }

        public void onComplete() {
            this.receiver.cancel();
            this.actual.onComplete();
        }
    }

    public FlowableRetryWhen(Flowable<T> flowable, Function<? super Flowable<Throwable>, ? extends b<?>> function) {
        super(flowable);
        this.handler = function;
    }

    public void subscribeActual(c<? super T> cVar) {
        c serializedSubscriber = new SerializedSubscriber(cVar);
        FlowableProcessor toSerialized = UnicastProcessor.create(8).toSerialized();
        try {
            b bVar = (b) ObjectHelper.requireNonNull(this.handler.apply(toSerialized), "handler returned a null Publisher");
            Object whenReceiver = new WhenReceiver(this.source);
            d retryWhenSubscriber = new RetryWhenSubscriber(serializedSubscriber, toSerialized, whenReceiver);
            whenReceiver.subscriber = retryWhenSubscriber;
            cVar.onSubscribe(retryWhenSubscriber);
            bVar.subscribe(whenReceiver);
            whenReceiver.onNext(Integer.valueOf(0));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
