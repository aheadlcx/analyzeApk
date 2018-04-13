package com.umeng.commonsdk.proguard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class x implements Serializable {
    private static Map<Class<? extends l>, Map<? extends s, x>> d = new HashMap();
    public final String a;
    public final byte b;
    public final y c;

    public x(String str, byte b, y yVar) {
        this.a = str;
        this.b = b;
        this.c = yVar;
    }

    public static void a(Class<? extends l> cls, Map<? extends s, x> map) {
        d.put(cls, map);
    }

    public static Map<? extends s, x> a(Class<? extends l> cls) {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return (Map) d.get(cls);
    }
}
