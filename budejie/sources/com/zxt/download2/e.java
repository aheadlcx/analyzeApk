package com.zxt.download2;

import android.util.Log;
import com.budejie.www.service.LocalTask;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class e extends LocalTask<Void, Integer, Void> {
    private f a;
    private g b;
    private volatile boolean c = false;
    private volatile boolean d = false;
    private int e = 0;

    protected /* synthetic */ void b(Object[] objArr) {
        a((Integer[]) objArr);
    }

    e(g gVar, f fVar) {
        this.a = fVar;
        this.b = gVar;
        Log.d("DownloadOperator", "file path : " + this.a.d());
        Log.d("DownloadOperator", "file name : " + this.a.c());
        Log.d("DownloadOperator", "download url : " + this.a.getUrl());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.Void a(java.lang.Void... r16) {
        /*
        r15 = this;
        r0 = r15.c;
        if (r0 == 0) goto L_0x0006;
    L_0x0004:
        r0 = 0;
    L_0x0005:
        return r0;
    L_0x0006:
        r15.g();
        r0 = r15.a;
        r1 = com.zxt.download2.DownloadState.DOWNLOADING;
        r0.a(r1);
        r0 = r15.b;
        r1 = r15.a;
        r0.d(r1);
        r0 = r15.b;
        r1 = r15.a;
        r0 = r0.g(r1);
        r1 = r0.iterator();
    L_0x0023:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0033;
    L_0x0029:
        r0 = r1.next();
        r0 = (com.zxt.download2.b) r0;
        r0.b();
        goto L_0x0023;
    L_0x0033:
        r1 = 0;
        r4 = 0;
        r2 = 0;
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0339, all -> 0x032e }
        r3 = r15.a;	 Catch:{ Exception -> 0x0339, all -> 0x032e }
        r3 = r3.getUrl();	 Catch:{ Exception -> 0x0339, all -> 0x032e }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0339, all -> 0x032e }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0339, all -> 0x032e }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0339, all -> 0x032e }
        r1 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = "Accept-Encoding";
        r3 = "musixmatch";
        r0.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = "GET";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = "Range";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r3.<init>();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = "bytes=";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r15.a;	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r5.e();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = "-";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r15.a;	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r5.f();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r0.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = "User-Agent";
        r3 = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)";
        r0.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r3 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1.<init>();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r15.a;	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r5.d();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = "/";
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r15.a;	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = r5.c();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r5 = "rwd";
        r3.<init>(r1, r5);	 Catch:{ Exception -> 0x033f, all -> 0x0332 }
        r1 = r15.a;	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r1 = r1.e();	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r4 = (long) r1;	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r3.seek(r4);	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r1 = r15.a;	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r1 = r1.e();	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r15.e = r1;	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r1 = r15.a;	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r1 = r1.f();	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r4 = r15.e;	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r2 = r0.getInputStream();	 Catch:{ Exception -> 0x0346, all -> 0x0230 }
        r5 = "DownloadOperator";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6.<init>();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r7 = "downloadListeners size=";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r7 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r7 = r7.g(r8);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r7 = r7.size();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        android.util.Log.d(r5, r6);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = "DownloadOperator";
        r6 = "start writing data to file.";
        android.util.Log.i(r5, r6);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r5 = new byte[r5];	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
    L_0x0108:
        r8 = r2.read(r5);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = -1;
        if (r8 == r9) goto L_0x0272;
    L_0x010f:
        r9 = r15.c;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        if (r9 == 0) goto L_0x01c7;
    L_0x0113:
        r1 = "DownloadOperator";
        r4 = "pause download, exit download loop.";
        android.util.Log.i(r1, r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = com.zxt.download2.DownloadState.PAUSE;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.a(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.a(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r1.g(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r1.iterator();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
    L_0x0134:
        r1 = r4.hasNext();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        if (r1 == 0) goto L_0x01a8;
    L_0x013a:
        r1 = r4.next();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = (com.zxt.download2.b) r1;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.c();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        goto L_0x0134;
    L_0x0144:
        r1 = move-exception;
        r14 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r0;
        r0 = r14;
    L_0x014a:
        r4 = "DownloadOperator";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0193 }
        r5.<init>();	 Catch:{ all -> 0x0193 }
        r6 = "download exception : ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0193 }
        r6 = r0.getMessage();	 Catch:{ all -> 0x0193 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0193 }
        r5 = r5.toString();	 Catch:{ all -> 0x0193 }
        android.util.Log.e(r4, r5);	 Catch:{ all -> 0x0193 }
        r0.printStackTrace();	 Catch:{ all -> 0x0193 }
        r0 = r15.a;	 Catch:{ all -> 0x0193 }
        r4 = com.zxt.download2.DownloadState.FAILED;	 Catch:{ all -> 0x0193 }
        r0.a(r4);	 Catch:{ all -> 0x0193 }
        r0 = r15.a;	 Catch:{ all -> 0x0193 }
        r4 = r15.e;	 Catch:{ all -> 0x0193 }
        r0.a(r4);	 Catch:{ all -> 0x0193 }
        r0 = r15.b;	 Catch:{ all -> 0x0193 }
        r4 = r15.a;	 Catch:{ all -> 0x0193 }
        r0 = r0.g(r4);	 Catch:{ all -> 0x0193 }
        r4 = r0.iterator();	 Catch:{ all -> 0x0193 }
    L_0x0183:
        r0 = r4.hasNext();	 Catch:{ all -> 0x0193 }
        if (r0 == 0) goto L_0x030c;
    L_0x0189:
        r0 = r4.next();	 Catch:{ all -> 0x0193 }
        r0 = (com.zxt.download2.b) r0;	 Catch:{ all -> 0x0193 }
        r0.e();	 Catch:{ all -> 0x0193 }
        goto L_0x0183;
    L_0x0193:
        r0 = move-exception;
        r14 = r1;
        r1 = r3;
        r3 = r2;
        r2 = r14;
    L_0x0198:
        if (r2 == 0) goto L_0x019d;
    L_0x019a:
        r2.close();	 Catch:{ Exception -> 0x0328 }
    L_0x019d:
        if (r3 == 0) goto L_0x01a2;
    L_0x019f:
        r3.close();	 Catch:{ Exception -> 0x0328 }
    L_0x01a2:
        if (r1 == 0) goto L_0x01a7;
    L_0x01a4:
        r1.disconnect();	 Catch:{ Exception -> 0x0328 }
    L_0x01a7:
        throw r0;
    L_0x01a8:
        r1 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.d(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = 0;
        if (r2 == 0) goto L_0x01b5;
    L_0x01b2:
        r2.close();	 Catch:{ Exception -> 0x01c2 }
    L_0x01b5:
        if (r3 == 0) goto L_0x01ba;
    L_0x01b7:
        r3.close();	 Catch:{ Exception -> 0x01c2 }
    L_0x01ba:
        if (r0 == 0) goto L_0x01bf;
    L_0x01bc:
        r0.disconnect();	 Catch:{ Exception -> 0x01c2 }
    L_0x01bf:
        r0 = r1;
        goto L_0x0005;
    L_0x01c2:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01bf;
    L_0x01c7:
        r9 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = r9 + r8;
        r15.e = r9;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = "DownloadOperator";
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10.<init>();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r11 = "length=";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = r10.append(r8);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = r10.toString();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        android.util.Log.d(r9, r10);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = 0;
        r3.write(r5, r9, r8);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = r9.e();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r8 - r9;
        r9 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
        if (r8 <= r9) goto L_0x0236;
    L_0x01f6:
        r8 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8.a(r9);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8.d(r9);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r8 - r4;
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r12 = 1;
        r10 = r10 + r12;
        r10 = r10 - r6;
        r9 = (int) r10;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r8 / r9;
        r9 = 3;
        r9 = new java.lang.Integer[r9];	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = 0;
        r11 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r11 = java.lang.Integer.valueOf(r11);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = 1;
        r11 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = 2;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9[r10] = r8;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r15.d(r9);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        goto L_0x0108;
    L_0x0230:
        r1 = move-exception;
        r14 = r1;
        r1 = r0;
        r0 = r14;
        goto L_0x0198;
    L_0x0236:
        r8 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r1 - r8;
        r9 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
        if (r8 >= r9) goto L_0x0108;
    L_0x023f:
        r8 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8.a(r9);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r8 - r4;
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r12 = 1;
        r10 = r10 + r12;
        r10 = r10 - r6;
        r9 = (int) r10;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r8 = r8 / r9;
        r9 = 3;
        r9 = new java.lang.Integer[r9];	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = 0;
        r11 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r11 = java.lang.Integer.valueOf(r11);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = 1;
        r11 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r10 = 2;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r9[r10] = r8;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r15.d(r9);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        goto L_0x0108;
    L_0x0272:
        r0.disconnect();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = com.zxt.download2.DownloadState.FINISHED;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.a(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.e;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.a(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = "DownloadOperator";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4.<init>();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = "finished ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        android.util.Log.d(r1, r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.d(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r1.g(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r1.iterator();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
    L_0x02b0:
        r1 = r4.hasNext();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        if (r1 == 0) goto L_0x02e3;
    L_0x02b6:
        r1 = r4.next();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = (com.zxt.download2.b) r1;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5.<init>();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = r6.d();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = "/";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r6 = r6.c();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.a(r5);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        goto L_0x02b0;
    L_0x02e3:
        r1 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r1.g(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.clear();	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1 = r15.b;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r4 = r15.a;	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        r1.h(r4);	 Catch:{ Exception -> 0x0144, all -> 0x0230 }
        if (r2 == 0) goto L_0x02fa;
    L_0x02f7:
        r2.close();	 Catch:{ Exception -> 0x0307 }
    L_0x02fa:
        if (r3 == 0) goto L_0x02ff;
    L_0x02fc:
        r3.close();	 Catch:{ Exception -> 0x0307 }
    L_0x02ff:
        if (r0 == 0) goto L_0x0304;
    L_0x0301:
        r0.disconnect();	 Catch:{ Exception -> 0x0307 }
    L_0x0304:
        r0 = 0;
        goto L_0x0005;
    L_0x0307:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0304;
    L_0x030c:
        r0 = r15.b;	 Catch:{ all -> 0x0193 }
        r4 = r15.a;	 Catch:{ all -> 0x0193 }
        r0.d(r4);	 Catch:{ all -> 0x0193 }
        if (r1 == 0) goto L_0x0318;
    L_0x0315:
        r1.close();	 Catch:{ Exception -> 0x0323 }
    L_0x0318:
        if (r2 == 0) goto L_0x031d;
    L_0x031a:
        r2.close();	 Catch:{ Exception -> 0x0323 }
    L_0x031d:
        if (r3 == 0) goto L_0x0304;
    L_0x031f:
        r3.disconnect();	 Catch:{ Exception -> 0x0323 }
        goto L_0x0304;
    L_0x0323:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0304;
    L_0x0328:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x01a7;
    L_0x032e:
        r0 = move-exception;
        r3 = r4;
        goto L_0x0198;
    L_0x0332:
        r1 = move-exception;
        r3 = r4;
        r14 = r1;
        r1 = r0;
        r0 = r14;
        goto L_0x0198;
    L_0x0339:
        r0 = move-exception;
        r3 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x014a;
    L_0x033f:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x014a;
    L_0x0346:
        r1 = move-exception;
        r14 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r0;
        r0 = r14;
        goto L_0x014a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zxt.download2.e.a(java.lang.Void[]):java.lang.Void");
    }

    protected void a(Integer... numArr) {
        super.b((Object[]) numArr);
        int intValue = numArr[0].intValue();
        int intValue2 = numArr[1].intValue();
        int intValue3 = numArr[2].intValue();
        Iterator it = this.b.g(this.a).iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(intValue, intValue2, intValue3);
        }
    }

    void d() {
        Log.i("DownloadOperator", "pause download.");
        this.c = true;
        this.d = false;
    }

    void e() {
        Log.i("DownloadOperator", "pause PrePare Download.");
        this.c = true;
        this.d = false;
        this.a.a(DownloadState.PAUSE);
        this.e = this.a.e();
        this.a.a(this.e);
        Iterator it = this.b.g(this.a).iterator();
        while (it.hasNext()) {
            ((b) it.next()).c();
        }
        this.b.d(this.a);
    }

    void f() {
        Log.i("DownloadOperator", "start download.");
        this.c = false;
        this.d = false;
        c((Object[]) new Void[0]);
    }

    private void g() {
        Iterator it;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a.getUrl()).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("Accept-Encoding", "musixmatch");
            httpURLConnection.setRequestMethod("HEAD");
            int contentLength = httpURLConnection.getContentLength();
            Log.i("DownloadOperator", "total size[" + contentLength + "]");
            this.a.b(contentLength);
            httpURLConnection.disconnect();
            File file = new File(this.a.d());
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(this.a.d() + "/" + this.a.c());
            if (!file.exists()) {
                file.createNewFile();
                this.a.a(0);
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            Log.d("DownloadOperator", "fileSize:" + contentLength);
            if (contentLength > 0) {
                randomAccessFile.setLength((long) contentLength);
            }
            randomAccessFile.close();
        } catch (Throwable e) {
            Log.e("DownloadOperator", "createFile MalformedURLException", e);
        } catch (Throwable e2) {
            Log.e("DownloadOperator", "createFile FileNotFoundException", e2);
            it = this.b.g(this.a).iterator();
            while (it.hasNext()) {
                ((b) it.next()).e();
            }
        } catch (Throwable e22) {
            Log.e("DownloadOperator", "createFile IOException", e22);
            it = this.b.g(this.a).iterator();
            while (it.hasNext()) {
                ((b) it.next()).e();
            }
        }
    }
}
