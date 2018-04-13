package cn.xiaochuankeji.tieba.common.c;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.cache.disk.b;
import com.facebook.drawee.a.a.c;
import com.facebook.imagepipeline.c.j;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.d.h$a;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import okhttp3.w;

public class a {
    private static PoolFactory a;

    public static b a(Context context) {
        if (TextUtils.isEmpty(cn.xiaochuankeji.tieba.background.a.e().a())) {
            return null;
        }
        return b.a(context).a(new File(cn.xiaochuankeji.tieba.background.a.e().a())).a("zuiyou_image").a(209715200).b(20971520).c(10485760).a();
    }

    public static String a() {
        return cn.xiaochuankeji.tieba.background.a.e().a() + "zuiyou_image";
    }

    public static void a(Context context, w wVar) {
        com.getkeepsafe.relinker.b.a(context, "imagepipeline");
        b a = a(context);
        h$a a2 = h.a(context).a(new c(wVar));
        if (a != null) {
            a2 = a2.a(a);
        }
        a = new PoolFactory(PoolConfig.newBuilder().build());
        a2.a(a).a(Config.RGB_565).a(true);
        c.a(context, a2.a());
        if (!c.d()) {
            c.a(context);
        }
    }

    @Nullable
    public static File a(ImageRequest imageRequest) {
        j a = j.a();
        if (a == null) {
            return null;
        }
        try {
            com.facebook.cache.common.b c = a.c(imageRequest, Boolean.valueOf(false));
            com.facebook.cache.disk.h h = com.facebook.imagepipeline.d.j.a().h();
            File o = imageRequest.o();
            if (!h.b(c) || h.a(c) == null) {
                return o;
            }
            return ((com.facebook.a.b) h.a(c)).c();
        } catch (Exception e) {
            com.izuiyou.a.a.b.d("get cache resource error", e);
            return null;
        }
    }

    public static PoolFactory b() {
        if (a == null) {
            a = new PoolFactory(PoolConfig.newBuilder().build());
        }
        return a;
    }
}
