package com.loc;

import android.content.Context;
import android.os.Looper;

public final class aa extends ad {
    private static boolean b = true;
    private String[] c = new String[10];
    private int d = 0;
    private boolean e = false;
    private int f = 0;

    protected aa(int i) {
        super(i);
    }

    private String d() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = this.d;
            while (i < 10 && i <= 9) {
                stringBuilder.append(this.c[i]);
                i++;
            }
            for (i = 0; i < this.d; i++) {
                stringBuilder.append(this.c[i]);
            }
        } catch (Throwable th) {
            w.a(th, "ANRWriter", "getLogInfo");
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final java.lang.String a(java.util.List<com.loc.s> r9) {
        /*
        r8 = this;
        r6 = 1024000; // 0xfa000 float:1.43493E-39 double:5.05923E-318;
        r5 = 0;
        r1 = 0;
        r0 = 0;
        r2 = 0;
        r4 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x011a, IOException -> 0x00cf, Throwable -> 0x00ed, all -> 0x0109 }
        r3 = "/data/anr/traces.txt";
        r4.<init>(r3);	 Catch:{ FileNotFoundException -> 0x011a, IOException -> 0x00cf, Throwable -> 0x00ed, all -> 0x0109 }
        r3 = r4.exists();	 Catch:{ FileNotFoundException -> 0x011a, IOException -> 0x00cf, Throwable -> 0x00ed, all -> 0x0109 }
        if (r3 != 0) goto L_0x0020;
    L_0x0014:
        if (r1 == 0) goto L_0x0019;
    L_0x0016:
        r2.close();	 Catch:{ IOException -> 0x01a3, Throwable -> 0x01ad }
    L_0x0019:
        if (r1 == 0) goto L_0x001e;
    L_0x001b:
        r0.close();	 Catch:{ IOException -> 0x01b7, Throwable -> 0x01c1 }
    L_0x001e:
        r0 = r1;
    L_0x001f:
        return r0;
    L_0x0020:
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x011a, IOException -> 0x00cf, Throwable -> 0x00ed, all -> 0x0109 }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x011a, IOException -> 0x00cf, Throwable -> 0x00ed, all -> 0x0109 }
        r0 = r3.available();	 Catch:{ FileNotFoundException -> 0x0202, IOException -> 0x01fb, Throwable -> 0x01f4, all -> 0x01ed }
        if (r0 <= r6) goto L_0x0030;
    L_0x002b:
        r0 = r0 - r6;
        r6 = (long) r0;	 Catch:{ FileNotFoundException -> 0x0202, IOException -> 0x01fb, Throwable -> 0x01f4, all -> 0x01ed }
        r3.skip(r6);	 Catch:{ FileNotFoundException -> 0x0202, IOException -> 0x01fb, Throwable -> 0x01f4, all -> 0x01ed }
    L_0x0030:
        r2 = new com.loc.bg;	 Catch:{ FileNotFoundException -> 0x0202, IOException -> 0x01fb, Throwable -> 0x01f4, all -> 0x01ed }
        r0 = com.loc.bh.a;	 Catch:{ FileNotFoundException -> 0x0202, IOException -> 0x01fb, Throwable -> 0x01f4, all -> 0x01ed }
        r2.<init>(r3, r0);	 Catch:{ FileNotFoundException -> 0x0202, IOException -> 0x01fb, Throwable -> 0x01f4, all -> 0x01ed }
        r4 = r5;
    L_0x0038:
        r0 = r2.a();	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r0 = r0.trim();	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r5 = "pid";
        r5 = r0.contains(r5);	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        if (r5 == 0) goto L_0x020c;
    L_0x0048:
        r4 = "\"main\"";
        r4 = r0.startsWith(r4);	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        if (r4 != 0) goto L_0x0055;
    L_0x0050:
        r0 = r2.a();	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        goto L_0x0048;
    L_0x0055:
        r4 = 1;
        r5 = r4;
        r4 = r0;
    L_0x0058:
        r0 = "";
        r0 = r4.equals(r0);	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        if (r0 == 0) goto L_0x0075;
    L_0x0060:
        if (r5 == 0) goto L_0x0075;
    L_0x0062:
        if (r2 == 0) goto L_0x0067;
    L_0x0064:
        r2.close();	 Catch:{ IOException -> 0x01cb, Throwable -> 0x01d5 }
    L_0x0067:
        if (r3 == 0) goto L_0x006c;
    L_0x0069:
        r3.close();	 Catch:{ IOException -> 0x01df, Throwable -> 0x01e6 }
    L_0x006c:
        r0 = r8.e;
        if (r0 == 0) goto L_0x0117;
    L_0x0070:
        r0 = r8.d();
        goto L_0x001f;
    L_0x0075:
        if (r5 == 0) goto L_0x00cc;
    L_0x0077:
        r0 = r8.d;	 Catch:{ Throwable -> 0x00b8, EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff }
        r6 = 9;
        if (r0 <= r6) goto L_0x0080;
    L_0x007d:
        r0 = 0;
        r8.d = r0;	 Catch:{ Throwable -> 0x00b8, EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff }
    L_0x0080:
        r0 = r8.c;	 Catch:{ Throwable -> 0x00b8, EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff }
        r6 = r8.d;	 Catch:{ Throwable -> 0x00b8, EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff }
        r0[r6] = r4;	 Catch:{ Throwable -> 0x00b8, EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff }
        r0 = r8.d;	 Catch:{ Throwable -> 0x00b8, EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff }
        r0 = r0 + 1;
        r8.d = r0;	 Catch:{ Throwable -> 0x00b8, EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff }
    L_0x008c:
        r0 = r8.f;	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r6 = 5;
        if (r0 == r6) goto L_0x0062;
    L_0x0091:
        r0 = r8.e;	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        if (r0 != 0) goto L_0x00c6;
    L_0x0095:
        r6 = r9.iterator();	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
    L_0x0099:
        r0 = r6.hasNext();	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        if (r0 == 0) goto L_0x00c3;
    L_0x009f:
        r0 = r6.next();	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r0 = (com.loc.s) r0;	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r7 = r0.f();	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r7 = com.loc.ad.a(r7, r4);	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r8.e = r7;	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r7 = r8.e;	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        if (r7 == 0) goto L_0x0099;
    L_0x00b3:
        r8.a(r0);	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r4 = r5;
        goto L_0x0038;
    L_0x00b8:
        r0 = move-exception;
        r6 = "ANRWriter";
        r7 = "addData";
        com.loc.w.a(r0, r6, r7);	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        goto L_0x008c;
    L_0x00c1:
        r0 = move-exception;
        goto L_0x0062;
    L_0x00c3:
        r4 = r5;
        goto L_0x0038;
    L_0x00c6:
        r0 = r8.f;	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
        r0 = r0 + 1;
        r8.f = r0;	 Catch:{ EOFException -> 0x00c1, FileNotFoundException -> 0x0207, IOException -> 0x01ff, Throwable -> 0x01f8 }
    L_0x00cc:
        r4 = r5;
        goto L_0x0038;
    L_0x00cf:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00d2:
        r4 = "ANRWriter";
        r5 = "initLog";
        com.loc.w.a(r0, r4, r5);	 Catch:{ all -> 0x01f1 }
        if (r2 == 0) goto L_0x00de;
    L_0x00db:
        r2.close();	 Catch:{ IOException -> 0x016f, Throwable -> 0x0179 }
    L_0x00de:
        if (r3 == 0) goto L_0x006c;
    L_0x00e0:
        r3.close();	 Catch:{ IOException -> 0x00e4, Throwable -> 0x0183 }
        goto L_0x006c;
    L_0x00e4:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
    L_0x00e9:
        com.loc.w.a(r0, r2, r3);
        goto L_0x006c;
    L_0x00ed:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00f0:
        r4 = "ANRWriter";
        r5 = "initLog";
        com.loc.w.a(r0, r4, r5);	 Catch:{ all -> 0x01f1 }
        if (r2 == 0) goto L_0x00fc;
    L_0x00f9:
        r2.close();	 Catch:{ IOException -> 0x0189, Throwable -> 0x0193 }
    L_0x00fc:
        if (r3 == 0) goto L_0x006c;
    L_0x00fe:
        r3.close();	 Catch:{ IOException -> 0x0103, Throwable -> 0x019d }
        goto L_0x006c;
    L_0x0103:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        goto L_0x00e9;
    L_0x0109:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x010c:
        if (r2 == 0) goto L_0x0111;
    L_0x010e:
        r2.close();	 Catch:{ IOException -> 0x014b, Throwable -> 0x0154 }
    L_0x0111:
        if (r3 == 0) goto L_0x0116;
    L_0x0113:
        r3.close();	 Catch:{ IOException -> 0x015d, Throwable -> 0x0166 }
    L_0x0116:
        throw r0;
    L_0x0117:
        r0 = r1;
        goto L_0x001f;
    L_0x011a:
        r0 = move-exception;
        r0 = r1;
        r2 = r1;
    L_0x011d:
        if (r0 == 0) goto L_0x0122;
    L_0x011f:
        r0.close();	 Catch:{ IOException -> 0x012f, Throwable -> 0x0138 }
    L_0x0122:
        if (r2 == 0) goto L_0x006c;
    L_0x0124:
        r2.close();	 Catch:{ IOException -> 0x0129, Throwable -> 0x0141 }
        goto L_0x006c;
    L_0x0129:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        goto L_0x00e9;
    L_0x012f:
        r0 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog1";
        com.loc.w.a(r0, r3, r4);
        goto L_0x0122;
    L_0x0138:
        r0 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog2";
        com.loc.w.a(r0, r3, r4);
        goto L_0x0122;
    L_0x0141:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
    L_0x0146:
        com.loc.w.a(r0, r2, r3);
        goto L_0x006c;
    L_0x014b:
        r1 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.loc.w.a(r1, r2, r4);
        goto L_0x0111;
    L_0x0154:
        r1 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.loc.w.a(r1, r2, r4);
        goto L_0x0111;
    L_0x015d:
        r1 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.loc.w.a(r1, r2, r3);
        goto L_0x0116;
    L_0x0166:
        r1 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.loc.w.a(r1, r2, r3);
        goto L_0x0116;
    L_0x016f:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.loc.w.a(r0, r2, r4);
        goto L_0x00de;
    L_0x0179:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.loc.w.a(r0, r2, r4);
        goto L_0x00de;
    L_0x0183:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        goto L_0x0146;
    L_0x0189:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.loc.w.a(r0, r2, r4);
        goto L_0x00fc;
    L_0x0193:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.loc.w.a(r0, r2, r4);
        goto L_0x00fc;
    L_0x019d:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        goto L_0x0146;
    L_0x01a3:
        r2 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog1";
        com.loc.w.a(r2, r3, r4);
        goto L_0x0019;
    L_0x01ad:
        r2 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog2";
        com.loc.w.a(r2, r3, r4);
        goto L_0x0019;
    L_0x01b7:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.loc.w.a(r0, r2, r3);
        goto L_0x001e;
    L_0x01c1:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.loc.w.a(r0, r2, r3);
        goto L_0x001e;
    L_0x01cb:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.loc.w.a(r0, r2, r4);
        goto L_0x0067;
    L_0x01d5:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.loc.w.a(r0, r2, r4);
        goto L_0x0067;
    L_0x01df:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        goto L_0x00e9;
    L_0x01e6:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        goto L_0x0146;
    L_0x01ed:
        r0 = move-exception;
        r2 = r1;
        goto L_0x010c;
    L_0x01f1:
        r0 = move-exception;
        goto L_0x010c;
    L_0x01f4:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00f0;
    L_0x01f8:
        r0 = move-exception;
        goto L_0x00f0;
    L_0x01fb:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00d2;
    L_0x01ff:
        r0 = move-exception;
        goto L_0x00d2;
    L_0x0202:
        r0 = move-exception;
        r0 = r1;
        r2 = r3;
        goto L_0x011d;
    L_0x0207:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
        goto L_0x011d;
    L_0x020c:
        r5 = r4;
        r4 = r0;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.aa.a(java.util.List):java.lang.String");
    }

    protected final boolean a(Context context) {
        if (n.m(context) != 1 || !b) {
            return false;
        }
        b = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            ar a = aqVar.a();
            if (a == null) {
                return true;
            } else if (a.c()) {
                a.c(false);
                aqVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
