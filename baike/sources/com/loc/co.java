package com.loc;

import android.database.sqlite.SQLiteDatabase;

public class co implements ae {
    public final String a() {
        return "alsn.db";
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS c (_id integer primary key autoincrement, a2 varchar(100), a4 varchar(2000), a3 LONG );");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS b (_id integer primary key autoincrement, b1 integer );");
        } catch (Throwable th) {
            cw.a(th, "SdCardDBCreator", "onCreate");
        }
    }
}
