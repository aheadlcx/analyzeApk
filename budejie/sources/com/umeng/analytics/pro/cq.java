package com.umeng.analytics.pro;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class cq<T extends cq<?, ?>, F extends cn> implements cg<T, F> {
    private static final Map<Class<? extends dn>, do> c = new HashMap();
    protected Object a;
    protected F b;

    private static class a extends dp<cq> {
        private a() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (cq) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (cq) cgVar);
        }

        public void a(df dfVar, cq cqVar) throws cm {
            cqVar.b = null;
            cqVar.a = null;
            dfVar.j();
            da l = dfVar.l();
            cqVar.a = cqVar.a(dfVar, l);
            if (cqVar.a != null) {
                cqVar.b = cqVar.a(l.c);
            }
            dfVar.m();
            dfVar.l();
            dfVar.k();
        }

        public void b(df dfVar, cq cqVar) throws cm {
            if (cqVar.a() == null || cqVar.c() == null) {
                throw new dg("Cannot write a TUnion with no set value!");
            }
            dfVar.a(cqVar.e());
            dfVar.a(cqVar.c(cqVar.b));
            cqVar.c(dfVar);
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

    private static class c extends dq<cq> {
        private c() {
        }

        public /* synthetic */ void a(df dfVar, cg cgVar) throws cm {
            b(dfVar, (cq) cgVar);
        }

        public /* synthetic */ void b(df dfVar, cg cgVar) throws cm {
            a(dfVar, (cq) cgVar);
        }

        public void a(df dfVar, cq cqVar) throws cm {
            cqVar.b = null;
            cqVar.a = null;
            short v = dfVar.v();
            cqVar.a = cqVar.a(dfVar, v);
            if (cqVar.a != null) {
                cqVar.b = cqVar.a(v);
            }
        }

        public void b(df dfVar, cq cqVar) throws cm {
            if (cqVar.a() == null || cqVar.c() == null) {
                throw new dg("Cannot write a TUnion with no set value!");
            }
            dfVar.a(cqVar.b.a());
            cqVar.d(dfVar);
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

    protected abstract F a(short s);

    protected abstract Object a(df dfVar, da daVar) throws cm;

    protected abstract Object a(df dfVar, short s) throws cm;

    protected abstract void b(F f, Object obj) throws ClassCastException;

    protected abstract da c(F f);

    protected abstract void c(df dfVar) throws cm;

    protected abstract void d(df dfVar) throws cm;

    protected abstract dk e();

    protected cq() {
        this.b = null;
        this.a = null;
    }

    static {
        c.put(dp.class, new b());
        c.put(dq.class, new d());
    }

    protected cq(F f, Object obj) {
        a((cn) f, obj);
    }

    protected cq(cq<T, F> cqVar) {
        if (cqVar.getClass().equals(getClass())) {
            this.b = cqVar.b;
            this.a = a(cqVar.a);
            return;
        }
        throw new ClassCastException();
    }

    private static Object a(Object obj) {
        if (obj instanceof cg) {
            return ((cg) obj).p();
        }
        if (obj instanceof ByteBuffer) {
            return ch.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        if (obj instanceof Map) {
            return a((Map) obj);
        }
        return obj;
    }

    private static Map a(Map<Object, Object> map) {
        Map hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put(a(entry.getKey()), a(entry.getValue()));
        }
        return hashMap;
    }

    private static Set a(Set set) {
        Set hashSet = new HashSet();
        for (Object a : set) {
            hashSet.add(a(a));
        }
        return hashSet;
    }

    private static List a(List list) {
        List arrayList = new ArrayList(list.size());
        for (Object a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object c() {
        return this.a;
    }

    public Object a(F f) {
        if (f == this.b) {
            return c();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
    }

    public Object a(int i) {
        return a(a((short) i));
    }

    public boolean d() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean c(int i) {
        return b(a((short) i));
    }

    public void a(df dfVar) throws cm {
        ((do) c.get(dfVar.D())).b().b(dfVar, this);
    }

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) {
        a(a((short) i), obj);
    }

    public void b(df dfVar) throws cm {
        ((do) c.get(dfVar.D())).b().a(dfVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" ");
        if (a() != null) {
            Object c = c();
            stringBuilder.append(c(a()).a);
            stringBuilder.append(":");
            if (c instanceof ByteBuffer) {
                ch.a((ByteBuffer) c, stringBuilder);
            } else {
                stringBuilder.append(c.toString());
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    public final void b() {
        this.b = null;
        this.a = null;
    }
}
