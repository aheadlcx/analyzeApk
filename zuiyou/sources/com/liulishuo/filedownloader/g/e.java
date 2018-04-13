package com.liulishuo.filedownloader.g;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class e {
    public final int a;
    public final long b;
    public final boolean c;
    public final boolean d;
    public final int e;
    public final boolean f;
    public final boolean g;
    public final boolean h;

    public static class a {
        private static final e a = new e();
    }

    public static e a() {
        return a.a;
    }

    private e() {
        if (c.a() == null) {
            throw new IllegalStateException("Please invoke the 'FileDownloader#setup' before using FileDownloader. If you want to register some components on FileDownloader please invoke the 'FileDownloader#setupOnApplicationOnCreate' on the 'Application#onCreate' first.");
        }
        String property;
        long currentTimeMillis = System.currentTimeMillis();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = c.a().getAssets().open("filedownloader.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                str = properties.getProperty("http.lenient");
                str2 = properties.getProperty("process.non-separate");
                str3 = properties.getProperty("download.min-progress-step");
                str4 = properties.getProperty("download.min-progress-time");
                str5 = properties.getProperty("download.max-network-thread-count");
                str6 = properties.getProperty("file.non-pre-allocation");
                str7 = properties.getProperty("broadcast.completed");
                property = properties.getProperty("download.trial-connection-head-method");
            } else {
                property = null;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e2) {
            if (!(e2 instanceof FileNotFoundException)) {
                e2.printStackTrace();
            } else if (d.a) {
                d.c(e.class, "not found filedownloader.properties", new Object[0]);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                    property = null;
                } catch (IOException e22) {
                    e22.printStackTrace();
                    property = null;
                }
            } else {
                property = null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (str == null) {
            this.c = false;
        } else if (str.equals("true") || str.equals("false")) {
            this.c = str.equals("true");
        } else {
            throw new IllegalStateException(f.a("the value of '%s' must be '%s' or '%s'", "http.lenient", "true", "false"));
        }
        if (str2 == null) {
            this.d = false;
        } else if (str2.equals("true") || str2.equals("false")) {
            this.d = str2.equals("true");
        } else {
            throw new IllegalStateException(f.a("the value of '%s' must be '%s' or '%s'", "process.non-separate", "true", "false"));
        }
        if (str3 != null) {
            this.a = Math.max(0, Integer.valueOf(str3).intValue());
        } else {
            this.a = 65536;
        }
        if (str4 != null) {
            this.b = Math.max(0, Long.valueOf(str4).longValue());
        } else {
            this.b = 2000;
        }
        if (str5 != null) {
            this.e = a(Integer.valueOf(str5).intValue());
        } else {
            this.e = 3;
        }
        if (str6 == null) {
            this.f = false;
        } else if (str6.equals("true") || str6.equals("false")) {
            this.f = str6.equals("true");
        } else {
            throw new IllegalStateException(f.a("the value of '%s' must be '%s' or '%s'", "file.non-pre-allocation", "true", "false"));
        }
        if (str7 == null) {
            this.g = false;
        } else if (str7.equals("true") || str7.equals("false")) {
            this.g = str7.equals("true");
        } else {
            throw new IllegalStateException(f.a("the value of '%s' must be '%s' or '%s'", "broadcast.completed", "true", "false"));
        }
        if (property == null) {
            this.h = false;
        } else if (property.equals("true") || property.equals("false")) {
            this.h = property.equals("true");
        } else {
            throw new IllegalStateException(f.a("the value of '%s' must be '%s' or '%s'", "download.trial-connection-head-method", "true", "false"));
        }
        if (d.a) {
            d.b(e.class, "init properties %d\n load properties: %s=%B; %s=%B; %s=%d; %s=%d; %s=%d; %s=%B; %s=%B; %s=%B", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "http.lenient", Boolean.valueOf(this.c), "process.non-separate", Boolean.valueOf(this.d), "download.min-progress-step", Integer.valueOf(this.a), "download.min-progress-time", Long.valueOf(this.b), "download.max-network-thread-count", Integer.valueOf(this.e), "file.non-pre-allocation", Boolean.valueOf(this.f), "broadcast.completed", Boolean.valueOf(this.g), "download.trial-connection-head-method", Boolean.valueOf(this.h));
        }
    }

    public static int a(int i) {
        if (i > 12) {
            d.d(e.class, "require the count of network thread  is %d, what is more than the max valid count(%d), so adjust to %d auto", Integer.valueOf(i), Integer.valueOf(12), Integer.valueOf(12));
            return 12;
        } else if (i >= 1) {
            return i;
        } else {
            d.d(e.class, "require the count of network thread  is %d, what is less than the min valid count(%d), so adjust to %d auto", Integer.valueOf(i), Integer.valueOf(1), Integer.valueOf(1));
            return 1;
        }
    }
}
