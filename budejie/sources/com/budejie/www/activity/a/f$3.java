package com.budejie.www.activity.a;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class f$3 implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ Dialog b;
    final /* synthetic */ f c;

    f$3(f fVar, String str, Dialog dialog) {
        this.c = fVar;
        this.a = str;
        this.b = dialog;
    }

    public void onClick(View view) {
        f.b(this.c, this.a);
        this.b.dismiss();
    }
}
