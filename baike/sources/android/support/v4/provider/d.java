package android.support.v4.provider;

import android.support.v4.provider.SelfDestructiveThread.ReplyCallback;

final class d implements ReplyCallback<a> {
    final /* synthetic */ String a;

    d(String str) {
        this.a = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReply(android.support.v4.provider.FontsContractCompat.a r5) {
        /*
        r4 = this;
        r1 = android.support.v4.provider.FontsContractCompat.c;
        monitor-enter(r1);
        r0 = android.support.v4.provider.FontsContractCompat.d;	 Catch:{ all -> 0x0034 }
        r2 = r4.a;	 Catch:{ all -> 0x0034 }
        r0 = r0.get(r2);	 Catch:{ all -> 0x0034 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x0034 }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r1);	 Catch:{ all -> 0x0034 }
    L_0x0014:
        return;
    L_0x0015:
        r2 = android.support.v4.provider.FontsContractCompat.d;	 Catch:{ all -> 0x0034 }
        r3 = r4.a;	 Catch:{ all -> 0x0034 }
        r2.remove(r3);	 Catch:{ all -> 0x0034 }
        monitor-exit(r1);	 Catch:{ all -> 0x0034 }
        r1 = 0;
        r2 = r1;
    L_0x0021:
        r1 = r0.size();
        if (r2 >= r1) goto L_0x0014;
    L_0x0027:
        r1 = r0.get(r2);
        r1 = (android.support.v4.provider.SelfDestructiveThread.ReplyCallback) r1;
        r1.onReply(r5);
        r1 = r2 + 1;
        r2 = r1;
        goto L_0x0021;
    L_0x0034:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0034 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.d.onReply(android.support.v4.provider.FontsContractCompat$a):void");
    }
}
