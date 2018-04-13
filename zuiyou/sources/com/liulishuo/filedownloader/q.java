package com.liulishuo.filedownloader;

import android.app.Application;
import com.liulishuo.filedownloader.a.b;
import com.liulishuo.filedownloader.g.c;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.f;
import java.io.File;

public class q {
    private static final Object a = new Object();
    private static final Object c = new Object();
    private v b;
    private u d;

    private static final class a {
        private static final q a = new q();
    }

    public static com.liulishuo.filedownloader.services.c.a a(Application application) {
        c.a(application.getApplicationContext());
        com.liulishuo.filedownloader.services.c.a aVar = new com.liulishuo.filedownloader.services.c.a();
        com.liulishuo.filedownloader.download.c.a().a(aVar);
        return aVar;
    }

    public static q a() {
        return a.a;
    }

    public a a(String str) {
        return new c(str);
    }

    public byte a(int i, String str) {
        byte b;
        b b2 = h.a().b(i);
        if (b2 == null) {
            b = m.a().b(i);
        } else {
            b = b2.y().q();
        }
        if (str != null && b == (byte) 0 && f.c(c.a()) && new File(str).exists()) {
            return (byte) -3;
        }
        return b;
    }

    public void b() {
        if (!c()) {
            m.a().a(c.a());
        }
    }

    public boolean c() {
        return m.a().c();
    }

    public void a(e eVar) {
        f.a().a("event.service.connect.changed", (com.liulishuo.filedownloader.event.c) eVar);
    }

    public boolean a(int i) {
        if (h.a().b()) {
            return m.a().c(i);
        }
        d.d(this, "Can't change the max network thread count, because there are actively executing tasks in FileDownloader, please try again after all actively executing tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
        return false;
    }

    v d() {
        if (this.b == null) {
            synchronized (a) {
                if (this.b == null) {
                    this.b = new aa();
                }
            }
        }
        return this.b;
    }

    u e() {
        if (this.d == null) {
            synchronized (c) {
                if (this.d == null) {
                    this.d = new y();
                    a((e) this.d);
                }
            }
        }
        return this.d;
    }
}
