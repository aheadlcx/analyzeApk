package com.bumptech.glide.load.b;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class c {
    private static final l c = new l() {
        public com.bumptech.glide.load.a.c a(Object obj, int i, int i2) {
            throw new NoSuchMethodError("This should never be called!");
        }

        public String toString() {
            return "NULL_MODEL_LOADER";
        }
    };
    private final Map<Class, Map<Class, m>> a = new HashMap();
    private final Map<Class, Map<Class, l>> b = new HashMap();
    private final Context d;

    public c(Context context) {
        this.d = context.getApplicationContext();
    }

    public synchronized <T, Y> m<T, Y> a(Class<T> cls, Class<Y> cls2, m<T, Y> mVar) {
        m<T, Y> mVar2;
        this.b.clear();
        Map map = (Map) this.a.get(cls);
        if (map == null) {
            map = new HashMap();
            this.a.put(cls, map);
        }
        mVar2 = (m) map.put(cls2, mVar);
        if (mVar2 != null) {
            for (Map containsValue : this.a.values()) {
                if (containsValue.containsValue(mVar2)) {
                    mVar2 = null;
                    break;
                }
            }
        }
        return mVar2;
    }

    public synchronized <T, Y> l<T, Y> a(Class<T> cls, Class<Y> cls2) {
        l<T, Y> c;
        c = c(cls, cls2);
        if (c == null) {
            m d = d(cls, cls2);
            if (d != null) {
                l a = d.a(this.d, this);
                a((Class) cls, (Class) cls2, a);
            } else {
                b(cls, cls2);
            }
        } else if (c.equals(c)) {
            c = null;
        }
        return c;
    }

    private <T, Y> void b(Class<T> cls, Class<Y> cls2) {
        a((Class) cls, (Class) cls2, c);
    }

    private <T, Y> void a(Class<T> cls, Class<Y> cls2, l<T, Y> lVar) {
        Map map = (Map) this.b.get(cls);
        if (map == null) {
            map = new HashMap();
            this.b.put(cls, map);
        }
        map.put(cls2, lVar);
    }

    private <T, Y> l<T, Y> c(Class<T> cls, Class<Y> cls2) {
        Map map = (Map) this.b.get(cls);
        if (map != null) {
            return (l) map.get(cls2);
        }
        return null;
    }

    private <T, Y> m<T, Y> d(Class<T> cls, Class<Y> cls2) {
        m<T, Y> mVar;
        Map map = (Map) this.a.get(cls);
        if (map != null) {
            mVar = (m) map.get(cls2);
        } else {
            mVar = null;
        }
        if (mVar != null) {
            return mVar;
        }
        m<T, Y> mVar2 = mVar;
        for (Class cls3 : this.a.keySet()) {
            if (cls3.isAssignableFrom(cls)) {
                map = (Map) this.a.get(cls3);
                if (map != null) {
                    mVar = (m) map.get(cls2);
                    if (mVar != null) {
                        return mVar;
                    }
                    mVar2 = mVar;
                }
            }
            mVar = mVar2;
            mVar2 = mVar;
        }
        return mVar2;
    }
}
