package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.LinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableCache<T> extends AbstractFlowableWithUpstream<T, T> {
    final AtomicBoolean once = new AtomicBoolean();
    final CacheState<T> state;

    static final class CacheState<T> extends LinkedArrayList implements FlowableSubscriber<T> {
        static final ReplaySubscription[] EMPTY = new ReplaySubscription[0];
        static final ReplaySubscription[] TERMINATED = new ReplaySubscription[0];
        final AtomicReference<d> connection = new AtomicReference();
        volatile boolean isConnected;
        final Flowable<T> source;
        boolean sourceDone;
        final AtomicReference<ReplaySubscription<T>[]> subscribers;

        CacheState(Flowable<T> flowable, int i) {
            super(i);
            this.source = flowable;
            this.subscribers = new AtomicReference(EMPTY);
        }

        public void addChild(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription[] replaySubscriptionArr;
            Object obj;
            do {
                replaySubscriptionArr = (ReplaySubscription[]) this.subscribers.get();
                if (replaySubscriptionArr != TERMINATED) {
                    int length = replaySubscriptionArr.length;
                    obj = new ReplaySubscription[(length + 1)];
                    System.arraycopy(replaySubscriptionArr, 0, obj, 0, length);
                    obj[length] = replaySubscription;
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(replaySubscriptionArr, obj));
        }

        public void removeChild(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription[] replaySubscriptionArr;
            Object obj;
            do {
                replaySubscriptionArr = (ReplaySubscription[]) this.subscribers.get();
                int length = replaySubscriptionArr.length;
                if (length != 0) {
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (replaySubscriptionArr[i2].equals(replaySubscription)) {
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
                        obj = new ReplaySubscription[(length - 1)];
                        System.arraycopy(replaySubscriptionArr, 0, obj, 0, i);
                        System.arraycopy(replaySubscriptionArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(replaySubscriptionArr, obj));
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.connection, dVar)) {
                dVar.request(Clock.MAX_TIME);
            }
        }

        public void connect() {
            this.source.subscribe(this);
            this.isConnected = true;
        }

        public void onNext(T t) {
            if (!this.sourceDone) {
                add(NotificationLite.next(t));
                for (ReplaySubscription replay : (ReplaySubscription[]) this.subscribers.get()) {
                    replay.replay();
                }
            }
        }

        public void onError(Throwable th) {
            if (this.sourceDone) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.sourceDone = true;
            add(NotificationLite.error(th));
            SubscriptionHelper.cancel(this.connection);
            for (ReplaySubscription replay : (ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                replay.replay();
            }
        }

        public void onComplete() {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.complete());
                SubscriptionHelper.cancel(this.connection);
                for (ReplaySubscription replay : (ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    replay.replay();
                }
            }
        }
    }

    static final class ReplaySubscription<T> extends AtomicInteger implements d {
        private static final long CANCELLED = -1;
        private static final long serialVersionUID = -2557562030197141021L;
        final c<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final AtomicLong requested = new AtomicLong();
        final CacheState<T> state;

        ReplaySubscription(c<? super T> cVar, CacheState<T> cacheState) {
            this.child = cVar;
            this.state = cacheState;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                long j2;
                do {
                    j2 = this.requested.get();
                    if (j2 != -1) {
                    } else {
                        return;
                    }
                } while (!this.requested.compareAndSet(j2, BackpressureHelper.addCap(j2, j)));
                replay();
            }
        }

        public void cancel() {
            if (this.requested.getAndSet(-1) != -1) {
                this.state.removeChild(this);
            }
        }

        public void replay() {
            if (getAndIncrement() == 0) {
                c cVar = this.child;
                AtomicLong atomicLong = this.requested;
                int i = 1;
                while (true) {
                    long j = atomicLong.get();
                    if (j >= 0) {
                        int i2;
                        int size = this.state.size();
                        if (size != 0) {
                            Object[] objArr = this.currentBuffer;
                            if (objArr == null) {
                                objArr = this.state.head();
                                this.currentBuffer = objArr;
                            }
                            int length = objArr.length - 1;
                            int i3 = this.index;
                            int i4 = 0;
                            Object[] objArr2 = objArr;
                            i2 = this.currentIndexInBuffer;
                            while (i3 < size && j > 0) {
                                if (atomicLong.get() != -1) {
                                    if (i2 == length) {
                                        objArr2 = (Object[]) objArr2[length];
                                        i2 = 0;
                                    }
                                    if (!NotificationLite.accept(objArr2[i2], cVar)) {
                                        i3++;
                                        j--;
                                        i4++;
                                        i2++;
                                    } else {
                                        return;
                                    }
                                }
                                return;
                            }
                            if (atomicLong.get() != -1) {
                                if (j == 0) {
                                    Object obj = objArr2[i2];
                                    if (NotificationLite.isComplete(obj)) {
                                        cVar.onComplete();
                                        return;
                                    } else if (NotificationLite.isError(obj)) {
                                        cVar.onError(NotificationLite.getError(obj));
                                        return;
                                    }
                                }
                                if (i4 != 0) {
                                    BackpressureHelper.producedCancel(atomicLong, (long) i4);
                                }
                                this.index = i3;
                                this.currentIndexInBuffer = i2;
                                this.currentBuffer = objArr2;
                            } else {
                                return;
                            }
                        }
                        i2 = addAndGet(-i);
                        if (i2 != 0) {
                            i = i2;
                        } else {
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }

    public FlowableCache(Flowable<T> flowable, int i) {
        super(flowable);
        this.state = new CacheState(flowable, i);
    }

    protected void subscribeActual(c<? super T> cVar) {
        d replaySubscription = new ReplaySubscription(cVar, this.state);
        this.state.addChild(replaySubscription);
        cVar.onSubscribe(replaySubscription);
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            this.state.connect();
        }
    }

    boolean isConnected() {
        return this.state.isConnected;
    }

    boolean hasSubscribers() {
        return ((ReplaySubscription[]) this.state.subscribers.get()).length != 0;
    }

    int cachedEventCount() {
        return this.state.size();
    }
}
