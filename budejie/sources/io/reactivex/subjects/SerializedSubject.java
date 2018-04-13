package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList.NonThrowingPredicate;
import io.reactivex.internal.util.NotificationLite;

final class SerializedSubject<T> extends Subject<T> implements NonThrowingPredicate<Object> {
    final Subject<T> actual;
    volatile boolean done;
    boolean emitting;
    AppendOnlyLinkedArrayList<Object> queue;

    SerializedSubject(Subject<T> subject) {
        this.actual = subject;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        this.actual.subscribe(observer);
    }

    public void onSubscribe(Disposable disposable) {
        Object obj = 1;
        if (!this.done) {
            synchronized (this) {
                if (!this.done) {
                    if (this.emitting) {
                        AppendOnlyLinkedArrayList appendOnlyLinkedArrayList = this.queue;
                        if (appendOnlyLinkedArrayList == null) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList(4);
                            this.queue = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.add(NotificationLite.disposable(disposable));
                        return;
                    }
                    this.emitting = true;
                    obj = null;
                }
            }
        }
        if (obj != null) {
            disposable.dispose();
            return;
        }
        this.actual.onSubscribe(disposable);
        emitLoop();
    }

    public void onNext(T t) {
        if (!this.done) {
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
    public void onError(java.lang.Throwable r3) {
        /*
        r2 = this;
        r0 = 1;
        r1 = r2.done;
        if (r1 == 0) goto L_0x0009;
    L_0x0005:
        io.reactivex.plugins.RxJavaPlugins.onError(r3);
    L_0x0008:
        return;
    L_0x0009:
        monitor-enter(r2);
        r1 = r2.done;	 Catch:{ all -> 0x0031 }
        if (r1 == 0) goto L_0x0015;
    L_0x000e:
        monitor-exit(r2);	 Catch:{ all -> 0x0031 }
        if (r0 == 0) goto L_0x0039;
    L_0x0011:
        io.reactivex.plugins.RxJavaPlugins.onError(r3);
        goto L_0x0008;
    L_0x0015:
        r0 = 1;
        r2.done = r0;	 Catch:{ all -> 0x0031 }
        r0 = r2.emitting;	 Catch:{ all -> 0x0031 }
        if (r0 == 0) goto L_0x0034;
    L_0x001c:
        r0 = r2.queue;	 Catch:{ all -> 0x0031 }
        if (r0 != 0) goto L_0x0028;
    L_0x0020:
        r0 = new io.reactivex.internal.util.AppendOnlyLinkedArrayList;	 Catch:{ all -> 0x0031 }
        r1 = 4;
        r0.<init>(r1);	 Catch:{ all -> 0x0031 }
        r2.queue = r0;	 Catch:{ all -> 0x0031 }
    L_0x0028:
        r1 = io.reactivex.internal.util.NotificationLite.error(r3);	 Catch:{ all -> 0x0031 }
        r0.setFirst(r1);	 Catch:{ all -> 0x0031 }
        monitor-exit(r2);	 Catch:{ all -> 0x0031 }
        goto L_0x0008;
    L_0x0031:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0031 }
        throw r0;
    L_0x0034:
        r0 = 0;
        r1 = 1;
        r2.emitting = r1;	 Catch:{ all -> 0x0031 }
        goto L_0x000e;
    L_0x0039:
        r0 = r2.actual;
        r0.onError(r3);
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.SerializedSubject.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.done) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                if (this.emitting) {
                    AppendOnlyLinkedArrayList appendOnlyLinkedArrayList = this.queue;
                    if (appendOnlyLinkedArrayList == null) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList(4);
                        this.queue = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.add(NotificationLite.complete());
                    return;
                }
                this.emitting = true;
                this.actual.onComplete();
            }
        }
    }

    void emitLoop() {
        while (true) {
            AppendOnlyLinkedArrayList appendOnlyLinkedArrayList;
            synchronized (this) {
                appendOnlyLinkedArrayList = this.queue;
                if (appendOnlyLinkedArrayList == null) {
                    this.emitting = false;
                    return;
                }
                this.queue = null;
            }
            appendOnlyLinkedArrayList.forEachWhile(this);
        }
    }

    public boolean test(Object obj) {
        return NotificationLite.acceptFull(obj, this.actual);
    }

    public boolean hasObservers() {
        return this.actual.hasObservers();
    }

    public boolean hasThrowable() {
        return this.actual.hasThrowable();
    }

    public Throwable getThrowable() {
        return this.actual.getThrowable();
    }

    public boolean hasComplete() {
        return this.actual.hasComplete();
    }
}
