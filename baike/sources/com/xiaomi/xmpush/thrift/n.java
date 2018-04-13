package com.xiaomi.xmpush.thrift;

import com.umeng.commonsdk.proguard.ar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class n implements Serializable, Cloneable, org.apache.thrift.a<n, a> {
    public static final Map<a, b> d;
    private static final j e = new j("NormalConfig");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b("version", (byte) 8, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("configItems", ar.m, (short) 2);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("type", (byte) 8, (short) 3);
    public int a;
    public List<p> b;
    public f c;
    private BitSet i = new BitSet(1);

    public enum a {
        VERSION((short) 1, "version"),
        CONFIG_ITEMS((short) 2, "configItems"),
        TYPE((short) 3, "type");
        
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
        enumMap.put(a.VERSION, new b("version", (byte) 1, new c((byte) 8)));
        enumMap.put(a.CONFIG_ITEMS, new b("configItems", (byte) 1, new d(ar.m, new g((byte) 12, p.class))));
        enumMap.put(a.TYPE, new b("type", (byte) 1, new org.apache.thrift.meta_data.a(ar.n, f.class)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(n.class, d);
    }

    public int a() {
        return this.a;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (b()) {
                    f();
                    return;
                }
                throw new f("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.a = eVar.t();
                    a(true);
                    break;
                case (short) 2:
                    if (i.b != ar.m) {
                        h.a(eVar, i.b);
                        break;
                    }
                    org.apache.thrift.protocol.c m = eVar.m();
                    this.b = new ArrayList(m.b);
                    for (int i2 = 0; i2 < m.b; i2++) {
                        p pVar = new p();
                        pVar.a(eVar);
                        this.b.add(pVar);
                    }
                    eVar.n();
                    break;
                case (short) 3:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = f.a(eVar.t());
                        break;
                    }
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.i.set(0, z);
    }

    public boolean a(n nVar) {
        if (nVar == null || this.a != nVar.a) {
            return false;
        }
        boolean c = c();
        boolean c2 = nVar.c();
        if ((c || c2) && (!c || !c2 || !this.b.equals(nVar.b))) {
            return false;
        }
        c = e();
        c2 = nVar.e();
        return !(c || c2) || (c && c2 && this.c.equals(nVar.c));
    }

    public int b(n nVar) {
        if (!getClass().equals(nVar.getClass())) {
            return getClass().getName().compareTo(nVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(nVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, nVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(nVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.b, nVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(nVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.c, nVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        f();
        eVar.a(e);
        eVar.a(f);
        eVar.a(this.a);
        eVar.b();
        if (this.b != null) {
            eVar.a(g);
            eVar.a(new org.apache.thrift.protocol.c((byte) 12, this.b.size()));
            for (p b : this.b) {
                b.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(h);
            eVar.a(this.c.a());
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.i.get(0);
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((n) obj);
    }

    public f d() {
        return this.c;
    }

    public boolean e() {
        return this.c != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof n)) ? a((n) obj) : false;
    }

    public void f() {
        if (this.b == null) {
            throw new f("Required field 'configItems' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new f("Required field 'type' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("NormalConfig(");
        stringBuilder.append("version:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("configItems:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("type:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
