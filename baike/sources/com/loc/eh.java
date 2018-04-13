package com.loc;

import android.content.Context;

final class eh implements Runnable {
    final /* synthetic */ Context a;

    eh(Context context) {
        this.a = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r7 = this;
        r0 = 0;
        r1 = r7.a;	 Catch:{ RejectedExecutionException -> 0x007e, Throwable -> 0x0049, all -> 0x0066 }
        r1 = 0;
        r2 = com.loc.x.d(r1);	 Catch:{ RejectedExecutionException -> 0x007e, Throwable -> 0x0049, all -> 0x0066 }
        r1 = r7.a;	 Catch:{ RejectedExecutionException -> 0x00ca, Throwable -> 0x00b5, all -> 0x009e }
        r1 = 1;
        r1 = com.loc.x.d(r1);	 Catch:{ RejectedExecutionException -> 0x00ca, Throwable -> 0x00b5, all -> 0x009e }
        r3 = r7.a;	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00bc, all -> 0x00a5 }
        r3 = 2;
        r0 = com.loc.x.d(r3);	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00bc, all -> 0x00a5 }
        r3 = r7.a;	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r2.c(r3);	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r3 = r7.a;	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r1.c(r3);	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r3 = r7.a;	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r0.c(r3);	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r3 = r7.a;	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        com.loc.bs.a(r3);	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r3 = r7.a;	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        com.loc.bq.a(r3);	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r3 = com.loc.ad.a();	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r4 = r3.size();	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        if (r4 <= 0) goto L_0x0091;
    L_0x0039:
        r3 = r3.iterator();	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
    L_0x003d:
        r4 = r3.hasNext();	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        if (r4 == 0) goto L_0x0091;
    L_0x0043:
        r3.next();	 Catch:{ RejectedExecutionException -> 0x00cd, Throwable -> 0x00c3, all -> 0x00ac }
        r4 = r7.a;	 Catch:{ Throwable -> 0x007c, RejectedExecutionException -> 0x00cd, all -> 0x00ac }
        goto L_0x003d;
    L_0x0049:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x004f:
        r4 = "Log";
        r5 = "processLog";
        com.loc.w.a(r0, r4, r5);	 Catch:{ all -> 0x00b3 }
        if (r3 == 0) goto L_0x005b;
    L_0x0058:
        r3.c();
    L_0x005b:
        if (r2 == 0) goto L_0x0060;
    L_0x005d:
        r2.c();
    L_0x0060:
        if (r1 == 0) goto L_0x0065;
    L_0x0062:
        r1.c();
    L_0x0065:
        return;
    L_0x0066:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x006c:
        if (r3 == 0) goto L_0x0071;
    L_0x006e:
        r3.c();
    L_0x0071:
        if (r2 == 0) goto L_0x0076;
    L_0x0073:
        r2.c();
    L_0x0076:
        if (r1 == 0) goto L_0x007b;
    L_0x0078:
        r1.c();
    L_0x007b:
        throw r0;
    L_0x007c:
        r4 = move-exception;
        goto L_0x003d;
    L_0x007e:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
    L_0x0081:
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        r2.c();
    L_0x0086:
        if (r1 == 0) goto L_0x008b;
    L_0x0088:
        r1.c();
    L_0x008b:
        if (r0 == 0) goto L_0x0065;
    L_0x008d:
        r0.c();
        goto L_0x0065;
    L_0x0091:
        if (r2 == 0) goto L_0x0096;
    L_0x0093:
        r2.c();
    L_0x0096:
        if (r1 == 0) goto L_0x009b;
    L_0x0098:
        r1.c();
    L_0x009b:
        if (r0 == 0) goto L_0x0065;
    L_0x009d:
        goto L_0x008d;
    L_0x009e:
        r1 = move-exception;
        r3 = r2;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x006c;
    L_0x00a5:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x006c;
    L_0x00ac:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x006c;
    L_0x00b3:
        r0 = move-exception;
        goto L_0x006c;
    L_0x00b5:
        r1 = move-exception;
        r3 = r2;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x004f;
    L_0x00bc:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x004f;
    L_0x00c3:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x004f;
    L_0x00ca:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0081;
    L_0x00cd:
        r3 = move-exception;
        goto L_0x0081;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eh.run():void");
    }
}
