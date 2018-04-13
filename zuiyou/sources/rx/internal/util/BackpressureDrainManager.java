package rx.internal.util;

import java.util.concurrent.atomic.AtomicLong;
import rx.f;

public final class BackpressureDrainManager extends AtomicLong implements f {
    private static final long serialVersionUID = 2826241102729529449L;
    final a actual;
    boolean emitting;
    Throwable exception;
    volatile boolean terminated;

    public interface a {
        Object a();

        void a(Throwable th);

        boolean a(Object obj);

        Object b();
    }

    public BackpressureDrainManager(a aVar) {
        this.actual = aVar;
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public void terminate() {
        this.terminated = true;
    }

    public void terminate(Throwable th) {
        if (!this.terminated) {
            this.exception = th;
            this.terminated = true;
        }
    }

    public void terminateAndDrain() {
        this.terminated = true;
        drain();
    }

    public void terminateAndDrain(Throwable th) {
        if (!this.terminated) {
            this.exception = th;
            this.terminated = true;
            drain();
        }
    }

    public void request(long j) {
        if (j != 0) {
            Object obj;
            long j2;
            long j3;
            do {
                j2 = get();
                obj = j2 == 0 ? 1 : null;
                if (j2 == Long.MAX_VALUE) {
                    break;
                } else if (j == Long.MAX_VALUE) {
                    j3 = j;
                    obj = 1;
                } else if (j2 > Long.MAX_VALUE - j) {
                    j3 = Long.MAX_VALUE;
                } else {
                    j3 = j2 + j;
                }
            } while (!compareAndSet(j2, j3));
            if (obj != null) {
                drain();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drain() {
        /*
        r13 = this;
        monitor-enter(r13);
        r0 = r13.emitting;	 Catch:{ all -> 0x0038 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r13);	 Catch:{ all -> 0x0038 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = 1;
        r13.emitting = r0;	 Catch:{ all -> 0x0038 }
        r2 = r13.terminated;	 Catch:{ all -> 0x0038 }
        monitor-exit(r13);	 Catch:{ all -> 0x0038 }
        r0 = r13.get();
        r3 = 0;
        r6 = r13.actual;	 Catch:{ all -> 0x002f }
    L_0x0014:
        r4 = 0;
        r12 = r4;
        r4 = r0;
        r1 = r12;
    L_0x0018:
        r8 = 0;
        r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r0 > 0) goto L_0x0020;
    L_0x001e:
        if (r2 == 0) goto L_0x0041;
    L_0x0020:
        if (r2 == 0) goto L_0x0069;
    L_0x0022:
        r0 = r6.a();	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x003b;
    L_0x0028:
        r3 = 1;
        r0 = r13.exception;	 Catch:{ all -> 0x002f }
        r6.a(r0);	 Catch:{ all -> 0x002f }
        goto L_0x0006;
    L_0x002f:
        r0 = move-exception;
    L_0x0030:
        if (r3 != 0) goto L_0x0037;
    L_0x0032:
        monitor-enter(r13);
        r1 = 0;
        r13.emitting = r1;	 Catch:{ all -> 0x00a2 }
        monitor-exit(r13);	 Catch:{ all -> 0x00a2 }
    L_0x0037:
        throw r0;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x0038 }
        throw r0;
    L_0x003b:
        r8 = 0;
        r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r0 != 0) goto L_0x0069;
    L_0x0041:
        monitor-enter(r13);	 Catch:{ all -> 0x002f }
        r2 = r13.terminated;	 Catch:{ all -> 0x0086 }
        r0 = r6.a();	 Catch:{ all -> 0x0086 }
        if (r0 == 0) goto L_0x007c;
    L_0x004a:
        r0 = 1;
        r4 = r0;
    L_0x004c:
        r8 = r13.get();	 Catch:{ all -> 0x0086 }
        r10 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r0 != 0) goto L_0x0089;
    L_0x0059:
        if (r4 != 0) goto L_0x007f;
    L_0x005b:
        if (r2 != 0) goto L_0x007f;
    L_0x005d:
        r1 = 1;
        r0 = 0;
        r13.emitting = r0;	 Catch:{ all -> 0x0063 }
        monitor-exit(r13);	 Catch:{ all -> 0x0063 }
        goto L_0x0006;
    L_0x0063:
        r0 = move-exception;
    L_0x0064:
        monitor-exit(r13);	 Catch:{ all -> 0x0063 }
        throw r0;	 Catch:{ all -> 0x0066 }
    L_0x0066:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0030;
    L_0x0069:
        r0 = r6.b();	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x0041;
    L_0x006f:
        r0 = r6.a(r0);	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x0006;
    L_0x0075:
        r8 = 1;
        r4 = r4 - r8;
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0018;
    L_0x007c:
        r0 = 0;
        r4 = r0;
        goto L_0x004c;
    L_0x007f:
        r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
    L_0x0084:
        monitor-exit(r13);	 Catch:{ all -> 0x0086 }
        goto L_0x0014;
    L_0x0086:
        r0 = move-exception;
        r1 = r3;
        goto L_0x0064;
    L_0x0089:
        r0 = -r1;
        r0 = (long) r0;	 Catch:{ all -> 0x0086 }
        r0 = r13.addAndGet(r0);	 Catch:{ all -> 0x0086 }
        r8 = 0;
        r5 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r5 == 0) goto L_0x0097;
    L_0x0095:
        if (r4 != 0) goto L_0x0084;
    L_0x0097:
        if (r2 == 0) goto L_0x009b;
    L_0x0099:
        if (r4 == 0) goto L_0x0084;
    L_0x009b:
        r1 = 1;
        r0 = 0;
        r13.emitting = r0;	 Catch:{ all -> 0x0063 }
        monitor-exit(r13);	 Catch:{ all -> 0x0063 }
        goto L_0x0006;
    L_0x00a2:
        r0 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x00a2 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.util.BackpressureDrainManager.drain():void");
    }
}
