package com.budejie.www.activity.detail;

import android.view.View;
import android.view.View.OnClickListener;

class c$20 implements OnClickListener {
    final /* synthetic */ c a;

    c$20(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        if (c.s(this.a) != null) {
            c.s(this.a).setVisibility(8);
        }
    }
}
