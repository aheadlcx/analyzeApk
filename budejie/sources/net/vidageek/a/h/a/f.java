package net.vidageek.a.h.a;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.a.c.b;
import net.vidageek.a.h.d;

public final class f implements d {
    private final Object a;
    private final Method b;

    public f(Object obj, Class<?> cls, Method method) {
        this.a = obj;
        this.b = method;
    }

    public final Object a(Object[] objArr) {
        Throwable e;
        try {
            a();
            return this.b.invoke(this.a, objArr);
        } catch (Throwable e2) {
            throw new b("Could not invoke method " + this.b.getName(), e2);
        } catch (Throwable e22) {
            throw new b("Could not invoke method " + this.b.getName(), e22);
        } catch (InvocationTargetException e3) {
            e22 = e3;
            String str = "Could not invoke method " + this.b.getName();
            if (e22.getCause() != null) {
                e22 = e22.getCause();
            }
            throw new b(str, e22);
        } catch (Throwable e222) {
            throw new b("Attempt to call an instance method ( " + this.b.getName() + ") on a null object.", e222);
        }
    }

    public final void a() {
        this.b.setAccessible(true);
    }
}
