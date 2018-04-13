package io.reactivex.internal.subscribers;

import com.facebook.common.time.Clock;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class FutureSubscriber<T> extends CountDownLatch implements FlowableSubscriber<T>, Future<T>, d {
    Throwable error;
    final AtomicReference<d> s = new AtomicReference();
    T value;

    public FutureSubscriber() {
        super(1);
    }

    public boolean cancel(boolean z) {
        SubscriptionHelper subscriptionHelper;
        do {
            subscriptionHelper = (d) this.s.get();
            if (subscriptionHelper == this || subscriptionHelper == SubscriptionHelper.CANCELLED) {
                return false;
            }
        } while (!this.s.compareAndSet(subscriptionHelper, SubscriptionHelper.CANCELLED));
        if (subscriptionHelper != null) {
            subscriptionHelper.cancel();
        }
        countDown();
        return true;
    }

    public boolean isCancelled() {
        return SubscriptionHelper.isCancelled((d) this.s.get());
    }

    public boolean isDone() {
        return getCount() == 0;
    }

    public T get() throws InterruptedException, ExecutionException {
        if (getCount() != 0) {
            BlockingHelper.verifyNonBlocking();
            await();
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.error;
        if (th == null) {
            return this.value;
        }
        throw new ExecutionException(th);
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (getCount() != 0) {
            BlockingHelper.verifyNonBlocking();
            if (!await(j, timeUnit)) {
                throw new TimeoutException();
            }
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.error;
        if (th == null) {
            return this.value;
        }
        throw new ExecutionException(th);
    }

    public void onSubscribe(d dVar) {
        if (SubscriptionHelper.setOnce(this.s, dVar)) {
            dVar.request(Clock.MAX_TIME);
        }
    }

    public void onNext(T t) {
        if (this.value != null) {
            ((d) this.s.get()).cancel();
            onError(new IndexOutOfBoundsException("More than one element received"));
            return;
        }
        this.value = t;
    }

    public void onError(Throwable th) {
        SubscriptionHelper subscriptionHelper;
        do {
            subscriptionHelper = (d) this.s.get();
            if (subscriptionHelper == this || subscriptionHelper == SubscriptionHelper.CANCELLED) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
        } while (!this.s.compareAndSet(subscriptionHelper, this));
        countDown();
    }

    public void onComplete() {
        if (this.value == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        SubscriptionHelper subscriptionHelper;
        do {
            subscriptionHelper = (d) this.s.get();
            if (subscriptionHelper == this || subscriptionHelper == SubscriptionHelper.CANCELLED) {
                return;
            }
        } while (!this.s.compareAndSet(subscriptionHelper, this));
        countDown();
    }

    public void cancel() {
    }

    public void request(long j) {
    }
}
