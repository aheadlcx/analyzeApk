package com.budejie.www.activity.a;

import com.budejie.www.R;
import com.budejie.www.activity.mycomment.d;
import com.budejie.www.activity.mycomment.f;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.util.an;
import net.tsz.afinal.a.a;

class f$4 extends a<String> {
    final /* synthetic */ f a;

    f$4(f fVar) {
        this.a = fVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        f.d(this.a).a();
    }

    public void a(String str) {
        f.d(this.a).b();
        f.d(this.a);
        if ("comments_type".equals(f.d(this.a).g)) {
            ListInfo a = com.budejie.www.j.a.a(str);
            if (a.np == 0) {
                f.d(this.a).a("comments_type", true);
                f.f(this.a).b();
                if (f.g(this.a).a() != 5) {
                    f.h(this.a).setPullLoadEnable(false);
                }
                f.g(this.a).a(false);
                return;
            }
            f.c(this.a, String.valueOf(a.np));
            f f = com.budejie.www.j.a.f(f.d(this.a), str);
            if (f != null) {
                Object obj = f.a;
                if (obj == null || obj.isEmpty()) {
                    f.d(this.a).a("comments_type", true);
                } else {
                    f.d(this.a).a("comments_type", false);
                    f.a(this.a, obj);
                    an.b(obj, f.b(this.a), f.c(this.a));
                    f.f(this.a).a(obj);
                    this.a.a.clear();
                    this.a.a.addAll(obj);
                    if (f.g(this.a).a() != 5) {
                        f.h(this.a).setPullLoadEnable(true);
                    }
                    f.g(this.a).a(true);
                    try {
                        k.a(f.d(this.a), ((d) obj.get(0)).c.getVideouri());
                    } catch (Exception e) {
                        e.printStackTrace();
                        k.a(f.d(this.a)).p();
                    }
                }
            }
            f.g(this.a).a(2);
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        f.d(this.a).b();
        f.g(this.a).a(2);
        an.a(f.d(this.a), f.d(this.a).getString(R.string.parse_failed), -1).show();
    }
}
