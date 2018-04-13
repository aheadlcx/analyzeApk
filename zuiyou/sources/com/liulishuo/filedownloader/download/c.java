package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.g.c.b;
import com.liulishuo.filedownloader.g.c.d;
import com.liulishuo.filedownloader.g.c.e;
import com.liulishuo.filedownloader.g.f;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class c {
    private com.liulishuo.filedownloader.services.c a;
    private com.liulishuo.filedownloader.g.c.a b;
    private b c;
    private e d;
    private com.liulishuo.filedownloader.b.a e;
    private d f;

    private static final class a {
        private static final c a = new c();
    }

    public static c a() {
        return a.a;
    }

    public void a(com.liulishuo.filedownloader.services.c.a aVar) {
        synchronized (this) {
            this.a = new com.liulishuo.filedownloader.services.c(aVar);
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    public com.liulishuo.filedownloader.a.b a(String str) throws IOException {
        return g().a(str);
    }

    public com.liulishuo.filedownloader.f.a a(File file) throws IOException {
        return h().a(file);
    }

    public d b() {
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = i().f();
            }
        }
        return this.f;
    }

    public com.liulishuo.filedownloader.b.a c() {
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = i().b();
                a(this.e.b());
            }
        }
        return this.e;
    }

    public int d() {
        return i().a();
    }

    public boolean e() {
        return h().a();
    }

    public int a(int i, String str, String str2, long j) {
        return f().a(i, str, str2, j);
    }

    private com.liulishuo.filedownloader.g.c.a f() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = i().e();
            }
        }
        return this.b;
    }

    private b g() {
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = i().d();
            }
        }
        return this.c;
    }

    private e h() {
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = i().c();
            }
        }
        return this.d;
    }

    private com.liulishuo.filedownloader.services.c i() {
        if (this.a != null) {
            return this.a;
        }
        synchronized (this) {
            if (this.a == null) {
                this.a = new com.liulishuo.filedownloader.services.c();
            }
        }
        return this.a;
    }

    private static void a(com.liulishuo.filedownloader.b.a.a aVar) {
        Iterator it = aVar.iterator();
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        d b = a().b();
        long currentTimeMillis = System.currentTimeMillis();
        while (it.hasNext()) {
            try {
                long j4;
                Object obj = null;
                com.liulishuo.filedownloader.d.c cVar = (com.liulishuo.filedownloader.d.c) it.next();
                if (cVar.f() == (byte) 3 || cVar.f() == (byte) 2 || cVar.f() == (byte) -1 || (cVar.f() == (byte) 1 && cVar.g() > 0)) {
                    cVar.a((byte) -2);
                }
                String d = cVar.d();
                if (d == null) {
                    obj = 1;
                } else {
                    File file = new File(d);
                    if (cVar.f() == (byte) -2 && f.a(cVar.a(), cVar, cVar.c(), null)) {
                        File file2 = new File(cVar.e());
                        if (!file2.exists() && file.exists()) {
                            boolean renameTo = file.renameTo(file2);
                            if (com.liulishuo.filedownloader.g.d.a) {
                                com.liulishuo.filedownloader.g.d.c(com.liulishuo.filedownloader.b.a.class, "resume from the old no-temp-file architecture [%B], [%s]->[%s]", Boolean.valueOf(renameTo), file.getPath(), file2.getPath());
                            }
                        }
                    }
                    if (cVar.f() == (byte) 1 && cVar.g() <= 0) {
                        obj = 1;
                    } else if (!f.a(cVar.a(), cVar)) {
                        obj = 1;
                    } else if (file.exists()) {
                        obj = 1;
                    }
                }
                long j5;
                if (obj != null) {
                    it.remove();
                    aVar.a(cVar);
                    j4 = 1 + j2;
                    j2 = j;
                    j5 = j4;
                    j4 = j3;
                    j3 = j5;
                } else {
                    int a = cVar.a();
                    int a2 = b.a(a, cVar.b(), cVar.c(), cVar.l());
                    if (a2 != a) {
                        if (com.liulishuo.filedownloader.g.d.a) {
                            com.liulishuo.filedownloader.g.d.c(com.liulishuo.filedownloader.b.a.class, "the id is changed on restoring from db: old[%d] -> new[%d]", Integer.valueOf(a), Integer.valueOf(a2));
                        }
                        cVar.a(a2);
                        aVar.a(a, cVar);
                        j3++;
                    }
                    aVar.b(cVar);
                    j5 = j3;
                    j3 = j2;
                    j2 = 1 + j;
                    j4 = j5;
                }
                j = j2;
                j2 = j3;
                j3 = j4;
            } catch (Throwable th) {
                f.b(com.liulishuo.filedownloader.g.c.a());
                aVar.a();
                if (com.liulishuo.filedownloader.g.d.a) {
                    com.liulishuo.filedownloader.g.d.c(com.liulishuo.filedownloader.b.a.class, "refreshed data count: %d , delete data count: %d, reset id count: %d. consume %d", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            }
        }
        f.b(com.liulishuo.filedownloader.g.c.a());
        aVar.a();
        if (com.liulishuo.filedownloader.g.d.a) {
            com.liulishuo.filedownloader.g.d.c(com.liulishuo.filedownloader.b.a.class, "refreshed data count: %d , delete data count: %d, reset id count: %d. consume %d", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
    }
}
