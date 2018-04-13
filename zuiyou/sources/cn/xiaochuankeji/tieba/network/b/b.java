package cn.xiaochuankeji.tieba.network.b;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import okhttp3.u;
import okhttp3.z;
import retrofit2.e;

public class b<T> implements e<T, z> {
    private static final u c = u.a("text/plain; charset=utf-8");
    private Type a;
    private Charset b;

    public /* synthetic */ Object b(Object obj) throws IOException {
        return a(obj);
    }

    public b(Type type, Charset charset) {
        this.a = type;
        this.b = charset;
    }

    public z a(T t) throws IOException {
        return z.create(c, JSON.toJSONString(t).getBytes(this.b));
    }
}
