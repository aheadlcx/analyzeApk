package com.budejie.www.activity.phonenumber;

import com.budejie.www.adapter.a.i;
import com.budejie.www.util.z;
import java.util.List;
import net.tsz.afinal.a.a;

class d$2 extends a {
    final /* synthetic */ d a;

    d$2(d dVar) {
        this.a = dVar;
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        this.a.c();
    }

    public void onSuccess(Object obj) {
        this.a.c();
        List i = z.i(obj.toString());
        if (i != null && i.size() > 0) {
            d.a(this.a, i);
            d.c(this.a).setAdapter(new i(d.a(this.a), d.b(this.a)));
            d.d(this.a).b("recommend_user");
            d.d(this.a).c(i);
        }
    }
}
