package com.budejie.www.adapter.c;

import android.view.View;
import com.budejie.www.adapter.g.a.a;
import com.budejie.www.bean.ListItemObject;

class b$1 extends a {
    View a = new View(b.a(this.b));
    final /* synthetic */ b b;

    b$1(b bVar) {
        this.b = bVar;
    }

    public void a(ListItemObject listItemObject) {
        listItemObject.getAdItem().onClicked(this.a);
    }
}
