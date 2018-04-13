package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.plugins.RxJavaPlugins;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableDelaySubscriptionOther<T, U> extends Flowable<T> {
    final b<? extends T> main;
    final b<U> other;

    final class DelaySubscriber implements FlowableSubscriber<U> {
        final c<? super T> child;
        boolean done;
        final SubscriptionArbiter serial;

        final class DelaySubscription implements d {
            private final d s;

            DelaySubscription(d dVar) {
                this.s = dVar;
            }

            public void request(long j) {
            }

            public void cancel() {
                this.s.cancel();
            }
        }

        final class OnCompleteSubscriber implements FlowableSubscriber<T> {
            OnCompleteSubscriber() {
            }

            public void onSubscribe(d dVar) {
                DelaySubscriber.this.serial.setSubscription(dVar);
            }

            public void onNext(T t) {
                DelaySubscriber.this.child.onNext(t);
            }

            public void onError(Throwable th) {
                DelaySubscriber.this.child.onError(th);
            }

            public void onComplete() {
                DelaySubscriber.this.child.onComplete();
            }
        }

        DelaySubscriber(SubscriptionArbiter subscriptionArbiter, c<? super T> cVar) {
            this.serial = subscriptionArbiter;
            this.child = cVar;
        }

        public void onSubscribe(d dVar) {
            this.serial.setSubscription(new DelaySubscription(dVar));
            dVar.request(Clock.MAX_TIME);
        }

        public void onNext(U u) {
            onComplete();
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.child.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                FlowableDelaySubscriptionOther.this.main.subscribe(new OnCompleteSubscriber());
            }
        }
    }

    public FlowableDelaySubscriptionOther(b<? extends T> bVar, b<U> bVar2) {
        this.main = bVar;
        this.other = bVar2;
    }

    public void subscribeActual(c<? super T> cVar) {
        Object subscriptionArbiter = new SubscriptionArbiter();
        cVar.onSubscribe(subscriptionArbiter);
        this.other.subscribe(new DelaySubscriber(subscriptionArbiter, cVar));
    }
}
