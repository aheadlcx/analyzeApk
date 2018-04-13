package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ak;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.av;
import com.umeng.commonsdk.proguard.i;
import com.umeng.commonsdk.proguard.l;
import com.umeng.commonsdk.proguard.r;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.proguard.x;
import com.umeng.commonsdk.proguard.y;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class a implements l<a, e>, Serializable, Cloneable {
    public static final Map<e, x> e;
    private static final ap f = new ap("IdJournal");
    private static final af g = new af(ClientCookie.DOMAIN_ATTR, (byte) 11, (short) 1);
    private static final af h = new af("old_id", (byte) 11, (short) 2);
    private static final af i = new af("new_id", (byte) 11, (short) 3);
    private static final af j = new af("ts", (byte) 10, (short) 4);
    private static final Map<Class<? extends as>, at> k = new HashMap();
    public String a;
    public String b;
    public String c;
    public long d;
    private byte l;
    private e[] m;

    private static class a extends au<a> {
        private a() {
        }

        public /* synthetic */ void a(ak akVar, l lVar) throws r {
            b(akVar, (a) lVar);
        }

        public /* synthetic */ void b(ak akVar, l lVar) throws r {
            a(akVar, (a) lVar);
        }

        public void a(ak akVar, a aVar) throws r {
            akVar.j();
            while (true) {
                af l = akVar.l();
                if (l.b == (byte) 0) {
                    akVar.k();
                    if (aVar.m()) {
                        aVar.n();
                        return;
                    }
                    throw new al("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        aVar.a = akVar.z();
                        aVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        aVar.b = akVar.z();
                        aVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        aVar.c = akVar.z();
                        aVar.c(true);
                        break;
                    case (short) 4:
                        if (l.b != (byte) 10) {
                            an.a(akVar, l.b);
                            break;
                        }
                        aVar.d = akVar.x();
                        aVar.d(true);
                        break;
                    default:
                        an.a(akVar, l.b);
                        break;
                }
                akVar.m();
            }
        }

        public void b(ak akVar, a aVar) throws r {
            aVar.n();
            akVar.a(a.f);
            if (aVar.a != null) {
                akVar.a(a.g);
                akVar.a(aVar.a);
                akVar.c();
            }
            if (aVar.b != null && aVar.g()) {
                akVar.a(a.h);
                akVar.a(aVar.b);
                akVar.c();
            }
            if (aVar.c != null) {
                akVar.a(a.i);
                akVar.a(aVar.c);
                akVar.c();
            }
            akVar.a(a.j);
            akVar.a(aVar.d);
            akVar.c();
            akVar.d();
            akVar.b();
        }
    }

    private static class b implements at {
        private b() {
        }

        public /* synthetic */ as b() {
            return a();
        }

        public a a() {
            return new a();
        }
    }

    private static class c extends av<a> {
        private c() {
        }

        public void a(ak akVar, a aVar) throws r {
            aq aqVar = (aq) akVar;
            aqVar.a(aVar.a);
            aqVar.a(aVar.c);
            aqVar.a(aVar.d);
            BitSet bitSet = new BitSet();
            if (aVar.g()) {
                bitSet.set(0);
            }
            aqVar.a(bitSet, 1);
            if (aVar.g()) {
                aqVar.a(aVar.b);
            }
        }

        public void b(ak akVar, a aVar) throws r {
            aq aqVar = (aq) akVar;
            aVar.a = aqVar.z();
            aVar.a(true);
            aVar.c = aqVar.z();
            aVar.c(true);
            aVar.d = aqVar.x();
            aVar.d(true);
            if (aqVar.b(1).get(0)) {
                aVar.b = aqVar.z();
                aVar.b(true);
            }
        }
    }

    private static class d implements at {
        private d() {
        }

        public /* synthetic */ as b() {
            return a();
        }

        public c a() {
            return new c();
        }
    }

    public enum e implements s {
        DOMAIN((short) 1, ClientCookie.DOMAIN_ATTR),
        OLD_ID((short) 2, "old_id"),
        NEW_ID((short) 3, "new_id"),
        TS((short) 4, "ts");
        
        private static final Map<String, e> e = null;
        private final short f;
        private final String g;

        static {
            e = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return (e) e.get(str);
        }

        private e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public short a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }
    }

    public /* synthetic */ l deepCopy() {
        return a();
    }

    public /* synthetic */ s fieldForId(int i) {
        return a(i);
    }

    static {
        k.put(au.class, new b());
        k.put(av.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new x(ClientCookie.DOMAIN_ATTR, (byte) 1, new y((byte) 11)));
        enumMap.put(e.OLD_ID, new x("old_id", (byte) 2, new y((byte) 11)));
        enumMap.put(e.NEW_ID, new x("new_id", (byte) 1, new y((byte) 11)));
        enumMap.put(e.TS, new x("ts", (byte) 1, new y((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        x.a(a.class, e);
    }

    public a() {
        this.l = (byte) 0;
        this.m = new e[]{e.OLD_ID};
    }

    public a(String str, String str2, long j) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j;
        d(true);
    }

    public a(a aVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.OLD_ID};
        this.l = aVar.l;
        if (aVar.d()) {
            this.a = aVar.a;
        }
        if (aVar.g()) {
            this.b = aVar.b;
        }
        if (aVar.j()) {
            this.c = aVar.c;
        }
        this.d = aVar.d;
    }

    public a a() {
        return new a(this);
    }

    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
    }

    public String b() {
        return this.a;
    }

    public a a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public String e() {
        return this.b;
    }

    public a b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String h() {
        return this.c;
    }

    public a c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public long k() {
        return this.d;
    }

    public a a(long j) {
        this.d = j;
        d(true);
        return this;
    }

    public void l() {
        this.l = i.b(this.l, 0);
    }

    public boolean m() {
        return i.a(this.l, 0);
    }

    public void d(boolean z) {
        this.l = i.a(this.l, 0, z);
    }

    public e a(int i) {
        return e.a(i);
    }

    public void read(ak akVar) throws r {
        ((at) k.get(akVar.D())).b().b(akVar, this);
    }

    public void write(ak akVar) throws r {
        ((at) k.get(akVar.D())).b().a(akVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdJournal(");
        stringBuilder.append("domain:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("old_id:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("new_id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.d);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void n() throws r {
        if (this.a == null) {
            throw new al("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new al("Required field 'new_id' was not present! Struct: " + toString());
        }
    }
}
