package com.tencent.wcdb.database;

import android.os.Environment;
import android.os.StatFs;

public final class SQLiteGlobal {
    private static final String TAG = "WCDB.SQLiteGlobal";
    public static final String defaultJournalMode = "PERSIST";
    public static final int defaultPageSize;
    public static final String defaultSyncMode = "FULL";
    public static final int journalSizeLimit = 524288;
    public static final int walAutoCheckpoint = 100;
    public static final int walConnectionPoolSize = 4;
    public static final String walSyncMode = "FULL";

    private static native int nativeReleaseMemory();

    private static native void nativeSetDefaultPageSize(int i);

    static {
        int blockSize;
        try {
            blockSize = new StatFs(Environment.getDataDirectory().getAbsolutePath()).getBlockSize();
        } catch (RuntimeException e) {
            blockSize = 4096;
        }
        defaultPageSize = blockSize;
        try {
            nativeSetDefaultPageSize(blockSize);
        } catch (UnsatisfiedLinkError e2) {
            System.loadLibrary("wcdb");
            nativeSetDefaultPageSize(blockSize);
        }
    }

    public static void loadLib() {
    }

    private SQLiteGlobal() {
    }

    public static int releaseMemory() {
        return nativeReleaseMemory();
    }
}
