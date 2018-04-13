package com.tencent.connect.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.tencent.tauth.IUiListener;

class h implements OnCancelListener {
    final /* synthetic */ IUiListener a;
    final /* synthetic */ Object b;
    final /* synthetic */ b c;

    h(b bVar, IUiListener iUiListener, Object obj) {
        this.c = bVar;
        this.a = iUiListener;
        this.b = obj;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a != null) {
            this.a.onComplete(this.b);
        }
    }
}
