package com.umeng.analytics.g;

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
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mozilla.classfile.ByteCode;

public class a implements a.a.a.d<a, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, a.a.a.a.b> k;
    private static final long l = 420342210744516016L;
    private static final k m = new k("UMEnvelope");
    private static final a.a.a.b.c n = new a.a.a.b.c("version", ByteCode.T_LONG, (short) 1);
    private static final a.a.a.b.c o = new a.a.a.b.c("address", ByteCode.T_LONG, (short) 2);
    private static final a.a.a.b.c p = new a.a.a.b.c("signature", ByteCode.T_LONG, (short) 3);
    private static final a.a.a.b.c q = new a.a.a.b.c("serial_num", (byte) 8, (short) 4);
    private static final a.a.a.b.c r = new a.a.a.b.c("ts_secs", (byte) 8, (short) 5);
    private static final a.a.a.b.c s = new a.a.a.b.c("length", (byte) 8, (short) 6);
    private static final a.a.a.b.c t = new a.a.a.b.c("entity", ByteCode.T_LONG, (short) 7);
    private static final a.a.a.b.c u = new a.a.a.b.c("guid", ByteCode.T_LONG, (short) 8);
    private static final a.a.a.b.c v = new a.a.a.b.c("checksum", ByteCode.T_LONG, (short) 9);
    private static final a.a.a.b.c w = new a.a.a.b.c("codex", (byte) 8, (short) 10);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    private static class a extends a.a.a.c.c<a> {
        private a() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (a) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (a) dVar);
        }

        public void a(f fVar, a aVar) throws j {
            fVar.f();
            while (true) {
                a.a.a.b.c h = fVar.h();
                if (h.b == (byte) 0) {
                    fVar.g();
                    if (!aVar.n()) {
                        throw new i("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!aVar.r()) {
                        throw new i("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (aVar.u()) {
                        aVar.I();
                        return;
                    } else {
                        throw new i("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.a = fVar.v();
                        aVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.b = fVar.v();
                        aVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.c = fVar.v();
                        aVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 8) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.d = fVar.s();
                        aVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 8) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.e = fVar.s();
                        aVar.e(true);
                        break;
                    case (short) 6:
                        if (h.b != (byte) 8) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.f = fVar.s();
                        aVar.f(true);
                        break;
                    case (short) 7:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.g = fVar.w();
                        aVar.g(true);
                        break;
                    case (short) 8:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.h = fVar.v();
                        aVar.h(true);
                        break;
                    case (short) 9:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.i = fVar.v();
                        aVar.i(true);
                        break;
                    case (short) 10:
                        if (h.b != (byte) 8) {
                            g.a(fVar, h.b);
                            break;
                        }
                        aVar.j = fVar.s();
                        aVar.j(true);
                        break;
                    default:
                        g.a(fVar, h.b);
                        break;
                }
                fVar.i();
            }
        }

        public void b(f fVar, a aVar) throws j {
            aVar.I();
            fVar.a(a.m);
            if (aVar.a != null) {
                fVar.a(a.n);
                fVar.a(aVar.a);
                fVar.b();
            }
            if (aVar.b != null) {
                fVar.a(a.o);
                fVar.a(aVar.b);
                fVar.b();
            }
            if (aVar.c != null) {
                fVar.a(a.p);
                fVar.a(aVar.c);
                fVar.b();
            }
            fVar.a(a.q);
            fVar.a(aVar.d);
            fVar.b();
            fVar.a(a.r);
            fVar.a(aVar.e);
            fVar.b();
            fVar.a(a.s);
            fVar.a(aVar.f);
            fVar.b();
            if (aVar.g != null) {
                fVar.a(a.t);
                fVar.a(aVar.g);
                fVar.b();
            }
            if (aVar.h != null) {
                fVar.a(a.u);
                fVar.a(aVar.h);
                fVar.b();
            }
            if (aVar.i != null) {
                fVar.a(a.v);
                fVar.a(aVar.i);
                fVar.b();
            }
            if (aVar.H()) {
                fVar.a(a.w);
                fVar.a(aVar.j);
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

    private static class c extends a.a.a.c.d<a> {
        private c() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (a) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (a) dVar);
        }

        public void a(f fVar, a aVar) throws j {
            l lVar = (l) fVar;
            lVar.a(aVar.a);
            lVar.a(aVar.b);
            lVar.a(aVar.c);
            lVar.a(aVar.d);
            lVar.a(aVar.e);
            lVar.a(aVar.f);
            lVar.a(aVar.g);
            lVar.a(aVar.h);
            lVar.a(aVar.i);
            BitSet bitSet = new BitSet();
            if (aVar.H()) {
                bitSet.set(0);
            }
            lVar.a(bitSet, 1);
            if (aVar.H()) {
                lVar.a(aVar.j);
            }
        }

        public void b(f fVar, a aVar) throws j {
            l lVar = (l) fVar;
            aVar.a = lVar.v();
            aVar.a(true);
            aVar.b = lVar.v();
            aVar.b(true);
            aVar.c = lVar.v();
            aVar.c(true);
            aVar.d = lVar.s();
            aVar.d(true);
            aVar.e = lVar.s();
            aVar.e(true);
            aVar.f = lVar.s();
            aVar.f(true);
            aVar.g = lVar.w();
            aVar.g(true);
            aVar.h = lVar.v();
            aVar.h(true);
            aVar.i = lVar.v();
            aVar.i(true);
            if (lVar.b(1).get(0)) {
                aVar.j = lVar.s();
                aVar.j(true);
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
        VERSION((short) 1, "version"),
        ADDRESS((short) 2, "address"),
        SIGNATURE((short) 3, "signature"),
        SERIAL_NUM((short) 4, "serial_num"),
        TS_SECS((short) 5, "ts_secs"),
        LENGTH((short) 6, "length"),
        ENTITY((short) 7, "entity"),
        GUID((short) 8, "guid"),
        CHECKSUM((short) 9, "checksum"),
        CODEX((short) 10, "codex");
        
        private static final Map<String, e> k = null;
        private final short l;
        private final String m;

        static {
            k = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
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
            return (e) k.get(str);
        }

        private e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public short a() {
            return this.l;
        }

        public String b() {
            return this.m;
        }
    }

    public /* synthetic */ a.a.a.f b(int i) {
        return f(i);
    }

    public /* synthetic */ a.a.a.d p() {
        return a();
    }

    static {
        x.put(a.a.a.c.c.class, new b());
        x.put(a.a.a.c.d.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new a.a.a.a.b("version", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.ADDRESS, new a.a.a.a.b("address", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.SIGNATURE, new a.a.a.a.b("signature", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.SERIAL_NUM, new a.a.a.a.b("serial_num", (byte) 1, new a.a.a.a.c((byte) 8)));
        enumMap.put(e.TS_SECS, new a.a.a.a.b("ts_secs", (byte) 1, new a.a.a.a.c((byte) 8)));
        enumMap.put(e.LENGTH, new a.a.a.a.b("length", (byte) 1, new a.a.a.a.c((byte) 8)));
        enumMap.put(e.ENTITY, new a.a.a.a.b("entity", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG, true)));
        enumMap.put(e.GUID, new a.a.a.a.b("guid", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.CHECKSUM, new a.a.a.a.b("checksum", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.CODEX, new a.a.a.a.b("codex", (byte) 2, new a.a.a.a.c((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        a.a.a.a.b.a(a.class, k);
    }

    public a() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public a(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public a(a aVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = aVar.C;
        if (aVar.e()) {
            this.a = aVar.a;
        }
        if (aVar.h()) {
            this.b = aVar.b;
        }
        if (aVar.k()) {
            this.c = aVar.c;
        }
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        if (aVar.y()) {
            this.g = a.a.a.c.d(aVar.g);
        }
        if (aVar.B()) {
            this.h = aVar.h;
        }
        if (aVar.E()) {
            this.i = aVar.i;
        }
        this.j = aVar.j;
    }

    public a a() {
        return new a(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public String c() {
        return this.a;
    }

    public a a(String str) {
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

    public a b(String str) {
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

    public a c(String str) {
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

    public int l() {
        return this.d;
    }

    public a a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void m() {
        this.C = a.a.a.a.b(this.C, 0);
    }

    public boolean n() {
        return a.a.a.a.a(this.C, 0);
    }

    public void d(boolean z) {
        this.C = a.a.a.a.a(this.C, 0, z);
    }

    public int o() {
        return this.e;
    }

    public a c(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void q() {
        this.C = a.a.a.a.b(this.C, 1);
    }

    public boolean r() {
        return a.a.a.a.a(this.C, 1);
    }

    public void e(boolean z) {
        this.C = a.a.a.a.a(this.C, 1, z);
    }

    public int s() {
        return this.f;
    }

    public a d(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void t() {
        this.C = a.a.a.a.b(this.C, 2);
    }

    public boolean u() {
        return a.a.a.a.a(this.C, 2);
    }

    public void f(boolean z) {
        this.C = a.a.a.a.a(this.C, 2, z);
    }

    public byte[] v() {
        a(a.a.a.c.c(this.g));
        return this.g == null ? null : this.g.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public a a(byte[] bArr) {
        a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    public a a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void x() {
        this.g = null;
    }

    public boolean y() {
        return this.g != null;
    }

    public void g(boolean z) {
        if (!z) {
            this.g = null;
        }
    }

    public String z() {
        return this.h;
    }

    public a d(String str) {
        this.h = str;
        return this;
    }

    public void A() {
        this.h = null;
    }

    public boolean B() {
        return this.h != null;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public String C() {
        return this.i;
    }

    public a e(String str) {
        this.i = str;
        return this;
    }

    public void D() {
        this.i = null;
    }

    public boolean E() {
        return this.i != null;
    }

    public void i(boolean z) {
        if (!z) {
            this.i = null;
        }
    }

    public int F() {
        return this.j;
    }

    public a e(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void G() {
        this.C = a.a.a.a.b(this.C, 3);
    }

    public boolean H() {
        return a.a.a.a.a(this.C, 3);
    }

    public void j(boolean z) {
        this.C = a.a.a.a.a(this.C, 3, z);
    }

    public e f(int i) {
        return e.a(i);
    }

    public void a(f fVar) throws j {
        ((a.a.a.c.b) x.get(fVar.y())).a().a(fVar, this);
    }

    public void b(f fVar) throws j {
        ((a.a.a.c.b) x.get(fVar.y())).a().b(fVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UMEnvelope(");
        stringBuilder.append("version:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("address:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signature:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("serial_num:");
        stringBuilder.append(this.d);
        stringBuilder.append(", ");
        stringBuilder.append("ts_secs:");
        stringBuilder.append(this.e);
        stringBuilder.append(", ");
        stringBuilder.append("length:");
        stringBuilder.append(this.f);
        stringBuilder.append(", ");
        stringBuilder.append("entity:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            a.a.a.c.a(this.g, stringBuilder);
        }
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.h);
        }
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.i == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.i);
        }
        if (H()) {
            stringBuilder.append(", ");
            stringBuilder.append("codex:");
            stringBuilder.append(this.j);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void I() throws j {
        if (this.a == null) {
            throw new i("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new i("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new i("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new i("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new i("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
            throw new i("Required field 'checksum' was not present! Struct: " + toString());
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
            this.C = (byte) 0;
            a(new a.a.a.b.b(new a.a.a.d.a(objectInputStream)));
        } catch (j e) {
            throw new IOException(e.getMessage());
        }
    }
}
