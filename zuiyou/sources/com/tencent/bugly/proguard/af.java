package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.bugly.a;
import com.tencent.bugly.crashreport.common.info.b;
import java.io.File;
import java.util.List;

public class af extends SQLiteOpenHelper {
    public static String a = "bugly_db";
    public static int b = 13;
    protected Context c;
    private List<a> d;

    public af(Context context, List<a> list) {
        StringBuilder append = new StringBuilder().append(a).append("_");
        com.tencent.bugly.crashreport.common.info.a.a(context).getClass();
        super(context, append.append("").toString(), null, b);
        this.c = context;
        this.d = list;
    }

    public synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE IF NOT EXISTS ").append("t_ui").append(" ( ").append("_id").append(" ").append("INTEGER PRIMARY KEY").append(" , ").append("_tm").append(" ").append("int").append(" , ").append("_ut").append(" ").append("int").append(" , ").append("_tp").append(" ").append("int").append(" , ").append("_dt").append(" ").append("blob").append(" , ").append("_pc").append(" ").append("text").append(" ) ");
            an.c(stringBuilder.toString(), new Object[0]);
            sQLiteDatabase.execSQL(stringBuilder.toString(), new String[0]);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE IF NOT EXISTS ").append("t_lr").append(" ( ").append("_id").append(" ").append("INTEGER PRIMARY KEY").append(" , ").append("_tp").append(" ").append("int").append(" , ").append("_tm").append(" ").append("int").append(" , ").append("_pc").append(" ").append("text").append(" , ").append("_th").append(" ").append("text").append(" , ").append("_dt").append(" ").append("blob").append(" ) ");
            an.c(stringBuilder.toString(), new Object[0]);
            sQLiteDatabase.execSQL(stringBuilder.toString(), new String[0]);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE IF NOT EXISTS ").append("t_pf").append(" ( ").append("_id").append(" ").append("integer").append(" , ").append("_tp").append(" ").append("text").append(" , ").append("_tm").append(" ").append("int").append(" , ").append("_dt").append(" ").append("blob").append(",primary key(").append("_id").append(",").append("_tp").append(" )) ");
            an.c(stringBuilder.toString(), new Object[0]);
            sQLiteDatabase.execSQL(stringBuilder.toString(), new String[0]);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE IF NOT EXISTS ").append("t_cr").append(" ( ").append("_id").append(" ").append("INTEGER PRIMARY KEY").append(" , ").append("_tm").append(" ").append("int").append(" , ").append("_s1").append(" ").append("text").append(" , ").append("_up").append(" ").append("int").append(" , ").append("_me").append(" ").append("int").append(" , ").append("_uc").append(" ").append("int").append(" , ").append("_dt").append(" ").append("blob").append(" ) ");
            an.c(stringBuilder.toString(), new Object[0]);
            sQLiteDatabase.execSQL(stringBuilder.toString(), new String[0]);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE IF NOT EXISTS ").append("dl_1002").append(" (").append("_id").append(" integer primary key autoincrement, ").append("_dUrl").append(" varchar(100), ").append("_sFile").append(" varchar(100), ").append("_sLen").append(" INTEGER, ").append("_tLen").append(" INTEGER, ").append("_MD5").append(" varchar(100), ").append("_DLTIME").append(" INTEGER)");
            an.c(stringBuilder.toString(), new Object[0]);
            sQLiteDatabase.execSQL(stringBuilder.toString(), new String[0]);
            stringBuilder.setLength(0);
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ").append("ge_1002").append(" (").append("_id").append(" integer primary key autoincrement, ").append("_time").append(" INTEGER, ").append("_datas").append(" blob)");
            an.c(stringBuilder.toString(), new Object[0]);
            sQLiteDatabase.execSQL(stringBuilder.toString(), new String[0]);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE IF NOT EXISTS ").append("st_1002").append(" ( ").append("_id").append(" ").append("integer").append(" , ").append("_tp").append(" ").append("text").append(" , ").append("_tm").append(" ").append("int").append(" , ").append("_dt").append(" ").append("blob").append(",primary key(").append("_id").append(",").append("_tp").append(" )) ");
            an.c(stringBuilder.toString(), new Object[0]);
            sQLiteDatabase.execSQL(stringBuilder.toString(), new String[0]);
        } catch (Throwable th) {
            if (!an.b(th)) {
                th.printStackTrace();
            }
        }
        if (this.d != null) {
            for (a onDbCreate : this.d) {
                try {
                    onDbCreate.onDbCreate(sQLiteDatabase);
                } catch (Throwable th2) {
                    if (!an.b(th2)) {
                        th2.printStackTrace();
                    }
                }
            }
        }
    }

    protected synchronized boolean a(SQLiteDatabase sQLiteDatabase) {
        boolean z = true;
        synchronized (this) {
            try {
                for (String str : new String[]{"t_lr", "t_ui", "t_pf"}) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str, new String[0]);
                }
            } catch (Throwable th) {
                if (!an.b(th)) {
                    th.printStackTrace();
                }
                z = false;
            }
        }
        return z;
    }

    public synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        an.d("[Database] Upgrade %d to %d , drop tables!", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        if (this.d != null) {
            for (a onDbUpgrade : this.d) {
                try {
                    onDbUpgrade.onDbUpgrade(sQLiteDatabase, i, i2);
                } catch (Throwable th) {
                    if (!an.b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        if (a(sQLiteDatabase)) {
            onCreate(sQLiteDatabase);
        } else {
            an.d("[Database] Failed to drop, delete db.", new Object[0]);
            File databasePath = this.c.getDatabasePath(a);
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }

    @TargetApi(11)
    public synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (b.c() >= 11) {
            an.d("[Database] Downgrade %d to %d drop tables.", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
            if (this.d != null) {
                for (a onDbDowngrade : this.d) {
                    try {
                        onDbDowngrade.onDbDowngrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!an.b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                an.d("[Database] Failed to drop, delete db.", new Object[0]);
                File databasePath = this.c.getDatabasePath(a);
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getReadableDatabase();
                } catch (Throwable th) {
                    an.d("[Database] Try to get db(count: %d).", new Object[]{Integer.valueOf(i)});
                    if (i == 5) {
                        an.e("[Database] Failed to get db.", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sQLiteDatabase;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (Throwable th) {
                    an.d("[Database] Try to get db(count: %d).", new Object[]{Integer.valueOf(i)});
                    if (i == 5) {
                        an.e("[Database] Failed to get db.", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (sQLiteDatabase == null) {
                an.d("[Database] db error delay error record 1min.", new Object[0]);
            }
        }
        return sQLiteDatabase;
    }
}
