package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

public class b {
    private static b c;
    private static SQLiteOpenHelper d;
    private AtomicInteger a = new AtomicInteger();
    private AtomicInteger b = new AtomicInteger();
    private SQLiteDatabase e;

    private static synchronized void b(Context context) {
        synchronized (b.class) {
            if (c == null) {
                c = new b();
                d = c.a(context);
            }
        }
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                b(context);
            }
            bVar = c;
        }
        return bVar;
    }

    public synchronized SQLiteDatabase a() {
        if (this.a.incrementAndGet() == 1) {
            this.e = d.getReadableDatabase();
        }
        return this.e;
    }

    public synchronized SQLiteDatabase b() {
        if (this.a.incrementAndGet() == 1) {
            this.e = d.getWritableDatabase();
        }
        return this.e;
    }

    public synchronized void c() {
        if (this.a.decrementAndGet() == 0) {
            this.e.close();
        }
        if (this.b.decrementAndGet() == 0) {
            this.e.close();
        }
    }
}
