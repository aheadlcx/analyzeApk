package com.umeng.analytics.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

class d {
    private static d c;
    private static SQLiteOpenHelper d;
    private AtomicInteger a = new AtomicInteger();
    private AtomicInteger b = new AtomicInteger();
    private SQLiteDatabase e;

    d() {
    }

    private static synchronized void b(Context context) {
        synchronized (d.class) {
            if (c == null) {
                c = new d();
                d = c.a(context);
            }
        }
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (c == null) {
                b(context);
            }
            dVar = c;
        }
        return dVar;
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
