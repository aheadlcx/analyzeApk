package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class ae {
    private static ae a = null;
    private static Context b = null;

    private ae() {
    }

    static ae a(Context context) {
        if (a == null) {
            synchronized (ae.class) {
                if (a == null) {
                    a = new ae();
                }
            }
        }
        b = context.getApplicationContext();
        return a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Properties e() {
        /*
        r5 = this;
        r1 = 0;
        r0 = r5.a();	 Catch:{ Exception -> 0x0027, all -> 0x003a }
        r2 = new java.util.Properties;	 Catch:{ Exception -> 0x0027, all -> 0x003a }
        r2.<init>();	 Catch:{ Exception -> 0x0027, all -> 0x003a }
        if (r0 == 0) goto L_0x0059;
    L_0x000c:
        if (r2 == 0) goto L_0x0059;
    L_0x000e:
        r3 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x004e, all -> 0x003a }
        r3.<init>(r0);	 Catch:{ Exception -> 0x004e, all -> 0x003a }
        r0 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x004e, all -> 0x003a }
        r0.<init>(r3);	 Catch:{ Exception -> 0x004e, all -> 0x003a }
        r2.load(r0);	 Catch:{ Exception -> 0x0054, all -> 0x0046 }
    L_0x001b:
        if (r0 == 0) goto L_0x0020;
    L_0x001d:
        r0.close();	 Catch:{ IOException -> 0x0022 }
    L_0x0020:
        r0 = r2;
    L_0x0021:
        return r0;
    L_0x0022:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0020;
    L_0x0027:
        r0 = move-exception;
        r2 = r1;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x002c:
        r1.printStackTrace();	 Catch:{ all -> 0x004b }
        if (r2 == 0) goto L_0x0021;
    L_0x0031:
        r2.close();	 Catch:{ IOException -> 0x0035 }
        goto L_0x0021;
    L_0x0035:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0021;
    L_0x003a:
        r0 = move-exception;
    L_0x003b:
        if (r1 == 0) goto L_0x0040;
    L_0x003d:
        r1.close();	 Catch:{ IOException -> 0x0041 }
    L_0x0040:
        throw r0;
    L_0x0041:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0040;
    L_0x0046:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x003b;
    L_0x004b:
        r0 = move-exception;
        r1 = r2;
        goto L_0x003b;
    L_0x004e:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r4;
        goto L_0x002c;
    L_0x0054:
        r1 = move-exception;
        r4 = r2;
        r2 = r0;
        r0 = r4;
        goto L_0x002c;
    L_0x0059:
        r0 = r1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ae.e():java.util.Properties");
    }

    File a() {
        aj.a();
        File file = new File(aj.j(b), "tbscoreinstall.txt");
        if (file == null || file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    void a(int i) {
        a("dexopt_retry_num", i);
    }

    void a(int i, int i2) {
        a("copy_core_ver", i);
        a("copy_status", i2);
    }

    void a(String str) {
        a("install_apk_path", str);
    }

    void a(String str, int i) {
        a(str, String.valueOf(i));
    }

    void a(String str, String str2) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        try {
            Properties e2 = e();
            if (e2 != null) {
                e2.setProperty(str, str2);
                File a = a();
                if (a != null) {
                    fileOutputStream = new FileOutputStream(a);
                    try {
                        e2.store(fileOutputStream, "update " + str + " and status!");
                        fileOutputStream2 = fileOutputStream;
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }
            }
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e42) {
                    e42.printStackTrace();
                }
            }
        } catch (Exception e6) {
            e = e6;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    int b() {
        return c("install_core_ver");
    }

    int b(String str) {
        Properties e = e();
        return (e == null || e.getProperty(str) == null) ? -1 : Integer.parseInt(e.getProperty(str));
    }

    void b(int i) {
        a("unzip_retry_num", i);
    }

    void b(int i, int i2) {
        a("install_core_ver", i);
        a("install_status", i2);
    }

    int c() {
        return b("install_status");
    }

    int c(String str) {
        Properties e = e();
        return (e == null || e.getProperty(str) == null) ? 0 : Integer.parseInt(e.getProperty(str));
    }

    void c(int i) {
        a("incrupdate_status", i);
    }

    int d() {
        return b("incrupdate_status");
    }

    String d(String str) {
        Properties e = e();
        return (e == null || e.getProperty(str) == null) ? null : e.getProperty(str);
    }

    void d(int i) {
        a("unlzma_status", i);
    }
}
