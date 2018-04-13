package android.support.v4.os;

import android.os.Build.VERSION;

public final class CancellationSignal {
    private boolean a;
    private OnCancelListener b;
    private Object c;
    private boolean d;

    public interface OnCancelListener {
        void onCancel();
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this) {
            z = this.a;
        }
        return z;
    }

    public void throwIfCanceled() {
        if (isCanceled()) {
            throw new OperationCanceledException();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.a;	 Catch:{ all -> 0x0030 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x0030 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = 1;
        r3.a = r0;	 Catch:{ all -> 0x0030 }
        r0 = 1;
        r3.d = r0;	 Catch:{ all -> 0x0030 }
        r1 = r3.b;	 Catch:{ all -> 0x0030 }
        r0 = r3.c;	 Catch:{ all -> 0x0030 }
        monitor-exit(r3);	 Catch:{ all -> 0x0030 }
        if (r1 == 0) goto L_0x0017;
    L_0x0014:
        r1.onCancel();	 Catch:{ all -> 0x0033 }
    L_0x0017:
        if (r0 == 0) goto L_0x0024;
    L_0x0019:
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x0033 }
        r2 = 16;
        if (r1 < r2) goto L_0x0024;
    L_0x001f:
        r0 = (android.os.CancellationSignal) r0;	 Catch:{ all -> 0x0033 }
        r0.cancel();	 Catch:{ all -> 0x0033 }
    L_0x0024:
        monitor-enter(r3);
        r0 = 0;
        r3.d = r0;	 Catch:{ all -> 0x002d }
        r3.notifyAll();	 Catch:{ all -> 0x002d }
        monitor-exit(r3);	 Catch:{ all -> 0x002d }
        goto L_0x0006;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002d }
        throw r0;
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0030 }
        throw r0;
    L_0x0033:
        r0 = move-exception;
        monitor-enter(r3);
        r1 = 0;
        r3.d = r1;	 Catch:{ all -> 0x003d }
        r3.notifyAll();	 Catch:{ all -> 0x003d }
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        throw r0;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.CancellationSignal.cancel():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setOnCancelListener(android.support.v4.os.CancellationSignal.OnCancelListener r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        r1.a();	 Catch:{ all -> 0x0014 }
        r0 = r1.b;	 Catch:{ all -> 0x0014 }
        if (r0 != r2) goto L_0x000a;
    L_0x0008:
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
    L_0x0009:
        return;
    L_0x000a:
        r1.b = r2;	 Catch:{ all -> 0x0014 }
        r0 = r1.a;	 Catch:{ all -> 0x0014 }
        if (r0 == 0) goto L_0x0012;
    L_0x0010:
        if (r2 != 0) goto L_0x0017;
    L_0x0012:
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        goto L_0x0009;
    L_0x0014:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        throw r0;
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        r2.onCancel();
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.CancellationSignal.setOnCancelListener(android.support.v4.os.CancellationSignal$OnCancelListener):void");
    }

    public Object getCancellationSignalObject() {
        if (VERSION.SDK_INT < 16) {
            return null;
        }
        Object obj;
        synchronized (this) {
            if (this.c == null) {
                this.c = new android.os.CancellationSignal();
                if (this.a) {
                    ((android.os.CancellationSignal) this.c).cancel();
                }
            }
            obj = this.c;
        }
        return obj;
    }

    private void a() {
        while (this.d) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }
}
