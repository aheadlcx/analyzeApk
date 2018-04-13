package com.budejie.www.activity.a;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class f$2 implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ f b;

    f$2(f fVar, Dialog dialog) {
        this.b = fVar;
        this.a = dialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
