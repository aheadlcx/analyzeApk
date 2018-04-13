package com.budejie.www.activity.detail;

import android.view.View;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.widget.NewTitleView.a;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;

class c$3 implements a {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public void a(View view, ListItemObject listItemObject) {
        if (c.u(this.a) != null) {
            c.u(this.a).a(view, c.d(this.a));
        }
    }

    public void b(View view, ListItemObject listItemObject) {
        if (c.u(this.a) != null) {
            c.u(this.a).b(view, c.d(this.a));
        }
    }

    public void a(View view) {
        c.a(this.a, view);
    }

    public void a(BarrageState barrageState) {
        if (k.a(c.c(this.a)).f != null) {
            k.a(c.c(this.a)).f.a(barrageState);
        }
        c.v(this.a);
    }

    public void c(View view, ListItemObject listItemObject) {
        if (c.u(this.a) != null) {
            c.u(this.a).a(view, c.d(this.a), "");
        }
    }
}
