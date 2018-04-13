package net.vidageek.a.e;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.vidageek.a.e.a.c;
import net.vidageek.a.h.f;

public final class d implements c {
    private final Object a;
    private final Class<?> b;
    private final Method c;
    private final f d;

    public d(f fVar, Object obj, Class<?> cls, Method method) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        } else if (method == null) {
            throw new IllegalArgumentException("method cannot be null");
        } else if (method.getDeclaringClass().isAssignableFrom(cls)) {
            this.d = fVar;
            this.a = obj;
            this.b = cls;
            this.c = method;
        } else {
            throw new IllegalArgumentException("method " + method + " cannot be invoked on clazz " + cls.getName());
        }
    }

    public final Object a() {
        return a(new Object[0]);
    }

    public final Object a(Object... objArr) {
        if (this.a != null || Modifier.isStatic(this.c.getModifiers())) {
            net.vidageek.a.h.d a = this.d.a(this.a, this.b, this.c);
            a.a();
            return a.a(objArr);
        }
        throw new IllegalStateException("attempt to call instance method " + this.c.getName() + " on class " + this.b.getName());
    }
}
