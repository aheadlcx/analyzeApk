package cn.xiaochuankeji.tieba.network;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import okhttp3.e;
import okhttp3.e$a;
import okhttp3.w;
import okhttp3.y;
import retrofit2.b;
import retrofit2.m;
import rx.d;

public class c {
    private static volatile c a = null;
    private final m b;
    private final w c;
    private final d d;

    private class a implements e$a {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public e a(y yVar) {
            if (this.a.d != null) {
                y a = this.a.d.a(yVar);
                if (a != null) {
                    yVar = a;
                }
            }
            return this.a.c.a(yVar);
        }
    }

    private c(String str, w wVar, d dVar, a aVar) {
        this.c = wVar;
        this.b = new retrofit2.m.a().a(str).a(cn.xiaochuankeji.tieba.network.b.a.a()).a(cn.xiaochuankeji.tieba.network.a.a.a(rx.f.a.c(), aVar)).a(new a()).a();
        this.d = dVar;
    }

    public static void a(String str, w wVar, d dVar, a aVar) {
        a = new c(str, wVar, dVar, aVar);
    }

    public static c a() {
        return a;
    }

    public <T> T a(Class<T> cls) {
        return this.b.a((Class) cls);
    }

    public static <I> I b(final Class<I> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                Object a = c.a().a(cls);
                if (a != null) {
                    try {
                        if (method.getReturnType() == d.class) {
                            d dVar = (d) method.invoke(a, objArr);
                            if (dVar != null) {
                                return dVar.b(rx.f.a.c());
                            }
                        } else if (method.getReturnType() == b.class) {
                            return method.invoke(a, objArr);
                        }
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }

    public <T> T c(Class<T> cls) {
        return new retrofit2.m.a().a("http://diagnosis.izuiyou.com").a(cn.xiaochuankeji.tieba.network.b.a.a()).a(cn.xiaochuankeji.tieba.network.a.a.a(rx.f.a.c(), new cn.xiaochuankeji.tieba.network.custom.c())).a(new a()).a().a((Class) cls);
    }
}
