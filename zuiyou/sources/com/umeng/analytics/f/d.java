package com.umeng.analytics.f;

import a.a.a.b.f;
import a.a.a.b.g;
import a.a.a.b.i;
import a.a.a.b.k;
import a.a.a.b.l;
import a.a.a.j;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import org.mozilla.classfile.ByteCode;

public class d implements a.a.a.d<d, e>, Serializable, Cloneable {
    public static final Map<e, a.a.a.a.b> d;
    private static final long e = -5764118265293965743L;
    private static final k f = new k("IdTracking");
    private static final a.a.a.b.c g = new a.a.a.b.c("snapshots", (byte) 13, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("journals", (byte) 15, (short) 2);
    private static final a.a.a.b.c i = new a.a.a.b.c("checksum", ByteCode.T_LONG, (short) 3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    public Map<String, c> a;
    public List<b> b;
    public String c;
    private e[] k;

    private static class a extends a.a.a.c.c<d> {
        private a() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (d) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (d) dVar);
        }

        public void a(f fVar, d dVar) throws j {
            fVar.f();
            while (true) {
                a.a.a.b.c h = fVar.h();
                if (h.b == (byte) 0) {
                    fVar.g();
                    dVar.o();
                    return;
                }
                int i;
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 13) {
                            g.a(fVar, h.b);
                            break;
                        }
                        a.a.a.b.e j = fVar.j();
                        dVar.a = new HashMap(j.c * 2);
                        for (i = 0; i < j.c; i++) {
                            String v = fVar.v();
                            c cVar = new c();
                            cVar.a(fVar);
                            dVar.a.put(v, cVar);
                        }
                        fVar.k();
                        dVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 15) {
                            g.a(fVar, h.b);
                            break;
                        }
                        a.a.a.b.d l = fVar.l();
                        dVar.b = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            b bVar = new b();
                            bVar.a(fVar);
                            dVar.b.add(bVar);
                        }
                        fVar.m();
                        dVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        dVar.c = fVar.v();
                        dVar.c(true);
                        break;
                    default:
                        g.a(fVar, h.b);
                        break;
                }
                fVar.i();
            }
        }

        public void b(f fVar, d dVar) throws j {
            dVar.o();
            fVar.a(d.f);
            if (dVar.a != null) {
                fVar.a(d.g);
                fVar.a(new a.a.a.b.e(ByteCode.T_LONG, (byte) 12, dVar.a.size()));
                for (Entry entry : dVar.a.entrySet()) {
                    fVar.a((String) entry.getKey());
                    ((c) entry.getValue()).b(fVar);
                }
                fVar.d();
                fVar.b();
            }
            if (dVar.b != null && dVar.k()) {
                fVar.a(d.h);
                fVar.a(new a.a.a.b.d((byte) 12, dVar.b.size()));
                for (b b : dVar.b) {
                    b.b(fVar);
                }
                fVar.e();
                fVar.b();
            }
            if (dVar.c != null && dVar.n()) {
                fVar.a(d.i);
                fVar.a(dVar.c);
                fVar.b();
            }
            fVar.c();
            fVar.a();
        }
    }

    private static class b implements a.a.a.c.b {
        private b() {
        }

        public /* synthetic */ a.a.a.c.a a() {
            return b();
        }

        public a b() {
            return new a();
        }
    }

    private static class c extends a.a.a.c.d<d> {
        private c() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (d) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (d) dVar);
        }

        public void a(f fVar, d dVar) throws j {
            fVar = (l) fVar;
            fVar.a(dVar.a.size());
            for (Entry entry : dVar.a.entrySet()) {
                fVar.a((String) entry.getKey());
                ((c) entry.getValue()).b(fVar);
            }
            BitSet bitSet = new BitSet();
            if (dVar.k()) {
                bitSet.set(0);
            }
            if (dVar.n()) {
                bitSet.set(1);
            }
            fVar.a(bitSet, 2);
            if (dVar.k()) {
                fVar.a(dVar.b.size());
                for (b b : dVar.b) {
                    b.b(fVar);
                }
            }
            if (dVar.n()) {
                fVar.a(dVar.c);
            }
        }

        public void b(f fVar, d dVar) throws j {
            int i = 0;
            fVar = (l) fVar;
            a.a.a.b.e eVar = new a.a.a.b.e(ByteCode.T_LONG, (byte) 12, fVar.s());
            dVar.a = new HashMap(eVar.c * 2);
            for (int i2 = 0; i2 < eVar.c; i2++) {
                String v = fVar.v();
                c cVar = new c();
                cVar.a(fVar);
                dVar.a.put(v, cVar);
            }
            dVar.a(true);
            BitSet b = fVar.b(2);
            if (b.get(0)) {
                a.a.a.b.d dVar2 = new a.a.a.b.d((byte) 12, fVar.s());
                dVar.b = new ArrayList(dVar2.b);
                while (i < dVar2.b) {
                    b bVar = new b();
                    bVar.a(fVar);
                    dVar.b.add(bVar);
                    i++;
                }
                dVar.b(true);
            }
            if (b.get(1)) {
                dVar.c = fVar.v();
                dVar.c(true);
            }
        }
    }

    private static class d implements a.a.a.c.b {
        private d() {
        }

        public /* synthetic */ a.a.a.c.a a() {
            return b();
        }

        public c b() {
            return new c();
        }
    }

    public enum e implements a.a.a.f {
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

    public /* synthetic */ a.a.a.f b(int i) {
        return a(i);
    }

    public /* synthetic */ a.a.a.d p() {
        return a();
    }

    static {
        j.put(a.a.a.c.c.class, new b());
        j.put(a.a.a.c.d.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.SNAPSHOTS, new a.a.a.a.b("snapshots", (byte) 1, new a.a.a.a.e((byte) 13, new a.a.a.a.c(ByteCode.T_LONG), new a.a.a.a.g((byte) 12, c.class))));
        enumMap.put(e.JOURNALS, new a.a.a.a.b("journals", (byte) 2, new a.a.a.a.d((byte) 15, new a.a.a.a.g((byte) 12, b.class))));
        enumMap.put(e.CHECKSUM, new a.a.a.a.b("checksum", (byte) 2, new a.a.a.a.c(ByteCode.T_LONG)));
        d = Collections.unmodifiableMap(enumMap);
        a.a.a.a.b.a(d.class, d);
    }

    public d() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public d(Map<String, c> map) {
        this();
        this.a = map;
    }

    public d(d dVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (dVar.f()) {
            Map hashMap = new HashMap();
            for (Entry entry : dVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new c((c) entry.getValue()));
            }
            this.a = hashMap;
        }
        if (dVar.k()) {
            List arrayList = new ArrayList();
            for (b bVar : dVar.b) {
                arrayList.add(new b(bVar));
            }
            this.b = arrayList;
        }
        if (dVar.n()) {
            this.c = dVar.c;
        }
    }

    public d a() {
        return new d(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(String str, c cVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, cVar);
    }

    public Map<String, c> d() {
        return this.a;
    }

    public d a(Map<String, c> map) {
        this.a = map;
        return this;
    }

    public void e() {
        this.a = null;
    }

    public boolean f() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int g() {
        return this.b == null ? 0 : this.b.size();
    }

    public Iterator<b> h() {
        return this.b == null ? null : this.b.iterator();
    }

    public void a(b bVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(bVar);
    }

    public List<b> i() {
        return this.b;
    }

    public d a(List<b> list) {
        this.b = list;
        return this;
    }

    public void j() {
        this.b = null;
    }

    public boolean k() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String l() {
        return this.c;
    }

    public d a(String str) {
        this.c = str;
        return this;
    }

    public void m() {
        this.c = null;
    }

    public boolean n() {
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

    public void a(f fVar) throws j {
        ((a.a.a.c.b) j.get(fVar.y())).a().a(fVar, this);
    }

    public void b(f fVar) throws j {
        ((a.a.a.c.b) j.get(fVar.y())).a().b(fVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdTracking(");
        stringBuilder.append("snapshots:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("journals:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (n()) {
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

    public void o() throws j {
        if (this.a == null) {
            throw new i("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            b(new a.a.a.b.b(new a.a.a.d.a(objectOutputStream)));
        } catch (j e) {
            throw new IOException(e.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            a(new a.a.a.b.b(new a.a.a.d.a(objectInputStream)));
        } catch (j e) {
            throw new IOException(e.getMessage());
        }
    }
}
