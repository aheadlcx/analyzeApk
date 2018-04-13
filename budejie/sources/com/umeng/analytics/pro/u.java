package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

class u {
    private static u c;
    private static SQLiteOpenHelper d;
    private AtomicInteger a = new AtomicInteger();
    private AtomicInteger b = new AtomicInteger();
    private SQLiteDatabase e;

    u() {
    }

    private static synchronized void b(Context context) {
        synchronized (u.class) {
            if (c == null) {
                c = new u();
                d = t.a(context);
            }
        }
    }

    public static synchronized u a(Context context) {
        u uVar;
        synchronized (u.class) {
            if (c == null) {
                b(context);
            }
            uVar = c;
        }
        return uVar;
    }

    public synchronized SQLiteDatabase a() {
        if (this.a.incrementAndGet() == 1) {
            this.e = d.getWritableDatabase();
        }
        return this.e;
    }

    public synchronized void b() {
        if (this.a.decrementAndGet() == 0) {
            this.e.close();
        }
        if (this.b.decrementAndGet() == 0) {
            this.e.close();
        }
    }
}
