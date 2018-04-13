package com.umeng.analytics.a.a;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.a.g;
import com.umeng.analytics.a.a.d.b;

class c extends SQLiteOpenHelper {
    private static Context b;
    private String a;

    private static class a {
        private static final c a = new c(c.b, d.a(c.b), d.c, null, 1);
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
        this(new e(context, str), str2, cursorFactory, i);
    }

    private c(Context context, String str, CursorFactory cursorFactory, int i) {
        if (str == null || str.equals("")) {
            str = d.c;
        }
        super(context, str, cursorFactory, i);
        b();
    }

    private void b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!(a(com.umeng.analytics.a.a.d.a.a, writableDatabase) && a(com.umeng.analytics.a.a.d.a.b, writableDatabase))) {
                c(writableDatabase);
            }
            if (!a("system", writableDatabase)) {
                b(writableDatabase);
            }
            if (!a(b.a, writableDatabase)) {
                a(writableDatabase);
            }
        } catch (Exception e) {
        }
    }

    public boolean a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        boolean z = false;
        if (str != null) {
            try {
                cursor = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
                if (cursor.moveToNext() && cursor.getInt(0) > 0) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return z;
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
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

    private boolean a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists limitedck(Id INTEGER primary key autoincrement, ck TEXT unique)";
            sQLiteDatabase.execSQL(this.a);
            return true;
        } catch (SQLException e) {
            g.d("create reference table error!");
            return false;
        }
    }

    private boolean b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists system(Id INTEGER primary key autoincrement, key TEXT, timeStamp INTEGER, count INTEGER)";
            sQLiteDatabase.execSQL(this.a);
            return true;
        } catch (SQLException e) {
            g.d("create system table error!");
            return false;
        }
    }

    private boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists aggregated_cache(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.a);
            this.a = "create table if not exists aggregated(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.a);
            return true;
        } catch (SQLException e) {
            g.d("create aggregated table error!");
            return false;
        }
    }
}
