package net.vidageek.a.i;

import java.lang.reflect.Field;
import net.vidageek.a.h.f;

public final class c {
    private final String a;
    private final f b;

    public c(f fVar, String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("fieldName cannot be null or blank");
        }
        this.b = fVar;
        this.a = str;
    }

    public final Field a(Class cls) {
        if (cls != null) {
            return this.b.a(cls).a(this.a);
        }
        throw new IllegalArgumentException("argument clazz cannot be null.");
    }
}
