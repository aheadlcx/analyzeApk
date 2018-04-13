package net.vidageek.a.h.a;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.vidageek.a.h.b;
import net.vidageek.a.h.c;
import net.vidageek.a.h.d;
import net.vidageek.a.h.f;

public final class a implements f {
    public final <T> net.vidageek.a.h.a<T> a(Class<T> cls) {
        return new c((Class) cls);
    }

    public final net.vidageek.a.h.a<?> a(String str) {
        return new c(str);
    }

    public final <T> b<T> a(Class<T> cls, Constructor<T> constructor) {
        return new d(cls, constructor);
    }

    public final c a(Object obj, Class<?> cls, Field field) {
        return new e(obj, cls, field);
    }

    public final d a(Object obj, Class<?> cls, Method method) {
        return new f(obj, cls, method);
    }
}
