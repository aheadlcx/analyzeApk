package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.c;
import com.xiaomi.channel.commonutils.file.a;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.f;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.List;

public class bf {
    public static final Object a = new Object();

    public static List<f> a(Context context) {
        FileLock lock;
        Throwable e;
        Throwable th;
        File file = new File(context.getFilesDir(), "tiny_data.data");
        if (file.exists()) {
            byte[] b = b(context);
            synchronized (a) {
                RandomAccessFile randomAccessFile;
                try {
                    File file2 = new File(context.getFilesDir(), "tiny_data.lock");
                    a.a(file2);
                    randomAccessFile = new RandomAccessFile(file2, "rw");
                    try {
                        lock = randomAccessFile.getChannel().lock();
                    } catch (Exception e2) {
                        e = e2;
                        lock = null;
                        try {
                            b.a(e);
                            if (lock != null) {
                                if (lock.isValid()) {
                                    try {
                                        lock.release();
                                    } catch (Throwable e3) {
                                        b.a(e3);
                                    }
                                }
                            }
                            a.a(randomAccessFile);
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            if (lock != null) {
                                if (lock.isValid()) {
                                    try {
                                        lock.release();
                                    } catch (Throwable e32) {
                                        b.a(e32);
                                    }
                                }
                            }
                            a.a(randomAccessFile);
                            throw th;
                        }
                    } catch (Throwable e322) {
                        lock = null;
                        th = e322;
                        if (lock != null) {
                            if (lock.isValid()) {
                                lock.release();
                            }
                        }
                        a.a(randomAccessFile);
                        throw th;
                    }
                    try {
                        List<f> a = a(file, b);
                        file.delete();
                        if (lock != null) {
                            if (lock.isValid()) {
                                try {
                                    lock.release();
                                } catch (Throwable th3) {
                                    b.a(th3);
                                }
                            }
                        }
                        a.a(randomAccessFile);
                        return a;
                    } catch (Exception e4) {
                        e322 = e4;
                        b.a(e322);
                        if (lock != null) {
                            if (lock.isValid()) {
                                lock.release();
                            }
                        }
                        a.a(randomAccessFile);
                        return null;
                    }
                } catch (Exception e5) {
                    e322 = e5;
                    lock = null;
                    randomAccessFile = null;
                    b.a(e322);
                    if (lock != null) {
                        if (lock.isValid()) {
                            lock.release();
                        }
                    }
                    a.a(randomAccessFile);
                    return null;
                } catch (Throwable e3222) {
                    lock = null;
                    randomAccessFile = null;
                    th3 = e3222;
                    if (lock != null) {
                        if (lock.isValid()) {
                            lock.release();
                        }
                    }
                    a.a(randomAccessFile);
                    throw th3;
                }
            }
        }
        b.a("TinyData no ready file to get data.");
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.xiaomi.xmpush.thrift.f> a(java.io.File r7, byte[] r8) {
        /*
        r6 = 4;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r0 = new byte[r6];
        r2 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x006c, all -> 0x0069 }
        r1.<init>(r7);	 Catch:{ Exception -> 0x006c, all -> 0x0069 }
    L_0x000e:
        r2 = r1.read(r0);	 Catch:{ Exception -> 0x0022 }
        r4 = -1;
        if (r2 != r4) goto L_0x0019;
    L_0x0015:
        com.xiaomi.channel.commonutils.file.a.a(r1);
        return r3;
    L_0x0019:
        if (r2 == r6) goto L_0x002c;
    L_0x001b:
        r0 = "TinyData read from cache file failed cause lengthBuffer error";
        com.xiaomi.channel.commonutils.logger.b.d(r0);	 Catch:{ Exception -> 0x0022 }
        goto L_0x0015;
    L_0x0022:
        r0 = move-exception;
    L_0x0023:
        com.xiaomi.channel.commonutils.logger.b.a(r0);	 Catch:{ all -> 0x0027 }
        goto L_0x0015;
    L_0x0027:
        r0 = move-exception;
    L_0x0028:
        com.xiaomi.channel.commonutils.file.a.a(r1);
        throw r0;
    L_0x002c:
        r2 = com.xiaomi.channel.commonutils.misc.b.a(r0);	 Catch:{ Exception -> 0x0022 }
        r4 = 1;
        if (r2 < r4) goto L_0x0037;
    L_0x0033:
        r4 = 30720; // 0x7800 float:4.3048E-41 double:1.51777E-319;
        if (r2 <= r4) goto L_0x003e;
    L_0x0037:
        r0 = "TinyData read from cache file failed cause lengthBuffer < 1 || too big";
        com.xiaomi.channel.commonutils.logger.b.d(r0);	 Catch:{ Exception -> 0x0022 }
        goto L_0x0015;
    L_0x003e:
        r4 = new byte[r2];	 Catch:{ Exception -> 0x0022 }
        r5 = r1.read(r4);	 Catch:{ Exception -> 0x0022 }
        if (r5 == r2) goto L_0x004d;
    L_0x0046:
        r0 = "TinyData read from cache file failed cause buffer size not equal length";
        com.xiaomi.channel.commonutils.logger.b.d(r0);	 Catch:{ Exception -> 0x0022 }
        goto L_0x0015;
    L_0x004d:
        r2 = com.xiaomi.channel.commonutils.android.c.a(r8, r4);	 Catch:{ Exception -> 0x0022 }
        if (r2 == 0) goto L_0x0056;
    L_0x0053:
        r4 = r2.length;	 Catch:{ Exception -> 0x0022 }
        if (r4 != 0) goto L_0x005d;
    L_0x0056:
        r2 = "TinyData read from cache file failed cause decrypt fail";
        com.xiaomi.channel.commonutils.logger.b.d(r2);	 Catch:{ Exception -> 0x0022 }
        goto L_0x000e;
    L_0x005d:
        r4 = new com.xiaomi.xmpush.thrift.f;	 Catch:{ Exception -> 0x0022 }
        r4.<init>();	 Catch:{ Exception -> 0x0022 }
        com.xiaomi.xmpush.thrift.au.a(r4, r2);	 Catch:{ Exception -> 0x0022 }
        r3.add(r4);	 Catch:{ Exception -> 0x0022 }
        goto L_0x000e;
    L_0x0069:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0028;
    L_0x006c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.bf.a(java.io.File, byte[]):java.util.List<com.xiaomi.xmpush.thrift.f>");
    }

    public static void a(Context context, f fVar) {
        Throwable e;
        FileLock fileLock = null;
        b.c("TinyData cache data to file begin itemId:" + fVar.m());
        synchronized (a) {
            RandomAccessFile randomAccessFile;
            try {
                File file = new File(context.getFilesDir(), "tiny_data.lock");
                a.a(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                    b(context, fVar);
                    if (fileLock != null) {
                        if (fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (Throwable e2) {
                                b.a(e2);
                            }
                        }
                    }
                    a.a(randomAccessFile);
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
                        a.a(randomAccessFile);
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
                        a.a(randomAccessFile);
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
                a.a(randomAccessFile);
            } catch (Throwable th2) {
                e22 = th2;
                randomAccessFile = null;
                if (fileLock != null) {
                    if (fileLock.isValid()) {
                        fileLock.release();
                    }
                }
                a.a(randomAccessFile);
                throw e22;
            }
        }
    }

    private static byte[] a(String str) {
        byte[] copyOf = Arrays.copyOf(com.xiaomi.channel.commonutils.string.a.a(str), 16);
        copyOf[0] = (byte) 68;
        copyOf[15] = (byte) 84;
        return copyOf;
    }

    private static void b(Context context, f fVar) {
        Throwable e;
        OutputStream bufferedOutputStream;
        try {
            byte[] b = c.b(b(context), au.a(fVar));
            if (b == null || b.length < 1) {
                b.a("TinyData write to cache file failed case encryption fail");
                a.a(null);
            } else if (b.length > 30720) {
                b.a("TinyData write to cache file failed case too much data content");
                a.a(null);
            } else {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(context.getFilesDir(), "tiny_data.data"), true));
                try {
                    bufferedOutputStream.write(com.xiaomi.channel.commonutils.misc.b.a(b.length));
                    bufferedOutputStream.write(b);
                    bufferedOutputStream.flush();
                    a.a(null);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        b.a("TinyData write to cache file failed cause io exception", e);
                        a.a(null);
                        a.a(bufferedOutputStream);
                    } catch (Throwable th) {
                        e = th;
                        a.a(null);
                        a.a(bufferedOutputStream);
                        throw e;
                    }
                } catch (Exception e3) {
                    e = e3;
                    b.a("TinyData write to cache file  failed", e);
                    a.a(null);
                    a.a(bufferedOutputStream);
                }
                a.a(bufferedOutputStream);
            }
            a.a(null);
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream = null;
            b.a("TinyData write to cache file failed cause io exception", e);
            a.a(null);
            a.a(bufferedOutputStream);
        } catch (Exception e5) {
            e = e5;
            bufferedOutputStream = null;
            b.a("TinyData write to cache file  failed", e);
            a.a(null);
            a.a(bufferedOutputStream);
        } catch (Throwable th2) {
            e = th2;
            bufferedOutputStream = null;
            a.a(null);
            a.a(bufferedOutputStream);
            throw e;
        }
    }

    private static byte[] b(Context context) {
        String b = bb.a(context).b("mipush", "td_key", "");
        if (TextUtils.isEmpty(b)) {
            b = d.a(20);
            bb.a(context).a("mipush", "td_key", b);
        }
        return a(b);
    }
}
