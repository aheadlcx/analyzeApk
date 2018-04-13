package com.budejie.www.activity.labelsubscription;

import android.widget.Toast;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class c$9 extends a {
    final /* synthetic */ c a;

    c$9(c cVar) {
        this.a = cVar;
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        c.e(this.a);
    }

    public void onSuccess(Object obj) {
        c.e(this.a);
        if (obj == null) {
            Toast.makeText(c.c(this.a), "数据为空", 0).show();
            return;
        }
        String obj2 = obj.toString();
        c.i(this.a).clear();
        c.a(this.a, z.h(obj2));
        if (c.i(this.a) == null || c.i(this.a).size() == 0) {
            Toast.makeText(c.c(this.a), "抱歉，没有该标签！", 0).show();
        } else if (c.i(this.a) != null && c.i(this.a).size() > 0 && c.g(this.a) != null) {
            c.j(this.a).setAdapter(c.g(this.a));
            c.b(this.a, "");
            c.g(this.a).a(c.i(this.a), c.k(this.a));
        }
    }
}
