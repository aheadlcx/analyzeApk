package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

final class PublishProcessor$PublishSubscription<T> extends AtomicLong implements d {
    private static final long serialVersionUID = 3562861878281475070L;
    final c<? super T> actual;
    final PublishProcessor<T> parent;

    PublishProcessor$PublishSubscription(c<? super T> cVar, PublishProcessor<T> publishProcessor) {
        this.actual = cVar;
        this.parent = publishProcessor;
    }

    public void onNext(T t) {
        long j = get();
        if (j != Long.MIN_VALUE) {
            if (j != 0) {
                this.actual.onNext(t);
                if (j != Clock.MAX_TIME) {
                    decrementAndGet();
                    return;
                }
                return;
            }
            cancel();
            this.actual.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
        }
    }

    public void onError(Throwable th) {
        if (get() != Long.MIN_VALUE) {
            this.actual.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    public void onComplete() {
        if (get() != Long.MIN_VALUE) {
            this.actual.onComplete();
        }
    }

    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            BackpressureHelper.addCancel(this, j);
        }
    }

    public void cancel() {
        if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
            this.parent.remove(this);
        }
    }

    public boolean isCancelled() {
        return get() == Long.MIN_VALUE;
    }

    boolean isFull() {
        return get() == 0;
    }
}
