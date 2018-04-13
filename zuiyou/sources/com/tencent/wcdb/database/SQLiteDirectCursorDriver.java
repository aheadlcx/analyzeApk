package com.tencent.wcdb.database;

import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.support.CancellationSignal;

public final class SQLiteDirectCursorDriver implements SQLiteCursorDriver {
    private static SQLiteDatabase$CursorFactory DEFAULT_FACTORY = SQLiteCursor.FACTORY;
    private final CancellationSignal mCancellationSignal;
    private final SQLiteDatabase mDatabase;
    private final String mEditTable;
    private SQLiteProgram mQuery;
    private final String mSql;

    public SQLiteDirectCursorDriver(SQLiteDatabase sQLiteDatabase, String str, String str2, CancellationSignal cancellationSignal) {
        this.mDatabase = sQLiteDatabase;
        this.mEditTable = str2;
        this.mSql = str;
        this.mCancellationSignal = cancellationSignal;
    }

    public Cursor query(SQLiteDatabase$CursorFactory sQLiteDatabase$CursorFactory, String[] strArr) {
        if (sQLiteDatabase$CursorFactory == null) {
            sQLiteDatabase$CursorFactory = DEFAULT_FACTORY;
        }
        SQLiteProgram sQLiteProgram = null;
        try {
            sQLiteProgram = sQLiteDatabase$CursorFactory.newQuery(this.mDatabase, this.mSql, strArr, this.mCancellationSignal);
            Cursor newCursor = sQLiteDatabase$CursorFactory.newCursor(this.mDatabase, this, this.mEditTable, sQLiteProgram);
            this.mQuery = sQLiteProgram;
            return newCursor;
        } catch (RuntimeException e) {
            if (sQLiteProgram != null) {
                sQLiteProgram.close();
            }
            throw e;
        }
    }

    public void cursorClosed() {
    }

    public void setBindArguments(String[] strArr) {
        this.mQuery.bindAllArgsAsStrings(strArr);
    }

    public void cursorDeactivated() {
    }

    public void cursorRequeried(Cursor cursor) {
    }

    public String toString() {
        return "SQLiteDirectCursorDriver: " + this.mSql;
    }
}
