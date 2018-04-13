package net.vidageek.a.h.a;

import java.lang.reflect.Field;
import net.vidageek.a.c.b;
import net.vidageek.a.h.c;

public final class e implements c {
    private final Object a;
    private final Class<?> b;
    private final Field c;

    public e(Object obj, Class<?> cls, Field field) {
        this.a = obj;
        this.b = cls;
        this.c = field;
    }

    public final void a() {
        this.c.setAccessible(true);
    }

    public final Object b() {
        try {
            a();
            return this.c.get(this.a);
        } catch (IllegalAccessException e) {
            throw new b("could not get value for field " + this.c.getName() + " of class " + this.b.getName());
        }
    }
}
