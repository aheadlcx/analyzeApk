package cn.xiaochuankeji.tieba.background;

import android.content.Context;
import android.content.SharedPreferences;
import cn.htjyb.netlib.c;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.h.f;
import cn.xiaochuankeji.tieba.background.h.g;
import cn.xiaochuankeji.tieba.background.modules.a.d;
import cn.xiaochuankeji.tieba.background.topic.TopicAttentionList;
import cn.xiaochuankeji.tieba.background.utils.share.b;

public class a {
    public static b a;
    public static cn.xiaochuankeji.tieba.ui.auth.a.b b;
    private static SharedPreferences c;
    private static SharedPreferences d;
    private static cn.htjyb.netlib.b e;
    private static f f;
    private static cn.xiaochuankeji.tieba.background.picture.a g;
    private static d h;
    private static cn.xiaochuankeji.tieba.background.modules.a.b i;
    private static cn.xiaochuankeji.tieba.background.post.d j;
    private static cn.xiaochuankeji.tieba.background.review.b k;
    private static TopicAttentionList l;
    private static final cn.xiaochuankeji.tieba.push.service.b m = new cn.xiaochuankeji.tieba.push.service.b();
    private static com.facebook.imagepipeline.d.a n;
    private static cn.xiaochuankeji.tieba.ui.tag.a o;

    public static void a(Context context) {
    }

    public static SharedPreferences a() {
        if (c == null) {
            c = BaseApplication.getAppContext().getSharedPreferences("common", 0);
        }
        return c;
    }

    public static SharedPreferences b() {
        if (d == null) {
            d = BaseApplication.getAppContext().getSharedPreferences("push_permission", 0);
        }
        return d;
    }

    public static SharedPreferences c() {
        return BaseApplication.getAppContext().getSharedPreferences("spref" + g().c(), 0);
    }

    public static cn.htjyb.netlib.b d() {
        if (e == null) {
            e = c.b();
        }
        return e;
    }

    public static f e() {
        if (f == null) {
            f = new f();
        }
        return f;
    }

    public static cn.xiaochuankeji.tieba.background.picture.a f() {
        if (g == null) {
            g = new cn.xiaochuankeji.tieba.background.picture.a();
        }
        return g;
    }

    public static cn.xiaochuankeji.tieba.background.modules.a.a g() {
        if (i == null) {
            synchronized (cn.xiaochuankeji.tieba.background.modules.a.b.class) {
                if (i == null) {
                    i = new cn.xiaochuankeji.tieba.background.modules.a.b();
                }
            }
        }
        return i;
    }

    public static cn.xiaochuankeji.tieba.background.modules.a.c h() {
        if (h == null) {
            h = new d();
        }
        return h;
    }

    public static cn.xiaochuankeji.tieba.background.modules.a.b i() {
        return (cn.xiaochuankeji.tieba.background.modules.a.b) g();
    }

    public static cn.xiaochuankeji.tieba.background.post.d j() {
        if (j == null) {
            j = new cn.xiaochuankeji.tieba.background.post.d();
        }
        return j;
    }

    public static cn.xiaochuankeji.tieba.background.review.b k() {
        if (k == null) {
            k = new cn.xiaochuankeji.tieba.background.review.b();
        }
        return k;
    }

    public static b l() {
        if (a == null) {
            a = b.a();
        }
        return a;
    }

    public static cn.xiaochuankeji.tieba.ui.auth.a.b m() {
        if (b == null) {
            b = new cn.xiaochuankeji.tieba.ui.auth.a.b();
        }
        return b;
    }

    public static TopicAttentionList n() {
        if (l == null) {
            l = new TopicAttentionList();
        }
        return l;
    }

    public static g b(Context context) {
        return g.a(context);
    }

    public static cn.xiaochuankeji.tieba.push.service.b o() {
        return m;
    }

    public static com.facebook.imagepipeline.d.a p() {
        if (n == null) {
            n = new com.facebook.imagepipeline.d.a(Runtime.getRuntime().availableProcessors());
        }
        return n;
    }

    public static cn.xiaochuankeji.tieba.ui.tag.a q() {
        if (o == null) {
            o = new cn.xiaochuankeji.tieba.ui.tag.a();
        }
        return o;
    }

    public static void r() {
        o = null;
    }
}
