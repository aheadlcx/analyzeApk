package com.xiaomi.smack;

import android.util.Pair;
import com.xiaomi.channel.commonutils.string.c;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ak;
import com.xiaomi.push.service.am;
import com.xiaomi.slim.b;
import com.xiaomi.smack.packet.d;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class a {
    public static boolean a;
    private static final AtomicInteger o = new AtomicInteger(0);
    protected int b = 0;
    protected long c = -1;
    protected volatile long d = 0;
    protected volatile long e = 0;
    protected int f;
    protected final Map<f, a> g = new ConcurrentHashMap();
    protected final Map<f, a> h = new ConcurrentHashMap();
    protected com.xiaomi.smack.debugger.a i = null;
    protected String j = "";
    protected String k = "";
    protected final int l = o.getAndIncrement();
    protected b m;
    protected XMPushService n;
    private LinkedList<Pair<Integer, Long>> p = new LinkedList();
    private final Collection<d> q = new CopyOnWriteArrayList();
    private int r = 2;
    private long s = 0;

    protected static class a {
        private f a;
        private com.xiaomi.smack.filter.a b;

        public a(f fVar, com.xiaomi.smack.filter.a aVar) {
            this.a = fVar;
            this.b = aVar;
        }

        public void a(b bVar) {
            this.a.a(bVar);
        }

        public void a(d dVar) {
            if (this.b == null || this.b.a(dVar)) {
                this.a.b(dVar);
            }
        }
    }

    static {
        a = false;
        try {
            a = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception e) {
        }
        g.a();
    }

    protected a(XMPushService xMPushService, b bVar) {
        this.m = bVar;
        this.n = xMPushService;
        i();
    }

    private String a(int i) {
        return i == 1 ? "connected" : i == 0 ? "connecting" : i == 2 ? "disconnected" : "unknown";
    }

    private void b(int i) {
        synchronized (this.p) {
            if (i == 1) {
                this.p.clear();
            } else {
                this.p.add(new Pair(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())));
                if (this.p.size() > 6) {
                    this.p.remove(0);
                }
            }
        }
    }

    public void a(int i, int i2, Exception exception) {
        if (i != this.r) {
            com.xiaomi.channel.commonutils.logger.b.a(String.format("update the connection status. %1$s -> %2$s : %3$s ", new Object[]{a(this.r), a(i), am.a(i2)}));
        }
        if (com.xiaomi.channel.commonutils.network.d.d(this.n)) {
            b(i);
        }
        if (i == 1) {
            this.n.a(10);
            if (this.r != 0) {
                com.xiaomi.channel.commonutils.logger.b.a("try set connected while not connecting.");
            }
            this.r = i;
            for (d a : this.q) {
                a.a(this);
            }
        } else if (i == 0) {
            if (this.r != 2) {
                com.xiaomi.channel.commonutils.logger.b.a("try set connecting while not disconnected.");
            }
            this.r = i;
            for (d a2 : this.q) {
                a2.b(this);
            }
        } else if (i == 2) {
            this.n.a(10);
            if (this.r == 0) {
                for (d a22 : this.q) {
                    a22.a(this, exception == null ? new CancellationException("disconnect while connecting") : exception);
                }
            } else if (this.r == 1) {
                for (d a222 : this.q) {
                    a222.a(this, i2, exception);
                }
            }
            this.r = i;
        }
    }

    public abstract void a(ak.b bVar);

    public void a(d dVar) {
        if (dVar != null && !this.q.contains(dVar)) {
            this.q.add(dVar);
        }
    }

    public void a(f fVar, com.xiaomi.smack.filter.a aVar) {
        if (fVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.g.put(fVar, new a(fVar, aVar));
    }

    public abstract void a(d dVar);

    public synchronized void a(String str) {
        if (this.r == 0) {
            com.xiaomi.channel.commonutils.logger.b.a("setChallenge hash = " + c.a(str).substring(0, 8));
            this.j = str;
            a(1, 0, null);
        } else {
            com.xiaomi.channel.commonutils.logger.b.a("ignore setChallenge because connection was disconnected");
        }
    }

    public abstract void a(String str, String str2);

    public abstract void a(b[] bVarArr);

    public abstract void a(d[] dVarArr);

    public boolean a() {
        return false;
    }

    public synchronized boolean a(long j) {
        return this.s >= j;
    }

    public abstract void b(int i, Exception exception);

    public abstract void b(b bVar);

    public void b(d dVar) {
        this.q.remove(dVar);
    }

    public void b(f fVar, com.xiaomi.smack.filter.a aVar) {
        if (fVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.h.put(fVar, new a(fVar, aVar));
    }

    public abstract void b(boolean z);

    public b c() {
        return this.m;
    }

    public String d() {
        return this.m.e();
    }

    public String e() {
        return this.m.c();
    }

    public int f() {
        return this.f;
    }

    public long g() {
        return this.e;
    }

    public void h() {
        b(0, null);
    }

    protected void i() {
        Class cls = null;
        if (this.m.f() && this.i == null) {
            String property;
            try {
                property = System.getProperty("smack.debuggerClass");
            } catch (Throwable th) {
                Object obj = cls;
            }
            if (property != null) {
                try {
                    cls = Class.forName(property);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cls == null) {
                this.i = new com.xiaomi.measite.smack.a(this);
                return;
            }
            try {
                this.i = (com.xiaomi.smack.debugger.a) cls.getConstructor(new Class[]{a.class, Writer.class, Reader.class}).newInstance(new Object[]{this});
            } catch (Throwable e2) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
            }
        }
    }

    public boolean j() {
        return this.r == 0;
    }

    public boolean k() {
        return this.r == 1;
    }

    public int l() {
        return this.b;
    }

    public int m() {
        return this.r;
    }

    public synchronized void n() {
        this.s = System.currentTimeMillis();
    }

    public synchronized boolean o() {
        return System.currentTimeMillis() - this.s < ((long) g.b());
    }

    public void p() {
        synchronized (this.p) {
            this.p.clear();
        }
    }
}
