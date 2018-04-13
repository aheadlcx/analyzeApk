package com.budejie.www.activity.label.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.type.SearchItem;

class c$2 implements OnClickListener {
    final /* synthetic */ SearchItem a;
    final /* synthetic */ c b;

    c$2(c cVar, SearchItem searchItem) {
        this.b = cVar;
        this.a = searchItem;
    }

    public void onClick(View view) {
        if (c.b(this.b) != null) {
            c.b(this.b).b(this.a);
        }
    }
}
