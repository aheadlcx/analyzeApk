package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ak;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.av;
import com.umeng.commonsdk.proguard.i;
import com.umeng.commonsdk.proguard.l;
import com.umeng.commonsdk.proguard.r;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.proguard.x;
import com.umeng.commonsdk.proguard.y;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class e implements l<e, e>, Serializable, Cloneable {
    public static final Map<e, x> d;
    private static final ap e = new ap("ImprintValue");
    private static final af f = new af("value", (byte) 11, (short) 1);
    private static final af g = new af("ts", (byte) 10, (short) 2);
    private static final af h = new af("guid", (byte) 11, (short) 3);
    private static final Map<Class<? extends as>, at> i = new HashMap();
    public String a;
    public long b;
    public String c;
    private byte j;
    private e[] k;

    private static class a extends au<e> {
        private a() {
        }

        public /* synthetic */ void a(ak akVar, l lVar) throws r {
            b(akVar, (e) lVar);
        }

        public /* synthetic */ void b(ak akVar, l lVar) throws r {
            a(akVar, (e) lVar);
        }

        public void a(ak akVar, e eVar) throws r {
            akVar.j();
            while (true) {
                af l = akVar.l();
                if (l.b == (byte) 0) {
                    akVar.k();
                    if (eVar.g()) {
                        eVar.k();
                        return;
                    }
                    throw new al("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        eVar.a = akVar.z();
                        eVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 10) {
                            an.a(akVar, l.b);
                            break;
                        }
                        eVar.b = akVar.x();
                        eVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            an.a(akVar, l.b);
                            break;
                        }
                        eVar.c = akVar.z();
                        eVar.c(true);
                        break;
                    default:
                        an.a(akVar, l.b);
                        break;
                }
                akVar.m();
            }
        }

        public void b(ak akVar, e eVar) throws r {
            eVar.k();
            akVar.a(e.e);
            if (eVar.a != null && eVar.d()) {
                akVar.a(e.f);
                akVar.a(eVar.a);
                akVar.c();
            }
            akVar.a(e.g);
            akVar.a(eVar.b);
            akVar.c();
            if (eVar.c != null) {
                akVar.a(e.h);
                akVar.a(eVar.c);
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

    private static class c extends av<e> {
        private c() {
        }

        public void a(ak akVar, e eVar) throws r {
            aq aqVar = (aq) akVar;
            aqVar.a(eVar.b);
            aqVar.a(eVar.c);
            BitSet bitSet = new BitSet();
            if (eVar.d()) {
                bitSet.set(0);
            }
            aqVar.a(bitSet, 1);
            if (eVar.d()) {
                aqVar.a(eVar.a);
            }
        }

        public void b(ak akVar, e eVar) throws r {
            aq aqVar = (aq) akVar;
            eVar.b = aqVar.x();
            eVar.b(true);
            eVar.c = aqVar.z();
            eVar.c(true);
            if (aqVar.b(1).get(0)) {
                eVar.a = aqVar.z();
                eVar.a(true);
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

    public /* synthetic */ l deepCopy() {
        return a();
    }

    public /* synthetic */ s fieldForId(int i) {
        return a(i);
    }

    static {
        i.put(au.class, new b());
        i.put(av.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.VALUE, new x("value", (byte) 2, new y((byte) 11)));
        enumMap.put(e.TS, new x("ts", (byte) 1, new y((byte) 10)));
        enumMap.put(e.GUID, new x("guid", (byte) 1, new y((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        x.a(e.class, d);
    }

    public e() {
        this.j = (byte) 0;
        this.k = new e[]{e.VALUE};
    }

    public e(long j, String str) {
        this();
        this.b = j;
        b(true);
        this.c = str;
    }

    public e(e eVar) {
        this.j = (byte) 0;
        this.k = new e[]{e.VALUE};
        this.j = eVar.j;
        if (eVar.d()) {
            this.a = eVar.a;
        }
        this.b = eVar.b;
        if (eVar.j()) {
            this.c = eVar.c;
        }
    }

    public e a() {
        return new e(this);
    }

    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public String b() {
        return this.a;
    }

    public e a(String str) {
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

    public long e() {
        return this.b;
    }

    public e a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void f() {
        this.j = i.b(this.j, 0);
    }

    public boolean g() {
        return i.a(this.j, 0);
    }

    public void b(boolean z) {
        this.j = i.a(this.j, 0, z);
    }

    public String h() {
        return this.c;
    }

    public e b(String str) {
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

    public e a(int i) {
        return e.a(i);
    }

    public void read(ak akVar) throws r {
        ((at) i.get(akVar.D())).b().b(akVar, this);
    }

    public void write(ak akVar) throws r {
        ((at) i.get(akVar.D())).b().a(akVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ImprintValue(");
        Object obj = 1;
        if (d()) {
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

    public void k() throws r {
        if (this.c == null) {
            throw new al("Required field 'guid' was not present! Struct: " + toString());
        }
    }
}
