package com.umeng.commonsdk.proguard;

import android.content.Context;

final class bb implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ Throwable b;

    bb(Context context, Throwable th) {
        this.a = context;
        this.b = th;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r8 = this;
        r1 = com.umeng.commonsdk.proguard.b.b;	 Catch:{ Throwable -> 0x00c9 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x00c9 }
        r0 = r8.a;	 Catch:{ all -> 0x00c6 }
        if (r0 == 0) goto L_0x00c4;
    L_0x0009:
        r0 = r8.b;	 Catch:{ all -> 0x00c6 }
        if (r0 == 0) goto L_0x00c4;
    L_0x000d:
        r0 = com.umeng.commonsdk.proguard.b.a;	 Catch:{ all -> 0x00c6 }
        if (r0 != 0) goto L_0x00c4;
    L_0x0013:
        r0 = 1;
        com.umeng.commonsdk.proguard.b.a = r0;	 Catch:{ all -> 0x00c6 }
        r0 = "walle-crash";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00c6 }
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c6 }
        r4.<init>();	 Catch:{ all -> 0x00c6 }
        r5 = "report thread is ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00c6 }
        r5 = com.umeng.commonsdk.proguard.b.a;	 Catch:{ all -> 0x00c6 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00c6 }
        r4 = r4.toString();	 Catch:{ all -> 0x00c6 }
        r2[r3] = r4;	 Catch:{ all -> 0x00c6 }
        com.umeng.commonsdk.statistics.common.e.a(r0, r2);	 Catch:{ all -> 0x00c6 }
        r0 = r8.b;	 Catch:{ all -> 0x00c6 }
        r0 = com.umeng.commonsdk.proguard.c.a(r0);	 Catch:{ all -> 0x00c6 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x00c6 }
        if (r2 != 0) goto L_0x00c4;
    L_0x0045:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c6 }
        r2.<init>();	 Catch:{ all -> 0x00c6 }
        r3 = r8.a;	 Catch:{ all -> 0x00c6 }
        r3 = r3.getFilesDir();	 Catch:{ all -> 0x00c6 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x00c6 }
        r3 = "/";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00c6 }
        r3 = "stateless";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00c6 }
        r3 = "/";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00c6 }
        r3 = "umpx_internal";
        r3 = r3.getBytes();	 Catch:{ all -> 0x00c6 }
        r4 = 0;
        r3 = android.util.Base64.encodeToString(r3, r4);	 Catch:{ all -> 0x00c6 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x00c6 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c6 }
        r3 = r8.a;	 Catch:{ all -> 0x00c6 }
        r4 = 10;
        com.umeng.commonsdk.stateless.f.a(r3, r2, r4);	 Catch:{ all -> 0x00c6 }
        r2 = new com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;	 Catch:{ all -> 0x00c6 }
        r2.<init>();	 Catch:{ all -> 0x00c6 }
        r3 = r8.a;	 Catch:{ all -> 0x00c6 }
        r3 = r2.buildSLBaseHeader(r3);	 Catch:{ all -> 0x00c6 }
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00cb }
        r4.<init>();	 Catch:{ JSONException -> 0x00cb }
        r5 = "content";
        r4.put(r5, r0);	 Catch:{ JSONException -> 0x00cb }
        r0 = "ts";
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x00cb }
        r4.put(r0, r6);	 Catch:{ JSONException -> 0x00cb }
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00cb }
        r0.<init>();	 Catch:{ JSONException -> 0x00cb }
        r5 = "crash";
        r0.put(r5, r4);	 Catch:{ JSONException -> 0x00cb }
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00cb }
        r4.<init>();	 Catch:{ JSONException -> 0x00cb }
        r5 = "tp";
        r4.put(r5, r0);	 Catch:{ JSONException -> 0x00cb }
        r0 = r8.a;	 Catch:{ JSONException -> 0x00cb }
        r5 = "umpx_internal";
        r0 = r2.buildSLEnvelope(r0, r3, r4, r5);	 Catch:{ JSONException -> 0x00cb }
        if (r0 == 0) goto L_0x00c4;
    L_0x00bc:
        r2 = "exception";
        r0 = r0.has(r2);	 Catch:{ JSONException -> 0x00cb }
        if (r0 != 0) goto L_0x00c4;
    L_0x00c4:
        monitor-exit(r1);	 Catch:{ all -> 0x00c6 }
    L_0x00c5:
        return;
    L_0x00c6:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00c6 }
        throw r0;	 Catch:{ Throwable -> 0x00c9 }
    L_0x00c9:
        r0 = move-exception;
        goto L_0x00c5;
    L_0x00cb:
        r0 = move-exception;
        goto L_0x00c4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.proguard.bb.run():void");
    }
}
