package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableFlattenIterable<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;

    static final class FlattenIterableSubscriber<T, R> extends BasicIntQueueSubscription<R> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3096000382929934955L;
        final c<? super R> actual;
        volatile boolean cancelled;
        int consumed;
        Iterator<? extends R> current;
        volatile boolean done;
        final AtomicReference<Throwable> error = new AtomicReference();
        int fusionMode;
        final int limit;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        d s;

        FlattenIterableSubscriber(c<? super R> cVar, Function<? super T, ? extends Iterable<? extends R>> function, int i) {
            this.actual = cVar;
            this.mapper = function;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                if (dVar instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) dVar;
                    int requestFusion = queueSubscription.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = queueSubscription;
                        this.actual.onSubscribe(this);
                        dVar.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.actual.onSubscribe(this);
                dVar.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode != 0 || this.queue.offer(t)) {
                    drain();
                } else {
                    onError(new MissingBackpressureException("Queue is full?!"));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done || !ExceptionHelper.addThrowable(this.error, th)) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            Throwable th;
            if (getAndIncrement() == 0) {
                boolean z;
                c cVar = this.actual;
                SimpleQueue simpleQueue = this.queue;
                if (this.fusionMode != 1) {
                    z = true;
                } else {
                    z = false;
                }
                Iterator it = this.current;
                int i = 1;
                while (true) {
                    boolean z2;
                    Iterator it2;
                    long j;
                    long j2;
                    long j3;
                    boolean z3;
                    int addAndGet;
                    if (it == null) {
                        z2 = this.done;
                        try {
                            Object poll = simpleQueue.poll();
                            if (!checkTerminated(z2, poll == null, cVar, simpleQueue)) {
                                if (poll != null) {
                                    try {
                                        it2 = ((Iterable) this.mapper.apply(poll)).iterator();
                                        if (it2.hasNext()) {
                                            this.current = it2;
                                            if (it2 != null) {
                                                j = this.requested.get();
                                                j2 = 0;
                                                while (j2 != j) {
                                                    if (!checkTerminated(this.done, false, cVar, simpleQueue)) {
                                                        try {
                                                            cVar.onNext(ObjectHelper.requireNonNull(it2.next(), "The iterator returned a null value"));
                                                            if (!checkTerminated(this.done, false, cVar, simpleQueue)) {
                                                                j2++;
                                                                try {
                                                                    if (!it2.hasNext()) {
                                                                        consumedOne(z);
                                                                        this.current = null;
                                                                        j3 = j2;
                                                                        it = null;
                                                                        break;
                                                                    }
                                                                } catch (Throwable th2) {
                                                                    Exceptions.throwIfFatal(th2);
                                                                    this.current = null;
                                                                    this.s.cancel();
                                                                    ExceptionHelper.addThrowable(this.error, th2);
                                                                    cVar.onError(ExceptionHelper.terminate(this.error));
                                                                    return;
                                                                }
                                                            }
                                                            return;
                                                        } catch (Throwable th22) {
                                                            Exceptions.throwIfFatal(th22);
                                                            this.current = null;
                                                            this.s.cancel();
                                                            ExceptionHelper.addThrowable(this.error, th22);
                                                            cVar.onError(ExceptionHelper.terminate(this.error));
                                                            return;
                                                        }
                                                    }
                                                    return;
                                                }
                                                j3 = j2;
                                                it = it2;
                                                if (j3 == j) {
                                                    z2 = this.done;
                                                    z3 = simpleQueue.isEmpty() && it == null;
                                                    if (checkTerminated(z2, z3, cVar, simpleQueue)) {
                                                        return;
                                                    }
                                                }
                                                if (!(j3 == 0 || j == Clock.MAX_TIME)) {
                                                    this.requested.addAndGet(-j3);
                                                }
                                                if (it == null) {
                                                    it2 = it;
                                                } else {
                                                    continue;
                                                }
                                            }
                                            addAndGet = addAndGet(-i);
                                            if (addAndGet != 0) {
                                                i = addAndGet;
                                                it = it2;
                                            } else {
                                                return;
                                            }
                                        }
                                        it = null;
                                        consumedOne(z);
                                    } catch (Throwable th222) {
                                        Exceptions.throwIfFatal(th222);
                                        this.s.cancel();
                                        ExceptionHelper.addThrowable(this.error, th222);
                                        cVar.onError(ExceptionHelper.terminate(this.error));
                                        return;
                                    }
                                }
                            }
                            return;
                        } catch (Throwable th2222) {
                            Exceptions.throwIfFatal(th2222);
                            this.s.cancel();
                            ExceptionHelper.addThrowable(this.error, th2222);
                            th2222 = ExceptionHelper.terminate(this.error);
                            this.current = null;
                            simpleQueue.clear();
                            cVar.onError(th2222);
                            return;
                        }
                    }
                    it2 = it;
                    if (it2 != null) {
                        j = this.requested.get();
                        j2 = 0;
                        while (j2 != j) {
                            if (!checkTerminated(this.done, false, cVar, simpleQueue)) {
                                cVar.onNext(ObjectHelper.requireNonNull(it2.next(), "The iterator returned a null value"));
                                if (!checkTerminated(this.done, false, cVar, simpleQueue)) {
                                    j2++;
                                    if (it2.hasNext()) {
                                        consumedOne(z);
                                        this.current = null;
                                        j3 = j2;
                                        it = null;
                                        break;
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        j3 = j2;
                        it = it2;
                        if (j3 == j) {
                            z2 = this.done;
                            if (!simpleQueue.isEmpty()) {
                            }
                            if (checkTerminated(z2, z3, cVar, simpleQueue)) {
                                return;
                            }
                        }
                        this.requested.addAndGet(-j3);
                        if (it == null) {
                            continue;
                        } else {
                            it2 = it;
                        }
                    }
                    addAndGet = addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                        it = it2;
                    } else {
                        return;
                    }
                }
            }
        }

        void consumedOne(boolean z) {
            if (z) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.s.request((long) i);
                    return;
                }
                this.consumed = i;
            }
        }

        boolean checkTerminated(boolean z, boolean z2, c<?> cVar, SimpleQueue<?> simpleQueue) {
            if (this.cancelled) {
                this.current = null;
                simpleQueue.clear();
                return true;
            }
            if (z) {
                if (((Throwable) this.error.get()) != null) {
                    Throwable terminate = ExceptionHelper.terminate(this.error);
                    this.current = null;
                    simpleQueue.clear();
                    cVar.onError(terminate);
                    return true;
                } else if (z2) {
                    cVar.onComplete();
                    return true;
                }
            }
            return false;
        }

        public void clear() {
            this.current = null;
            this.queue.clear();
        }

        public boolean isEmpty() {
            Iterator it = this.current;
            if (it == null) {
                return this.queue.isEmpty();
            }
            return !it.hasNext();
        }

        @Nullable
        public R poll() throws Exception {
            Iterator it = this.current;
            while (it == null) {
                Object poll = this.queue.poll();
                if (poll != null) {
                    it = ((Iterable) this.mapper.apply(poll)).iterator();
                    if (it.hasNext()) {
                        this.current = it;
                        break;
                    }
                    it = null;
                } else {
                    return null;
                }
            }
            R requireNonNull = ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.current = null;
            }
            return requireNonNull;
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0 || this.fusionMode != 1) {
                return 0;
            }
            return 1;
        }
    }

    public FlowableFlattenIterable(Flowable<T> flowable, Function<? super T, ? extends Iterable<? extends R>> function, int i) {
        super(flowable);
        this.mapper = function;
        this.prefetch = i;
    }

    public void subscribeActual(c<? super R> cVar) {
        if (this.source instanceof Callable) {
            try {
                Object call = ((Callable) this.source).call();
                if (call == null) {
                    EmptySubscription.complete(cVar);
                    return;
                }
                try {
                    FlowableFromIterable.subscribe(cVar, ((Iterable) this.mapper.apply(call)).iterator());
                    return;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    EmptySubscription.error(th, cVar);
                    return;
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                EmptySubscription.error(th2, cVar);
                return;
            }
        }
        this.source.subscribe(new FlattenIterableSubscriber(cVar, this.mapper, this.prefetch));
    }
}
