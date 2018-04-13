package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.tencent.bugly.b;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class u {
    private static u b = null;
    public boolean a = true;
    private final p c;
    private final Context d;
    private Map<Integer, Long> e = new HashMap();
    private long f;
    private long g;
    private LinkedBlockingQueue<Runnable> h = new LinkedBlockingQueue();
    private LinkedBlockingQueue<Runnable> i = new LinkedBlockingQueue();
    private final Object j = new Object();
    private String k = null;
    private byte[] l = null;
    private long m = 0;
    private byte[] n = null;
    private long o = 0;
    private String p = null;
    private long q = 0;
    private final Object r = new Object();
    private boolean s = false;
    private final Object t = new Object();
    private int u = 0;

    class a implements Runnable {
        private final Context a;
        private final Runnable b;
        private final long c;
        private /* synthetic */ u d;

        public a(u uVar, Context context) {
            this.d = uVar;
            this.a = context;
            this.b = null;
            this.c = 0;
        }

        public a(u uVar, Context context, Runnable runnable, long j) {
            this.d = uVar;
            this.a = context;
            this.b = runnable;
            this.c = j;
        }

        public final void run() {
            if (z.a(this.a, "security_info", (long) i.MIN_UPLOAD_INTERVAL)) {
                if (!this.d.e()) {
                    x.d("[UploadManager] Failed to load security info from database", new Object[0]);
                    this.d.b(false);
                }
                if (this.d.p != null) {
                    if (this.d.b()) {
                        x.c("[UploadManager] Sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        if (this.b != null) {
                            this.d.a(this.b, this.c);
                        }
                        this.d.c(0);
                        z.b(this.a, "security_info");
                        synchronized (this.d.t) {
                            this.d.s = false;
                        }
                        return;
                    }
                    x.a("[UploadManager] Session ID is expired, drop it.", new Object[0]);
                    this.d.b(true);
                }
                byte[] a = z.a(128);
                if (a == null || (a.length << 3) != 128) {
                    x.d("[UploadManager] Failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    this.d.b(false);
                    z.b(this.a, "security_info");
                    synchronized (this.d.t) {
                        this.d.s = false;
                    }
                    return;
                }
                this.d.n = a;
                x.c("[UploadManager] Execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (this.b != null) {
                    this.d.a(this.b, this.c);
                    return;
                } else {
                    this.d.c(1);
                    return;
                }
            }
            x.c("[UploadManager] Sleep %d try to lock security file again (pid=%d | tid=%d)", Integer.valueOf(5000), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            z.b((long) Config.BPLUS_DELAY_TIME);
            if (z.a((Runnable) this, "BUGLY_ASYNC_UPLOAD") == null) {
                x.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
                w a2 = w.a();
                if (a2 != null) {
                    a2.a(this);
                } else {
                    x.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
                }
            }
        }
    }

    static /* synthetic */ int b(u uVar) {
        int i = uVar.u - 1;
        uVar.u = i;
        return i;
    }

    private u(Context context) {
        this.d = context;
        this.c = p.a();
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e) {
            x.a("[UploadManager] Error: Can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.a = false;
        }
        if (this.a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/").append("fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB");
            this.k = stringBuilder.toString();
        }
    }

    public static synchronized u a(Context context) {
        u uVar;
        synchronized (u.class) {
            if (b == null) {
                b = new u(context);
            }
            uVar = b;
        }
        return uVar;
    }

    public static synchronized u a() {
        u uVar;
        synchronized (u.class) {
            uVar = b;
        }
        return uVar;
    }

    public final void a(int i, am amVar, String str, String str2, t tVar, long j, boolean z) {
        try {
            a(new v(this.d, i, amVar.g, a.a((Object) amVar), str, str2, tVar, this.a, z), true, true, j);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i, int i2, byte[] bArr, String str, String str2, t tVar, int i3, int i4, boolean z, Map<String, String> map) {
        try {
            a(new v(this.d, i, i2, bArr, str, str2, tVar, this.a, i3, i4, false, map), z, false, 0);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i, am amVar, String str, String str2, t tVar, boolean z) {
        a(i, amVar.g, a.a((Object) amVar), str, str2, tVar, 0, 0, z, null);
    }

    public final long a(boolean z) {
        long j;
        long j2 = 0;
        long b = z.b();
        int i = z ? 5 : 3;
        List a = this.c.a(i);
        if (a == null || a.size() <= 0) {
            j = z ? this.g : this.f;
        } else {
            try {
                r rVar = (r) a.get(0);
                if (rVar.e >= b) {
                    j2 = z.c(rVar.g);
                    if (i == 3) {
                        this.f = j2;
                    } else {
                        this.g = j2;
                    }
                    a.remove(rVar);
                }
                j = j2;
            } catch (Throwable th) {
                Throwable th2 = th;
                j = j2;
                x.a(th2);
            }
            if (a.size() > 0) {
                this.c.a(a);
            }
        }
        x.c("[UploadManager] Local network consume: %d KB", Long.valueOf(j / 1024));
        return j;
    }

    protected final synchronized void a(long j, boolean z) {
        int i = z ? 5 : 3;
        r rVar = new r();
        rVar.b = i;
        rVar.e = z.b();
        rVar.c = "";
        rVar.d = "";
        rVar.g = z.c(j);
        this.c.b(i);
        this.c.a(rVar);
        if (z) {
            this.g = j;
        } else {
            this.f = j;
        }
        x.c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / 1024));
    }

    public final synchronized void a(int i, long j) {
        if (i >= 0) {
            this.e.put(Integer.valueOf(i), Long.valueOf(j));
            r rVar = new r();
            rVar.b = i;
            rVar.e = j;
            rVar.c = "";
            rVar.d = "";
            rVar.g = new byte[0];
            this.c.b(i);
            this.c.a(rVar);
            x.c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), z.a(j));
        } else {
            x.e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
        }
    }

    public final synchronized long a(int i) {
        long j;
        j = 0;
        if (i >= 0) {
            Long l = (Long) this.e.get(Integer.valueOf(i));
            if (l != null) {
                j = l.longValue();
            } else {
                List<r> a = this.c.a(i);
                if (a != null && a.size() > 0) {
                    if (a.size() > 1) {
                        for (r rVar : a) {
                            long j2;
                            if (rVar.e > j) {
                                j2 = rVar.e;
                            } else {
                                j2 = j;
                            }
                            j = j2;
                        }
                        this.c.b(i);
                    } else {
                        try {
                            j = ((r) a.get(0)).e;
                        } catch (Throwable th) {
                            x.a(th);
                        }
                    }
                }
            }
        } else {
            x.e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return j;
    }

    public final boolean b(int i) {
        if (b.c) {
            x.c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        x.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf((System.currentTimeMillis() - a(i)) / 1000), Integer.valueOf(i));
        if (System.currentTimeMillis() - a(i) >= i.MIN_UPLOAD_INTERVAL) {
            return true;
        }
        x.a("[UploadManager] Data only be uploaded once in %d seconds.", Long.valueOf(30));
        return false;
    }

    private static boolean c() {
        boolean z = false;
        x.c("[UploadManager] Drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            p a = p.a();
            if (a == null) {
                x.d("[UploadManager] Failed to get Database", new Object[0]);
            } else {
                z = a.a(555, "security_info", null, true);
            }
        } catch (Throwable th) {
            x.a(th);
        }
        return z;
    }

    private boolean d() {
        x.c("[UploadManager] Record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            p a = p.a();
            if (a == null) {
                x.d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (this.n != null) {
                stringBuilder.append(Base64.encodeToString(this.n, 0));
                stringBuilder.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (this.o != 0) {
                    stringBuilder.append(Long.toString(this.o));
                } else {
                    stringBuilder.append("null");
                }
                stringBuilder.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (this.p != null) {
                    stringBuilder.append(this.p);
                } else {
                    stringBuilder.append("null");
                }
                stringBuilder.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (this.q != 0) {
                    stringBuilder.append(Long.toString(this.q));
                } else {
                    stringBuilder.append("null");
                }
                a.a(555, "security_info", stringBuilder.toString().getBytes(), null, true);
                return true;
            }
            x.c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            x.a(th);
            c();
            return false;
        }
    }

    private boolean e() {
        x.c("[UploadManager] Load security info from database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        int i;
        try {
            p a = p.a();
            if (a == null) {
                x.d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            Map a2 = a.a(555, null, true);
            if (a2 != null && a2.containsKey("security_info")) {
                String[] split = new String((byte[]) a2.get("security_info")).split(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (split.length == 4) {
                    if (split[0].isEmpty() || split[0].equals("null")) {
                        i = 0;
                    } else {
                        this.n = Base64.decode(split[0], 0);
                        i = 0;
                    }
                    if (i == 0) {
                        if (!(split[1].isEmpty() || split[1].equals("null"))) {
                            try {
                                this.o = Long.parseLong(split[1]);
                            } catch (Throwable th) {
                                x.a(th);
                                i = 1;
                            }
                        }
                    }
                    if (i == 0) {
                        if (!(split[2].isEmpty() || split[2].equals("null"))) {
                            this.p = split[2];
                        }
                    }
                    if (!(i != 0 || split[3].isEmpty() || split[3].equals("null"))) {
                        try {
                            this.q = Long.parseLong(split[3]);
                        } catch (Throwable th2) {
                            x.a(th2);
                            i = 1;
                        }
                    }
                } else {
                    x.a("SecurityInfo = %s, Strings.length = %d", r3, Integer.valueOf(split.length));
                    i = 1;
                }
                if (i != 0) {
                    c();
                }
            }
            return true;
        } catch (Throwable th22) {
            x.a(th22);
            return false;
        }
    }

    protected final boolean b() {
        if (this.p == null || this.q == 0) {
            return false;
        }
        if (this.q >= System.currentTimeMillis() + this.m) {
            return true;
        }
        x.c("[UploadManager] Session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.q), new Date(this.q).toString(), Long.valueOf(System.currentTimeMillis() + this.m), new Date(System.currentTimeMillis() + this.m).toString());
        return false;
    }

    protected final void b(boolean z) {
        synchronized (this.r) {
            x.c("[UploadManager] Clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.n = null;
            this.p = null;
            this.q = 0;
        }
        if (z) {
            c();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r15) {
        /*
        r14 = this;
        r13 = 3;
        r12 = 2;
        r11 = 1;
        r2 = 0;
        if (r15 >= 0) goto L_0x000e;
    L_0x0006:
        r0 = "[UploadManager] Number of task to execute should >= 0";
        r1 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.x.a(r0, r1);
    L_0x000d:
        return;
    L_0x000e:
        r4 = com.tencent.bugly.proguard.w.a();
        r5 = new java.util.concurrent.LinkedBlockingQueue;
        r5.<init>();
        r6 = new java.util.concurrent.LinkedBlockingQueue;
        r6.<init>();
        r7 = r14.j;
        monitor-enter(r7);
        r0 = "[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0057 }
        r3 = 0;
        r8 = android.os.Process.myPid();	 Catch:{ all -> 0x0057 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x0057 }
        r1[r3] = r8;	 Catch:{ all -> 0x0057 }
        r3 = 1;
        r8 = android.os.Process.myTid();	 Catch:{ all -> 0x0057 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x0057 }
        r1[r3] = r8;	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ all -> 0x0057 }
        r0 = r14.h;	 Catch:{ all -> 0x0057 }
        r1 = r0.size();	 Catch:{ all -> 0x0057 }
        r0 = r14.i;	 Catch:{ all -> 0x0057 }
        r0 = r0.size();	 Catch:{ all -> 0x0057 }
        if (r1 != 0) goto L_0x005a;
    L_0x004b:
        if (r0 != 0) goto L_0x005a;
    L_0x004d:
        r0 = "[UploadManager] There is no upload task in queue.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ all -> 0x0057 }
        monitor-exit(r7);	 Catch:{ all -> 0x0057 }
        goto L_0x000d;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x005a:
        if (r15 == 0) goto L_0x016b;
    L_0x005c:
        if (r15 >= r1) goto L_0x0081;
    L_0x005e:
        r0 = r2;
    L_0x005f:
        if (r4 == 0) goto L_0x0067;
    L_0x0061:
        r1 = r4.c();	 Catch:{ all -> 0x0057 }
        if (r1 != 0) goto L_0x0168;
    L_0x0067:
        r1 = r2;
    L_0x0068:
        r3 = r2;
    L_0x0069:
        if (r3 >= r15) goto L_0x009a;
    L_0x006b:
        r0 = r14.h;	 Catch:{ all -> 0x0057 }
        r0 = r0.peek();	 Catch:{ all -> 0x0057 }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x0057 }
        if (r0 == 0) goto L_0x009a;
    L_0x0075:
        r5.put(r0);	 Catch:{ Throwable -> 0x0089 }
        r0 = r14.h;	 Catch:{ Throwable -> 0x0089 }
        r0.poll();	 Catch:{ Throwable -> 0x0089 }
    L_0x007d:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0069;
    L_0x0081:
        r3 = r1 + r0;
        if (r15 >= r3) goto L_0x016b;
    L_0x0085:
        r0 = r15 - r1;
        r15 = r1;
        goto L_0x005f;
    L_0x0089:
        r0 = move-exception;
        r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x0057 }
        r10 = 0;
        r0 = r0.getMessage();	 Catch:{ all -> 0x0057 }
        r9[r10] = r0;	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.x.e(r8, r9);	 Catch:{ all -> 0x0057 }
        goto L_0x007d;
    L_0x009a:
        r3 = r2;
    L_0x009b:
        if (r3 >= r1) goto L_0x00c4;
    L_0x009d:
        r0 = r14.i;	 Catch:{ all -> 0x0057 }
        r0 = r0.peek();	 Catch:{ all -> 0x0057 }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x0057 }
        if (r0 == 0) goto L_0x00c4;
    L_0x00a7:
        r6.put(r0);	 Catch:{ Throwable -> 0x00b3 }
        r0 = r14.i;	 Catch:{ Throwable -> 0x00b3 }
        r0.poll();	 Catch:{ Throwable -> 0x00b3 }
    L_0x00af:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x009b;
    L_0x00b3:
        r0 = move-exception;
        r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x0057 }
        r10 = 0;
        r0 = r0.getMessage();	 Catch:{ all -> 0x0057 }
        r9[r10] = r0;	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.x.e(r8, r9);	 Catch:{ all -> 0x0057 }
        goto L_0x00af;
    L_0x00c4:
        monitor-exit(r7);	 Catch:{ all -> 0x0057 }
        if (r15 <= 0) goto L_0x00e8;
    L_0x00c7:
        r0 = "[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r13];
        r7 = java.lang.Integer.valueOf(r15);
        r3[r2] = r7;
        r7 = android.os.Process.myPid();
        r7 = java.lang.Integer.valueOf(r7);
        r3[r11] = r7;
        r7 = android.os.Process.myTid();
        r7 = java.lang.Integer.valueOf(r7);
        r3[r12] = r7;
        com.tencent.bugly.proguard.x.c(r0, r3);
    L_0x00e8:
        r3 = r2;
    L_0x00e9:
        if (r3 >= r15) goto L_0x0139;
    L_0x00eb:
        r0 = r5.poll();
        r0 = (java.lang.Runnable) r0;
        if (r0 == 0) goto L_0x0139;
    L_0x00f3:
        r7 = r14.j;
        monitor-enter(r7);
        r8 = r14.u;	 Catch:{ all -> 0x012b }
        if (r8 < r12) goto L_0x0104;
    L_0x00fa:
        if (r4 == 0) goto L_0x0104;
    L_0x00fc:
        r4.a(r0);	 Catch:{ all -> 0x012b }
        monitor-exit(r7);	 Catch:{ all -> 0x012b }
    L_0x0100:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x00e9;
    L_0x0104:
        monitor-exit(r7);
        r7 = "[UploadManager] Create and start a new thread to execute a upload task: %s";
        r8 = new java.lang.Object[r11];
        r9 = "BUGLY_ASYNC_UPLOAD";
        r8[r2] = r9;
        com.tencent.bugly.proguard.x.a(r7, r8);
        r7 = new com.tencent.bugly.proguard.au;
        r7.<init>(r14, r0);
        r8 = "BUGLY_ASYNC_UPLOAD";
        r7 = com.tencent.bugly.proguard.z.a(r7, r8);
        if (r7 == 0) goto L_0x012e;
    L_0x011d:
        r7 = r14.j;
        monitor-enter(r7);
        r0 = r14.u;	 Catch:{ all -> 0x0128 }
        r0 = r0 + 1;
        r14.u = r0;	 Catch:{ all -> 0x0128 }
        monitor-exit(r7);	 Catch:{ all -> 0x0128 }
        goto L_0x0100;
    L_0x0128:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x012b:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x012e:
        r7 = "[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.";
        r8 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.x.d(r7, r8);
        r14.a(r0, r11);
        goto L_0x0100;
    L_0x0139:
        if (r1 <= 0) goto L_0x015c;
    L_0x013b:
        r0 = "[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r13];
        r5 = java.lang.Integer.valueOf(r1);
        r3[r2] = r5;
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r3[r11] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r3[r12] = r2;
        com.tencent.bugly.proguard.x.c(r0, r3);
    L_0x015c:
        if (r4 == 0) goto L_0x000d;
    L_0x015e:
        r0 = new com.tencent.bugly.proguard.av;
        r0.<init>(r14, r1, r6);
        r4.a(r0);
        goto L_0x000d;
    L_0x0168:
        r1 = r0;
        goto L_0x0068;
    L_0x016b:
        r15 = r1;
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.c(int):void");
    }

    private boolean a(Runnable runnable, boolean z) {
        if (runnable == null) {
            x.a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            x.c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.j) {
                if (z) {
                    this.h.put(runnable);
                } else {
                    this.i.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            x.e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    private void a(Runnable runnable, long j) {
        if (runnable == null) {
            x.d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        x.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread a = z.a(runnable, "BUGLY_SYNC_UPLOAD");
        if (a == null) {
            x.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            a(runnable, true);
            return;
        }
        try {
            a.join(j);
        } catch (Throwable th) {
            x.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            a(runnable, true);
            c(0);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.Runnable r7, boolean r8, boolean r9, long r10) {
        /*
        r6 = this;
        r5 = 2;
        r3 = 1;
        r4 = 0;
        if (r7 != 0) goto L_0x000c;
    L_0x0005:
        r0 = "[UploadManager] Upload task should not be null";
        r1 = new java.lang.Object[r4];
        com.tencent.bugly.proguard.x.d(r0, r1);
    L_0x000c:
        r0 = "[UploadManager] Add upload task (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.x.c(r0, r1);
        r0 = r6.p;
        if (r0 == 0) goto L_0x0077;
    L_0x002b:
        r0 = r6.b();
        if (r0 == 0) goto L_0x0059;
    L_0x0031:
        r0 = "[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.x.c(r0, r1);
        if (r9 == 0) goto L_0x0052;
    L_0x004e:
        r6.a(r7, r10);
    L_0x0051:
        return;
    L_0x0052:
        r6.a(r7, r8);
        r6.c(r4);
        goto L_0x0051;
    L_0x0059:
        r0 = "[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.x.a(r0, r1);
        r6.b(r4);
    L_0x0077:
        r1 = r6.t;
        monitor-enter(r1);
        r0 = r6.s;	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x0086;
    L_0x007e:
        r6.a(r7, r8);	 Catch:{ all -> 0x0083 }
        monitor-exit(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x0051;
    L_0x0083:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0086:
        r0 = 1;
        r6.s = r0;	 Catch:{ all -> 0x0083 }
        monitor-exit(r1);	 Catch:{ all -> 0x0083 }
        r0 = "[UploadManager] Initialize security context now (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.x.c(r0, r1);
        if (r9 == 0) goto L_0x00b7;
    L_0x00a7:
        r0 = new com.tencent.bugly.proguard.u$a;
        r2 = r6.d;
        r1 = r6;
        r3 = r7;
        r4 = r10;
        r0.<init>(r1, r2, r3, r4);
        r2 = 0;
        r6.a(r0, r2);
        goto L_0x0051;
    L_0x00b7:
        r6.a(r7, r8);
        r0 = new com.tencent.bugly.proguard.u$a;
        r1 = r6.d;
        r0.<init>(r6, r1);
        r1 = "[UploadManager] Create and start a new thread to execute a task of initializing security context: %s";
        r2 = new java.lang.Object[r3];
        r3 = "BUGLY_ASYNC_UPLOAD";
        r2[r4] = r3;
        com.tencent.bugly.proguard.x.a(r1, r2);
        r1 = "BUGLY_ASYNC_UPLOAD";
        r1 = com.tencent.bugly.proguard.z.a(r0, r1);
        if (r1 != 0) goto L_0x0051;
    L_0x00d4:
        r1 = "[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.";
        r2 = new java.lang.Object[r4];
        com.tencent.bugly.proguard.x.d(r1, r2);
        r1 = com.tencent.bugly.proguard.w.a();
        if (r1 == 0) goto L_0x00e6;
    L_0x00e1:
        r1.a(r0);
        goto L_0x0051;
    L_0x00e6:
        r0 = "[UploadManager] Asynchronous thread pool is unavailable now, try next time.";
        r1 = new java.lang.Object[r4];
        com.tencent.bugly.proguard.x.e(r0, r1);
        r1 = r6.t;
        monitor-enter(r1);
        r0 = 0;
        r6.s = r0;	 Catch:{ all -> 0x00f6 }
        monitor-exit(r1);	 Catch:{ all -> 0x00f6 }
        goto L_0x0051;
    L_0x00f6:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(java.lang.Runnable, boolean, boolean, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r9, com.tencent.bugly.proguard.an r10) {
        /*
        r8 = this;
        r4 = 2;
        r1 = 1;
        r2 = 0;
        r0 = r8.a;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        if (r9 != r4) goto L_0x003e;
    L_0x000a:
        r0 = "[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r3[r1] = r2;
        com.tencent.bugly.proguard.x.c(r0, r3);
        r8.b(r1);
    L_0x0028:
        r1 = r8.t;
        monitor-enter(r1);
        r0 = r8.s;	 Catch:{ all -> 0x003b }
        if (r0 == 0) goto L_0x0039;
    L_0x002f:
        r0 = 0;
        r8.s = r0;	 Catch:{ all -> 0x003b }
        r0 = r8.d;	 Catch:{ all -> 0x003b }
        r2 = "security_info";
        com.tencent.bugly.proguard.z.b(r0, r2);	 Catch:{ all -> 0x003b }
    L_0x0039:
        monitor-exit(r1);	 Catch:{ all -> 0x003b }
        goto L_0x0007;
    L_0x003b:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x003e:
        r3 = r8.t;
        monitor-enter(r3);
        r0 = r8.s;	 Catch:{ all -> 0x0047 }
        if (r0 != 0) goto L_0x004a;
    L_0x0045:
        monitor-exit(r3);	 Catch:{ all -> 0x0047 }
        goto L_0x0007;
    L_0x0047:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x004a:
        monitor-exit(r3);
        if (r10 == 0) goto L_0x012f;
    L_0x004d:
        r0 = "[UploadManager] Record security context (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r4 = android.os.Process.myTid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r1] = r4;
        com.tencent.bugly.proguard.x.c(r0, r3);
        r3 = r10.g;	 Catch:{ Throwable -> 0x0118 }
        if (r3 == 0) goto L_0x0102;
    L_0x006c:
        r0 = "S1";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0118 }
        if (r0 == 0) goto L_0x0102;
    L_0x0074:
        r0 = "S2";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0118 }
        if (r0 == 0) goto L_0x0102;
    L_0x007c:
        r4 = r10.e;	 Catch:{ Throwable -> 0x0118 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0118 }
        r4 = r4 - r6;
        r8.m = r4;	 Catch:{ Throwable -> 0x0118 }
        r0 = "[UploadManager] Time lag of server is: %d";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0118 }
        r5 = 0;
        r6 = r8.m;	 Catch:{ Throwable -> 0x0118 }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x0118 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.x.c(r0, r4);	 Catch:{ Throwable -> 0x0118 }
        r0 = "S1";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0118 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0118 }
        r8.p = r0;	 Catch:{ Throwable -> 0x0118 }
        r0 = "[UploadManager] Session ID from server is: %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0118 }
        r5 = 0;
        r6 = r8.p;	 Catch:{ Throwable -> 0x0118 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.x.c(r0, r4);	 Catch:{ Throwable -> 0x0118 }
        r0 = r8.p;	 Catch:{ Throwable -> 0x0118 }
        r0 = r0.length();	 Catch:{ Throwable -> 0x0118 }
        if (r0 <= 0) goto L_0x0126;
    L_0x00b5:
        r0 = "S2";
        r0 = r3.get(r0);	 Catch:{ NumberFormatException -> 0x0109 }
        r0 = (java.lang.String) r0;	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0109 }
        r8.q = r4;	 Catch:{ NumberFormatException -> 0x0109 }
        r0 = "[UploadManager] Session expired time from server is: %d(%s)";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = 0;
        r6 = r8.q;	 Catch:{ NumberFormatException -> 0x0109 }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ NumberFormatException -> 0x0109 }
        r3[r4] = r5;	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = 1;
        r5 = new java.util.Date;	 Catch:{ NumberFormatException -> 0x0109 }
        r6 = r8.q;	 Catch:{ NumberFormatException -> 0x0109 }
        r5.<init>(r6);	 Catch:{ NumberFormatException -> 0x0109 }
        r5 = r5.toString();	 Catch:{ NumberFormatException -> 0x0109 }
        r3[r4] = r5;	 Catch:{ NumberFormatException -> 0x0109 }
        com.tencent.bugly.proguard.x.c(r0, r3);	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = r8.q;	 Catch:{ NumberFormatException -> 0x0109 }
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x00f7;
    L_0x00ea:
        r0 = "[UploadManager] Session expired time from server is less than 1 second, will set to default value";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ NumberFormatException -> 0x0109 }
        com.tencent.bugly.proguard.x.d(r0, r3);	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r8.q = r4;	 Catch:{ NumberFormatException -> 0x0109 }
    L_0x00f7:
        r0 = r8.d();	 Catch:{ Throwable -> 0x0118 }
        if (r0 == 0) goto L_0x011d;
    L_0x00fd:
        r1 = r2;
    L_0x00fe:
        r0 = 0;
        r8.c(r0);	 Catch:{ Throwable -> 0x0118 }
    L_0x0102:
        if (r1 == 0) goto L_0x0028;
    L_0x0104:
        r8.b(r2);
        goto L_0x0028;
    L_0x0109:
        r0 = move-exception;
        r0 = "[UploadManager] Session expired time is invalid, will set to default value";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.x.d(r0, r3);	 Catch:{ Throwable -> 0x0118 }
        r4 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r8.q = r4;	 Catch:{ Throwable -> 0x0118 }
        goto L_0x00f7;
    L_0x0118:
        r0 = move-exception;
        com.tencent.bugly.proguard.x.a(r0);
        goto L_0x0102;
    L_0x011d:
        r0 = "[UploadManager] Failed to record database";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.x.c(r0, r3);	 Catch:{ Throwable -> 0x0118 }
        goto L_0x00fe;
    L_0x0126:
        r0 = "[UploadManager] Session ID from server is invalid, try next time";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.x.c(r0, r3);	 Catch:{ Throwable -> 0x0118 }
        goto L_0x0102;
    L_0x012f:
        r0 = "[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r4 = android.os.Process.myTid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r1] = r4;
        com.tencent.bugly.proguard.x.c(r0, r3);
        r8.b(r2);
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(int, com.tencent.bugly.proguard.an):void");
    }

    public final byte[] a(byte[] bArr) {
        if (this.n != null && (this.n.length << 3) == 128) {
            return z.a(1, bArr, this.n);
        }
        x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final byte[] b(byte[] bArr) {
        if (this.n != null && (this.n.length << 3) == 128) {
            return z.a(2, bArr, this.n);
        }
        x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        x.c("[UploadManager] Integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.p != null) {
            map.put("secureSessionId", this.p);
            return true;
        } else if (this.n == null || (this.n.length << 3) != 128) {
            x.d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        } else {
            if (this.l == null) {
                this.l = Base64.decode(this.k, 0);
                if (this.l == null) {
                    x.d("[UploadManager] Failed to decode RSA public key", new Object[0]);
                    return false;
                }
            }
            byte[] b = z.b(1, this.n, this.l);
            if (b == null) {
                x.d("[UploadManager] Failed to encrypt AES key", new Object[0]);
                return false;
            }
            String encodeToString = Base64.encodeToString(b, 0);
            if (encodeToString == null) {
                x.d("[UploadManager] Failed to encode AES key", new Object[0]);
                return false;
            }
            map.put("raKey", encodeToString);
            return true;
        }
    }
}
