package com.tencent.bugly.crashreport.crash.anr;

import android.content.Context;
import android.os.FileObserver;
import com.baidu.mobstat.Config;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public final class b {
    private AtomicInteger a = new AtomicInteger(0);
    private long b = -1;
    private final Context c;
    private final a d;
    private final w e;
    private final com.tencent.bugly.crashreport.common.strategy.a f;
    private final String g;
    private final com.tencent.bugly.crashreport.crash.b h;
    private FileObserver i;
    private boolean j = true;

    public b(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, a aVar2, w wVar, com.tencent.bugly.crashreport.crash.b bVar) {
        this.c = z.a(context);
        this.g = context.getDir("bugly", 0).getAbsolutePath();
        this.d = aVar2;
        this.e = wVar;
        this.f = aVar;
        this.h = bVar;
    }

    private CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.E = this.d.p();
            crashDetailBean.F = this.d.o();
            crashDetailBean.G = this.d.q();
            crashDetailBean.w = z.a(this.c, c.e, null);
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.h();
            crashDetailBean.f = this.d.j;
            crashDetailBean.g = this.d.w();
            crashDetailBean.m = this.d.g();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f;
            crashDetailBean.q = aVar.g;
            crashDetailBean.N = new HashMap();
            crashDetailBean.N.put("BUGLY_CR_01", aVar.e);
            int i = -1;
            if (crashDetailBean.q != null) {
                i = crashDetailBean.q.indexOf("\n");
            }
            crashDetailBean.p = i > 0 ? crashDetailBean.q.substring(0, i) : "GET_FAIL";
            crashDetailBean.r = aVar.c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
            }
            crashDetailBean.y = aVar.b;
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = "main(1)";
            crashDetailBean.H = this.d.y();
            crashDetailBean.h = this.d.v();
            crashDetailBean.i = this.d.I();
            crashDetailBean.v = aVar.d;
            crashDetailBean.K = this.d.n;
            crashDetailBean.L = this.d.a;
            crashDetailBean.M = this.d.a();
            crashDetailBean.O = this.d.F();
            crashDetailBean.P = this.d.G();
            crashDetailBean.Q = this.d.z();
            crashDetailBean.R = this.d.E();
            this.h.c(crashDetailBean);
            crashDetailBean.x = y.a();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    private static boolean a(String str, String str2, String str3) {
        BufferedWriter bufferedWriter;
        Throwable e;
        TraceFileHelper.a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.d == null || readTargetDumpInfo.d.size() <= 0) {
            x.e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (file.exists() && file.canWrite()) {
                BufferedWriter bufferedWriter2 = null;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                    try {
                        String[] strArr = (String[]) readTargetDumpInfo.d.get("main");
                        if (strArr != null && strArr.length >= 3) {
                            String str4 = strArr[0];
                            bufferedWriter.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + strArr[1] + "\n\n");
                            bufferedWriter.flush();
                        }
                        for (Entry entry : readTargetDumpInfo.d.entrySet()) {
                            if (!(((String) entry.getKey()).equals("main") || entry.getValue() == null || ((String[]) entry.getValue()).length < 3)) {
                                String str5 = ((String[]) entry.getValue())[0];
                                bufferedWriter.write("\"" + ((String) entry.getKey()) + "\" tid=" + ((String[]) entry.getValue())[2] + " :\n" + str5 + "\n" + ((String[]) entry.getValue())[1] + "\n\n");
                                bufferedWriter.flush();
                            }
                        }
                        try {
                            bufferedWriter.close();
                        } catch (Throwable e2) {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        return true;
                    } catch (IOException e3) {
                        e2 = e3;
                        bufferedWriter2 = bufferedWriter;
                        try {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                            x.e("dump trace fail %s", e2.getClass().getName() + Config.TRACE_TODAY_VISIT_SPLIT + e2.getMessage());
                            if (bufferedWriter2 != null) {
                                try {
                                    bufferedWriter2.close();
                                } catch (Throwable e22) {
                                    if (!x.a(e22)) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            e22 = th;
                            bufferedWriter = bufferedWriter2;
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (Throwable e4) {
                                    if (!x.a(e4)) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                            throw e22;
                        }
                    } catch (Throwable th2) {
                        e22 = th2;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    if (x.a(e22)) {
                        e22.printStackTrace();
                    }
                    x.e("dump trace fail %s", e22.getClass().getName() + Config.TRACE_TODAY_VISIT_SPLIT + e22.getMessage());
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    return false;
                } catch (Throwable th3) {
                    e22 = th3;
                    bufferedWriter = null;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw e22;
                }
            }
            x.e("backup file create fail %s", str2);
            return false;
        } catch (Throwable e222) {
            if (!x.a(e222)) {
                e222.printStackTrace();
            }
            x.e("backup file create error! %s  %s", e222.getClass().getName() + Config.TRACE_TODAY_VISIT_SPLIT + e222.getMessage(), str2);
            return false;
        }
    }

    public final boolean a() {
        return this.a.get() != 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r13) {
        /*
        r12 = this;
        monitor-enter(r12);
        r0 = r12.a;	 Catch:{ all -> 0x0066 }
        r0 = r0.get();	 Catch:{ all -> 0x0066 }
        if (r0 == 0) goto L_0x0013;
    L_0x0009:
        r0 = "trace started return ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0066 }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ all -> 0x0066 }
        monitor-exit(r12);	 Catch:{ all -> 0x0066 }
    L_0x0012:
        return;
    L_0x0013:
        r0 = r12.a;	 Catch:{ all -> 0x0066 }
        r1 = 1;
        r0.set(r1);	 Catch:{ all -> 0x0066 }
        monitor-exit(r12);	 Catch:{ all -> 0x0066 }
        r0 = "read trace first dump for create time!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = -1;
        r2 = 0;
        r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r13, r2);	 Catch:{ Throwable -> 0x01cd }
        if (r2 == 0) goto L_0x002d;
    L_0x002b:
        r0 = r2.c;	 Catch:{ Throwable -> 0x01cd }
    L_0x002d:
        r2 = -1;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x02f0;
    L_0x0033:
        r0 = "trace dump fail could not get time!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x01cd }
        r4 = r0;
    L_0x0040:
        r0 = r12.b;	 Catch:{ Throwable -> 0x01cd }
        r0 = r4 - r0;
        r0 = java.lang.Math.abs(r0);	 Catch:{ Throwable -> 0x01cd }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0069;
    L_0x004e:
        r0 = "should not process ANR too Fre in %d";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        r2 = 0;
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x01cd }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x0066:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x0069:
        r12.b = r4;	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.a;	 Catch:{ Throwable -> 0x01cd }
        r1 = 1;
        r0.set(r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = com.tencent.bugly.crashreport.crash.c.f;	 Catch:{ Throwable -> 0x008f }
        r1 = 0;
        r6 = com.tencent.bugly.proguard.z.a(r0, r1);	 Catch:{ Throwable -> 0x008f }
        if (r6 == 0) goto L_0x0080;
    L_0x007a:
        r0 = r6.size();	 Catch:{ Throwable -> 0x01cd }
        if (r0 > 0) goto L_0x00a3;
    L_0x0080:
        r0 = "can't get all thread skip this anr";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x008f:
        r0 = move-exception;
        com.tencent.bugly.proguard.x.a(r0);	 Catch:{ Throwable -> 0x01cd }
        r0 = "get all thread stack fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.e(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x00a3:
        r7 = r12.c;	 Catch:{ Throwable -> 0x01cd }
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0103;
    L_0x00ad:
        r0 = 0;
        r2 = r0;
    L_0x00b0:
        r0 = "to find!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = "activity";
        r0 = r7.getSystemService(r0);	 Catch:{ Throwable -> 0x01cd }
        r0 = (android.app.ActivityManager) r0;	 Catch:{ Throwable -> 0x01cd }
        r8 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r8 = r2 / r8;
        r1 = 0;
        r2 = r1;
    L_0x00c6:
        r1 = "waiting!";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r1, r3);	 Catch:{ Throwable -> 0x01cd }
        r1 = r0.getProcessesInErrorState();	 Catch:{ Throwable -> 0x01cd }
        if (r1 == 0) goto L_0x0107;
    L_0x00d4:
        r3 = r1.iterator();	 Catch:{ Throwable -> 0x01cd }
    L_0x00d8:
        r1 = r3.hasNext();	 Catch:{ Throwable -> 0x01cd }
        if (r1 == 0) goto L_0x0107;
    L_0x00de:
        r1 = r3.next();	 Catch:{ Throwable -> 0x01cd }
        r1 = (android.app.ActivityManager.ProcessErrorStateInfo) r1;	 Catch:{ Throwable -> 0x01cd }
        r7 = r1.condition;	 Catch:{ Throwable -> 0x01cd }
        r10 = 2;
        if (r7 != r10) goto L_0x00d8;
    L_0x00e9:
        r0 = "found!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r0, r2);	 Catch:{ Throwable -> 0x01cd }
    L_0x00f1:
        if (r1 != 0) goto L_0x011d;
    L_0x00f3:
        r0 = "proc state is unvisiable!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x0103:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2 = r0;
        goto L_0x00b0;
    L_0x0107:
        r10 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        com.tencent.bugly.proguard.z.b(r10);	 Catch:{ Throwable -> 0x01cd }
        r1 = r2 + 1;
        r2 = (long) r2;	 Catch:{ Throwable -> 0x01cd }
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x02ed;
    L_0x0113:
        r0 = "end!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r1 = 0;
        goto L_0x00f1;
    L_0x011d:
        r0 = r1.pid;	 Catch:{ Throwable -> 0x01cd }
        r2 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x01cd }
        if (r0 == r2) goto L_0x013a;
    L_0x0125:
        r0 = "not mind proc!";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01cd }
        r3 = 0;
        r1 = r1.processName;	 Catch:{ Throwable -> 0x01cd }
        r2[r3] = r1;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r0, r2);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x013a:
        r0 = "found visiable anr , start to process!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.a(r0, r2);	 Catch:{ Throwable -> 0x01cd }
        r2 = r12.c;	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f;	 Catch:{ Throwable -> 0x01cd }
        r0.c();	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.b();	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x016d;
    L_0x0151:
        r0 = "waiting for remote sync";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.e(r0, r3);	 Catch:{ Throwable -> 0x01cd }
        r0 = 0;
    L_0x015a:
        r3 = r12.f;	 Catch:{ Throwable -> 0x01cd }
        r3 = r3.b();	 Catch:{ Throwable -> 0x01cd }
        if (r3 != 0) goto L_0x016d;
    L_0x0162:
        r8 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        com.tencent.bugly.proguard.z.b(r8);	 Catch:{ Throwable -> 0x01cd }
        r0 = r0 + 500;
        r3 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        if (r0 < r3) goto L_0x015a;
    L_0x016d:
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x01cd }
        r2 = r2.getFilesDir();	 Catch:{ Throwable -> 0x01cd }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01cd }
        r7 = "bugly/bugly_trace_";
        r3.<init>(r7);	 Catch:{ Throwable -> 0x01cd }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01cd }
        r7 = ".txt";
        r3 = r3.append(r7);	 Catch:{ Throwable -> 0x01cd }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x01cd }
        r0.<init>(r2, r3);	 Catch:{ Throwable -> 0x01cd }
        r7 = new com.tencent.bugly.crashreport.crash.anr.a;	 Catch:{ Throwable -> 0x01cd }
        r7.<init>();	 Catch:{ Throwable -> 0x01cd }
        r7.c = r4;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.getAbsolutePath();	 Catch:{ Throwable -> 0x01cd }
        r7.d = r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r1.processName;	 Catch:{ Throwable -> 0x01cd }
        r7.a = r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r1.shortMsg;	 Catch:{ Throwable -> 0x01cd }
        r7.f = r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r1.longMsg;	 Catch:{ Throwable -> 0x01cd }
        r7.e = r0;	 Catch:{ Throwable -> 0x01cd }
        r7.b = r6;	 Catch:{ Throwable -> 0x01cd }
        if (r6 == 0) goto L_0x01f2;
    L_0x01a8:
        r0 = r6.keySet();	 Catch:{ Throwable -> 0x01cd }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x01cd }
    L_0x01b0:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x01cd }
        if (r0 == 0) goto L_0x01f2;
    L_0x01b6:
        r0 = r1.next();	 Catch:{ Throwable -> 0x01cd }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x01cd }
        r2 = "main(";
        r2 = r0.startsWith(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r2 == 0) goto L_0x01b0;
    L_0x01c4:
        r0 = r6.get(r0);	 Catch:{ Throwable -> 0x01cd }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x01cd }
        r7.g = r0;	 Catch:{ Throwable -> 0x01cd }
        goto L_0x01b0;
    L_0x01cd:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.x.a(r0);	 Catch:{ all -> 0x0264 }
        if (r1 != 0) goto L_0x01d7;
    L_0x01d4:
        r0.printStackTrace();	 Catch:{ all -> 0x0264 }
    L_0x01d7:
        r1 = "handle anr error %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0264 }
        r3 = 0;
        r0 = r0.getClass();	 Catch:{ all -> 0x0264 }
        r0 = r0.toString();	 Catch:{ all -> 0x0264 }
        r2[r3] = r0;	 Catch:{ all -> 0x0264 }
        com.tencent.bugly.proguard.x.e(r1, r2);	 Catch:{ all -> 0x0264 }
        r0 = r12.a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x01f2:
        r1 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
        r0 = 6;
        r2 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x01cd }
        r0 = 0;
        r4 = r7.c;	 Catch:{ Throwable -> 0x01cd }
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 1;
        r3 = r7.d;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 2;
        r3 = r7.a;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 3;
        r3 = r7.f;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 4;
        r3 = r7.e;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r3 = 5;
        r0 = r7.b;	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x024a;
    L_0x0219:
        r0 = 0;
    L_0x021a:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x01cd }
        r2[r3] = r0;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.c(r1, r2);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.b();	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x0251;
    L_0x022b:
        r0 = "crash report sync remote fail, will not upload to Bugly , print local for helpful!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.e(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = "ANR";
        r1 = com.tencent.bugly.proguard.z.a();	 Catch:{ Throwable -> 0x01cd }
        r2 = r7.a;	 Catch:{ Throwable -> 0x01cd }
        r3 = 0;
        r4 = r7.e;	 Catch:{ Throwable -> 0x01cd }
        r5 = 0;
        com.tencent.bugly.crashreport.crash.b.a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x01cd }
    L_0x0242:
        r0 = r12.a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x024a:
        r0 = r7.b;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.size();	 Catch:{ Throwable -> 0x01cd }
        goto L_0x021a;
    L_0x0251:
        r0 = r12.f;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.c();	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.j;	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x026c;
    L_0x025b:
        r0 = "ANR Report is closed!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x0242;
    L_0x0264:
        r0 = move-exception;
        r1 = r12.a;
        r2 = 0;
        r1.set(r2);
        throw r0;
    L_0x026c:
        r0 = "found visiable anr , start to upload!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.a(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r5 = r12.a(r7);	 Catch:{ Throwable -> 0x01cd }
        if (r5 != 0) goto L_0x0283;
    L_0x027a:
        r0 = "pack anr fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.e(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x0242;
    L_0x0283:
        r0 = com.tencent.bugly.crashreport.crash.c.a();	 Catch:{ Throwable -> 0x01cd }
        r0.a(r5);	 Catch:{ Throwable -> 0x01cd }
        r0 = r5.a;	 Catch:{ Throwable -> 0x01cd }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 < 0) goto L_0x02e4;
    L_0x0292:
        r0 = "backup anr record success!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.a(r0, r1);	 Catch:{ Throwable -> 0x01cd }
    L_0x029a:
        if (r13 == 0) goto L_0x02bf;
    L_0x029c:
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x01cd }
        r0.<init>(r13);	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x01cd }
        if (r0 == 0) goto L_0x02bf;
    L_0x02a7:
        r0 = r12.a;	 Catch:{ Throwable -> 0x01cd }
        r1 = 3;
        r0.set(r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r7.d;	 Catch:{ Throwable -> 0x01cd }
        r1 = r7.a;	 Catch:{ Throwable -> 0x01cd }
        r0 = a(r13, r0, r1);	 Catch:{ Throwable -> 0x01cd }
        if (r0 == 0) goto L_0x02bf;
    L_0x02b7:
        r0 = "backup trace success";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.a(r0, r1);	 Catch:{ Throwable -> 0x01cd }
    L_0x02bf:
        r0 = "ANR";
        r1 = com.tencent.bugly.proguard.z.a();	 Catch:{ Throwable -> 0x01cd }
        r2 = r7.a;	 Catch:{ Throwable -> 0x01cd }
        r3 = 0;
        r4 = r7.e;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.crashreport.crash.b.a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.h;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.a(r5);	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x02dd;
    L_0x02d5:
        r0 = r12.h;	 Catch:{ Throwable -> 0x01cd }
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r1 = 1;
        r0.a(r5, r2, r1);	 Catch:{ Throwable -> 0x01cd }
    L_0x02dd:
        r0 = r12.h;	 Catch:{ Throwable -> 0x01cd }
        r0.b(r5);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x0242;
    L_0x02e4:
        r0 = "backup anr record fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.x.d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x029a;
    L_0x02ed:
        r2 = r1;
        goto L_0x00c6;
    L_0x02f0:
        r4 = r0;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String):void");
    }

    private synchronized void c() {
        if (e()) {
            x.d("start when started!", new Object[0]);
        } else {
            this.i = new e(this, "/data/anr/", 8);
            try {
                this.i.startWatching();
                x.a("start anr monitor!", new Object[0]);
                this.e.a(new f(this));
            } catch (Throwable th) {
                this.i = null;
                x.d("start anr monitor failed!", new Object[0]);
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private synchronized void d() {
        if (e()) {
            try {
                this.i.stopWatching();
                this.i = null;
                x.d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                x.d("stop anr monitor failed!", new Object[0]);
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        } else {
            x.d("close when closed!", new Object[0]);
        }
    }

    private synchronized boolean e() {
        return this.i != null;
    }

    private synchronized void b(boolean z) {
        if (z) {
            c();
        } else {
            d();
        }
    }

    private synchronized boolean f() {
        return this.j;
    }

    private synchronized void c(boolean z) {
        if (this.j != z) {
            x.a("user change anr %b", Boolean.valueOf(z));
            this.j = z;
        }
    }

    public final void a(boolean z) {
        c(z);
        boolean f = f();
        com.tencent.bugly.crashreport.common.strategy.a a = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a != null) {
            f = f && a.c().g;
        }
        if (f != e()) {
            x.a("anr changed to %b", Boolean.valueOf(f));
            b(f);
        }
    }

    protected final void b() {
        long b = z.b() - c.g;
        File file = new File(this.g);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "bugly_trace_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            x.e("tomb format error delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                x.c("clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    public final synchronized void a(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.j != e()) {
                    x.d("server anr changed to %b", Boolean.valueOf(strategyBean.j));
                }
                if (!(strategyBean.j && f())) {
                    z = false;
                }
                if (z != e()) {
                    x.a("anr changed to %b", Boolean.valueOf(z));
                    b(z);
                }
            }
        }
    }
}
