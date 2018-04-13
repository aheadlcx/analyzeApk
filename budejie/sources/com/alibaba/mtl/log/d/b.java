package com.alibaba.mtl.log.d;

import com.alibaba.mtl.log.e.e;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.l;
import com.alibaba.mtl.log.e.n;
import com.alibaba.mtl.log.model.a;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.budejie.www.R$styleable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public abstract class b implements Runnable {
    static int A = 0;
    private static volatile boolean E = false;
    private static boolean F = false;
    int B = -1;
    int C = 0;
    float a = 200.0f;

    public abstract void K();

    public abstract void L();

    public void run() {
        try {
            M();
            K();
        } catch (Throwable th) {
        }
    }

    public static boolean isRunning() {
        return E;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void M() {
        /*
        r18 = this;
        r2 = com.alibaba.mtl.log.e.l.isConnected();
        if (r2 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r2 = F;
        if (r2 != 0) goto L_0x0006;
    L_0x000b:
        r2 = E;
        if (r2 != 0) goto L_0x0006;
    L_0x000f:
        r2 = 1;
        E = r2;
        r3 = 0;
        r2 = com.alibaba.mtl.log.a.d.a();
        r8 = r2.b();
        r2 = 0;
        r7 = r2;
    L_0x001d:
        r2 = 3;
        if (r7 >= r2) goto L_0x0038;
    L_0x0020:
        r6 = 0;
        r2 = com.alibaba.mtl.log.a.getContext();
        r2 = com.alibaba.mtl.log.e.k.c(r2);
        if (r2 != 0) goto L_0x003f;
    L_0x002b:
        r2 = "UploadTask";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = "Other Process is Uploading, break";
        r3[r4] = r5;
        com.alibaba.mtl.log.e.i.a(r2, r3);
    L_0x0038:
        r2 = 0;
        E = r2;
        com.alibaba.mtl.log.e.k.release();
        goto L_0x0006;
    L_0x003f:
        r2 = com.alibaba.mtl.log.c.c.a();
        r2.G();
        r2 = 0;
        if (r8 == 0) goto L_0x021b;
    L_0x0049:
        r4 = r8.size();
        if (r4 <= 0) goto L_0x021b;
    L_0x004f:
        r4 = r3;
    L_0x0050:
        r3 = r8.size();
        if (r4 >= r3) goto L_0x0217;
    L_0x0056:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r4);
        r3 = "";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r2 = r8.get(r2);
        r2 = (com.alibaba.mtl.log.a.c) r2;
        r3 = 0;
        r5 = r2.a;
        if (r5 == 0) goto L_0x00b5;
    L_0x0074:
        r5 = r2.a;
        r5 = r5.size();
        if (r5 <= 0) goto L_0x00b5;
    L_0x007c:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r3 = "eventId";
        r3 = r9.append(r3);
        r5 = " in (";
        r3.append(r5);
        r3 = 0;
        r5 = r3;
    L_0x008e:
        r3 = r2.a;
        r3 = r3.size();
        if (r5 >= r3) goto L_0x00ac;
    L_0x0096:
        if (r5 == 0) goto L_0x009d;
    L_0x0098:
        r3 = " , ";
        r9.append(r3);
    L_0x009d:
        r3 = r2.a;
        r3 = r3.get(r5);
        r3 = (java.lang.String) r3;
        r9.append(r3);
        r3 = r5 + 1;
        r5 = r3;
        goto L_0x008e;
    L_0x00ac:
        r3 = " ) ";
        r9.append(r3);
        r3 = r9.toString();
    L_0x00b5:
        r5 = com.alibaba.mtl.log.c.c.a();
        r9 = r18.h();
        r3 = r5.a(r3, r9);
        r5 = r3.size();
        if (r5 <= 0) goto L_0x00f1;
    L_0x00c7:
        r2 = r2.Q;
        r5 = r4;
        r4 = r2;
        r2 = r3;
    L_0x00cc:
        if (r2 == 0) goto L_0x00d6;
    L_0x00ce:
        if (r2 == 0) goto L_0x0214;
    L_0x00d0:
        r3 = r2.size();
        if (r3 != 0) goto L_0x0214;
    L_0x00d6:
        r2 = com.alibaba.mtl.log.c.c.a();
        r3 = 0;
        r6 = r18.h();
        r2 = r2.a(r3, r6);
        r3 = r2;
    L_0x00e4:
        if (r3 == 0) goto L_0x00ec;
    L_0x00e6:
        r2 = r3.size();
        if (r2 != 0) goto L_0x00f7;
    L_0x00ec:
        r2 = 0;
        E = r2;
        goto L_0x0038;
    L_0x00f1:
        r2 = r4 + 1;
        r4 = r2;
        r2 = r3;
        goto L_0x0050;
    L_0x00f7:
        r0 = r18;
        r6 = r0.b(r3);
        r0 = r18;
        r9 = r0.a(r3);
        if (r9 == 0) goto L_0x010b;
    L_0x0105:
        r2 = r9.size();
        if (r2 != 0) goto L_0x0110;
    L_0x010b:
        r2 = 0;
        E = r2;
        goto L_0x0038;
    L_0x0110:
        r10 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2 = com.alibaba.mtl.log.a.a.M;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r12 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        if (r12 != 0) goto L_0x0135;
    L_0x011c:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2.<init>();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r12 = "http://";
        r2 = r2.append(r12);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r4 = "/rest/sur";
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
    L_0x0135:
        r4 = 0;
        r2 = com.alibaba.mtl.log.e.t.a(r2, r4, r9);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r0 = r18;
        r2 = r0.a(r2, r9);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r4 = r2.G;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r12 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r14 = r12 - r10;
        r9 = java.lang.Boolean.valueOf(r4);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r0 = r18;
        r0.a(r9, r14);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        if (r4 == 0) goto L_0x01dd;
    L_0x0153:
        r2 = com.alibaba.mtl.log.c.c.a();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2 = r2.a(r3);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r9 = r3.size();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r9 = r9 - r6;
        if (r2 >= r9) goto L_0x0165;
    L_0x0162:
        r18.L();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
    L_0x0165:
        com.alibaba.mtl.log.b.a.a(r3, r2);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        com.alibaba.mtl.log.b.a.t();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
    L_0x016b:
        r14 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2 = "UploadTask";
        r9 = 4;
        r9 = new java.lang.Object[r9];	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r16 = 0;
        r17 = "logs.size():";
        r9[r16] = r17;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r16 = 1;
        r3 = r3.size();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r9[r16] = r3;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r3 = 2;
        r16 = " selfMonitorLogCount:";
        r9[r3] = r16;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r3 = 3;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r9[r3] = r6;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        com.alibaba.mtl.log.e.i.a(r2, r9);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2 = "UploadTask";
        r3 = 6;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r6 = 0;
        r9 = "upload isSendSuccess:";
        r3[r6] = r9;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r6 = 1;
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r3[r6] = r4;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r4 = 2;
        r6 = " consume:";
        r3[r4] = r6;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r4 = 3;
        r10 = r12 - r10;
        r6 = java.lang.Long.valueOf(r10);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r3[r4] = r6;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r4 = 4;
        r6 = " delete consume:";
        r3[r4] = r6;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r4 = 5;
        r10 = r14 - r12;
        r6 = java.lang.Long.valueOf(r10);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r3[r4] = r6;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        com.alibaba.mtl.log.e.i.a(r2, r3);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r2 = new java.util.Random;	 Catch:{ Throwable -> 0x0201, all -> 0x020f }
        r2.<init>();	 Catch:{ Throwable -> 0x0201, all -> 0x020f }
        r3 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r2 = r2.nextInt(r3);	 Catch:{ Throwable -> 0x0201, all -> 0x020f }
        r2 = (long) r2;	 Catch:{ Throwable -> 0x0201, all -> 0x020f }
        java.lang.Thread.sleep(r2);	 Catch:{ Throwable -> 0x0201, all -> 0x020f }
    L_0x01d4:
        com.alibaba.mtl.log.e.k.release();
    L_0x01d7:
        r2 = r7 + 1;
        r7 = r2;
        r3 = r5;
        goto L_0x001d;
    L_0x01dd:
        r9 = r3.size();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r9 = r9 - r6;
        com.alibaba.mtl.log.b.a.d(r9);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        com.alibaba.mtl.log.b.a.u();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        r9 = r2.i();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        if (r9 == 0) goto L_0x01f3;
    L_0x01ee:
        com.alibaba.mtl.log.e.k.release();
        goto L_0x0038;
    L_0x01f3:
        r2 = r2.j();	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        if (r2 == 0) goto L_0x016b;
    L_0x01f9:
        r2 = 1;
        F = r2;	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        com.alibaba.mtl.log.e.k.release();
        goto L_0x0038;
    L_0x0201:
        r2 = move-exception;
        r3 = "UploadTask";
        r4 = "thread sleep interrupted";
        com.alibaba.mtl.log.e.i.a(r3, r4, r2);	 Catch:{ Throwable -> 0x020a, all -> 0x020f }
        goto L_0x01d4;
    L_0x020a:
        r2 = move-exception;
        com.alibaba.mtl.log.e.k.release();
        goto L_0x01d7;
    L_0x020f:
        r2 = move-exception;
        com.alibaba.mtl.log.e.k.release();
        throw r2;
    L_0x0214:
        r3 = r2;
        goto L_0x00e4;
    L_0x0217:
        r5 = r4;
        r4 = r6;
        goto L_0x00cc;
    L_0x021b:
        r4 = r6;
        r5 = r3;
        goto L_0x00cc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.d.b.M():void");
    }

    private int b(List<a> list) {
        if (list == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = ((a) list.get(i2)).T;
            if (str != null && "6005".equalsIgnoreCase(str.toString())) {
                i++;
            }
        }
        return i;
    }

    private int h() {
        if (this.B == -1) {
            String u = l.u();
            if (IXAdSystemUtils.NT_WIFI.equalsIgnoreCase(u)) {
                this.B = 20;
            } else if ("4G".equalsIgnoreCase(u)) {
                this.B = 16;
            } else if ("3G".equalsIgnoreCase(u)) {
                this.B = 12;
            } else {
                this.B = 8;
            }
        }
        return this.B;
    }

    private com.alibaba.mtl.log.e.a.a a(String str, Map<String, Object> map) {
        if (str != null) {
            byte[] bArr = e.a(2, str, map, false).e;
            i.a("UploadTask", "url:", str);
            if (bArr != null) {
                String str2;
                try {
                    str2 = new String(bArr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    str2 = null;
                }
                if (str2 != null) {
                    i.a("UploadTask", "result:", str2);
                    return com.alibaba.mtl.log.e.a.a(str2);
                }
            }
        }
        return com.alibaba.mtl.log.e.a.a.a;
    }

    private int a(Boolean bool, long j) {
        if (j < 0) {
            return this.B;
        }
        float f = ((float) this.C) / ((float) j);
        if (!bool.booleanValue()) {
            this.B /= 2;
            A++;
        } else if (j > 45000) {
            return this.B;
        } else {
            this.B = (int) ((((double) (f * 45000.0f)) / ((double) this.a)) - ((double) A));
        }
        if (this.B < 1) {
            this.B = 1;
            A = 0;
        } else if (this.B > R$styleable.Theme_Custom_recyclerview_load_image_background) {
            this.B = R$styleable.Theme_Custom_recyclerview_load_image_background;
        }
        i.a("UploadTask", "winsize:", Integer.valueOf(this.B));
        return this.B;
    }

    private Map<String, Object> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            List a = a((a) list.get(i));
            if (a != null) {
                for (int i2 = 0; i2 < a.size(); i2++) {
                    StringBuilder stringBuilder;
                    StringBuilder stringBuilder2 = (StringBuilder) hashMap.get(a.get(i2));
                    if (stringBuilder2 == null) {
                        stringBuilder2 = new StringBuilder();
                        hashMap.put(a.get(i2), stringBuilder2);
                        stringBuilder = stringBuilder2;
                    } else {
                        stringBuilder2.append("\n");
                        stringBuilder = stringBuilder2;
                    }
                    stringBuilder.append(((a) list.get(i)).h());
                }
            }
        }
        HashMap hashMap2 = new HashMap();
        this.C = 0;
        for (String str : hashMap.keySet()) {
            Object a2 = a(((StringBuilder) hashMap.get(str)).toString());
            hashMap2.put(str, a2);
            this.C += a2.length;
        }
        this.a = ((float) this.C) / ((float) list.size());
        i.a("UploadTask", "averagePackageSize:", Float.valueOf(this.a));
        return hashMap2;
    }

    private byte[] a(String str) {
        IOException e;
        byte[] a;
        Throwable th;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream;
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(str.getBytes("UTF-8"));
                gZIPOutputStream.flush();
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    a = n.a(byteArrayOutputStream.toByteArray(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK");
                    byteArrayOutputStream.close();
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e6) {
            e = e6;
            gZIPOutputStream = null;
            e.printStackTrace();
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            a = n.a(byteArrayOutputStream.toByteArray(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK");
            byteArrayOutputStream.close();
            return a;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = null;
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            throw th;
        }
        a = n.a(byteArrayOutputStream.toByteArray(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK");
        try {
            byteArrayOutputStream.close();
        } catch (Exception e7) {
        }
        return a;
    }

    private List<String> a(a aVar) {
        return com.alibaba.mtl.log.a.a.a(aVar.T);
    }
}
