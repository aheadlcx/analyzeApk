package com.ishumei.d;

import android.content.Context;
import com.ishumei.b.d;

public class g {
    private static g b = null;
    private Context a;

    private g() {
        this.a = null;
        this.a = d.a;
    }

    public static g a() {
        if (b == null) {
            synchronized (g.class) {
                if (b == null) {
                    b = new g();
                }
            }
        }
        return b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b() {
        /*
        r8 = this;
        r0 = "";
        r1 = "b1b0afbaadb2";
        r2 = com.ishumei.f.d.g(r1);	 Catch:{ SecurityException -> 0x007d, Exception -> 0x0079 }
        r0 = r8.a;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        if (r0 == 0) goto L_0x007a;
    L_0x000c:
        r0 = r8.a;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r1 = "location";
        r0 = r0.getSystemService(r1);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r0 = (android.location.LocationManager) r0;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        if (r0 != 0) goto L_0x001b;
    L_0x0018:
        r0 = "";
    L_0x001a:
        return r0;
    L_0x001b:
        r1 = 1;
        r1 = r0.getProviders(r1);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r3 = r1.iterator();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
    L_0x0024:
        r1 = r3.hasNext();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        if (r1 == 0) goto L_0x007a;
    L_0x002a:
        r1 = r3.next();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r1 = (java.lang.String) r1;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r1 = r0.getLastKnownLocation(r1);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        if (r1 == 0) goto L_0x0024;
    L_0x0036:
        r0 = java.util.Locale.CHINA;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r3 = "%.5f,%.5f,%g,%g,%d";
        r4 = 5;
        r4 = new java.lang.Object[r4];	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r5 = 0;
        r6 = r1.getLatitude();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r6 = java.lang.Double.valueOf(r6);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r4[r5] = r6;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r5 = 1;
        r6 = r1.getLongitude();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r6 = java.lang.Double.valueOf(r6);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r4[r5] = r6;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r5 = 2;
        r6 = r1.getAccuracy();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r6 = java.lang.Float.valueOf(r6);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r4[r5] = r6;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r5 = 3;
        r6 = r1.getSpeed();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r6 = java.lang.Float.valueOf(r6);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r4[r5] = r6;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r5 = 4;
        r6 = r1.getTime();	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r1 = java.lang.Long.valueOf(r6);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r4[r5] = r1;	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        r0 = java.lang.String.format(r0, r3, r4);	 Catch:{ SecurityException -> 0x007f, Exception -> 0x0079 }
        goto L_0x001a;
    L_0x0079:
        r0 = move-exception;
    L_0x007a:
        r0 = "";
        goto L_0x001a;
    L_0x007d:
        r1 = move-exception;
        goto L_0x001a;
    L_0x007f:
        r0 = move-exception;
        r0 = r2;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.d.g.b():java.lang.String");
    }
}
