package com.facebook.common.references;

import com.facebook.common.c.a;
import com.facebook.common.internal.g;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class SharedReference<T> {
    @GuardedBy
    private static final Map<Object, Integer> a = new IdentityHashMap();
    @GuardedBy
    private T b;
    @GuardedBy
    private int c = 1;
    private final c<T> d;

    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    public SharedReference(T t, c<T> cVar) {
        this.b = g.a((Object) t);
        this.d = (c) g.a((Object) cVar);
        a((Object) t);
    }

    private static void a(Object obj) {
        synchronized (a) {
            Integer num = (Integer) a.get(obj);
            if (num == null) {
                a.put(obj, Integer.valueOf(1));
            } else {
                a.put(obj, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    private static void b(Object obj) {
        synchronized (a) {
            Integer num = (Integer) a.get(obj);
            if (num == null) {
                a.c("SharedReference", "No entry in sLiveObjects for value of type %s", obj.getClass());
            } else if (num.intValue() == 1) {
                a.remove(obj);
            } else {
                a.put(obj, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    public synchronized T a() {
        return this.b;
    }

    public synchronized boolean b() {
        return this.c > 0;
    }

    public static boolean a(SharedReference<?> sharedReference) {
        return sharedReference != null && sharedReference.b();
    }

    public synchronized void c() {
        f();
        this.c++;
    }

    public void d() {
        if (e() == 0) {
            Object obj;
            synchronized (this) {
                obj = this.b;
                this.b = null;
            }
            this.d.release(obj);
            b(obj);
        }
    }

    private synchronized int e() {
        f();
        g.a(this.c > 0);
        this.c--;
        return this.c;
    }

    private void f() {
        if (!a(this)) {
            throw new NullReferenceException();
        }
    }
}
