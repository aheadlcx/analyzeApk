package cn.xiaochuankeji.tieba.network.a;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.adapter.rxjava.g;
import retrofit2.c;
import retrofit2.c$a;
import retrofit2.m;

public class a extends c$a {
    private final g a;
    private final cn.xiaochuankeji.tieba.network.a b;

    public static a a(rx.g gVar, cn.xiaochuankeji.tieba.network.a aVar) {
        return new a(gVar, aVar);
    }

    private a(rx.g gVar, cn.xiaochuankeji.tieba.network.a aVar) {
        if (gVar == null) {
            this.a = g.a();
        } else {
            this.a = g.a(gVar);
        }
        this.b = aVar;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        return new b(this.a.a(type, annotationArr, mVar), mVar.c(), this.b);
    }
}
