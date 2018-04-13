package com.xiaomi.xmpush.thrift;

import com.iflytek.cloud.SpeechConstant;
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
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;
import org.mozilla.classfile.ByteCode;

public class an implements Serializable, Cloneable, org.apache.thrift.a<an, a> {
    public static final Map<a, b> m;
    private static final j n = new j("XmPushActionSendMessage");
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("debug", ByteCode.T_LONG, (short) 1);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("target", (byte) 12, (short) 2);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("id", ByteCode.T_LONG, (short) 3);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("appId", ByteCode.T_LONG, (short) 4);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b("packageName", ByteCode.T_LONG, (short) 5);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("topic", ByteCode.T_LONG, (short) 6);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b("aliasName", ByteCode.T_LONG, (short) 7);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("message", (byte) 12, (short) 8);
    private static final org.apache.thrift.protocol.b w = new org.apache.thrift.protocol.b("needAck", (byte) 2, (short) 9);
    private static final org.apache.thrift.protocol.b x = new org.apache.thrift.protocol.b(SpeechConstant.PARAMS, (byte) 13, (short) 10);
    private static final org.apache.thrift.protocol.b y = new org.apache.thrift.protocol.b("category", ByteCode.T_LONG, (short) 11);
    private static final org.apache.thrift.protocol.b z = new org.apache.thrift.protocol.b("userAccount", ByteCode.T_LONG, (short) 12);
    private BitSet A = new BitSet(1);
    public String a;
    public x b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public t h;
    public boolean i = true;
    public Map<String, String> j;
    public String k;
    public String l;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, "appId"),
        PACKAGE_NAME((short) 5, "packageName"),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        MESSAGE((short) 8, "message"),
        NEED_ACK((short) 9, "needAck"),
        PARAMS((short) 10, SpeechConstant.PARAMS),
        CATEGORY((short) 11, "category"),
        USER_ACCOUNT((short) 12, "userAccount");
        
        private static final Map<String, a> m = null;
        private final short n;
        private final String o;

        static {
            m = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                m.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.n = s;
            this.o = str;
        }

        public String a() {
            return this.o;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new b("debug", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.TARGET, new b("target", (byte) 2, new g((byte) 12, x.class)));
        enumMap.put(a.ID, new b("id", (byte) 1, new c(ByteCode.T_LONG)));
        enumMap.put(a.APP_ID, new b("appId", (byte) 1, new c(ByteCode.T_LONG)));
        enumMap.put(a.PACKAGE_NAME, new b("packageName", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.TOPIC, new b("topic", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.ALIAS_NAME, new b("aliasName", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.MESSAGE, new b("message", (byte) 2, new g((byte) 12, t.class)));
        enumMap.put(a.NEED_ACK, new b("needAck", (byte) 2, new c((byte) 2)));
        enumMap.put(a.PARAMS, new b(SpeechConstant.PARAMS, (byte) 2, new e((byte) 13, new c(ByteCode.T_LONG), new c(ByteCode.T_LONG))));
        enumMap.put(a.CATEGORY, new b("category", (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.USER_ACCOUNT, new b("userAccount", (byte) 2, new c(ByteCode.T_LONG)));
        m = Collections.unmodifiableMap(enumMap);
        b.a(an.class, m);
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                t();
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
                    if (i.b != (byte) 12) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = new x();
                    this.b.a(eVar);
                    break;
                case (short) 3:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
                        break;
                    }
                case (short) 4:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.d = eVar.w();
                        break;
                    }
                case (short) 5:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.e = eVar.w();
                        break;
                    }
                case (short) 6:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.f = eVar.w();
                        break;
                    }
                case (short) 7:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.g = eVar.w();
                        break;
                    }
                case (short) 8:
                    if (i.b != (byte) 12) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.h = new t();
                    this.h.a(eVar);
                    break;
                case (short) 9:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.i = eVar.q();
                    a(true);
                    break;
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
                case (short) 12:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.l = eVar.w();
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
        this.A.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(an anVar) {
        if (anVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = anVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(anVar.a))) {
            return false;
        }
        a = b();
        a2 = anVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(anVar.b))) {
            return false;
        }
        a = d();
        a2 = anVar.d();
        if ((a || a2) && (!a || !a2 || !this.c.equals(anVar.c))) {
            return false;
        }
        a = f();
        a2 = anVar.f();
        if ((a || a2) && (!a || !a2 || !this.d.equals(anVar.d))) {
            return false;
        }
        a = g();
        a2 = anVar.g();
        if ((a || a2) && (!a || !a2 || !this.e.equals(anVar.e))) {
            return false;
        }
        a = i();
        a2 = anVar.i();
        if ((a || a2) && (!a || !a2 || !this.f.equals(anVar.f))) {
            return false;
        }
        a = k();
        a2 = anVar.k();
        if ((a || a2) && (!a || !a2 || !this.g.equals(anVar.g))) {
            return false;
        }
        a = m();
        a2 = anVar.m();
        if ((a || a2) && (!a || !a2 || !this.h.a(anVar.h))) {
            return false;
        }
        a = n();
        a2 = anVar.n();
        if ((a || a2) && (!a || !a2 || this.i != anVar.i)) {
            return false;
        }
        a = o();
        a2 = anVar.o();
        if ((a || a2) && (!a || !a2 || !this.j.equals(anVar.j))) {
            return false;
        }
        a = q();
        a2 = anVar.q();
        if ((a || a2) && (!a || !a2 || !this.k.equals(anVar.k))) {
            return false;
        }
        a = s();
        a2 = anVar.s();
        return !(a || a2) || (a && a2 && this.l.equals(anVar.l));
    }

    public int b(an anVar) {
        if (!getClass().equals(anVar.getClass())) {
            return getClass().getName().compareTo(anVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(anVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, anVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(anVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, anVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(anVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, anVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(anVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.d, anVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(anVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.e, anVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(anVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.f, anVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(anVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.g, anVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(anVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.h, anVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(anVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.i, anVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(o()).compareTo(Boolean.valueOf(anVar.o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (o()) {
            compareTo = org.apache.thrift.b.a(this.j, anVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(q()).compareTo(Boolean.valueOf(anVar.q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (q()) {
            compareTo = org.apache.thrift.b.a(this.k, anVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(s()).compareTo(Boolean.valueOf(anVar.s()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (s()) {
            compareTo = org.apache.thrift.b.a(this.l, anVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        t();
        eVar.a(n);
        if (this.a != null && a()) {
            eVar.a(o);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(p);
            this.b.b(eVar);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(q);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null) {
            eVar.a(r);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && g()) {
            eVar.a(s);
            eVar.a(this.e);
            eVar.b();
        }
        if (this.f != null && i()) {
            eVar.a(t);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && k()) {
            eVar.a(u);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && m()) {
            eVar.a(v);
            this.h.b(eVar);
            eVar.b();
        }
        if (n()) {
            eVar.a(w);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && o()) {
            eVar.a(x);
            eVar.a(new d(ByteCode.T_LONG, ByteCode.T_LONG, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (this.k != null && q()) {
            eVar.a(y);
            eVar.a(this.k);
            eVar.b();
        }
        if (this.l != null && s()) {
            eVar.a(z);
            eVar.a(this.l);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public String c() {
        return this.c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((an) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public String e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof an)) ? a((an) obj) : false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.e != null;
    }

    public String h() {
        return this.f;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f != null;
    }

    public String j() {
        return this.g;
    }

    public boolean k() {
        return this.g != null;
    }

    public t l() {
        return this.h;
    }

    public boolean m() {
        return this.h != null;
    }

    public boolean n() {
        return this.A.get(0);
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

    public String r() {
        return this.l;
    }

    public boolean s() {
        return this.l != null;
    }

    public void t() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendMessage(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("debug:");
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
            stringBuilder.append("target:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("message:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("needAck:");
            stringBuilder.append(this.i);
        }
        if (o()) {
            stringBuilder.append(", ");
            stringBuilder.append("params:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (q()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        if (s()) {
            stringBuilder.append(", ");
            stringBuilder.append("userAccount:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
