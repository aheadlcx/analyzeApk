package com.zhihu.matisse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

public class MediasDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "medias.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "MediasDBHelper";

    MediasDBHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, null, null, 1, null);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("CREATE TABLE thumbnail (_id INTEGER PRIMARY KEY AUTOINCREMENT ,orig_id INTEGER NOT NULL ,orig_path TEXT,thumbnail_path TEXT,status INTEGER NOT NULL ,last_generate_time INTEGER NOT NULL );");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.i(TAG, String.format("Upgrading database from version %d to version %d.", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
    }
}
