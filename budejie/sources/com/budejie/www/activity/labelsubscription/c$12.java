package com.budejie.www.activity.labelsubscription;

import android.widget.Toast;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.tsz.afinal.a.a;

class c$12 extends a {
    final /* synthetic */ c a;

    c$12(c cVar) {
        this.a = cVar;
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        c.e(this.a);
        c.a(this.a, true);
        c.m(this.a);
    }

    public void onSuccess(Object obj) {
        c.e(this.a);
        c.a(this.a, false);
        if (obj == null) {
            Toast.makeText(c.c(this.a), "请求数据为空", 0).show();
            return;
        }
        String obj2 = obj.toString();
        c.n(this.a).clear();
        c.b(this.a, z.g(obj2));
        if (!c.o(this.a)) {
            if (c.n(this.a) == null || c.n(this.a).size() <= 0) {
                Toast.makeText(c.c(this.a), "无数据", 0).show();
                return;
            }
            int i;
            c.p(this.a).clear();
            c.q(this.a).clear();
            List arrayList = new ArrayList();
            for (i = 0; i < c.n(this.a).size(); i++) {
                if ("r".equals(((RecommendSubscription) c.n(this.a).get(i)).getType())) {
                    c.p(this.a).add(c.n(this.a).get(i));
                } else {
                    c.q(this.a).add(c.n(this.a).get(i));
                    arrayList.add(((RecommendSubscription) c.n(this.a).get(i)).getTheme_id());
                }
            }
            for (i = c.p(this.a).size() - 1; i >= 0; i--) {
                if (arrayList.contains(((RecommendSubscription) c.p(this.a).get(i)).getTheme_id())) {
                    c.p(this.a).remove(i);
                }
            }
            c.r(this.a).clear();
            c.c(this.a, this.a.a(c.q(this.a)));
            c.n(this.a).clear();
            if (c.p(this.a) != null && c.p(this.a).size() > 0) {
                c.n(this.a).addAll(c.p(this.a));
            }
            if (c.r(this.a) != null && c.r(this.a).size() > 0) {
                c.n(this.a).addAll(c.r(this.a));
            }
            Collection a = c.s(this.a).a(c.n(this.a));
            c.n(this.a).clear();
            c.n(this.a).addAll(a);
            new Thread(new Runnable(this) {
                final /* synthetic */ c$12 a;

                {
                    this.a = r1;
                }

                public void run() {
                    c.t(this.a.a);
                }
            }).start();
            c.l(this.a).setAdapter(c.s(this.a));
            an.a(c.l(this.a));
        }
    }
}
