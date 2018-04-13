package com.budejie.www.activity.label;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

class c$4 implements OnClickListener {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        this.a.a = new g(c.b(this.a), c.b(this.a).q, c.b(this.a).i, c.j(this.a), c.k(this.a), c.l(this.a), c.m(this.a), c.b(this.a).j);
        if (TextUtils.isEmpty(c.n(this.a))) {
            c.a(this.a, "这个太给力了，伙伴儿们一起来参加吧!");
        }
        this.a.a.a(null, c.n(this.a), c.c(this.a).getImage_list(), c.c(this.a).getInfo(), c.c(this.a).getShare(), "", "", "", "", c.c(this.a).getTheme_id());
    }
}
