package com.liulishuo.filedownloader;

import android.text.TextUtils;
import com.liulishuo.filedownloader.a.b;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.f;
import java.io.File;
import java.util.ArrayList;

public class c implements a, b, a {
    volatile int a = 0;
    private final w b;
    private final com.liulishuo.filedownloader.w.a c;
    private int d;
    private ArrayList<com.liulishuo.filedownloader.a.a> e;
    private final String f;
    private String g;
    private String h;
    private boolean i;
    private com.liulishuo.filedownloader.d.b j;
    private i k;
    private Object l;
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private int p = 100;
    private int q = 10;
    private boolean r = false;
    private boolean s = false;
    private final Object t;
    private final Object u = new Object();
    private volatile boolean v = false;

    private static final class a implements com.liulishuo.filedownloader.a.c {
        private final c a;

        private a(c cVar) {
            this.a = cVar;
            this.a.s = true;
        }

        public int a() {
            int d = this.a.d();
            if (d.a) {
                d.c(this, "add the task[%d] to the queue", Integer.valueOf(d));
            }
            h.a().c(this.a);
            return d;
        }
    }

    c(String str) {
        this.f = str;
        this.t = new Object();
        Object dVar = new d(this, this.t);
        this.b = dVar;
        this.c = dVar;
    }

    public a a(String str) {
        return a(str, false);
    }

    public a a(String str, boolean z) {
        this.g = str;
        if (d.a) {
            d.c(this, "setPath %s", str);
        }
        this.i = z;
        if (z) {
            this.h = null;
        } else {
            this.h = new File(str).getName();
        }
        return this;
    }

    public a a(i iVar) {
        this.k = iVar;
        if (d.a) {
            d.c(this, "setListener %s", iVar);
        }
        return this;
    }

    public a a(int i) {
        this.q = i;
        return this;
    }

    public a a(Object obj) {
        this.l = obj;
        if (d.a) {
            d.c(this, "setTag %s", obj);
        }
        return this;
    }

    public a a(boolean z) {
        this.r = z;
        return this;
    }

    public a b(int i) {
        this.m = i;
        return this;
    }

    public a b(boolean z) {
        this.o = z;
        return this;
    }

    public com.liulishuo.filedownloader.a.c a() {
        return new a();
    }

    public boolean J() {
        return this.b.f() != (byte) 0;
    }

    public boolean K() {
        if (q.a().e().a(this)) {
            return true;
        }
        return com.liulishuo.filedownloader.d.d.b(q());
    }

    public boolean b() {
        return this.a != 0;
    }

    public int c() {
        if (!this.s) {
            return O();
        }
        throw new IllegalStateException("If you start the task manually, it means this task doesn't belong to a queue, so you must not invoke BaseDownloadTask#ready() or InQueueTask#enqueue() before you start() this method. For detail: If this task doesn't belong to a queue, what is just an isolated task, you just need to invoke BaseDownloadTask#start() to start this task, that's all. In other words, If this task doesn't belong to a queue, you must not invoke BaseDownloadTask#ready() method or InQueueTask#enqueue() method before invoke BaseDownloadTask#start(), If you do that and if there is the same listener object to start a queue in another thread, this task may be assembled by the queue, in that case, when you invoke BaseDownloadTask#start() manually to start this task or this task is started by the queue, there is an exception buried in there, because this task object is started two times without declare BaseDownloadTask#reuse() : 1. you invoke BaseDownloadTask#start() manually;  2. the queue start this task automatically.");
    }

    private int O() {
        if (!J()) {
            if (!b()) {
                C();
            }
            this.b.e();
            return d();
        } else if (K()) {
            throw new IllegalStateException(f.a("This task is running %d, if you want to start the same task, please create a new one by FileDownloader.create", Integer.valueOf(d())));
        } else {
            throw new IllegalStateException("This task is dirty to restart, If you want to reuse this task, please invoke #reuse method manually and retry to restart again." + this.b.toString());
        }
    }

    public int d() {
        if (this.d != 0) {
            return this.d;
        }
        if (TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.f)) {
            return 0;
        }
        int a = f.a(this.f, this.g, this.i);
        this.d = a;
        return a;
    }

    public String e() {
        return this.f;
    }

    public int f() {
        return this.p;
    }

    public int g() {
        return this.q;
    }

    public String h() {
        return this.g;
    }

    public boolean i() {
        return this.i;
    }

    public String j() {
        return this.h;
    }

    public String k() {
        return f.a(h(), i(), j());
    }

    public i l() {
        return this.k;
    }

    public int m() {
        if (this.b.g() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.b.g();
    }

    public long n() {
        return this.b.g();
    }

    public int o() {
        if (this.b.h() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.b.h();
    }

    public long p() {
        return this.b.h();
    }

    public byte q() {
        return this.b.f();
    }

    public boolean r() {
        return this.r;
    }

    public Throwable s() {
        return this.b.i();
    }

    public Object t() {
        return this.l;
    }

    public int u() {
        return this.m;
    }

    public int v() {
        return this.b.j();
    }

    public boolean w() {
        return this.n;
    }

    public boolean x() {
        return this.o;
    }

    public com.liulishuo.filedownloader.d.b L() {
        return this.j;
    }

    public void E() {
        this.v = true;
    }

    public void F() {
        this.b.k();
        if (h.a().a((b) this)) {
            this.v = false;
        }
    }

    public void G() {
        O();
    }

    public Object H() {
        return this.t;
    }

    public boolean I() {
        return this.e != null && this.e.size() > 0;
    }

    public boolean D() {
        return this.v;
    }

    public b M() {
        return this;
    }

    public void b(String str) {
        this.h = str;
    }

    public ArrayList<com.liulishuo.filedownloader.a.a> N() {
        return this.e;
    }

    public a y() {
        return this;
    }

    public com.liulishuo.filedownloader.w.a z() {
        return this.c;
    }

    public boolean c(int i) {
        return d() == i;
    }

    public boolean A() {
        return com.liulishuo.filedownloader.d.d.a(q());
    }

    public int B() {
        return this.a;
    }

    public void C() {
        int hashCode;
        if (l() != null) {
            hashCode = l().hashCode();
        } else {
            hashCode = hashCode();
        }
        this.a = hashCode;
    }

    public String toString() {
        return f.a("%d@%s", Integer.valueOf(d()), super.toString());
    }
}
