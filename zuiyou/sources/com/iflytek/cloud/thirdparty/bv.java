package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.MemoryFile;
import android.text.TextUtils;
import com.iflytek.cloud.record.d;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class bv {
    private static String a = "";

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String absolutePath = context.getFilesDir().getAbsolutePath();
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        absolutePath = absolutePath + "msclib/";
        File file = new File(absolutePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        a = absolutePath;
        return a;
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean a(MemoryFile memoryFile, long j, String str) {
        FileOutputStream fileOutputStream;
        Throwable e;
        Throwable th;
        boolean z = false;
        if (!(memoryFile == null || TextUtils.isEmpty(str))) {
            try {
                a(str);
                b(str);
                fileOutputStream = new FileOutputStream(str);
                try {
                    byte[] bArr = new byte[65536];
                    int i = 0;
                    while (((long) i) < j) {
                        int i2 = (int) (j - ((long) i) > 65536 ? 65536 : j - ((long) i));
                        memoryFile.readBytes(bArr, i, 0, i2);
                        fileOutputStream.write(bArr, 0, i2);
                        i += i2;
                    }
                    z = true;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        cb.a(e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e4) {
                            }
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileOutputStream = null;
                cb.a(e);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return z;
    }

    public static boolean a(String str, int i) {
        try {
            d dVar = new d(new File(str), i);
            dVar.b();
            dVar.c();
            return true;
        } catch (Throwable e) {
            cb.a(e);
            return false;
        }
    }

    public static boolean a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || "pcm".equals(str)) {
            return true;
        }
        if ("wav".equals(str)) {
            return a(str2, i);
        }
        cb.c("format not supported");
        return false;
    }

    public static boolean a(ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue, String str) {
        Throwable e;
        FileOutputStream fileOutputStream;
        try {
            b(str);
            fileOutputStream = new FileOutputStream(str);
            try {
                Iterator it = concurrentLinkedQueue.iterator();
                while (it.hasNext()) {
                    fileOutputStream.write((byte[]) it.next());
                }
                fileOutputStream.close();
                FileOutputStream fileOutputStream2 = null;
                if (null == null) {
                    return true;
                }
                try {
                    fileOutputStream2.close();
                    return true;
                } catch (Throwable e2) {
                    cb.a(e2);
                    return true;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    cb.a(e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e4) {
                            cb.a(e4);
                            return false;
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    e4 = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e22) {
                            cb.a(e22);
                        }
                    }
                    throw e4;
                }
            }
        } catch (Exception e5) {
            e4 = e5;
            fileOutputStream = null;
            cb.a(e4);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return false;
        } catch (Throwable th2) {
            e4 = th2;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e4;
        }
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (!str.endsWith("/")) {
                file = file.getParentFile();
            }
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
}
