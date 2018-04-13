package a.a.a.a;

import a.a.a.d;
import a.a.a.f;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class b implements Serializable {
    private static Map<Class<? extends d>, Map<? extends f, b>> d = new HashMap();
    public final String a;
    public final byte b;
    public final c c;

    public b(String str, byte b, c cVar) {
        this.a = str;
        this.b = b;
        this.c = cVar;
    }

    public static void a(Class<? extends d> cls, Map<? extends f, b> map) {
        d.put(cls, map);
    }

    public static Map<? extends f, b> a(Class<? extends d> cls) {
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
