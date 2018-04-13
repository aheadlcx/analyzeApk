package com.umeng.analytics.f;

import a.a.a.b.f;
import a.a.a.b.g;
import a.a.a.b.i;
import a.a.a.b.k;
import a.a.a.b.l;
import a.a.a.j;
import com.iflytek.cloud.SpeechConstant;
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

public class b implements a.a.a.d<b, e>, Serializable, Cloneable {
    public static final Map<e, a.a.a.a.b> e;
    private static final long f = 9132678615281394583L;
    private static final k g = new k("IdJournal");
    private static final a.a.a.b.c h = new a.a.a.b.c(SpeechConstant.DOMAIN, ByteCode.T_LONG, (short) 1);
    private static final a.a.a.b.c i = new a.a.a.b.c("old_id", ByteCode.T_LONG, (short) 2);
    private static final a.a.a.b.c j = new a.a.a.b.c("new_id", ByteCode.T_LONG, (short) 3);
    private static final a.a.a.b.c k = new a.a.a.b.c("ts", (byte) 10, (short) 4);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> l = new HashMap();
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    private static class a extends a.a.a.c.c<b> {
        private a() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (b) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (b) dVar);
        }

        public void a(f fVar, b bVar) throws j {
            fVar.f();
            while (true) {
                a.a.a.b.c h = fVar.h();
                if (h.b == (byte) 0) {
                    fVar.g();
                    if (bVar.n()) {
                        bVar.o();
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
                        bVar.a = fVar.v();
                        bVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        bVar.b = fVar.v();
                        bVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        bVar.c = fVar.v();
                        bVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 10) {
                            g.a(fVar, h.b);
                            break;
                        }
                        bVar.d = fVar.t();
                        bVar.d(true);
                        break;
                    default:
                        g.a(fVar, h.b);
                        break;
                }
                fVar.i();
            }
        }

        public void b(f fVar, b bVar) throws j {
            bVar.o();
            fVar.a(b.g);
            if (bVar.a != null) {
                fVar.a(b.h);
                fVar.a(bVar.a);
                fVar.b();
            }
            if (bVar.b != null && bVar.h()) {
                fVar.a(b.i);
                fVar.a(bVar.b);
                fVar.b();
            }
            if (bVar.c != null) {
                fVar.a(b.j);
                fVar.a(bVar.c);
                fVar.b();
            }
            fVar.a(b.k);
            fVar.a(bVar.d);
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

    private static class c extends a.a.a.c.d<b> {
        private c() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (b) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (b) dVar);
        }

        public void a(f fVar, b bVar) throws j {
            l lVar = (l) fVar;
            lVar.a(bVar.a);
            lVar.a(bVar.c);
            lVar.a(bVar.d);
            BitSet bitSet = new BitSet();
            if (bVar.h()) {
                bitSet.set(0);
            }
            lVar.a(bitSet, 1);
            if (bVar.h()) {
                lVar.a(bVar.b);
            }
        }

        public void b(f fVar, b bVar) throws j {
            l lVar = (l) fVar;
            bVar.a = lVar.v();
            bVar.a(true);
            bVar.c = lVar.v();
            bVar.c(true);
            bVar.d = lVar.t();
            bVar.d(true);
            if (lVar.b(1).get(0)) {
                bVar.b = lVar.v();
                bVar.b(true);
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
        DOMAIN((short) 1, SpeechConstant.DOMAIN),
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

    public /* synthetic */ a.a.a.f b(int i) {
        return a(i);
    }

    public /* synthetic */ a.a.a.d p() {
        return a();
    }

    static {
        l.put(a.a.a.c.c.class, new b());
        l.put(a.a.a.c.d.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new a.a.a.a.b(SpeechConstant.DOMAIN, (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.OLD_ID, new a.a.a.a.b("old_id", (byte) 2, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.NEW_ID, new a.a.a.a.b("new_id", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        enumMap.put(e.TS, new a.a.a.a.b("ts", (byte) 1, new a.a.a.a.c((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        a.a.a.a.b.a(b.class, e);
    }

    public b() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    public b(String str, String str2, long j) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j;
        d(true);
    }

    public b(b bVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = bVar.n;
        if (bVar.e()) {
            this.a = bVar.a;
        }
        if (bVar.h()) {
            this.b = bVar.b;
        }
        if (bVar.k()) {
            this.c = bVar.c;
        }
        this.d = bVar.d;
    }

    public b a() {
        return new b(this);
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

    public b a(String str) {
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

    public b b(String str) {
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

    public b c(String str) {
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

    public b a(long j) {
        this.d = j;
        d(true);
        return this;
    }

    public void m() {
        this.n = a.a.a.a.b(this.n, 0);
    }

    public boolean n() {
        return a.a.a.a.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = a.a.a.a.a(this.n, 0, z);
    }

    public e a(int i) {
        return e.a(i);
    }

    public void a(f fVar) throws j {
        ((a.a.a.c.b) l.get(fVar.y())).a().a(fVar, this);
    }

    public void b(f fVar) throws j {
        ((a.a.a.c.b) l.get(fVar.y())).a().b(fVar, this);
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

    public void o() throws j {
        if (this.a == null) {
            throw new i("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new i("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = (byte) 0;
            a(new a.a.a.b.b(new a.a.a.d.a(objectInputStream)));
        } catch (j e) {
            throw new IOException(e.getMessage());
        }
    }
}
