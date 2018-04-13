package net.vidageek.a.h.a;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.vidageek.a.c.b;
import net.vidageek.a.h.a;

public final class c<T> implements a<T> {
    private Class<T> a;

    public c(Class<T> cls) {
        this.a = cls;
    }

    public c(String str) {
        try {
            this.a = Class.forName(str, false, c.class.getClassLoader());
        } catch (Throwable e) {
            this.a = b.a(str);
            if (this.a == null) {
                throw new b("class " + str + " could not be found.", e);
            }
        }
    }

    public final Class<T> a() {
        return this.a;
    }

    public final Constructor<T> a(Class<?>[] clsArr) {
        net.vidageek.a.g.a aVar = new net.vidageek.a.g.a(clsArr);
        Constructor<T> constructor = null;
        for (Constructor<T> constructor2 : d()) {
            Constructor<T> constructor22;
            net.vidageek.a.g.b a = aVar.a(constructor22.getParameterTypes());
            if (net.vidageek.a.g.b.PERFECT.equals(a)) {
                return constructor22;
            }
            if (!net.vidageek.a.g.b.MATCH.equals(a)) {
                constructor22 = constructor;
            }
            constructor = constructor22;
        }
        return constructor;
    }

    public final Field a(String str) {
        for (Field field : b()) {
            if (field.getName().equals(str)) {
                return field;
            }
        }
        return null;
    }

    public final Method a(String str, Class<?>[] clsArr) {
        net.vidageek.a.g.a aVar = new net.vidageek.a.g.a(clsArr);
        Method method = null;
        for (Method method2 : c()) {
            if (method2.getName().equals(str)) {
                net.vidageek.a.g.b a = aVar.a(method2.getParameterTypes());
                if (net.vidageek.a.g.b.PERFECT.equals(a)) {
                    return method2;
                }
                if (net.vidageek.a.g.b.MATCH.equals(a)) {
                    method = method2;
                }
            }
            Method method22 = method;
            method = method22;
        }
        return method;
    }

    public final List<Field> b() {
        Class cls = this.a;
        List<Field> arrayList = new ArrayList();
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            arrayList.addAll(Arrays.asList(cls2.getDeclaredFields()));
            for (Class fields : cls2.getInterfaces()) {
                arrayList.addAll(Arrays.asList(fields.getFields()));
            }
        }
        return arrayList;
    }

    public final List<Method> c() {
        List<Method> arrayList = new ArrayList();
        for (Class cls = this.a; cls != null; cls = cls.getSuperclass()) {
            arrayList.addAll(Arrays.asList(cls.getDeclaredMethods()));
        }
        return arrayList;
    }

    public final List<Constructor<T>> d() {
        return Arrays.asList(this.a.getDeclaredConstructors());
    }
}
