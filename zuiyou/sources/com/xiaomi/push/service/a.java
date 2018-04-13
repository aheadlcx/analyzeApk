package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.module.PushChannelRegion;
import com.xiaomi.xmpush.thrift.g;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.channels.FileLock;

public class a {
    private static volatile a r;
    private final Object a = new Object();
    private final String b = "mipush_region";
    private final String c = "mipush_region.lock";
    private final String d = "success.";
    private final String e = "fail.";
    private final String f = ".";
    private final Object g = new Object();
    private final Object h = new Object();
    private final Object i = new Object();
    private final Object j = new Object();
    private Context k;
    private String l;
    private String m = null;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;

    public a(Context context) {
        this.k = context;
    }

    public static a a(Context context) {
        if (r == null) {
            synchronized (a.class) {
                if (r == null) {
                    r = new a(context);
                }
            }
        }
        return r;
    }

    private void a(Context context, PushChannelRegion pushChannelRegion) {
        RandomAccessFile randomAccessFile;
        Throwable e;
        FileLock fileLock = null;
        synchronized (this.a) {
            try {
                File file = new File(context.getFilesDir(), "mipush_region.lock");
                com.xiaomi.channel.commonutils.file.a.a(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                    b(context, pushChannelRegion);
                    if (fileLock != null) {
                        if (fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (Throwable e2) {
                                b.a(e2);
                            }
                        }
                    }
                    com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                } catch (Exception e3) {
                    e2 = e3;
                    try {
                        b.a(e2);
                        if (fileLock != null) {
                            if (fileLock.isValid()) {
                                try {
                                    fileLock.release();
                                } catch (Throwable e22) {
                                    b.a(e22);
                                }
                            }
                        }
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                    } catch (Throwable th) {
                        e22 = th;
                        if (fileLock != null) {
                            if (fileLock.isValid()) {
                                try {
                                    fileLock.release();
                                } catch (Throwable e4) {
                                    b.a(e4);
                                }
                            }
                        }
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        throw e22;
                    }
                }
            } catch (Exception e5) {
                e22 = e5;
                randomAccessFile = null;
                b.a(e22);
                if (fileLock != null) {
                    if (fileLock.isValid()) {
                        fileLock.release();
                    }
                }
                com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
            } catch (Throwable th2) {
                e22 = th2;
                randomAccessFile = null;
                if (fileLock != null) {
                    if (fileLock.isValid()) {
                        fileLock.release();
                    }
                }
                com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                throw e22;
            }
        }
    }

    private void a(String str, String str2) {
        if (am.a(this.k).a(g.GlobalRegionIOSwitch.a(), true)) {
            be.a(this.k, str, "region_io", 1, str2);
        }
    }

    private String b(Context context) {
        RandomAccessFile randomAccessFile;
        FileLock lock;
        Throwable e;
        Throwable th;
        Object obj;
        String str = null;
        if (new File(context.getFilesDir(), "mipush_region").exists()) {
            synchronized (this.a) {
                try {
                    File file = new File(context.getFilesDir(), "mipush_region.lock");
                    com.xiaomi.channel.commonutils.file.a.a(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    try {
                        lock = randomAccessFile.getChannel().lock();
                        try {
                            str = c(context);
                            if (lock != null) {
                                if (lock.isValid()) {
                                    try {
                                        lock.release();
                                    } catch (Throwable e2) {
                                        b.a(e2);
                                    }
                                }
                            }
                            com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        } catch (Exception e3) {
                            e2 = e3;
                            try {
                                b.a(e2);
                                if (lock != null) {
                                    if (lock.isValid()) {
                                        try {
                                            lock.release();
                                        } catch (Throwable e22) {
                                            b.a(e22);
                                        }
                                    }
                                }
                                com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                                return str;
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    lock.release();
                                } catch (Throwable e222) {
                                    b.a(e222);
                                }
                                com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                                throw th;
                            }
                        }
                    } catch (Exception e4) {
                        e222 = e4;
                        obj = str;
                        b.a(e222);
                        if (lock != null) {
                            if (lock.isValid()) {
                                lock.release();
                            }
                        }
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        return str;
                    } catch (Throwable e2222) {
                        obj = str;
                        th = e2222;
                        lock.release();
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        throw th;
                    }
                } catch (Exception e5) {
                    e2222 = e5;
                    lock = str;
                    randomAccessFile = str;
                    b.a(e2222);
                    if (lock != null) {
                        if (lock.isValid()) {
                            lock.release();
                        }
                    }
                    com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                    return str;
                } catch (Throwable e22222) {
                    lock = str;
                    randomAccessFile = str;
                    th = e22222;
                    if (lock != null && lock.isValid()) {
                        lock.release();
                    }
                    com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                    throw th;
                }
            }
        }
        b.a("Region no ready file to get data.");
        return str;
    }

    private void b(Context context, PushChannelRegion pushChannelRegion) {
        OutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput("mipush_region", 32768);
            outputStream.write(pushChannelRegion.name().getBytes());
            outputStream.flush();
        } catch (Throwable e) {
            b.a(e);
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.file.a.a(outputStream);
        }
        com.xiaomi.channel.commonutils.file.a.a(outputStream);
    }

    private String c(Context context) {
        InputStream openFileInput;
        Reader bufferedReader;
        String str;
        Throwable e;
        Throwable e2;
        String str2 = "";
        try {
            openFileInput = context.openFileInput("mipush_region");
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(openFileInput));
                str = str2;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    } catch (Exception e3) {
                        e = e3;
                    }
                }
                com.xiaomi.channel.commonutils.file.a.a(openFileInput);
            } catch (Throwable e22) {
                bufferedReader = null;
                e = e22;
                str = str2;
                try {
                    b.a(e);
                    com.xiaomi.channel.commonutils.file.a.a(openFileInput);
                    com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                    return str;
                } catch (Throwable th) {
                    e22 = th;
                    com.xiaomi.channel.commonutils.file.a.a(openFileInput);
                    com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                    throw e22;
                }
            } catch (Throwable th2) {
                e22 = th2;
                bufferedReader = null;
                com.xiaomi.channel.commonutils.file.a.a(openFileInput);
                com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                throw e22;
            }
        } catch (Throwable e222) {
            bufferedReader = null;
            openFileInput = null;
            e = e222;
            str = str2;
            b.a(e);
            com.xiaomi.channel.commonutils.file.a.a(openFileInput);
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            return str;
        } catch (Throwable th3) {
            e222 = th3;
            bufferedReader = null;
            openFileInput = null;
            com.xiaomi.channel.commonutils.file.a.a(openFileInput);
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            throw e222;
        }
        com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
        return str;
    }

    public String a() {
        return a(true);
    }

    public String a(boolean z) {
        if (!this.n) {
            boolean z2;
            new Thread(new b(this)).start();
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this.g) {
                try {
                    this.g.wait((long) am.a(this.k).a(g.GlobalRegionIOWait.a(), 100));
                } catch (InterruptedException e) {
                }
            }
            currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
            this.n = true;
            synchronized (this.i) {
                z2 = this.p;
                this.l = this.m;
            }
            if (z) {
                String str = currentTimeMillis + "." + bb.a(this.k).a("mipush_extra", "mipush_registed", false);
                if (z2) {
                    b.c(this.k.getPackageName() + " get region success.");
                    a("category_region_write", "success." + str);
                } else {
                    b.a(this.k.getPackageName() + " get region fail.");
                    a("category_region_write", "fail." + str);
                }
            }
        }
        return this.l;
    }

    public void a(PushChannelRegion pushChannelRegion) {
        long currentTimeMillis = System.currentTimeMillis();
        int a = am.a(this.k).a(g.GlobalRegionIOWait.a(), 100);
        if (!this.o && TextUtils.isEmpty(a(false))) {
            new Thread(new c(this, pushChannelRegion, currentTimeMillis, a)).start();
            synchronized (this.h) {
                try {
                    this.h.wait((long) a);
                } catch (InterruptedException e) {
                }
            }
            String str = (System.currentTimeMillis() - currentTimeMillis) + "." + bb.a(this.k).a("mipush_extra", "mipush_registed", false);
            if (this.q) {
                b.c(this.k.getPackageName() + " set region success.");
                a("category_region_read", "success." + str);
            } else {
                b.a(this.k.getPackageName() + " set region fail.");
                a("category_region_read", "fail." + str);
            }
            this.o = true;
            this.n = false;
        }
    }
}
