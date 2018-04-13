package com.xiaomi.channel.commonutils.android;

import android.os.Environment;
import com.xiaomi.channel.commonutils.file.a;
import com.xiaomi.channel.commonutils.logger.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class g {
    private static int a = 0;

    public static synchronized boolean a() {
        boolean z = true;
        synchronized (g.class) {
            if (c() != 1) {
                z = false;
            }
        }
        return z;
    }

    public static synchronized boolean b() {
        boolean z;
        synchronized (g.class) {
            z = c() == 2;
        }
        return z;
    }

    public static synchronized int c() {
        InputStream fileInputStream;
        Throwable th;
        int i = 1;
        synchronized (g.class) {
            if (a == 0) {
                try {
                    Properties properties = new Properties();
                    fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                    try {
                        properties.load(fileInputStream);
                        int i2 = (properties.getProperty("ro.miui.ui.version.code", null) == null && properties.getProperty("ro.miui.ui.version.name", null) == null) ? 0 : 1;
                        if (i2 == 0) {
                            i = 2;
                        }
                        a = i;
                        a.a(fileInputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            b.a("get isMIUI failed", th);
                            a = 0;
                            a.a(fileInputStream);
                            b.b("isMIUI's value is: " + a);
                            i = a;
                            return i;
                        } catch (Throwable th3) {
                            th = th3;
                            a.a(fileInputStream);
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileInputStream = null;
                    a.a(fileInputStream);
                    throw th;
                }
                b.b("isMIUI's value is: " + a);
            }
            i = a;
        }
        return i;
    }
}
