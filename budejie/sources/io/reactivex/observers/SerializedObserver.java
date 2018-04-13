package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.NotificationLite;

public final class SerializedObserver<T> implements Observer<T>, Disposable {
    static final int QUEUE_LINK_SIZE = 4;
    final Observer<? super T> actual;
    final boolean delayError;
    volatile boolean done;
    boolean emitting;
    AppendOnlyLinkedArrayList<Object> queue;
    Disposable s;

    public SerializedObserver(@NonNull Observer<? super T> observer) {
        this(observer, false);
    }

    public SerializedObserver(@NonNull Observer<? super T> observer, boolean z) {
        this.actual = observer;
        this.delayError = z;
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        if (DisposableHelper.validate(this.s, disposable)) {
            this.s = disposable;
            this.actual.onSubscribe(this);
        }
    }

    public void dispose() {
        this.s.dispose();
    }

    public boolean isDisposed() {
        return this.s.isDisposed();
    }

    public void onNext(@NonNull T t) {
        if (!this.done) {
            if (t == null) {
                this.s.dispose();
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
    public void onError(@io.reactivex.annotations.NonNull java.lang.Throwable r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.observers.SerializedObserver.onError(java.lang.Throwable):void");
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
}
