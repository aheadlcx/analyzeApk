package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.misc.h.a;
import com.xiaomi.push.mpcd.b;
import com.xiaomi.push.mpcd.c;
import com.xiaomi.push.mpcd.e;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.d;
import com.xiaomi.xmpush.thrift.k;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public abstract class f extends a {
    protected int c;
    protected Context d;

    public f(Context context, int i) {
        this.c = i;
        this.d = context;
    }

    public static void a(Context context, k kVar) {
        b b = c.a().b();
        Object a = b == null ? "" : b.a();
        if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(kVar.c())) {
            a(context, kVar, a);
        }
    }

    private static void a(Context context, k kVar, String str) {
        RandomAccessFile randomAccessFile;
        FileLock lock;
        IOException e;
        OutputStream outputStream;
        RandomAccessFile randomAccessFile2;
        FileLock fileLock;
        Throwable th;
        OutputStream outputStream2 = null;
        byte[] b = e.b(str, au.a(kVar));
        if (b != null && b.length != 0) {
            synchronized (com.xiaomi.push.mpcd.f.a) {
                try {
                    File file = new File(context.getExternalFilesDir(null), "push_cdata.lock");
                    com.xiaomi.channel.commonutils.file.a.a(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    try {
                        lock = randomAccessFile.getChannel().lock();
                    } catch (IOException e2) {
                        e = e2;
                        outputStream = null;
                        randomAccessFile2 = randomAccessFile;
                        try {
                            e.printStackTrace();
                            if (fileLock != null) {
                                if (fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e3) {
                                    }
                                }
                            }
                            com.xiaomi.channel.commonutils.file.a.a(outputStream);
                            com.xiaomi.channel.commonutils.file.a.a(randomAccessFile2);
                        } catch (Throwable th2) {
                            th = th2;
                            randomAccessFile = randomAccessFile2;
                            lock = fileLock;
                            outputStream2 = outputStream;
                            try {
                                lock.release();
                            } catch (IOException e4) {
                            }
                            com.xiaomi.channel.commonutils.file.a.a(outputStream2);
                            com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        lock = null;
                        lock.release();
                        com.xiaomi.channel.commonutils.file.a.a(outputStream2);
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        throw th;
                    }
                    try {
                        outputStream = new BufferedOutputStream(new FileOutputStream(new File(context.getExternalFilesDir(null), "push_cdata.data"), true));
                        try {
                            outputStream.write(com.xiaomi.channel.commonutils.misc.b.a(b.length));
                            outputStream.write(b);
                            outputStream.flush();
                            if (lock != null) {
                                if (lock.isValid()) {
                                    try {
                                        lock.release();
                                    } catch (IOException e5) {
                                    }
                                }
                            }
                            com.xiaomi.channel.commonutils.file.a.a(outputStream);
                            com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        } catch (IOException e6) {
                            e = e6;
                            fileLock = lock;
                            randomAccessFile2 = randomAccessFile;
                            e.printStackTrace();
                            if (fileLock != null) {
                                if (fileLock.isValid()) {
                                    fileLock.release();
                                }
                            }
                            com.xiaomi.channel.commonutils.file.a.a(outputStream);
                            com.xiaomi.channel.commonutils.file.a.a(randomAccessFile2);
                        } catch (Throwable th4) {
                            th = th4;
                            outputStream2 = outputStream;
                            lock.release();
                            com.xiaomi.channel.commonutils.file.a.a(outputStream2);
                            com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                            throw th;
                        }
                    } catch (IOException e7) {
                        e = e7;
                        outputStream = null;
                        fileLock = lock;
                        randomAccessFile2 = randomAccessFile;
                        e.printStackTrace();
                        if (fileLock != null) {
                            if (fileLock.isValid()) {
                                fileLock.release();
                            }
                        }
                        com.xiaomi.channel.commonutils.file.a.a(outputStream);
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile2);
                    } catch (Throwable th5) {
                        th = th5;
                        lock.release();
                        com.xiaomi.channel.commonutils.file.a.a(outputStream2);
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        throw th;
                    }
                } catch (IOException e8) {
                    e = e8;
                    outputStream = null;
                    randomAccessFile2 = null;
                    e.printStackTrace();
                    if (fileLock != null) {
                        if (fileLock.isValid()) {
                            fileLock.release();
                        }
                    }
                    com.xiaomi.channel.commonutils.file.a.a(outputStream);
                    com.xiaomi.channel.commonutils.file.a.a(randomAccessFile2);
                } catch (Throwable th6) {
                    th = th6;
                    lock = null;
                    randomAccessFile = null;
                    if (lock != null && lock.isValid()) {
                        lock.release();
                    }
                    com.xiaomi.channel.commonutils.file.a.a(outputStream2);
                    com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                    throw th;
                }
            }
        }
    }

    public abstract String b();

    protected boolean c() {
        return true;
    }

    public abstract d d();

    protected boolean e() {
        return com.xiaomi.channel.commonutils.misc.f.a(this.d, String.valueOf(a()), (long) this.c);
    }

    public void run() {
        if (e()) {
            b b = c.a().b();
            Object a = b == null ? "" : b.a();
            if (!TextUtils.isEmpty(a) && c()) {
                String b2 = b();
                if (!TextUtils.isEmpty(b2)) {
                    k kVar = new k();
                    kVar.a(b2);
                    kVar.a(System.currentTimeMillis());
                    kVar.a(d());
                    a(this.d, kVar, a);
                }
            }
        }
    }
}
