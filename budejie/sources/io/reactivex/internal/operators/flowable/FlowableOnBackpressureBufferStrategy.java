package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Action;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class FlowableOnBackpressureBufferStrategy<T> extends AbstractFlowableWithUpstream<T, T> {
    final long bufferSize;
    final Action onOverflow;
    final BackpressureOverflowStrategy strategy;

    static final class OnBackpressureBufferStrategySubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, d {
        private static final long serialVersionUID = 3240706908776709697L;
        final c<? super T> actual;
        final long bufferSize;
        volatile boolean cancelled;
        final Deque<T> deque = new ArrayDeque();
        volatile boolean done;
        Throwable error;
        final Action onOverflow;
        final AtomicLong requested = new AtomicLong();
        d s;
        final BackpressureOverflowStrategy strategy;

        OnBackpressureBufferStrategySubscriber(c<? super T> cVar, Action action, BackpressureOverflowStrategy backpressureOverflowStrategy, long j) {
            this.actual = cVar;
            this.onOverflow = action;
            this.strategy = backpressureOverflowStrategy;
            this.bufferSize = j;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
                dVar.request(Clock.MAX_TIME);
            }
        }

        public void onNext(T t) {
            Object obj = null;
            Object obj2 = 1;
            if (!this.done) {
                Deque deque = this.deque;
                synchronized (deque) {
                    if (((long) deque.size()) == this.bufferSize) {
                        switch (this.strategy) {
                            case DROP_LATEST:
                                deque.pollLast();
                                deque.offer(t);
                                break;
                            case DROP_OLDEST:
                                deque.poll();
                                deque.offer(t);
                                break;
                            default:
                                obj2 = null;
                                obj = 1;
                                break;
                        }
                    }
                    deque.offer(t);
                    obj2 = null;
                }
                if (obj2 != null) {
                    if (this.onOverflow != null) {
                        try {
                            this.onOverflow.run();
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.s.cancel();
                            onError(th);
                        }
                    }
                } else if (obj != null) {
                    this.s.cancel();
                    onError(new MissingBackpressureException());
                } else {
                    drain();
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            if (getAndIncrement() == 0) {
                clear(this.deque);
            }
        }

        void clear(Deque<T> deque) {
            synchronized (deque) {
                deque.clear();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Deque deque = this.deque;
                c cVar = this.actual;
                int i = 1;
                do {
                    boolean z;
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (this.cancelled) {
                            clear(deque);
                            return;
                        }
                        Object poll;
                        z = this.done;
                        synchronized (deque) {
                            poll = deque.poll();
                        }
                        Object obj = poll == null ? 1 : null;
                        if (z) {
                            Throwable th = this.error;
                            if (th != null) {
                                clear(deque);
                                cVar.onError(th);
                                return;
                            } else if (obj != null) {
                                cVar.onComplete();
                                return;
                            }
                        }
                        if (obj != null) {
                            break;
                        }
                        cVar.onNext(poll);
                        j2 = 1 + j2;
                    }
                    if (j2 == j) {
                        if (this.cancelled) {
                            clear(deque);
                            return;
                        }
                        boolean z2 = this.done;
                        synchronized (deque) {
                            z = deque.isEmpty();
                        }
                        if (z2) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                clear(deque);
                                cVar.onError(th2);
                                return;
                            } else if (z) {
                                cVar.onComplete();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        BackpressureHelper.produced(this.requested, j2);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    public FlowableOnBackpressureBufferStrategy(Flowable<T> flowable, long j, Action action, BackpressureOverflowStrategy backpressureOverflowStrategy) {
        super(flowable);
        this.bufferSize = j;
        this.onOverflow = action;
        this.strategy = backpressureOverflowStrategy;
    }

    protected void subscribeActual(c<? super T> cVar) {
        this.source.subscribe(new OnBackpressureBufferStrategySubscriber(cVar, this.onOverflow, this.strategy, this.bufferSize));
    }
}
