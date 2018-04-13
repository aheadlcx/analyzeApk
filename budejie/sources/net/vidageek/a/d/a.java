package net.vidageek.a.d;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.vidageek.a.b.c;
import net.vidageek.a.h.f;

public final class a implements net.vidageek.a.d.a.a {
    private final Object a;
    private final Class<?> b;
    private final f c;

    public a(f fVar, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("target cannot be null");
        }
        this.c = fVar;
        this.b = obj.getClass();
        this.a = obj;
    }

    public final Object a(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("fieldName cannot be null or empty.");
        }
        Field a = new c(this.c).a(this.b).b().a(str);
        if (a != null) {
            return a(a);
        }
        throw new net.vidageek.a.c.a("could not find field " + str + " for class " + this.b.getName());
    }

    public final Object a(Field field) {
        if (field == null) {
            throw new IllegalArgumentException("field cannot be null");
        } else if (!field.getDeclaringClass().isAssignableFrom(this.b)) {
            throw new IllegalArgumentException("field declaring class (" + field.getDeclaringClass().getName() + ") doesn't match clazz " + this.b.getName());
        } else if (this.a != null || Modifier.isStatic(field.getModifiers())) {
            net.vidageek.a.h.c a = this.c.a(this.a, this.b, field);
            a.a();
            return a.b();
        } else {
            throw new IllegalStateException("attempt to get instance field " + field.getName() + " on class " + this.b.getName());
        }
    }
}
