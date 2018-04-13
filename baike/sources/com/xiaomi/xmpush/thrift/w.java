package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;
import qsbk.app.database.QsbkDatabase;

public class w implements Serializable, Cloneable, org.apache.thrift.a<w, a> {
    private static final b A = new b("messageTs", (byte) 10, (short) 5);
    private static final b B = new b("topic", (byte) 11, (short) 6);
    private static final b C = new b("aliasName", (byte) 11, (short) 7);
    private static final b D = new b("request", (byte) 12, (short) 8);
    private static final b E = new b("packageName", (byte) 11, (short) 9);
    private static final b F = new b("category", (byte) 11, (short) 10);
    private static final b G = new b("isOnline", (byte) 2, (short) 11);
    private static final b H = new b("regId", (byte) 11, (short) 12);
    private static final b I = new b("callbackUrl", (byte) 11, (short) 13);
    private static final b J = new b("userAccount", (byte) 11, (short) 14);
    private static final b K = new b("deviceStatus", (byte) 6, (short) 15);
    private static final b L = new b("geoMsgStatus", (byte) 6, (short) 16);
    private static final b M = new b("imeiMd5", (byte) 11, (short) 20);
    private static final b N = new b("deviceId", (byte) 11, (short) 21);
    private static final b O = new b("passThrough", (byte) 8, (short) 22);
    private static final b P = new b("extra", (byte) 13, (short) 23);
    public static final Map<a, org.apache.thrift.meta_data.b> u;
    private static final j v = new j("XmPushActionAckMessage");
    private static final b w = new b("debug", (byte) 11, (short) 1);
    private static final b x = new b(QsbkDatabase.TARGET, (byte) 12, (short) 2);
    private static final b y = new b("id", (byte) 11, (short) 3);
    private static final b z = new b("appId", (byte) 11, (short) 4);
    private BitSet Q = new BitSet(5);
    public String a;
    public u b;
    public String c;
    public String d;
    public long e;
    public String f;
    public String g;
    public aj h;
    public String i;
    public String j;
    public boolean k = false;
    public String l;
    public String m;
    public String n;
    public short o;
    public short p;
    public String q;
    public String r;
    public int s;
    public Map<String, String> t;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, QsbkDatabase.TARGET),
        ID((short) 3, "id"),
        APP_ID((short) 4, "appId"),
        MESSAGE_TS((short) 5, "messageTs"),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        REQUEST((short) 8, "request"),
        PACKAGE_NAME((short) 9, "packageName"),
        CATEGORY((short) 10, "category"),
        IS_ONLINE((short) 11, "isOnline"),
        REG_ID((short) 12, "regId"),
        CALLBACK_URL((short) 13, "callbackUrl"),
        USER_ACCOUNT((short) 14, "userAccount"),
        DEVICE_STATUS((short) 15, "deviceStatus"),
        GEO_MSG_STATUS((short) 16, "geoMsgStatus"),
        IMEI_MD5((short) 20, "imeiMd5"),
        DEVICE_ID((short) 21, "deviceId"),
        PASS_THROUGH((short) 22, "passThrough"),
        EXTRA((short) 23, "extra");
        
        private static final Map<String, a> u = null;
        private final short v;
        private final String w;

        static {
            u = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                u.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.v = s;
            this.w = str;
        }

        public String a() {
            return this.w;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new org.apache.thrift.meta_data.b("debug", (byte) 2, new c((byte) 11)));
        enumMap.put(a.TARGET, new org.apache.thrift.meta_data.b(QsbkDatabase.TARGET, (byte) 2, new g((byte) 12, u.class)));
        enumMap.put(a.ID, new org.apache.thrift.meta_data.b("id", (byte) 1, new c((byte) 11)));
        enumMap.put(a.APP_ID, new org.apache.thrift.meta_data.b("appId", (byte) 1, new c((byte) 11)));
        enumMap.put(a.MESSAGE_TS, new org.apache.thrift.meta_data.b("messageTs", (byte) 1, new c((byte) 10)));
        enumMap.put(a.TOPIC, new org.apache.thrift.meta_data.b("topic", (byte) 2, new c((byte) 11)));
        enumMap.put(a.ALIAS_NAME, new org.apache.thrift.meta_data.b("aliasName", (byte) 2, new c((byte) 11)));
        enumMap.put(a.REQUEST, new org.apache.thrift.meta_data.b("request", (byte) 2, new g((byte) 12, aj.class)));
        enumMap.put(a.PACKAGE_NAME, new org.apache.thrift.meta_data.b("packageName", (byte) 2, new c((byte) 11)));
        enumMap.put(a.CATEGORY, new org.apache.thrift.meta_data.b("category", (byte) 2, new c((byte) 11)));
        enumMap.put(a.IS_ONLINE, new org.apache.thrift.meta_data.b("isOnline", (byte) 2, new c((byte) 2)));
        enumMap.put(a.REG_ID, new org.apache.thrift.meta_data.b("regId", (byte) 2, new c((byte) 11)));
        enumMap.put(a.CALLBACK_URL, new org.apache.thrift.meta_data.b("callbackUrl", (byte) 2, new c((byte) 11)));
        enumMap.put(a.USER_ACCOUNT, new org.apache.thrift.meta_data.b("userAccount", (byte) 2, new c((byte) 11)));
        enumMap.put(a.DEVICE_STATUS, new org.apache.thrift.meta_data.b("deviceStatus", (byte) 2, new c((byte) 6)));
        enumMap.put(a.GEO_MSG_STATUS, new org.apache.thrift.meta_data.b("geoMsgStatus", (byte) 2, new c((byte) 6)));
        enumMap.put(a.IMEI_MD5, new org.apache.thrift.meta_data.b("imeiMd5", (byte) 2, new c((byte) 11)));
        enumMap.put(a.DEVICE_ID, new org.apache.thrift.meta_data.b("deviceId", (byte) 2, new c((byte) 11)));
        enumMap.put(a.PASS_THROUGH, new org.apache.thrift.meta_data.b("passThrough", (byte) 2, new c((byte) 8)));
        enumMap.put(a.EXTRA, new org.apache.thrift.meta_data.b("extra", (byte) 2, new e((byte) 13, new c((byte) 11), new c((byte) 11))));
        u = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(w.class, u);
    }

    public w a(long j) {
        this.e = j;
        a(true);
        return this;
    }

    public w a(String str) {
        this.c = str;
        return this;
    }

    public w a(short s) {
        this.o = s;
        c(true);
        return this;
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (e()) {
                    u();
                    return;
                }
                throw new f("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
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
                    if (i.b != (byte) 12) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = new u();
                    this.b.a(eVar);
                    break;
                case (short) 3:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
                        break;
                    }
                case (short) 4:
                    if (i.b != (byte) 11) {
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
                    a(true);
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
                    if (i.b != (byte) 11) {
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
                    this.h = new aj();
                    this.h.a(eVar);
                    break;
                case (short) 9:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.i = eVar.w();
                        break;
                    }
                case (short) 10:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.j = eVar.w();
                        break;
                    }
                case (short) 11:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.k = eVar.q();
                    b(true);
                    break;
                case (short) 12:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.l = eVar.w();
                        break;
                    }
                case (short) 13:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.m = eVar.w();
                        break;
                    }
                case (short) 14:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.n = eVar.w();
                        break;
                    }
                case (short) 15:
                    if (i.b != (byte) 6) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.o = eVar.s();
                    c(true);
                    break;
                case (short) 16:
                    if (i.b != (byte) 6) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.p = eVar.s();
                    d(true);
                    break;
                case (short) 20:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.q = eVar.w();
                        break;
                    }
                case (short) 21:
                    if (i.b != (byte) 11) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.r = eVar.w();
                        break;
                    }
                case (short) 22:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.s = eVar.t();
                    e(true);
                    break;
                case (short) 23:
                    if (i.b != (byte) 13) {
                        h.a(eVar, i.b);
                        break;
                    }
                    d k = eVar.k();
                    this.t = new HashMap(k.c * 2);
                    for (int i2 = 0; i2 < k.c; i2++) {
                        this.t.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.Q.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(w wVar) {
        if (wVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = wVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(wVar.a))) {
            return false;
        }
        a = b();
        a2 = wVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(wVar.b))) {
            return false;
        }
        a = c();
        a2 = wVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(wVar.c))) {
            return false;
        }
        a = d();
        a2 = wVar.d();
        if (((a || a2) && (!a || !a2 || !this.d.equals(wVar.d))) || this.e != wVar.e) {
            return false;
        }
        a = f();
        a2 = wVar.f();
        if ((a || a2) && (!a || !a2 || !this.f.equals(wVar.f))) {
            return false;
        }
        a = g();
        a2 = wVar.g();
        if ((a || a2) && (!a || !a2 || !this.g.equals(wVar.g))) {
            return false;
        }
        a = h();
        a2 = wVar.h();
        if ((a || a2) && (!a || !a2 || !this.h.a(wVar.h))) {
            return false;
        }
        a = i();
        a2 = wVar.i();
        if ((a || a2) && (!a || !a2 || !this.i.equals(wVar.i))) {
            return false;
        }
        a = j();
        a2 = wVar.j();
        if ((a || a2) && (!a || !a2 || !this.j.equals(wVar.j))) {
            return false;
        }
        a = k();
        a2 = wVar.k();
        if ((a || a2) && (!a || !a2 || this.k != wVar.k)) {
            return false;
        }
        a = l();
        a2 = wVar.l();
        if ((a || a2) && (!a || !a2 || !this.l.equals(wVar.l))) {
            return false;
        }
        a = m();
        a2 = wVar.m();
        if ((a || a2) && (!a || !a2 || !this.m.equals(wVar.m))) {
            return false;
        }
        a = n();
        a2 = wVar.n();
        if ((a || a2) && (!a || !a2 || !this.n.equals(wVar.n))) {
            return false;
        }
        a = o();
        a2 = wVar.o();
        if ((a || a2) && (!a || !a2 || this.o != wVar.o)) {
            return false;
        }
        a = p();
        a2 = wVar.p();
        if ((a || a2) && (!a || !a2 || this.p != wVar.p)) {
            return false;
        }
        a = q();
        a2 = wVar.q();
        if ((a || a2) && (!a || !a2 || !this.q.equals(wVar.q))) {
            return false;
        }
        a = r();
        a2 = wVar.r();
        if ((a || a2) && (!a || !a2 || !this.r.equals(wVar.r))) {
            return false;
        }
        a = s();
        a2 = wVar.s();
        if ((a || a2) && (!a || !a2 || this.s != wVar.s)) {
            return false;
        }
        a = t();
        a2 = wVar.t();
        return !(a || a2) || (a && a2 && this.t.equals(wVar.t));
    }

    public int b(w wVar) {
        if (!getClass().equals(wVar.getClass())) {
            return getClass().getName().compareTo(wVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(wVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, wVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(wVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, wVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(wVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, wVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(wVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, wVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(wVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, wVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(wVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, wVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(wVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.g, wVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(wVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.h, wVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(wVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.i, wVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(wVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.j, wVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(wVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.k, wVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(wVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.l, wVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(wVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.m, wVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(wVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.n, wVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(o()).compareTo(Boolean.valueOf(wVar.o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (o()) {
            compareTo = org.apache.thrift.b.a(this.o, wVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(p()).compareTo(Boolean.valueOf(wVar.p()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (p()) {
            compareTo = org.apache.thrift.b.a(this.p, wVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(q()).compareTo(Boolean.valueOf(wVar.q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (q()) {
            compareTo = org.apache.thrift.b.a(this.q, wVar.q);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(r()).compareTo(Boolean.valueOf(wVar.r()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (r()) {
            compareTo = org.apache.thrift.b.a(this.r, wVar.r);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(s()).compareTo(Boolean.valueOf(wVar.s()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (s()) {
            compareTo = org.apache.thrift.b.a(this.s, wVar.s);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(t()).compareTo(Boolean.valueOf(wVar.t()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (t()) {
            compareTo = org.apache.thrift.b.a(this.t, wVar.t);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public w b(String str) {
        this.d = str;
        return this;
    }

    public w b(short s) {
        this.p = s;
        d(true);
        return this;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        u();
        eVar.a(v);
        if (this.a != null && a()) {
            eVar.a(w);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(x);
            this.b.b(eVar);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(y);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null) {
            eVar.a(z);
            eVar.a(this.d);
            eVar.b();
        }
        eVar.a(A);
        eVar.a(this.e);
        eVar.b();
        if (this.f != null && f()) {
            eVar.a(B);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && g()) {
            eVar.a(C);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && h()) {
            eVar.a(D);
            this.h.b(eVar);
            eVar.b();
        }
        if (this.i != null && i()) {
            eVar.a(E);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && j()) {
            eVar.a(F);
            eVar.a(this.j);
            eVar.b();
        }
        if (k()) {
            eVar.a(G);
            eVar.a(this.k);
            eVar.b();
        }
        if (this.l != null && l()) {
            eVar.a(H);
            eVar.a(this.l);
            eVar.b();
        }
        if (this.m != null && m()) {
            eVar.a(I);
            eVar.a(this.m);
            eVar.b();
        }
        if (this.n != null && n()) {
            eVar.a(J);
            eVar.a(this.n);
            eVar.b();
        }
        if (o()) {
            eVar.a(K);
            eVar.a(this.o);
            eVar.b();
        }
        if (p()) {
            eVar.a(L);
            eVar.a(this.p);
            eVar.b();
        }
        if (this.q != null && q()) {
            eVar.a(M);
            eVar.a(this.q);
            eVar.b();
        }
        if (this.r != null && r()) {
            eVar.a(N);
            eVar.a(this.r);
            eVar.b();
        }
        if (s()) {
            eVar.a(O);
            eVar.a(this.s);
            eVar.b();
        }
        if (this.t != null && t()) {
            eVar.a(P);
            eVar.a(new d((byte) 11, (byte) 11, this.t.size()));
            for (Entry entry : this.t.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.Q.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public w c(String str) {
        this.f = str;
        return this;
    }

    public void c(boolean z) {
        this.Q.set(2, z);
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((w) obj);
    }

    public w d(String str) {
        this.g = str;
        return this;
    }

    public void d(boolean z) {
        this.Q.set(3, z);
    }

    public boolean d() {
        return this.d != null;
    }

    public void e(boolean z) {
        this.Q.set(4, z);
    }

    public boolean e() {
        return this.Q.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof w)) ? a((w) obj) : false;
    }

    public boolean f() {
        return this.f != null;
    }

    public boolean g() {
        return this.g != null;
    }

    public boolean h() {
        return this.h != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.i != null;
    }

    public boolean j() {
        return this.j != null;
    }

    public boolean k() {
        return this.Q.get(1);
    }

    public boolean l() {
        return this.l != null;
    }

    public boolean m() {
        return this.m != null;
    }

    public boolean n() {
        return this.n != null;
    }

    public boolean o() {
        return this.Q.get(2);
    }

    public boolean p() {
        return this.Q.get(3);
    }

    public boolean q() {
        return this.q != null;
    }

    public boolean r() {
        return this.r != null;
    }

    public boolean s() {
        return this.Q.get(4);
    }

    public boolean t() {
        return this.t != null;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionAckMessage(");
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
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.e);
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("isOnline:");
            stringBuilder.append(this.k);
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("callbackUrl:");
            if (this.m == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.m);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("userAccount:");
            if (this.n == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.n);
            }
        }
        if (o()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceStatus:");
            stringBuilder.append(this.o);
        }
        if (p()) {
            stringBuilder.append(", ");
            stringBuilder.append("geoMsgStatus:");
            stringBuilder.append(this.p);
        }
        if (q()) {
            stringBuilder.append(", ");
            stringBuilder.append("imeiMd5:");
            if (this.q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.q);
            }
        }
        if (r()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceId:");
            if (this.r == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.r);
            }
        }
        if (s()) {
            stringBuilder.append(", ");
            stringBuilder.append("passThrough:");
            stringBuilder.append(this.s);
        }
        if (t()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.t == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.t);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void u() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
