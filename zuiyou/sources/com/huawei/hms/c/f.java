package com.huawei.hms.c;

import com.huawei.hms.support.log.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class f {
    public static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            a.d("SHA256", "NoSuchAlgorithmException" + e.getMessage());
            return new byte[0];
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.io.File r8) {
        /*
        r2 = 0;
        r0 = 0;
        r1 = "SHA-256";
        r3 = java.security.MessageDigest.getInstance(r1);	 Catch:{ NoSuchAlgorithmException -> 0x004f, IOException -> 0x0053, all -> 0x0045 }
        r1 = new java.io.BufferedInputStream;	 Catch:{ NoSuchAlgorithmException -> 0x004f, IOException -> 0x0055, all -> 0x0045 }
        r4 = new java.io.FileInputStream;	 Catch:{ NoSuchAlgorithmException -> 0x004f, IOException -> 0x0057, all -> 0x0045 }
        r4.<init>(r8);	 Catch:{ NoSuchAlgorithmException -> 0x004f, IOException -> 0x0059, all -> 0x0045 }
        r1.<init>(r4);	 Catch:{ NoSuchAlgorithmException -> 0x004f, IOException -> 0x005b, all -> 0x0045 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r4 = new byte[r0];	 Catch:{ NoSuchAlgorithmException -> 0x0025, IOException -> 0x005d }
        r0 = r2;
    L_0x0018:
        r5 = r1.read(r4);	 Catch:{ NoSuchAlgorithmException -> 0x0025, IOException -> 0x0060 }
        r6 = -1;
        if (r5 == r6) goto L_0x0037;
    L_0x001f:
        r0 = r0 + r5;
        r6 = 0;
        r3.update(r4, r6, r5);	 Catch:{ NoSuchAlgorithmException -> 0x0025, IOException -> 0x0063 }
        goto L_0x0018;
    L_0x0025:
        r0 = move-exception;
        r0 = r1;
    L_0x0027:
        r1 = r0;
        r0 = "SHA256";
        r3 = "An exception occurred while computing file 'SHA-256'.";
        com.huawei.hms.support.log.a.d(r0, r3);	 Catch:{ all -> 0x004d }
        com.huawei.hms.c.c.a(r1);
    L_0x0034:
        r0 = new byte[r2];
    L_0x0036:
        return r0;
    L_0x0037:
        if (r0 <= 0) goto L_0x0041;
    L_0x0039:
        r0 = r3.digest();	 Catch:{ NoSuchAlgorithmException -> 0x0025, IOException -> 0x0066 }
        com.huawei.hms.c.c.a(r1);
        goto L_0x0036;
    L_0x0041:
        com.huawei.hms.c.c.a(r1);
        goto L_0x0034;
    L_0x0045:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0049:
        com.huawei.hms.c.c.a(r1);
        throw r0;
    L_0x004d:
        r0 = move-exception;
        goto L_0x0049;
    L_0x004f:
        r1 = move-exception;
        goto L_0x0027;
    L_0x0051:
        r1 = move-exception;
        goto L_0x0027;
    L_0x0053:
        r1 = move-exception;
        goto L_0x0027;
    L_0x0055:
        r1 = move-exception;
        goto L_0x0027;
    L_0x0057:
        r1 = move-exception;
        goto L_0x0027;
    L_0x0059:
        r1 = move-exception;
        goto L_0x0027;
    L_0x005b:
        r1 = move-exception;
        goto L_0x0027;
    L_0x005d:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0027;
    L_0x0060:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0027;
    L_0x0063:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0027;
    L_0x0066:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.c.f.a(java.io.File):byte[]");
    }
}
