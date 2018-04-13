package net.vidageek.a.i;

import java.lang.reflect.Method;
import net.vidageek.a.h.f;
import net.vidageek.a.i.a.c;

public final class d implements c {
    private final String a;
    private final Class<?> b;
    private final f c;

    public d(f fVar, String str, Class<?> cls) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("methodName cannot be null or empty");
        } else if (cls == null) {
            throw new IllegalArgumentException("clazz cannnot be null");
        } else {
            this.c = fVar;
            this.a = str.trim();
            this.b = cls;
        }
    }

    public final Method a() {
        return a(new Class[0]);
    }

    public final Method a(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.c.a(this.b).a(this.a, clsArr);
        }
        throw new IllegalArgumentException("classes cannot be null");
    }
}
