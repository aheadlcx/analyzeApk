package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import com.tencent.bugly.b;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class ak {
    private static ak c = null;
    public aj a;
    public boolean b = true;
    private final ae d;
    private final Context e;
    private Map<Integer, Long> f = new HashMap();
    private long g;
    private long h;
    private LinkedBlockingQueue<Runnable> i = new LinkedBlockingQueue();
    private LinkedBlockingQueue<Runnable> j = new LinkedBlockingQueue();
    private final Object k = new Object();
    private String l = null;
    private byte[] m = null;
    private long n = 0;
    private byte[] o = null;
    private long p = 0;
    private String q = null;
    private long r = 0;
    private final Object s = new Object();
    private boolean t = false;
    private final Object u = new Object();
    private int v = 0;

    class a implements Runnable {
        final /* synthetic */ ak a;
        private final Context b;
        private final Runnable c;
        private final long d;

        public a(ak akVar, Context context) {
            this.a = akVar;
            this.b = context;
            this.c = null;
            this.d = 0;
        }

        public a(ak akVar, Context context, Runnable runnable, long j) {
            this.a = akVar;
            this.b = context;
            this.c = runnable;
            this.d = j;
        }

        public void run() {
            if (ap.a(this.b, "security_info", 30000)) {
                if (!this.a.e()) {
                    an.d("[UploadManager] Failed to load security info from database", new Object[0]);
                    this.a.b(false);
                }
                if (this.a.q != null) {
                    if (this.a.b()) {
                        an.c("[UploadManager] Sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
                        if (this.c != null) {
                            this.a.a(this.c, this.d);
                        }
                        this.a.c(0);
                        ap.b(this.b, "security_info");
                        synchronized (this.a.u) {
                            this.a.t = false;
                        }
                        return;
                    }
                    an.a("[UploadManager] Session ID is expired, drop it.", new Object[0]);
                    this.a.b(true);
                }
                byte[] a = ap.a(128);
                if (a == null || a.length * 8 != 128) {
                    an.d("[UploadManager] Failed to create AES key (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
                    this.a.b(false);
                    ap.b(this.b, "security_info");
                    synchronized (this.a.u) {
                        this.a.t = false;
                    }
                    return;
                }
                this.a.o = a;
                an.c("[UploadManager] Execute one upload task for requesting session ID (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
                if (this.c != null) {
                    this.a.a(this.c, this.d);
                    return;
                } else {
                    this.a.c(1);
                    return;
                }
            }
            an.c("[UploadManager] Sleep %d try to lock security file again (pid=%d | tid=%d)", new Object[]{Integer.valueOf(5000), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
            ap.b(5000);
            if (ap.a(this, "BUGLY_ASYNC_UPLOAD") == null) {
                an.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
                am a2 = am.a();
                if (a2 != null) {
                    a2.a(this);
                } else {
                    an.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
                }
            }
        }
    }

    static /* synthetic */ int b(ak akVar) {
        int i = akVar.v - 1;
        akVar.v = i;
        return i;
    }

    protected ak(Context context) {
        this.e = context;
        this.d = ae.a();
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e) {
            an.a("[UploadManager] Error: Can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.b = false;
        }
        if (this.b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/").append("sbTvVO2+RvW0PH01IdaBxc/").append("fB6fbHZocC9T3nl1+").append("J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB");
            this.l = stringBuilder.toString();
        }
    }

    public static synchronized ak a(Context context) {
        ak akVar;
        synchronized (ak.class) {
            if (c == null) {
                c = new ak(context);
            }
            akVar = c;
        }
        return akVar;
    }

    public static synchronized ak a() {
        ak akVar;
        synchronized (ak.class) {
            akVar = c;
        }
        return akVar;
    }

    public void a(int i, int i2, byte[] bArr, String str, String str2, aj ajVar, long j, boolean z) {
        try {
            b(new al(this.e, i, i2, bArr, str, str2, ajVar, this.b, z), true, true, j);
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public void a(int i, bd bdVar, String str, String str2, aj ajVar, long j, boolean z) {
        a(i, bdVar.g, ah.a((Object) bdVar), str, str2, ajVar, j, z);
    }

    public void a(int i, int i2, byte[] bArr, String str, String str2, aj ajVar, int i3, int i4, boolean z, Map<String, String> map) {
        try {
            b(new al(this.e, i, i2, bArr, str, str2, ajVar, this.b, i3, i4, false, map), z, false, 0);
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public void a(int i, int i2, byte[] bArr, String str, String str2, aj ajVar, boolean z, Map<String, String> map) {
        a(i, i2, bArr, str, str2, ajVar, 0, 0, z, map);
    }

    public void a(int i, bd bdVar, String str, String str2, aj ajVar, boolean z) {
        a(i, bdVar.g, ah.a((Object) bdVar), str, str2, ajVar, 0, 0, z, null);
    }

    public long a(boolean z) {
        long j;
        long j2 = 0;
        long b = ap.b();
        int i = z ? 5 : 3;
        List a = this.d.a(i);
        if (a == null || a.size() <= 0) {
            j = z ? this.h : this.g;
        } else {
            try {
                ag agVar = (ag) a.get(0);
                if (agVar.e >= b) {
                    j2 = ap.c(agVar.g);
                    if (i == 3) {
                        this.g = j2;
                    } else {
                        this.h = j2;
                    }
                    a.remove(agVar);
                }
                j = j2;
            } catch (Throwable th) {
                Throwable th2 = th;
                j = j2;
                an.a(th2);
            }
            if (a.size() > 0) {
                this.d.a(a);
            }
        }
        an.c("[UploadManager] Local network consume: %d KB", new Object[]{Long.valueOf(j / 1024)});
        return j;
    }

    protected synchronized void a(long j, boolean z) {
        int i = z ? 5 : 3;
        ag agVar = new ag();
        agVar.b = i;
        agVar.e = ap.b();
        agVar.c = "";
        agVar.d = "";
        agVar.g = ap.c(j);
        this.d.b(i);
        this.d.a(agVar);
        if (z) {
            this.h = j;
        } else {
            this.g = j;
        }
        an.c("[UploadManager] Network total consume: %d KB", new Object[]{Long.valueOf(j / 1024)});
    }

    public synchronized void a(int i, long j) {
        if (i >= 0) {
            this.f.put(Integer.valueOf(i), Long.valueOf(j));
            ag agVar = new ag();
            agVar.b = i;
            agVar.e = j;
            agVar.c = "";
            agVar.d = "";
            agVar.g = new byte[0];
            this.d.b(i);
            this.d.a(agVar);
            an.c("[UploadManager] Uploading(ID:%d) time: %s", new Object[]{Integer.valueOf(i), ap.a(j)});
        } else {
            an.e("[UploadManager] Unknown uploading ID: %d", new Object[]{Integer.valueOf(i)});
        }
    }

    public synchronized long a(int i) {
        long j;
        j = 0;
        if (i >= 0) {
            Long l = (Long) this.f.get(Integer.valueOf(i));
            if (l != null) {
                j = l.longValue();
            } else {
                List<ag> a = this.d.a(i);
                if (a != null && a.size() > 0) {
                    if (a.size() > 1) {
                        for (ag agVar : a) {
                            long j2;
                            if (agVar.e > j) {
                                j2 = agVar.e;
                            } else {
                                j2 = j;
                            }
                            j = j2;
                        }
                        this.d.b(i);
                    } else {
                        try {
                            j = ((ag) a.get(0)).e;
                        } catch (Throwable th) {
                            an.a(th);
                        }
                    }
                }
            }
        } else {
            an.e("[UploadManager] Unknown upload ID: %d", new Object[]{Integer.valueOf(i)});
        }
        return j;
    }

    public boolean b(int i) {
        if (b.c) {
            an.c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        an.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", new Object[]{Long.valueOf((System.currentTimeMillis() - a(i)) / 1000), Integer.valueOf(i)});
        if (System.currentTimeMillis() - a(i) >= 30000) {
            return true;
        }
        an.a("[UploadManager] Data only be uploaded once in %d seconds.", new Object[]{Long.valueOf(30)});
        return false;
    }

    private boolean c() {
        boolean z = false;
        an.c("[UploadManager] Drop security info of database (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        try {
            ae a = ae.a();
            if (a == null) {
                an.d("[UploadManager] Failed to get Database", new Object[0]);
            } else {
                z = a.a(555, "security_info", null, true);
            }
        } catch (Throwable th) {
            an.a(th);
        }
        return z;
    }

    private boolean d() {
        an.c("[UploadManager] Record security info to database (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        try {
            ae a = ae.a();
            if (a == null) {
                an.d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (this.o != null) {
                stringBuilder.append(Base64.encodeToString(this.o, 0));
                stringBuilder.append("#");
                if (this.p != 0) {
                    stringBuilder.append(Long.toString(this.p));
                } else {
                    stringBuilder.append("null");
                }
                stringBuilder.append("#");
                if (this.q != null) {
                    stringBuilder.append(this.q);
                } else {
                    stringBuilder.append("null");
                }
                stringBuilder.append("#");
                if (this.r != 0) {
                    stringBuilder.append(Long.toString(this.r));
                } else {
                    stringBuilder.append("null");
                }
                a.a(555, "security_info", stringBuilder.toString().getBytes(), null, true);
                return true;
            }
            an.c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            an.a(th);
            c();
            return false;
        }
    }

    private boolean e() {
        an.c("[UploadManager] Load security info from database (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        int i;
        try {
            ae a = ae.a();
            if (a == null) {
                an.d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            Map a2 = a.a(555, null, true);
            if (a2 != null && a2.containsKey("security_info")) {
                String[] split = new String((byte[]) a2.get("security_info")).split("#");
                if (split.length == 4) {
                    if (split[0].isEmpty() || split[0].equals("null")) {
                        i = 0;
                    } else {
                        this.o = Base64.decode(split[0], 0);
                        i = 0;
                    }
                    if (i == 0) {
                        if (!(split[1].isEmpty() || split[1].equals("null"))) {
                            try {
                                this.p = Long.parseLong(split[1]);
                            } catch (Throwable th) {
                                an.a(th);
                                i = 1;
                            }
                        }
                    }
                    if (i == 0) {
                        if (!(split[2].isEmpty() || split[2].equals("null"))) {
                            this.q = split[2];
                        }
                    }
                    if (!(i != 0 || split[3].isEmpty() || split[3].equals("null"))) {
                        try {
                            this.r = Long.parseLong(split[3]);
                        } catch (Throwable th2) {
                            an.a(th2);
                            i = 1;
                        }
                    }
                } else {
                    an.a("SecurityInfo = %s, Strings.length = %d", new Object[]{r3, Integer.valueOf(split.length)});
                    i = 1;
                }
                if (i != 0) {
                    c();
                }
            }
            return true;
        } catch (Throwable th22) {
            an.a(th22);
            return false;
        }
    }

    protected boolean b() {
        if (this.q == null || this.r == 0) {
            return false;
        }
        if (this.r >= System.currentTimeMillis() + this.n) {
            return true;
        }
        an.c("[UploadManager] Session ID expired time from server is: %d(%s), but now is: %d(%s)", new Object[]{Long.valueOf(this.r), new Date(this.r).toString(), Long.valueOf(System.currentTimeMillis() + this.n), new Date(System.currentTimeMillis() + this.n).toString()});
        return false;
    }

    protected void b(boolean z) {
        synchronized (this.s) {
            an.c("[UploadManager] Clear security context (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
            this.o = null;
            this.q = null;
            this.r = 0;
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
        if (r15 >= 0) goto L_0x000f;
    L_0x0006:
        r0 = "[UploadManager] Number of task to execute should >= 0";
        r1 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.an.a(r0, r1);
    L_0x000e:
        return;
    L_0x000f:
        r4 = com.tencent.bugly.proguard.am.a();
        r5 = new java.util.concurrent.LinkedBlockingQueue;
        r5.<init>();
        r6 = new java.util.concurrent.LinkedBlockingQueue;
        r6.<init>();
        r7 = r14.k;
        monitor-enter(r7);
        r0 = "[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x005a }
        r3 = 0;
        r8 = android.os.Process.myPid();	 Catch:{ all -> 0x005a }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x005a }
        r1[r3] = r8;	 Catch:{ all -> 0x005a }
        r3 = 1;
        r8 = android.os.Process.myTid();	 Catch:{ all -> 0x005a }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x005a }
        r1[r3] = r8;	 Catch:{ all -> 0x005a }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ all -> 0x005a }
        r0 = r14.i;	 Catch:{ all -> 0x005a }
        r1 = r0.size();	 Catch:{ all -> 0x005a }
        r0 = r14.j;	 Catch:{ all -> 0x005a }
        r0 = r0.size();	 Catch:{ all -> 0x005a }
        if (r1 != 0) goto L_0x005d;
    L_0x004d:
        if (r0 != 0) goto L_0x005d;
    L_0x004f:
        r0 = "[UploadManager] There is no upload task in queue.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x005a }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ all -> 0x005a }
        monitor-exit(r7);	 Catch:{ all -> 0x005a }
        goto L_0x000e;
    L_0x005a:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x005a }
        throw r0;
    L_0x005d:
        if (r15 != 0) goto L_0x00e5;
    L_0x005f:
        r3 = r1 + r0;
        r15 = r1;
    L_0x0062:
        if (r4 == 0) goto L_0x006a;
    L_0x0064:
        r1 = r4.c();	 Catch:{ all -> 0x005a }
        if (r1 != 0) goto L_0x017c;
    L_0x006a:
        r1 = r2;
    L_0x006b:
        r3 = r2;
    L_0x006c:
        if (r3 >= r15) goto L_0x0078;
    L_0x006e:
        r0 = r14.i;	 Catch:{ all -> 0x005a }
        r0 = r0.peek();	 Catch:{ all -> 0x005a }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x005a }
        if (r0 != 0) goto L_0x00f3;
    L_0x0078:
        r3 = r2;
    L_0x0079:
        if (r3 >= r1) goto L_0x0085;
    L_0x007b:
        r0 = r14.j;	 Catch:{ all -> 0x005a }
        r0 = r0.peek();	 Catch:{ all -> 0x005a }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x005a }
        if (r0 != 0) goto L_0x0112;
    L_0x0085:
        monitor-exit(r7);	 Catch:{ all -> 0x005a }
        if (r15 <= 0) goto L_0x00aa;
    L_0x0088:
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
        com.tencent.bugly.proguard.an.c(r0, r3);
    L_0x00aa:
        r3 = r2;
    L_0x00ab:
        if (r3 >= r15) goto L_0x00b5;
    L_0x00ad:
        r0 = r5.poll();
        r0 = (java.lang.Runnable) r0;
        if (r0 != 0) goto L_0x0131;
    L_0x00b5:
        if (r1 <= 0) goto L_0x00d9;
    L_0x00b7:
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
        com.tencent.bugly.proguard.an.c(r0, r3);
    L_0x00d9:
        if (r4 == 0) goto L_0x000e;
    L_0x00db:
        r0 = new com.tencent.bugly.proguard.ak$2;
        r0.<init>(r14, r1, r6);
        r4.a(r0);
        goto L_0x000e;
    L_0x00e5:
        if (r15 >= r1) goto L_0x00ea;
    L_0x00e7:
        r0 = r2;
        goto L_0x0062;
    L_0x00ea:
        r3 = r1 + r0;
        if (r15 >= r3) goto L_0x017f;
    L_0x00ee:
        r0 = r15 - r1;
        r15 = r1;
        goto L_0x0062;
    L_0x00f3:
        r5.put(r0);	 Catch:{ Throwable -> 0x0100 }
        r0 = r14.i;	 Catch:{ Throwable -> 0x0100 }
        r0.poll();	 Catch:{ Throwable -> 0x0100 }
    L_0x00fb:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x006c;
    L_0x0100:
        r0 = move-exception;
        r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x005a }
        r10 = 0;
        r0 = r0.getMessage();	 Catch:{ all -> 0x005a }
        r9[r10] = r0;	 Catch:{ all -> 0x005a }
        com.tencent.bugly.proguard.an.e(r8, r9);	 Catch:{ all -> 0x005a }
        goto L_0x00fb;
    L_0x0112:
        r6.put(r0);	 Catch:{ Throwable -> 0x011f }
        r0 = r14.j;	 Catch:{ Throwable -> 0x011f }
        r0.poll();	 Catch:{ Throwable -> 0x011f }
    L_0x011a:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0079;
    L_0x011f:
        r0 = move-exception;
        r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x005a }
        r10 = 0;
        r0 = r0.getMessage();	 Catch:{ all -> 0x005a }
        r9[r10] = r0;	 Catch:{ all -> 0x005a }
        com.tencent.bugly.proguard.an.e(r8, r9);	 Catch:{ all -> 0x005a }
        goto L_0x011a;
    L_0x0131:
        r7 = r14.k;
        monitor-enter(r7);
        r8 = r14.v;	 Catch:{ all -> 0x016d }
        if (r8 < r12) goto L_0x0143;
    L_0x0138:
        if (r4 == 0) goto L_0x0143;
    L_0x013a:
        r4.a(r0);	 Catch:{ all -> 0x016d }
        monitor-exit(r7);	 Catch:{ all -> 0x016d }
    L_0x013e:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x00ab;
    L_0x0143:
        monitor-exit(r7);	 Catch:{ all -> 0x016d }
        r7 = "[UploadManager] Create and start a new thread to execute a upload task: %s";
        r8 = new java.lang.Object[r11];
        r9 = "BUGLY_ASYNC_UPLOAD";
        r8[r2] = r9;
        com.tencent.bugly.proguard.an.a(r7, r8);
        r7 = new com.tencent.bugly.proguard.ak$1;
        r7.<init>(r14, r0);
        r8 = "BUGLY_ASYNC_UPLOAD";
        r7 = com.tencent.bugly.proguard.ap.a(r7, r8);
        if (r7 == 0) goto L_0x0170;
    L_0x015f:
        r7 = r14.k;
        monitor-enter(r7);
        r0 = r14.v;	 Catch:{ all -> 0x016a }
        r0 = r0 + 1;
        r14.v = r0;	 Catch:{ all -> 0x016a }
        monitor-exit(r7);	 Catch:{ all -> 0x016a }
        goto L_0x013e;
    L_0x016a:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x016a }
        throw r0;
    L_0x016d:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x016d }
        throw r0;
    L_0x0170:
        r7 = "[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.";
        r8 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.an.d(r7, r8);
        r14.a(r0, r11);
        goto L_0x013e;
    L_0x017c:
        r1 = r0;
        goto L_0x006b;
    L_0x017f:
        r15 = r1;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.ak.c(int):void");
    }

    private boolean a(Runnable runnable, boolean z) {
        if (runnable == null) {
            an.a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            an.c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
            synchronized (this.k) {
                if (z) {
                    this.i.put(runnable);
                } else {
                    this.j.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            an.e("[UploadManager] Failed to add upload task to queue: %s", new Object[]{th.getMessage()});
            return false;
        }
    }

    private void a(Runnable runnable, long j) {
        if (runnable == null) {
            an.d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        an.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        Thread a = ap.a(runnable, "BUGLY_SYNC_UPLOAD");
        if (a == null) {
            an.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            a(runnable, true);
            return;
        }
        try {
            a.join(j);
        } catch (Throwable th) {
            an.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", new Object[]{th.getMessage()});
            a(runnable, true);
            c(0);
        }
    }

    private void a(Runnable runnable, boolean z, boolean z2, long j) {
        an.c("[UploadManager] Initialize security context now (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        if (z2) {
            a(new a(this, this.e, runnable, j), 0);
            return;
        }
        a(runnable, z);
        Runnable aVar = new a(this, this.e);
        an.a("[UploadManager] Create and start a new thread to execute a task of initializing security context: %s", new Object[]{"BUGLY_ASYNC_UPLOAD"});
        if (ap.a(aVar, "BUGLY_ASYNC_UPLOAD") == null) {
            an.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
            am a = am.a();
            if (a != null) {
                a.a(aVar);
                return;
            }
            an.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
            synchronized (this.u) {
                this.t = false;
            }
        }
    }

    private void b(Runnable runnable, boolean z, boolean z2, long j) {
        if (runnable == null) {
            an.d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        an.c("[UploadManager] Add upload task (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        if (this.q != null) {
            if (b()) {
                an.c("[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
                if (z2) {
                    a(runnable, j);
                    return;
                }
                a(runnable, z);
                c(0);
                return;
            }
            an.a("[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
            b(false);
        }
        synchronized (this.u) {
            if (this.t) {
                a(runnable, z);
                return;
            }
            this.t = true;
            a(runnable, z, z2, j);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r10, com.tencent.bugly.proguard.be r11) {
        /*
        r9 = this;
        r4 = 2;
        r1 = 1;
        r2 = 0;
        r0 = r9.b;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        if (r10 != r4) goto L_0x0040;
    L_0x000a:
        r0 = "[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r3[r1] = r2;
        com.tencent.bugly.proguard.an.c(r0, r3);
        r9.b(r1);
    L_0x0029:
        r1 = r9.u;
        monitor-enter(r1);
        r0 = r9.t;	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x003b;
    L_0x0030:
        r0 = 0;
        r9.t = r0;	 Catch:{ all -> 0x003d }
        r0 = r9.e;	 Catch:{ all -> 0x003d }
        r2 = "security_info";
        com.tencent.bugly.proguard.ap.b(r0, r2);	 Catch:{ all -> 0x003d }
    L_0x003b:
        monitor-exit(r1);	 Catch:{ all -> 0x003d }
        goto L_0x0007;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x003d }
        throw r0;
    L_0x0040:
        r3 = r9.u;
        monitor-enter(r3);
        r0 = r9.t;	 Catch:{ all -> 0x0049 }
        if (r0 != 0) goto L_0x004c;
    L_0x0047:
        monitor-exit(r3);	 Catch:{ all -> 0x0049 }
        goto L_0x0007;
    L_0x0049:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0049 }
        throw r0;
    L_0x004c:
        monitor-exit(r3);	 Catch:{ all -> 0x0049 }
        if (r11 == 0) goto L_0x013f;
    L_0x004f:
        r0 = "[UploadManager] Record security context (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r4 = android.os.Process.myTid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r1] = r4;
        com.tencent.bugly.proguard.an.c(r0, r3);
        r3 = r11.h;	 Catch:{ Throwable -> 0x0124 }
        if (r3 == 0) goto L_0x0128;
    L_0x006f:
        r0 = "S1";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0124 }
        if (r0 == 0) goto L_0x0128;
    L_0x0078:
        r0 = "S2";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0124 }
        if (r0 == 0) goto L_0x0128;
    L_0x0081:
        r4 = r11.e;	 Catch:{ Throwable -> 0x0124 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0124 }
        r4 = r4 - r6;
        r9.n = r4;	 Catch:{ Throwable -> 0x0124 }
        r0 = "[UploadManager] Time lag of server is: %d";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0124 }
        r5 = 0;
        r6 = r9.n;	 Catch:{ Throwable -> 0x0124 }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x0124 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0124 }
        com.tencent.bugly.proguard.an.c(r0, r4);	 Catch:{ Throwable -> 0x0124 }
        r0 = "S1";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0124 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0124 }
        r9.q = r0;	 Catch:{ Throwable -> 0x0124 }
        r0 = "[UploadManager] Session ID from server is: %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0124 }
        r5 = 0;
        r6 = r9.q;	 Catch:{ Throwable -> 0x0124 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0124 }
        com.tencent.bugly.proguard.an.c(r0, r4);	 Catch:{ Throwable -> 0x0124 }
        r0 = r9.q;	 Catch:{ Throwable -> 0x0124 }
        r0 = r0.length();	 Catch:{ Throwable -> 0x0124 }
        if (r0 <= 0) goto L_0x0135;
    L_0x00bd:
        r0 = "S2";
        r0 = r3.get(r0);	 Catch:{ NumberFormatException -> 0x0114 }
        r0 = (java.lang.String) r0;	 Catch:{ NumberFormatException -> 0x0114 }
        r4 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0114 }
        r9.r = r4;	 Catch:{ NumberFormatException -> 0x0114 }
        r0 = "[UploadManager] Session expired time from server is: %d(%s)";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ NumberFormatException -> 0x0114 }
        r4 = 0;
        r6 = r9.r;	 Catch:{ NumberFormatException -> 0x0114 }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ NumberFormatException -> 0x0114 }
        r3[r4] = r5;	 Catch:{ NumberFormatException -> 0x0114 }
        r4 = 1;
        r5 = new java.util.Date;	 Catch:{ NumberFormatException -> 0x0114 }
        r6 = r9.r;	 Catch:{ NumberFormatException -> 0x0114 }
        r5.<init>(r6);	 Catch:{ NumberFormatException -> 0x0114 }
        r5 = r5.toString();	 Catch:{ NumberFormatException -> 0x0114 }
        r3[r4] = r5;	 Catch:{ NumberFormatException -> 0x0114 }
        com.tencent.bugly.proguard.an.c(r0, r3);	 Catch:{ NumberFormatException -> 0x0114 }
        r4 = r9.r;	 Catch:{ NumberFormatException -> 0x0114 }
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x0102;
    L_0x00f4:
        r0 = "[UploadManager] Session expired time from server is less than 1 second, will set to default value";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ NumberFormatException -> 0x0114 }
        com.tencent.bugly.proguard.an.d(r0, r3);	 Catch:{ NumberFormatException -> 0x0114 }
        r4 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r9.r = r4;	 Catch:{ NumberFormatException -> 0x0114 }
    L_0x0102:
        r0 = r9.d();	 Catch:{ Throwable -> 0x0124 }
        if (r0 == 0) goto L_0x012a;
    L_0x0108:
        r0 = r2;
    L_0x0109:
        r1 = 0;
        r9.c(r1);	 Catch:{ Throwable -> 0x0160 }
    L_0x010d:
        if (r0 == 0) goto L_0x0029;
    L_0x010f:
        r9.b(r2);
        goto L_0x0029;
    L_0x0114:
        r0 = move-exception;
        r0 = "[UploadManager] Session expired time is invalid, will set to default value";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0124 }
        com.tencent.bugly.proguard.an.d(r0, r3);	 Catch:{ Throwable -> 0x0124 }
        r4 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r9.r = r4;	 Catch:{ Throwable -> 0x0124 }
        goto L_0x0102;
    L_0x0124:
        r0 = move-exception;
    L_0x0125:
        com.tencent.bugly.proguard.an.a(r0);
    L_0x0128:
        r0 = r1;
        goto L_0x010d;
    L_0x012a:
        r0 = "[UploadManager] Failed to record database";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0124 }
        com.tencent.bugly.proguard.an.c(r0, r3);	 Catch:{ Throwable -> 0x0124 }
        r0 = r1;
        goto L_0x0109;
    L_0x0135:
        r0 = "[UploadManager] Session ID from server is invalid, try next time";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0124 }
        com.tencent.bugly.proguard.an.c(r0, r3);	 Catch:{ Throwable -> 0x0124 }
        goto L_0x0128;
    L_0x013f:
        r0 = "[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r4 = android.os.Process.myTid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r1] = r4;
        com.tencent.bugly.proguard.an.c(r0, r3);
        r9.b(r2);
        goto L_0x0029;
    L_0x0160:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0125;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.ak.a(int, com.tencent.bugly.proguard.be):void");
    }

    public byte[] a(byte[] bArr) {
        if (this.o != null && this.o.length * 8 == 128) {
            return ap.a(1, bArr, this.o);
        }
        an.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        return null;
    }

    public byte[] b(byte[] bArr) {
        if (this.o != null && this.o.length * 8 == 128) {
            return ap.a(2, bArr, this.o);
        }
        an.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        return null;
    }

    public boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        an.c("[UploadManager] Integrate security to HTTP headers (pid=%d | tid=%d)", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        if (this.q != null) {
            map.put("secureSessionId", this.q);
            return true;
        } else if (this.o == null || this.o.length * 8 != 128) {
            an.d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        } else {
            if (this.m == null) {
                this.m = Base64.decode(this.l, 0);
                if (this.m == null) {
                    an.d("[UploadManager] Failed to decode RSA public key", new Object[0]);
                    return false;
                }
            }
            byte[] b = ap.b(1, this.o, this.m);
            if (b == null) {
                an.d("[UploadManager] Failed to encrypt AES key", new Object[0]);
                return false;
            }
            String encodeToString = Base64.encodeToString(b, 0);
            if (encodeToString == null) {
                an.d("[UploadManager] Failed to encode AES key", new Object[0]);
                return false;
            }
            map.put("raKey", encodeToString);
            return true;
        }
    }
}
