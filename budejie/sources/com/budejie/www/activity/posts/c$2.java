package com.budejie.www.activity.posts;

import android.view.View;
import android.view.View.OnClickListener;

class c$2 implements OnClickListener {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        if (c.c(this.a).canGoBack()) {
            c.c(this.a).goBack();
        }
    }
}
