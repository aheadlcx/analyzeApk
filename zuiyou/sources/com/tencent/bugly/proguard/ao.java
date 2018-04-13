package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ao {
    public static boolean a = true;
    private static SimpleDateFormat b;
    private static int c = 5120;
    private static StringBuilder d;
    private static StringBuilder e;
    private static boolean f;
    private static a g;
    private static String h;
    private static String i;
    private static Context j;
    private static String k;
    private static boolean l;
    private static int m;
    private static final Object n = new Object();

    public static class a {
        private boolean a;
        private File b;
        private String c;
        private long d;
        private long e = 30720;

        public a(String str) {
            if (str != null && !str.equals("")) {
                this.c = str;
                this.a = a();
            }
        }

        private boolean a() {
            try {
                this.b = new File(this.c);
                if (!this.b.exists() || this.b.delete()) {
                    if (!this.b.createNewFile()) {
                        this.a = false;
                        return false;
                    }
                    return true;
                }
                this.a = false;
                return false;
            } catch (Throwable th) {
                this.a = false;
            }
        }

        public boolean a(String str) {
            FileOutputStream fileOutputStream;
            FileOutputStream fileOutputStream2;
            Throwable th;
            if (!this.a) {
                return false;
            }
            try {
                fileOutputStream = new FileOutputStream(this.b, true);
                try {
                    byte[] bytes = str.getBytes("UTF-8");
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    this.d += (long) bytes.length;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                        }
                    }
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
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
    }

    static {
        b = null;
        try {
            b = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
        }
    }

    private static boolean b(String str, String str2, String str3) {
        try {
            com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
            if (!(b == null || b.J == null)) {
                return b.J.appendLogToNative(str, str2, str3);
            }
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public static synchronized void a(Context context) {
        synchronized (ao.class) {
            if (!(l || context == null || !a)) {
                try {
                    e = new StringBuilder(0);
                    d = new StringBuilder(0);
                    j = context;
                    com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(context);
                    h = a.e;
                    a.getClass();
                    i = "";
                    k = j.getFilesDir().getPath() + "/" + "buglylog_" + h + "_" + i + ".txt";
                    m = Process.myPid();
                } catch (Throwable th) {
                }
                l = true;
            }
        }
    }

    public static void a(int i) {
        synchronized (n) {
            c = i;
            if (i < 0) {
                c = 0;
            } else if (i > 10240) {
                c = 10240;
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            a(str, str2, message + '\n' + ap.b(th));
        }
    }

    public static synchronized void a(String str, String str2, String str3) {
        synchronized (ao.class) {
            if (l && a) {
                b(str, str2, str3);
                final String a = a(str, str2, str3, (long) Process.myTid());
                synchronized (n) {
                    e.append(a);
                    if (e.length() <= c) {
                    } else if (f) {
                    } else {
                        f = true;
                        am.a().a(new Runnable() {
                            public void run() {
                                synchronized (ao.n) {
                                    try {
                                        if (ao.g == null) {
                                            ao.g = new a(ao.k);
                                        } else if (ao.g.b == null || ao.g.b.length() + ((long) ao.e.length()) > ao.g.e) {
                                            ao.g.a();
                                        }
                                        if (ao.g.a) {
                                            ao.g.a(ao.e.toString());
                                            ao.e.setLength(0);
                                        } else {
                                            ao.e.setLength(0);
                                            ao.e.append(a);
                                        }
                                        ao.f = false;
                                    } catch (Throwable th) {
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private static String a(String str, String str2, String str3, long j) {
        String format;
        d.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        if (b != null) {
            format = b.format(date);
        } else {
            format = date.toString();
        }
        d.append(format).append(" ").append(m).append(" ").append(j).append(" ").append(str).append(" ").append(str2).append(": ").append(str3).append("\u0001\r\n");
        return d.toString();
    }

    public static byte[] a() {
        byte[] bArr = null;
        if (a) {
            synchronized (n) {
                try {
                    File file;
                    if (g == null || !g.a) {
                        file = bArr;
                    } else {
                        file = g.b;
                    }
                    if (e.length() == 0 && file == null) {
                    } else {
                        bArr = ap.a(file, e.toString(), "BuglyLog.txt");
                    }
                } catch (Throwable th) {
                }
            }
        }
        return bArr;
    }
}
