package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import org.a.c;
import org.a.d;

public final class SerializedSubscriber<T> implements FlowableSubscriber<T>, d {
    static final int QUEUE_LINK_SIZE = 4;
    final c<? super T> actual;
    final boolean delayError;
    volatile boolean done;
    boolean emitting;
    AppendOnlyLinkedArrayList<Object> queue;
    d subscription;

    public SerializedSubscriber(c<? super T> cVar) {
        this(cVar, false);
    }

    public SerializedSubscriber(c<? super T> cVar, boolean z) {
        this.actual = cVar;
        this.delayError = z;
    }

    public void onSubscribe(d dVar) {
        if (SubscriptionHelper.validate(this.subscription, dVar)) {
            this.subscription = dVar;
            this.actual.onSubscribe(this);
        }
    }

    public void onNext(T t) {
        if (!this.done) {
            if (t == null) {
                this.subscription.cancel();
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            synchronized (this) {
                if (this.done) {
                } else if (this.emitting) {
                    AppendOnlyLinkedArrayList appendOnlyLinkedArrayList = this.queue;
                    if (appendOnlyLinkedArrayList == null) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList(4);
                        this.queue = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.add(NotificationLite.next(t));
                } else {
                    this.emitting = true;
                    this.actual.onNext(t);
                    emitLoop();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onError(java.lang.Throwable r4) {
        /*
        r3 = this;
        r0 = 1;
        r1 = r3.done;
        if (r1 == 0) goto L_0x0009;
    L_0x0005:
        io.reactivex.plugins.RxJavaPlugins.onError(r4);
    L_0x0008:
        return;
    L_0x0009:
        monitor-enter(r3);
        r1 = r3.done;	 Catch:{ all -> 0x0035 }
        if (r1 == 0) goto L_0x0015;
    L_0x000e:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0044;
    L_0x0011:
        io.reactivex.plugins.RxJavaPlugins.onError(r4);
        goto L_0x0008;
    L_0x0015:
        r0 = r3.emitting;	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x003c;
    L_0x0019:
        r0 = 1;
        r3.done = r0;	 Catch:{ all -> 0x0035 }
        r0 = r3.queue;	 Catch:{ all -> 0x0035 }
        if (r0 != 0) goto L_0x0028;
    L_0x0020:
        r0 = new io.reactivex.internal.util.AppendOnlyLinkedArrayList;	 Catch:{ all -> 0x0035 }
        r1 = 4;
        r0.<init>(r1);	 Catch:{ all -> 0x0035 }
        r3.queue = r0;	 Catch:{ all -> 0x0035 }
    L_0x0028:
        r1 = io.reactivex.internal.util.NotificationLite.error(r4);	 Catch:{ all -> 0x0035 }
        r2 = r3.delayError;	 Catch:{ all -> 0x0035 }
        if (r2 == 0) goto L_0x0038;
    L_0x0030:
        r0.add(r1);	 Catch:{ all -> 0x0035 }
    L_0x0033:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        goto L_0x0008;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        throw r0;
    L_0x0038:
        r0.setFirst(r1);	 Catch:{ all -> 0x0035 }
        goto L_0x0033;
    L_0x003c:
        r0 = 1;
        r3.done = r0;	 Catch:{ all -> 0x0035 }
        r0 = 1;
        r3.emitting = r0;	 Catch:{ all -> 0x0035 }
        r0 = 0;
        goto L_0x000e;
    L_0x0044:
        r0 = r3.actual;
        r0.onError(r4);
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subscribers.SerializedSubscriber.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.done) {
            synchronized (this) {
                if (this.done) {
                } else if (this.emitting) {
                    AppendOnlyLinkedArrayList appendOnlyLinkedArrayList = this.queue;
                    if (appendOnlyLinkedArrayList == null) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList(4);
                        this.queue = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.add(NotificationLite.complete());
                } else {
                    this.done = true;
                    this.emitting = true;
                    this.actual.onComplete();
                }
            }
        }
    }

    void emitLoop() {
        AppendOnlyLinkedArrayList appendOnlyLinkedArrayList;
        do {
            synchronized (this) {
                appendOnlyLinkedArrayList = this.queue;
                if (appendOnlyLinkedArrayList == null) {
                    this.emitting = false;
                    return;
                }
                this.queue = null;
            }
        } while (!appendOnlyLinkedArrayList.accept(this.actual));
    }

    public void request(long j) {
        this.subscription.request(j);
    }

    public void cancel() {
        this.subscription.cancel();
    }
}
