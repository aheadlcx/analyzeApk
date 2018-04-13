package com.alibaba.mtl.appmonitor.d;

import android.content.Context;
import com.alibaba.mtl.appmonitor.a.f;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class j {
    private static final String TAG = null;
    private static j a;
    private String A;
    private Map<f, g> q = new HashMap();
    private int r;

    private j() {
        for (f fVar : f.a()) {
            if (fVar == f.ALARM) {
                this.q.put(fVar, new f(fVar, fVar.e()));
            } else {
                this.q.put(fVar, new g(fVar, fVar.e()));
            }
        }
    }

    public static j a() {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new j();
                }
            }
        }
        return a;
    }

    public void init(Context context) {
        k();
    }

    public static boolean a(f fVar, String str, String str2) {
        return a().b(fVar, str, str2, null);
    }

    public static boolean a(f fVar, String str, String str2, Map<String, String> map) {
        return a().b(fVar, str, str2, (Map) map);
    }

    public static boolean a(String str, String str2, Boolean bool, Map<String, String> map) {
        return a().b(str, str2, bool, (Map) map);
    }

    public boolean b(f fVar, String str, String str2, Map<String, String> map) {
        g gVar = (g) this.q.get(fVar);
        if (gVar != null) {
            return gVar.a(this.r, str, str2, map);
        }
        return false;
    }

    public boolean b(String str, String str2, Boolean bool, Map<String, String> map) {
        g gVar = (g) this.q.get(f.ALARM);
        if (gVar == null || !(gVar instanceof f)) {
            return false;
        }
        return ((f) gVar).a(this.r, str, str2, bool, map);
    }

    public void k() {
        this.r = new Random(System.currentTimeMillis()).nextInt(10000);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r11) {
        /*
        r10 = this;
        r2 = 2;
        r4 = 1;
        r0 = 0;
        r1 = "SampleRules";
        r2 = new java.lang.Object[r2];
        r3 = "config:";
        r2[r0] = r3;
        r2[r4] = r11;
        com.alibaba.mtl.log.e.i.a(r1, r2);
        monitor-enter(r10);
        r1 = com.alibaba.mtl.appmonitor.f.b.isBlank(r11);	 Catch:{ all -> 0x005e }
        if (r1 != 0) goto L_0x0023;
    L_0x0017:
        r1 = r10.A;	 Catch:{ all -> 0x005e }
        if (r1 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r10.A;	 Catch:{ all -> 0x005e }
        r1 = r1.equals(r11);	 Catch:{ all -> 0x005e }
        if (r1 == 0) goto L_0x0025;
    L_0x0023:
        monitor-exit(r10);	 Catch:{ all -> 0x005e }
    L_0x0024:
        return;
    L_0x0025:
        monitor-exit(r10);	 Catch:{ all -> 0x005e }
        r2 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0064 }
        r2.<init>(r11);	 Catch:{ Throwable -> 0x0064 }
        r3 = com.alibaba.mtl.appmonitor.a.f.a();	 Catch:{ Throwable -> 0x0064 }
        r4 = r3.length;	 Catch:{ Throwable -> 0x0064 }
        r1 = r0;
    L_0x0031:
        if (r1 >= r4) goto L_0x0061;
    L_0x0033:
        r5 = r3[r1];	 Catch:{ Throwable -> 0x0064 }
        r0 = r5.toString();	 Catch:{ Throwable -> 0x0064 }
        r6 = r2.optJSONObject(r0);	 Catch:{ Throwable -> 0x0064 }
        r0 = r10.q;	 Catch:{ Throwable -> 0x0064 }
        r0 = r0.get(r5);	 Catch:{ Throwable -> 0x0064 }
        r0 = (com.alibaba.mtl.appmonitor.d.g) r0;	 Catch:{ Throwable -> 0x0064 }
        if (r6 == 0) goto L_0x005a;
    L_0x0047:
        if (r0 == 0) goto L_0x005a;
    L_0x0049:
        r7 = TAG;	 Catch:{ Throwable -> 0x0064 }
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0064 }
        r9 = 0;
        r8[r9] = r5;	 Catch:{ Throwable -> 0x0064 }
        r5 = 1;
        r8[r5] = r6;	 Catch:{ Throwable -> 0x0064 }
        com.alibaba.mtl.log.e.i.a(r7, r8);	 Catch:{ Throwable -> 0x0064 }
        r0.b(r6);	 Catch:{ Throwable -> 0x0064 }
    L_0x005a:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0031;
    L_0x005e:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x005e }
        throw r0;
    L_0x0061:
        r10.A = r11;	 Catch:{ Throwable -> 0x0064 }
        goto L_0x0024;
    L_0x0064:
        r0 = move-exception;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.appmonitor.d.j.b(java.lang.String):void");
    }

    public void a(f fVar, int i) {
        g gVar = (g) this.q.get(fVar);
        if (gVar != null) {
            gVar.setSampling(i);
        }
    }
}
