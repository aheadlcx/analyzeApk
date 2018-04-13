package org.greenrobot.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

public class c {
    public static String a = "EventBus";
    static volatile c b;
    private static final d c = new d();
    private static final Map<Class<?>, List<Class<?>>> d = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<p>> e;
    private final Map<Object, List<Class<?>>> f;
    private final Map<Class<?>, Object> g;
    private final ThreadLocal<a> h;
    private final g i;
    private final k j;
    private final b k;
    private final a l;
    private final o m;
    private final ExecutorService n;
    private final boolean o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final boolean s;
    private final boolean t;
    private final int u;
    private final f v;

    /* renamed from: org.greenrobot.eventbus.c$2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ThreadMode.values().length];

        static {
            try {
                a[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    static final class a {
        final List<Object> a = new ArrayList();
        boolean b;
        boolean c;
        p d;
        Object e;
        boolean f;

        a() {
        }
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public c() {
        this(c);
    }

    c(d dVar) {
        this.h = new ThreadLocal<a>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object initialValue() {
                return a();
            }

            protected a a() {
                return new a();
            }
        };
        this.v = dVar.a();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new ConcurrentHashMap();
        this.i = dVar.b();
        this.j = this.i != null ? this.i.a(this) : null;
        this.k = new b(this);
        this.l = new a(this);
        this.u = dVar.j != null ? dVar.j.size() : 0;
        this.m = new o(dVar.j, dVar.h, dVar.g);
        this.p = dVar.a;
        this.q = dVar.b;
        this.r = dVar.c;
        this.s = dVar.d;
        this.o = dVar.e;
        this.t = dVar.f;
        this.n = dVar.i;
    }

    public void a(Object obj) {
        List<n> a = this.m.a(obj.getClass());
        synchronized (this) {
            for (n a2 : a) {
                a(obj, a2);
            }
        }
    }

    private void a(Object obj, n nVar) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Class cls = nVar.c;
        p pVar = new p(obj, nVar);
        CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.e.get(cls);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.e.put(cls, copyOnWriteArrayList2);
            copyOnWriteArrayList = copyOnWriteArrayList2;
        } else if (copyOnWriteArrayList2.contains(pVar)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        } else {
            copyOnWriteArrayList = copyOnWriteArrayList2;
        }
        int size = copyOnWriteArrayList.size();
        int i = 0;
        while (i <= size) {
            if (i == size || nVar.d > ((p) copyOnWriteArrayList.get(i)).b.d) {
                copyOnWriteArrayList.add(i, pVar);
                break;
            }
            i++;
        }
        List list = (List) this.f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f.put(obj, list);
        }
        list.add(cls);
        if (!nVar.e) {
            return;
        }
        if (this.t) {
            for (Entry entry : this.g.entrySet()) {
                if (cls.isAssignableFrom((Class) entry.getKey())) {
                    b(pVar, entry.getValue());
                }
            }
            return;
        }
        b(pVar, this.g.get(cls));
    }

    private void b(p pVar, Object obj) {
        if (obj != null) {
            a(pVar, obj, d());
        }
    }

    private boolean d() {
        return this.i != null ? this.i.a() : true;
    }

    public synchronized boolean b(Object obj) {
        return this.f.containsKey(obj);
    }

    private void a(Object obj, Class<?> cls) {
        List list = (List) this.e.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2;
                p pVar = (p) list.get(i);
                if (pVar.a == obj) {
                    pVar.c = false;
                    list.remove(i);
                    i2 = i - 1;
                    i = size - 1;
                } else {
                    i2 = i;
                    i = size;
                }
                size = i;
                i = i2 + 1;
            }
        }
    }

    public synchronized void c(Object obj) {
        List<Class> list = (List) this.f.get(obj);
        if (list != null) {
            for (Class a : list) {
                a(obj, a);
            }
            this.f.remove(obj);
        } else {
            this.v.a(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void d(Object obj) {
        a aVar = (a) this.h.get();
        List list = aVar.a;
        list.add(obj);
        if (!aVar.b) {
            aVar.c = d();
            aVar.b = true;
            if (aVar.f) {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    a(list.remove(0), aVar);
                } finally {
                    aVar.b = false;
                    aVar.c = false;
                }
            }
        }
    }

    private void a(Object obj, a aVar) throws Error {
        boolean z;
        Class cls = obj.getClass();
        if (this.t) {
            List a = a(cls);
            boolean z2 = false;
            for (int i = 0; i < a.size(); i++) {
                z2 |= a(obj, aVar, (Class) a.get(i));
            }
            z = z2;
        } else {
            z = a(obj, aVar, cls);
        }
        if (!z) {
            if (this.q) {
                this.v.a(Level.FINE, "No subscribers registered for event " + cls);
            }
            if (this.s && cls != h.class && cls != m.class) {
                d(new h(this, obj));
            }
        }
    }

    private boolean a(Object obj, a aVar, Class<?> cls) {
        synchronized (this) {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.e.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        loop0:
        while (it.hasNext()) {
            p pVar = (p) it.next();
            aVar.e = obj;
            aVar.d = pVar;
            try {
                a(pVar, obj, aVar.c);
                Object obj2 = aVar.f;
                continue;
            } finally {
                aVar.e = null;
                aVar.d = null;
                aVar.f = false;
            }
            if (obj2 != null) {
                break loop0;
            }
        }
        return true;
    }

    private void a(p pVar, Object obj, boolean z) {
        switch (AnonymousClass2.a[pVar.b.b.ordinal()]) {
            case 1:
                a(pVar, obj);
                return;
            case 2:
                if (z) {
                    a(pVar, obj);
                    return;
                } else {
                    this.j.a(pVar, obj);
                    return;
                }
            case 3:
                if (this.j != null) {
                    this.j.a(pVar, obj);
                    return;
                } else {
                    a(pVar, obj);
                    return;
                }
            case 4:
                if (z) {
                    this.k.a(pVar, obj);
                    return;
                } else {
                    a(pVar, obj);
                    return;
                }
            case 5:
                this.l.a(pVar, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + pVar.b.b);
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (d) {
            list = (List) d.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a((List) list, cls2.getInterfaces());
                }
                d.put(cls, list);
            }
        }
        return list;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    void a(i iVar) {
        Object obj = iVar.a;
        p pVar = iVar.b;
        i.a(iVar);
        if (pVar.c) {
            a(pVar, obj);
        }
    }

    void a(p pVar, Object obj) {
        try {
            pVar.b.a.invoke(pVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            a(pVar, obj, e.getCause());
        } catch (Throwable e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void a(p pVar, Object obj, Throwable th) {
        if (obj instanceof m) {
            if (this.p) {
                this.v.a(Level.SEVERE, "SubscriberExceptionEvent subscriber " + pVar.a.getClass() + " threw an exception", th);
                m mVar = (m) obj;
                this.v.a(Level.SEVERE, "Initial event " + mVar.c + " caused exception in " + mVar.d, mVar.b);
            }
        } else if (this.o) {
            throw new EventBusException("Invoking subscriber failed", th);
        } else {
            if (this.p) {
                this.v.a(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + pVar.a.getClass(), th);
            }
            if (this.r) {
                d(new m(this, th, obj, pVar.a));
            }
        }
    }

    ExecutorService b() {
        return this.n;
    }

    public f c() {
        return this.v;
    }

    public String toString() {
        return "EventBus[indexCount=" + this.u + ", eventInheritance=" + this.t + "]";
    }
}
