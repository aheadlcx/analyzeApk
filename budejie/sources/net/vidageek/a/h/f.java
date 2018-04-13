package net.vidageek.a.h;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface f {
    <T> a<T> a(Class<T> cls);

    a<? extends Object> a(String str);

    <T> b<T> a(Class<T> cls, Constructor<T> constructor);

    c a(Object obj, Class<?> cls, Field field);

    d a(Object obj, Class<?> cls, Method method);
}
