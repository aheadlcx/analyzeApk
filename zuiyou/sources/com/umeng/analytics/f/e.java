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
import java.util.Map.Entry;
import org.mozilla.classfile.ByteCode;

public class e implements a.a.a.d<e, e>, Serializable, Cloneable {
    public static final Map<e, a.a.a.a.b> d;
    private static final long e = 2846460275012375038L;
    private static final k f = new k("Imprint");
    private static final a.a.a.b.c g = new a.a.a.b.c("property", (byte) 13, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("version", (byte) 8, (short) 2);
    private static final a.a.a.b.c i = new a.a.a.b.c("checksum", ByteCode.T_LONG, (short) 3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    private static final int k = 0;
    public Map<String, f> a;
    public int b;
    public String c;
    private byte l;

    private static class a extends a.a.a.c.c<e> {
        private a() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (e) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (e) dVar);
        }

        public void a(f fVar, e eVar) throws j {
            fVar.f();
            while (true) {
                a.a.a.b.c h = fVar.h();
                if (h.b == (byte) 0) {
                    fVar.g();
                    if (eVar.i()) {
                        eVar.m();
                        return;
                    }
                    throw new i("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 13) {
                            g.a(fVar, h.b);
                            break;
                        }
                        a.a.a.b.e j = fVar.j();
                        eVar.a = new HashMap(j.c * 2);
                        for (int i = 0; i < j.c; i++) {
                            String v = fVar.v();
                            f fVar2 = new f();
                            fVar2.a(fVar);
                            eVar.a.put(v, fVar2);
                        }
                        fVar.k();
                        eVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 8) {
                            g.a(fVar, h.b);
                            break;
                        }
                        eVar.b = fVar.s();
                        eVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != ByteCode.T_LONG) {
                            g.a(fVar, h.b);
                            break;
                        }
                        eVar.c = fVar.v();
                        eVar.c(true);
                        break;
                    default:
                        g.a(fVar, h.b);
                        break;
                }
                fVar.i();
            }
        }

        public void b(f fVar, e eVar) throws j {
            eVar.m();
            fVar.a(e.f);
            if (eVar.a != null) {
                fVar.a(e.g);
                fVar.a(new a.a.a.b.e(ByteCode.T_LONG, (byte) 12, eVar.a.size()));
                for (Entry entry : eVar.a.entrySet()) {
                    fVar.a((String) entry.getKey());
                    ((f) entry.getValue()).b(fVar);
                }
                fVar.d();
                fVar.b();
            }
            fVar.a(e.h);
            fVar.a(eVar.b);
            fVar.b();
            if (eVar.c != null) {
                fVar.a(e.i);
                fVar.a(eVar.c);
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

    private static class c extends a.a.a.c.d<e> {
        private c() {
        }

        public /* synthetic */ void a(f fVar, a.a.a.d dVar) throws j {
            b(fVar, (e) dVar);
        }

        public /* synthetic */ void b(f fVar, a.a.a.d dVar) throws j {
            a(fVar, (e) dVar);
        }

        public void a(f fVar, e eVar) throws j {
            fVar = (l) fVar;
            fVar.a(eVar.a.size());
            for (Entry entry : eVar.a.entrySet()) {
                fVar.a((String) entry.getKey());
                ((f) entry.getValue()).b(fVar);
            }
            fVar.a(eVar.b);
            fVar.a(eVar.c);
        }

        public void b(f fVar, e eVar) throws j {
            fVar = (l) fVar;
            a.a.a.b.e eVar2 = new a.a.a.b.e(ByteCode.T_LONG, (byte) 12, fVar.s());
            eVar.a = new HashMap(eVar2.c * 2);
            for (int i = 0; i < eVar2.c; i++) {
                String v = fVar.v();
                f fVar2 = new f();
                fVar2.a(fVar);
                eVar.a.put(v, fVar2);
            }
            eVar.a(true);
            eVar.b = fVar.s();
            eVar.b(true);
            eVar.c = fVar.v();
            eVar.c(true);
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
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, "version"),
        CHECKSUM((short) 3, "checksum");
        
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
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
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
        enumMap.put(e.PROPERTY, new a.a.a.a.b("property", (byte) 1, new a.a.a.a.e((byte) 13, new a.a.a.a.c(ByteCode.T_LONG), new a.a.a.a.g((byte) 12, f.class))));
        enumMap.put(e.VERSION, new a.a.a.a.b("version", (byte) 1, new a.a.a.a.c((byte) 8)));
        enumMap.put(e.CHECKSUM, new a.a.a.a.b("checksum", (byte) 1, new a.a.a.a.c(ByteCode.T_LONG)));
        d = Collections.unmodifiableMap(enumMap);
        a.a.a.a.b.a(e.class, d);
    }

    public e() {
        this.l = (byte) 0;
    }

    public e(Map<String, f> map, int i, String str) {
        this();
        this.a = map;
        this.b = i;
        b(true);
        this.c = str;
    }

    public e(e eVar) {
        this.l = (byte) 0;
        this.l = eVar.l;
        if (eVar.f()) {
            Map hashMap = new HashMap();
            for (Entry entry : eVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new f((f) entry.getValue()));
            }
            this.a = hashMap;
        }
        this.b = eVar.b;
        if (eVar.l()) {
            this.c = eVar.c;
        }
    }

    public e a() {
        return new e(this);
    }

    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public int c() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(String str, f fVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, fVar);
    }

    public Map<String, f> d() {
        return this.a;
    }

    public e a(Map<String, f> map) {
        this.a = map;
        return this;
    }

    public void e() {
        this.a = null;
    }

    public boolean f() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int g() {
        return this.b;
    }

    public e a(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public void h() {
        this.l = a.a.a.a.b(this.l, 0);
    }

    public boolean i() {
        return a.a.a.a.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = a.a.a.a.a(this.l, 0, z);
    }

    public String j() {
        return this.c;
    }

    public e a(String str) {
        this.c = str;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
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
        StringBuilder stringBuilder = new StringBuilder("Imprint(");
        stringBuilder.append("property:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m() throws j {
        if (this.a == null) {
            throw new i("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
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
            this.l = (byte) 0;
            a(new a.a.a.b.b(new a.a.a.d.a(objectInputStream)));
        } catch (j e) {
            throw new IOException(e.getMessage());
        }
    }
}
