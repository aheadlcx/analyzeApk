package com.tencent.open.b;

class l implements Runnable {
    final /* synthetic */ g a;

    l(g gVar) {
        this.a = gVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r18 = this;
        r0 = r18;
        r2 = r0.a;	 Catch:{ Exception -> 0x00a3 }
        r14 = r2.d();	 Catch:{ Exception -> 0x00a3 }
        if (r14 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r2 = "openSDK_LOG.ReportManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a3 }
        r3.<init>();	 Catch:{ Exception -> 0x00a3 }
        r4 = "-->doReportVia, params: ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a3 }
        r4 = r14.toString();	 Catch:{ Exception -> 0x00a3 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a3 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00a3 }
        com.tencent.open.a.f.a(r2, r3);	 Catch:{ Exception -> 0x00a3 }
        r11 = com.tencent.open.b.e.a();	 Catch:{ Exception -> 0x00a3 }
        r10 = 0;
        r3 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a3 }
        r6 = 0;
        r4 = 0;
        r2 = 0;
    L_0x0036:
        r10 = r10 + 1;
        r12 = com.tencent.open.utils.d.a();	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        r13 = "http://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report";
        r15 = "POST";
        r15 = com.tencent.open.utils.HttpUtils.openUrl2(r12, r13, r15, r14);	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        r12 = r15.a;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        r12 = com.tencent.open.utils.i.d(r12);	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        r13 = "ret";
        r12 = r12.getInt(r13);	 Catch:{ JSONException -> 0x00ad, ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
    L_0x0050:
        if (r12 == 0) goto L_0x005a;
    L_0x0052:
        r12 = r15.a;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        r12 = android.text.TextUtils.isEmpty(r12);	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        if (r12 != 0) goto L_0x005c;
    L_0x005a:
        r3 = 1;
        r10 = r11;
    L_0x005c:
        r12 = r15.b;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        r4 = r15.c;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x012b, IOException -> 0x0104, Exception -> 0x010f }
        r6 = r12;
    L_0x0061:
        if (r10 < r11) goto L_0x0036;
    L_0x0063:
        r10 = r2;
        r13 = r3;
        r16 = r8;
        r8 = r4;
        r4 = r16;
    L_0x006a:
        r0 = r18;
        r2 = r0.a;	 Catch:{ Exception -> 0x00a3 }
        r3 = "mapp_apptrace_sdk";
        r11 = 0;
        r12 = 0;
        r2.a(r3, r4, r6, r8, r10, r11, r12);	 Catch:{ Exception -> 0x00a3 }
        if (r13 == 0) goto L_0x0118;
    L_0x0077:
        r2 = com.tencent.open.b.f.a();	 Catch:{ Exception -> 0x00a3 }
        r3 = "report_via";
        r2.b(r3);	 Catch:{ Exception -> 0x00a3 }
    L_0x0080:
        r0 = r18;
        r2 = r0.a;	 Catch:{ Exception -> 0x00a3 }
        r2 = r2.d;	 Catch:{ Exception -> 0x00a3 }
        r2.clear();	 Catch:{ Exception -> 0x00a3 }
        r2 = "openSDK_LOG.ReportManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a3 }
        r3.<init>();	 Catch:{ Exception -> 0x00a3 }
        r4 = "-->doReportVia, uploadSuccess: ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a3 }
        r3 = r3.append(r13);	 Catch:{ Exception -> 0x00a3 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00a3 }
        com.tencent.open.a.f.b(r2, r3);	 Catch:{ Exception -> 0x00a3 }
        goto L_0x000a;
    L_0x00a3:
        r2 = move-exception;
        r3 = "openSDK_LOG.ReportManager";
        r4 = "-->doReportVia, exception in serial executor.";
        com.tencent.open.a.f.b(r3, r4, r2);
        goto L_0x000a;
    L_0x00ad:
        r12 = move-exception;
        r12 = -4;
        goto L_0x0050;
    L_0x00b0:
        r2 = move-exception;
        r2 = r10;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a3 }
        r12 = 0;
        r6 = 0;
        r4 = -7;
        r10 = r2;
        r2 = r4;
        r4 = r6;
        r6 = r12;
        goto L_0x0061;
    L_0x00c0:
        r2 = move-exception;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a3 }
        r6 = 0;
        r4 = 0;
        r2 = -8;
        goto L_0x0061;
    L_0x00cb:
        r2 = move-exception;
        r6 = 0;
        r4 = 0;
        r2 = -4;
        goto L_0x0061;
    L_0x00d2:
        r2 = move-exception;
        r0 = r18;
        r2 = r0.a;	 Catch:{ Exception -> 0x00a3 }
        r2 = r2.d;	 Catch:{ Exception -> 0x00a3 }
        r2.clear();	 Catch:{ Exception -> 0x00a3 }
        r2 = "openSDK_LOG.ReportManager";
        r3 = "doReportVia, NetworkUnavailableException.";
        com.tencent.open.a.f.b(r2, r3);	 Catch:{ Exception -> 0x00a3 }
        goto L_0x000a;
    L_0x00e5:
        r10 = move-exception;
        r16 = r10;
        r10 = r3;
        r3 = r16;
    L_0x00eb:
        r3 = r3.getMessage();	 Catch:{ Exception -> 0x0129 }
        r11 = "http status code error:";
        r12 = "";
        r3 = r3.replace(r11, r12);	 Catch:{ Exception -> 0x0129 }
        r2 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x0129 }
    L_0x00fb:
        r13 = r10;
        r10 = r2;
        r16 = r8;
        r8 = r4;
        r4 = r16;
        goto L_0x006a;
    L_0x0104:
        r2 = move-exception;
        r6 = 0;
        r4 = 0;
        r2 = com.tencent.open.utils.HttpUtils.getErrorCodeFromException(r2);	 Catch:{ Exception -> 0x00a3 }
        goto L_0x0061;
    L_0x010f:
        r2 = move-exception;
        r6 = 0;
        r4 = 0;
        r2 = -6;
        r10 = r11;
        goto L_0x0061;
    L_0x0118:
        r2 = com.tencent.open.b.f.a();	 Catch:{ Exception -> 0x00a3 }
        r3 = "report_via";
        r0 = r18;
        r4 = r0.a;	 Catch:{ Exception -> 0x00a3 }
        r4 = r4.d;	 Catch:{ Exception -> 0x00a3 }
        r2.a(r3, r4);	 Catch:{ Exception -> 0x00a3 }
        goto L_0x0080;
    L_0x0129:
        r3 = move-exception;
        goto L_0x00fb;
    L_0x012b:
        r6 = move-exception;
        r10 = r3;
        r3 = r6;
        r6 = r12;
        goto L_0x00eb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.l.run():void");
    }
}
