package cn.tatagou.sdk.a;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.t;
import okhttp3.w;
import org.apache.http.entity.mime.MIME;
import retrofit2.m;

public final class d {
    public static final String a = cn.tatagou.sdk.util.d.b;
    private static final String b = d.class.getSimpleName();
    private static w d = new okhttp3.w.a().a(new t() {
        public aa intercept(okhttp3.t.a aVar) throws IOException {
            return aVar.a(aVar.a().e().a("User-Agent", "ttgsdka/2.4.4.6").a("Referer", "ttgsdka/2.4.4.6").b("CONTENT_TYPE", FastJsonJsonView.DEFAULT_CONTENT_TYPE).b(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8").b());
        }
    }).a();
    private final m c = new retrofit2.m.a().a(a).a(d).a();

    static class a {
        private static final d a = new d();
    }

    public static d getInstance() {
        return a.a;
    }

    public <T> T getService(Class<T> cls) {
        return this.c.a(cls);
    }
}
