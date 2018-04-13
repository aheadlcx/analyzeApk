package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import cn.xiaochuan.base.BaseApplication;

public final class b {
    private static volatile b a;
    private final a b;

    private b(a aVar) {
        this.b = aVar;
    }

    public static a a() {
        if (a == null) {
            a = new b(e.a(BaseApplication.getAppContext()));
        }
        return a.b;
    }
}
