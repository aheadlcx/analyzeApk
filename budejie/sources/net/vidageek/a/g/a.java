package net.vidageek.a.g;

import java.util.HashMap;
import java.util.Map;

public final class a {
    private static Map<Class<?>, Class<?>> b;
    private final Class<?>[] a;

    static {
        Map hashMap = new HashMap();
        b = hashMap;
        hashMap.put(Boolean.TYPE, Boolean.class);
        b.put(Byte.TYPE, Byte.class);
        b.put(Character.TYPE, Character.class);
        b.put(Short.TYPE, Short.class);
        b.put(Integer.TYPE, Integer.class);
        b.put(Long.TYPE, Long.class);
        b.put(Float.TYPE, Float.class);
        b.put(Double.TYPE, Double.class);
    }

    public a(Class<?>... clsArr) {
        if (clsArr == null) {
            throw new IllegalArgumentException("argument baseClasses cannot be null.");
        }
        this.a = clsArr;
    }

    private boolean b(Class<?>[] clsArr) {
        for (int i = 0; i < this.a.length; i++) {
            Class cls = clsArr[i];
            Class cls2 = this.a[i];
            boolean isAssignableFrom = (cls.isPrimitive() ^ cls2.isPrimitive()) == 0 ? cls.isAssignableFrom(cls2) : cls.isPrimitive() ? ((Class) b.get(cls)).isAssignableFrom(cls2) : ((Class) b.get(cls2)).isAssignableFrom(cls);
            if (!isAssignableFrom) {
                return false;
            }
        }
        return true;
    }

    private boolean c(Class<?>[] clsArr) {
        for (int i = 0; i < this.a.length; i++) {
            Class cls = this.a[i];
            Class cls2 = clsArr[i];
            boolean equals = (cls.isPrimitive() ^ cls2.isPrimitive()) == 0 ? cls.equals(cls2) : cls.isPrimitive() ? ((Class) b.get(cls)).equals(cls2) : ((Class) b.get(cls2)).equals(cls);
            if (!equals) {
                return false;
            }
        }
        return true;
    }

    public final b a(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.a.length != clsArr.length ? b.DONT_MATCH : c(clsArr) ? b.PERFECT : b(clsArr) ? b.MATCH : b.DONT_MATCH;
        } else {
            throw new IllegalArgumentException("argument classes cannot be null.");
        }
    }
}
