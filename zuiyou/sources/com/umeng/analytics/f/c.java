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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mozilla.classfile.ByteCode;

public class c implements a.a.a.d<c, e>, Serializable, Cloneable {
    public static final Map<e, a.a.a.a.b> d;
    private static final long e = -6496538196005191531L;
    private static final k f = new k("IdSnapshot");
    private static final a.a.a.b.c g = new a.a.a.b.c("identity", ByteCode.T_LONG, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("ts", (byte) 10, (short) 2);
    private static final a.a.a.b.c i = new a.a.a.b.c("version", (byte) 8, (short) 3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    private static class a extends a.a.a.c.c<c> {
        private a() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (c) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (c) dVar);
        }

        public void a(f fVar, c cVar) throws j {
            fVar.f();
            while (true) {
                a.a.a.b.c h = fVar.h();
                if (h.b == (byte) 0) {
                    fVar.g();
                    if (!cVar.h()) {
                        throw new i("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (cVar.k()) {
                        cVar.l();
                        return;
                    } else {
                        throw new i("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        cVar.a = fVar.v();
                        cVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 10) {
                            g.a(fVar, h.b);
                            break;
                        }
                        cVar.b = fVar.t();
                        cVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 8) {
                            g.a(fVar, h.b);
                            break;
                        }
                        cVar.c = fVar.s();
                        cVar.c(true);
                        break;
                    default:
                        g.a(fVar, h.b);
                        break;
                }
                fVar.i();
            }
        }

        public void b(f fVar, c cVar) throws j {
            cVar.l();
            fVar.a(c.f);
            if (cVar.a != null) {
                fVar.a(c.g);
                fVar.a(cVar.a);
                fVar.b();
            }
            fVar.a(c.h);
            fVar.a(cVar.b);
            fVar.b();
            fVar.a(c.i);
            fVar.a(cVar.c);
            fVar.b();
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

    private static class c extends a.a.a.c.d<c> {
        private c() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (c) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (c) dVar);
        }

        public void a(f fVar, c cVar) throws j {
            l lVar = (l) fVar;
            lVar.a(cVar.a);
            lVar.a(cVar.b);
            lVar.a(cVar.c);
        }

        public void b(f fVar, c cVar) throws j {
            l lVar = (l) fVar;
            cVar.a = lVar.v();
            cVar.a(true);
            cVar.b = lVar.t();
            cVar.b(true);
            cVar.c = lVar.s();
            cVar.c(true);
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
        IDENTITY((short) 1, "identity"),
        TS((short) 2, "ts"),
        VERSION((short) 3, "version");
        
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
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
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
        enumMap.put(e.IDENTITY, new a.a.a.a.b("identity", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.TS, new a.a.a.a.b("ts", (byte) 1, new a.a.a.a.c((byte) 10)));
        enumMap.put(e.VERSION, new a.a.a.a.b("version", (byte) 1, new a.a.a.a.c((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        a.a.a.a.b.a(c.class, d);
    }

    public c() {
        this.m = (byte) 0;
    }

    public c(String str, long j, int i) {
        this();
        this.a = str;
        this.b = j;
        b(true);
        this.c = i;
        c(true);
    }

    public c(c cVar) {
        this.m = (byte) 0;
        this.m = cVar.m;
        if (cVar.e()) {
            this.a = cVar.a;
        }
        this.b = cVar.b;
        this.c = cVar.c;
    }

    public c a() {
        return new c(this);
    }

    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        c(false);
        this.c = 0;
    }

    public String c() {
        return this.a;
    }

    public c a(String str) {
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

    public c a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void g() {
        this.m = a.a.a.a.b(this.m, 0);
    }

    public boolean h() {
        return a.a.a.a.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = a.a.a.a.a(this.m, 0, z);
    }

    public int i() {
        return this.c;
    }

    public c a(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public void j() {
        this.m = a.a.a.a.b(this.m, 1);
    }

    public boolean k() {
        return a.a.a.a.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = a.a.a.a.a(this.m, 1, z);
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
        StringBuilder stringBuilder = new StringBuilder("IdSnapshot(");
        stringBuilder.append("identity:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.c);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws j {
        if (this.a == null) {
            throw new i("Required field 'identity' was not present! Struct: " + toString());
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
            this.m = (byte) 0;
            a(new a.a.a.b.b(new a.a.a.d.a(objectInputStream)));
        } catch (j e) {
            throw new IOException(e.getMessage());
        }
    }
}
