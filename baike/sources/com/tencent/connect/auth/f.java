package com.tencent.connect.auth;

import android.app.Dialog;
import android.view.View;
import com.tencent.tauth.IUiListener;

class f extends a {
    final /* synthetic */ IUiListener a;
    final /* synthetic */ Object b;
    final /* synthetic */ b c;

    f(b bVar, Dialog dialog, IUiListener iUiListener, Object obj) {
        this.c = bVar;
        this.a = iUiListener;
        this.b = obj;
        super(bVar, dialog);
    }

    public void onClick(View view) {
        this.c.a();
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
        if (this.a != null) {
            this.a.onComplete(this.b);
        }
    }
}
