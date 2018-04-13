package cn.xiaochuankeji.tieba.d;

import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;

public class a {
    public static int a = 0;
    public static int b = 1;
    public static a c;
    public static int d = 0;
    public static int e = 1;
    public static int f = 0;
    public static int g = 1;
    public static int h = 0;
    public static int i = 1;

    public static class a {
        public int a = a.b;
        public int b = a.e;
        public int c = a.g;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
                c.a = cn.xiaochuankeji.tieba.background.a.a().getInt("tack_recommend_cache", c.a);
                c.b = cn.xiaochuankeji.tieba.background.a.a().getInt("tack_auto_refresh", c.b);
                c.c = cn.xiaochuankeji.tieba.background.a.a().getInt("tack_auto_refresh_more", c.c);
            }
            aVar = c;
        }
        return aVar;
    }

    public static int b() {
        return a().a;
    }

    public static int c() {
        return a().b;
    }

    public static int d() {
        return a().c;
    }

    public static int e() {
        int i = h;
        GrayConfigBean C = cn.xiaochuankeji.tieba.background.utils.c.a.c().C();
        if (C == null || C.abPicTackWebp != i) {
            return i;
        }
        return i;
    }
}
