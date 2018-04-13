package cn.xiaochuankeji.tieba.background.upload;

import cn.xiaochuankeji.tieba.network.d;
import okhttp3.e$a;
import okhttp3.w;
import okhttp3.y;
import retrofit2.m;

public class e {
    private static volatile e a = null;
    private final m b;
    private final w c;
    private final d d;

    private class a implements e$a {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public okhttp3.e a(y yVar) {
            if (this.a.d != null) {
                y a = this.a.d.a(yVar);
                if (a != null) {
                    yVar = a;
                }
            }
            return this.a.c.a(yVar);
        }
    }

    private e(String str, w wVar, d dVar, cn.xiaochuankeji.tieba.network.a aVar) {
        this.c = wVar;
        this.b = new retrofit2.m.a().a(str).a(cn.xiaochuankeji.tieba.network.b.a.a()).a(cn.xiaochuankeji.tieba.network.a.a.a(null, aVar)).a(new a()).a();
        this.d = dVar;
    }

    public static void a(String str, w wVar, d dVar, cn.xiaochuankeji.tieba.network.a aVar) {
        a = new e(str, wVar, dVar, aVar);
    }

    public void a() {
        if (this.c != null) {
            this.c.s().b();
        }
    }

    public static e b() {
        return a;
    }

    public <T> T a(Class<T> cls) {
        return this.b.a((Class) cls);
    }
}
