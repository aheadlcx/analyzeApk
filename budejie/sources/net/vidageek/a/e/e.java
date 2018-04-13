package net.vidageek.a.e;

import java.lang.reflect.Method;
import net.vidageek.a.c.a;
import net.vidageek.a.e.a.c;
import net.vidageek.a.h.f;

public final class e implements c {
    private final Object a;
    private final String b;
    private final Class<?> c;
    private final f d;

    public e(f fVar, Object obj, Class<?> cls, String str) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz can't be null");
        } else if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("methodName can't be null");
        } else {
            this.d = fVar;
            this.a = obj;
            this.c = cls;
            this.b = str;
        }
    }

    private Method b(Object[] objArr) {
        int i = 0;
        int length = objArr == null ? 0 : objArr.length;
        Class[] clsArr = new Class[length];
        while (i < length) {
            if (objArr[i] == null) {
                throw new IllegalArgumentException("Cannot invoke a method by name if one of it's arguments is null. First reflect the method.");
            }
            clsArr[i] = objArr[i].getClass();
            i++;
        }
        Method a = new net.vidageek.a.b.c(this.d).a(this.c).b().b(this.b).a(clsArr);
        if (a != null) {
            return a;
        }
        throw new a("Could not find method " + this.b + " on class " + this.c.getName());
    }

    public final Object a() {
        return a(new Object[0]);
    }

    public final Object a(Object... objArr) {
        return new d(this.d, this.a, this.c, b(objArr)).a(objArr);
    }
}
