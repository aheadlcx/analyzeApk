package com.umeng.analytics.pro;

import com.tencent.open.GameAppOperation;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class bn implements cg<bn, e>, Serializable, Cloneable {
    public static final Map<e, cs> d;
    private static final long e = 2846460275012375038L;
    private static final dk f = new dk("Imprint");
    private static final da g = new da("property", dm.k, (short) 1);
    private static final da h = new da(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 8, (short) 2);
    private static final da i = new da("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends dn>, do> j = new HashMap();
    private static final int k = 0;
    public Map<String, bo> a;
    public int b;
    public String c;
    private byte l;

    private static class a extends dp<bn> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (bn) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (bn) cgVar);
        }

        public void a(df dfVar, bn bnVar) throws cm {
            dfVar.j();
            while (true) {
                da l = dfVar.l();
                if (l.b == (byte) 0) {
                    dfVar.k();
                    if (bnVar.i()) {
                        bnVar.m();
                        return;
                    }
                    throw new dg("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != dm.k) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        dc n = dfVar.n();
                        bnVar.a = new HashMap(n.c * 2);
                        for (int i = 0; i < n.c; i++) {
                            String z = dfVar.z();
                            bo boVar = new bo();
                            boVar.a(dfVar);
                            bnVar.a.put(z, boVar);
                        }
                        dfVar.o();
                        bnVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 8) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bnVar.b = dfVar.w();
                        bnVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        bnVar.c = dfVar.z();
                        bnVar.c(true);
                        break;
                    default:
                        di.a(dfVar, l.b);
                        break;
                }
                dfVar.m();
            }
        }

        public void b(df dfVar, bn bnVar) throws cm {
            bnVar.m();
            dfVar.a(bn.f);
            if (bnVar.a != null) {
                dfVar.a(bn.g);
                dfVar.a(new dc((byte) 11, (byte) 12, bnVar.a.size()));
                for (Entry entry : bnVar.a.entrySet()) {
                    dfVar.a((String) entry.getKey());
                    ((bo) entry.getValue()).b(dfVar);
                }
                dfVar.e();
                dfVar.c();
            }
            dfVar.a(bn.h);
            dfVar.a(bnVar.b);
            dfVar.c();
            if (bnVar.c != null) {
                dfVar.a(bn.i);
                dfVar.a(bnVar.c);
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

    private static class c extends dq<bn> {
        private c() {
        }

        public void a(df dfVar, bn bnVar) throws cm {
            dfVar = (dl) dfVar;
            dfVar.a(bnVar.a.size());
            for (Entry entry : bnVar.a.entrySet()) {
                dfVar.a((String) entry.getKey());
                ((bo) entry.getValue()).b(dfVar);
            }
            dfVar.a(bnVar.b);
            dfVar.a(bnVar.c);
        }

        public void b(df dfVar, bn bnVar) throws cm {
            dfVar = (dl) dfVar;
            dc dcVar = new dc((byte) 11, (byte) 12, dfVar.w());
            bnVar.a = new HashMap(dcVar.c * 2);
            for (int i = 0; i < dcVar.c; i++) {
                String z = dfVar.z();
                bo boVar = new bo();
                boVar.a(dfVar);
                bnVar.a.put(z, boVar);
            }
            bnVar.a(true);
            bnVar.b = dfVar.w();
            bnVar.b(true);
            bnVar.c = dfVar.z();
            bnVar.c(true);
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
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, GameAppOperation.QQFAV_DATALINE_VERSION),
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

    public /* synthetic */ cn b(int i) {
        return c(i);
    }

    public /* synthetic */ cg p() {
        return a();
    }

    static {
        j.put(dp.class, new b());
        j.put(dq.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.PROPERTY, new cs("property", (byte) 1, new cv(dm.k, new ct((byte) 11), new cx((byte) 12, bo.class))));
        enumMap.put(e.VERSION, new cs(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 1, new ct((byte) 8)));
        enumMap.put(e.CHECKSUM, new cs("checksum", (byte) 1, new ct((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        cs.a(bn.class, d);
    }

    public bn() {
        this.l = (byte) 0;
    }

    public bn(Map<String, bo> map, int i, String str) {
        this();
        this.a = map;
        this.b = i;
        b(true);
        this.c = str;
    }

    public bn(bn bnVar) {
        this.l = (byte) 0;
        this.l = bnVar.l;
        if (bnVar.f()) {
            Map hashMap = new HashMap();
            for (Entry entry : bnVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new bo((bo) entry.getValue()));
            }
            this.a = hashMap;
        }
        this.b = bnVar.b;
        if (bnVar.l()) {
            this.c = bnVar.c;
        }
    }

    public bn a() {
        return new bn(this);
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

    public void a(String str, bo boVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, boVar);
    }

    public Map<String, bo> d() {
        return this.a;
    }

    public bn a(Map<String, bo> map) {
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

    public bn a(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public void h() {
        this.l = cd.b(this.l, 0);
    }

    public boolean i() {
        return cd.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = cd.a(this.l, 0, z);
    }

    public String j() {
        return this.c;
    }

    public bn a(String str) {
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

    public void a(df dfVar) throws cm {
        ((do) j.get(dfVar.D())).b().b(dfVar, this);
    }

    public void b(df dfVar) throws cm {
        ((do) j.get(dfVar.D())).b().a(dfVar, this);
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

    public void m() throws cm {
        if (this.a == null) {
            throw new dg("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
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
            this.l = (byte) 0;
            a(new cz(new dr((InputStream) objectInputStream)));
        } catch (cm e) {
            throw new IOException(e.getMessage());
        }
    }
}
