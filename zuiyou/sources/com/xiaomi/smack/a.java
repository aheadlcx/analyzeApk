package com.xiaomi.smack;

import android.util.Pair;
import com.xiaomi.channel.commonutils.string.c;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ap;
import com.xiaomi.push.service.as;
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
    private static final AtomicInteger p = new AtomicInteger(0);
    protected int b = 0;
    protected long c = -1;
    protected volatile long d = 0;
    protected volatile long e = 0;
    protected int f;
    protected final Map<f, a> g = new ConcurrentHashMap();
    protected final Map<f, a> h = new ConcurrentHashMap();
    protected com.xiaomi.smack.a.a i = null;
    protected String j = "";
    protected String k = "";
    protected final int l = p.getAndIncrement();
    protected b m;
    protected XMPushService n;
    protected long o = 0;
    private LinkedList<Pair<Integer, Long>> q = new LinkedList();
    private final Collection<d> r = new CopyOnWriteArrayList();
    private int s = 2;
    private long t = 0;

    public static class a {
        private f a;
        private com.xiaomi.smack.b.a b;

        public a(f fVar, com.xiaomi.smack.b.a aVar) {
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
        j();
    }

    private String a(int i) {
        return i == 1 ? "connected" : i == 0 ? "connecting" : i == 2 ? "disconnected" : "unknown";
    }

    private void b(int i) {
        synchronized (this.q) {
            if (i == 1) {
                this.q.clear();
            } else {
                this.q.add(new Pair(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())));
                if (this.q.size() > 6) {
                    this.q.remove(0);
                }
            }
        }
    }

    public void a(int i, int i2, Exception exception) {
        if (i != this.s) {
            com.xiaomi.channel.commonutils.logger.b.a(String.format("update the connection status. %1$s -> %2$s : %3$s ", new Object[]{a(this.s), a(i), as.a(i2)}));
        }
        if (com.xiaomi.channel.commonutils.network.d.c(this.n)) {
            b(i);
        }
        if (i == 1) {
            this.n.a(10);
            if (this.s != 0) {
                com.xiaomi.channel.commonutils.logger.b.a("try set connected while not connecting.");
            }
            this.s = i;
            for (d a : this.r) {
                a.a(this);
            }
        } else if (i == 0) {
            if (this.s != 2) {
                com.xiaomi.channel.commonutils.logger.b.a("try set connecting while not disconnected.");
            }
            this.s = i;
            for (d a2 : this.r) {
                a2.b(this);
            }
        } else if (i == 2) {
            this.n.a(10);
            if (this.s == 0) {
                for (d a22 : this.r) {
                    a22.a(this, exception == null ? new CancellationException("disconnect while connecting") : exception);
                }
            } else if (this.s == 1) {
                for (d a222 : this.r) {
                    a222.a(this, i2, exception);
                }
            }
            this.s = i;
        }
    }

    public abstract void a(ap.b bVar);

    public void a(d dVar) {
        if (dVar != null && !this.r.contains(dVar)) {
            this.r.add(dVar);
        }
    }

    public void a(f fVar, com.xiaomi.smack.b.a aVar) {
        if (fVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.g.put(fVar, new a(fVar, aVar));
    }

    public abstract void a(d dVar);

    public synchronized void a(String str) {
        if (this.s == 0) {
            com.xiaomi.channel.commonutils.logger.b.a("setChallenge hash = " + c.a(str).substring(0, 8));
            this.j = str;
            a(1, 0, null);
        } else {
            com.xiaomi.channel.commonutils.logger.b.a("ignore setChallenge because connection was disconnected");
        }
    }

    public abstract void a(String str, String str2);

    public abstract void a(b[] bVarArr);

    public synchronized boolean a(long j) {
        return this.t >= j;
    }

    public abstract void b(int i, Exception exception);

    public abstract void b(b bVar);

    public void b(d dVar) {
        this.r.remove(dVar);
    }

    public void b(f fVar, com.xiaomi.smack.b.a aVar) {
        if (fVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.h.put(fVar, new a(fVar, aVar));
    }

    public abstract void b(boolean z);

    public boolean b() {
        return false;
    }

    public b d() {
        return this.m;
    }

    public String e() {
        return this.m.e();
    }

    public String f() {
        return this.m.c();
    }

    public int g() {
        return this.f;
    }

    public long h() {
        return this.e;
    }

    public void i() {
        b(0, null);
    }

    protected void j() {
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
                this.i = (com.xiaomi.smack.a.a) cls.getConstructor(new Class[]{a.class, Writer.class, Reader.class}).newInstance(new Object[]{this});
            } catch (Throwable e2) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
            }
        }
    }

    public boolean k() {
        return this.s == 0;
    }

    public boolean l() {
        return this.s == 1;
    }

    public int m() {
        return this.b;
    }

    public int n() {
        return this.s;
    }

    public synchronized void o() {
        this.t = System.currentTimeMillis();
    }

    public synchronized boolean p() {
        return System.currentTimeMillis() - this.t < ((long) g.b());
    }

    public synchronized boolean q() {
        return System.currentTimeMillis() - this.o < ((long) g.b());
    }

    public void r() {
        synchronized (this.q) {
            this.q.clear();
        }
    }
}
