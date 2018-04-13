package com.umeng.analytics.f;

import a.a.a.b.g;
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

public class f implements a.a.a.d<f, e>, Serializable, Cloneable {
    public static final Map<e, a.a.a.a.b> d;
    private static final long e = 7501688097813630241L;
    private static final k f = new k("ImprintValue");
    private static final a.a.a.b.c g = new a.a.a.b.c("value", ByteCode.T_LONG, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("ts", (byte) 10, (short) 2);
    private static final a.a.a.b.c i = new a.a.a.b.c("guid", ByteCode.T_LONG, (short) 3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private e[] m;

    private static class a extends a.a.a.c.c<f> {
        private a() {
        }

        public /* synthetic */ void a(a.a.a.b.f fVar, a.a.a.d dVar) throws j {
            a(fVar, (f) dVar);
        }

        public /* synthetic */ void b(a.a.a.b.f fVar, a.a.a.d dVar) throws j {
            b(fVar, (f) dVar);
        }

        public void a(a.a.a.b.f fVar, f fVar2) throws j {
            fVar.f();
            while (true) {
                a.a.a.b.c h = fVar.h();
                if (h.b == (byte) 0) {
                    fVar.g();
                    if (fVar2.h()) {
                        fVar2.l();
                        return;
                    }
                    throw new i("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        fVar2.a = fVar.v();
                        fVar2.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 10) {
                            g.a(fVar, h.b);
                            break;
                        }
                        fVar2.b = fVar.t();
                        fVar2.b(true);
                        break;
                    case (short) 3:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        fVar2.c = fVar.v();
                        fVar2.c(true);
                        break;
                    default:
                        g.a(fVar, h.b);
                        break;
                }
                fVar.i();
            }
        }

        public void b(a.a.a.b.f fVar, f fVar2) throws j {
            fVar2.l();
            fVar.a(f.f);
            if (fVar2.a != null && fVar2.e()) {
                fVar.a(f.g);
                fVar.a(fVar2.a);
                fVar.b();
            }
            fVar.a(f.h);
            fVar.a(fVar2.b);
            fVar.b();
            if (fVar2.c != null) {
                fVar.a(f.i);
                fVar.a(fVar2.c);
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

    private static class c extends a.a.a.c.d<f> {
        private c() {
        }

        public /* synthetic */ void a(a.a.a.b.f fVar, a.a.a.d dVar) throws j {
            b(fVar, (f) dVar);
        }

        public /* synthetic */ void b(a.a.a.b.f fVar, a.a.a.d dVar) throws j {
            a(fVar, (f) dVar);
        }

        public void a(a.a.a.b.f fVar, f fVar2) throws j {
            l lVar = (l) fVar;
            lVar.a(fVar2.b);
            lVar.a(fVar2.c);
            BitSet bitSet = new BitSet();
            if (fVar2.e()) {
                bitSet.set(0);
            }
            lVar.a(bitSet, 1);
            if (fVar2.e()) {
                lVar.a(fVar2.a);
            }
        }

        public void b(a.a.a.b.f fVar, f fVar2) throws j {
            l lVar = (l) fVar;
            fVar2.b = lVar.t();
            fVar2.b(true);
            fVar2.c = lVar.v();
            fVar2.c(true);
            if (lVar.b(1).get(0)) {
                fVar2.a = lVar.v();
                fVar2.a(true);
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
        enumMap.put(e.VALUE, new a.a.a.a.b("value", (byte) 2, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.TS, new a.a.a.a.b("ts", (byte) 1, new a.a.a.a.c((byte) 10)));
        enumMap.put(e.GUID, new a.a.a.a.b("guid", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        d = Collections.unmodifiableMap(enumMap);
        a.a.a.a.b.a(f.class, d);
    }

    public f() {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
    }

    public f(long j, String str) {
        this();
        this.b = j;
        b(true);
        this.c = str;
    }

    public f(f fVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
        this.l = fVar.l;
        if (fVar.e()) {
            this.a = fVar.a;
        }
        this.b = fVar.b;
        if (fVar.k()) {
            this.c = fVar.c;
        }
    }

    public f a() {
        return new f(this);
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

    public f a(String str) {
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

    public f a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void g() {
        this.l = a.a.a.a.b(this.l, 0);
    }

    public boolean h() {
        return a.a.a.a.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = a.a.a.a.a(this.l, 0, z);
    }

    public String i() {
        return this.c;
    }

    public f b(String str) {
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

    public void a(a.a.a.b.f fVar) throws j {
        ((a.a.a.c.b) j.get(fVar.y())).a().a(fVar, this);
    }

    public void b(a.a.a.b.f fVar) throws j {
        ((a.a.a.c.b) j.get(fVar.y())).a().b(fVar, this);
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

    public void l() throws j {
        if (this.c == null) {
            throw new i("Required field 'guid' was not present! Struct: " + toString());
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
