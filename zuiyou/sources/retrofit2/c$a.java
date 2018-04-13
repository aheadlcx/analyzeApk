package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

public abstract class c$a {
    @Nullable
    public abstract c<?, ?> a(Type type, Annotation[] annotationArr, m mVar);

    protected static Type a(int i, ParameterizedType parameterizedType) {
        return o.a(i, parameterizedType);
    }

    protected static Class<?> a(Type type) {
        return o.a(type);
    }
}
