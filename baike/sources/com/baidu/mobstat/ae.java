package com.baidu.mobstat;

import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;
import java.io.File;

class ae extends ContextWrapper {
    public ae() {
        super(null);
    }

    public File getDatabasePath(String str) {
        if (!"mounted".equals(cu.a())) {
            return null;
        }
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "backups/system";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str2 + File.separator + str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Throwable e) {
                bd.b(e);
            }
        }
        if (!file2.exists()) {
            file2 = null;
        }
        return file2;
    }

    public SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory) {
        File databasePath = getDatabasePath(str);
        if (databasePath != null && databasePath.canWrite()) {
            return SQLiteDatabase.openOrCreateDatabase(databasePath, null);
        }
        throw new RuntimeException("db path is null or path can not be write");
    }

    public SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        File databasePath = getDatabasePath(str);
        if (databasePath != null && databasePath.canWrite()) {
            return SQLiteDatabase.openOrCreateDatabase(databasePath, null);
        }
        throw new RuntimeException("db path is null or path can not be write");
    }
}
