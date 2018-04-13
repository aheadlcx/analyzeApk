package com.tencent.smtt.utils;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class y {
    private static y e = null;
    public boolean a = false;
    private Context b = null;
    private File c = null;
    private boolean d = false;
    private File f = null;

    private y(Context context) {
        this.b = context.getApplicationContext();
        b();
    }

    public static synchronized y a() {
        y yVar;
        synchronized (y.class) {
            yVar = e;
        }
        return yVar;
    }

    public static synchronized y a(Context context) {
        y yVar;
        synchronized (y.class) {
            if (e == null) {
                e = new y(context);
            }
            yVar = e;
        }
        return yVar;
    }

    private File d() {
        File file;
        try {
            if (this.c == null) {
                this.c = new File(this.b.getDir("tbs", 0), "core_private");
                if (this.c == null || !this.c.isDirectory()) {
                    return null;
                }
            }
            file = new File(this.c, "debug.conf");
            if (!(file == null || file.exists())) {
                file.createNewFile();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            file = null;
        }
        return file;
    }

    public void a(boolean z) {
        this.d = z;
        c();
    }

    public synchronized void b() {
        Throwable th;
        InputStream inputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            if (this.f == null) {
                this.f = d();
            }
            if (this.f != null) {
                InputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(this.f));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream2);
                    String property = properties.getProperty("setting_forceUseSystemWebview", "");
                    if (!"".equals(property)) {
                        this.a = Boolean.parseBoolean(property);
                    }
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            } else if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        }
    }

    public void c() {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream2;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream3 = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            File d = d();
            if (d == null) {
                try {
                    bufferedInputStream3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    bufferedOutputStream2.close();
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            bufferedInputStream = new BufferedInputStream(new FileInputStream(d));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream);
                properties.setProperty("setting_forceUseSystemWebview", Boolean.toString(this.a));
                properties.setProperty("result_systemWebviewForceUsed", Boolean.toString(this.d));
                bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(d));
                try {
                    properties.store(bufferedOutputStream2, null);
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                    try {
                        bufferedOutputStream2.close();
                    } catch (Exception e222) {
                        e222.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = bufferedOutputStream2;
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream.close();
                bufferedOutputStream.close();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            bufferedInputStream.close();
            bufferedOutputStream.close();
            throw th;
        }
    }
}
