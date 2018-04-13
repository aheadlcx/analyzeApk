package com.budejie.www.activity.label;

import android.view.View;
import android.view.View.OnClickListener;

class a$1 implements OnClickListener {
    final /* synthetic */ LabelBean a;
    final /* synthetic */ a b;

    a$1(a aVar, LabelBean labelBean) {
        this.b = aVar;
        this.a = labelBean;
    }

    public void onClick(View view) {
        h.a(a.a(this.b), this.a.getTheme_id(), this.a.getTheme_name());
    }
}
