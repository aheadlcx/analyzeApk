package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.internal.subscribers.InnerQueuedSubscriberSupport;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableConcatMapEager<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends b<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;

    static final class ConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, InnerQueuedSubscriberSupport<R>, d {
        private static final long serialVersionUID = -4255299542215038287L;
        final c<? super R> actual;
        volatile boolean cancelled;
        volatile InnerQueuedSubscriber<R> current;
        volatile boolean done;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final Function<? super T, ? extends b<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        final AtomicLong requested = new AtomicLong();
        d s;
        final SpscLinkedArrayQueue<InnerQueuedSubscriber<R>> subscribers;

        ConcatMapEagerDelayErrorSubscriber(c<? super R> cVar, Function<? super T, ? extends b<? extends R>> function, int i, int i2, ErrorMode errorMode) {
            this.actual = cVar;
            this.mapper = function;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = errorMode;
            this.subscribers = new SpscLinkedArrayQueue(Math.min(i2, i));
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
                dVar.request(this.maxConcurrency == Integer.MAX_VALUE ? Clock.MAX_TIME : (long) this.maxConcurrency);
            }
        }

        public void onNext(T t) {
            try {
                b bVar = (b) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                Object innerQueuedSubscriber = new InnerQueuedSubscriber(this, this.prefetch);
                if (!this.cancelled) {
                    this.subscribers.offer(innerQueuedSubscriber);
                    if (!this.cancelled) {
                        bVar.subscribe(innerQueuedSubscriber);
                        if (this.cancelled) {
                            innerQueuedSubscriber.cancel();
                            drainAndCancel();
                        }
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.s.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                drainAndCancel();
            }
        }

        void drainAndCancel() {
            if (getAndIncrement() == 0) {
                do {
                    cancelAll();
                } while (decrementAndGet() != 0);
            }
        }

        void cancelAll() {
            while (true) {
                InnerQueuedSubscriber innerQueuedSubscriber = (InnerQueuedSubscriber) this.subscribers.poll();
                if (innerQueuedSubscriber != null) {
                    innerQueuedSubscriber.cancel();
                } else {
                    return;
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void innerNext(InnerQueuedSubscriber<R> innerQueuedSubscriber, R r) {
            if (innerQueuedSubscriber.queue().offer(r)) {
                drain();
                return;
            }
            innerQueuedSubscriber.cancel();
            innerError(innerQueuedSubscriber, new MissingBackpressureException());
        }

        public void innerError(InnerQueuedSubscriber<R> innerQueuedSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                innerQueuedSubscriber.setDone();
                if (this.errorMode != ErrorMode.END) {
                    this.s.cancel();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void innerComplete(InnerQueuedSubscriber<R> innerQueuedSubscriber) {
            innerQueuedSubscriber.setDone();
            drain();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                int i = 1;
                InnerQueuedSubscriber innerQueuedSubscriber = this.current;
                c cVar = this.actual;
                ErrorMode errorMode = this.errorMode;
                while (true) {
                    long j = this.requested.get();
                    long j2 = 0;
                    if (innerQueuedSubscriber == null) {
                        if (errorMode == ErrorMode.END || ((Throwable) this.errors.get()) == null) {
                            innerQueuedSubscriber = (InnerQueuedSubscriber) this.subscribers.poll();
                            if (this.done && innerQueuedSubscriber == null) {
                                break;
                            } else if (innerQueuedSubscriber != null) {
                                this.current = innerQueuedSubscriber;
                            }
                        } else {
                            cancelAll();
                            cVar.onError(this.errors.terminate());
                            return;
                        }
                    }
                    InnerQueuedSubscriber innerQueuedSubscriber2 = innerQueuedSubscriber;
                    Object obj = null;
                    if (innerQueuedSubscriber2 != null) {
                        SimpleQueue queue = innerQueuedSubscriber2.queue();
                        if (queue != null) {
                            while (j2 != j) {
                                if (this.cancelled) {
                                    cancelAll();
                                    return;
                                } else if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.errors.get()) == null) {
                                    boolean isDone = innerQueuedSubscriber2.isDone();
                                    try {
                                        Object poll = queue.poll();
                                        Object obj2 = poll == null ? 1 : null;
                                        if (!isDone || obj2 == null) {
                                            if (obj2 != null) {
                                                break;
                                            }
                                            cVar.onNext(poll);
                                            j2++;
                                            innerQueuedSubscriber2.requestOne();
                                        } else {
                                            innerQueuedSubscriber2 = null;
                                            this.current = null;
                                            this.s.request(1);
                                            obj = 1;
                                            break;
                                        }
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.current = null;
                                        innerQueuedSubscriber2.cancel();
                                        cancelAll();
                                        cVar.onError(th);
                                        return;
                                    }
                                } else {
                                    this.current = null;
                                    innerQueuedSubscriber2.cancel();
                                    cancelAll();
                                    cVar.onError(this.errors.terminate());
                                    return;
                                }
                            }
                            if (j2 == j) {
                                if (this.cancelled) {
                                    cancelAll();
                                    return;
                                } else if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.errors.get()) == null) {
                                    boolean isDone2 = innerQueuedSubscriber2.isDone();
                                    boolean isEmpty = queue.isEmpty();
                                    if (isDone2 && isEmpty) {
                                        this.current = null;
                                        this.s.request(1);
                                        innerQueuedSubscriber = null;
                                        obj = 1;
                                        if (!(j2 == 0 || j == Clock.MAX_TIME)) {
                                            this.requested.addAndGet(-j2);
                                        }
                                        if (obj == null) {
                                            i = addAndGet(-i);
                                            if (i == 0) {
                                                return;
                                            }
                                        }
                                    }
                                } else {
                                    this.current = null;
                                    innerQueuedSubscriber2.cancel();
                                    cancelAll();
                                    cVar.onError(this.errors.terminate());
                                    return;
                                }
                            }
                        }
                    }
                    innerQueuedSubscriber = innerQueuedSubscriber2;
                    this.requested.addAndGet(-j2);
                    if (obj == null) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                }
                Throwable th2 = this.errors.terminate();
                if (th2 != null) {
                    cVar.onError(th2);
                } else {
                    cVar.onComplete();
                }
            }
        }
    }

    public FlowableConcatMapEager(Flowable<T> flowable, Function<? super T, ? extends b<? extends R>> function, int i, int i2, ErrorMode errorMode) {
        super(flowable);
        this.mapper = function;
        this.maxConcurrency = i;
        this.prefetch = i2;
        this.errorMode = errorMode;
    }

    protected void subscribeActual(c<? super R> cVar) {
        this.source.subscribe(new ConcatMapEagerDelayErrorSubscriber(cVar, this.mapper, this.maxConcurrency, this.prefetch, this.errorMode));
    }
}
