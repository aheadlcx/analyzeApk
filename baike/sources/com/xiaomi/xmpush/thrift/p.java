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
import qsbk.app.activity.pay.PayPWDUniversalActivity;

public class p implements Serializable, Cloneable, org.apache.thrift.a<p, a> {
    public static final Map<a, b> h;
    private static final j i = new j("OnlineConfigItem");
    private static final org.apache.thrift.protocol.b j = new org.apache.thrift.protocol.b(PayPWDUniversalActivity.KEY, (byte) 8, (short) 1);
    private static final org.apache.thrift.protocol.b k = new org.apache.thrift.protocol.b("type", (byte) 8, (short) 2);
    private static final org.apache.thrift.protocol.b l = new org.apache.thrift.protocol.b("clear", (byte) 2, (short) 3);
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("intValue", (byte) 8, (short) 4);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("longValue", (byte) 10, (short) 5);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("stringValue", (byte) 11, (short) 6);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("boolValue", (byte) 2, (short) 7);
    public int a;
    public int b;
    public boolean c;
    public int d;
    public long e;
    public String f;
    public boolean g;
    private BitSet q = new BitSet(6);

    public enum a {
        KEY((short) 1, PayPWDUniversalActivity.KEY),
        TYPE((short) 2, "type"),
        CLEAR((short) 3, "clear"),
        INT_VALUE((short) 4, "intValue"),
        LONG_VALUE((short) 5, "longValue"),
        STRING_VALUE((short) 6, "stringValue"),
        BOOL_VALUE((short) 7, "boolValue");
        
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
        enumMap.put(a.KEY, new b(PayPWDUniversalActivity.KEY, (byte) 2, new c((byte) 8)));
        enumMap.put(a.TYPE, new b("type", (byte) 2, new c((byte) 8)));
        enumMap.put(a.CLEAR, new b("clear", (byte) 2, new c((byte) 2)));
        enumMap.put(a.INT_VALUE, new b("intValue", (byte) 2, new c((byte) 8)));
        enumMap.put(a.LONG_VALUE, new b("longValue", (byte) 2, new c((byte) 10)));
        enumMap.put(a.STRING_VALUE, new b("stringValue", (byte) 2, new c((byte) 11)));
        enumMap.put(a.BOOL_VALUE, new b("boolValue", (byte) 2, new c((byte) 2)));
        h = Collections.unmodifiableMap(enumMap);
        b.a(p.class, h);
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
                n();
                return;
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
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = eVar.t();
                    b(true);
                    break;
                case (short) 3:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.c = eVar.q();
                    c(true);
                    break;
                case (short) 4:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.d = eVar.t();
                    d(true);
                    break;
                case (short) 5:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.e = eVar.u();
                    e(true);
                    break;
                case (short) 6:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.f = eVar.w();
                        break;
                    }
                case (short) 7:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.g = eVar.q();
                    f(true);
                    break;
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

    public boolean a(p pVar) {
        if (pVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = pVar.b();
        if ((b || b2) && (!b || !b2 || this.a != pVar.a)) {
            return false;
        }
        b = d();
        b2 = pVar.d();
        if ((b || b2) && (!b || !b2 || this.b != pVar.b)) {
            return false;
        }
        b = e();
        b2 = pVar.e();
        if ((b || b2) && (!b || !b2 || this.c != pVar.c)) {
            return false;
        }
        b = g();
        b2 = pVar.g();
        if ((b || b2) && (!b || !b2 || this.d != pVar.d)) {
            return false;
        }
        b = i();
        b2 = pVar.i();
        if ((b || b2) && (!b || !b2 || this.e != pVar.e)) {
            return false;
        }
        b = k();
        b2 = pVar.k();
        if ((b || b2) && (!b || !b2 || !this.f.equals(pVar.f))) {
            return false;
        }
        b = m();
        b2 = pVar.m();
        return !(b || b2) || (b && b2 && this.g == pVar.g);
    }

    public int b(p pVar) {
        if (!getClass().equals(pVar.getClass())) {
            return getClass().getName().compareTo(pVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(pVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, pVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(pVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.b, pVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(pVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.c, pVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(pVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.d, pVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(pVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.e, pVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(pVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.f, pVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(pVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.g, pVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        n();
        eVar.a(i);
        if (b()) {
            eVar.a(j);
            eVar.a(this.a);
            eVar.b();
        }
        if (d()) {
            eVar.a(k);
            eVar.a(this.b);
            eVar.b();
        }
        if (e()) {
            eVar.a(l);
            eVar.a(this.c);
            eVar.b();
        }
        if (g()) {
            eVar.a(m);
            eVar.a(this.d);
            eVar.b();
        }
        if (i()) {
            eVar.a(n);
            eVar.a(this.e);
            eVar.b();
        }
        if (this.f != null && k()) {
            eVar.a(o);
            eVar.a(this.f);
            eVar.b();
        }
        if (m()) {
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
        return this.q.get(0);
    }

    public int c() {
        return this.b;
    }

    public void c(boolean z) {
        this.q.set(2, z);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((p) obj);
    }

    public void d(boolean z) {
        this.q.set(3, z);
    }

    public boolean d() {
        return this.q.get(1);
    }

    public void e(boolean z) {
        this.q.set(4, z);
    }

    public boolean e() {
        return this.q.get(2);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof p)) ? a((p) obj) : false;
    }

    public int f() {
        return this.d;
    }

    public void f(boolean z) {
        this.q.set(5, z);
    }

    public boolean g() {
        return this.q.get(3);
    }

    public long h() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.q.get(4);
    }

    public String j() {
        return this.f;
    }

    public boolean k() {
        return this.f != null;
    }

    public boolean l() {
        return this.g;
    }

    public boolean m() {
        return this.q.get(5);
    }

    public void n() {
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("OnlineConfigItem(");
        Object obj2 = 1;
        if (b()) {
            stringBuilder.append("key:");
            stringBuilder.append(this.a);
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("type:");
            stringBuilder.append(this.b);
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("clear:");
            stringBuilder.append(this.c);
            obj2 = null;
        }
        if (g()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("intValue:");
            stringBuilder.append(this.d);
            obj2 = null;
        }
        if (i()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("longValue:");
            stringBuilder.append(this.e);
            obj2 = null;
        }
        if (k()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("stringValue:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        } else {
            obj = obj2;
        }
        if (m()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("boolValue:");
            stringBuilder.append(this.g);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
