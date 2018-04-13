package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Emitter;
import rx.Emitter.BackpressureMode;
import rx.b.b;
import rx.b.e;
import rx.d$a;
import rx.exceptions.MissingBackpressureException;
import rx.f;
import rx.g.d;
import rx.internal.subscriptions.CancellableSubscription;
import rx.internal.util.a.s;
import rx.internal.util.a.y;
import rx.internal.util.atomic.c;
import rx.j;
import rx.k;

public final class OnSubscribeCreate<T> implements d$a<T> {
    final b<Emitter<T>> a;
    final BackpressureMode b;

    static abstract class BaseEmitter<T> extends AtomicLong implements Emitter<T>, f, k {
        private static final long serialVersionUID = 7326289992464377023L;
        final j<? super T> actual;
        final d serial = new d();

        public BaseEmitter(j<? super T> jVar) {
            this.actual = jVar;
        }

        public void onCompleted() {
            if (!this.actual.isUnsubscribed()) {
                try {
                    this.actual.onCompleted();
                } finally {
                    this.serial.unsubscribe();
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.actual.isUnsubscribed()) {
                try {
                    this.actual.onError(th);
                } finally {
                    this.serial.unsubscribe();
                }
            }
        }

        public final void unsubscribe() {
            this.serial.unsubscribe();
            onUnsubscribed();
        }

        void onUnsubscribed() {
        }

        public final boolean isUnsubscribed() {
            return this.serial.isUnsubscribed();
        }

        public final void request(long j) {
            if (a.a(j)) {
                a.a((AtomicLong) this, j);
                onRequested();
            }
        }

        void onRequested() {
        }

        public final void setSubscription(k kVar) {
            this.serial.a(kVar);
        }

        public final void setCancellation(e eVar) {
            setSubscription(new CancellableSubscription(eVar));
        }

        public final long requested() {
            return get();
        }
    }

    static final class BufferEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final Queue<Object> queue;
        final AtomicInteger wip;

        public BufferEmitter(j<? super T> jVar, int i) {
            super(jVar);
            this.queue = y.a() ? new s(i) : new c(i);
            this.wip = new AtomicInteger();
        }

        public void onNext(T t) {
            this.queue.offer(NotificationLite.a((Object) t));
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onCompleted() {
            this.done = true;
            drain();
        }

        void onRequested() {
            drain();
        }

        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                j jVar = this.actual;
                Queue queue = this.queue;
                int i = 1;
                do {
                    boolean z;
                    Throwable th;
                    long j = get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (jVar.isUnsubscribed()) {
                            queue.clear();
                            return;
                        }
                        z = this.done;
                        Object poll = queue.poll();
                        Object obj = poll == null ? 1 : null;
                        if (z && obj != null) {
                            th = this.error;
                            if (th != null) {
                                super.onError(th);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (obj != null) {
                            break;
                        } else {
                            jVar.onNext(NotificationLite.d(poll));
                            j2 = 1 + j2;
                        }
                    }
                    if (j2 == j) {
                        if (jVar.isUnsubscribed()) {
                            queue.clear();
                            return;
                        }
                        boolean z2 = this.done;
                        z = queue.isEmpty();
                        if (z2 && z) {
                            th = this.error;
                            if (th != null) {
                                super.onError(th);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        a.b(this, j2);
                    }
                    i = this.wip.addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    static abstract class NoOverflowBaseEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        abstract void onOverflow();

        public NoOverflowBaseEmitter(j<? super T> jVar) {
            super(jVar);
        }

        public void onNext(T t) {
            if (!this.actual.isUnsubscribed()) {
                if (get() != 0) {
                    this.actual.onNext(t);
                    a.b(this, 1);
                    return;
                }
                onOverflow();
            }
        }
    }

    static final class DropEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        public DropEmitter(j<? super T> jVar) {
            super(jVar);
        }

        void onOverflow() {
        }
    }

    static final class ErrorEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;
        private boolean done;

        public ErrorEmitter(j<? super T> jVar) {
            super(jVar);
        }

        public void onNext(T t) {
            if (!this.done) {
                super.onNext(t);
            }
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                super.onCompleted();
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                rx.e.c.a(th);
                return;
            }
            this.done = true;
            super.onError(th);
        }

        void onOverflow() {
            onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
        }
    }

    static final class LatestEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<Object> queue = new AtomicReference();
        final AtomicInteger wip = new AtomicInteger();

        public LatestEmitter(j<? super T> jVar) {
            super(jVar);
        }

        public void onNext(T t) {
            this.queue.set(NotificationLite.a((Object) t));
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onCompleted() {
            this.done = true;
            drain();
        }

        void onRequested() {
            drain();
        }

        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                j jVar = this.actual;
                AtomicReference atomicReference = this.queue;
                int i = 1;
                do {
                    Object obj;
                    Throwable th;
                    long j = get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (jVar.isUnsubscribed()) {
                            atomicReference.lazySet(null);
                            return;
                        }
                        boolean z = this.done;
                        Object andSet = atomicReference.getAndSet(null);
                        obj = andSet == null ? 1 : null;
                        if (z && obj != null) {
                            th = this.error;
                            if (th != null) {
                                super.onError(th);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (obj != null) {
                            break;
                        } else {
                            jVar.onNext(NotificationLite.d(andSet));
                            j2++;
                        }
                    }
                    if (j2 == j) {
                        if (jVar.isUnsubscribed()) {
                            atomicReference.lazySet(null);
                            return;
                        }
                        boolean z2 = this.done;
                        obj = atomicReference.get() == null ? 1 : null;
                        if (z2 && obj != null) {
                            th = this.error;
                            if (th != null) {
                                super.onError(th);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        a.b(this, j2);
                    }
                    i = this.wip.addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    static final class NoneEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        public NoneEmitter(j<? super T> jVar) {
            super(jVar);
        }

        public void onNext(T t) {
            if (!this.actual.isUnsubscribed()) {
                this.actual.onNext(t);
                long j;
                do {
                    j = get();
                    if (j == 0) {
                        return;
                    }
                } while (!compareAndSet(j, j - 1));
            }
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    public void a(j<? super T> jVar) {
        Object noneEmitter;
        switch (this.b) {
            case NONE:
                noneEmitter = new NoneEmitter(jVar);
                break;
            case ERROR:
                noneEmitter = new ErrorEmitter(jVar);
                break;
            case DROP:
                noneEmitter = new DropEmitter(jVar);
                break;
            case LATEST:
                noneEmitter = new LatestEmitter(jVar);
                break;
            default:
                noneEmitter = new BufferEmitter(jVar, rx.internal.util.e.b);
                break;
        }
        jVar.add(noneEmitter);
        jVar.setProducer(noneEmitter);
        this.a.call(noneEmitter);
    }
}
