package cn.xiaochuankeji.tieba.network.a;

import cn.xiaochuankeji.tieba.network.a;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import retrofit2.c;

public class b<R> implements c<R, Object> {
    private final c<?, ?> a;
    private final Executor b;
    private final a c;

    public b(c<?, ?> cVar, Executor executor, a aVar) {
        this.a = cVar;
        this.b = executor;
        this.c = aVar;
    }

    public Type a() {
        return this.a.a();
    }

    public Object a(retrofit2.b<R> bVar) {
        return this.a.a(new c(bVar, this.b, this.c));
    }
}
