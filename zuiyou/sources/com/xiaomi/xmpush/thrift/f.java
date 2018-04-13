package com.xiaomi.xmpush.thrift;

import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.umeng.analytics.b.g;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;
import org.mozilla.classfile.ByteCode;

public class f implements Serializable, Cloneable, org.apache.thrift.a<f, a> {
    public static final Map<a, b> l;
    private static final j m = new j("ClientUploadDataItem");
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b(g.b, ByteCode.T_LONG, (short) 1);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("data", ByteCode.T_LONG, (short) 2);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("name", ByteCode.T_LONG, (short) 3);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("counter", (byte) 10, (short) 4);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("timestamp", (byte) 10, (short) 5);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b("fromSdk", (byte) 2, (short) 6);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("category", ByteCode.T_LONG, (short) 7);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b("sourcePackage", ByteCode.T_LONG, (short) 8);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("id", ByteCode.T_LONG, (short) 9);
    private static final org.apache.thrift.protocol.b w = new org.apache.thrift.protocol.b(PushConstants.EXTRA, (byte) 13, (short) 10);
    private static final org.apache.thrift.protocol.b x = new org.apache.thrift.protocol.b("pkgName", ByteCode.T_LONG, (short) 11);
    public String a;
    public String b;
    public String c;
    public long d;
    public long e;
    public boolean f;
    public String g;
    public String h;
    public String i;
    public Map<String, String> j;
    public String k;
    private BitSet y = new BitSet(3);

    public enum a {
        CHANNEL((short) 1, g.b),
        DATA((short) 2, "data"),
        NAME((short) 3, "name"),
        COUNTER((short) 4, "counter"),
        TIMESTAMP((short) 5, "timestamp"),
        FROM_SDK((short) 6, "fromSdk"),
        CATEGORY((short) 7, "category"),
        SOURCE_PACKAGE((short) 8, "sourcePackage"),
        ID((short) 9, "id"),
        EXTRA((short) 10, PushConstants.EXTRA),
        PKG_NAME((short) 11, "pkgName");
        
        private static final Map<String, a> l = null;
        private final short m;
        private final String n;

        static {
            l = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                l.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.m = s;
            this.n = str;
        }

        public String a() {
            return this.n;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.CHANNEL, new b(g.b, (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.DATA, new b("data", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.NAME, new b("name", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.COUNTER, new b("counter", (byte) 2, new c((byte) 10)));
        enumMap.put(a.TIMESTAMP, new b("timestamp", (byte) 2, new c((byte) 10)));
        enumMap.put(a.FROM_SDK, new b("fromSdk", (byte) 2, new c((byte) 2)));
        enumMap.put(a.CATEGORY, new b("category", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.SOURCE_PACKAGE, new b("sourcePackage", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.ID, new b("id", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.EXTRA, new b(PushConstants.EXTRA, (byte) 2, new e((byte) 13, new c(ByteCode.T_LONG), new c(ByteCode.T_LONG))));
        enumMap.put(a.PKG_NAME, new b("pkgName", (byte) 2, new c(ByteCode.T_LONG)));
        l = Collections.unmodifiableMap(enumMap);
        b.a(f.class, l);
    }

    public f a(long j) {
        this.d = j;
        a(true);
        return this;
    }

    public f a(String str) {
        this.a = str;
        return this;
    }

    public String a() {
        return this.a;
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                r();
                return;
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.a = eVar.w();
                        break;
                    }
                case (short) 2:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.b = eVar.w();
                        break;
                    }
                case (short) 3:
                    if (i.b != ByteCode.T_LONG) {
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
                    d(true);
                    break;
                case (short) 7:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.g = eVar.w();
                        break;
                    }
                case (short) 8:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.h = eVar.w();
                        break;
                    }
                case (short) 9:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.i = eVar.w();
                        break;
                    }
                case (short) 10:
                    if (i.b != (byte) 13) {
                        h.a(eVar, i.b);
                        break;
                    }
                    d k = eVar.k();
                    this.j = new HashMap(k.c * 2);
                    for (int i2 = 0; i2 < k.c; i2++) {
                        this.j.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
                case (short) 11:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.k = eVar.w();
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
        this.y.set(0, z);
    }

    public boolean a(f fVar) {
        if (fVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = fVar.b();
        if ((b || b2) && (!b || !b2 || !this.a.equals(fVar.a))) {
            return false;
        }
        b = c();
        b2 = fVar.c();
        if ((b || b2) && (!b || !b2 || !this.b.equals(fVar.b))) {
            return false;
        }
        b = e();
        b2 = fVar.e();
        if ((b || b2) && (!b || !b2 || !this.c.equals(fVar.c))) {
            return false;
        }
        b = f();
        b2 = fVar.f();
        if ((b || b2) && (!b || !b2 || this.d != fVar.d)) {
            return false;
        }
        b = h();
        b2 = fVar.h();
        if ((b || b2) && (!b || !b2 || this.e != fVar.e)) {
            return false;
        }
        b = i();
        b2 = fVar.i();
        if ((b || b2) && (!b || !b2 || this.f != fVar.f)) {
            return false;
        }
        b = j();
        b2 = fVar.j();
        if ((b || b2) && (!b || !b2 || !this.g.equals(fVar.g))) {
            return false;
        }
        b = l();
        b2 = fVar.l();
        if ((b || b2) && (!b || !b2 || !this.h.equals(fVar.h))) {
            return false;
        }
        b = n();
        b2 = fVar.n();
        if ((b || b2) && (!b || !b2 || !this.i.equals(fVar.i))) {
            return false;
        }
        b = o();
        b2 = fVar.o();
        if ((b || b2) && (!b || !b2 || !this.j.equals(fVar.j))) {
            return false;
        }
        b = q();
        b2 = fVar.q();
        return !(b || b2) || (b && b2 && this.k.equals(fVar.k));
    }

    public int b(f fVar) {
        if (!getClass().equals(fVar.getClass())) {
            return getClass().getName().compareTo(fVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(fVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, fVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(fVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.b, fVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(fVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.c, fVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(fVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.d, fVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(fVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.e, fVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(fVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.f, fVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(fVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.g, fVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(fVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.h, fVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(fVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.i, fVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(o()).compareTo(Boolean.valueOf(fVar.o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (o()) {
            compareTo = org.apache.thrift.b.a(this.j, fVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(q()).compareTo(Boolean.valueOf(fVar.q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (q()) {
            compareTo = org.apache.thrift.b.a(this.k, fVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public f b(long j) {
        this.e = j;
        b(true);
        return this;
    }

    public f b(String str) {
        this.b = str;
        return this;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        r();
        eVar.a(m);
        if (this.a != null && b()) {
            eVar.a(n);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && c()) {
            eVar.a(o);
            eVar.a(this.b);
            eVar.b();
        }
        if (this.c != null && e()) {
            eVar.a(p);
            eVar.a(this.c);
            eVar.b();
        }
        if (f()) {
            eVar.a(q);
            eVar.a(this.d);
            eVar.b();
        }
        if (h()) {
            eVar.a(r);
            eVar.a(this.e);
            eVar.b();
        }
        if (i()) {
            eVar.a(s);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && j()) {
            eVar.a(t);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && l()) {
            eVar.a(u);
            eVar.a(this.h);
            eVar.b();
        }
        if (this.i != null && n()) {
            eVar.a(v);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && o()) {
            eVar.a(w);
            eVar.a(new d(ByteCode.T_LONG, ByteCode.T_LONG, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (this.k != null && q()) {
            eVar.a(x);
            eVar.a(this.k);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.y.set(1, z);
    }

    public boolean b() {
        return this.a != null;
    }

    public f c(String str) {
        this.c = str;
        return this;
    }

    public f c(boolean z) {
        this.f = z;
        d(true);
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((f) obj);
    }

    public f d(String str) {
        this.g = str;
        return this;
    }

    public String d() {
        return this.c;
    }

    public void d(boolean z) {
        this.y.set(2, z);
    }

    public f e(String str) {
        this.h = str;
        return this;
    }

    public boolean e() {
        return this.c != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof f)) ? a((f) obj) : false;
    }

    public f f(String str) {
        this.i = str;
        return this;
    }

    public boolean f() {
        return this.y.get(0);
    }

    public long g() {
        return this.e;
    }

    public f g(String str) {
        this.k = str;
        return this;
    }

    public boolean h() {
        return this.y.get(1);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.y.get(2);
    }

    public boolean j() {
        return this.g != null;
    }

    public String k() {
        return this.h;
    }

    public boolean l() {
        return this.h != null;
    }

    public String m() {
        return this.i;
    }

    public boolean n() {
        return this.i != null;
    }

    public boolean o() {
        return this.j != null;
    }

    public String p() {
        return this.k;
    }

    public boolean q() {
        return this.k != null;
    }

    public void r() {
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("ClientUploadDataItem(");
        Object obj2 = 1;
        if (b()) {
            stringBuilder.append("channel:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (c()) {
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
        if (e()) {
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
        if (f()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("counter:");
            stringBuilder.append(this.d);
            obj2 = null;
        }
        if (h()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("timestamp:");
            stringBuilder.append(this.e);
            obj2 = null;
        }
        if (i()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("fromSdk:");
            stringBuilder.append(this.f);
            obj2 = null;
        }
        if (j()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("category:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
            obj2 = null;
        }
        if (l()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("sourcePackage:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
            obj2 = null;
        }
        if (n()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("id:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
            obj2 = null;
        }
        if (o()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("extra:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        } else {
            obj = obj2;
        }
        if (q()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("pkgName:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
