package com.umeng.analytics.a.c;

import com.umeng.analytics.a.a.d;
import com.umeng.analytics.a.b.a;
import com.umeng.analytics.a.d.e;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class b implements Serializable {
    private static final long a = 1;
    private List<String> b = new ArrayList();
    private List<String> c = new ArrayList();
    private long d = 0;
    private long e = 0;
    private long f = 0;
    private String g = null;

    public b(List<String> list, long j, long j2, long j3, List<String> list2, String str) {
        this.b = list;
        this.c = list2;
        this.d = j;
        this.e = j2;
        this.f = j3;
        this.g = str;
    }

    public void a(String str) {
        try {
            if (this.c.size() < com.umeng.analytics.a.d.b.a().b()) {
                this.c.add(str);
            } else {
                this.c.remove(this.c.get(0));
                this.c.add(str);
            }
            if (this.c.size() > com.umeng.analytics.a.d.b.a().b()) {
                for (int i = 0; i < this.c.size() - com.umeng.analytics.a.d.b.a().b(); i++) {
                    this.c.remove(this.c.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(a aVar, e eVar) {
        a(eVar.b());
        this.f++;
        this.e += eVar.c();
        this.d += eVar.d();
        aVar.a(this, false);
    }

    public void a(e eVar) {
        this.f = 1;
        this.b = eVar.a();
        a(eVar.b());
        this.e = eVar.c();
        this.d = System.currentTimeMillis();
        this.g = e.a(System.currentTimeMillis());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[key: ").append(this.b).append("] [label: ").append(this.c).append("][ totalTimeStamp").append(this.g).append("][ value").append(this.e).append("][ count").append(this.f).append("][ timeWindowNum").append(this.g).append("]");
        return stringBuffer.toString();
    }

    public String a() {
        return d.a(this.b);
    }

    public List<String> b() {
        return this.b;
    }

    public String c() {
        return d.a(this.c);
    }

    public List<String> d() {
        return this.c;
    }

    public long e() {
        return this.d;
    }

    public long f() {
        return this.e;
    }

    public long g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public void b(List<String> list) {
        this.c = list;
    }

    public void a(long j) {
        this.d = j;
    }

    public void b(long j) {
        this.e = j;
    }

    public void c(long j) {
        this.f = j;
    }

    public void b(String str) {
        this.g = str;
    }
}
