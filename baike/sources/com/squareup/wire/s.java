package com.squareup.wire;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class s<E extends WireEnum> extends EnumAdapter<E> {
    private final Class<E> d;
    private Method e;

    s(Class<E> cls) {
        super(cls);
        this.d = cls;
    }

    private Method a() {
        Method method = this.e;
        if (method == null) {
            try {
                method = this.d.getMethod("fromValue", new Class[]{Integer.TYPE});
                this.e = method;
            } catch (NoSuchMethodException e) {
                throw new AssertionError(e);
            }
        }
        return method;
    }

    protected E b(int i) {
        Object e;
        try {
            return (WireEnum) a().invoke(null, new Object[]{Integer.valueOf(i)});
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new AssertionError(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof s) && ((s) obj).d == this.d;
    }

    public int hashCode() {
        return this.d.hashCode();
    }
}
