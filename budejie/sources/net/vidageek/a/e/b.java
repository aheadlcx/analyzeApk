package net.vidageek.a.e;

import java.lang.reflect.Constructor;
import net.vidageek.a.e.a.a;
import net.vidageek.a.h.f;

public final class b<T> implements a<T> {
    private final Constructor<T> a;
    private final Class<T> b;
    private final f c;

    public b(f fVar, Class<T> cls, Constructor<T> constructor) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        } else if (constructor == null) {
            throw new IllegalArgumentException("constructor cannot be null");
        } else if (cls.equals(constructor.getDeclaringClass())) {
            this.c = fVar;
            this.b = cls;
            this.a = constructor;
        } else {
            throw new IllegalArgumentException("constructor declaring type should be " + cls.getName() + " but was " + constructor.getDeclaringClass().getName());
        }
    }

    public final T a() {
        return a(new Object[0]);
    }

    public final T a(Object... objArr) {
        net.vidageek.a.h.b a = this.c.a(this.b, this.a);
        a.a();
        return a.a(objArr);
    }
}
