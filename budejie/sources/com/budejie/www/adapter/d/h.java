package com.budejie.www.adapter.d;

import android.app.Activity;
import android.view.View;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.adapter.g.b.p;
import com.budejie.www.adapter.g.c;
import com.budejie.www.adapter.g.d;
import com.budejie.www.bean.ListItemObject;

public class h extends a {
    protected final Activity a;
    protected final c b;
    protected final b<ListItemObject> c;

    public /* synthetic */ Object d() {
        return a();
    }

    public h(Activity activity, c cVar, b<ListItemObject> bVar) {
        this.a = activity;
        this.b = cVar;
        this.c = bVar;
    }

    public View b() {
        p pVar = new p(this.a, this.c);
        View a = pVar.a(null);
        com.budejie.www.adapter.b bVar = new com.budejie.www.adapter.b();
        bVar.F = d.a(this.a, pVar.e, this.b, this.c);
        a.setTag(bVar);
        return a;
    }

    public int c() {
        return this.c.d.ordinal();
    }

    public void a(com.budejie.www.adapter.b bVar) {
        for (com.budejie.www.adapter.g.a a : bVar.F) {
            a.a(this.c);
        }
    }

    public ListItemObject a() {
        return (ListItemObject) this.c.b;
    }
}
