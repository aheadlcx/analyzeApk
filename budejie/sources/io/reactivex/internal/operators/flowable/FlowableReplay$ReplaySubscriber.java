package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription;
import io.reactivex.internal.operators.flowable.FlowableReplay.ReplayBuffer;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

final class FlowableReplay$ReplaySubscriber<T> extends AtomicReference<d> implements FlowableSubscriber<T>, Disposable {
    static final InnerSubscription[] EMPTY = new InnerSubscription[0];
    static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
    private static final long serialVersionUID = 7224554242710036740L;
    final ReplayBuffer<T> buffer;
    boolean done;
    final AtomicInteger management = new AtomicInteger();
    long maxChildRequested;
    long maxUpstreamRequested;
    final AtomicBoolean shouldConnect = new AtomicBoolean();
    final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference(EMPTY);

    FlowableReplay$ReplaySubscriber(ReplayBuffer<T> replayBuffer) {
        this.buffer = replayBuffer;
    }

    public boolean isDisposed() {
        return this.subscribers.get() == TERMINATED;
    }

    public void dispose() {
        this.subscribers.set(TERMINATED);
        SubscriptionHelper.cancel(this);
    }

    boolean add(InnerSubscription<T> innerSubscription) {
        if (innerSubscription == null) {
            throw new NullPointerException();
        }
        InnerSubscription[] innerSubscriptionArr;
        Object obj;
        do {
            innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
            if (innerSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = innerSubscriptionArr.length;
            obj = new InnerSubscription[(length + 1)];
            System.arraycopy(innerSubscriptionArr, 0, obj, 0, length);
            obj[length] = innerSubscription;
        } while (!this.subscribers.compareAndSet(innerSubscriptionArr, obj));
        return true;
    }

    void remove(InnerSubscription<T> innerSubscription) {
        InnerSubscription[] innerSubscriptionArr;
        Object obj;
        do {
            innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
            int length = innerSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (innerSubscriptionArr[i2].equals(innerSubscription)) {
                        i = i2;
                        break;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    obj = EMPTY;
                } else {
                    obj = new InnerSubscription[(length - 1)];
                    System.arraycopy(innerSubscriptionArr, 0, obj, 0, i);
                    System.arraycopy(innerSubscriptionArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(innerSubscriptionArr, obj));
    }

    public void onSubscribe(d dVar) {
        if (SubscriptionHelper.setOnce(this, dVar)) {
            manageRequests();
            for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.get()) {
                this.buffer.replay(replay);
            }
        }
    }

    public void onNext(T t) {
        if (!this.done) {
            this.buffer.next(t);
            for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.get()) {
                this.buffer.replay(replay);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        this.buffer.error(th);
        for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
            this.buffer.replay(replay);
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            this.buffer.complete();
            for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                this.buffer.replay(replay);
            }
        }
    }

    void manageRequests() {
        if (this.management.getAndIncrement() == 0) {
            int i = 1;
            while (!isDisposed()) {
                InnerSubscription[] innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
                long j = this.maxChildRequested;
                long j2 = j;
                for (InnerSubscription innerSubscription : innerSubscriptionArr) {
                    j2 = Math.max(j2, innerSubscription.totalRequested.get());
                }
                long j3 = this.maxUpstreamRequested;
                d dVar = (d) get();
                j = j2 - j;
                if (j != 0) {
                    this.maxChildRequested = j2;
                    if (dVar == null) {
                        j3 += j;
                        if (j3 < 0) {
                            j3 = Clock.MAX_TIME;
                        }
                        this.maxUpstreamRequested = j3;
                    } else if (j3 != 0) {
                        this.maxUpstreamRequested = 0;
                        dVar.request(j3 + j);
                    } else {
                        dVar.request(j);
                    }
                } else if (!(j3 == 0 || dVar == null)) {
                    this.maxUpstreamRequested = 0;
                    dVar.request(j3);
                }
                int addAndGet = this.management.addAndGet(-i);
                if (addAndGet != 0) {
                    i = addAndGet;
                } else {
                    return;
                }
            }
        }
    }
}
