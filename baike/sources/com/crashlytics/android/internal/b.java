package com.crashlytics.android.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class b {
    public static final String a = "default";
    private final ConcurrentMap<Class<?>, Set<cd>> b;
    private final ConcurrentMap<Class<?>, ce> c;
    private final String d;
    private final m e;
    private final cf f;
    private final ThreadLocal<ConcurrentLinkedQueue<cc>> g;
    private final ThreadLocal<Boolean> h;
    private final Map<Class<?>, Set<Class<?>>> i;

    public b() {
        this("default");
    }

    public b(String str) {
        this(m.b, str);
    }

    public b(m mVar) {
        this(mVar, "default");
    }

    public b(m mVar, String str) {
        this(mVar, str, cf.a);
    }

    private b(m mVar, String str, cf cfVar) {
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.g = new ca(this);
        this.h = new cb(this);
        this.i = new HashMap();
        this.e = mVar;
        this.d = str;
        this.f = cfVar;
    }

    public String toString() {
        return "[Bus \"" + this.d + "\"]";
    }

    public void a(Object obj) {
        this.e.a(this);
        Map a = this.f.a(obj);
        for (Class cls : a.keySet()) {
            ce ceVar = (ce) a.get(cls);
            ce ceVar2 = (ce) this.c.putIfAbsent(cls, ceVar);
            if (ceVar2 != null) {
                throw new IllegalArgumentException("Producer method for type " + cls + " found on type " + ceVar.a.getClass() + ", but already registered by type " + ceVar2.a.getClass() + ".");
            }
            Set<cd> set = (Set) this.b.get(cls);
            if (!(set == null || set.isEmpty())) {
                for (cd a2 : set) {
                    a(a2, ceVar);
                }
            }
        }
        a = this.f.b(obj);
        for (Class cls2 : a.keySet()) {
            Set set2 = (Set) this.b.get(cls2);
            if (set2 == null) {
                CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
                set2 = (Set) this.b.putIfAbsent(cls2, copyOnWriteArraySet);
                if (set2 == null) {
                    set2 = copyOnWriteArraySet;
                }
            }
            set2.addAll((Set) a.get(cls2));
        }
        for (Entry entry : a.entrySet()) {
            ceVar = (ce) this.c.get((Class) entry.getKey());
            if (ceVar != null && ceVar.a()) {
                for (cd a22 : (Set) entry.getValue()) {
                    if (!ceVar.a()) {
                        break;
                    } else if (a22.a()) {
                        a(a22, ceVar);
                    }
                }
            }
        }
    }

    private void a(cd cdVar, ce ceVar) {
        Object obj = null;
        try {
            obj = ceVar.c();
        } catch (InvocationTargetException e) {
            a("Producer " + ceVar + " threw an exception.", e);
        }
        if (obj != null) {
            a(obj, cdVar);
        }
    }

    public void b(Object obj) {
        this.e.a(this);
        for (Entry entry : this.f.a(obj).entrySet()) {
            Class cls = (Class) entry.getKey();
            ce ceVar = (ce) this.c.get(cls);
            ce ceVar2 = (ce) entry.getValue();
            if (ceVar2 == null || !ceVar2.equals(ceVar)) {
                throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + obj.getClass() + " registered?");
            }
            ((ce) this.c.remove(cls)).b();
        }
        for (Entry entry2 : this.f.b(obj).entrySet()) {
            Set<cd> a = a((Class) entry2.getKey());
            Collection collection = (Collection) entry2.getValue();
            if (a == null || !a.containsAll(collection)) {
                throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + obj.getClass() + " registered?");
            }
            for (cd cdVar : a) {
                if (collection.contains(cdVar)) {
                    cdVar.b();
                }
            }
            a.removeAll(collection);
        }
    }

    public void c(Object obj) {
        Class cls;
        this.e.a(this);
        Class cls2 = obj.getClass();
        Set set = (Set) this.i.get(cls2);
        if (set == null) {
            List linkedList = new LinkedList();
            Set hashSet = new HashSet();
            linkedList.add(cls2);
            while (!linkedList.isEmpty()) {
                cls = (Class) linkedList.remove(0);
                hashSet.add(cls);
                cls = cls.getSuperclass();
                if (cls != null) {
                    linkedList.add(cls);
                }
            }
            this.i.put(cls2, hashSet);
            set = hashSet;
        }
        int i = 0;
        for (Class cls3 : r0) {
            int i2;
            Set<cd> a = a(cls3);
            if (a == null || a.isEmpty()) {
                i2 = i;
            } else {
                for (cd ccVar : a) {
                    ((ConcurrentLinkedQueue) this.g.get()).offer(new cc(obj, ccVar));
                }
                i2 = 1;
            }
            i = i2;
        }
        if (i == 0 && !(obj instanceof f)) {
            c(new f(this, obj));
        }
        a();
    }

    private void a() {
        if (!((Boolean) this.h.get()).booleanValue()) {
            this.h.set(Boolean.valueOf(true));
            while (true) {
                try {
                    cc ccVar = (cc) ((ConcurrentLinkedQueue) this.g.get()).poll();
                    if (ccVar == null) {
                        break;
                    } else if (ccVar.b.a()) {
                        a(ccVar.a, ccVar.b);
                    }
                } finally {
                    this.h.set(Boolean.valueOf(false));
                }
            }
        }
    }

    private static void a(Object obj, cd cdVar) {
        try {
            cdVar.a(obj);
        } catch (InvocationTargetException e) {
            a("Could not dispatch event: " + obj.getClass() + " to handler " + cdVar, e);
        }
    }

    private Set<cd> a(Class<?> cls) {
        return (Set) this.b.get(cls);
    }

    private static void a(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            throw new RuntimeException(str, cause);
        }
        throw new RuntimeException(str);
    }
}
