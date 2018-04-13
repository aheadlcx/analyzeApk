package com.xiaomi.xmpush.thrift;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import cz.msebera.android.httpclient.HttpHeaders;
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

public class l implements Serializable, Cloneable, org.apache.thrift.a<l, a> {
    public static final Map<a, b> c;
    private static final j d = new j(HttpHeaders.LOCATION);
    private static final org.apache.thrift.protocol.b e = new org.apache.thrift.protocol.b(ParamKey.LONGITUDE, (byte) 4, (short) 1);
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b(ParamKey.LATITUDE, (byte) 4, (short) 2);
    public double a;
    public double b;
    private BitSet g = new BitSet(2);

    public enum a {
        LONGITUDE((short) 1, ParamKey.LONGITUDE),
        LATITUDE((short) 2, ParamKey.LATITUDE);
        
        private static final Map<String, a> c = null;
        private final short d;
        private final String e;

        static {
            c = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                c.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.d = s;
            this.e = str;
        }

        public String a() {
            return this.e;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.LONGITUDE, new b(ParamKey.LONGITUDE, (byte) 1, new c((byte) 4)));
        enumMap.put(a.LATITUDE, new b(ParamKey.LATITUDE, (byte) 1, new c((byte) 4)));
        c = Collections.unmodifiableMap(enumMap);
        b.a(l.class, c);
    }

    public double a() {
        return this.a;
    }

    public l a(double d) {
        this.a = d;
        a(true);
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (!b()) {
                    throw new f("Required field 'longitude' was not found in serialized data! Struct: " + toString());
                } else if (d()) {
                    e();
                    return;
                } else {
                    throw new f("Required field 'latitude' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 4) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.a = eVar.v();
                    a(true);
                    break;
                case (short) 2:
                    if (i.b != (byte) 4) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = eVar.v();
                    b(true);
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.g.set(0, z);
    }

    public boolean a(l lVar) {
        return lVar != null && this.a == lVar.a && this.b == lVar.b;
    }

    public int b(l lVar) {
        if (!getClass().equals(lVar.getClass())) {
            return getClass().getName().compareTo(lVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(lVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, lVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(lVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.b, lVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public l b(double d) {
        this.b = d;
        b(true);
        return this;
    }

    public void b(e eVar) {
        e();
        eVar.a(d);
        eVar.a(e);
        eVar.a(this.a);
        eVar.b();
        eVar.a(f);
        eVar.a(this.b);
        eVar.b();
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.g.set(1, z);
    }

    public boolean b() {
        return this.g.get(0);
    }

    public double c() {
        return this.b;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((l) obj);
    }

    public boolean d() {
        return this.g.get(1);
    }

    public void e() {
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof l)) ? a((l) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Location(");
        stringBuilder.append("longitude:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("latitude:");
        stringBuilder.append(this.b);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
