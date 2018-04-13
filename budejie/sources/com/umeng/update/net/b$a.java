package com.umeng.update.net;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import u.upd.b;

class b$a extends SQLiteOpenHelper {
    private static final int b = 2;
    private static final String c = "CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);";
    final /* synthetic */ b a;

    b$a(b bVar, Context context) {
        this.a = bVar;
        super(context, "UMENG_DATA", null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        b.c(b.a(), c);
        sQLiteDatabase.execSQL(c);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
