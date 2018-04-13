package mtopsdk.mtop.util;

import mtopsdk.a.b.a;
import mtopsdk.common.util.g;
import mtopsdk.common.util.l;

public class h implements Cloneable {
    public boolean a = true;
    public long b;
    public long c;
    public long d;
    public int e;
    public String f;
    public String g;
    protected long h;
    protected long i;
    protected long j;
    protected long k;
    protected long l;
    protected long m;
    protected String n = "";
    protected a o;
    public String p = "";
    public int q = g.a();
    private j r;
    private String s = ("MTOP" + this.q);

    private long j() {
        return System.nanoTime() / 1000000;
    }

    public void a() {
        this.h = j();
    }

    public void a(a aVar) {
        this.o = aVar;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void b() {
        this.i = j();
    }

    public void c() {
        this.l = j();
    }

    public Object clone() {
        return super.clone();
    }

    public void d() {
        this.m = j();
    }

    public void e() {
        this.j = j();
    }

    public void f() {
        this.k = j();
    }

    public String g() {
        return this.s;
    }

    public void h() {
        this.b = this.i - this.h;
        this.c = this.k - this.j;
        this.d = this.m - this.l;
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("mtopOneWayTime=").append(this.b);
        stringBuilder.append(",oneWayTime=").append(this.c);
        stringBuilder.append(",mtopResponseParseTime=").append(this.d);
        stringBuilder.append(",httpResponseStatus=").append(this.e);
        stringBuilder.append(",ret=").append(this.f);
        if (this.o != null) {
            stringBuilder.append(",");
            if (l.b(this.o.a)) {
                stringBuilder.append(this.o.a());
            } else {
                stringBuilder.append(this.o.a);
            }
        }
        this.n = stringBuilder.toString();
    }

    public synchronized j i() {
        if (this.r == null) {
            this.r = new j();
        }
        return this.r;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MtopStatistics [Detail]:");
        stringBuilder.append("startTime=" + this.h);
        stringBuilder.append(",mtopResponseParseStartTime=" + this.l);
        stringBuilder.append(",mtopResponseParseEndTime=" + this.m);
        stringBuilder.append(",endTime=" + this.i);
        stringBuilder.append("\nMtopStatistics[sumstat(ms)]:" + this.n);
        if (this.r != null) {
            stringBuilder.append("\nrbStatData=" + this.r);
        }
        return stringBuilder.toString();
    }
}
