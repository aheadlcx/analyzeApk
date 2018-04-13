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

public class bl implements cg<bl, e>, Serializable, Cloneable {
    public static final Map<e, cs> d;
    private static final long e = -6496538196005191531L;
    private static final dk f = new dk("IdSnapshot");
    private static final da g = new da("identity", (byte) 11, (short) 1);
    private static final da h = new da("ts", (byte) 10, (short) 2);
    private static final da i = new da(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 8, (short) 3);
    private static final Map<Class<? extends dn>, do> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    private static class a extends dp<bl> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (bl) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (bl) cgVar);
        }

        public void a(df dfVar, bl blVar) throws cm {
            dfVar.j();
            while (true) {
                da l = dfVar.l();
                if (l.b == (byte) 0) {
                    dfVar.k();
                    if (!blVar.h()) {
                        throw new dg("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (blVar.k()) {
                        blVar.l();
                        return;
                    } else {
                        throw new dg("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        blVar.a = dfVar.z();
                        blVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 10) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        blVar.b = dfVar.x();
                        blVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 8) {
                            di.a(dfVar, l.b);
                            break;
                        }
                        blVar.c = dfVar.w();
                        blVar.c(true);
                        break;
                    default:
                        di.a(dfVar, l.b);
                        break;
                }
                dfVar.m();
            }
        }

        public void b(df dfVar, bl blVar) throws cm {
            blVar.l();
            dfVar.a(bl.f);
            if (blVar.a != null) {
                dfVar.a(bl.g);
                dfVar.a(blVar.a);
                dfVar.c();
            }
            dfVar.a(bl.h);
            dfVar.a(blVar.b);
            dfVar.c();
            dfVar.a(bl.i);
            dfVar.a(blVar.c);
            dfVar.c();
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

    private static class c extends dq<bl> {
        private c() {
        }

        public void a(df dfVar, bl blVar) throws cm {
            dl dlVar = (dl) dfVar;
            dlVar.a(blVar.a);
            dlVar.a(blVar.b);
            dlVar.a(blVar.c);
        }

        public void b(df dfVar, bl blVar) throws cm {
            dl dlVar = (dl) dfVar;
            blVar.a = dlVar.z();
            blVar.a(true);
            blVar.b = dlVar.x();
            blVar.b(true);
            blVar.c = dlVar.w();
            blVar.c(true);
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
        IDENTITY((short) 1, "identity"),
        TS((short) 2, "ts"),
        VERSION((short) 3, GameAppOperation.QQFAV_DATALINE_VERSION);
        
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
        enumMap.put(e.IDENTITY, new cs("identity", (byte) 1, new ct((byte) 11)));
        enumMap.put(e.TS, new cs("ts", (byte) 1, new ct((byte) 10)));
        enumMap.put(e.VERSION, new cs(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 1, new ct((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        cs.a(bl.class, d);
    }

    public bl() {
        this.m = (byte) 0;
    }

    public bl(String str, long j, int i) {
        this();
        this.a = str;
        this.b = j;
        b(true);
        this.c = i;
        c(true);
    }

    public bl(bl blVar) {
        this.m = (byte) 0;
        this.m = blVar.m;
        if (blVar.e()) {
            this.a = blVar.a;
        }
        this.b = blVar.b;
        this.c = blVar.c;
    }

    public bl a() {
        return new bl(this);
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

    public bl a(String str) {
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

    public bl a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void g() {
        this.m = cd.b(this.m, 0);
    }

    public boolean h() {
        return cd.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = cd.a(this.m, 0, z);
    }

    public int i() {
        return this.c;
    }

    public bl a(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public void j() {
        this.m = cd.b(this.m, 1);
    }

    public boolean k() {
        return cd.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = cd.a(this.m, 1, z);
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

    public void l() throws cm {
        if (this.a == null) {
            throw new dg("Required field 'identity' was not present! Struct: " + toString());
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
            this.m = (byte) 0;
            a(new cz(new dr((InputStream) objectInputStream)));
        } catch (cm e) {
            throw new IOException(e.getMessage());
        }
    }
}
