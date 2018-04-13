package com.alibaba.mtl.log.e;

import java.util.Random;

public class m {
    private static final Random a = new Random();

    public static final String getUniqueID() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nanoTime = (int) System.nanoTime();
        int nextInt = a.nextInt();
        int nextInt2 = a.nextInt();
        Object bytes = f.getBytes(currentTimeMillis);
        Object bytes2 = f.getBytes(nanoTime);
        Object bytes3 = f.getBytes(nextInt);
        Object bytes4 = f.getBytes(nextInt2);
        Object obj = new byte[16];
        System.arraycopy(bytes, 0, obj, 0, 4);
        System.arraycopy(bytes2, 0, obj, 4, 4);
        System.arraycopy(bytes3, 0, obj, 8, 4);
        System.arraycopy(bytes4, 0, obj, 12, 4);
        return c.encodeToString(obj, 2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getImei(android.content.Context r6) {
        /*
        r1 = 0;
        if (r6 == 0) goto L_0x003f;
    L_0x0003:
        r0 = "UTCommon";
        r2 = 0;
        r0 = r6.getSharedPreferences(r0, r2);	 Catch:{ Exception -> 0x002f }
        r2 = "_ie";
        r3 = "";
        r2 = r0.getString(r2, r3);	 Catch:{ Exception -> 0x002f }
        r0 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x002f }
        if (r0 != 0) goto L_0x0030;
    L_0x0018:
        r0 = new java.lang.String;	 Catch:{ Exception -> 0x002f }
        r2 = r2.getBytes();	 Catch:{ Exception -> 0x002f }
        r3 = 2;
        r2 = com.alibaba.mtl.log.e.c.decode(r2, r3);	 Catch:{ Exception -> 0x002f }
        r3 = "UTF-8";
        r0.<init>(r2, r3);	 Catch:{ Exception -> 0x002f }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x002f }
        if (r2 != 0) goto L_0x0030;
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = move-exception;
    L_0x0030:
        r0 = "phone";
        r0 = r6.getSystemService(r0);	 Catch:{ Exception -> 0x0074 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x0074 }
        if (r0 == 0) goto L_0x0078;
    L_0x003a:
        r0 = r0.getDeviceId();	 Catch:{ Exception -> 0x0074 }
    L_0x003e:
        r1 = r0;
    L_0x003f:
        r0 = android.text.TextUtils.isEmpty(r1);
        if (r0 == 0) goto L_0x0076;
    L_0x0045:
        r0 = getUniqueID();
    L_0x0049:
        if (r6 == 0) goto L_0x002e;
    L_0x004b:
        r1 = "UTCommon";
        r2 = 0;
        r1 = r6.getSharedPreferences(r1, r2);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r1 = r1.edit();	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r2 = "_ie";
        r3 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r4 = "UTF-8";
        r4 = r0.getBytes(r4);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r5 = 2;
        r4 = com.alibaba.mtl.log.e.c.encode(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r3.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r1.putString(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r1.commit();	 Catch:{ UnsupportedEncodingException -> 0x006f }
        goto L_0x002e;
    L_0x006f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002e;
    L_0x0074:
        r0 = move-exception;
        goto L_0x003f;
    L_0x0076:
        r0 = r1;
        goto L_0x0049;
    L_0x0078:
        r0 = r1;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.e.m.getImei(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getImsi(android.content.Context r6) {
        /*
        r1 = 0;
        if (r6 == 0) goto L_0x003f;
    L_0x0003:
        r0 = "UTCommon";
        r2 = 0;
        r0 = r6.getSharedPreferences(r0, r2);	 Catch:{ Exception -> 0x002f }
        r2 = "_is";
        r3 = "";
        r2 = r0.getString(r2, r3);	 Catch:{ Exception -> 0x002f }
        r0 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x002f }
        if (r0 != 0) goto L_0x0030;
    L_0x0018:
        r0 = new java.lang.String;	 Catch:{ Exception -> 0x002f }
        r2 = r2.getBytes();	 Catch:{ Exception -> 0x002f }
        r3 = 2;
        r2 = com.alibaba.mtl.log.e.c.decode(r2, r3);	 Catch:{ Exception -> 0x002f }
        r3 = "UTF-8";
        r0.<init>(r2, r3);	 Catch:{ Exception -> 0x002f }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x002f }
        if (r2 != 0) goto L_0x0030;
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = move-exception;
    L_0x0030:
        r0 = "phone";
        r0 = r6.getSystemService(r0);	 Catch:{ Exception -> 0x0074 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x0074 }
        if (r0 == 0) goto L_0x0078;
    L_0x003a:
        r0 = r0.getSubscriberId();	 Catch:{ Exception -> 0x0074 }
    L_0x003e:
        r1 = r0;
    L_0x003f:
        r0 = android.text.TextUtils.isEmpty(r1);
        if (r0 == 0) goto L_0x0076;
    L_0x0045:
        r0 = getUniqueID();
    L_0x0049:
        if (r6 == 0) goto L_0x002e;
    L_0x004b:
        r1 = "UTCommon";
        r2 = 0;
        r1 = r6.getSharedPreferences(r1, r2);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r1 = r1.edit();	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r2 = "_is";
        r3 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r4 = "UTF-8";
        r4 = r0.getBytes(r4);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r5 = 2;
        r4 = com.alibaba.mtl.log.e.c.encode(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r3.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r1.putString(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x006f }
        r1.commit();	 Catch:{ UnsupportedEncodingException -> 0x006f }
        goto L_0x002e;
    L_0x006f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002e;
    L_0x0074:
        r0 = move-exception;
        goto L_0x003f;
    L_0x0076:
        r0 = r1;
        goto L_0x0049;
    L_0x0078:
        r0 = r1;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.e.m.getImsi(android.content.Context):java.lang.String");
    }
}
