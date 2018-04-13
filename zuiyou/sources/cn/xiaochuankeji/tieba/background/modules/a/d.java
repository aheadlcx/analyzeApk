package cn.xiaochuankeji.tieba.background.modules.a;

import android.os.Handler;
import cn.htjyb.netlib.h;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.modules.a.g.a;
import java.util.HashSet;
import java.util.Iterator;

public class d implements c {
    private f a;
    private l b;
    private g c;
    private j d;
    private h e;
    private e f;
    private int g;
    private final HashSet<a> h = new HashSet();

    public void a(String str, String str2, String str3, int i, f.a aVar) {
        if (this.a != null) {
            this.a.a.c();
        }
        this.a = new f(str, str2, str3, i, aVar);
        this.a.a();
    }

    public void a(String str, j.a aVar) {
        this.d = new j(str, aVar);
        this.d.a();
    }

    public void a(String str, String str2, String str3, int i, e.a aVar) {
        if (this.f != null) {
            this.f.b();
        }
        this.f = new e(str, str2, str3, i, aVar);
        this.f.a();
    }

    public void a(int i, String str, long j, h.a aVar) {
        if (this.e != null) {
            this.e.b();
        }
        this.e = new h(i, str, j, aVar);
        this.e.a();
    }

    public void a(k kVar, l.a aVar) {
        if (this.b != null) {
            this.b.a.c();
        }
        this.b = new l(kVar, aVar);
        this.b.a();
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.h.add(aVar);
        }
        this.g = 0;
        a();
    }

    private void a() {
        if (this.c == null) {
            this.c = new g(new a(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void a(boolean z, String str) {
                    this.a.c = null;
                    Iterator it = this.a.h.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).a(z, str);
                    }
                    this.a.h.clear();
                    if (z) {
                        cn.xiaochuankeji.tieba.background.utils.c.a.c().d();
                    } else {
                        this.a.b();
                    }
                }
            });
            this.c.a();
            this.g++;
        }
    }

    private void b() {
        if (0 != cn.xiaochuankeji.tieba.background.a.g().c() || this.g >= 3) {
            return;
        }
        if (h.a(BaseApplication.getAppContext())) {
            a();
        } else {
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b();
                }
            }, 600000);
        }
    }
}
