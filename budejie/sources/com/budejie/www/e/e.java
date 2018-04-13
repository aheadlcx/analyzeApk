package com.budejie.www.e;

import android.graphics.drawable.Drawable;
import com.budejie.www.R;
import com.nostra13.universalimageloader.core.b.a;
import com.nostra13.universalimageloader.core.b.b;
import com.nostra13.universalimageloader.core.b.d;
import com.nostra13.universalimageloader.core.c;

public class e {
    private static c a;
    private static c b;
    private static c c;
    private static c d;
    private static c e;
    private static c f;
    private static c g;
    private static c h;
    private static c i;
    private static c j;
    private static c k;
    private static c l;

    public static c a() {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = d.a(R.drawable.label_default_icon, R.drawable.label_default_icon, new d());
                }
            }
        }
        return a;
    }

    public static c b() {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = d.a(new d());
                }
            }
        }
        return b;
    }

    public static c c() {
        if (i == null) {
            synchronized (e.class) {
                if (i == null) {
                    i = d.a((int) R.drawable.defaut_bg, (int) R.drawable.defaut_bg);
                }
            }
        }
        return i;
    }

    public static c d() {
        if (j == null) {
            synchronized (e.class) {
                if (j == null) {
                    j = d.a((int) R.drawable.likelist_defaut_bg, (int) R.drawable.likelist_defaut_bg);
                }
            }
        }
        return j;
    }

    public static c e() {
        if (k == null) {
            synchronized (e.class) {
                if (k == null) {
                    k = d.a((int) R.drawable.search_duanzi_bg, (int) R.drawable.search_duanzi_bg);
                }
            }
        }
        return k;
    }

    public static c f() {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = d.a((int) R.drawable.lable_default_bg, (int) R.drawable.lable_default_bg);
                }
            }
        }
        return c;
    }

    public static c g() {
        if (d == null) {
            synchronized (e.class) {
                if (d == null) {
                    d = d.a((int) R.drawable.rich_post_no_pic, (int) R.drawable.rich_post_no_pic);
                }
            }
        }
        return d;
    }

    public static c h() {
        if (d == null) {
            synchronized (e.class) {
                if (d == null) {
                    d = d.a((int) R.drawable.recommend_video_defaut_bg, (int) R.drawable.recommend_video_defaut_bg);
                }
            }
        }
        return d;
    }

    public static c i() {
        if (f == null) {
            synchronized (e.class) {
                if (f == null) {
                    f = d.a(new d());
                }
            }
        }
        return f;
    }

    public static c a(int i) {
        if (e == null) {
            synchronized (e.class) {
                if (e == null) {
                    if (i == 1) {
                        e = d.a(R.drawable.head_portrait, R.drawable.head_portrait, new b());
                    } else if (i == 2) {
                        e = d.a(R.drawable.head_portrait, R.drawable.head_portrait, new b());
                    } else if (i == 0) {
                        e = d.a(new b());
                    }
                }
            }
        }
        return e;
    }

    public static c a(Drawable drawable, a aVar) {
        if (g == null) {
            synchronized (e.class) {
                if (g == null) {
                    g = d.a(aVar, drawable);
                }
            }
        }
        return g;
    }

    public static c b(Drawable drawable, a aVar) {
        if (h == null) {
            synchronized (e.class) {
                if (h == null) {
                    h = d.a(aVar, drawable);
                }
            }
        }
        return h;
    }

    public static c j() {
        if (l == null) {
            synchronized (e.class) {
                if (l == null) {
                    l = d.a();
                }
            }
        }
        return l;
    }
}
