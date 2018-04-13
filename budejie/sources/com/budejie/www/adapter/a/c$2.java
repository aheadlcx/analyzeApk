package com.budejie.www.adapter.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.activity.label.h;

class c$2 implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ c b;

    c$2(c cVar, int i) {
        this.b = cVar;
        this.a = i;
    }

    public void onClick(View view) {
        h.a(c.a(this.b), ((LabelBean) c.b(this.b).get(this.a)).getTheme_id(), ((LabelBean) c.b(this.b).get(this.a)).getTheme_name());
    }
}
