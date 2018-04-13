package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.internal.g;
import java.util.HashSet;
import java.util.Set;

public class a {
    private static a a = null;
    private final Set<a> b = new HashSet();
    private final Handler c = new Handler(Looper.getMainLooper());
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            a.c();
            for (a d : this.a.b) {
                d.d();
            }
            this.a.b.clear();
        }
    };

    public interface a {
        void d();
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public void a(a aVar) {
        c();
        if (this.b.add(aVar) && this.b.size() == 1) {
            this.c.post(this.d);
        }
    }

    public void b(a aVar) {
        c();
        this.b.remove(aVar);
    }

    private static void c() {
        g.b(Looper.getMainLooper().getThread() == Thread.currentThread());
    }
}
