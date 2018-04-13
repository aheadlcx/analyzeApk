package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.operators.flowable.FlowableReplay.ReplayBuffer;
import io.reactivex.internal.util.NotificationLite;
import java.util.ArrayList;

final class FlowableReplay$UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
    private static final long serialVersionUID = 7063189396499112664L;
    volatile int size;

    FlowableReplay$UnboundedReplayBuffer(int i) {
        super(i);
    }

    public void next(T t) {
        add(NotificationLite.next(t));
        this.size++;
    }

    public void error(Throwable th) {
        add(NotificationLite.error(th));
        this.size++;
    }

    public void complete() {
        add(NotificationLite.complete());
        this.size++;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r13) {
        /*
        r12 = this;
        monitor-enter(r13);
        r0 = r13.emitting;	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = 1;
        r13.missed = r0;	 Catch:{ all -> 0x004e }
        monitor-exit(r13);	 Catch:{ all -> 0x004e }
    L_0x0009:
        return;
    L_0x000a:
        r0 = 1;
        r13.emitting = r0;	 Catch:{ all -> 0x004e }
        monitor-exit(r13);	 Catch:{ all -> 0x004e }
        r7 = r13.child;
    L_0x0010:
        r0 = r13.isDisposed();
        if (r0 != 0) goto L_0x0009;
    L_0x0016:
        r8 = r12.size;
        r0 = r13.index();
        r0 = (java.lang.Integer) r0;
        if (r0 == 0) goto L_0x0051;
    L_0x0020:
        r0 = r0.intValue();
    L_0x0024:
        r4 = r13.get();
        r2 = 0;
        r6 = r0;
        r0 = r2;
        r2 = r4;
    L_0x002d:
        r10 = 0;
        r9 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r9 == 0) goto L_0x006a;
    L_0x0033:
        if (r6 >= r8) goto L_0x006a;
    L_0x0035:
        r9 = r12.get(r6);
        r9 = io.reactivex.internal.util.NotificationLite.accept(r9, r7);	 Catch:{ Throwable -> 0x0053 }
        if (r9 != 0) goto L_0x0009;
    L_0x003f:
        r9 = r13.isDisposed();
        if (r9 != 0) goto L_0x0009;
    L_0x0045:
        r6 = r6 + 1;
        r10 = 1;
        r2 = r2 - r10;
        r10 = 1;
        r0 = r0 + r10;
        goto L_0x002d;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x004e }
        throw r0;
    L_0x0051:
        r0 = 0;
        goto L_0x0024;
    L_0x0053:
        r0 = move-exception;
        io.reactivex.exceptions.Exceptions.throwIfFatal(r0);
        r13.dispose();
        r1 = io.reactivex.internal.util.NotificationLite.isError(r9);
        if (r1 != 0) goto L_0x0009;
    L_0x0060:
        r1 = io.reactivex.internal.util.NotificationLite.isComplete(r9);
        if (r1 != 0) goto L_0x0009;
    L_0x0066:
        r7.onError(r0);
        goto L_0x0009;
    L_0x006a:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0082;
    L_0x0070:
        r2 = java.lang.Integer.valueOf(r6);
        r13.index = r2;
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0082;
    L_0x007f:
        r13.produced(r0);
    L_0x0082:
        monitor-enter(r13);
        r0 = r13.missed;	 Catch:{ all -> 0x008d }
        if (r0 != 0) goto L_0x0090;
    L_0x0087:
        r0 = 0;
        r13.emitting = r0;	 Catch:{ all -> 0x008d }
        monitor-exit(r13);	 Catch:{ all -> 0x008d }
        goto L_0x0009;
    L_0x008d:
        r0 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x008d }
        throw r0;
    L_0x0090:
        r0 = 0;
        r13.missed = r0;	 Catch:{ all -> 0x008d }
        monitor-exit(r13);	 Catch:{ all -> 0x008d }
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay$UnboundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
    }
}
