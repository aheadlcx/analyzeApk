package com.umeng.commonsdk.proguard;

import com.baidu.mobstat.Config;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public abstract class v<T extends v<?, ?>, F extends s> implements l<T, F> {
    private static final Map<Class<? extends as>, at> c = new HashMap();
    protected Object a = null;
    protected F b = null;

    private static class a extends au<v> {
        private a() {
        }

        public /* synthetic */ void a(ak akVar, l lVar) throws r {
            b(akVar, (v) lVar);
        }

        public /* synthetic */ void b(ak akVar, l lVar) throws r {
            a(akVar, (v) lVar);
        }

        public void a(ak akVar, v vVar) throws r {
            vVar.b = null;
            vVar.a = null;
            akVar.j();
            af l = akVar.l();
            vVar.a = vVar.a(akVar, l);
            if (vVar.a != null) {
                vVar.b = vVar.a(l.c);
            }
            akVar.m();
            akVar.l();
            akVar.k();
        }

        public void b(ak akVar, v vVar) throws r {
            if (vVar.a() == null || vVar.b() == null) {
                throw new al("Cannot write a TUnion with no set value!");
            }
            akVar.a(vVar.d());
            akVar.a(vVar.c(vVar.b));
            vVar.a(akVar);
            akVar.c();
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

    private static class c extends av<v> {
        private c() {
        }

        public /* synthetic */ void a(ak akVar, l lVar) throws r {
            b(akVar, (v) lVar);
        }

        public /* synthetic */ void b(ak akVar, l lVar) throws r {
            a(akVar, (v) lVar);
        }

        public void a(ak akVar, v vVar) throws r {
            vVar.b = null;
            vVar.a = null;
            short v = akVar.v();
            vVar.a = vVar.a(akVar, v);
            if (vVar.a != null) {
                vVar.b = vVar.a(v);
            }
        }

        public void b(ak akVar, v vVar) throws r {
            if (vVar.a() == null || vVar.b() == null) {
                throw new al("Cannot write a TUnion with no set value!");
            }
            akVar.a(vVar.b.a());
            vVar.b(akVar);
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

    protected abstract F a(short s);

    protected abstract Object a(ak akVar, af afVar) throws r;

    protected abstract Object a(ak akVar, short s) throws r;

    protected abstract void a(ak akVar) throws r;

    protected abstract void b(ak akVar) throws r;

    protected abstract void b(F f, Object obj) throws ClassCastException;

    protected abstract af c(F f);

    protected abstract ap d();

    protected v() {
    }

    static {
        c.put(au.class, new b());
        c.put(av.class, new d());
    }

    public F a() {
        return this.b;
    }

    public Object b() {
        return this.a;
    }

    public Object a(F f) {
        if (f == this.b) {
            return b();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
    }

    public Object a(int i) {
        return a(a((short) i));
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean b(int i) {
        return b(a((short) i));
    }

    public void read(ak akVar) throws r {
        ((at) c.get(akVar.D())).b().b(akVar, this);
    }

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) {
        a(a((short) i), obj);
    }

    public void write(ak akVar) throws r {
        ((at) c.get(akVar.D())).b().a(akVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (a() != null) {
            Object b = b();
            stringBuilder.append(c(a()).a);
            stringBuilder.append(Config.TRACE_TODAY_VISIT_SPLIT);
            if (b instanceof ByteBuffer) {
                m.a((ByteBuffer) b, stringBuilder);
            } else {
                stringBuilder.append(b.toString());
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    public final void clear() {
        this.b = null;
        this.a = null;
    }
}
