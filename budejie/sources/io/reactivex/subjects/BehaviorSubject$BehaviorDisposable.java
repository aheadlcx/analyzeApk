package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList.NonThrowingPredicate;
import io.reactivex.internal.util.NotificationLite;

final class BehaviorSubject$BehaviorDisposable<T> implements Disposable, NonThrowingPredicate<Object> {
    final Observer<? super T> actual;
    volatile boolean cancelled;
    boolean emitting;
    boolean fastPath;
    long index;
    boolean next;
    AppendOnlyLinkedArrayList<Object> queue;
    final BehaviorSubject<T> state;

    BehaviorSubject$BehaviorDisposable(Observer<? super T> observer, BehaviorSubject<T> behaviorSubject) {
        this.actual = observer;
        this.state = behaviorSubject;
    }

    public void dispose() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.state.remove(this);
        }
    }

    public boolean isDisposed() {
        return this.cancelled;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void emitFirst() {
        /*
        r6 = this;
        r0 = 1;
        r1 = r6.cancelled;
        if (r1 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        monitor-enter(r6);
        r1 = r6.cancelled;	 Catch:{ all -> 0x000d }
        if (r1 == 0) goto L_0x0010;
    L_0x000b:
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        goto L_0x0005;
    L_0x000d:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        throw r0;
    L_0x0010:
        r1 = r6.next;	 Catch:{ all -> 0x000d }
        if (r1 == 0) goto L_0x0016;
    L_0x0014:
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        goto L_0x0005;
    L_0x0016:
        r1 = r6.state;	 Catch:{ all -> 0x000d }
        r2 = r1.readLock;	 Catch:{ all -> 0x000d }
        r2.lock();	 Catch:{ all -> 0x000d }
        r4 = r1.index;	 Catch:{ all -> 0x000d }
        r6.index = r4;	 Catch:{ all -> 0x000d }
        r1 = r1.value;	 Catch:{ all -> 0x000d }
        r1 = r1.get();	 Catch:{ all -> 0x000d }
        r2.unlock();	 Catch:{ all -> 0x000d }
        if (r1 == 0) goto L_0x003e;
    L_0x002c:
        r6.emitting = r0;	 Catch:{ all -> 0x000d }
        r0 = 1;
        r6.next = r0;	 Catch:{ all -> 0x000d }
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        if (r1 == 0) goto L_0x0005;
    L_0x0034:
        r0 = r6.test(r1);
        if (r0 != 0) goto L_0x0005;
    L_0x003a:
        r6.emitLoop();
        goto L_0x0005;
    L_0x003e:
        r0 = 0;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.BehaviorSubject$BehaviorDisposable.emitFirst():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void emitNext(java.lang.Object r5, long r6) {
        /*
        r4 = this;
        r2 = 1;
        r0 = r4.cancelled;
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r0 = r4.fastPath;
        if (r0 != 0) goto L_0x0037;
    L_0x000a:
        monitor-enter(r4);
        r0 = r4.cancelled;	 Catch:{ all -> 0x0011 }
        if (r0 == 0) goto L_0x0014;
    L_0x000f:
        monitor-exit(r4);	 Catch:{ all -> 0x0011 }
        goto L_0x0005;
    L_0x0011:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0011 }
        throw r0;
    L_0x0014:
        r0 = r4.index;	 Catch:{ all -> 0x0011 }
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 != 0) goto L_0x001c;
    L_0x001a:
        monitor-exit(r4);	 Catch:{ all -> 0x0011 }
        goto L_0x0005;
    L_0x001c:
        r0 = r4.emitting;	 Catch:{ all -> 0x0011 }
        if (r0 == 0) goto L_0x0031;
    L_0x0020:
        r0 = r4.queue;	 Catch:{ all -> 0x0011 }
        if (r0 != 0) goto L_0x002c;
    L_0x0024:
        r0 = new io.reactivex.internal.util.AppendOnlyLinkedArrayList;	 Catch:{ all -> 0x0011 }
        r1 = 4;
        r0.<init>(r1);	 Catch:{ all -> 0x0011 }
        r4.queue = r0;	 Catch:{ all -> 0x0011 }
    L_0x002c:
        r0.add(r5);	 Catch:{ all -> 0x0011 }
        monitor-exit(r4);	 Catch:{ all -> 0x0011 }
        goto L_0x0005;
    L_0x0031:
        r0 = 1;
        r4.next = r0;	 Catch:{ all -> 0x0011 }
        monitor-exit(r4);	 Catch:{ all -> 0x0011 }
        r4.fastPath = r2;
    L_0x0037:
        r4.test(r5);
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.BehaviorSubject$BehaviorDisposable.emitNext(java.lang.Object, long):void");
    }

    public boolean test(Object obj) {
        return this.cancelled || NotificationLite.accept(obj, this.actual);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void emitLoop() {
        /*
        r2 = this;
    L_0x0000:
        r0 = r2.cancelled;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        monitor-enter(r2);
        r0 = r2.queue;	 Catch:{ all -> 0x000f }
        if (r0 != 0) goto L_0x0012;
    L_0x000a:
        r0 = 0;
        r2.emitting = r0;	 Catch:{ all -> 0x000f }
        monitor-exit(r2);	 Catch:{ all -> 0x000f }
        goto L_0x0004;
    L_0x000f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x000f }
        throw r0;
    L_0x0012:
        r1 = 0;
        r2.queue = r1;	 Catch:{ all -> 0x000f }
        monitor-exit(r2);	 Catch:{ all -> 0x000f }
        r0.forEachWhile(r2);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.BehaviorSubject$BehaviorDisposable.emitLoop():void");
    }
}
