package com.tencent.wcdb.database;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import java.util.HashSet;

public class SQLiteAsyncCheckpointer implements Callback, SQLiteCheckpointListener {
    private static final int DEFAULT_BLOCKING_THRESHOLD = 100;
    private static final int DEFAULT_THRESHOLD = 0;
    private static HandlerThread gDefaultThread;
    private static final Object gDefaultThreadLock = new Object();
    private static int gDefaultThreadRefCount = 0;
    private int mBlockingThreshold;
    private Handler mHandler;
    private int mLastSyncMode;
    private Looper mLooper;
    private final HashSet<Pair<SQLiteDatabase, String>> mPendingCheckpoints;
    private int mThreshold;
    private boolean mUseDefaultLooper;

    public SQLiteAsyncCheckpointer() {
        this(null, 0, 100);
    }

    public SQLiteAsyncCheckpointer(Looper looper) {
        this(looper, 0, 100);
    }

    public SQLiteAsyncCheckpointer(Looper looper, int i, int i2) {
        this.mLooper = looper;
        this.mThreshold = i;
        this.mBlockingThreshold = i2;
        this.mPendingCheckpoints = new HashSet();
    }

    public void onAttach(SQLiteDatabase sQLiteDatabase) {
        if (this.mLooper == null) {
            this.mLooper = acquireDefaultLooper();
            this.mUseDefaultLooper = true;
        }
        this.mHandler = new Handler(this.mLooper, this);
        this.mLastSyncMode = sQLiteDatabase.getSynchronousMode();
        sQLiteDatabase.setSynchronousMode(1);
    }

    public void onWALCommit(SQLiteDatabase sQLiteDatabase, String str, int i) {
        int i2 = 1;
        if (i >= this.mThreshold) {
            boolean add;
            int i3 = i >= this.mBlockingThreshold ? 1 : 0;
            Pair pair = new Pair(sQLiteDatabase, str);
            synchronized (this.mPendingCheckpoints) {
                add = this.mPendingCheckpoints.add(pair);
            }
            if (add) {
                sQLiteDatabase.acquireReference();
                Handler handler = this.mHandler;
                if (i3 == 0) {
                    i2 = 0;
                }
                this.mHandler.sendMessage(handler.obtainMessage(0, i2, 0, pair));
            }
        }
    }

    public void onDetach(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.setSynchronousMode(this.mLastSyncMode);
        this.mHandler = null;
        if (this.mUseDefaultLooper) {
            this.mLooper = null;
            releaseDefaultLooper();
            this.mUseDefaultLooper = false;
        }
    }

    public boolean handleMessage(Message message) {
        Pair pair = (Pair) message.obj;
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) pair.first;
        String str = (String) pair.second;
        boolean z = message.arg1 != 0;
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            Pair walCheckpoint = sQLiteDatabase.walCheckpoint(str, z);
            onCheckpointResult(sQLiteDatabase, ((Integer) walCheckpoint.first).intValue(), ((Integer) walCheckpoint.second).intValue(), SystemClock.uptimeMillis() - uptimeMillis);
            synchronized (this.mPendingCheckpoints) {
                if (this.mPendingCheckpoints.remove(pair)) {
                } else {
                    throw new AssertionError("mPendingCheckpoints.remove(p)");
                }
            }
            return true;
        } finally {
            sQLiteDatabase.releaseReference();
        }
    }

    protected void onCheckpointResult(SQLiteDatabase sQLiteDatabase, int i, int i2, long j) {
    }

    private static Looper acquireDefaultLooper() {
        Looper looper;
        synchronized (gDefaultThreadLock) {
            int i = gDefaultThreadRefCount;
            gDefaultThreadRefCount = i + 1;
            if (i == 0) {
                if (gDefaultThread != null) {
                    throw new AssertionError("gDefaultThread == null");
                }
                gDefaultThread = new HandlerThread("WCDB.AsyncCheckpointer", 4);
                gDefaultThread.start();
            }
            looper = gDefaultThread.getLooper();
        }
        return looper;
    }

    private static void releaseDefaultLooper() {
        synchronized (gDefaultThreadLock) {
            int i = gDefaultThreadRefCount - 1;
            gDefaultThreadRefCount = i;
            if (i <= 0) {
                if (gDefaultThreadRefCount < 0) {
                    throw new AssertionError("gDefaultThreadRefCount == 0");
                }
                gDefaultThread.quit();
                gDefaultThread = null;
            }
        }
    }
}
