package com.facebook.stetho.inspector.database;

import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver.ExecuteResultHandler;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver;
import com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse;
import com.facebook.stetho.inspector.protocol.module.DatabaseDriver2;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class DatabaseDriver2Adapter extends DatabaseDriver2<DatabaseDriver2Adapter$StringDatabaseDescriptor> {
    private final DatabaseDriver mLegacy;

    public DatabaseDriver2Adapter(DatabaseDriver databaseDriver) {
        super(databaseDriver.getContext());
        this.mLegacy = databaseDriver;
    }

    public List<DatabaseDriver2Adapter$StringDatabaseDescriptor> getDatabaseNames() {
        List<Object> databaseNames = this.mLegacy.getDatabaseNames();
        List<DatabaseDriver2Adapter$StringDatabaseDescriptor> arrayList = new ArrayList(databaseNames.size());
        for (Object obj : databaseNames) {
            arrayList.add(new DatabaseDriver2Adapter$StringDatabaseDescriptor(obj.toString()));
        }
        return arrayList;
    }

    public List<String> getTableNames(DatabaseDriver2Adapter$StringDatabaseDescriptor databaseDriver2Adapter$StringDatabaseDescriptor) {
        return this.mLegacy.getTableNames(databaseDriver2Adapter$StringDatabaseDescriptor.name);
    }

    public ExecuteSQLResponse executeSQL(DatabaseDriver2Adapter$StringDatabaseDescriptor databaseDriver2Adapter$StringDatabaseDescriptor, String str, ExecuteResultHandler executeResultHandler) throws SQLiteException {
        return this.mLegacy.executeSQL(databaseDriver2Adapter$StringDatabaseDescriptor.name, str, executeResultHandler);
    }
}
