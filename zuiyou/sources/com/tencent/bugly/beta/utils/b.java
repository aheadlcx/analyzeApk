package com.tencent.bugly.beta.utils;

import android.util.Log;
import java.io.IOException;
import java.util.HashMap;

public class b {
    private String a = null;
    private a b = null;
    private long c = 0;
    private long d = 0;
    private String e = null;
    private final HashMap<Long, String> f = new HashMap();

    public b(String str, long j, long j2) {
        this.f.put(Long.valueOf(1), "armeabi-v4");
        this.f.put(Long.valueOf(2), "armeabi-v4t");
        this.f.put(Long.valueOf(3), "armeabi-v5t");
        this.f.put(Long.valueOf(4), "armeabi-v5te");
        this.f.put(Long.valueOf(5), "armeabi-v5tej");
        this.f.put(Long.valueOf(6), "armeabi-v6");
        this.f.put(Long.valueOf(7), "armeabi-v6kz");
        this.f.put(Long.valueOf(8), "armeabi-v6t2");
        this.f.put(Long.valueOf(9), "armeabi-v6k");
        this.f.put(Long.valueOf(10), "armeabi-v7a");
        this.f.put(Long.valueOf(11), "armeabi-v6-m");
        this.f.put(Long.valueOf(12), "armeabi-v6s-m");
        this.f.put(Long.valueOf(13), "armeabi-v7e-m");
        this.f.put(Long.valueOf(14), "armeabi-v8a");
        this.a = str;
        this.c = j;
        this.d = j2;
    }

    private boolean a() {
        return 0 != this.d;
    }

    private synchronized void b() {
        if (this.b != null) {
            if (this.b.a()) {
                this.b = null;
            }
        }
    }

    private synchronized boolean c() {
        boolean z = false;
        synchronized (this) {
            if (a()) {
                if (this.b != null) {
                    b();
                }
                try {
                    this.b = new a(this.a, this.c);
                    if (this.b.b(this.d)) {
                        z = true;
                    }
                } catch (Exception e) {
                    Log.e("ElfArmAttrParser", e.getMessage());
                }
            }
        }
        return z;
    }

    private synchronized String d() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                char b = (char) this.b.b();
                if (b == '\u0000') {
                    break;
                }
                stringBuilder.append(b);
            } catch (IOException e) {
                Log.e("ElfArmAttrParser", e.getMessage());
                str = null;
            }
        }
        str = stringBuilder.toString();
        return str;
    }

    private String e() {
        return d();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean a(long r8) {
        /*
        r7 = this;
        r2 = 0;
        monitor-enter(r7);
        r0 = r8;
    L_0x0003:
        r4 = 0;
        r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r3 <= 0) goto L_0x0055;
    L_0x0009:
        r3 = r7.b;	 Catch:{ IOException -> 0x0032 }
        r4 = a(r3);	 Catch:{ IOException -> 0x0032 }
        r3 = (int) r4;	 Catch:{ IOException -> 0x0032 }
        switch(r3) {
            case 4: goto L_0x001f;
            case 5: goto L_0x001f;
            case 6: goto L_0x003f;
            case 7: goto L_0x002c;
            case 8: goto L_0x002c;
            case 9: goto L_0x002c;
            case 10: goto L_0x002c;
            case 11: goto L_0x002c;
            case 12: goto L_0x002c;
            case 13: goto L_0x002c;
            case 14: goto L_0x002c;
            case 15: goto L_0x002c;
            case 16: goto L_0x002c;
            case 17: goto L_0x002c;
            case 18: goto L_0x002c;
            case 19: goto L_0x002c;
            case 20: goto L_0x002c;
            case 21: goto L_0x002c;
            case 22: goto L_0x002c;
            case 23: goto L_0x002c;
            case 24: goto L_0x002c;
            case 25: goto L_0x002c;
            case 26: goto L_0x002c;
            case 27: goto L_0x002c;
            case 28: goto L_0x002c;
            case 29: goto L_0x002c;
            case 30: goto L_0x002c;
            case 31: goto L_0x002c;
            case 32: goto L_0x001f;
            case 33: goto L_0x0013;
            case 34: goto L_0x002c;
            case 35: goto L_0x0013;
            case 36: goto L_0x002c;
            case 37: goto L_0x0013;
            case 38: goto L_0x002c;
            case 39: goto L_0x0013;
            case 40: goto L_0x0013;
            case 41: goto L_0x0013;
            case 42: goto L_0x002c;
            case 43: goto L_0x0013;
            case 44: goto L_0x002c;
            case 45: goto L_0x0013;
            case 46: goto L_0x0013;
            case 47: goto L_0x0013;
            case 48: goto L_0x0013;
            case 49: goto L_0x0013;
            case 50: goto L_0x0013;
            case 51: goto L_0x0013;
            case 52: goto L_0x0013;
            case 53: goto L_0x0013;
            case 54: goto L_0x0013;
            case 55: goto L_0x0013;
            case 56: goto L_0x0013;
            case 57: goto L_0x0013;
            case 58: goto L_0x0013;
            case 59: goto L_0x0013;
            case 60: goto L_0x0013;
            case 61: goto L_0x0013;
            case 62: goto L_0x0013;
            case 63: goto L_0x0013;
            case 64: goto L_0x002c;
            case 65: goto L_0x001f;
            case 66: goto L_0x002c;
            case 67: goto L_0x001f;
            case 68: goto L_0x002c;
            case 69: goto L_0x0013;
            case 70: goto L_0x002c;
            default: goto L_0x0013;
        };	 Catch:{ IOException -> 0x0032 }
    L_0x0013:
        r0 = "ElfArmAttrParser";
        r1 = "Unimplemented tag.";
        android.util.Log.e(r0, r1);	 Catch:{ IOException -> 0x0032 }
        r0 = r2;
    L_0x001d:
        monitor-exit(r7);
        return r0;
    L_0x001f:
        r3 = r7.d();	 Catch:{ IOException -> 0x0032 }
        if (r3 == 0) goto L_0x0003;
    L_0x0025:
        r3 = r3.length();	 Catch:{ IOException -> 0x0032 }
        r4 = (long) r3;	 Catch:{ IOException -> 0x0032 }
        r0 = r0 - r4;
        goto L_0x0003;
    L_0x002c:
        r3 = r7.b;	 Catch:{ IOException -> 0x0032 }
        a(r3);	 Catch:{ IOException -> 0x0032 }
        goto L_0x0003;
    L_0x0032:
        r0 = move-exception;
        r1 = "ElfArmAttrParser";
        r0 = r0.getMessage();	 Catch:{ all -> 0x0057 }
        android.util.Log.e(r1, r0);	 Catch:{ all -> 0x0057 }
        r0 = r2;
        goto L_0x001d;
    L_0x003f:
        r0 = r7.b;	 Catch:{ IOException -> 0x0032 }
        r0 = a(r0);	 Catch:{ IOException -> 0x0032 }
        r3 = r7.f;	 Catch:{ IOException -> 0x0032 }
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ IOException -> 0x0032 }
        r0 = r3.get(r0);	 Catch:{ IOException -> 0x0032 }
        r0 = (java.lang.String) r0;	 Catch:{ IOException -> 0x0032 }
        r7.e = r0;	 Catch:{ IOException -> 0x0032 }
        r0 = 1;
        goto L_0x001d;
    L_0x0055:
        r0 = r2;
        goto L_0x001d;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.utils.b.a(long):boolean");
    }

    private synchronized boolean f() {
        boolean z = false;
        synchronized (this) {
            try {
                if (65 == this.b.f()) {
                    long h = this.b.h();
                    String e = e();
                    if (e != null && e.equals("aeabi")) {
                        h -= (long) e.length();
                        while (h > 0) {
                            long h2 = this.b.h() - 5;
                            if (1 == this.b.f()) {
                                z = a(h2);
                                break;
                            }
                            this.b.b(h2);
                        }
                        z = true;
                    }
                }
            } catch (IOException e2) {
                Log.e("ElfArmAttrParser", e2.getMessage());
            }
        }
        return z;
    }

    private boolean g() {
        if (c()) {
            if (!f()) {
                Log.e("ElfArmAttrParser", "Failed to parse elf header");
            }
            b();
            return true;
        }
        b();
        return false;
    }

    public static String a(String str, long j, long j2) {
        b bVar = new b(str, j, j2);
        if (bVar.g()) {
            return bVar.e;
        }
        Log.e("ElfArmAttrParser", "Failed to parse the arch.");
        return null;
    }

    public static synchronized long a(a aVar) throws IOException {
        long j;
        long j2 = 0;
        synchronized (b.class) {
            j = 0;
            byte b;
            do {
                b = aVar.b();
                j |= (((long) b) & 127) << ((int) j2);
                j2 += 7;
            } while ((b & 128) != 0);
        }
        return j;
    }
}
