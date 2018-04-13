package com.facebook.stetho.inspector.protocol.module;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver.ExecuteResultHandler;
import com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import java.util.Arrays;
import java.util.Collections;

class Database$1 implements ExecuteResultHandler<ExecuteSQLResponse> {
    final /* synthetic */ Database this$0;

    Database$1(Database database) {
        this.this$0 = database;
    }

    public ExecuteSQLResponse handleRawQuery() throws SQLiteException {
        ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
        executeSQLResponse.columnNames = Collections.singletonList(ANConstants.SUCCESS);
        executeSQLResponse.values = Collections.singletonList("true");
        return executeSQLResponse;
    }

    public ExecuteSQLResponse handleSelect(Cursor cursor) throws SQLiteException {
        ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
        executeSQLResponse.columnNames = Arrays.asList(cursor.getColumnNames());
        executeSQLResponse.values = Database.access$200(cursor, 250);
        return executeSQLResponse;
    }

    public ExecuteSQLResponse handleInsert(long j) throws SQLiteException {
        ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
        executeSQLResponse.columnNames = Collections.singletonList("ID of last inserted row");
        executeSQLResponse.values = Collections.singletonList(String.valueOf(j));
        return executeSQLResponse;
    }

    public ExecuteSQLResponse handleUpdateDelete(int i) throws SQLiteException {
        ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
        executeSQLResponse.columnNames = Collections.singletonList("Modified rows");
        executeSQLResponse.values = Collections.singletonList(String.valueOf(i));
        return executeSQLResponse;
    }
}
