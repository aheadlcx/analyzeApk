package com.budejie.www.activity.label.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.type.SearchItem;
import com.budejie.www.type.SearchItem$TypeEnum;
import com.budejie.www.util.a;

class c$1 implements OnClickListener {
    final /* synthetic */ SearchItem a;
    final /* synthetic */ c b;

    c$1(c cVar, SearchItem searchItem) {
        this.b = cVar;
        this.a = searchItem;
    }

    public void onClick(View view) {
        if (c.a(this.b)) {
            if (this.a.getType() != SearchItem$TypeEnum.ADD_MODERATOR.getValue()) {
                a.a(c.c(this.b), this.a.getId());
                this.b.notifyDataSetChanged();
            } else if (c.b(this.b) != null) {
                c.b(this.b).a();
            }
        } else if (c.b(this.b) != null) {
            c.b(this.b).a(this.a);
        }
    }
}
