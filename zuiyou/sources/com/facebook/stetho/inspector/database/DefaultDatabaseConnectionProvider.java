package com.facebook.stetho.inspector.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.database.SQLiteDatabaseCompat.SQLiteOpenOptions;
import java.io.File;

public class DefaultDatabaseConnectionProvider implements DatabaseConnectionProvider {
    public SQLiteDatabase openDatabase(File file) throws SQLiteException {
        return performOpen(file, determineOpenOptions(file));
    }

    @SQLiteOpenOptions
    protected int determineOpenOptions(File file) {
        if (new File(file.getParent(), file.getName() + "-wal").exists()) {
            return 1;
        }
        return 0;
    }

    protected SQLiteDatabase performOpen(File file, @SQLiteOpenOptions int i) {
        SQLiteDatabaseCompat instance = SQLiteDatabaseCompat.getInstance();
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 0 | instance.provideOpenFlags(i));
        instance.enableFeatures(i, openDatabase);
        return openDatabase;
    }
}
