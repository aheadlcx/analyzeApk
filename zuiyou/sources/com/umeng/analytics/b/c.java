package com.umeng.analytics.b;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.analytics.b.b.b;

class c extends SQLiteOpenHelper {
    private static Context b = null;
    private String a;

    private static class a {
        private static final c a = new c(c.b, e.a(c.b), b.c, null, 1);
    }

    public static synchronized c a(Context context) {
        c a;
        synchronized (c.class) {
            b = context;
            a = a.a;
        }
        return a;
    }

    private c(Context context, String str, String str2, CursorFactory cursorFactory, int i) {
        this(new a(context, str), str2, cursorFactory, i);
    }

    private c(Context context, String str, CursorFactory cursorFactory, int i) {
        if (str == null || str.equals("")) {
            str = b.c;
        }
        super(context, str, cursorFactory, i);
        this.a = null;
        b();
    }

    private void b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!e.a(com.umeng.analytics.b.b.c.a, writableDatabase)) {
                c(writableDatabase);
            }
            if (!e.a(b.a, writableDatabase)) {
                b(writableDatabase);
            }
            if (!e.a(com.umeng.analytics.b.b.a.a, writableDatabase)) {
                a(writableDatabase);
            }
        } catch (Exception e) {
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            c(sQLiteDatabase);
            b(sQLiteDatabase);
            a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException e) {
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException e) {
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException e) {
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
