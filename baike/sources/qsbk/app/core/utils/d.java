package qsbk.app.core.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class d implements ExclusionStrategy {
    d() {
    }

    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return false;
    }

    public boolean shouldSkipClass(Class<?> cls) {
        return cls == Field.class || cls == Method.class;
    }
}
