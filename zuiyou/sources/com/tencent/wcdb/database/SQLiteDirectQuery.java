package com.tencent.wcdb.database;

import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.Log;

public class SQLiteDirectQuery extends SQLiteProgram {
    private static final int[] SQLITE_TYPE_MAPPING = new int[]{3, 1, 2, 3, 4, 0};
    private static final String TAG = "WCDB.SQLiteDirectQuery";
    private final CancellationSignal mCancellationSignal;

    private static native byte[] nativeGetBlob(long j, int i);

    private static native double nativeGetDouble(long j, int i);

    private static native long nativeGetLong(long j, int i);

    private static native String nativeGetString(long j, int i);

    private static native int nativeGetType(long j, int i);

    private static native int nativeStep(long j, int i);

    public SQLiteDirectQuery(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr, CancellationSignal cancellationSignal) {
        super(sQLiteDatabase, str, objArr, cancellationSignal);
        this.mCancellationSignal = cancellationSignal;
    }

    public long getLong(int i) {
        return nativeGetLong(this.mPreparedStatement.a(), i);
    }

    public double getDouble(int i) {
        return nativeGetDouble(this.mPreparedStatement.a(), i);
    }

    public String getString(int i) {
        return nativeGetString(this.mPreparedStatement.a(), i);
    }

    public byte[] getBlob(int i) {
        return nativeGetBlob(this.mPreparedStatement.a(), i);
    }

    public int getType(int i) {
        return SQLITE_TYPE_MAPPING[nativeGetType(this.mPreparedStatement.a(), i)];
    }

    public int step(int i) {
        try {
            if (acquirePreparedStatement()) {
                this.mPreparedStatement.a("directQuery", getBindArgs());
                this.mPreparedStatement.a(this.mCancellationSignal);
            }
            return nativeStep(this.mPreparedStatement.a(), i);
        } catch (Exception e) {
            if (e instanceof SQLiteDatabaseCorruptException) {
                onCorruption();
            } else if (e instanceof SQLiteException) {
                Log.e(TAG, "Got exception on stepping: " + e.getMessage() + ", SQL: " + getSql());
            }
            if (this.mPreparedStatement != null) {
                this.mPreparedStatement.b(this.mCancellationSignal);
                this.mPreparedStatement.a(e);
            }
            releasePreparedStatement();
            throw e;
        }
    }

    public synchronized void reset(boolean z) {
        if (this.mPreparedStatement != null) {
            this.mPreparedStatement.a(false);
            if (z) {
                this.mPreparedStatement.b(this.mCancellationSignal);
                this.mPreparedStatement.a(null);
                releasePreparedStatement();
            }
        }
    }

    protected void onAllReferencesReleased() {
        synchronized (this) {
            if (this.mPreparedStatement != null) {
                this.mPreparedStatement.b(this.mCancellationSignal);
                this.mPreparedStatement.a(null);
            }
        }
        super.onAllReferencesReleased();
    }
}
