package com.umeng.commonsdk.proguard;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class h implements l<h, e>, Serializable, Cloneable {
    public static final Map<e, x> k;
    private static final ap l = new ap("UMEnvelope");
    private static final af m = new af("version", (byte) 11, (short) 1);
    private static final af n = new af("address", (byte) 11, (short) 2);
    private static final af o = new af("signature", (byte) 11, (short) 3);
    private static final af p = new af("serial_num", (byte) 8, (short) 4);
    private static final af q = new af("ts_secs", (byte) 8, (short) 5);
    private static final af r = new af("length", (byte) 8, (short) 6);
    private static final af s = new af("entity", (byte) 11, (short) 7);
    private static final af t = new af("guid", (byte) 11, (short) 8);
    private static final af u = new af("checksum", (byte) 11, (short) 9);
    private static final af v = new af("codex", (byte) 8, (short) 10);
    private static final Map<Class<? extends as>, at> w = new HashMap();
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
    private byte x;
    private e[] y;

    private static class a extends au<h> {
        private a() {
        }

        public /* synthetic */ void a(ak akVar, l lVar) throws r {
            b(akVar, (h) lVar);
        }

        public /* synthetic */ void b(ak akVar, l lVar) throws r {
            a(akVar, (h) lVar);
        }

        public void a(ak akVar, h hVar) throws r {
            akVar.j();
            while (true) {
                af l = akVar.l();
                if (l.b == (byte) 0) {
                    akVar.k();
                    if (!hVar.m()) {
                        throw new al("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!hVar.p()) {
                        throw new al("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (hVar.s()) {
                        hVar.G();
                        return;
                    } else {
                        throw new al("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.a = akVar.z();
                        hVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.b = akVar.z();
                        hVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.c = akVar.z();
                        hVar.c(true);
                        break;
                    case (short) 4:
                        if (l.b != (byte) 8) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.d = akVar.w();
                        hVar.d(true);
                        break;
                    case (short) 5:
                        if (l.b != (byte) 8) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.e = akVar.w();
                        hVar.e(true);
                        break;
                    case (short) 6:
                        if (l.b != (byte) 8) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.f = akVar.w();
                        hVar.f(true);
                        break;
                    case (short) 7:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.g = akVar.A();
                        hVar.g(true);
                        break;
                    case (short) 8:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.h = akVar.z();
                        hVar.h(true);
                        break;
                    case (short) 9:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.i = akVar.z();
                        hVar.i(true);
                        break;
                    case (short) 10:
                        if (l.b != (byte) 8) {
                            an.a(akVar, l.b);
                            break;
                        }
                        hVar.j = akVar.w();
                        hVar.j(true);
                        break;
                    default:
                        an.a(akVar, l.b);
                        break;
                }
                akVar.m();
            }
        }

        public void b(ak akVar, h hVar) throws r {
            hVar.G();
            akVar.a(h.l);
            if (hVar.a != null) {
                akVar.a(h.m);
                akVar.a(hVar.a);
                akVar.c();
            }
            if (hVar.b != null) {
                akVar.a(h.n);
                akVar.a(hVar.b);
                akVar.c();
            }
            if (hVar.c != null) {
                akVar.a(h.o);
                akVar.a(hVar.c);
                akVar.c();
            }
            akVar.a(h.p);
            akVar.a(hVar.d);
            akVar.c();
            akVar.a(h.q);
            akVar.a(hVar.e);
            akVar.c();
            akVar.a(h.r);
            akVar.a(hVar.f);
            akVar.c();
            if (hVar.g != null) {
                akVar.a(h.s);
                akVar.a(hVar.g);
                akVar.c();
            }
            if (hVar.h != null) {
                akVar.a(h.t);
                akVar.a(hVar.h);
                akVar.c();
            }
            if (hVar.i != null) {
                akVar.a(h.u);
                akVar.a(hVar.i);
                akVar.c();
            }
            if (hVar.F()) {
                akVar.a(h.v);
                akVar.a(hVar.j);
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

    private static class c extends av<h> {
        private c() {
        }

        public void a(ak akVar, h hVar) throws r {
            aq aqVar = (aq) akVar;
            aqVar.a(hVar.a);
            aqVar.a(hVar.b);
            aqVar.a(hVar.c);
            aqVar.a(hVar.d);
            aqVar.a(hVar.e);
            aqVar.a(hVar.f);
            aqVar.a(hVar.g);
            aqVar.a(hVar.h);
            aqVar.a(hVar.i);
            BitSet bitSet = new BitSet();
            if (hVar.F()) {
                bitSet.set(0);
            }
            aqVar.a(bitSet, 1);
            if (hVar.F()) {
                aqVar.a(hVar.j);
            }
        }

        public void b(ak akVar, h hVar) throws r {
            aq aqVar = (aq) akVar;
            hVar.a = aqVar.z();
            hVar.a(true);
            hVar.b = aqVar.z();
            hVar.b(true);
            hVar.c = aqVar.z();
            hVar.c(true);
            hVar.d = aqVar.w();
            hVar.d(true);
            hVar.e = aqVar.w();
            hVar.e(true);
            hVar.f = aqVar.w();
            hVar.f(true);
            hVar.g = aqVar.A();
            hVar.g(true);
            hVar.h = aqVar.z();
            hVar.h(true);
            hVar.i = aqVar.z();
            hVar.i(true);
            if (aqVar.b(1).get(0)) {
                hVar.j = aqVar.w();
                hVar.j(true);
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

    public /* synthetic */ l deepCopy() {
        return a();
    }

    public /* synthetic */ s fieldForId(int i) {
        return e(i);
    }

    static {
        w.put(au.class, new b());
        w.put(av.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new x("version", (byte) 1, new y((byte) 11)));
        enumMap.put(e.ADDRESS, new x("address", (byte) 1, new y((byte) 11)));
        enumMap.put(e.SIGNATURE, new x("signature", (byte) 1, new y((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new x("serial_num", (byte) 1, new y((byte) 8)));
        enumMap.put(e.TS_SECS, new x("ts_secs", (byte) 1, new y((byte) 8)));
        enumMap.put(e.LENGTH, new x("length", (byte) 1, new y((byte) 8)));
        enumMap.put(e.ENTITY, new x("entity", (byte) 1, new y((byte) 11, true)));
        enumMap.put(e.GUID, new x("guid", (byte) 1, new y((byte) 11)));
        enumMap.put(e.CHECKSUM, new x("checksum", (byte) 1, new y((byte) 11)));
        enumMap.put(e.CODEX, new x("codex", (byte) 2, new y((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        x.a(h.class, k);
    }

    public h() {
        this.x = (byte) 0;
        this.y = new e[]{e.CODEX};
    }

    public h(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
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

    public h(h hVar) {
        this.x = (byte) 0;
        this.y = new e[]{e.CODEX};
        this.x = hVar.x;
        if (hVar.d()) {
            this.a = hVar.a;
        }
        if (hVar.g()) {
            this.b = hVar.b;
        }
        if (hVar.j()) {
            this.c = hVar.c;
        }
        this.d = hVar.d;
        this.e = hVar.e;
        this.f = hVar.f;
        if (hVar.w()) {
            this.g = m.d(hVar.g);
        }
        if (hVar.z()) {
            this.h = hVar.h;
        }
        if (hVar.C()) {
            this.i = hVar.i;
        }
        this.j = hVar.j;
    }

    public h a() {
        return new h(this);
    }

    public void clear() {
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

    public String b() {
        return this.a;
    }

    public h a(String str) {
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

    public h b(String str) {
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

    public h c(String str) {
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

    public int k() {
        return this.d;
    }

    public h a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void l() {
        this.x = i.b(this.x, 0);
    }

    public boolean m() {
        return i.a(this.x, 0);
    }

    public void d(boolean z) {
        this.x = i.a(this.x, 0, z);
    }

    public int n() {
        return this.e;
    }

    public h b(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void o() {
        this.x = i.b(this.x, 1);
    }

    public boolean p() {
        return i.a(this.x, 1);
    }

    public void e(boolean z) {
        this.x = i.a(this.x, 1, z);
    }

    public int q() {
        return this.f;
    }

    public h c(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void r() {
        this.x = i.b(this.x, 2);
    }

    public boolean s() {
        return i.a(this.x, 2);
    }

    public void f(boolean z) {
        this.x = i.a(this.x, 2, z);
    }

    public byte[] t() {
        a(m.c(this.g));
        return this.g == null ? null : this.g.array();
    }

    public ByteBuffer u() {
        return this.g;
    }

    public h a(byte[] bArr) {
        a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    public h a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void v() {
        this.g = null;
    }

    public boolean w() {
        return this.g != null;
    }

    public void g(boolean z) {
        if (!z) {
            this.g = null;
        }
    }

    public String x() {
        return this.h;
    }

    public h d(String str) {
        this.h = str;
        return this;
    }

    public void y() {
        this.h = null;
    }

    public boolean z() {
        return this.h != null;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public String A() {
        return this.i;
    }

    public h e(String str) {
        this.i = str;
        return this;
    }

    public void B() {
        this.i = null;
    }

    public boolean C() {
        return this.i != null;
    }

    public void i(boolean z) {
        if (!z) {
            this.i = null;
        }
    }

    public int D() {
        return this.j;
    }

    public h d(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void E() {
        this.x = i.b(this.x, 3);
    }

    public boolean F() {
        return i.a(this.x, 3);
    }

    public void j(boolean z) {
        this.x = i.a(this.x, 3, z);
    }

    public e e(int i) {
        return e.a(i);
    }

    public void read(ak akVar) throws r {
        ((at) w.get(akVar.D())).b().b(akVar, this);
    }

    public void write(ak akVar) throws r {
        ((at) w.get(akVar.D())).b().a(akVar, this);
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
            m.a(this.g, stringBuilder);
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
        if (F()) {
            stringBuilder.append(", ");
            stringBuilder.append("codex:");
            stringBuilder.append(this.j);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void G() throws r {
        if (this.a == null) {
            throw new al("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new al("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new al("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new al("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new al("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
            throw new al("Required field 'checksum' was not present! Struct: " + toString());
        }
    }
}
