package com.umeng.analytics.pro;

import com.tencent.open.GameAppOperation;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class bq implements cg<bq, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, cs> k;
    private static final long l = 420342210744516016L;
    private static final dk m = new dk("UMEnvelope");
    private static final da n = new da(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 11, (short) 1);
    private static final da o = new da("address", (byte) 11, (short) 2);
    private static final da p = new da(GameAppOperation.GAME_SIGNATURE, (byte) 11, (short) 3);
    private static final da q = new da("serial_num", (byte) 8, (short) 4);
    private static final da r = new da("ts_secs", (byte) 8, (short) 5);
    private static final da s = new da("length", (byte) 8, (short) 6);
    private static final da t = new da("entity", (byte) 11, (short) 7);
    private static final da u = new da("guid", (byte) 11, (short) 8);
    private static final da v = new da("checksum", (byte) 11, (short) 9);
    private static final da w = new da("codex", (byte) 8, (short) 10);
    private static final Map<Class<? extends dn>, do> x = new HashMap();
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

    private static class a extends dp<bq> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (bq) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (bq) cgVar);
        }

        public void a(df dfVar, bq bqVar) throws cm {
            dfVar.j();
            while (true) {
                da l = dfVar.l();
                if (l.b == (byte) 0) {
                    dfVar.k();
                    if (!bqVar.n()) {
                        throw new dg("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!bqVar.r()) {
                        throw new dg("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (bqVar.u()) {
                        bqVar.I();
                        return;
                    } else {
                        throw new dg("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.a = dfVar.z();
                        bqVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.b = dfVar.z();
                        bqVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.c = dfVar.z();
                        bqVar.c(true);
                        break;
                    case (short) 4:
                        if (l.b != (byte) 8) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.d = dfVar.w();
                        bqVar.d(true);
                        break;
                    case (short) 5:
                        if (l.b != (byte) 8) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.e = dfVar.w();
                        bqVar.e(true);
                        break;
                    case (short) 6:
                        if (l.b != (byte) 8) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.f = dfVar.w();
                        bqVar.f(true);
                        break;
                    case (short) 7:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.g = dfVar.A();
                        bqVar.g(true);
                        break;
                    case (short) 8:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.h = dfVar.z();
                        bqVar.h(true);
                        break;
                    case (short) 9:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.i = dfVar.z();
                        bqVar.i(true);
                        break;
                    case (short) 10:
                        if (l.b != (byte) 8) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bqVar.j = dfVar.w();
                        bqVar.j(true);
                        break;
                    default:
                        di.a(dfVar, l.b);
                        break;
                }
                dfVar.m();
            }
        }

        public void b(df dfVar, bq bqVar) throws cm {
            bqVar.I();
            dfVar.a(bq.m);
            if (bqVar.a != null) {
                dfVar.a(bq.n);
                dfVar.a(bqVar.a);
                dfVar.c();
            }
            if (bqVar.b != null) {
                dfVar.a(bq.o);
                dfVar.a(bqVar.b);
                dfVar.c();
            }
            if (bqVar.c != null) {
                dfVar.a(bq.p);
                dfVar.a(bqVar.c);
                dfVar.c();
            }
            dfVar.a(bq.q);
            dfVar.a(bqVar.d);
            dfVar.c();
            dfVar.a(bq.r);
            dfVar.a(bqVar.e);
            dfVar.c();
            dfVar.a(bq.s);
            dfVar.a(bqVar.f);
            dfVar.c();
            if (bqVar.g != null) {
                dfVar.a(bq.t);
                dfVar.a(bqVar.g);
                dfVar.c();
            }
            if (bqVar.h != null) {
                dfVar.a(bq.u);
                dfVar.a(bqVar.h);
                dfVar.c();
            }
            if (bqVar.i != null) {
                dfVar.a(bq.v);
                dfVar.a(bqVar.i);
                dfVar.c();
            }
            if (bqVar.H()) {
                dfVar.a(bq.w);
                dfVar.a(bqVar.j);
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

    private static class c extends dq<bq> {
        private c() {
        }

        public void a(df dfVar, bq bqVar) throws cm {
            dl dlVar = (dl) dfVar;
            dlVar.a(bqVar.a);
            dlVar.a(bqVar.b);
            dlVar.a(bqVar.c);
            dlVar.a(bqVar.d);
            dlVar.a(bqVar.e);
            dlVar.a(bqVar.f);
            dlVar.a(bqVar.g);
            dlVar.a(bqVar.h);
            dlVar.a(bqVar.i);
            BitSet bitSet = new BitSet();
            if (bqVar.H()) {
                bitSet.set(0);
            }
            dlVar.a(bitSet, 1);
            if (bqVar.H()) {
                dlVar.a(bqVar.j);
            }
        }

        public void b(df dfVar, bq bqVar) throws cm {
            dl dlVar = (dl) dfVar;
            bqVar.a = dlVar.z();
            bqVar.a(true);
            bqVar.b = dlVar.z();
            bqVar.b(true);
            bqVar.c = dlVar.z();
            bqVar.c(true);
            bqVar.d = dlVar.w();
            bqVar.d(true);
            bqVar.e = dlVar.w();
            bqVar.e(true);
            bqVar.f = dlVar.w();
            bqVar.f(true);
            bqVar.g = dlVar.A();
            bqVar.g(true);
            bqVar.h = dlVar.z();
            bqVar.h(true);
            bqVar.i = dlVar.z();
            bqVar.i(true);
            if (dlVar.b(1).get(0)) {
                bqVar.j = dlVar.w();
                bqVar.j(true);
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
        VERSION((short) 1, GameAppOperation.QQFAV_DATALINE_VERSION),
        ADDRESS((short) 2, "address"),
        SIGNATURE((short) 3, GameAppOperation.GAME_SIGNATURE),
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

    public /* synthetic */ cn b(int i) {
        return f(i);
    }

    public /* synthetic */ cg p() {
        return a();
    }

    static {
        x.put(dp.class, new b());
        x.put(dq.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new cs(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 1, new ct((byte) 11)));
        enumMap.put(e.ADDRESS, new cs("address", (byte) 1, new ct((byte) 11)));
        enumMap.put(e.SIGNATURE, new cs(GameAppOperation.GAME_SIGNATURE, (byte) 1, new ct((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new cs("serial_num", (byte) 1, new ct((byte) 8)));
        enumMap.put(e.TS_SECS, new cs("ts_secs", (byte) 1, new ct((byte) 8)));
        enumMap.put(e.LENGTH, new cs("length", (byte) 1, new ct((byte) 8)));
        enumMap.put(e.ENTITY, new cs("entity", (byte) 1, new ct((byte) 11, true)));
        enumMap.put(e.GUID, new cs("guid", (byte) 1, new ct((byte) 11)));
        enumMap.put(e.CHECKSUM, new cs("checksum", (byte) 1, new ct((byte) 11)));
        enumMap.put(e.CODEX, new cs("codex", (byte) 2, new ct((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        cs.a(bq.class, k);
    }

    public bq() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public bq(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
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

    public bq(bq bqVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = bqVar.C;
        if (bqVar.e()) {
            this.a = bqVar.a;
        }
        if (bqVar.h()) {
            this.b = bqVar.b;
        }
        if (bqVar.k()) {
            this.c = bqVar.c;
        }
        this.d = bqVar.d;
        this.e = bqVar.e;
        this.f = bqVar.f;
        if (bqVar.y()) {
            this.g = ch.d(bqVar.g);
        }
        if (bqVar.B()) {
            this.h = bqVar.h;
        }
        if (bqVar.E()) {
            this.i = bqVar.i;
        }
        this.j = bqVar.j;
    }

    public bq a() {
        return new bq(this);
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

    public bq a(String str) {
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

    public bq b(String str) {
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

    public bq c(String str) {
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

    public bq a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void m() {
        this.C = cd.b(this.C, 0);
    }

    public boolean n() {
        return cd.a(this.C, 0);
    }

    public void d(boolean z) {
        this.C = cd.a(this.C, 0, z);
    }

    public int o() {
        return this.e;
    }

    public bq c(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void q() {
        this.C = cd.b(this.C, 1);
    }

    public boolean r() {
        return cd.a(this.C, 1);
    }

    public void e(boolean z) {
        this.C = cd.a(this.C, 1, z);
    }

    public int s() {
        return this.f;
    }

    public bq d(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void t() {
        this.C = cd.b(this.C, 2);
    }

    public boolean u() {
        return cd.a(this.C, 2);
    }

    public void f(boolean z) {
        this.C = cd.a(this.C, 2, z);
    }

    public byte[] v() {
        a(ch.c(this.g));
        return this.g == null ? null : this.g.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public bq a(byte[] bArr) {
        a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    public bq a(ByteBuffer byteBuffer) {
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

    public bq d(String str) {
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

    public bq e(String str) {
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

    public bq e(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void G() {
        this.C = cd.b(this.C, 3);
    }

    public boolean H() {
        return cd.a(this.C, 3);
    }

    public void j(boolean z) {
        this.C = cd.a(this.C, 3, z);
    }

    public e f(int i) {
        return e.a(i);
    }

    public void a(df dfVar) throws cm {
        ((do) x.get(dfVar.D())).b().b(dfVar, this);
    }

    public void b(df dfVar) throws cm {
        ((do) x.get(dfVar.D())).b().a(dfVar, this);
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
            ch.a(this.g, stringBuilder);
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

    public void I() throws cm {
        if (this.a == null) {
            throw new dg("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new dg("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new dg("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new dg("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new dg("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
            throw new dg("Required field 'checksum' was not present! Struct: " + toString());
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
            this.C = (byte) 0;
            a(new cz(new dr((InputStream) objectInputStream)));
        } catch (cm e) {
            throw new IOException(e.getMessage());
        }
    }
}
