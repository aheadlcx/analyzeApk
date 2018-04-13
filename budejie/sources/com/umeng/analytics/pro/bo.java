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

public class bo implements cg<bo, e>, Serializable, Cloneable {
    public static final Map<e, cs> d;
    private static final long e = 7501688097813630241L;
    private static final dk f = new dk("ImprintValue");
    private static final da g = new da("value", (byte) 11, (short) 1);
    private static final da h = new da("ts", (byte) 10, (short) 2);
    private static final da i = new da("guid", (byte) 11, (short) 3);
    private static final Map<Class<? extends dn>, do> j = new HashMap();
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private e[] m;

    private static class a extends dp<bo> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (bo) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (bo) cgVar);
        }

        public void a(df dfVar, bo boVar) throws cm {
            dfVar.j();
            while (true) {
                da l = dfVar.l();
                if (l.b == (byte) 0) {
                    dfVar.k();
                    if (boVar.h()) {
                        boVar.l();
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
                        boVar.a = dfVar.z();
                        boVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 10) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        boVar.b = dfVar.x();
                        boVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        boVar.c = dfVar.z();
                        boVar.c(true);
                        break;
                    default:
                        di.a(dfVar, l.b);
                        break;
                }
                dfVar.m();
            }
        }

        public void b(df dfVar, bo boVar) throws cm {
            boVar.l();
            dfVar.a(bo.f);
            if (boVar.a != null && boVar.e()) {
                dfVar.a(bo.g);
                dfVar.a(boVar.a);
                dfVar.c();
            }
            dfVar.a(bo.h);
            dfVar.a(boVar.b);
            dfVar.c();
            if (boVar.c != null) {
                dfVar.a(bo.i);
                dfVar.a(boVar.c);
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

    private static class c extends dq<bo> {
        private c() {
        }

        public void a(df dfVar, bo boVar) throws cm {
            dl dlVar = (dl) dfVar;
            dlVar.a(boVar.b);
            dlVar.a(boVar.c);
            BitSet bitSet = new BitSet();
            if (boVar.e()) {
                bitSet.set(0);
            }
            dlVar.a(bitSet, 1);
            if (boVar.e()) {
                dlVar.a(boVar.a);
            }
        }

        public void b(df dfVar, bo boVar) throws cm {
            dl dlVar = (dl) dfVar;
            boVar.b = dlVar.x();
            boVar.b(true);
            boVar.c = dlVar.z();
            boVar.c(true);
            if (dlVar.b(1).get(0)) {
                boVar.a = dlVar.z();
                boVar.a(true);
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
        VALUE((short) 1, "value"),
        TS((short) 2, "ts"),
        GUID((short) 3, "guid");
        
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
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
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
        enumMap.put(e.VALUE, new cs("value", (byte) 2, new ct((byte) 11)));
        enumMap.put(e.TS, new cs("ts", (byte) 1, new ct((byte) 10)));
        enumMap.put(e.GUID, new cs("guid", (byte) 1, new ct((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        cs.a(bo.class, d);
    }

    public bo() {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
    }

    public bo(long j, String str) {
        this();
        this.b = j;
        b(true);
        this.c = str;
    }

    public bo(bo boVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
        this.l = boVar.l;
        if (boVar.e()) {
            this.a = boVar.a;
        }
        this.b = boVar.b;
        if (boVar.k()) {
            this.c = boVar.c;
        }
    }

    public bo a() {
        return new bo(this);
    }

    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public String c() {
        return this.a;
    }

    public bo a(String str) {
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

    public long f() {
        return this.b;
    }

    public bo a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void g() {
        this.l = cd.b(this.l, 0);
    }

    public boolean h() {
        return cd.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = cd.a(this.l, 0, z);
    }

    public String i() {
        return this.c;
    }

    public bo b(String str) {
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
        StringBuilder stringBuilder = new StringBuilder("ImprintValue(");
        Object obj = 1;
        if (e()) {
            stringBuilder.append("value:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws cm {
        if (this.c == null) {
            throw new dg("Required field 'guid' was not present! Struct: " + toString());
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
            this.l = (byte) 0;
            a(new cz(new dr((InputStream) objectInputStream)));
        } catch (cm e) {
            throw new IOException(e.getMessage());
        }
    }
}
