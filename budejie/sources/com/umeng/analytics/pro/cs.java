package com.umeng.analytics.pro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class cs implements Serializable {
    private static Map<Class<? extends cg>, Map<? extends cn, cs>> d = new HashMap();
    public final String a;
    public final byte b;
    public final ct c;

    public cs(String str, byte b, ct ctVar) {
        this.a = str;
        this.b = b;
        this.c = ctVar;
    }

    public static void a(Class<? extends cg> cls, Map<? extends cn, cs> map) {
        d.put(cls, map);
    }

    public static Map<? extends cn, cs> a(Class<? extends cg> cls) {
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
