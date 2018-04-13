package com.budejie.www.activity.labelsubscription;

import com.budejie.www.util.z;
import java.util.List;
import net.tsz.afinal.a.a;

class c$3 extends a {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public void onFailure(Throwable th, int i, String str) {
    }

    public void onSuccess(final Object obj) {
        new Thread(new Runnable(this) {
            final /* synthetic */ c$3 b;

            public void run() {
                List h = z.h(obj.toString());
                if (h != null && h.size() > 0) {
                    c.v(this.b.a).b("recommend_Label");
                    c.v(this.b.a).b(h);
                }
            }
        }).start();
    }
}
