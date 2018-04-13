package cn.tatagou.sdk.util;

import android.os.Handler;
import android.os.SystemClock;

public abstract class e {
    private final long a;
    private final long b;
    private long c;
    private boolean d = false;
    private Handler e = new Handler(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r9) {
            /*
            r8 = this;
            r6 = 0;
            r2 = r8.a;
            monitor-enter(r2);
            r0 = r8.a;	 Catch:{ all -> 0x0025 }
            r0 = r0.d;	 Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x000f;
        L_0x000d:
            monitor-exit(r2);	 Catch:{ all -> 0x0025 }
        L_0x000e:
            return;
        L_0x000f:
            r0 = r8.a;	 Catch:{ all -> 0x0025 }
            r0 = r0.c;	 Catch:{ all -> 0x0025 }
            r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0025 }
            r0 = r0 - r4;
            r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
            if (r3 > 0) goto L_0x0028;
        L_0x001e:
            r0 = r8.a;	 Catch:{ all -> 0x0025 }
            r0.onFinish();	 Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r2);	 Catch:{ all -> 0x0025 }
            goto L_0x000e;
        L_0x0025:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0025 }
            throw r0;
        L_0x0028:
            r3 = r8.a;	 Catch:{ all -> 0x0025 }
            r4 = r3.b;	 Catch:{ all -> 0x0025 }
            r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r3 >= 0) goto L_0x003b;
        L_0x0032:
            r3 = 1;
            r3 = r8.obtainMessage(r3);	 Catch:{ all -> 0x0025 }
            r8.sendMessageDelayed(r3, r0);	 Catch:{ all -> 0x0025 }
            goto L_0x0023;
        L_0x003b:
            r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0025 }
            r3 = r8.a;	 Catch:{ all -> 0x0025 }
            r3.onTick(r0);	 Catch:{ all -> 0x0025 }
            r0 = r8.a;	 Catch:{ all -> 0x0025 }
            r0 = r0.b;	 Catch:{ all -> 0x0025 }
            r0 = r0 + r4;
            r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0025 }
            r0 = r0 - r4;
        L_0x0050:
            r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
            if (r3 >= 0) goto L_0x005c;
        L_0x0054:
            r3 = r8.a;	 Catch:{ all -> 0x0025 }
            r4 = r3.b;	 Catch:{ all -> 0x0025 }
            r0 = r0 + r4;
            goto L_0x0050;
        L_0x005c:
            r3 = 1;
            r3 = r8.obtainMessage(r3);	 Catch:{ all -> 0x0025 }
            r8.sendMessageDelayed(r3, r0);	 Catch:{ all -> 0x0025 }
            goto L_0x0023;
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.tatagou.sdk.util.e.1.handleMessage(android.os.Message):void");
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j);

    public e(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public final synchronized void cancel() {
        this.d = true;
        this.e.removeMessages(1);
    }

    public final synchronized e start() {
        e eVar;
        this.d = false;
        if (this.a <= 0) {
            onFinish();
            eVar = this;
        } else {
            this.c = SystemClock.elapsedRealtime() + this.a;
            this.e.sendMessage(this.e.obtainMessage(1));
            eVar = this;
        }
        return eVar;
    }
}
