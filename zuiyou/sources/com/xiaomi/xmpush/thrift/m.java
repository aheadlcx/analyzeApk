package com.xiaomi.xmpush.thrift;

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
import org.mozilla.classfile.ByteCode;

public class m implements Serializable, Cloneable, org.apache.thrift.a<m, a> {
    public static final Map<a, b> k;
    private static final j l = new j("GeoFencing");
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("id", ByteCode.T_LONG, (short) 1);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("name", ByteCode.T_LONG, (short) 2);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("appId", (byte) 10, (short) 3);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("packageName", ByteCode.T_LONG, (short) 4);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("createTime", (byte) 10, (short) 5);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("type", (byte) 8, (short) 6);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b("circleCenter", (byte) 12, (short) 7);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("circleRadius", (byte) 4, (short) 9);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b("polygonPoints", (byte) 15, (short) 10);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("coordinateProvider", (byte) 8, (short) 11);
    public String a;
    public String b;
    public long c;
    public String d;
    public long e;
    public n f;
    public o g;
    public double h;
    public List<o> i;
    public j j;
    private BitSet w = new BitSet(3);

    public enum a {
        ID((short) 1, "id"),
        NAME((short) 2, "name"),
        APP_ID((short) 3, "appId"),
        PACKAGE_NAME((short) 4, "packageName"),
        CREATE_TIME((short) 5, "createTime"),
        TYPE((short) 6, "type"),
        CIRCLE_CENTER((short) 7, "circleCenter"),
        CIRCLE_RADIUS((short) 9, "circleRadius"),
        POLYGON_POINTS((short) 10, "polygonPoints"),
        COORDINATE_PROVIDER((short) 11, "coordinateProvider");
        
        private static final Map<String, a> k = null;
        private final short l;
        private final String m;

        static {
            k = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                k.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public String a() {
            return this.m;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.ID, new b("id", (byte) 1, new c(ByteCode.T_LONG)));
        enumMap.put(a.NAME, new b("name", (byte) 1, new c(ByteCode.T_LONG)));
        enumMap.put(a.APP_ID, new b("appId", (byte) 1, new c((byte) 10)));
        enumMap.put(a.PACKAGE_NAME, new b("packageName", (byte) 1, new c(ByteCode.T_LONG)));
        enumMap.put(a.CREATE_TIME, new b("createTime", (byte) 1, new c((byte) 10)));
        enumMap.put(a.TYPE, new b("type", (byte) 1, new org.apache.thrift.meta_data.a((byte) 16, n.class)));
        enumMap.put(a.CIRCLE_CENTER, new b("circleCenter", (byte) 2, new g((byte) 12, o.class)));
        enumMap.put(a.CIRCLE_RADIUS, new b("circleRadius", (byte) 2, new c((byte) 4)));
        enumMap.put(a.POLYGON_POINTS, new b("polygonPoints", (byte) 2, new d((byte) 15, new g((byte) 12, o.class))));
        enumMap.put(a.COORDINATE_PROVIDER, new b("coordinateProvider", (byte) 1, new org.apache.thrift.meta_data.a((byte) 16, j.class)));
        k = Collections.unmodifiableMap(enumMap);
        b.a(m.class, k);
    }

    public m a(double d) {
        this.h = d;
        c(true);
        return this;
    }

    public m a(long j) {
        this.c = j;
        a(true);
        return this;
    }

    public m a(j jVar) {
        this.j = jVar;
        return this;
    }

    public m a(n nVar) {
        this.f = nVar;
        return this;
    }

    public m a(o oVar) {
        this.g = oVar;
        return this;
    }

    public m a(String str) {
        this.a = str;
        return this;
    }

    public m a(List<o> list) {
        this.i = list;
        return this;
    }

    public String a() {
        return this.a;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (!f()) {
                    throw new f("Required field 'appId' was not found in serialized data! Struct: " + toString());
                } else if (j()) {
                    u();
                    return;
                } else {
                    throw new f("Required field 'createTime' was not found in serialized data! Struct: " + toString());
                }
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
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.c = eVar.u();
                    a(true);
                    break;
                case (short) 4:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.d = eVar.w();
                        break;
                    }
                case (short) 5:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.e = eVar.u();
                    b(true);
                    break;
                case (short) 6:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.f = n.a(eVar.t());
                        break;
                    }
                case (short) 7:
                    if (i.b != (byte) 12) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.g = new o();
                    this.g.a(eVar);
                    break;
                case (short) 9:
                    if (i.b != (byte) 4) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.h = eVar.v();
                    c(true);
                    break;
                case (short) 10:
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    org.apache.thrift.protocol.c m = eVar.m();
                    this.i = new ArrayList(m.b);
                    for (int i2 = 0; i2 < m.b; i2++) {
                        o oVar = new o();
                        oVar.a(eVar);
                        this.i.add(oVar);
                    }
                    eVar.n();
                    break;
                case (short) 11:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.j = j.a(eVar.t());
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
        this.w.set(0, z);
    }

    public boolean a(m mVar) {
        if (mVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = mVar.b();
        if ((b || b2) && (!b || !b2 || !this.a.equals(mVar.a))) {
            return false;
        }
        b = d();
        b2 = mVar.d();
        if (((b || b2) && (!b || !b2 || !this.b.equals(mVar.b))) || this.c != mVar.c) {
            return false;
        }
        b = h();
        b2 = mVar.h();
        if (((b || b2) && (!b || !b2 || !this.d.equals(mVar.d))) || this.e != mVar.e) {
            return false;
        }
        b = l();
        b2 = mVar.l();
        if ((b || b2) && (!b || !b2 || !this.f.equals(mVar.f))) {
            return false;
        }
        b = n();
        b2 = mVar.n();
        if ((b || b2) && (!b || !b2 || !this.g.a(mVar.g))) {
            return false;
        }
        b = p();
        b2 = mVar.p();
        if ((b || b2) && (!b || !b2 || this.h != mVar.h)) {
            return false;
        }
        b = r();
        b2 = mVar.r();
        if ((b || b2) && (!b || !b2 || !this.i.equals(mVar.i))) {
            return false;
        }
        b = t();
        b2 = mVar.t();
        return !(b || b2) || (b && b2 && this.j.equals(mVar.j));
    }

    public int b(m mVar) {
        if (!getClass().equals(mVar.getClass())) {
            return getClass().getName().compareTo(mVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(mVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, mVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(mVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.b, mVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(mVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.c, mVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(mVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.d, mVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(mVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.e, mVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(mVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.f, mVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(mVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.g, mVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(p()).compareTo(Boolean.valueOf(mVar.p()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (p()) {
            compareTo = org.apache.thrift.b.a(this.h, mVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(r()).compareTo(Boolean.valueOf(mVar.r()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (r()) {
            compareTo = org.apache.thrift.b.a(this.i, mVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(t()).compareTo(Boolean.valueOf(mVar.t()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (t()) {
            compareTo = org.apache.thrift.b.a(this.j, mVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public m b(long j) {
        this.e = j;
        b(true);
        return this;
    }

    public m b(String str) {
        this.b = str;
        return this;
    }

    public void b(e eVar) {
        u();
        eVar.a(l);
        if (this.a != null) {
            eVar.a(m);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null) {
            eVar.a(n);
            eVar.a(this.b);
            eVar.b();
        }
        eVar.a(o);
        eVar.a(this.c);
        eVar.b();
        if (this.d != null) {
            eVar.a(p);
            eVar.a(this.d);
            eVar.b();
        }
        eVar.a(q);
        eVar.a(this.e);
        eVar.b();
        if (this.f != null) {
            eVar.a(r);
            eVar.a(this.f.a());
            eVar.b();
        }
        if (this.g != null && n()) {
            eVar.a(s);
            this.g.b(eVar);
            eVar.b();
        }
        if (p()) {
            eVar.a(t);
            eVar.a(this.h);
            eVar.b();
        }
        if (this.i != null && r()) {
            eVar.a(u);
            eVar.a(new org.apache.thrift.protocol.c((byte) 12, this.i.size()));
            for (o b : this.i) {
                b.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (this.j != null) {
            eVar.a(v);
            eVar.a(this.j.a());
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.w.set(1, z);
    }

    public boolean b() {
        return this.a != null;
    }

    public m c(String str) {
        this.d = str;
        return this;
    }

    public String c() {
        return this.b;
    }

    public void c(boolean z) {
        this.w.set(2, z);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((m) obj);
    }

    public boolean d() {
        return this.b != null;
    }

    public long e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof m)) ? a((m) obj) : false;
    }

    public boolean f() {
        return this.w.get(0);
    }

    public String g() {
        return this.d;
    }

    public boolean h() {
        return this.d != null;
    }

    public int hashCode() {
        return 0;
    }

    public long i() {
        return this.e;
    }

    public boolean j() {
        return this.w.get(1);
    }

    public n k() {
        return this.f;
    }

    public boolean l() {
        return this.f != null;
    }

    public o m() {
        return this.g;
    }

    public boolean n() {
        return this.g != null;
    }

    public double o() {
        return this.h;
    }

    public boolean p() {
        return this.w.get(2);
    }

    public List<o> q() {
        return this.i;
    }

    public boolean r() {
        return this.i != null;
    }

    public j s() {
        return this.j;
    }

    public boolean t() {
        return this.j != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("GeoFencing(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("name:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append("packageName:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("createTime:");
        stringBuilder.append(this.e);
        stringBuilder.append(", ");
        stringBuilder.append("type:");
        if (this.f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f);
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("circleCenter:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (p()) {
            stringBuilder.append(", ");
            stringBuilder.append("circleRadius:");
            stringBuilder.append(this.h);
        }
        if (r()) {
            stringBuilder.append(", ");
            stringBuilder.append("polygonPoints:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("coordinateProvider:");
        if (this.j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.j);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void u() {
        if (this.a == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new f("Required field 'name' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'packageName' was not present! Struct: " + toString());
        } else if (this.f == null) {
            throw new f("Required field 'type' was not present! Struct: " + toString());
        } else if (this.j == null) {
            throw new f("Required field 'coordinateProvider' was not present! Struct: " + toString());
        }
    }
}
