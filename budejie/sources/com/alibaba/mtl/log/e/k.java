package com.alibaba.mtl.log.e;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class k {
    static File a = null;
    /* renamed from: a */
    static FileChannel f38a;
    /* renamed from: a */
    static FileLock f39a;

    public static synchronized boolean c(Context context) {
        boolean z = true;
        synchronized (k.class) {
            if (a == null) {
                a = new File(context.getFilesDir() + File.separator + "ap.Lock");
            }
            boolean exists = a.exists();
            if (!exists) {
                try {
                    exists = a.createNewFile();
                } catch (IOException e) {
                }
            }
            if (exists) {
                if (f38a == null) {
                    try {
                        f38a = new RandomAccessFile(a, "rw").getChannel();
                    } catch (Exception e2) {
                        z = false;
                    }
                }
                Object obj;
                try {
                    FileLock tryLock = f38a.tryLock();
                    if (tryLock != null) {
                        f39a = tryLock;
                    } else {
                        FileLock fileLock = tryLock;
                        Log.d("TAG", "mLock:" + obj);
                        z = false;
                    }
                } catch (Throwable th) {
                    obj = null;
                }
            }
        }
        return z;
    }

    public static synchronized void release() {
        synchronized (k.class) {
            if (f39a != null) {
                try {
                    f39a.release();
                    f39a = null;
                } catch (Exception e) {
                    f38a = null;
                } catch (IOException e2) {
                    f39a = null;
                } catch (Throwable th) {
                    f39a = null;
                }
            }
            if (f38a != null) {
                f38a.close();
                f38a = null;
            }
        }
    }
}
