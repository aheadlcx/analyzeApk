package cn.tatagou.sdk.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class c {
    public static b a;
    private static c d;
    private AtomicInteger b = new AtomicInteger();
    private Semaphore c = new Semaphore(1);
    private SQLiteDatabase e;

    public Semaphore getSemaphore() {
        return this.c;
    }

    public static synchronized void initialize(Context context) {
        synchronized (c.class) {
            if (d == null && context != null) {
                d = new c();
                a = new b(context);
            }
        }
    }

    public static synchronized c getInstance(Context context) {
        c cVar;
        synchronized (c.class) {
            if (d == null && context != null) {
                initialize(context);
            }
            cVar = d;
        }
        return cVar;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (a != null && this.b.incrementAndGet() == 1) {
            this.e = a.getWritableDatabase();
        }
        return this.e;
    }

    public synchronized void closeDatabase() {
        if (this.b.decrementAndGet() == 0 && this.e != null && this.e.isOpen()) {
            this.e.close();
        }
    }
}
