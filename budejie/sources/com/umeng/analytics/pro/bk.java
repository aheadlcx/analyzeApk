package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class bk implements cg<bk, e>, Serializable, Cloneable {
    public static final Map<e, cs> e;
    private static final long f = 9132678615281394583L;
    private static final dk g = new dk("IdJournal");
    private static final da h = new da("domain", (byte) 11, (short) 1);
    private static final da i = new da("old_id", (byte) 11, (short) 2);
    private static final da j = new da("new_id", (byte) 11, (short) 3);
    private static final da k = new da("ts", (byte) 10, (short) 4);
    private static final Map<Class<? extends dn>, do> l = new HashMap();
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    private static class a extends dp<bk> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (bk) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (bk) cgVar);
        }

        public void a(df dfVar, bk bkVar) throws cm {
            dfVar.j();
            while (true) {
                da l = dfVar.l();
                if (l.b == (byte) 0) {
                    dfVar.k();
                    if (bkVar.n()) {
                        bkVar.o();
                        return;
                    }
                    throw new dg("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bkVar.a = dfVar.z();
                        bkVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bkVar.b = dfVar.z();
                        bkVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bkVar.c = dfVar.z();
                        bkVar.c(true);
                        break;
                    case (short) 4:
                        if (l.b != (byte) 10) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bkVar.d = dfVar.x();
                        bkVar.d(true);
                        break;
                    default:
                        di.a(dfVar, l.b);
                        break;
                }
                dfVar.m();
            }
        }

        public void b(df dfVar, bk bkVar) throws cm {
            bkVar.o();
            dfVar.a(bk.g);
            if (bkVar.a != null) {
                dfVar.a(bk.h);
                dfVar.a(bkVar.a);
                dfVar.c();
            }
            if (bkVar.b != null && bkVar.h()) {
                dfVar.a(bk.i);
                dfVar.a(bkVar.b);
                dfVar.c();
            }
            if (bkVar.c != null) {
                dfVar.a(bk.j);
                dfVar.a(bkVar.c);
                dfVar.c();
            }
            dfVar.a(bk.k);
            dfVar.a(bkVar.d);
            dfVar.c();
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

    private static class c extends dq<bk> {
        private c() {
        }

        public void a(df dfVar, bk bkVar) throws cm {
            dl dlVar = (dl) dfVar;
            dlVar.a(bkVar.a);
            dlVar.a(bkVar.c);
            dlVar.a(bkVar.d);
            BitSet bitSet = new BitSet();
            if (bkVar.h()) {
                bitSet.set(0);
            }
            dlVar.a(bitSet, 1);
            if (bkVar.h()) {
                dlVar.a(bkVar.b);
            }
        }

        public void b(df dfVar, bk bkVar) throws cm {
            dl dlVar = (dl) dfVar;
            bkVar.a = dlVar.z();
            bkVar.a(true);
            bkVar.c = dlVar.z();
            bkVar.c(true);
            bkVar.d = dlVar.x();
            bkVar.d(true);
            if (dlVar.b(1).get(0)) {
                bkVar.b = dlVar.z();
                bkVar.b(true);
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
        DOMAIN((short) 1, "domain"),
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

    public /* synthetic */ cn b(int i) {
        return a(i);
    }

    public /* synthetic */ cg p() {
        return a();
    }

    static {
        l.put(dp.class, new b());
        l.put(dq.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new cs("domain", (byte) 1, new ct((byte) 11)));
        enumMap.put(e.OLD_ID, new cs("old_id", (byte) 2, new ct((byte) 11)));
        enumMap.put(e.NEW_ID, new cs("new_id", (byte) 1, new ct((byte) 11)));
        enumMap.put(e.TS, new cs("ts", (byte) 1, new ct((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        cs.a(bk.class, e);
    }

    public bk() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    public bk(String str, String str2, long j) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j;
        d(true);
    }

    public bk(bk bkVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = bkVar.n;
        if (bkVar.e()) {
            this.a = bkVar.a;
        }
        if (bkVar.h()) {
            this.b = bkVar.b;
        }
        if (bkVar.k()) {
            this.c = bkVar.c;
        }
        this.d = bkVar.d;
    }

    public bk a() {
        return new bk(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
    }

    public String c() {
        return this.a;
    }

    public bk a(String str) {
        this.a = str;
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

    public String f() {
        return this.b;
    }

    public bk b(String str) {
        this.b = str;
        return this;
    }

    public void g() {
        this.b = null;
    }

    public boolean h() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String i() {
        return this.c;
    }

    public bk c(String str) {
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

    public long l() {
        return this.d;
    }

    public bk a(long j) {
        this.d = j;
        d(true);
        return this;
    }

    public void m() {
        this.n = cd.b(this.n, 0);
    }

    public boolean n() {
        return cd.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = cd.a(this.n, 0, z);
    }

    public e a(int i) {
        return e.a(i);
    }

    public void a(df dfVar) throws cm {
        ((do) l.get(dfVar.D())).b().b(dfVar, this);
    }

    public void b(df dfVar) throws cm {
        ((do) l.get(dfVar.D())).b().a(dfVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdJournal(");
        stringBuilder.append("domain:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (h()) {
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

    public void o() throws cm {
        if (this.a == null) {
            throw new dg("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new dg("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = (byte) 0;
            a(new cz(new dr((InputStream) objectInputStream)));
        } catch (cm e) {
            throw new IOException(e.getMessage());
        }
    }
}
