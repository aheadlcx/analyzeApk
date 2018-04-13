package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ah;
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
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class d implements l<d, e>, Serializable, Cloneable {
    public static final Map<e, x> d;
    private static final ap e = new ap("Imprint");
    private static final af f = new af("property", (byte) 13, (short) 1);
    private static final af g = new af("version", (byte) 8, (short) 2);
    private static final af h = new af("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends as>, at> i = new HashMap();
    public Map<String, e> a;
    public int b;
    public String c;
    private byte j;

    private static class a extends au<d> {
        private a() {
        }

        public /* synthetic */ void a(ak akVar, l lVar) throws r {
            b(akVar, (d) lVar);
        }

        public /* synthetic */ void b(ak akVar, l lVar) throws r {
            a(akVar, (d) lVar);
        }

        public void a(ak akVar, d dVar) throws r {
            akVar.j();
            while (true) {
                af l = akVar.l();
                if (l.b == (byte) 0) {
                    akVar.k();
                    if (dVar.h()) {
                        dVar.l();
                        return;
                    }
                    throw new al("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 13) {
                            an.a(akVar, l.b);
                            break;
                        }
                        ah n = akVar.n();
                        dVar.a = new HashMap(n.c * 2);
                        for (int i = 0; i < n.c; i++) {
                            String z = akVar.z();
                            e eVar = new e();
                            eVar.read(akVar);
                            dVar.a.put(z, eVar);
                        }
                        akVar.o();
                        dVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 8) {
                            an.a(akVar, l.b);
                            break;
                        }
                        dVar.b = akVar.w();
                        dVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        dVar.c = akVar.z();
                        dVar.c(true);
                        break;
                    default:
                        an.a(akVar, l.b);
                        break;
                }
                akVar.m();
            }
        }

        public void b(ak akVar, d dVar) throws r {
            dVar.l();
            akVar.a(d.e);
            if (dVar.a != null) {
                akVar.a(d.f);
                akVar.a(new ah((byte) 11, (byte) 12, dVar.a.size()));
                for (Entry entry : dVar.a.entrySet()) {
                    akVar.a((String) entry.getKey());
                    ((e) entry.getValue()).write(akVar);
                }
                akVar.e();
                akVar.c();
            }
            akVar.a(d.g);
            akVar.a(dVar.b);
            akVar.c();
            if (dVar.c != null) {
                akVar.a(d.h);
                akVar.a(dVar.c);
                akVar.c();
            }
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

    private static class c extends av<d> {
        private c() {
        }

        public void a(ak akVar, d dVar) throws r {
            aq aqVar = (aq) akVar;
            aqVar.a(dVar.a.size());
            for (Entry entry : dVar.a.entrySet()) {
                aqVar.a((String) entry.getKey());
                ((e) entry.getValue()).write(aqVar);
            }
            aqVar.a(dVar.b);
            aqVar.a(dVar.c);
        }

        public void b(ak akVar, d dVar) throws r {
            aq aqVar = (aq) akVar;
            ah ahVar = new ah((byte) 11, (byte) 12, aqVar.w());
            dVar.a = new HashMap(ahVar.c * 2);
            for (int i = 0; i < ahVar.c; i++) {
                String z = aqVar.z();
                e eVar = new e();
                eVar.read(aqVar);
                dVar.a.put(z, eVar);
            }
            dVar.a(true);
            dVar.b = aqVar.w();
            dVar.b(true);
            dVar.c = aqVar.z();
            dVar.c(true);
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
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, "version"),
        CHECKSUM((short) 3, "checksum");
        
        private static final Map<String, e> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
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
            return (e) d.get(str);
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public short a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }
    }

    public /* synthetic */ l deepCopy() {
        return a();
    }

    public /* synthetic */ s fieldForId(int i) {
        return b(i);
    }

    static {
        i.put(au.class, new b());
        i.put(av.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.PROPERTY, new x("property", (byte) 1, new aa((byte) 13, new y((byte) 11), new ac((byte) 12, e.class))));
        enumMap.put(e.VERSION, new x("version", (byte) 1, new y((byte) 8)));
        enumMap.put(e.CHECKSUM, new x("checksum", (byte) 1, new y((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        x.a(d.class, d);
    }

    public d() {
        this.j = (byte) 0;
    }

    public d(Map<String, e> map, int i, String str) {
        this();
        this.a = map;
        this.b = i;
        b(true);
        this.c = str;
    }

    public d(d dVar) {
        this.j = (byte) 0;
        this.j = dVar.j;
        if (dVar.e()) {
            Map hashMap = new HashMap();
            for (Entry entry : dVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new e((e) entry.getValue()));
            }
            this.a = hashMap;
        }
        this.b = dVar.b;
        if (dVar.k()) {
            this.c = dVar.c;
        }
    }

    public d a() {
        return new d(this);
    }

    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public int b() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(String str, e eVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, eVar);
    }

    public Map<String, e> c() {
        return this.a;
    }

    public d a(Map<String, e> map) {
        this.a = map;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int f() {
        return this.b;
    }

    public d a(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public void g() {
        this.j = i.b(this.j, 0);
    }

    public boolean h() {
        return i.a(this.j, 0);
    }

    public void b(boolean z) {
        this.j = i.a(this.j, 0, z);
    }

    public String i() {
        return this.c;
    }

    public d a(String str) {
        this.c = str;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public e b(int i) {
        return e.a(i);
    }

    public void read(ak akVar) throws r {
        ((at) i.get(akVar.D())).b().b(akVar, this);
    }

    public void write(ak akVar) throws r {
        ((at) i.get(akVar.D())).b().a(akVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Imprint(");
        stringBuilder.append("property:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws r {
        if (this.a == null) {
            throw new al("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new al("Required field 'checksum' was not present! Struct: " + toString());
        }
    }
}
