package com.bumptech.glide.load.engine.b;

import android.util.Log;
import com.bumptech.glide.a.a;
import com.bumptech.glide.a.a.c;
import com.bumptech.glide.load.b;
import java.io.File;
import java.io.IOException;

public class e implements a {
    private static e a = null;
    private final c b = new c();
    private final k c;
    private final File d;
    private final int e;
    private a f;

    public static synchronized a a(File file, int i) {
        a aVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e(file, i);
            }
            aVar = a;
        }
        return aVar;
    }

    protected e(File file, int i) {
        this.d = file;
        this.e = i;
        this.c = new k();
    }

    private synchronized a b() throws IOException {
        if (this.f == null) {
            this.f = a.a(this.d, 1, 1, (long) this.e);
        }
        return this.f;
    }

    private synchronized void c() {
        this.f = null;
    }

    public File a(b bVar) {
        File file = null;
        try {
            c a = b().a(this.c.a(bVar));
            if (a != null) {
                file = a.a(0);
            }
        } catch (Throwable e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e);
            }
        }
        return file;
    }

    public void a(b bVar, a.b bVar2) {
        a.a b;
        String a = this.c.a(bVar);
        this.b.a(bVar);
        try {
            b = b().b(a);
            if (b != null) {
                if (bVar2.a(b.a(0))) {
                    b.a();
                }
                b.c();
            }
            this.b.b(bVar);
        } catch (Throwable e) {
            try {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e);
                }
                this.b.b(bVar);
            } catch (Throwable th) {
                this.b.b(bVar);
            }
        } catch (Throwable th2) {
            b.c();
        }
    }

    public void b(b bVar) {
        try {
            b().c(this.c.a(bVar));
        } catch (Throwable e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to delete from disk cache", e);
            }
        }
    }

    public synchronized void a() {
        try {
            b().a();
            c();
        } catch (Throwable e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to clear disk cache", e);
            }
        }
    }
}
