package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;
import org.mozilla.classfile.ByteCode;

public class k implements Serializable, Cloneable, org.apache.thrift.a<k, a> {
    public static final Map<a, b> d;
    private static final j e = new j("DataCollectionItem");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b("collectedAt", (byte) 10, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("collectionType", (byte) 8, (short) 2);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("content", ByteCode.T_LONG, (short) 3);
    public long a;
    public d b;
    public String c;
    private BitSet i = new BitSet(1);

    public enum a {
        COLLECTED_AT((short) 1, "collectedAt"),
        COLLECTION_TYPE((short) 2, "collectionType"),
        CONTENT((short) 3, "content");
        
        private static final Map<String, a> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                d.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public String a() {
            return this.f;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.COLLECTED_AT, new b("collectedAt", (byte) 1, new c((byte) 10)));
        enumMap.put(a.COLLECTION_TYPE, new b("collectionType", (byte) 1, new org.apache.thrift.meta_data.a((byte) 16, d.class)));
        enumMap.put(a.CONTENT, new b("content", (byte) 1, new c(ByteCode.T_LONG)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(k.class, d);
    }

    public k a(long j) {
        this.a = j;
        a(true);
        return this;
    }

    public k a(d dVar) {
        this.b = dVar;
        return this;
    }

    public k a(String str) {
        this.c = str;
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (a()) {
                    e();
                    return;
                }
                throw new f("Required field 'collectedAt' was not found in serialized data! Struct: " + toString());
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.a = eVar.u();
                    a(true);
                    break;
                case (short) 2:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.b = d.a(eVar.t());
                        break;
                    }
                case (short) 3:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
                        break;
                    }
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.i.set(0, z);
    }

    public boolean a() {
        return this.i.get(0);
    }

    public boolean a(k kVar) {
        if (kVar == null || this.a != kVar.a) {
            return false;
        }
        boolean b = b();
        boolean b2 = kVar.b();
        if ((b || b2) && (!b || !b2 || !this.b.equals(kVar.b))) {
            return false;
        }
        b = d();
        b2 = kVar.d();
        return !(b || b2) || (b && b2 && this.c.equals(kVar.c));
    }

    public int b(k kVar) {
        if (!getClass().equals(kVar.getClass())) {
            return getClass().getName().compareTo(kVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(kVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, kVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(kVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, kVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(kVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, kVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        e();
        eVar.a(e);
        eVar.a(f);
        eVar.a(this.a);
        eVar.b();
        if (this.b != null) {
            eVar.a(g);
            eVar.a(this.b.a());
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(h);
            eVar.a(this.c);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public String c() {
        return this.c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((k) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public void e() {
        if (this.b == null) {
            throw new f("Required field 'collectionType' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new f("Required field 'content' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof k)) ? a((k) obj) : false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("DataCollectionItem(");
        stringBuilder.append("collectedAt:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("collectionType:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("content:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
