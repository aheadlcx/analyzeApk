package com.budejie.www.activity.search;

import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.widget.XListView.a;

class d$1 implements a {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void a() {
    }

    public void b() {
        int a = d.a(this.a) + 1;
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", d.b(this.a).e(this.a.getActivity(), this.a.f, Integer.toString(a)), new d$a(this.a, a, null));
    }
}
