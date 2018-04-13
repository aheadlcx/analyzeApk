package com.huawei.hms.update.a;

import android.content.Context;
import com.huawei.hms.update.a.a.a;
import com.huawei.hms.update.a.a.c;
import com.huawei.hms.update.b.b;
import com.huawei.hms.update.b.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class e implements a {
    private final Context a;
    private final d b = new b();
    private com.huawei.hms.update.a.a.b c;
    private String d;
    private c e;

    public e(Context context) {
        this.a = context.getApplicationContext();
    }

    private synchronized void b(com.huawei.hms.update.a.a.b bVar) {
        this.c = bVar;
    }

    private synchronized void a(int i) {
        if (this.c != null) {
            this.c.a(i, this.e);
        }
    }

    public Context a() {
        return this.a;
    }

    public void b() {
        com.huawei.hms.support.log.a.b("OtaUpdateCheck", "Enter cancel.");
        b(null);
        this.b.b();
    }

    public void a(com.huawei.hms.update.a.a.b bVar) {
        com.huawei.hms.c.a.a(bVar, "callback must not be null.");
        com.huawei.hms.support.log.a.b("OtaUpdateCheck", "Enter checkUpdate.");
        b(bVar);
        this.e = new c();
        this.e.a(this.a);
        if (!this.e.a() || this.e.a < 20502300) {
            try {
                c();
                return;
            } catch (com.huawei.hms.update.b.a e) {
                com.huawei.hms.support.log.a.c("OtaUpdateCheck", "In checkUpdate, Canceled to download the update file.");
                a(1101);
                return;
            }
        }
        a(1000);
    }

    public void a(com.huawei.hms.update.a.a.b bVar, c cVar) {
        throw new IllegalStateException("Not supported.");
    }

    private void c() throws com.huawei.hms.update.b.a {
        com.huawei.hms.support.log.a.b("OtaUpdateCheck", "Enter checkUpdate.");
        try {
            int d = d();
            if (d != 200) {
                com.huawei.hms.support.log.a.d("OtaUpdateCheck", "In CheckUpdateHelper.checkUpdate, Check whether has a new version, HTTP code: " + d);
                a(1201);
            } else if (this.d == null) {
                a(1202);
            } else {
                d = e();
                if (d != 200) {
                    com.huawei.hms.support.log.a.d("OtaUpdateCheck", "In CheckUpdateHelper.checkUpdate, Request the update-info of the new version, HTTP code: " + d);
                    a(1201);
                } else if (this.e == null || this.e.a < 20502300) {
                    a(1203);
                } else {
                    this.e.b(this.a);
                    a(1000);
                }
            }
        } catch (IOException e) {
            com.huawei.hms.support.log.a.d("OtaUpdateCheck", "In CheckUpdateHelper.checkUpdate, Failed to check update." + e.getMessage());
            a(1201);
        }
    }

    private int d() throws IOException, com.huawei.hms.update.b.a {
        Throwable th;
        InputStream inputStream = null;
        a aVar = new a(this.a);
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("OtaUpdateCheck", "In doCheckUpdate, Check update params: " + aVar.toString());
        }
        OutputStream byteArrayOutputStream;
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(aVar.a().toString().getBytes(Charset.defaultCharset()));
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    int a = this.b.a("https://query.hicloud.com/hwid/v2/CheckEx.action", byteArrayInputStream, byteArrayOutputStream);
                    if (a != 200) {
                        com.huawei.hms.c.c.a(byteArrayOutputStream);
                        com.huawei.hms.c.c.a(byteArrayInputStream);
                        this.b.a();
                    } else {
                        String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                        if (com.huawei.hms.support.log.a.a()) {
                            com.huawei.hms.support.log.a.a("OtaUpdateCheck", "In CheckUpdateHelper.doCheckUpdate, Check update response: " + str);
                        }
                        this.d = b.a(str).a();
                        com.huawei.hms.c.c.a(byteArrayOutputStream);
                        com.huawei.hms.c.c.a(byteArrayInputStream);
                        this.b.a();
                    }
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = byteArrayInputStream;
                    com.huawei.hms.c.c.a(byteArrayOutputStream);
                    com.huawei.hms.c.c.a(inputStream);
                    this.b.a();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
                inputStream = byteArrayInputStream;
                com.huawei.hms.c.c.a(byteArrayOutputStream);
                com.huawei.hms.c.c.a(inputStream);
                this.b.a();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
            com.huawei.hms.c.c.a(byteArrayOutputStream);
            com.huawei.hms.c.c.a(inputStream);
            this.b.a();
            throw th;
        }
    }

    private int e() throws IOException, com.huawei.hms.update.b.a {
        Throwable th;
        OutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int a = this.b.a(this.d + "full/filelist.xml", byteArrayOutputStream);
                if (a != 200) {
                    com.huawei.hms.c.c.a(byteArrayOutputStream);
                    this.b.a();
                } else {
                    String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    if (com.huawei.hms.support.log.a.a()) {
                        com.huawei.hms.support.log.a.a("OtaUpdateCheck", "In doGetFilelist, Check update response: " + str);
                    }
                    d a2 = d.a(str);
                    this.e = new c(a2.d(), this.d + "full" + "/" + a2.a(), a2.b(), a2.c());
                    com.huawei.hms.c.c.a(byteArrayOutputStream);
                    this.b.a();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                com.huawei.hms.c.c.a(byteArrayOutputStream);
                this.b.a();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            com.huawei.hms.c.c.a(byteArrayOutputStream);
            this.b.a();
            throw th;
        }
    }
}
