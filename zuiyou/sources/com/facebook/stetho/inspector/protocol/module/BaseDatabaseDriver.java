package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse;
import java.util.List;

public abstract class BaseDatabaseDriver<DESC> {
    protected Context mContext;

    public interface ExecuteResultHandler<RESULT> {
        RESULT handleInsert(long j) throws SQLiteException;

        RESULT handleRawQuery() throws SQLiteException;

        RESULT handleSelect(Cursor cursor) throws SQLiteException;

        RESULT handleUpdateDelete(int i) throws SQLiteException;
    }

    public abstract ExecuteSQLResponse executeSQL(DESC desc, String str, ExecuteResultHandler<ExecuteSQLResponse> executeResultHandler) throws SQLiteException;

    public abstract List<DESC> getDatabaseNames();

    public abstract List<String> getTableNames(DESC desc);

    public BaseDatabaseDriver(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }
}
