package com.umeng.analytics.pro;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.analytics.pro.s.b;
import com.umeng.analytics.pro.s.c;

class t extends SQLiteOpenHelper {
    private static Context b = null;
    private String a;

    private static class a {
        private static final t a = new t(t.b, v.a(t.b), s.c, null, 1);

        private a() {
        }
    }

    public static synchronized t a(Context context) {
        t a;
        synchronized (t.class) {
            b = context;
            a = a.a;
        }
        return a;
    }

    private t(Context context, String str, String str2, CursorFactory cursorFactory, int i) {
        this(new r(context, str), str2, cursorFactory, i);
    }

    private t(Context context, String str, CursorFactory cursorFactory, int i) {
        if (str == null || str.equals("")) {
            str = s.c;
        }
        super(context, str, cursorFactory, i);
        this.a = null;
        b();
    }

    private void b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!v.a(c.a, writableDatabase)) {
                c(writableDatabase);
            }
            if (!v.a(b.a, writableDatabase)) {
                b(writableDatabase);
            }
            if (!v.a(com.umeng.analytics.pro.s.a.a, writableDatabase)) {
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
