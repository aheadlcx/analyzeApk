package cn.xiaochuankeji.tieba.network.b;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import okhttp3.ab;
import retrofit2.e;

public class c<T> implements e<ab, T> {
    private Type a;
    private Charset b;

    public /* synthetic */ Object b(Object obj) throws IOException {
        return a((ab) obj);
    }

    public c(Type type, Charset charset) {
        this.a = type;
        this.b = charset;
    }

    public T a(ab abVar) throws IOException {
        try {
            T parseObject = JSON.parseObject(abVar.string(), this.a, new Feature[0]);
            return parseObject;
        } finally {
            abVar.close();
        }
    }
}
