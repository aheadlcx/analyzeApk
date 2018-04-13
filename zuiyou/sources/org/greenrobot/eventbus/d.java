package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.a.b;
import org.greenrobot.eventbus.f.a;

public class d {
    private static final ExecutorService m = Executors.newCachedThreadPool();
    boolean a = true;
    boolean b = true;
    boolean c = true;
    boolean d = true;
    boolean e;
    boolean f = true;
    boolean g;
    boolean h;
    ExecutorService i = m;
    List<b> j;
    f k;
    g l;

    d() {
    }

    f a() {
        if (this.k != null) {
            return this.k;
        }
        return (!a.a() || c() == null) ? new f.b() : new a("EventBus");
    }

    g b() {
        if (this.l != null) {
            return this.l;
        }
        if (!a.a()) {
            return null;
        }
        Object c = c();
        return c == null ? null : new g.a((Looper) c);
    }

    Object c() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException e) {
            return null;
        }
    }
}
