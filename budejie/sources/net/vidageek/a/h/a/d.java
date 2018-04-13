package net.vidageek.a.h.a;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.vidageek.a.h.b;

public final class d<T> implements b<T> {
    private final Class<T> a;
    private final Constructor<T> b;

    public d(Class<T> cls, Constructor<T> constructor) {
        this.a = cls;
        this.b = constructor;
    }

    public final T a(Object... objArr) {
        Throwable e;
        try {
            a();
            return this.b.newInstance(objArr);
        } catch (Throwable e2) {
            throw new net.vidageek.a.c.b("could not invoke constructor " + this.b.toGenericString() + " on class " + this.a.getName(), e2);
        } catch (Throwable e22) {
            throw new net.vidageek.a.c.b("could not invoke constructor " + this.b.toGenericString() + " on class " + this.a.getName(), e22);
        } catch (Throwable e222) {
            throw new net.vidageek.a.c.b("could not invoke constructor " + this.b.toGenericString() + " on class " + this.a.getName(), e222);
        } catch (InvocationTargetException e3) {
            e222 = e3;
            String str = "could not invoke constructor " + this.b.toGenericString() + " on class " + this.a.getName();
            if (e222.getCause() != null) {
                e222 = e222.getCause();
            }
            throw new net.vidageek.a.c.b(str, e222);
        }
    }

    public final void a() {
        this.b.setAccessible(true);
    }
}
