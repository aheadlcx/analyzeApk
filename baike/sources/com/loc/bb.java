package com.loc;

import android.database.sqlite.SQLiteDatabase;

public final class bb implements ae {
    private static bb a;

    private bb() {
    }

    public static synchronized bb b() {
        bb bbVar;
        synchronized (bb.class) {
            if (a == null) {
                a = new bb();
            }
            bbVar = a;
        }
        return bbVar;
    }

    public final String a() {
        return "dafile.db";
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sname  varchar(20), fname varchar(100),md varchar(20),version varchar(20),dversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            w.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }
}
