package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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

public class bm implements cg<bm, e>, Serializable, Cloneable {
    public static final Map<e, cs> d;
    private static final long e = -5764118265293965743L;
    private static final dk f = new dk("IdTracking");
    private static final da g = new da("snapshots", dm.k, (short) 1);
    private static final da h = new da("journals", dm.m, (short) 2);
    private static final da i = new da("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends dn>, do> j = new HashMap();
    public Map<String, bl> a;
    public List<bk> b;
    public String c;
    private e[] k;

    private static class a extends dp<bm> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (bm) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (bm) cgVar);
        }

        public void a(df dfVar, bm bmVar) throws cm {
            dfVar.j();
            while (true) {
                da l = dfVar.l();
                if (l.b == (byte) 0) {
                    dfVar.k();
                    bmVar.o();
                    return;
                }
                int i;
                switch (l.c) {
                    case (short) 1:
                        if (l.b != dm.k) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        dc n = dfVar.n();
                        bmVar.a = new HashMap(n.c * 2);
                        for (i = 0; i < n.c; i++) {
                            String z = dfVar.z();
                            bl blVar = new bl();
                            blVar.a(dfVar);
                            bmVar.a.put(z, blVar);
                        }
                        dfVar.o();
                        bmVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != dm.m) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        db p = dfVar.p();
                        bmVar.b = new ArrayList(p.b);
                        for (i = 0; i < p.b; i++) {
                            bk bkVar = new bk();
                            bkVar.a(dfVar);
                            bmVar.b.add(bkVar);
                        }
                        dfVar.q();
                        bmVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bmVar.c = dfVar.z();
                        bmVar.c(true);
                        break;
                    default:
                        di.a(dfVar, l.b);
                        break;
                }
                dfVar.m();
            }
        }

        public void b(df dfVar, bm bmVar) throws cm {
            bmVar.o();
            dfVar.a(bm.f);
            if (bmVar.a != null) {
                dfVar.a(bm.g);
                dfVar.a(new dc((byte) 11, (byte) 12, bmVar.a.size()));
                for (Entry entry : bmVar.a.entrySet()) {
                    dfVar.a((String) entry.getKey());
                    ((bl) entry.getValue()).b(dfVar);
                }
                dfVar.e();
                dfVar.c();
            }
            if (bmVar.b != null && bmVar.k()) {
                dfVar.a(bm.h);
                dfVar.a(new db((byte) 12, bmVar.b.size()));
                for (bk b : bmVar.b) {
                    b.b(dfVar);
                }
                dfVar.f();
                dfVar.c();
            }
            if (bmVar.c != null && bmVar.n()) {
                dfVar.a(bm.i);
                dfVar.a(bmVar.c);
                dfVar.c();
            }
            dfVar.d();
            dfVar.b();
        }
    }

    private static class b implements do {
        private b() {
        }

        public /* synthetic */ dn b() {
            return a();
        }

        public a a() {
            return new a();
        }
    }

    private static class c extends dq<bm> {
        private c() {
        }

        public void a(df dfVar, bm bmVar) throws cm {
            dfVar = (dl) dfVar;
            dfVar.a(bmVar.a.size());
            for (Entry entry : bmVar.a.entrySet()) {
                dfVar.a((String) entry.getKey());
                ((bl) entry.getValue()).b(dfVar);
            }
            BitSet bitSet = new BitSet();
            if (bmVar.k()) {
                bitSet.set(0);
            }
            if (bmVar.n()) {
                bitSet.set(1);
            }
            dfVar.a(bitSet, 2);
            if (bmVar.k()) {
                dfVar.a(bmVar.b.size());
                for (bk b : bmVar.b) {
                    b.b(dfVar);
                }
            }
            if (bmVar.n()) {
                dfVar.a(bmVar.c);
            }
        }

        public void b(df dfVar, bm bmVar) throws cm {
            int i = 0;
            dfVar = (dl) dfVar;
            dc dcVar = new dc((byte) 11, (byte) 12, dfVar.w());
            bmVar.a = new HashMap(dcVar.c * 2);
            for (int i2 = 0; i2 < dcVar.c; i2++) {
                String z = dfVar.z();
                bl blVar = new bl();
                blVar.a(dfVar);
                bmVar.a.put(z, blVar);
            }
            bmVar.a(true);
            BitSet b = dfVar.b(2);
            if (b.get(0)) {
                db dbVar = new db((byte) 12, dfVar.w());
                bmVar.b = new ArrayList(dbVar.b);
                while (i < dbVar.b) {
                    bk bkVar = new bk();
                    bkVar.a(dfVar);
                    bmVar.b.add(bkVar);
                    i++;
                }
                bmVar.b(true);
            }
            if (b.get(1)) {
                bmVar.c = dfVar.z();
                bmVar.c(true);
            }
        }
    }

    private static class d implements do {
        private d() {
        }

        public /* synthetic */ dn b() {
            return a();
        }

        public c a() {
            return new c();
        }
    }

    public enum e implements cn {
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

    public /* synthetic */ cn b(int i) {
        return a(i);
    }

    public /* synthetic */ cg p() {
        return a();
    }

    static {
        j.put(dp.class, new b());
        j.put(dq.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.SNAPSHOTS, new cs("snapshots", (byte) 1, new cv(dm.k, new ct((byte) 11), new cx((byte) 12, bl.class))));
        enumMap.put(e.JOURNALS, new cs("journals", (byte) 2, new cu(dm.m, new cx((byte) 12, bk.class))));
        enumMap.put(e.CHECKSUM, new cs("checksum", (byte) 2, new ct((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        cs.a(bm.class, d);
    }

    public bm() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public bm(Map<String, bl> map) {
        this();
        this.a = map;
    }

    public bm(bm bmVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (bmVar.f()) {
            Map hashMap = new HashMap();
            for (Entry entry : bmVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new bl((bl) entry.getValue()));
            }
            this.a = hashMap;
        }
        if (bmVar.k()) {
            List arrayList = new ArrayList();
            for (bk bkVar : bmVar.b) {
                arrayList.add(new bk(bkVar));
            }
            this.b = arrayList;
        }
        if (bmVar.n()) {
            this.c = bmVar.c;
        }
    }

    public bm a() {
        return new bm(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(String str, bl blVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, blVar);
    }

    public Map<String, bl> d() {
        return this.a;
    }

    public bm a(Map<String, bl> map) {
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

    public Iterator<bk> h() {
        return this.b == null ? null : this.b.iterator();
    }

    public void a(bk bkVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(bkVar);
    }

    public List<bk> i() {
        return this.b;
    }

    public bm a(List<bk> list) {
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

    public bm a(String str) {
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

    public void a(df dfVar) throws cm {
        ((do) j.get(dfVar.D())).b().b(dfVar, this);
    }

    public void b(df dfVar) throws cm {
        ((do) j.get(dfVar.D())).b().a(dfVar, this);
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

    public void o() throws cm {
        if (this.a == null) {
            throw new dg("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            b(new cz(new dr((OutputStream) objectOutputStream)));
        } catch (cm e) {
            throw new IOException(e.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            a(new cz(new dr((InputStream) objectInputStream)));
        } catch (cm e) {
            throw new IOException(e.getMessage());
        }
    }
}
