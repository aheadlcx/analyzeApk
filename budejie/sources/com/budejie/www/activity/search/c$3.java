package com.budejie.www.activity.search;

import com.budejie.www.activity.label.f;
import java.util.List;
import net.tsz.afinal.a.a;

class c$3 extends a<String> {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        List d = f.d(str);
        if (d != null) {
            c.a(this.a, d);
            c.b(this.a).a(c.a(this.a));
        }
    }

    public void onFailure(Throwable th, int i, String str) {
    }
}
