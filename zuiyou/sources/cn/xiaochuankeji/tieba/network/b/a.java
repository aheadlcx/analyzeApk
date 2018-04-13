package cn.xiaochuankeji.tieba.network.b;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import okhttp3.ab;
import okhttp3.z;
import retrofit2.e;
import retrofit2.e$a;
import retrofit2.m;

public class a extends e$a {
    private static final Charset b = Charset.forName("UTF-8");
    private Charset a;

    public static a a() {
        return a(b);
    }

    public static a a(Charset charset) {
        return new a(charset);
    }

    public a(Charset charset) {
        this.a = charset;
    }

    public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        return new b(type, this.a);
    }

    public e<ab, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        return new c(type, this.a);
    }
}
