package com.facebook.common.statfs;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.k;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class StatFsHelper {
    private static StatFsHelper a;
    private static final long b = TimeUnit.MINUTES.toMillis(2);
    private volatile StatFs c = null;
    private volatile File d;
    private volatile StatFs e = null;
    private volatile File f;
    @GuardedBy
    private long g;
    private final Lock h = new ReentrantLock();
    private volatile boolean i = false;

    public enum StorageType {
        INTERNAL,
        EXTERNAL
    }

    public static synchronized StatFsHelper a() {
        StatFsHelper statFsHelper;
        synchronized (StatFsHelper.class) {
            if (a == null) {
                a = new StatFsHelper();
            }
            statFsHelper = a;
        }
        return statFsHelper;
    }

    protected StatFsHelper() {
    }

    private void b() {
        if (!this.i) {
            this.h.lock();
            try {
                if (!this.i) {
                    this.d = Environment.getDataDirectory();
                    this.f = Environment.getExternalStorageDirectory();
                    d();
                    this.i = true;
                }
                this.h.unlock();
            } catch (Throwable th) {
                this.h.unlock();
            }
        }
    }

    public boolean a(StorageType storageType, long j) {
        b();
        long a = a(storageType);
        if (a <= 0 || a < j) {
            return true;
        }
        return false;
    }

    @SuppressLint({"DeprecatedMethod"})
    public long a(StorageType storageType) {
        b();
        c();
        StatFs statFs = storageType == StorageType.INTERNAL ? this.c : this.e;
        if (statFs == null) {
            return 0;
        }
        long blockSizeLong;
        long availableBlocksLong;
        if (VERSION.SDK_INT >= 18) {
            blockSizeLong = statFs.getBlockSizeLong();
            availableBlocksLong = statFs.getAvailableBlocksLong();
        } else {
            blockSizeLong = (long) statFs.getBlockSize();
            availableBlocksLong = (long) statFs.getAvailableBlocks();
        }
        return availableBlocksLong * blockSizeLong;
    }

    private void c() {
        if (this.h.tryLock()) {
            try {
                if (SystemClock.uptimeMillis() - this.g > b) {
                    d();
                }
                this.h.unlock();
            } catch (Throwable th) {
                this.h.unlock();
            }
        }
    }

    @GuardedBy
    private void d() {
        this.c = a(this.c, this.d);
        this.e = a(this.e, this.f);
        this.g = SystemClock.uptimeMillis();
    }

    private StatFs a(@Nullable StatFs statFs, @Nullable File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        if (statFs == null) {
            try {
                return a(file.getAbsolutePath());
            } catch (IllegalArgumentException e) {
                return null;
            } catch (Throwable th) {
                RuntimeException b = k.b(th);
            }
        } else {
            statFs.restat(file.getAbsolutePath());
            return statFs;
        }
    }

    protected static StatFs a(String str) {
        return new StatFs(str);
    }
}
