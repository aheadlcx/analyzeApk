package com.tencent.wcdb.database;

import com.tencent.wcdb.Cursor;

public interface SQLiteCursorDriver {
    void cursorClosed();

    void cursorDeactivated();

    void cursorRequeried(Cursor cursor);

    Cursor query(SQLiteDatabase$CursorFactory sQLiteDatabase$CursorFactory, String[] strArr);

    void setBindArguments(String[] strArr);
}
