package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ab;
import okhttp3.z;

public interface e<F, T> {

    public static abstract class a {
        public e<ab, ?> a(Type type, Annotation[] annotationArr, m mVar) {
            return null;
        }

        public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
            return null;
        }

        public e<?, String> b(Type type, Annotation[] annotationArr, m mVar) {
            return null;
        }
    }

    T a(F f) throws IOException;
}
