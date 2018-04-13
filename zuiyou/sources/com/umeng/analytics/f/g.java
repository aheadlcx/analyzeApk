package com.umeng.analytics.f;

import a.a.a.b.f;
import a.a.a.b.i;
import a.a.a.b.k;
import a.a.a.b.l;
import a.a.a.j;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mozilla.classfile.ByteCode;

public class g implements a.a.a.d<g, e>, Serializable, Cloneable {
    public static final Map<e, a.a.a.a.b> d;
    private static final long e = -4549277923241195391L;
    private static final k f = new k("Response");
    private static final a.a.a.b.c g = new a.a.a.b.c("resp_code", (byte) 8, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("msg", ByteCode.T_LONG, (short) 2);
    private static final a.a.a.b.c i = new a.a.a.b.c(com.umeng.analytics.b.g.N, (byte) 12, (short) 3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    private static final int k = 0;
    public int a;
    public String b;
    public e c;
    private byte l;
    private e[] m;

    private static class a extends a.a.a.c.c<g> {
        private a() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (g) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (g) dVar);
        }

        public void a(f fVar, g gVar) throws j {
            fVar.f();
            while (true) {
                a.a.a.b.c h = fVar.h();
                if (h.b == (byte) 0) {
                    fVar.g();
                    if (gVar.e()) {
                        gVar.l();
                        return;
                    }
                    throw new i("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 8) {
                            a.a.a.b.g.a(fVar, h.b);
                            break;
                        }
                        gVar.a = fVar.s();
                        gVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != ByteCode.T_LONG) {
                            a.a.a.b.g.a(fVar, h.b);
                            break;
                        }
                        gVar.b = fVar.v();
                        gVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 12) {
                            a.a.a.b.g.a(fVar, h.b);
                            break;
                        }
                        gVar.c = new e();
                        gVar.c.a(fVar);
                        gVar.c(true);
                        break;
                    default:
                        a.a.a.b.g.a(fVar, h.b);
                        break;
                }
                fVar.i();
            }
        }

        public void b(f fVar, g gVar) throws j {
            gVar.l();
            fVar.a(g.f);
            fVar.a(g.g);
            fVar.a(gVar.a);
            fVar.b();
            if (gVar.b != null && gVar.h()) {
                fVar.a(g.h);
                fVar.a(gVar.b);
                fVar.b();
            }
            if (gVar.c != null && gVar.k()) {
                fVar.a(g.i);
                gVar.c.b(fVar);
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

    private static class c extends a.a.a.c.d<g> {
        private c() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (g) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (g) dVar);
        }

        public void a(f fVar, g gVar) throws j {
            fVar = (l) fVar;
            fVar.a(gVar.a);
            BitSet bitSet = new BitSet();
            if (gVar.h()) {
                bitSet.set(0);
            }
            if (gVar.k()) {
                bitSet.set(1);
            }
            fVar.a(bitSet, 2);
            if (gVar.h()) {
                fVar.a(gVar.b);
            }
            if (gVar.k()) {
                gVar.c.b(fVar);
            }
        }

        public void b(f fVar, g gVar) throws j {
            fVar = (l) fVar;
            gVar.a = fVar.s();
            gVar.a(true);
            BitSet b = fVar.b(2);
            if (b.get(0)) {
                gVar.b = fVar.v();
                gVar.b(true);
            }
            if (b.get(1)) {
                gVar.c = new e();
                gVar.c.a(fVar);
                gVar.c(true);
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
        RESP_CODE((short) 1, "resp_code"),
        MSG((short) 2, "msg"),
        IMPRINT((short) 3, com.umeng.analytics.b.g.N);
        
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

    public /* synthetic */ a.a.a.f b(int i) {
        return c(i);
    }

    public /* synthetic */ a.a.a.d p() {
        return a();
    }

    static {
        j.put(a.a.a.c.c.class, new b());
        j.put(a.a.a.c.d.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.RESP_CODE, new a.a.a.a.b("resp_code", (byte) 1, new a.a.a.a.c((byte) 8)));
        enumMap.put(e.MSG, new a.a.a.a.b("msg", (byte) 2, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.IMPRINT, new a.a.a.a.b(com.umeng.analytics.b.g.N, (byte) 2, new a.a.a.a.g((byte) 12, e.class)));
        d = Collections.unmodifiableMap(enumMap);
        a.a.a.a.b.a(g.class, d);
    }

    public g() {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
    }

    public g(int i) {
        this();
        this.a = i;
        a(true);
    }

    public g(g gVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
        this.l = gVar.l;
        this.a = gVar.a;
        if (gVar.h()) {
            this.b = gVar.b;
        }
        if (gVar.k()) {
            this.c = new e(gVar.c);
        }
    }

    public g a() {
        return new g(this);
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

    public g a(int i) {
        this.a = i;
        a(true);
        return this;
    }

    public void d() {
        this.l = a.a.a.a.b(this.l, 0);
    }

    public boolean e() {
        return a.a.a.a.a(this.l, 0);
    }

    public void a(boolean z) {
        this.l = a.a.a.a.a(this.l, 0, z);
    }

    public String f() {
        return this.b;
    }

    public g a(String str) {
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

    public e i() {
        return this.c;
    }

    public g a(e eVar) {
        this.c = eVar;
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

    public void a(f fVar) throws j {
        ((a.a.a.c.b) j.get(fVar.y())).a().a(fVar, this);
    }

    public void b(f fVar) throws j {
        ((a.a.a.c.b) j.get(fVar.y())).a().b(fVar, this);
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

    public void l() throws j {
        if (this.c != null) {
            this.c.m();
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
            this.l = (byte) 0;
            a(new a.a.a.b.b(new a.a.a.d.a(objectInputStream)));
        } catch (j e) {
            throw new IOException(e.getMessage());
        }
    }
}
