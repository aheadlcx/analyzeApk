package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class d implements Serializable, Cloneable, org.apache.thrift.a<d, a> {
    public static final Map<a, b> h;
    private static final j i = new j("ClientUploadDataItem");
    private static final org.apache.thrift.protocol.b j = new org.apache.thrift.protocol.b("channel", (byte) 11, (short) 1);
    private static final org.apache.thrift.protocol.b k = new org.apache.thrift.protocol.b("data", (byte) 11, (short) 2);
    private static final org.apache.thrift.protocol.b l = new org.apache.thrift.protocol.b("name", (byte) 11, (short) 3);
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("counter", (byte) 10, (short) 4);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("timestamp", (byte) 10, (short) 5);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("fromSdk", (byte) 2, (short) 6);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("category", (byte) 11, (short) 7);
    public String a;
    public String b;
    public String c;
    public long d;
    public long e;
    public boolean f;
    public String g;
    private BitSet q = new BitSet(3);

    public enum a {
        CHANNEL((short) 1, "channel"),
        DATA((short) 2, "data"),
        NAME((short) 3, "name"),
        COUNTER((short) 4, "counter"),
        TIMESTAMP((short) 5, "timestamp"),
        FROM_SDK((short) 6, "fromSdk"),
        CATEGORY((short) 7, "category");
        
        private static final Map<String, a> h = null;
        private final short i;
        private final String j;

        static {
            h = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                h.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.i = s;
            this.j = str;
        }

        public String a() {
            return this.j;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.CHANNEL, new b("channel", (byte) 2, new c((byte) 11)));
        enumMap.put(a.DATA, new b("data", (byte) 2, new c((byte) 11)));
        enumMap.put(a.NAME, new b("name", (byte) 2, new c((byte) 11)));
        enumMap.put(a.COUNTER, new b("counter", (byte) 2, new c((byte) 10)));
        enumMap.put(a.TIMESTAMP, new b("timestamp", (byte) 2, new c((byte) 10)));
        enumMap.put(a.FROM_SDK, new b("fromSdk", (byte) 2, new c((byte) 2)));
        enumMap.put(a.CATEGORY, new b("category", (byte) 2, new c((byte) 11)));
        h = Collections.unmodifiableMap(enumMap);
        b.a(d.class, h);
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                h();
                return;
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.a = eVar.w();
                        break;
                    }
                case (short) 2:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.b = eVar.w();
                        break;
                    }
                case (short) 3:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
                        break;
                    }
                case (short) 4:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.d = eVar.u();
                    a(true);
                    break;
                case (short) 5:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.e = eVar.u();
                    b(true);
                    break;
                case (short) 6:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.f = eVar.q();
                    c(true);
                    break;
                case (short) 7:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.g = eVar.w();
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
        this.q.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(d dVar) {
        if (dVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = dVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(dVar.a))) {
            return false;
        }
        a = b();
        a2 = dVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.equals(dVar.b))) {
            return false;
        }
        a = c();
        a2 = dVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(dVar.c))) {
            return false;
        }
        a = d();
        a2 = dVar.d();
        if ((a || a2) && (!a || !a2 || this.d != dVar.d)) {
            return false;
        }
        a = e();
        a2 = dVar.e();
        if ((a || a2) && (!a || !a2 || this.e != dVar.e)) {
            return false;
        }
        a = f();
        a2 = dVar.f();
        if ((a || a2) && (!a || !a2 || this.f != dVar.f)) {
            return false;
        }
        a = g();
        a2 = dVar.g();
        return !(a || a2) || (a && a2 && this.g.equals(dVar.g));
    }

    public int b(d dVar) {
        if (!getClass().equals(dVar.getClass())) {
            return getClass().getName().compareTo(dVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(dVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, dVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(dVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, dVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(dVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, dVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(dVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, dVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(dVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, dVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(dVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, dVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(dVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.g, dVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        h();
        eVar.a(i);
        if (this.a != null && a()) {
            eVar.a(j);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(k);
            eVar.a(this.b);
            eVar.b();
        }
        if (this.c != null && c()) {
            eVar.a(l);
            eVar.a(this.c);
            eVar.b();
        }
        if (d()) {
            eVar.a(m);
            eVar.a(this.d);
            eVar.b();
        }
        if (e()) {
            eVar.a(n);
            eVar.a(this.e);
            eVar.b();
        }
        if (f()) {
            eVar.a(o);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && g()) {
            eVar.a(p);
            eVar.a(this.g);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.q.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public void c(boolean z) {
        this.q.set(2, z);
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((d) obj);
    }

    public boolean d() {
        return this.q.get(0);
    }

    public boolean e() {
        return this.q.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof d)) ? a((d) obj) : false;
    }

    public boolean f() {
        return this.q.get(2);
    }

    public boolean g() {
        return this.g != null;
    }

    public void h() {
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("ClientUploadDataItem(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("channel:");
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
            stringBuilder.append("data:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("name:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("counter:");
            stringBuilder.append(this.d);
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("timestamp:");
            stringBuilder.append(this.e);
            obj2 = null;
        }
        if (f()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("fromSdk:");
            stringBuilder.append(this.f);
        } else {
            obj = obj2;
        }
        if (g()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("category:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
