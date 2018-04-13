package com.budejie.www.activity.posts;

import com.budejie.www.R;
import com.budejie.www.util.an;
import com.budejie.www.widget.xrecyclerview.XRecyclerView$c;

class b$5 implements XRecyclerView$c {
    final /* synthetic */ b a;

    b$5(b bVar) {
        this.a = bVar;
    }

    public void a() {
        if (an.a(b.b(this.a))) {
            b.f(this.a);
            return;
        }
        b.a(this.a, b.b(this.a).getString(R.string.nonet));
        b.g(this.a).a(false);
    }

    public void b() {
        if (an.a(b.b(this.a))) {
            this.a.c();
            return;
        }
        b.a(this.a, b.b(this.a).getString(R.string.nonet));
        b.g(this.a).b();
    }
}
