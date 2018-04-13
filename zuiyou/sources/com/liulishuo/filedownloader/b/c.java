package com.liulishuo.filedownloader.b;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.liulishuo.filedownloader.d.a;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.e;
import com.liulishuo.filedownloader.g.f;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class c implements a {
    private final b a = new b();
    private final d b = new d();
    private Handler c;
    private final long d = e.a().b;
    private final List<Integer> e = new ArrayList();
    private AtomicInteger f = new AtomicInteger();
    private volatile Thread g;

    public c() {
        HandlerThread handlerThread = new HandlerThread(f.j("RemitHandoverToDB"));
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper(), new Callback(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i != 0) {
                    try {
                        this.a.f.set(i);
                        this.a.g(i);
                        this.a.e.add(Integer.valueOf(i));
                        this.a.f.set(0);
                        if (this.a.g != null) {
                            LockSupport.unpark(this.a.g);
                            this.a.g = null;
                        }
                    } catch (Throwable th) {
                        this.a.f.set(0);
                        if (this.a.g != null) {
                            LockSupport.unpark(this.a.g);
                            this.a.g = null;
                        }
                    }
                } else if (this.a.g != null) {
                    LockSupport.unpark(this.a.g);
                    this.a.g = null;
                }
                return false;
            }
        });
    }

    private void g(int i) {
        if (d.a) {
            d.c(this, "sync cache to db %d", Integer.valueOf(i));
        }
        this.b.b(this.a.b(i));
        List<a> c = this.a.c(i);
        this.b.d(i);
        for (a a : c) {
            this.b.a(a);
        }
    }

    private boolean h(int i) {
        return !this.e.contains(Integer.valueOf(i));
    }

    public void a(int i) {
        this.c.sendEmptyMessageDelayed(i, this.d);
    }

    public com.liulishuo.filedownloader.d.c b(int i) {
        return this.a.b(i);
    }

    public List<a> c(int i) {
        return this.a.c(i);
    }

    public void d(int i) {
        this.a.d(i);
        if (!h(i)) {
            this.b.d(i);
        }
    }

    public void a(a aVar) {
        this.a.a(aVar);
        if (!h(aVar.a())) {
            this.b.a(aVar);
        }
    }

    public void a(int i, int i2, long j) {
        this.a.a(i, i2, j);
        if (!h(i)) {
            this.b.a(i, i2, j);
        }
    }

    public void a(int i, long j) {
        this.a.a(i, j);
        if (!h(i)) {
            this.b.a(i, j);
        }
    }

    public void a(int i, int i2) {
        this.a.a(i, i2);
        if (!h(i)) {
            this.b.a(i, i2);
        }
    }

    public void b(com.liulishuo.filedownloader.d.c cVar) {
        this.a.b(cVar);
        if (!h(cVar.a())) {
            this.b.b(cVar);
        }
    }

    public boolean e(int i) {
        this.b.e(i);
        return this.a.e(i);
    }

    public void a() {
        this.a.a();
        this.b.a();
    }

    public void a(int i, String str, long j, long j2, int i2) {
        this.a.a(i, str, j, j2, i2);
        if (!h(i)) {
            this.b.a(i, str, j, j2, i2);
        }
    }

    public void a(int i, long j, String str, String str2) {
        this.a.a(i, j, str, str2);
        if (!h(i)) {
            this.b.a(i, j, str, str2);
        }
    }

    public void f(int i) {
        this.a.f(i);
        if (!h(i)) {
            this.b.f(i);
        }
    }

    public void a(int i, Throwable th) {
        this.a.a(i, th);
        if (!h(i)) {
            this.b.a(i, th);
        }
    }

    private void i(int i) {
        this.c.removeMessages(i);
        if (this.f.get() == i) {
            this.g = Thread.currentThread();
            this.c.sendEmptyMessage(0);
            LockSupport.park();
            return;
        }
        g(i);
    }

    public void a(int i, Throwable th, long j) {
        this.a.a(i, th, j);
        if (h(i)) {
            i(i);
        }
        this.b.a(i, th, j);
        this.e.remove(Integer.valueOf(i));
    }

    public void b(int i, long j) {
        this.a.b(i, j);
        if (h(i)) {
            this.c.removeMessages(i);
            if (this.f.get() == i) {
                this.g = Thread.currentThread();
                this.c.sendEmptyMessage(0);
                LockSupport.park();
                this.b.b(i, j);
            }
        } else {
            this.b.b(i, j);
        }
        this.e.remove(Integer.valueOf(i));
    }

    public void c(int i, long j) {
        this.a.c(i, j);
        if (h(i)) {
            i(i);
        }
        this.b.c(i, j);
        this.e.remove(Integer.valueOf(i));
    }

    public a.a b() {
        return this.b.a(this.a.a, this.a.b);
    }
}
