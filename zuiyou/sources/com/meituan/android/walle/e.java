package com.meituan.android.walle;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

public final class e {
    public static String a(File file, int i) {
        byte[] b = b(file, i);
        if (b == null) {
            return null;
        }
        try {
            return new String(b, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] b(File file, int i) {
        Map a = a(file);
        if (a == null) {
            return null;
        }
        ByteBuffer byteBuffer = (ByteBuffer) a.get(Integer.valueOf(i));
        if (byteBuffer == null) {
            return null;
        }
        return a(byteBuffer);
    }

    private static byte[] a(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Map<java.lang.Integer, java.nio.ByteBuffer> a(java.io.File r4) {
        /*
        r1 = 0;
        r3 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x0026, all -> 0x0038 }
        r0 = "r";
        r3.<init>(r4, r0);	 Catch:{ IOException -> 0x0026, all -> 0x0038 }
        r2 = r3.getChannel();	 Catch:{ IOException -> 0x005a, all -> 0x0055 }
        r0 = com.meituan.android.walle.a.c(r2);	 Catch:{ IOException -> 0x005e, all -> 0x0058 }
        r0 = r0.a();	 Catch:{ IOException -> 0x005e, all -> 0x0058 }
        r0 = (java.nio.ByteBuffer) r0;	 Catch:{ IOException -> 0x005e, all -> 0x0058 }
        r0 = com.meituan.android.walle.a.a(r0);	 Catch:{ IOException -> 0x005e, all -> 0x0058 }
        if (r2 == 0) goto L_0x0020;
    L_0x001d:
        r2.close();	 Catch:{ IOException -> 0x0049, SignatureNotFoundException -> 0x0053 }
    L_0x0020:
        if (r3 == 0) goto L_0x0025;
    L_0x0022:
        r3.close();	 Catch:{ IOException -> 0x004b, SignatureNotFoundException -> 0x0053 }
    L_0x0025:
        return r0;
    L_0x0026:
        r0 = move-exception;
        r0 = r1;
        r2 = r1;
    L_0x0029:
        if (r0 == 0) goto L_0x002e;
    L_0x002b:
        r0.close();	 Catch:{ IOException -> 0x004d }
    L_0x002e:
        if (r2 == 0) goto L_0x0033;
    L_0x0030:
        r2.close();	 Catch:{ IOException -> 0x0035 }
    L_0x0033:
        r0 = r1;
        goto L_0x0025;
    L_0x0035:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0025;
    L_0x0038:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x003b:
        if (r2 == 0) goto L_0x0040;
    L_0x003d:
        r2.close();	 Catch:{ IOException -> 0x004f }
    L_0x0040:
        if (r3 == 0) goto L_0x0045;
    L_0x0042:
        r3.close();	 Catch:{ IOException -> 0x0051 }
    L_0x0045:
        throw r0;	 Catch:{ SignatureNotFoundException -> 0x0046 }
    L_0x0046:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0025;
    L_0x0049:
        r1 = move-exception;
        goto L_0x0020;
    L_0x004b:
        r1 = move-exception;
        goto L_0x0025;
    L_0x004d:
        r0 = move-exception;
        goto L_0x002e;
    L_0x004f:
        r2 = move-exception;
        goto L_0x0040;
    L_0x0051:
        r2 = move-exception;
        goto L_0x0045;
    L_0x0053:
        r1 = move-exception;
        goto L_0x0025;
    L_0x0055:
        r0 = move-exception;
        r2 = r1;
        goto L_0x003b;
    L_0x0058:
        r0 = move-exception;
        goto L_0x003b;
    L_0x005a:
        r0 = move-exception;
        r0 = r1;
        r2 = r3;
        goto L_0x0029;
    L_0x005e:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.android.walle.e.a(java.io.File):java.util.Map<java.lang.Integer, java.nio.ByteBuffer>");
    }
}
