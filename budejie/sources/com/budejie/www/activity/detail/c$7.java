package com.budejie.www.activity.detail;

import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.detail.a.a;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;

class c$7 implements a {
    final /* synthetic */ c a;

    c$7(c cVar) {
        this.a = cVar;
    }

    public void a(String str) {
        c.f(this.a, str);
    }

    public void b(String str) {
        c.a(this.a, str, "0");
    }

    public void c(String str) {
        c.a(this.a, str, "1");
    }

    public void d(String str) {
        k.a(c.c(this.a)).k();
        c.g(this.a, str);
    }

    public void a(CommentItem commentItem) {
        c.a(this.a, commentItem);
        c.M(this.a);
    }

    public void b(CommentItem commentItem) {
        if (commentItem != null) {
            c.a(this.a, commentItem);
            c.N(this.a).performClick();
            if (c.O(this.a) != null) {
                c.P(this.a).setText("回复：" + c.O(this.a).getUname());
            }
        }
    }

    public void e(String str) {
        c.R(this.a).a(c.c(this.a).getApplicationContext(), c.Q(this.a), str, c.B(this.a));
    }

    public void a() {
        c.a(this.a, 1);
        c.R(this.a).a(c.c(this.a), c.Q(this.a), c.S(this.a), "1", c.T(this.a));
    }

    public void a(int i, String str) {
        c.b(this.a, i);
        BudejieApplication.a.a(RequstMethod.GET, j.b(c.Q(this.a), str), c.U(this.a), c.V(this.a));
    }
}
