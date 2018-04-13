package net.vidageek.a.i;

import java.lang.reflect.Field;
import net.vidageek.a.h.f;
import net.vidageek.a.i.a.b;
import net.vidageek.a.i.a.c;
import net.vidageek.a.i.a.d;

public final class e<T> implements d<T> {
    private final Class<T> a;
    private final f b;

    public e(f fVar, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.b = fVar;
        this.a = cls;
    }

    public final Field a(String str) {
        if (str != null && str.trim().length() != 0) {
            return new c(this.b, str).a(this.a);
        }
        throw new IllegalArgumentException("fieldName cannot be null or empty.");
    }

    public final b<T> a() {
        return new b(this.b, this.a);
    }

    public final c b(String str) {
        if (str != null && str.trim().length() != 0) {
            return new d(this.b, str, this.a);
        }
        throw new IllegalArgumentException("methodName cannot be null or empty.");
    }
}
