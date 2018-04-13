package com.budejie.www.http;

import android.app.Activity;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.f.a;

public class d {
    Activity a;
    a b;
    j c = new j();

    public d(Activity activity, a aVar) {
        this.a = activity;
        this.b = aVar;
    }

    public void a(int i, String str) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", this.c.o(this.a, str), this.b, i);
    }

    public void a(int i) {
        h hVar = new h("http://s.budejie.com/op2/promotion");
        hVar.b().a("-xiaomi").c("0", "20").a();
        BudejieApplication.a.a(this.a, hVar.toString(), "get", new j(this.a), this.b, null, i);
    }
}
