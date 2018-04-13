package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class p implements Serializable, Cloneable, org.apache.thrift.a<p, a> {
    public static final Map<a, b> d;
    private static final j e = new j("LocationInfo");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b("wifiList", (byte) 15, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("cellList", (byte) 15, (short) 2);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("gps", (byte) 12, (short) 3);
    public List<y> a;
    public List<c> b;
    public l c;

    public enum a {
        WIFI_LIST((short) 1, "wifiList"),
        CELL_LIST((short) 2, "cellList"),
        GPS((short) 3, "gps");
        
        private static final Map<String, a> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                d.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public String a() {
            return this.f;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.WIFI_LIST, new b("wifiList", (byte) 2, new d((byte) 15, new g((byte) 12, y.class))));
        enumMap.put(a.CELL_LIST, new b("cellList", (byte) 2, new d((byte) 15, new g((byte) 12, c.class))));
        enumMap.put(a.GPS, new b("gps", (byte) 2, new g((byte) 12, l.class)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(p.class, d);
    }

    public p a(l lVar) {
        this.c = lVar;
        return this;
    }

    public p a(List<y> list) {
        this.a = list;
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                e();
                return;
            }
            c m;
            int i2;
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    m = eVar.m();
                    this.a = new ArrayList(m.b);
                    for (i2 = 0; i2 < m.b; i2++) {
                        y yVar = new y();
                        yVar.a(eVar);
                        this.a.add(yVar);
                    }
                    eVar.n();
                    break;
                case (short) 2:
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    m = eVar.m();
                    this.b = new ArrayList(m.b);
                    for (i2 = 0; i2 < m.b; i2++) {
                        c cVar = new c();
                        cVar.a(eVar);
                        this.b.add(cVar);
                    }
                    eVar.n();
                    break;
                case (short) 3:
                    if (i.b != (byte) 12) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.c = new l();
                    this.c.a(eVar);
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(p pVar) {
        if (pVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = pVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(pVar.a))) {
            return false;
        }
        a = b();
        a2 = pVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.equals(pVar.b))) {
            return false;
        }
        a = d();
        a2 = pVar.d();
        return !(a || a2) || (a && a2 && this.c.a(pVar.c));
    }

    public int b(p pVar) {
        if (!getClass().equals(pVar.getClass())) {
            return getClass().getName().compareTo(pVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(pVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, pVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(pVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, pVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(pVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, pVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public p b(List<c> list) {
        this.b = list;
        return this;
    }

    public void b(e eVar) {
        e();
        eVar.a(e);
        if (this.a != null && a()) {
            eVar.a(f);
            eVar.a(new c((byte) 12, this.a.size()));
            for (y b : this.a) {
                b.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(g);
            eVar.a(new c((byte) 12, this.b.size()));
            for (c b2 : this.b) {
                b2.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (this.c != null && d()) {
            eVar.a(h);
            this.c.b(eVar);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public l c() {
        return this.c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((p) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public void e() {
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof p)) ? a((p) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("LocationInfo(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("wifiList:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("cellList:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        } else {
            obj = obj2;
        }
        if (d()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("gps:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
