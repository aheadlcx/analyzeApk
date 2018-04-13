package com.budejie.www.activity.labelsubscription;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class c$7 implements OnFocusChangeListener {
    final /* synthetic */ c a;

    c$7(c cVar) {
        this.a = cVar;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            c.c(this.a).getParent().getWindow().setSoftInputMode(35);
        } else {
            c.c(this.a).getParent().getWindow().setSoftInputMode(19);
        }
    }
}
