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

public class bp implements cg<bp, e>, Serializable, Cloneable {
    public static final Map<e, cs> d;
    private static final long e = -4549277923241195391L;
    private static final dk f = new dk("Response");
    private static final da g = new da("resp_code", (byte) 8, (short) 1);
    private static final da h = new da("msg", (byte) 11, (short) 2);
    private static final da i = new da(x.N, (byte) 12, (short) 3);
    private static final Map<Class<? extends dn>, do> j = new HashMap();
    private static final int k = 0;
    public int a;
    public String b;
    public bn c;
    private byte l;
    private e[] m;

    private static class a extends dp<bp> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (bp) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (bp) cgVar);
        }

        public void a(df dfVar, bp bpVar) throws cm {
            dfVar.j();
            while (true) {
                da l = dfVar.l();
                if (l.b == (byte) 0) {
                    dfVar.k();
                    if (bpVar.e()) {
                        bpVar.l();
                        return;
                    }
                    throw new dg("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 8) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bpVar.a = dfVar.w();
                        bpVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bpVar.b = dfVar.z();
                        bpVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 12) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bpVar.c = new bn();
                        bpVar.c.a(dfVar);
                        bpVar.c(true);
                        break;
                    default:
                        di.a(dfVar, l.b);
                        break;
                }
                dfVar.m();
            }
        }

        public void b(df dfVar, bp bpVar) throws cm {
            bpVar.l();
            dfVar.a(bp.f);
            dfVar.a(bp.g);
            dfVar.a(bpVar.a);
            dfVar.c();
            if (bpVar.b != null && bpVar.h()) {
                dfVar.a(bp.h);
                dfVar.a(bpVar.b);
                dfVar.c();
            }
            if (bpVar.c != null && bpVar.k()) {
                dfVar.a(bp.i);
                bpVar.c.b(dfVar);
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

    private static class c extends dq<bp> {
        private c() {
        }

        public void a(df dfVar, bp bpVar) throws cm {
            dfVar = (dl) dfVar;
            dfVar.a(bpVar.a);
            BitSet bitSet = new BitSet();
            if (bpVar.h()) {
                bitSet.set(0);
            }
            if (bpVar.k()) {
                bitSet.set(1);
            }
            dfVar.a(bitSet, 2);
            if (bpVar.h()) {
                dfVar.a(bpVar.b);
            }
            if (bpVar.k()) {
                bpVar.c.b(dfVar);
            }
        }

        public void b(df dfVar, bp bpVar) throws cm {
            dfVar = (dl) dfVar;
            bpVar.a = dfVar.w();
            bpVar.a(true);
            BitSet b = dfVar.b(2);
            if (b.get(0)) {
                bpVar.b = dfVar.z();
                bpVar.b(true);
            }
            if (b.get(1)) {
                bpVar.c = new bn();
                bpVar.c.a(dfVar);
                bpVar.c(true);
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
        RESP_CODE((short) 1, "resp_code"),
        MSG((short) 2, "msg"),
        IMPRINT((short) 3, x.N);
        
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
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
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
        return c(i);
    }

    public /* synthetic */ cg p() {
        return a();
    }

    static {
        j.put(dp.class, new b());
        j.put(dq.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.RESP_CODE, new cs("resp_code", (byte) 1, new ct((byte) 8)));
        enumMap.put(e.MSG, new cs("msg", (byte) 2, new ct((byte) 11)));
        enumMap.put(e.IMPRINT, new cs(x.N, (byte) 2, new cx((byte) 12, bn.class)));
        d = Collections.unmodifiableMap(enumMap);
        cs.a(bp.class, d);
    }

    public bp() {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
    }

    public bp(int i) {
        this();
        this.a = i;
        a(true);
    }

    public bp(bp bpVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
        this.l = bpVar.l;
        this.a = bpVar.a;
        if (bpVar.h()) {
            this.b = bpVar.b;
        }
        if (bpVar.k()) {
            this.c = new bn(bpVar.c);
        }
    }

    public bp a() {
        return new bp(this);
    }

    public void b() {
        a(false);
        this.a = 0;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a;
    }

    public bp a(int i) {
        this.a = i;
        a(true);
        return this;
    }

    public void d() {
        this.l = cd.b(this.l, 0);
    }

    public boolean e() {
        return cd.a(this.l, 0);
    }

    public void a(boolean z) {
        this.l = cd.a(this.l, 0, z);
    }

    public String f() {
        return this.b;
    }

    public bp a(String str) {
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

    public bn i() {
        return this.c;
    }

    public bp a(bn bnVar) {
        this.c = bnVar;
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

    public e c(int i) {
        return e.a(i);
    }

    public void a(df dfVar) throws cm {
        ((do) j.get(dfVar.D())).b().b(dfVar, this);
    }

    public void b(df dfVar) throws cm {
        ((do) j.get(dfVar.D())).b().a(dfVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Response(");
        stringBuilder.append("resp_code:");
        stringBuilder.append(this.a);
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("msg:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws cm {
        if (this.c != null) {
            this.c.m();
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
