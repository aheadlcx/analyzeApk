package retrofit2.a.a;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ab;
import okhttp3.z;
import retrofit2.e;
import retrofit2.m;

public final class a extends retrofit2.e.a {
    private final Gson a;

    public static a a() {
        return a(new Gson());
    }

    public static a a(Gson gson) {
        return new a(gson);
    }

    private a(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.a = gson;
    }

    public e<ab, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        return new c(this.a, this.a.getAdapter(TypeToken.get(type)));
    }

    public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        return new b(this.a, this.a.getAdapter(TypeToken.get(type)));
    }
}
