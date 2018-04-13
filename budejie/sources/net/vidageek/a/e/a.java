package net.vidageek.a.e;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import net.vidageek.a.b.c;
import net.vidageek.a.h.f;

public final class a<T> implements net.vidageek.a.e.a.a<T> {
    private final Class<T> a;
    private final f b;

    public a(f fVar, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Argument class cannot be null");
        }
        this.b = fVar;
        this.a = cls;
    }

    private Constructor<T> b(Object... objArr) {
        int i = 0;
        int length = objArr == null ? 0 : objArr.length;
        Class[] clsArr = new Class[length];
        while (i < length) {
            if (objArr[i] == null) {
                throw new IllegalArgumentException("Cannot invoke a constructor by args if one of it's arguments is null. First reflect the constructor.");
            }
            clsArr[i] = objArr[i].getClass();
            i++;
        }
        Constructor<T> a = new c(this.b).a(this.a).b().a().a(clsArr);
        if (a != null) {
            return a;
        }
        throw new net.vidageek.a.c.a("Could not find constructor with args " + Arrays.asList(clsArr) + " on class " + this.a.getName());
    }

    public final T a() {
        return a(new Object[0]);
    }

    public final T a(Object... objArr) {
        return new b(this.b, this.a, b(objArr)).a(objArr);
    }
}
