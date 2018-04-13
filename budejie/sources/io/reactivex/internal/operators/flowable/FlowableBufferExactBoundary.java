package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.Collection;
import java.util.concurrent.Callable;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableBufferExactBoundary<T, U extends Collection<? super T>, B> extends AbstractFlowableWithUpstream<T, U> {
    final b<B> boundary;
    final Callable<U> bufferSupplier;

    static final class BufferBoundarySubscriber<T, U extends Collection<? super T>, B> extends DisposableSubscriber<B> {
        final BufferExactBoundarySubscriber<T, U, B> parent;

        BufferBoundarySubscriber(BufferExactBoundarySubscriber<T, U, B> bufferExactBoundarySubscriber) {
            this.parent = bufferExactBoundarySubscriber;
        }

        public void onNext(B b) {
            this.parent.next();
        }

        public void onError(Throwable th) {
            this.parent.onError(th);
        }

        public void onComplete() {
            this.parent.onComplete();
        }
    }

    static final class BufferExactBoundarySubscriber<T, U extends Collection<? super T>, B> extends QueueDrainSubscriber<T, U, U> implements FlowableSubscriber<T>, Disposable, d {
        final b<B> boundary;
        U buffer;
        final Callable<U> bufferSupplier;
        Disposable other;
        d s;

        BufferExactBoundarySubscriber(c<? super U> cVar, Callable<U> callable, b<B> bVar) {
            super(cVar, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.boundary = bVar;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    Object bufferBoundarySubscriber = new BufferBoundarySubscriber(this);
                    this.other = bufferBoundarySubscriber;
                    this.actual.onSubscribe(this);
                    if (!this.cancelled) {
                        dVar.request(Clock.MAX_TIME);
                        this.boundary.subscribe(bufferBoundarySubscriber);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    dVar.cancel();
                    EmptySubscription.error(th, this.actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                Collection collection = this.buffer;
                if (collection == null) {
                    return;
                }
                collection.add(t);
            }
        }

        public void onError(Throwable th) {
            cancel();
            this.actual.onError(th);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onComplete() {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.buffer;	 Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x0022 }
        L_0x0006:
            return;
        L_0x0007:
            r1 = 0;
            r3.buffer = r1;	 Catch:{ all -> 0x0022 }
            monitor-exit(r3);	 Catch:{ all -> 0x0022 }
            r1 = r3.queue;
            r1.offer(r0);
            r0 = 1;
            r3.done = r0;
            r0 = r3.enter();
            if (r0 == 0) goto L_0x0006;
        L_0x0019:
            r0 = r3.queue;
            r1 = r3.actual;
            r2 = 0;
            io.reactivex.internal.util.QueueDrainHelper.drainMaxLoop(r0, r1, r2, r3, r3);
            goto L_0x0006;
        L_0x0022:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0022 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferExactBoundary.BufferExactBoundarySubscriber.onComplete():void");
        }

        public void request(long j) {
            requested(j);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.other.dispose();
                this.s.cancel();
                if (enter()) {
                    this.queue.clear();
                }
            }
        }

        void next() {
            try {
                Collection collection = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                synchronized (this) {
                    Collection collection2 = this.buffer;
                    if (collection2 == null) {
                        return;
                    }
                    this.buffer = collection;
                    fastPathEmitMax(collection2, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.actual.onError(th);
            }
        }

        public void dispose() {
            cancel();
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public boolean accept(c<? super U> cVar, U u) {
            this.actual.onNext(u);
            return true;
        }
    }

    public FlowableBufferExactBoundary(Flowable<T> flowable, b<B> bVar, Callable<U> callable) {
        super(flowable);
        this.boundary = bVar;
        this.bufferSupplier = callable;
    }

    protected void subscribeActual(c<? super U> cVar) {
        this.source.subscribe(new BufferExactBoundarySubscriber(new SerializedSubscriber(cVar), this.bufferSupplier, this.boundary));
    }
}
