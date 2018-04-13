package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ag;
import com.umeng.commonsdk.proguard.ah;
import com.umeng.commonsdk.proguard.ak;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.ar;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.av;
import com.umeng.commonsdk.proguard.l;
import com.umeng.commonsdk.proguard.r;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.proguard.x;
import com.umeng.commonsdk.proguard.y;
import com.umeng.commonsdk.proguard.z;
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
import java.util.Map.Entry;

public class c implements l<c, e>, Serializable, Cloneable {
    public static final Map<e, x> d;
    private static final ap e = new ap("IdTracking");
    private static final af f = new af("snapshots", (byte) 13, (short) 1);
    private static final af g = new af("journals", ar.m, (short) 2);
    private static final af h = new af("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends as>, at> i = new HashMap();
    public Map<String, b> a;
    public List<a> b;
    public String c;
    private e[] j;

    private static class a extends au<c> {
        private a() {
        }

        public /* synthetic */ void a(ak akVar, l lVar) throws r {
            b(akVar, (c) lVar);
        }

        public /* synthetic */ void b(ak akVar, l lVar) throws r {
            a(akVar, (c) lVar);
        }

        public void a(ak akVar, c cVar) throws r {
            akVar.j();
            while (true) {
                af l = akVar.l();
                if (l.b == (byte) 0) {
                    akVar.k();
                    cVar.n();
                    return;
                }
                int i;
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 13) {
                            an.a(akVar, l.b);
                            break;
                        }
                        ah n = akVar.n();
                        cVar.a = new HashMap(n.c * 2);
                        for (i = 0; i < n.c; i++) {
                            String z = akVar.z();
                            b bVar = new b();
                            bVar.read(akVar);
                            cVar.a.put(z, bVar);
                        }
                        akVar.o();
                        cVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != ar.m) {
                            an.a(akVar, l.b);
                            break;
                        }
                        ag p = akVar.p();
                        cVar.b = new ArrayList(p.b);
                        for (i = 0; i < p.b; i++) {
                            a aVar = new a();
                            aVar.read(akVar);
                            cVar.b.add(aVar);
                        }
                        akVar.q();
                        cVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        cVar.c = akVar.z();
                        cVar.c(true);
                        break;
                    default:
                        an.a(akVar, l.b);
                        break;
                }
                akVar.m();
            }
        }

        public void b(ak akVar, c cVar) throws r {
            cVar.n();
            akVar.a(c.e);
            if (cVar.a != null) {
                akVar.a(c.f);
                akVar.a(new ah((byte) 11, (byte) 12, cVar.a.size()));
                for (Entry entry : cVar.a.entrySet()) {
                    akVar.a((String) entry.getKey());
                    ((b) entry.getValue()).write(akVar);
                }
                akVar.e();
                akVar.c();
            }
            if (cVar.b != null && cVar.j()) {
                akVar.a(c.g);
                akVar.a(new ag((byte) 12, cVar.b.size()));
                for (a write : cVar.b) {
                    write.write(akVar);
                }
                akVar.f();
                akVar.c();
            }
            if (cVar.c != null && cVar.m()) {
                akVar.a(c.h);
                akVar.a(cVar.c);
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

    private static class c extends av<c> {
        private c() {
        }

        public void a(ak akVar, c cVar) throws r {
            aq aqVar = (aq) akVar;
            aqVar.a(cVar.a.size());
            for (Entry entry : cVar.a.entrySet()) {
                aqVar.a((String) entry.getKey());
                ((b) entry.getValue()).write(aqVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.j()) {
                bitSet.set(0);
            }
            if (cVar.m()) {
                bitSet.set(1);
            }
            aqVar.a(bitSet, 2);
            if (cVar.j()) {
                aqVar.a(cVar.b.size());
                for (a write : cVar.b) {
                    write.write(aqVar);
                }
            }
            if (cVar.m()) {
                aqVar.a(cVar.c);
            }
        }

        public void b(ak akVar, c cVar) throws r {
            int i = 0;
            aq aqVar = (aq) akVar;
            ah ahVar = new ah((byte) 11, (byte) 12, aqVar.w());
            cVar.a = new HashMap(ahVar.c * 2);
            for (int i2 = 0; i2 < ahVar.c; i2++) {
                String z = aqVar.z();
                b bVar = new b();
                bVar.read(aqVar);
                cVar.a.put(z, bVar);
            }
            cVar.a(true);
            BitSet b = aqVar.b(2);
            if (b.get(0)) {
                ag agVar = new ag((byte) 12, aqVar.w());
                cVar.b = new ArrayList(agVar.b);
                while (i < agVar.b) {
                    a aVar = new a();
                    aVar.read(aqVar);
                    cVar.b.add(aVar);
                    i++;
                }
                cVar.b(true);
            }
            if (b.get(1)) {
                cVar.c = aqVar.z();
                cVar.c(true);
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
        SNAPSHOTS((short) 1, "snapshots"),
        JOURNALS((short) 2, "journals"),
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
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
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
        return a(i);
    }

    static {
        i.put(au.class, new b());
        i.put(av.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.SNAPSHOTS, new x("snapshots", (byte) 1, new aa((byte) 13, new y((byte) 11), new ac((byte) 12, b.class))));
        enumMap.put(e.JOURNALS, new x("journals", (byte) 2, new z(ar.m, new ac((byte) 12, a.class))));
        enumMap.put(e.CHECKSUM, new x("checksum", (byte) 2, new y((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        x.a(c.class, d);
    }

    public c() {
        this.j = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public c(Map<String, b> map) {
        this();
        this.a = map;
    }

    public c(c cVar) {
        this.j = new e[]{e.JOURNALS, e.CHECKSUM};
        if (cVar.e()) {
            Map hashMap = new HashMap();
            for (Entry entry : cVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new b((b) entry.getValue()));
            }
            this.a = hashMap;
        }
        if (cVar.j()) {
            List arrayList = new ArrayList();
            for (a aVar : cVar.b) {
                arrayList.add(new a(aVar));
            }
            this.b = arrayList;
        }
        if (cVar.m()) {
            this.c = cVar.c;
        }
    }

    public c a() {
        return new c(this);
    }

    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int b() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(String str, b bVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bVar);
    }

    public Map<String, b> c() {
        return this.a;
    }

    public c a(Map<String, b> map) {
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
        return this.b == null ? 0 : this.b.size();
    }

    public Iterator<a> g() {
        return this.b == null ? null : this.b.iterator();
    }

    public void a(a aVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(aVar);
    }

    public List<a> h() {
        return this.b;
    }

    public c a(List<a> list) {
        this.b = list;
        return this;
    }

    public void i() {
        this.b = null;
    }

    public boolean j() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String k() {
        return this.c;
    }

    public c a(String str) {
        this.c = str;
        return this;
    }

    public void l() {
        this.c = null;
    }

    public boolean m() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public e a(int i) {
        return e.a(i);
    }

    public void read(ak akVar) throws r {
        ((at) i.get(akVar.D())).b().b(akVar, this);
    }

    public void write(ak akVar) throws r {
        ((at) i.get(akVar.D())).b().a(akVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdTracking(");
        stringBuilder.append("snapshots:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("journals:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("checksum:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void n() throws r {
        if (this.a == null) {
            throw new al("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }
}
