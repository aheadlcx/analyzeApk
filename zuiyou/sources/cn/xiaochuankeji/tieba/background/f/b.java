package cn.xiaochuankeji.tieba.background.f;

import android.content.res.Resources;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.util.DisplayMetrics;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.a;

public class b {
    private static a f(long j) {
        return new a(j);
    }

    public static a a() {
        return f(0);
    }

    public static a a(long j, long j2) {
        if (j2 < 10) {
            a f = f(-1);
            f.a(g(j));
            return f;
        }
        f = f(j2);
        if (f.a()) {
            f.a(cn.xiaochuankeji.tieba.background.utils.d.a.a("/account/avatar/id/", j2, null));
            return f;
        }
        f.a(g(j));
        return f;
    }

    @DrawableRes
    private static int g(long j) {
        long j2 = j % 5;
        if (j2 == 0) {
            return R.drawable.img_default_avatar_1;
        }
        if (j2 == 1) {
            return R.drawable.img_default_avatar_2;
        }
        if (j2 == 2) {
            return R.drawable.img_default_avatar_3;
        }
        if (j2 == 3) {
            return R.drawable.img_default_avatar_4;
        }
        return R.drawable.img_default_avatar_5;
    }

    public static a a(long j) {
        a f = f(j);
        f.a(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, "/sz/src"));
        return f;
    }

    public static a a(long j, boolean z) {
        String a;
        a f = f(j);
        if (z) {
            a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, "/sz/480x270");
        } else {
            a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, "/sz/228");
        }
        f.a(a);
        return f;
    }

    public static a b(long j, boolean z) {
        String str;
        a f = f(j);
        if (cn.xiaochuankeji.tieba.d.a.e() == cn.xiaochuankeji.tieba.d.a.i) {
            str = "/img/webp/id/";
        } else {
            str = "/img/view/id/";
        }
        if (z) {
            str = cn.xiaochuankeji.tieba.background.utils.d.a.a(str, j, "/sz/480x270");
        } else {
            str = cn.xiaochuankeji.tieba.background.utils.d.a.a(str, j, "/sz/228");
        }
        f.a(str);
        return f;
    }

    public static a b(long j) {
        a f = f(j);
        f.a(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, "/sz/origin"));
        return f;
    }

    public static a c(long j, boolean z) {
        String a;
        a f = f(j);
        if (z) {
            a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/topic/cover/id/", j, "/sz/280");
        } else {
            a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/topic/cover/id/", j, null);
        }
        f.a(a);
        f.a((int) R.drawable.default_topic_cover);
        return f;
    }

    public static a c(long j) {
        a f = f(j);
        f.a(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, "/sz/60"));
        return f;
    }

    public static String d(long j) {
        return String.format("http://%s/img/share/id/%d", new Object[]{cn.xiaochuankeji.tieba.background.utils.d.a.c(), Long.valueOf(j)});
    }

    public static String e(long j) {
        return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, "/sz/origin");
    }

    public static RectF a(float f, float f2) {
        float f3 = 120.0f;
        if (f == 0.0f || f2 == 0.0f) {
            return new RectF(0.0f, 0.0f, 90.0f, 120.0f);
        }
        float f4 = (120.0f * 2.0f) / 5.0f;
        if (f * 2.0f <= 120.0f && f2 * 2.0f <= 120.0f) {
            return new RectF(0.0f, 0.0f, f, f2);
        }
        if (f / f2 < f4 / 120.0f) {
            f3 = Math.min(120.0f, (f2 * f4) / f);
        } else if (f / f2 >= 120.0f / f4) {
            float f5 = f4;
            f4 = Math.min(120.0f, (f * f4) / f2);
            f3 = f5;
        } else if (f < f2) {
            f4 = Math.min(120.0f, (f * 120.0f) / f2);
        } else {
            f4 = 120.0f;
            f3 = Math.min(120.0f, (f2 * 120.0f) / f);
        }
        return new RectF(0.0f, 0.0f, f4, f3);
    }

    public static RectF a(Resources resources, float f, float f2) {
        if (f == 0.0f || f2 == 0.0f) {
            return new RectF(0.0f, 0.0f, 90.0f, 120.0f);
        }
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float f3 = ((float) displayMetrics.widthPixels) / displayMetrics.density;
        if (f * 2.0f > f3 || f2 * 2.0f > f3) {
            return new RectF(0.0f, 0.0f, f3, (f2 / f) * f3);
        }
        return new RectF(0.0f, 0.0f, f, f2);
    }
}
