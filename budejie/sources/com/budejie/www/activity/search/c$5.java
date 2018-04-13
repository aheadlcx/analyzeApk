package com.budejie.www.activity.search;

import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.XListView.a;

class c$5 implements a {
    final /* synthetic */ c a;

    c$5(c cVar) {
        this.a = cVar;
    }

    public void a() {
    }

    public void b() {
        NetWorkUtil netWorkUtil = BudejieApplication.a;
        RequstMethod requstMethod = RequstMethod.GET;
        String f = j.f("" + c.f(this.a));
        c.g(this.a);
        netWorkUtil.a(requstMethod, f, j.r(this.a.getActivity(), this.a.f), new c$a(this.a, 1, null));
    }
}
