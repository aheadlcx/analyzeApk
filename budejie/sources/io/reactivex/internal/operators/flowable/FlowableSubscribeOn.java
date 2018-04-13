package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler$Worker;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableSubscribeOn<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean nonScheduledRequests;
    final Scheduler scheduler;

    static final class SubscribeOnSubscriber<T> extends AtomicReference<Thread> implements FlowableSubscriber<T>, Runnable, d {
        private static final long serialVersionUID = 8094547886072529208L;
        final c<? super T> actual;
        final boolean nonScheduledRequests;
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<d> s = new AtomicReference();
        b<T> source;
        final Scheduler$Worker worker;

        static final class Request implements Runnable {
            private final long n;
            private final d s;

            Request(d dVar, long j) {
                this.s = dVar;
                this.n = j;
            }

            public void run() {
                this.s.request(this.n);
            }
        }

        SubscribeOnSubscriber(c<? super T> cVar, Scheduler$Worker scheduler$Worker, b<T> bVar, boolean z) {
            this.actual = cVar;
            this.worker = scheduler$Worker;
            this.source = bVar;
            this.nonScheduledRequests = z;
        }

        public void run() {
            lazySet(Thread.currentThread());
            b bVar = this.source;
            this.source = null;
            bVar.subscribe(this);
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.s, dVar)) {
                long andSet = this.requested.getAndSet(0);
                if (andSet != 0) {
                    requestUpstream(andSet, dVar);
                }
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            this.actual.onComplete();
            this.worker.dispose();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                d dVar = (d) this.s.get();
                if (dVar != null) {
                    requestUpstream(j, dVar);
                    return;
                }
                BackpressureHelper.add(this.requested, j);
                dVar = (d) this.s.get();
                if (dVar != null) {
                    long andSet = this.requested.getAndSet(0);
                    if (andSet != 0) {
                        requestUpstream(andSet, dVar);
                    }
                }
            }
        }

        void requestUpstream(long j, d dVar) {
            if (this.nonScheduledRequests || Thread.currentThread() == get()) {
                dVar.request(j);
            } else {
                this.worker.schedule(new Request(dVar, j));
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.s);
            this.worker.dispose();
        }
    }

    public FlowableSubscribeOn(Flowable<T> flowable, Scheduler scheduler, boolean z) {
        super(flowable);
        this.scheduler = scheduler;
        this.nonScheduledRequests = z;
    }

    public void subscribeActual(c<? super T> cVar) {
        Scheduler$Worker createWorker = this.scheduler.createWorker();
        Object subscribeOnSubscriber = new SubscribeOnSubscriber(cVar, createWorker, this.source, this.nonScheduledRequests);
        cVar.onSubscribe(subscribeOnSubscriber);
        createWorker.schedule(subscribeOnSubscriber);
    }
}
